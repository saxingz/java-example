package org.saxing.a0041_wemedia;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.saxing.a0041_wemedia.domain.entity.ChannelDO;
import org.saxing.a0041_wemedia.domain.entity.TransferDO;
import org.saxing.a0041_wemedia.domain.enums.Platform;
import org.saxing.a0041_wemedia.logic.IChannelLogic;
import org.saxing.a0041_wemedia.mapper.TransferMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

/**
 * crud test
 *
 * @author saxing 2020/10/18 16:39
 */
@SpringBootTest
public class CRUDTest {

    @Autowired
    TransferMapper transferMapper;
    @Autowired
    IChannelLogic channelLogic;

    @Test
    @Transactional
    public void testInsert(){
        TransferDO transferDO = new TransferDO().setVideoId(1L)
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
        TransferDO transferDO = new TransferDO().setId(1L);
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

    @Test
    @Transactional
    public void testChannel() {
        for (int i = 0; i < 400; i++) {
            ChannelDO channelDO = new ChannelDO()
                    .setChannelId(i + UUID.randomUUID().toString())
                    .setChannelTitle("title " + i + UUID.randomUUID().toString())
                    .setFollowTime(new Date())
                    .setStartTime(new Date())
                    ;
            channelLogic.save(channelDO);
        }
    }

}
