package org.saxing.bean;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by saxing on 18-1-19.
 */
public class WxUser {

    private static final long serialVersionUID = 2520869894112984149L;

    private String userId;

    private String userName;

    private String userDepartStr;

    private String avatar;

    public WxUser() {
    }

    public WxUser(String userId, String userName, String userDepartStr) {
        this.userId = userId;
        this.userName = userName;
        this.userDepartStr = userDepartStr;
    }

    public WxUser(String userId, String userName, String userDepartStr, String avatar) {
        this.userId = userId;
        this.userName = userName;
        this.userDepartStr = userDepartStr;
        this.avatar = avatar;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserDepartStr() {
        return userDepartStr;
    }

    public void setUserDepartStr(String userDepartStr) {
        this.userDepartStr = userDepartStr;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public static class ThumbRecordRowMapper implements RowMapper<WxUser> {

        @Override
        public WxUser mapRow(ResultSet resultSet, int i) throws SQLException {
            WxUser wxUser = new WxUser();
            wxUser.setUserId(resultSet.getString("user_id"));
            wxUser.setUserName(resultSet.getString("user_name"));
            wxUser.setUserDepartStr(resultSet.getString("user_depart"));
            wxUser.setAvatar(resultSet.getString("avatar"));
            return wxUser;
        }
    }
}
