/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.dao.impl;

import com.ningpai.goods.bean.FollowAndCityVo;
import com.ningpai.goods.bean.WareCity;
import com.ningpai.goods.dao.WareCityMapper;
import com.ningpai.manager.base.BasicSqlSupport;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 仓库关联地区DAO实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年4月28日 下午3:00:11
 * @version 1.0
 */
@Repository("WareCityMapper")
public class WareCityMapperImpl extends BasicSqlSupport implements
        WareCityMapper {

    /**
     * 根据仓库ID查询所有的关联记录
     *
     * @param wareId
     *            仓库ID
     * @return 查询到的集合
     */
    public List<WareCity> queryAllByWareId(Long wareId) {
        return this
                .selectList(
                        "com.ningpai.goods.dao.WareCityMapper.queryAllByWareId",
                        wareId);
    }

    /**
     * 插入记录
     *
     * @param record
     *            待插入的实体
     * @return 插入的行数
     */
    public int insertSelective(WareCity record) {
        return this.insert(
                "com.ningpai.goods.dao.WareCityMapper.insertSelective", record);
    }

    /**
     * 根据选择的城市ID和仓库ID删除未被选中的记录
     *
     * @param map
     *            封装参数的Map
     * @return 删除的行数
     */
    public int delNotInChecked(Map<String, Object> map) {
        return this.update(
                "com.ningpai.goods.dao.WareCityMapper.delNotInChecked", map);
    }

    /**
     * 根据仓库ID和城市ID查询是否已经关联过记录
     *
     * @param map
     *            封装参数的Map
     * @return 查询到的行数
     */
    public int queryByWareIdAndDistinctId(Map<String, Object> map) {
        return this
                .selectOne(
                        "com.ningpai.goods.dao.WareCityMapper.queryByWareIdAndDistinctId",
                        map);
    }

    /**
     * 根据仓库id删除地址
     * 
     * @param wareId
     *            仓库id
     * @return
     */
    @Override
    public int deleteCityByWareId(Long wareId) {
        return this.update(
                "com.ningpai.goods.dao.WareCityMapper.deleteCityByWareId",
                wareId);
    }

    /**
     * 判断该仓库下的商品是否被收藏
     * 
     * @param
     */
    @Override
    public List<FollowAndCityVo> selectFollow(Long wareId,
            BigDecimal productPrices, BigDecimal productVipPrices, Long goodsId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("wareId", wareId);
        map.put("productPrices", productPrices);
        map.put("productVipPrices", productVipPrices);
        map.put("goodsId", goodsId);
        return this.selectList(
                "com.ningpai.goods.dao.WareCityMapper.selectFollow", map);
    }

    /**
     * 查询不可用的地区id
     * 
     * @param map
     * @return
     */
    @Override
    public List<Long> selectCityId(Map<String, Object> map) {
        return this.selectList(
                "com.ningpai.goods.dao.WareCityMapper.selectCityId", map);
    }

}
