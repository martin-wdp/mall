package com.junit.templet.temp.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;

import com.ningpai.temp.bean.TempToken;
import com.ningpai.temp.dao.TempTokenMapper;
import com.ningpai.temp.service.TempTokenService;
import com.ningpai.temp.service.impl.TempTokenServiceImpl;

/**
 * SERVICE-模板内容变更token测试
 * @author djk
 *
 */
public class TempTokenServiceTest extends UnitilsJUnit3 
{

	/**
	 * 需要测试的接口类
	 */
	 @TestedObject
	 private TempTokenService tempTokenService = new TempTokenServiceImpl();
	 
	 /**
	  *  模拟MOCK
      */
	 @InjectIntoByType
	 private Mock<TempTokenMapper> tempTokenMapperMock;
	 
	 /**
	  * 实体类-模板内容变更token
	  */
	 private TempToken tempToken = new TempToken();
	 
	 /**
	  * 添加token测试
	  */
	 @Test
	 public void testCreateToken()
	 {
		 tempTokenMapperMock.returns(1).insertSelective(tempToken);
		 assertEquals(1, tempTokenService.createToken(tempToken));
	 }
	 
	 /**
	  * 更新token测试
	  */
	 @Test
	 public void testUpdateToken()
	 {
		 tempTokenMapperMock.returns(1).updateByPrimaryKeySelective(tempToken);
		 assertEquals(1, tempTokenService.updateToken(tempToken));
	 }
	 
	 /**
	  * 删除token测试
	  */
	 @Test
	 public void testDeleteToken()
	 {
		 tempTokenMapperMock.returns(1).deleteByPrimaryKey(1L);
		 assertEquals(1, tempTokenService.deleteToken(1L));
	 }
	 
	 /**
	  * 获取当前token测试
	  */
	 @Test
	 public void testGetCurrToken()
	 {
		List<TempToken> list = new ArrayList<>();
		list.add(tempToken);
		tempTokenMapperMock.returns(list).selectAllToken();
		assertNotNull(tempTokenService.getCurrToken()); 
	 }
	 
	 /**
	  * 根据类型，获取首页内容变更token测试
	  */
	 @Test
	 public void testSelectTokenByType()
	 {
		 tempTokenMapperMock.returns(tempToken).selectTokenByType("a");
		 assertNotNull(tempTokenService.selectTokenByType("a"));
	 }
	 
	 /**
	  * 
	  */
	 @Test
	 public void testUpdateTokenValue()
	 {
		 tempTokenMapperMock.returns(1).updateTokenValue(tempToken);
		 assertEquals(1, tempTokenService.updateTokenValue(tempToken));
	 }
}
