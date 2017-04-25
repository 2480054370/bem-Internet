/**
 * login 
 */


$(document).ready(function() {
	
//	//添加键盘事件
//    $(document).keyup(function(event){
//  	  if(event.keyCode == 13){
//  		  if($("*[name='login_Form']").hasClass('form_toggle')){
//  			  	login();
//  			}else if ($("*[name='register_Form']").hasClass('form_toggle')){
//  				register();
//  			}else if ($("*[name='forget_Form']").hasClass('form_toggle')){
//  				forgetpwd();
//  			}else if ($("*[name='change_password_Form']").hasClass('form_toggle')){
//  				updatepwd();
//  			}
//  	  }
//  	});
	
	$._messengerDefaults = {
			extraClasses: 'messenger-fixed messenger-theme-future messenger-on-top'
		};
	
	
	$(document).on("click", "[name='register']", function(){
		$(".card-signup").removeClass("form_toggle");
		$("[name='register_Form']").addClass("form_toggle");
	})
	$(document).on("click", "[name='login']", function(){
		$(".card-signup").removeClass("form_toggle");
		$("[name='login_Form']").addClass("form_toggle");
	})
	$(document).on("click", "[name='forget']", function(){
		$(".card-signup").removeClass("form_toggle");
		$("[name='forget_Form']").addClass("form_toggle");
	})


});

//numvalue numsum 来自initalize.js
//注册
$(".register_btn").click(function register(){
	$('.register_validate').bootstrapValidator('validate');
	console.log(numvalue + "---" + numsum);
	if(numvalue != numsum){
		 $.globalMessenger().post({
	            message: "验证错误，请检查后重新输入",
	            hideAfter: 2,
	            type: 'error',
	            //showCloseButton: true
	        });
	}else{
		var url = $(this).parents("form").attr("action");
		var data = $(this).parents("form").serialize();
		$.post(url, data, function(data){
			console.log(data.data)
			if(data.data == "success"){
				$(".card-signup").removeClass("form_toggle");
				$("[name='login_Form']").addClass("form_toggle");
				$("*[name='register_Form']").find("input").val("");
				 $.globalMessenger().post({
			            message: "注册成功",
			            hideAfter: 2,
			            type: 'success',
			            //showCloseButton: true
			        });
				 $(".register_validate").data('bootstrapValidator').resetForm();
			}else if(data.data == "userExists"){
				 $.globalMessenger().post({
			            message: "用户名已存在，请重新输入！",
			            hideAfter: 2,
			            type: 'error',
			            //showCloseButton: true
			        });
			}else{
				$.globalMessenger().post({
		            message: "验证错误，请检查后重新输入",
		            hideAfter: 2,
		            type: 'error',
		            //showCloseButton: true
		        });
			}
			
		});
	}
});

//登陆
$(".login_btn").click(function login(){
	$('.login_validate').bootstrapValidator('validate');
	if(numvalue != numsum){
		 $.globalMessenger().post({
	            message: "验证码错误!",
	            hideAfter: 2,
	            type: 'error',
	            //showCloseButton: true
	        });
	}else{
		var ajax_option={
				url:"login",//默认是form action
				success:function(data){
					if(data == "error"){
						 $.globalMessenger().post({
					            message: "验证错误，请检查后重新输入",
					            hideAfter: 2,
					            type: 'error',
					            //showCloseButton: true
					        });
					}else{
						window.location.href = "/";
					}
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
					if(XMLHttpRequest.status == 403){
						window.location.href = "/403";
					}
				}
			}
		$(this).parents("form").ajaxSubmit(ajax_option);
	}
});

$('.forget_btn').click(function forgetpwd() {
	  $.post("/check_vercode",{"findemail" : $("*[name='forget_Form']").find("*[name='email']").val(), "vercode" : $("*[name='code']").val()},function(data){
    	  if(data.data == "success"){
    			 $.globalMessenger().post({
    		            message: "验证通过",
    		            hideAfter: 2,
    		            type: 'success',
    		            //showCloseButton: true
    		        });
    			 //跳转修改密码
    			 $(".card-signup").removeClass("form_toggle");
 				$("[name='change_password_Form']").addClass("form_toggle");
 				updatepassword(data.account);
 				
 				//清空内容
 				$("*[name='forget_Form']").find("*[name='email']").val("");
 				$("*[name='code']").val("");
 				$(".forget_validate").data('bootstrapValidator').resetForm();
    	  }else if(data.data == "error"){
    			 $.globalMessenger().post({
    		            message: "验证错误，请重新输入",
    		            hideAfter: 2,
    		            type: 'error',
    		            //showCloseButton: true
    		        });
    			 //不跳转
    			 $(".card-signup").removeClass("form_toggle");
    				$("[name='forget_Form']").addClass("form_toggle");
    	  }
	});
});
	

function updatepassword(account){
	var upaccount = account;
	$('.change_btn2').click(function updatepwd() {
		  $.post("/check_updatepwd",{"cuppwd" : $("#CUpPwd").val(), "cuprepwd" : $("#CUpRePwd").val(), "upaccount" : upaccount},function(data){
	    	  if(data.data == "success"){
	    			 $.globalMessenger().post({
	    		            message: "修改成功",
	    		            hideAfter: 2,
	    		            type: 'success',
	    		            //showCloseButton: true
	    		        });
	    			 //跳转登陆
	    				$(".card-signup").removeClass("form_toggle");
	    				$("[name='login_Form']").addClass("form_toggle");
	    				
	    				//清空内容
	    				$("#CUpPwd").val("");
	    				$("#CUpRePwd").val("");
	    				$(".change_password_validate").data('bootstrapValidator').resetForm();
	    	  }else if(data.data == "error"){
	    			 $.globalMessenger().post({
	    		            message: "验证错误，请重新输入!!!",
	    		            hideAfter: 2,
	    		            type: 'error',
	    		            //showCloseButton: true
	    		        });
	    			 //不跳转
	 				$(".card-signup").removeClass("form_toggle");
					$("[name='change_password_Form']").addClass("form_toggle");
	    	  }
		});
	});
}

$('.send_code').click(function() {
	  $.post("/send_email",{"findemail" : $("*[name='forget_Form']").find("*[name='email']").val()},function(data){
    	  if(data.msg == "send success"){
    			 $.globalMessenger().post({
    		            message: "发送成功，请到邮箱查看验证码",
    		            hideAfter: 2,
    		            type: 'success',
    		            //showCloseButton: true
    		        });
    	  }else if(data.msg == "send error"){
    			
    	  }else if(data.msg == "account not exist"){
    			 $.globalMessenger().post({
    		            message: "用户不存在，请检查输入是否正确",
    		            hideAfter: 2,
    		            type: 'error',
    		            //showCloseButton: true
    		     });
    	  }
	});
});