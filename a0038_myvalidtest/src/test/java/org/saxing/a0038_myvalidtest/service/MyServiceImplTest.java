package org.saxing.a0038_myvalidtest.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.saxing.a0038_myvalidtest.A0038MyvalidtestApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = A0038MyvalidtestApplication.class)
public class MyServiceImplTest {

    @Test
    public void testParam() {
        System.out.println("fffff");
    }
}