/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.thirdaudit.mapper;

import java.util.List;
import java.util.Map;

import com.ningpai.thirdaudit.bean.DeduBrokeage;
import com.ningpai.thirdaudit.bean.DeduBrokeageVo;

/**
 * 扣点Mapper
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年7月25日 下午3:00:02
 * @version 0.0.1
 */
public interface DeduBrokeageMapper {

    /**
     * 添加商家平台扣点
     * 
     * @param record
     *            扣点类 {@link DeduBrokeage}
     * @return 0失败 1成功
     */
    int insertSelective(DeduBrokeage record);

    /**
     * 根据商家id查询商家扣点
     * 
     * @param storeId
     * @return
     */
    List<DeduBrokeageVo> selectBrokeageByStoreId(Long storeId);

    /**
     * 删除商家扣点
     * 
     * @param storeId
     */
    void deleteByStoreId(Long storeId);

    /**
     * 添加商家扣点
     * 
     * @param paramMap
     * @return
     */
    Integer insertByStoreId(Map<String, Object> paramMap);
}
