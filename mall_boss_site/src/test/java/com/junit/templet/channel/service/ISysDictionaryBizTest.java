package com.junit.templet.channel.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;



import com.ningpai.channel.bean.SysDictionary;
import com.ningpai.channel.dao.ChannelSysDictionaryMapper;
import com.ningpai.channel.service.ISysDictionaryBiz;
import com.ningpai.channel.service.impl.SysDictionaryBizImpl;

/**
 * 系统字典业务接口测试
 * @author djk
 *
 */
public class ISysDictionaryBizTest extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口类
	 */
	 @TestedObject
	 private  ISysDictionaryBiz iSysDictionaryBiz = new  SysDictionaryBizImpl();
	 
	 /**
	 *  模拟MOCK
	 */
	 @InjectIntoByType
	 private Mock<ChannelSysDictionaryMapper> channelSysDictionaryMapperMock;
	 
	 /**
	  *  根据系统字典对象的id查询系统字典对象测试
	  */
	 @Test
	 public void testGetSysDictionaryById()
	 {
		 channelSysDictionaryMapperMock.returns(new SysDictionary()).selectByPrimaryKey(1);
		 assertNotNull(iSysDictionaryBiz.getSysDictionaryById(1));
	 }
	 
	 /**
	  * 根据系统字典对象的单个字段查询系统字典对象信息测试
	  */
	 @Test
	 public void testgetSysDictionaryByField()
	 {
		 List<SysDictionary>  list = new ArrayList<>();
		 list.add(new SysDictionary());
		 channelSysDictionaryMapperMock.returns(list).getSysDictionaryByField(1L);
		 assertEquals(1, iSysDictionaryBiz.getSysDictionaryByField(1L).size());
	 }
	 
}
