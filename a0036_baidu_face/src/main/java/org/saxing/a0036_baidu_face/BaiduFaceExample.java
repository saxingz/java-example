package org.saxing.a0036_baidu_face;

import com.baidu.aip.face.AipFace;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class BaiduFaceExample {

    //设置APPID/AK/SK
    public static final String APP_ID = "";
    public static final String API_KEY = "";
    public static final String SECRET_KEY = "";

    public static void main(String[] args) throws IOException {

        for (int i = 0; i < 12; i++) {
            byte[] fileContent = FileUtils.readFileToByteArray(new File("D:\\temp\\ceshi-face\\"+(i+1)+".jpg"));
            System.out.println("第 " + (i + 1) + "张");
            test(fileContent);
        }
    }

    public static void test(byte[] fileContent){
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("max_face_num", "10");
        options.put("match_threshold", "70");
        options.put("quality_control", "NORMAL");
        options.put("liveness_control", "NONE");
        options.put("max_user_num", "1");


        // 初始化一个AipFace
        AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//        client.setHttpProxy("192.168.50.2", 3128);  // 设置http代理
//        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理




        // 调用接口
//        String image = "取决于image_type参数，传入BASE64字符串或URL字符串或FACE_TOKEN字符串";
//        String image = Base64.getEncoder().encodeToString(fileContent);
//        String imageType = "BASE64";

        // 人脸检测
//        JSONObject res = client.detect(image, imageType, options);
//        String groupIdList = "group1";
//        JSONObject res = client.multiSearch(image, imageType, groupIdList, options);
//        if (res.toString().contains("songsenhai")){
//            System.out.println("========== true =============");
//        }
//        if (res.toString().contains("niumingliang")){
//            System.out.println("------- niumingliang ------------");
//        }
//        if (res.toString().contains("xushuai")){
//            System.out.println("#########################");
//        }
//        System.out.println(res.toString(2));
    }

}
