/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.customer.controller;

import com.ningpai.customer.bean.Educationinfo;
import com.ningpai.customer.service.EducationinfoServiceMapper;
import com.ningpai.other.util.CustomerConstantStr;
import com.ningpai.util.MyLogger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * 教育控制层
 * 
 * @author jiping
 * @since 2014年7月7日 下午4:02:21
 * @version 0.0.1
 */
@Controller
public class EducationinfoController {
    // spring注解
    private EducationinfoServiceMapper educationinfoMapper;
    private static final MyLogger LOGGER = new MyLogger(
            EducationinfoController.class);

    /**
     * 添加教育信息
     * 
     * @param educationinfo
     * @param request
     * @return
     */
    @RequestMapping("/addeducation")
    @ResponseBody
    public int addEducation(@Valid Educationinfo educationinfo,
            HttpServletRequest request) {
        // 当前登录成功的会员
        Long customerId = (Long) request.getSession().getAttribute(
                CustomerConstantStr.CUSTOMERID);
        // 验证学校名称是否为空
        if (null != educationinfo.getSchoolName()) {
            // 日志记录
            LOGGER.info("添加学校名称为：" + educationinfo.getSchoolName() + "的教育信息成功！");
        }
        educationinfo.setCustomerId(customerId);
        // 添加教育信息
        return educationinfoMapper.insert(educationinfo);
    }

    /**
     * 删除教育信息
     * 
     * @param request
     * @param educationId
     * @return
     */
    @RequestMapping("/deleducation")
    @ResponseBody
    public int deleteEducation(HttpServletRequest request, long educationId) {
        // 当前登录成功的会员
        Long customerId = (Long) request.getSession().getAttribute(
                CustomerConstantStr.CUSTOMERID);
        // 获取单个的教育对象
        Educationinfo educationinfo = educationinfoMapper
                .selectByPrimaryKey(educationId);
        if (customerId != null && educationinfo != null
                && educationinfo.getCustomerId().equals(customerId)) {
            // 获取单个的教育对象
            if (null != educationinfo.getSchoolName()) {
                // 日志记录
                LOGGER.info("删除学校名称为：" + educationinfo.getSchoolName()
                        + "的教育信息成功！");
            }
            // 删除教育信息

            return educationinfoMapper.deleteByPrimaryKey(educationId);
        }
        return 0;

    }

    /**
     * 修改教育信息
     * 
     * @param request
     * @param educationinfo
     * @return
     */
    @RequestMapping("/updateeducation")
    @ResponseBody
    public int updateEducation(HttpServletRequest request,
            @Valid Educationinfo educationinfo) {
        // 当前登录成功的会员
        Long customerId = (Long) request.getSession().getAttribute(
                CustomerConstantStr.CUSTOMERID);
        if (customerId != null
                && customerId.equals(educationinfo.getCustomerId())) {
            // 非空验证 学校名称
            if (null != educationinfo.getSchoolName()) {
                LOGGER.info("修改学校名称为：" + educationinfo.getSchoolName()
                        + "教育信息成功！");
            }
            // 修改教育信息
            return educationinfoMapper.updateByPrimaryKey(educationinfo);
        }
        return 0;
    }

    /**
     * 根据教育编号查询单个教育信息
     * 
     * @param request
     * @param educationId
     * @return
     */
    @RequestMapping("/selectedubyid")
    @ResponseBody
    public Educationinfo selectEduById(HttpServletRequest request,
            long educationId) {
        // 当前登录成功的会员
        Long customerId = (Long) request.getSession().getAttribute(
                CustomerConstantStr.CUSTOMERID);
        // 获取单个的教育对象
        Educationinfo educationinfo = educationinfoMapper
                .selectByPrimaryKey(educationId);
        if (customerId != null && educationinfo != null
                && educationinfo.getCustomerId().equals(customerId)) {
            if (null != educationinfo.getSchoolName()) {
                // 日志记录
                LOGGER.info("获取学校名称为：" + educationinfo.getSchoolName()
                        + "的教育信息！");
            }
            // 根据id查看教育信息
            return educationinfoMapper.selectByPrimaryKey(educationId);
        }
        return null;
    }

    /**
     * 查看全部教育信息
     * 
     * @param request
     * @return List<Educationinfo>
     */
    @RequestMapping("/selectalledu")
    @ResponseBody
    public List<Educationinfo> selectAllEdu(HttpServletRequest request) {
        // 当前登录成功的会员
        Long customerId = (Long) request.getSession().getAttribute(
                CustomerConstantStr.CUSTOMERID);
        // 查看全部教育信息
        return educationinfoMapper.selectAll(customerId);
    }

    public EducationinfoServiceMapper getEducationinfoMapper() {
        return educationinfoMapper;
    }

    @Resource(name = "educationinfoServiceMapper")
    public void setEducationinfoMapper(
            EducationinfoServiceMapper educationinfoMapper) {
        this.educationinfoMapper = educationinfoMapper;
    }
}
