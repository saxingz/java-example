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
}  
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
	


})