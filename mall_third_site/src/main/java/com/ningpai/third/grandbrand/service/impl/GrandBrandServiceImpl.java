/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.third.grandbrand.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.goods.bean.GoodsBrand;
import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.third.grandbrand.bean.GrandBrand;
import com.ningpai.third.grandbrand.mapper.GrandBrandMapper;
import com.ningpai.third.grandbrand.service.GrandBrandService;
import com.ningpai.util.MapUtil;
import com.ningpai.util.PageBean;

/**
 * 品牌授权管理service实现
 * 
 * @author NINGPAI-LIH
 * @since 2014年5月23日19:37:36
 * @version 1.0
 */
@Service("GrandBrandService")
public class GrandBrandServiceImpl extends BasicSqlSupport implements GrandBrandService {

    private static final String THIRDID = "thirdId";
    private GrandBrandMapper mapper;

    /**
     * 查询该品牌商品的数量
     * 
     * @param brandId
     * @param thirdId
     * @return
     */
    @Override
    public int checkGoodCount(Long brandId, Long thirdId) {
        Map<String, Object> map = new HashMap<String, Object>();
        // 品牌ID
        map.put("brandId", brandId);
        // 商家ID
        map.put(THIRDID, thirdId);
        // 查询该品牌商品的数量
        return mapper.checkGoodCount(map);
    }

    /**
     * 分页查询
     * 
     * @param pb
     * @param goodsBrand
     *            商品品牌
     * @param rateStatus
     *            品牌状态
     * @param thirdId
     *            商家ID
     * @return
     */
    public PageBean queryAllGoodsGrandBrand(PageBean pb, GoodsBrand goodsBrand, String rateStatus, Long thirdId) {
        // 分装实体类属性
        Map<String, Object> map = MapUtil.getParamsMap(goodsBrand);
        map.put("startRowNum", pb.getStartRowNum());
        map.put("endRowNum", pb.getEndRowNum());
        map.put(THIRDID, thirdId);
        map.put("rateStatus", rateStatus);
        map.put("forBrand", null);
        // 查询总数
        pb.setRows(mapper.searchGrandBrandCount(map));
        try {
            // 查询列表页
            pb.setList(mapper.queryAllThirdGrandBrand(map));
            return pb;
        } finally {
            map = null;
        }

    }

    /**
     * 查询为加入的品牌
     * 
     * @param pb
     * @param goodsBrand
     * @param thirdId
     * @param forBrand
     * @return
     */
    public PageBean queryForGoodsGrandBrand(PageBean pb, GoodsBrand goodsBrand, Long thirdId, String forBrand) {
        // 分装实体类属性
        Map<String, Object> map = MapUtil.getParamsMap(goodsBrand);
        // 分页开始的页数
        map.put("startRowNum", pb.getStartRowNum());
        // 分页结束的页数
        map.put("endRowNum", pb.getEndRowNum());
        // 商家ID
        map.put(THIRDID, thirdId);
        map.put("rateStatus", null);
        map.put("forBrand", forBrand);
        // 查询总数
        pb.setRows(mapper.searchGrandBrandCount(map));

        try {
            // 查询列表页
            pb.setList(mapper.forQueryAllThirdGoodsBrand(map));
            return pb;
        } finally {
            map = null;
        }
    }

    /**
     * 循环申请品牌
     * 
     * @param brandId
     *            品牌id
     * @param thirdId
     */
    public void forTheGoodsBrand(String[] brandId, Long thirdId) {
        List<GrandBrand> grandBrands = new ArrayList<GrandBrand>();
        for (int i = 0; i < brandId.length; i++) {
            GrandBrand grandBrand = new GrandBrand();
            // 设置商家品牌ID
            grandBrand.setBrandId(Long.valueOf(brandId[i]));
            // 设置商家id
            grandBrand.setThirdId(thirdId);
            // 设置申请时间
            grandBrand.setModifyTime(new Date());
            grandBrands.add(grandBrand);
        }
        // 申请品牌
        mapper.forTheGoodsBrand(grandBrands);
    }

    /**
     * 更改品牌标记为删除
     * 
     * @param goodsBrand
     *            品牌对象
     */
    public void updateGrandBrand(GoodsBrand goodsBrand) {
        mapper.updateGrandBrand(goodsBrand);
    }

    /**
     * 批量修改品牌标记为删除
     * 
     * @param brandIds
     *            品牌ids数组
     * @param thirdId
     */
    public void updateGrandBrands(Long[] brandIds, Long thirdId) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Long> grandBrands = new ArrayList<Long>();
        for (int i = 0; i < brandIds.length; i++) {
            grandBrands.add(brandIds[i]);
        }
        try {
            map.put("grandBrands", brandIds);
            map.put(THIRDID, thirdId);
            mapper.updateGrandBrands(map);
        } finally {
            grandBrands = null;
        }
    }

    /**
     * 分页查询thirdstoreindex
     * 
     * @param thirdId
     *            查询参数
     * @return
     */
    public List<Object> queryAllGoodsGrandBrand(Long thirdId) {
        Map<String, Object> map = new HashMap<String, Object>();
        // 商家id
        map.put(THIRDID, thirdId);
        // 根据商家ID 查询下面的品牌信息
        return mapper.queryAllByThirdGoodsBrand(map);
    }

    public GrandBrandMapper getMapper() {
        return mapper;
    }

    @Resource(name = "GrandBrandMapper")
    public void setMapper(GrandBrandMapper mapper) {
        this.mapper = mapper;
    }

}
