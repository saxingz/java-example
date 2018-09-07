package org.saxing.mybatis_code_helper.dao;

import org.apache.ibatis.annotations.Mapper;
import org.saxing.mybatis_code_helper.bean.Person;

@Mapper
public interface IPerson {

    public Person findByName();

}
