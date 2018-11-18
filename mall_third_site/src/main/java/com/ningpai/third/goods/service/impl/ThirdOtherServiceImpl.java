/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.third.goods.service.impl;

import com.ningpai.goods.bean.GoodsBrand;
import com.ningpai.goods.bean.GoodsCate;
import com.ningpai.goods.bean.GoodsTag;
import com.ningpai.third.goods.dao.ThirdOtherMapper;
import com.ningpai.third.goods.service.ThirdOtherService;
import com.ningpai.third.goods.util.ThirdValueBean;
import com.ningpai.util.PageBean;
import com.ningpai.util.SelectBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 第三方Other service实现
 * </p>
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年5月9日 上午10:38:57
 * @version 2.0
 */
@Service("ThirdOtherService")
public class ThirdOtherServiceImpl implements ThirdOtherService {

    private static final String CATID = "catId";

    private ThirdOtherMapper thirdOtherMapper;

    /**
     * 查询当前店铺的一级分类ID
     * 
     * @param thirdId
     *            第三方ID {@link Long}
     * @param catId
     * @return
     */
    @Override
    public List<GoodsCate> queryGrandCateForThirdnew(Long thirdId, Long catId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 商家ID
            map.put(ThirdValueBean.THIRDID, thirdId);
            // 分类ID
            map.put(CATID, catId);
            return this.thirdOtherMapper.queryGrandCateForThirdnew(map);
        } finally {
            map = null;
        }
    }

    /**
     * 根据商家id 查询下面的所有品牌信息
     * 
     * @param thirdId
     *            第三方ID {@link Long}
     * @return
     */
    public List<GoodsBrand> queryGrandBrandByThirdId(Long thirdId) {
        // 根据商家id 查询下面的所有品牌信息
        return this.thirdOtherMapper.queryGrandListByThirdId(thirdId);
    }

    /**
     * 查询所有的商品标签类
     * 
     * @return
     */
    public List<GoodsTag> queryAllGoodsTagForThird() {
        return this.thirdOtherMapper.queryAllGoodsTagForThirdId();
    }

    /**
     * 根据商家ID查询商品类型
     * 
     * @param thirdId
     *            第三方ID {@link Long}
     * @return
     */
    public List<GoodsCate> queryGrandCateForThird(Long thirdId) {
        return this.thirdOtherMapper.queryAllGoodsCateForThird(thirdId);
    }

    /**
     * 查询指定商家的商品标签
     * 
     * @param thirdId
     *            第三方ID {@link Long}
     * @param catId
     *            类型ID
     * @return
     */
    public List<GoodsCate> queryGrandCateForThirdtwo(Long thirdId, Long catId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 商家ID
            map.put(ThirdValueBean.THIRDID, thirdId);
            // 分类ID
            map.put(CATID, catId);
            return this.thirdOtherMapper.queryAllGoodsCateForThirdtwo(map);
        } finally {
            map = null;
        }

    }

    /**
     * 根据商家ID 类型ID 获取单个的商品类型
     * 
     * @param thirdId
     *            分类ID {@link Long}
     * @param cateId
     * @return
     */
    public GoodsCate queryGoodsCateForThirdById(Long thirdId, Long cateId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 商家Id
            map.put(ThirdValueBean.THIRDID, thirdId);
            // fenlei ID
            map.put(CATID, cateId);
            return this.thirdOtherMapper.queryGoodsCateForThirdById(map);
        } finally {
            map = null;
        }
    }

    /**
     * 根据参数查询相关商品
     * 
     * @param pb
     *            分页辅助Bean {@link com.ningpai.util.PageBean}
     * @param thirdId
     *            第三方签约ID {@link Long }
     * @param goodsCatId
     *            选择的宁派分类ID {@link Long}
     * @return
     */
    public PageBean queryAboutGoodsForThirdByThirdInfo(PageBean pb, Long thirdId, Long goodsCatId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 商家ID
            map.put(ThirdValueBean.THIRDID, thirdId);
            // 分类ID
            map.put(CATID, goodsCatId);
            // 设置要分页的数据条数
            pb.setRows(this.thirdOtherMapper.queryAboutGoodsCountByCatId(map));
            // 分页的起始页
            map.put("startRowNum", pb.getStartRowNum());
            // 分页的结束页
            map.put("endRowNum", pb.getEndRowNum());
            // 装载根据条件筛选出来的商品信息
            pb.setList(this.thirdOtherMapper.queryAboutGoodsListByCatId(map));
            return pb;
        } finally {
            map = null;
        }
    }

    /**
     * 根据参数查询商品组合
     * 
     * @param pb
     *            分页辅助Bean {@link com.ningpai.util.PageBean}
     * @param thirdId
     *            thirdId 第三方签约ID {@link Long }
     * @param bean
     * @return
     */
    public PageBean queryThirdGroupByParam(PageBean pb, Long thirdId, SelectBean bean) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 商家ID
            map.put(ThirdValueBean.THIRDID, thirdId);
            map.put("condition", bean.getCondition());
            //
            map.put("searchText", bean.getSearchText());
            // 获取总行数
            pb.setRows(thirdOtherMapper.queryThirdTotalAcount(map));
            // 分页开始的条数
            map.put("startRowNum", pb.getStartRowNum());
            // 分页开始的条数
            map.put("endRowNum", pb.getEndRowNum());
            // 装载根据条件筛选出来的数据
            pb.setList(thirdOtherMapper.queryThirdGroupByParam(map));
            return pb;
        } finally {
            map = null;
        }
    }

    /**
     * 插入签约的品牌信息
     * 
     * @param thirdId
     *            第三方店铺ID {@link Long}
     * @param brandId
     *            品牌ID {@link Long}
     * @return
     */
    @Transactional
    public int saveGrantBrand(Long thirdId, Long brandId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 商家ID
            map.put(ThirdValueBean.THIRDID, thirdId);
            // 品牌ID
            map.put("brandId", brandId);
            return this.thirdOtherMapper.insertThirdGrantbrand(map);
        } finally {
            map = null;
        }
    }

    /**
     * 插入签约的分类信息
     * 
     * @param thirdId
     *            第三方店铺ID {@link Long}
     * @param catId
     *            分类ID {@link Long}
     * @return
     */
    @Transactional
    public int saveGrantCat(Long thirdId, Long catId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 商家ID
            map.put(ThirdValueBean.THIRDID, thirdId);
            // 分类ID
            map.put(CATID, catId);
            return this.thirdOtherMapper.insertThirdGrantCat(map);
        } finally {
            map = null;
        }
    }

    public ThirdOtherMapper getThirdOtherMapper() {
        return thirdOtherMapper;
    }

    @Resource(name = "ThirdOtherMapper")
    public void setThirdOtherMapper(ThirdOtherMapper thirdOtherMapper) {
        this.thirdOtherMapper = thirdOtherMapper;
    }

}
