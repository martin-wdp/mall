/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.third.storestreet.mapper.impl;
import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.third.storestreet.bean.StoreStreetThirdImage;
import com.ningpai.third.storestreet.mapper.StoreStreetMapper;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @see com.ningpai.third.storestreet.mapper.StoreStreetMapper
 * @author NINGPAI-zhanghailong
 * @since 2015年6月15日
 * @version 0.0.1
 */
@Repository("storeStreetMapper")
public class StoreStreetMapperImpl extends BasicSqlSupport implements StoreStreetMapper {

    /**
     * 批量改变店铺街图片的信息
     * @param map 图片集合
     */
    @Override
    public void updateImages(Map<String, Object> map) {
         this.update("com.ningpai.third.seller.mapper.SotreInfoMapper.updateImages", map);
    }

    /**
     * 根据ID获取图片详信息
     * @param imageId
     * @return StoreStreetThirdImage
     */
    @Override
    public StoreStreetThirdImage selectStoreStreetImageById(Long imageId) {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("imageId",imageId);
        return this.selectOne("com.ningpai.third.seller.mapper.SotreInfoMapper.selectStoreStreetImageById", map);
    }

    /**
     * 修改店铺街图片
     * @param image
     * @return int
     */
    @Override
    public int updateStoreStreetImage(StoreStreetThirdImage image) {
        return  this.update("com.ningpai.third.seller.mapper.SotreInfoMapper.updateStoreStreetImage", image);
    }

    /**
     * 保存店铺街图片
     * @param image
     * @return int
     */
    @Override
    public int saveStoreStreetImage(StoreStreetThirdImage image) {
        return  this.insert("com.ningpai.third.seller.mapper.SotreInfoMapper.saveStoreStreetImage", image);
    }

    /**
     * 查询该店铺下面的店铺街展示广告信息
     * @param thirdId
     * @return List
     */
    @Override
    public List<Object> selectStoreStreetListImage(Long thirdId) {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("thirdId",thirdId);
        return this.selectList("com.ningpai.third.seller.mapper.SotreInfoMapper.selectStoreStreetListImage", map);
    }
}
