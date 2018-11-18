/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.manager.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.ningpai.manager.bean.valuebean.MenuVo;
import org.springframework.stereotype.Service;

import com.ningpai.manager.bean.Authority;
import com.ningpai.manager.bean.AuthorityPage;
import com.ningpai.manager.bean.ManagerAuthority;
import com.ningpai.manager.mapper.AuthorityMapper;
import com.ningpai.manager.mapper.AuthorityPageMapper;
import com.ningpai.manager.mapper.ManagerAuthorityMapper;
import com.ningpai.manager.mapper.PageMapper;
import com.ningpai.manager.service.AuthorityServiceInterface;
import com.ningpai.util.PageBean;

/**
 * 权限服务处理类
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年1月9日 上午1:21:14
 * @version 0.0.1
 */
@Service("authorityService")
public class AuthorityService implements AuthorityServiceInterface {

    private static final String CREATEID = "createId";
    private static final String AUTHORITYID = "authorityId";
    private static final String PAGEID = "pageId";

    // spring 注解
    private AuthorityMapper authorityMapper;
    // spring 注解
    private ManagerAuthorityMapper managerAuthorityMapper;
    // spring 注解
    private AuthorityPageMapper authorityPageMapper;
    // spring 注解
    private PageMapper pageMapper;

    @Override
    public List<Authority> queryAuthoerityList(Long createId) {
        return authorityMapper.selectAllAuthority(createId);
    }

    public AuthorityMapper getAuthorityMapper() {
        return authorityMapper;
    }

    @Resource(name = "authorityMapper")
    public void setAuthorityMapper(AuthorityMapper authorityMapper) {
        this.authorityMapper = authorityMapper;
    }

    public PageMapper getPageMapper() {
        return pageMapper;
    }

    @Resource(name = "PageMapperImpl")
    public void setPageMapper(PageMapper pageMapper) {
        this.pageMapper = pageMapper;
    }

    public AuthorityPageMapper getAuthorityPageMapper() {
        return authorityPageMapper;
    }

    @Resource(name = "authorityPageMapper")
    public void setAuthorityPageMapper(AuthorityPageMapper authorityPageMapper) {
        this.authorityPageMapper = authorityPageMapper;
    }

    /**
     *
     */
    public ManagerAuthority queryAuthorByManagerId(Long mid) {
        return managerAuthorityMapper.selectByManagerId(mid);
    }

    public ManagerAuthorityMapper getManagerAuthorityMapper() {
        return managerAuthorityMapper;
    }

    @Resource(name = "managerAuthorityMapper")
    public void setManagerAuthorityMapper(ManagerAuthorityMapper managerAuthorityMapper) {
        this.managerAuthorityMapper = managerAuthorityMapper;
    }

    /**
     * 
     *
     */
    @Override
    public PageBean queryAuthoerityList(Authority authority, PageBean pageBean, Long createId) {
        Authority authorityNew = authority;
        Map<String, Object> paramMap = null;
        Integer no = 0;
        try {
            if (authorityNew == null) {
                authorityNew = new Authority();
            }
            authorityNew.setCreateId(createId);
            // 设置总行数
            pageBean.setRows(Integer.parseInt(authorityMapper.queryAuthoritySize(authorityNew) + ""));
            no = pageBean.getRows() % pageBean.getPageSize() == 0 ? pageBean.getRows() / pageBean.getPageSize() : (pageBean.getRows() / pageBean.getPageSize() + 1);
            no = no == 0 ? 1 : no;
            // 若页码超过最大页码 则显示最后一个
            if (pageBean.getPageNo() >= no) {
                pageBean.setPageNo(no);
                pageBean.setStartRowNum((no - 1) * pageBean.getPageSize());
            }
            // 设置查询条件
            if (authorityNew != null) {
                authorityNew.setStartRowNum(pageBean.getStartRowNum());
                authorityNew.setEndRowNum(pageBean.getEndRowNum());
            }
            paramMap = new HashMap<String, Object>();
            paramMap.put("startRowNum", pageBean.getStartRowNum());
            paramMap.put("endRowNum", pageBean.getEndRowNum());
            paramMap.put(CREATEID, createId);
            pageBean.setObjectBean(authorityNew);
            // 查询会员信息
            pageBean.setList(authorityNew == null ? authorityMapper.selectAuthorityByLimit(paramMap) : authorityMapper.selectAuthorityByAuthority(authorityNew));
        } finally {
            paramMap = null;
            no = null;
        }
        return pageBean;
    }

    /**
     *
     */
    @Override
    public int deleteAuthority(String[] parameterValues) {
        Integer count = 0;
        Map<String, Object> paramMap = new HashMap<String, Object>();
        try {
            paramMap.put("parameterValues", parameterValues);
            count = authorityMapper.deleteAuthorityByIds(paramMap);
        } finally {
            paramMap = null;
        }
        return count;
    }

    @Override
    public int mergeAuthority(String split, Authority authority) {
        int num = authorityMapper.updateByPrimaryKey(authority);
        if (split.length() != 0) {
            ArrayList<Map<String, Object>> authorities = new ArrayList<Map<String, Object>>(0);
            // 存放要添加的权限Id
            List<Long> pageList = new ArrayList<Long>(0);
            // 遍历传递过来的权限Id,并存放到集合中
            for (String str : split.split(",")) {
                pageList.add(Long.parseLong(str));
            }
            // 查询存在的权限Id
            List<Long> pageIds = pageMapper.selectByPrimaryKeys(pageList);
            // 遍历存在的权限Id
            for (Long id : pageIds) {
                Map<String, Object> map = new HashMap<String, Object>();
                char type = id != null && id == 0 ? '0' : '1';
                if (id == null) {
                    continue;
                }
                map.put(AUTHORITYID, authority.getId());
                map.put(PAGEID, id);
                map.put("type", type);
                authorities.add(map);
            }
            // 批量插入选中的角色
            authorityPageMapper.insertAuthorities(authorities);
        }
        return num;
    }

