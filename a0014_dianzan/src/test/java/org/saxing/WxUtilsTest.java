package org.saxing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.saxing.util.WxUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by saxing on 17-10-31.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BocApplication.class)
public class WxUtilsTest {


    @Test
    public void del1TokenTest(){
        WxUtils.del1Token();
    }

    @Test
    public void refreshTokenTest(){
        WxUtils.refreshToken();
    }


    @Test
    public void downloadTokenFromWxTest(){
        WxUtils.downloadTokenFromWx();
    }

    @Test
    public void getTokenTest(){
        String token = WxUtils.getToken();
        System.out.println(token);
    }

}
