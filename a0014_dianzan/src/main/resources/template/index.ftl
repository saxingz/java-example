<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"/>
<meta name="apple-mobile-web-app-capable" content="yes"/>
<meta name="apple-mobile-web-app-status-bar-style" content="black"/>
<meta content="telephone=no" name="format-detection"/>
<title>为你点赞</title>
<link rel="stylesheet" type="text/css" href="/static/css/common.css?v=1.47" >
<link rel="stylesheet" type="text/css" href="/static/css/style.css?v=1.47" >
<link rel="stylesheet" href="/static/css/animate.min.css">
</head>
<body class="bodyBg animated fadeInUp">
	<div class="headerNav">
        <a href="/static/zuof.html">
            <div class="headBg"></div>
        </a>
	</div>
	<div class="evaluate">
		<div class="ilike"><a class="no" href="/static/list.html?ref=1&departId=1"><i></i><div class="izan">我要点赞</div></a></div>
		<div class="dislike"><a class="no" href="/static/list.html?ref=2&departId=1"><i></i><div class="icha">我要差评</div></a></div>
	</div>

	<div class="rencently likeTab">
		<h3>
            <b>点赞展示</b>
            <b class="more">更多>></b>
        </h3>
		<div class="list_lh">
			<ul>
			</ul>
		</div>
	</div>

    <div class="mask-lay">&nbsp;</div>
	<#--<div id="statistic_info" class="statisticInfoDialog">-->
        <#--<div class="head">— 点赞英雄榜 —<i></i><span>20S</span></div>-->
        <#--<div class="infodata">-->
            <#--<table class="dataTable">-->
                <#--<colgroup>-->
                    <#--<col />-->
                    <#--<col width="25%" />-->
                    <#--<col width="25%" />-->
                <#--</colgroup>-->
                <#--<thead>-->
                <#--<tr>-->
                    <#--<th>部门</th>-->
                    <#--<th>点赞数</th>-->
                    <#--<th>差评数</th>-->
                <#--</tr>-->
                <#--</thead>-->
                <#--<tbody>-->
                <#--</tbody>-->
            <#--</table>-->
        <#--</div>-->
	<#--</div>-->


<script type="text/javascript" src="/static/js/jquery.min.js?v=1.47"></script>
<script type="text/javascript" src="/static/js/scroll.js?v=1.47"></script>
<script type="text/javascript" src="/static/js/jquery.cookie.js?v=1.47"></script>
<script type="text/javascript" src="/static/js/wxlimit.js?v=1.47"></script>
<script type="text/javascript" src="/static/js/custom.js?v=1.47"></script>
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js?v=1.47"></script>
<script type="text/javascript">

    window.onload = function () {
        window.history.forward(1);
    };

    sessionStorage.myname="${myname}";
    sessionStorage.nowDepart="${nowDepart}";

//    alert(sessionStorage.myname);

    function bodyScroll(event){
        event.preventDefault();
    }

    function touchStop(swit) {
        document.addEventListener("touchmove", bodyScroll, false);
    }
    function touchStart() {
        document.removeEventListener("touchmove", bodyScroll, false     );
    }


//    //页面加载弹出统计信息
//    function openDialogInfo(){
//
//        var screenW=window.screen.width;
//        var screenH=window.screen.height;
//        var dialogW=$("#statistic_info").width();
//        var dialogH=$("#statistic_info").height();
//        var marginW=eval(screenW-dialogW)/2;
//        var marginH=eval(screenH-dialogH)/2;
//        $("#statistic_info").css({"left":marginW,"top":marginH-40})
        //启动屏幕遮罩
//        $(".mask-lay").show();
//        //绑定事件
//        $("#statistic_info .head i").off().on("click",function(){
//            closeDialog();
//        });

//        //启动20秒关闭
//        var index=19;
//        var dialogInterval=setInterval(function(){
//            $("#statistic_info .head span").text(index+"S");
//            if(index<=0){
//                closeDialog();
//                clearInterval(dialogInterval);
//            }
//            index--;
//        },1000);

