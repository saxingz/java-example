/**
 * Created by saxing on 18-1-19.
 */


/**
 * 获取年
 */
function getSaYear() {
    return new Date().getFullYear();
}

/**
 * 获取月
 */
function getSa0Month() {
    var month = new Date().getMonth() + 1;
    return month < 10 ? "0" + month : month;
}

/**
 * 获取年月 2018-01
 * @returns {string}
 */
function getSaYearMonth() {
    return getSaYear() + "-" + getSa0Month();
}


/**
 * 获取中划线year-month
 * @returns {string}
 */
function getSaYearMonthWithSep(sep){
    return getSaYear() + sep + getSa0Month();
}


//从缓存取值
function getDataFromSession(key, callback) {
    var data = sessionStorage.getItem(key);
    callback(data);
}

//缓存存值
function sessionSave(key, data){
    sessionStorage.removeItem(key);
    sessionStorage.setItem(key, JSON.stringify(data));
}

/**
 * 绑定时间控件
 */
function bindDatePicker(obj, dateFormat) {
    //时间选择
    var currYear = (new Date()).getFullYear();
    var opt={};
    opt.date = {preset : 'date'};
    opt.datetime = {preset : 'datetime'};
    opt.time = {preset : 'time'};
    opt.default = {
        theme: 'android-ics light', //皮肤样式
        display: 'modal', //显示方式
        mode: 'scroller', //日期选择模式
        dateFormat: dateFormat,
        lang: 'zh',
        showNow: true,
        dateOrder:"yymm",
        nowText: "今天",
        startYear: 2017, //开始年份
        endYear: currYear + 100 //结束年份
    };
    obj.mobiscroll($.extend(opt['date'], opt['default']));
}

function tishi(content,url) {
    var html = '<div class="xiaoxi none msg_div" style="z-index:9999;left: 5%;width: 90%;position: fixed;background:none;top:50%;"> <p class="msg" style="background: none repeat scroll 0 0 #000; border-radius: 30px;color: #fff; margin: 0 auto;padding: 1.5em;text-align: center;width: 70%;opacity: 0.8;"></p></div>';
    $(document.body).append(html);
    $(".msg_div").show();
    $(".msg").html(content);
    if(url){
        window.setTimeout("location.href='"+url+"'", 1500);
    }else{
        setTimeout('$(".msg_div").fadeOut()', 1500);
    }
}

function justTishi(content) {
    var html = '<div class="xiaoxi none msg_div" style="z-index:9999;left: 5%;width: 90%;position: fixed;background:none;top:50%;"> <p class="msg" style="background: none repeat scroll 0 0 #000; border-radius: 30px;color: #fff; margin: 0 auto;padding: 1.5em;text-align: center;width: 70%;opacity: 0.8;"></p></div>';
    $(document.body).append(html);
    $(".msg_div").show();
    $(".msg").html(content);
    setTimeout('$(".msg_div").fadeOut()', 500);
}

/**
 * 获取链接属性
 * @param link http://localhost:8080/static/alllist.html?a=1&b=2&c=3
 * @param name
 */
function getLinkParam(link, name) {
    var paras = link.substring(link.indexOf("?")+1);
    //c=3  a=1  b=2
    var arr = paras.split("&");
    for(var i = 0; i < arr.length; i++) {
        var eqIndex = arr[i].indexOf("=");
        var key = arr[i].substring(0, eqIndex);
        if (name == key){
            return arr[i].substring(eqIndex + 1, arr[i].length);
        }
    }
    return "";
}

/**
 * 部门点击
 * @param obj
 * @param selDate
 */
function departClick(obj, selDate) {
    var departName = $(obj).text();
    var departId = $(obj).attr("departId");
    location.href="/static/alllist.html" + "?departId=" + departId + "&date=" + selDate
        + "&departName=" + encodeURIComponent(departName);
}

/**
 * 部门个人排名点击
 * @param obj
 * @param selDate
 * @param timeMode
 */
function rankClick(obj, selDate, timeMode){
    var departName = $($(obj).prev()).text();
    var departId = $($(obj).prev()).attr("departid");
    location.href="/qisi/person_rank.html" + "?departId=" + departId + "&timeMode=" + timeMode + "&date=" + selDate
        + "&departName=" + encodeURIComponent(departName);
}