package com.ningpai.thirdproject.service.impl;


/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.thirdproject.bean.ThirdProject;
import com.ningpai.thirdproject.mapper.ThirdProjectMapper;
import com.ningpai.thirdproject.service.ThirdProjectService;
import com.ningpai.util.PageBean;
/**
 * 第三方专题管理service的实现类
 * @author zhangsl
 * @since 2015年1月15日 下午4:59:16
 * @version
 */
@Service("ThirdProjectService")
public class ThirdProjectServiceImpl implements ThirdProjectService {
    @Resource(name="ThirdProjectMapper")
    private ThirdProjectMapper thirdProjectMapper;
    
    
    /*
     * 分页查询专题信息
     * @see com.ningpai.third.project.service.ThirdProjectService#queryThirdProjectByPage(com.ningpai.util.PageBean)
     */
    @Override
    public PageBean queryThirdProjectByPage(PageBean pageBean,ThirdProject thirdProject) {
         Map<String, Object> paraMap = new HashMap<String, Object>();
         paraMap.put("startRowNum", pageBean.getStartRowNum());
         paraMap.put("endRowNum", pageBean.getEndRowNum());
         paraMap.put("thirdId",thirdProject.getThirdId());
         paraMap.put("thirdProjectName", thirdProject.getThirdProjectName());
         pageBean.setList(thirdProjectMapper.queryThirdProjectByPage(paraMap));
         pageBean.setRows(thirdProjectMapper.queryThirdProjectCount(thirdProject));
        return pageBean;
    }


    /*
     * 添加专题信息
     * @see com.ningpai.third.project.service.ThirdProjectService#addThirdProject(com.ningpai.third.project.bean.ThirdProject)
     */
    @Override
    public int addThirdProject(ThirdProject thirdProject) {        
        thirdProject.setThirdProjectCreateTime(new Date());
        thirdProject.setThirdProjectModifyTime(new Date());        
        return thirdProjectMapper.insertSelective(thirdProject);
    }


    /*
     * 删除专题信息（逻辑删除）
     * @see com.ningpai.third.project.service.ThirdProjectService#updateDelflagstatu(java.lang.Long)
     */
    @Override
    public int updateDelflagstatu(Map<String,Object> map) {
        
        return thirdProjectMapper.updateDelflagstatu(map);
    }


    /*
     * 根据Id查询专题信息
     * @see com.ningpai.third.project.service.ThirdProjectService#selectProjectById(java.lang.Long)
     */
    @Override
    public ThirdProject selectProjectById(Long thirdProjectId) {
        
        return thirdProjectMapper.selectByPrimaryKey(thirdProjectId);
    }


    /*
     * 更新专题信息
     * @see com.ningpai.third.project.service.ThirdProjectService#updateThirdProject(com.ningpai.third.project.bean.ThirdProject)
     */
    @Override
    public int updateThirdProject(ThirdProject thirdProject) {
        thirdProject.setThirdProjectModifyTime(new Date());
        return thirdProjectMapper.updateByPrimaryKeySelective(thirdProject);
    }

}
