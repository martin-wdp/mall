package com.ningpai.goods.dao;

import com.ningpai.customer.bean.CustomerFollow;
import com.ningpai.goods.bean.Goods;
import com.ningpai.goods.bean.GoodsProduct;
import com.ningpai.goods.vo.GoodsProductDetailViewVo;
import com.ningpai.goods.vo.GoodsProductDetailVo;
import com.ningpai.goods.vo.GoodsProductVo;

import java.util.List;
import java.util.Map;

/**
 * 货品信息DAO
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月27日 下午2:57:36
 * @version 1.0
 */
public interface GoodsProductMapper {
    /**
     * 根据主键获取商品信息
     * 
     * @param goodsId
     * @return
     */
    Goods selectGoodsByGoodsId(Long goodsId);

    /***
     * 根据ID删除关注信息
     * 
     * @param followId
     * @return
     */
    int deleteFolloById(Long followId);
    /***
     * 根据ID删除关注信息
     *
     * @param param
     * @return
     */
    int deleteFolloById(Map<String,Object> param);

    /**
     * 根据商品ID查询关注表对象集合
     * 
     * @param goods
     * @return
     */
    List<CustomerFollow> selectFollowByGoods(Long goods);

    /**
     * 查询上线第三方商品
     * 
     * @param map
     * @return
     */
    List<Object> queryThirdProduct(Map<String, Object> map);

    /**
     * 修改商品关注表
     * 
     * @param customerFollow
     * @return
     */
    int updateFollow(CustomerFollow customerFollow);

    /**
     * 删除货品信息
     * 
     * @param map
     *            删除的参数
     * @return 删除的行数{@link java.lang.Integer}
     */
    int deleteByPrimaryKey(Map<String, String> map);

    /**
     * 删除第三方货品信息
     * 
     * @param map
     * @return
     */
    int deleteThirdProductByPrimaryKey(Map<String, String> map);

    /**
     * 插入记录(可选属性 推荐)
     * 
     * @param record
     *            待插入的实体{@link com.ningpai.goods.bean.GoodsProduct}
     * @return 插入的行数{@link java.lang.Integer}
     */
    int insertSelective(GoodsProduct record);

    /**
     * 根据主键查询
     * 
     * @param goodsInfoId
     *            主键{@link java.lang.Long}
     * @return 查询到的实体{@link com.ningpai.goods.bean.GoodsProduct}
     */
    GoodsProductVo selectByPrimaryKey(Long goodsInfoId);

    /**
     * 更新信息(可选属性 推荐)
     * 
     * @param record
     *            {@link com.ningpai.goods.bean.GoodsProduct}
     * @return 更新的行数{@link java.lang.Integer}
     */
    int updateByPrimaryKeySelective(GoodsProduct record);

    /**
     * 根据商品ID和分页参数查询
     * 
     * @param map
     *            封装了商品ID和分页参数的Map
     * @return 查询到的列表
     */
    List<Object> queryProductByGoodsId(Map<String, Object> map);

    /**
     * 根据商品ID和分页参数查询 新版boss更改
     * 
     * @param map
     *            封装了商品ID和分页参数的Map
     * @return 查询到的列表
     */
    List<Object> queryProductByGoodsIdNew(Map<String, Object> map);

    /**
     * 查询所有的行数
     * 
     * @param goodsId
     *            商品ID {@link java.lang.Long}
     * @return 查询到的所有的行数 {@link java.lang.Integer}
     */
    int queryTotalCountByGoodsId(Long goodsId);

    /**
     * 查询所有的行数
     * 
     * @return 查询到的行数{@link java.lang.Integer}
     */
    int queryTotalCount();

    /**
     * 批量上下架货品
     * 
     * @param map
     *            封装的参数{@link java.util.Map}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    int batchUploadProduct(Map<String, Object> map);

    /**
     * 批量显示或者隐藏到列表页
     * 
     * @param map
     *            参数 {@link java.util.Map}
     * @return 受影响的行数 {@link Integer}
     */
    int batchShow(Map<String, Object> map);

    /**
     * 批量显示或隐藏到手机版
     * 
     * @param map
     *            参数
     * @return 受影响的行数
     */
    int batchShowMobile(Map<String, Object> map);

    /**
     * 查询最新插入的主键ID
     * 
     * @return 查询到的最新的ID
     */
    int selectLastId();

    /**
     * 根据参数查询货品详细Vo的集合
     * 
     * @param map
     *            封装参数的Map {@link java.util.Map}
     * @return 查询到的List {@link java.util.List}
     */
    List<Object> queryProductListByPageBean(Map<String, Long> map);

    /**
     * 根据商品ID删除货品信息
     * 
     * @param map
     *            参数
     * @return 删除的行数
     */
    Long delProductWithGoodsId(Map<String, String> map);

