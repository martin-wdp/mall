/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.other.bean;

import java.util.List;

/**
 * 搜索辅助类
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年6月5日 上午11:00:38
 * @version 0.0.1
 */
public class CustBean {
    private String showFlag;
    private List<Object> attr;

    public String getShowFlag() {
        return showFlag;
    }

    public void setShowFlag(String showFlag) {
        this.showFlag = showFlag;
    }

    public List<Object> getAttr() {
        return attr;
    }

    public void setAttr(List<Object> attr) {
        this.attr = attr;
    }

}
