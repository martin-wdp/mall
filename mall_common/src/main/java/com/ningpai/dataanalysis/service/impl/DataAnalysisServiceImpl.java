/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.dataanalysis.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.dataanalysis.dao.DataAnalysisMapper;
import com.ningpai.dataanalysis.service.DataAnalysisService;

import com.ningpai.util.PageBean;
import com.ningpai.util.SelectBean;

/**
 * 数据分析service层实现类
 * 
 * @author zhangsl
 * @since 2014年12月19日 下午1:47:29
 * @version
 */
@Service("DataAnalysisService")
public class DataAnalysisServiceImpl implements DataAnalysisService {

    // Spring注入
    @Resource(name = "DataAnalysisMapper")
    private DataAnalysisMapper dataAnalysisMapper;

    /*
     * 查询数据分析所需要的数据
     * @see
     * com.ningpai.dataanalysis.service.DataAnalysisService#selectAllData(com
     * .ningpai.util.PageBean, com.ningpai.util.SelectBean, java.lang.String,
     * java.lang.String)
     */
    @Override
    public PageBean selectAllData(PageBean pb, SelectBean selectBean,
            Date startTime, Date endTime) {
        // 申明查询参数
        Map<String, Object> paraMap = new HashMap<String, Object>();
        // 设置参数
        paraMap.put("condition", selectBean.getCondition());
        if (selectBean.getSearchText() != null) {
            paraMap.put("searchText", selectBean.getSearchText().trim());
        }
        // 开始时间
        paraMap.put("startTime", startTime);
        // 结束时间
        paraMap.put("endTime", endTime);
        // 查询总数
        pb.setRows(dataAnalysisMapper.selectAllSize(paraMap));
        // 分页参数
        paraMap.put("startRowNum", pb.getStartRowNum());
        paraMap.put("endRowNum", pb.getEndRowNum());
        // 查询数据信息
        pb.setList(dataAnalysisMapper.selectAllData(paraMap));

        return pb;
    }

}
