/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.group.bean;

/**
 * @version 2014�?�?0�?下午7:44:54
 * @author qiyuanyuan
 */

public class GroupBean {
    /**
     * 是否显示
     */
    private String showflag;
    /**
     * 内容
     */
    private String condition;
    /**
     * 条件
     */
    private String convalue;

    public String getShowflag() {
        return showflag;
    }

    public void setShowflag(String showflag) {
        this.showflag = showflag;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getConvalue() {
        return convalue;
    }

    public void setConvalue(String convalue) {
        this.convalue = convalue;
    }

}
