/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.thirdaudit.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ningpai.customer.bean.PunishRecord;
import com.ningpai.customer.service.PunishRecordService;
import com.ningpai.thirdaudit.bean.ThirdStorePoint;
import com.ningpai.thirdaudit.mapper.ThirdStorePointMapper;
import com.ningpai.thirdaudit.service.StoreInfoService;
import com.ningpai.thirdaudit.service.ThirdStorePointService;
/**
 *商家信息定位服务层接口实现类
 *
 * */
@Service("ThirdStorePointService")
public class ThirdStorePointServiceImpl implements ThirdStorePointService {
    @Resource(name = "ThirdStorePointMapper")
    private ThirdStorePointMapper thirdStorePointMapper;
    @Resource(name = "PunishRecordService")
    private PunishRecordService punishRecordService;
    @Resource(name = "StoreService")
    private StoreInfoService storeInfoService;
    /**
     * 添加扣积分记录
     */
    @Override
    public int addpunishPoint(Long thirdId, String pointDetail, int point) {
        ThirdStorePoint thirdStorePoint = new ThirdStorePoint();
        thirdStorePoint.setThirdId(thirdId);
        thirdStorePoint.setPointDetail(pointDetail);
        thirdStorePoint.setPoint(point);
        thirdStorePoint.setCreateTime(new Date());
        thirdStorePoint.setModifiedTime(new Date());
        thirdStorePoint.setPointType("1");
        return thirdStorePointMapper.insertSelective(thirdStorePoint);
    }

    /**
     * 扣积分的方法（分三步）
     *
     * @param punishRecord
     * @param thirdId
     * @param pointDetail
     * @param point
     * @return
     */
    @Transactional
    @Override
    public int reduceStorePoint(PunishRecord punishRecord, Long thirdId,
            String pointDetail, int point) {
        // 添加商家积分记录
        int n = this.addpunishPoint(thirdId, pointDetail, point);
        // 添加处罚记录
        punishRecordService.addPunishRecord(punishRecord);
        // 减积分动作
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("reducePoint", punishRecord.getReducePoint());
        paraMap.put("storeId", punishRecord.getThirdId());
        storeInfoService.upStorePointByThirdId(paraMap);

        return n;
    }
    /**
     * 扣违约金
     *
     * @param punishRecord
     * @return
     */
    @Transactional
    @Override
    public int reduceStoreMoney(PunishRecord punishRecord) {
        // 添加处罚记录
        int n = punishRecordService.addPunishRecord(punishRecord);
        // 扣违约金动作
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("reduceMoney", punishRecord.getReduceMoney());
        paramMap.put("storeId", punishRecord.getThirdId());
        storeInfoService.upStoreBalanceByThirdId(paramMap);
        return n;
    }

}
