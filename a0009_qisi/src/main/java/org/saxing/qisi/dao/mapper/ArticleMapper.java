package org.saxing.qisi.dao.mapper;

import org.apache.ibatis.annotations.*;
import org.saxing.qisi.utils.StringConstant;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * article mapper
 *
 * Created by saxing on 2018/5/6.
 */
public interface ArticleMapper {

    /**
     * 插入文章, 返回主键
     *
     * @param article
     * @return
     */
    @Insert("INSERT INTO `idea`.`article`" +
            " (`type`, `title`, `content`, `advice`, `author_wxid`, `del`) " +
            " VALUES (#{type, jdbcType=VARCHAR}, #{title, jdbcType=VARCHAR}, #{content, jdbcType=VARCHAR}," +
            " #{advice, jdbcType=VARCHAR}, #{wxUserId, jdbcType=VARCHAR}, 0);")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertArticle(Map<String, Object> article);

    /**
     * 插入图片
     * @param img
     * @return
     */
    @Insert("INSERT INTO `idea`.`imgaddress` (`article_id`, `pic_origin_name`, `pic_uid_name`, `pic_index`)" +
            " VALUES " +
            " (#{" + StringConstant.IMAGE_ARTICLE_ID + ", jdbcType=VARCHAR}, " +
            " #{" + StringConstant.IMAGE_ORI_NAME + ", jdbcType=VARCHAR}," +
            " #{" + StringConstant.IMAGE_UID_NAME + ", jdbcType=VARCHAR}," +
            " #{" + StringConstant.IMAGE_INDEX + ", jdbcType=VARCHAR});")
    int insertImage(Map<String, Object> img);

    /**
     * 根据类型 分页查找文章列表
     *
     * @param offset
     * @param row
     * @param type
     * @return
     */
    @Select("SELECT a.`id`, a.`type`, a.`title`, a.`author_wxid`, a.`create_time`, w.`user_name` " +
            " FROM article a, wx_user w WHERE a.author_wxid = w.user_id AND type=#{type,jdbcType=INTEGER}" +
            " AND del=0 ORDER BY create_time DESC LIMIT #{offset,jdbcType=INTEGER},#{row,jdbcType=INTEGER}")
    @Results({
            @Result(column = "author_wxid", property = StringConstant.ARTICLE_AUTHOR_ID),
            @Result(column = "close", property = StringConstant.ARTICLE_CLOSE)
    })
    List<Map<String,Object>> queryArticleListByType(@Param("offset") int offset, @Param("row") int row, @Param("type") int type);

    /**
     * 分页查找全部文章列表
     *
     * @param offset
     * @param row
     * @return
     */
    @Select("SELECT `id`, `type`, `title`, `author_wxid`, " +
            " DATE_FORMAT(create_time,'%Y-%m-%d %H:%i:%s') create_time " +
            " FROM article WHERE del=0 ORDER BY create_time DESC LIMIT #{offset,jdbcType=INTEGER},#{row,jdbcType=INTEGER}")
    @Results({
            @Result(column = "title", property = StringConstant.ARTICLE_TITLE),
            @Result(column = "type", property = StringConstant.ARTICLE_TYPE),
            @Result(column = "content", property = StringConstant.ARTICLE_CONTENT),
            @Result(column = "advice", property = StringConstant.ARTICLE_ADVICE),
            @Result(column = "author_wxid", property = StringConstant.ARTICLE_AUTHOR_ID),
            @Result(column = "close", property = StringConstant.ARTICLE_CLOSE)
    })
    List<Map<String,Object>> queryAllArticleList(@Param("offset") int offset, @Param("row") int row);

    /**
     * 根据文章 id 查找图片地址, 按图片pic_index顺序查找
     *
     * @param articleId
     * @return
     */
    @Select("SELECT * FROM imgaddress WHERE article_id=#{articleId,jdbcType=INTEGER} ORDER BY pic_index")
    @Results({
            @Result(column = "pic_origin_name", property = StringConstant.IMAGE_ORI_NAME),
            @Result(column = "pic_uid_name", property = StringConstant.IMAGE_UID_NAME),
            @Result(column = "pic_index", property = StringConstant.IMAGE_INDEX),
            @Result(column = "article_id", property = StringConstant.IMAGE_ARTICLE_ID)
    })
    List<Map<String,String>> queryImgsByArticleId(int articleId);

    /**
     * 查出一种总数
     * @param type
     * @return
     */
    @Select("SELECT COUNT(1) from article WHERE type=#{type,jdbcType=INTEGER} ")
    int queryTotalArticleInType(int type);

