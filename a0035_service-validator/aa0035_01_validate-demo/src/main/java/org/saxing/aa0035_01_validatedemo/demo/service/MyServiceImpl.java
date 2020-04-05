package org.saxing.aa0035_01_validatedemo.demo.service;

import org.saxing.aa0035_01_validatedemo.demo.dto.DO;
import org.saxing.aa0035_01_validatedemo.demo.dto.DTO;
import org.saxing.aa0035_01_validatedemo.demo.valid.DTOValidGroup;
import org.saxing.validator.annotation.ParamValidation;
import org.saxing.validator.annotation.ServiceValidation;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Service
public class MyServiceImpl implements MyService {

    @Override
    @ServiceValidation
    public void doSomething (DTO dto) {
        System.out.println(dto);
    }

    @Override
    @ServiceValidation
    public void test2(@NotNull(message = "dtos 不能为空") List<DTO> dtos) {
        System.out.println(dtos);
    }

    @Override
    @ServiceValidation
    public boolean testParam(@ParamValidation(groups = {DTO.update.class}, nullAble = false) DTO dto,
                             @ParamValidation  DO doo) {
        System.out.println(dto);
        System.out.println(doo);
        return false;
    }

    @Override
    public boolean testVoid() {
        return false;
    }
}
