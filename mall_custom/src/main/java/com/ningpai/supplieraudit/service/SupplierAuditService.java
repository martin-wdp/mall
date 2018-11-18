/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.supplieraudit.service;

import com.ningpai.supplieraudit.bean.StoreInfo;
import com.ningpai.util.PageBean;

/**
 * 商家审核接口
 * @author NINGPAI-qiaoy
 * @since 2015年3月23日 16:18
 * @version 0.0.1
 */
public interface SupplierAuditService {
    /**
     * 查询供应商审核列表
     * @param storeInfo
     *            商家信息 {@link com.ningpai.thirdaudit.bean.StoreInfo}
     * @param pageBean
     *            分页辅助类 {@link com.ningpai.util.PageBean}
     * @return 分页辅助类 {@link com.ningpai.util.PageBean}
     */
    PageBean selectSupplierAuditList(StoreInfo storeInfo, PageBean pageBean);
}