//        var staticList =function(data){
//
//            if(data.result){
//                var _plen = data.result.length;
//                var trHtml = function(i){
//                    return ['<tr>',
//                        '<td class="linkName" departId='+ data.result[i].depart_id +'>'+data.result[i].depart_name+'</td>',
//                        '<td>'+data.result[i].good+'</td>',
//                        '<td>'+data.result[i].bad+'</td>',
//                        '</tr>'];
//                };
//                for(var i = 0; i < _plen; i++){
//                    var deptHtml = trHtml(i).join('');
//                    $("#statistic_info .dataTable tbody").append(deptHtml);
//                }
//            }else{
//                $("#statistic_info .dataTable tbody").append('<tr><td colspan="3">暂无数据</td></tr>');
//            }
//
//            $(".linkName").off().on("click",function(){
//                departClick(this, getSaYearMonthWithSep("-"));
//            });
//        };

//        $.ajax({
//            url: '/thumb/getTotalReport',
//            type: "GET",
//            dataType:'json',
//            success:function(data){
//                $("#statistic_info .dataTable tbody").html("");
//                staticList(data)
//            },
//            error:function(er){
//                console.log(er);
//                //BackErr(er);
//            }
//        });
//    }

    $(".rencently .more").off().on("click",function(){
        location.href="/static/alllist.html" + "?departId=-1&date=" + getSaYear() + "-" + getSa0Month()
                + "&departName=" + encodeURIComponent("所有部门");
    });

    function pushHistory() {
        var state = {
            title: "title",
            url: "#"
        };
        window.history.pushState(state, "title", "#");
    }

    // 获取最新点赞10条
    function getLastGood10() {
        $.ajax({
            url: '/thumb/getLastGood10',
            type: "GET",
            dataType:'json',
            success:function(data){
                var prodTpl =function(j){
                    return ['<li>',
                        '<p class="name"> 致&nbsp;&nbsp;<b>'+ data.result[j].toDepartmentName + '&nbsp;&nbsp;'+ data.result[j].toUserName+'</b></p>',
                        '<p class="nstatic">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+ ((data.result[j].reason && data.result[j].reason.length > 0) ? data.result[j].reason : "赞") +'</p>',
                        '<p class="nstatic-from"><span>'+ data.result[j].fromDepartName + '&nbsp;&nbsp;' + data.result[j].fromUserName +'</span></p>',
                        '<p class="nstatic-date"><span>'+ data.result[j].date +'</span></p>',
                        '</li>'];
                };
                var _len = data.result.length;
                for(var i=0; i < _len; i++){
                    var prodHtml = prodTpl(i).join('');
                    $(".list_lh ul").append(prodHtml);
                }
                var bodyH = $(document).height();
                var headerH = $(".headerNav").height();
                var evalH = $(".evaluate").height();
                var h3H = $(".rencently h3").height();
                var aminH = parseInt(headerH+evalH+h3H+6);
                //一个li的高度 62
                $(".list_lh").height(bodyH-aminH + 17);
                if($(".list_lh li").length>3){
                    $("div.list_lh").myScroll({
                        speed:50, //数值越大，速度越慢
                        rowHeight:$(".list_lh li").eq(0).height()+23 //li的高度
                    });
                }
            },
            error:function(er){
                console.log(er)
                //BackErr(er);
            }
        });
    }

//    //关闭弹框
//    function closeDialog(){
//        touchStop();
//        $(".mask-lay").hide();
//        $("#statistic_info").hide();
//        $("#statistic_info .head span").hide();
//    }

    $(function(){
        touchStart();
        touchStop();

        getLastGood10();

        /**
         * 首页返回退出
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

//        openDialogInfo();
//        //页面加载， 弹出统计框信息
//        if (sessionStorage.enter){
//            closeDialog();
//        }
        sessionStorage.enter = true;
    });

</script>
</body>
</html>