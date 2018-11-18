/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.m.goods.vo;
/**
 * 列表页显示的货品信息VO
 *
 * @author NINGPAI-YuanKangKang
 * @since 2014年2月19日 下午6:23:49
 * @version
 */
public class GoodsListScreenVo {
    /**
     *参数标题
      */
    private String title;
    /**
     *参数值
      */
    private String text;
    /**
     *参数ID
      */
    private String value;
    /**
     *上级ID
      */
    private String parentId;
    /**
     *类型标记
      */
    private String type;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
