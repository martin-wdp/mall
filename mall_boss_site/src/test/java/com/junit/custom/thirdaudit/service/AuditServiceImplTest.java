package com.junit.custom.thirdaudit.service;

import com.alibaba.fastjson.JSON;
import com.ningpai.thirdaudit.bean.DeduBrokeage;
import com.ningpai.thirdaudit.bean.StoreInfo;
import com.ningpai.thirdaudit.mapper.DeduBrokeageMapper;
import com.ningpai.thirdaudit.mapper.StoreCommonMapper;
import com.ningpai.thirdaudit.mapper.StoreInfoMapper;
import com.ningpai.thirdaudit.mapper.ThirdManagerAuthorityMapper;
import com.ningpai.thirdaudit.service.AuditService;
import com.ningpai.thirdaudit.service.impl.AuditServiceImpl;
import com.ningpai.util.PageBean;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.io.annotation.FileContent;
import org.unitils.mock.Mock;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 商家审核接口测试类
 */
 public class AuditServiceImplTest extends UnitilsJUnit3 {
    //测试类
    @TestedObject
    private AuditService auditService = new AuditServiceImpl();


    //数据模拟
    @InjectIntoByType
    Mock<StoreInfoMapper> storeInfoMapperMock;
    @InjectIntoByType
    Mock<ThirdManagerAuthorityMapper> managerAuthorityMapperMock;
    @InjectIntoByType
    Mock<DeduBrokeageMapper> deduBrokeageMapperMock;
    @InjectIntoByType
    Mock<StoreCommonMapper> storeCommonMapperMock;

	
    //测试数据
    List<StoreInfo> storeInfoList;

    List<DeduBrokeage> deduBrokeages;
    @FileContent("storeInfoList.js")
    private String storeInfoListJS;
    @FileContent("deduBrokeageList.js")
    private String deduBrokeageJS;

    public void setUp() {
        storeInfoList = JSON.parseArray(storeInfoListJS, StoreInfo.class);
        deduBrokeages=JSON.parseArray(deduBrokeageJS,DeduBrokeage.class);
    }

    /**
     * 设置改店铺是都在店铺街显示以及排序
     */
    public void testSetStore() {
        storeInfoMapperMock.returns(1).setStore(1L, "1", 1L);
        assertEquals(1, auditService.setStore(1L, "1", 1L));
    }

    /**
     * 查询会员id
     */
    public void testFindcid() {
        storeInfoMapperMock.returns(1).findcid(1L);
        assertEquals(1, auditService.findcid(1L));
    }

    /**
     * 查询商家审核列表
     */
    public void testSelectAuditList() {
        storeInfoMapperMock.returns(1L).selectAuditListSize(storeInfoList.get(0));
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("storeInfo", storeInfoList.get(0));
        // 分页开始
        paramMap.put("startRowNum", 0);
        // 分页结束
        paramMap.put("endRowNum", 15);
        paramMap.put("companyName", storeInfoList.get(0).getCompanyName());
        storeInfoMapperMock.returns(storeInfoList).selectAuditList(paramMap);
        assertEquals(1, auditService.selectAuditList(storeInfoList.get(0), new PageBean()).getList().size());


    }
/**
 * 审核商家信息
 */
 public void testUpdateStore() {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("parameterValues", new String[]{"1"});
        paramMap.put("time","2015-1-1");
        paramMap.put("storeQi", "1");
       storeInfoMapperMock.returns(1).updateStore(paramMap);
        paramMap.clear();
        paramMap.put("storeId","1");
        managerAuthorityMapperMock.returns(1).addRecord(paramMap);
        storeInfoMapperMock.returns(1).auditBrand(Long.parseLong("1"));
        assertEquals(new Integer(1),auditService.updateStore(new String[]{"1"},deduBrokeages.get(0),"2015-1-1","1","1"));
    }
/**
 * 根据卖家编号查找店铺信息
 */
 public void testSelectByCustomerId(){
        storeInfoMapperMock.returns(storeInfoList.get(0)).selectByStoreId(1L);
        assertNotNull(auditService.selectByCustomerId(1L));
    }
/**
 * 删除签约品牌
 */
 public void testRefuseStore() {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("thirdId",1L);
       storeInfoMapperMock.returns(1).refuseStore(storeInfoList.get(0));
        assertEquals(new Integer(1),auditService.refuseStore(storeInfoList.get(0)));
    }
/**
 * 查询商家扣率
 */
 public void testSelectBrokeageByStoreId() {
        deduBrokeageMapperMock.returns(storeInfoList).selectBrokeageByStoreId(1L);
        assertEquals(1,auditService.selectBrokeageByStoreId(1L).size());
    }
/**
 * 修改支付支付方式扣点
 */
 public void testUpdatePayMent()  {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("storeId", 1L);
        paramMap.put("billingCycle", "qianmi");
        storeInfoMapperMock.returns(new Integer(1)).updatePayMent(paramMap);
        assertEquals(new Integer(1),auditService.updatePayMent(1L,"qianmi"));
    }
/**
 * 修改商家结算信息中的支付方式
 */
 public void testUpdatePayWay(){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("storeId", 1L);
        paramMap.put("payId", 1L);
        paramMap.put("deduction", 0.5);
        paramMap.put("brokerage",0.5);
        deduBrokeageMapperMock.returns(1).insertByStoreId(paramMap);
        assertEquals(new Integer(1),auditService.updatePayWay(1L, new Long[]{1L}, "0.5", "0.5"));
    }
/**
 * 根据商家编号 查询商家店铺名称 和是否开启商家首页
 */
 public void testSelectNameAndIsStoreBySId() {
        storeInfoMapperMock.returns(storeInfoList.get(0)).selectNameAndIsStoreBySId(1L);
        assertNotNull(auditService.selectNameAndIsStoreBySId(1L));
    }
/**
 * 修改商铺期限
 */
 public void testUpdateStoreValidTime() throws Exception {
        Map<String, Object> param = new HashMap<String, Object>();
        String expiry = null;
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        expiry = "2015-1-1" + " " + sdf.format(new Date());
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date expiryTime = sf.parse(expiry);
        param.put("expiryTime", expiryTime);
        param.put("storeId", 1L);
        storeInfoMapperMock.returns(1).updateStoreValidTime(param);
        assertEquals(1,auditService.updateStoreValidTime("2015-1-1",1L));
    }
/**
 * 查询第三方店铺关闭时间判断是否开启
 */
 public void testSelectStoreTimeByThirdId() throws Exception {
     storeInfoMapperMock.returns(1).selectStoreTimeByThirdId(1L);
     assertEquals(1,auditService.selectStoreTimeByThirdId(1L));
    }
}