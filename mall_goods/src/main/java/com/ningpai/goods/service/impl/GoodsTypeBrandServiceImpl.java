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

import com.ningpai.goods.bean.GoodsBrand;
import com.ningpai.goods.bean.GoodsTypeBrand;
import com.ningpai.goods.dao.CascDelMapper;
import com.ningpai.goods.dao.GoodsTypeBrandMapper;
import com.ningpai.goods.service.GoodsBrandService;
import com.ningpai.goods.service.GoodsTypeBrandService;
import com.ningpai.goods.util.ValueUtil;
import com.ningpai.goods.vo.GoodsTypeBrandVo;
import com.ningpai.util.MyLogger;

/**
 * 商品关联品牌Service实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月21日 上午11:03:54
 * @version 1.0
 */
@Service("GoodsTypeBrandService")
public class GoodsTypeBrandServiceImpl implements GoodsTypeBrandService {
    // 类型关联品牌类的DAO
    private GoodsTypeBrandMapper goodsTypeBrandMapper;
    // 商品品牌的Service
    private GoodsBrandService goodsBrandService;
    private CascDelMapper cascDelMapper;
    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(
            GoodsTypeBrandServiceImpl.class);

    public CascDelMapper getCascDelMapper() {
        return cascDelMapper;
    }

    @Resource(name = "CascDelMapper")
    public void setCascDelMapper(CascDelMapper cascDelMapper) {
        this.cascDelMapper = cascDelMapper;
    }

    public GoodsBrandService getGoodsBrandService() {
        return goodsBrandService;
    }

    @Resource(name = "GoodsBrandService")
    public void setGoodsBrandService(GoodsBrandService goodsBrandService) {
        this.goodsBrandService = goodsBrandService;
    }

    public GoodsTypeBrandMapper getGoodsTypeBrandMapper() {
        return goodsTypeBrandMapper;
    }

    @Resource(name = "GoodsTypeBrandMapper")
    public void setGoodsTypeBrandMapper(
            GoodsTypeBrandMapper goodsTypeBrandMapper) {
        this.goodsTypeBrandMapper = goodsTypeBrandMapper;
    }

    /**
     * 插入类型关联品牌记录
     *
     * @param goodsTypeBrand
     *            待插入的实体 {@link com.ningpai.goods.bean.GoodsTypeBrands}
     * @param username
     *            操作的用户名
     * @return 插入的行数 {@link java.lang.Integer}
     */
    @Transactional
    public int insertTypeBrand(GoodsTypeBrand goodsTypeBrand, String username) {
        // 设置删除标记
        goodsTypeBrand.setDelflag("0");
        //打印日志
        LOGGER.info(ValueUtil.INSERTTYPEBRAND + username);
        //新增一条记录
        return this.goodsTypeBrandMapper.insertSelective(goodsTypeBrand);
    }

    /**
     * 根据主键删除
     *
     * @param typeBrandId
     *            类型品牌ID {@link java.lang.Long}
     * @param username
     *            操作的用户名称
     * @return 删除的行数 {@link java.lang.Integer}
     */
    @Transactional
    public int delTypeBrand(Long typeBrandId, String username) {
        //定义一个HashMap集合
        Map<String, String> map = new HashMap<String, String>();
        try {
            //把username放进map集合
            map.put("delName", username);
            map.put("typeBrandId", typeBrandId.toString());
            //根据主键ID删除
            return this.goodsTypeBrandMapper.deleteByPrimaryKey(map);
        } finally {
            //打印日志
            LOGGER.info(ValueUtil.DELTYPEBRAND + username);
            this.cascDelMapper.cascDel(username);
            // 参数置空
            map = null;
        }
    }

    /**
     * 修改记录
     *
     * @param goodsTypeBrand
     *            需要修改的实体 {@link com.ningpai.goods.bean.GoodsTypeBrand}
     * @param username
     *            操作的用户名称
     * @return 更新的行数
     */
    @Transactional
    public int updateTypeBrand(GoodsTypeBrand goodsTypeBrand, String username) {
        //打印日志
        LOGGER.info(ValueUtil.UPDATETYPEBRAND + username);
        //更新实体
        return this.goodsTypeBrandMapper
                .updateByPrimaryKeySelective(goodsTypeBrand);
    }

    /**
     * 根据主键查询类型关联品牌实体
     *
     * @param typeBrandId
     *            主键ID {@link java.lang.Long}
     * @return 查询到的实体 {@link com.ningpai.goods.bean.GoodsTypeBrand}
     */
    public GoodsTypeBrand queryTypeBrandById(Long typeBrandId) {
        //根据主键查询
        return this.goodsTypeBrandMapper.selectByPrimaryKey(typeBrandId);
    }

