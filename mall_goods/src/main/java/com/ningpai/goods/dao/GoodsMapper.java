package com.ningpai.goods.dao;

import com.ningpai.goods.bean.Goods;
import com.ningpai.goods.util.GoodsSearchBean;
import com.ningpai.goods.vo.GoodsMoifiedVo;

import java.util.List;
import java.util.Map;

/**
 * 商品DAO
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月24日 下午2:56:06
 * @version 1.0
 */
public interface GoodsMapper {
    /**
     * 查询商品是否下架
     * */
    String selectCheckGoodsMapper(int goodsId);

    /**
     * 删除商品信息 (更新删除标记)
     * 
     * @param map
     *            封装了更新的参数 {@link java.util.Map}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    int deleteByPrimaryKey(Map<String, String> map);

    /**
     * 插入商品信息(全属性 不推荐)
     * 
     * @param record
     *            商品实体 {@link com.ningpai.goods.bean.Goods}
     * @return 最新插入的主键ID {@link java.lang.Integer}
     */
    int insert(Goods record);

    /**
     * 插入商品信息(可选属性 推荐)
     * 
     * @param record
     *            商品实体 {@link com.ningpai.goods.bean.Goods}
     * @return 最新插入的主键ID {@link java.lang.Integer}
     */
    int insertSelective(Goods record);

    /**
     * 根据主键查询商品信息
     * 
     * @param goodsId
     *            商品主键ID {@link java.lang.Long}
     * @return 查询到的商品实体 {@link com.ningpai.goods.bean.Goods}
     */
    Goods selectByPrimaryKey(Long goodsId);

    /**
     * 更新商品信息 （可选属性 推荐）
     * 
     * @param record
     *            需要更新的商品实体 {@link com.ningpai.goods.bean.Goods}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    int updateByPrimaryKeySelective(Goods record);

    /**
     * 更新商品信息 （全属性 不推荐）
     * 
     * @param record
     *            需要更新的商品实体 {@link com.ningpai.goods.bean.Goods}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    int updateByPrimaryKeyWithBLOBs(Goods record);

    /**
     * 更新商品信息 （全属性 不推荐）
     * 
     * @param record
     *            需要更新的商品实体 {@link com.ningpai.goods.bean.Goods}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    int updateByPrimaryKey(Goods record);

    /**
     * 查询所有的行数，分页要用到
     * 
     * @return 查询到的所有的行数 {@link java.lang.Integer}
     */
    int queryTotalCount(Map<String, Object> map);

    /**
     * 根据分页参数查询商品列表
     * 
     * @param map
     *            封装分页参数的map {@link java.util.Map}
     * @return 查询到的商品列表 {@link java.util.List}
     *         {@link com.ningpai.goods.bean.Goods}
     */
    List<Object> queryGoodsListByPageBean(Map<String, Object> map);

    /**
     * 查询最新插入的主键ID
     * 
     * @return 查询到的主键ID
     */
    Long selectLastId();

    /**
     * 根据商品分类ID查询商品列表
     * 
     * @param catId
     *            分类ID {@link java.lang.Long}
     * @return 查询到的商品的集合 {@link java.util.List}
     */
    List<Object> queryGoodsListByCatId(Long catId);

    /**
     * 根据商品IDc查询修改商品的VO
     * 
     * @param goodsId
     *            商品ID {@link java.lang.Long}
     * @return 查询到的商品的VO {@link com.ningpai.goods.vo.GoodsMoifiedVo}
     */
    GoodsMoifiedVo queryModeifiedVoByGoodsId(Long goodsId);

    /**
     * 根据商品ID查询所属货品的库存总和
     * 
     * @param goodsId
     *            商品ID {@link java.lang.Long}
     * @return 查询到的库存和
     */
    Long queryStockByGoodsId(Long goodsId);

    /**
     * 高级查询商品列表
     * 
     * @param map
     *            封装查询参数
     * @return 查询到的列表
     */
    List<Object> queryByPageBeanAndSearchBean(Map<String, Object> map);

    /**
     * 高级查询第三方商品列表
     *
     * @param map
     * @return List
     * */
    List<Object> queryThirdByPageBeanAndSearchBean(Map<String, Object> map);

    /**
     * 根据查询参数查询所有的行数，分页要用到
     * 
     * @return 查询到的所有的行数 {@link java.lang.Integer}
     */
    int queryTotalCountBySearchBean(GoodsSearchBean searchBean);

