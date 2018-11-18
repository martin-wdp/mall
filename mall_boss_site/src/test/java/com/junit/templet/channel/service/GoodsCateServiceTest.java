package com.junit.templet.channel.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;

import com.ningpai.channel.bean.GoodsCate;
import com.ningpai.channel.dao.GoodsCateMapper;
import com.ningpai.channel.service.GoodsCateService;
import com.ningpai.channel.service.impl.GoodsCateServiceImpl;

/**
 * 商品分类serivce接口测试
 * @author djk
 *
 */
public class GoodsCateServiceTest extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口类
	 */
	 @TestedObject
	 private GoodsCateService goodsCateService = new GoodsCateServiceImpl();
	 
	 /**
	 *  模拟MOCK
	 */
	 @InjectIntoByType
	 private Mock<GoodsCateMapper> goodsCateMapperMock;
	 
	 /**
	  * 商品分类实体类
	  */
	 private GoodsCate goodsCate = new GoodsCate();
	 
	 /**
	  * 根据主键查询商品分类测试
	  */
	 @Test
	 public void testSelectGoosCateById()
	 {
		 goodsCateMapperMock.returns(goodsCate).selectGoosCateById(1L);
		 assertNotNull(goodsCateService.selectGoosCateById(1L));
	 }
	 
	 /**
	  * 查询所有的商品一级分类列表测试
	  */
	 @Test
	 public void testQueryAllFirstGradeGoosCate()
	 {
		 List<GoodsCate> lists = new ArrayList<>();
		 lists.add(goodsCate);
		 goodsCateMapperMock.returns(lists).queryAllFirstGradeGoosCate();
		 assertEquals(1, goodsCateService.queryAllFirstGradeGoosCate().size());
	 }
	 
	 /**
	  *  根据父ID查询商品分类测试
	  */
	 @Test
	 public void testQueryGoosCateByParentId()
	 {
		 List<GoodsCate> lists = new ArrayList<>();
		 lists.add(goodsCate);
		 goodsCateMapperMock.returns(lists).queryGoosCateByParentId(1L);
		 assertEquals(1, goodsCateService.queryGoosCateByParentId(1L).size());
	 }
}