    /**
     * 根据类型ID查询关联的品牌的列表
     *
     * @param typeId
     *            类型ID {@link java.lang.Long}
     * @return 查询到的列表 {@link java.util.List}
     *         {@link com.ningpai.goods.bean.GoodsTypeBrand}
     */
    public List<GoodsTypeBrandVo> queryTypeBrandByTypeId(Long typeId) {
        //根据类型ID查询所有的类型关联品牌实体
        return this.goodsTypeBrandMapper.queryAllTypeBrand(typeId);
    }

    /**
     * 执行批量更新商品类型关联品牌的操作
     *
     * @param typeId
     *            商品类型的ID {@link java.lang.Long}
     * @param username
     *            操作人名称
     * @param brandIds
     *            商品类型关联品牌的所有ID
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    @Transactional
    public int batchUpdateTypeBrand(Long typeId, String username,
            String[] brandIds) {
        // 临时变量
        GoodsTypeBrand brand = null;
        //定义一个HashMap集合
        Map<String, Long> map = new HashMap<String, Long>();
        // 循环传递过来的品牌ID的数组，并查询对象，执行相应的操作
        if (null != brandIds && brandIds.length > 0) {
            for (int i = 0; i < brandIds.length; i++) {
                //把类型id放进map集合中
                map.put("typeId", typeId);
                //把品牌id放进map集合
                map.put("brandId", Long.parseLong(brandIds[i]));
                //根据类型ID和品牌ID查询实体
                brand = this.goodsTypeBrandMapper
                        .queryTypeBrandByTypeIdAndBrandId(map);
                if (null == brand) {
                    brand = new GoodsTypeBrand();
                    //设置品牌id
                    brand.setBrandId(Long.parseLong(brandIds[i]));
                    //设置品牌是否已删除
                    brand.setDelflag(ValueUtil.DEFAULTDELFLAG);
                    //设置类型id
                    brand.setTypeId(typeId);
                    insertTypeBrand(brand, username);
                } else if ("1".equals(brand.getDelflag())) {
                    //设置删除标记
                    brand.setDelflag("0");
                    updateTypeBrand(brand, username);
                }
            }
        }
        // 更新未被选中的品牌，如果之前存在记录就删除
        updateUnCheckedBrand(typeId, username, brandIds, map);
        //批量删除
        this.cascDelMapper.cascDel(username);
        //打印日志
        LOGGER.info(ValueUtil.BATCHUPDATETYPEBRAND + username);
        return 0;
    }

    /**
     * 更新未被选中的品牌，如果之前存在记录就删除
     * 
     * @param typeId
     *            类型ID {@link java.lang.Long}
     * @param username
     *            操作人名称
     * @param brandIds
     *            品牌ID数组
     * @param map
     *            封装参数的Map
     */
    @Transactional
    private void updateUnCheckedBrand(Long typeId, String username,
            String[] brandIds, Map<String, Long> map) {
        GoodsTypeBrand brand;
        // 查询所有的品牌
        List<GoodsBrand> brandList = this.goodsBrandService.queryAllBrand();
        // 获取所有的商品品牌的ID
        List<Long> allbrandIds = new ArrayList<Long>();
        for (int i = 0; i < brandList.size(); i++) {
            allbrandIds.add(brandList.get(i).getBrandId());
        }
        if (null != brandIds && brandIds.length > 0) {
            for (int i = 0; i < allbrandIds.size(); i++) {
                for (int k = 0; k < brandIds.length; k++) {
                    // 如果包含 就移除，剩下的就是为选中的
                    if (allbrandIds.get(i) == Long.parseLong(brandIds[k])) {
                        allbrandIds.remove(i);
                    }
                }
            }
        }
        // 循环未被选中的品牌ID
        for (int i = 0; i < allbrandIds.size(); i++) {
            //把类型id放进map集合中
            map.put("typeId", typeId);
            //把品牌id放进map集合中
            map.put("brandId", allbrandIds.get(i));
            //根据类型ID和品牌ID查询实体
            brand = this.goodsTypeBrandMapper
                    .queryTypeBrandByTypeIdAndBrandId(map);
            if (null != brand) {
                delTypeBrand(brand.getTypeBrandId(), username);
            }
        }
        //打印日志
        LOGGER.info(ValueUtil.UPDATEUNCHECKEDBRAND + username);
    }

}
