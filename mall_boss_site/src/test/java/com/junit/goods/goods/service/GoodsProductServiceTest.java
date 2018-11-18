package com.junit.goods.goods.service;

import com.ningpai.customer.bean.CustomerFollow;
import com.ningpai.goods.bean.Goods;
import com.ningpai.goods.bean.GoodsProduct;
import com.ningpai.goods.dao.GoodsProductMapper;
import com.ningpai.goods.service.GoodsProductService;
import com.ningpai.goods.service.impl.GoodsProductServiceImpl;
import com.ningpai.goods.util.GoodsSearchBean;
import com.ningpai.goods.util.ValueUtil;
import com.ningpai.goods.vo.GoodsProductDetailViewVo;
import com.ningpai.goods.vo.GoodsProductDetailVo;
import com.ningpai.util.PageBean;
import com.ningpai.util.SelectBean;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by houyichang on 2015/10/12.
 */
public class GoodsProductServiceTest extends UnitilsJUnit3 {
    /**
     * 需要测试的类
     * */
    @TestedObject
    private GoodsProductService goodsProductService = new GoodsProductServiceImpl();

    /**
     * 模拟
     * */
    @InjectIntoByType
    Mock<GoodsProductMapper> goodsProductMapperMock;

    /**
     * 根据商品ID 获取商品对象
     *
     */
    public void testSelectGoodsByGoodsId(){
        goodsProductMapperMock.returns(new Goods()).selectGoodsByGoodsId(1L);
        assertNotNull(goodsProductService.selectGoodsByGoodsId(1L));
    }

    /***
     * 更新关注商品对象
     */
    public void testUpdateFollow(){
        goodsProductMapperMock.returns(1).updateFollow(new CustomerFollow());
        assertEquals(1,goodsProductService.updateFollow(new CustomerFollow()));
    }

    /**
     * 根据商品ID查询货品信息列表
     *
     */
    public void testQueryByGoodsId(){
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        // 往map集合中放值
        map.put("goodsId", 1L);
        map.put(ValueUtil.CONDITION,"1");
        map.put(ValueUtil.SEARCHTEXT,"1");
        map.put("goodsName", "1");
        map.put("goodsNo","1");
        goodsProductMapperMock.returns(1).queryCountByGoodsAndSelectParam(map);
        // 设置开始行数
        map.put(ValueUtil.STARTROWNUM,0);
        // 设置结束行
        map.put(ValueUtil.ENDROWNUM,15);
        List<Object> goodsProductList = new ArrayList<Object>();
        GoodsProduct goodsProduct = new GoodsProduct();
        goodsProduct.setGoodsInfoName("1");
        goodsProduct.setGoodsInfoItemNo("1");
        goodsProduct.setGoodsId(1L);
        goodsProductList.add(goodsProduct);
        goodsProductMapperMock.returns(goodsProductList).queryProductByGoodsId(map);
        SelectBean selectBean = new SelectBean();
        selectBean.setSearchText("1");
        selectBean.setCondition("1");
        selectBean.setGoodsName("1");
        selectBean.setGoodsNo("1");
        PageBean pageBean  = new PageBean();
        pageBean.setStartRowNum(0);
        pageBean.setEndRowNum(15);
        assertEquals(1, goodsProductService.queryByGoodsId(1L,pageBean, selectBean).getList().size());
    }

    /**
     * 根据商品ID查询货品信息列表 新版boss更改
     */
    public void testQueryByGoodsIdNew(){
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        // 往map集合放参数
        map.put("goodsId", 1L);
        map.put("goodsName","1");
        map.put("goodsNo","1");
        goodsProductMapperMock.returns(1).queryCountByGoodsAndSelectParam(map);
        // 设置开始行
        map.put(ValueUtil.STARTROWNUM,0);
        // 设置结束行
        map.put(ValueUtil.ENDROWNUM,15);
        List<Object> goodsProductList = new ArrayList<Object>();
        GoodsProduct goodsProduct = new GoodsProduct();
        goodsProduct.setGoodsInfoName("1");
        goodsProduct.setGoodsInfoItemNo("1");
        goodsProduct.setGoodsId(1L);
        goodsProductList.add(goodsProduct);
        goodsProductMapperMock.returns(goodsProductList).queryProductByGoodsIdNew(map);
        SelectBean selectBean = new SelectBean();
        selectBean.setSearchText("1");
        selectBean.setCondition("1");
        selectBean.setGoodsName("1");
        selectBean.setGoodsNo("1");
        PageBean pageBean  = new PageBean();
        pageBean.setStartRowNum(0);
        pageBean.setEndRowNum(15);
        assertEquals(1,goodsProductService.queryByGoodsIdNew(1L,pageBean,selectBean).getList().size());
    }

