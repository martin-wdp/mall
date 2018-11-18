/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.supplieraudit.service.impl;

import com.ningpai.supplieraudit.bean.StoreInfo;
import com.ningpai.supplieraudit.mapper.SupplierStoreInfoMapper;
import com.ningpai.supplieraudit.service.SupplierAuditService;
import com.ningpai.util.PageBean;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 商家审核接口
 * @author NINGPAI-qiaoy
 * @since 2015年3月23日 16:18
 * @version 0.0.1
 */
@Service("SupplierAuditService")
public class SupplierAuditServiceImpl implements SupplierAuditService {
    private SupplierStoreInfoMapper supplierStoreInfoMapper;

    /**
     * 供应商商家审核
     * @param storeInfo
     *            商家信息 {@link com.ningpai.thirdaudit.bean.StoreInfo}
     * @param pageBean
     *            分页辅助类 {@link com.ningpai.util.PageBean}
     * @return
     */
    @Override
    public PageBean selectSupplierAuditList(StoreInfo storeInfo, PageBean pageBean) {
        Map<String, Object> paramMap = null;
        int no = 0;
        try {
            // 设置总行数
            pageBean.setRows(Integer.parseInt(supplierStoreInfoMapper.selectSupplierAuditCount(storeInfo).toString()));
            no = pageBean.getRows() % pageBean.getPageSize() == 0 ? pageBean.getRows() / pageBean.getPageSize() : (pageBean.getRows() / pageBean.getPageSize() + 1);
            no = no == 0 ? 1 : no;
            // 若页码超过最大页码 则显示最后一个
            if (pageBean.getPageNo() >= no) {
                pageBean.setPageNo(no);
                pageBean.setStartRowNum((no - 1) * pageBean.getPageSize());
            }
            // 设置查询条件
            paramMap = new HashMap<String, Object>();
            paramMap.put("storeInfo", storeInfo);
            paramMap.put("startRowNum", pageBean.getStartRowNum());
            paramMap.put("endRowNum", pageBean.getEndRowNum());
            paramMap.put("companyName", storeInfo.getCompanyName());
            // 查询会员信息
            pageBean.setList(supplierStoreInfoMapper.selectSupplierAuditList(paramMap));
        } finally {
            paramMap = null;
        }
        return pageBean;
    }

    public SupplierStoreInfoMapper getSupplierStoreInfoMapper() {
        return supplierStoreInfoMapper;
    }

    @Resource(name="supplierStoreInfoMapper")
    public void setSupplierStoreInfoMapper(SupplierStoreInfoMapper supplierStoreInfoMapper) {
        this.supplierStoreInfoMapper = supplierStoreInfoMapper;
    }
}
