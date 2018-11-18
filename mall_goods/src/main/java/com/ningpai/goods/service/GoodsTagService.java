/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.service;

import java.util.List;

import com.ningpai.goods.bean.GoodsTag;
import com.ningpai.goods.util.SelectBean;
import com.ningpai.util.PageBean;

/**
 * 商品标签业务层接口
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月14日 下午2:23:49
 * @version 1.0
 */
public interface GoodsTagService {
    /**
     * 根据标签ID查询标签实体
     * 
     * @param tagId
     *            {@link java.lang.Long}
     * @return 查询到的标签实体 {@link com.ningpai.goods.bean.GoodsTag}
     */
    GoodsTag selectByPaimarykey(Long tagId);

    /**
     * 查询所有的标签
     * 
     * @return 查询到的标签列表 {@link com.ningpai.goods.bean.GoodsTag}
     *         {@link java.util.List}
     * @see {@link com.ningpai.goods.bean.GoodsTag}
     */
    PageBean selectAllTag(PageBean pageBean, SelectBean selectBean);

    /**
     * 根据主键删除
     * 
     * @param tagId
     *            标签ID
     * @param del_name
     *            删除人名称
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    int deleteByPrimaryKey(Long tagId, String delname);

    /**
     * 插入一个商品标签(可选属性)
     * 
     * @param record
     *            待插入的商品标签实体 {@link com.ningpai.goods.bean.GoodsTag}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    int insertSelective(GoodsTag record);

    /**
     * 批量删除商品标签
     * 
     * @param tagIds
     *            需要删除的标签ID{@link java.lang.Map}
     * @param delName
     *            删除人名称 {@link com.ningpai.goods.bean.GoodsTag}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    int batchDeleteTag(Long[] tagIds, String delName);

    /**
     * 根据条件模糊查询
     * 
     * @param columnName
     *            模糊查询的列名
     * @param paramvalue
     *            模糊查询的值
     * @return {@link com.ningpai.goods.bean.GoodsTag} {@link java.util.List}
     */
    List<GoodsTag> queryTagByParam(String columnName, String paramvalue);

    /**
     * 更新商品标签
     * 
     * @param tag
     *            {@link com.ningpai.goods.bean.GoodsTag}
     *            {@link java.lang.Integer}
     * @param username
     *            修改人名称
     * @return
     */
    int updateTagSelective(GoodsTag tag, String username);

    /**
     * 查询所有的商品标签
     * 
     * @return 查询到的标签列表 {@link java.util.List}
     *         {@link com.ningpai.goods.bean.GoodsTag}
     */
    List<Object> queryAllTag();

    /**
     * 验证标签名称是否已经存在
     * 
     * @param tagName
     *            待验证的标签名称
     * @return 可用返回true 不可用返回false
     */
    boolean checkTagName(String tagName);
}
