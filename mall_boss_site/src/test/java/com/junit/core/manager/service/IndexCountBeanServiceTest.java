package com.junit.core.manager.service;

import com.alibaba.fastjson.JSON;
import com.ningpai.manager.bean.EchartsTimeCount;
import com.ningpai.manager.bean.IndexCountBean;
import com.ningpai.manager.mapper.IndexCountBeanMapper;
import com.ningpai.manager.service.IndexCountBeanService;
import com.ningpai.manager.service.impl.IndexCountBeanServiceImpl;
import com.ningpai.util.UtilDate;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.io.annotation.FileContent;
import org.unitils.mock.Mock;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 首页待办Service
 * 
 * @author ggn
 *
 */
public class IndexCountBeanServiceTest  extends UnitilsJUnit3 {



    /**
     * 需要测试的Service
     */
    @TestedObject
    private IndexCountBeanService indexCountBeanService  = new IndexCountBeanServiceImpl();

    /**
     * 模拟
     */
    @InjectIntoByType
    Mock<IndexCountBeanMapper> indexCountBeanMapperMock;
    /**
     * JS数据
     */
    @FileContent("indexCountBeanList.js")
    private String indexCountBeanListJs;
    @FileContent("echartsTimeCountList.js")
    private String echartsTimeCountListJS;
    /**
     * 共享数据
     */
    List<IndexCountBean> indexCountBeanList;
    List<EchartsTimeCount> echartsTimeCountList;
    /**
     * 初始化
     */
    @Override
    public void setUp(){
        indexCountBeanList= JSON.parseArray(indexCountBeanListJs,IndexCountBean.class);
        echartsTimeCountList= JSON.parseArray(echartsTimeCountListJS,EchartsTimeCount.class);
    }




    /**
     * 查询首页待办信息
     */
    public void testSelectIndexCount(){
        // 封装查询参数
        Map<String, Object> paramMap = new HashMap<String, Object>();
        // 获取昨天日期
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        String yesterday = new SimpleDateFormat("yyyy-MM-dd ").format(cal
                .getTime());
        paramMap.put("startTime", yesterday + " 00:00:00");
        paramMap.put("endTime", yesterday + " 23:59:59");
        indexCountBeanMapperMock.returns(indexCountBeanList.get(0)).selectIndexCount(paramMap);
        assertNotNull(indexCountBeanService.selectIndexCount());
    }

    /**
     * 查询首页Echarts数据
     */
    public void testSelectIndexEchartsData(){
        // 定义返回Map
        Map<String, Object> map = new HashMap<String, Object>();
        // 获取结束日期
        String endTime = UtilDate.todayFormat(new Date());
        // 获取开始日期
        String startTime = UtilDate.addDay(endTime, -7);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
       indexCountBeanMapperMock.returns(echartsTimeCountList).selectSubOrderList(map);
        // 获取付款订单笔数
        indexCountBeanMapperMock.returns(echartsTimeCountList).payorderlist(map);
        // 获取发货订单笔数
        indexCountBeanMapperMock.returns(echartsTimeCountList).sendorderlist(map);
        indexCountBeanService.selectIndexEchartsData();
    }
}
