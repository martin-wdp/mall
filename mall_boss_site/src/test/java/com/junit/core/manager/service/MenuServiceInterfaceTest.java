package com.junit.core.manager.service;

import com.alibaba.fastjson.JSON;
import com.ningpai.manager.bean.Authority;
import com.ningpai.manager.bean.Manager;
import com.ningpai.manager.bean.ManagerAuthority;
import com.ningpai.manager.bean.valuebean.MenuVo;
import com.ningpai.manager.mapper.AuthorityPageMapper;
import com.ningpai.manager.mapper.ManagerAuthorityMapper;
import com.ningpai.manager.service.MenuServiceInterface;
import com.ningpai.manager.service.PageServiceInterface;
import com.ningpai.manager.service.impl.MenuService;
import com.ningpai.smscommon.bean.Sms;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.io.annotation.FileContent;
import org.unitils.mock.Mock;

import java.util.List;

/**
 * 菜单格式处理接口
 * 
 * @author AthrunNatu
 * @since 2013年11月21日下午3:42:28
 */
public class MenuServiceInterfaceTest  extends UnitilsJUnit3 {
    /**
     * 需要测试的Service
     */
    @TestedObject
    private MenuServiceInterface menuServiceInterface  = new MenuService();


    @InjectIntoByType
    Mock<ManagerAuthorityMapper> managerAuthorityMapperMock;
    @InjectIntoByType
    Mock<AuthorityPageMapper> authorityPageMapperMock;
    @InjectIntoByType
    Mock<PageServiceInterface> pageServiceInterfaceMock;

    /**
     * JS数据
     */
    @FileContent("smsList.js")
    private String smsListJs;
    @FileContent("managerList.js")
    private String managerListJs;
    @FileContent("authorityList.js")
    private String authorityListJs;
    @FileContent("managerAuthorityList.js")
    private String managerAuthorityListJs;
    @FileContent("menuVos.js")
    private String menuVosJs;
    @FileContent("menuParentVos.js")
    private String menuParentVosJs;

    /**
     * 共享数据
     */
    List<Sms> smsList;
    List<Manager> managerList;
    List<Authority> authorityList;
    List<ManagerAuthority> managerAuthorityList;
    List<MenuVo> menuVoList;
    List<MenuVo> menuParentVoList;
    /**
     * 初始化
     */
    @Override
    public void setUp(){
        managerList = JSON.parseArray(managerListJs, Manager.class);
        smsList = JSON.parseArray(smsListJs,Sms.class);
        authorityList = JSON.parseArray(authorityListJs,Authority.class);
        managerAuthorityList = JSON.parseArray(managerAuthorityListJs,ManagerAuthority.class);
        menuVoList =  JSON.parseArray(menuVosJs,MenuVo.class);
        menuParentVoList = JSON.parseArray(menuParentVosJs,MenuVo.class);

    }


    /**
     * 根据管理员权限显示菜单
     */
    public void testGetMenuLists(){
        managerAuthorityMapperMock.returns(managerAuthorityList.get(0)).selectByManagerId(1L);
        authorityPageMapperMock.returns(menuVoList).selectByAuthorityID(managerAuthorityList.get(0).getAuthorityId());
        authorityPageMapperMock.returns(menuParentVoList).selectAllMenuVos(managerAuthorityList.get(0).getAuthorityId());
        assertEquals(1, menuServiceInterface.getMenuLists(managerList.get(0)).size());
    }

    /**
     *根据管理员ID 从管理员权限关系表中获取到权限ID 根据权限ID在权限页面表中查找到对应的页面集合父,递归权限菜单
     */
    public void testGetAllMenuByLogin(){
       pageServiceInterfaceMock.returns(menuVoList).queryAllParentMenuVoByLogin(1L);
        assertEquals(1, menuServiceInterface.getAllMenuByLogin(1L).size());
    }
}
