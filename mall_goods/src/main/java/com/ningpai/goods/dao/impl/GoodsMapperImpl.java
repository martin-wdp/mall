
package com.ningpai.goods.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.goods.bean.Goods;
import com.ningpai.goods.dao.GoodsMapper;
import com.ningpai.goods.util.GoodsSearchBean;
import com.ningpai.goods.vo.GoodsMoifiedVo;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * 商品DAO实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月24日 下午5:02:17
 * @version 1.0
 */
@Repository("GoodsMapper")
public class GoodsMapperImpl extends BasicSqlSupport implements GoodsMapper {
    /**
     * 查询商品是否下架
     * */
    @Override
    public String selectCheckGoodsMapper(int goodsId) {
        return this.selectOne("com.ningpai.goods.dao.GoodsMapper.selectCheckGoodsMapper",goodsId);
    }

    /**
     * 删除商品信息 (更新删除标记)
     *
     * @param map
     *            封装了更新的参数 {@link java.util.Map}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    public int deleteByPrimaryKey(Map<String, String> map) {
        return this.delete("com.ningpai.goods.dao.GoodsMapper.deleteByPrimaryKey", map);
    }

    /**
     * 插入商品信息(全属性 不推荐)
     *
     * @param record
     *            商品实体 {@link com.ningpai.goods.bean.Goods}
     * @return 最新插入的主键ID {@link java.lang.Integer}
     */
    public int insert(Goods record) {
        return this.insert("com.ningpai.goods.dao.GoodsMapper.insert", record);
    }

    /**
     * 插入商品信息(可选属性 推荐)
     *
     * @param record
     *            商品实体 {@link com.ningpai.goods.bean.Goods}
     * @return 最新插入的主键ID {@link java.lang.Integer}
     */
    public int insertSelective(Goods record) {
        return this.insert("com.ningpai.goods.dao.GoodsMapper.insertSelective", record);
    }

    /**
     * 根据主键查询商品信息
     *
     * @param goodsId
     *            商品主键ID {@link java.lang.Long}
     * @return 查询到的商品实体 {@link com.ningpai.goods.bean.Goods}
     */
    public Goods selectByPrimaryKey(Long goodsId) {
        return this.selectOne("com.ningpai.goods.dao.GoodsMapper.selectByPrimaryKey", goodsId);
    }

    /**
     * 更新商品信息 （可选属性 推荐）
     *
     * @param record
     *            需要更新的商品实体 {@link com.ningpai.goods.bean.Goods}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    public int updateByPrimaryKeySelective(Goods record) {
        return this.update("com.ningpai.goods.dao.GoodsMapper.updateByPrimaryKeySelective", record);
    }

    /**
     * 更新商品信息 （全属性 不推荐）
     *
     * @param record
     *            需要更新的商品实体 {@link com.ningpai.goods.bean.Goods}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    public int updateByPrimaryKeyWithBLOBs(Goods record) {
        return this.update("com.ningpai.goods.dao.GoodsMapper.updateByPrimaryKeyWithBLOBs", record);
    }

    /**
     * 更新商品信息 （全属性 不推荐）
     *
     * @param record
     *            需要更新的商品实体 {@link com.ningpai.goods.bean.Goods}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    public int updateByPrimaryKey(Goods record) {
        return this.update("com.ningpai.goods.dao.GoodsMapper.updateByPrimaryKey", record);
    }

    /**
     * 查询所有的行数，分页要用到
     *
     * @return 查询到的所有的行数 {@link java.lang.Integer}
     */
    public int queryTotalCount(Map<String, Object> map) {
        return this.selectOne("com.ningpai.goods.dao.GoodsMapper.queryTotalCount",map);
    }

    /**
     * 根据分页参数查询商品列表
     *
     * @param map
     *            封装分页参数的map {@link java.util.Map}
     * @return 查询到的商品列表 {@link java.util.List}
     *         {@link com.ningpai.goods.bean.Goods}
     */
    public List<Object> queryGoodsListByPageBean(Map<String, Object> map) {
        return this.selectList("com.ningpai.goods.dao.GoodsMapper.queryGoodsListByPageBean", map);
    }



    /**
     * 查询最新插入的主键ID
     *
     * @return 查询到的主键ID
     */
    public Long selectLastId() {
        return this.selectOne("com.ningpai.goods.dao.GoodsMapper.selectLastId");
    }

    /**
     * 根据商品分类ID查询商品列表
     *
     * @param catId
     *            分类ID {@link java.lang.Long}
     * @return 查询到的商品的集合 {@link java.util.List}
     */
    public List<Object> queryGoodsListByCatId(Long catId) {
        return this.selectList("com.ningpai.goods.dao.GoodsMapper.queryGoodsListByCatId", catId);
    }

