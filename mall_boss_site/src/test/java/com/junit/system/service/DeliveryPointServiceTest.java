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

import com.ningpai.system.bean.DeliveryPoint;
import com.ningpai.system.dao.DeliveryPointMapper;
import com.ningpai.system.service.DeliveryPointService;
import com.ningpai.system.service.DistrictService;
import com.ningpai.system.service.impl.DeliveryPointServiceImpl;
import com.ningpai.system.vo.DistrictVo;
import com.ningpai.util.PageBean;

/**
 *  SERVICE-自提点测试类
 * @author djk
 *
 */
public class DeliveryPointServiceTest  extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口
	 */
	@TestedObject
	private DeliveryPointService deliveryPointService = new DeliveryPointServiceImpl();
	
	/**
	 * 模拟MOCK
	 */
	@InjectIntoByType
	private Mock<DeliveryPointMapper> deliveryPointMapperMock;
	
	/**
	 * 模拟MOCK
	 */
	@InjectIntoByType
	private Mock<DistrictService> districtServiceMock;
	
	/**
	 * 删除测试
	 */
	@Test
	public void testDeleteDeliveryPoint()
	{
		deliveryPointMapperMock.returns(1).deleteByPrimaryKey(1L);
		assertEquals(1, deliveryPointService.deleteDeliveryPoint(1L));
	}
	
	/**
	 * 批量删除测试
	 */
	@Test
	public void testBatchDelDeliveryPoint()
	{
		Long [] ids = {1L};
		deliveryPointMapperMock.returns(1).batchDeleteByPrimaryKey(ids);
		assertEquals(1, deliveryPointService.batchDelDeliveryPoint(ids));
	}
	
	/**
	 * 添加自提点测试
	 */
	@Test
	public void testSaveDeliveryPoint()
	{
		deliveryPointMapperMock.returns(1).insertSelective(new DeliveryPoint());
		assertEquals(1, deliveryPointService.saveDeliveryPoint(new DeliveryPoint()));
	}
	
	/**
	 * 根据ID查询自提点测试
	 */
	@Test
	public void testGetDeliveryPoint()
	{
		deliveryPointMapperMock.returns(new DeliveryPoint()).selectByPrimaryKey(1L);
		assertNotNull(deliveryPointService.getDeliveryPoint(1L));
	}
	
	/**
	 * 修改测试
	 */
	@Test
	public void testUpdateDeliveryPoint()
	{
		deliveryPointMapperMock.returns(1).updateByPrimaryKeySelective(new DeliveryPoint());
		assertEquals(1, deliveryPointService.updateDeliveryPoint(new DeliveryPoint()));
	}

	/**
	 *  根据区县ID分页查询所有未删除的
	 */
	@Test
	public void testSelectAllDeliveryPointByPb()
	{
		PageBean pb = new PageBean();
		
		Map<String, Object> map = new HashMap<String, Object>();
	    map.put("districtId", 1L);
	    deliveryPointMapperMock.returns(1).selectAllCount(map);
        map.put("startRowNum", pb.getStartRowNum());
        map.put("endRowNum", pb.getEndRowNum());
        List<Object> list = new ArrayList<Object>();
        list.add(new Object());
        deliveryPointMapperMock.returns(list).selectAllByPb(map);
		assertEquals(1, deliveryPointService.selectAllDeliveryPointByPb(pb, 1L).getList().size());
	}
	
	/**
	 *  根据区县ID查询未删除、已启用的自提点-前台用测试
	 */
	@Test
	public void testSelectDeliveryPointByDistrictIdForSite()
	{
		Map<String, Object> map = new HashMap<String, Object>();
	    map.put("districtId", 1L);
	    List<DeliveryPoint> list = new ArrayList<DeliveryPoint>();
	    list.add(new DeliveryPoint());
	    deliveryPointMapperMock.returns(list).selectByDistrictIdForSite(map);
		assertEquals(1, deliveryPointService.selectDeliveryPointByDistrictIdForSite(1L).size());
	}
	
	/**
	 * 根据当前城市id查询得到该城市下的所有自提点测试
	 */
	@Test
	public void testSelectDeliveryPointBycityId()
	{
		List<DistrictVo> list = new ArrayList<DistrictVo>();
		list.add(new DistrictVo());
		districtServiceMock.returns(list).queryDistrictByCityId(1L);
		
		List<DeliveryPoint> lists = new ArrayList<DeliveryPoint>();
		lists.add(new DeliveryPoint());
		deliveryPointMapperMock.returns(lists).selectDeliveryPointBydistrictIds(list);
		
		assertEquals(1, deliveryPointService.selectDeliveryPointBycityId(1L).size());
	}
	
	/**
	 * 修改自提点的启用状态
	 */
	@Test
	public void testChangeUserdStatus()
	{
		DeliveryPoint dp = new DeliveryPoint();
		dp.setIsUserd("0");
		deliveryPointMapperMock.returns(dp).selectByPrimaryKey(1L);
		deliveryPointMapperMock.returns(1).updateByPrimaryKeySelective(dp);
		assertEquals(1, deliveryPointService.changeUserdStatus(1L));
		assertEquals("1", dp.getIsUserd());
	}
	
}
