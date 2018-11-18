package com.junit.core.manager.service;

import com.alibaba.fastjson.JSON;
import com.ningpai.manager.bean.Authority;
import com.ningpai.manager.bean.Manager;
import com.ningpai.manager.bean.ManagerAuthority;
import com.ningpai.manager.bean.valuebean.MenuVo;
import com.ningpai.manager.mapper.ManagerAuthorityMapper;
import com.ningpai.manager.mapper.ManagerMapper;
import com.ningpai.manager.service.AuthorityServiceInterface;
import com.ningpai.manager.service.ManagerServiceInterface;
import com.ningpai.manager.service.MenuServiceInterface;
import com.ningpai.manager.service.impl.ManagerService;
import com.ningpai.util.PageBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.io.annotation.FileContent;
import org.unitils.mock.Mock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 管理员服务处理接口
 * 
 * @author AthrunNatu
 * @since 2013年12月17日上午11:23:32
 * @version 0.01
 */

public class ManagerServiceInterfaceTest extends UnitilsJUnit3 {


    /**
     * 需要测试的Service
     */
    @TestedObject
    private ManagerServiceInterface managerService  = new ManagerService();

    /**
     * 模拟
     */
    @InjectIntoByType
    Mock<ManagerAuthorityMapper> managerAuthorityMapperMock;
    @InjectIntoByType
    Mock<AuthorityServiceInterface> authorityServiceInterfaceMock;
    @InjectIntoByType
    Mock<ManagerMapper> managerMapperMock;
    @InjectIntoByType
    Mock<Manager> managerMock;
    @InjectIntoByType
    Mock<MenuServiceInterface> menuServiceInterfaceMock;


    /**
     * JS数据
     */
    @FileContent("managerList.js")
    private String managerListJs;
    @FileContent("authorityList.js")
    private String authorityListJs;
    @FileContent("managerAuthorityList.js")
    private String managerAuthorityListJs;
    @FileContent("menuVos.js")
    private String menuVosJs;
    /**
     * 共享数据
     */
    List<Manager> managerList;
    List<Authority> authorityList;
    List<ManagerAuthority> managerAuthorityList;
    List<MenuVo> menuVoList;
    /**
     * 初始化
     */
    @Override
    public void setUp(){
        managerList = JSON.parseArray(managerListJs,Manager.class);
        authorityList = JSON.parseArray(authorityListJs,Authority.class);
        managerAuthorityList = JSON.parseArray(managerAuthorityListJs,ManagerAuthority.class);
        menuVoList =  JSON.parseArray(menuVosJs,MenuVo.class);
    }



    /**
     * 根据用户名判断管理员状态
     */
    public void testIfManager(){
        MockHttpServletRequest request = new MockHttpServletRequest("POST","");
        managerMapperMock.returns(1L).checkexistsByCustName("qianmi");
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("username", "qianmi");
        paramMap.put("password", "qianmi520");
        managerMapperMock.returns(managerList.get(0)).selectCustomerByNamePwd(paramMap);
        authorityServiceInterfaceMock.returns(authorityList.get(0)).querySupperAuthor();
        authorityServiceInterfaceMock.returns(managerAuthorityList.get(0)).queryAuthorByManagerId(1L);
        managerMapperMock.returns(1).updateByPrimaryKey(managerList.get(0));
        assertEquals(10001, managerService.ifManager(request, "qianmi", "qianmi520"));
    }

    /**
     * 通过用户名称选取菜单
     */
    public void testQueryMenuByMangerName(){
        managerMapperMock.returns(managerList.get(0)).selectByName("qianmi");
        menuServiceInterfaceMock.returns(menuVoList).getMenuLists(managerList.get(0));
        assertEquals(1, managerService.queryMenuByMangerName("qianmi").size());
    }

    /**
     * 获取管理员列表
     */
    public void testQueryManagerList(){
        managerMapperMock.returns(1).queryManagerCount(managerList.get(0));
        managerMapperMock.returns(managerList).selectManagerByManager(managerList.get(0));
        assertEquals(1, managerService.queryManagerList(managerList.get(0), new PageBean(), 1L).getList().size());
    }
    /**
     * 添加管理员
     */
    public void testAddManager(){
        managerMapperMock.returns(1).addManager(managerList.get(0), "1");
        assertEquals(1, managerService.addManager(managerList.get(0), "1"));
    }
    /**
     * 根据编号查找管理员
     */
    public void testQueryManagerById(){
        managerMapperMock.returns(managerList.get(0)).selectById(1L);
        assertNotNull(managerService.queryManagerById(1L));
    }

