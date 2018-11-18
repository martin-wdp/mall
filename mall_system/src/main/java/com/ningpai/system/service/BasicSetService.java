/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.service;

import com.ningpai.system.bean.BasicSet;

/**
 * 站点设置服务层接口
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月16日 下午5:00:39
 * @version 1.0
 */
public interface BasicSetService {

    /**
     * 查询站点信息
     * 
     * @return BasicSet
     */
    BasicSet findBasicSet();

    /**
     * 修改配置
     * @param basicSet
     * @return int
     */ 
    int updateBasicSet(BasicSet basicSet);


    /**
     * 查看店铺街的开启状态
     * @return
     */
    String getStoreStatus();

}
