package com.ningpai.goods.dao.impl;

import com.ningpai.customer.bean.CustomerFollow;
import com.ningpai.goods.bean.Goods;
import com.ningpai.goods.bean.GoodsProduct;
import com.ningpai.goods.dao.GoodsProductMapper;
import com.ningpai.goods.vo.GoodsMoifiedVo;
import com.ningpai.goods.vo.GoodsProductDetailViewVo;
import com.ningpai.goods.vo.GoodsProductDetailVo;
import com.ningpai.goods.vo.GoodsProductVo;
import com.ningpai.manager.base.BasicSqlSupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 货品信息DAO实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月27日 下午3:36:18
 * @version 1.0
 */
@Repository("GoodsProductMapper")
public class GoodsProductMapperImpl extends BasicSqlSupport implements
        GoodsProductMapper {
    /**
     * 删除货品信息
     *
     * @param map
     *            删除的参数
     * @return 删除的行数{@link java.lang.Integer}
     */
    public int deleteByPrimaryKey(Map<String, String> map) {
        return this.update(
                "com.ningpai.goods.dao.GoodsProductMapper.deleteByPrimaryKey",
                map);
    }

    /**
     * 删除第三方货品信息
     * 
     * @param map
     * @return
     */
    public int deleteThirdProductByPrimaryKey(Map<String, String> map) {
        return this
                .update("com.ningpai.goods.dao.GoodsProductMapper.deleteThirdProductByPrimaryKey",
                        map);
    }

    /**
     * 插入记录(可选属性 推荐)
     *
     * @param record
     *            待插入的实体{@link com.ningpai.goods.bean.GoodsProduct}
     * @return 插入的行数{@link java.lang.Integer}
     */
    public int insertSelective(GoodsProduct record) {
        return this.insert(
                "com.ningpai.goods.dao.GoodsProductMapper.insertSelective",
                record);
    }

    /**
     * 根据主键查询
     *
     * @param goodsInfoId
     *            主键{@link java.lang.Long}
     * @return 查询到的实体{@link com.ningpai.goods.bean.GoodsProduct}
     */
    public GoodsProductVo selectByPrimaryKey(Long goodsInfoId) {
        return this.selectOne(
                "com.ningpai.goods.dao.GoodsProductMapper.selectByPrimaryKeys",
                goodsInfoId);
    }

    /**
     * 更新信息(可选属性 推荐)
     *
     * @param record
     *            {@link com.ningpai.goods.bean.GoodsProduct}
     * @return 更新的行数{@link java.lang.Integer}
     */
    public int updateByPrimaryKeySelective(GoodsProduct record) {
        return this
                .update("com.ningpai.goods.dao.GoodsProductMapper.updateByPrimaryKeySelective",
                        record);
    }

    /**
     * 
     * 新版》店铺商品列表》查看商品页面搜索
     * 
     * @see
     * .Map)
     */
    public List<Object> queryProductByGoodsId(Map<String, Object> map) {
        return this
                .selectList(
                        "com.ningpai.goods.dao.GoodsProductMapper.queryProductByGoodsId",
                        map);
    }

    /**
     * 根据商品ID和分页参数查询 新版boss更改
     * 
     * @param map
     *            封装了商品ID和分页参数的Map
     * @return 查询到的列表
     */
    public List<Object> queryProductByGoodsIdNew(Map<String, Object> map) {
        return this
                .selectList(
                        "com.ningpai.goods.dao.GoodsProductMapper.queryProductByGoodsIdNew",
                        map);
    }

    /**
     * 查询所有的行数
     *
     * @param goodsId
     *            商品ID {@link java.lang.Long}
     * @return 查询到的所有的行数 {@link java.lang.Integer}
     */
    public int queryTotalCountByGoodsId(Long goodsId) {
        return this
                .selectOne(
                        "com.ningpai.goods.dao.GoodsProductMapper.queryTotalCountByGoodsId",
                        goodsId);
    }

    /**
     * 批量上下架货品
     *
     * @param map
     *            封装的参数{@link java.util.Map}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    public int batchUploadProduct(Map<String, Object> map) {
        return this.update(
                "com.ningpai.goods.dao.GoodsProductMapper.batchUploadProduct",
                map);
    }

    /**
     * 查询最新插入的主键ID
     *
     * @return 查询到的最新的ID
     */
    public int selectLastId() {
        return this
                .selectOne("com.ningpai.goods.dao.GoodsProductMapper.selectLastId");
    }

    /**
     * 根据参数查询货品详细Vo的集合
     *
     * @param map
     *            封装参数的Map {@link java.util.Map}
     * @return 查询到的List {@link java.util.List}
     */
    public List<Object> queryProductListByPageBean(Map<String, Long> map) {
        return this
                .selectList(
                        "com.ningpai.goods.dao.GoodsProductMapper.queryProductListByPageBean",
                        map);
    }

    /**
     * 查询所有的行数
     *
     * @return 查询到的行数{@link java.lang.Integer}
     */
    public int queryTotalCount() {
        return this
                .selectOne("com.ningpai.goods.dao.GoodsProductMapper.queryTotalCount");
    }

    /**
     * 根据商品ID删除货品信息
     *
     * @param map
     *            参数
     * @return 删除的行数
     */
    public Long delProductWithGoodsId(Map<String, String> map) {
        return (long) this
                .delete("com.ningpai.goods.dao.GoodsProductMapper.delProductWithGoodsId",
                        map);
    }

    /**
     * 根据商品ID删除第三方货品信息
     * 
     * @param map
     * @return
     */
    public Long delThirdProductWithGoodsId(Map<String, String> map) {
        return (long) this
                .delete("com.ningpai.goods.dao.GoodsProductMapper.delThirdProductWithGoodsId",
                        map);
    }

    /**
     * 修改商品为下架状态的时候把货品信息页修改为下架状态
     *
     * @param map
     * @return
     */
    public Long updateProductAddedWithGoodsId(Map<String, String> map) {
        return (long) this
                .delete("com.ningpai.goods.dao.GoodsProductMapper.updateProductAddedWithGoodsId",
                        map);
    }

    /**
     * 批量审核上架商品时把货品也设置审核
     * 
     * @param param
     * @return
     */
    public Long updateProductAddedWithAudit(Map<String, String> param) {
        return (long) this
                .update("com.ningpai.goods.dao.GoodsProductMapper.updateProductAddedWithAudit",
                        param);
    }

    /**
     * 根据商品组合的ID查询货品的行数
     *
     * @param map
     * @return
     */
    public Integer queryTotalCountWithGroupId(Map<String, Long> map) {
        return this
                .selectOne(
                        "com.ningpai.goods.dao.GoodsProductMapper.queryTotalCountWithGroupId",
                        map);
    }

    /**
     * 根据货品Id查询
     *
     * @param map
     *            货品ID {@link }
     * @return 查询到的实体 {@link com.ningpai.goods.vo.GoodsProductDetailViewVo}
     */
    public GoodsProductDetailViewVo queryByProductId(Map<String, Object> map) {
        return this
                .selectOne(
                        "com.ningpai.goods.dao.GoodsProductMapper.queryViewVoByProductId",
                        map);
    }


    /**
     * 根据货品Id查询
     *
     * @param map
     *            货品ID {@link }
     * @return 查询到的实体 {@link com.ningpai.goods.vo.GoodsProductDetailViewVo}
     */
    public GoodsProductDetailViewVo simpleQueryByProductId(Map<String, Object> map) {
        GoodsProductDetailViewVo goodsProductDetailViewVo =  this
                .selectOne(
                        "com.ningpai.goods.dao.GoodsProductMapper.queryViewVoByProductIdSimple",
                        map);
        GoodsMoifiedVo goodsMoifiedVo =
                this.selectOne("com.ningpai.goods.dao.GoodsMapper.querySimpleModeifiedVoByGoodsId",goodsProductDetailViewVo.getGoodsId());
        goodsProductDetailViewVo.setGoods(goodsMoifiedVo);
        return goodsProductDetailViewVo;
    }

    /**
     * 根据货品编号查询行数
     *
     * @param productNo
     *            待查询的货品编号 {@link java.lang.String}
     * @return 查询到的行数 {@link java.lang.Integer}
     */
    public int queryCountByProductNo(String productNo) {
        return this
                .selectOne(
                        "com.ningpai.goods.dao.GoodsProductMapper.queryCountByProductNo",
                        productNo);
    }

    /**
     * 根据参数查询货品是否存在
     *
     * @param map
     *            参数 {@link java.util.Map}
     * @return 查询到的行数 {@link java.lang.Integer}
     */
    public int queryCountByParam(Map<String, Object> map) {
        return this.selectOne(
                "com.ningpai.goods.dao.GoodsProductMapper.queryCountByParam",
                map);
    }

    /**
     * 根据参数查询货品的列表
     *
     * @param map
     *            封装参数
     * @return 查询到的列表
     */
    public List<Object> queryProductForCoupon(Map<String, Object> map) {
        return this
                .selectList(
                        "com.ningpai.goods.dao.GoodsProductMapper.queryProductForCoupon",
                        map);
    }

    /**
     * 根据参数查询货品的列表 团购抢购
     *
     * @param map
     *            封装参数
     * @return 查询到的列表
     */
    @Override
    public List<Object> queryMarketingProduct(Map<String, Object> map) {
        return this
                .selectList(
                        "com.ningpai.goods.dao.GoodsProductMapper.queryMarketingProduct",
                        map);
    }

    /**
     * 根据参数查询货品的数量 团购抢购
     *
     * @param map
     *            封装参数
     * @return 查询到的列表
     */
    @Override
    public int queryMarketingProductCount(Map<String, Object> map) {
        return this
                .selectOne(
                        "com.ningpai.goods.dao.GoodsProductMapper.queryMarketingProductCount",
                        map);
    }

    /**
     * 根据参数查询行数
     *
     * @param map
     *            参数集合
     * @return 查询到的行数
     */
    public int queryCountForCoupon(Map<String, Object> map) {
        return this.selectOne(
                "com.ningpai.goods.dao.GoodsProductMapper.queryCountForCoupon",
                map);
    }

    /**
     * 新第三方查询上线商品个数
     */
    @Override
    public int queryCountThirdProduct(Map<String, Object> map) {
        return this
                .selectOne(
                        "com.ningpai.goods.dao.GoodsProductMapper.queryCountThirdProduct",
                        map);
    }

    /**
     * 新第三方查询上线商品分页
     * 
     * @param map
     * @return
     */
    @Override
    public List<Object> queryThirdProduct(Map<String, Object> map) {
        return this.selectList(
                "com.ningpai.goods.dao.GoodsProductMapper.queryThirdProduct",
                map);
    }

    /**
     * 查询库存报警的行数
     *
     * @return 查询到的行数 {@link java.lang.Integer}
     */
    public int queryStockWarnCount(Map<String, Object> map) {
        return this.selectOne(
                "com.ningpai.goods.dao.GoodsProductMapper.queryStockWarnCount",
                map);
    }

    /**
     * 根据参数查询货品列表
     *
     * @param map
     * @return
     */
    public List<Object> queryProductListBySomeParam(Map<String, Object> map) {
        return this
                .selectList(
                        "com.ningpai.goods.dao.GoodsProductMapper.queryProductListBySomeParam",
                        map);
    }

    /**
     * 根据商品ID和查询参数查询货品数量
     *
     * @param map
     *            参数
     * @return 查询到的行数
     */
    public int queryCountByGoodsAndSelectParam(Map<String, Object> map) {
        return this
                .selectOne(
                        "com.ningpai.goods.dao.GoodsProductMapper.queryCountByGoodsAndSelectParam",
                        map);
    }

    /**
     * 根据商品编号数组查询数量
     *
     * @param map
     *            参数Map {@link Map}
     * @return 查询到的数量 {@link Integer}
     * @author NINGPAI-LIH
     */
    @Override
    public int queryCountForCouponByGoodsInfoIds(Map<String, Object> map) {
        return this
                .selectOne(
                        "com.ningpai.goods.dao.GoodsProductMapper.queryCountForCouponByGoodsInfoIds",
                        map);
    }

    /**
     * 根据商品编号数组进行查询
     *
     * @param map
     *            参数Map {@link Map}
     * @return 查询到的集合{@link List}
     * @author NINGPAI-LIH
     */
    @Override
    public List<Object> queryProductForCouponByGoodsInfoIds(
            Map<String, Object> map) {
        return this
                .selectList(
                        "com.ningpai.goods.dao.GoodsProductMapper.queryProductForCouponByGoodsInfoIds",
                        map);
    }

    /**
     * 根据货品ID的数组查询货品集合
     *
     * @param map
     *            封装的货品ID数组
     * @return 查询到的货品集合
     */
    public List<Object> queryProductsByProductIds(Map<String, Object> map) {
        return this
                .selectList(
                        "com.ningpai.goods.dao.GoodsProductMapper.queryProductsByProductIds",
                        map);
    }
    /**
     * 根据货品ID查询货品适配车型
     *
     * @param map
     *            封装的货品ID
     * @return 查询到的货品适配车型集合
     */
    public GoodsProduct queryProductAutoStyleByProductId(Map<String, Object> map) {
        return this
                .selectOne(
                        "com.ningpai.goods.dao.GoodsProductMapper.queryProductAutoStyleByProductId",
                        map);
    }
    /**
     * 根据货品ID的数组查询货品集合
     *
     * @param map
     * @return
     */
    public List<GoodsProductVo> queryGoodsProductVoByProductIds(
            Map<String, Object> map) {
        return this
                .selectList(
                        "com.ningpai.goods.dao.GoodsProductMapper.queryProductsByProductIds",
                        map);
    }

    /**
     * 根据参数查询数量
     *
     * @param map
     *            参数Map {@link Map}
     * @return 查询到的数量 {@link Integer}
     */
    public int queryTodayProCount(Map<String, Object> map) {
        return this.selectOne(
                "com.ningpai.goods.dao.GoodsProductMapper.queryTodayProCount",
                map);
    }

    /**
     * 根据参数查询货品的列表
     *
     * @param map
     *            封装参数
     * @return 查询到的列表
     * @author NINGPAI-LIH
     */
    @Override
    public List<Object> queryProductForCouponByThird(Map<String, Object> map) {
        return this
                .selectList(
                        "com.ningpai.goods.dao.GoodsProductMapper.queryProductForCouponByThird",
                        map);
    }

    /**
     * 批量显示或者隐藏到列表页
     *
     * @param map
     *            参数 {@link java.util.Map}
     * @return 受影响的行数 {@link Integer}
     */
    public int batchShow(Map<String, Object> map) {
        return this.update(
                "com.ningpai.goods.dao.GoodsProductMapper.batchShow", map);
    }

    /**
     * 减货品基本库存
     *
     * @return 更新的行数
     */
    public int minBaseStock(Map<String, Object> map) {
        return this.update(
                "com.ningpai.goods.dao.GoodsProductMapper.minBaseStock", map);
    }

    /**
     * 释放货品基本库存
     *
     * @return 更新的行数
     */
    public int plusBaseStock(Map<String, Object> map) {
        return this.update(
                "com.ningpai.goods.dao.GoodsProductMapper.plusBaseStock", map);
    }

    /**
     * 减仓库库存
     *
     * @return 更新的行数
     */
    public int minStockToWare(Map<String, Object> map) {
        return this.update(
                "com.ningpai.goods.dao.ProductWareMapper.minStockToWare", map);
    }

    /**
     * 释放仓库库存
     *
     * @return 更新的行数
     */
    public int plusStockToWare(Map<String, Object> map) {
        return this.update(
                "com.ningpai.goods.dao.ProductWareMapper.plusStockToWare", map);
    }

    /**
     * 批量显示或隐藏到手机版
     *
     * @param map
     *            参数
     * @return 受影响的行数
     */
    public int batchShowMobile(Map<String, Object> map) {
        return this
                .update("com.ningpai.goods.dao.GoodsProductMapper.batchShowMobile",
                        map);
    }

    /**
     * 根据商品id查询货品列表
     *
     * @param goodsId
     *            商品id
     * @return 货品列表
     * @author NINGPAI-LIH
     */
    @Override
    public List<GoodsProductVo> queryProductListByGoodsId(Long goodsId) {
        return this
                .selectList(
                        "com.ningpai.goods.dao.GoodsProductMapper.queryProductListByGoodsId",
                        goodsId);
    }

    /**
     * 根据货品id查询货品详细信息
     *
     * @param goodsInfoId
     *            货品id
     * @return 货品详细信息
     * @author NINGPAI-LIH
     */
    @Override
    public GoodsProduct selectByGoodsInfoId(Long goodsInfoId) {
        return this.selectOne(
                "com.ningpai.goods.dao.GoodsProductMapper.selectByGoodsInfoId",
                goodsInfoId);
    }

    /**
     * 查询该货品的详细信息
     *
     * @param goodsInfoId
     * @return
     */
    @Override
    public GoodsProduct queryByGoodsInfoDetail(Long goodsInfoId) {
        return this
                .selectOne(
                        "com.ningpai.goods.dao.GoodsProductMapper.queryByGoodsInfoDetail",
                        goodsInfoId);
    }

    /**
     * 根据仓库标识和货品编号减少库存
     * 
     * @param map
     *            stock:要减去的库存 goodsInfoItemNo:货品编号 identifyId 标识id
     * @return
     */
    @Override
    public int minStockToWareByIdentity(Map<String, Object> map) {
        return this
                .update("com.ningpai.goods.dao.ProductWareMapper.minStockToWareByIdentity",
                        map);
    }

    /**
     * 根据仓库标识和货品编号添加库存
     * 
     * @param map
     *            stock:要减去的库存 goodsInfoItemNo:货品编号 identifyId 标识id
     * @return
     */
    @Override
    public int plusStockToWareByIdentity(Map<String, Object> map) {
        return this
                .update("com.ningpai.goods.dao.ProductWareMapper.plusStockToWareByIdentity",
                        map);
    }

    /**
     * 根据货品编号查询库存信息
     * 
     * @param goodsInfoItemNo
     * @return
     */
    @Override
    public GoodsProduct selectByGoodsInfoItemNo(String goodsInfoItemNo) {
        return this
                .selectOne(
                        "com.ningpai.goods.dao.GoodsProductMapper.selectByGoodsInfoItemNo",
                        goodsInfoItemNo);
    }

    /**
     * 查询全部商品信息
     * 
     * @return
     */
    @Override
    public List<GoodsProduct> selectAll() {
        return this
                .selectList("com.ningpai.goods.dao.GoodsProductMapper.selectAll");
    }

    /**
     * 修改商品关注表
     * 
     * @param customerFollow
     * @return
     */
    @Override
    public int updateFollow(CustomerFollow customerFollow) {
        return this.update(
                "com.ningpai.customer.dao.CustomerFollowMapper.updateFollow",
                customerFollow);
    }

    /**
     * 根据商品ID查询关注表对象集合
     * 
     * @return
     */
    @Override
    public List<CustomerFollow> selectFollowByGoods(Long goodsId) {
        return this
                .selectList(
                        "com.ningpai.customer.dao.CustomerFollowMapper.selectFollowByGoods",
                        goodsId);
    }

    /***
     * 根据ID删除关注信息
     * 
     * @param followId
     * @return
     */
    @Override
    public int deleteFolloById(Long followId) {
        return this
                .delete("com.ningpai.customer.dao.CustomerFollowMapper.deleteFolloById",
                        followId);
    }
    /***
     * 根据ID删除关注信息
     *
     * @param param
     * @return
     */
    @Override
    public int deleteFolloById(Map<String,Object> param) {
        return this
                .delete("com.ningpai.customer.dao.CustomerFollowMapper.deleteFolloByIdUId",
                        param);
    }

    /**
     * 货品审核通过
     * 
     * @param goodsInfoId
     * @return
     */
    @Override
    public int auditProductAction(Long goodsInfoId) {
        return this.update(
                "com.ningpai.goods.dao.GoodsProductMapper.auditProductAction",
                goodsInfoId);
    }

    /**
     * 拒绝审核通过
     * 
     * @param
     * @return
     */
    @Override
    public int refuseAuditProductAction(Map<String, Object> map) {
        return this
                .update("com.ningpai.goods.dao.GoodsProductMapper.refuseAuditProductAction",
                        map);
    }

    /**
     * 根据商品ID和分页参数查询审核货品
     * 
     * @param map
     * @return
     */
    @Override
    public List<Object> queryAuditProductByGoodsId(Map<String, Object> map) {
        return this
                .selectList(
                        "com.ningpai.goods.dao.GoodsProductMapper.queryAuditProductByGoodsId",
                        map);
    }

    /**
     * 批量审核上架
     * 
     * @param map
     * @return
     */
    @Override
    public int batchAuditUploadProduct(Map<String, Object> map) {
        return this
                .update("com.ningpai.goods.dao.GoodsProductMapper.batchAuditUploadProduct",
                        map);
    }

    /**
     * 查询货品销售排行
     * 
     * @param map
     * @return
     */
    @Override
    public List<Object> queryGoodsProductSalesRank(Map<String, Object> map) {
        return this
                .selectList(
                        "com.ningpai.goods.dao.GoodsProductMapper.queryGoodsProductSalesRank",
                        map);
    }

    /**
     * 查询总数
     * 
     * @param paraMap
     * @return
     */
    @Override
    public int selectAllSize(Map<String, Object> paraMap) {
        return this.selectOne(
                "com.ningpai.goods.dao.GoodsProductMapper.selectAllSize",
                paraMap);
    }

    /**
     * 根据商品Id查询货品Id
     * 
     * @param goodsId
     * @return
     */
    @Override
    public List<GoodsProduct> queryGoodsInfoIdByGoodsId(Long goodsId) {
        return this
                .selectList(
                        "com.ningpai.goods.dao.GoodsProductMapper.queryGoodsInfoIdByGoodsId",
                        goodsId);
    }

    /**
     * 根据主键获取商品信息
     * 
     * @param goodsId
     * @return
     */
    @Override
    public Goods selectGoodsByGoodsId(Long goodsId) {
        return this.selectOne(
                "com.ningpai.goods.dao.GoodsMapper.selectGoodsByGoodsId",
                goodsId);
    }

    /**
     * 根据map获取货品信息
     * 
     * @param map
     * @return
     */
    @Override
    public GoodsProduct queryProductById(Map<String, Object> map) {
        return this.selectOne(
                "com.ningpai.goods.dao.GoodsProductMapper.queryProductById",
                map);
    }

    /**
     * 根据productId获取货品信息
     * 
     * @param productId
     * @return
     */
    @Override
    public GoodsProduct queryProductByGoodsId(Long productId) {
        return this
                .selectOne(
                        "com.ningpai.goods.dao.GoodsProductMapper.queryProductByGoodsIds",
                        productId);
    }

    /**
     * 根据商品id获取所有货品信息
     * 
     * @param goodsId
     * @return
     */
    @Override
    public List<GoodsProduct> queryProductsByGoodsId(Long goodsId) {
        return this
                .selectList(
                        "com.ningpai.goods.dao.GoodsProductMapper.queryProductsByGoodsId",
                        goodsId);
    }

    /**
     * 同步修改货品副标题
     * 
     * @param map
     * @return
     */
    @Override
    public int updateGoodsSubtitleById(Map<String, Object> map) {
        return this
                .update("com.ningpai.goods.dao.GoodsProductMapper.updateGoodsSubtitleById",
                        map);
    }

    /**
     * 查询货品list
     * 
     * @return list
     */
    @Override
    public List<GoodsProductDetailVo> queryProductForMarketing(
            Map<String, Object> map) {
        return this
                .selectList(
                        "com.ningpai.goods.dao.GoodsProductMapper.queryProductForMarketing",
                        map);
    }

    /**
     * 新查询货品列表
     * 
     * @param map
     *            查询条件{@link java.util.Map}
     * @return pb
     */
    @Override
    public List<Object> newQueryProductForMarketing(Map<String, Object> map) {
        return this
                .selectList(
                        "com.ningpai.goods.dao.GoodsProductMapper.newQueryProductForMarketing",
                        map);
    }

    /**
     * 查询货品数目
     * 
     * @param map
     *            查询条件{@link java.util.Map}
     * @return int
     */
    @Override
    public int newQueryProductForMarketingCount(Map<String, Object> map) {
        return this
                .selectOne(
                        "com.ningpai.goods.dao.GoodsProductMapper.newQueryProductForMarketingCount",
                        map);
    }

    /**
     * 退款成功返还库存
     * 
     * @param map
     * @return
     */
    @Override
    public int addBaseStock(Map<String, Object> map) {
        return this.update(
                "com.ningpai.goods.dao.ProductWareMapper.plusStockToWare", map);
    }

    /**
     * 查询IDS
     * 
     * @see com.ningpai.goods.dao.GoodsProductMapper#selectInfoIdList(java.util.List)
     */
    @Override
    public List<Long> selectInfoIdList(List<Long> list) {
        return this.selectList(
                "com.ningpai.goods.dao.GoodsProductMapper.selectInfoIdList",
                list);
    }
    /**
     * 删除购物车的货品
     *
     * @param goodsIds 货品编号
     * @return int
     */
    @Override
    public int delShoppingGoodsByGoodsInfoIds(List<Long> goodsIds) {
        return this.update(
                "com.ningpai.goods.dao.GoodsProductMapper.delShoppingGoodsByGoodsInfoIds", goodsIds);
    }

    /**
     * 更新适配车型(力扬ID和力扬压缩ID)
     * @param param
     * @return
     */
    @Override
    public int updateAutoStyleByGoodsInfoId(Map<String,Object> param) {
        return this.update(
                "com.ningpai.goods.dao.GoodsProductMapper.updateAutoStyleByGoodsInfoId", param);
    }
}