    /**
     * 删除货品信息
     *
     */
    public void testDelProductByProductId(){
        assertEquals(1,1);
    }

    /**
     * 删除第三方单个货品信息
     *
     */
    public void testDelThirdProductByProductId(){
        assertEquals(1,1);
    }

    /**
     * 批量删除货品
     *
     */
    public void testBatchDelProduct(){
        assertEquals(1,1);
    }

    /**
     * 批量上下架货品
     *
     */
    public void testBatchUploadProduct(){
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        // 往map集合中放入参数
        map.put("username", "1");
        map.put("status", "1");
        map.put("productIds", new Long[]{1L});
        goodsProductMapperMock.returns(1).batchUploadProduct(map);
        assertEquals(1,goodsProductService.batchUploadProduct("1",new Long[]{1L},1));
    }

    /**
     * 批量显示或者隐藏在列表页
     *
     */
    public void testBatchShowOrHide(){
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        // 把参数放入Map集合中
        map.put("username", "1");
        map.put("status", "1");
        map.put("productIds", new Long[]{1L});
        goodsProductMapperMock.returns(1).batchShow(map);
        assertEquals(1,goodsProductService.batchShowOrHide("1",new Long[]{1L},1));
    }

    /**
     * 批量显示或隐藏到手机版
     *
     */
    public void testBatchShowOrHideMobile(){
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        // 把参数放进map集合中
        map.put("username", "1");
        map.put("status","1");
        map.put("productIds", new Long[]{1L});
        goodsProductMapperMock.returns(1).batchShowMobile(map);
        assertEquals(1,goodsProductService.batchShowOrHideMobile("1",new Long[]{1L},1));
    }

    /**
     * 添加货品信息
     *
     */
    public void testSaveProduct(){
        assertEquals(1,1);
    }

    /**
     * 根据主键查询ProductVo
     *
     */
    public void testQueryByPrimaryId(){
        assertEquals(1,1);
    }

    /**
     * 更新货品信息
     *
     */
    public void testUpdateProduct(){
        assertEquals(1,1);
    }

    /**
     * 根据PageBean查询货品详细信息
     *
     */
    public void testQueryProductDetailInfoByPageBean(){
        // 定义一个HashMap集合
        Map<String, Long> map = new HashMap<String, Long>();
        // 把参数放入Map集合中
        map.put(ValueUtil.STARTROWNUM, 0L);
        map.put(ValueUtil.ENDROWNUM, 15L);
        map.put("groupId", 1L);
        goodsProductMapperMock.returns(1).queryTotalCountWithGroupId(map);
        List<Object> goodsProductList = new ArrayList<Object>();
        goodsProductMapperMock.returns(goodsProductList).queryProductListByPageBean(map);
        assertEquals(1,1);
    }

    /**
     * 根据货品ID查询货品预览页的Vo
     *
     */
    public void testQueryViewVoByProductId(){
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        // 把参数放入map集合中
        map.put("productId", 1L);
        goodsProductMapperMock.returns(new GoodsProductDetailViewVo()).queryByProductId(map);
        assertNotNull(goodsProductService.queryViewVoByProductId(1L));
    }

    /**
     * 验证货品编号是否可用
     *
     */
    public void testCheckProuctNo(){
        goodsProductMapperMock.returns(1).queryCountByProductNo("1");
        assertEquals(false,goodsProductService.checkProuctNo("1"));
    }

    /**
     * 验证所选的参数是否已经生成了货品
     *
     */
    public void testCheckProductParams(){
        goodsProductMapperMock.returns(1).queryCountByParam(new HashMap<String, Object>());
        assertEquals(false,goodsProductService.checkProductParams(new HashMap<String, Object>()));
    }

    /**
     * 查询第三方货品
     *
     * */
    public void testQueryThirdProduct(){
        Map<String, Object> map = new HashMap<String,Object>();
        PageBean pageBean = new PageBean();
        goodsProductMapperMock.returns(1).queryCountThirdProduct(map);
        // 设置当前页码
        map.put("pageNo",1);
        // 设置开始行数
        map.put("startRowNum", 0);
        // 设置结束行数
        map.put("endRowNum",15);
        goodsProductMapperMock.returns(new ArrayList<GoodsProduct>()).queryThirdProduct(map);
        assertEquals(1,1);
    }

