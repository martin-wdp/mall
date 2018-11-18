package com.junit.common.thirdproject.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.io.annotation.FileContent;
import org.unitils.mock.Mock;

/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */


import com.alibaba.fastjson.JSON;
import com.ningpai.thirdproject.bean.ThirdProject;
import com.ningpai.thirdproject.mapper.ThirdProjectMapper;
import com.ningpai.thirdproject.service.ThirdProjectService;
import com.ningpai.thirdproject.service.impl.ThirdProjectServiceImpl;
import com.ningpai.util.PageBean;

/**
 * 第三方专题管理service
 * @author zhangsl
 * @since 2015年1月15日 下午1:13:49
 * @version
 */
public class ThirdProjectServiceTest extends UnitilsJUnit3 {

    /**
     * 需要测试的Service
     */
    @TestedObject
    private ThirdProjectService thirdProjectService = new ThirdProjectServiceImpl();


    /**
     * 模拟
     */
    @InjectIntoByType
    Mock<ThirdProjectMapper> thirdProjectMapperMock;
    /**
     * JS数据
     */
    @FileContent("thirdProjectList.js")
    private String thirdProjectListJs;


    /**
     * 共享数据
     */
    List<ThirdProject> thirdProjectList;
    /**
     * 初始化
     */
    @Override
    public void setUp(){
        thirdProjectList = JSON.parseArray(thirdProjectListJs, ThirdProject.class);
    }


    /**
     * 分页查询专题信息
     */
    public void testQueryThirdProjectByPage(){
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("startRowNum", 0);
        paraMap.put("endRowNum", 15);
        paraMap.put("thirdId", 0L);
        paraMap.put("thirdProjectName", "name1");
        thirdProjectMapperMock.returns(thirdProjectList).queryThirdProjectByPage(paraMap);
         ThirdProject tp =  new ThirdProject();
        tp.setThirdId(0L);
        tp.setThirdProjectName("name1");
        thirdProjectMapperMock.returns(1).queryThirdProjectCount(tp);
        assertEquals(1,thirdProjectService.queryThirdProjectByPage(new PageBean(),tp).getList().size());

    }

    /**
     * 添加专题信息
     */
    public void testAddThirdProject(){
        thirdProjectMapperMock.returns(1).insertSelective(thirdProjectList.get(0));
        assertEquals(1, thirdProjectService.addThirdProject(thirdProjectList.get(0)));
    }

    /**
     * 删除专题信息（逻辑删除）
     */
    public void testUpdateDelflagstatu(){
        thirdProjectMapperMock.returns(1).updateDelflagstatu(new HashMap<String, Object>());
        assertEquals(1, thirdProjectService.updateDelflagstatu(new HashMap<String, Object>()));
    }

    /**
     * 根据Id查询专题信息
     */
    public void testSelectProjectById(){
        thirdProjectMapperMock.returns(thirdProjectList.get(0)).selectByPrimaryKey(1L);
        assertNotNull(thirdProjectService.selectProjectById(1L));
    }

    /**
     * 更新专题信息
     */
    public void testUpdateThirdProject(){
        thirdProjectMapperMock.returns(1).updateByPrimaryKeySelective(thirdProjectList.get(0));
        assertEquals(1, thirdProjectService.updateThirdProject(thirdProjectList.get(0)));
    }
    

}
