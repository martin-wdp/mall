/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.supplieraudit.controller;

import com.ningpai.businesscircle.service.BusinessCircleService;
import com.ningpai.customer.service.CustomerPunishService;
import com.ningpai.other.util.CustomerConstantStr;
import com.ningpai.supplieraudit.bean.StoreInfo;
import com.ningpai.supplieraudit.service.SupplierAuditService;
import com.ningpai.thirdaudit.bean.DeduBrokeage;
import com.ningpai.thirdaudit.service.AuditService;
import com.ningpai.thirdaudit.service.StoreCommonSerivce;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 供应商审核Controller
 * 
 * @author NINGPAI-qiaoy
 * @since 2015年3月23日 16:00
 * @version 0.0.1
 */
@Controller
public class SupplierAuditController {

    @Resource(name = "SupplierAuditService")
    private SupplierAuditService supplierAuditService;

    @Resource(name = "BusinessCircleService")
    private BusinessCircleService businessCircleService;

    @Resource(name = "auditService")
    private AuditService auditService;

    @Resource(name = "StoreCommonSerivce")
    private StoreCommonSerivce storeCommonSerivce;

    @Resource(name = "CustomerPunishService")
    private CustomerPunishService customerPunishService;

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(SupplierAuditController.class);

    /**
     * 供应商审核列表
     *
     * @param pageBean
     *            分页辅助类
     * @param storeInfo
     *            查询条件
     * @param showFlag
     *            显示类型
     * @param attr
     *            查询文字
     * @return
     */
    @RequestMapping("/supplierauditlist")
    public ModelAndView supplierauditlist(PageBean pageBean, StoreInfo storeInfo, String showFlag, String[] attr, String searchId, String searchText) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        ModelAndView mav = null;
        if (searchId != null) {
            if ("1".equals(searchId)) {
                storeInfo.setCompanyName(searchText);
            } else if ("2".equals(searchId)) {
                storeInfo.setStoreName(searchText);
            }
        }
        pageBean.setUrl("supplierauditlist.htm");
        try {
            storeInfo.setCheckStatus("0");
            resultMap.put("pageBean", supplierAuditService.selectSupplierAuditList(storeInfo, pageBean));
            resultMap.put("showFlag", showFlag);
            resultMap.put("attr", attr);
            resultMap.put("storeInfo", storeInfo);
            resultMap.put("searchId", searchId);
            resultMap.put("searchText", searchText);
            mav = new ModelAndView(CustomerConstantStr.SUPPLIERAUDITLIST);
            return mav.addAllObjects(resultMap);
        } finally {
            resultMap = null;
            mav = null;
        }
    }

    /**
     * 审核供应商
     * 
     * @param request
     * @param response
     * @return
     * @throws java.io.IOException
     */
    @RequestMapping("/updateSupplierStore")
    public ModelAndView updateSupplierStore(Long storeId, Long businessCircle, DeduBrokeage brokeage, String cellTime, String thirdIds, String payIds, String storeQi) {
        // 商家与商圈绑定
        if (businessCircle != null) {
            businessCircleService.bindBusinessCircle(storeId, businessCircle);
        }
        // 删除会员信息 输出信息 0 失败 1成功
        auditService.updateStore(thirdIds.split(","), brokeage, cellTime, payIds, storeQi);

        storeCommonSerivce.applyBrandToTrueBrand(thirdIds.split(","));

        return new ModelAndView(new RedirectView("supplierauditlist.htm?checkStatus=0"));
    }

    /**
     * 拒绝供应商提交信息
     * 
     * @param request
     * @param storeInfo
     *            商家信息
     * @return
     */
    @RequestMapping("/refuseSupplierStore")
    public ModelAndView refuseSupplierStore(HttpServletRequest request, com.ningpai.thirdaudit.bean.StoreInfo storeInfo) {
        // 非空验证 店铺名称
        if (null != storeInfo.getStoreName()) {
            // 日志记录
            LOGGER.info("拒绝供应商名称为：" + storeInfo.getStoreName() + "提交的信息！");
        }
        auditService.refuseStore(storeInfo);
        return new ModelAndView(new RedirectView("supplierauditlist.htm?CheckStatus=0"));
    }

    /**
     * 查询已审核供应商列表
     * 
     * @param pageBean
     *            分页辅助类
     * @param storeInfo
     *            查询条件
     * @param showFlag
     *            显示类型
     * @param attr
     *            查询文字
     * @return
     */
    @RequestMapping("/supplierauditedlist")
    public ModelAndView supplierauditedlist(PageBean pageBean, StoreInfo storeInfo, String showFlag, String[] attr, String searchId, String searchText) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        ModelAndView mav = null;
        if (searchId != null) {
            if ("1".equals(searchId)) {
                storeInfo.setCompanyName(searchText);
            } else if ("2".equals(searchId)) {
                storeInfo.setStoreName(searchText);
            }
        }
        pageBean.setUrl("supplierauditedlist.htm");
        try {
            storeInfo.setCheckStatus("1");
            resultMap.put("pageBean", supplierAuditService.selectSupplierAuditList(storeInfo, pageBean));
            resultMap.put("showFlag", showFlag);
            resultMap.put("attr", attr);
            resultMap.put("storeInfo", storeInfo);
            resultMap.put("searchId", searchId);
            resultMap.put("searchText", searchText);
            resultMap.put("punishList", customerPunishService.queryAllRules());
            mav = new ModelAndView(CustomerConstantStr.SUPPLIERAUDITEDLIST);
            return mav.addAllObjects(resultMap);
        } finally {
            resultMap = null;
            mav = null;
        }
    }
}
