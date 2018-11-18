/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.service;

import java.util.List;
import java.util.Map;

import com.ningpai.goods.bean.GoodsGroup;
import com.ningpai.goods.util.SelectBean;
import com.ningpai.goods.vo.GoodsGroupVo;
import com.ningpai.util.PageBean;

import javax.servlet.http.HttpServletRequest;

/**
 * 商品组合Service
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月31日 下午3:50:11
 * @version 1.0
 */
public interface GoodsGroupService {
    /**
     * 保存商品组合
     * 
     * @param goodsGroup
     *            商品组合实体{@link com.ningpai.goods.bean.GoodsGroup}
     * @param username
     *            操作人名称
     * @return 插入的最新ID {@link java.lang.Long}
     */
    Long saveGoodsGroup(GoodsGroup goodsGroup, String username);

    /**
     * 删除商品组合
     * 
     * @param goodsGroupId
     *            商品组合ID {@link java.lang.Long }
     * @param username
     *            操作人名称
     * @return 删除的行数{@link java.lang.Integer}
     */
    int delGoodsGroup(Long goodsGroupId, String username);

    /**
     * 删除商品组合
     * 
     * @param goodsGroupId
     * @param username
     * @param thirdId
     * @return
     */
    int delGoodsGroupNew(Long goodsGroupId, String username, Long thirdId);

    /**
     * 更新商品组合信息
     * 
     * @param goodsGroup
     *            待更新的商品组合实体{@link com.ningpai.goods.bean.GoodsGroup}
     * @param username
     *            操作人名称
     * @return 更新的行数
     */
    int updateGoodsGroup(GoodsGroup goodsGroup, String username);

    /**
     * 根据组合ID查询商品组合实体
     * 
     * @param goodsGroupId
     *            组合ID {@link java.lang.Long}
     * @return 查询到的组合实体{@link com.ningpai.goods.bean.GoodsGroup}
     */
    GoodsGroup queryGoodsGroupByPrimaryKey(Long goodsGroupId);

    /**
     * 根据pageBean分页查询
     * 
     * @param pb
     *            分页辅助类{@link com.ningpai.util.PageBean}
     * @return 已经进行分页的PageBean
     */
    PageBean queryGoodsGroupByPageBean(PageBean pb);

    /**
     * 根据PageBean和参数Bean查询分页查询列表
     * 
     * @param pb
     *            分页Bean
     * @param selBean
     *            参数Bean
     * @return
     */
    PageBean queryGoodsGroupByPageBeanAndSearchBean(PageBean pb,
            SelectBean selBean);

    /**
     * 批量删除商品组合
     * 
     * @param groupIds
     *            待删除的商品组合的ID数组
     * @param username
     *            操作人名称
     * @return 删除的行数
     */
    int batchDelGroup(HttpServletRequest request, Long[] groupIds,
            String username);

    /**
     * 根据主键ID查询VO信息
     * 
     * @param groupId
     *            主键ID {@link java.lang.Long}
     * @return 查询到的详细信息 {@link com.ningpai.goods.vo.GoodsGroupVo}
     */
    GoodsGroupVo queryVoByPrimaryKey(Long groupId);

    /**
     * 根据货品ID查询组合或者是套装列表
     * 
     * @param productId
     *            {@link 货品ID}
     * @return 查询到的集合 {@link List}
     */
    List<GoodsGroupVo> queryGroupVoListByProductId(Long productId);

    /**
     * 根据货品ID查询组合或者是套装列表,去除当前的ID
     * 
     * @param productId
     *            {@link 货品ID}
     * @return 查询到的集合 {@link List}
     */
    List<GoodsGroupVo> queryGroupVoListWithOutProductId(Long productId);

    /**
     * 获取首页商品组合和商品预警的数量
     * 
     * @return
     */
    Map<String, Object> getIndexCount();
}