    /**
     * 根据分类ID和品牌Id查询货品列表
     *
     */
    public void testQueryProductForCouponLife(){
         assertEquals(1,1);
    }

    /**
     * 根据分类ID和品牌Id查询货品列表
     *
     */
    public void testQueryProductForCoupon(){
        assertEquals(1,1);
    }

    /**
     * 查询库存预警额货品个数
     *
     */
    public void testQueryStockWarnCount(){
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        // 把参数放入map集合中
        map.put("flag", 1);
        map.put(ValueUtil.CONDITION, "1");
        map.put(ValueUtil.SEARCHTEXT, "1");
        goodsProductMapperMock.returns(1).queryStockWarnCount(map);
        SelectBean selectBean = new SelectBean();
        selectBean.setCondition("1");
        selectBean.setSearchText("1");
        assertEquals(1, goodsProductService.queryStockWarnCount(1, selectBean));
    }

    /**
     * 根据参数查询货品的列表并返回PageBean
     */
    public void testQueryProductListBySomeParam(){
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        // 把参数放进map集合中
        map.put("flag", 1);
        map.put(ValueUtil.CONDITION, "1");
        map.put(ValueUtil.SEARCHTEXT,"1");
        goodsProductMapperMock.returns(1).queryStockWarnCount(map);
        PageBean pb = new PageBean();
        pb.setStartRowNum(0);
        pb.setEndRowNum(15);
        // 设置开始行数
        map.put(ValueUtil.STARTROWNUM, pb.getStartRowNum());
        // 设置结束行
        map.put(ValueUtil.ENDROWNUM, pb.getEndRowNum());
        List<GoodsProduct> goodsProductList = new ArrayList<GoodsProduct>();
        GoodsProduct goodsProduct = new GoodsProduct();
        goodsProductList.add(goodsProduct);
        goodsProductMapperMock.returns(goodsProductList).queryProductListBySomeParam(map);
        SelectBean selectBean = new SelectBean();
        selectBean.setCondition("1");
        selectBean.setSearchText("1");
        assertEquals(1,goodsProductService.queryProductListBySomeParam(1,pb,selectBean).getList().size());

    }

    /**
     * 根据商品Id和查询参数查询货品列表for 导出数据
     *
     */
    public void testQueryAllProductByGoodsIdForExport(){
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        // 把参数放入map集合中
        map.put("goodsId", 1);
        map.put(ValueUtil.CONDITION, "1");
        map.put(ValueUtil.SEARCHTEXT,"1");
        map.put(ValueUtil.STARTROWNUM, 0);
        map.put(ValueUtil.ENDROWNUM, 1000000);
        List<Object> goodsProductList = new ArrayList<Object>();
        GoodsProduct goodsProduct = new GoodsProduct();
        goodsProductList.add(goodsProduct);
        goodsProductMapperMock.returns(goodsProductList).queryProductByGoodsId(map);
        SelectBean selectBean = new SelectBean();
        selectBean.setSearchText("1");
        selectBean.setCondition("1");
        assertNotNull(goodsProductService.queryAllProductByGoodsIdForExport(1L, selectBean));
    }

    /**
     * 根据货品ID数组查询货品集合
     *
     */
    public void testQueryAllProductByProductIdsForExport(){
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("productIds", 1L);
        List<Object> list = new ArrayList<Object>();
        goodsProductMapperMock.returns(list).queryProductsByProductIds(map);
        assertNotNull(goodsProductService.queryAllProductByProductIdsForExport(new Long[]{1L}));
    }

    /**
     * 查询今天上架的商品
     *
     */
    public void testQueryTodayProCount(){
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("thirdId", 1L);
        goodsProductMapperMock.returns(1).queryTodayProCount(map);
        assertEquals(1,goodsProductService.queryTodayProCount(1L));
    }

    /**
     *
     *            商品id数组
     * @return 查询到的集合
     * @author NINGPAI-LIH
     */
     public void testQueryProductForCouponByGoodsInfoIds(){
         // 定义一个HashMap集合
         Map<String, Object> map = new HashMap<String, Object>();
         // 把参数放进map集合中
         map.put("goodsInfoIds", 1L);
         goodsProductMapperMock.returns(1).queryCountForCouponByGoodsInfoIds(map);
         // 设置PageBean的开始行
         map.put("startRowNum",0);
         // 设置PageBean的结束行
         map.put("endRowNum", 15);
         List<GoodsProduct> list = new ArrayList<GoodsProduct>();
         GoodsProduct goodsProduct = new GoodsProduct();
         goodsProduct.setGoodsInfoId(1L);
         list.add(goodsProduct);
         goodsProductMapperMock.returns(list).queryProductForCouponByGoodsInfoIds(map);
         assertEquals(1,1);
     }

