/**
 *
 * Created by saxing on 2018/5/19.
 */

function linkCur(){
    var idType = getLinkParam(document.location.href, "id");
    var linkTypeqs = $(".footernav").find(".ic2");
    var linkTypetc = $(".footernav").find(".ic3");
    if(idType.indexOf("qs") > -1){
        linkTypeqs.addClass("iccur");
        linkTypeqs.find(".tb").addClass("cur");
        $(".cx_formbg").show();
    }else{
        linkTypetc.addClass("iccur");
        linkTypetc.find(".tb").addClass("cur");
        $(".cx_formbgtc").show();
    }

}
linkCur();

/**
 * is in array?
 *
 * @param arr
 * @param val
 * @returns {boolean}
 */
function isInArray(arr, val){
    for(var i=0; i < arr.length; i++){
        if (val === arr[i]){
            return true;
        }
    }
    return false;
}


/**
 * judgeBackType
 */
function judgeBackType() {

    // back type
    var linkType = getLinkParam(document.location.href, "link");
    var close = $(".close");
    if ("d" === linkType){
        close.removeAttr("onclick");
        close.click(
            function () {
                window.location.href = '/qisi/page/index.html';
            }
        );
    }
}



/**
 * 详情页文章展示
 *
 */
var detailsId = getLinkParam(document.location.href, "id");
$.ajax({
    url: "/qisi/article/showDetail.action?id="+detailsId,
    type: "GET",
    dataType:'json',
    success: function (data) {
        var authorstr = "";
        if (data.result && data.result.depart && data.result.depart.departName !== undefined){
            authorstr = data.result.depart.departName + "  " ;
        }
        if (data.result && data.result.userInfo && data.result.userInfo.user_name !== undefined){
            authorstr += data.result.userInfo.user_name;
        }

        var type = data.result.type;
        var hheader = "";
        if (0 === type){
            $("title").text("奇思妙想");
            hheader = "想法：";
        }else if (1 === type){
            $("title").text("奇思妙想");
            hheader = "吐槽：";
        }

        var contstr =
            '<div class="title"><h3>' + hheader + data.result.title+'</h3><em class="close" onclick="history.back(-1);"></em>'+
            '<p style="word-break:break-all; " class="date"><span class="name">' + authorstr + '</span>' +
            '<span class="time">'+data.result.create_time+'</span>' +
            '</p>' +
            '</div>'+
            '<span style="font-size: 0.96rem;color: #999;">相关部门: '+ data.result.relateDepart + '</span>' +
            '<xmp style="word-break:break-all; white-space: pre-wrap;" class="content">'+data.result.content+'</xmp>'+
            '<xmp style="word-break:break-all; white-space: pre-wrap;" class="advice"  >'+data.result.advice+'</xmp>';

        var imgLen = data.result.images.length;
        var imgstr = "";
        if( imgLen > 0 ){
            for(var i = 0; i < imgLen ; i++){
                var imgAddress = data.result.images[i].imgAddress;
                imgstr += '<p class="img"><img src="'+imgAddress+'" /></p>';
            }
        }
        var strHtml = contstr + imgstr;
        $(".dt_details").append(strHtml);

        // 判断是否已经关闭评论 0=未关 1=已关
        if (0 === data.result.close){
            // 判断author
            if (sessionStorage.userId === data.result.userInfo.user_id){
                $(".btn_closecom").append('<a href="javascript:void(0);" onclick="closeComment(this)" class="btn_close">关闭评论</a>');
            }
        }else {
            $(".comment_foot").hide();
        }

        comupload(data.result.id);

        judgeBackType();
    },
    error:function(er){
        console.log(er)
    }
});


/**
 * 详情页评论展示
 *
 * @param articleId
 * @param wxId
 */
var comupload = function(articleId) {
    $.ajax({
        type: 'POST',
        url: "/qisi/comment/showComment.action?articleId="+getLinkParam(document.location.href, "id")+"&wxId="+sessionStorage.userId,
        type: "GET",
        dataType:'json',
        success: function (data) {
            if(data.status === 200){
                if(data.result !== undefined && data.result.length > 0){
                    var comLen = data.result.length;
                    var comstr = "";
                    for(var i = 0; i < comLen; i++){
                        var avatar = "images/img_face.png";
                        var cName = "";
                        if (data.result[i].commenter !== undefined && data.result[i].commenter.avatar !== undefined && data.result[i].commenter.avatar.length > 0){
                            avatar = data.result[i].commenter.avatar;
                        }
                        if (data.result[i].commenter !== undefined && data.result[i].commenter.departName !== undefined && data.result[i].commenter.departName.length > 0){
                            cName += data.result[i].commenter.departName;
                        }
                        if (data.result[i].commenter !== undefined && data.result[i].commenter.user_name !== undefined && data.result[i].commenter.user_name.length > 0){
                            cName += "  ";
                            cName += data.result[i].commenter.user_name;
                        }

                        var isThumbed = "comdzCur";
                        var thumbFun = "thumb(this)";
                        if (1 === data.result[i].thumbed){
                            isThumbed = "comdzCur";
                            thumbFun = "";
                        }else{
                            isThumbed = "comdz";
                            thumbFun = "thumb(this)";
                        }

                        comstr += '<li><div class="dt" onclick="addAtMem(this)"><img src="'+avatar+'" /></div>' +
                            '<input class="wxid" wxid="'+ data.result[i].authorWxId +'" hidden />'+
                            '<div class="dd"><h3 onclick="addAtMem(this)">'+cName+'</h3>'+
                            '<div class="action"><p class="cont">'+data.result[i].content+'</p>' +
                            '<div commentid="'+data.result[i].id+'" class="'+isThumbed+'" onclick="'+thumbFun+'">'+data.result[i].thumbNum+'</div>' +
                            '</div></div></li>';

                    }
                    $(".comentList ul").append(comstr);
                }else {
                    $(".comentList ul").append("<p class='comtips'>暂无评论！</p>");
                }
            }else{
            }
        },error:function(er){
            console.log(er)
        }
    });
};

