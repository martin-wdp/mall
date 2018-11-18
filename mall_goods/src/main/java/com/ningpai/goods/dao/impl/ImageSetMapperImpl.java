/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.goods.dao.impl;

import java.util.List;
import java.util.Map;

import com.ningpai.goods.bean.InfoImageManage;
import org.springframework.stereotype.Repository;

import com.ningpai.goods.bean.ImageSet;
import com.ningpai.goods.dao.ImageSetMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * 图片设置数据层实现类
 *
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月17日 上午9:55:06
 * @version 1.0
 */
@Repository("GoodsImageSetMapper")
public class ImageSetMapperImpl extends BasicSqlSupport implements
        ImageSetMapper {
    /**
     * 查询图片规则集合
     */
    public List<ImageSet> queryImageSet() {
        return this
                .selectList("com.ningpai.goods.dao.ImageSetMapper.queryImageSet");
    }

    /**
     * 根据原图路径查询图片信息
     *
     * @param paramMap
     *            原图路径
     * @return 图片信息
     */
    @Override
    public InfoImageManage queryImageByUrl(Map<String, Object> paramMap) {
        return this.selectOne(
                "com.ningpai.goods.dao.ImageSetMapper.queryImageByUrl",
                paramMap);
    }

}
