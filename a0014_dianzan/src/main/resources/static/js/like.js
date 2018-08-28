(function ($) {
	$.extend({
		tipsBox: function (options) {
			options = $.extend({
				obj: null,  //jq对象，要在那个html标签上显示
				str: "+1",  //字符串，要显示的内容;也可以传一段html，如: "<b style='font-family:Microsoft YaHei;'>+1</b>"
				startSize: "0.8rem",  //动画开始的文字大小
				endSize: "1rem",    //动画结束的文字大小
				interval: 600,  //动画时间间隔
				color: "red",    //文字颜色
				callback: function () { }    //回调函数
			}, options);
			$("body").append("<span class='num'>" + options.str + "</span>");
			var box = $(".num");
			var left = options.obj.offset().left + options.obj.width() / 2;
			var top = options.obj.offset().top;
			box.css({
				"position": "absolute",
				"left": left + "px",
				"top": top + "px",
				"z-index": 9999,
				"font-size": options.startSize,
				"line-height": options.endSize,
				"color": options.color
			});
			box.animate({
				"font-size": options.endSize,
				"opacity": "0",
				"top": top - parseInt(options.endSize) + "px"
			}, options.interval, function () {
				box.remove();
				options.callback();
			});
		}
	});
})(jQuery);
  
function niceIn(prop){
	prop.find('i').addClass('niceIn');
	setTimeout(function(){
		prop.find('i').removeClass('niceIn');	
	},2000);		
}
$(function () {
	$(".evaluate div").click(function () {
		$.tipsBox({
			obj: $(this),
			str: "+1",
			callback: function () {
			}
		});
		niceIn($(this));
	});
	
	$(".headerNav >div").each(function(index){
		$(this).click(function(){
			$(this).addClass("show").siblings("div").removeClass("show");
			$(".likeTab").eq(index).show().siblings(".likeTab").hide();
			$("div.list_lh").myScroll({
				speed:60, //数值越大，速度越慢
				rowHeight:68 //li的高度
			});
		});
	});
	$(".headerNav >div:first").click();
});