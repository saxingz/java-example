package org.saxing.qisi.dao.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.*;
import java.text.SimpleDateFormat;

/**
 * DateStringHandler
 *
 * Created by saxing on 2018/5/19.
 */
@MappedJdbcTypes({JdbcType.TIMESTAMP, JdbcType.VARCHAR})
@MappedTypes({Timestamp.class, Date.class, String.class})
public class DateStringHandler extends BaseTypeHandler<String> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, String s, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, s);
    }

    @Override
    public String getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(resultSet.getLong(s)));
    }

    @Override
    public String getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(resultSet.getLong(i)));
    }

    @Override
    public String getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(callableStatement.getLong(i)));
    }
}
