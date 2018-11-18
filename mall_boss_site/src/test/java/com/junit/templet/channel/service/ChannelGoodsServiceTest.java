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

import com.ningpai.channel.bean.ChannelStoreyGoods;
import com.ningpai.channel.dao.ChannelStoreyGoodsMapper;
import com.ningpai.channel.service.ChannelGoodsService;
import com.ningpai.channel.service.impl.ChannelGoodsServiceImpl;
import com.ningpai.util.PageBean;

/**
 * SERVICE-热销推荐商品测试
 * @author djk
 *
 */
public class ChannelGoodsServiceTest  extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口类
	 */
	 @TestedObject
	 private ChannelGoodsService channelGoodsService = new ChannelGoodsServiceImpl();
	 
	/**
	 *  模拟MOCK
	 */
	 @InjectIntoByType
	 private Mock<ChannelStoreyGoodsMapper> channelStoreyGoodsMapperMock;
		 
	 /**
	  * 实体类-楼层商品测试
	  */
     private ChannelStoreyGoods channelStoreyGoods = new ChannelStoreyGoods();
     
     /**
      * 根据主键删除（彻底删除）测试
      */
     @Test
     public void testDeleteChannelStoreyGoodsnew()
     {
    	 channelStoreyGoods.setStoreyId(1L);
    	 channelStoreyGoodsMapperMock.returns(channelStoreyGoods).selectByPrimaryKey(1L);
    	 Map<String, Object> map = new HashMap<>();
         // 要删除的主键
         map.put("productId", 1L);
         // 当前商品对应的楼层ID
         map.put("storeyId", 1L);
         channelStoreyGoodsMapperMock.returns(1).deleteChannelStoreyGoodsnew(map);
    	 assertEquals(1, channelGoodsService.deleteChannelStoreyGoodsnew(1L));
     }
     
     /**
     * 根据主键删除(这个只是进行了状态修改 不是彻底删除 新增商品的时候 就会出现要添加的商品ID存在的问题  id是去的当前商品的ID并不是自增长的ID)
      */
     @Test
     public void testDeleteChannelStoreyGoods()
     {
    	 channelStoreyGoodsMapperMock.returns(channelStoreyGoods).selectByPrimaryKey(1L);
    	 channelStoreyGoodsMapperMock.returns(1).updateByPrimaryKeySelective(channelStoreyGoods);
    	 assertEquals(1, channelGoodsService.deleteChannelStoreyGoods(1L, 1L));
     }
     
     /**
      * 添加测试
      */
     @Test
     public void testSaveChannelStoreyGoods()
     {
    	 channelStoreyGoodsMapperMock.returns(1).insertSelective(channelStoreyGoods);
    	 assertEquals(1, channelGoodsService.saveChannelStoreyGoods(channelStoreyGoods));
     }
     
     /**
      * 修改测试
      */
     @Test
     public void testUpdateChannelStoreyGoods()
     {
    	 channelStoreyGoodsMapperMock.returns(1).updateByPrimaryKeySelective(channelStoreyGoods);
    	 assertEquals(1, channelGoodsService.updateChannelStoreyGoods(channelStoreyGoods));
     }
     
     /**
      *  根据主键查询测试
      */
     @Test
     public void testGetChannelStoreyGoodsById()
     {
    	 channelStoreyGoodsMapperMock.returns(channelStoreyGoods).selectByPrimaryKey(1L);
    	 assertNotNull(channelGoodsService.getChannelStoreyGoodsById(1L));
     }
     
     /**
      * 根据分页参数和楼层ID、楼层标签ID、是否热销查询频道楼层商品测试
      */
     @Test
     public void testSelectchannelStoreyGoodsByParam()
     {
    	 PageBean pb = new PageBean();
         Map<String, Object> map = new HashMap<String, Object>();
         map.put("isHot", "1");
         map.put("temp1", "1");
         map.put("temp2", "1");
         map.put("temp4", "1");
         channelStoreyGoodsMapperMock.returns(1).selectchannelStoreyGoodsCountByParam(map);
         map.put("startRowNum", pb.getStartRowNum());
         map.put("endRowNum", pb.getEndRowNum());
         List<Object> lists = new ArrayList<>();
         lists.add(new Object());
         channelStoreyGoodsMapperMock.returns(lists).selectchannelStoreyGoodsByParam(map);
    	 assertEquals(1, channelGoodsService.selectchannelStoreyGoodsByParam(pb, "1", "1", "1").getList().size());
     }
     
     /**
      *  根据分页参数和楼层ID、楼层标签ID、是否热销查询频道楼层商品-前台展示用测试
      */
     @Test
     public void testSelectchannelStoreyGoodsByParamForSite()
     {
    	 List<ChannelStoreyGoods> lists = new ArrayList<>();
    	 lists.add(channelStoreyGoods);
         Map<String, Object> map = new HashMap<String, Object>();
         map.put("temp1", "1");
         map.put("temp2", "1");
         map.put("temp4", "1");
         map.put("isHot", "1");
         channelStoreyGoodsMapperMock.returns(lists).selectchannelStoreyGoodsByParamForSite(map);
    	 assertEquals(1, channelGoodsService.selectchannelStoreyGoodsByParamForSite("1", "1", "1").size());
     }
     
     /**
      * 根据分页参数和楼层ID、楼层标签ID、是否热销查询频道楼层商品-前台展示用测试
      */
     @Test
     public void testSelectchannelStoreyGoodsByParamForSiteRandom()
     {
    	 List<ChannelStoreyGoods> lists = new ArrayList<>();
    	 lists.add(channelStoreyGoods);
         Map<String, Object> map = new HashMap<String, Object>();
         map.put("temp1", "1");
         map.put("temp2", "1");
         channelStoreyGoodsMapperMock.returns(lists).selectchannelStoreyGoodsByParamForSiteRandom(map);
    	 assertEquals(1, channelGoodsService.selectchannelStoreyGoodsByParamForSiteRandom("1", "1").size());
     }
     
}
