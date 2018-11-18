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

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ningpai.goods.bean.GoodsTypeParam;
import com.ningpai.goods.dao.CascDelMapper;
import com.ningpai.goods.dao.GoodsTypeParamMapper;
import com.ningpai.goods.service.GoodsTypeParamService;
import com.ningpai.goods.util.ValueUtil;
import com.ningpai.util.MyLogger;

/**
 * 商品类型详细参数Service实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月23日 上午10:03:02
 * @version
 */
@Service("GoodsTypeParamService")
public class GoodsTypeParamServiceImpl implements GoodsTypeParamService {
    // 商品类型详细参数 Dao
    private GoodsTypeParamMapper goodsTypeParamMapper;
    private CascDelMapper cascDelMapper;
    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(
            GoodsTypeParamServiceImpl.class);

    public CascDelMapper getCascDelMapper() {
        return cascDelMapper;
    }

    @Resource(name = "CascDelMapper")
    public void setCascDelMapper(CascDelMapper cascDelMapper) {
        this.cascDelMapper = cascDelMapper;
    }

    public GoodsTypeParamMapper getGoodsTypeParamMapper() {
        return goodsTypeParamMapper;
    }

    @Resource(name = "GoodsTypeParamMapper")
    public void setGoodsTypeParamMapper(
            GoodsTypeParamMapper goodsTypeParamMapper) {
        this.goodsTypeParamMapper = goodsTypeParamMapper;
    }

    /**
     * 添加商品详细参数
     *
     * @param goodsTypeParam
     *            详细参数实体 {@link com.ningpai.goods.bean.GoodsTypeParam}
     * @param username
     *            操作人名称
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    @Transactional
    public int saveTypeParam(GoodsTypeParam goodsTypeParam, String username) {
        goodsTypeParam.setParamCreateName(username);
        //打印日志
        LOGGER.info(ValueUtil.SAVETYPEPARAM + username);
        //执行方法，返回结果
        return this.goodsTypeParamMapper.insertSelective(goodsTypeParam);
    }

    /**
     * 根据主键ID删除详细参数
     *
     * @param paramId
     *            详细参数的主键ID {@link java.lang.Long}
     * @param username
     *            操作人名称
     * @return 影响的行数 {@link java.lang.Integer}
     */
    @Transactional
    public int delTypeParam(Long paramId, String username) {
        //定义一个HashMap集合
        Map<String, String> map = new HashMap<String, String>();
        try {
            // 封装参数
            map.put("delName", username);
            map.put("paramId", paramId.toString());
            //执行删除方法
            return this.goodsTypeParamMapper.deleteByPrimaryKey(map);
        } finally {
            //打印日志
            LOGGER.info(ValueUtil.DELTYPEPARAM + username);
            this.cascDelMapper.cascDel(username);
            // 参数置空
            map = null;
        }
    }

    /**
     * 更新商品详细参数
     *
     * @param goodsTypeParam
     *            详细参数实体 {@link com.ningpai.goods.bean.GoodsTypeParam}
     * @param username
     *            操作人名称呢过
     * @return 影响的行数 {@link java.lang.Integer}
     */
    @Transactional
    public int update(GoodsTypeParam goodsTypeParam, String username) {
        // 设置修改人名称
        goodsTypeParam.setParamModifiedName(username);
        //打印日志
        LOGGER.info(ValueUtil.UPDATETYPEPARAM + username);
        //执行更新操作
        return this.goodsTypeParamMapper
                .updateByPrimaryKeySelective(goodsTypeParam);
    }

    /**
     * 根据主键ID查询商品详细参数
     *
     * @param paramId
     *            详细参数的主键ID {@link java.lang.Long}
     * @return 详细参数实体 {@link com.ningpai.goods.bean.GoodsTypeParam}
     */
    public GoodsTypeParam queryByPrimaryKey(Long paramId) {
        // 根据主键ID查询
        return this.goodsTypeParamMapper.selectByPrimaryKey(paramId);
    }

    /**
     * 根据类型ID查询详细参数的列表
     *
     * @param typeId
     *            类型ID {@link java.lang.Long}
     * @return 查询到的详细参数的列表 {@link java.util.List}
     *         {@link com.ningpai.goods.bean.GoodsTypeParam}
     */
    public List<GoodsTypeParam> queryParamListByTypeId(Long typeId) {
        //根据类型ID查询详细参数列表
        return this.goodsTypeParamMapper.queryTypeParamByTypeId(typeId);
    }

    /**
     * 批量进行操作
     *
     * @param typeId
     *            类型ID
     * @param username
     *            操作人名称
     * @param typeParamId
     *            详细参数ID
     * @param paramDelflag
     *            删除标记
     * @param paramname
     *            详细参数名称
     * @param paramnickname
     *            详细参数别名
     * @return 受影响的行数
     */
    @Transactional
    public int batchUpateParam(Long typeId, String username,
            String[] typeParamId, String[] paramDelflag, String[] paramname,
            String[] paramnickname) {
        // 保存迭代变量的集合
        List<GoodsTypeParam> paramList = new ArrayList<GoodsTypeParam>();
        GoodsTypeParam param = null;
        if (null != typeParamId) {
            for (int i = 0; i < typeParamId.length; i++) {
                param = new GoodsTypeParam();
                param.setParamId(Long.parseLong(typeParamId[i]));
                param.setParamDelflag(paramDelflag[i]);
                param.setParamName(paramname[i]);
                param.setParamNickname(paramnickname[i]);
                paramList.add(param);
            }
        }
        for (int i = 0; i < paramList.size(); i++) {
            param = paramList.get(i);
            // 如果详细参数的ID等于-1，则说明是新增记录，否则就是更新或者删除
            if (-1 == param.getParamId()) {
                param.setParamId(null);
                param.setParamDelflag("0");
                param.setTypeId(typeId);
                saveTypeParam(param, username);
            } else if ("1".equals(param.getParamDelflag())) {
                delTypeParam(param.getParamId(), username);
            } else {
                update(param, username);
            }
        }
        //打印日志
        LOGGER.info(ValueUtil.BATCHUPATEPARAM + username);
        return 0;
    }

}