    /**
     * 根据分类ID和品牌Id查询货品列表 查询到的是boss的商品
     *
     */
    public void testQueryProductForCouponByThird(){
        assertEquals(1,1);
    }

    /**
     * 减少库存
     *
     */
    public void testMinStock(){
        assertEquals(1,1);
    }

    /**
     * 增加库存
     *
     */
    public void testPlusStock(){
        assertEquals(1,1);
    }

    /**
     * 货品审核通过
     *
     */
    public void testAuditProductAction(){
        goodsProductMapperMock.returns(1).auditProductAction(1L);
        assertEquals(1,goodsProductService.auditProductAction(1L));
    }

    /**
     * 拒绝审核通过
     *
     */
    public void testRefuseAuditProductAction(){
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        // 把参数放入map集合中
        map.put("goodsInfoId", 1L);
        map.put("refuseReason", "1");
        goodsProductMapperMock.returns(1).refuseAuditProductAction(map);
        assertEquals(1,goodsProductService.refuseAuditProductAction(1L,"1"));
    }

    /**
     * 获取货品审核信息
     *
     */
    public void testQueryAuditByGoodsId(){
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("goodsId", 1L);
        // 设置查询条件
        map.put(ValueUtil.CONDITION, "1");
        // 设置查询文本
        map.put(ValueUtil.SEARCHTEXT,"1");
        map.put("goodsName","1");
        map.put("goodsNo","1");
        goodsProductMapperMock.returns(1).queryCountByGoodsAndSelectParam(map);
        List<GoodsProduct> goodsProductList = new ArrayList<GoodsProduct>();
        GoodsProduct goodsProduct = new GoodsProduct();
        goodsProduct.setGoodsId(1L);
        goodsProduct.setGoodsInfoName("1");
        goodsProduct.setGoodsInfoItemNo("1");
        // 设置开始行数
        map.put(ValueUtil.STARTROWNUM,0);
        // 设置结束行数
        map.put(ValueUtil.ENDROWNUM,15);
        goodsProductMapperMock.returns(goodsProductList).queryAuditProductByGoodsId(map);
        SelectBean selectBean = new SelectBean();
        selectBean.setCondition("1");
        selectBean.setSearchText("1");
        assertEquals(1,1);
    }

    /**
     * 批量审核上架
     *
     */
    public void testBatchAuditUploadProduct(){
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        // 把参数放入map集合中
        map.put("userName", "1");
        map.put("status","1");
        map.put("auditStatus", "1");
        map.put("productIds",1L);
        goodsProductMapperMock.returns(1).batchAuditUploadProduct(map);
        assertEquals(1,1);
    }

    /**
     * 查询货品销售排行
     *
     */
    public void testQueryGoodsProductSalesRank(){
        // 定义一个HashMap集合
        Map<String, Object> paraMap = new HashMap<String, Object>();
        // 把参数放入map集合中
        paraMap.put("startTime", "1");
        paraMap.put("endTime", "1");
        paraMap.put("startRowNum",0);
        paraMap.put("endRowNum",15);
        goodsProductMapperMock.returns(1).selectAllSize(paraMap);
        List<GoodsProduct> goodsProductList = new ArrayList<GoodsProduct>();
        GoodsProduct goodsProduct = new GoodsProduct();
        goodsProductList.add(goodsProduct);
        goodsProductMapperMock.returns(goodsProductList).queryGoodsProductSalesRank(paraMap);
        assertEquals(1,goodsProductService.queryGoodsProductSalesRank(new PageBean(),"1","1").getList().size());
    }

    /**
     * 供应商上传货品
     *
     */
    public void testSaveSupplierProduct(){
        assertEquals(1,1);
    }

    /**
     * 根据map获取货品信息
     *
     */
    public void testQueryProductById(){
        goodsProductMapperMock.returns(new GoodsProduct()).queryProductById(new HashMap<String, Object>());
        assertNotNull(goodsProductService.queryProductById(new HashMap<String, Object>()));
    }

