/**
 * 
 */
	$(document).ready(function(){
		
		  $._messengerDefaults = {
					extraClasses: 'messenger-fixed messenger-theme-future messenger-on-top'
				};
		
		  for(var i = 1; i <= 5; i++){
    		  if(document.getElementById("purview" + i).options[0].text == "公开") {
    			  document.getElementById("purview" + i).options.add(new Option("私密","私密"));
    			  document.getElementById("purview" + i).options.add(new Option("好友可见","好友可见"));
    		  }
    		  if(document.getElementById("purview" + i).options[0].text == "私密") {
    			  document.getElementById("purview" + i).options.add(new Option("公开","公开"));
    			  document.getElementById("purview" + i).options.add(new Option("好友可见","好友可见"));
    		  }	
    		  if(document.getElementById("purview" + i).options[0].text == "好友可见") {
    			  document.getElementById("purview" + i).options.add(new Option("公开","公开"));
    			  document.getElementById("purview" + i).options.add(new Option("私密","私密"));
    		  }	
		  }
		  for(var i = 1; i <= 4; i++){
    		  if($("#checkbox" + i).val() == "true"){
    			  $("#checkbox" + i).attr("checked",true);
    		  }else if($("#checkbox" + i).val() == "false"){
    			  $("#checkbox" + i).attr("checked",false);
    		  }
		  }
	});
	
	$('#infoconfig').click(function(){
		$.post("/config_infoconfig",{"cfphonenumber" : $("#purview1").val(), "cfaddress" : $("#purview2").val(), "cfbirthday" : $("#purview3").val(), "cfsex" : $("#purview4").val(), "cfhobby" : $("#purview5").val()},function(data){
		    	if(data.data == "success"){
		    		  $.globalMessenger().post({
	    		          message: "保存成功",
	    		          hideAfter: 2,
	    		          type: 'success',
	    		      });
		    	}
		  });
	});
	
	$('#messagenotify').click(function(){
		$.post("/config_messagenotify",{"cfinfo" : $("#checkbox1").is(':checked'), "cfphoto" : $("#checkbox2").is(':checked'), "cfreport" : $("#checkbox3").is(':checked'), "cfmail" : $("#checkbox4").is(':checked')},function(data){
	    	if(data.data == "success"){
	    		  $.globalMessenger().post({
    		          message: "保存成功",
    		          hideAfter: 2,
    		          type: 'success',
    		      });
	    	}
	  });
	});
	
	//头像上传

	//实时预览
	$("#uploadFile").change(function () {
	    var fil = this.files;
	    for (var i = 0; i < fil.length; i++) {
	        reads(fil[i]);
	    }
	});
	function reads(fil){
	    var reader = new FileReader();
	    reader.readAsDataURL(fil);
	    reader.onload = function()
	    {
	    	$("#head-loader").css('display','block');
	        $("#headimg").attr("src",reader.result);
	    	$("#uploadhead").ajaxSubmit({
	    		type: 'POST',  
	    		url: "/uploadAvatar" ,  
	    		success: function(data){
	    			if(data.msg == "上传成功"){
	    	    		  $.globalMessenger().post({
	      		          message: "保存成功",
	      		          hideAfter: 2,
	      		          type: 'success',
	      		            //showCloseButton: true
	      		      });
	    				$("#headimg").attr("src","http://oihey4yi1.bkt.clouddn.com/" + data.file);
	    				$("#headimg2").attr("src","http://oihey4yi1.bkt.clouddn.com/" + data.file);
	    				$("#headimg3").attr("src","http://oihey4yi1.bkt.clouddn.com/" + data.file);
	    				$("#head-loader").css('display','none');
	    			}else if(data.msg == "上传失败"){
		    			 $.globalMessenger().post({
		    		            message: "保存失败，请检查网络设置",
		    		            hideAfter: 2,
		    		            type: 'error',
		    		            //showCloseButton: true
		    		        });
		    			 $("#head-loader").css('display','none');
	    			}
	    		},  
	    		error: function(XmlHttpRequest, textStatus, errorThrown){  
		    			 $.globalMessenger().post({
		    		            message: "Serious error",
		    		            hideAfter: 2,
		    		            type: 'error',
		    		            //showCloseButton: true
		    		        });
		    			 $("#head-loader").css('display','none');
	    		}  
	    	});
	    };
	}

	//背景上传
	$("#uploadFile2").change(function () {
	    var fil = this.files;
	    for (var i = 0; i < fil.length; i++) {
	        reads2(fil[i]);
	    }
	});
	function reads2(fil){
	    var reader = new FileReader();
	    reader.readAsDataURL(fil);
	    reader.onload = function()
	    {
	    	$("#banner-loader").css('display','block');
	        $("#bannerimg").attr("src",reader.result);
	    	$("#uploadbanner").ajaxSubmit({
	    		type: 'POST',  
	    		url: "/uploadbanner" ,  
	    		success: function(data){  
	    			if(data.msg == "上传成功"){
	    	    		  $.globalMessenger().post({
	      		          message: "保存成功",
	      		          hideAfter: 2,
	      		          type: 'success',
	      		            //showCloseButton: true
	      		      });
	    	    		$("#bannerimg").attr("src","http://oihey4yi1.bkt.clouddn.com/" + data.file);
	    	    		$("#banner-loader").css('display','none');
	    			}else if(data.msg == "上传失败"){
	  	    			 $.globalMessenger().post({
	  	    		            message: "保存失败，请检查网络设置",
	  	    		            hideAfter: 2,
	  	    		            type: 'error',
	  	    		            //showCloseButton: true
	  	    		        });
	  	    			$("#banner-loader").css('display','none');
	    			}
	    		},  
	    		error: function(XmlHttpRequest, textStatus, errorThrown){  
		    			 $.globalMessenger().post({
		    		            message: "Serious error",
		    		            hideAfter: 2,
		    		            type: 'error',
		    		            //showCloseButton: true
		    		        });
		    			 $("#banner-loader").css('display','none');
	    		}  
	    	}); 
	    };
	}