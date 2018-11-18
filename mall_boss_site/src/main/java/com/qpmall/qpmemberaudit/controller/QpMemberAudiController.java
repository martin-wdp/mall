/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.qpmall.qpmemberaudit.controller;

import com.ningpai.customer.dao.CustomerInfoMapper;
import com.ningpai.customer.dao.CustomerMapper;
import com.ningpai.customer.service.CustomerServiceMapper;
import com.ningpai.other.bean.CustomerAllInfo;
import com.ningpai.system.service.BasicSetService;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import com.qpmall.qpmemberaudit.bean.QpEnterpriseCertificationAllInfo;
import com.qpmall.qpmemberaudit.bean.QpEnterpriseCertificationInfo;
import com.qpmall.qpmemberaudit.service.QpEnterpriseCertificationInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 前台注册Controller
 *
 * @author luyong
 * @serialDate 2015-09-21
 */
@Controller
//@RequestMapping(value="/register")
public class QpMemberAudiController {


    @Resource(name = "basicSetService")
    private BasicSetService basicSetService;
    @Resource(name = "qpEnterpriseCertificationInfoService")
    private QpEnterpriseCertificationInfoService qpEnterpriseCertificationInfoService;
    @Resource(name = "customerServiceMapper")
    private CustomerServiceMapper customerServiceMapper;
    @Resource(name = "customerMapper")
    private CustomerMapper customerMapper;
    @Resource(name = "customerInfoMapper")
    private CustomerInfoMapper customerInfoMapper;

    private static final String STARTROWNUM = "startRowNum";
    private static final String PAGESIZE = "pageSize";
    private static final String PAGEBEAN = "pageBean";
    private static final String CHECKSTATUS = "checkStatus";

    /**
     * 记录日志对象
     */
    private static final MyLogger LOGGER = new MyLogger(QpMemberAudiController.class);

    /**
     * 查询所有的认证信息
     *
     * @return
     */
    @RequestMapping("/queryEnterpriseAllInfos")
    public ModelAndView queryallInfos(HttpServletRequest request,PageBean pageBean) {
        pageBean.setUrl("queryEnterpriseAllInfos.htm");
        List<QpEnterpriseCertificationAllInfo> qpEnterpriseCertificationInfos=null;
        Map<String, Integer> paramMap = null;
        int no = 0;
            // 设置总行数
       pageBean.setRows(qpEnterpriseCertificationInfoService.selectEsAndCustomerAllInfosCount(null));
        no = pageBean.getRows() % pageBean.getPageSize() == 0 ? pageBean.getRows() / pageBean.getPageSize() : (pageBean.getRows() / pageBean.getPageSize() + 1);
        no = no == 0 ? 1 : no;
            // 若页码超过最大页码 则显示最后一个
            if (pageBean.getPageNo() >= no) {
                pageBean.setPageNo(no);
                pageBean.setStartRowNum((no - 1) * pageBean.getPageSize());
            }
            // 设置查询条件
            paramMap = new HashMap<String, Integer>();
            paramMap.put(STARTROWNUM, pageBean.getStartRowNum());
            paramMap.put(PAGESIZE, pageBean.getPageSize());
            // 查询会员信息
            qpEnterpriseCertificationInfos=qpEnterpriseCertificationInfoService.selectEsAndCustomerAllInfos(paramMap);
/*        List<QpEnterpriseCertificationInfo> qpEnterpriseCertificationInfos =
                qpEnterpriseCertificationInfoService.selectAllInfos();*/
        String userName = "管理员：" + (String) request.getSession().getAttribute("name") + " ";
        LOGGER.info(userName + "获取企业认证信息列表");
        return new ModelAndView("jsp/customer/certificationInfo", "qpEnterpriseCertificationInfos", qpEnterpriseCertificationInfos)
                .addObject(PAGEBEAN,pageBean);
    }

