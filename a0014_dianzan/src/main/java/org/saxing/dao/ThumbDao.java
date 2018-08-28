package org.saxing.dao;

import org.json.JSONObject;
import org.saxing.bean.ThumbRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

/**
 * @author saxing
 * @description
 * @time 2017/10/30 0030 14:34
 */
@Repository
public class ThumbDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ThumbDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 保存结果
     *
     * @param thumbRecord
     * @return
     * @author saxing on 2017/10/30 0030 14:55
     */
    public int saveThumbup(ThumbRecord thumbRecord){
        String sql = "INSERT INTO thumb_up("
                + "`from_userid`, `from_username`, `from_depart`, `from_position`, `from_mobile`, `from_gender`, `from_email`, "
                + "`from_avatar`, `from_telephone`, `from_status`, `thumb_up`, `reason`, `to_isleader`, "
                + "`to_department_id`, `to_department_name`, `to_userid`, `to_username`, `to_position`, "
                + "`to_mobile`, `to_gender`, `to_email`, `to_avatar`, `to_telephone`, `to_status`)"

                + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(sql,
                thumbRecord.getFromUserId(),
                thumbRecord.getFromUsername(),
                thumbRecord.getFromDepartName(),
                thumbRecord.getFromPosition(),
                thumbRecord.getFromMobile(),
                thumbRecord.getFromGender(),
                thumbRecord.getFromEmail(),
                thumbRecord.getFromAvatar(),
                thumbRecord.getFromTelephone(),
                thumbRecord.getFromStatus(),
                thumbRecord.getThumbUp(),
                thumbRecord.getReason(),
                thumbRecord.getToIsleader(),
                thumbRecord.getToDepartmentId(),
                thumbRecord.getToDepartmentName(),
                thumbRecord.getToUserId(),
                thumbRecord.getToUserName(),
                thumbRecord.getToPosition(),
                thumbRecord.getToMobile(),
                thumbRecord.getToGender(),
                thumbRecord.getToEmail(),
                thumbRecord.getToAvatar(),
                thumbRecord.getToTelephone(),
                thumbRecord.getToStatus()
        );
    }

    /**
     * 根据开始时间和结束时间获取
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public List<ThumbRecord> getThumb(String startDate, String endDate){
        String sql = "SELECT"
                + "`from_userid`, `from_username`, `from_depart`, `from_position`, `from_mobile`, `from_gender`, `from_email`, "
                + "`from_avatar`, `from_telephone`, `from_status`, `thumb_up`, `reason`, `to_isleader`, "
                + "`to_department_id`, `to_department_name`, `to_userid`, `to_username`, `to_position`, "
                + "`to_mobile`, `to_gender`, `to_email`, `to_avatar`, `to_telephone`, `to_status`, `create_time`"
                + "FROM thumb_up WHERE create_time >= ? and create_time < ?";
        return jdbcTemplate.query(sql, new Object[]{startDate, endDate}, new ThumbRecord.ThumbRecordRowMapper());
    }

    public List<ThumbRecord> getLastGood10() {
        String sql = "SELECT " +
                "`from_userid`, `from_username`, `from_depart`, `from_position`, `from_mobile`, `from_gender`, `from_email`, " +
                "`from_avatar`, `from_telephone`, `from_status`, `thumb_up`, `reason`, `to_isleader`, " +
                "`to_department_id`, `to_department_name`, `to_userid`, `to_username`, `to_position`, `to_mobile`, " +
                "`to_gender`, `to_email`, `to_avatar`, `to_telephone`, `to_status`, `create_time`" +
                " from thumb_up WHERE thumb_up = 1 ORDER BY id DESC LIMIT 10";
        List<ThumbRecord> records = jdbcTemplate.query(sql, new ThumbRecord.ThumbRecordRowMapper());
        return records;
    }


    public List<ThumbRecord> getCurMonthGood(String start, String end) {
        String sql = "SELECT " +
                "`from_userid`, `from_username`, `from_depart`, `from_position`, `from_mobile`, `from_gender`, `from_email`, " +
                "`from_avatar`, `from_telephone`, `from_status`, `thumb_up`, `reason`, `to_isleader`, " +
                "`to_department_id`, `to_department_name`, `to_userid`, `to_username`, `to_position`, `to_mobile`, " +
                "`to_gender`, `to_email`, `to_avatar`, `to_telephone`, `to_status`, `create_time`" +
                " from thumb_up WHERE thumb_up = 1 AND create_time > ? AND create_time < ? ORDER BY id DESC";
        List<ThumbRecord> records = jdbcTemplate.query(sql, new Object[]{start, end}, new ThumbRecord.ThumbRecordRowMapper());
        return records;
    }

    /**
     * 如果查询所有部门， 则传-1
     * @param thumb
     * @param start
     * @param end
     * @param toDepartId
     * @return
     */
    public List<ThumbRecord> getByDepartIdAndMonth(int thumb, String start, String end, int toDepartId){
        String sql = "SELECT " +
                "`from_userid`, `from_username`, `from_depart`, `from_position`, `from_mobile`, `from_gender`, `from_email`, " +
                "`from_avatar`, `from_telephone`, `from_status`, `thumb_up`, `reason`, `to_isleader`, " +
                "`to_department_id`, `to_department_name`, `to_userid`, `to_username`, `to_position`, `to_mobile`, " +
                "`to_gender`, `to_email`, `to_avatar`, `to_telephone`, `to_status`, `create_time`" +
                " from thumb_up WHERE thumb_up = ?" +
                " AND create_time > ? AND create_time < ?" +
                (toDepartId < 0 ? "" : "AND to_department_id = ?") +
                " ORDER BY id DESC";
        Object[] params = null;

        params = toDepartId < 0 ? new Object[]{thumb, start, end} : new Object[]{thumb, start, end, toDepartId};

        List<ThumbRecord> records = jdbcTemplate.query(sql, params, new ThumbRecord.ThumbRecordRowMapper());
        return records;
    }

    public List<Map<String, Object>> getDepartCount(int yearMonth){
        String sql = "SELECT * FROM depart_count WHERE yearmonth = ? ORDER BY good DESC";
        return jdbcTemplate.queryForList(sql, yearMonth);
    }

    /**
     * 获取年度统计数量
     * @param start
     * @param end
     * @return
     */
    public List<Map<String, Object>> getYearCount(int start, int end){
        String sql =
                "SELECT " +
                        "  depart_id AS depart_id, " +
                        "  depart_name as depart_name, " +
                        "  sum(good) as good, " +
                        "  sum(bad) as bad " +
                        "FROM " +
                        "   depart_count " +
                        "WHERE yearmonth > ? and yearmonth < ? " +
                        "GROUP BY depart_id ORDER BY good DESC;";
        Object[] params = new Object[]{start, end};
        return jdbcTemplate.queryForList(sql, params);
    }


    /**
     * 获取点赞总榜
     * @return
     */
    public List<Map<String, Object>> getTotalDepartCount(){
        String sql =
                "SELECT " +
                "  depart_id AS depart_id, " +
                "  depart_name as depart_name, " +
                "  sum(good) as good, " +
                "  sum(bad) as bad " +
                "FROM " +
                "   depart_count " +
                "GROUP BY depart_id ORDER BY good DESC;";
        return jdbcTemplate.queryForList(sql);
    }

    public void delDepartCount(int yearMonth) {
        String sql = "DELETE FROM depart_count WHERE yearmonth = ?";
        jdbcTemplate.update(sql, yearMonth);
    }

    public void saveDepartCount(int departId, String departName, int good, int bad, int yearMonth) {
        String sql = "INSERT INTO `thumb_up`.`depart_count` (`depart_id`, `depart_name`, `good`, `bad`, `yearmonth`) " +
                "VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, departId, departName, good, bad, yearMonth);
    }

    public List<Map<String, Object>> getPersonRank(int thumb, int departId, String startTime, String endTime){
        String sql = "SELECT" +
                " *" +
                " FROM" +
                " (" +
                " SELECT" +
                " to_userid," +
                " to_username," +
                " sum(thumb_up) AS num" +
                " FROM" +
                " thumb_up" +
                " WHERE" +
                " thumb_up = ?" +
                " AND to_userid != ''" +
                " AND create_time > ?" +
                " AND create_time < ?" +
                " AND to_department_id = ?" +
                " GROUP BY" +
                " to_userid" +
                " ) x" +
                " ORDER BY" +
                " x.num DESC";
        Object[] params = new Object[]{thumb, startTime, endTime, departId};
        List<Map<String, Object>> res = jdbcTemplate.query(sql, params, new ResultSetExtractor<List<Map<String, Object>>>() {
            @Override
            public List<Map<String, Object>> extractData(ResultSet resultSet) throws SQLException, DataAccessException {

                List<Map<String, Object>> r = new ArrayList<>();
                try {
                    while (resultSet.next()){
                        ResultSetMetaData rsd = resultSet.getMetaData();
                        int clength = rsd.getColumnCount();
                        Map<String, Object> m = new HashMap<String, Object>();

                        for (int i = 0; i < clength; i++){
                            m.put(rsd.getColumnLabel(i+1), resultSet.getObject(i + 1));
                        }
                        r.add(m);
                    }
                    return r;
                }catch (Exception e){

                }
                return null;
            }
        });
        return res;
    }

}
