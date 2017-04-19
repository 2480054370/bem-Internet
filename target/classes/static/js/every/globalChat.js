/**
 * 设置全局消息显示
 */
window.onload = function() {
	  $.post("/initialization",function(data){
    	  
    	  if(data.unreadUser == "" & data.unreadUserNull == ""){

    	  }else if(data.noLogin == "noLogin"){
    		  console.log("noLogin");
    	  	}else{
    	  		console.log(data.msgTotal);
      		  $('#info-btn').find('.material-icons').after('<span class="tmn-counts">' + data.msgTotal + '</span>')
    	  	}
		  });
};