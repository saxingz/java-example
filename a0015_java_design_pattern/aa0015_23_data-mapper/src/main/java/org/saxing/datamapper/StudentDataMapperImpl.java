package org.saxing.datamapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Student data mapper impl
 *
 * @author saxing 2018/12/5 21:02
 */
public class StudentDataMapperImpl implements StudentDataMapper {

    private List<Student> students = new ArrayList<>();

    public List<Student> getStudents() {
        return students;
    }

    @Override
    public Optional<Student> find(int studentId) {
        for (final Student student : this.getStudents()){
            if (student.getStudentId() == studentId){
                return Optional.of(student);
            }
        }
        return Optional.empty();
    }

    @Override
    public void insert(Student student) throws DataMapperException {
        if (this.getStudents().contains(student)){
            throw new DataMapperException("Student already [" + student.getName() + "] exists");
        }else {
            this.getStudents().add(student);
        }
    }

    @Override
    public void update(Student student) throws DataMapperException {
        if (this.getStudents().contains(student)){
            final int index = this.getStudents().indexOf(student);
            this.getStudents().set(index, student);
        } else {
            throw new DataMapperException("Student [" + student.getName() + "] is not found");
        }
    }

    @Override
    public void delete(Student student) throws DataMapperException {
        if (this.getStudents().contains(student)){
            this.getStudents().remove(student);
        } else {
            throw new DataMapperException("Student [" + student.getName() + "] is not found");
        }
    }
}
