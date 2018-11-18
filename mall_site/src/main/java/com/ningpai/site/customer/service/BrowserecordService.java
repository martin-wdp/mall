/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.site.customer.service;

import com.ningpai.site.customer.bean.Browserecord;

import java.util.List;

/**
 * 浏览记录接口
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年4月12日 下午4:42:47
 * @version 0.0.1
 */
public interface BrowserecordService {
    /**
     * 查询浏览记录
     * 
     * @param customerId
     *            会员编号
     * @return List<Browserecord> {@link java.util.List}
     */
    List<Browserecord> selectBrowserecord(Long customerId);

    /**
     * 根据主键删除
     *
     * @param likeId
     *            浏览编号
     * @return 0失败 1成功
     */
    int deleteByPrimaryKey(Long likeId,Long customerId);
    /**
     * 根据货品编号删除
     *
     * @param goodInfoId
     *           货品编号
     * @return 0失败 1成功
     */
    int deleteByGoodsInfoId(Long goodInfoId,Long customerId);
}
