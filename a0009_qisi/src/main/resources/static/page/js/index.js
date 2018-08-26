/**
 *
 * Created by saxing on 2018/5/13.
 */


$(function(){
    //滑动图高度限制
    var wH = $(window).height();
    var sH = wH-97;
    $(".swiper-wrapper").height(sH);
    $(".swiper-slide .swiper-slide").css("lineHeight",sH);
    
    

    Date.prototype.Format = function (fmt) { //author: meizz
        var o = {
            "M+": this.getMonth() + 1, //月份
            "d+": this.getDate(), //日
            "H+": this.getHours(), //小时
            "m+": this.getMinutes(), //分
            "s+": this.getSeconds(), //秒
            "q+": Math.floor((this.getMonth() + 3) / 3), //季度
            "S": this.getMilliseconds() //毫秒
        };
        if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var k in o)
            if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        return fmt;
    };
    var nowTime=new Date();
    var times = nowTime.Format("yyyy-MM-dd HH:mm");
    $("#time").text(times);
    //获取日期
    function getmonth(){
        var myDate = new Date();
        var year = myDate.getFullYear(); //获取当前年份(2位)
        var sMonth = myDate.getMonth()+1;
        var day =  myDate.getDate(); //日
        var hour = myDate.getHours(); //小时
        var min = myDate.getMinutes(); //分
        var thisMonth = year+"/"+sMonth;
        return thisMonth
    }

    $(".static").click(function(){
        $(this).find("a").attr("href","static.html?month="+getmonth());
    });
    /*<i><img src="'+data.result[j].fromHeadPic+' /></i>*/

    //首页
    $.ajax({
        url: '/qisi/article/showArticleList.action?pageSize=10&pageNum=1&type=-1&wxId=' + sessionStorage.userId,
        type: "GET",
        dataType:'json',
        success:function(data){
            var prodTpl =function(j){
                var str, img, contentImg;
                var hheader = "";
                if (1 === data.result[j].type){
                    hheader = "吐槽：";
                }else if(0 === data.result[j].type){
                    hheader = "想法：";
                }
                if(data.result[j].mainImg !== undefined){
                    img = data.result[j].mainImg;
                    contentImg = data.result[j].mainImg;
                }else{
                    img = 1 === data.result[j].type ? "images/tucao_default.png" : "images/qisi_default.png";
                    contentImg = 1 === data.result[j].type ? "images/tucao_default.png" : "images/qisi_default.png";
                }
                str = '<div class="swiper-slide"><a href="details.html?id='+data.result[j].id+'"><div class="swiper-slidebg-blur" style="background-image:url('+img+')"></div>';


                return [str,
                    '<img src="' + contentImg + '" onerror="this.src=\'images/loading.gif\'"/>',
                    '<div class="info">',
                    '<span><em>'+hheader+data.result[j].title+'</em></span>',
                    '</div></a></div>'];
            };
            
            var _len = data.result.length;
            if (_len > 5){
                _len = 5;
            }
            for(var i=0; i < _len; i++){
                var prodHtml = prodTpl(i).join('');
                $(".swiper-wrapper").append(prodHtml);
            }
            $(".swiper-slidebg-blur").css({
		    	'height':sH,
		    	'lineHeight':sH
		    })
            
		    $(".swiper-slide").css("lineHeight",sH+'px');


            var mySwiper = new Swiper('.swiper-container',{
                autoplay : true,
                autoplayDisableOnInteraction : true,
                speed:500,
                loop: true,
                initialSlide: 0,
                spaceBetween: 0,
                centeredSlides: true,
                slideToClickedSlide: true,
                grabCursor: true,
                observer:true,//修改swiper自己或子元素时，自动初始化swiper
                observeParents:true,//修改swiper的父元素时，自动初始化swiper
                mousewheel: {
                    enabled: true,
                },
                keyboard: {
                    enabled: true,
                },
                pagination: {
                    el: '.swiper-pagination',
                },
                effect : 'flip',
                flipEffect: {
                    slideShadows : false,
                    limitRotation : true,
                }
            });


        },
        error:function(er){
            console.log(er)
            //BackErr(er);
        }
    });

})


/**
 */
pushHistory();
window.addEventListener("popstate", function(e) {
    WeixinJSBridge.call('closeWindow');
}, false);
function pushHistory() {
    var state = {
        title: "title",
        url: "#"
    };
    window.history.pushState(state, "title", "#");
}

// 清除位置
function cleanOffetTop() {
    sessionStorage.setItem("offsetTop", 0);
}