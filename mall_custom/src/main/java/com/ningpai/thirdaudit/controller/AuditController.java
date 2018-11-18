/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.thirdaudit.controller;

import com.ningpai.businesscircle.service.BusinessCircleService;
import com.ningpai.comment.bean.ValueUtil;
import com.ningpai.customer.bean.Customer;
import com.ningpai.customer.bean.CustomerPunish;
import com.ningpai.customer.bean.PunishRecord;
import com.ningpai.customer.service.CustomerPunishService;
import com.ningpai.customer.service.CustomerServiceMapper;
import com.ningpai.customer.service.PunishRecordService;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.other.util.CustomerConstantStr;
import com.ningpai.system.service.DistrictService;
import com.ningpai.system.util.AddressUtil;
import com.ningpai.thirdaudit.bean.DeduBrokeage;
import com.ningpai.thirdaudit.bean.DeduBrokeageVo;
import com.ningpai.thirdaudit.bean.StoreInfo;
import com.ningpai.thirdaudit.service.AuditService;
import com.ningpai.thirdaudit.service.StoreCommonSerivce;
import com.ningpai.thirdaudit.service.StoreInfoService;
import com.ningpai.thirdaudit.service.ThirdStorePointService;
import com.ningpai.util.PageBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商家审核Controller
 *
 * @author NINGPAI-zhangqiang
 * @version 0.0.1
 * @since 2014年5月26日 下午4:26:43
 */
@Controller
public class AuditController {

    private static final String PAGEBEAN = "pageBean";
    private static final String SHOWFLAG = "showFlag";
    private static final String STOREINFO = "storeInfo";
    private static final String SEARCHID = "searchId";
    private static final String SEARCHTEXT = "searchText";
    private static final String HADAUDITLIST = "hadauditlist.htm";
    private static final String THIRDCATE = "thirdcate";

    @Resource(name = "customerServiceMapper")
    private CustomerServiceMapper customerServiceMapper;

    private AuditService auditService;
    @Resource(name = "BusinessCircleService")
    private BusinessCircleService businessCircleService;

    @Resource(name = "StoreCommonSerivce")
    private StoreCommonSerivce storeCommonSerivce;

    @Resource(name = "CustomerPunishService")
    private CustomerPunishService customerPunishService;

    @Resource(name = "PunishRecordService")
    private PunishRecordService punishRecordService;

    @Resource(name = "StoreService")
    private StoreInfoService storeInfoService;

    @Resource(name = "ThirdStorePointService")
    private ThirdStorePointService thirdStorePointService;

    @Resource(name = "DistrictService")
    private DistrictService districtService;

    /**
     * 查询审核/未审核列表
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
    @RequestMapping("/auditlist")
    public ModelAndView auditList(PageBean pageBean, StoreInfo storeInfo, String showFlag, String[] attr, String searchId, String searchText) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        ModelAndView mav;
        if (searchId != null) {
            if ("1".equals(searchId)) {
                storeInfo.setCompanyName(searchText);
            } else if ("2".equals(searchId)) {
                storeInfo.setStoreName(searchText);
            }
        }
        pageBean.setUrl("auditlist.htm");
        storeInfo.setCheckStatus("0");
        resultMap.put(PAGEBEAN, auditService.selectAuditList(storeInfo, pageBean));
        resultMap.put(SHOWFLAG, showFlag);
        resultMap.put("attr", attr);
        resultMap.put(STOREINFO, storeInfo);
        resultMap.put(SEARCHID, searchId);
        resultMap.put(SEARCHTEXT, searchText);
        mav = new ModelAndView(CustomerConstantStr.AUDITLIST);
        return mav.addAllObjects(resultMap);
    }

    /**
     * 查询审核/未审核列表
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
    @RequestMapping("/auditchecklist")
    public ModelAndView auditCheckList(PageBean pageBean, StoreInfo storeInfo, String showFlag, String[] attr, String searchId, String searchText) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        ModelAndView mav;
        if (searchId != null) {
            if ("1".equals(searchId)) {
                storeInfo.setCompanyName(searchText);
            } else if ("2".equals(searchId)) {
                storeInfo.setStoreName(searchText);
            }
        }
        pageBean.setUrl("auditchecklist.htm");
        storeInfo.setCheckStatus("1");
        resultMap.put(PAGEBEAN, auditService.selectAuditList(storeInfo, pageBean));
        resultMap.put(SHOWFLAG, showFlag);
        resultMap.put("attr", attr);
        resultMap.put(STOREINFO, storeInfo);
        resultMap.put(SEARCHID, searchId);
        resultMap.put(SEARCHTEXT, searchText);
        mav = new ModelAndView(CustomerConstantStr.AUDITLIST);
        return mav.addAllObjects(resultMap);
    }

    /**
     * 设置改店铺是都在店铺街显示以及排序
     *
     * @param request
     * @param setTore
     *            ：排布
     * @param isShow
     *            ：是否显示
     * @param storeId
     *            ：商铺ID author zhanghl
     * @return
     */
    @RequestMapping("/setstore")
    public ModelAndView setStore(HttpServletRequest request, Long setTore, String isShow, Long storeId) {
        auditService.setStore(setTore, isShow, storeId);
        return new ModelAndView(new RedirectView(request.getContextPath() + "/hadauditlist.htm"));
    }

