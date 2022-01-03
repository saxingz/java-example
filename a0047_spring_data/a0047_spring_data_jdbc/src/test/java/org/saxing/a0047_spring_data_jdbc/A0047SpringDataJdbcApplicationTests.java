package org.saxing.a0047_spring_data_jdbc;

import org.junit.jupiter.api.Test;
import org.saxing.a0047_spring_data_jdbc.entity.AccessLogDO;
import org.saxing.a0047_spring_data_jdbc.repository.AccessLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class A0047SpringDataJdbcApplicationTests {

    @Autowired
    private AccessLogRepository accessLogRepository;

    @Test
    void contextLoads() {
        String tableName = "access_log_20201201";
        String pathLike = "/attendance/v1/admin/mi/atm/%";
        List<AccessLogDO> accessLogDOS = accessLogRepository.queryByPathInTable(pathLike);
        System.out.println(accessLogDOS);
    }

}
