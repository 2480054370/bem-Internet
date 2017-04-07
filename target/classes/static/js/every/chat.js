/**
 * 
 */
var thisUser = null;
//var thisState = null;
var outputname = "111111";
var message = null;
var stompClient = null;

window.onload = function() {
	
	  $._messengerDefaults = {
				extraClasses: 'messenger-fixed messenger-theme-future messenger-on-top'
			};
	
	  $.post("/initialization",function(data){
    	  thisUser = data.thisUser;
    	  
    	  if(data.unreadUser == ""){

    	  }else{
    		  var unread = null;
	    	  for(var item in data.unreadUser){
	    		  if(unread == data.unreadUser[item]){
	    			  continue;
	    		  }else{
	    			  unread = data.unreadUser[item];
			    	  for(var i = 0; i < $('.lv-item.media.user').size(); i++){
			  				if($('.lv-item.media.user:eq('+ i +')').find(".hidden_text").text() == unread){
			      				$('.lv-item.media.user:eq('+ i +')').addClass("unread");
			      				break;
			      			}
			  			}
	    		  }
	    	  }
    	  }
    	  
    	  if(data.unreadUserNull == ""){

    	  }else{
    		  var unread = null;
	    	  for(var item in data.unreadUserNull){
	    		  if(unread == data.unreadUserNull[item]){
	    			  continue;
	    		  }else{
	    			  unread = data.unreadUserNull[item];
			    	  for(var i = 0; i < $('.lv-item.media.user').size(); i++){
			  				if($('.lv-item.media.user:eq('+ i +')').find(".hidden_text").text() == unread){
			      				$('.lv-item.media.user:eq('+ i +')').addClass("unread");
			      				break;
			      			}
			  			}
	    		  }
	    	  }
    	  }
		  });
	
	WebsocketInit();
};

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#send" ).click(function() { 
    	message = $('textarea[type="text"]').val();
    	$('textarea[type="text"]').focus();
    	sendName(); 
    });
});


function sendName() {
	console.log("111");
	$('.lv-item.media.chat:last').after('<div class="lv-item media chat right"><div class="lv-avatar pull-right"><img src="images/tou.jpg" alt=""></div><div class="media-body"><div class="ms-item">' + message + '</div></div></div>');
    stompClient.send("/app/" + "sendChat", {}, JSON.stringify({'inputname': thisUser, 'outputname' : outputname, 'message' : message}));
    $('.lv-body.chat').scrollTop( $('.lv-body.chat')[0].scrollHeight );
    $('textarea[type="text"]').val('');
    
	
	  $.post("/chat_check",{"inputname" : thisUser, "outputname" : outputname, "message" : message, "state" : "-2"},function(data){
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

function WebsocketInit(){
    var socket = new SockJS('/websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/' + thisUser, function (greeting) {
        	console.log("11------------------" + greeting.body);
        	var obj = JSON.parse(greeting.body);
    		var state = null;
    		console.log(obj.Inname);
    		if($('.lv-item.media.user.active').find(".hidden_text").text() == obj.Inname){
    			state = "1";
    			$('.lv-item.media.chat:last').after('<div class="lv-item media chat"><div class="lv-avatar pull-left"><img src="images/tou.jpg" alt=""></div><div class="media-body"><div class="ms-item">' + obj.message + '</div></div></div>');
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
}

$('.test').click(function(){
	  $.post("/test",function(data){});
});

$('.listview.lv-user').click(function(e){
	$('textarea[type="text"]').focus();
	$('.lv-item.media.user.active').removeClass("active");
	$(this).find(".lv-item.media.user.unread").removeClass("unread");
	$(this).find(".lv-item.media.user").addClass("active");
	outputname = $(this).find(".hidden_text").text();
	
	$(".lv-body.chat").empty();
	$(".lv-body.chat").append('<div class="lv-item media chat"></div>');
	
	
	  $.post("/get_message",{"inputname" : thisUser, "outputname" : outputname},function(data){
			for(var item in data.message){
	    		  if(data.thisUser[item] == thisUser){
	    			  $('.lv-item.media.chat:last').after('<div class="lv-item media chat right"><div class="lv-avatar pull-right"><img src="images/tou.jpg" alt=""></div><div class="media-body"><div class="ms-item">' + data.message[item] + '</div><small class="ms-date"><i class="material-icons">access_time</i> '+ data.thisTime[item] +'</small></div></div>');
	    		  }else{
	    			  $('.lv-item.media.chat:last').after('<div class="lv-item media chat"><div class="lv-avatar pull-left"><img src="images/tou.jpg" alt=""></div><div class="media-body"><div class="ms-item">' + data.message[item] + '</div><small class="ms-date"><i class="material-icons">access_time</i> '+ data.thisTime[item] +'</small></div></div>');
	    		  }	  
			}
			$('.lv-body.chat').scrollTop( $('.lv-body.chat')[0].scrollHeight );
		  });
	
	
	  $.post("/save_state",{"inputname" : outputname, "outputname" : thisUser,"state" : "1"},function(data){});
});
