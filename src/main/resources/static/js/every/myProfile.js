/**
 * myProfile
 */

$(document).ready(function() {
    
	$._messengerDefaults = {
			extraClasses: 'messenger-fixed messenger-theme-future messenger-on-top'
		};
	
});

//修改手机号和邮箱
$('#authentication').click(function() {
	if($("#email").val() == ""){
			 $.globalMessenger().post({
		            message: "邮箱不能为空！",
		            hideAfter: 2,
		            type: 'error',
		            //showCloseButton: true
		        });
	}else{
		  $.post("/profile_authentication",{"phone" : $('#phone').val(), "email" : $('#email').val()},function(data){
	    	  if(data.msg == "success"){
				  $('.loginAfter').css('display','block');
	    	      $('.loginBefore').css('display','none');
	    	  	  $("#myphone").html(data.phone);
	    	  	  $("#myemail").html(data.email);
	    	  	  $("#myheademail").html(data.email);
	    	  	  $("#phone").val("");
	    	  	  $("#email").val("");
	    	  	  $('#authentication').removeAttr("disabled");
	    	  	  $("#form_authentication").data('bootstrapValidator').resetForm();
			      //提示成功
	    		  $.globalMessenger().post({
			          message: "保存成功",
			          hideAfter: 2,
			          type: 'success',
			            //showCloseButton: true
			      });
			  }else if(data.msg == "error"){
	  			 $.globalMessenger().post({
	  		            message: "验证错误，请重新输入！",
	  		            hideAfter: 2,
	  		            type: 'error',
	  		            //showCloseButton: true
	  		        });
			  	}
			  });
	}

//});
});

//修改密码
$('#updatepassword').click(function() {
	if($('#old_password').val() == "" || $('#new_password').val() == "" || $('#confirm_password').val() == ""){
		  $.globalMessenger().post({
	          message: "密码不能为空，请重新输入！",
	          hideAfter: 2,
	          type: 'error',
	            //showCloseButton: true
	      });
	}else{
		  $.post("/profile_updatepassword",{"oldpwd" : $('#old_password').val(), "newpwd" : $('#new_password').val(), "confirmpwd" : $('#confirm_password').val()},function(data){
			  if(data.data == "success"){
				  $('.loginAfter').css('display','block');
	    	      $('.loginBefore').css('display','none');
	    	  	  $("#confirm_password").val("");
	    	  	  $("#new_password").val("");
	    	  	  $("#old_password").val("");
	    	  	  $('#updatepassword').removeAttr("disabled");
	    	  	  $("#form_updatepassword").data('bootstrapValidator').resetForm();
			      //提示成功
	    		  $.globalMessenger().post({
			          message: "保存成功",
			          hideAfter: 2,
			          type: 'success',
			            //showCloseButton: true
			      });
			    }else if(data.data == "error"){
		    		  $.globalMessenger().post({
	    		          message: "验证错误，请确认是否输入正确",
	    		          hideAfter: 2,
	    		          type: 'error',
	    		            //showCloseButton: true
	    		      });
			    }
			    else{
		    		  $.globalMessenger().post({
	    		          message: "旧密码输入错误，请重新输入！",
	    		          hideAfter: 2,
	    		          type: 'error',
	    		            //showCloseButton: true
	    		      });
			    }
		});
	}
});

//修改基本信息
$('#basic').click(function(){
	if($('#name').val() == ""){
			 $.globalMessenger().post({
		            message: "姓名不能为空，请重新输入",
		            hideAfter: 2,
		            type: 'error',
		            //showCloseButton: true
		        });
	}else{
		  $.post("/profile_basic",{"address" : $('#address').val(), "name" : $('#name').val(), "birth" : $('#birth').val(), "hobby" : $('#hobby').val(), "sign" : $('#sign').val(), "sex" : $('input:radio[name="gender"]:checked').val()},function(data){
			    if(data.data == "success"){
		    		  $('.loginAfter').css('display','block');
		    	      $('.loginBefore').css('display','none');
		    	  	  $("#myaddress").html($('#address').val());
		    	  	  $("#myusername").html($('#name').val());
		    	  	  $("#myusernametop").html($('#name').val());
		    	  	  $("#myusernamehead").html($('#name').val());
		    	  	  $("#mybirthday").html($('#birth').val());
		    	  	  $("#myhobby").html($('#hobby').val());
		    	  	  $("#mysign").html($('#sigh').val());
		    	  	  $("#mysex").html($('input:radio[name="gender"]:checked').val());
		    	  	  $("#address").val("");
		    	  	  $("#name").val("");
		    	  	  $("#birth").val("");
		    	  	  $("#hobby").val("");
		    	  	  $("#sign").val("");
		    	  	  $("#gender1").removeAttr('checked');
		    	  	  $("#gender2").removeAttr('checked');
		    		  $.globalMessenger().post({
				          message: "保存成功",
				          hideAfter: 2,
				          type: 'success',
				            //showCloseButton: true
				      });
			    }else{
	   			 $.globalMessenger().post({
			            message: "验证错误，请重新输入",
			            hideAfter: 2,
			            type: 'error',
			            //showCloseButton: true
			        });
			    }
			  });
	}
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


