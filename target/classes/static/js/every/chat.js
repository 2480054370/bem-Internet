/**
 * 
 */
var thisUser = null;
//var thisState = null;
var outputname = "111111";
var message = null;
var stompClient = null;
var outputImg = null;
var inputImg = null;
var inputUsername = null;
var outputUsername = null;
var outputImg = null;


$(document).ready(function(){
	
	  $._messengerDefaults = {
				extraClasses: 'messenger-fixed messenger-theme-future messenger-on-top'
			};
	
	  $.post("/initialization",function(data){
    	  thisUser = data.thisUser;
    	  
    	  if(data.unreadUser == ""){

    	  }else{
	    		unread = data.unreadUser;
			    for(var i = 0; i < $('.lv-item.media.user').size(); i++){
			  		if($('.lv-item.media.user:eq('+ i +')').find(".hidden_text").text() == unread){
			  			$('.lv-item.media.user:eq('+ i +')').addClass("unread");
			      			break;
			      		}
			    }
    	  }
    	  
    	  if(data.outputname != "null"){
    		  outputname = data.outputname;
    		  getMessage();
    	  }
		  });
	
	//WebsocketInit();
});

$(function () {
    $("form").on('submit', function (e) {
        //e.preventDefault();
    });
    $( "#send" ).click(function() { 
    	message = $('textarea[type="text"]').val();
    	$('textarea[type="text"]').focus();
    	sendName(); 
    });
    //添加键盘事件
	$(document).keydown(function(e){
	    if(e.which == 13 && e.ctrlKey){
	    	message = $('textarea[type="text"]').val();
	    	$('textarea[type="text"]').focus();
	    	sendName(); 
	   }
	});
});


function sendName() {
	$('.lv-item.media.chat:last').after('<div class="lv-item media chat right"><div class="lv-avatar pull-right"><img src="http://oihey4yi1.bkt.clouddn.com/'+ inputImg +'" alt=""></div><div class="media-body"><div class="ms-item">' + message + '</div></div></div>');
    stompClient.send("/app/" + "sendChat", {}, JSON.stringify({'inputname': thisUser, 'outputname' : outputname, 'message' : message, 'outputImg' : inputImg, "inputUsername" : inputUsername }));
    $('.lv-body.chat').scrollTop( $('.lv-body.chat')[0].scrollHeight );
    $('textarea[type="text"]').val('');
    
	
	  $.post("/chat_check",{"inputname" : thisUser, "outputname" : outputname, "outputUsername" : outputUsername, "inputUsername" : inputUsername, "outputImg" : inputImg, "message" : message, "state" : "-1"},function(data){
    	  if(data.msg == "success"){
    			 $.globalMessenger().post({
    		            message: "发送成功",
    		            hideAfter: 3,
    		            type: 'success',
    		        });
    	  }else if(data.msg == "error"){
			 $.globalMessenger().post({
		            message: "发送失败",
		            hideAfter: 3,
		            type: 'error',
		        });
    	  }
		  });
}

/*function WebsocketInit(){
    var socket = new SockJS('/websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        //console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/' + thisUser, function (greeting) {
        	//console.log("11------------------" + greeting.body);
        	var obj = JSON.parse(greeting.body);
    		var state = null;
    		//console.log(obj.Inname);
    		if($('.lv-item.media.user.active').find(".hidden_text").text() == obj.Inname){
    			state = "1";
    			$('.lv-item.media.chat:last').after('<div class="lv-item media chat"><div class="lv-avatar pull-left"><img src="http://oihey4yi1.bkt.clouddn.com/'+ outputImg +'" alt=""></div><div class="media-body"><div class="ms-item">' + obj.message + '</div></div></div>');
    			$('.lv-body.chat').scrollTop( $('.lv-body.chat')[0].scrollHeight );
    		}else{
    			state = "-1";
    			for(var i = 0; i < $('.lv-item.media.user').size(); i++){
    				if($('.lv-item.media.user:eq('+ i +')').find(".hidden_text").text() == obj.Inname){
    					$('.lv-item.media.user:eq('+ i +')').addClass("unread");
        				break;
        			}
    			}
    		}
        	
        	  $.post("/save_state",{"inputname" : obj.Inname, "outputname" : obj.Ouname,"state" : state },function(data){});
        	
        });
    });
}*/

$('.test').click(function(){
	  $.post("/test",function(data){});
});

$('.listview.lv-user').click(function(e){
	outputname = $(this).find(".hidden_text").text();
	$(".lvh-label.hidden-xs").empty();
	getMessage();
});

function getMessage(){
	inputUsername = $(".myname").text();
	outputUsername = $("*[name="+outputname+"]").closest('.listview.lv-user').find(".outUsername").text();
	outputImg = $("#outputImg").text();
	$('textarea[type="text"]').focus();
	$('.lv-item.media.user.active').removeClass("active");
	$("*[name="+outputname+"]").closest('.listview.lv-user').find(".lv-item.media.user.unread").removeClass("unread");
	$("*[name="+outputname+"]").closest('.listview.lv-user').find(".lv-item.media.user").addClass("active");
	
	$(".lv-body.chat").empty();
	$(".lv-body.chat").append('<div class="lv-item media chat"></div>');
	
	  $.post("/get_message",{"inputname" : thisUser, "outputname" : outputname},function(data){
  		outputImg = data.outputImg;
		inputImg = data.inputImg;
			for(var item in data.message){
	    		  if(data.thisUser[item] == thisUser){
	    			  $('.lv-item.media.chat:last').after('<div class="lv-item media chat right"><div class="lv-avatar pull-right"><img src="http://oihey4yi1.bkt.clouddn.com/'+ inputImg +'" alt=""></div><div class="media-body"><div class="ms-item">' + data.message[item] + '</div><small class="ms-date"><i class="material-icons">access_time</i> '+ data.thisTime[item] +'</small></div></div>');
	    		  }else{
	    			  $('.lv-item.media.chat:last').after('<div class="lv-item media chat"><div class="lv-avatar pull-left"><img src="http://oihey4yi1.bkt.clouddn.com/'+ outputImg +'" alt=""></div><div class="media-body"><div class="ms-item">' + data.message[item] + '</div><small class="ms-date"><i class="material-icons">access_time</i> '+ data.thisTime[item] +'</small></div></div>');
	    		  }	  
			}
			$('.lv-body.chat').scrollTop( $('.lv-body.chat')[0].scrollHeight );
			$(".lvh-label.hidden-xs").append('<div class="lv-avatar pull-left"><img src="http://oihey4yi1.bkt.clouddn.com/'+ outputImg +'" alt=""> </div><span class="c-black">' + outputUsername + '</span>');
		  });
	
	
	  $.post("/save_state",{"inputname" : outputname, "outputname" : thisUser,"state" : "1"},function(data){});
}
