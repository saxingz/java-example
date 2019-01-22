package org.saxing.a0023_search.domain.entity;

public class AnsDO {
    /**
     *  ,所属表字段为ans.id
     */
    private Long id;

    /**
     *  ,所属表字段为ans.title
     */
    private String title;

    /**
     *  ,所属表字段为ans.article
     */
    private String article;

    /**
     * 获取  字段:ans.id
     *
     * @return ans.id, 
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置  字段:ans.id
     *
     * @param id ans.id, 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取  字段:ans.title
     *
     * @return ans.title, 
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置  字段:ans.title
     *
     * @param title ans.title, 
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 获取  字段:ans.article
     *
     * @return ans.article, 
     */
    public String getArticle() {
        return article;
    }

    /**
     * 设置  字段:ans.article
     *
     * @param article ans.article, 
     */
    public void setArticle(String article) {
        this.article = article == null ? null : article.trim();
    }
}