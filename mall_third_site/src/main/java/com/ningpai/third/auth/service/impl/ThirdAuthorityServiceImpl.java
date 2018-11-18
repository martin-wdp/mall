/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.third.auth.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ningpai.customer.bean.Customer;
import com.ningpai.customer.dao.CustomerMapper;
import com.ningpai.customer.service.CustomerServiceMapper;
import com.ningpai.other.bean.CustomerAllInfo;
import com.ningpai.third.auth.bean.ThirdAuthority;
import com.ningpai.third.auth.bean.ThirdManagerAuthority;
import com.ningpai.third.auth.bean.ThirdPage;
import com.ningpai.third.auth.mapper.ThirdAuthorityMapper;
import com.ningpai.third.auth.mapper.ThirdAuthorityPageMapper;
import com.ningpai.third.auth.mapper.ThirdManagerAuthorityMapper;
import com.ningpai.third.auth.mapper.ThirdManagerMapper;
import com.ningpai.third.auth.service.ThirdAuthorityService;
import com.ningpai.third.util.SellerConstants;
import com.ningpai.util.PageBean;

/**
 * @author NINGPAI-zhangqiang
 * @version 0.0.1
 * @see com.ningpai.third.auth.service.ThirdAuthorityService
 * @since 2014年5月10日 上午11:18:33
 */
@Service("thirdAuthorityService")
public class ThirdAuthorityServiceImpl implements ThirdAuthorityService {

    private static final String THIRDID = "thirdId";

    /**
     * 权限关系表 dao层接口
     */
    private ThirdAuthorityMapper thirdAuthorityMapper;
    /**
     * 权限页面关系 接口
     */
    private ThirdAuthorityPageMapper authorityPageMapper;
    /**
     * 会员接口
     */
    private CustomerMapper customerMapper;
    /**
     * 第三方 角色权限关系Mapper 接口
     */
    private ThirdManagerAuthorityMapper managerAuthorityMapper;
    /**
     * 第三方管理员底层 接口
     */
    private ThirdManagerMapper thirdManagerMapper;
    /**
     * 会员服务处理接口
     */
    private CustomerServiceMapper customerServiceMapper;

    /**
     * 根据主键获取单个的职位对象
     *
     * @param id
     *            职位ID
     * @return
     */
    @Override
    public ThirdAuthority selectAuthorById(Long id) {
        return thirdAuthorityMapper.selectAuthorById(id);
    }

    /**
     * 根据商家编号查询商家权限列表
     *
     * @param storeId
     *            商家编号 {@link java.lang.Long}
     * @return
     */
    public List<ThirdAuthority> queryThirdAuthorityByStotreId(Long storeId) {
        // 根据商家编号查询商家权限列表
        List<ThirdAuthority> authList = thirdAuthorityMapper.queryThirdAuthorityByStotreId(storeId);
        // 装载查询条件
        Map<String, Object> parmaMap = null;
        for (ThirdAuthority auth : authList) {
            for (ThirdPage page : auth.getPages()) {
                parmaMap = new HashMap<String, Object>();
                // 权限ID
                parmaMap.put("authorityId", auth.getId());
                // 父类ID
                parmaMap.put("parentId", page.getId());
                // 根据权限编号 页面父编号查找页面
                page.setMenuVos(authorityPageMapper.selectPageByAuthIdAndParentId(parmaMap));
            }
        }
        return authList;
    }

    /**
     * 添加权限角色
     *
     * @param request
     * @param thirdAuthority
     *            商家权限 {@link com.ningpai.third.auth.bean.ThirdAuthority}
     * @return
     */
    public int addAuthority(HttpServletRequest request, ThirdAuthority thirdAuthority) {
        // 设置商家Id
        thirdAuthority.setStoreId((Long) request.getSession().getAttribute(THIRDID));
        return thirdAuthorityMapper.insertSelective(thirdAuthority);
    }

    /**
     * 查询权限角色存在性
     *
     * @param request
     * @param thirdAuthority
     *            商家权限 {@link com.ningpai.third.auth.bean.ThirdAuthority}
     * @return
     */
    public int checkAuthorityExist(HttpServletRequest request, ThirdAuthority thirdAuthority) {
        // 根据角色名称查找角色
        List<ThirdAuthority> list = thirdAuthorityMapper.selectByDesignation(thirdAuthority.getDesignation(), (Long) request.getSession().getAttribute(THIRDID));
        if (list != null && !list.isEmpty()) {
            return 1;
        }
        return 0;
    }

    /**
     * 删除权限角色
     *
     * @param thirdAuthority
     *            商家权限 {@link com.ningpai.third.auth.bean.ThirdAuthority}
     *            thirdId 商家ID
     * @param thirdId
     * @return
     */
    public int delAuthority(ThirdAuthority thirdAuthority, Long thirdId) {
        return thirdAuthorityMapper.deleteByPrimaryKey(thirdAuthority.getId(), thirdId);
    }

    /**
     * 修改权限名称 --重命名
     *
     * @param thirdAuthority
     *            商家权限 {@link com.ningpai.third.auth.bean.ThirdAuthority}
     * @return
     */
    public int updateAuthorityName(ThirdAuthority thirdAuthority) {
        return thirdAuthorityMapper.updateByPrimaryKeySelective(thirdAuthority);
    }

