package com.lkl.util;

/**
 * @author 刘凯丽
 * @createTime 2020/10/30 13:04
 * @projectName first
 * @className Page.java
 * @description TODO
 */

import java.util.List;

/**
 * Description:  <br />
 * Project: itclass <br />
 * ClassName: Page <br />
 * Copyright: Copyright (c) 2011 onest<br />
 *
 * @author wangwei
 * @version 1.0 2013-4-18涓婂崍9:19:53
 */
public class Page<T> {
    private List<T> list;
    private int currentPageNum;//当前页
    private int pageSize;//页内记录数
    private int prePageNum;//上一页
    private int nextPageNum;//下一页
    private int totalPageNum;//总页数
    private int totalCount;//总记录数

    public Page() {
    }

    public Page(int pageNum, int pageSize) {
        this.currentPageNum = pageNum;
        this.pageSize = pageSize;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public int getCurrentPageNum() {
        return currentPageNum;
    }

    public void setCurrentPageNum(int currentPageNum) {
        this.currentPageNum = currentPageNum;
    }

    public int getTotalPageNum() {
        return totalPageNum;
    }

    public void setTotalPageNum(int totalPageNum) {
        totalPageNum = totalPageNum;
    }

    public int getPrePageNum() {
        return prePageNum;
    }

    public void setPrePageNum(int prePageNum) {
        this.prePageNum = prePageNum;
    }

    public int getNextPageNum() {
        return nextPageNum;
    }

    public void setNextPageNum(int nextPageNum) {
        this.nextPageNum = nextPageNum;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        if (totalCount % pageSize == 0)
            totalPageNum = totalCount / pageSize;
        else
            totalPageNum = totalCount / pageSize + 1;

        if (currentPageNum > 1)
            prePageNum = currentPageNum - 1;
        else
            prePageNum = 1;

        if (currentPageNum < totalPageNum)
            nextPageNum = currentPageNum + 1;
        else
            nextPageNum = totalPageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

}