    @Override
    public int updateAuthority(String split, Authority authority) {
        int num = authorityMapper.updateByPrimaryKey(authority);
        if (split.length() != 0) {
            for (String str : split.split(",")) {
                Map<String, Object> map = new HashMap<String, Object>();
                Long result = pageMapper.selectByPrimaryKey(Long.parseLong(str)).getId();
                char type = result != null && result == 0 ? '0' : '1';
                if (result == null) {
                    continue;
                }
                map.put(AUTHORITYID, authority.getId());
                map.put(PAGEID, Long.parseLong(str));
                map.put("type", type);
                authorityPageMapper.insertAAndPage(map);
                map.clear();
            }
        }
        return num;
    }

    /**
     *
     */
    @Override
    public int addAuthority(String split, Authority authority, Long createId) {
        int num = 0;
        Long aid = 0L;
        Map<String, Object> paramMap = null;
        if (authority.getId() != null) {
            aid = authority.getId();
        } else {
            // 封装查询条件
            paramMap = new HashMap<String, Object>();
            paramMap.put("designation", authority.getDesignation());
            paramMap.put("characterization", authority.getCharacterization());
            paramMap.put("id", authority.getId());
            paramMap.put(CREATEID, createId);
            // 插入主表
            num = authorityMapper.insertByDesignation(paramMap);
            // 查询主键
            aid = authorityMapper.selectLastId();

        }
        try {
            if (split.length() != 0) {
                for (String str : split.split(",")) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    Long result = pageMapper.selectByPrimaryKey(Long.parseLong(str)).getId();
                    char type = result != null && result == 0 ? '0' : '1';
                    if (result == null) {
                        continue;
                    }
                    map.put(AUTHORITYID, aid);
                    map.put(PAGEID, Long.parseLong(str));
                    map.put("type", type);
                    num += authorityPageMapper.insertAAndPage(map);
                    map.clear();
                }
            }
        } finally {
            paramMap = null;
            aid = null;
        }
        return num;
    }

    @Override
    public Authority queryAuthorId(Long id) {
        return authorityMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<AuthorityPage> queryAuthorityByAId(Long id) {
        return authorityMapper.selectAuthorityByAId(id);
    }

    @Override
    public int deleteAuthorityPage(Long id) {
        return authorityPageMapper.deleteAuthorityPageById(id);
    }

    @Override
    public Authority querySupperAuthor() {
        return authorityMapper.querySupperAuthor();
    }

    @Override
    public Long checkAuthExist(String authName) {
        return authorityMapper.checkAuthExist(authName);
    }

    @Override
    public Authority checkManagerExist(String username) {
        return authorityMapper.checkManagerExist(username);
    }

    /**
     * 批量赋予某个角色权限
     *
     * @param authorityId
     *            角色id
     * @param menuVos
     *            菜单列表
     */
    @Override
    public void addAuthorityPageBatch(Long authorityId, List<MenuVo> menuVos) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(AUTHORITYID, authorityId);
        paramMap.put("menuVos", menuVos);
        this.authorityPageMapper.addAuthorityPageBatch(paramMap);
    }

    /**
     * 删除模块中所有的权限
     *
     * @param bundleName
     *            开始菜单bundleName
     */
    @Override
    public void deleteAuthByBundleName(String bundleName) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("bundleName", bundleName);
        this.authorityPageMapper.deleteAuthByParam(paramMap);
    }

    /**
     * 添加权限
     *
     * @param split
     * @param authority
     * @return
     */
    @Override
    public int insertAuthorities(String split, Authority authority, Long createId) {
        int num = 0;
        Long aid = 0L;
        Map<String, Object> paramMap = null;
        if (authority.getId() != null) {
            aid = authority.getId();
        } else {
            // 封装查询条件
            paramMap = new HashMap<String, Object>();
            paramMap.put("designation", authority.getDesignation());
            paramMap.put("characterization", authority.getCharacterization());
            paramMap.put("id", authority.getId());
            paramMap.put(CREATEID, createId);
            // 插入主表
            num = authorityMapper.insertByDesignation(paramMap);
            // 查询主键
            aid = authorityMapper.selectLastId();

        }
        try {
            if (split.length() != 0) {
                ArrayList<Map<String, Object>> authorities = new ArrayList<Map<String, Object>>(0);
                // 存放要添加的权限Id
                List<Long> pageList = new ArrayList<Long>(0);
                // 遍历传递过来的权限Id,并存放到集合中
                for (String str : split.split(",")) {
                    pageList.add(Long.parseLong(str));
                }
                // 查询存在的权限Id
                List<Long> pageIds = pageMapper.selectByPrimaryKeys(pageList);
                // 遍历存在的权限Id
                for (Long id : pageIds) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    char type = id != null && id == 0 ? '0' : '1';
                    if (id == null) {
                        continue;
                    }
                    map.put(AUTHORITYID, aid);
                    map.put(PAGEID, id);
                    map.put("type", type);
                    authorities.add(map);
                }
                // 批量插入选中的角色
                authorityPageMapper.insertAuthorities(authorities);
            }

        } finally {
            paramMap = null;
            aid = null;
        }
        return num;
    }
}
