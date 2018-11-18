/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.third.storestreet.service;



import com.ningpai.third.storestreet.bean.StoreStreetThirdImage;

import java.util.List;

/**
 * 登录Service
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年4月15日 下午3:56:00
 * @version 0.0.1
 */
public interface StoreStreetService {
    /**
     * 批量改变店铺街图片的信息
     * @param imageId 图片集合
     * @param thirdId 第三方商家
     */
    void updateImages(Long[] imageId,Long thirdId);
    /**
     * 根据ID获取图片详信息
     * @param imageId
     * @return
     */
    StoreStreetThirdImage selectStoreStreetImageById(Long imageId);


    /**
     * 保存店铺街图片
     * @param image
     * @return
     */
    int  updateStoreStreetImage(StoreStreetThirdImage image);
    /**
     * 保存店铺街图片
     * @param image
     * @return
     */
    int  saveStoreStreetImage(StoreStreetThirdImage image);


    /**
     * 查询该店铺下面的店铺街展示广告信息
     * @return
     */
    List<Object> selectStoreStreetListImage(Long thirdId);

}
