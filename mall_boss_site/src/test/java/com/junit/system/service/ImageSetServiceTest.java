package com.junit.system.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;

import com.ningpai.system.bean.ImageSet;
import com.ningpai.system.dao.ImageSetMapper;
import com.ningpai.system.service.ImageSetService;
import com.ningpai.system.service.impl.ImageSetServiceImpl;
import com.ningpai.system.util.SelectBean;
import com.ningpai.util.PageBean;

/**
 * 图片设置服务层接口测试类
 * @author djk
 *
 */
public class ImageSetServiceTest extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口
	 */
	@TestedObject
	private ImageSetService imageSetService = new ImageSetServiceImpl();
	
	/**
	 * 模拟MOCK
	 */
	@InjectIntoByType
	private Mock<ImageSetMapper> imageSetMapperMock;
	
	/**
	 * 分页辅助类
	 */
	private PageBean pageBean;
	
	/**
	 *  分页参数Bean
	 */
	private SelectBean selectBean;
	
	@Override
	protected void setUp() throws Exception
	{
		pageBean = new PageBean();
		selectBean = new SelectBean();
	}
	
	/**
	 *  分页查询图片规则测试
	 */
	@Test
	public void testFindByPageBean()
	{     
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startRowNum", pageBean.getStartRowNum());
        map.put("endRowNum", pageBean.getEndRowNum());
        map.put("condition", selectBean.getCondition());
        map.put("searchText", selectBean.getSearchText());
        imageSetMapperMock.returns(1).findTotalCount(selectBean);
        
        List<Object> lists = new ArrayList<>();
        lists.add(new Object());
        imageSetMapperMock.returns(lists).findByPageBean(map);
		assertEquals(1, imageSetService.findByPageBean(pageBean, selectBean).getList().size());
	}
	
	/**
	 *  添加图片设置信息测试
	 */
	@Test
	public void testInsertImageSet()
	{
		imageSetMapperMock.returns(1).insertSelective(new ImageSet());
		assertEquals(1, imageSetService.insertImageSet(new ImageSet()));
	}
	
	/**
	 *  删除图片设置信息测试
	 */
	@Test
	public void testDeleteImageSet()
	{
		String [] ids = {"1"};
		imageSetMapperMock.returns(1).deleteByPrimaryKey(1L);
		assertEquals(1, imageSetService.deleteImageSet(ids));
	}
	
	/**
	 * 修改图片设置信息测试
	 */
	@Test
	public void testUpdateImageSet()
	{
		imageSetMapperMock.returns(1).updateByPrimaryKeySelective(new ImageSet());
		assertEquals(1, imageSetService.updateImageSet(new ImageSet()));
	}
	
	/**
	 * 按照ID查询数据测试
	 */
	@Test
	public void testFindByRuleId()
	{
		imageSetMapperMock.returns(new ImageSet()).selectByPrimaryKey(1L);
		assertNotNull(imageSetService.findByRuleId(1L));
	}
	
}
