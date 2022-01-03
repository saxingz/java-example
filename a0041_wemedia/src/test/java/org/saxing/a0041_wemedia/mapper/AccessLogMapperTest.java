package org.saxing.a0041_wemedia.mapper;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.saxing.a0041_wemedia.domain.entity.AccessLogDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccessLogMapperTest {

    @Autowired
    private AccessLogMapper accessLogMapper;

    @Test
    void queryByPath() {
        Map<String, Object> atmMap = new LinkedHashMap<>();
        Map<String, Integer> schoolMap = new HashMap<>();

        LocalDate date = LocalDate.of(2021, 1, 1);
        LocalDate stopDate = LocalDate.of(2021, 2, 3);
        for (LocalDate d = date; d.isBefore(stopDate); d = d.plusDays(1L)) {
            int year = d.getYear();
            int monthValue = d.getMonthValue();
            int dayOfMonth = d.getDayOfMonth();
            String monthStr = String.valueOf(monthValue);
            if (monthValue < 10) {
                monthStr = "0" + monthStr;
            }
            String dayStr = String.valueOf(dayOfMonth);
            if (dayOfMonth < 10) {
                dayStr = "0" + dayStr;
            }
            String key = year + monthStr + dayStr;
            String tableName = "access_log_" + key;
            String queryPath = "/attendance/v1/admin/mi/walklake/user/getStudents";
            List<AccessLogDO> accessLogDOList = accessLogMapper.queryByPath(tableName, queryPath);
            System.out.println(accessLogDOList);
            if (CollectionUtils.isNotEmpty(accessLogDOList)) {
                atmMap.put(key, accessLogDOList);
                for (AccessLogDO a : accessLogDOList) {
                    String[] split = a.getQueryParams().split(",");
                    for (String s : split) {
                        if (s.contains("schoolId")) {
                            Integer count = schoolMap.get(s);
                            if (Objects.isNull(count)) {
                                schoolMap.put(s, 1);
                            } else {
                                count += 1;
                                schoolMap.put(s, count);
                            }
                        }
                    }
                }
            }
        }

        System.out.println(atmMap);
        System.out.println(schoolMap);
    }
}