    /**
     * 根据查询参数查询所有货品的行数，分页要用到
     *
     * 2015-12-19 wuyanbo add
     *
     * @return 查询到的所有的行数 {@link java.lang.Integer}
     */
    int queryProductTotalCountBySearchBean(GoodsSearchBean searchBean);

    /**
     * 第三方查询行数
     * 
     * @param searchBean
     * @return
     */
    int queryThirdTotalCountBySearchBean(GoodsSearchBean searchBean);

    /**
     * 根据list 查询多个商品SKU
     * 
     * @param idList
     * @return List
     */
    List<Object> selectProductSkuList(List<Long> idList);

    /**
     * 根据商品编号查询商品是否已经存在
     * 
     * @param goodsNo
     * @return
     */
    int queryCountByGoodsNo(String goodsNo);

    /**
     * 根据商品ID数组查询商品列表FOR EXPORT
     * 
     * @param map
     * @return
     */
    List<Object> queryGoodsListVoListForExportByGoodsIds(Map<String, Object> map);

    /**
     * 批量上下架商品
     * 
     * @param map
     *            封装参数 {@link Map}
     * @return 操作的行数 {@link Integer}
     */
    int batchUploadOrDownGoods(Map<String, Object> map);

    /**
     * 查询所有的商品信息用于导出
     * 
     * @return 查询到的集合
     */
    List<Object> queryAllGoodsToExport(String isThird);

    /**
     * 获取商品审核标记
     * 
     * @return
     */
    String selectAuditAction();

    /**
     * 批量审核上架商品
     * 
     * @param map
     * @return
     */
    int batchAuditUploadOrDownGoods(Map<String, Object> map);

    /**
     * 根据条件查询商品信息
     * 
     * @param map
     * @return
     */
    List<Object> queryGoodsForCoupon(Map<String, Object> map);

    /**
     * ajax查询商品个数 分页用
     * 
     * @param map
     * @return
     */
    int queryCountForCoupon(Map<String, Object> map);

    /**
     * 根据id查询信息
     * 
     * @param goodsId
     * @return
     */
    Goods queryGoodsByGoodsId(Long goodsId);

    /**
     * 批量下架
     * 
     * @param map
     * @return
     */
    int batchDown(Map<String, Object> map);

    /**
     * 批量下架商品
     * 
     * @param map
     * @return
     */
    int batchDownGoods(Map<String, Object> map);

    /**
     * 批量上架
     * 
     * @param map
     * @return
     */
    int batchUp(Map<String, Object> map);

    /**
     * 批量上架商品
     * 
     * @param map
     * @return
     */
    int batchUpGoods(Map<String, Object> map);

    /**
     * 高级查询缺货商品列表
     *
     * @param map
     *            封装查询参数
     * @return 查询到的列表
     */
    List<Object> queryStockByPageBeanAndSearchBean(Map<String, Object> map);

    /**
     * 高级查询预警商品列表
     *
     * @param map
     *            封装查询参数
     * @return 查询到的列表
     */
    List<Object> queryElaryByPageBeanAndSearchBean(Map<String, Object> map);

    /**
     * 根据查询参数查询所有预警的行数，分页要用到
     *
     * @return 查询到的所有的行数 {@link java.lang.Integer}
     */
    int queryElaryTotalCountBySearchBean(GoodsSearchBean searchBean);

    /**
     * 根据查询参数查询所有预警货品的行数，分页要用到
     *
     * @return 查询到的所有的行数 {@link java.lang.Integer}
     * 2015-12-19 wuyanbo add
     */
    int queryElaryProductTotalCountBySearchBean(GoodsSearchBean searchBean);

    /**
     * 根据查询参数查询所有缺货的行数，分页要用到
     *
     * @return 查询到的所有的行数 {@link java.lang.Integer}
     */
    int queryStockTotalCountBySearchBean(GoodsSearchBean searchBean);

    /**
     * 根据查询参数查询所有缺货的货品行数，分页要用到
     *
     * @return 查询到的所有的行数 {@link java.lang.Integer}
     * 2015-12-19 wuyanbo add
     */
    int queryStockProductTotalCountBySearchBean(GoodsSearchBean searchBean);

    /**
     * 批量修改库存
     *用于后台批量修改库存
     *
     * @auhor houyichang  2015/8/24
     * @param map
     * */
    int batchUpdateStock(Map<String,Object> map);

    /**
     * 查询商品下是否有货品
     * 用于后台批量修改库存时查询是否有货品
     *
     * @author houyichang 2015/8/24
     * @param goodsId
     * */
    int queryProductByGoodsId(Long goodsId);
 }
