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

import com.ningpai.channel.service.ChannelSysTempService;
import com.ningpai.channel.service.impl.ChannelSysTempServiceImpl;
import com.ningpai.temp.bean.SysTemp;
import com.ningpai.temp.dao.SysTempDao;

/**
 * SERVICE-频道用模板service测试
 * @author djk
 *
 */
public class ChannelSysTempServiceTest extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口类
	 */
	 @TestedObject
	 private ChannelSysTempService channelSysTempService = new ChannelSysTempServiceImpl();
	 
	 /**
	 *  模拟MOCK
	 */
	 @InjectIntoByType
	 private Mock<SysTempDao> sysTempDaoMock;
	 
	 /**
	  * 获取所有模板设置对象集合
	  */
	 @Test
	 public void testQueryAllSystemp()
	 {
		 List<SysTemp>  lists = new ArrayList<>();
		 lists.add(new SysTemp());
         Map<String, Object> map = new HashMap<String, Object>();
         map.put("startRowNum", 0L);
         map.put("endRowNum", 1000L);
         sysTempDaoMock.returns(lists).querySysTempByPage(map);
		 assertEquals(1, channelSysTempService.queryAllSystemp().size());
	 }
	 
	 /**
	  * 根据主键查询模板设置对象测试
	  */
	 @Test
	 public void testGetSystempById()
	 {
		 sysTempDaoMock.returns(new SysTemp()).getSysTempById(1);
		 assertNotNull(channelSysTempService.getSystempById(1L));
	 }
}
