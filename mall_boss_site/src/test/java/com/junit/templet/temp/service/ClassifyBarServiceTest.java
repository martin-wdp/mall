package com.junit.templet.temp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;

import com.ningpai.channel.bean.GoodsCate;
import com.ningpai.channel.service.GoodsCateService;
import com.ningpai.temp.bean.ClassifyBar;
import com.ningpai.temp.bean.ClassifyBarCate;
import com.ningpai.temp.bean.ClassifyBarQuick;
import com.ningpai.temp.dao.ClassifyBarCateMapper;
import com.ningpai.temp.dao.ClassifyBarMapper;
import com.ningpai.temp.dao.ClassifyBarQuickMapper;
import com.ningpai.temp.service.ClassifyBarService;
import com.ningpai.temp.service.impl.ClassifyBarServiceImpl;
import com.ningpai.temp.vo.ClassifyBarVo;
import com.ningpai.util.PageBean;


public class ClassifyBarServiceTest extends UnitilsJUnit3 
{
	/**
	 * 需要测试的接口类
	 */
	 @TestedObject
	 private ClassifyBarService classifyBarService = new ClassifyBarServiceImpl();
	 
	 /**
	  *  模拟MOCK
      */
	 @InjectIntoByType
	 private Mock<ClassifyBarMapper> classifyBarMapperMock;
	 
	 /**
	  *  模拟MOCK
      */
	 @InjectIntoByType
	 private Mock<ClassifyBarCateMapper> classifyBarCateMapperMock;
	 
	 /**
	  *  模拟MOCK
      */
	 @InjectIntoByType
	 private Mock<ClassifyBarQuickMapper> classifyBarQuickMapperMock;
	 
	 /**
	  *  模拟MOCK
      */
	 @InjectIntoByType
	 private Mock<GoodsCateService> goodsCateServiceMock;
	 
	 /**
	  *  实体类-分类导航
	  */
	 private ClassifyBar classifyBar = new ClassifyBar();
	 
	 /**
	  * 删除分类导航测试
	  */
	 @Test
	 public void testDeleteClassifyBar()
	 {
		 classifyBarMapperMock.returns(1).deleteByPrimaryKey(1L);
		 assertEquals(1, classifyBarService.deleteClassifyBar(1L));
	 }
	 
	 /**
	  *  添加分类导航测试
	  */
	 @Test
	 public void testSaveClassifyBar()
	 {
		 
		 classifyBar.setGoodsCatId(1L);
		 classifyBar.setGrade(3);
		 GoodsCate cate = new GoodsCate();
		 cate.setCatParentId(1L);
		 goodsCateServiceMock.returns(cate).selectGoosCateById(1L);
		 goodsCateServiceMock.returns(cate).selectGoosCateById(1L);
		 classifyBarMapperMock.returns(1).insertSelective(classifyBar);
		 assertEquals(1, classifyBarService.saveClassifyBar(classifyBar));
	 }
	 
	 /**
	  * 修改分类导航测试
	  */
	 @Test
	 public void testUpdateClassifyBar()
	 {
		 classifyBar.setGoodsCatId(1L);
		 classifyBar.setGrade(3);
		 GoodsCate cate = new GoodsCate();
		 cate.setCatParentId(1L);
		 goodsCateServiceMock.returns(cate).selectGoosCateById(1L);
		 goodsCateServiceMock.returns(cate).selectGoosCateById(1L);
		 classifyBarMapperMock.returns(1).updateByPrimaryKeySelective(classifyBar);
		 assertEquals(1, classifyBarService.updateClassifyBar(classifyBar));
	 }
	 
	 /**
	  * 根据主键查询分类导航测试
	  */
	 @Test
	 public void testGetClassifyBarById()
	 {
		 classifyBarMapperMock.returns(classifyBar).selectByPrimaryKey(1L);
		 assertNotNull(classifyBarService.getClassifyBarById(1L));
	 }
	 
	 /**
	  * 根据分页参数和频道ID、模板ID查询分类导航测试
	  */
	 @Test
	 public void testSelectClassifyBarByParam()
	 {
		 PageBean pb = new PageBean();
         Map<String, Object> map = new HashMap<String, Object>();
         map.put("channelId", 1L);
         map.put("tempId", 1L);
         map.put("temp1", "a");
         classifyBarMapperMock.returns(1).selectClassifyBarCountByParam(map);
         map.put("startRowNum", pb.getStartRowNum());
         map.put("endRowNum", pb.getEndRowNum());
         List<Object> lists = new ArrayList<>();
         lists.add(new Object());
         classifyBarMapperMock.returns(lists).selectClassifyBarByParam(map);
		 assertEquals(1, classifyBarService.selectClassifyBarByParam(pb, 1l, 1l, "a").getList().size());
	 }
	 
	 /**
	  *  根据频道ID、模板ID查询分类导航 测试
	  */
	 @Test
	 public void testSelectClassifyBarByParamSite()
	 {
		 List<ClassifyBarVo> lists = new ArrayList<>();
		 lists.add(new ClassifyBarVo());
		 Map<String, Object> map = new HashMap<String, Object>();
         map.put("channelId", 1L);
         map.put("tempId", 1L);
         map.put("temp1", "a");
         List<Object> list = new ArrayList<>();
         list.add(new Object());
         classifyBarMapperMock.returns(list).selectClassifyBarByParamSite(map);
		 assertEquals(1, classifyBarService.selectClassifyBarByParamSite(1L, 1L, "a").size());
	 }
	 
