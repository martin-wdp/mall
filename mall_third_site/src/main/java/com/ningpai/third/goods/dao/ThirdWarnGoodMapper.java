/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.third.goods.dao;

import com.ningpai.third.goods.vo.StockWarningVo;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 货品预警
 * </p>
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年5月9日 上午10:31:24
 * @version 2.0
 */
public interface ThirdWarnGoodMapper {

    /**
     * 查询第三方库存预警值
     * 
     * @return StockWarningVo
     */
    StockWarningVo selectstock(Long thirdId);

    /**
     * 更新库存预警下限
     * 
     * @param stockWarningVo
     * @return
     */
    int updatestockgoods(StockWarningVo stockWarningVo);

    /**
     * 查询第三方预警货品
     * 
     * @param map
     * @return
     */
    List<Object> selectwarngoods(Map<String, Object> map);

    /**
     * 查询库存预警货品记录数
     * 
     * @param map
     * @return
     */
    int selectwarncount(Map<String, Object> map);

    /**
     * 更新库存
     * 
     * @param stockWarningVo
     * @return
     */
    int updatewarnstock(StockWarningVo stockWarningVo);
}