    /**
     * 根据商品ID删除第三方货品信息
     * 
     * @param map
     * @return
     */
    Long delThirdProductWithGoodsId(Map<String, String> map);

    /**
     * 修改商品为下架状态的时候把货品信息页修改为下架状态
     * 
     * @param map
     * @return
     */
    Long updateProductAddedWithGoodsId(Map<String, String> map);

    /**
     * 批量审核上架商品时把货品也设置审核
     * 
     * @param param
     * @return
     */
    Long updateProductAddedWithAudit(Map<String, String> param);

    /**
     * 根据商品组合的ID查询货品的行数
     * 
     * @param map
     * @return
     */
    Integer queryTotalCountWithGroupId(Map<String, Long> map);

    /**
     * 根据货品Id查询
     * 
     * @param map
     *            货品ID {@link }
     * @return 查询到的实体 {@link com.ningpai.goods.vo.GoodsProductDetailViewVo}
     */
    GoodsProductDetailViewVo queryByProductId(Map<String, Object> map);

    /**
     * 根据货品Id查询
     *
     * @param map
     *            货品ID {@link }
     * @return 查询到的实体 {@link com.ningpai.goods.vo.GoodsProductDetailViewVo}
     */
    GoodsProductDetailViewVo simpleQueryByProductId(Map<String, Object> map);

    /**
     * 根据货品编号查询行数
     * 
     * @param productNo
     *            待查询的货品编号 {@link java.lang.String}
     * @return 查询到的行数 {@link java.lang.Integer}
     */
    int queryCountByProductNo(String productNo);

    /**
     * 根据参数查询货品是否存在
     * 
     * @param map
     *            参数 {@link java.util.Map}
     * @return 查询到的行数 {@link java.lang.Integer}
     */
    int queryCountByParam(Map<String, Object> map);

    /**
     * 根据参数查询货品的列表
     * 
     * @param map
     *            封装参数
     * @return 查询到的列表
     */
    List<Object> queryProductForCoupon(Map<String, Object> map);

    /**
     * 根据参数查询货品的列表 团购抢购
     *
     * @param map
     *            封装参数
     * @return 查询到的列表
     */
    List<Object> queryMarketingProduct(Map<String, Object> map);

    /**
     * 根据参数查询货品的数量 团购抢购
     *
     * @param map
     *            封装参数
     * @return 查询到的列表
     */
    int queryMarketingProductCount(Map<String, Object> map);

    /**
     * 根据参数查询行数
     * 
     * @param map
     *            参数集合
     * @return 查询到的行数
     */
    int queryCountForCoupon(Map<String, Object> map);
    /**
     * 新第三方查询上线商品个数
     */
    int queryCountThirdProduct(Map<String, Object> map);

    /**
     * 查询库存报警的行数
     * 
     * @return 查询到的行数 {@link java.lang.Integer}
     */
    int queryStockWarnCount(Map<String, Object> map);

    /**
     * 根据参数查询货品列表
     * 
     * @param map
     * @return
     */
    List<Object> queryProductListBySomeParam(Map<String, Object> map);

    /**
     * 根据商品ID和查询参数查询货品数量
     * 
     * @param map
     *            参数
     * @return 查询到的行数
     */
    int queryCountByGoodsAndSelectParam(Map<String, Object> map);

    /**
     * 根据货品ID的数组查询货品集合
     * 
     * @param map
     *            封装的货品ID数组
     * @return 查询到的货品集合
     */
    List<Object> queryProductsByProductIds(Map<String, Object> map);

    /**
     * 根据货品ID查询货品适配车型
     *
     * @param map
     *            封装的货品ID
     * @return 查询到的货品适配车型集合
     */
    GoodsProduct queryProductAutoStyleByProductId(Map<String, Object> map);

    /**
     * 根据货品ID的数组查询货品集合
     * 
     * @param map
     * @return
     */
    List<GoodsProductVo> queryGoodsProductVoByProductIds(Map<String, Object> map);

    /**
     * 根据参数查询数量
     * 
     * @param map
     *            参数Map {@link Map}
     * @return 查询到的数量 {@link Integer}
     */
    int queryTodayProCount(Map<String, Object> map);

    /**
     * 根据商品编号数组查询数量
     * 
     * @param map
     *            参数Map {@link Map}
     * @return 查询到的数量 {@link Integer}
     * @author NINGPAI-LIH
     */
    int queryCountForCouponByGoodsInfoIds(Map<String, Object> map);

    /**
     * 根据商品编号数组进行查询
     * 
     * @param map
     *            参数Map {@link Map}
     * @return 查询到的集合{@link List}
     * @author NINGPAI-LIH
     */
    List<Object> queryProductForCouponByGoodsInfoIds(Map<String, Object> map);

