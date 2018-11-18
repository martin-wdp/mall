/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ningpai.goods.bean.Goods;
import com.ningpai.goods.util.GoodsSearchBean;
import com.ningpai.goods.vo.GoodsMoifiedVo;
import com.ningpai.goods.vo.GoodsOpenSpecValueVo;
import com.ningpai.util.PageBean;

import javax.servlet.http.HttpServletRequest;

/**
 * 商品Service
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月24日 下午5:17:44
 * @version 1.0
 */
public interface GoodsService {
    /**
     * 查询商品是否下架
     * */
    String selectCheckGoods(int goodsId);

    /**
     * 根据pagebean查询GoodsListVo
     * 
     * @param pb
     *            分页辅助对象 {@link com.ningpai.util.PageBean}
     * @return 封装好的分页对象
     */
    PageBean queryListVo(PageBean pb, String isThird);

    /**
     * 删除商品信息
     * 
     * @param goodsId
     *            商品信息ID {@link java.lang.Long}
     * @param username
     *            操作人名称
     * @return 删除的行数 {@link java.lang.Integer}
     */
    int delGoods(Long goodsId, String username);

    /**
     * 删除第三方商家商品信息
     * 
     * @param goodsId
     * @param thirdId
     * @param username
     * @return
     */
    int delThirdGoods(Long goodsId, Long thirdId, String username);

    /**
     * 批量删除商品信息
     * 
     * @param goodsIds
     *            商品ID数组 {@link java.lang.Long}
     * @param username
     *            操作人名称
     * @return 删除的行数
     */
    int batchDel(Long[] goodsIds, String username);

    /**
     * 保存商品
     * 
     * @param goods
     *            待保存的商品实体 {@link com.ningpai.goods.bean.Goods}
     * @param username
     *            操作人名称
     * @param map
     *            封装了所有需要用的参数 {@link java.util.Map}
     * @return 最新的ID {@link java.lang.Long}
     */
    Long saveGoods(Goods goods, String username, Map<String, String[]> map);

    /**
     * 根据分类ID查询相关商品
     * 
     * @param catId
     *            商品分类ID {@link java.lang.Long }
     * @return 查询到的商品列表 {@link java.util.List}
     */
    List<Object> queryGoodsListByCatId(Long catId);

    /**
     * 根据商品ID查询修改时的Vo
     * 
     * @param goodsId
     *            商品ID {@link java.lang.Long}
     * @return 查询到的VO {@link com.ningpai.goods.vo.GoodsMoifiedVo}
     */
    GoodsMoifiedVo queryModeifiedVoByGoodsId(Long goodsId);

    /**
     * 更新商品信息
     * 
     * @param goods
     *            待更新的商品实体 {@link com.ningpai.goods.bean.Goods}
     * @param map
     *            分装参数 {@link java.util.Map}
     * @param username
     *            操作人名称
     * @return 更新的行数 {@link java.lang.Integer}
     */
    int updateGoods(Goods goods, Map<String, String[]> map,
            String isThirdAuditUsed, String username);

    /**
     * 更新商品信息
     *
     * @param goods
     *            待更新的商品实体 {@link com.ningpai.goods.bean.Goods}
     * @param map
     *            分装参数 {@link java.util.Map}
     * @param username
     *            操作人名称
     * @return 更新的行数 {@link java.lang.Integer}
     */
    int updateGoodsNew(Goods goods, Map<String, String[]> map,
            String isThirdAuditUsed, String username);

    /**
     * 更新商品信息
     *
     * @param goods
     *            待更新的商品实体 {@link com.ningpai.goods.bean.Goods}
     * @param map
     *            分装参数 {@link java.util.Map}
     * @param username
     *            操作人名称
     * @return 更新的行数 {@link java.lang.Integer}
     */
    int updateGoodsParamSpec(Goods goods, Map<String, String[]> map,
            String isThirdAuditUsed, String username);

    /**
     * 只更新商品详细介绍
     * 
     * @param goods
     *            待更新的实体
     * @return 更新的行数
     */
    int updateGoodsDesc(Goods goods);

    /**
     * 根据pageBean和SearchBean高级查询
     * 
     * @param pb
     *            分页实体 {@link com.ningpai.util.PageBean}
     * @param searchBean
     *            参数bean {@link com.ningpai.goods.util.GoodsSearchBean}
     * @return PageBean
     */
    PageBean searchBySearchBeanAndPageBean(PageBean pb,
            GoodsSearchBean searchBean);

    /**
     * 根据pageBean和SearchBean高级查询
     *
     * @param pb
     *            分页实体 {@link com.ningpai.util.PageBean}
     * @param searchBean
     *            参数bean {@link com.ningpai.goods.util.GoodsSearchBean}
     * @param request
     * @return PageBean
     */
    PageBean searchBySearchBeanAndPageBean(PageBean pb, GoodsSearchBean searchBean, HttpServletRequest request);


