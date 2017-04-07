/**
 * 
 */
/** 照片瀑布 **/
		(function() {
			var support = { transitions: Modernizr.csstransitions },
				// transition end event name
				transEndEventNames = { 'WebkitTransition': 'webkitTransitionEnd', 'MozTransition': 'transitionend', 'OTransition': 'oTransitionEnd', 'msTransition': 'MSTransitionEnd', 'transition': 'transitionend' },
				transEndEventName = transEndEventNames[ Modernizr.prefixed( 'transition' ) ],
				onEndTransition = function( el, callback ) {
					var onEndCallbackFn = function( ev ) {
						if( support.transitions ) {
							if( ev.target != this ) return;
							this.removeEventListener( transEndEventName, onEndCallbackFn );
						}
						if( callback && typeof callback === 'function' ) { callback.call(this); }
					};
					if( support.transitions ) {
						el.addEventListener( transEndEventName, onEndCallbackFn );
					}
					else {
						onEndCallbackFn();
					}
				};

			new GridFx(document.querySelector('.grid'), {
				imgPosition : {
					x : -0.5,
					y : 1
				},
				onOpenItem : function(instance, item) {
					instance.items.forEach(function(el) {
						if(item != el) {
							var delay = Math.floor(Math.random() * 50);
							el.style.WebkitTransition = 'opacity .5s ' + delay + 'ms cubic-bezier(.7,0,.3,1), -webkit-transform .5s ' + delay + 'ms cubic-bezier(.7,0,.3,1)';
							el.style.transition = 'opacity .5s ' + delay + 'ms cubic-bezier(.7,0,.3,1), transform .5s ' + delay + 'ms cubic-bezier(.7,0,.3,1)';
							el.style.WebkitTransform = 'scale3d(0.1,0.1,1)';
							el.style.transform = 'scale3d(0.1,0.1,1)';
							el.style.opacity = 0;
						}
					});
				},
				onCloseItem : function(instance, item) {
					instance.items.forEach(function(el) {
						if(item != el) {
							el.style.WebkitTransition = 'opacity .4s, -webkit-transform .4s';
							el.style.transition = 'opacity .4s, transform .4s';
							el.style.WebkitTransform = 'scale3d(1,1,1)';
							el.style.transform = 'scale3d(1,1,1)';
							el.style.opacity = 1;

							onEndTransition(el, function() {
								el.style.transition = 'none';
								el.style.WebkitTransform = 'none';
							});
						}
					});
				}
			});
		})();
		/** 照片瀑布 end **/
		
		/** bootstrap时间控件 **/
		$.fn.datetimepicker.dates['en'] = {
				days: ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"],
				daysShort: ["周日", "周一", "周二", "周三", "周四", "周五", "周六", "周日"],
				daysMin:  ["日", "一", "二", "三", "四", "五", "六", "日"],
				months: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
				monthsShort: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
				today: "今天",
				suffix: [],
				meridiem: ["上午", "下午"]
		};
		$(".form_datetime").datetimepicker({
			format: 'yyyy-mm-dd hh:ii',
			weekStart: 1,
      		todayBtn:  1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 2,
			forceParse: 0,
      		showMeridian: 1
		});

		var $grid = $('.grid').masonry({
			 itemSelector: '.grid__item',
			 isFitWidth : true
		});

		$("#file").fileinput({
			uploadUrl:'/classPhoto/uploadFile',
			showUpload: true,
			uploadAsync: true,
			uploadExtraData:function(previewId, index) {
		        var data = {
		                title : $("#title").val(),
		                detail:$("#details").val()
		            };
		            return data;
		        }
		}).on("fileuploaded",function(event,data){
			//data.setContentType("text/html;charset=UTF-8");
			if(data.response){
 			 	//console.log(data);
 			 	//location.reload(true);
 			 	//var regE = escapeRegExp("\\[([^\\[\\]]*?)\\]");
 			 	document.getElementById("form-party").reset();
	 			
 			
 			    var reg = new RegExp("\\[([^\\[\\]]*?)\\]","igm");
				var html = document.getElementById("part_classphoto").innerHTML; 
		        var source = html.replace(reg, function(node, key) {  
		              return {  
		                  'address' : "http://oihey4yi1.bkt.clouddn.com/"+data.response[0].address,  
		                  'title' :   data.response[0].title,  
		                  'remarks' : data.response[0].remarks,
		                  'p_time' : data.response[0].p_time
		              }[key];  
		          });  
		          
		        //$("#photo_grid").prepend(source);
		        var $content = $(source);
		        
		        $('#file').fileinput('reset');
	 			$("#party-form").slideUp();
		        
 				$grid.prepend( $content ).imagesLoaded(function (){
 					$grid.masonry('prepended',$content);
 				});
 				  
 				  
 			}
		});
		/** bootstrap时间控件 end **/
		
		/** 表单的控制 **/
		$(".currenty-new, .party-launch-btn").on("click", function(){
			$("#party-form").slideDown(500);
			$("html,body").animate({scrollTop: 0},1000);
		})
		$(".currency-form-close").on("click", function(){
			$("#party-form").slideUp();
		})
		// 照片详情内点击评论出评论框
		$(".want-Comment").on("click", function(){
			$(".want-Comment-input").slideDown();
			$(".want-Comment-input input").focus();
		})

		$(".join-party").on("click",function(){
			$(".fileinput-upload-button").click();
		});