    /**
     * 根据管理员编号 删除管理员
     */
    public void testDeleteManager(){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("parameterIds", new String[]{"1"});
        managerMapperMock.returns(1).delectMangerByIds(paramMap);
        managerAuthorityMapperMock.returns(1).deleteByManagerIds(paramMap);
        assertEquals(1, managerService.deleteManager(new String[]{"1"}));
    }

    /**
     * 根据管理员编号 启用管理员
     */
    public void testEnabledManager(){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("parameterIds", new String[]{"1"});
        managerMapperMock.returns(1).enabledMangerByIds(paramMap);
        // 启用权限管理员关系
        managerAuthorityMapperMock.returns(1).enabledByManagerIds(paramMap);
        assertEquals(1, managerService.enabledManager(new String[]{"1"}));
    }

    /**
     * 修改管理员信息
     */
    public void testUpdateManager(){
        managerMapperMock.returns(1).updateByPrimaryKeySelective(managerList.get(0));
        managerAuthorityMapperMock.returns(1).updateByPrimaryKeySelective(managerAuthorityList.get(0));
        assertEquals(2, managerService.updateManager(managerList.get(0), managerAuthorityList.get(0)));
    }

    /**
     * 通过用户名查询管理员
     */
    public void testQueryManagerByName(){
        managerMapperMock.returns(managerList.get(0)).selectByName("qianmi");
        assertNotNull(managerService.queryManagerByName("qianmi"));
    }

    /**
     * 通过用户名查询已冻结管理员
     */
    public void testQueryDelManagerByName(){
         managerMapperMock.returns(managerList.get(0)).selectDelManagerByName("qianmi");
        assertNotNull(managerService.queryDelManagerByName("qianmi"));
    }

    /**
     * 验证密码
     */
    public void testCheckUserKey(){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("username", "qianmi");
        paramMap.put("password", "qianmi520");
        // 通过用户名和密码查询用户
        managerMapperMock.returns(managerList.get(0)).selectCustomerByNamePwd(paramMap);
        MockHttpServletRequest request = new MockHttpServletRequest("POST","");
        request.getSession().setAttribute("name", "qianmi");
        assertEquals(1,managerService.checkUserKey(request,"qianmi520"));
    }

    /**
     * 修改密码
     *
     */
    public void testModifiedUserKey(){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("username", "qianmi");
        paramMap.put("password", "qianmi520");
            // 通过用户名和密码查询用户
         managerMapperMock.returns(managerList.get(0)).selectCustomerByNamePwd(paramMap);
        managerMapperMock.returns(1).updateByPrimaryKeySelective(managerList.get(0));
        MockHttpServletRequest request = new MockHttpServletRequest("POST","");
        request.getSession().setAttribute("name", "qianmi");
        assertEquals(1, managerService.modifiedUserKey(request, "qianmi520","qianmi520"));
    }

    /**
     * 验证管理员是否存在
     */
    public void testCheckManagerExist(){
        managerMapperMock.returns(1L).checkexistsByCustName("qianmi");
        assertEquals(Long.valueOf(1),Long.valueOf(managerService.checkManagerExist("qianmi")));

    }

    /**
     * 修改管理员信息 --仅仅修改管理员普通信息
     */
    public void testUpdateManagerOnly(){
       managerMapperMock.returns(1).updateByPrimaryKeySelective(managerList.get(0));
        assertEquals(1, managerService.updateManagerOnly(managerList.get(0)));
    }

    /**
     * 定时器，定时冻结账号
     */
    public void testStopCloudAccountByTime(){
        managerMapperMock.returns(managerList).queryCloudManagerList();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", 1L);
        managerMapperMock.returns(1).delectMangerByIds(paramMap);
        managerAuthorityMapperMock.returns(1).deleteByManagerIds(paramMap);
        managerService.stopCloudAccountByTime();
    }
}
