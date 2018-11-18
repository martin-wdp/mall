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

import com.ningpai.channel.bean.ChannelStorey;
import com.ningpai.channel.dao.ChannelStoreyMapper;
import com.ningpai.channel.service.ChannelStoreyService;
import com.ningpai.channel.service.impl.ChannelStoreyServiceImpl;
import com.ningpai.util.PageBean;

/**
 * SERVICE-频道楼层测试
 * @author djk
 *
 */
public class ChannelStoreyServiceTest extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口类
	 */
	 @TestedObject
	 private ChannelStoreyService channelStoreyService = new ChannelStoreyServiceImpl();
	 
	 /**
	 *  模拟MOCK
	 */
	 @InjectIntoByType
	 private Mock<ChannelStoreyMapper> channelStoreyMapperMock;
	 
	 /**
	  * 实体类-频道楼层
	  */
	 private ChannelStorey channelStorey = new ChannelStorey();
	 
	 /**
	  * 删除频道楼层测试
	  */
	 @Test
	 public void testDeleteChannelStorey()
	 {
		 channelStoreyMapperMock.returns(channelStorey).selectByPrimaryKey(1L);
		 channelStoreyMapperMock.returns(1).updateByPrimaryKeySelective(channelStorey);
		 assertEquals(1, channelStoreyService.deleteChannelStorey(1L, 1L));
	 }
	 
	 /**
	  * 添加测试
	  */
	 @Test
	 public void testSaveChannelStorey()
	 {
		 channelStoreyMapperMock.returns(1).insertSelective(channelStorey);
		 assertEquals(1, channelStoreyService.saveChannelStorey(channelStorey));
	 }
	 
	 /**
	  * 修改测试
	  */
	 @Test
	 public void testUpdateChannelStorey()
	 {
		 channelStoreyMapperMock.returns(1).updateByPrimaryKeySelective(channelStorey);
		 assertEquals(1, channelStoreyService.updateChannelStorey(channelStorey));
	 }
	 
	 /**
	  * 根据主键查询测试
	  */
	 @Test
	 public void testGetChannelStoreyById()
	 {
		 channelStoreyMapperMock.returns(channelStorey).selectByPrimaryKey(1L);
		 assertNotNull(channelStoreyService.getChannelStoreyById(1L));
	 }
	 
	 /**
	  * 根据分页参数和频道ID、模板ID查询频道楼层测试
	  */
	 @Test
	 public void testSelectchannelStoreyByParam()
	 {
		 PageBean pageBean = new PageBean();
	     Map<String, Object> map = new HashMap<String, Object>();
	     map.put("channelId", 1L);
         map.put("tempId", 1L);
         map.put("temp1", "1");
         channelStoreyMapperMock.returns(1).selectchannelStoreyCountByParam(map);
         map.put("startRowNum", pageBean.getStartRowNum());
         map.put("endRowNum", pageBean.getEndRowNum());
         List<Object> list = new ArrayList<>();
         list.add(new Object());
         channelStoreyMapperMock.returns(list).selectchannelStoreyByParam(map);
		 assertEquals(1, channelStoreyService.selectchannelStoreyByParam(pageBean, 1L, 1L, "1").getList().size());
	 }
	 
	 /**
	  * 根据分页参数和频道ID、模板ID查询频道楼层列表-前台展示用测试
	  */
	 @Test
	 public void testSelectchannelStoreyByParamForSite()
	 {
		 List<ChannelStorey> list = new ArrayList<>();
		 list.add(channelStorey);
         Map<String, Object> map = new HashMap<String, Object>();
         map.put("channelId", 1L);
         map.put("tempId", 1L);
         map.put("temp1", "1");
         channelStoreyMapperMock.returns(list).selectchannelStoreyByParamForSite(map);
		 assertEquals(1, channelStoreyService.selectchannelStoreyByParamForSite(1L, 1L, "1").size());
	 }
	 
	 /**
	  *  调用存储过程级联删除楼层
	  */
	 @Test
	 public void testDeleteByPrimaryKeyCallPro()
	 {
		 channelStoreyMapperMock.returns(1).deleteByPrimaryKeyCallPro(1L);
		 assertEquals(1, channelStoreyService.deleteByPrimaryKeyCallPro(1L));
	 }
}
