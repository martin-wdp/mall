/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.service;

import com.ningpai.customer.bean.CustomerFollow;
import com.ningpai.goods.bean.Goods;
import com.ningpai.goods.bean.GoodsProduct;
import com.ningpai.goods.bean.GoodsProductAutoStyle;
import com.ningpai.goods.util.CalcStockUtil;
import com.ningpai.goods.util.GoodsSearchBean;
import com.ningpai.goods.vo.GoodsProductDetailViewVo;
import com.ningpai.goods.vo.GoodsProductDetailVo;
import com.ningpai.goods.vo.GoodsProductVo;
import com.ningpai.util.PageBean;
import com.ningpai.util.SelectBean;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 货品信息Service
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月27日 下午4:29:04
 * @version
 */
public interface GoodsProductService {
    /**
     * 根据商品ID 获取商品对象
     * 
     * @param goodsId
     * @return
     */
    Goods selectGoodsByGoodsId(Long goodsId);

    /***
     * 更新关注商品对象
     * 
     * @return
     */
    int updateFollow(CustomerFollow customerFollow);

    /**
     * 根据商品ID查询货品信息列表
     * 
     * @param goodsId
     *            商品ID {@link java.lang.Long}
     * @param pb
     *            封装分页参数的PageBean {@link com.ningpai.util.PageBean}
     * @return {@link com.ningpai.util.PageBean}
     */
    PageBean queryByGoodsId(Long goodsId, PageBean pb, SelectBean selectBean);

    /**
     * 根据商品ID查询货品信息列表 新版boss更改
     * 
     * @param goodsId
     *            商品ID {@link java.lang.Long}
     * @param pb
     *            封装分页参数的PageBean {@link com.ningpai.util.PageBean}
     * @return {@link com.ningpai.util.PageBean}
     */
    PageBean queryByGoodsIdNew(Long goodsId, PageBean pb, SelectBean selectBean);

    /**
     * 删除货品信息
     * 
     * @param productId
     *            货品ID {@link java.lang.Long}
     * @param username
     *            操作人名称
     * @return 删除的行数
     */
    int delProductByProductId(Long productId, String username);

    /**
     * 删除第三方单个货品信息
     *
     * @param productId
     * @param thirdId
     * @param username
     * @return
     */
    int delThirdProductByProductId(Long productId, Long thirdId, String username);

    /**
     * 批量删除货品
     * 
     * @param products
     *            待删除的货品ID的集合
     * @param username
     *            操作人名称
     * @return 删除的行数
     */
    int batchDelProduct(Long[] products, String username);

    /**
     * 批量上下架货品
     * 
     * @param username
     *            操作人名称
     * @param productIds
     *            待修改的货品的ID数组
     * @param status
     *            上架状态 0：下架 1：上架
     * @return 更新的行数
     */
    int batchUploadProduct(String username, Long[] productIds, Integer status);

    /**
     * 批量显示或者隐藏在列表页
     * 
     * @param username
     *            操作人名称
     * @param productIds
     *            货品ID数组
     * @param status
     *            显示状态 0:隐藏 1:显示
     * @return 更新的行数
     */
    int batchShowOrHide(String username, Long[] productIds, Integer status);

    /**
     * 批量显示或隐藏到手机版
     * 
     * @param username
     *            操作人名称
     * @param productIds
     *            货品ID数组
     * @param status
     *            显示状态 0:隐藏 1:显示
     * @return 更新的行数
     */
    int batchShowOrHideMobile(String username, Long[] productIds, Integer status);

    /**
     * 添加货品信息
     * 
     * @param product
     *            货品实体 {@link com.ningpai.goods.bean.GoodsProduct}
     * @param username
     *            操作人名称
     * @return 插入的货品ID
     */
    int saveProduct(GoodsProduct product, String username, String[] specIds,
            String[] specDetailIds, Map<String, Object> map);

    /**
     * 根据主键查询ProductVo
     * 
     * @param productId
     *            货品信息的主键{@link java.lang.Long}
     * @return 查询到的货品VO信息 {@link com.ningpai.goods.vo.GoodsProductVo}
     */
    GoodsProductVo queryByPrimaryId(Long productId);

    /**
     * 根据主键查询货品适配车型
     *
     * @param productId
     *            货品信息的主键{@link java.lang.Long}
     * @return 查询到的货品VO信息 {@link com.ningpai.goods.vo.GoodsProductVo}
     */
    GoodsProduct queryProductAutoStyleByProductId(Long productId);

