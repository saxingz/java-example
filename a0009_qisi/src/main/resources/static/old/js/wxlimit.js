/**
 *
 * Created by saxing on 2017/11/20.
 */


$(function () {
    // if (sessionStorage.userId === undefined){
    //     alert("未授权访问");
    //     window.location.href='/qisi/getOauth';
    // }

    var ua = navigator.userAgent.toLowerCase();
     if(ua.match(/MicroMessenger/i)=="micromessenger") {
     } else {
         //saxing todo
         window.location.href='/qisi/wx_error.html';
     }
});
