package com.junit.custom.customer.service;

import java.util.List;

import org.junit.Test;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.io.annotation.FileContent;
import org.unitils.mock.Mock;

import com.alibaba.fastjson.JSON;
import com.ningpai.customer.bean.Educationinfo;
import com.ningpai.customer.dao.EducationinfoMapper;
import com.ningpai.customer.service.EducationinfoServiceMapper;
import com.ningpai.customer.service.impl.EducationinfoServiceMapperImpl;

/**
 * 教育服务处理接口单元测试
 * @author qiyuanyuan
 *
 */
public class EducationinfoServiceMapperTest extends UnitilsJUnit3{

    /**
     * 需要测试的service
     */
    @TestedObject
    private EducationinfoServiceMapper educationinfoServiceMapper = new EducationinfoServiceMapperImpl();

    /**
     * 模拟
     */
    @InjectIntoByType
    Mock<EducationinfoMapper> educationinfoMapperMock;
    
    /**
     * 共享数据
     */
    List<Educationinfo> educationinfoList;
    
    /**
     * JS数据
     */
    @FileContent("educationinfoList.js")
    private String educationinfoListJS;
    
    /**
     * 初始化数据
     */
    public void setUp(){
        educationinfoList = JSON.parseArray(educationinfoListJS, Educationinfo.class);
    }
    
    /**
     * 测试根据主键删除教育信息
     */
    @Test
    public void testDeleteByPrimaryKey(){
        educationinfoMapperMock.returns(1).deleteByPrimaryKey(1L);
        assertEquals(1, educationinfoServiceMapper.deleteByPrimaryKey(1L));
    }
    
    /**
     * 测试添加教育信息
     */
    public void testInsert(){
        educationinfoMapperMock.returns(1).insert(educationinfoList.get(0));
        assertEquals(1, educationinfoServiceMapper.insert(educationinfoList.get(0)));
    }
    
    /**
     * 测试根据主键查询教育信息
     */
    @Test
    public void testSelectByPrimaryKey(){
        educationinfoMapperMock.returns(educationinfoList.get(0)).selectByPrimaryKey(1L);
        assertNotNull(educationinfoServiceMapper.selectByPrimaryKey(1L));
    }
    
    /**
     * 测试更新教育信息
     */
    @Test
    public void testUpdateByPrimaryKey(){
        educationinfoMapperMock.returns(1).updateByPrimaryKey(educationinfoList.get(0));
        assertEquals(1, educationinfoServiceMapper.updateByPrimaryKey(educationinfoList.get(0)));
    }
    
    /**
     * 测试查询所有教育信息
     */
    @Test
    public void testSelectAll(){
        educationinfoMapperMock.returns(educationinfoList).selectAll(1L);
        assertEquals(1, educationinfoServiceMapper.selectAll(1L).size());
    }
} 
