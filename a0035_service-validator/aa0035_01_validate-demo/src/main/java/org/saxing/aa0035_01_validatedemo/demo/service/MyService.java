package org.saxing.aa0035_01_validatedemo.demo.service;


import org.saxing.aa0035_01_validatedemo.demo.dto.DO;
import org.saxing.aa0035_01_validatedemo.demo.dto.DTO;

import java.util.List;

public interface MyService {

    void doSomething(DTO dto);

    void test2(List<DTO> dtos);

    boolean testParam(DTO dto, DO doo);

    boolean testVoid();
}
