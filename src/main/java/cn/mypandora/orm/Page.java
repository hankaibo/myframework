/**
 * Copyright © 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.mypandora.orm;

import java.util.List;

/**
 * 分页。
 * 因为页面使用jqGrid自带的分页功能，故不需要计算分页导航条。暂时没有找到好的在分页中的排序，故采用原生SQL的办法。
 * <p>User: kaibo
 * <p>Date: 2015/7/17
 * <p>Version: 1.0
 */
public class Page<T> {
    // 当前页
    protected int currentPage = 1;
    // 每页的记录条数
    protected int pageSize = 10;
    // 总页数
    protected long totalPage = -1;
    // 总记录数
    protected long total = -1;
    // 自动计算总记录数
    protected boolean autoCount = true;
    // 返回结果集
    private List<T> resultList;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        if (currentPage > 0) {
            this.currentPage = currentPage;
        }
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        if (pageSize > 0) {
            this.pageSize = pageSize;
        }
    }

    public int getTotalPage() {
        return (int) (total % pageSize == 0 ? total / pageSize : total / pageSize + 1);
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public boolean isAutoCount() {
        return autoCount;
    }

    public void setAutoCount(boolean autoCount) {
        this.autoCount = autoCount;
    }

    public List<T> getResultList() {
        return resultList;
    }

    public void setResultList(List<T> resultList) {
        this.resultList = resultList;
    }

    /**
     * 根据currentPage和pageSize计算当前页第一条记录在总结果集中的位置,序号从1开始.
     *
     * @return
     */
    public int getFirst() {
        return ((currentPage - 1) * pageSize);
    }

}
