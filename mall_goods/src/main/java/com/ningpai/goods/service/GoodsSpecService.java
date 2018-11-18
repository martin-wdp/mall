/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.service;

import java.util.List;

import com.ningpai.goods.bean.GoodsSpec;
import com.ningpai.goods.bean.GoodsSpecDetail;
import com.ningpai.goods.util.SelectBean;
import com.ningpai.goods.vo.GoodsSpecVo;
import com.ningpai.util.PageBean;

/**
 * 商品规格Service
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月18日 下午6:26:38
 * @version 1.0
 */
public interface GoodsSpecService {
    /**
     * 保存商品规格
     * 
     * @param goodsSpec
     *            商品规格对象 {@link com.ningpai.goods.bean.GoodsSpec}
     * @param detailnames
     *            详细值得名称数组
     * @param specDetailNicknames
     *            详细值得别名数组
     * @param specDetailImgs
     *            详细值得图片数组
     * @param specDetailSorts
     *            详细值得排序数组
     * @param username
     *            操作的用户名称
     * @return 影响的行数 {@link java.lang.Integer}
     */
    int saveGoodsSpec(GoodsSpec goodsSpec, String[] detailnames,
            String[] specDetailNicknames, String[] specDetailImgs,
            String[] specDetailSorts, String username);

    /**
     * 删除商品分类
     * 
     * @param specId
     *            商品分类ID{@link java.lang.Long}
     * @param username
     *            删除人名称
     * @return 删除的行数 {@link java.lang.Integer}
     */
    int delGoodsSpec(Long specId, String username);

    /**
     * 批量删除商品规格信息
     * 
     * @param specIds
     *            规格ID数组 {@link java.lang.Long}
     * @param username
     *            操作的用户名称
     * @return 删除的行数 {@link java.lang.Integer}
     */
    int batchDelGoodsSpec(Long[] specIds, String username);

    /**
     * 更新商品规格信息
     * 
     * @param goodsSpec
     *            需要更新的商品规格的实体{@link com.ningpai.goods.bean.GoodsSpec}
     * @param specDetails
     *            更新商品规格下的所有的规格值 {@link com.ningpai.goods.bean.GoodsSpecDetail}
     * @param username
     *            执行操作的用户名称
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    int updateGoodsSpec(GoodsSpec goodsSpec, List<GoodsSpecDetail> specDetails,
            String username);

    /**
     * 根据主键查询商品规格信息
     * 
     * @param specId
     *            规格ID
     * @return 查询到的商品规格Vo的实体 {@link com.ningpai.goods.vo.GoodsSpecVo}
     */
    GoodsSpecVo queryBySpecPrimaryKey(Long specId);

    /**
     * 根据分页辅助Bean查询规格的分页列表
     * 
     * @param pb
     *            分页帮助类 {@link com.ningpai.util.PageBean}
     * @return 塞进列表的分页辅助类 {@link com.ningpai.util.PageBean}
     */
    PageBean qyerySpecListByPageBean(PageBean pb);

    /**
     * 把传递过来的数组转换为规格值集合
     * 
     * @param specDetailIds
     *            所有的规格值ID数组
     * @param specDetailDelflag
     *            所有的规格值的删除标记
     * @param specDetailName
     *            所有的规格值得名称
     * @param specDetailNickname
     *            所有的规格值得别名
     * @param specDetailImg
     *            所有的规格值的图片
     * @param specDetailSort
     *            所有的规格值的排序
     * @return 整理好的列表 {@link java.util.List}
     *         {@link com.ningpai.goods.bean.GoodsSpecDetail}
     */
    List<GoodsSpecDetail> changeSpecDetail(String[] specDetailIds,
            String[] specDetailDelflag, String[] specDetailName,
            String[] specDetailNickname, String[] specDetailImg,
            String[] specDetailSort);

    /**
     * 查询所有的商品规格
     * 
     * @return 查询到的规格列表
     */
    List<GoodsSpec> queryAllSpec();

    /**
     * 查询所有的商品规格包含删除的
     * 
     * @return 查询到的规格列表
     */
    List<GoodsSpec> queryAllSpecIncludeDel();

    /**
     * 根据商品ID查询关联的规格Vo
     * 
     * @param goodsId
     *            商品ID {@link java.lang.Long}
     * @return 查询到的列表 {@link java.util.List}
     *         {@link com.ningpai.goods.vo.GoodsSpecVo}
     */
    List<GoodsSpecVo> querySpecVoByGoodsId(Long goodsId);

    /**
     * 参数查询
     * 
     * @param pb
     * @param selectBean
     * @return PageBean
     */
    PageBean searchSpecListByPageBean(PageBean pb, SelectBean selectBean);

    /**
     * 验证规格名称是否可用
     * 
     * @param specName
     *            待验证的规格名称 {@link java.lang.String}
     * @return 可用返回true 不可用返回false
     */
    boolean checkSpecName(String specName);

    /**
     * 保存规格信息
     * 
     * @param goodsSpec
     *            规格信息
     * @param username
     *            用户名
     */
    void saveGoodsSpec(GoodsSpec goodsSpec, String username);

    /**
     * 修改规格信息
     * 
     * @param goodsSpec
     *            规格信息
     * @param attribute
     *            用户名
     */
    void updateGoodsSpec(GoodsSpec goodsSpec, String username);
}
