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

import com.ningpai.channel.bean.ChannelAdver;
import com.ningpai.channel.bean.GoodsCate;
import com.ningpai.channel.dao.GoodsCateMapper;
import com.ningpai.mobile.bean.MobCateBar;
import com.ningpai.mobile.dao.MobCateBarMapper;
import com.ningpai.mobile.service.MobCateBarService;
import com.ningpai.mobile.service.impl.MobCateBarServiceImpl;
import com.ningpai.mobile.vo.MobCateBarVo;
import com.ningpai.util.PageBean;

/**
 *  SERVICE-移动版分类导航测试
 * @author djk
 *
 */
public class MobCateBarServiceTest extends UnitilsJUnit3 
{
	/**
	 * 需要测试的接口类
	 */
	 @TestedObject
	 private MobCateBarService mobCateBarService = new MobCateBarServiceImpl();
	 
	/**
     *  模拟MOCK
	 */
     @InjectIntoByType
	 private Mock<MobCateBarMapper> mobCateBarMapperMock;
	 
 	/**
      *  模拟MOCK
 	 */
     @InjectIntoByType
	 private Mock<GoodsCateMapper> goodsCateMapperMock;
     
     /**
      * 实体类-移动版分类导航
      */
     private MobCateBar mobCateBar = new MobCateBar();
     
     /**
      * 实体类-频道广告
      */
     private ChannelAdver channelAdver = new ChannelAdver();
     
     /**
      * 查询移动端店铺页轮播大广告测试
      */
     @Test
     public void testSelectStoreListImage()
     {
    	 List<ChannelAdver> list = new ArrayList<>();
    	 list.add(channelAdver);
    	 mobCateBarMapperMock.returns(list).selectStoreListImage("a");
    	 assertEquals(1, mobCateBarService.selectStoreListImage("a").size());
     }
     
     /**
      * 查询未删除的分类导航分页数据，返回分页工具bean测试
      */
     @Test
     public void testSelectMobCateBarByPb()
     {
    	 PageBean pb = new PageBean();
         Map<String, Object> map = new HashMap<String, Object>();
         mobCateBarMapperMock.returns(1).selectCountByPb();
         List<Object> list = new ArrayList<>();
         list.add(new Object());
         map.put("startRowNum", pb.getStartRowNum());
         map.put("endRowNum", pb.getEndRowNum());
         mobCateBarMapperMock.returns(list).selectAllByPb(map);
    	 assertEquals(1, mobCateBarService.selectMobCateBarByPb(pb).getList().size());
     }
     
     /**
      *  查询所有未删除、已启用的分类导航，返回List，前台调用测试
      */
     @Test
     public void testSelectMobCateBarForSite()
     {
    	 List<MobCateBarVo>  list = new ArrayList<>();
    	 list.add(new MobCateBarVo());
    	 mobCateBarMapperMock.returns(list).selectAll();
    	 assertEquals(1, mobCateBarService.selectMobCateBarForSite().size());
     }
     
     /**
      *  查询所有未删除、已启用的分类导航，返回List，后台选择用测试
      */
     @Test
     public void testSelectMobCateBarForMobChoose()
     {
    	 List<MobCateBar> list = new ArrayList<>();
    	 list.add(mobCateBar);
    	 mobCateBarMapperMock.returns(list).selectAllForMobChoose();
    	 assertEquals(1, mobCateBarService.selectMobCateBarForMobChoose().size());
     }
     
     /**
      * 根据ID查询分类导航测试
      */
     @Test
     public void testSelectMobcateBarById()
     {
    	 mobCateBarMapperMock.returns(mobCateBar).selectByPrimaryKey(1L);
    	 assertNotNull(mobCateBarService.selectMobcateBarById(1L));
     }
     
     /**
      * 添加分类导航测试
      */
     @Test
     public void testCreateMobCateBar()
     {
	     List<GoodsCate> list = new ArrayList<>();
	     list.add(new GoodsCate());
	     mobCateBar.setCateId(1L);
    	 mobCateBar.setGrade(1);
    	 mobCateBarMapperMock.returns(1).insertSelective(mobCateBar);
    	 goodsCateMapperMock.returns(list).queryGoosCateByParentId(1L);
    	 mobCateBarMapperMock.returns(1).insertSelective(mobCateBar);
    	 assertEquals(1, mobCateBarService.createMobCateBar(mobCateBar));
     }
     
     /**
      *  修改分类导航，层级不可变，设置为不启用时，它的子分类也要设置为不启用测试
      */
     @Test
     public void testUpdateMobCateBar()
     {
    	 mobCateBar.setIsUsing("0");
    	 mobCateBar.setCateBarId(1L);
    	 List<MobCateBar> list = new ArrayList<>();
    	 list.add(mobCateBar);
    	 mobCateBarMapperMock.returns(list).selectByParentId(1L);
    	 mobCateBarMapperMock.returns(1).updateByPrimaryKeySelective(mobCateBar);
      	 mobCateBarMapperMock.returns(1).updateByPrimaryKeySelective(mobCateBar);
    	 assertEquals(1, mobCateBarService.updateMobCateBar(mobCateBar));
     }
     
     /**
      * 删除分类导航，删除时要级联删除它的子分类测试
      */
     @Test
     public void testDeleteMobCateBar()
     {
    	 mobCateBar.setCateBarId(1L);
    	 mobCateBarMapperMock.returns(mobCateBar).selectByPrimaryKey(1L);
         List<MobCateBar> list = new ArrayList<>();
         list.add(mobCateBar);
         mobCateBarMapperMock.returns(list).selectByParentId(1L);
         mobCateBarMapperMock.returns(1).updateByPrimaryKeySelective(mobCateBar);
      	 mobCateBarMapperMock.returns(1).updateByPrimaryKeySelective(mobCateBar);
    	 assertEquals(1, mobCateBarService.deleteMobCateBar(1L));
     }
     
     /**
      * 验证分类导航的唯一性，用关联的商品分类ID验证测试
      */
     @Test
     public void testCheckMobCateBarIsOnly()
     {
    	 mobCateBarMapperMock.returns(0).selectCountByCateId(1L);
    	 assertEquals(true, mobCateBarService.checkMobCateBarIsOnly(1L));
     }
     
     /**
      *  验证是否可删除分类导航测试
      */
     @Test
     public void testCheckDelete()
     {
    	 List<MobCateBar> list = new ArrayList<>();
    	 list.add(mobCateBar);
    	 mobCateBarMapperMock.returns(list).selectByParentId(1L);
    	 assertEquals(false, mobCateBarService.checkDelete(1L));
     }
     
     /**
      * 修改分类导航的启用状态测试
      */
     @Test
     public void testChangeUserdStatus()
     {
    	 mobCateBar.setIsUsing("1");
    	 mobCateBar.setGrade(1);
    	 mobCateBar.setCateBarId(1L);
    	 List<MobCateBar> list = new ArrayList<>();
    	 list.add(mobCateBar);
    	 
    	 mobCateBarMapperMock.returns(mobCateBar).selectByPrimaryKey(1L);
    	 mobCateBarMapperMock.returns(list).selectByParentId(1L);
    	 mobCateBarMapperMock.returns(1).updateByPrimaryKeySelective(mobCateBar);
       	 mobCateBarMapperMock.returns(1).updateByPrimaryKeySelective(mobCateBar);
    	 assertEquals(1, mobCateBarService.changeUserdStatus(1L));
     }
}
