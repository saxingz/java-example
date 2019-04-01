/**
 *
 * Created by saxing on 2018/5/13.
 */

/*$(".comipt").focus(function(){
	var _thisP = $(this).parents(".comment_foot");
	_thisP.addClass('comment_foot_focus');
})
$(".comipt").blur(function(){
	var _thisP = $(this).parents(".comment_foot");
	_thisP.removeClass('comment_foot_focus');
})uploadArea
*/
//输入文本框字数限制
$(".areawrap textarea").each(function(){
    var thisp = $(this).parents(".areawrap");
    var staticSpan = thisp.find(".static_num");
    var sB = staticSpan.find("b");
    var sTotal = staticSpan.find("em").text();
    $(this).keyup(function(){
        var zsLen = $(this).val().length;
        if(zsLen <= sTotal){
            sB.text(zsLen);
        }else{
            return ;
            $(this).blur();
        }
    })
});

// 0=奇思妙想  1=吐槽
var articleType = 0;


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

//图片
var uploadAreaWrap = $(".fit .uploadAreaWrap");
//文章
var content = $("#content");
var topic = $("#topic");
var advice = $("#advice");
var depart = $("#depart");

/**
 * 删除图片
 */
function delPic(el) {
    var div = $(el).parent("div");
    div.remove();
}

/**
 * 上传
 */
function uploadPic() {
    // 查看现在有多少图片了
    var picLength = uploadAreaWrap.find("img").length;
    if (picLength >= 5){
        tishi("最多添加5张图");
        return;
    }

    var pic = $('#imageFile')[0].files[0];
    var fd = new FormData();
    fd.append('multipartFile', pic);
    $.ajax({
        url:"/qisi/article/imgUpload.action",
        type:"PUT",
        data: fd,
        cache: false,
        contentType: false,
        processData: false,
        xhr: function() { //用以显示上传进度
            var xhr = $.ajaxSettings.xhr();
            if (xhr.upload) {
                xhr.upload.addEventListener('progress', function(event) {
                    var percent = Math.floor(event.loaded / event.total * 100);
                    tishi("图片上传中..." + percent + "%");
                }, false);
            }
            return xhr
        },
        success:function(data){
            console.log(data);
            data = JSON.parse(data);
            if (data.status === 200){
                data = data.result;
                uploadAreaWrap.append('<div class="uploadimg">' +
                    '<img class="upimg" src='+ data.picPath + ' uidName=' + data.uidName + ' picName='+ data.picName + ' />' +
                    '<span class="close" onclick="delPic(this)"></span>' +
                    '</div>');
                tishi("上传成功！");
            }else {
                tishi("上传图片错误，只支持JPEG,JPG,PNG,BMP格式，稍后再试！")
            }

        }
    });
}

/**
 * 提交
 */
function commit(el) {
    // 检测图片张数
    var picLength = uploadAreaWrap.find("img").length;
    if (picLength > 5){
        tishi("图片数量超过5张");
        return;
    }

    var topicContent = topic.val().trim();
    var contentContent = content.val().trim();
    var adviceContent = advice.val().trim();
    var departContent = depart.val().trim();

    //检测字数
    if (topicContent.length > 20 || contentContent.length > 1000 || adviceContent.length > 1000){
        tishi("字数超限");
        return;
    }
    //检测字数
    if (topicContent.length < 1 || contentContent.length < 1 || adviceContent.length < 1){
        tishi("字数不得为空");
        return;
    }

    //检测部门
    var correctDepart = false;
    alldepartments.forEach(function(d){
        if (d == departContent){
            correctDepart = true;
        } 
    });
    
    if (!correctDepart){
        tishi("请选择正确部门！");
        return;
    }

    var params = {
        "wxUserId": sessionStorage.userId,
        "content": content.val(),
        "title": topic.val(),
        "advice":advice.val(),
        "type": articleType,
        "relateDepart": departContent
    };

    var imageParams = [];
    // 统计图片
    var images = uploadAreaWrap.find("img");
    if (images.length > 0){
        for (var i = 0; i < images.length; i++){
            var tm = {
                "originName": $(images[i]).attr("picname"),
                "uidName": $(images[i]).attr("uidname"),
                "index": i
            };
            imageParams.push(tm);
        }
    }
    if (imageParams.length > 0){
        params.images = imageParams;
    }
    var ca = $(".commitbtn").find("a");
    ca.removeAttr("onclick");
    ca.addClass("btn-disabled")

    $.ajax({
        type: 'POST',
        url: "/qisi/article/uploadArticle.action",
        data: JSON.stringify(params),
        contentType : "application/json",
        success: function (data) {
            console.log(data);
            tishiAndTrans("发表成功", "/qisi/page/index.html")
        }
    });


}

function linkCur(){
    var idType = getLinkParam(document.location.href, "relType");
    var linkTypeqs = $(".footernav").find(".ic2");
    var linkTypetc = $(".footernav").find(".ic3");
    if(idType.indexOf("qs") > -1){
        $("title").text("奇思妙想");
        articleType = 0;
        linkTypeqs.addClass("iccur");
        linkTypeqs.find(".tb").addClass("cur");
        $(".cx_formbg").show();
    }else{
        $("title").text("我要吐槽");
        articleType = 1;
        linkTypetc.addClass("iccur");
        linkTypetc.find(".tb").addClass("cur");
        $(".cx_formbgtc").show();
    }
}

linkCur();


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


var alldepartments = [
    "工会办公室",
    "公司金融部",
    "个人金融部",
    "财务管理部",
    "监察部",
    "法律与合规部",
    "风险管理部",
    "综合管理部",
    "运营部",
    "市行营业部",
    "宿豫支行",
    "城中支行",
    "泗阳支行",
    "泗洪支行",
    "沭阳支行"
];

function getSelectOption(optionArray){
    var html = '<option value="" class="depart">请选择部门</option>';
    for(var i = 0, length = optionArray.length; i < length; i++){
        html += '<option class="depart" value="' + optionArray[i] + '">' + optionArray[i] + '</option>';
    }
    return html;
}

function getDepart(){
    $('.dept').html(getSelectOption(alldepartments));
    $('.dept').chosen();
}