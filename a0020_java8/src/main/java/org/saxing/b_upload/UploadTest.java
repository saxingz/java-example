package org.saxing.b_upload;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.io.file.FileWriter;
import cn.hutool.http.HttpUtil;
import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class UploadTest {

    public static String domain = "http://192.168.50.27:8012";


    public static void main(String[] args) {

        // 生成文件
//        genfile();

        // 批量上传
//        batchUpload();

        // 批量加入渲染任务
//        batchAddTask();

        // 批量读取 情况1：未渲染的文件  情况2：已渲染好的文件
        new Thread(() -> batchPreview(0)).start();
        new Thread(() -> batchPreview(1000)).start();
        new Thread(() -> batchPreview(2000)).start();
        new Thread(() -> batchPreview(3000)).start();
        new Thread(() -> batchPreview(4000)).start();
        new Thread(() -> batchPreview(5000)).start();
        new Thread(() -> batchPreview(6000)).start();
        new Thread(() -> batchPreview(7000)).start();
        new Thread(() -> batchPreview(8000)).start();
        new Thread(() -> batchPreview(9000)).start();


        // 批量删除
//        delFile(0);
//        delFile(1000);
//        delFile(2000);

    }

    private static void batchPreview(int base) {
        for (int i = 0; i < 1000; i++) {
            try {
                String fileName = domain + "/demo/" + (i+base) + ".docx";
                String base64 = Base64.encodeBase64String(fileName.getBytes(StandardCharsets.UTF_8));
                String url = URLEncoder.encode(base64, StandardCharsets.UTF_8.name());
                System.out.println("fileName:  " + fileName + ", url: " + url);

                String previewUrl = domain + "/onlinePreview?url=" + url;
                String result = HttpUtil.get(previewUrl);
                System.out.println(result);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }


//        String result = HttpUtil.get(domain + "/onlinePreview?url=aHR0cDovLzE5Mi4xNjguNTAuMjc6ODAxMi9kZW1vLzMwMDIuZG9jeA%3D%3D");
//        System.out.println(result);

    }

    private static void batchAddTask() {
        for (int i = 1; i <= 9999; i++) {
            String result = HttpUtil.get(domain + "/addTask?url=" + domain + "/demo/" + i + ".docx");
            System.out.println("序号： " + i + " 加入渲染任务， result: " + result);
        }
    }

    public static void delFile(int base){
        for (int i = 1; i <= 999; i++) {
            String result = HttpUtil.get(domain + "/deleteFile?fileName=" + (i + base) + ".docx");
            System.out.println("删除： " + (i + base) + " .docx成功 " + result);
        }
    }

    public static void genfile() {
        FileReader fileReader = new FileReader("D:\\saprogram\\D_desktop\\test\\batch-testdoc\\1.docx");

        FileWriter writer;
        for (int i = 2; i < 10000; i++) {
            writer = new FileWriter("D:\\saprogram\\D_desktop\\test\\batch-testdoc\\"+i+".docx");
            writer.writeFromStream(fileReader.getInputStream());
        }

    }

    public static void batchUpload(){
        new Thread(() -> upload(0)).start();
        new Thread(() -> upload(1000)).start();
        new Thread(() -> upload(2000)).start();
//        new Thread(() -> upload(3000)).start();
//        new Thread(() -> upload(4000)).start();
//        new Thread(() -> upload(5000)).start();
//        new Thread(() -> upload(6000)).start();
//        new Thread(() -> upload(7000)).start();
//        new Thread(() -> upload(8000)).start();
//        new Thread(() -> upload(9000)).start();
    }

    public static void upload(int base) {
        for (int i = 1; i <= 999; i++) {
            HashMap<String, Object> paramMap = new HashMap<>();
            //文件上传只需将参数中的键指定（默认file），值设为文件对象即可，对于使用者来说，文件上传与普通表单提交并无区别
            paramMap.put("file", FileUtil.file("D:\\saprogram\\D_desktop\\test\\batch-testdoc\\"+(i+base)+".docx"));
            String result= HttpUtil.post(domain + "/fileUpload", paramMap);
            System.out.println("上传： " + (i+base) + " 文件结果： " + result);
        }
    }
}
