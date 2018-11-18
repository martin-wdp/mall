/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.ningpai.goods.bean.InfoImageManage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ningpai.goods.bean.GoodsImage;
import com.ningpai.goods.bean.GoodsProduct;
import com.ningpai.goods.dao.CascDelMapper;
import com.ningpai.goods.dao.GoodsImageMapper;
import com.ningpai.goods.dao.ImageSetMapper;
import com.ningpai.goods.service.GoodsImageService;
import com.ningpai.goods.service.GoodsProductService;
import com.ningpai.goods.util.ValueUtil;
import com.ningpai.util.MyLogger;
import com.ningpai.util.UploadUtil;

/**
 * 货品图片Service实现
 *
 * @author NINGPAI-YuanKangKang
 * @version 1.0
 * @since 2014年1月3日 下午4:22:42
 */
@Service("GoodsImageService")
public class GoodsImageServiceImpl implements GoodsImageService {
    // 货品图片DAO
    private GoodsImageMapper goodsImageMapper;
    // 图片规则DAO
    private ImageSetMapper imageSetMapper;
    // 货品Service
    private GoodsProductService goodsProductService;

    private CascDelMapper cascDelMapper;
    /**
     * 记录日志对象
     */
    private static final MyLogger LOGGER = new MyLogger(
            GoodsImageServiceImpl.class);

    public ImageSetMapper getImageSetMapper() {
        return imageSetMapper;
    }

    @Resource(name = "GoodsImageSetMapper")
    public void setImageSetMapper(ImageSetMapper imageSetMapper) {
        this.imageSetMapper = imageSetMapper;
    }

    public CascDelMapper getCascDelMapper() {
        return cascDelMapper;
    }

    @Resource(name = "CascDelMapper")
    public void setCascDelMapper(CascDelMapper cascDelMapper) {
        this.cascDelMapper = cascDelMapper;
    }

    public GoodsProductService getGoodsProductService() {
        return goodsProductService;
    }

    @Resource(name = "GoodsProductService")
    public void setGoodsProductService(GoodsProductService goodsProductService) {
        this.goodsProductService = goodsProductService;
    }

    public GoodsImageMapper getGoodsImageMapper() {
        return goodsImageMapper;
    }

    @Resource(name = "GoodsImageMapper")
    public void setGoodsImageMapper(GoodsImageMapper goodsImageMapper) {
        this.goodsImageMapper = goodsImageMapper;
    }

    /**
     * 添加记录
     *
     * @param image {@link com.ningpai.goods.bean.GoodsImage}
     * @return 添加的行数
     */
    @Transactional
    public Long saveGoodsImage(GoodsImage image) {
        // 打印日志
        LOGGER.info(ValueUtil.SAVEGOODSIMAGE);
        // 添加记录
        return this.goodsImageMapper.insertSelective(image);
    }

    /**
     * 删除记录
     *
     * @param goodsImageId 主键ID {@link java.lang.Longs}
     * @param username     操作人名称
     * @return 删除的行数
     */
    @Transactional
    public int delGoodsImage(Long goodsImageId, String username) {
        // 定义一个HashMap集合
        Map<String, String> map = new HashMap<String, String>();
        try {
            if (null != goodsImageId) {
                map.put("goodsImgId", goodsImageId.toString());
                map.put("delName", username);
                // 删除记录
                return this.goodsImageMapper.deleteByPrimaryKey(map);
            }
        } finally {
            // 打印日志
            LOGGER.info(ValueUtil.DELGOODSIMAGE + username);
            this.cascDelMapper.cascDel(username);
            map = null;
        }
        return 0;
    }

    /**
     * 更新记录
     *
     * @param goodsImage 待更新的实体 {@link com.ningpai.goods.bean.GoodsImage}
     * @param username   操作人名称
     * @return 更新的行数
     */
    @Transactional
    public int updateGoodsImage(GoodsImage goodsImage, String username) {
        goodsImage.setGoodsImgModifiedName(username);
        // 打印日志
        LOGGER.info(ValueUtil.UPDATEGOODSIMAGE + username);
        // 更新记录
        return this.goodsImageMapper.updateByPrimaryKeySelective(goodsImage);
    }

