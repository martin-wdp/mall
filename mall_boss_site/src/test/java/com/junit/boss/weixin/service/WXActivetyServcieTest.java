package com.junit.boss.weixin.service;

import java.util.List;

import org.junit.Test;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.io.annotation.FileContent;
import org.unitils.mock.Mock;

import com.alibaba.fastjson.JSON;
import com.ningpai.weixin.bean.WXGroup;
import com.ningpai.weixin.mapper.WXGroupMapper;
import com.ningpai.weixin.service.WXActivityService;
import com.ningpai.weixin.service.impl.WXActivityServiceImpl;

/**
 * 微信单元测试
 * Created by guoguangnan on 2015/9/9.
 */
public class WXActivetyServcieTest  extends UnitilsJUnit3 {

    /**
     * 需要测试的接口类
     */
    @TestedObject
    private WXActivityService wXActivetyServcie = new WXActivityServiceImpl();

    /**
     * 加载JS的JSON数据
     */
    @FileContent("wxgroupList.js")
    private String wxgroupListJS;

    /**
     * 模拟MOCK
     */
    @InjectIntoByType
    Mock<WXGroupMapper> wxGroupMapperMock;

    /**
     * 共享数据
     */
    List<WXGroup> list;

    /**
     * 初始化数据
     */
    @Override
    public void setUp(){
        list = JSON.parseArray(wxgroupListJS, WXGroup.class);
    }

    /**
     *查询所有群发用户
     */
    @Test
    public void testSelectAllGroup(){
        wxGroupMapperMock.returns(list).selectAllGroup();
        assertEquals(1,wXActivetyServcie.selectAllGroup().size());
    }

}