    /**
     * 根据SearchBean高级查询
     *
     * 2015-12-19 wuyanbo add
     *
     * @param searchBean
     *            参数bean {@link com.ningpai.goods.util.GoodsSearchBean}
     * @return 查询货品的总数
     */
    int queryProductTotalCountBySearchBean(GoodsSearchBean searchBean);

    /**
     * 第三方上传商品时查询关联商品
     * 
     * @param pb
     * @param searchBean
     * @return
     */
    PageBean searchThirdBySearchBeanAndPageBean(PageBean pb,
            GoodsSearchBean searchBean);

    /**
     * 根据商品编号查询商品是否可用
     * 
     * @param goodsNo
     * @return 可用 返回true 不可用返回false
     */
    boolean queryCountByGoodsNo(String goodsNo);

    /**
     * 生成前台搜索引擎索引
     */
    boolean createIndex();

    /**
     * 查询所有的商品列表进行导出
     * 
     * @return 查询到的商品列表
     */
    List<Object> queryAllGoodsForExport(GoodsSearchBean searchBean);

    /**
     * 根据商品ID数组查询商品列表
     * 
     * @param goodsIds
     *            商品ID数组
     * @return 查询到的集合
     */
    List<Object> queryGoodsListVoListForExportByGoodsIds(Long[] goodsIds);

    /**
     * 批量上下架商品
     * 
     * @param goodsIds
     *            商品ID数组 {@link Long}
     * @param addedSta
     *            待更新的状态 1:上架状态 0:下架状态 {@link String}
     * @param username
     *            操作人名称 {@link String}
     * @return 更新的行数 {@link Integer}
     */
    int batchUploadOrDownGoods(Long[] goodsIds, String addedSta,
            String username, String auditStatus, Long thirdId);

    /**
     * 保存商品的同时创建所有的规格组合的货品
     * 
     * @param goodsId
     *            商品ID {@link Long}
     * @param goods
     *            商品{@link Goods}
     * @param username
     *            操作的用户名 {@link String}
     * @return 创建的个数{@link Integer}
     */
    int saveProductWhenSaveGoods(Long goodsId, Goods goods, String username);

    /**
     * 点击批量生成货品的时候
     * 
     * @param goodsId
     *            需要批量生成货品的商品ID {@link Long}
     * @param username
     *            操作人名称 {@link String}
     * @return 执行标记 {@link Integer}
     */
    int saveProductWhenClickBatchCreate(Long goodsId, String username);

    /**
     * 循环执行插入
     * 
     * @param list
     *            规格值集合 {@link ArrayList}
     */
    void loopSaveProduct(List<Object> list);

    /**
     * 点击生成的时候循环执行插入
     * 
     * @param list
     *            规格值集合 {@link ArrayList}
     */
    void loopSaveProductWhenClick(List<GoodsOpenSpecValueVo> list);

    /**
     * 查询所有的商品信息用于导出
     * 
     * @return 查询到的集合
     */
    List<Object> queryAllGoodsForExport(String isThird);

    /**
     * 复制商品
     * 
     * @param goodsId
     *            商品id
     * @param thirdId
     *            第三方商家id
     * @param thirdName
     *            商家名称
     * @author NINGPAI-LIH
     */
    void copyGoods(Long goodsId, Long thirdId, String thirdName);

    /**
     * 获取商品审核开关标记
     * 
     * @return
     */
    String selectAuditAction();

    /**
     * 批量审核上架商品
     * 
     * @param goodsIds
     * @param addedSta
     * @param auditStatus
     * @param username
     * @return
     */
    int batchAuditUploadOrDownGoods(Long[] goodsIds, String addedSta,
            String auditStatus, String username, Long thirdId);

    /**
     * 根据条件查询商品信息
     * 
     * @param pb
     * @param thirdId
     * @param productNo
     * @return
     */
    PageBean queryGoodsForCoupon(PageBean pb, Long thirdId, String productNo);

    /**
     * 根据ID获取商品信息
     * 
     * @param goodsId
     * @return
     */
    Goods queryGoodsByGoodsId(Long goodsId);

    /**
     * 批量下架商品
     *
     * @param goodsIds
     *            需要下架的商品的ID的集合
     * @return 操作结果
     */
    int batchDown(Long[] goodsIds, String username);

    /**
     * 批量上架商品
     * 
     * @param goodsIds
     * @return
     */
    int batchUp(Long[] goodsIds, String username);

    /**
     * 批量修改库存
     *用于后台批量修改库存
     *
     * @auhor houyichang  2015/8/24
     * @param goodsId
     * */
    int batchUpdateStock(Long[] goodsId,String stock);

}
