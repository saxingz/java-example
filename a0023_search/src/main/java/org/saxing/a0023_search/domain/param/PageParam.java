package org.saxing.a0023_search.domain.param;

import java.io.Serializable;

/**
 * page param
 *
 * @author saxing 2019/1/22 10:19
 */
public class PageParam implements Serializable {

    private static final long serialVersionUID = 4727035005934391376L;
    private Integer page;
    private Integer pageSize;
    private String sort;
    private String order;
    private Integer limit;
    private Integer offset;

    public PageParam() {
    }

    public PageParam(Integer page, Integer pageSize, String sort, String order) {
        this.page = page;
        this.pageSize = pageSize;
        this.sort = sort;
        this.order = order;
    }

    public Integer getPage() {
        return this.page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getSort() {
        return this.sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return this.order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Integer getLimit() {
        return this.limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return this.offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

}
