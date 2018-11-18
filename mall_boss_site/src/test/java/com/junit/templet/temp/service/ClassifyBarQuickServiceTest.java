package com.junit.templet.temp.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;

import com.ningpai.temp.bean.ClassifyBarQuick;
import com.ningpai.temp.dao.ClassifyBarQuickMapper;
import com.ningpai.temp.service.ClassifyBarQuickService;
import com.ningpai.temp.service.impl.ClassifyBarQuickServiceImpl;

/**
 *  SERVICE-分类导航关联快捷分类测试
 * @author djk
 *
 */
public class ClassifyBarQuickServiceTest extends UnitilsJUnit3 
{
	/**
	 * 需要测试的接口类
	 */
	 @TestedObject
	 private ClassifyBarQuickService classifyBarQuickService = new ClassifyBarQuickServiceImpl();
	 
	 /**
	  *  模拟MOCK
      */
	 @InjectIntoByType
	 private Mock<ClassifyBarQuickMapper> classifyBarQuickMapperMock;
	 
	 /**
	  * 根据分类导航ID查询测试
	  */
	 @Test
	 public void  testSelectByClassifyBarId()
	 {
		 List<ClassifyBarQuick> list = new ArrayList<>();
		 list.add(new ClassifyBarQuick());
		 classifyBarQuickMapperMock.returns(list).selectByClassifyBarId(1L);
		 assertEquals(1, classifyBarQuickService.selectByClassifyBarId(1L).size());
	 }

}
