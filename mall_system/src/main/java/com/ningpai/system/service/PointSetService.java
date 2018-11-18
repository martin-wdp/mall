/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.service;

import com.ningpai.system.bean.PointSet;

/**
 * 积分设置服务层接口
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月16日 下午2:35:22
 * @version 1.0
 */
public interface PointSetService {

    /**
     * 查找积分设置信息
     * 
     * @return PointSet
     */
    PointSet findPointSet();

    /**
     * 修改积分设置
     * @param pointSet
     * @return
     */
    int updatePointSet(PointSet pointSet);

}
