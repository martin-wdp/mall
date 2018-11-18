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

import com.ningpai.channel.bean.ChannelStoreyTag;
import com.ningpai.channel.dao.ChannelStoreyTagMapper;
import com.ningpai.channel.service.ChannelStoreyTagService;
import com.ningpai.channel.service.impl.ChannelStoreyTagServiceImpl;
import com.ningpai.util.PageBean;

/**
 * SERVICE-频道楼层标签测试
 * @author djk
 *
 */
public class ChannelStoreyTagServiceTest extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口类
	 */
	 @TestedObject
	 private ChannelStoreyTagService channelStoreyTagService = new ChannelStoreyTagServiceImpl();
	 
	 /**
	 *  模拟MOCK
	 */
	 @InjectIntoByType
	 private Mock<ChannelStoreyTagMapper> channelStoreyTagMapperMock;
	 
	 /**
	  * 实体类-楼层标签测试
	  */
	 private ChannelStoreyTag channelStoreyTag = new ChannelStoreyTag();
	 
	 /**
	  * 根据主键删除
	  */
	 @Test
	 public void testDeleteChannelStoreyTag()
	 {
		 channelStoreyTagMapperMock.returns(channelStoreyTag).selectByPrimaryKey(1L);
		 channelStoreyTagMapperMock.returns(1).updateByPrimaryKeySelective(channelStoreyTag);
		 assertEquals(1, channelStoreyTagService.deleteChannelStoreyTag(1L, 1L));
	 }
	 
	 /**
	  * 添加测试
	  */
	 @Test
	 public void testSaveChannelStoreyTag()
	 {
		 channelStoreyTagMapperMock.returns(1).insertSelective(channelStoreyTag);
		 assertEquals(1, channelStoreyTagService.saveChannelStoreyTag(channelStoreyTag));
	 }
	 
	 /**
	  * 修改测试
	  */
	 @Test
	 public void testUpdateChannelStoreyTag()
	 {
		 channelStoreyTagMapperMock.returns(1).updateByPrimaryKeySelective(channelStoreyTag);
		 assertEquals(1, channelStoreyTagService.updateChannelStoreyTag(channelStoreyTag));
	 }
	 
	 /**
	  * 根据主键查询测试
	  */
	 @Test
	 public void testGetChannelStoreyTagById()
	 {
		 channelStoreyTagMapperMock.returns(channelStoreyTag).selectByPrimaryKey(1L);
		 assertNotNull(channelStoreyTagService.getChannelStoreyTagById(1L));
	 }
	 
	 /**
	  * 根据分页参数和楼层ID查询频道楼层标签测试
	  */
	 @Test
	 public void testSelectchannelStoreyTagByParam()
	 {
		 PageBean pageBean = new PageBean();
	     Map<String, Object> map = new HashMap<String, Object>();
         map.put("storeyId", 1L);
         map.put("temp1", 1L);
         map.put("temp2", 1L);
         channelStoreyTagMapperMock.returns(1).selectchannelStoreyTagCountByParam(map);
         map.put("startRowNum", pageBean.getStartRowNum());
         map.put("endRowNum", pageBean.getEndRowNum());
         List<Object> lists = new ArrayList<>();
         lists.add(new Object());
         channelStoreyTagMapperMock.returns(lists).selectchannelStoreyTagByParam(map);
		 assertEquals(1, channelStoreyTagService.selectchannelStoreyTagByParam(pageBean, 1L, 1L, 1L).getList().size());
	 }
	 
	 /**
	  * 根据分页参数和楼层ID查询频道楼层标签列表-前台展示用测试
	  */
	 @Test
	 public void testSelectchannelStoreyTagByParamForSite()
	 {
		 List<ChannelStoreyTag> list = new ArrayList<>();
		 list.add(channelStoreyTag);
         Map<String, Object> map = new HashMap<String, Object>();
         map.put("storeyId", 1L);
         map.put("temp1", 1L);
         map.put("temp2", 1L);
		 channelStoreyTagMapperMock.returns(list).selectchannelStoreyTagByParamForSite(map);
		 assertEquals(1, channelStoreyTagService.selectchannelStoreyTagByParamForSite(1L, 1L, 1L).size());
	 }
	 
	 /**
	  *  调用存储过程级联删除楼层标签测试
	  */
	 @Test
	 public void testDeleteByPrimaryKeyCallPro()
	 {
		 channelStoreyTagMapperMock.returns(1).deleteByPrimaryKeyCallPro(1L);
		 assertEquals(1, channelStoreyTagService.deleteByPrimaryKeyCallPro(1L));
	 }
	 
}
