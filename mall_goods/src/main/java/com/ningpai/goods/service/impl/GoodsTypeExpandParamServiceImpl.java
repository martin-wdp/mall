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
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ningpai.goods.bean.GoodsTypeExpandParam;
import com.ningpai.goods.bean.GoodsTypeExpandParamValue;
import com.ningpai.goods.dao.CascDelMapper;
import com.ningpai.goods.dao.GoodsTypeExpandParamMapper;
import com.ningpai.goods.service.GoodsTypeExpandParamService;
import com.ningpai.goods.service.GoodsTypeExpandParamValueService;
import com.ningpai.goods.util.ValueUtil;
import com.ningpai.goods.vo.GoodsTypeExpandParamVo;
import com.ningpai.util.MyLogger;

/**
 * 类型扩展属性Service实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月21日 下午2:51:28
 * @version 1.0
 */
@Service("GoodsTypeExpandParamService")
public class GoodsTypeExpandParamServiceImpl implements GoodsTypeExpandParamService {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(GoodsTypeExpandParamServiceImpl.class);

    // 扩展属性DAO
    private GoodsTypeExpandParamMapper goodsTypeExpandParamMapper;

    // 商品类型扩展属性值Service
    private GoodsTypeExpandParamValueService goodsTypeExpandParamValueService;

    private CascDelMapper cascDelMapper;

    public CascDelMapper getCascDelMapper() {
        return cascDelMapper;
    }

    @Resource(name = "CascDelMapper")
    public void setCascDelMapper(CascDelMapper cascDelMapper) {
        this.cascDelMapper = cascDelMapper;
    }

    public GoodsTypeExpandParamMapper getGoodsTypeExpandParamMapper() {
        return goodsTypeExpandParamMapper;
    }

    @Resource(name = "GoodsTypeExpandParamMapper")
    public void setGoodsTypeExpandParamMapper(GoodsTypeExpandParamMapper goodsTypeExpandParamMapper) {
        this.goodsTypeExpandParamMapper = goodsTypeExpandParamMapper;
    }

    public GoodsTypeExpandParamValueService getGoodsTypeExpandParamValueService() {
        return goodsTypeExpandParamValueService;
    }

    @Resource(name = "GoodsTypeExpandParamValueService")
    public void setGoodsTypeExpandParamValueService(GoodsTypeExpandParamValueService goodsTypeExpandParamValueService) {
        this.goodsTypeExpandParamValueService = goodsTypeExpandParamValueService;
    }

    /**
     * 添加扩展属性
     *
     * @param goodsTypeExpandParam
     *            扩展属性 {@link com.ningpai.goods.bean.GoodsTypeExpandParam}
     * @param username
     *            操作的用户名
     * @return 插入的最新的ID {@link java.lang.Long}
     */
    @Transactional
    public Long saveExpandParam(GoodsTypeExpandParam goodsTypeExpandParam, String username) {
        goodsTypeExpandParam.setExpandparamCreateName(username);
        // 插入记录
        this.goodsTypeExpandParamMapper.insertSelective(goodsTypeExpandParam);
        // 打印日志
        LOGGER.info(ValueUtil.SAVEEXPANDPARAM2 + username);
        // 查询最后插入的id
        return this.goodsTypeExpandParamMapper.selectLastId();
    }

    /**
     * 删除扩展属性
     *
     * @param paramId
     *            主键ID {@link java.lang.Long}
     * @param username
     *            操作的用户名
     * @return 删除的行数 插入的行数 {@link java.lang.Integer}
     */
    @Transactional
    public int delExpandParam(Long paramId, String username) {
        // 定义一个HashMap集合
        Map<String, String> map = new HashMap<String, String>();
        try {
            // 把参数放进map集合
            map.put("delName", username);
            map.put("expandparamId", paramId.toString());
            // 根据主键删除
            return this.goodsTypeExpandParamMapper.deleteByPrimaryKey(map);
        } finally {
            // 打印日志
            LOGGER.info(ValueUtil.DELEXPANDPARAM + username);
            this.cascDelMapper.cascDel(username);
            map = null;
        }
    }

    /**
     * 更新扩展属性，操作的用户名
     *
     * @param goodsTypeExpandParam
     *            扩展属性 {@link com.ningpai.goods.bean.GoodsTypeExpandParam}
     * @param username
     *            操作的用户名
     * @return 插入的行数 {@link java.lang.Integer}
     */
    @Transactional
    public int updateExpandParam(GoodsTypeExpandParam goodsTypeExpandParam, String username) {
        goodsTypeExpandParam.setExpandparamModifiedName(username);
        // 打印日志
        LOGGER.info(ValueUtil.UPDATEEXPANDPARAM + username);
        // 更新扩展属性
        return this.goodsTypeExpandParamMapper.updateByPrimaryKeySelective(goodsTypeExpandParam);
    }

    /**
     * 根据类型ID查询所有的扩展属性
     *
     * @param typeId
     *            分类ID
     * @return 查询到的扩展属性的集合 {@link java.util.List}
     *         {@link com.ningpai.goods.bean.GoodsTypeExpandParam}
     */
    public List<GoodsTypeExpandParamVo> queryParamListByTypeId(Long typeId) {
        // 根据类型ID查询所有的扩展属性
        return this.goodsTypeExpandParamMapper.queryAllExpandParam(typeId);
    }

