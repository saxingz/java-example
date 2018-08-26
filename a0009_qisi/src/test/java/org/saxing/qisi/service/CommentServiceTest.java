package org.saxing.qisi.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * CommentServiceTest
 *
 * Created by saxing on 2018/5/8.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentServiceTest {

    @Autowired
    private CommentService commentService;

    @Test
    public void thumbComment() throws Exception {
        commentService.thumbComment(2, "LiuHan");
    }

}