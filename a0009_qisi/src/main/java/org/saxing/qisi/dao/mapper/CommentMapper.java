package org.saxing.qisi.dao.mapper;

import org.apache.ibatis.annotations.*;
import org.saxing.qisi.utils.StringConstant;

import java.util.List;
import java.util.Map;

/**
 * comment mapper
 *
 * Created by saxing on 2018/5/8.
 */
public interface CommentMapper {

    /**
     * insert
     *
     * @param params
     * @return
     */
    @Insert("INSERT INTO `comment` (`article_id`, `author_wxid`, `content`)" +
            " VALUES (#{articleId,jdbcType=INTEGER}, #{authorWxId,jdbcType=VARCHAR}, #{content,jdbcType=VARCHAR});")
    int insertComment(Map<String, Object> params);

    /**
     * find comment
     *
     * @param commentId
     * @return
     */
    @Select("SELECT `id`, `article_id`, `author_wxid`, `create_time`, `update_time`, `content`, `thumb_num`" +
            " FROM comment WHERE id=#{commentId, jdbcType=INTEGER}")
    @Results({
            @Result(column = "id", property = StringConstant.COMMENT_ID),
            @Result(column = "article_id", property = StringConstant.COMMENT_ARTICLE_ID),
            @Result(column = "author_wxid", property = StringConstant.COMMENT_AUTHOR_WX_ID),
            @Result(column = "create_time", property = StringConstant.CREATE_TIME),
            @Result(column = "update_time", property = StringConstant.UPDATE_TIME),
            @Result(column = "content", property = StringConstant.COMMENT_CONTENT),
            @Result(column = "thumb_num", property = StringConstant.COMMENT_THUMB_NUM)
    })
    Map<String,Object> findCommentById(int commentId);

    /**
     * update
     *
     * @param comment
     * @return
     */
    @Update("UPDATE `comment` SET `article_id`=#{"+StringConstant.COMMENT_ARTICLE_ID+",jdbcType=INTEGER}, " +
            " `author_wxid`=#{"+StringConstant.COMMENT_AUTHOR_WX_ID+",jdbcType=VARCHAR}, " +
            " `content`=#{"+StringConstant.COMMENT_CONTENT+",jdbcType=VARCHAR}, " +
            " `thumb_num`= #{"+StringConstant.COMMENT_THUMB_NUM+",jdbcType=INTEGER} " +
            " WHERE `id`= #{"+StringConstant.COMMENT_ID+",jdbcType=INTEGER};")
    int updateComment(Map<String, Object> comment);

    /**
     * save thumber
     *
     * @param commentId
     * @param wxId
     * @return
     */
    @Insert("INSERT INTO `comment_thumbup` (`comment_id`, `thumb_userid`) VALUES (#{commentId,jdbcType=INTEGER}, #{wxId,jdbcType=VARCHAR});")
    int saveThumber(@Param("commentId") int commentId, @Param("wxId") String wxId);

    /**
     * find comments
     *
     * @param articleId
     * @return
     */
    @Select("SELECT `id`, `article_id`, `author_wxid`, `create_time`, `update_time`, `content`, `thumb_num`" +
            " FROM comment WHERE article_id=#{articleId,jdbcType=INTEGER} ORDER BY thumb_num DESC, create_time DESC")
    @Results({
            @Result(column = "id", property = StringConstant.COMMENT_ID),
            @Result(column = "article_id", property = StringConstant.COMMENT_ARTICLE_ID),
            @Result(column = "author_wxid", property = StringConstant.COMMENT_AUTHOR_WX_ID),
            @Result(column = "create_time", property = StringConstant.CREATE_TIME),
            @Result(column = "update_time", property = StringConstant.UPDATE_TIME),
            @Result(column = "content", property = StringConstant.COMMENT_CONTENT),
            @Result(column = "thumb_num", property = StringConstant.COMMENT_THUMB_NUM)
    })
    List<Map<String,Object>> findCommentsOrderByThumbNum(int articleId);

    /**
     * find comments
     *
     * @param articleId
     * @return
     */
    @Select("SELECT `id`, `article_id`, `author_wxid`, `create_time`, `update_time`, `content`, `thumb_num`" +
            " FROM comment WHERE article_id=#{articleId,jdbcType=INTEGER} ORDER BY create_time DESC")
    @Results({
            @Result(column = "id", property = StringConstant.COMMENT_ID),
            @Result(column = "article_id", property = StringConstant.COMMENT_ARTICLE_ID),
            @Result(column = "author_wxid", property = StringConstant.COMMENT_AUTHOR_WX_ID),
            @Result(column = "create_time", property = StringConstant.CREATE_TIME),
            @Result(column = "update_time", property = StringConstant.UPDATE_TIME),
            @Result(column = "content", property = StringConstant.COMMENT_CONTENT),
            @Result(column = "thumb_num", property = StringConstant.COMMENT_THUMB_NUM)
    })
    List<Map<String,Object>> findCommentsOrderByTime(int articleId);

    /**
     * find thumber
     *
     * @param commentId
     * @return
     */
    @Select("SELECT `thumb_userid` FROM comment_thumbup WHERE comment_id=#{commentId,jdbcType=INTEGER}")
    List<String> findThumber(int commentId);
}
