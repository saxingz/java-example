package org.saxing.mybatis_code_helper.bean;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import org.saxing.mybatis_code_helper.bean.Person;

@Mapper
public interface PersonDao {
    int insert(@Param("person") Person person);

    int insertSelective(@Param("person") Person person);

    int insertList(@Param("persons") List<Person> persons);

    int update(@Param("person") Person person);
}