    /**
     * 根据审核状态查询认证信息  0 带审核 1 已通过 2 已驳回
     *
     * @param checkStatus
     * @return
     */
    @RequestMapping("/queryEnterpriseAllInfoIsEn")
    public ModelAndView queryEnInfo(HttpServletRequest request, String checkStatus,PageBean pageBean) {
        pageBean.setUrl("queryEnterpriseAllInfos.htm");
        List<QpEnterpriseCertificationAllInfo> qpEnterpriseCertificationInfos=null;
        Map<String, Object> paramMap = null;
        int no = 0;
        // 设置总行数
        pageBean.setRows(qpEnterpriseCertificationInfoService.selectEsAndCustomerAllInfosCount(checkStatus));
        no = pageBean.getRows() % pageBean.getPageSize() == 0 ? pageBean.getRows() / pageBean.getPageSize() : (pageBean.getRows() / pageBean.getPageSize() + 1);
        no = no == 0 ? 1 : no;
        // 若页码超过最大页码 则显示最后一个
        if (pageBean.getPageNo() >= no) {
            pageBean.setPageNo(no);
            pageBean.setStartRowNum((no - 1) * pageBean.getPageSize());
        }
        // 设置查询条件
        paramMap = new HashMap<String, Object>();
        paramMap.put(STARTROWNUM, pageBean.getStartRowNum());
        paramMap.put(PAGESIZE, pageBean.getPageSize());
        /*QpEnterpriseCertificationInfo qpEnterpriseCertificationInfo = new QpEnterpriseCertificationInfo();
        qpEnterpriseCertificationInfo.setCheckStatus(checkStatus);*/
        if(checkStatus==null&&"".equals(checkStatus)){
            checkStatus="0";
        }
        paramMap.put(CHECKSTATUS,checkStatus);
        String userName = "管理员：" + (String) request.getSession().getAttribute("name") + " ";
        if ("0".equals(checkStatus)) {
            LOGGER.info(userName + "获取待认证的企业认证信息列表");
        } else if ("1".equals(checkStatus)) {
            LOGGER.info(userName + "获取已通过的企业认证信息列表");
        } else if ("2".equals(checkStatus)) {
            LOGGER.info(userName + "获取已驳回的企业认证信息列表");
        } else {
            LOGGER.info(userName + "输入参数：checkStatus=" + checkStatus + "，错误!");
        }
        return new ModelAndView("jsp/customer/certificationInfo", "qpEnterpriseCertificationInfos",
                qpEnterpriseCertificationInfoService.selectEsAndCustomerAllInfosByStatus(paramMap));
    }

    /**
     * 根据认证信息ID查询认证信息
     *
     * @param enterpriseId
     * @return
     */
    @RequestMapping("/queryEnterpriseAllInfoById")
    public ModelAndView queryEnterpriseAllInfoById(HttpServletRequest request, Long enterpriseId) {
        QpEnterpriseCertificationAllInfo qpEnterpriseCertificationInfo =
                qpEnterpriseCertificationInfoService.selectEsAndCustomerAllInfo(enterpriseId);
        String userName = "管理员：" + (String) request.getSession().getAttribute("name") + " ";
        LOGGER.info(userName + "获得个人详情！");
        return new ModelAndView("jsp/customer/certificationInfodet", "qpEnterpriseCertificationInfo",
                qpEnterpriseCertificationInfo);
    }

    /*@RequestMapping("/deleteEnterpriseAllInfoById")
    public ModelAndView deleteEnterpriseAllInfoById(HttpServletRequest request, Long enterpriseId){
        String userName = "管理员：" + (String) request.getSession().getAttribute("name") + " ";
        if(qpEnterpriseCertificationInfoService.deleteEnterpriseAllInfoById(enterpriseId)==1){
            LOGGER.info(userName+"删除认证信息成功");
        }else {
            LOGGER.info(userName+"删除认证信息失败");
        }
        return new ModelAndView(new RedirectView("queryEnterpriseAllInfos.htm"));
    }*/

