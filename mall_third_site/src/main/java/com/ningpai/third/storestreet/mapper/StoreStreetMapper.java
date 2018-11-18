package com.ningpai.third.storestreet.mapper;

import com.ningpai.third.storestreet.bean.StoreStreetThirdImage;

import java.util.List;
import java.util.Map;

/**
 * IPRecord Mapper
 * 
 * @author NINGPAI-zhanghailong
 * @since 2015年6月15日
 */
public interface StoreStreetMapper {
    /**
     * 批量改变店铺街图片的信息
     * @param map 图片集合
     */
    void updateImages(Map<String,Object> map);

    /**
     * 根据ID获取图片详信息
     * @param imageId
     * @return
     */
    StoreStreetThirdImage selectStoreStreetImageById(Long imageId);
    /**
     * 修改店铺街图片
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
