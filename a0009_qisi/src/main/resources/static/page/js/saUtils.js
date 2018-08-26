/**
 * Created by saxing on 2018/5/20.
 */


function tishiAndTrans(content,url) {
    var html = '<div class="xiaoxi msg_div" style="z-index:9999;left: 5%;width: 90%;position: fixed;background:none;top:50%;"> <p class="msg" style="background: none repeat scroll 0 0 #000; border-radius: 30px;color: #fff; margin: 0 auto;padding: 1.5em;text-align: center;width: 70%;opacity: 0.8;"></p></div>';
    $(document.body).append(html);
    $(".msg_div").show();
    $(".msg").html(content);
    if(url){
        window.setTimeout("location.href='"+url+"'", 1500);
    }else{
        setTimeout('$(".msg_div").fadeOut()', 1500);
    }
}

function tishi(content) {
    var html = '<div class="xiaoxi msg_div" style="z-index:9999;left: 5%;width: 90%;position: fixed;background:none;top:50%;"> <p class="msg" style="background: none repeat scroll 0 0 #000; border-radius: 30px;color: #fff; margin: 0 auto;padding: 1.5em;text-align: center;width: 70%;opacity: 0.8;"></p></div>';
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