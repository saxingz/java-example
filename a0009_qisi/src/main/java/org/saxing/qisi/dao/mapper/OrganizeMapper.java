package org.saxing.qisi.dao.mapper;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * oragnize mapper
 *
 * Created by saxing on 2018/5/6.
 */
public interface OrganizeMapper {

    /**
     * 清空user
     * @return
     */
    @Delete("DELETE FROM wx_user")
    int clearWxUser();

    /**
     * 清空depart
     * @return
     */
    @Delete("DELETE FROM depart")
    int clearWxDepart();

    /**
     * 插入所有部门
     * @param allDepartsData
     * @return
     */
    @Insert("<script> " +
            " insert into depart (`depart_id`, `parent_id`, `depart_name`, `order`)" +
            " values " +
            " <foreach collection=\"allDepartsData\" index=\"index\" item=\"item\" separator=\",\">" +
            " (#{item.id,jdbcType=INTEGER},#{item.parentId,jdbcType=INTEGER},#{item.name,jdbcType=VARCHAR},#{item.order,jdbcType=VARCHAR})" +
            " </foreach> " +
            "</script>")
    int saveAllDeparts(@Param("allDepartsData") List<Map<String, Object>> allDepartsData);

    /**
     * 查找部门
     * @param departId
     * @return
     */
    @Select("SELECT * FROM depart WHERE depart_id=#{departId, jdbcType=INTEGER}")
    Map<String, Object> selectDepartById(int departId);

    /**
     * 保存用户
     * @param wxUser
     * @return
     */
    @Insert("INSERT INTO `wx_user` (`user_id`, `user_name`, `avatar`) VALUES (#{userId, jdbcType=VARCHAR},#{name, jdbcType=VARCHAR},#{avatar, jdbcType=VARCHAR});")
    int insertWxUser(Map<String, Object> wxUser);

    /**
     * 保存用户部门关系
     * @param departUserRelation
     * @return
     */
    @Insert("INSERT INTO `depart_user` (`depart_id`, `user_id`) VALUES (#{departId, jdbcType=INTEGER}, #{userId, jdbcType=VARCHAR});")
    int insertDepartUserRelation(Map<String, Object> departUserRelation);

    /**
     * 更新用户
     * @param wUser
     * @return
     */
    @Update("UPDATE `wx_user` SET `user_name`=#{name, jdbcType=VARCHAR}, `avatar`=#{avatar, jdbcType=VARCHAR} WHERE (`user_id`=#{userId, jdbcType=VARCHAR});")
    int updateWxUser(Map<String, Object> wUser);

    /**
     * 查找
     * @param wxId
     * @return
     */
    @Select("SELECT * FROM wx_user WHERE user_id=#{wxId, jdbcType=VARCHAR}")
    Map<String,Object> findWxUserById(String wxId);

    /**
     * findDepartById
     *
     * @param departId
     * @return
     */
    @Select("SELECT * FROM depart WHERE depart_id=#{departId,jdbcType=INTEGER}")
    Map<String,Object> findDepartById(int departId);

    /**
     * findDepartByWxUserId
     *
     * @param wxId
     * @return
     */
    @Select("SELECT " +
            "   d.depart_id as departId, d.parent_id as parentId, d.depart_name as departName" +
            " FROM " +
            "   depart d, depart_user du, wx_user w " +
            " WHERE " +
            "   du.depart_id=d.depart_id AND w.user_id=du.user_id AND w.user_id=#{wxId,jdbcType=VARCHAR}")
    List<Map<String, Object>> findDepartByWxUserId(String wxId);

    /**
     * findReadersByArticleId
     *
     * @param articleId
     * @return
     */
    @Select("SELECT * FROM wx_user WHERE user_id IN " +
            "   (SELECT reader_wxid FROM article_read WHERE article_id=#{articleId, jdbcType=INTEGER})")
    List<Map<String, Object>> findReadersByArticleId(int articleId);

}
