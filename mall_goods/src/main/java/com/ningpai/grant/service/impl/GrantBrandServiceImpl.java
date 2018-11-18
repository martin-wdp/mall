/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.grant.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.goods.bean.GoodsBrand;
import com.ningpai.grant.dao.GrantBrandMapper;
import com.ningpai.grant.service.GrantBrandService;
import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.util.MapUtil;
import com.ningpai.util.PageBean;

/**
 * 品牌授权管理service实现
 * 
 * @author NINGPAI-LIH
 * @since 2014年5月29日10:13:55
 * @version 1.0
 */
@Service("GrantBrandService")
public class GrantBrandServiceImpl extends BasicSqlSupport implements GrantBrandService {
    private GrantBrandMapper mapper;

    /**
     * 分页查询
     *
     * @param pb
     *            分页参数
     * @param goodsBrand
     *            查询参数
     * @return
     */
    public PageBean queryAllGoodsGrandBrand(PageBean pb, GoodsBrand goodsBrand) {
        // 分装实体类属性
        Map<String, Object> map = MapUtil.getParamsMap(goodsBrand);
        map.put("startRowNum", pb.getStartRowNum());
        map.put("endRowNum", pb.getEndRowNum());
        map.put("rateStatus", '0');
        // 查询总数
        pb.setRows(mapper.searchGrandBrandCount(map));
        if (goodsBrand != null) {
            pb.setObjectBean(goodsBrand);
        }
        try {
            // 查询列表页
            pb.setList(mapper.queryAllThirdGrandBrand(map));
            return pb;
        } finally {
            map = null;
        }

    }

    /**
     *
     * @param brandIds
     *            要修改的品牌id数组
     */
    @Override
    public void updateGrantBrands(Long[] brandIds, String reason, String rateStatuts) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Long> brandId = new ArrayList<Long>();
        for (int i = 0; i < brandIds.length; i++) {
            brandId.add(brandIds[i]);
        }
        map.put("brandIds", brandId);
        map.put("reason", reason);
        map.put("rateStatuts", rateStatuts);
        try {
            mapper.updateGrantBrands(map);
        } finally {
            map = null;
        }
    }

    public GrantBrandMapper getMapper() {
        return mapper;
    }

    @Resource(name = "GrantBrandMapper")
    public void setMapper(GrantBrandMapper mapper) {
        this.mapper = mapper;
    }

}
