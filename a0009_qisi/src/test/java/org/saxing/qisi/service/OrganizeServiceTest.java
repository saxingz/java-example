package org.saxing.qisi.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by saxing on 2018/5/6.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrganizeServiceTest {

    @Autowired
    private OrganizeService organizeService;

    @Test
    public void refreshAllUserId() throws Exception {
        organizeService.refreshAllUserId();
    }

    @Test
    public void downLoadDepartFromWx() throws Exception {

    }

    @Test
    public void downLoadDepartUserFromWx() throws Exception {

    }

    @Test
    public void downLoadUserFromWx() throws Exception {

    }

    @Test
    public void refreshAllDepart() throws Exception{
        organizeService.refreshAllDepart();
    }

}