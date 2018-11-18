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
import com.ningpai.channel.dao.ChannelMapper;
import com.ningpai.channel.service.ChannelService;
import com.ningpai.channel.service.impl.ChannelServiceImpl;
import com.ningpai.util.PageBean;

/**
 * SERVICE-频道测试
 * @author djk
 *
 */
public class ChannelServiceTest extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口类
	 */
	 @TestedObject
	 private ChannelService channelService = new ChannelServiceImpl();
	 
	 /**
	 *  模拟MOCK
	 */
	 @InjectIntoByType
	 private Mock<ChannelMapper> channelMapperMock;
	 
	 /**
	  * 实体类-频道
	  */
	 private Channel channel = new Channel();
	 
	 /**
	  * 根据ID删除频道
	  */
	 @Test
	 public void testDeleteChannel()
	 {
		 channelMapperMock.returns(channel).selectByPrimaryKey(1L);
		 channelMapperMock.returns(1).updateByPrimaryKeySelective(channel);
		 assertEquals(1, channelService.deleteChannel(1L, 1L));
	 }
	 
	 /**
	  * 添加频道测试
	  */
	 @Test
	 public void testCreateChannel()
	 {
		 channelMapperMock.returns(1).insertSelective(channel);
		 assertEquals(1, channelService.createChannel(channel));
	 }
	 
	 /**
	  * 修改频道测试
	  */
	 @Test
	 public void testUpdateChannel()
	 {
		 channelMapperMock.returns(1).updateByPrimaryKeySelective(channel);
		 assertEquals(1, channelService.updateChannel(channel));
	 }
	 
	 /**
	  * 根据主键查询频道测试
	  */
	 @Test
	 public void testFindChannelByID()
	 {
		 channelMapperMock.returns(channel).selectByPrimaryKey(1L);
		 assertNotNull(channelService.findChannelByID(1L));
	 }
	 
	 /**
	  * 根据商品分类ID查询频道测试
	  */
	 @Test
	 public void testSelectByCateId()
	 {
		 channelMapperMock.returns(channel).selectByCateId(1L);
		 assertNotNull(channelService.selectByCateId(1L));
	 }
	 
	 /**
	  * 根据分页参数查询频道列表测试
	  */
	 @Test
	 public void testFindChannelByPageBean()
	 {
		 PageBean pb = new PageBean();
	     Map<String, Object> map = new HashMap<String, Object>();
         map.put("searchText", "1");
         map.put("tempId", 1L);
         channelMapperMock.returns(1).queryTotalCount(map);
         map.put("startRowNum", pb.getStartRowNum());
         map.put("endRowNum", pb.getEndRowNum());
         List<Object> lists = new ArrayList<>();
         lists.add(new Object());
         channelMapperMock.returns(lists).queryByPageBean(map);
		 assertEquals(1, channelService.findChannelByPageBean(pb, "1", 1L).getList().size());
	 }
	 
	 /**
	  * 开启全部频道测试
	  */
	 @Test
	 public void testOpenAllChannel()
	 {
		 List<Channel> list  = new ArrayList<>();
		 list.add(channel);
		 channelMapperMock.returns(list).selectAll();
		 channelMapperMock.returns(1).updateByPrimaryKeySelective(channel);
		 channelService.openAllChannel();
	 }
	 
	 /**
	  *  关闭全部频道测试
	  */
	 @Test
	 public void testCloseAllChannel()
	 {
		 List<Channel> list  = new ArrayList<>();
		 list.add(channel);
		 channelMapperMock.returns(list).selectAll();
		 channelMapperMock.returns(1).updateByPrimaryKeySelective(channel);
		 channelService.closeAllChannel();
	 }
	 
	 /**
	  * 根据频道ID开启频道测试
	  */
	 @Test
	 public void testOpenChannelByID()
	 {
		 channelMapperMock.returns(channel).selectByPrimaryKey(1L);
		 channelMapperMock.returns(1).updateByPrimaryKeySelective(channel);
		 channelService.openChannelByID(1L, 1L);
	 }
	 
	 /**
	  * 根据频道ID关闭频道测试
	  */
	 @Test
	 public void testCloseChannelByID()
	 {
		 channelMapperMock.returns(channel).selectByPrimaryKey(1L);
		 channelMapperMock.returns(1).updateByPrimaryKeySelective(channel);
		 channelService.closeChannelByID(1L, 1L);
	 }
	 
	 /**
	  * 根据页面导航ID查询频道测试
	  */
	 @Test
	 public void testSelectByBarId()
	 {
		 channelMapperMock.returns(channel).selectByBarId("1");
		 assertNotNull(channelService.selectByBarId("1"));
	 }
}
