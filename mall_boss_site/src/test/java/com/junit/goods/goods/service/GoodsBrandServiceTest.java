
package com.junit.goods.goods.service;

import com.ningpai.excel.ImportGoods;
import com.ningpai.goods.bean.GoodsBrand;
import com.ningpai.goods.dao.CascDelMapper;
import com.ningpai.goods.dao.GoodsBrandMapper;
import com.ningpai.goods.service.GoodsBrandService;
import com.ningpai.goods.service.impl.GoodsBrandServiceImpl;
import com.ningpai.goods.util.SelectBean;
import com.ningpai.goods.util.ValueUtil;
import com.ningpai.util.PageBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartHttpServletRequest;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品品牌service测试
 */
public class GoodsBrandServiceTest  extends UnitilsJUnit3 {

    @InjectIntoByType
    Mock<GoodsBrandMapper> goodsBrandMapperMock;

    @InjectIntoByType
    Mock<CascDelMapper>  cascDelMapperMock;

    @InjectIntoByType
    private Mock<ImportGoods> importGoodsMock;

    @TestedObject
    private GoodsBrandService goodsBrandService=new GoodsBrandServiceImpl();

    private static List<GoodsBrand> goodsBrandsList=new ArrayList<>();

    private static GoodsBrand  goodsBrand =new GoodsBrand();
     /**
     * 根据主键删除商品品牌
     */
   public void testDeleteGoodsBrand(){
       Map<String, String> map = new HashMap<String, String>();
       map.put("brandId", "1");
       map.put("del_name", "1");
       goodsBrandMapperMock.returns(1).deleteByPrimaryKey(map);
       assertEquals(1,goodsBrandService.deleteGoodsBrand(1L,"1"));
   }

    /**
     * 批量删除商品品牌
     *
     */
     public void  testBatchDeleteGodosBrand(){
         // 定义一个HashMap集合
         Map<String, String> map = new HashMap<String, String>();
         map.put("brandId", "1");
         map.put("del_name", "1");
         goodsBrandMapperMock.returns(1).deleteByPrimaryKey(map);
         assertEquals(null,goodsBrandService.batchDeleteGodosBrand(new Long[]{1L},"1"));
     }

    /**
     * 更新商品品牌信息
     */
    public void testUpdateGoodsBrad(){
        goodsBrandMapperMock.returns(1).updateByPrimaryKeySelective(goodsBrand);
        assertEquals(1,goodsBrandService.updateGoodsBrad(goodsBrand,"1"));
    }

    /**
     * 插入一条商品品牌信息
     */
     public void testInsertGoodsBrand(){


         // 为品牌创建者赋值
         goodsBrand.setBrandCreateName("1");
         // 设置品牌删除标记
         goodsBrand.setBrandDelflag("0");
         goodsBrand.setBrandName("1");
         goodsBrand.setBrandDesc(goodsBrand.getBrandName());
         goodsBrand.setBrandSeoDesc(goodsBrand.getBrandName());
         goodsBrand.setBrandSeoKeyword(goodsBrand.getBrandName());
         goodsBrand.setBrandSeoTitle(goodsBrand.getBrandName());
         goodsBrandMapperMock.returns(1).insertSelective(goodsBrand);
         assertEquals(1,goodsBrandService.insertGoodsBrand(goodsBrand,"1"));
  }

    /**
     * 根据PageBean 查询分页列表
     */
   public void testQueryByPageBean(){
       Map<String, Integer> map = new HashMap<String, Integer>();
       map.put(ValueUtil.STARTROWNUM, 0);
       map.put(ValueUtil.ENDROWNUM, 15);
       goodsBrandMapperMock.returns(1).queryTotalCount();
       goodsBrandMapperMock.returns(goodsBrandsList).queryByPageBean(map);
       assertNotNull(goodsBrandService.queryByPageBean(new PageBean()));
   }

    /**
     * 根据Id查询商品品牌
     *
     */
   public void testQueryBrandById(){
    goodsBrandMapperMock.returns(goodsBrand).selectByPrimaryKey(1L);
    assertEquals(goodsBrand,goodsBrandService.queryBrandById(1L));
   }

    /**
     * 查询所有的商品品牌
     */
   public  void testQueryAllBrand(){

       goodsBrandMapperMock.returns(goodsBrandsList).queryAllBrand();
       assertEquals(goodsBrandsList,goodsBrandService.queryAllBrand());
   }

    /**
     * 根君名称查询商品品牌
     */
    public  void testQueryallbrandbyName(){
      goodsBrandMapperMock.returns(goodsBrandsList).queryallbrandbyName("1");
      assertEquals(goodsBrandsList,goodsBrandService.queryallbrandbyName("1"));
  }

    /**
     * 参数查询分页bean
     */
    public void testSearchByPageBean(){
        SelectBean selectBean=new SelectBean();
        Map<String, Object> map = new HashMap<String, Object>();
            // 设置开始行数
            map.put(ValueUtil.STARTROWNUM, 0);
            // 设置结束行数
            map.put(ValueUtil.ENDROWNUM,15);
            map.put(ValueUtil.CONDITION, selectBean.getCondition());
            map.put(ValueUtil.SEARCHTEXT, selectBean.getSearchText());
            map.put(ValueUtil.SELECTBEAN, selectBean);
        goodsBrandMapperMock.returns(1).searchTotalCount(selectBean);
        goodsBrandMapperMock.returns(goodsBrandsList).searchAllBrand(map);
        assertNotNull(goodsBrandService.searchByPageBean(new PageBean(),selectBean));
  }

    /**
     * 查询所有品牌
     * 
     */
    public void testQueryAllBrandList(){
     goodsBrandMapperMock.returns(goodsBrandsList).queryAllBrandList();
     assertEquals(goodsBrandsList,goodsBrandService.queryAllBrandList());
 }

    /**
     * 验证品牌名称是否可用
     */
    public void testCheckBrandName(){
      goodsBrandMapperMock.returns(1).selectByBrandName("1");
      assertEquals(false,goodsBrandService.checkBrandName("1"));
  }

    /**
     * 导出商品品牌
     */
    public void testExportGoodsBrand(){
      HttpServletResponse response = new MockHttpServletResponse();
      goodsBrandMapperMock.returns(goodsBrandsList).queryAllBrandList();
      goodsBrandService.exportGoodsBrand(response);
  }

    /**
     * 导出商品品牌模板
     */
  public void testExportGoodsBrandTemp(){
      HttpServletResponse response = new MockHttpServletResponse();
      goodsBrandService.exportGoodsBrandTemp(response);
  }

    /**
     * 导入商品品牌
     */
  public void testImportGoodsBrandByExcel(){
    assertEquals("401",goodsBrandService.importGoodsBrandByExcel(new MockHttpServletRequest(),new MockHttpServletResponse(),new MockMultipartHttpServletRequest()));
  }

    /**
     * 验证品牌名称，不可重复添加
     */
  public void testSelectByBrandName(){
      goodsBrandMapperMock.returns(1).selectByBrandName("1");
      assertEquals(1,goodsBrandService.selectByBrandName("1"));
  }
}
