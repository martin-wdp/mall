package com.ningpai.thirdproject.service;


/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */



import com.ningpai.thirdproject.bean.ThirdProject;
import com.ningpai.util.PageBean;

import java.util.Map;

/**
 * 第三方专题管理service
 * @author zhangsl
 * @since 2015年1月15日 下午1:13:49
 * @version
 */
public interface ThirdProjectService {
    /**
     * 分页查询专题信息
     * @param pageBean
     * @return
     */
    PageBean  queryThirdProjectByPage(PageBean pageBean,ThirdProject thirdProject);
    /**
     * 添加专题信息
     * @param thirdProject
     * @return
     */
    int addThirdProject(ThirdProject thirdProject);
    /**
     * 删除专题信息（逻辑删除）
     * @return
     */
    int updateDelflagstatu(Map<String,Object> map);
    /**
     * 根据Id查询专题信息
     * @param thirdProjectId
     * @return
     */
    ThirdProject selectProjectById(Long thirdProjectId);
    /**
     * 更新专题信息
     * @param thirdProject
     * @return
     */
    int updateThirdProject(ThirdProject thirdProject);
    

}
