package com.ningpai.third.analysis.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.ningpai.third.analysis.bean.OCustomerFollow;
import com.ningpai.third.analysis.bean.OOrder;
import com.ningpai.third.analysis.dao.AnalysisServiceMapper;
import com.ningpai.third.analysis.service.AnalysisService;

/**
 * <p>
 * 数据分析
 * </p>
 *
 * @author zhanghl
 * @versuin 2.0
 * @since 2015.07.30
 */
@Service("AnalysisService")
public class AnalysisServiceImpl implements AnalysisService {

    private static final String STARTTIME = "startTime";
    private static final String ENDTIME = "endTime";
    private static final String BUSINESSID = "businessId";

    // 数据分析接口
    @Resource(name = "AnalysisServiceMapper")
    private AnalysisServiceMapper analysisServiceMapper;

    /**
     * 查询所有的会员关注
     * 
     * @see com.ningpai.third.analysis.service.AnalysisService#selectThirdFollowGoods
     *      (java.lang.Long, java.lang.String, java.lang.String, java.lang.Long)
     */
    @Override
    public List<OCustomerFollow> selectThirdFollowGoods(Long thirdId, String startTime, String endTime, Long catId) {
        // 装载查询条件
        Map<String, Object> paramMap = new HashMap<String, Object>();
        // 商家ID
        paramMap.put("thirdId", thirdId);
        // 起始时间
        paramMap.put(STARTTIME, startTime);
        // 终止日期
        paramMap.put(ENDTIME, endTime + " 23:59:59");

        // 判断类型ID是否为空
        if (catId != null) {
            paramMap.put("catId", catId);
        }
        // 根据条件筛选 关注的商品集合
        List<OCustomerFollow> glist = analysisServiceMapper.selectThirdFollowGoods(paramMap);
        // 遍历商品
        if (glist != null && !glist.isEmpty()) {
            for (int i = 0; i < glist.size(); i++) {
                paramMap.put("goodsInfoId", glist.get(i).getGoodsId());
                // 根据条件筛选 关注的商品集合
                List<OCustomerFollow> ffist = analysisServiceMapper.selectThirdFollowGoodsCount(paramMap);
                // 遍历商品结婚
                if (ffist != null && !ffist.isEmpty()) {
                    Map<String, Long> pMap = new HashMap<String, Long>();
                    for (OCustomerFollow g : ffist) {
                        pMap.put(g.getGoodsDownTime(), g.getCountSum());
                    }
                    glist.get(i).setTimeMap(pMap);
                }
            }

        }
        return glist;
    }

    /**
     * 根据会员ID获取所有的订单信息
     *
     * @param businessId
     *            商家ID
     * @return
     */
    @Override
    public List<OOrder> queryCountByDay(Long businessId) {
        // 装载查询条件
        Map<String, Object> paramMap = new HashMap<String, Object>();
        // 商家ID
        paramMap.put(BUSINESSID, businessId);
        return analysisServiceMapper.queryCountByDay(paramMap);
    }

    /**
     * 根据条件筛选订单信息
     *
     * @param startTime
     *            起始时间
     * @param endTime
     *            结束日期
     * @param businessId
     *            商家Id
     * @return
     */
    @Override
    public OOrder queryNoSuccCountByDay(String startTime, String endTime, Long businessId) {
        // 用来装载查询条件
        Map<String, Object> paramMap = new HashMap<String, Object>();
        // 开始日期
        paramMap.put(STARTTIME, startTime);
        // 结束日期
        paramMap.put(ENDTIME, endTime);
        // 商家ID
        paramMap.put(BUSINESSID, businessId);
        return analysisServiceMapper.queryNoSuccCountByDay(paramMap);
    }

    /**
     * 根据条件筛选信息
     *
     * @param startTime
     *            起始日期
     * @param endTime
     *            结束日期
     * @param businessId
     *            商家ID
     * @return
     */
    @Override
    public OOrder querySuccCountByTime(String startTime, String endTime, Long businessId) {
        // 用来装载查询条件
        Map<String, Object> paramMap = new HashMap<String, Object>();
        // 起始日期
        paramMap.put(STARTTIME, startTime);
        // 结束日期
        paramMap.put(ENDTIME, endTime);
        // 商家ID
        paramMap.put(BUSINESSID, businessId);
        return analysisServiceMapper.querySuccCountByTime(paramMap);
    }
}