    /**
     * 更新货品信息
     * 
     * @param goodsProduct
     *            待更新的货品实体 {@link com.ningpai.goods.bean.GoodsProduct}
     * @param username
     *            操作人名称
     * @param specId
     *            规格ID的数组
     * @param specDetailIds
     *            规格值得ID数组
     * @return 更新影响的行数
     */
    int updateProduct(GoodsProduct goodsProduct, String username,
            String[] specId, String[] specDetailIds, String[] specRemarks);

    /**
     * 更新货品适配车型信息
     *
     * @param goodsInfoId
     *            待更新的货品实体 {@link com.ningpai.goods.bean.GoodsProduct}
     * @param autoStyleIdLiYangID
     *            力扬ID
     * @param goodsInfoAutoStyle
     *            力扬压力ID
     * @return 更新影响的行数
     */
    int updateProductAutoStyle(String goodsInfoId, String autoStyleIdLiYangID,String goodsInfoAutoStyle);

    /**
     * 根据PageBean查询货品详细信息
     * 
     * @param pb
     *            PageBean {@link com.ningpai.util.PageBean}
     * @return pageBean {@link com.ningpai.util.PageBean}
     */
    PageBean queryProductDetailInfoByPageBean(Long groupId, PageBean pb);

    /**
     * 根据货品ID查询货品预览页的Vo
     * 
     * @param productId
     *            货品ID {@link java.lang.Long}
     * @return 查询到的Vo {@link com.ningpai.goods.vo.GoodsProductDetailViewVo}
     */
    GoodsProductDetailViewVo queryViewVoByProductId(Long productId);

    /**
     * 根据货品ID查询货品预览页的Vo
     *
     * @param productId
     *            货品ID {@link java.lang.Long}
     * @return 查询到的Vo {@link com.ningpai.goods.vo.GoodsProductDetailViewVo}
     */
    GoodsProductDetailViewVo simpleQueryViewVoByProductId(Long productId);

    /**
     * 验证货品编号是否可用
     * 
     * @param productNo
     *            待验证的货品编号 {@link java.lang.String}
     * @return 是否已经存在 true表示可用 false表示不可用
     */
    boolean checkProuctNo(String productNo);

    /**
     * 验证所选的参数是否已经生成了货品
     * 
     * @param map
     *            参数 {@link java.util.Map}
     * @return 可用返回true 不可用返回false
     */
    boolean checkProductParams(Map<String, Object> map);

    /**
     * 查询第三方货品
     *
     * @param pb
     * @return pageBean
     * */
    PageBean queryThirdProduct(PageBean pb, Map<String, Object> map);

    /**
     * 根据分类ID和品牌Id查询货品列表
     *
     * @param pb
     *            分页参数
     * @param catIds
     *            分类ID
     * @param brandIds
     *            品牌ID数组
     * @return 分页参数
     */
    PageBean queryProductForCouponLife(PageBean pb, String[] catIds,
            String[] brandIds, Long thirdId, String productNo,
            String productName, Long catId, Long brandId, Integer haveStock,String flag);

    /**
     * 根据分类ID和品牌Id查询货品列表
     *
     * @param pb
     *            分页参数
     * @param catIds
     *            分类ID
     * @param brandIds
     *            品牌ID数组
     * @param productInfoName
     *            货品名称
     * @return 分页参数
     */
    PageBean queryProductForCoupon(PageBean pb, String[] catIds,
            String[] brandIds, String productInfoName, Long thirdId,
            String productNo);

    /**
     * 根据分类ID和品牌Id查询货品列表
     *
     * @param marketType
     *            促销编号
     *
     * @param sTime
     *            开始时间
     * @param eTime
     *            结束时间
     * @return 分页参数
     */
    PageBean queryProductForCoupon(PageBean pb, String[] catIds,
            String[] brandIds, Long thirdId, String productNo, Long marketType,
            String sTime, String eTime, String searchText);

    /**
     * 查询库存预警额货品个数
     * 
     * @return
     */
    int queryStockWarnCount(Integer flag, SelectBean selectBean);

    /**
     * 根据参数查询货品的列表并返回PageBean
     */
    PageBean queryProductListBySomeParam(Integer flag, PageBean pb,
            SelectBean selectBean);

    /**
     * 根据商品Id和查询参数查询货品列表for 导出数据
     * 
     * @param goodsId
     *            商品ID
     * @param selectBean
     *            查询帮助Bean
     * @return 查询到的集合
     */
    List<Object> queryAllProductByGoodsIdForExport(Long goodsId,
            SelectBean selectBean);

    /**
     * 根据货品ID数组查询货品集合
     * 
     * @param productIds
     *            货品ID的数组
     * @return 查询到的集合
     */
    List<Object> queryAllProductByProductIdsForExport(Long[] productIds);

    /**
     * 查询今天上架的商品
     * 
     * @param thirdId
     *            第三方标记{@link Long}
     * @return 查询到的数量 {@link Integer}
     */
    int queryTodayProCount(Long thirdId);

