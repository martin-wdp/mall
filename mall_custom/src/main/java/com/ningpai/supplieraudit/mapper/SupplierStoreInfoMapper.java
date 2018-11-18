/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.supplieraudit.mapper;

import com.ningpai.supplieraudit.bean.StoreInfo;
import java.util.List;
import java.util.Map;

/**
 * 供应商mapper
 * @author NINGPAI-qiaoy
 * @since 2015年3月23日 16:47
 * @version 0.0.1
 */
public interface SupplierStoreInfoMapper {
    /**
     * 查询需要审核供应商的数量
     * @param storeInfo
     * 查询条件 {@link com.ningpai.thirdaudit.bean.StoreInfo}
     * @return 总数量 {@link Long}
     */
    Long selectSupplierAuditCount(StoreInfo storeInfo);

    /**
     * 查询需要审核供应商的列表
     *
     * @param paramMap
     *            查询条件 {@link java.util.Map}
     * @return 审核列表 {@link java.util.List}
     */
    List<Object> selectSupplierAuditList(Map<String, Object> paramMap);
}
