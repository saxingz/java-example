package org.saxing.qisi.dao.mapper;

import org.apache.commons.collections.MapUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.saxing.qisi.utils.StringConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * article test
 *
 * Created by saxing on 2018/5/6.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleMapperTest {

    @Autowired
    private ArticleMapper articleMapper;

    @Test
    public void insertArticle() throws Exception {
        Map<String, Object> article = new HashMap<>();
        article.put(StringConstant.WX_USER_ID, "LiuHan");
        article.put(StringConstant.ARTICLE_ADVICE, "ADVICE");
        article.put(StringConstant.ARTICLE_CONTENT, "CONTENT");
        article.put(StringConstant.ARTICLE_TITLE, "THIS IS TITLE");
        article.put(StringConstant.ARTICLE_TYPE, 0);
        int res = articleMapper.insertArticle(article);
        Assert.assertTrue(res == 1);
        Assert.assertTrue(MapUtils.getInteger(article, "id", 0) > 1);
    }

    @Test
    public void insertImage() throws Exception{
        Map<String, Object> images = new HashMap<>();
        images.put(StringConstant.IMAGE_INDEX, 0);
        images.put(StringConstant.IMAGE_ORI_NAME, "123.PNG");
        images.put(StringConstant.IMAGE_UID_NAME, "324324324.PNG");
        images.put(StringConstant.IMAGE_ARTICLE_ID, 4);
        int res = articleMapper.insertImage(images);
        Assert.assertTrue(res == 1);
    }

    @Test
    public void queryArticleList() throws Exception{
        List<Map<String, Object>> res = articleMapper.queryArticleListByType(0, 3, 0);
        Assert.assertNotNull(res);
    }

    @Test
    public void queryImgsByArticleId() throws Exception{
        List<Map<String, String >> res = articleMapper.queryImgsByArticleId(18);
        Assert.assertNotNull(res);
    }

    @Test
    public void queryTotalArticleInType() throws Exception{
        int num1 = articleMapper.queryTotalArticleInType(0);
        int num2 = articleMapper.queryTotalArticle();
        Assert.assertTrue(num1 + num2 > 1);
    }

    @Test
    public void queryArticleDetail() throws Exception{
        Map<String, Object> res = articleMapper.queryArticleDetail(27);
        Assert.assertNotNull(res);
    }

    @Test
    @Transactional
    public void closeArticle() throws Exception{
        int res = articleMapper.closeArticle(26);
        Assert.assertTrue(res == 1);
    }

    @Test
    public void queryArticleReadRecord() throws Exception{
        Map<String, Object> result =  articleMapper.queryArticleReadRecord(114, "LiuHan");
        System.out.println(result);
    }

    @Test
    public void insertReadRecord() throws Exception{
        int result =  articleMapper.insertReadRecord(115, "LiuHan");
        System.out.println(result);
    }

    @Test
    public void queryReadCount() throws Exception{
        int result = articleMapper.queryReadCount(1122);
        System.out.println(result);
    }

    @Test
    public void queryReadersTest() throws Exception{
        List<String> res = articleMapper.queryReaders(1188);
        System.out.println(res);
    }


}