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
import com.ningpai.temp.dao.ThirdTempDao;
import com.ningpai.temp.service.ThirdTempService;

/**
 * SERVICE实现类-频道用模板
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年5月4日下午4:13:32
 */
@Service("ThirdTempService")
public class ThirdTempServiceImpl implements ThirdTempService {

    @Resource(name = "ThirdTempDao")
    private ThirdTempDao systempDao;

    private static final Long TEMPTYPE = 137L;

    /*
     * 
     * 
     * @see
     * com.ningpai.temp.service.ThirdTempService#getSystempById(java.lang.Long)
     */
    @Override
    public SysTemp getSystempById(Long systempId) {
        return systempDao.getSysTempById(systempId.intValue());
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.temp.service.ThirdTempService#querySystempByType(java.lang
     * .Long)
     */
    @Override
    public List<SysTemp> querySystempByType(Long typeId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("tempType", typeId);
        map.put("standby2", "1");
        return systempDao.getSysTempByField(map);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.temp.service.ThirdTempService#updateSystemp(com.ningpai.temp
     * .bean.SysTemp)
     */
    @Override
    public int updateSystemp(SysTemp temp) {
        return systempDao.updateSysTemp(temp);
    }

    /*
     * 
     * 
     * @see com.ningpai.temp.service.ThirdTempService#getCurrTemp()
     */
    @Override
    public SysTemp getCurrTemp() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("tempType", TEMPTYPE);
        map.put("usedStatus", "1");
        map.put("deleteStatus", "0");
        map.put("standby2", "1");
        List<SysTemp> list = systempDao.getSysTempByField(map);
        return list.get(0);
    }
}
