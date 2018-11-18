/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.service;

import com.ningpai.util.PageBean;
import com.ningpai.util.SelectBean;

/**
 * 商品关注Service
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年3月18日 下午4:34:31
 * @version 1.0
 */
public interface GoodsAtteService {
    /**
     * 删除商品关注信息
     * 
     * @param atteId
     *            主键ID {@link java.lang.Long}
     * @return 删除的行数 {@link java.lang.Integer}
     */
    int deleteAtte(Long atteId);

    /**
     * 批量删除商品关注信息
     * 
     * @param atteIds
     *            {@link java.lang.Long}
     * @return 删除的行数 {@link java.lang.Integer}
     */
    int batchDelAtte(Long[] atteIds);

    /**
     * 根据分页参数和参数查询分页集合
     * 
     * @param pb
     *            分页辅助Bean {@link com.ningpai.util.PageBean}
     * @return 封装过集合的PageBean {@link com.ningpai.util.PageBean}
     */
    PageBean queryByPageAndParam(PageBean pb, SelectBean selectBean);

    /**
     * 根据货品ID和分页参数查询关注信息
     * 
     * @param pb
     *            分页辅助Bean {@link com.ningpai.util.PageBean}
     * @param productId
     *            {@link java.lang.Long}
     * @return {@link com.ningpai.util.PageBean}
     */
    PageBean querybyProductId(PageBean pb, Long productId);

    /**
     * 查询商品关注列表
     */
    int selectGoodsAtteCount();
}
