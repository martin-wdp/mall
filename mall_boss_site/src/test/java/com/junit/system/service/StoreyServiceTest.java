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

import com.ningpai.system.bean.GoodsCateSystem;
import com.ningpai.system.bean.Storey;
import com.ningpai.system.dao.StoreyMapper;
import com.ningpai.system.service.StoreyService;
import com.ningpai.system.service.impl.StoreyServiceImpl;
import com.ningpai.system.util.SelectBean;
import com.ningpai.util.PageBean;

/**
 *  楼层服务层接口测试
 * @author djk
 *
 */
public class StoreyServiceTest extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口
	 */
	@TestedObject
	private StoreyService storeyService = new StoreyServiceImpl();
	
	/**
	 * 模拟MOCK
	 */
	@InjectIntoByType
	private Mock<StoreyMapper> storeyMapperMock;
	
	/**
	 *  分页查询楼层信息测试
	 */
	@Test
	public void testFindByPageBean()
	{
		PageBean pageBean = new PageBean();
		SelectBean selectBean = new SelectBean();
		storeyMapperMock.returns(1).findTotalCount(selectBean);
		Map<String, Object> maps = new HashMap<String, Object>();
        maps.put("startRowNum", pageBean.getStartRowNum());
        maps.put("endRowNum", pageBean.getEndRowNum());
        maps.put("condition", selectBean.getCondition());
        maps.put("searchText", selectBean.getSearchText());
        List<Object> lists = new ArrayList<>();
        lists.add(new Object());
        storeyMapperMock.returns(lists).findByPageBean(maps);
		assertEquals(1, storeyService.findByPageBean(pageBean, selectBean).getList().size());
	}
	
	/**
	 * 查询所有商品父分类测试
	 */
	@Test
	public void testFindParentGoodsCate()
	{
		List<GoodsCateSystem> lists = new ArrayList<>();
		lists.add(new GoodsCateSystem());
		storeyMapperMock.returns(lists).findParentGoodsCate();
		assertEquals(1, storeyService.findParentGoodsCate().size());
	}
	
	/**
	 * 添加一条楼层信息测试
	 */
	@Test
	public void testInsertStorey()
	{
		storeyMapperMock.returns(1).insertSelective(new Storey());
		assertEquals(1, storeyService.insertStorey(new Storey()));
	}
	
	/**
	 * 根据楼层层数查询楼层信息测试
	 */
	@Test
	public void testFindStoreyByFloorId()
	{
		storeyMapperMock.returns(new Storey()).findStoreyByFloorId(1L);
		assertNotNull(storeyService.findStoreyByFloorId(1L));
	}
	
	/**
	 * 删除楼层信息测试
	 */
	@Test
	public void testDeleteStorey()
	{
		String [] ids = {"1"};
		storeyMapperMock.returns(1).deleteByPrimaryKey(1L);
		assertEquals(1, storeyService.deleteStorey(ids));
	}
	
	/**
	 * 根据ID查询楼层信息测试
	 */
	@Test
	public void testFindStoreyById()
	{
		storeyMapperMock.returns(new Storey()).selectByPrimaryKey(1L);
		assertNotNull(storeyService.findStoreyById(1L));
	}
	
	/**
	 *  修改楼层信息测试
	 */
	@Test
	public void testUpdateStorey()
	{
		storeyMapperMock.returns(1).updateByPrimaryKeySelective(new Storey());
		assertEquals(1, storeyService.updateStorey(new Storey()));
	}
}
