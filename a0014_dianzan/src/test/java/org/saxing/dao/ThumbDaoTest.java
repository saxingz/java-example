package org.saxing.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 *
 * Created by saxing on 2018/3/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ThumbDaoTest {

    @Autowired
    ThumbDao thumbDao;

    @Test
    public void testGetPersonRank(){
        List<Map<String, Object>> res = thumbDao.getPersonRank(1, 4, "2017-12-31 00:00:00 ", "2019-01-01 00:00:00");
        System.out.println(res);
    }

}