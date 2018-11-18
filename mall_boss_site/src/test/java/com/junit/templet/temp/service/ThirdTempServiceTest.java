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
import com.ningpai.temp.dao.ThirdTempDao;
import com.ningpai.temp.service.ThirdTempService;
import com.ningpai.temp.service.impl.ThirdTempServiceImpl;

public class ThirdTempServiceTest extends UnitilsJUnit3 
{
	/**
	 * 需要测试的接口类
	 */
	 @TestedObject
	 private ThirdTempService thirdTempService = new ThirdTempServiceImpl();
	 
	 
	 /**
	  *  模拟MOCK
      */
	 @InjectIntoByType
	 private Mock<ThirdTempDao> thirdTempDaoMock;
	 
	 /**
	  * 模板设置
	  */
	 private SysTemp sysTemp = new SysTemp();
	 
	 /**
	  * 根据第三方商家ID和模板类型查询模板测试
	  */
	 @Test
	 public void testQuerySystempByType()
	 {
		  List<SysTemp> list = new ArrayList<>();
		  list.add(sysTemp);
	      Map<String, Object> map = new HashMap<String, Object>();
	      map.put("tempType", 1L);
	      map.put("standby2", "1");
	      thirdTempDaoMock.returns(list).getSysTempByField(map);
		  assertEquals(1, thirdTempService.querySystempByType(1L).size());
	 }
	 
	 /**
	  *  根据主键查询模板设置对象测试
	  */
	 @Test
	 public void testGetSystempById()
	 {
		 thirdTempDaoMock.returns(sysTemp).getSysTempById(1);
		 assertNotNull(thirdTempService.getSystempById(1L));
	 }
	 
	 /**
	  * 修改模板设置对象测试
	  */
	 @Test
	 public void testUpdateSystemp()
	 {
		 thirdTempDaoMock.returns(1).updateSysTemp(sysTemp);
		 assertEquals(1, thirdTempService.updateSystemp(sysTemp));
	 }
	 
	 /**
	  *  获取当前使用的首页模板测试
	  */
	 @Test
	 public void testGetCurrTemp()
	 {
	     List<SysTemp> list = new ArrayList<>();
	     list.add(sysTemp);
         Map<String, Object> map = new HashMap<String, Object>();
         map.put("tempType", 137L);
         map.put("usedStatus", "1");
         map.put("deleteStatus", "0");
         map.put("standby2", "1");
         thirdTempDaoMock.returns(list).getSysTempByField(map);
		 assertNotNull(thirdTempService.getCurrTemp());
	 }
}
