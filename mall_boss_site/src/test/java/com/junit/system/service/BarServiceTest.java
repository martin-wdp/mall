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

import com.ningpai.system.bean.Bar;
import com.ningpai.system.dao.BarMapper;
import com.ningpai.system.service.BarService;
import com.ningpai.system.service.impl.BarServiceImpl;
import com.ningpai.system.util.SelectBean;
import com.ningpai.util.PageBean;

/**
 *  前台导航服务层接口测试类
 * @author djk
 *
 */
public class BarServiceTest   extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口
	 */
	@TestedObject
	private BarService barService = new BarServiceImpl();
	
	/**
	 * 模拟MOCK
	 */
	@InjectIntoByType
	private Mock<BarMapper> barMapperMock;
	
	/**
	 * 测试导航信息分页查询
	 */
	@Test
	public void testFindByPageBean()
	{
    	// 生成分页参数Bean
        SelectBean selectBean = new SelectBean();
        selectBean.setCondition("1");
        selectBean.setSearchText("千米");
        
        // 生成分页辅助类
        PageBean pb = new PageBean();
        pb.setStartRowNum(0);
        pb.setEndRowNum(15);

        // 设置参数
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("startRowNum", pb.getStartRowNum());
        map.put("endRowNum", pb.getEndRowNum());
        map.put("condition", selectBean.getCondition());
        map.put("searchText", selectBean.getSearchText());
        
        // 设置返回的对象
        List<Object> list = new ArrayList<>();
        list.add(new Object());
        
        barMapperMock.returns(1).findTotalCount(selectBean);
        barMapperMock.returns(list).findByPageBean(map);
		assertEquals(1, barService.findByPageBean(pb, selectBean).getList().size());
	}
	
	/**
	 * 测试添加导航信息
	 */
	@Test
	public void testInsertBar()
	{
		barMapperMock.returns(1).insertSelective(new Bar());
		assertEquals(1, barService.insertBar(new Bar()));
	}
	
	/**
	 * 测试删除导航信息
	 */
	@Test
	public void testDeleteBar()
	{
		String [] barIds = {"1"};
		barMapperMock.returns(1).deleteByPrimaryKey(1L);
		assertEquals(1, barService.deleteBar(barIds));
	}
	
	/**
	 * 查找单条导航信息测试
	 */
	@Test
	public void testFindByBarId()
	{
		barMapperMock.returns(new Bar()).selectByPrimaryKey(1L);
		assertNotNull(barService.findByBarId(1L));
	}
	
	/**
	 * 测试修改导航信息
	 */
	@Test
	public void testUpdateBar()
	{
		barMapperMock.returns(1).updateByPrimaryKeySelective(new Bar());
		assertEquals(1, barService.updateBar(new Bar()));
	}
			
	
}
