/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.service;

import com.ningpai.system.bean.Bar;
import com.ningpai.system.util.SelectBean;
import com.ningpai.util.PageBean;

/**
 * 前台导航服务层接口
 * @author NINGPAI-LiHaoZe 
 * @since 2013年12月19日 下午4:06:14 
 * @version 1.0
 */
public interface BarService {
    
    /**
     * 导航信息分页查询
     * @param pageBean
     * @return PageBean
     */
     PageBean findByPageBean(PageBean pageBean, SelectBean selectBean);
     
    /**
     * 添加导航信息
     * @param bar
     * @return int
     */
    int insertBar(Bar bar);
    
    /**
     * 删除导航信息
     * @param parameterValues
     * @return int
     */
    int deleteBar(String[] barId);
    
    /**
     * 查找单条导航信息
     * @param barId
     * @return Bar
     */
    Bar findByBarId(Long barId);
    
    /**
     * 修改导航信息
     * @param bar
     * @return int
     */
    int updateBar(Bar bar);
}
