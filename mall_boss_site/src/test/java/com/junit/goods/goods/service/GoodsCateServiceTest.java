
package com.junit.goods.goods.service;

import com.ningpai.excel.ImportGoods;
import com.ningpai.goods.bean.GoodsCate;
import com.ningpai.goods.dao.CascDelMapper;
import com.ningpai.goods.dao.GoodsCateMapper;
import com.ningpai.goods.service.GoodsCateService;
import com.ningpai.goods.service.impl.GoodsCateServiceImpl;
import com.ningpai.goods.util.SelectBean;
import com.ningpai.goods.util.ValueUtil;
import com.ningpai.goods.vo.GoodsCateVo;
import com.ningpai.util.PageBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartHttpServletRequest;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 商品分类serivce 测试
 */
public class GoodsCateServiceTest  extends UnitilsJUnit3 {

   private static GoodsCate goodsCate=new GoodsCate();
   private static List<GoodsCate> goodsCateList=new ArrayList<>();
   private static GoodsCateVo goodsCateVo=new GoodsCateVo();
   private static List<GoodsCateVo> goodsCateVoList=new ArrayList<>();

    @InjectIntoByType
    Mock<GoodsCateMapper> goodsCateMapperMock;

    @InjectIntoByType
    Mock<CascDelMapper> cascDelMapperMock;

    @InjectIntoByType
    Mock<ImportGoods> importGoodsMock;

    @TestedObject
    GoodsCateService goodsCateService=new GoodsCateServiceImpl();
    /**
     * 添加一个商品分类
     */
    public void testInsertGoodsCate(){
        goodsCate.setCatParentId(1L);
        goodsCate.setCatGrade(1);
        goodsCateVo.setCatGrade(1);
        goodsCateMapperMock.returns(goodsCateVo).selectByPrimaryKey(1L);
        goodsCateMapperMock.returns(1).insertSelective(goodsCate);
        assertEquals(1, goodsCateService.insertGoodsCate(goodsCate, "1"));
 }

    /**
     * 根据主键ID删除记录
     */
  public void testDelGoodsCate(){

      Map<String, String> map = new HashMap<String, String>();
      map.put("delName", "1");
      map.put("catId", "1");
          // 根据主键ID删除记录
      goodsCateMapperMock.returns(1).deleteByPrimaryKey(map);
      assertEquals(1,goodsCateService.delGoodsCate(1L,"1"));
  }

    /**
     * 批量删除商品分类
     */
  public void testBatchDelGoodsCate(){
      Map<String, String> map = new HashMap<String, String>();
      map.put("delName", "1");
      map.put("catId", "1");
      goodsCateMapperMock.returns(1).deleteByPrimaryKey(map);
      assertEquals(1,goodsCateService.batchDelGoodsCate(new Long[]{1L},"1"));
  }

    /**
     * 更新商品分类信息
     */
  public void testUpdateGoodsCate(){
      goodsCate.setCatParentId(1L);
      goodsCate.setCatGrade(1);
      goodsCateVo.setCatGrade(1);
      goodsCateMapperMock.returns(1).updateByPrimaryKey(goodsCate);
      goodsCateMapperMock.returns(goodsCateVo).selectByPrimaryKey(1L);
      assertEquals(0, goodsCateService.updateGoodsCate(goodsCate,"1"));
  }

    /**
     * 根据分页帮助类查询分页列表
     */
  public void testQueryCateListByPageBean(){
      Map<String, Object> map = new HashMap<String, Object>();
      goodsCateMapperMock.returns(1).queryTotalCount(map);
      map.put(ValueUtil.STARTROWNUM, 1);
      map.put(ValueUtil.ENDROWNUM, 15);
      goodsCateMapperMock.returns(goodsCateVoList).queryByPageBean(map);
      assertNotNull(goodsCateService.queryCateListByPageBean(new PageBean(),new SelectBean()));


  }

    /**
     * 根据主键ID查询
     */
    public  void testQueryGoodsCateById(){
        goodsCateMapperMock.returns(goodsCateVo).selectByPrimaryKey(1L);
        assertEquals(goodsCateVo,goodsCateService.queryGoodsCateById(1L));
    }

