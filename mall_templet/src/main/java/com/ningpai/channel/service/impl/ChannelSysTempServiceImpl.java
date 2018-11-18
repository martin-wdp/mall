/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.channel.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.channel.service.ChannelSysTempService;
import com.ningpai.temp.bean.SysTemp;
import com.ningpai.temp.dao.SysTempDao;

/**
 * SERVICE实现类-频道用模板service
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月4日上午11:38:03
 */
@Service("ChannelSysTempService")
public class ChannelSysTempServiceImpl implements ChannelSysTempService {

    private SysTempDao systempDao;

    private static final Long STARTROWNUM = 0L;

    private static final Long ENDROWNUM = 1000L;

    /*
     * 
     * 
     * @see com.ningpai.channel.service.ChannelSysTempService#queryAllSystemp()
     */
    @Override
    public List<SysTemp> queryAllSystemp() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("startRowNum", STARTROWNUM);
        map.put("endRowNum", ENDROWNUM);
        return systempDao.querySysTempByPage(map);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.channel.service.ChannelSysTempService#getSystempById(java
     * .lang.Long)
     */
    @Override
    public SysTemp getSystempById(Long systempId) {
        return systempDao.getSysTempById(systempId.intValue());
    }

    public SysTempDao getSystempDao() {
        return systempDao;
    }

    @Resource(name = "SysTempDao")
    public void setSystempDao(SysTempDao systempDao) {
        this.systempDao = systempDao;
    }
}
