/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.third.auth.service;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import com.ningpai.other.bean.CustomerAllInfo;
import com.ningpai.third.auth.bean.ThirdAuthority;
import com.ningpai.third.auth.bean.ThirdManagerAuthority;
import com.ningpai.util.PageBean;

/**
 *
 * <p>第三方权限角色Service</p>
 * @author zhanghl
 * @since 20150730
 * @version 2.0
 */
public interface ThirdAuthorityService {
    /***
     * 根据主键获取单个的职位对象
     * @param id 职位ID
     * @return
     */
    ThirdAuthority selectAuthorById(Long id);
    /**
     * 根据商家编号查询商家权限列表
     * 
     * @param storeId
     *            商家编号 {@link java.lang.Long}
     * @return List<ThirdAuthority> 商家权限 {@link com.ningpai.third.auth.bean.ThirdAuthority}
     */
    List<ThirdAuthority> queryThirdAuthorityByStotreId(Long storeId);

    /**
     * 添加权限角色
     * 
     * @param request
     * @param thirdAuthority
     *            商家权限 {@link com.ningpai.third.auth.bean.ThirdAuthority}
     * @return 0失败 1成功 {@link java.lang.Integer}
     */
    int addAuthority(HttpServletRequest request, ThirdAuthority thirdAuthority);

    /**
     * 查询权限角色存在性
     * 
     * @param request
     * @param thirdAuthority
     *            商家权限 {@link com.ningpai.third.auth.bean.ThirdAuthority}
     * @return 0不存在 1存在 {@link java.lang.Integer}
     */
    int checkAuthorityExist(HttpServletRequest request, ThirdAuthority thirdAuthority);

    /**
     * 删除权限角色
     * 
     * @param thirdAuthority
     *            商家权限 {@link com.ningpai.third.auth.bean.ThirdAuthority} thirdId 商家ID
     * @return 0失败 1成功 {@link java.lang.Integer}
     */
    int delAuthority(ThirdAuthority thirdAuthority,Long thirdId);

    /**
     * 修改权限名称 --重命名
     * 
     * @param thirdAuthority
     *            商家权限 {@link com.ningpai.third.auth.bean.ThirdAuthority}
     * @return int 0 失败 1成功
     */
    int updateAuthorityName(ThirdAuthority thirdAuthority);

    /**
     * 查询商家员工列表
     * 
     * @param stordId
     *            Long 商家编号 {@link Long}
     * @param pb
     *            分页辅助类 {@link PageBean}
     * @return 分页辅助类 {@link PageBean}
     */
    PageBean queryEmployeeListByStotreId(Long stordId, PageBean pb);

    /**
     * 添加员工
     * 
     * @param request
     * @param allInfo
     *            员工信息 {@link CustomerAllInfo}
     * @param authId
     *            权限编号 {@link Long}
     * @return 0 失败 1成功 {@link Long}
     */
    int addEmp(HttpServletRequest request, CustomerAllInfo allInfo, Long authId);
    
    /**
     * 修改员工角色
     * @param author 第三方管理员权限
     * @return
     */
    int updateByPrimaryKeySelective(ThirdManagerAuthority author,Long thirdId);

    /**
     * 验证用户名是否存在
     * @return
     */
    Integer checkUsernameExitOrNot(Map<String,Object> map);
}
