package org.saxing;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.saxing.service.OrganizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by saxing on 17-10-31.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BocApplication.class)
public class OrganizeServiceTest {

    @Autowired
    OrganizeService organizeService;

    @Test
    public void downLoadDepartFromWxTest(){
        JSONObject jsonObject = organizeService.downLoadDepartFromWx(0);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(jsonObject.toString());
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    @Test
    public void downLoadDepartUserFromWxTest(){
        JSONObject jsonObject = organizeService.downLoadDepartUserFromWx(4);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(jsonObject);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    @Test
    public void getDepartAndUserTest(){
        JSONObject jsonObject = organizeService.getDepartAndUser(1);
        System.out.println(jsonObject);
        JSONObject jsonObject2 = organizeService.getDepartAndUser(2);
        System.out.println(jsonObject2);
    }

    @Test
    public void downLoadUserFromWxTest(){
        JSONObject jsonObject = organizeService.downLoadUserFromWx("LiuHan");
        System.out.println(jsonObject);
    }

    @Test
    public void testGetAllUser(){
        organizeService.refreshAllUserId();
    }

}
