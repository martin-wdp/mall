/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.customer.controller;

import com.ningpai.customer.bean.Vocationinfo;
import com.ningpai.customer.service.VocationinfoServiceMapper;
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
 * 职业控制层
 * 
 * @author jiping
 * @since 2014年7月7日 下午4:30:15
 * @version 0.0.1
 */
@Controller("vocationController")
public class VocationinfoController {
    // spring注解
    private VocationinfoServiceMapper vocationinfoServiceMapper;

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(VocationinfoController.class);

    /**
     * 添加职业信息
     * 
     * @param request
     * @param vocationinfo
     * @return
     */
    @RequestMapping("/insertvocation")
    @ResponseBody
    public int insertVocation(HttpServletRequest request, @Valid Vocationinfo vocationinfo) {
        // 获得用户id
        Long customerId = (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);
        if (customerId != null) {
            // 填充用户
            vocationinfo.setCustomerId(customerId);
            // 非空验证 公司名称
            if (null != vocationinfo.getCompanyName()) {
                // 日志记录
                LOGGER.info("添加公司名称为：" + vocationinfo.getCompanyName());
            }
            // 添加职业
            return vocationinfoServiceMapper.insert(vocationinfo);
        }
        return 0;
    }

    /**
     * 根据主键删除职业信息
     * 
     * @param request
     * @param vocationId
     * @return
     */
    @RequestMapping("/delvocation")
    @ResponseBody
    public int delVocation(HttpServletRequest request, long vocationId) {
        // 获得用户id
        Long customerId = (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);
        // 获取单个职业信息对象
        Vocationinfo vocationinfo = vocationinfoServiceMapper.selectByPrimaryKey(vocationId);

        // 判断是否越权
        if (vocationinfo.getCustomerId().equals(customerId)) {
            // 非空验证 公司名称
            if (null != vocationinfo.getCompanyName()) {
                // 日志记录
                LOGGER.info("删除公司名称为：" + vocationinfo.getCompanyName());
            }
            // 删除职业
            return vocationinfoServiceMapper.deleteByPrimaryKey(vocationId);
        }
        return 0;
    }

    /**
     * 根据主键修改职业
     * 
     * @param request
     * @param vocationinfo
     * @return
     */
    @RequestMapping("/updatevocation")
    @ResponseBody
    public int updateVocation(HttpServletRequest request, @Valid Vocationinfo vocationinfo) {
        // 获得用户id
        Long customerId = (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);
        // 获取单个职业信息对象
        Vocationinfo vocation = vocationinfoServiceMapper.selectByPrimaryKey(vocationinfo.getVocationId());
        // 非空验证 公司名称
        if (null != vocation.getCompanyName()) {
            // 日志记录
            LOGGER.info("修改公司名称为：" + vocationinfo.getCompanyName());
        }
        if (vocation.getCustomerId().equals(customerId)) {
            // 修改职业
            return vocationinfoServiceMapper.updateByPrimaryKey(vocationinfo);
        }
        return 0;
    }

    /**
     * 根据主键查看单个职业信息
     * 
     * @param request
     * @param vocationId
     * @return
     */
    @RequestMapping("/selectvobyid")
    @ResponseBody
    public Vocationinfo selectVocationById(HttpServletRequest request, long vocationId) {
        // 获得用户id
        Long customerId = (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);
        // 获取单个职业信息对象
        Vocationinfo vocation = vocationinfoServiceMapper.selectByPrimaryKey(vocationId);
        // 判断是否越权
        if (vocation != null && vocation.getCustomerId().equals(customerId)) {
            // 日志记录
            LOGGER.info("获取公司名称为：" + vocation.getCompanyName());
        } else {
            vocation = null;
        }

        // 查看单个职业
        return vocation;
    }

    /**
     * 全部职业信息
     * 
     * @param request
     * @return
     */
    @RequestMapping("/selectall")
    @ResponseBody
    public List<Vocationinfo> selectAll(HttpServletRequest request) {
        // 获得用户id
        Long customerId = (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);
        // 全部职业信息
        return vocationinfoServiceMapper.selectAllVocation(customerId);
    }

    public VocationinfoServiceMapper getVocationinfoServiceMapper() {
        return vocationinfoServiceMapper;
    }

    @Resource(name = "vocationinfoServiceMapper")
    public void setVocationinfoServiceMapper(VocationinfoServiceMapper vocationinfoServiceMapper) {
        this.vocationinfoServiceMapper = vocationinfoServiceMapper;
    }

}
