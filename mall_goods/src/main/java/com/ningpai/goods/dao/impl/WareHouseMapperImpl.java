/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.dao.impl;

import com.ningpai.goods.bean.WareCity;
import com.ningpai.goods.bean.WareHouse;
import com.ningpai.goods.dao.WareHouseMapper;
import com.ningpai.goods.vo.WareHouseVo;
import com.ningpai.manager.base.BasicSqlSupport;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 仓库DAO实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年4月23日 上午10:17:55
 * @version 1.0
 */
@Repository("WareHouseMapper")
public class WareHouseMapperImpl extends BasicSqlSupport implements
        WareHouseMapper {

    /**
     * 查询库存信息
     *
     * */
    @Override
    public List<WareHouse> findWares() {
        return selectList("com.ningpai.goods.dao.WareHouseMapper.findWares");
    }

    /**
     * 删除仓库信息
     *
     * @param map
     *            参数Map
     * @return 删除的行数
     */
    public int deleteByPrimaryKey(Map<String, Object> map) {
        return this
                .update("com.ningpai.goods.dao.WareHouseMapper.deleteByPrimaryKey",
                        map);
    }

    /**
     * 新建仓库信息
     *
     * @param record
     *            待插入的实体
     * @return 插入的ID
     */
    public Long insertSelective(WareHouse record) {
        this.insert("com.ningpai.goods.dao.WareHouseMapper.insertSelective",
                record);
        return this
                .selectOne("com.ningpai.goods.dao.WareHouseMapper.selectLastId");
    }

    /**
     * 根据主键查询仓库信息
     *
     * @param wareId
     *            仓库ID
     * @return 查询到的实体
     */
    public WareHouseVo selectByPrimaryKey(Long wareId) {
        return this.selectOne(
                "com.ningpai.goods.dao.WareHouseMapper.selectByPrimaryKey",
                wareId);
    }

    /**
     * 更新仓库信息
     *
     * @param record
     *            待更新的实体
     * @return 更新的行数
     */
    public int updateByPrimaryKeySelective(WareHouse record) {
        return this
                .update("com.ningpai.goods.dao.WareHouseMapper.updateByPrimaryKeySelective",
                        record);
    }

    /**
     * 查询所有的仓库信息
     *
     * @return 查询到的仓库集合
     */
    public List<WareHouse> queryAllWareHouse() {
        return this
                .selectList("com.ningpai.goods.dao.WareHouseMapper.queryAllWareHouse");
    }

    /**
     * 根据参数查询仓库的数量
     *
     * @param map
     * @return
     */
    public int queryCountByParams(Map<String, Object> map) {
        return this
                .selectOne(
                        "com.ningpai.goods.dao.WareHouseMapper.queryCountByParams",
                        map);
    }

    /**
     * 根据分页参数查询仓库的集合
     *
     * @param map
     * @return
     */
    public List<Object> queryAllWareHouseByPageBean(Map<String, Object> map) {
        return this
                .selectList(
                        "com.ningpai.goods.dao.WareHouseMapper.queryAllWareHouseByPageBean",
                        map);
    }

    /**
     * 根据仓库名称查询数量
     *
     * @param wareName
     *            仓库名称
     * @return 查询到的行货
     */
    public List<WareHouse> queryWareCountByWareName(String wareName) {
        return this
                .selectList(
                        "com.ningpai.goods.dao.WareHouseMapper.queryWareCountByWareName",
                        wareName);
    }

    /**
     * 批量删除仓库信息
     *
     * @param map
     *            参数Map
     * @return 删除的行数
     */
    public int batchDelWare(Map<String, Object> map) {
        return this.update(
                "com.ningpai.goods.dao.WareHouseMapper.batchDelWare", map);
    }

    /**
     * 判断标识是否已经存在
     * 
     * @param identifyId
     *            标识编号
     * @return 查询到结果数量
     */
    @Override
    public int identifyIsExist(String identifyId) {
        return this.selectOne(
                "com.ningpai.goods.dao.WareHouseMapper.identifyIsExist",
                identifyId);
    }

    /**
     * 查询已经存在的仓库的所在城市ID组
     * 
     * @return List
     */
    @Override
    public List<WareCity> getAllWareHouseDistrict() {
        return this
                .selectList("com.ningpai.goods.dao.WareCityMapper.getAllWareHouseDistrict");
    }

    /**
     * 根据当前地区查询所在库存
     * 
     * @param distinctId
     * @return Long
     */
    @Override
    public Long selectWareIdByDistinctId(Long distinctId) {
        return this
                .selectOne(
                        "com.ningpai.goods.dao.WareCityMapper.selectWareIdByDistinctId",
                        distinctId);
    }

}
