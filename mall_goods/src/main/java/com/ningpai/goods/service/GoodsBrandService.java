/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ningpai.goods.bean.GoodsBrand;
import com.ningpai.goods.util.SelectBean;
import com.ningpai.util.PageBean;

/**
 * 商品品牌service层接口
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月16日 下午7:50:05
 * @version 1.0
 */
public interface GoodsBrandService {
    /**
     * 根据主键删除商品品牌
     * 
     * @param brandId
     *            品牌主键ID {@link java.lang.Long}
     * @param username
     *            删除用户的用户名称
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    int deleteGoodsBrand(Long brandId, String username);

    /**
     * 批量删除商品品牌
     * 
     * @param brandIds
     *            商品品牌ID的集合 {@link java.lang.Long}
     * @param username
     *            删除人名称
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    Integer batchDeleteGodosBrand(Long[] brandIds, String username);

    /**
     * 更新商品品牌信息
     * 
     * @param goodsBrand
     *            更新的商品品牌的实体 {@link com.ningpai.goods.bean.GoodsBrand}
     * @param username
     *            更新的用户名称
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    int updateGoodsBrad(GoodsBrand goodsBrand, String username);

    /**
     * 插入一条商品品牌信息
     * 
     * @param goodsBrand
     *            待插入的商品品牌的实体 {@link com.ningpai.goods.bean.GoodsBrand}
     * @param username
     *            插入记录的用户名称
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    int insertGoodsBrand(GoodsBrand goodsBrand, String username);

    /**
     * 根据PageBean 查询分页列表
     * 
     * @param pageBean
     *            分页辅助类 {@link com.ningpai.goods.util.PageBean}
     * @return 查询到的列表封装到PageBean中
     */
    PageBean queryByPageBean(PageBean pageBean);

    /**
     * 根据Id查询商品品牌
     * 
     * @param brandId
     *            商品品牌ID {@link java.lang.Long}
     * @return {@link com.ningpai.goods.bean.GoodsBrand}
     */
    GoodsBrand queryBrandById(Long brandId);

    /**
     * 查询所有的商品品牌
     * 
     * @return 查询到的商品品牌的集合 {@link java.util.List}
     *         {@link com.ningpai.goods.bean.GoodsBrand}
     */
    List<GoodsBrand> queryAllBrand();

    /**
     * 查询所有的商品品牌
     *
     * @return 查询到的商品品牌的集合 {@link java.util.List}
     *         {@link com.ningpai.goods.bean.GoodsBrand}
     */
    List<GoodsBrand> queryAllGoodsBrandBy_PL();
    /**
     * 根据品牌类型查询商品品牌
     * ADD BY LY 15-11-05
     * @return 查询到的商品品牌的集合 {@link java.util.List}
     *         {@link com.ningpai.goods.bean.GoodsBrand}
     */
    List<GoodsBrand> queryGoodsBrand(Map<String,Object> param);


    /**
     * 根君名称查询商品品牌
     * 
     * @return 查询到的商品品牌的集合 {@link java.util.List}
     *         {@link com.ningpai.goods.bean.GoodsBrand}
     */
    List<GoodsBrand> queryallbrandbyName(String brandName);

    /**
     * 参数查询分页bean
     * 
     * @param pb
     * @param selectBean
     * @return int
     */
    PageBean searchByPageBean(PageBean pb, SelectBean selectBean);

    /**
     * 查询所有品牌
     * 
     * @return List
     */
    List<GoodsBrand> queryAllBrandList();

    /**
     * 验证品牌名称是否可用
     * 
     * @param brandNmae
     *            待验证的品牌名称
     * @return 可用返回true 不可用返回false
     */
    boolean checkBrandName(String brandNmae);

    /**
     * 导出商品品牌
     * 
     * @param response
     */
    void exportGoodsBrand(HttpServletResponse response);

    /**
     * 导出商品品牌模板
     * 
     * @param response
     */
    void exportGoodsBrandTemp(HttpServletResponse response);

    /**
     * 导入商品品牌
     * 
     * @param request
     * @param response
     * @param multiRequest
     */
    String importGoodsBrandByExcel(HttpServletRequest request,
            HttpServletResponse response,
            MultipartHttpServletRequest multiRequest);

    /**
     * 验证品牌名称，不可重复添加
     *
     * @param brandName
     *            待验证的品牌名称
     * @return 返回查询条数，若=0则可以添加，反之不可添加
     */
    int selectByBrandName(String brandName);
}
