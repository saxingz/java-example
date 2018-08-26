package org.saxing.qisi.dao.mapper;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

/**
 * test
 *
 * Created by saxing on 2018/5/5.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WxTokenMapperTest {

    @Autowired
    private WxTokenMapper wxTokenMapper;

    @Test
    public void queryTokenNum() throws Exception {
        int num = wxTokenMapper.queryTokenNum();
        System.out.println("queryTokenNum: " + num);
        Assert.assertTrue(num >= 0);
    }

    @Test
    public void insertToken(){
        int num = wxTokenMapper.insertToken("this is a token");
        Assert.assertTrue(num == 1);
    }

    @Test
    public void delete1stToken(){
        int num = wxTokenMapper.delete1stToken();
        Assert.assertTrue(num == 1);
    }

    @Test
    public void get1stToken(){
        String token = wxTokenMapper.get1stToken();
        System.out.println("get1stToken: " + token);
        Assert.assertTrue(!StringUtils.isEmpty(token));
    }

}