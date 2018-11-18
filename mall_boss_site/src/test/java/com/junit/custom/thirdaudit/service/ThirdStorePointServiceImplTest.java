package com.junit.custom.thirdaudit.service;

import com.alibaba.fastjson.JSON;
import com.ningpai.customer.bean.PunishRecord;
import com.ningpai.customer.service.PunishRecordService;
import com.ningpai.thirdaudit.bean.ThirdStorePoint;
import com.ningpai.thirdaudit.mapper.ThirdStorePointMapper;
import com.ningpai.thirdaudit.service.StoreInfoService;
import com.ningpai.thirdaudit.service.ThirdStorePointService;
import com.ningpai.thirdaudit.service.impl.ThirdStorePointServiceImpl;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.io.annotation.FileContent;
import org.unitils.mock.Mock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 *商家信息定位服务层接口实现类测试类
 *
 * */
public class ThirdStorePointServiceImplTest extends UnitilsJUnit3 {

    //测试类
    @TestedObject
    private ThirdStorePointService thirdStorePointService=new ThirdStorePointServiceImpl();
    //数据模拟
    @InjectIntoByType
    Mock<ThirdStorePointMapper> thirdStorePointMapperMock;
    @InjectIntoByType
    Mock<PunishRecordService> punishRecordServiceMock;
    @InjectIntoByType
    Mock<StoreInfoService> storeInfoServiceMock;


    //测试数据
    List<PunishRecord> punishRecordList;
    @FileContent("punishRecordList.js")
    private String  punishRecordListJS;

    public void setUp() {
        punishRecordList= JSON.parseArray(punishRecordListJS, PunishRecord.class);

    }

    /**
     * 添加扣积分记录
     */
    public void testAddpunishPoint() {
        ThirdStorePoint thirdStorePoint = new ThirdStorePoint();
        thirdStorePoint.setThirdId(1L);
        thirdStorePoint.setPointDetail("qianmi");
        thirdStorePoint.setPoint(1);
        thirdStorePoint.setPointType("1");
        thirdStorePointMapperMock.returns(1).insertSelective(thirdStorePoint);
        assertEquals(1,thirdStorePointService.addpunishPoint(1L,"qianmi",1));
    }
/**
 * 扣积分的方法（分三步）
 */
    public void testReduceStorePoint() {
        ThirdStorePoint thirdStorePoint = new ThirdStorePoint();
        thirdStorePoint.setThirdId(1L);
        thirdStorePoint.setPointDetail("qianmi");
        thirdStorePoint.setPoint(1);
        thirdStorePoint.setPointType("1");
        thirdStorePointMapperMock.returns(1).insertSelective(thirdStorePoint);
        punishRecordServiceMock.returns(1).addPunishRecord(punishRecordList.get(0));
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("reducePoint",1);
        paraMap.put("storeId",1L);
        storeInfoServiceMock.returns(1).upStorePointByThirdId(paraMap);
        assertEquals(1,thirdStorePointService.reduceStorePoint(punishRecordList.get(0),1L,"qianmi",1));
    }
/**
 * 扣违约金
 */
    public void testReduceStoreMoney() {
        punishRecordServiceMock.returns(1).addPunishRecord(punishRecordList.get(0));
        // 扣违约金动作
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("reduceMoney", punishRecordList.get(0).getReduceMoney());
        paramMap.put("storeId", punishRecordList.get(0).getThirdId());
        storeInfoServiceMock.returns(1).upStoreBalanceByThirdId(paramMap);
        assertEquals(1,thirdStorePointService.reduceStoreMoney(punishRecordList.get(0)));
    }
}