    /**
     * 查出总数
     * @return
     */
    @Select("SELECT COUNT(1) from article ")
    int queryTotalArticle();

    /**
     * 查询文章详情
     *
     * @param id
     * @return
     */
    @Select("SELECT `id`, `type`, `title`, `content`, `advice`, `author_wxid`, `create_time`, `update_time`, `close`" +
            " FROM article WHERE id=#{id, jdbcType=INTEGER} AND del=0")
    @Results({
            @Result(column = "title", property = StringConstant.ARTICLE_TITLE),
            @Result(column = "type", property = StringConstant.ARTICLE_TYPE),
            @Result(column = "content", property = StringConstant.ARTICLE_CONTENT),
            @Result(column = "advice", property = StringConstant.ARTICLE_ADVICE),
            @Result(column = "author_wxid", property = StringConstant.ARTICLE_AUTHOR_ID),
            @Result(column = "close", property = StringConstant.ARTICLE_CLOSE)
    })
    Map<String,Object> queryArticleDetail(int id);

    /**
     * close article
     *
     * @param articleId
     * @return
     */
    @Update("UPDATE `article` SET `close`='1' WHERE (`id`=#{articleId, jdbcType=INTEGER});")
    int closeArticle(int articleId);

    /**
     * queryArticleByDepart
     *
     * @param departId
     * @param type
     * @param startTime
     * @param endTime
     * @param offset
     * @param pageSize
     * @return
     */
    @Select("SELECT a.id as id, a.type as type, a.title as title, a.author_wxid as author_wxid" +
            " FROM article a, depart_user du, depart d" +
            " WHERE a.author_wxid = du.user_id AND du.depart_id = d.depart_id" +
            " AND d.depart_id=#{departId,jdbcType=INTEGER} " +
            " AND a.type=#{type,jdbcType=INTEGER} " +
            " AND a.create_time >= #{startTime, jdbcType=TIMESTAMP} " +
            " AND a.create_time <= #{endTime, jdbcType=TIMESTAMP} " +
            " ORDER BY a.create_time DESC LIMIT #{offset,jdbcType=INTEGER},#{pageSize,jdbcType=INTEGER}")
    List<Map<String, Object>> queryArticleByDepart(@Param("departId") int departId,
                                                   @Param("type") int type,
                                                   @Param("startTime") LocalDateTime startTime,
                                                   @Param("endTime") LocalDateTime endTime,
                                                   @Param("offset") int offset,
                                                   @Param("pageSize") int pageSize);


    /**
     * queryArticleCountByDepart
     *
     * @param departId
     * @param type
     * @param startTime
     * @param endTime
     * @return
     */
    @Select("SELECT COUNT(1)" +
            " FROM article a, depart_user du, depart d" +
            " WHERE a.author_wxid = du.user_id AND du.depart_id = d.depart_id" +
            " AND d.depart_id=#{departId,jdbcType=INTEGER} " +
            " AND a.type=#{type,jdbcType=INTEGER} " +
            " AND a.create_time >= #{startTime, jdbcType=TIMESTAMP} " +
            " AND a.create_time <= #{endTime, jdbcType=TIMESTAMP}")
    int queryArticleCountByDepart(@Param("departId") int departId,
                                  @Param("type") int type,
                                  @Param("startTime") LocalDateTime startTime,
                                  @Param("endTime") LocalDateTime endTime);

    /**
     * queryArticleReadRecord
     *
     * @param articleId
     * @param wxId
     * @return
     */
    @Select("SELECT * FROM article_read WHERE article_id=#{articleId,jdbcType=INTEGER} AND reader_wxid=#{wxId, jdbcType=VARCHAR}")
    Map<String,Object> queryArticleReadRecord(@Param("articleId") int articleId, @Param("wxId") String wxId);

    /**
     * insertReadRecord
     *
     * @param articleId
     * @param wxId
     * @return
     */
    @Insert("INSERT INTO `article_read` (`article_id`, `reader_wxid`) VALUES (#{articleId,jdbcType=INTEGER}, #{wxId, jdbcType=VARCHAR});")
    int insertReadRecord(@Param("articleId") int articleId, @Param("wxId") String wxId);

    /**
     * queryReadCount
     *
     * @param articleId
     * @return
     */
    @Select("SELECT COUNT(1) FROM article_read WHERE article_id=#{articleId,jdbcType=INTEGER}")
    int queryReadCount(int articleId);

    /**
     * queryReadCount
     * @param articleId
     * @return
     */
    @Select("SELECT reader_wxid as wxid FROM article_read WHERE article_id=#{articleId,jdbcType=INTEGER}")
    List<String> queryReaders(int articleId);
}
