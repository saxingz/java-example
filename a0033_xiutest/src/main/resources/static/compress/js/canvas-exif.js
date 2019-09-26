(function(){
    $('#imgupload').on('change', function() {
        var files = this.files;
        for(var i = 0;i<files.length;i++){
            var file = files[i];
            
            var reader = new FileReader();
            reader.onload = function(e) {
                upload(this.result);
            };
            reader.readAsDataURL(file);
        }
    });

    function upload(result) {
        var img = new Image();
        var exif;
        var angle = 0;
        img.onload = function() {
            var orientation = exif ? exif.Orientation : 1;
            // 判断拍照设备持有方向调整照片角度
            switch (orientation) {
                case 3:
                    angle = 180;
                    break;
                case 6:
                    angle = 90;
                    break;
                case 8:
                    angle = 270;
                    break;
            }
            imgScale(img, angle, function(result) {
                console.log(result);
                dataURLtoBlob(result);
                console.log(dataURLtoBlob(result));
                $('img').attr('src', result).show();
            });
            img.onload = null;
        };
        // 转换二进制数据
        // exif = EXIF.readFromBinaryFile(base64ToArrayBuffer(result));
        console.log(dataURLtoBlob(result))
        img.src = result;
    }

    // Base64字符串转成ArrayBuffer数组
    function base64ToArrayBuffer (base64) {
        base64 = base64.replace(/^data\:([^\;]+)\;base64,/gmi, '');
        var binaryString = atob(base64);
        var len = binaryString.length;
        var bytes = new Uint8Array(len);
        for (var i = 0; i < len; i++) {
            bytes[i] = binaryString.charCodeAt(i);
        }
        return bytes.buffer;
    }
    //转成blob,
    function dataURLtoBlob(dataurl) {

        var arr = dataurl.split(',');
      //注意base64的最后面中括号和引号是不转译的
        var _arr = arr[1].substring(0,arr[1].length-2);
        var mime = arr[0].match(/:(.*?);/)[1],
        bstr =atob(_arr),
        n = bstr.length,
        u8arr = new Uint8Array(n);
        while (n--) {
            u8arr[n] = bstr.charCodeAt(n);
        }
        return new Blob([u8arr], {
            type: mime
        });
    }
    // 将图片用canvas重画宽高缩小一倍
    function imgScale(img, angle, callback) {
        var cvs = document.createElement('canvas');
        var cvxTx = cvs.getContext('2d');
        var imgWidth = img.width;
        var imgHeight;
        var ww, hh, dataURL;
        var formatSize = function(w, h) {
            imgHeight = imgWidth * h / w;
            cvs.width = imgWidth;
            cvs.height = imgHeight;
        };

        if (angle == 90) {
            formatSize(img.height, img.width);
            cvxTx.translate(imgWidth, 0);
            ww = imgHeight;
            hh = imgWidth;
        } else if (angle == 180) {
            formatSize(img.width, img.height);
            cvxTx.translate(imgWidth, imgHeight);
            ww = imgWidth;
            hh = imgHeight;
        } else if (angle == 270) {
            formatSize(img.height, img.width);
            cvxTx.translate(0, imgHeight);
            ww = imgHeight;
            hh = imgWidth;
        } else {
            formatSize(img.width, img.height);
            ww = imgWidth;
            hh = imgHeight;
        }

        if (angle) {
            cvxTx.rotate(angle * Math.PI / 180);
        }

        cvxTx.drawImage(img, 0, 0, ww, hh);

        dataURL = cvs.toDataURL('image/jpeg', 0.7);
        callback && callback(dataURL);
    }
})();