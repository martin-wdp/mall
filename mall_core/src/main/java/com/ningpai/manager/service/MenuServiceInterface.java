package com.ningpai.manager.service;

import java.util.List;

import com.ningpai.manager.bean.Manager;
import com.ningpai.manager.bean.valuebean.MenuVo;

/**
 * 菜单格式处理接口
 * 
 * @author AthrunNatu
 * @since 2013年11月21日下午3:42:28
 */
public interface MenuServiceInterface {
    /**
     * 菜单显示格式化
     * 
     * @param pageList
     * @see java.util.List {@link java.util.List} <code>Page</code>
     * @see com.ningpai.manager.bean.Page {@link com.ningpai.manager.bean.Page}
     * @return @see com.ningpai.manager.bean.valueBean.MenuVo
     *         {@link com.ningpai.manager.bean.valueBean.Menvo}
     */
  /*  MenuVo getMenuList(List<Page> pageList);*/

    /**
     * 根据管理员权限显示菜单
     * 
     * @param<code>Manager</code> {@link com.ningpai.manager.bean.Manager}
     * @return {@link com.ningpai.manager.bean.valueBean.Menvo}
     */
    List<MenuVo> getMenuLists(Manager manager);
    /**
     * 根据管理员ID 从管理员权限关系表中获取到权限ID 根据权限ID在权限页面表中查找到对应的页面集合父,递归权限菜单
     * @param attribute
     * @return List<MenuVo>
     */
    List<MenuVo> getAllMenuByLogin(Long attribute);
}
