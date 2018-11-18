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

import com.ningpai.mobile.bean.MobStorey;
import com.ningpai.mobile.dao.MobAdverMapper;
import com.ningpai.mobile.dao.MobStoreyMapper;
import com.ningpai.mobile.service.MobStoreyService;
import com.ningpai.mobile.service.impl.MobStoreyServiceImpl;
import com.ningpai.util.PageBean;

public class MobStoreyServiceTest extends UnitilsJUnit3 
{
	/**
	 * 需要测试的接口类
	 */
	 @TestedObject
	 private MobStoreyService mobStoreyService = new MobStoreyServiceImpl();
	 
	 /**
     *  模拟MOCK
	 */
     @InjectIntoByType
	 private Mock<MobStoreyMapper> mobStoreyMapperMock;
	 
 	 /**
      *  模拟MOCK
 	 */
      @InjectIntoByType
	 private Mock<MobAdverMapper> mobAdverMapperMock;
      
     /**
      * 实体类-移动版楼层
      */
     private MobStorey mobStorey = new MobStorey();
      
     /**
      * 删除楼层测试
      */
      @Test
     public void testDeleteMobStorey()
     {
    	 mobAdverMapperMock.returns(1).deleteByStoreyId(1L);
    	 mobStoreyMapperMock.returns(1).deleteByPrimaryKey(1L);
    	 assertEquals(2, mobStoreyService.deleteMobStorey(1L));
     }
      
     /**
      * 添加楼层测试
      */
      @Test
     public void testcCreateMobStorey()
     {
    	  mobStoreyMapperMock.returns(1).insertSelective(mobStorey);
    	  assertEquals(1, mobStoreyService.createMobStorey(mobStorey));
     }
     
      /**
       * 修改楼层测试
       */
      @Test
     public void testUpdateMobStorey()
     {
    	  mobStoreyMapperMock.returns(1).updateByPrimaryKeySelective(mobStorey);
    	  assertEquals(1, mobStoreyService.updateMobStorey(mobStorey));
     }
      
     /**
      * 查看楼层测试
      */
      @Test
     public void testGetMobStorey()
     {
    	  mobStoreyMapperMock.returns(mobStorey).selectByPrimaryKey(1L);
    	 assertNotNull(mobStoreyService.getMobStorey(1L));
     }
      
      /**
       * 分页查询楼层测试
       */
      @Test
     public void testSelectMobStoreyByPb()
     {
    	 PageBean pb = new PageBean();
    	 Map<String, Object> map = new HashMap<String, Object>();
    	 mobStoreyMapperMock.returns(1).selectCount();
         map.put("startRowNum", pb.getStartRowNum());
         map.put("endRowNum", pb.getEndRowNum());
         List<Object> list = new ArrayList<>();
         list.add(new Object());
         mobStoreyMapperMock.returns(list).selectByPb(map);
    	 assertEquals(1, mobStoreyService.selectMobStoreyByPb(pb).getList().size());
     }
      
      /**
       *  查询所有楼层测试
       */
      @Test
      public void testSelectMobStoreyForSite()
      {
    	  List<MobStorey> list = new ArrayList<>();
    	  list.add(mobStorey);
    	  mobStoreyMapperMock.returns(list).selectAllForSite();
    	  assertEquals(1, mobStoreyService.selectMobStoreyForSite().size());
      }
      
      /**
       *  修改楼层启用状态测试
       */
      @Test
      public void testChangeMobStoreyUserdStatus()
      {
    	  mobStorey.setUserStatus("0");
    	  mobStoreyMapperMock.returns(mobStorey).selectByPrimaryKey(1L);
    	  mobStoreyMapperMock.returns(1).updateByPrimaryKeySelective(mobStorey);
    	  assertEquals(1, mobStoreyService.changeMobStoreyUserdStatus(1L));
    	  assertEquals("1", mobStorey.getUserStatus());
      }
}