    /**
     * 
     * @param pb
     *            分页bean
     * @param goodsInfoIds
     *            商品id数组
     * @return 查询到的集合
     * @author NINGPAI-LIH
     */
    PageBean queryProductForCouponByGoodsInfoIds(PageBean pb,
            Long[] goodsInfoIds);

    /**
     * 根据分类ID和品牌Id查询货品列表 查询到的是boss的商品
     * 
     * @param pb
     *            分页参数
     * @param catIds
     *            分类ID
     * @param brandIds
     *            品牌ID数组
     * @return 分页参数
     * 
     * @author NINGPAI-LIH
     */
    PageBean queryProductForCouponByThird(PageBean pb, String[] catIds,
            String[] brandIds, Long thirdId, Long productNo);

    /**
     * 减少库存
     * 
     * @param list
     *            计算库存辅助Bean集合
     * @return 更新的行数
     */
    int minStock(List<CalcStockUtil> list);

    /**
     * 增加库存
     * 
     * @param list
     *            计算库存辅助Bean集合
     * @return 更新的行数
     */
    int plusStock(List<CalcStockUtil> list);

    /**
     * 货品审核通过
     * 
     * @param goodsInfoId
     * @return
     */
    int auditProductAction(Long goodsInfoId);

    /**
     * 拒绝审核通过
     * 
     * @param goodsInfoId
     * @return
     */
    int refuseAuditProductAction(Long goodsInfoId, String refuseReason);

    /**
     * 获取货品审核信息
     * 
     * @param goodsId
     * @param pb
     * @param selectBean
     * @return
     */
    PageBean queryAuditByGoodsId(Long goodsId, PageBean pb,
            SelectBean selectBean);

    /**
     * 批量审核上架
     * 
     * @param username
     * @param productIds
     * @param status
     * @param auditStatus
     * @return
     */
    int batchAuditUploadProduct(String username, Long[] productIds,
            Integer status, String auditStatus);

    /**
     * 查询货品销售排行
     * 
     * @param startTime
     * @param endTime
     * @return
     */
    PageBean queryGoodsProductSalesRank(PageBean pageBean, String startTime,
            String endTime);

    /**
     * 供应商上传货品
     * 
     * @param product
     * @param username
     * @param specIds
     * @param specDetailIds
     * @param map
     * @return
     */
    int saveSupplierProduct(GoodsProduct product, String username,
            String[] specIds, String[] specDetailIds, Map<String, Object> map,
            Long thirdId);

    /**
     * 根据map获取货品信息
     * 
     * @param map
     * @return
     */
    GoodsProduct queryProductById(Map<String, Object> map);

    /**
     * 根据productId获取货品信息
     * 
     * @param productId
     * @return
     */
    GoodsProduct queryProductByGoodsId(Long productId);

    /**
     * 根据商品id查询所有货品信息
     * 
     * @param productId
     * @return
     */
    List<GoodsProduct> queryProductsByGoodsId(Long productId);

    /**
     * 同步修改
     * 
     * @param goodsInfoId
     * @param goodsSubtitle
     * @return
     */
    int updateGoodsSubtitleById(Long goodsInfoId, String goodsSubtitle);

    /**
     * 根据商品id，查询货品详细列表
     * 
     * @param goodsId
     *            商品id
     * @return
     */
    List<GoodsProductVo> queryProductListByGoodsId(Long goodsId);

    /**
     * 查询第三方货品列表
     * 
     * @param thirdId
     *            第三方商家Id
     * @return list
     */
    List<GoodsProductDetailVo> queryProductForMarketing(Long thirdId);

    /**
     * 新查询货品列表 分页
     * 
     * @param pb
     *            PageBean{@link com.ningpai.util.PageBean}
     * @param thirdId
     *            店铺ID{@link java.lang.Long}
     * @param searchBean
     *            查询条件对象{@link com.ningpai.goods.util.GoodsSearchBean}
     * @return pb
     */
    PageBean newQueryProductList(PageBean pb, Long thirdId,
            GoodsSearchBean searchBean,BigDecimal offValue);

    /**
     * 
     * @param pb
     * @param thirdId
     * @param searchBean
     * @return
     */
    PageBean queryBossProductList(PageBean pb, Long thirdId,
            GoodsSearchBean searchBean, Long marketType);

    /**
     * 查询所有ID
     * 
     * @param goodsIds
     * @return List
     */
    List<Long> selectInfoIdList(Long[] goodsIds);

    /**
     * 删除购物车的货品
     *
     * @param goodsIds 货品编号
     * @return int
     */
    int  delShoppingGoodsByGoodsInfoIds(List<Long> goodsIds);

}
