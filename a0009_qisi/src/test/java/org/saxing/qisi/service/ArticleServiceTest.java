package org.saxing.qisi.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * ArticleServiceTest
 *
 * Created by saxing on 2018/5/9.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleServiceTest {

    @Autowired
    private ArticleService articleService;

    @Test
    public void showArticleListByDepartAndMonth() throws Exception {
        String result =  articleService.showArticleListByDepartAndMonth(1, 10, 0, 5, "2018-05");
        System.out.println(result);
        Assert.assertNotNull(result);
    }

}