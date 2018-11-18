/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.third.storestreet.service.impl;

import com.ningpai.third.storestreet.bean.StoreStreetThirdImage;
import com.ningpai.third.storestreet.mapper.StoreStreetMapper;
import com.ningpai.third.storestreet.service.StoreStreetService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * #see com.ningpai.site.login.service.LoginService
 * 
 * @author NINGPAI-zhanghailong
 * @since 2015年6月15日
 */
@Service("storeStreetService")
public class StoreStreetServiceImpl implements StoreStreetService {

    @Resource(name = "storeStreetMapper")
    private StoreStreetMapper storeStreetMapper;

    @Override
    public void updateImages(Long[] imageId, Long thirdId) {

        //装载查询条件
        List<Long> imageIds = new ArrayList<Long>();
        //遍历图片ID 循环放入集合里
        for (int i = 0; i < imageId.length; i++) {
            imageIds.add(imageId[i]);
        }
        try {
            Map<String,Object> param = new HashMap<String,Object>();
            //要删除的图片ID
            param.put("imageId",imageIds);
            //当前登录的商家
            param.put("thirdId", thirdId);
            storeStreetMapper.updateImages(param);
        } finally {
            imageIds = null;
        }
    }

    @Override
    public StoreStreetThirdImage selectStoreStreetImageById(Long imageId) {
        return this.storeStreetMapper.selectStoreStreetImageById(imageId);
    }

    @Override
    public int updateStoreStreetImage(StoreStreetThirdImage image) {
        return  this.storeStreetMapper.updateStoreStreetImage(image);
    }

    @Override
    public int saveStoreStreetImage(StoreStreetThirdImage image) {
        return this.storeStreetMapper.saveStoreStreetImage(image);
    }

    @Override
    public List<Object> selectStoreStreetListImage(Long thirdId) {
        return this.storeStreetMapper.selectStoreStreetListImage( thirdId);
    }
}
