package org.saxing.a0041_wemedia;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.saxing.a0041_wemedia.domain.entity.TransferDO;
import org.saxing.a0041_wemedia.mapper.TransferMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

/**
 * wrapper test
 * @author saxing 2020/10/18 19:40
 */
@SpringBootTest
public class WrapperTest {

    @Autowired
    TransferMapper transferMapper;

    @Test
    void wrapQuery(){
        QueryWrapper<TransferDO> wrapper = new QueryWrapper<>();
        wrapper.isNotNull("description")
                .isNotNull("video_id")
                .lt("video_id", 20);
        transferMapper.selectList(wrapper).forEach(System.out::println);
    }

    @Test
    void wrapQueryOne(){
        QueryWrapper<TransferDO> wrapper = new QueryWrapper<>();
        wrapper.eq("id", 10);
        TransferDO transferDO = transferMapper.selectOne(wrapper);
        System.out.println(transferDO);
    }

    @Test
    void wrapQueryBetween(){
        QueryWrapper<TransferDO> wrapper = new QueryWrapper<>();
        wrapper.between("id", 5, 8);
        transferMapper.selectList(wrapper).forEach(System.out::println);
    }

    @Test
    void wrapQueryList(){
        QueryWrapper<TransferDO> wrapper = new QueryWrapper<>();
        wrapper
                .notLike("url", "baidu").likeRight("url", "ding")
        ;
        transferMapper.selectList(wrapper).forEach(System.out::println);
        List<TransferDO> transferDOS = transferMapper.selectList(wrapper);
        transferDOS.forEach(System.out::println);
        List<Map<String, Object>> maps = transferMapper.selectMaps(wrapper);
        maps.forEach(System.out::println);
        List<Object> objects = transferMapper.selectObjs(wrapper);
        objects.forEach(System.out::println);
    }

    @Test
    void wrapQueryInSql(){
        QueryWrapper<TransferDO> wrapper = new QueryWrapper<>();
        wrapper
                .inSql("id", "select id from transfer where id < 6")
        ;
        transferMapper.selectList(wrapper).forEach(System.out::println);
    }

    @Test
    void wrapQueryOrder(){
        QueryWrapper<TransferDO> wrapper = new QueryWrapper<>();
        wrapper
                .orderByDesc("id")
        ;
        transferMapper.selectList(wrapper).forEach(System.out::println);
    }

}