    /**
     * 批量进行扩展属性的操作
     *
     * @param username
     *            操作人名称呢过
     * @param typeId
     *            类型ID，{@link Long}
     * @param map
     *            所有的参数 {@link java.util.Map}
     * @param request
     * @return 受影响的 行数 {@link java.lang.Integer}
     */
    @Transactional
    public int batchUpdateExpandParam(String username, Long typeId, Map<String, String[]> map, HttpServletRequest request) {
        Integer count = 0;
        Long lastParamId = null;
        String[] expandparamId = map.get("expandparamId");
        String[] expandparamDelflag = map.get("expandparamDelflag");
        String[] expandnames = map.get("expandnames");
        String[] expandnicknames = map.get("expandnicknames");
        String[] expanparamIsshow = map.get("expandparamIsshow");
        String[] expandparamsort = map.get("expandparamsort");
        String[] expandvalues = map.get("expandvalues");
        // 创建一个集合用来保存所有的迭代变量
        List<GoodsTypeExpandParam> expandParamList = new ArrayList<GoodsTypeExpandParam>();
        // 迭代变量
        GoodsTypeExpandParam expandParam = null;
        try {
            if (null != expandparamId) {
                for (int i = 0; i < expandparamId.length; i++) {
                    expandParam = new GoodsTypeExpandParam();
                    /**
                     * 设置所有参数
                     *
                     * */
                    expandParam.setExpandparamId(Long.parseLong(expandparamId[i]));
                    expandParam.setExpandparamDelflag(expandparamDelflag[i]);
                    expandParam.setExpandparamName(expandnames[i]);
                    expandParam.setExpandparamNickname(expandnicknames[i]);
                    expandParam.setExpandparamSort(Integer.parseInt(expandparamsort[i]));
                    expandParam.setExpanparamIsshow(expanparamIsshow[i]);
                    expandParamList.add(expandParam);
                }
            }
            // 遍历获得的集合，进行对应的操作
            for (int i = 0; i < expandParamList.size(); i++) {
                expandParam = expandParamList.get(i);
                // 如果主键ID为-1，则说明是新添加的扩展属性，否则就是已经存在的,如果删除标记是1，执行删除，否则就是更新
                if (-1 == expandParam.getExpandparamId()) {
                    expandParam.setExpandparamId(null);
                    expandParam.setExpandparamDelflag("0");
                    expandParam.setTypeId(typeId);
                    // 插入新建的扩展属性并获取最新插入的主键
                    lastParamId = this.saveExpandParam(expandParam, username);
                    // 如果获取的主键大于0
                    if (null != lastParamId && lastParamId > 0) {
                        // 保存扩展属性值
                        saveExpandParamValueWhenInsertExpandParam(username, lastParamId, expandvalues, i);
                    }
                } else if ("1".equals(expandParam.getExpandparamDelflag())) {
                    count = count + delExpandParam(expandParam.getExpandparamId(), username);
                } else {
                    count = count + updateExpandParam(expandParam, username);
                    updateExpandParamValues(expandparamId[i], request);
                }
            }
            return count;
        } finally {
            LOGGER.info("" + username);
            // 置空所有的参数
            count = null;
            lastParamId = null;
            expandparamId = null;
            expandparamDelflag = null;
            expandnames = null;
            expandnames = null;
            expandnicknames = null;
            expanparamIsshow = null;
            expandparamsort = null;
            expandParamList = null;
            expandParam = null;
        }
    }

    /**
     * g更新扩展属性值
     * 
     * @param expandParamId
     *            扩展属性记录id
     * @param request
     */
    private void updateExpandParamValues(String expandParamId, HttpServletRequest request) {
        // 扩展属性值id
        String[] expandParamValueIds = request.getParameterValues("expandParamValueId" + expandParamId);
        // 新后台才会有，老后台直接跳过
        if (expandParamValueIds == null) {
            return;
        }
        // 扩展属性值删除标记
        String[] expandParamValueDelFlags = request.getParameterValues("expandParamValueDelFlag" + expandParamId);
        // 扩展属性值
        String[] expandvalues = request.getParameterValues("expandvalues" + expandParamId);
        // 扩展属性值排序
        String[] expandvaluesorts = request.getParameterValues("expandvaluesort" + expandParamId);
        // 用户名
        String username = (String) request.getSession().getAttribute(ValueUtil.NAME);
        // 更新商品类型扩展属性值
        goodsTypeExpandParamValueService.updateParamValues(new String[] { expandParamId }, expandParamValueIds, expandParamValueDelFlags, expandvalues, expandvaluesorts, username);
    }

    /**
     * 当保存新建的扩展属性的时候，保存扩展属性值
     * 
     * @param username
     *            操作人名称
     * @param lastParamId
     *            最新插入的扩展属性ID
     * @param expandvalues
     *            扩展属性值
     * @param i
     *            循环标记
     */
    @Transactional
    private void saveExpandParamValueWhenInsertExpandParam(String username, Long lastParamId, String[] expandvalues, int i) {
        // 如果扩展属性值不为空，保存商品扩展属性值
        if (null != expandvalues[i] && !"".equals(expandvalues[i])) {
            // 创建一个迭代变量
            GoodsTypeExpandParamValue paramValue = null;
            // 必须加入\\转义才可以正确分割
            String[] values = expandvalues[i].split("\\|");
            for (int k = 0; k < values.length; k++) {
                paramValue = new GoodsTypeExpandParamValue();
                paramValue.setExpandparamId(Long.parseLong(String.valueOf(lastParamId)));
                paramValue.setExpandparamValueDelflag(ValueUtil.DEFAULTDELFLAG);
                paramValue.setExpandparamValueName(values[k]);
                // 添加属性值
                this.goodsTypeExpandParamValueService.saveParamValue(paramValue, username);
            }
            // 置空参数
            values = null;
            // 置空参数
            paramValue = null;
            // 打印日志
            LOGGER.info(ValueUtil.SAVEEXPANDPARAMVALUEWHENINSERTEXPANDPARAM + username);
        }
    }

}
