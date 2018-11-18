/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.system.bean.Bar;
import com.ningpai.system.dao.BarMapper;
import com.ningpai.system.service.BarService;
import com.ningpai.system.util.SelectBean;
import com.ningpai.util.PageBean;

/**
 * 前台导航服务层实现类
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月19日 下午4:06:43
 * @version 1.0
 */
@Service("barService")
public class BarServiceImpl implements BarService {
    /** spring注解 */
    @Resource(name = "barMapper")
    private BarMapper barMapper;

    /**
     * 导航信息分页查询
     * 
     * @param pageBean
     * @return PageBean
     */
    public PageBean findByPageBean(PageBean pageBean, SelectBean selectBean) {
        // 设置总行数
        pageBean.setRows(barMapper.findTotalCount(selectBean));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("startRowNum", pageBean.getStartRowNum());
        map.put("endRowNum", pageBean.getEndRowNum());
        map.put("condition", selectBean.getCondition());
        map.put("searchText", selectBean.getSearchText());
        // 设置显示列表
        pageBean.setList(barMapper.findByPageBean(map));

        return pageBean;
    }

    /**
     * 添加导航信息
     * 
     * @param bar
     * @return int
     */
    public int insertBar(Bar bar) {
        bar.setDelFlag("0");
        return barMapper.insertSelective(bar);
    }

    /**
     * 删除导航信息
     * 
     * @param parameterValues
     * @return int
     */
    public int deleteBar(String[] barId) {
        int count = 0;
        // 删除导航信息
        if (barId != null) {
            // 循环数组
            for (String id : barId) {
                count += barMapper.deleteByPrimaryKey(Long.parseLong(id));
            }
        }
        return count;
    }

    /**
     * 查找单条导航信息
     * 
     * @param barId
     * @return Bar
     */
    public Bar findByBarId(Long barId) {
        return barMapper.selectByPrimaryKey(barId);
    }

    /**
     * 修改导航信息
     * 
     * @param bar
     * @return int
     */
    public int updateBar(Bar bar) {
        // 设置修改时间
        bar.setModifyTime(new Date());
        return barMapper.updateByPrimaryKeySelective(bar);
    }

}
