/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.junit.custom.comment.service;


import java.util.List;

import org.junit.Test;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.io.annotation.FileContent;
import org.unitils.mock.Mock;

import com.alibaba.fastjson.JSON;
import com.ningpai.comment.dao.InfosettingMapper;
import com.ningpai.comment.service.InfoSettingServiceMapper;
import com.ningpai.comment.service.impl.InfoSettingServiceMapperImpl;
import com.ningpai.comment.bean.Infosetting;

/**
 * 消息设置服务类单元测试
 * @author jiping 
 * @since 2015年9月15日 下午8:24:30 
 * @version 0.01
 */
public class InfoSettingServiceMapperTest extends UnitilsJUnit3{
    /**
     * 需要测试的接口
     */
    @TestedObject
    private InfoSettingServiceMapper infoSettingServiceMapper =new InfoSettingServiceMapperImpl();
    
    /**
     * 模拟
     */
    @InjectIntoByType
    Mock<InfosettingMapper> infosettingMapperMock;
    
    /**
     * JS数据
     */
    @FileContent("infoSettingList.js")
    private String infoSettingListJS;
    
    /**
     * 共享数据
     */
    List<Infosetting> infosettingList;
    
    /**
     * 初始化
     */
    public void setUp(){
        infosettingList = JSON.parseArray(infoSettingListJS,Infosetting.class);
    }

    /**
     * 测试修改消息设置
     */
    @Test
    public void testUpdateInfoSetting(){
        infosettingMapperMock.returns(1).updateByPrimaryKeySelective(infosettingList.get(0));
        assertEquals(1, infoSettingServiceMapper.updateInfoSetting(infosettingList.get(0)));
    }
    
    /**
     * 测试查找当前设置
     */
    @Test
    public void testSelectInfoSetting() {
        infosettingMapperMock.returns(infosettingList.get(0)).selectInfoSetting();
        assertNotNull(infoSettingServiceMapper.selectInfoSetting());
    }
}
