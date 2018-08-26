package org.saxing.qisi.dao.mapper;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.saxing.qisi.utils.StringConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CommentMapperTest
 *
 * Created by saxing on 2018/5/8.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentMapperTest {

    @Autowired
    private CommentMapper commentMapper;

    @Test
    public void insertComment() throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put(StringConstant.COMMENT_AUTHOR_WX_ID, "LiuHan");
        params.put(StringConstant.COMMENT_CONTENT, "THIS IS COMMENT3");
        params.put(StringConstant.COMMENT_ARTICLE_ID, 27);
        int res = commentMapper.insertComment(params);
        Assert.assertTrue(res == 1);
    }

    @Test
    public void findCommentById() throws Exception{
        Map<String, Object> comment = commentMapper.findCommentById(2);
        Assert.assertNotNull(comment);
    }

    @Test
    public void updateComment() throws Exception{
        Map<String, Object> comment = commentMapper.findCommentById(2);
        comment.put(StringConstant.COMMENT_THUMB_NUM, 23);
        int res = commentMapper.updateComment(comment);
        Assert.assertTrue(res == 1);
    }

    @Test
    public void saveThumber() throws Exception{
        int res = commentMapper.saveThumber(20, "LiuHan");
        Assert.assertTrue(res == 1);
    }

    @Test
    public void findCommentsOBThumbNum() throws Exception{
        List<Map<String, Object>> comments = commentMapper.findCommentsOrderByThumbNum(27);
        Assert.assertNotNull(comments);
    }

    @Test
    public void findThumber(){
        List<String> thumbers = commentMapper.findThumber(20);
        Assert.assertNotNull(thumbers);
    }
}