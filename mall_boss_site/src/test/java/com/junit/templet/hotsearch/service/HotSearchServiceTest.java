package com.junit.templet.hotsearch.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;

import com.ningpai.hotsearch.bean.HotSearch;
import com.ningpai.hotsearch.dao.HotSearchMapper;
import com.ningpai.hotsearch.service.HotSearchService;
import com.ningpai.hotsearch.service.impl.HotSearchServiceImpl;
import com.ningpai.util.PageBean;

/**
 * 热门搜索测试类
 * @author djk
 *
 */
public class HotSearchServiceTest  extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口类
	 */
	 @TestedObject
	 private HotSearchService hotSearchService = new HotSearchServiceImpl();
	 
	/**
     *  模拟MOCK
	 */
     @InjectIntoByType
	 private Mock<HotSearchMapper> hotSearchMapperMock;
     
     /**
      *  热门搜索类
      */
     private HotSearch hotSearch = new HotSearch();
     
     /**
      * 根据主键Id，删除热门搜索记录测试
      */
     @Test
     public void testRemoveHotSearchById()
     {
    	 hotSearchMapperMock.returns(hotSearch).selectByPrimaryKey(1L);
    	 hotSearchMapperMock.returns(1).updateByPrimaryKeySelective(hotSearch);
    	 assertEquals(1, hotSearchService.removeHotSearchById(1L));
     }
     
     /**
      * 批量删除测试 
      */
     @Test
     public void testBatchDelHotSearch()
     {
    	 Long [] hots ={1L};
    	 List<Object> list = new ArrayList<Object>();
    	 list.add(1L);
    	 hotSearchMapperMock.returns(1).batchDelHotSearch(list);
    	 assertEquals(1, hotSearchService.batchDelHotSearch(hots));
     }
     
     /**
      *  选择字段添加热门搜索记录
      */
     @Test
     public void testAddHotSearchSelective()
     {
    	 hotSearchMapperMock.returns(1).insertSelective(hotSearch);
    	 assertEquals(1, hotSearchService.addHotSearchSelective(hotSearch));
     }
     
     /**
      * 修改热门搜索记录可选择字段，根据主键Id测试
      */
     @Test
     public void testModifyHostSearchSelectiveById()
     {
    	 hotSearchMapperMock.returns(1).updateByPrimaryKeySelective(hotSearch);
    	 assertEquals(1, hotSearchService.modifyHostSearchSelectiveById(hotSearch));
     }
     
     /**
      * 根据主键ID，查询热门搜索表记录测试
      */
     @Test
     public void testSelectHotSearchById()
     {
    	 hotSearchMapperMock.returns(hotSearch).selectByPrimaryKey(1L);
    	 assertNotNull(hotSearchService.selectHotSearchById(1L));
     }
     
     /**
      * 根据条件查询分页热门搜索记录测试
      */
     @Test
     public void testQueryHotBySelectivePageSize()
     {
    	 PageBean pageBean = new PageBean();
         Map<String, Object> map = new HashMap<String, Object>();
         map.put("keyword", "1");
         map.put("tempid", 1L);
         map.put("channelid", 1L);
         hotSearchMapperMock.returns(1).selectHotSearchCount(map);
         map.put("startRowNum", pageBean.getStartRowNum());
         map.put("endRowNum", pageBean.getEndRowNum());
         List<Object> list = new ArrayList<>();
         list.add(new Object());
         hotSearchMapperMock.returns(list).selectHotBySelectivePageSize(map);
    	 assertEquals(1, hotSearchService.queryHotBySelectivePageSize("1", 1L, 1L, pageBean).getList().size());
     }
     
     /**
      * 根据模板ID、频道ID查询热门搜索-前台展示用测试
      */
     @Test
     public void testSelectHotBySelectiveForSite()
     {
    	 List<HotSearch> list = new ArrayList<>();
    	 list.add(hotSearch);
         Map<String, Object> map = new HashMap<String, Object>();
         map.put("tempid", 1L);
         map.put("channelid", 1L);
         hotSearchMapperMock.returns(list).selectHotBySelectiveForSite(map);
    	 assertEquals(1, hotSearchService.selectHotBySelectiveForSite(1L, 1L).size());
     }
}
