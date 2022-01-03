package org.saxing.a0047_spring_data_jdbc.repository;

import org.saxing.a0047_spring_data_jdbc.entity.AccessLogDO;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccessLogRepository extends CrudRepository<AccessLogDO, Long> {

    @Query("select * from access_log_20201201 where is_deleted = 0 and path like :path limit 1000")
    List<AccessLogDO> queryByPathInTable(
                                         @Param("path") String path);
}
