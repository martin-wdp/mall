package com.ningpai.manager.service.impl;

import com.ningpai.manager.bean.Manager;
import com.ningpai.manager.bean.valuebean.MenuVo;
import com.ningpai.manager.mapper.AuthorityPageMapper;
import com.ningpai.manager.mapper.ManagerAuthorityMapper;
import com.ningpai.manager.service.ManagerServiceInterface;
import com.ningpai.manager.service.MenuServiceInterface;
import com.ningpai.manager.service.PageServiceInterface;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单格式化实现类
 * 
 * @author AthrunNatu
 * @since 2013年11月21日下午4:59:19
 * @see com.ningpai.manager.service.MenuServiceInterface
 */
@Service("menuServiceInterface")
public final class MenuService implements MenuServiceInterface {

    /**
     * ManagerAuthorityMapper ManagerAuthority的DAO接口
     */
    private ManagerAuthorityMapper managerAuthorityMapper;
    /**
     * 权限管理DAO接口
     */
    private AuthorityPageMapper authorityPageMapper;
    /**
     * 菜单管理DAO接口
     */
    private PageServiceInterface pageServiceInterface;
    /**
     * 管理员DAO接口
     */
    private ManagerServiceInterface managerServiceInterface;

    /**
     * 权限菜单
     *
     * @see com.ningpai.manager.service.MenuServiceInterface#getMenuList(java.util.List)
     *
     */

    /**
     * 权限菜单
     * 
     * @see com.ningpai.manager.service.MenuServiceInterface#getMenuLists(com.ningpai.manager.bean.Manager)
     *
     */
    @Override
    public List<MenuVo> getMenuLists(Manager manager) {
        // 获取权限编号
        Long num = managerAuthorityMapper.selectByManagerId(manager.getId()).getAuthorityId();
        // 所有页面
        List<MenuVo> menuVoList = authorityPageMapper.selectByAuthorityID(num);
        // 根据管理员ID 从管理员权限关系表中获取到权限ID 根据权限ID在权限页面表中查找到对应的页面集合 父
        List<MenuVo> parentMenuVos = authorityPageMapper.selectAllMenuVos(num);
        // 递归权限菜单
        fillMenuList(parentMenuVos, menuVoList);
        return parentMenuVos;
    }

    /**
     * 获取当前管理员的所有菜单
     * 
     * @param name
     *            管理员名称
     * @return 全部菜单
     */
    public List<MenuVo> getAllMenus(String name) {
        // 获取当前管理员的所有权限
        // 用户分配权限
        return authorityPageMapper.selectAllMenuVo(managerAuthorityMapper.selectByManagerId(managerServiceInterface.queryManagerByName(name).getId()).getAuthorityId());
    }

    /**
     * 填充权限菜单 递归
     * 
     * @param parentMenuVos
     *            父级菜单项
     * @param menuVoList
     *            全部菜单
     */
    private void fillMenuList(List<MenuVo> parentMenuVos, List<MenuVo> menuVoList) {
        // 循环获取管理员权限
        for (int i = 0; i < parentMenuVos.size(); i++) {
            MenuVo menuVo = parentMenuVos.get(i);
            for (int j = 0; j < menuVoList.size(); j++) {
                // 初始化变量申明
                MenuVo mv = menuVoList.get(j);
                // 发现子级 添加到父级
                if ((menuVo.getId() + "").equals(mv.getParentId() + "")) {
                    if (menuVo.getMenuVos() == null) {
                        menuVo.setMenuVos(new ArrayList<MenuVo>());
                    }
                    // 把MV加入菜单中
                    menuVo.getMenuVos().add(mv);
                }
            }
            // 使用已经处理过的菜单 替换之前子菜单为空菜单
            parentMenuVos.set(i, menuVo);
            if (menuVo.getMenuVos() != null) {
                fillMenuList(menuVo.getMenuVos(), menuVoList);
            }
        }
    }

    /**
     *  根据管理员ID 从管理员权限关系表中获取到权限ID 根据权限ID在权限页面表中查找到对应的页面集合父,递归权限菜单
     * @return  List<MenuVo>
     */
    public List<MenuVo> getAllMenu() {
        // 根据管理员ID 从管理员权限关系表中获取到权限ID 根据权限ID在权限页面表中查找到对应的页面集合父,递归权限菜单
        return pageServiceInterface.queryAllParentMenuVo();
    }

    /**
     * 根据管理员ID 从管理员权限关系表中获取到权限ID 根据权限ID在权限页面表中查找到对应的页面集合父,递归权限菜单
     * @param managerId
     * @return List<MenuVo>
     */
    public List<MenuVo> getAllMenuByLogin(Long managerId) {
        // 根据管理员ID 从管理员权限关系表中获取到权限ID 根据权限ID在权限页面表中查找到对应的页面集合父,递归权限菜单
        return pageServiceInterface.queryAllParentMenuVoByLogin(managerId);
    }

    /**
     * 序列化菜单
     * @param objs
     */
    public void bb(List<MenuVo> objs) {
        // 序列化菜单
        if (objs != null) {
            for (int i = 0; i < objs.size(); i++) {
                if (objs.get(i).getMenuVos() != null) {
                    bb(objs.get(i).getMenuVos());
                }
            }
        }
    }

    public ManagerAuthorityMapper getManagerAuthorityMapper() {
        return managerAuthorityMapper;
    }

    @Resource(name = "managerAuthorityMapper")
    public void setManagerAuthorityMapper(ManagerAuthorityMapper managerAuthorityMapper) {
        this.managerAuthorityMapper = managerAuthorityMapper;
    }

    public AuthorityPageMapper getAuthorityPageMapper() {
        return authorityPageMapper;
    }

    @Resource(name = "authorityPageMapper")
    public void setAuthorityPageMapper(AuthorityPageMapper authorityPageMapper) {
        this.authorityPageMapper = authorityPageMapper;
    }

    public PageServiceInterface getPageServiceInterface() {
        return pageServiceInterface;
    }

    @Resource(name = "PageService")
    public void setPageServiceInterface(PageServiceInterface pageServiceInterface) {
        this.pageServiceInterface = pageServiceInterface;
    }

    public ManagerServiceInterface getManagerServiceInterface() {
        return managerServiceInterface;
    }

    @Resource(name = "managerService")
    public void setManagerServiceInterface(ManagerServiceInterface managerServiceInterface) {
        this.managerServiceInterface = managerServiceInterface;
    }

}
