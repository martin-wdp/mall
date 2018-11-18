/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.ningpai.goods.bean.GoodsImage;

/**
 * 货品图片Service
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年1月3日 下午4:16:51
 * @version 1.0
 */
public interface GoodsImageService {
    /**
     * 添加记录
     * 
     * @param image
     *            {@link com.ningpai.goods.bean.GoodsImage}
     * @return 添加的行数
     */
    Long saveGoodsImage(GoodsImage image);

    /**
     * 删除记录
     * 
     * @param goodsImageId
     *            主键ID {@link java.lang.Longs}
     * @param username
     *            操作人名称
     * @return 删除的行数
     */
    int delGoodsImage(Long goodsImageId, String username);

    /**
     * 更新记录
     * 
     * @param goodsImage
     *            待更新的实体 {@link com.ningpai.goods.bean.GoodsImage}
     * @param username
     *            操作人名称
     * @return 更新的行数
     */
    int updateGoodsImage(GoodsImage goodsImage, String username);

    /**
     * 根据主键查询对象
     * 
     * @param goodsImageId
     *            主键ID {@link java.lang.Long}
     * @return 查询到的实体 {@link com.ningpai.goods.bean.GoodsImage}
     */
    GoodsImage queryByGoodsImageId(Long goodsImageId);

    /**
     * 根据货品ID查询关联的记录列表
     * 
     * @param productId
     *            货品ID {@link java.lang.Long}
     * @return 查询到的列表 {@link java.util.List}
     *         {@link com.ningpai.goods.bean.GoodsImage}
     */
    List<GoodsImage> queryImageListByProductId(Long productId);

    /**
     * 上传图片
     * 
     * @param productId
     *            货品ID {@link java.lang.Long}
     * @param username
     *            操作人名称
     * @param fileList
     *            需要上传的列表
     *            {@link org.springframework.web.multipart.MultipartFile}
     * @return 插入的图片
     */
    GoodsImage uploadImage(Long productId, String username,
            List<MultipartFile> fileList, HttpServletRequest request);

    /**
     * 上传单张图片
     * 
     * @param productId
     *            货品ID {@link java.lang.Long}
     * @param username
     *            操作人名称
     * @param file
     *            需要上传的图片{@link org.springframework.web.multipart.MultipartFile}
     * @return 插入的图片
     * @return
     */
    GoodsImage uploadImageSingle(Long productId, String username,
            MultipartFile file, HttpServletRequest request);

    /**
     * 设置默认图片
     * 
     * @param productId
     *            货品ID {@link java.lang.Long }
     * @param imageName
     *            图片路径
     * @param username
     *            操作人名称
     * @return 更新的行数 {@link java.lang.Integer}
     */
    int setDefaultImage(Long productId, String imageName, String username);

    /**
     * 批量删除图片
     * 
     * @param delImages
     *            待删除的图片ID的数组
     * @param username
     *            操作人名称
     * @return 删除的行数
     */
    int batchDelImage(String[] delImages, String username);

    /**
     * 批量更新图片信息
     * 
     * @param imageIds
     *            图片ID
     * @param productId
     *            货品ID
     * @param username
     *            操作人名称
     * @return
     */
    int batchUpdateImage(String[] imageIds, int productId, String username);

    /**
     * 根据多张原图路径,上传货品图片
     * 
     * @param imageUrl
     *            原图路径
     * @param productId
     *            货品ID
     * @param username
     *            操作人名称
     * @return 保存数量
     */
    int batchSaveImage(String[] imageUrl, int productId, String username);

    /**
     * 修改sku默认图片
     * 
     * @param goodsInfoId
     *            skuid
     * @param goodsImgId
     *            图片id
     * @return
     */
    int setDefaultImage(Long goodsInfoId, Long goodsImgId);

}
