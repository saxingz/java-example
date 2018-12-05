package org.saxing.datamapper;

import java.util.Optional;

/**
 * Student data mapper
 * 
 * @author saxing 2018/12/5 22:05
 */
public interface StudentDataMapper {

    Optional<Student> find(int studentId);

    void insert(Student student) throws DataMapperException;

    void update(Student student) throws DataMapperException;

    void delete(Student student) throws DataMapperException;

}
