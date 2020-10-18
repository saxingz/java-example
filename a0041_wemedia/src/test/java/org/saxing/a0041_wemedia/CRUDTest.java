package org.saxing.a0041_wemedia;

import org.junit.jupiter.api.Test;
import org.saxing.a0041_wemedia.domain.entity.TransferDO;
import org.saxing.a0041_wemedia.domain.enums.Platform;
import org.saxing.a0041_wemedia.mapper.TransferMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
    public void testInsert(){
        TransferDO transferDO = new TransferDO().setVideoId(1L).setDescription("")
                .setUrl("https://www.baidu.com")
                .setPublishTime(new Date())
                .setPlatform(Platform.BILIBILI.getName());
        int insert = transferMapper.insert(transferDO);
        System.out.println(insert);
    }

}
