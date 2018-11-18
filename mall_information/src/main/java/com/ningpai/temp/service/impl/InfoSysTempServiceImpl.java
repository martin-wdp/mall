/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.temp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.temp.bean.SysTemp;
import com.ningpai.temp.dao.InfoSysTempDao;
import com.ningpai.temp.service.InfoSysTempService;

/**
 * SERVICE实现类-频道用模板service
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月4日上午11:38:03
 */
@Service("InfoSysTempService")
public class InfoSysTempServiceImpl implements InfoSysTempService {
    /** 开始NUM */
    private static final Long STARTROWNUM = 0L;
    /** 结束NUM */
    private static final Long ENDROWNUM = 1000L;

    /** 模板设置数据操作接口 */
    private InfoSysTempDao systempDao;
    public InfoSysTempDao getSystempDao() {
        return systempDao;
    }
    @Resource(name = "InfoSysTempDao")
    public void setSystempDao(InfoSysTempDao systempDao) {
        this.systempDao = systempDao;
    }

    /**
     * 获取所有模板设置对象集合
     * 
     * @see com.ningpai.channel.service.ChannelSysTempService#queryAllSystemp()
     */
    @Override
    public List<SysTemp> queryAllSystemp() {
        /** 定义一个HashMap集合 */
        Map<String, Object> map = new HashMap<String, Object>();
        /** 添加key和value */
        map.put("startRowNum", STARTROWNUM);
        map.put("endRowNum", ENDROWNUM);
        /** 执行查询方法 */
        return systempDao.querySysTempByPage(map);
    }

    /**
     * 根据主键查询模板设置对象
     * 
     * @see com.ningpai.channel.service.ChannelSysTempService#getSystempById(java.lang.Long)
     */
    @Override
    public SysTemp getSystempById(Long systempId) {
        /** 根据模板设置对象的id查询模板设置对象 */
        return systempDao.getSysTempById(systempId.intValue());
    }

}
