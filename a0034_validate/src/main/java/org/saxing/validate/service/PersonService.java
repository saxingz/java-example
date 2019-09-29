package org.saxing.validate.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author 白亮
 *         创建时间： 2016-10-10 21:08
 *         创建任务号：YHJY-
 *         创建原因：
 */
@Service
@Validated
public class PersonService {
    public void createPerson(@NotNull String name,@Min(18) int age) {
        System.out.println(name);
        System.out.println(age);
    }

}

