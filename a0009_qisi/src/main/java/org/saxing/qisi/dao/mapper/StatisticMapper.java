package org.saxing.qisi.dao.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 *
 * StatisticMapper
 *
 * Created by saxing on 2018/5/9.
 */
public interface StatisticMapper {


    /**
     * queryDepartData
     *
     * @param startTime
     * @param endTime
     * @return
     */
    @Select("SELECT * from (" +
            " (" +
            "  SELECT IFNULL(g.goodNum, 0) as goodNum, IFNULL(g.departId, b.departId) as departId, " +
            "  IFNULL(g.departName, b.departName) as departName, IFNULL(b.badNum, 0) as badNum" +
            "  FROM " +
            " (" +
            " SELECT count(1) as goodNum, d.depart_id as departId, d.depart_name as departName" +
            "  FROM article a, depart_user du, depart d " +
            "  WHERE" +
            "  a.author_wxid = du.user_id" +
            "  AND du.depart_id = d.depart_id" +
            "  AND a.type=0" +
            "  AND a.create_time >= #{startTime, jdbcType=TIMESTAMP}" +
            "  AND a.create_time <= #{endTime, jdbcType=TIMESTAMP}" +
            "  GROUP BY d.depart_id" +
            " ) g" +
            " RIGHT JOIN " +
            " (" +
            " SELECT count(1) as badNum, d.depart_id as departId, d.depart_name as departName" +
            "  FROM article a, depart_user du, depart d " +
            "  WHERE" +
            "  a.author_wxid = du.user_id" +
            "  AND du.depart_id = d.depart_id" +
            "  AND a.type=1" +
            "  AND a.create_time >= #{startTime, jdbcType=TIMESTAMP}" +
            "  AND a.create_time <= #{endTime, jdbcType=TIMESTAMP}" +
            "  GROUP BY d.depart_id" +
            " " +
            " ) b ON b.departId = g.departId" +
            " ORDER BY g.goodNum ASC" +
            " )" +
            " UNION" +
            " (" +
            "  SELECT IFNULL(g.goodNum, 0) as goodNum, IFNULL(g.departId, b.departId) as departId, " +
            "  IFNULL(g.departName, b.departName) as departName, IFNULL(b.badNum, 0) as badNum" +
            "  FROM " +
            " (" +
            " " +
            " SELECT count(1) as goodNum, d.depart_id as departId, d.depart_name as departName" +
            "  FROM article a, depart_user du, depart d " +
            "  WHERE" +
            "  a.author_wxid = du.user_id" +
            "  AND du.depart_id = d.depart_id" +
            "  AND a.type=0" +
            "  AND a.create_time >= #{startTime, jdbcType=TIMESTAMP}" +
            "  AND a.create_time <= #{endTime, jdbcType=TIMESTAMP}" +
            "  GROUP BY d.depart_id" +
            " ) g" +
            " LEFT JOIN " +
            " (" +
            " SELECT count(1) as badNum, d.depart_id as departId, d.depart_name as departName" +
            "  FROM article a, depart_user du, depart d " +
            "  WHERE" +
            "  a.author_wxid = du.user_id" +
            "  AND du.depart_id = d.depart_id" +
            "  AND a.type=1" +
            "  AND a.create_time >= #{startTime, jdbcType=TIMESTAMP}" +
            "  AND a.create_time <= #{endTime, jdbcType=TIMESTAMP}" +
            "  GROUP BY d.depart_id" +
            " ) b ON b.departId = g.departId" +
            " )" +
            " ) res" +
            " ORDER BY res.goodNum DESC")
    List<Map<String,Object>> queryDepartData(@Param("startTime") LocalDateTime startTime,
                                             @Param("endTime") LocalDateTime endTime);

    @Select("SELECT COUNT(1) as num, a.author_wxid, u.user_name" +
            " FROM article a, depart_user du, depart d , wx_user u" +
            " WHERE" +
            " a.author_wxid = du.user_id" +
            " AND du.depart_id = d.depart_id" +
            " AND d.depart_id = #{departId, jdbcType=INTEGER}" +
            " AND a.author_wxid = u.user_id" +
            " AND a.type=0" +
            " AND a.create_time >= #{startTime, jdbcType=TIMESTAMP}" +
            " AND a.create_time <= #{endTime, jdbcType=TIMESTAMP}" +
            " GROUP BY a.author_wxid" +
            " ORDER BY num DESC")
    List<Map<String, Object>> queryPersonData(@Param("departId") int departId,
                                              @Param("startTime") LocalDateTime startTime,
                                              @Param("endTime") LocalDateTime endTime);
}
