/**
 * 全局JS
 */
var thisUser = $('.studentld').text();
$(document).ready(function(){
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
				/**
				 * chatRoom
				 */
				for(var i = 0; i < $('.lv-item.media.user').size(); i++){
					if($('.lv-item.media.user:eq('+ i +')').find(".hidden_text").text() == obj.Inname){
						$('.lv-item.media.user:eq('+ i +')').addClass("unread");
						break;
					}
				}
				/**
				 * otherHTML
				 */
				//更新消息数量
				if($('.tmn-counts').length > 0){
					var msgTotal = $('.tmn-counts').text();
					msgTotal++;
					$('.tmn-counts').html(msgTotal);
				}else{
					$('#info-btn').find('.material-icons').after('<span class="tmn-counts">' + 1 + '</span>')
				}
				
				//更新消息内容
				if($('#msgNotice').length > 0){
					$('#msgNotice:first').before('<div id="msgNotice" class="lv-body"><a class="lv-item" href="/load_chatRoom/'+ obj.Inname +'"><div class="media"><div class="pull-left"><img class="lv-img-sm" src="http://oihey4yi1.bkt.clouddn.com/'+ obj.inputImge +'" alt=""></div><div class="media-body"><div class="lv-title"><span>'+ obj.inputUsername +'</span></div><small class="lv-small"><span>'+ obj.message +'</span></small></div></div></a></div>');
				}else{
					$('#msgRemind').after('<div id="msgNotice" class="lv-body"><a class="lv-item" href="/load_chatRoom/'+ obj.Inname +'"><div class="media"><div class="pull-left"><img class="lv-img-sm" src="http://oihey4yi1.bkt.clouddn.com/'+ obj.inputImge +'" alt=""></div><div class="media-body"><div class="lv-title"><span>'+ obj.inputUsername +'</span></div><small class="lv-small"><span>'+ obj.message +'</span></small></div></div></a></div>');
				}
			}
    	
			$.post("/save_state",{"inputname" : obj.Inname, "outputname" : obj.Ouname,"state" : state },function(data){});
			
		});
	});
});