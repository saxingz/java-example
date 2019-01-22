package org.saxing.a0023_search.domain.entity;


import java.io.Serializable;
import java.util.List;

/**
 * pageresult
 * 
 * @param <T>
 *     
 *     @author saxing 2019/1/22 10:32
 */
public class PageResult<T> implements Serializable {
    private static final long serialVersionUID = -9040710142922205581L;
    private Integer page;
    private Integer pageSize;
    private Integer total;
    private List<T> list;

    public PageResult() {
    }

    public PageResult(Integer page, Integer pageSize, Integer total, List<T> list) {
        this.page = page;
        this.pageSize = pageSize;
        this.total = total;
        this.list = list;
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

    public Integer getTotal() {
        return this.total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getList() {
        return this.list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}