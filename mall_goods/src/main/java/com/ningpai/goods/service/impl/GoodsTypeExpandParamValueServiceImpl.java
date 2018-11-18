/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ningpai.goods.bean.GoodsTypeExpandParamValue;
import com.ningpai.goods.dao.CascDelMapper;
import com.ningpai.goods.dao.GoodsTypeExpandParamValueMapper;
import com.ningpai.goods.service.GoodsTypeExpandParamValueService;
import com.ningpai.goods.util.ValueUtil;
import com.ningpai.util.MyLogger;

/**
 * 商品扩展属性值Service实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月21日 下午3:21:05
 * @version 1.0
 */
@Repository("GoodsTypeExpandParamValueService")
public class GoodsTypeExpandParamValueServiceImpl implements
        GoodsTypeExpandParamValueService {
    // 商品规格值Dao
    private GoodsTypeExpandParamValueMapper goodsTypeExpandParamValueMapper;
    private CascDelMapper cascDelMapper;
    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(
            GoodsTypeExpandParamValueServiceImpl.class);

    public CascDelMapper getCascDelMapper() {
        return cascDelMapper;
    }

    @Resource(name = "CascDelMapper")
    public void setCascDelMapper(CascDelMapper cascDelMapper) {
        this.cascDelMapper = cascDelMapper;
    }

    public GoodsTypeExpandParamValueMapper getGoodsTypeExpandParamValueMapper() {
        return goodsTypeExpandParamValueMapper;
    }

    @Resource(name = "GoodsTypeExpandParamValueMapper")
    public void setGoodsTypeExpandParamValueMapper(
            GoodsTypeExpandParamValueMapper goodsTypeExpandParamValueMapper) {
        this.goodsTypeExpandParamValueMapper = goodsTypeExpandParamValueMapper;
    }

    /**
     * 添加属性值
     *
     * @param goodsTypeExpandParamValue
     *            属性值实体 {@link com.ningpai.goods.bean.GoodsTypeExpandParamValue}
     * @param username
     *            操作人名称
     * @return 添加的行数
     */
    @Transactional
    public int saveParamValue(
            GoodsTypeExpandParamValue goodsTypeExpandParamValue, String username) {
        goodsTypeExpandParamValue.setExpandparamValueCreateName(username);
        //打印日志
        LOGGER.info(ValueUtil.SAVEPARAMVALUE + username);
        //插入一条记录
        return this.goodsTypeExpandParamValueMapper
                .insertSelective(goodsTypeExpandParamValue);
    }

    /**
     * 删除属性值
     *
     * @param paramValueId
     *            属性值ID {@link java.lang.Long }
     * @param username
     *            操作人名称
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    @Transactional
    public int delParamValue(Long paramValueId, String username) {
        //定义一个HashMap集合
        Map<String, String> map = new HashMap<String, String>();
        try {
            //把参数放入map集合中
            map.put("delName", username);
            map.put("expandparamValueId", paramValueId.toString());
            //删除类型扩展属性值
            return this.goodsTypeExpandParamValueMapper.deleteByPrimaryKey(map);
        } finally {
            //打印日志
            LOGGER.info(ValueUtil.DELPARAMVALUE + username);
            this.cascDelMapper.cascDel(username);
            map = null;
        }
    }

    /**
     * 修改属性值
     *
     * @param goodsTypeExpandParamValue
     *            带修改的属性值实体
     *            {@link com.ningpai.goods.bean.GoodsTypeExpandParamValue}
     * @param username
     *            操作人名称
     * @return 受影响的行数
     */
    @Transactional
    public int updateParamValue(
            GoodsTypeExpandParamValue goodsTypeExpandParamValue, String username) {
        goodsTypeExpandParamValue.setExpandparamValueModifiedName(username);
        //打印日志
        LOGGER.info(ValueUtil.UPDATEPARAMVALUE + username);
        //更新
        return this.goodsTypeExpandParamValueMapper
                .updateByPrimaryKeySelective(goodsTypeExpandParamValue);
    }

    /**
     * 根据属性ID查询所有的属性值
     *
     * @param paramId
     *            属性ID {@link java.lang.Long}
     * @return 查询到的列表 {@link java.util.List}
     *         {@link com.ningpai.goods.bean.GoodsTypeExpandParamValue}
     */
    public List<GoodsTypeExpandParamValue> queryParamValueByParamId(Long paramId) {
        //根据属性ID查询所有的属性值
        return this.goodsTypeExpandParamValueMapper
                .queryParamValueByParamId(paramId);
    }

    /**
     * 更新商品类型扩展属性值
     *
     * @param paramIds
     *            扩展属性ID数组
     * @param expandParamValueId
     *            扩展属性值ID数组
     * @param expandParamValueDelFlag
     *            扩展属性值删除标记数组
     * @param expandParamValueName
     *            扩展属性值名称数组
     * @param expandParamValueSort
     *            扩展属性值排序数组
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    @Transactional
    public int updateParamValues(String[] paramIds,
            String[] expandParamValueId, String[] expandParamValueDelFlag,
            String[] expandParamValueName, String[] expandParamValueSort,
            String username) {
        //定义一个List集合
        List<GoodsTypeExpandParamValue> paramValueList = new ArrayList<GoodsTypeExpandParamValue>();
        GoodsTypeExpandParamValue paramValue = null;
        for (int i = 0; i < expandParamValueId.length; i++) {
            paramValue = new GoodsTypeExpandParamValue();
            // 如果paramIds的长度大于1，则说明之前已经存在记录，按照下标获取值即可，否则就取第一个值
            if (paramIds.length > 1) {
                paramValue.setExpandparamId(Long.parseLong(paramIds[i]));
            } else {
                paramValue.setExpandparamId(Long.parseLong(paramIds[0]));
            }
            /**
             * 设置所有参数
             * */
            paramValue.setExpandparamValueId(Long
                    .parseLong(expandParamValueId[i]));
            paramValue.setExpandparamValueName(expandParamValueName[i]);
            paramValue.setExpandparamValueSort(Integer
                    .parseInt(expandParamValueSort[i]));
            paramValue.setExpandparamValueDelflag(expandParamValueDelFlag[i]);
            paramValueList.add(paramValue);
        }
        for (int i = 0; i < paramValueList.size(); i++) {
            paramValue = paramValueList.get(i);
            // 如果对象的主键ID为-1，则是新增记录
            if (-1 == paramValue.getExpandparamValueId()) {
                paramValue.setExpandparamId(Long.parseLong(paramIds[0]));
                paramValue.setExpandparamValueId(null);
                paramValue.setExpandparamValueDelflag("0");
                paramValue.setExpandparamValueCreateName(username);
                //插入一条记录
                this.goodsTypeExpandParamValueMapper
                        .insertSelective(paramValue);
            } else if ("1".equals(paramValue.getExpandparamValueDelflag())) {
                this.delParamValue(paramValue.getExpandparamValueId(), username);
            } else {
                this.updateParamValue(paramValue, username);
            }
        }
        //打印日志
        LOGGER.info(ValueUtil.UPDATEPARAMVALUES + username);
        return 0;
    }

    /**
     * 根据扩展属性值ID查询商品扩展属性值对象
     *
     * @param expandParamId
     *            扩展属性值ID {@link java.lang.Long}
     * @return 查询到的扩展属性值对象
     *         {@link com.ningpai.goods.bean.GoodsTypeExpandParamValue}
     */
    public GoodsTypeExpandParamValue queryExpandParamValueByExpandParamId(
            Long expandParamId) {
        //根据主键ID查询
        return this.goodsTypeExpandParamValueMapper
                .selectByPrimaryKey(expandParamId);
    }

}
