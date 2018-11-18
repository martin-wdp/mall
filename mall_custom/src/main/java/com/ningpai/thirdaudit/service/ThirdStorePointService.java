/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.thirdaudit.service;

import com.ningpai.customer.bean.PunishRecord;

/**
 *商家信息定位服务层接口
 *
 * */
public interface ThirdStorePointService {

    /**
     * 添加扣积分记录
     */
    int addpunishPoint(Long thirdId, String pointDetail, int point);

    /**
     * 扣积分的方法（分三步）
     * 
     * @param punishRecord
     * @param thirdId
     * @param pointDetail
     * @param point
     * @return
     */
    int reduceStorePoint(PunishRecord punishRecord, Long thirdId,
            String pointDetail, int point);

    /**
     * 扣违约金
     * 
     * @param punishRecord
     * @return
     */
    int reduceStoreMoney(PunishRecord punishRecord);

}
