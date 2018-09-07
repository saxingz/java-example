package org.saxing.mybatis_code_helper.bean;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import org.saxing.mybatis_code_helper.bean.Person;
import org.saxing.mybatis_code_helper.bean.PersonDao;
import org.saxing.mybatis_code_helper.bean.PersonService;

@Service
public class PersonServiceImpl implements PersonService{

    @Resource
    private PersonDao personDao;

    @Override
    public int insert(Person person){
        return personDao.insert(person);
    }

    @Override
    public int insertSelective(Person person){
        return personDao.insertSelective(person);
    }

    @Override
    public int insertList(List<Person> persons){
        return personDao.insertList(persons);
    }

    @Override
    public int update(Person person){
        return personDao.update(person);
    }
}