	 /**
	  * 添加分类导航，并添加关联的商品分类和快捷分类
	  */
	 @Test
	 public void testSaveClassifyBarAndCateAndQuick()
	 {
		 List<GoodsCate> twolist  = new ArrayList<>();
		 GoodsCate goodsCate = new GoodsCate();
		 goodsCate.setCatParentId(1L);
		 goodsCate.setCatId(1L);
		 twolist.add(goodsCate);
		 List<GoodsCate> thirdlist = new ArrayList<>();
		 thirdlist.add(goodsCate);
		 ClassifyBar record = new ClassifyBar();
		 List<ClassifyBarCate> barCates = new ArrayList<>();
		 ClassifyBarCate cate = new ClassifyBarCate();
		 cate.setCateId(1L);
		 barCates.add(cate);
		 ClassifyBarQuick classifyBarQuick = new ClassifyBarQuick();
		 classifyBarQuick.setCateId(1L);
		 List<ClassifyBarQuick> barQuicks = new ArrayList<>();
		 barQuicks.add(classifyBarQuick);
		 classifyBarMapperMock.returns(1).insertSelective(classifyBar);
		 goodsCateServiceMock.returns(twolist).queryGoosCateByParentId(1L);
		 goodsCateServiceMock.returns(thirdlist).queryGoosCateByParentId(1L);
		 classifyBarCateMapperMock.returns(1).insertSelective(cate);
		 goodsCateServiceMock.returns(goodsCate).selectGoosCateById(1L);
		 goodsCateServiceMock.returns(goodsCate).selectGoosCateById(1L);
		 classifyBarQuickMapperMock.returns(1).insertSelective(classifyBarQuick);
		 assertEquals(1, classifyBarService.saveClassifyBarAndCateAndQuick(record, barCates, barQuicks));
	 }
	 
	 /**
	  *  修改分类导航，并删除原关联的商品分类和快捷分类重新添加测试
	  */
	 @Test
	 public void testUpdateClassifyBarAndCateAndQuick()
	 {
		 List<GoodsCate> twolist  = new ArrayList<>();
		 GoodsCate goodsCate = new GoodsCate();
		 goodsCate.setCatParentId(1L);
		 goodsCate.setCatId(1L);
		 twolist.add(goodsCate);
		 List<GoodsCate> thirdlist = new ArrayList<>();
		 thirdlist.add(goodsCate);
		 ClassifyBar record = new ClassifyBar();
		 List<ClassifyBarCate> barCates = new ArrayList<>();
		 ClassifyBarCate cate = new ClassifyBarCate();
		 cate.setCateId(1L);
		 barCates.add(cate);
		 ClassifyBarQuick classifyBarQuick = new ClassifyBarQuick();
		 classifyBarQuick.setCateId(1L);
		 List<ClassifyBarQuick> barQuicks = new ArrayList<>();
		 barQuicks.add(classifyBarQuick);
		 classifyBarCateMapperMock.returns(1).batchDeleteByClassifyBarId(1L);
		 classifyBarQuickMapperMock.returns(1).batchDeleteByClassifyBarId(1L);
		 classifyBarMapperMock.returns(1).updateByPrimaryKeySelective(classifyBar);
		 goodsCateServiceMock.returns(twolist).queryGoosCateByParentId(1L);
		 goodsCateServiceMock.returns(thirdlist).queryGoosCateByParentId(1L);
		 classifyBarCateMapperMock.returns(1).insertSelective(cate);
		 goodsCateServiceMock.returns(goodsCate).selectGoosCateById(1L);
		 goodsCateServiceMock.returns(goodsCate).selectGoosCateById(1L);
		 classifyBarQuickMapperMock.returns(1).insertSelective(classifyBarQuick);
		 assertEquals(1, classifyBarService.updateClassifyBarAndCateAndQuick(record, barCates, barQuicks));
	 }
	 
	 /**
	  * 删除分类导航，并删除原关联的商品分类和快捷分类重新添加测试
	  */
	 @Test
	 public void testDeleteClassifyBarAndCateAndQuick()
	 {
		 classifyBarMapperMock.returns(1).deleteByPrimaryKey(1L);
		 classifyBarCateMapperMock.returns(1).batchDeleteByClassifyBarId(1L);
		 classifyBarQuickMapperMock.returns(1).batchDeleteByClassifyBarId(1L);
		 assertEquals(1, classifyBarService.deleteClassifyBarAndCateAndQuick(1L));
	 }
	 
	 /**
	  * 调用存储过程级联删除分类导航测试
	  */
	 @Test
	 public void testDeleteByPrimaryKeyAndPro()
	 {
		 classifyBarMapperMock.returns(1).deleteByPrimaryKeyAndPro(1L);
		 assertEquals(1, classifyBarService.deleteByPrimaryKeyAndPro(1L));
	 }
	 
	 /**
	  * 删除店铺分类导航测试
	  */
	 @Test
	 public void testDeleteClassBarById()
	 {
	      Map<String, Object> map = new HashMap<String, Object>();
          map.put("classifyBarId", 1L);
          map.put("temp1", "1");
          classifyBarMapperMock.returns(1).deleteClassBarById(map);
		  assertEquals(1, classifyBarService.deleteClassBarById(1L, 1L));
	 }
}
