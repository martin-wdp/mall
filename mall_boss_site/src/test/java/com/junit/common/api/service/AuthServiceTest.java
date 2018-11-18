package com.junit.common.api.service;

import com.ningpai.api.bean.Auth;
import com.ningpai.api.dao.AuthMapper;
import com.ningpai.api.service.AuthService;
import com.ningpai.api.service.impl.AuthServiceImpl;
import org.junit.Test;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;

import java.util.ArrayList;
import java.util.List;

/**
 * 授权
 * Created by guoguangnan on 2015/9/9.
 */
public class AuthServiceTest extends UnitilsJUnit3 {

    /**
     * 需要测试的接口类
     */
    @TestedObject
    private AuthService authService = new AuthServiceImpl();


    @InjectIntoByType
    Mock<AuthMapper> authMapperMock;
    /**
     * 共享数据
     */
    Auth auth;

    /**
     * 初始化数据
     */
    @Override
    public void setUp(){
        auth =new Auth();
        auth.setAuthId(1L);
        auth.setAuthName("QianMi");
        auth.setAuthCreateName("QianMi");
    }

    /**
     * 根据ID获取授权信息
     */
    @Test
    public void testFindAuthById(){
         authMapperMock.returns(auth).findAuthById(1L);
        assertNotNull(authService.findAuthById(1L));
    }

    /**
     * 根据授权名称获取授权信息
     */
    @Test
    public void testFindAuthByName(){
        authMapperMock.returns(auth).findAuthByName("QianMi");
        assertNotNull(authService.findAuthByName("QianMi"));
    }

    /**
     *  查询全部未删除的授权信息
     */
    @Test
    public void testFindAuths(){
        List<Auth> ls = new ArrayList<Auth>();
        ls.add(auth);
        authMapperMock.returns(ls).findAuths();
        assertEquals(1, authService.findAuths().size());
    }

}
