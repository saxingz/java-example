package org.saxing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.saxing.service.ThumbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

/**
 * Created by saxing on 17-11-1.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BocApplication.class)
public class ThumbServiceTest {

    @Autowired
    private ThumbService thumbService;

    @Test
    public void doThumbTest(){
        thumbService.doThumb("LiuHan", 1, "LiuHan", 1, "员工好");
    }

    @Test
    public void getLast10Test(){
        String result = thumbService.getLastGood10();
        System.out.println(result);
    }

    @Test
    public void getDepartCount(){
        List<Map<String, Object>> list = thumbService.getDepartCount(201612);
        System.out.println(list);
    }

    @Test
    public void wxSendToDepartLeaderTest(){

    }

    @Test
    public void testGetGoodDetailByDepartAndMonth(){
        String result = thumbService.getGoodDetailByDepartAndMonth(1, 2017, 11, -1);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(result);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        result = thumbService.getGoodDetailByDepartAndMonth(1, 2017, 11, 3);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(result);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

    }
}