/**
 *  add at member
 */

//@member
var atMem = [];
function addAtMem(el) {
    var wxid = $(el).parents("li").find(".wxid").attr("wxid");
    var wxName = $(el).parents("li").find("h3").text();
    if (!isInArray(atMem, wxid)){
        atMem.push(wxid);
    }
    console.log(atMem);

    //写入at
    var itext = $(".comipt");
    var aContent = itext.val();
    if (aContent.length > 100){
        alert("字数大于100");
        return;
    }
    itext.val(itext.val() + " @" + wxName + " ");
}

/**
 * thumb
 */
function thumb(el) {
    console.log(el);
    var num = $(el).text();
    $(el).text(++num);
    $(el).removeAttr("onclick");
    $(el).removeClass("comdz").addClass("comdzCur");
    var commentId = $(el).attr("commentid");

    $.ajax({
        url: '/qisi/comment/thumbComment.action?commentId='+commentId+'&wxId=' + sessionStorage.userId,
        type: "GET",
        dataType:'json',
        success:function(data){

        },
        error:function(er){
            console.log(er)
        }
    });
}

/**
 *  close comment
 *
 * @param el
 */
function closeComment(el){
    // $(".detailsWrap").css("padding",10);
    var res =  confirm("只有一次关闭评论机会，且不可撤销");
    if (res === true){
        $.ajax({
            url: '/qisi/article/closeArticle.action?articleId='+getLinkParam(document.location.href, "id")+'&wxId=' + sessionStorage.userId,
            type: "GET",
            dataType:'json',
            success:function(data){
                if (200 === data.status){
                    tishi("关闭成功");
                    $(".comment_foot").hide();
                    $(el).hide();
                }
            },
            error:function(er){
                console.log(er)
            }
        });
    }
}

/**
 * 发表评论
 */
function commitComment() {
    var content = $(".comipt").val().trim();
    if (content.length > 100){
        tishi("长度大于100");
    }

    $.ajax({
        url: '/qisi/comment/publish.action?articleId='+getLinkParam(document.location.href, "id")+'&wxId='+sessionStorage.userId+'&content='+content,
        type: "GET",
        dataType:'json',
        success:function(data){
            if (200 === data.status){
                tishi("发表成功");
                setTimeout("window.location.reload()", 1000);
            }
        },
        error:function(er){
            console.log(er)
        }
    });

}

/**
 * 显示阅读人
 */
function showReader() {
    $("#light").show();
    $("#fade").show();
    $(".closefloat").show();

    $.ajax({
        url: '/qisi/article/getReaderDetail.action?articleId=' + getLinkParam(document.location.href, "id"),
        type: "GET",
        dataType: 'json',
        success: function (data) {
            if (200 === data.status){
                var name = data.result;
                if (name && name.length > 0){
                    for (var i = 0; i <name.length; i++){
                        $("#light").append("<div>"+name[i]+"</div>");
                    }
                }
            }else{
                $("#light").text('异常，请稍后再试');
            }
        }
    });
}

/**
 * close float
 */
function closeFloat() {
    $("#light").empty();
    $("#light").hide();
    $("#fade").hide();
    $(".closefloat").hide();
}


//阅读数
window.onload = function () {
    $.ajax({
        url: '/qisi/article/setReader.action?articleId='+getLinkParam(document.location.href, "id")+'&wxId='+sessionStorage.userId,
        type: "GET",
        dataType:'json',
        success:function(data){
            if (200 === data.status){
                console.log(data);
            }
        },
        error:function(er){
            console.log(er)
        }
    });

    setTimeout(function () {
        $.ajax({
            url: '/qisi/article/getReadNum.action?id=' + getLinkParam(document.location.href, "id"),
            type: "GET",
            dataType: 'json',
            success: function (data) {
                if (200 === data.status){
                    var num = data.result;
                    if (num){
                        $(".readnum").text("已有 " + num + " 位同事阅读此文");
                    }
                }
            }
        });
    }, 1000);
};