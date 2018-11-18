package com.junit.system.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;

import com.ningpai.system.bean.ImageCate;
import com.ningpai.system.dao.ImageCateMapper;
import com.ningpai.system.service.ImageCateService;
import com.ningpai.system.service.impl.ImageCateServiceImpl;

/**
 *  图片分类服务层接口测试类
 * @author djk
 *
 */
public class ImageCateServiceTest  extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口
	 */
	@TestedObject
	private ImageCateService imageCateService = new ImageCateServiceImpl();
	
	/**
	 * 模拟MOCK
	 */
	@InjectIntoByType
	private Mock<ImageCateMapper> imageCateMapperMock;
	
	/**
	 * 查询所有分类
	 */
	@Test
	public void testFindCateAll()
	{
	    List<ImageCate>  lists = new ArrayList<>();
	    lists.add(new ImageCate());
	    imageCateMapperMock.returns(lists).findImageCate();
	    assertEquals(1, imageCateService.findCateAll().size());
	}
}
