package com.junit.templet.channel.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;

import com.ningpai.channel.bean.ChannelGoods;
import com.ningpai.channel.dao.ChannelSalesGoodsMapper;
import com.ningpai.channel.service.ChannelSalesGoodsService;
import com.ningpai.channel.service.impl.ChannelSalesGoodsServiceImpl;
import com.ningpai.util.PageBean;

/**
 * 测试类
 * @author djk
 *
 */
public class ChannelSalesGoodsServiceTest extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口类
	 */
	 @TestedObject
	 private ChannelSalesGoodsService channelSalesGoodsService = new ChannelSalesGoodsServiceImpl();
	 
	/**
	 *  模拟MOCK
	 */
	 @InjectIntoByType
	 private Mock<ChannelSalesGoodsMapper> channelSalesGoodsMapperMock;
	 
	 /**
	  * 频道商品
	  */
	 private ChannelGoods channelGoods = new ChannelGoods();
	 
	 /**
	  * 根据主键删除 参数:主键 返回:删除个数测试
	  */
	 @Test
	 public void testDeleteByPrimaryKey()
	 {
		 channelSalesGoodsMapperMock.returns(1).updateByPrimaryKeySelective(channelGoods);
		 assertEquals(1, channelSalesGoodsService.deleteByPrimaryKey(1L, 1L));
	 }
	 
	 /**
	  * 插入，空属性不会插入 参数:pojo对象 返回:删除个数测试
	  */
	 @Test
	 public void testInsertSelective()
	 {
		 channelSalesGoodsMapperMock.returns(1).insertSelective(channelGoods);
		 assertEquals(1, channelSalesGoodsService.insertSelective(channelGoods));
	 }
	 
	 /**
	  * 根据主键查询 参数:查询条件,主键值 返回:对象测试
	  */
	 @Test
	 public void testSelectByPrimaryKey()
	 {
		 channelSalesGoodsMapperMock.returns(channelGoods).selectByPrimaryKey(1L);
		 assertNotNull(channelSalesGoodsService.selectByPrimaryKey(1L));
	 }
	 
	 /**
	  * 根据主键修改，空值条件不会修改成null 参数:1.要修改成的值 返回:成功修改个数测试
	  */
	 @Test
	 public void testUpdateByPrimaryKeySelective()
	 {
		 channelSalesGoodsMapperMock.returns(1).updateByPrimaryKeySelective(channelGoods);
		 assertEquals(1, channelSalesGoodsService.updateByPrimaryKeySelective(channelGoods));
	 }
	 
	 /**
	  *  根据分页参数和楼层ID查询促销商品测试
	  */
	 @Test
	 public void testSelectChannelGoodsByParam()
	 {
		 PageBean pb = new PageBean();
	     Map<String, Object> map = new HashMap<String, Object>();
	     map.put("channelId", 1L);
	     channelSalesGoodsMapperMock.returns(1).selectChannelGoodsCount(map);
         map.put("startRowNum", pb.getStartRowNum());
         map.put("endRowNum", pb.getEndRowNum());
         List<Object> lists = new ArrayList<>();
         lists.add(new Object());
         channelSalesGoodsMapperMock.returns(lists).selectChannelGoodsList(map);
		 assertEquals(1, channelSalesGoodsService.selectChannelGoodsByParam(pb, 1L).getList().size());
	 }
	 
	 /**
	  * 查询频道页商品
	  */
	 @Test
	 public void testSelectChannelGoodsByFlag()
	 {
		 List<ChannelGoods> list = new ArrayList<>();
		 list.add(channelGoods);
		 Map<String, Object> pMap = new HashMap<String, Object>();
         pMap.put("channelId", 1L);
         pMap.put("channelGoodsFlag", "1");
         pMap.put("top", 1);
         channelSalesGoodsMapperMock.returns(list).selectChannelGoodsByFlag(pMap);
		 assertEquals(1, channelSalesGoodsService.selectChannelGoodsByFlag(1L, "1", 1).size());
	 }
	 
}