    /**
     * 获得分类下的所有的子分类
     */
    public void testGetCateList(){
        Map<String, Object> map = new HashMap<String, Object>();
        goodsCateMapperMock.returns(1).queryTotalCount(map);
        map.put(ValueUtil.STARTROWNUM, 1);
        map.put(ValueUtil.ENDROWNUM, 15);
        goodsCateMapperMock.returns(goodsCateVoList).queryByPageBean(map);
        goodsCateMapperMock.returns(goodsCateVoList).queryAllGoosCate();
        assertEquals(goodsCateVoList,goodsCateService.getCateList(new PageBean(),new SelectBean()));
 }


    /**
     * 查询所有的商品分类
     */
   public void testQueryAllCate(){
       goodsCateMapperMock.returns(goodsCateVoList).queryAllGoosCate();
       assertEquals(goodsCateVoList,goodsCateService.queryAllCate());
   }

    /**
     * 验证是否可以删除 如果传递过来的分类 下面有子类 就返回false表示不可以删除
     */
   public void testCheckDelWithCateId(){
       goodsCateMapperMock.returns(1).querySonCountByParentId(1L);
       assertEquals(false,goodsCateService.checkDelWithCateId(1L));
   }

    /**
     * 根据分类名称查询商品分类
     */
  public void testQueryCateByCateName(){
      goodsCateMapperMock.returns(goodsCate).queryCateByCateName("1");
      assertEquals(goodsCate,goodsCateService.queryCateByCateName("1"));
  }


    /**
     * 查询所有分类
     */
  public void testQueryAllGoodCate(){
      goodsCateMapperMock.returns(goodsCateList).queryAllGoodCate();
      assertEquals(goodsCateList,goodsCateService.queryAllGoodCate());
  }

    /**
     * 根据父分类ID 查询子分类列表
     */
  public  void testQuerySonCateByParentId(){
      goodsCateMapperMock.returns(goodsCateList).querySonCatByParentId(1L);
      assertEquals(goodsCateList,goodsCateService.querySonCateByParentId(1L));
  }

    /**
     * 根据父分类ID 查询子分类列表
     */
  public void testQuerySonCateByParentIdAndName(){
      Map<String, Object> paramMap = new HashMap<String, Object>();
      paramMap.put("cateId", 1L);
      paramMap.put("cateName", "1");
      goodsCateMapperMock.returns(goodsCateList).querySonCatByParm(paramMap);
      assertEquals(goodsCateList,goodsCateService.querySonCateByParentIdAndName(1L,"1"));
  }

    /**
     * 根据父分类ID 查询子分类列表
     */
 public void testQuerySonCateVoByParentIdAndName(){
     Map<String, Object> paramMap = new HashMap<String, Object>();
     paramMap.put("cateId", 1L);
     paramMap.put("cateName", "1");
     goodsCateMapperMock.returns(goodsCateVoList).querySonCatVoByParm(paramMap);
     assertEquals(goodsCateVoList,goodsCateService.querySonCateVoByParentIdAndName(1L,"1"));
 }

    /**
     * 查询所有三级分类
     *
     */
  public void testQueryAllGoodThirdCate(){
      goodsCateMapperMock.returns(goodsCateList).queryAllGoodThirdCate();
      assertEquals(goodsCateList,goodsCateService.queryAllGoodThirdCate());
  }

    /**
     * 导出商品分类
     */
  public  void testExportGoodsCate(){
      goodsCateMapperMock.returns(goodsCateList).queryAllGoodCate();
      goodsCateService.exportGoodsCate(new MockHttpServletResponse());

  }

    /**
     * 导出商品分类模板
     */
  public  void  testExportGoodsCateTemp(){
    goodsCateService.exportGoodsCateTemp(new MockHttpServletResponse());
  }

    /**
     * 导入商品分类
     */
 public void testImportGoodsCateByExcel(){
     assertEquals("401",goodsCateService.importGoodsCateByExcel(new MockHttpServletRequest(),new MockHttpServletResponse(),new MockMultipartHttpServletRequest()));
 }

}