    /**
     * 根据商品IDc查询修改商品的VO
     *
     * @param goodsId
     *            商品ID {@link java.lang.Long}
     * @return 查询到的商品的VO {@link com.ningpai.goods.vo.GoodsMoifiedVo}
     */
    public GoodsMoifiedVo queryModeifiedVoByGoodsId(Long goodsId) {
        return this.selectOne("com.ningpai.goods.dao.GoodsMapper.queryModeifiedVoByGoodsId", goodsId);
    }

    /**
     * 根据商品ID查询所属货品的库存总和
     *
     * @param goodsId
     *            商品ID {@link java.lang.Long}
     * @return 查询到的库存和
     */
    public Long queryStockByGoodsId(Long goodsId) {
        return this.selectOne("com.ningpai.goods.dao.GoodsMapper.queryStockByGoodsId", goodsId);
    }

    /**
     * 高级查询商品列表
     *
     * @param map
     *            封装查询参数
     * @return 查询到的列表
     */
    public List<Object> queryByPageBeanAndSearchBean(Map<String, Object> map) {
        return this.selectList("com.ningpai.goods.dao.GoodsMapper.queryByPageBeanAndSearchBean", map);
    }
    /**
     * 高级查询第三方商品列表
     *
     * @param map
     * @return List
     * */
    public List<Object> queryThirdByPageBeanAndSearchBean(Map<String, Object> map) {
        return this.selectList("com.ningpai.goods.dao.GoodsMapper.queryThirdByPageBeanAndSearchBean", map);
    }

    /**
     * 根据查询参数查询所有的行数，分页要用到
     *
     * @return 查询到的所有的行数 {@link java.lang.Integer}
     */
    public int queryTotalCountBySearchBean(GoodsSearchBean searchBean) {
        return this.selectOne("com.ningpai.goods.dao.GoodsMapper.queryTotalCountBySearchBean", searchBean);
    }

    /**
     * 根据查询参数查询所有货品的行数，分页要用到
     *
     * 2015-12-19 wuyanbo add
     *
     * @return 查询到的所有的行数 {@link java.lang.Integer}
     */
    public int queryProductTotalCountBySearchBean(GoodsSearchBean searchBean) {
        return this.selectOne("com.ningpai.goods.dao.GoodsMapper.queryProductTotalCountBySearchBean", searchBean);
    }

    /**
     * 第三方查询行数
     *
     * @param searchBean
     * @return
     */
    public int queryThirdTotalCountBySearchBean(GoodsSearchBean searchBean) {
        return this.selectOne("com.ningpai.goods.dao.GoodsMapper.queryThirdTotalCountBySearchBean", searchBean);
    }

    /**
     * 根据list 查询多个商品SKU
     *
     * @param list
     * @return List
     */
    public List<Object> selectProductSkuList(List<Long> list) {
        return this.selectList("com.ningpai.goods.dao.GoodsProductMapper.selectProductSkuList", list);
    }

    /**
     * 根据商品编号查询商品是否已经存在
     *
     * @param goodsNo
     * @return
     */
    public int queryCountByGoodsNo(String goodsNo) {
        return this.selectOne("com.ningpai.goods.dao.GoodsMapper.queryCountByGoodsNo", goodsNo);
    }

    /**
     * 根据商品ID数组查询商品列表FOR EXPORT
     *
     * @param map
     * @return
     */
    public List<Object> queryGoodsListVoListForExportByGoodsIds(Map<String, Object> map) {
        return this.selectList("com.ningpai.goods.dao.GoodsMapper.queryGoodsListVoListForExportByGoodsIds", map);
    }

    /**
     * 批量上下架商品
     *
     * @param map
     *            封装参数 {@link Map}
     * @return 操作的行数 {@link Integer}
     */
    public int batchUploadOrDownGoods(Map<String, Object> map) {
        return this.update("com.ningpai.goods.dao.GoodsMapper.batchUploadOrDownGoods", map);
    }

    /**
     * 查询所有的商品信息用于导出
     *
     * @return 查询到的集合
     */
    public List<Object> queryAllGoodsToExport(String isThird) {
        return this.selectList("com.ningpai.goods.dao.GoodsMapper.queryAllGoodsToExport",isThird);
    }
    /**
     * 获取商品审核标记
     *
     * @return
     */
    @Override
    public String selectAuditAction() {
        return this.selectOne("com.ningpai.goods.dao.GoodsMapper.selectAuditAction");
    }
    /**
     * 批量审核上架商品
     *
     * @param map
     * @return
     */
    @Override
    public int batchAuditUploadOrDownGoods(Map<String, Object> map) {
        return this.update("com.ningpai.goods.dao.GoodsMapper.batchAuditUploadOrDownGoods", map);
    }
    /**
     * 根据条件查询商品信息
     *
     * @param map
     * @return
     */
    @Override
    public List<Object> queryGoodsForCoupon(Map<String, Object> map) {
        return this.selectList("com.ningpai.goods.dao.GoodsMapper.queryGoodsForCoupon",map);
    }
    /**
     * ajax查询商品个数 分页用
     *
     * @param map
     * @return
     */
    @Override
    public int queryCountForCoupon(Map<String, Object> map) {
        return this.selectOne("com.ningpai.goods.dao.GoodsMapper.queryCountForCoupon",map);
    }
    /**
     * 根据id查询信息
     *
     * @param goodsId
     * @return
     */
    @Override
    public Goods queryGoodsByGoodsId(Long goodsId) {
        return this.selectOne("com.ningpai.goods.dao.GoodsMapper.queryGoodsByGoodsIds",goodsId);
    }

