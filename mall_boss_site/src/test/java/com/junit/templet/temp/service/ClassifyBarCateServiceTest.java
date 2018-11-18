package com.junit.templet.temp.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;

import com.ningpai.temp.bean.ClassifyBarCate;
import com.ningpai.temp.dao.ClassifyBarCateMapper;
import com.ningpai.temp.service.ClassifyBarCateService;
import com.ningpai.temp.service.impl.ClassifyBarCateServiceImpl;

public class ClassifyBarCateServiceTest extends UnitilsJUnit3 
{
	/**
	 * 需要测试的接口类
	 */
	 @TestedObject
	 private ClassifyBarCateService classifyBarCateService = new ClassifyBarCateServiceImpl();
	 
	 /**
	  *  模拟MOCK
      */
	 @InjectIntoByType
	 private Mock<ClassifyBarCateMapper> classifyBarCateMapperMock;
	 
	 /**
	  * 根据分类导航ID查询测试
	  */
	 @Test
	 public void testSelectByClassifyBarId()
	 {
		 List<ClassifyBarCate>  list = new ArrayList<>();
		 list.add(new ClassifyBarCate());
		 classifyBarCateMapperMock.returns(list).selectByClassifyBarId(1L);
		 assertEquals(1, classifyBarCateService.selectByClassifyBarId(1L).size());
		 
	 }
}
