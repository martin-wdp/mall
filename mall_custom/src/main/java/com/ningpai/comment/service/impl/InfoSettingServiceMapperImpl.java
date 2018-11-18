/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.comment.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.comment.bean.Infosetting;
import com.ningpai.comment.dao.InfosettingMapper;
import com.ningpai.comment.service.InfoSettingServiceMapper;

/**
 * 消息设置服务类
 * 
 * @author NINGPAI-zhangqiang
 * @since 2013年12月31日 下午6:42:03
 * @version 1.0
 */
@Service("infoSettingServiceMapper")
public class InfoSettingServiceMapperImpl implements InfoSettingServiceMapper {
    // spring注解
    private InfosettingMapper infosettingMapper;

    /*
     * 
     * 
     * @see com.ningpai.comment.service#updateInfoSetting(Infosetting infosetting)
     */
    @Override
    public int updateInfoSetting(Infosetting infosetting) {
        return infosettingMapper.updateByPrimaryKeySelective(infosetting);
    }

    /*
     * 
     * 
     * @see com.ningpai.comment.service#selectInfoSetting()
     */
    @Override
    public Infosetting selectInfoSetting() {
        return infosettingMapper.selectInfoSetting();
    }

    public InfosettingMapper getInfosettingMapper() {
        return infosettingMapper;
    }

    @Resource(name = "infosettingMapper")
    public void setInfosettingMapper(InfosettingMapper infosettingMapper) {
        this.infosettingMapper = infosettingMapper;
    }

}
