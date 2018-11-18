/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.system.dao.impl;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.system.bean.ShopKuaiShang;
import com.ningpai.system.dao.ShopKuaiShangMapper;
import org.springframework.stereotype.Repository;

/**
 * 区县DAO实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年2月13日 上午10:49:43
 * @version 1.0
 */
@Repository("ShopKuaiShangMapper")
public class ShopKuaiShangMapperImpl extends BasicSqlSupport implements
        ShopKuaiShangMapper {
    /**
     * 添加一条区县记录 待添加的实体
     * 
     * @return 插入的行数
     */
    @Override
    public int insertKuaiShang(ShopKuaiShang shopKuaiShang) {
        return this.insert(
                "com.ningpai.system.dao.ShopKuaiShangMapper.insertKuaiShang",
                shopKuaiShang);
    }

    /**
     * 查询初始化的快商通
     * 
     * @return 查询到的实体
     */
    @Override
    public ShopKuaiShang selectInitone() {
        return this
                .selectOne("com.ningpai.system.dao.ShopKuaiShangMapper.selectInitone");
    }

    /**
     * 申请快商通
     * 
     * @return
     */
    @Override
    public int updateKuaiShangByPrimaryKey(ShopKuaiShang shopKuaiShang) {
        return this
                .update("com.ningpai.system.dao.ShopKuaiShangMapper.updateKuaiShangByPrimaryKey",
                        shopKuaiShang);
    }
}
