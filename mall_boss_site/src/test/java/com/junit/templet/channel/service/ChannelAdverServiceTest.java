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

import com.ningpai.channel.bean.ChannelAdver;
import com.ningpai.channel.dao.ChannelAdverMapper;
import com.ningpai.channel.service.ChannelAdverService;
import com.ningpai.channel.service.impl.ChannelAdverServiceImpl;
import com.ningpai.util.PageBean;

/**
 * SERVICE-频道广告测试类
 * @author djk
 *
 */
public class ChannelAdverServiceTest extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口类
	 */
	@TestedObject
	private ChannelAdverService channelAdverService = new ChannelAdverServiceImpl();
	
	/**
	 *  模拟MOCK
	 */
	 @InjectIntoByType
	private Mock<ChannelAdverMapper> channelAdverMapperMock;
	 
	 /**
	  *  实体类-频道广告测试
	  */
	private ChannelAdver channelAdver =  new ChannelAdver();
	 
	/**
	 * 根据主键删除频道广告测试
	 */
	 @Test
	 public void testDeleteChannelAdver()
	 {
		 channelAdverMapperMock.returns(channelAdver).selectByPrimaryKey(1L);
		 channelAdverMapperMock.returns(1).updateByPrimaryKeySelective(channelAdver);
		 assertEquals(1, channelAdverService.deleteChannelAdver(1L, 1L));
	 }
	 
	 /**
	  *  添加频道广告测试
	  */
	 @Test
	 public void testSaveChannelAdver()
	 {
		 channelAdverMapperMock.returns(1).insertSelective(channelAdver);
		 assertEquals(1, channelAdverService.saveChannelAdver(channelAdver));
	 }
	 
	 /**
	  * 修改频道广告
	  */
	 @Test
	 public void testUpdateChannelAdver()
	 {
		 channelAdverMapperMock.returns(1).updateByPrimaryKeySelective(channelAdver);
		 assertEquals(1, channelAdverService.updateChannelAdver(channelAdver));
	 }
	 
	 /**
	  *  根据主键查询频道广告测试
	  */
	 @Test
	 public void testSelectByPrimaryKey()
	 {
		 channelAdverMapperMock.returns(channelAdver).selectByPrimaryKey(1L);
		 assertNotNull(channelAdverService.selectByPrimaryKey(1L));
	 }
	 
	 /**
	  *  根据分页参数和频道ID、模板ID、楼层ID、楼层标签ID、分类导航ID查询频道广告测试
	  */
	 @Test
	 public void testSelectchannelAdverByParam()
	 {
		 PageBean pb = new PageBean();
		 
	     Map<String, Object> map = new HashMap<String, Object>();
	     
         map.put("channelId", 1L);
         map.put("tempId", 1L);
         map.put("floorId", 1L);
         map.put("floorTagId", 1L);
         map.put("atId", 1L);
         map.put("adverType", 1L);
         map.put("temp1", "1");
         map.put("temp3", "0");
         map.put("temp4", "1");
         channelAdverMapperMock.returns(1).selectchannelAdverCountByParam(map);
         map.put("startRowNum", pb.getStartRowNum());
         map.put("endRowNum", pb.getEndRowNum());
         List<Object> lists = new ArrayList<>();
         lists.add(new Object());
         channelAdverMapperMock.returns(lists).selectchannelAdverByParam(map);
		 assertEquals(1, channelAdverService.selectchannelAdverByParam(pb, 1L, 1L, 1L, 1L, 1L, 1L, "1", "1").getList().size());
	 }
	 
	 /**
	  * 根据分页参数、分类导航ID查询导航分类父框广告测试
	  */
	 @Test
	 public void testSelectClassifyBarAdverByParam()
	 {
		 PageBean pb = new PageBean();
	     Map<String, Object> map = new HashMap<String, Object>();
         map.put("channelId", 1L);
         map.put("tempId", 1L);
         map.put("atId",161L);
         map.put("adverType", 151L);
         map.put("temp1", "1");
         map.put("temp3", "1");
         channelAdverMapperMock.returns(1).selectchannelAdverCountByParam(map);
         map.put("startRowNum", pb.getStartRowNum());
         map.put("endRowNum", pb.getEndRowNum());
         List<Object> lists = new ArrayList<>();
         lists.add(new Object());
         channelAdverMapperMock.returns(lists).selectchannelAdverByParam(map);
		 assertEquals(1, channelAdverService.selectClassifyBarAdverByParam(pb, 1L, 1L, "1", "1").getList().size());
	 }
	 
	 /**
     * 根据分页参数和频道ID、模板ID、楼层ID、楼层标签ID查询频道广告列表-前台展示用 channelId 频道ID tempId 模板ID floorId 楼层ID floorTagId 楼层标签ID atId 广告分类ID 157大广告；159小广告；161页面广告 adverType 广告类型ID 现用151 temp1 分类导航ID temp3 是否是导航分类父框 0：不是 1：是 temp5 广告显示位置
	  */
	 @Test
	 public void testSelectchannelAdverByParamForSite()
	 {
		 List<ChannelAdver> lists = new ArrayList<>();
		 lists.add(channelAdver);
         Map<String, Object> map = new HashMap<String, Object>();
         map.put("channelId", 1L);
         map.put("tempId", 1L);
         map.put("floorId", 1L);
         map.put("floorTagId", 1L);
         map.put("atId", 1L);
         map.put("adverType", 1L);
         map.put("temp1", "1");
         map.put("temp3", "1");
         map.put("temp4", "1");
         map.put("temp5", "1");
         channelAdverMapperMock.returns(lists).selectchannelAdverByParamForSite(map);
		 assertEquals(1, channelAdverService.selectchannelAdverByParamForSite(1L, 1L, 1L, 1L, 1L, 1L, "1", "1", "1","1").size());
	 }
}
