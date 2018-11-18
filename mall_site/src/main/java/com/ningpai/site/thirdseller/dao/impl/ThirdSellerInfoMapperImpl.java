package com.ningpai.site.thirdseller.dao.impl;


import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.site.thirdseller.bean.ThirdStoreInfo;
import com.ningpai.site.thirdseller.dao.ThirdStoreInfoMapper;

/**
 * 第三方店铺Mapper
 * @Description 第三方店铺Mapper
 * @author Songhl
 * @since 2015年8月28日 15:39:34
 */
@Repository("ThirdStoreInfoMapper")
public class ThirdSellerInfoMapperImpl extends BasicSqlSupport implements ThirdStoreInfoMapper{

    /**
     * 根据thirdId查询店铺信息
     * @param thirdId
     *             店铺Id{@link java.lang.Long}
     * @return
     */
    @Override
    public ThirdStoreInfo selectByThirdId(Long thirdId) {
        
        return this.selectOne("com.ningpai.third.seller.mapper.ThirdSotreInfoMapper.selectByThirdId", thirdId);
    }
    
}