    /**
     * 认证信息审核
     *
     * @param enterpriseId
     * @param checkStatus
     * @return
     */
    @RequestMapping("/enterpriseExamine")
    public ModelAndView enterpriseExamine(HttpServletRequest request, Long enterpriseId, String checkStatus,String auditFeedback) {
        QpEnterpriseCertificationInfo qpEnterpriseCertificationInfo =
                qpEnterpriseCertificationInfoService.selectInfoById(enterpriseId);
        Map<String, Object> resultParam = new HashMap<String, Object>();
        String userName = "管理员：" + (String) request.getSession().getAttribute("name") + " ";
        String resultInfo = "";
        if (qpEnterpriseCertificationInfo == null) {
            LOGGER.info(userName + "审核的认证信息不存在！");
            resultInfo = "认证信息不存在！";
            resultParam.put("resultInfo", resultInfo);
            return new ModelAndView("jsp/customer/certificationInforesult", "resultInfos", resultParam);
        }
        if (!"0".equals(qpEnterpriseCertificationInfo.getCheckStatus())) {
            LOGGER.info(userName + "审核的认证信息不是待审核状态！认证信息：" + qpEnterpriseCertificationInfo);
            resultInfo = "认证信息不是待审核状态！";
            resultParam.put("resultInfo", resultInfo);
            return new ModelAndView("jsp/customer/certificationInforesult", "resultInfos", resultParam);
        }
        if (!("1".equals(checkStatus) || "2".equals(checkStatus))) {
            LOGGER.info(userName + "审核的认证信息时候参数错误！错误参数：checkStatus=" + checkStatus);
            resultInfo = "参数错误！";
            resultParam.put("resultInfo", resultInfo);
            return new ModelAndView("jsp/customer/certificationInforesult", "resultInfos", resultParam);
        }
        String managerName= (String) request.getSession().getAttribute("name");
        Long loginUserId= (Long) request.getSession().getAttribute("loginUserId");
        //进行审核
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("enterpriseId", enterpriseId);
        param.put("checkStatus", checkStatus);
        param.put("auditTime",new Date(System.currentTimeMillis()));
        param.put("auditManager",managerName);
        param.put("auditFeedback","".equals(auditFeedback.trim())?"无":auditFeedback);
        param.put("auditManagerId",loginUserId) ;
        //修改会员信息表中的是否是企业字段
        CustomerAllInfo customerAllInfo =
                customerServiceMapper.selectByPrimaryKey(qpEnterpriseCertificationInfo.getCustomerid());
        if (customerAllInfo == null) {
            LOGGER.info(userName + "审核的信息对应的会员用户不存在！");
            resultParam.put("resultInfo", "审核的信息对应的会员用户不存在！");
            return new ModelAndView("jsp/customer/certificationInforesult", "resultInfos", resultParam);
        }
        if (customerAllInfo.getIsEnterprise()==null||"1".equals(customerAllInfo.getIsEnterprise())) {
            LOGGER.info(userName + "审核的信息对应的会员用户已经是企业用户！");
            resultParam.put("resultInfo", "审核的信息对应的会员用户已经是企业用户！");
            return new ModelAndView("jsp/customer/certificationInforesult", "resultInfos", resultParam);
        }
        if (qpEnterpriseCertificationInfoService.updateCheckStatus(param) == 1) {
            if ("1".equals(checkStatus)) {
                Map<String,Object> param1=new HashMap<String,Object>();
                param1.put("pointLevelId", 3);
                param1.put("pointLevelName", "企业会员");
                param1.put("customerId", qpEnterpriseCertificationInfo.getCustomerid());
                if (!((customerInfoMapper.upCusLevel(param1)==1)&&(customerMapper.updataIsEnterprise(customerAllInfo.getCustomerId()) == 1)))
                        {
                            param1.put("pointLevelId", 2);
                            param1.put("pointLevelName", "普通会员");
                            customerInfoMapper.upCusLevel(param1);
                            Map<String, Object> paramre = new HashMap<String, Object>();
                            paramre.put("enterpriseId", enterpriseId);
                            paramre.put("checkStatus", "0");
                            paramre.put("auditTime",null);
                            paramre.put("auditManager",null);
                            paramre.put("auditFeedback",null);
                            paramre.put("auditManagerId",null) ;
                            qpEnterpriseCertificationInfoService.updateCheckStatus(paramre);
                    LOGGER.info(userName + "修改用户的是否为企业失败");
                    resultParam.put("resultInfo", "修改用户的是否为企业失败");
                    return new ModelAndView("jsp/customer/certificationInforesult", "resultInfos", resultParam);
                }
                String res =  "审核通过" ;
                LOGGER.info(userName + "企业认证审核成功!审核结果：" + res);
                //resultInfo = res;
                resultParam.put("resultInfo", res);
                resultParam.put("checkStatus", checkStatus);
                return new ModelAndView("jsp/customer/certificationInforesult", "resultInfos", resultParam);
            }else {
                    String res =  "审核驳回";
                    resultParam.put("resultInfo", res);
                    resultParam.put("checkStatus", checkStatus);
                    return new ModelAndView("jsp/customer/certificationInforesult", "resultInfos", resultParam);
            }
        }
        LOGGER.info(userName + "企业认证审核失败!");
        resultInfo = "审核失败！";
        resultParam.put("resultInfo", resultInfo);
        return new ModelAndView("jsp/customer/certificationInforesult", "resultInfos", resultParam);
    }




}
