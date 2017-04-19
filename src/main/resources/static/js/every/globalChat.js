/**
 * 设置全局消息显示
 */
window.onload = function() {
	  thisUser = $('.studentld').text();
	  $.post("/initialization",function(data){
    	  
    	  if(data.unreadUser == ""){

    	  }else if(data.noLogin == "noLogin"){
    		  console.log("noLogin");
    	  	}else{
      		  $('#info-btn').find('.material-icons').after('<span class="tmn-counts">' + data.msgTotal + '</span>')
    	  	}
		  });
};

$('#clearNotice').on("click", function(){
	$('#msgNotice').empty();
	$('.tmn-counts').remove();
	/**
	 * 这里写清空后台
	 */
	/**
	 * ****** --- over
	 * ******新增消息提醒
	 */
});
