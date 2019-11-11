package org.saxing.a0036_baidu_face;

import com.baidu.aip.face.AipFace;
import okhttp3.OkHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Objects;

public class BaiduFaceClient {

    public static AipFace getAipFace(){
        String APP_ID = "17539254";
        String API_KEY = "zFr5OLa3PbMwN2qg9iNdq1GP";
        String SECRET_KEY = "1pQg3II3RGcr2zi4CGL4dS6BNQtHoUot";
        // 初始化一个AipFace
        AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);
        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        return client;
    }

    public static void main(String[] args) throws IOException {
//        testQuery();
        testDetect();
//        testGetFaceList();
//        testyu();
//        testMultiSearch();
//        deleteGroup("16");
    }

    public static void deleteGroup(String groupId){

        AipFace aipFace = getAipFace();
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();


        // 删除用户组
        JSONObject res = aipFace.groupDelete(groupId, options);
        System.out.println(res.toString(2));
    }

    public static void testMultiSearch() throws IOException {
        AipFace aipFace = getAipFace();

        HashMap<String, String> options = new HashMap<>();
        options.put("max_face_num", "10");
        options.put("match_threshold", "60");
        options.put("quality_control", "HIGH");
        options.put("liveness_control", "NONE");
        options.put("max_user_num", "10");

//        String url = "http://resource-zh-ts.7kid.com/7kid/user/201911/62404517106295812.jpg?x-oss-process=image/quality,Q_50";
//        String url = "http://resource-zh-ts.7kid.com/7kid/user/201911/62437773038067712.jpg";
//        String url = "http://resource-zh-ts.7kid.com/7kid/user/201911/62639431860887557.jpg";
        String url = "http://resource-zh-ts.7kid.com/7kid/user/201911/62404517106295812.jpg?x-oss-process=image/resize,w_1200,h_1200,0/format,src,image/quality,Q_50";
//        byte[] bytes = OkhttpTest.getPic(url);
//        String base64Str = Base64.getEncoder().encodeToString(bytes);

        String imageType = "URL";
//        String imageType = "BASE64";
        String groupList = "group1,group0,group2";
        JSONObject result = aipFace.multiSearch(url, imageType, groupList, options);
        System.out.println(result.toString(2));

        if (Objects.equals("SUCCESS", result.getString("error_msg"))){
            JSONObject jsonResult = result.getJSONObject("result");
            JSONArray faceList = jsonResult.getJSONArray("face_list");
            for (int i = 0; i < faceList.length(); i++) {
                JSONObject face = faceList.getJSONObject(i);
                JSONArray userList = face.getJSONArray("user_list");
                for (int j = 0; j < userList.length(); j++) {
                    JSONObject userInfo = userList.getJSONObject(j);
                    String userIdStr = userInfo.getString("user_id");
                    System.out.println(userIdStr);
                }
            }
        }
    }

    public static void testGetFaceList(){
        AipFace aipFace = getAipFace();

        HashMap<String, String> options = new HashMap<String, String>();
        JSONObject jsonObject = aipFace.faceGetlist("liuhan2", "group1", options);
        System.out.println(jsonObject.toString(2));

    }



    public static void testyu(){
        Long id = 59649693012985864L;
        long a = id % 20;
        System.out.println(a);
    }

    public static void testDetect(){
        AipFace aipFace = getAipFace();
        HashMap<String, String> options;
        options = new HashMap<String, String>();
        options.put("face_field", "age");
        options.put("max_face_num", "10");
        options.put("face_type", "LIVE");
        options.put("liveness_control", "NONE");
//        JSONObject detect = aipFace.detect("http://resource-zh-ts.7kid.com/7kid/201910/58267674894929920.png", "URL", options);
        JSONObject detect = aipFace.detect("http://static.ws.126.net/lady/luoma/springfestival03/41.jpg", "URL", options);
//        JSONObject detect = aipFace.detect("http://news.cri.cn/gb/mmsource/images/2011/02/07/wc110207059.jpg", "URL", options);

        // 只有检测出一张人脸，才算检测通过，face_probability , 不是1的,不算过
        if (Objects.equals("SUCCESS", detect.getString("error_msg"))
                && Objects.equals(1, detect.getJSONObject("result").getInt("face_num"))
                && Objects.equals(1d, detect.getJSONObject("result")
                .getJSONArray("face_list").getJSONObject(0).getDouble("face_probability"))){
            System.out.println("1 face!");
        } else {
            System.out.println("more face!");
        }

        System.out.println(detect.toString(2));
    }

    public static void testQuery(){
        AipFace aipFace = getAipFace();
        JSONObject user = aipFace.getUser("liuhan2", "group1", new HashMap<>());
        System.out.println(user.toString(2));
    }


}
