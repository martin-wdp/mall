package com.ningpai.manager.service.impl;

import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.ningpai.logcore.util.IPAddress;
import com.ningpai.manager.bean.ManagerLoginHistory;
import com.ningpai.manager.mapper.ManagerLoginHistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ningpai.manager.bean.Manager;
import com.ningpai.manager.bean.ManagerAuthority;
import com.ningpai.manager.bean.valuebean.MenuVo;
import com.ningpai.manager.mapper.ManagerAuthorityMapper;
import com.ningpai.manager.mapper.ManagerMapper;
import com.ningpai.manager.service.AuthorityServiceInterface;
import com.ningpai.manager.service.ManagerServiceInterface;
import com.ningpai.manager.service.MenuServiceInterface;
import com.ningpai.util.PageBean;

/**
 * 管理员处理服务类
 * 
 * @author AthrunNatu
 * @since 2013年12月17日上午11:32:28
 * @version 0.0.1
 */
@Service("managerService")
public class ManagerService implements ManagerServiceInterface {

    private static final int MANAGER_BASE_CODE = 10000;

    private static final int THIRD = 3;

    private static final String USERNAME = "username";

    private static final String PASSWORD = "password";

    // spring注解
    private MenuServiceInterface menuServiceInterface;

    private ManagerAuthorityMapper managerAuthorityMapper;

    private AuthorityServiceInterface authorityServiceInterface;

    private ManagerMapper managerMapper;

    private Manager manager;

    @Autowired
    private ManagerLoginHistoryMapper managerLoginHistoryMapper;
    /**
     *
     */
    @Override
    public int ifManager(HttpServletRequest request, String name, String password) {
        // int 3:表示账户被冻结 2:密码错误 1:表示正常 0:用户名不存在
        Long existsFlag = managerMapper.checkexistsByCustName(name);
        Map<String, Object> paramMap = null;
        if (!"0".equals(existsFlag.toString())) {
            // manager = managerMapper.selectByName(name);
            paramMap = new HashMap<String, Object>();
            paramMap.put(USERNAME, name);
            paramMap.put(PASSWORD, password);
            manager = managerMapper.selectCustomerByNamePwd(paramMap);
            /*
             * //判断是否是私有云的配置账号 if(manager!=null && manager.getCreateId()!=null
             * && manager.getCreateId().longValue()==-1L){ // 获取当前系统时间-
             * 账号创建时间>7天 不能登录 if (System.currentTimeMillis() -
             * manager.getCreateTime().getTime() > (60 * 60 * 24 * 1000 * 7)) {
             * Map<String, Object> map = new HashMap<String, Object>();
             * map.put("id", manager.getId()); // 删除管理员
             * managerMapper.delectMangerByIds(map); // 删除权限
             * managerAuthorityMapper.deleteByManagerIds(map);
             * manager.setFlag("2"); } }
             */
            if (manager != null && "0".equals(manager.getFlag())) {
                // 将登陆名存在session
                request.getSession().setAttribute("name", name);
                request.getSession().setAttribute("loginUserId", manager.getId());
                request.getSession().setAttribute("photoImg", manager.getPhotoImg());
                //add by 卢勇 start
                request.getSession().setAttribute("lastLonginTime",manager.getLoginTime());
                //add by 卢勇 end
                // 是否是管理员
                request.getSession().setAttribute("managerFlag",
                        authorityServiceInterface.queryAuthorByManagerId(manager.getId()).getAuthorityId().equals(authorityServiceInterface.querySupperAuthor().getId()) ? 1 : 0);
                // 修改管理员登陆时间
                managerMapper.updateByPrimaryKey(manager);
                //添加管理员登录记录
                ManagerLoginHistory loginHistory = new ManagerLoginHistory();
                loginHistory.setManagerId(manager.getId());
                loginHistory.setLoginDate(new Date());
                loginHistory.setLoginIP(IPAddress.getIpAddr(request));
                //web登录
                loginHistory.setLoginType("1");
                managerLoginHistoryMapper.insert(loginHistory);
                return MANAGER_BASE_CODE + manager.getId().intValue();
            } else if (manager != null && !"0".equals(manager.getFlag())) {
                // 冻结
                return THIRD;
            } else {
                // 密码错误
                return 2;
            }
        } else {
            return 0;
        }

    }

    /**
     *
     */
    @Override
    public List<MenuVo> queryMenuByMangerName(String name) {
        // 如果用户不存在返回null
        if (this.queryManagerByName(name) == null) {
            return Collections.emptyList();
        } else {
            // 如果用户存在返回查询的菜单信息
            return menuServiceInterface.getMenuLists(this.queryManagerByName(name));
        }
    }

    /**
     *
     */
    @Override
    public PageBean queryManagerList(Manager manager, PageBean pageBean, Long createId) {
        Integer no = 0;
        try {
            manager.setCreateId(createId);
            // 设置总行数
            pageBean.setRows(managerMapper.queryManagerCount(manager));
            // 设置no计算
            no = pageBean.getRows() % pageBean.getPageSize() == 0 ? pageBean.getRows() / pageBean.getPageSize() : (pageBean.getRows() / pageBean.getPageSize() + 1);
            // 判断no是否为0 如果no==0 设置为1
            no = no == 0 ? 1 : no;
            // 若页码超过最大页码 则显示最后一个
            if (pageBean.getPageNo() >= no) {
                pageBean.setPageNo(no);
                pageBean.setStartRowNum((no - 1) * pageBean.getPageSize());
            }
            // 设置查询条件
            if (manager != null) {
                manager.setStartRowNum(pageBean.getStartRowNum());
                manager.setEndRowNum(pageBean.getEndRowNum());
            }
            // 查询会员信息
            pageBean.setObjectBean(manager);
            // 设置查询list
            pageBean.setList(managerMapper.selectManagerByManager(manager));
        } finally {
            no = null;
        }
        return pageBean;
    }

