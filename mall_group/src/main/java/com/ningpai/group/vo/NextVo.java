/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.group.vo;

import com.ningpai.group.bean.GroupImg;
import com.ningpai.util.PageBean;

/**
 * @version 2014年6月27日 下午4:07:25
 * @author qiyuanyuan
 */

public class NextVo {

    /**
     * prev
     */
    private Long prev;
    /**
     * nowId
     */
    private Long nowId;
    /**
     * next
     */
    private Long next;
    /**
     * 分页实体类
     */
    private PageBean pb;
    /**
     * 小组图片实体
     */
    private GroupImg img;

    public Long getNowId() {
        return nowId;
    }

    public void setNowId(Long nowId) {
        this.nowId = nowId;
    }

    public Long getPrev() {
        return prev;
    }

    public void setPrev(Long prev) {
        this.prev = prev;
    }

    public Long getNext() {
        return next;
    }

    public void setNext(Long next) {
        this.next = next;
    }

    public GroupImg getImg() {
        return img;
    }

    public void setImg(GroupImg img) {
        this.img = img;
    }

    public PageBean getPb() {
        return pb;
    }

    public void setPb(PageBean pb) {
        this.pb = pb;
    }

}
