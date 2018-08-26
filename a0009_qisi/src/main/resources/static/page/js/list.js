$(function(){

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


    //列表页加载数据
    // 页数
    var page = 0;

    // dropload
    $('.content').dropload({
        scrollArea: window,
        loadDownFn: function (me) {
            page++;
            $.ajax({
                url: '/qisi/article/showArticleList.action?pageSize=100&pageNum='+page+'&type=-1&wxId=' + sessionStorage.userId,
                type: "GET",
                dataType: 'json',
                success: function (data) {
                    var result = '';

                    var prodTpl = function (j) {
                        var mainImg = data.result[j].mainImg;
                        if (mainImg) {
                            mainImg = "<img src=\"" + mainImg + "\" onerror=\"this.src=\'images/loading.gif\'\"/>";
                        } else {
                            mainImg = "";
                        }

                        var hheader = "";
                        var readTag = "";
                        if (1 === data.result[j].type){
                            hheader = "吐槽：";
                        }else if(0 === data.result[j].type){
                            hheader = "想法：";
                        }
                        var tTitle = "";
                        if (1 === data.result[j].read){
                            tTitle =
                                '<p>' + hheader + data.result[j].title + '</p>';
                        }else{
                            readTag = "●&nbsp;";
                            tTitle = '<div style="display: inline-block;color: orange; vertical-align: top;">' + readTag + '</div>' +
                                '<p class="hheader">' + hheader + data.result[j].title + '</p>';
                        }
                        return ['<li onclick="savePosition()" comType=' + data.result[j].type + '>',
                            '<div class="dd"><a href="details.html?id=' + data.result[j].id + '">',
                            tTitle,
                            '<p><span>' + data.result[j].depart.departName + "  " + data.result[j].userInfo.user_name + '</span><br>',
                            '<span id="time">' + data.result[j].create_time + '</span></p></div>',
                            '<div class="dt">' + mainImg + '</div></a>',
                            '</li>'];
                    };
                    console.log(data.result.length)

                    var arrLen = data.result.length;
                    if(arrLen > 0){
                        for(var i=0; i<arrLen; i++){
                            result += prodTpl(i).join('');
                        }
                        // 如果没有数据
                    }else{
                        // 锁定
                        me.lock();
                        // 无数据
                        me.noData();
                    }

                    // 为了测试，延迟1秒加载
                    setTimeout(function(){
                        $(".dt_list .list").append(result);

                        // 每次数据加载完，必须重置
                        me.resetload();
                    },5);

                    //判断是否有图片 设定展示样式
                    $(".dt_list li").each(function () {
                        var imgLen = $(this).find("img").length;
                        if (imgLen <= 0) {
                            $(this).find(".dd").css("maxWidth", '100%');
                            $(this).find("dt").hide();
                        }
                    });

                },
                error: function (er) {
                    console.log(er)
                    me.resetload();
                    //BackErr(er);
                }
            });
        }
    })

});

//save position
function savePosition() {
    if($(document).scrollTop()!=0){
        sessionStorage.setItem("offsetTop", $(window).scrollTop());
    }
}

function backPosition() {
    console.log("backposition: 期望值：" + sessionStorage.getItem("offsetTop") + "  当前值：" +  $(window).scrollTop());
    if ($(document).scrollTop()!=sessionStorage.getItem("offsetTop")){
        setTimeout(function(){
            var offset = sessionStorage.getItem("offsetTop");
            $(document).scrollTop(offset);
            backPosition();
        },50);
    }
}



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


$(window).scroll(function(){
    // if($(document).scrollTop()!=0){
    //     sessionStorage.setItem("offsetTop", $(window).scrollTop());
    // }
});
//onload时，取出并滚动到上次保存位置
window.onload = function(){
    backPosition();
};