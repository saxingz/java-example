package org.saxing.mybatis_code_helper.bean;

import java.util.List;
import org.saxing.mybatis_code_helper.bean.Person;
public interface PersonService{

    int insert(Person person);

    int insertSelective(Person person);

    int insertList(List<Person> persons);

    int update(Person person);
}
