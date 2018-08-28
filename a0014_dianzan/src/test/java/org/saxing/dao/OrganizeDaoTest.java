package org.saxing.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by saxing on 18-1-19.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrganizeDaoTest {

    @Autowired
    OrganizeDao organizeDao;

    @Test
    public void saveUser() throws Exception {
    }

    @Test
    public void emptyUser() throws Exception {
    }

    @Test
    public void getAllUserId() throws Exception {
        List<String> all =  organizeDao.getAllUserId();
        System.out.println("~~~~~~~~~~~~~~~~~~~~");
        System.out.println(all);
        System.out.println("~~~~~~~~~~~~~~~~~~~~");
    }

    @Test
    public void testFindUserById() throws Exception{
        System.out.println(organizeDao.findUserById("LiuHan"));
    }

}