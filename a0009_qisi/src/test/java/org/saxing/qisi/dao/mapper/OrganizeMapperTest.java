package org.saxing.qisi.dao.mapper;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * OrganizeMapperTest
 *
 * Created by saxing on 2018/5/6.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrganizeMapperTest {

    @Autowired
    private OrganizeMapper organizeMapper;

    @Test
    public void clearWxUser() throws Exception {
        organizeMapper.clearWxUser();
    }

    @Test
    public void clearWxDepart() throws Exception {
        organizeMapper.clearWxDepart();
    }

    @Test
    public void saveAllDeparts() throws Exception {
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", "name" + i);
            map.put("id", i);
            map.put("parentId", i * 5);
            map.put("order", 2222);
            list.add(map);
        }

        organizeMapper.saveAllDeparts(list);

    }

    @Test
    public void insertWxUser() throws Exception{
        Map<String, Object> map = new HashMap<>();
        map.put("userId", "111");
        map.put("name", "222");
        map.put("avatar", "333");
        int result = organizeMapper.insertWxUser(map);
        Assert.assertTrue(result == 1);
    }

    @Test
    public void saveDepartUserRelation() throws Exception{
        Map<String, Object> departUserRelation = new HashMap<>();
        departUserRelation.put("departId", 1);
        departUserRelation.put("userId", "111");
        int result = organizeMapper.insertDepartUserRelation(departUserRelation);
        Assert.assertTrue(result == 1);
    }

    @Test
    public void findWxUserById() throws Exception{
        Map<String, Object> wxUser = organizeMapper.findWxUserById("LiuHan");
        Assert.assertNotNull(wxUser);
        Map<String, Object> wxUser2 = organizeMapper.findWxUserById("LiuHan2");
        Assert.assertNull(wxUser2);
    }

    @Test
    public void findDepartById() throws Exception{
        Map<String, Object> depart = organizeMapper.findDepartById(39);
        Assert.assertNotNull(depart);
    }
}