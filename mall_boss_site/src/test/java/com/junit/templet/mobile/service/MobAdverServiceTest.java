package com.junit.templet.mobile.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;

import com.ningpai.mobile.bean.MobAdver;
import com.ningpai.mobile.dao.MobAdverMapper;
import com.ningpai.mobile.service.MobAdverService;
import com.ningpai.mobile.service.impl.MobAdverServiceImpl;
import com.ningpai.util.PageBean;

/**
 * SERVICE-移动版广告测试
 * @author djk
 *
 */
public class MobAdverServiceTest  extends UnitilsJUnit3 
{
	/**
	 * 需要测试的接口类
	 */
	 @TestedObject
	 private MobAdverService mobAdverService = new MobAdverServiceImpl();
	 
	/**
     *  模拟MOCK
	 */
     @InjectIntoByType
	 private Mock<MobAdverMapper> mobAdverMapperMock;
     
     /**
      *  实体类-移动版广告<br/>
      */
     private MobAdver mobAdver = new MobAdver();
     
     /**
      * 批量删除测试
      */
     @Test
     public void testBatchDelMobAdver()
     {
    	 List<Long> ids = new ArrayList<>();
    	 ids.add(1L);
    	 mobAdverMapperMock.returns(1).batchDelMobAdver(ids);
    	 assertEquals(1, mobAdverService.batchDelMobAdver(ids));
     }
     
     /**
      * 删除测试
      */
     @Test
     public void testDeleteMobAdver()
     {
    	 mobAdverMapperMock.returns(1).deleteByPrimaryKey(1L);
    	 assertEquals(1, mobAdverService.deleteMobAdver(1L));
     }
     
     /**
      * 修改测试
      */
     @Test
     public void testUpdateMobAdver()
     {
    	 mobAdverMapperMock.returns(1).updateByPrimaryKeySelective(mobAdver);
    	 assertEquals(1, mobAdverService.updateMobAdver(mobAdver));
     }
     
     /**
      * 添加测试
      */
     @Test
     public void testCreateMobAdver()
     {
    	 mobAdverMapperMock.returns(1).insertSelective(mobAdver);
    	 assertEquals(1, mobAdverService.createMobAdver(mobAdver));
     }
     
     /**
      * 根据ID查询测试
      */
     @Test
     public void testGetMobAdver()
     {
    	 mobAdverMapperMock.returns(mobAdver).selectByPrimaryKey(1L);
    	 assertNotNull(mobAdverService.getMobAdver(1L));
     }
     
     /**
      * 根据楼层ID分页查询未删除的广告列表,首页广告楼层ID为-1测试
      */
     @Test
     public void testSelectByStoreyIdAndPb()
     {
    	 PageBean pb = new PageBean();
         Map<String, Object> map = new HashMap<String, Object>();
         mobAdverMapperMock.returns(1).selectCountByStoreyId(1L);
         map.put("storeyId", 1L);
         map.put("startRowNum", pb.getStartRowNum());
         map.put("endRowNum", pb.getEndRowNum());
         List<Object> lists = new ArrayList<>();
         lists.add(new Object());
         mobAdverMapperMock.returns(lists).selectByStoreyIdAndPb(map);
    	 assertEquals(1, mobAdverService.selectByStoreyIdAndPb(pb, 1L).getList().size());
     }
     
     /**
      * 根据楼层ID查询未删除的广告数量,验证是否可删除楼层<br/>测试
      */
     @Test
     public void testSelectCountByStoreyId()
     {
    	 mobAdverMapperMock.returns(0).selectCountByStoreyId(1L);
    	 assertEquals(true, mobAdverService.selectCountByStoreyId(1L));
     }
     
     /**
      * 根据楼层ID查询未删除、已启用的广告,前台展示用,首页广告楼层ID为-1测试
      */
     @Test
     public void testSelectByStoreyIdForSite()
     {
    	    List<MobAdver> list = new ArrayList<>();
    	    list.add(mobAdver);
    	    mobAdverMapperMock.returns(list).selectByStoreyIdForSite(1L);
    	    assertEquals(1, mobAdverService.selectByStoreyIdForSite(1L).size());
     }
     
     /**
      * 修改移动版广告启用状态测试
      */
     @Test
     public void testChangeMobAdverUserdStatus()
     {
    	 mobAdver.setUserStatus("0");
    	 mobAdverMapperMock.returns(mobAdver).selectByPrimaryKey(1L);
    	 mobAdverMapperMock.returns(1).updateByPrimaryKeySelective(mobAdver);
    	 assertEquals(1, mobAdverService.changeMobAdverUserdStatus(1L));
    	 assertEquals("1", mobAdver.getUserStatus());
     }
	 
}
