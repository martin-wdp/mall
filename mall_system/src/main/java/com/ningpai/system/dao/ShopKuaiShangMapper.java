package com.ningpai.system.dao;

import com.ningpai.system.bean.ShopKuaiShang;

/**
 * 快商通DAO
 */
public interface ShopKuaiShangMapper {

    /**
     * 添加一条区县记录 待添加的实体
     * 
     * @return 插入的行数
     */
    int insertKuaiShang(ShopKuaiShang shopKuaiShang);

    /**
     * 查询初始化的快商通
     * 
     * @return 查询到的实体
     */
    ShopKuaiShang selectInitone();

    /**
     * 申请快商通
     * 
     * @return
     */
    int updateKuaiShangByPrimaryKey(ShopKuaiShang shopKuaiShang);
}
