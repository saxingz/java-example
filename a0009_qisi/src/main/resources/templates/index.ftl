<script>
    sessionStorage.userId="${userId}";
    sessionStorage.departId="${departId!''}";
    if (sessionStorage.userId === undefined){
        alert("未授权访问");
    }else {
        var articleId = ${articleId!'-1'};

        if (articleId === -1){
            // to main page
            window.location.href='/qisi/page/index.html';
        }else{
            // to detail
            window.location.href='/qisi/page/details.html?link=d&id=' + articleId;
        }

    }

</script>