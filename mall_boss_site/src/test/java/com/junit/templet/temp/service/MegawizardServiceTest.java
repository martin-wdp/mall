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

import com.ningpai.temp.bean.Megawizard;
import com.ningpai.temp.dao.MegawizardMapper;
import com.ningpai.temp.service.MegawizardService;
import com.ningpai.temp.service.impl.MegawizardServiceImpl;
import com.ningpai.util.PageBean;

public class MegawizardServiceTest extends UnitilsJUnit3 
{
	/**
	 * 需要测试的接口类
	 */
	 @TestedObject
	 private MegawizardService megawizardService = new MegawizardServiceImpl();
	 
	 /**
	  *  模拟MOCK
      */
	 @InjectIntoByType
	 private Mock<MegawizardMapper> megawizardMapperMock;
	 
	 /**
	  * 页面说明
	  */
	 private Megawizard megawizard = new Megawizard();
	 
	 /**
	  * 新建页面说明测试
	  */
	 @Test
	 public void testInsert()
	 {
		 megawizardMapperMock.returns(1).insert(megawizard);
		 assertEquals(1, megawizardService.insert(megawizard));
	 }
	 
	 /**
	  *  根据模板id分页查询页面说明测试
	  */
	 @Test
	 public void testSelectByTempId()
	 {
		 PageBean pb = new PageBean();
		 Map<String, Object> map = new HashMap<String, Object>();
		 megawizardMapperMock.returns(1).selectCountByTempId(1);
         map.put("startRowNum", pb.getStartRowNum());
         map.put("endRowNum", pb.getEndRowNum());
         map.put("tempId", 1L);
         List<Object> list = new ArrayList<>();
         list.add(new Object());
         megawizardMapperMock.returns(list).selectByTempId(map);
		 assertEquals(1, megawizardService.selectByTempId(pb, 1L).getList().size());
	 }
	 
	 /**
	  * 根据id修改删除标记测试
	  */
	 @Test
	 public void testUpdateById()
	 {
		 String [] ids = {"1"};
		 List<Long> list = new ArrayList<>();
		 list.add(1L);
		 megawizardMapperMock.returns(1).updateById(list);
		 assertEquals(1, megawizardService.updateById(ids));
	 }
	 
	 /**
	  * 根据id修改页面说明内容测试
	  */
	 @Test
	 public void testUpdateByIdToContent()
	 {
		 megawizardMapperMock.returns(1).updateByIdToContent(megawizard);
		 assertEquals(1, megawizardService.updateByIdToContent(megawizard));
	 }
	 
	 /**
	  * 根据id查询单个页面说明测试
	  */
	 @Test
	 public void testSelectById()
	 {
		 megawizardMapperMock.returns(megawizard).selectById(1L);
		 assertNotNull(megawizardService.selectById(1L));
	 }
	 
	 /**
	  * 根据页面类型和模板ID查询单个页面说明测试
	  */
	 @Test
	 public void testSelectByType()
	 {
		  Map<String, Object> map = new HashMap<String, Object>();
          map.put("sort", 1);
          map.put("tempId", 1L);
          megawizardMapperMock.returns(megawizard).selectBySort(map);
		  assertNotNull(megawizardService.selectByType(1, 1L));
	 }
}
