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

import com.ningpai.system.bean.Express;
import com.ningpai.system.dao.ExpressMapper;
import com.ningpai.system.service.ExpressService;
import com.ningpai.system.util.SelectBean;
import com.ningpai.util.PageBean;

/**
 * 配送方式服务层接口实现类
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月18日 下午4:22:52
 * @version 1.0
 */
@Service("expressService")
public class ExpressServiceImpl implements ExpressService {
    /** spring注解 */
    @Resource(name = "expressMapper")
    private ExpressMapper expressMapper;

    /**
     * 分页查询配送方式信息
     * 
     * @param pageBean
     * @return PageBean
     */
    public PageBean findByPageBean(PageBean pageBean, SelectBean selectBean) {
        // 设置总行数
        pageBean.setRows(expressMapper.findTotalCount(selectBean));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("startRowNum", pageBean.getStartRowNum());
        map.put("endRowNum", pageBean.getEndRowNum());
        map.put("condition", selectBean.getCondition());
        map.put("searchText", selectBean.getSearchText());
        // 设置列表
        pageBean.setList(expressMapper.findByPageBean(map));

        return pageBean;
    }

    /**
     * 添加配送
     * 
     * @param express
     * @return int
     */
    public int insertExpress(Express express) {
        // 添加配送信息
        express.setDelFlag("0");
        return expressMapper.insertSelective(express);
    }

    /**
     * 删除配送信息
     * 
     * @param expIds
     * @return int
     */
    public int deleteExpress(String[] expIds) {
        int count = 0;
        // 循环要删除的ID
        for (String id : expIds) {
            count += expressMapper.deleteByPrimaryKey(Long.parseLong(id));
        }
        return count;
    }

    /**
     * 按expid查询配送信息
     * 
     * @param expid
     * @return Express
     */
    public Express findByExpid(Long expid) {
        // 按照ID查找配送信息
        return expressMapper.selectByPrimaryKey(expid);
    }

    /**
     * 修改配送信息
     * 
     * @param express
     * @return int
     */
    public int updateExpress(Express express) {
        // 修改配送信息
        express.setModifyTime(new Date());
        return expressMapper.updateByPrimaryKeySelective(express);
    }
}
