package com.ningpai.site.thirdseller.dao;

import com.ningpai.site.thirdseller.bean.ThirdStoreInfo;
/**
 * 第三方店铺Mapper
 * @Description 第三方店铺Mapper
 * @author Songhl
 * @since 2015年8月28日 15:39:34
 */
public interface ThirdStoreInfoMapper {

    /**
     * 根据thirdId查询店铺信息
     * @param thirdId
     *             店铺Id{@link java.lang.Long}
     * @return
     *     对象
     */
    ThirdStoreInfo selectByThirdId(Long thirdId);

}
