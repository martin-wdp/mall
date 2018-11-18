/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.system.bean.SysBasic;
import com.ningpai.system.dao.SysBasicMapper;
import com.ningpai.system.service.SysBasicService;

/**
 * SERVICE实现类-后台Logo设置
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年9月24日下午8:43:39
 */
@Service("SysBasicService")
public class SysBasicServiceImpl implements SysBasicService {

    @Resource(name = "SysBasicMapper")
    private SysBasicMapper sysBasicMapper;

    /*
     * 
     * 
     * @see com.ningpai.system.service.SysBasicService#getSysBasic()
     */
    @Override
    public SysBasic getSysBasic() {
        SysBasic basic = sysBasicMapper.selectCurr();
        if (null == basic) {
            basic = new SysBasic();
            basic.setBasicId(1L);
            sysBasicMapper.insertSelective(basic);
        }
        return basic;
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.system.service.SysBasicService#updateSysBasic(com.ningpai
     * .system.bean.SysBasic)
     */
    @Override
    public int updateSysBasic(SysBasic basic) {

        return sysBasicMapper.updateByPrimaryKeySelective(basic);
    }


}
