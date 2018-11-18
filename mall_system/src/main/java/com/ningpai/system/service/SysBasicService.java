/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.system.service;

import com.ningpai.system.bean.SysBasic;

/**
 * SERVICE-后台Logo设置
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年9月24日下午8:40:27
 */
public interface SysBasicService {
    /**
     * 查看当前，没有创建一个
     * 
     * @return
     */
    SysBasic getSysBasic();

    /**
     * 修改
     * 
     * @param basic
     * @return
     */
    int updateSysBasic(SysBasic basic);

}
