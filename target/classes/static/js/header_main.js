$(function(){
	// Page Preloader
	$(window).load(function() {
		$(".loader").delay(300).fadeOut();
		$(".animationload").delay(600).fadeOut("slow");
	});

	$("#menu-trigger").on("click", function(){    //点击菜单
		$("#menu-trigger").toggleClass("open");
		$("#leftmenu").toggleClass("toggled");
		$(".leftmenu-mb").toggleClass("toggleDisplay");
	})
	$(".leftmenu-mb").on("click", function(){    //点到其他位置收起菜单
		$("#menu-trigger").toggleClass("open");
		$("#leftmenu").toggleClass("toggled");
		$(".leftmenu-mb").toggleClass("toggleDisplay");
		$("#chat").removeClass("show-chat");
	})
	$("#top-search, .top-search-btn").on("click", function(){	//header 搜索按钮
		$("#top-search-wrap").toggleClass("show-search");
		$("#top-search-wrap .tsw-inner input").focus()
		$(".tsw-inner input").on("click", function(){event.stopPropagation();})
		event.stopPropagation();
		$(document).one("click", function(){
			$("#top-search-wrap").removeClass("show-search");
			event.stopPropagation();
		})
	})
	$("#chat-btn").on("click", function(){				//在线用户聊天 chat		
		$("#chat").toggleClass("show-chat");
		$(".chat-btn").toggleClass("toggleli");
		$("#chat").on("click", function(){event.stopPropagation();})
		event.stopPropagation();
		$(document).one("click", function(){
			$("#chat").removeClass("show-chat");
			$(".chat-btn, #info-btn, #top-user-btn").removeClass("toggleli");
			event.stopPropagation();
		})
	})
	$("#info-btn").on("click", function(){				//消息提醒
		$("#info").toggleClass("show-info");
		$("#info-btn").toggleClass("toggleli");
		$("#info").on("click", function(){event.stopPropagation();})
		event.stopPropagation();
		$(document).one("click", function(){
			$("#info").removeClass("show-info");
			$(".chat-btn, #info-btn, #top-user-btn").removeClass("toggleli");
			event.stopPropagation();
		})
	})
	$("#top-user-btn").on("click", function(){				//当前用户
		$("#top-user").toggleClass("show-info");
		$("#top-user-btn").toggleClass("toggleli");
		$("#top-user").on("click", function(){event.stopPropagation();})
		event.stopPropagation();
		$(document).one("click", function(){
			$("#top-user").removeClass("show-info");
			$(".chat-btn, #info-btn, #top-user-btn").removeClass("toggleli");
			event.stopPropagation();
		})
	})
	$("#main").on("click", function(){
		$("#chat").removeClass("show-chat");				//收起chat
		$("#info").removeClass("show-info");				//收起消息栏
		$("#top-user").removeClass("show-info");				//收起当前用户
		$(".chat-btn, #info-btn, #top-user-btn").removeClass("toggleli");	//去掉按钮背景	
	})
	var touchtime = new Date().getTime();      //双击header返回顶部
	$("#header").on("click", function(){
        if( new Date().getTime() - touchtime < 500 ){
            $("html,body").animate({scrollTop:"0px"},800)
        }else{
            touchtime = new Date().getTime();
        }
    });
	// 判断上滚下滚
	var bottom=0,top=0;  
	$(window).scroll(function(e){  
	   	bottom = $(this).scrollTop();  
	    if(top <= bottom){//下滚  
	      $("#header").css("top", "-75px");
	      $(".dataShare_back_listveiw").css("bottom", "-75px");
	    }  
	    else{//上滚  
	      $("#header").css("top", "0");
	      $(".dataShare_back_listveiw").css("bottom", "15px");
	    }  
	    setTimeout(function(){top = bottom;},0);         
	});
	// 隐藏和显示通用新增按钮
	$(window).scroll(function(){
		if($(window).scrollTop() > 100){
			$(".currenty-new").css("bottom", "20px");
		}
		if($(window).scrollTop() < 100){
			$(".currenty-new").css("bottom", "-50px");
		}
	})
})