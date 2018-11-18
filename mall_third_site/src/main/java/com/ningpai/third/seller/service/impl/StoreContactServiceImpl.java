/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.third.seller.service.impl;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import com.ningpai.third.seller.bean.StoreContact;
import com.ningpai.third.seller.mapper.StoreContactMapper;
import com.ningpai.third.seller.service.StroreContactService;

/**
 * @ com.ningpai.third.seller.service.StroreContactService
 * 
 * @author Zhouh
 * @since 2014.5.20
 * @version 0.0.1
 * 
 */
@Service("StroreContactService")
public class StoreContactServiceImpl implements StroreContactService {
    /**
     * 店铺联系人Mapper
     */
    private StoreContactMapper storeContactMapper;

    /**
     * 查询店铺的联系人信息
     * @param storeId
     *            店铺编号 {@link Long}
     * @return
     */
    public List<StoreContact> selectByStoreId(Long storeId) {
        return storeContactMapper.selectByStoreId(storeId);
    }

    /**
     * 修改店铺联系人信息
     * @param storeContact
     * @return
     */
    public int updateByPrimaryKeySelective(StoreContact storeContact) {
        return storeContactMapper.updateByPrimaryKeySelective(storeContact);
    }

    /**
     * 添加店铺联系人信息
     * @param request
     * @param storeContact
     * @return
     */
    public int insertStoreSelective(HttpServletRequest request, StoreContact storeContact) {
        storeContact.setStoreId((Long) request.getSession().getAttribute("thirdId"));
        return storeContactMapper.insertStoreSelective(storeContact);
    }

    public StoreContactMapper getStoreContactMapper() {
        return storeContactMapper;
    }

    @Resource(name = "storeContactMapper")
    public void setStoreContactMapper(StoreContactMapper storeContactMapper) {
        this.storeContactMapper = storeContactMapper;
    }

}
