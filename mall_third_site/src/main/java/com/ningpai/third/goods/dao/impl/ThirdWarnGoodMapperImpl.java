/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.third.goods.dao.impl;

import com.ningpai.third.goods.dao.ThirdWarnGoodMapper;
import com.ningpai.third.goods.vo.StockWarningVo;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 库存预警实现类
 * </p>
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年5月9日 上午10:34:16
 * @version 2.0
 */
@Repository("ThirdWarnGoodMapper")
public class ThirdWarnGoodMapperImpl extends SqlSessionDaoSupport implements
        ThirdWarnGoodMapper {

    /*
     * 
     * 
     * @see
     * com.ningpai.goods.dao.ThirdOtherMapper#queryGrandListByThirdId(java.lang
     * .Long)
     */
    @Override
    public StockWarningVo selectstock(Long thirdId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("thirdId", thirdId);
        return this.getSqlSession().selectOne(
                "com.ningpai.third.goods.dao.ThirdWarnGoodMapper.selectstock",
                map);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.goods.dao.ThirdOtherMapper#queryGrandListByThirdId(java.lang
     * .Long)
     */
    @Override
    public int updatestockgoods(StockWarningVo stockWarningVo) {

        return this
                .getSqlSession()
                .update("com.ningpai.third.goods.dao.ThirdWarnGoodMapper.updatestockgoods",
                        stockWarningVo);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.goods.dao.ThirdOtherMapper#queryGrandListByThirdId(java.lang
     * .Long)
     */
    @Override
    public List<Object> selectwarngoods(Map<String, Object> map) {

        return this
                .getSqlSession()
                .selectList(
                        "com.ningpai.third.goods.dao.ThirdWarnGoodMapper.selectwarngoods",
                        map);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.goods.dao.ThirdOtherMapper#queryGrandListByThirdId(java.lang
     * .Long)
     */
    @Override
    public int selectwarncount(Map<String, Object> map) {

        return this
                .getSqlSession()
                .selectOne(
                        "com.ningpai.third.goods.dao.ThirdWarnGoodMapper.selectwarncount",
                        map);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.goods.dao.ThirdOtherMapper#queryGrandListByThirdId(java.lang
     * .Long)
     */
    @Override
    public int updatewarnstock(StockWarningVo stockWarningVo) {

        return this
                .getSqlSession()
                .update("com.ningpai.third.goods.dao.ThirdWarnGoodMapper.updatewarnstock",
                        stockWarningVo);
    }

}