    /**
     * 根据参数查询货品的列表
     * 
     * @param map
     *            封装参数
     * @return 查询到的列表
     * @author NINGPAI-LIH
     */
    List<Object> queryProductForCouponByThird(Map<String, Object> map);

    /**
     * 减货品基本库存
     * 
     * @return 更新的行数
     */
    int minBaseStock(Map<String, Object> map);

    /**
     * 释放货品基本库存
     * 
     * @return 更新的行数
     */
    int plusBaseStock(Map<String, Object> map);

    /**
     * 减仓库库存
     * 
     * @return 更新的行数
     */
    int minStockToWare(Map<String, Object> map);

    /**
     * 释放仓库库存
     * 
     * @return 更新的行数
     */
    int plusStockToWare(Map<String, Object> map);

    /**
     * 根据商品id查询货品列表
     * 
     * @param goodsId
     *            商品id
     * @return 货品列表
     * @author NINGPAI-LIH
     */
    List<GoodsProductVo> queryProductListByGoodsId(Long goodsId);

    /**
     * 根据货品id查询货品详细信息
     * 
     * @param goodsInfoId
     *            货品id
     * @return 货品详细信息
     * @author NINGPAI-LIH
     */
    GoodsProduct selectByGoodsInfoId(Long goodsInfoId);

    /**
     * 查询该货品的详细信息
     *
     * @param goodsInfoId
     * @return
     */
    GoodsProduct queryByGoodsInfoDetail(Long goodsInfoId);

    /**
     * 根据仓库标识和货品编号减少库存
     * 
     * @param map
     *            stock:要减去的库存 goodsInfoItemNo:货品编号 identifyId 标识id
     * @return
     */
    int minStockToWareByIdentity(Map<String, Object> map);

    /**
     * 根据仓库标识和货品编号添加库存
     * 
     * @param map
     *            stock:要减去的库存 goodsInfoItemNo:货品编号 identifyId 标识id
     * @return
     */
    int plusStockToWareByIdentity(Map<String, Object> map);

    /**
     * 根据货品编号查询库存信息
     * 
     * @param goodsInfoItemNo
     * @return
     */
    GoodsProduct selectByGoodsInfoItemNo(String goodsInfoItemNo);

    /**
     * 查询全部商品信息
     * 
     * @return
     */
    List<GoodsProduct> selectAll();

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
     * @param
     * @return
     */
    int refuseAuditProductAction(Map<String, Object> map);

    /**
     * 根据商品ID和分页参数查询审核货品
     * 
     * @param map
     * @return
     */
    List<Object> queryAuditProductByGoodsId(Map<String, Object> map);

    /**
     * 批量审核上架
     * 
     * @param map
     * @return
     */
    int batchAuditUploadProduct(Map<String, Object> map);

    /**
     * 查询货品销售排行
     * 
     * @param map
     * @return
     */
    List<Object> queryGoodsProductSalesRank(Map<String, Object> map);

    /**
     * 查询总数
     * 
     * @param paraMap
     * @return
     */
    int selectAllSize(Map<String, Object> paraMap);

    /**
     * 根据商品Id查询货品Id
     * 
     * @param goodsId
     * @return
     */
    List<GoodsProduct> queryGoodsInfoIdByGoodsId(Long goodsId);

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
     * 根据商品id获取所有货品信息
     * 
     * @param goodsId
     * @return
     */
    List<GoodsProduct> queryProductsByGoodsId(Long goodsId);

    /**
     * 同步修改货品副标题
     * 
     * @param map
     * @return
     */
    int updateGoodsSubtitleById(Map<String, Object> map);

    /**
     * 查询货品list
     * 
     * @return list
     */
    List<GoodsProductDetailVo> queryProductForMarketing(Map<String, Object> map);

    /**
     * 新查询货品列表
     * 
     * @param map
     *            查询条件{@link java.util.Map}
     * @return pb
     */
    List<Object> newQueryProductForMarketing(Map<String, Object> map);

    /**
     * 查询货品数目
     * 
     * @param map
     *            查询条件{@link java.util.Map}
     * @return int
     */
    int newQueryProductForMarketingCount(Map<String, Object> map);

    /**
     * 退款成功返还库存
     * 
     * @param map
     * @return
     */
    int addBaseStock(Map<String, Object> map);

    /**
     * 查询IDS
     * 
     * @param list
     * @return List
     */
    List<Long> selectInfoIdList(List<Long> list);
    /**
     * 删除购物车的货品
     *
     * @param goodsIds 货品编号
     * @return int
     */
    int delShoppingGoodsByGoodsInfoIds(List<Long> goodsIds);

    /**
     * 更新适配车型(力扬ID和力扬压缩ID)
     * @param param
     * @return
     */
    int updateAutoStyleByGoodsInfoId(Map<String,Object> param);

}