    /**
     * 查询审核/已审核列表
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
    @RequestMapping("/hadauditlist")
    public ModelAndView auditListUn(PageBean pageBean, StoreInfo storeInfo, String showFlag, String[] attr, String searchId, String searchText) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        ModelAndView mav;
        if (searchId != null) {
            if ("1".equals(searchId)) {
                storeInfo.setCompanyName(searchText);
            } else if ("2".equals(searchId)) {
                storeInfo.setStoreName(searchText);
            }
        }
        pageBean.setUrl(HADAUDITLIST);
        storeInfo.setCheckStatus("1");
        resultMap.put(PAGEBEAN, auditService.selectAuditList(storeInfo, pageBean));
        resultMap.put(SHOWFLAG, showFlag);
        resultMap.put("attr", attr);
        resultMap.put(STOREINFO, storeInfo);
        resultMap.put(SEARCHID, searchId);
        resultMap.put(SEARCHTEXT, searchText);
        resultMap.put("punishList", customerPunishService.queryAllRules());
        mav = new ModelAndView(CustomerConstantStr.HADAUDITLIST);
        return mav.addAllObjects(resultMap);
    }

    /**
     * 审核商家
     *
     * @return
     * @throws IOException
     */
    @RequestMapping("/updatestore")
    public ModelAndView updateStore(Long storeId, Long businessCircle, DeduBrokeage brokeage, String cellTime, String thirdIds, String payIds, String storeQi) {
        // 1.根据storeID查询出会员id
        int cId = auditService.findcid(storeId);
        // 2.根据会员id修改会员状态为商家
        customerServiceMapper.updateStatus(cId);
        // 商家与商圈绑定
        if (businessCircle != null) {

            businessCircleService.bindBusinessCircle(storeId, businessCircle);
        }
        // 删除会员信息 输出信息 0 失败 1成功
        auditService.updateStore(thirdIds.split(","), brokeage, cellTime, payIds, storeQi);

        storeCommonSerivce.applyBrandToTrueBrand(thirdIds.split(","));
        // 修改自定义品牌审核状态
        storeCommonSerivce.updateAppStatus(storeId);
        return new ModelAndView(new RedirectView("auditlist.htm?checkStatus=0"));
    }