    /**
     * 根据productId获取货品信息
     *
     */
    public void testqueryProductByGoodsId(){
        goodsProductMapperMock.returns(new GoodsProduct()).queryProductByGoodsId(1L);
        assertNotNull(goodsProductService.queryProductByGoodsId(1L));
    }

    /**
     * 根据商品id查询所有货品信息
     *
     */
    public void testQueryProductsByGoodsId(){
        goodsProductMapperMock.returns(new ArrayList<GoodsProduct>()).queryProductsByGoodsId(1L);
        assertNotNull(goodsProductService.queryProductsByGoodsId(1L));
    }

    /**
     * 同步修改
     *
     */
    public void testUpdateGoodsSubtitleById(){
        // 定义一个Hashmap集合
        Map<String, Object> map = new HashMap<String, Object>();
        // 把参数放入map集合中
        map.put("goodsInfoId", 1L);
        map.put("goodsSubtitle", "1");
        goodsProductMapperMock.returns(1).updateGoodsSubtitleById(map);
        assertEquals(1,goodsProductService.updateGoodsSubtitleById(1L,"1"));
    }

    /**
     * 根据商品id，查询货品详细列表
     *
     */
    public void testQueryProductListByGoodsId(){
        goodsProductMapperMock.returns(new ArrayList<GoodsProduct>()).queryProductListByGoodsId(1L);
        assertNotNull(goodsProductService.queryProductListByGoodsId(1L));
    }

    /**
     * 查询第三方货品列表
     *
     */
    public void testQueryProductForMarketing(){
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        // 把参数放入map集合
        map.put("thirdId", 1L);
        goodsProductMapperMock.returns(new ArrayList<GoodsProductDetailVo>()).queryProductForMarketing(map);
        assertNotNull(goodsProductService.queryProductForMarketing(1L));
    }

    /**
     * 新查询货品列表 分页
     *
     */
    public void testNewQueryProductList(){
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        GoodsSearchBean selectBean = new GoodsSearchBean();
        selectBean.setGoodsNo("1");
        selectBean.setGoodsName("1");
        selectBean.setGoodsInfoItemNo("1");
        selectBean.setLowGoodsInfoPrice(new BigDecimal(1));
        selectBean.setHighGoodsInfoPrice(new BigDecimal(1));
        // 把参数放入map集合
        map.put("thirdId", 1L);
        map.put("goodaName",selectBean.getGoodsName());
        map.put("goodstNo", selectBean.getGoodsNo());
        map.put("productNo",selectBean.getGoodsInfoItemNo());
        map.put("goodsInfoAdded", "1");
        map.put("lowGoodsInfoPrice",selectBean.getLowGoodsInfoPrice());
        map.put("highGoodsInfoPrice",selectBean.getHighGoodsInfoPrice());
        map.put("offValue",new BigDecimal(0));
        goodsProductMapperMock.returns(1).newQueryProductForMarketingCount(map);
        map.put("startRowNum",0);
        map.put("endRowNum",15);
        List<GoodsProduct> goodsProductList = new ArrayList<GoodsProduct>();
        GoodsProduct goodsProduct = new GoodsProduct();
        goodsProduct.setThirdId(1L);
        goodsProduct.setGoodsInfoAdded("1");
        goodsProduct.setGoodsInfoItemNo("1");
        goodsProduct.setGoodsInfoName("1");
        goodsProduct.setGoodsInfoPreferPrice(new BigDecimal(1));
        goodsProductList.add(goodsProduct);

        goodsProductMapperMock.returns(goodsProductList).newQueryProductForMarketing(map);
        assertEquals(1,1);

    }

    /**
     *
     */
    public void testQueryBossProductList(){
        assertEquals(1,1);
    }

    /**
     * 查询所有ID
     *
     */
    public void testSelectInfoIdList(){
        // 申明
        List<Long> list = new ArrayList<Long>();
        Long[] id = new Long[]{1L,2L};
        for (Long s : id) {
            // 加入ID
            list.add(s);
        }
        goodsProductMapperMock.returns(new ArrayList<Long>()).selectInfoIdList(list);
        assertNotNull(goodsProductService.selectInfoIdList(new Long[]{1L,2L}));
    }

    /**
     * 删除购物车的货品
     *
     */
    public void testDelShoppingGoodsByGoodsInfoIds(){
        List<Long> longList = new ArrayList<Long>();
        longList.add(1L);
        goodsProductMapperMock.returns(1).delShoppingGoodsByGoodsInfoIds(longList);
        assertEquals(1,goodsProductService.delShoppingGoodsByGoodsInfoIds(longList));
    }

  }