    /**
     * 批量下架
     *
     * @param map
     * @return
     */
    @Override
    public int batchDown(Map<String, Object> map) {
        return this.update("com.ningpai.goods.dao.GoodsMapper.batchDown",map);
    }

    /**
     * 批量下架商品
     *
     * @param map
     * @return
     */
    @Override
    public int batchDownGoods(Map<String, Object> map) {
        return  this.update("com.ningpai.goods.dao.GoodsMapper.batchDownGoods",map);
    }

    /**
     * 批量上架
     *
     * @param map
     * @return
     */
    @Override
    public int batchUp(Map<String, Object> map) {
        return this.update("com.ningpai.goods.dao.GoodsMapper.batchUp",map);
    }

    /**
     * 批量上架商品
     *
     * @param map
     * @return
     */
    @Override
    public int batchUpGoods(Map<String, Object> map) {
        return this.update("com.ningpai.goods.dao.GoodsMapper.batchUpGoods",map);
    }

    /**
     * 高级查询缺货商品列表
     *
     * @param map 封装查询参数
     * @return 查询到的列表
     */
    @Override
    public List<Object> queryStockByPageBeanAndSearchBean(Map<String, Object> map) {
        return this
                .selectList(
                        "com.ningpai.goods.dao.GoodsMapper.queryStock",
                        map);
    }

    /**
     * 高级查询预警商品列表
     *
     * @param map 封装查询参数
     * @return 查询到的列表
     */
    @Override
    public List<Object> queryElaryByPageBeanAndSearchBean(Map<String, Object> map) {
        return this
                .selectList(
                        "com.ningpai.goods.dao.GoodsMapper.queryEarly",
                        map);
    }

    /**
     * 根据查询参数查询所有预警的行数，分页要用到
     *
     * @param searchBean
     * @return 查询到的所有的行数 {@link Integer}
     */
    @Override
    public int queryElaryTotalCountBySearchBean(GoodsSearchBean searchBean) {
        return this
                .selectOne(
                        "com.ningpai.goods.dao.GoodsMapper.queryEarlyCount",
                        searchBean);
    }

    /**
     * 根据查询参数查询所有预警货品的行数，分页要用到
     *
     * @param searchBean
     * @return 查询到的所有的行数 {@link Integer}
     * 2015-12-19 wuyanbo add
     */
    @Override
    public int queryElaryProductTotalCountBySearchBean(GoodsSearchBean searchBean) {
        return this
                .selectOne(
                        "com.ningpai.goods.dao.GoodsMapper.queryEarlyProductCount",
                        searchBean);
    }

    /**
     * 根据查询参数查询所有缺货的行数，分页要用到
     *
     * @param searchBean
     * @return 查询到的所有的行数 {@link Integer}
     */
    @Override
    public int queryStockTotalCountBySearchBean(GoodsSearchBean searchBean) {
        return this
                .selectOne(
                        "com.ningpai.goods.dao.GoodsMapper.queryStockCount",
                        searchBean);
    }

    /**
     * 根据查询参数查询所有缺货货品的行数，分页要用到
     *
     * @param searchBean
     * @return 查询到的所有的行数 {@link Integer}
     * 2015-12-19 wuyanbo add
     *
     */
    @Override
    public int queryStockProductTotalCountBySearchBean(GoodsSearchBean searchBean) {
        return this
                .selectOne(
                        "com.ningpai.goods.dao.GoodsMapper.queryStockProductCount",
                        searchBean);
    }

    /**
     * 批量修改库存
     * 用于后台批量修改库存
     *
     * @param map
     * @auhor houyichang  2015/8/24
     */
    @Override
    public int batchUpdateStock(Map<String, Object> map) {
        return this.update("com.ningpai.goods.dao.GoodsMapper.batchUpdateStock",map);
    }

    /**
     * 查询商品下是否有货品
     * 用于后台批量修改库存时查询是否有货品
     *
     * @param goodsId
     * @author houyichang 2015/8/24
     */
    @Override
    public int queryProductByGoodsId(Long goodsId) {
        return this
                .selectOne(
                        "com.ningpai.goods.dao.GoodsMapper.queryProductCountByGoodsId",
                        goodsId);
    }

}
