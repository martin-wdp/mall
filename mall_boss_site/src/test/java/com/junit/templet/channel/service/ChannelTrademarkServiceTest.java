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

import com.ningpai.channel.bean.ChannelTrademark;
import com.ningpai.channel.dao.ChannelTrademarkMapper;
import com.ningpai.channel.service.ChannelTrademarkService;
import com.ningpai.channel.service.impl.ChannelTrademarkServiceImpl;
import com.ningpai.util.PageBean;

/**
 * SERVICE-频道品牌测试
 * @author djk
 *
 */
public class ChannelTrademarkServiceTest extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口类
	 */
	 @TestedObject
	 private ChannelTrademarkService channelTrademarkService = new ChannelTrademarkServiceImpl();
	 
	 /**
	 *  模拟MOCK
	 */
	 @InjectIntoByType
	 private Mock<ChannelTrademarkMapper> channelTrademarkMapperMock;
	 
	 /**
	  * 实体类-频道品牌
	  */
	 private ChannelTrademark channelTrademark = new ChannelTrademark();
	 
	 /**
	  * 根据ID删除测试
	  */
	 @Test
	 public void testDeleteChannelTrademark()
	 {
		 channelTrademarkMapperMock.returns(channelTrademark).selectByPrimaryKey(1L);
		 channelTrademarkMapperMock.returns(1).updateByPrimaryKeySelective(channelTrademark);
		 assertEquals(1, channelTrademarkService.deleteChannelTrademark(1L, 1L));
	 }
	 
	 /**
	  * 添加测试
	  */
	 @Test
	 public void testSaveChannelTrademark()
	 {
		 channelTrademarkMapperMock.returns(1).insertSelective(channelTrademark);
		 assertEquals(1, channelTrademarkService.saveChannelTrademark(channelTrademark));
	 }
	 
	 /**
	  * 修改测试
	  */
	 @Test
	 public void testUpdateChannelTrademark()
	 {
		 channelTrademarkMapperMock.returns(1).updateByPrimaryKeySelective(channelTrademark);
		 assertEquals(1, channelTrademarkService.updateChannelTrademark(channelTrademark));
	 }
	 
	 /**
	  * 根据ID查询测试
	  */
	 @Test
	 public void testGetChannelTrademarkById()
	 {
		 channelTrademarkMapperMock.returns(channelTrademark).selectByPrimaryKey(1L);
		 assertNotNull(channelTrademarkService.getChannelTrademarkById(1L));
	 }
	 
	 /**
	  * 根据分页参数和频道ID、模板ID、楼层ID、分类导航ID查询品牌测试
	  */
	 @Test
	 public void testSelectchannelTrademarkByParam()
	 {
		 PageBean pageBean = new PageBean();
         Map<String, Object> map = new HashMap<String, Object>();
         map.put("channelId", 1L);
         map.put("tempId", 1L);
         map.put("storeyId", 1L);
         map.put("storeyTagId", 1L);
         map.put("temp1", "1");
         map.put("temp2", "0");
         map.put("temp3", "1");
         channelTrademarkMapperMock.returns(1).selectchannelTrademarkCountByParam(map);
         map.put("startRowNum", pageBean.getStartRowNum());
         map.put("endRowNum", pageBean.getEndRowNum());
         List<Object> lists = new ArrayList<>();
         lists.add(new Object());
         channelTrademarkMapperMock.returns(lists).selectchannelTrademarkByParam(map);
		 assertEquals(1, channelTrademarkService.selectchannelTrademarkByParam(pageBean, 1L, 1L, 1L, 1L, "1", "1").getList().size());
	 }
	 
	 /**
	  * 根据分页参数和分类导航ID和是否分类导航父框查询品牌测试
	  */
	 @Test
	 public void testSelectClassifyTrademarkByParam()
	 {
		 PageBean pageBean = new PageBean();
         Map<String, Object> map = new HashMap<String, Object>();
         map.put("temp1", "1");
         map.put("temp2", "1");
         map.put("tempId", 1L);
         map.put("channelId", 1L);
         channelTrademarkMapperMock.returns(1).selectchannelTrademarkCountByParam(map);
         map.put("startRowNum", pageBean.getStartRowNum());
         map.put("endRowNum", pageBean.getEndRowNum());
         List<Object> lists = new ArrayList<>();
         lists.add(new Object());
         channelTrademarkMapperMock.returns(lists).selectchannelTrademarkByParam(map);
		 assertEquals(1, channelTrademarkService.selectClassifyTrademarkByParam(pageBean, 1L, 1L, "1", "1").getList().size());
	 }
	 
	 /**
	  * 根据频道ID、模板ID、楼层ID查询频道品牌--前台展示用测试
	  */
	 @Test
	 public void testSelectChannelTrademarkByParamForSite()
	 {
		 List<ChannelTrademark> list = new ArrayList<>();
		 list.add(channelTrademark);
         Map<String, Object> map = new HashMap<String, Object>();
         map.put("channelId", 1L);
         map.put("tempId", 1L);
         map.put("storeyId", 1L);
         map.put("storeyTagId", 1L);
         map.put("temp1", "1");
         map.put("temp2", "1");
         map.put("temp3", "1");
         channelTrademarkMapperMock.returns(list).selectChannelTrademarkByParamForSite(map);
		 assertEquals(1, channelTrademarkService.selectChannelTrademarkByParamForSite(1L, 1L, 1L, 1L, "1",  "1",  "1").size());
	 }
	 
	 /**
	  * 根据模板ID查询品牌设置
	  */
	 @Test
	 public void testSelectTrademarkByTempId()
	 {		 
		 List<ChannelTrademark>  list = new ArrayList<>();
		 list.add(channelTrademark);
		 channelTrademarkMapperMock.returns(list).selectTrademarkByTempId(1L);
		 assertEquals(1, channelTrademarkService.selectTrademarkByTempId(1L).size());
	 }
}
