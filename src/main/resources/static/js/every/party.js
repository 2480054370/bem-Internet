/**
 * 
 */
		$(".currenty-new, .party-launch-btn").on("click", function(){
			$("#party-form").slideDown(500);
			$("html,body").animate({scrollTop: 0},1000);
		})
		$(".currency-form-close").on("click", function(){
			$("#party-form").slideUp();
		})

	function formatTemplate(dta, tmpl) {  
	    var format = {  
	            name: function(x) {  
	                return x  
	            }  
	        };  
	        return tmpl.replace(/{(\w+)}/g, function(m1, m2) {  
	            if (!m2)  
	                return "";  
	            return (format && format[m2]) ? format[m2](dta[m2]) : dta[m2];  
	        });  
	    } 

	$(".currency-form-edit-close").on("click", function(){
		$("#party-edit").slideUp();
	}); 
	$(".currency-form-edit-close").on("click", function(){
		$("#party-edit").slideUp();
	}); 
	$("body").on("click",".edit", function(){
		$("#party-edit").slideDown(500);
		$("html,body").animate({scrollTop: 0},1000);
		editfun(this);
		var self = this;
  });
	function editfun(ee){
		
		  $.post("/edit",{id : $(ee).nextAll(".article-id").text()},function(data){
				$(".id").val(data.id);
				$(".address").val(data.address);
				$(".endtime").val(data.endtime);
				$(".starttime").val(data.starttime);
				$(".note").val(data.note);
				$(".money").val(data.money);
				$(".topic").val(data.topic);
			  });
		
			}									 
			$("body").on("click",".delete",function(){
		console.log("delete click");
		var d = $(this);
		  $.post("/DeleteArticle",{id : $(this).nextAll(".article-id").text()},function(data){
			  d.parents(".timeline").remove();
			  });
	});								   
	
	 $("#edit-submit").on("click",function(){
		  $.post("update",$("#edit_form").serialize(),function(data){
			  		$("#party-edit").slideUp();
			  });
		});  
	$("#submit").on("click",function(){
				var data = $(".fileinput-upload-button").click();
		console.log(data);
	});
	$("#file-0a").fileinput({
		'uploadUrl':"/party/addArticle",
		'showUpload':true,
		'uploadAsync':false,
		'showRemove':false,
		'maxFileSize':'1024',
		'msgSizeTooLarge':'您所选择的图片 "{name}" ({size} KB) 超过 {maxSize} KB. 请选择小于1024kb的图片!',			   
		'uploadExtraData':function(){
			var data = {
					topic:$("#topic").val(),
					address:$("#address").val(),
					starttime:$(".stime").val(),
					endtime:$(".etime").val(),
					money:$("#money").val(),
					note:$("#note").val()
			}
			return data;
		}
	}).on("filebatchuploadsuccess", function(event,data){
		//console.log(data.response);
		 $(".fileinput-remove").click();
		 $(".mReset").click();
		 $(".currency-form-close").click();
		 var html = $('script[type="text/template"]').html();  
		 var arr = [];  
		 arr.push(formatTemplate(data.response, html));
		$(".timecard-body:first").prepend(arr.join(""));
		 $.each(data.response.photos,function(i,item){
			 			 if(i==0){
				 $("#imgCon").append('<div class="item active"><img src="http://oihey4yi1.bkt.clouddn.com/'+item.name+'" alt=""></div>');
			 }else {
				 $("#imgCon").append('<div class="item"><img src="http://oihey4yi1.bkt.clouddn.com/'+item.name+'" alt=""></div>');
			 }
		 });
	});
	
	$('.withripple').click(function(){
		$('.withripple.active').removeClass('active');
		$(this).addClass('active');
		$('.timecard-body').empty();
		  $.post("/party_tab",{"tab_attribute" : $(this).index()},function(data){
	    	  if(data.msg == "all"){
	    		  var tpl = document.getElementById('all').innerHTML
				  $('.timecard-body').append(tpl);
	    	  }else if(data.msg == "personal"){
	    		  var tpl = document.getElementById('personal').innerHTML
				  $('.timecard-body').append(tpl);
	    	  }else if(data.msg == "join"){
	    			$._messengerDefaults = {
	    					extraClasses: 'messenger-fixed messenger-theme-future messenger-on-top'
	    				};
	    			 $.globalMessenger().post({
	    		            message: "此功能暂不可用",
	    		            hideAfter: 3,
	    		            type: 'error',
	    		            //showCloseButton: true
	    		        });
	    	  }
			  });
	});