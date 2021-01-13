package org.saxing.java8;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Random;
import java.util.UUID;

public class StringSizeTest {

    public static String getId() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static void main(String[] args) throws IOException {

        for (int i = 0; i < 20000; i++) {
            String json = "{\"id\":\""+ getId() +"\",\"accountId\":\""+getId()+"\",\"realName\":\""+getId()+"\",\"avatarImgInfo\":{\"resourceId\":\""+getId()+"\",\"thumbnailUrl\":\"http://resource-fat.7kid.com/7kid/user/img/202008/"+getId()+"/maleTeacherDefaultAvatar.png?x-oss-process=image/resize,w_200,h_200/format,src/quality,Q_50\",\"compressUrl\":\"http://resource-fat.7kid.com/7kid/user/img/202008/"+getId()+"/maleTeacherDefaultAvatar.png?x-oss-process=image/quality,Q_50\",\"height\":"+new Random().nextInt(1000) +",\"width\":"+new Random().nextInt(1000)+"},\"jobList\":[{\"id\":\""+getId()+"\",\"organizationId\":\""+getId()+"\",\"name\":\""+getId()+"\"}]}";
            System.out.println("i: " + i + "\t  json: " + json);
            FileUtils.writeLines(new File("branch.txt"), Collections.singleton(json), true);
        }


    }

}
