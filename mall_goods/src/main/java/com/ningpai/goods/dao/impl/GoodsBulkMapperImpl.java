package com.ningpai.goods.dao.impl;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.goods.bean.GoodsBulk;
import com.ningpai.goods.dao.GoodsBulkMapper;
import org.springframework.stereotype.Repository;

/**
 * 起批设置
 * 
 * @author NINGPAI-qiaoy
 * @since 2015年3月25日 14:29
 * @version 1.0
 */

@Repository("GoodsBulkMapper")
public class GoodsBulkMapperImpl extends BasicSqlSupport implements
        GoodsBulkMapper {

    /**
     * 插入起批设置
     * 
     * @param goodsBulk
     * @return int
     */
    public int insertGoodsBulk(GoodsBulk goodsBulk) {
        return this.insert(
                "com.ningpai.third.goods.mapper.GoodsBulkMapper.insert",
                goodsBulk);
    }
}
