package com.hn.ums.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: chenzikang
 * @Date: 2021-07-29
 * @Version: 1.0
 */
public class BeanPage<T> implements Serializable {
    //对象集合列表
    private List<T> list;
    // 当前页码
    private Integer curPage;
    // 总页码数
    private Integer totalPage;
    // 每页的记录数
    private Integer pageNum;
    // 总的记录数
    private Integer totalCount;

    public BeanPage() {
    }

    public BeanPage(List<T> list, Integer curPage, Integer totalPage, Integer pageNum, Integer totalCount) {
        this.list = list;
        this.curPage = curPage;
        this.totalPage = totalPage;
        this.pageNum = pageNum;
        this.totalCount = totalCount;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Integer getCurPage() {
        return curPage;
    }

    public void setCurPage(Integer curPage) {
        this.curPage = curPage;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return "BeanPage{" +
                "list=" + list +
                ", curPage=" + curPage +
                ", totalPage=" + totalPage +
                ", pageNum=" + pageNum +
                ", totalCount=" + totalCount +
                '}';
    }
}
