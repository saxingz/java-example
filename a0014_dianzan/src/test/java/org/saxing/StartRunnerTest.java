package org.saxing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.saxing.service.StartRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author saxing
 * @description: []
 * Created on 2017/11/2 23:32
 * modified By  []
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StartRunnerTest {

    @Autowired
    StartRunner startRunner;

    @Test
    public void executeDepartTest(){
        startRunner.executeDepart();
    }

}
