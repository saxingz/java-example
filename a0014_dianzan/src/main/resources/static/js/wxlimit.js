/**
 * Created by saxing on 2017/11/20.
 */


$(function () {
    var ua = navigator.userAgent.toLowerCase();
     if(ua.match(/MicroMessenger/i)=="micromessenger") {
     } else {
         window.location.href='/static/wx_error.html';
     }
});
