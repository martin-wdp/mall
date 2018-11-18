package com.junit.system.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;

import com.ningpai.system.bean.PricePrecisionCof;
import com.ningpai.system.dao.IPricePrecisionCofDao;
import com.ningpai.system.service.IPricePrecisionCofBiz;
import com.ningpai.system.service.impl.PricePrecisionCofBizImpl;
import com.ningpai.util.PageBean;


/**
 * 价格精度设置业务接口测试类
 * @author djk
 *
 */
public class IPricePrecisionCofBizTest extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口
	 */
	@TestedObject
	private IPricePrecisionCofBiz iPricePrecisionCofBiz = new PricePrecisionCofBizImpl();
	
	/**
	 * 模拟MOCK
	 */
	@InjectIntoByType
	private Mock<IPricePrecisionCofDao> iPricePrecisionCofDaoMock;
	
	/**
	 * 价格精度设置
	 */
	private PricePrecisionCof precisionCof;

	
	/**
	 *  分页辅助类
	 */
	private PageBean pageBean;
	/**
	 * 参数map
	 */
	private Map<String,Object> map ;
	
	/**
	 * 查询的结果实体
	 */
	private List<Object> list ;
	
	@Override
	protected void setUp() throws Exception 
	{
		precisionCof = new PricePrecisionCof();
		
		pageBean = new PageBean();
		
		map = new HashMap<>();
		
		map.put("startRowNum", pageBean.getStartRowNum());
		map.put("endRowNum", pageBean.getEndRowNum());
		
		list = new ArrayList<>();
		list.add(new Object());
	}
	
	/**
	 * 保存价格精度设置测试
	 */
	@Test
	public void testSavePricePrecisionCof()
	{
		iPricePrecisionCofDaoMock.returns(true).savePricePrecisionCof(precisionCof);
		assertEquals(true, iPricePrecisionCofBiz.savePricePrecisionCof(precisionCof));
	}
	
	/**
	 * 修改价格精度设置
	 */
	@Test
	public void testUpdatePricePrecisionCof()
	{
		iPricePrecisionCofDaoMock.returns(1).updatePricePrecisionCof(precisionCof);
		assertEquals(1, iPricePrecisionCofBiz.updatePricePrecisionCof(precisionCof));
	}
	
	/**
	 *  根据价格精度设置对象的id查询价格精度设置对象测试
	 */
	@Test
	public void testGetPricePrecisionCofById()
	{
		iPricePrecisionCofDaoMock.returns(precisionCof).getPricePrecisionCofById(1);
		assertNotNull(iPricePrecisionCofBiz.getPricePrecisionCofById(1));
	}
	
	/**
	 * 根据价格精度设置对象的id字符集合查询价格精度设置对象测试
	 */
	@Test
	public void testGetPricePrecisionCofByIds()
	{
		List<PricePrecisionCof> list = new ArrayList<>();
		list.add(precisionCof);
		iPricePrecisionCofDaoMock.returns(list).getPricePrecisionCofByIds("1");
		assertEquals(1, iPricePrecisionCofBiz.getPricePrecisionCofByIds("1").size());
	}
	
	
	/**
	 * 根据价格精度设置对象的id字符集合删除价格精度设置对象测试
	 */
	@Test
	public void testDeletePricePrecisionCof()
	{
		iPricePrecisionCofDaoMock.returns(1).deletePricePrecisionCof("1");
		assertEquals(1, iPricePrecisionCofBiz.deletePricePrecisionCof("1"));
	}
	
	/**
	 * 更新价格精度设置对象的单个字段 其中要包含ids键，保存更新对象的id（多个id以，号分割）测试
	 */
	@Test
	public void testUpdatePricePrecisionCofFieldById()
	{
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("ids", "1,2");
		iPricePrecisionCofDaoMock.returns(1).updatePricePrecisionCofFieldById(map);
		assertEquals(1, iPricePrecisionCofBiz.updatePricePrecisionCofFieldById(map));
		List<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		assertEquals(list, map.get("ids"));
	}
	
	/**
     * 根据价格精度设置对象的单个字段查询价格精度设置对象信息总数 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End） 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
	 */
	@Test
	public void testGetPricePrecisionCofByFieldTotal()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("1", "1");
		iPricePrecisionCofDaoMock.returns(1).getPricePrecisionCofByFieldTotal(map);
		assertEquals(1, iPricePrecisionCofBiz.getPricePrecisionCofByFieldTotal(map));
	}
	
	/**
     * 根据价格精度设置对象的单个字段查询价格精度设置对象信息 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
	 */
	@Test
	public void testGetPricePrecisionCofByField()
	{
		iPricePrecisionCofDaoMock.returns(1).getPricePrecisionCofByFieldTotal(map);
		iPricePrecisionCofDaoMock.returns(list).getPricePrecisionCofByField(map);
		assertEquals(1, iPricePrecisionCofBiz.getPricePrecisionCofByField(map, pageBean).getList().size());
	}
	
	/**
	 * 查询价格精度设置对象信息总数测试
	 */
	@Test
	public void testQueryPricePrecisionCofTotal()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("1", "1");
		iPricePrecisionCofDaoMock.returns(1).queryPricePrecisionCofTotal(map);
		assertEquals(1, iPricePrecisionCofBiz.queryPricePrecisionCofTotal(map));
	}
	
	/**
	 * 分页查询价格精度设置对象信息 如果需要分页查询，开始为：startRowNum，结束为：endRowNum测试
	 */
	@Test
	public void testQueryPricePrecisionCofByPage()
	{
		iPricePrecisionCofDaoMock.returns(1).queryPricePrecisionCofTotal(map);
		iPricePrecisionCofDaoMock.returns(list).queryPricePrecisionCofByPage(map);
		assertEquals(1, iPricePrecisionCofBiz.queryPricePrecisionCofByPage(map, pageBean).getList().size());
	}
}