    /**
     *
     */
    @Override
    public int addManager(Manager manager, String authorityId) {
        return managerMapper.addManager(manager, authorityId);
    }

    /**
     *
     */
    @Override
    public Manager queryManagerById(Long id) {
        return managerMapper.selectById(id);
    }

    /**
     *
     */
    @Override
    public int deleteManager(String[] parameterIds) {
        // 申明count
        Integer count = 0;
        // 申明Map查询参数
        Map<String, Object> paramMap = new HashMap<String, Object>();
        try {
            if (null != parameterIds && parameterIds.length > 0) {
                paramMap.put("parameterIds", parameterIds);
                // 删除管理员
                count = managerMapper.delectMangerByIds(paramMap);
                // 删除权限管理员关系
                managerAuthorityMapper.deleteByManagerIds(paramMap);
            }
            // 返回count
            return count;
        } finally {
            count = null;
            paramMap = null;
        }
    }

    /**
     *
     */
    @Override
    public int enabledManager(String[] parameterIds) {
        // 申明count
        Integer count = 0;
        // 申明Map查询参数
        Map<String, Object> paramMap = new HashMap<String, Object>();
        try {
            if (null != parameterIds && parameterIds.length > 0) {
                paramMap.put("parameterIds", parameterIds);
                // 启用管理员
                count = managerMapper.enabledMangerByIds(paramMap);
                // 启用权限管理员关系
                managerAuthorityMapper.enabledByManagerIds(paramMap);
            }
            // 返回count
            return count;
        } finally {
            count = null;
            paramMap = null;
        }
    }

    /**
     *
     */
    @Override
    public int updateManager(Manager manager, ManagerAuthority authority) {
        // 修改管理员信息
        int count = managerMapper.updateByPrimaryKeySelective(manager);
        // 如果修改成功为1
        if (count == 1) {
            // 设置权限
            authority.setManagerId(manager.getId());
            count += managerAuthorityMapper.updateByPrimaryKeySelective(authority);
        }
        return count;
    }

    /**
     *
     */
    @Override
    public Manager queryManagerByName(String name) {
        return managerMapper.selectByName(name);
    }

    @Override
    public Manager queryDelManagerByName(String name) {
        return managerMapper.selectDelManagerByName(name);
    }

    /**
     *
     */
    @Override
    public int checkUserKey(HttpServletRequest request, String userKey) {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        try {
            paramMap.put(USERNAME, request.getSession().getAttribute("name").toString());
            paramMap.put(PASSWORD, userKey);
            // 通过用户名和密码查询用户
            manager = managerMapper.selectCustomerByNamePwd(paramMap);
            // 不为null代表密码正确
            if (manager != null) {
                return 1;
            } else {
                return 0;
            }
        } finally {
            paramMap = null;
        }

    }

    /**
     *
     */
    @Override
    public int modifiedUserKey(HttpServletRequest request, String userKey, String newuserkey) {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        try {
            paramMap.put(USERNAME, request.getSession().getAttribute("name").toString());
            paramMap.put(PASSWORD, userKey);
            // 通过用户名和密码查询用户
            manager = managerMapper.selectCustomerByNamePwd(paramMap);
            if (manager != null) {
                manager.setUserkey(newuserkey);
                if (managerMapper.updateByPrimaryKeySelective(manager) == 1) {
                    return 1;
                } else {
                    return 0;
                }
            } else {
                return 0;
            }
        } finally {
            paramMap = null;
        }

    }

    /**
     *
     */
    @Override
    public Long checkManagerExist(String username) {
        return managerMapper.checkexistsByCustName(username);
    }

    /**
     *
     */
    @Override
    public int updateManagerOnly(Manager manager) {
        return managerMapper.updateByPrimaryKeySelective(manager);
    }

    /**
     * 自动冻结过期账号
     */
    public void stopCloudAccountByTime() {
        System.out.println("========自动冻结过期账号==========");
        // 查询出所有私有云配置账号
        List<Manager> managers = managerMapper.queryCloudManagerList();
        if (managers != null) {
            for (int i = 0; i < managers.size(); i++) {
                if (managers.get(i).getCreateTime() != null && System.currentTimeMillis() - managers.get(i).getCreateTime().getTime() > (60 * 60 * 24 * 1000 * 7)) {
                    // 判断是否对账号进行冻结
                        Map<String, Object> paramMap = new HashMap<String, Object>();
                        paramMap.put("id", managers.get(i).getId());
                        // 删除管理员
                        managerMapper.delectMangerByIds(paramMap);
                        // 删除权限
                        managerAuthorityMapper.deleteByManagerIds(paramMap);
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

    public MenuServiceInterface getMenuServiceInterface() {
        return menuServiceInterface;
    }

    @Resource(name = "menuServiceInterface")
    public void setMenuServiceInterface(MenuServiceInterface menuServiceInterface) {
        this.menuServiceInterface = menuServiceInterface;
    }

    public ManagerMapper getManagerMapper() {
        return managerMapper;
    }

    @Resource(name = "managerMapper")
    public void setManagerMapper(ManagerMapper managerMapper) {
        this.managerMapper = managerMapper;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public AuthorityServiceInterface getAuthorityServiceInterface() {
        return authorityServiceInterface;
    }

    @Resource(name = "authorityService")
    public void setAuthorityServiceInterface(AuthorityServiceInterface authorityServiceInterface) {
        this.authorityServiceInterface = authorityServiceInterface;
    }

}
