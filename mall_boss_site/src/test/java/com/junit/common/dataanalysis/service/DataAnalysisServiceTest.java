package com.junit.common.dataanalysis.service;

import com.ningpai.dataanalysis.dao.DataAnalysisMapper;
import com.ningpai.dataanalysis.service.DataAnalysisService;
import com.ningpai.dataanalysis.service.impl.DataAnalysisServiceImpl;
import com.ningpai.util.PageBean;
import com.ningpai.util.SelectBean;
import org.junit.Test;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;

import java.util.*;

/**
 * 数据分析
 * Created by guoguangnan on 2015/9/9.
 */
public class DataAnalysisServiceTest extends UnitilsJUnit3 {

    /**
     * 需要测试的接口
     */
    @TestedObject
    private DataAnalysisService dataAnalysisService = new DataAnalysisServiceImpl();

    /**
     * 初始化
     */
    public void setUp(){

    }
    /**
     * 模拟MOCK
     */
    @InjectIntoByType
    Mock<DataAnalysisMapper> dataAnalysisMapperMock;

    /**
     * 查询数据分析所需要的数据
     */
    @Test
    public void testSelectAllData(){
        List<Object> list = new ArrayList<Object>();
        list.add(1);

        SelectBean selectBean = new SelectBean();
        selectBean.setCondition("1");
        selectBean.setSearchText("千米");
        PageBean pb = new PageBean();
        pb.setStartRowNum(0);
        pb.setEndRowNum(15);
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("startRowNum", pb.getStartRowNum());
        paraMap.put("endRowNum", pb.getEndRowNum());
        Date startTime = new Date();
        Date endTime= new Date();
        // 开始时间
        paraMap.put("startTime", startTime);
        // 结束时间
        paraMap.put("endTime", endTime);
        paraMap.put("searchText", selectBean.getSearchText().trim());
        paraMap.put("condition", selectBean.getCondition());
        // 设置参数
        paraMap.put("condition", selectBean.getCondition());
        dataAnalysisMapperMock.returns(1).selectAllSize(paraMap);
        dataAnalysisMapperMock.returns(list).selectAllData(paraMap);
        assertEquals(1, dataAnalysisService.selectAllData(pb, selectBean, startTime, endTime).getList().size());
    }
}
