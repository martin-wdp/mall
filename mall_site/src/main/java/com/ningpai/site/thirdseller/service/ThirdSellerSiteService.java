package com.ningpai.site.thirdseller.service;

import com.ningpai.site.thirdseller.bean.ThirdIndexFloor;
import com.ningpai.site.thirdseller.bean.ThirdStoreInfo;

/**
 * 第三方店铺首页service
 * @author qiyuanyuan
 *
 */
public interface ThirdSellerSiteService {

    /**
     * 第三方店铺首页楼层数据
     * @param tempId 
     *             模板ID
     * @param thirdId
     *             商家ID
     * @return
     */
    ThirdIndexFloor getStoreys(Long tempId,Long thirdId);
    
    /**
     * 根据店铺Id查询店铺信息
     * @param thirdId
     *        店铺Id{@link java.lang.Long}
     * @return
     *      店铺信息
     */
    ThirdStoreInfo selectByThirdId(Long thirdId);
}
