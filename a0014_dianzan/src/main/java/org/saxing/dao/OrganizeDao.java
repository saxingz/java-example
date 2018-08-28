package org.saxing.dao;

import org.apache.commons.collections.CollectionUtils;
import org.saxing.bean.WxUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 存储组织关系
 *
 * Created by saxing on 18-1-19.
 * @author saxing
 */
@Repository
public class OrganizeDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public OrganizeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 保存数据
     * @param wxUser
     * @return
     * @throws Exception
     */
    public int saveUser(WxUser wxUser) throws Exception{
        String sql = "INSERT INTO wx_user(user_id, user_name, user_depart) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, wxUser.getUserId(), wxUser.getUserName(), wxUser.getUserDepartStr());
    }

    /**
     * 修改
     * @param wxUser
     * @return
     * @throws Exception
     */
    public int updateUser(WxUser wxUser) throws Exception{
        String sql = "UPDATE wx_user set user_depart=?, avatar=? WHERE user_id=?";
        return jdbcTemplate.update(sql, wxUser.getUserDepartStr(), wxUser.getAvatar(), wxUser.getUserId());
    }

    /**
     * 清空数据
     * @return
     */
    public int emptyUser(){
        String sql = "DELETE FROM wx_user";
        return jdbcTemplate.update(sql);
    }

    /**
     * 获取所有 userId
     * @return
     */
    public List<String> getAllUserId(){
        String sql = "SELECT user_id from wx_user";
        return jdbcTemplate.queryForList(sql, String.class);
    }

    /**
     * 按id查找
     * @param userId
     * @return
     */
    public List<WxUser> findUserById(String userId){
        String sql = "SELECT * FROM wx_user WHERE user_id=?";
        return jdbcTemplate.query(sql, new Object[]{userId}, new WxUser.ThumbRecordRowMapper());
    }

    /**
     * 查找部门名称
     *
     * @param departId
     * @return
     */
    public String getDepartName(Integer departId){
        String sql = "SELECT depart_name FROM depart_count WHERE depart_id = ?";
        List<String> names = jdbcTemplate.queryForList(sql, new Object[]{departId}, String.class);
        if (CollectionUtils.isNotEmpty(names)){
            return names.get(0);
        }else{
            return "";
        }
    }

}
