package org.saxing.a0041_wemedia;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.saxing.a0041_wemedia.domain.entity.TransferDO;
import org.saxing.a0041_wemedia.domain.enums.Platform;
import org.saxing.a0041_wemedia.mapper.TransferMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * crud test
 *
 * @author saxing 2020/10/18 16:39
 */
@SpringBootTest
public class CRUDTest {

    @Autowired
    TransferMapper transferMapper;

    @Test
    @Transactional
    public void testInsert(){
        TransferDO transferDO = new TransferDO().setVideoId(1L).setDescription("")
                .setUrl("https://www.baidu.com")
                .setPublishTime(new Date())
                .setPlatform(Platform.BILIBILI.getName());
        int insert = transferMapper.insert(transferDO);
        for (int i = 0; i < 100; i++) {
            transferMapper.insert(transferDO);
        }
        System.out.println(insert);
    }

    @Test
    @Transactional
    public void testUpdate(){
        TransferDO transferDO = new TransferDO().setId(1L).setDescription("hello description");
        int insert = transferMapper.updateById(transferDO);
        System.out.println(insert);
    }

    @Test
    @Transactional
    public void testPage(){
        Page<TransferDO> transferDOPage = new Page<>(1, 5);
        transferMapper.selectPage(transferDOPage, null);
        transferDOPage.getRecords().forEach(System.out::println);
    }

    @Test
    @Transactional
    public void testDeleteById(){
        transferMapper.deleteById(1);
    }

}