    /**
     * 根据主键查询对象
     *
     * @param goodsImageId 主键ID {@link java.lang.Long}
     * @return 查询到的实体 {@link com.ningpai.goods.bean.GoodsImage}
     */
    public GoodsImage queryByGoodsImageId(Long goodsImageId) {
        // 根据主键查询对象
        return this.goodsImageMapper.selectByPrimaryKey(goodsImageId);
    }

    /**
     * 根据货品ID查询关联的记录列表
     *
     * @param productId 货品ID {@link java.lang.Long}
     * @return 查询到的列表 {@link java.util.List}
     * {@link com.ningpai.goods.bean.GoodsImage}
     */
    public List<GoodsImage> queryImageListByProductId(Long productId) {
        // 根据货品Id查询关联的记录列表
        return this.goodsImageMapper.queryByProductId(productId);
    }

    /**
     * 上传图片
     *
     * @param productId 货品ID {@link java.lang.Long}
     * @param username  操作人名称
     * @param fileList  需要上传的列表
     *                  {@link org.springframework.web.multipart.MultipartFile}
     * @return 插入的图片
     */
    @Transactional
    public GoodsImage uploadImage(Long productId, String username,
                                  List<MultipartFile> fileList, HttpServletRequest request) {
        GoodsImage image = null;
        // 定义一个HashMap集合
        Map<String, String> imagePath = new HashMap<String, String>();
        try {
            // 判断文件列表不为空
            if (null != fileList && !fileList.isEmpty()) {
                // 循环
                for (int i = 0; i < fileList.size(); i++) {
                    if (!fileList.get(i).isEmpty()) {
                        imagePath = UploadUtil.uploadFile(fileList.get(i),
                                request);
                        image = new GoodsImage();
                        // 设置货品ID
                        image.setGoodsInfoId(productId);
                        // 上传原图
                        image.setImageArtworkName((String) imagePath
                                .get("oldimg"));
                        // 上传小图片
                        image.setImageThumName((String) imagePath.get("0"));
                        // 上传中图
                        image.setImageInName((String) imagePath.get("1"));
                        // 上传大图
                        image.setImageBigName((String) imagePath.get("2"));
                        image.setGoodsImgSort(0);
                        image.setGoodsImgDelflag("0");
                        image.setGoodsImgCreateName(username);
                        image.setGoodsImgId(saveGoodsImage(image));
                    }
                }
            }
            return image;
        } finally {
            // 打印日志
            LOGGER.info(ValueUtil.UPLOADIMAGE + username);
            image = null;
            imagePath = null;
        }
    }

    /**
     * 上传单张图片
     *
     * @param productId 货品ID {@link java.lang.Long}
     * @param username  操作人名称
     * @param file      需要上传的图片{@link org.springframework.web.multipart.MultipartFile}
     * @return
     */
    @Transactional
    public GoodsImage uploadImageSingle(Long productId, String username,
                                        MultipartFile file, HttpServletRequest request) {
        GoodsImage image = null;
        // 定义一个HashMap集合
        Map<String, String> imagePath = new HashMap<String, String>();
        try {
            if (!file.isEmpty()) {
                imagePath = UploadUtil.uploadFile(file, request);
                image = new GoodsImage();
                // 设置货品ID
                image.setGoodsInfoId(productId);
                // 上传原图
                image.setImageArtworkName((String) imagePath.get("oldimg"));
                // 上传小图片
                image.setImageThumName((String) imagePath.get("0"));
                // 上传中图
                image.setImageInName((String) imagePath.get("1"));
                // 上传大图
                image.setImageBigName((String) imagePath.get("2"));
                image.setGoodsImgSort(0);
                image.setGoodsImgDelflag("0");
                image.setGoodsImgCreateName(username);
                image.setGoodsImgId(saveGoodsImage(image));
            }
            return image;
        } finally {
            // 打印日志
            LOGGER.info(ValueUtil.UPLOADIMAGE + username);
            image = null;
            imagePath = null;
        }
    }

