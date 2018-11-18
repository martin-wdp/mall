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

import com.ningpai.system.bean.StockWarning;
import com.ningpai.system.dao.StockWarningMapper;
import com.ningpai.system.service.StockWarningService;
import com.ningpai.system.service.impl.StockWarningServiceImpl;
import com.ningpai.system.util.StorkWarningUtil;
import com.ningpai.util.PageBean;
import com.ningpai.util.SelectBean;

/**
 * 库存报警查询库存下限业务接口测试
 * @author djk
 *
 */
public class StockWarningServiceTest extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口
	 */
	@TestedObject
	private StockWarningService stockWarningService = new StockWarningServiceImpl();
	
	/**
	 * 模拟MOCK
	 */
	@InjectIntoByType
	private Mock<StockWarningMapper> stockWarningMapperMock;
	
	/**
	 * 库存报警表测试
	 */
	private StockWarning stockWarning = new StockWarning();
	
	/**
	 * 查询货品辅助bean
	 */
	private StorkWarningUtil storkWarningUtil = new StorkWarningUtil();
	
	/**
	 * 分页辅助类测试
	 */
	private PageBean pageBean = new PageBean();
	
	/**
	 *  查询库存下限测试
	 */
	@Test
	public void testSelect()
	{
		stockWarningMapperMock.returns(stockWarning).select();
		assertNotNull(stockWarningService.select());
	}
	
	/**
	 * 更新库存下限测试
	 */
	@Test
	public void testUpdate()
	{
		stockWarningMapperMock.returns(1).update(stockWarning);
		assertEquals(1, stockWarningService.update(stockWarning));
	}
	
	/**
	 *  查询库存预警货品信息测试
	 */
	@Test
	public void testSelectGoods()
	{
        List<Object> goodsList = new ArrayList<Object>();
        goodsList.add(new Object());
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("startRowNum", pageBean.getStartRowNum());
        paramMap.put("endRowNum", pageBean.getEndRowNum());
        paramMap.put("id", storkWarningUtil.getId());
        paramMap.put("goodname", storkWarningUtil.getGoodname());
        paramMap.put("number", storkWarningUtil.getNumber());
        paramMap.put("storename", storkWarningUtil.getStorename());
        paramMap.put("stock", storkWarningUtil.getStock());
        paramMap.put("warename", storkWarningUtil.getWarename());
        paramMap.put("wareid", storkWarningUtil.getWareid());
        paramMap.put("thirdid", storkWarningUtil.getThirdid());
        paramMap.put("stockWarnSpec", storkWarningUtil.getStockWarnSpec());
        paramMap.put("goodsName", storkWarningUtil.getGoodsName());
        paramMap.put("goodsNo", storkWarningUtil.getGoodsNo());
        paramMap.put("isThird", storkWarningUtil.getIsThird());
        stockWarningMapperMock.returns(goodsList).selectGoods(paramMap);
        stockWarningMapperMock.returns(1).selectcount(paramMap);
		assertEquals(1, stockWarningService.selectGoods(storkWarningUtil, pageBean).getList().size());
	}
	
	/**
	 *  查询库存预警仓库信息测试
	 */
	@Test
	public void testSelectWare()
	{
        Map<String, Object> paramMap = new HashMap<String, Object>();
        List<Object> goodsList = new ArrayList<Object>();
        goodsList.add(new Object());
        paramMap.put("startRowNum", pageBean.getStartRowNum());
        paramMap.put("endRowNum", pageBean.getEndRowNum());
        paramMap.put("id", storkWarningUtil.getId());
        paramMap.put("goodname", storkWarningUtil.getGoodname());
        paramMap.put("number", storkWarningUtil.getNumber());
        paramMap.put("storename", storkWarningUtil.getStorename());
        paramMap.put("stock", storkWarningUtil.getStock());
        paramMap.put("warename", storkWarningUtil.getWarename());
        paramMap.put("wareid", storkWarningUtil.getWareid());
        paramMap.put("thirdid", storkWarningUtil.getThirdid());
        stockWarningMapperMock.returns(goodsList).selectWare(paramMap);
        stockWarningMapperMock.returns(1).selectcounts(paramMap);
		assertEquals(1, stockWarningService.selectWare(storkWarningUtil, pageBean).getList().size());
	}
	
	/**
	 * 查询库存预警仓库信息测试
	 */
	@Test
	public void testSelectbyId()
	{
		stockWarningMapperMock.returns(storkWarningUtil).selectbyId(1L);
		assertNotNull(stockWarningService.selectbyId(1L));
	}
	
	/**
	 * 跟新库存测试
	 */
	@Test
	public void testUpdatestock()
	{
		stockWarningMapperMock.returns(1).updatestock(storkWarningUtil);
		assertEquals(1, stockWarningService.updatestock(storkWarningUtil));
	}
	
	/**
	 *  按货品名字搜索货品测试
	 */
	@Test
	public void testSelectgoodLists()
	{
		List<Object>  goodLists = new ArrayList<>();
		goodLists.add(new Object());
		Map<String, Object> paramMap = new HashMap<String, Object>();
		SelectBean selectBean = new SelectBean();
	    paramMap.put("condition", selectBean.getCondition());
        paramMap.put("searchText", selectBean.getSearchText());

        paramMap.put("goodsName", selectBean.getGoodsName());
        paramMap.put("goodsNo", selectBean.getGoodsNo());

        paramMap.put("startRowNum", pageBean.getStartRowNum());
        paramMap.put("endRowNum", pageBean.getEndRowNum());
        stockWarningMapperMock.returns(goodLists).selectGoodsbyname(paramMap);
        stockWarningMapperMock.returns(1).selectcountbyname(paramMap);
		assertEquals(1, stockWarningService.selectgoodLists(pageBean, selectBean).getList().size());
	}
}
