package com.junit.order.report.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletResponse;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;

import com.ningpai.order.bean.Order;
import com.ningpai.order.service.OrderService;
import com.ningpai.report.bean.Report;
import com.ningpai.report.dao.ReportMapper;
import com.ningpai.report.service.ReportService;
import com.ningpai.report.service.impl.ReportServiceImpl;
import com.ningpai.thirdaudit.bean.StoreInfo;
import com.ningpai.thirdaudit.mapper.StoreInfoMapper;
import com.ningpai.util.PageBean;

public class ReportServiceTest extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口类
	 */
	@TestedObject
	private ReportService reportService = new ReportServiceImpl();
	
	/**
	 *  模拟MOCK
	 */
	@InjectIntoByType
	private Mock<OrderService> orderServiceMock;
	
	/**
	 *  模拟MOCK
	 */
	@InjectIntoByType
	private Mock<StoreInfoMapper> storeInfoMapperMock;
	
	/**
	 *  模拟MOCK
	 */
	@InjectIntoByType
	private Mock<ReportMapper> reportMapperMock;
	
	/**
	 * np_report表的实体bean
	 */
	private Report report = new Report();
	
	/**
	 * 根据主键删除 测试
	 */
	@Test
	public void testdDelete()
	{
		reportMapperMock.returns(1).deleteByPrimaryKey(1L);
		assertEquals(1, reportService.delete(1L));
	}
	
	/**
	 * 插入，空属性不会插入
	 */
	@Test
	public void testInsert()
	{
		reportMapperMock.returns(1).insertSelective(report);
		assertEquals(1, reportService.insert(report));
	}
	
	/**
	 * 根据主键查询测试
	 */
	@Test
	public void testSelect()
	{
		reportMapperMock.returns(report).selectByPrimaryKey(1L);
		assertNotNull(reportService.select(1L));
	}
	
	/**
	 * 根据主键修改，空值条件不会修改成null测试
	 */
	@Test
	public void testUpdate()
	{
		reportMapperMock.returns(1).updateByPrimaryKeySelective(report);
		assertEquals(1, reportService.update(report));
	}
	
	/**
	 * 删除多条记录测试
	 */
	@Test
	public void testDeleteMuilti()
	{
		Long [] reportIds = {1L};
		reportMapperMock.returns(1).deleteMuilti(reportIds);
		assertEquals(1, reportService.deleteMuilti(reportIds));
	}
	
	/**
	 * 分页查询列表测试
	 */
	@Test
	public void testSelectList()
	{
		PageBean pageBean = new PageBean();
		 Map<String, Object> map = new HashMap<String, Object>();
	     map.put("pageBean", pageBean);
	     reportMapperMock.returns(1).selectListCount(map);
	     List<Object> list = new ArrayList<>();
	     list.add(new Object());
	     reportMapperMock.returns(list).selectList(map);
		 assertEquals(1, reportService.selectList(report, pageBean).getList().size());
	}
	
	/**
     * 根据时间段，查询结算周期在这个时间段内的商家的订单金额、凤凰卡金额、银联付款金额测试
	 */
	@Test
	public void testGenerateReport()
	{
		List<Report> list = new ArrayList<>();
		list.add(new Report());
		StoreInfo storeInfo = new StoreInfo();
		storeInfo.setBillingCycle("20");
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("startRowNum", 0);
        paramMap.put("endRowNum", 100000);
        List<Object> stores = new ArrayList<>();
        stores.add(storeInfo);
        storeInfoMapperMock.returns(stores).selectAuditList(paramMap);
        paramMap.put("startDate", "2015-08-21");
        paramMap.put("endDate", "2015-09-21");
        paramMap.put("storeId", null);
        reportMapperMock.returns(list).queryReportData(paramMap);
        assertNull(reportService.generateReport("2015-09-20", "2015-09-24"));
	}
	
	/**
	 * 查询报表包含的订单测试
	 */
	@Test
	public void testSelectReportOrders()
	{
		PageBean pageBean = new PageBean();
		Report report = new Report();
		report.setStoreId(1L);
		report.setStartTime(new Date());
		report.setEndTime(new Date());
		reportMapperMock.returns(report).selectByPrimaryKey(1L);
		List<Object> list = new ArrayList<>();
		list.add(new Object());
		pageBean.setList(list);
		orderServiceMock.returns(pageBean).searchOrderList(new Order(), pageBean);
		assertEquals(1, reportService.selectReportOrders(1L, pageBean).getList().size());
	}
	
	/**
	 * 分页查询列表测试
	 */
	@Test
	public void testSelectReportCateList()
	{
		PageBean pageBean = new PageBean();
		Report report = new Report();
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put("pageBean", pageBean);
	    reportMapperMock.returns(1).selectReportCateListCount(map);
	    List<Object> lists = new ArrayList<>();
	    lists.add(new Object());
	    reportMapperMock.returns(lists).selectReportCateList(map);
		assertEquals(1, reportService.selectReportCateList(report, pageBean).getList().size());
	}
	
	/**
	 * 根据商家id，删除报表记录测试
	 */
	@Test
	public void testDeleteByReportByStoreId()
	{
		reportService.deleteByReportByStoreId(1L);
	}
	
	/**
	 * 根据条件导出报表测试
	 */
	@Test
	public void testExportSumReport()
	{
		Report report = new Report();
		report.setTotalOrderMoney(new BigDecimal(1));
		report.setTotalOrderPrePrice(new BigDecimal(1));
		report.setTotalGoodsPrePrice(new BigDecimal(1));
		List<Object> reports = new ArrayList<>();
		reports.add(report);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("report", report);
		HttpServletResponse response = new MockHttpServletResponse();
		reportMapperMock.returns(reports).selectSumListByParam(paramMap);
		reportService.exportSumReport(report, response);
	}
	
	/**
	 * 根据参数查询报表测试
	 */
	@Test
	public void testSelectSumListByParam()
	{
		Report record = new Report();
	    List<Object> list = new ArrayList<>();
	    list.add(new Object());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("report", record);
        reportMapperMock.returns(list).selectSumListByParam(map);
	    assertEquals(1, reportService.selectSumListByParam(record).size());
	}
	
	/**
	 * 导出类目报表测试
	 */
	@Test
	public void testExportCateReport()
	{
		Report report = new Report();
		report.setCateRate(new BigDecimal(1));
		report.setTotalOrderMoney(new BigDecimal(1));
		report.setTotalOrderPrePrice(new BigDecimal(1));
		report.setTotalGoodsPrePrice(new BigDecimal(1));
		report.setStartTime(new Date());
		report.setEndTime(new Date());
		List<Object> reports = new ArrayList<>();
		reports.add(report);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("report", report);
		HttpServletResponse response = new MockHttpServletResponse();
		reportMapperMock.returns(reports).selectStoreCateReportList(paramMap);
		reportService.exportCateReport(report, response);
	}
	
	/**
	 * 根据商店id，删除报表记录测试
	 */
	@Test
	public void testSettleReport()
	{
		reportService.settleReport(1L);
	}
	
	/**
	 * 根据分类id，删除报表记录测试
	 */
	@Test
	public void testsettleReportById()
	{
		reportService.settleReportById(1L);
	}
}
