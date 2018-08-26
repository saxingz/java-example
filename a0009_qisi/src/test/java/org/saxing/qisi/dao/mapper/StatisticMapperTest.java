package org.saxing.qisi.dao.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * StatisticMapperTest
 *
 * Created by saxing on 2018/5/9.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StatisticMapperTest {

    @Autowired
    private StatisticMapper statisticMapper;

    @Test
    public void queryDepartData() throws Exception {
        LocalDateTime s = LocalDateTime.of(2018, 1, 1, 0, 0, 0);
        LocalDateTime e = LocalDateTime.of(2019, 1, 1, 0, 0, 0);
        List<Map<String, Object>> result = statisticMapper.queryDepartData(s, e);
        System.out.println(result);
    }

    @Test
    public void queryPersonData() throws Exception{
        LocalDateTime s = LocalDateTime.of(2018, 1, 1, 0, 0, 0);
        LocalDateTime e = LocalDateTime.of(2019, 1, 1, 0, 0, 0);
        List<Map<String, Object>> result = statisticMapper.queryPersonData(4, s, e);
        System.out.println(result);
    }

}