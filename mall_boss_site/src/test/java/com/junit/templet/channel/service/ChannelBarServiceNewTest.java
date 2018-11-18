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

import com.ningpai.channel.bean.Channel;
import com.ningpai.channel.bean.ChannelBar;
import com.ningpai.channel.dao.ChannelBarMapper;
import com.ningpai.channel.dao.ChannelMapper;
import com.ningpai.channel.service.ChannelBarService;
import com.ningpai.channel.service.impl.ChannelBarServiceNewImpl;
import com.ningpai.util.PageBean;

/**
 * SERVICE-频道导航测试
 * @author djk
 *
 */
public class ChannelBarServiceNewTest  extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口类
	 */
	 @TestedObject
	 private ChannelBarService channelBarService = new ChannelBarServiceNewImpl();
	 
	 /**
	  *  模拟MOCK
	  */
	 @InjectIntoByType
	 private Mock<ChannelBarMapper> channelBarMapperMock;
	 
	 /**
	  *  模拟MOCK
	  */
	 @InjectIntoByType
	 private Mock<ChannelMapper> channelMapperMock;
	 
	 /**
	  * 频道导航测试
	  */
	 private ChannelBar channelBar = new ChannelBar();
	 /**
	  * 根据主键删除频道导航测试
	  */
	 @Test
	 public void testDeleteChannelBar()
	 {
		 channelBarMapperMock.returns(channelBar).selectByPrimaryKey(1L);
		 channelBarMapperMock.returns(1).updateByPrimaryKeySelective(channelBar);
		 assertEquals(1, channelBarService.deleteChannelBar(1L));
	 }
	 
	 /**
	  * 添加频道导航测试
	  */
	 @Test
	 public void testSaveChannelBar()
	 {
		 channelBar.setBarId(1L);
		 channelBarMapperMock.returns(1).insertSelective(channelBar);
		 channelMapperMock.returns(1).insertSelective(new Channel());
		 assertEquals(1, channelBarService.saveChannelBar(channelBar));
	 }
	 
	 /**
	  * 修改频道导航测试
	  */
	 @Test
	 public void testUpdateChannelBar()
	 {
		 channelBarMapperMock.returns(1).updateByPrimaryKeySelective(channelBar);
		 assertEquals(1, channelBarService.updateChannelBar(channelBar));
	 }
	 
	 /**
	  * 根据主键查询频道导航测试
	  */
	 @Test
	 public void testGetChannelBarById()
	 {
		 channelBarMapperMock.returns(channelBar).selectByPrimaryKey(1L);
		 assertNotNull(channelBarService.getChannelBarById(1L));
	 }
	 
	 /**
	  * 根据分页参数和频道ID、模板ID查询频道导航
	  */
	 @Test
	 public void testSelectChannelBarByParam()
	 {
		 PageBean pb = new PageBean();
	     Map<String, Object> map = new HashMap<String, Object>();
	     map.put("channelId", 1L);
         map.put("tempId", 1L);
         map.put("expFleid1", "1");
         channelBarMapperMock.returns(1).selectChannelBarCountByParam(map);
         map.put("startRowNum", pb.getStartRowNum());
         map.put("endRowNum", pb.getEndRowNum());
         List<Object> lists = new ArrayList<>();
         lists.add(new Object());
         channelBarMapperMock.returns(lists).selectChannelBarByParam(map);
         assertEquals(1, channelBarService.selectChannelBarByParam(pb, 1L, 1L, "1").getList().size());
	 }
	 
	 /**
	  *  根据频道ID、模板ID查询频道导航
	  */
	 @Test
	 public void testSelectAllChannelBarByParam()
	 {
		 List<ChannelBar> lists = new ArrayList<>();
		 lists.add(channelBar);
		 Map<String, Object> map = new HashMap<String, Object>();
         map.put("channelId", 1L);
         map.put("tempId", 1L);
         map.put("expFleid1", "1");
		 channelBarMapperMock.returns(lists).selectAllChannelBarByParam(map);
		 assertEquals(1, channelBarService.selectAllChannelBarByParam(1L, 1L, "1").size());
	 }
}
