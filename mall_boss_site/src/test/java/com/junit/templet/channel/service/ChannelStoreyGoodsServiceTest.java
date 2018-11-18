package com.junit.templet.channel.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;

import com.ningpai.channel.bean.ChannelStoreyGoods;
import com.ningpai.channel.bean.GoodsSiteSearchBean;
import com.ningpai.channel.dao.ChannelStoreyGoodsMapper;
import com.ningpai.channel.service.ChannelStoreyGoodsService;
import com.ningpai.channel.service.impl.ChannelStoreyGoodsServiceImpl;
import com.ningpai.util.PageBean;

/**
 *  SERVICE-频道楼层商品测试
 * @author djk
 *
 */
public class ChannelStoreyGoodsServiceTest  extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口类
	 */
	 @TestedObject
	 private ChannelStoreyGoodsService channelStoreyGoodsService = new ChannelStoreyGoodsServiceImpl();
	 
	 
	 /**
	 *  模拟MOCK
	 */
	 @InjectIntoByType
	 private Mock<ChannelStoreyGoodsMapper> channelStoreyGoodsMapperMock;
	 
	 /**
	  * 实体类-楼层商品
	  */
	 private ChannelStoreyGoods channelStoreyGoods = new ChannelStoreyGoods();
	 
	 /**
	  * 根据主键删除测试
	  */
	 @Test
	 public void testDeleteChannelStoreyGoods()
	 {
		 channelStoreyGoodsMapperMock.returns(channelStoreyGoods).selectByPrimaryKey(1L);
		 channelStoreyGoodsMapperMock.returns(1).updateByPrimaryKeySelective(channelStoreyGoods);
		 assertEquals(1, channelStoreyGoodsService.deleteChannelStoreyGoods(1L, 1L));
	 }
	 
	 /**
	  * 添加测试
	  */
	 @Test
	 public void testSaveChannelStoreyGoods()
	 {
		 channelStoreyGoodsMapperMock.returns(1).insertSelective(channelStoreyGoods);
		 assertEquals(1, channelStoreyGoodsService.saveChannelStoreyGoods(channelStoreyGoods));
	 }
	 
	 /**
	  * 修改测试
	  */
	 @Test
	 public void testUpdateChannelStoreyGoods()
	 {
		 channelStoreyGoodsMapperMock.returns(1).updateByPrimaryKeySelective(channelStoreyGoods);
		 assertEquals(1, channelStoreyGoodsService.updateChannelStoreyGoods(channelStoreyGoods));
	 }
	 
	 /**
	  *  根据主键查询测试
	  */
	 @Test
	 public void testGetChannelStoreyGoodsById()
	 {
		 channelStoreyGoodsMapperMock.returns(channelStoreyGoods).selectByPrimaryKey(1L);
		 assertNotNull(channelStoreyGoodsService.getChannelStoreyGoodsById(1L));
	 }
	 
	 /**
	  * 根据分页参数和楼层ID、楼层标签ID、是否热销查询频道楼层商品测试
	  */
	 @Test
	 public void testSelectchannelStoreyGoodsByParam()
	 {
		 PageBean pb = new PageBean();
	     Map<String, Object> map = new HashMap<String, Object>();
         map.put("storeyId", 1L);
         map.put("storeyTagId", 1L);
         map.put("isHot", "1");
         channelStoreyGoodsMapperMock.returns(1).selectchannelStoreyGoodsCountByParam(map);
         map.put("startRowNum", pb.getStartRowNum());
         map.put("endRowNum", pb.getEndRowNum());
         List<Object> lists = new ArrayList<>();
         lists.add(new Object());
         channelStoreyGoodsMapperMock.returns(lists).selectchannelStoreyGoodsByParam(map);
         assertEquals(1, channelStoreyGoodsService.selectchannelStoreyGoodsByParam(pb, 1L, 1L, "1").getList().size());
	 }
	 
	 /**
	  * 根据楼层ID、楼层标签ID、是否热销查询频道楼层商品-前台展示用
	  */
	 @Test
	 public void testSelectchannelStoreyGoodsByParamForSite()
	 {
         Map<String, Object> map = new HashMap<String, Object>();
         map.put("storeyId", 1L);
         map.put("storeyTagId", 1L);
         map.put("isHot", "1");
         List<ChannelStoreyGoods> lists = new ArrayList<>();
         lists.add(channelStoreyGoods);
         channelStoreyGoodsMapperMock.returns(lists).selectchannelStoreyGoodsByParamForSite(map);
		 assertEquals(1, channelStoreyGoodsService.selectchannelStoreyGoodsByParamForSite(1L, 1L, "1").size());
	 }
	 
	 /**
	  * 根据分页参数和楼层ID、楼层标签ID、是否热销查询频道楼层商品-前台展示测试
	  */
	 @Test
	 public void testSelectchannelStoreyGoodsByParamForChannelSite()
	 {
		 List<ChannelStoreyGoods> list = new ArrayList<>();
		 list.add(channelStoreyGoods);
		 HttpServletRequest request = new MockHttpServletRequest();
		 request.getSession().setAttribute("distinctId", "1");
		 PageBean pb = new PageBean();
		 GoodsSiteSearchBean goodsSiteSearchBean = new GoodsSiteSearchBean();
		 Map<String, Object> map = new HashMap<String, Object>();
	     map.put("storeyId", 1L);
         map.put("storeyTagId", 1L);
         map.put("isHot", "1");
	     map.put("searchBean", goodsSiteSearchBean);
	     channelStoreyGoodsMapperMock.returns(1).selectchannelStoreyGoodsNumberByParam(map);
         map.put("startRowNum", pb.getStartRowNum());
         map.put("endRowNum", pb.getEndRowNum());
         map.put("distinctId", 1L);
         List<Object> lists = new ArrayList<>();
         lists.add(new Object());
         channelStoreyGoodsMapperMock.returns(lists).selectchannelStoreyGoodsByParamForChannelSite(map);
         channelStoreyGoodsMapperMock.returns(list).selectchannelStoreyGoodsByParamForSite(map);
		 assertEquals(1, channelStoreyGoodsService.selectchannelStoreyGoodsByParamForChannelSite(request, 1L, 1L, "1", goodsSiteSearchBean, pb).size());
	 }
}