    /**
     * 设置默认图片
     *
     * @param productId 货品ID {@link java.lang.Long }
     * @param imageName 图片路径
     * @param username  操作人名称
     * @return 更新的行数 {@link java.lang.Integer}
     */
    public int setDefaultImage(Long productId, String imageName, String username) {
        GoodsProduct product = null;
        try {
            if (null != productId && null != imageName) {
                product = new GoodsProduct();
                product.setGoodsInfoId(productId);
                product.setGoodsInfoImgId(imageName);
                // 设置默认图片
                return this.goodsProductService.updateProduct(product,
                        username, null, null, null);
            }
        } finally {
            // 打印日志
            LOGGER.info(ValueUtil.SETDEFAULTIMAGE + username);
            product = null;
        }
        return 0;
    }

    /**
     * 批量删除图片
     *
     * @param delImages 待删除的图片ID的数组
     * @param username  操作人名称
     * @return 删除的行数
     */
    @Transactional
    public int batchDelImage(String[] delImages, String username) {
        Integer count = 0;
        try {
            if (null != delImages && delImages.length > 0) {
                for (int i = 0; i < delImages.length; i++) {
                    // 批量删除图片
                    count += this.delGoodsImage(Long.parseLong(delImages[i]),
                            username);
                }
            }
            return count;
        } finally {
            // 打印日志
            LOGGER.info(ValueUtil.BATCHDELIMAGE + username);
            this.cascDelMapper.cascDel(username);
            count = null;
        }
    }

    /**
     * 批量更新图片信息
     *
     * @param imageIds  图片ID
     * @param productId 货品ID
     * @param username  操作人名称
     * @return
     */
    @Transactional
    public int batchUpdateImage(String[] imageIds, int productId,
                                String username) {
        Integer count = 0;
        GoodsImage image = null;
        try {
            if (null != imageIds && imageIds.length > 0) {
                for (int i = 0; i < imageIds.length; i++) {
                    image = new GoodsImage();
                    image.setGoodsImgId(Long.parseLong(imageIds[i]));
                    image.setGoodsInfoId(Long.parseLong(String
                            .valueOf(productId)));
                    image.setGoodsImgCreateName(username);
                    // 批量更新图片信息
                    count += this.goodsImageMapper
                            .updateByPrimaryKeySelective(image);
                }
            }
            return count;
        } finally {
            count = null;
            image = null;
        }
    }

    /**
     * 根据多张原图路径,上传货品图片
     *
     * @param imageUrl  原图路径
     * @param productId 货品ID
     * @param username  操作人名称
     * @return 保存数量
     */
    public int batchSaveImage(String[] imageUrl, int productId, String username) {
        int count = 0;
        GoodsImage image = null;

        try {
            if (null != imageUrl && imageUrl.length > 0) {
                for (int i = 0; i < imageUrl.length; i++) {
                    image = new GoodsImage();
                    image.setGoodsImgCreateName(username);
                    image.setGoodsImgDelflag("0");
                    image.setGoodsImgSort(0);
                    image.setGoodsInfoId(Long.parseLong(String
                            .valueOf(productId)));
                    image.setImageArtworkName(imageUrl[i]);
                    if (imageUrl[i].indexOf(".com") != -1) {
                        // 设置大图
                        image.setImageBigName(imageUrl[i] + "!352");
                        // 设置中图
                        image.setImageInName(imageUrl[i] + "!160");
                        // 设置小图
                        image.setImageThumName(imageUrl[i] + "!56");
                    } else {
                        Map<String, Object> paramMap = new HashMap<String, Object>();
                        paramMap.put("url", imageUrl[i]);
                        InfoImageManage infoImageManage = imageSetMapper
                                .queryImageByUrl(paramMap);
                        // 设置大图
                        image.setImageBigName(infoImageManage.getBigImgUrl());
                        // 设置中图
                        image.setImageInName(infoImageManage.getMiddleImgUrl());
                        // 设置小图
                        image.setImageThumName(infoImageManage.getSmallImgUrl());
                    }

                    this.goodsImageMapper.insertSelective(image);
                    count += 1;
                }
                return count;
            } else {
                return 0;
            }
        } finally {
            image = null;
        }
    }

    /**
     * 修改sku默认图片
     *
     * @param goodsInfoId skuid
     * @param goodsImgId  图片id
     * @return
     */
    @Override
    @Transactional
    public int setDefaultImage(Long goodsInfoId, Long goodsImgId) {
        goodsImageMapper.updateByProductInfoId(goodsInfoId);
        return goodsImageMapper.setDefaultImage(goodsImgId);
    }
}
