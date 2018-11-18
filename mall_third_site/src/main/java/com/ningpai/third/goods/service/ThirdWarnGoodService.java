/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.third.goods.service;

import com.ningpai.third.goods.vo.StockWarningVo;
import com.ningpai.util.PageBean;

/**
 * <p>货品预警</p>
 * @author NINGPAI-YuanKangKang
 * @since 2014年5月9日 上午10:37:22
 * @version 2.0
 */
public interface ThirdWarnGoodService {

    /**
     * 查询第三方库存预警值
     * @param thirdId 商家ID
     * @return
     */
    StockWarningVo selectstock(Long thirdId);


    /**
     * 更新库存下限
     * @param stockWarningVo 货品预警信息
     * @return
     */
    int updatestockgoods(StockWarningVo stockWarningVo);
    
    
    /**
     * 查询第三方预警货品
     * @param stockWarningVo 货品预警信息
     * @return
     */
    PageBean selectwarngoods(StockWarningVo stockWarningVo, PageBean pb);
    
    
    /**
     * 更新库存
     * @param stockWarningVo  货品预警信息
     * @return
     */
    int updatewarnstock(StockWarningVo stockWarningVo);
}
