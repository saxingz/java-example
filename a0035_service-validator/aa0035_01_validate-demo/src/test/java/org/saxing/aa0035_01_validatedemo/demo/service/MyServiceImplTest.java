package org.saxing.aa0035_01_validatedemo.demo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.saxing.aa0035_01_validatedemo.demo.dto.DO;
import org.saxing.aa0035_01_validatedemo.demo.dto.DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyServiceImplTest {

    @Autowired
    MyServiceImpl myService;

    @Test
    public void doSomething() {
        DTO dto = new DTO();
        dto.setSons(new ArrayList<>());
        dto.setText("fff");
        dto.setNumber(3);
        myService.doSomething(dto);
    }

    @Test
    public void test2(){
        List<DTO> dto2List = new ArrayList<>();
        DTO dto2 = new DTO();
        dto2List.add(dto2);


        List<DTO> dtos = new ArrayList<>();
        DTO dto1 = new DTO();
        dto1.setText("aa");
        dto1.setNumber(1);
        dto1.setSons(dto2List);
        dtos.add(dto1);
        myService.test2(dtos);
    }

    @Test
    public void testParam(){
        DTO dto = new DTO();
        DO doo = new DO();
        boolean res = myService.testParam(dto, doo);
        System.out.println(res);
    }

    @Test
    public void testVoid(){
        boolean res = myService.testVoid();
        System.out.println(res);
    }

}