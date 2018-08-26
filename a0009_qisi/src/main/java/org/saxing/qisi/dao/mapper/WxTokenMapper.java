package org.saxing.qisi.dao.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

/**
 * token
 *
 * Created by saxing on 2018/5/5.
 */
public interface WxTokenMapper {

    /**
     * 查找总数
     *
     * @return
     */
    @Select("SELECT COUNT(1) FROM wx_token")
    int queryTokenNum();

    /**
     * 插入一个
     * @param token
     * @return
     */
    @Insert("INSERT INTO wx_token(token) values(#{token, jdbcType=VARCHAR})")
    int insertToken(String token);

    /**
     * 删除第一个token
     * @return
     */
    @Delete("DELETE FROM wx_token LIMIT 1")
    int delete1stToken();

    /**
     * 获取第一个token
     * @return
     */
    @Select("SELECT token FROM wx_token ORDER BY id DESC LIMIT 1")
    String get1stToken();
}
