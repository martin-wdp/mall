package com.ningpai.manager.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ningpai.manager.bean.Manager;
import com.ningpai.manager.bean.ManagerAuthority;
import com.ningpai.manager.bean.valuebean.MenuVo;
import com.ningpai.util.PageBean;

/**
 * 管理员服务处理接口
 * 
 * @author AthrunNatu
 * @since 2013年12月17日上午11:23:32
 * @version 0.01
 */

public interface ManagerServiceInterface {
    /**
     * 根据用户名判断管理员状态
     * 
     * @param name
     *            用户名
     * @return int 3:表示账户被冻结 2:密码错误 1:表示正常 0:用户名不存在
     */
    int ifManager(HttpServletRequest request, String name, String password);

    /**
     * 通过用户名称选取菜单
     * 
     * @param name
     *            用户名称
     * @return java.util.List <code>MenuVo</code>对象
     *         {@link com.ningpai.manager.bean.valuebean.MenuVo}菜单信息
     */
    List<MenuVo> queryMenuByMangerName(String name);

    /**
     * 获取管理员列表
     * 
     * @return java.util.List <code>Manager</code>对象
     *         {@link com.ningpai.manager.bean.Manager}管理员信息
     */
    PageBean queryManagerList(Manager manager, PageBean pageBean, Long createId);

    /**
     * 添加管理员
     * 
     * @param manager
     *            管理员信息
     * @param authorityId
     *            权限编号
     * @return
     */
    int addManager(Manager manager, String authorityId);

    /**
     * 根据编号查找管理员
     * 
     * @param id
     *            管理员编号
     * @return
     */
    Manager queryManagerById(Long id);

    /**
     * 根据管理员编号 删除管理员
     * 
     * @param parameterIds
     * @return
     */
    int deleteManager(String[] parameterIds);

    /**
     * 根据管理员编号 启用管理员
     *
     * @param parameterIds
     * @return
     */
    int enabledManager(String[] parameterIds);

    /**
     * 修改管理员信息
     * 
     * @param manager
     * @return
     */
    int updateManager(Manager manager, ManagerAuthority authority);

    /**
     * 通过用户名查询管理员
     * 
     * @param name
     *            管理员名
     * @return manager
     */
    Manager queryManagerByName(String name);

    /**
     * 通过用户名查询已冻结管理员
     *
     * @param name
     *            管理员名
     * @return manager
     */
    Manager queryDelManagerByName(String name);

    /**
     * 验证密码
     * 
     * @param request
     * @param userKey
     *            密码
     * @return 0 密码错误 1成功
     */
    int checkUserKey(HttpServletRequest request, String userKey);

    /**
     * 修改密码
     * 
     * @param request
     * @param userKey
     *            旧密码
     * @param newuserkey
     *            新密码
     * @return 0 失败 1 成功
     */
    int modifiedUserKey(HttpServletRequest request, String userKey,
            String newuserkey);

    /**
     * 验证管理员是否存在
     * 
     * @param username
     *            管理员名
     * @return 0不存在 1存在
     */
    Long checkManagerExist(String username);

    /**
     * 修改管理员信息 --仅仅修改管理员普通信息
     * 
     * @param manager
     * @return
     */
    int updateManagerOnly(Manager manager);

    /**
     * 定时器，定时冻结账号
     */
    void stopCloudAccountByTime();
}
