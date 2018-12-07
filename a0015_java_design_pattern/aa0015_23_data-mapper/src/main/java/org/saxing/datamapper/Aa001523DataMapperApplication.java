package org.saxing.datamapper;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

/**
 * main
 *
 * @author saxing 2018/12/5 22:10
 */
public class Aa001523DataMapperApplication {

    private static Logger log = Logger.getLogger(Aa001523DataMapperApplication.class);

    public static void main(String[] args) {
//        SpringApplication.run(Aa001523DataMapperApplication.class, args);
        /* Create new data mapper for type 'first' */
        final StudentDataMapper mapper = new StudentDataMapperImpl();

        /* Create new student */
        Student student = new Student(1, "Adam", 'A');

        /* Add student in respectibe store */
        mapper.insert(student);

        log.debug("App.main(), student : " + student + ", is inserted");

        /* Find this student */
        final Optional<Student> studentToBeFound = mapper.find(student.getStudentId());

        log.debug("App.main(), student : " + studentToBeFound + ", is searched");

        final Optional<Student> studentToBeFound2 = mapper.find(323333);

        log.debug("App.main(), student2 : " + studentToBeFound2 + ", is searched");

        /* Update existing student object */
        student = new Student(student.getStudentId(), "AdamUpdated", 'A');

        /* Update student in respectibe db */
        mapper.update(student);

        log.debug("App.main(), student : " + student + ", is updated");
        log.debug("App.main(), student : " + student + ", is going to be deleted");

        /* Delete student in db */
        mapper.delete(student);


    }
}