    /**
     * 新审核商家
     *
     * @param request
     * @param
     * @return
     * @throws IOException
     */
    @RequestMapping("/newupdatestore")
    @ResponseBody
    public Integer newupdateStore(HttpServletRequest request, Long storeId, Long businessCircle, DeduBrokeage brokeage, String cellTime, String thirdIds, String payIds,
            String storeQi) {
        String cellTimeNew = cellTime;
        Integer flag;
        cellTimeNew = cellTimeNew.replace(",", "|");
        // 商家与商圈绑定
        if (businessCircle != null) {
            businessCircleService.bindBusinessCircle(storeId, businessCircle);
        }
        // 审核店铺 输出信息 0 失败 1成功
        if (storeId != null) {
            flag = auditService.updateStore(storeId.toString().split(","), brokeage, cellTimeNew, payIds, storeQi);
        } else {
            flag = auditService.updateStore(thirdIds.split(","), brokeage, cellTimeNew, payIds, storeQi);
            storeCommonSerivce.applyBrandToTrueBrand(thirdIds.split(","));
        }
        StoreInfo storeInfos = storeCommonSerivce.selectModelPrice(storeId);
        customerServiceMapper.updateStatus(storeInfos.getCustomerid().intValue());
        // 操作日志
        OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), "审核店铺", (String) request.getSession().getAttribute(ValueUtil.OPERAPATH)
                + "审核店铺,用户名：" + (String) request.getSession().getAttribute(ValueUtil.NAME));
        return flag;
    }

    /**
     * 修改信息
     */
    @RequestMapping(value = "updateClassifyPay")
    public ModelAndView updatePay(PageBean pageBean, Long storeId, Long businessCircle, Long[] payIds, String billingCycle, String deduction, String brokerage,
            HttpServletRequest request) {

        // 商家与商圈绑定
        if (businessCircle != null) {
            businessCircleService.bindBusinessCircle(storeId, businessCircle);
        }
        auditService.updatePayMent(storeId, billingCycle);
        auditService.updatePayWay(storeId, payIds, deduction, brokerage);

        return new ModelAndView(new RedirectView("hadauditlist.htm?pageNo=" + pageBean.getPageNo()));
    }

    /**
     * 新修改信息
     */
    @RequestMapping("/newupdateclassifypay")
    public ModelAndView newUpdatePay(PageBean pageBean, Long storeId, Long businessCircle, Long[] payIds, String billingCycle, String deduction, String brokerage,
            HttpServletRequest request) {
        // 商家与商圈绑定
        if (businessCircle != null) {
            businessCircleService.bindBusinessCircle(storeId, businessCircle);
        }
        // 修改支付方式扣点
        auditService.updatePayMent(storeId, billingCycle);
        // 修改商家结算信息中的支付方式
        auditService.updatePayWay(storeId, payIds, deduction, brokerage);
        return null;
    }

    /**
     * 拒绝商家提交信息
     *
     * @param request
     * @param storeInfo
     *            商家信息
     * @return
     */
    @RequestMapping("/refusestore")
    public ModelAndView refuseStore(HttpServletRequest request, StoreInfo storeInfo) {
        auditService.refuseStore(storeInfo);
        return new ModelAndView(new RedirectView("auditlist.htm?CheckStatus=0"));
    }

    /**
     * 新店铺审核打回店铺
     *
     * @param request
     * @param thirdIds
     * @param storeInfo
     * @return
     */
    @RequestMapping("/newrefusestore")
    @ResponseBody
    public int newrefuseStore(HttpServletRequest request, String thirdIds, StoreInfo storeInfo) {
        int flag = 0;
        if (storeInfo.getStoreId() != null) {
            // 执行拒绝
            flag = auditService.refuseStore(storeInfo);
        } else {
            // 获取店铺Id封装成Sting数组
            String[] storeIds = thirdIds.split(",");

            // 遍历String数组
            for (String storeId : storeIds) {
                // string数组转换为Long型
                storeInfo.setStoreId(Long.valueOf(storeId));
                // 执行拒绝
                auditService.refuseStore(storeInfo);
                flag++;
            }
        }
        // 操作日志
        OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), "打回店铺", (String) request.getSession().getAttribute(ValueUtil.OPERAPATH)
                + "打回店铺,用户名：" + (String) request.getSession().getAttribute(ValueUtil.NAME));
        return flag;
    }

    /**
     * 商家信息设置
     *
     * @return
     */
    @RequestMapping("sellerinfo")
    public ModelAndView sellerinfo(HttpServletRequest request, Long storeId) {
        StoreInfo storeInfo=auditService.selectByCustomerId(storeId);
        String address="";
        if(storeInfo!=null&&storeInfo.getBussAddrId()!=null&&storeInfo.getBussAddrId().split(",").length==3){
            AddressUtil addressUtil= districtService.queryAddressNameByDistrictId(Long.valueOf(storeInfo.getBussAddrId().split(",")[2]));
            address=addressUtil.getProvinceName()+"-"+addressUtil.getCityName()+"-"+addressUtil.getDistrictName();
        }
        return new ModelAndView(CustomerConstantStr.SELLERINFO).addObject("info", storeInfo).addObject("address",address)
                .addObject(THIRDCATE, storeCommonSerivce.selectThirdCate(storeId)).addObject("thirdbrand", storeCommonSerivce.selectThirdBrand(storeId))
                .addObject("appbrand", storeCommonSerivce.selectApplyBrandbyStoreId(storeId));
    }

    /**
     * 商家信息设置
     *
     * @return
     */
    @RequestMapping("updatesellerinfo")
    public ModelAndView updatesellerinfo(HttpServletRequest request, Long storeId) {
        return new ModelAndView(CustomerConstantStr.UPDATESELLERINFO).addObject(THIRDCATE, storeCommonSerivce.selectThirdCate(storeId)).addObject("storeId", storeId);
    }

    /**
     * 新商家信息设置
     *
     * @return
     */
    @RequestMapping("newupdatesellerinfo")
    public ModelAndView newUpdatesellerinfo(HttpServletRequest request, PageBean pb, Long storeId) {
        pb.setPageSize(6);
        return new ModelAndView(CustomerConstantStr.UPDATESELLERINFO).addObject(PAGEBEAN, storeCommonSerivce.newselectThirdCate(storeId, pb)).addObject("storeId", storeId)
                .addObject(THIRDCATE, storeCommonSerivce.selectThirdCate(storeId));
    }

    /**
     * 修改签约分类
     *
     * @return
     */
    @RequestMapping("updatethridcate")
    public ModelAndView updateThridCate(HttpServletRequest request, Long storeId, Long[] thirdCateId) {
        storeCommonSerivce.updateThridCate(storeId, thirdCateId);
        return new ModelAndView(new RedirectView("updatesellerinfo.htm?storeId=" + storeId));
    }

    /**
     * 新修改签约分类
     *
     * @return
     */
    @RequestMapping("newupdatethridcate")
    public ModelAndView newUpdateThridCate(HttpServletRequest request, Long storeId, Long[] thirdCateId) {
        storeCommonSerivce.updateThridCate(storeId, thirdCateId);
        return new ModelAndView(new RedirectView("newupdatesellerinfo.htm?storeId=" + storeId));
    }

    /**
     * 修改签约分类
     *
     * @return
     */
    @RequestMapping("deletesellerinfocate")
    public ModelAndView deleteSellerinfoCate(HttpServletRequest request, Long cateId, Long thirdId) {
        storeCommonSerivce.deleteSellerinfoCate(thirdId, cateId);
        return new ModelAndView(new RedirectView("updatesellerinfo.htm?storeId=" + thirdId));
    }

    /**
     * 修改签约分类
     *
     * @return
     */
    @RequestMapping("newdeletesellerinfocate")
    public ModelAndView newdeleteSellerinfoCate(HttpServletRequest request, Long cateId, Long thirdId) {
        storeCommonSerivce.deleteSellerinfoCate(thirdId, cateId);
        // return new ModelAndView(new
        // RedirectView("updatesellerinfo.htm?storeId=" + thirdId));
        return null;
    }

    /**
     * 查询数据
     *
     * @param storeId
     */
    @RequestMapping("selectDeduByStoreId")
    @ResponseBody
    public List<DeduBrokeageVo> selectDeduByStoreId(Long storeId) {
        return auditService.selectBrokeageByStoreId(storeId);
    }

    /**
     * 处罚条例的具体实现
     *
     * @param request
     * @param punishRecord
     * @param customerPunish
     * @param closeSstime
     * @param closeEetime
     * @param limitSstime
     * @param limitEetime
     * @return
     */
    @RequestMapping("/punishShop")
    public ModelAndView punishShop(HttpServletRequest request, PunishRecord punishRecord, CustomerPunish customerPunish, String closeSstime, String closeEetime,
            String limitSstime, String limitEetime) {
        punishRecord.setOperatorId((Long) request.getSession().getAttribute("loginUserId"));

        punishRecord.setOperatorName(String.valueOf(request.getSession().getAttribute("name")));
        punishRecord.setPunishId(customerPunishService.queryIdByRule(customerPunish).getId());

        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            if (!"".equals(closeSstime) && !"".equals(closeEetime)) {
                punishRecord.setCloseStime(formatDate.parse(closeSstime));
                punishRecord.setCloseEtime(formatDate.parse(closeEetime));
            }

            if (!"".equals(limitSstime) && !"".equals(limitEetime)) {
                punishRecord.setLimitStime(formatDate.parse(limitSstime));
                punishRecord.setLimitEtime(formatDate.parse(limitEetime));
            }
        } catch (ParseException e) {
            Customer cust = (Customer) request.getSession().getAttribute("cust");
            OperaLogUtil.addOperaException(cust.getCustomerUsername(), e, request);
        }

        // 扣积分
        if ("1".equals(customerPunish.getRule())) {
            thirdStorePointService.reduceStorePoint(punishRecord, punishRecord.getThirdId(), punishRecord.getPunishReason(), punishRecord.getReducePoint());

        } else if ("2".equals(customerPunish.getRule())) {
            thirdStorePointService.reduceStoreMoney(punishRecord);

        } else {
            punishRecordService.addPunishRecord(punishRecord);
        }
        return new ModelAndView(new RedirectView(HADAUDITLIST));
    }

    /**
     * 修改商铺期限
     *
     * @param endTime
     * @param goodsBelo
     * @return
     */
    @RequestMapping("updateStoreValidTime")
    public ModelAndView updateStoreValidTime(String endTime, Long goodsBelo) {
        int count = auditService.updateStoreValidTime(endTime, goodsBelo);
        if (count > 0) {
            return new ModelAndView(new RedirectView(HADAUDITLIST));
        } else {
            return null;
        }
    }

    /**
     * 查询被处罚商家当前总积分
     *
     * @param thirdId
     * @return
     */
    @RequestMapping("/queryAllPointByCusId")
    @ResponseBody
    public StoreInfo queryAllPointByCusId(long thirdId) {

        return storeInfoService.queryStorePointByThirdId(thirdId);
    }

    /**
     * 查询商家余额
     *
     * @param thirdId
     */
    @RequestMapping("/queryStoreBalanceByThirdId")
    @ResponseBody
    public StoreInfo queryStoreBalanceByThirdId(long thirdId) {

        return storeInfoService.queryStoreBalanceByThirdId(thirdId);
    }

    public AuditService getAuditService() {
        return auditService;
    }

    @Resource(name = "auditService")
    public void setAuditService(AuditService auditService) {
        this.auditService = auditService;
    }

}