    /**
     * 查询商家员工列表
     *
     * @param stordId
     *            Long 商家编号 {@link Long}
     * @param pb
     *            分页辅助类 {@link PageBean}
     * @return
     */
    public PageBean queryEmployeeListByStotreId(Long stordId, PageBean pb) {
        // 设置分页的页数
        pb.setPageSize(10);
        // 装载查询的条件
        Map<String, Object> paramMap = new HashMap<String, Object>();
        // 根据商家编号 查询商家员工数量
        Long count = thirdManagerMapper.queryEmployeeList(stordId);
        // 获取数据的总行数
        pb.setRows(Integer.parseInt(count == null ? "0" : count.toString()));
        if (pb.getPageNo() > pb.getLastPageNo()) {
            pb.setPageNo(pb.getLastPageNo());
        }
        if (pb.getPageNo() == 0) {
            pb.setPageNo(1);
        }
        // 商家ID
        paramMap.put("storeId", stordId);
        // 分页起始页数
        paramMap.put(SellerConstants.STARTNUM, pb.getStartRowNum());
        // 分页结束页数
        paramMap.put(SellerConstants.ENDNUM, pb.getEndRowNum());
        // 根据商家编号 查询商家员工集合
        pb.setList(thirdManagerMapper.queryEmployeeListByStotreId(paramMap));
        return pb;
    }

    /**
     * 添加员工
     *
     * @param request
     * @param allInfo
     *            员工信息 {@link CustomerAllInfo}
     * @param authId
     *            权限编号 {@link Long}
     * @return
     */
    @Transactional
    public int addEmp(HttpServletRequest request, CustomerAllInfo allInfo, Long authId) {
        // 设置商家ID
        allInfo.setThirdId((Long) request.getSession().getAttribute(THIRDID));
        allInfo.setIsSeller("2");
        // 添加会员allinfo
        customerServiceMapper.addCustomer(allInfo);
        // 保存查询的条件
        Map<String, Object> paramMap = new HashMap<String, Object>();
        // 用户名
        paramMap.put("username", allInfo.getCustomerUsername());
        // 用户密码
        paramMap.put("password", allInfo.getCustomerPassword());
        // 根据条件查询出来的会员信息
        Customer customer = customerMapper.selectCustomerByNamePwd(paramMap);
        // 是否查询到会员
        if (customer != null) {
            paramMap = new HashMap<String, Object>();
            // 设置会员ID
            paramMap.put("customerId", allInfo.getCustomerId());
            // 权限ID
            paramMap.put("authId", authId);
            // 商家ID
            paramMap.put("storeId", (Long) request.getSession().getAttribute(THIRDID));
            // 添加员工权限记录
            managerAuthorityMapper.addRecord(paramMap);
        }
        return 0;
    }

    /**
     * 验证用户名是否存在
     *
     * @param map
     * @return
     */
    @Override
    public Integer checkUsernameExitOrNot(Map<String, Object> map) {
        // 查询商家员工用户名是否存在
        return customerServiceMapper.checkUsernameExitOrNot(map);
    }

    /**
     * 修改员工角色
     *
     * @param author
     *            第三方管理员权限
     * @param thirdId
     * @return
     */
    @Override
    public int updateByPrimaryKeySelective(ThirdManagerAuthority author, Long thirdId) {
        // 根据查询该管理员ID对应的店铺商家ID 重新创建一个对象用于两个Long类型的额值进行比较
        Long bussId = Long.valueOf(managerAuthorityMapper.selectCustomerByManagerId(author.getManagerId()));
        // 判断是否是修改当前店铺的员工权限
        if (bussId.equals(thirdId)) {
            // 是当前商家就允许修改该员工权限
            return managerAuthorityMapper.updateByPrimaryKeySelective(author);
        }
        return 0;
        // 修改权限

    }

    public ThirdAuthorityMapper getThirdAuthorityMapper() {
        return thirdAuthorityMapper;
    }

    @Resource(name = "thirdAuthorityMapper")
    public void setThirdAuthorityMapper(ThirdAuthorityMapper thirdAuthorityMapper) {
        this.thirdAuthorityMapper = thirdAuthorityMapper;
    }

    public ThirdAuthorityPageMapper getAuthorityPageMapper() {
        return authorityPageMapper;
    }

    @Resource(name = "thirdAuthorityPageMapper")
    public void setAuthorityPageMapper(ThirdAuthorityPageMapper authorityPageMapper) {
        this.authorityPageMapper = authorityPageMapper;
    }

    public CustomerMapper getCustomerMapper() {
        return customerMapper;
    }

    @Resource(name = "customerMapper")
    public void setCustomerMapper(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    public ThirdManagerAuthorityMapper getManagerAuthorityMapper() {
        return managerAuthorityMapper;
    }

    @Resource(name = "thirdManagerAuthorityMapper")
    public void setManagerAuthorityMapper(ThirdManagerAuthorityMapper managerAuthorityMapper) {
        this.managerAuthorityMapper = managerAuthorityMapper;
    }

    public ThirdManagerMapper getThirdManagerMapper() {
        return thirdManagerMapper;
    }

    @Resource(name = "thirdManagerMapper")
    public void setThirdManagerMapper(ThirdManagerMapper thirdManagerMapper) {
        this.thirdManagerMapper = thirdManagerMapper;
    }

    public CustomerServiceMapper getCustomerServiceMapper() {
        return customerServiceMapper;
    }

    @Resource(name = "customerServiceMapper")
    public void setCustomerServiceMapper(CustomerServiceMapper customerServiceMapper) {
        this.customerServiceMapper = customerServiceMapper;
    }

}
