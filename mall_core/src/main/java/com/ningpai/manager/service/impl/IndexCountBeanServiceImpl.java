package com.ningpai.manager.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.manager.bean.EchartsBean;
import com.ningpai.manager.bean.EchartsTimeCount;
import com.ningpai.manager.bean.IndexCountBean;
import com.ningpai.manager.mapper.IndexCountBeanMapper;
import com.ningpai.manager.service.IndexCountBeanService;
import com.ningpai.util.UtilDate;

/**
 * 首页待办
 * 
 * @author ggn
 *
 */
@Service("IndexCountBeanService")
public class IndexCountBeanServiceImpl implements IndexCountBeanService {

    @Resource(name = "IndexCountBeanMapper")
    private IndexCountBeanMapper indexCountBeanMapper;

    /*
     * 
     * @see com.ningpai.manager.service.IndexCountBeanService#selectIndexCount()
     */
    @Override
    public IndexCountBean selectIndexCount() {
        // 封装查询参数
        Map<String, Object> paramMap = new HashMap<String, Object>();
        // 获取昨天日期
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        String yesterday = new SimpleDateFormat("yyyy-MM-dd ").format(cal
                .getTime());
        paramMap.put("startTime", yesterday + " 00:00:00");
        paramMap.put("endTime", yesterday + " 23:59:59");
        // 查询首页待办
        return indexCountBeanMapper.selectIndexCount(paramMap);
    }

    /*
     * 
     * @see
     * com.ningpai.manager.service.IndexCountBeanService#selectIndexEchartsData
     * ()
     */
    @Override
    public Map<String, Object> selectIndexEchartsData() {
        // 定义返回Map
        Map<String, Object> map = new HashMap<String, Object>();
        // 获取结束日期
        String endTime = UtilDate.todayFormat(new Date());
        // 获取开始日期
        String startTime = UtilDate.addDay(endTime, -7);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        // 获取下单笔数
        List<EchartsTimeCount> suborderlist = indexCountBeanMapper
                .selectSubOrderList(map);
        // 获取付款订单笔数
        List<EchartsTimeCount> payorderlist = indexCountBeanMapper
                .payorderlist(map);
        // 获取发货订单笔数
        List<EchartsTimeCount> sendorderlist = indexCountBeanMapper
                .sendorderlist(map);
        // 定义时间区间List
        List<String> ctime = new ArrayList<String>();

        // 格式化时间区间
        while (UtilDate.compare_date(startTime, endTime)) {
            ctime.add(startTime);
            startTime = UtilDate.addDay(startTime, 1);
        }
        // echarts x轴的时间间隔
        map.put("xtime", ctime);

        // 封装echarts的legend
        List<String> legends = new ArrayList<String>();
        // 封装ecahrts data数据
        List<EchartsBean> eblist = new ArrayList<EchartsBean>();

        List<Long> data = new ArrayList<Long>();
        // 循环 时间，取值。（下单笔数）
        for (int i = 0; i < ctime.size(); i++) {
            boolean flg = true;
            // 定义存放 返回的count数组
            for (int j = 0; j < suborderlist.size(); j++) {
                EchartsTimeCount et = suborderlist.get(j);
                if (ctime.get(i).equals(et.getEtime())) {
                    data.add(et.getEcount());
                    flg = false;
                    break;
                }
            }
            if (flg) {
                data.add(0L);
            }

        }
        EchartsBean eb1 = new EchartsBean();
        eb1.setData(data);
        eb1.setName("下单笔数");
        eb1.setStock("数量");
        eb1.setType("line");
        // 加入list
        eblist.add(eb1);
        legends.add(eb1.getName());

        List<Long> data1 = new ArrayList<Long>();
        // 循环 时间，取值。（付款笔数）
        for (int i = 0; i < ctime.size(); i++) {
            boolean flg = true;
            // 定义存放 返回的count数组
            for (int j = 0; j < payorderlist.size(); j++) {
                EchartsTimeCount et = payorderlist.get(j);
                if (ctime.get(i).equals(et.getEtime())) {
                    data1.add(et.getEcount());
                    flg = false;
                    break;
                }
            }
            if (flg) {
                data1.add(0L);
            }

        }
        EchartsBean eb = new EchartsBean();
        eb.setData(data1);
        eb.setName("付款笔数");
        eb.setStock("数量");
        eb.setType("line");
        // 加入list
        eblist.add(eb);
        legends.add(eb.getName());

        List<Long> data2 = new ArrayList<Long>();
        // 循环 时间，取值。（发货单笔数）
        for (int i = 0; i < ctime.size(); i++) {
            boolean flg = true;
            // 定义存放 返回的count数组
            for (int j = 0; j < sendorderlist.size(); j++) {
                EchartsTimeCount et = sendorderlist.get(j);
                if (ctime.get(i).equals(et.getEtime())) {
                    data2.add(et.getEcount());
                    flg = false;
                    break;
                }
            }
            if (flg) {
                data2.add(0L);
            }

        }
        EchartsBean eb2 = new EchartsBean();
        eb2.setData(data2);
        eb2.setName("发货笔数");
        eb2.setStock("数量");
        eb2.setType("line");
        // 加入list
        eblist.add(eb2);
        legends.add(eb2.getName());

        map.put("series", eblist);
        map.put("legend", legends);

        return map;
    }

}
