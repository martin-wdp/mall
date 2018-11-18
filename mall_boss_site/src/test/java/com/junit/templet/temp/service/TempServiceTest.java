package com.junit.templet.temp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;

import com.ningpai.temp.bean.SysTemp;
import com.ningpai.temp.dao.SysTempDao;
import com.ningpai.temp.service.TempService;
import com.ningpai.temp.service.impl.TempServiceImpl;

/**
 * SERVICE-频道用模板service 测试
 * @author djk
 *
 */
public class TempServiceTest extends UnitilsJUnit3 
{
	/**
	 * 需要测试的接口类
	 */
	 @TestedObject
	 private TempService tempService = new TempServiceImpl();
	 
	 /**
	  *  模拟MOCK
      */
	 @InjectIntoByType
	 private Mock<SysTempDao> sysTempDaoMock;
	 
	 /**
	  * 模板设置测试
	  */
	 private SysTemp sysTemp = new SysTemp();
	 
	 /**
	  * 返回的数据集
	  */
	 private  List<SysTemp> list = new ArrayList<SysTemp>();
	 
	 @Override
	 protected void setUp() throws Exception
	 {
		 list.add(sysTemp);
	 }
	 
	 /**
	  * 获取所有模板设置对象集合测试
	  */
	 @Test
	 public void testQueryAllSystemp()
	 {
	       Map<String, Object> map = new HashMap<String, Object>();
           map.put("deleteStatus", "0");
           map.put("startRowNum", 0L);
           map.put("endRowNum", 1000L);
           sysTempDaoMock.returns(list).querySysTempByPage(map);
		   assertEquals(1, tempService.queryAllSystemp().size());
	 }
	 
	 /**
	  * 根据模板类型查询模板测试
	  */
	 @Test
	 public void testQuerySystempByType()
	 {
	        Map<String, Object> map = new HashMap<String, Object>();
	        map.put("tempType", 1L);
	        map.put("standby2", "0");
	        map.put("deleteStatus", "0");
	        map.put("startRowNum", 0L);
	        map.put("endRowNum", 1000L);
	        sysTempDaoMock.returns(list).getSysTempByField(map);
		    assertEquals(1, tempService.querySystempByType(1L).size());
	 }
	 
	 /**
	  *  根据主键查询模板设置对象测试
	  */
	 @Test
	 public void testGetSystempById()
	 {
		 sysTempDaoMock.returns(sysTemp).getSysTempById(1);
		 assertNotNull(tempService.getSystempById(1L));
	 }
	 
	 /**
	  * 修改模板设置对象测试
	  */
	 @Test
	 public void testUpdateSystemp()
	 {
		 sysTempDaoMock.returns(1).updateSysTemp(sysTemp);
		 assertEquals(1, tempService.updateSystemp(sysTemp));
	 }
	 
	 /**
	  * 获取当前使用的首页模板
	  */
	 @Test
	 public void testGetCurrTemp()
	 {
         Map<String, Object> map = new HashMap<String, Object>();
         map.put("tempType", 137L);
         map.put("standby2", "0");
         map.put("usedStatus", "1");
         map.put("deleteStatus", "0");
         map.put("startRowNum", 0L);
         map.put("endRowNum", 1000L);
         sysTempDaoMock.returns(list).getSysTempByField(map);
         assertNotNull(tempService.getCurrTemp());
	 }
	 
	 /**
	  * 设置该ID模板为当前使用首页模板模板测试
	  */
	 @Test
	 public void testSetUserd()
	 {
		 sysTempDaoMock.returns(1).setUserd(1L);
		 sysTempDaoMock.returns(1).removeUserd(1L);
		 tempService.setUserd(1L);
	 }
}
