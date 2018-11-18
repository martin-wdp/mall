package com.ningpai.goods.dao;

import com.ningpai.goods.bean.GoodsBulk;

/**
 * 起批设置
 * 
 * @author NINGPAI-qiaoy
 * @since 2015年3月25日 14:29
 * @version 1.0
 */
public interface GoodsBulkMapper {

    /**
     * 插入起批设置
     * 
     * @param goodsBulk
     * @return
     */
    int insertGoodsBulk(GoodsBulk goodsBulk);
}
