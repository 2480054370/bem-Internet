/**
 * 设置全局消息显示
 */



$(document).ready(function(){
	  $.post("/initialization",function(data){
    	  
    	  if(data.unreadUser == ""){

    	  }else if(data.noLogin == "noLogin"){
    		  console.log("noLogin");
    	  	}else{
      		  $('#info-btn').find('.material-icons').after('<span class="tmn-counts">' + data.msgTotal + '</span>')
    	  	}
		  });
});

$('#clearNotice').on("click", function(){
	$('#msgNotice').empty();
	$('.tmn-counts').remove();
	/**
	 * 这里写清空后台
	 */
	/**
	 * ****** --- over
	 * ****** --- over
	 * ****** --- over
	 */
});
