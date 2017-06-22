package com.demo.common;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenww3 on 2015/7/8.
 */
public class PageModel2<T> extends BaseObject {

    /**
     * 页面大小
     */
    private int pageSize;
    /**
     * 页码，从1开始
     */
    private int pageNum;
    /**
     * 总页数,不分页的时候值必须为0
     */
    private int totalPageNum;
    /**
     * 总数
     */
    private long totalCount;

    private List<T> datas;

    public PageModel2(){
        super();
    }

    public PageModel2(PageQuery pageQuery, List<T> datas) {
        this.pageSize = pageQuery.getPageSize();
        this.pageNum = pageQuery.getPageNum();
        this.totalCount = pageQuery.getTotalCount();
        this.totalPageNum = pageQuery.getTotalPageNum();
        this.datas =datas;
    }

    public PageModel2(PageModel pageModel) {
        this.pageSize = pageModel.getPageSize();
        this.pageNum = pageModel.getPageNum();
        this.totalCount = pageModel.getTotalCount();
        this.totalPageNum = pageModel.getTotalPageNum();
        this.datas = new ArrayList(pageModel.getDatas());
    }


    public long getTotalCount() {
        return this.totalCount;
    }

    public List<T> getDatas() {
        return this.datas;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public int getTotalPageNum() {
        return this.totalPageNum;
    }

    public int getPageNum() {
        return this.pageNum;
    }

    @Override
    public String toString() {
        return "PageModel2{" +
                "pageSize=" + pageSize +
                ", pageNum=" + pageNum +
                ", totalPageNum=" + totalPageNum +
                ", totalCount=" + totalCount +
                ", datas=" + datas +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PageModel2 that = (PageModel2) o;

        if (pageNum != that.pageNum) return false;
        if (pageSize != that.pageSize) return false;
        if (totalCount != that.totalCount) return false;
        if (totalPageNum != that.totalPageNum) return false;
        if (datas != null ? !datas.equals(that.datas) : that.datas != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pageSize;
        result = 31 * result + pageNum;
        result = 31 * result + totalPageNum;
        result = 31 * result + (int) (totalCount ^ (totalCount >>> 32));
        result = 31 * result + (datas != null ? datas.hashCode() : 0);
        return result;
    }
}
