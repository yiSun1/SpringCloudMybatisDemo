package com.demo.common;

/**
 * 分页的查询条件
 * Created by chenww3 on 2015/4/19.
 */
public class PageQuery extends BaseObject {

    /**
     * 页面大小
     */
    private int pageSize;
    /**
     * 页码，从1开始
     */
    private int pageNum;

    /**
     * 当设置为true的时候，如果pagesize设置为0（或RowBounds的limit=0），就不执行分页，返回全部结果
     */
    private Boolean pageSizeZero;

    /**
     * 起始行
     */
    private int startRow;
    /**
     * 末行
     */
    private int endRow;

    /**
     * 总页数,不分页的时候值必须为0
     */
    private int totalPageNum;
    /**
     * 总数
     */
    private long totalCount;


    public PageQuery() {
    }

    public PageQuery(Boolean pageSizeZero) {
        this.pageSizeZero = pageSizeZero;
    }

    public PageQuery(int pageNum, int pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        calculateStartAndEndRow();
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
        if (pageSize > 0) {
            this.totalPageNum = (int) (totalCount / pageSize + ((totalCount % pageSize == 0) ? 0 : 1));
        } else {
            this.totalPageNum = 0;
        }

        //分页合理化，针对不合理的页码自动处理
        if (pageNum < 1) {
            pageNum = 1;//恢复到第一页
        }
        calculateStartAndEndRow();
    }

    /**
     * 计算起止行号
     */
    private void calculateStartAndEndRow() {
        this.startRow = this.pageNum > 0 ? (this.pageNum - 1) * this.pageSize : 0;
        this.endRow = this.startRow + this.pageSize * (this.pageNum > 0 ? 1 : 0);
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
        calculateStartAndEndRow();
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public Boolean getPageSizeZero() {
        return pageSizeZero;
    }

    public int getStartRow() {
        return startRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public int getTotalPageNum() {
        return totalPageNum;
    }

    public long getTotalCount() {
        return totalCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PageQuery pageQuery = (PageQuery) o;

        if (endRow != pageQuery.endRow) return false;
        if (pageNum != pageQuery.pageNum) return false;
        if (pageSize != pageQuery.pageSize) return false;
        if (startRow != pageQuery.startRow) return false;
        if (totalCount != pageQuery.totalCount) return false;
        if (totalPageNum != pageQuery.totalPageNum) return false;
        if (pageSizeZero != null ? !pageSizeZero.equals(pageQuery.pageSizeZero) : pageQuery.pageSizeZero != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pageSize;
        result = 31 * result + pageNum;
        result = 31 * result + (pageSizeZero != null ? pageSizeZero.hashCode() : 0);
        result = 31 * result + startRow;
        result = 31 * result + endRow;
        result = 31 * result + totalPageNum;
        result = 31 * result + (int) (totalCount ^ (totalCount >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "PageQuery{" +
                "pageSize=" + pageSize +
                ", pageNum=" + pageNum +
                ", pageSizeZero=" + pageSizeZero +
                ", startRow=" + startRow +
                ", endRow=" + endRow +
                ", totalPageNum=" + totalPageNum +
                ", totalCount=" + totalCount +
                '}';
    }
}
