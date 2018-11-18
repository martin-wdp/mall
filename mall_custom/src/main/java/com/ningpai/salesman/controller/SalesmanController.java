/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.salesman.controller;

import com.ningpai.comment.bean.ValueUtil;
import com.ningpai.common.safe.CSRFTokenManager;
import com.ningpai.customer.bean.Customer;
import com.ningpai.customer.bean.CustomerPoint;
import com.ningpai.customer.service.CustomerServiceMapper;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.other.bean.*;
import com.ningpai.other.util.CustomerConstantStr;
import com.ningpai.other.util.IPAddress;
import com.ningpai.other.util.SalesmanConstantStr;
import com.ningpai.salesman.bean.Salesman;
import com.ningpai.salesman.service.CustomerRelaSalesmanService;
import com.ningpai.salesman.service.SalesmanService;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import com.ningpai.util.UtilDate;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

//import com.ningpai.util.UploadUtil;

/**
 * 会员控制层
 *
 * @author NINGPAI-zhangqiang
 * @version 0.0.1
 * @since 2013年12月14日11:05:38
 */
@Controller
public class SalesmanController {

    // 记录日志对象
    private static final MyLogger LOGGER = new MyLogger(SalesmanController.class);

    private static final String PAGEBEAN = "pageBean";
    private static final String LOGGERINFO = ",业务员编号:";

    @Resource(name = "salesmanService")
    private SalesmanService salesmanService;

    @Resource(name = "customerRelaSalesmanService")
    private CustomerRelaSalesmanService customerRelaSalesmanService;

    /**
     * 初始化业务员列表
     *
     * @param pageBean
     *            {@link com.ningpai.util.PageBean}
     * @return
     */
    @RequestMapping("/initSalesman")
    public ModelAndView initSalesman(PageBean pageBean, Salesman salesman) {
        pageBean.setUrl(SalesmanConstantStr.INITSALESMAN);
        return new ModelAndView(SalesmanConstantStr.SALESMANLIST, PAGEBEAN, salesmanService.querySalesmanList(salesman, pageBean));
    }

    /**
     * 添加业务员
     *
     * @param salesman
     *            业务员全部信息
     * @return
     */
    @RequestMapping("/addSalesman")
    public ModelAndView addCustomer(@Valid Salesman salesman,BindingResult bindingResult,
                                    HttpServletRequest request) {
        // 保存新注册的业务员信息 非空验证 业务员姓名
        if(bindingResult.hasErrors()){
            return  new ModelAndView(new RedirectView(SalesmanConstantStr.INITSALESMAN));
        }
        if (null != salesman.getSalesmanName()) {
            LOGGER.info("保存名称为【" + salesman.getSalesmanName() + "】的业务员信息");
        }
        if(salesmanService.checkExistSalesmanName(salesman.getSalesmanName())>0){
            return   new ModelAndView(new RedirectView(SalesmanConstantStr.INITSALESMAN));
        }
        // 添加业务员
        salesman.setSalesmanId(null);
        salesmanService.addSalesman(salesman);
        LOGGER.debug(SalesmanConstantStr.ADDSALESMAN + salesman.getSalesmanName());
        OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(SalesmanConstantStr.NAME), SalesmanConstantStr.ADDSALESMAN,
                request.getSession().getAttribute(SalesmanConstantStr.OPERAPATH) + ",用户名:" + salesman.getSalesmanName());
        return new ModelAndView(new RedirectView(SalesmanConstantStr.INITSALESMAN));
    }

    /**
     * 新删除业务员
     *
     * @param request
     * @param salesmanId
     * @return ModelAndView
     */
    @RequestMapping("/deleteSalesman")
    public ModelAndView deleteSalesman(HttpServletRequest request, Long salesmanId) {
        if(customerRelaSalesmanService.getSalesmanIdBySalaId(salesmanId)>0){
            return new ModelAndView(new RedirectView(SalesmanConstantStr.INITSALESMAN));
        }
        // 执行删除
        salesmanService.deleteSalesman(salesmanId);
        // 记录日志
        OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(SalesmanConstantStr.NAME), SalesmanConstantStr.DELSALESMAN,
                request.getSession().getAttribute(SalesmanConstantStr.OPERAPATH) + SalesmanConstantStr.DELSALESMAN + LOGGERINFO + salesmanId);
        // 返回当前列表页面
        return new ModelAndView(new RedirectView(SalesmanConstantStr.INITSALESMAN));
    }

    /**
     * 新删除业务员（批量）
     *
     * @param request
     * @param salesmanId
     * @return ModelAndView
     */
    @RequestMapping("/deleteAllSalesman")
    public ModelAndView deleteAllSalesman(HttpServletRequest request, Long[] salesmanId) {
        //
    /*    List<Long> salaIdList = new ArrayList<Long>();
        for(Long sala:salaIdList){
            salaIdList.add(sala);
        }*/
        if (customerRelaSalesmanService.getSalesmanIdBySalaIds(salesmanId)>0) {
            return new ModelAndView(new RedirectView(SalesmanConstantStr.INITSALESMAN));
        }
        // 执行删除
         salesmanService.deleteAllSalesman(salesmanId);
        // 记录日志
        OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(SalesmanConstantStr.NAME), SalesmanConstantStr.DELSALESMAN,
                request.getSession().getAttribute(SalesmanConstantStr.OPERAPATH) + SalesmanConstantStr.DELSALESMAN + LOGGERINFO + salesmanId);
        // 返回当前列表页面
        return new ModelAndView(new RedirectView(SalesmanConstantStr.INITSALESMAN));
    }

    /**
     * 修改业务员信息
     *
     * @param request
     *            页面请求 {@link javax.servlet.http.HttpServletRequest} 响应
     *            {@link javax.servlet.http.HttpServletResponse}
     * @return
     */
    @RequestMapping("/updateSalesman")
    public ModelAndView updateSalesman(Salesman salesman, HttpServletRequest request) {
        // 修改会员信息 非空验证 业务员姓名
        if (null != salesman.getSalesmanName()) {
            LOGGER.info("修改名称为【" + salesman.getSalesmanName() + "】的业务员信息");
        }
        salesman.setSalesmanName(null);
        String pageNo = StringUtils.isNotEmpty(request.getParameter("pageNo")) ? request.getParameter("pageNo") : "1";
        if("1".equals(salesman.getIsEnabled())){
            //判断该业务员是否存在关联用户
            if(customerRelaSalesmanService.getSalesmanIdBySalaId(salesman.getSalesmanId())>0){
                return new ModelAndView(new RedirectView(SalesmanConstantStr.INITSALESMAN + ValueUtil.TOKENPARAM + CSRFTokenManager.getTokenFromRequest(request) + "&pageNo=" + pageNo));
            }
        }
        salesmanService.updateSalesman(salesman);
        OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(SalesmanConstantStr.NAME), SalesmanConstantStr.UPDATESALESMAN,
                request.getSession().getAttribute(SalesmanConstantStr.OPERAPATH) + ",用户名:" + salesman.getSalesmanName());
        return new ModelAndView(new RedirectView(SalesmanConstantStr.INITSALESMAN + ValueUtil.TOKENPARAM + CSRFTokenManager.getTokenFromRequest(request) + "&pageNo=" + pageNo));
    }

    /**
     * 根据会员编号查询业务员 实现弹出层展示数据
     *
     * @param salesmanId
     *            会员编号
     * @return
     */
    @RequestMapping("/querySalesman")
    public ModelAndView querySalesman(Long salesmanId, PageBean pageBean, String status) {
        // 结果集
        Map<String, Object> resultMap = new HashMap<String, Object>();
            resultMap.put("salesman", salesmanService.selectByPrimaryKey(salesmanId));
            return new ModelAndView("jsp/salesman/salesmandetail").addAllObjects(resultMap);

    }

    /**
     * 根据会员编号查询会员 实现修改数据展示
     *
     * @param salesmanId
     *            会员编号
     * @return 会员详细信息 CustomerAllInfo
     */
    @RequestMapping("/querySalesmanById")
    @ResponseBody
    public Salesman querySalesmanById(Long salesmanId) {
        Salesman salesman = salesmanService.querySalesmanInfo(salesmanId);

        return salesman;
    }

    /**
     * 检查业务员用户名是否存在
     *
     * @param salesmanName
     *            用户名
     * @param response
     * @return 1 存在 0 不存在
     * @throws java.io.IOException
     */
    @RequestMapping("/checkExistSalesmanName")
    @ResponseBody
    public int checkExistSalesmanName(String salesmanName, HttpServletResponse response) throws IOException {
        // 修改业务员信息 非空验证 会员姓名
        if (null != salesmanName) {
            // 日志记录
            LOGGER.info("检查名称为【" + salesmanName + "】的业务员是否存在");
        }
        return salesmanService.checkExistSalesmanName(salesmanName);
    }

    /**
     *  业务员修改启动或者删除判断
     *
     * @param salesmanId
     *            用户名
     * @param response
     * @return 1 存在 0 不存在
     * @throws java.io.IOException
     */
    @RequestMapping("/checkUpdateEnable")
    @ResponseBody
    public int checkUpdateEnable(Long salesmanId, HttpServletResponse response) throws IOException {
        // 修改业务员信息 非空验证 会ong员姓名
        if (null != salesmanId) {
            // 日志记录
            LOGGER.info("检查名称为【" + salesmanId + "】的业务员是否存在关联用户");
        }
        return customerRelaSalesmanService.getSalesmanIdBySalaId(salesmanId);
    }

    /**
     *  批量业务员修改启动或者删除判断
     *
     * @param salesmanIds
     *            用户名
     * @param response
     * @return 1 存在 0 不存在
     * @throws java.io.IOException
     */
    @RequestMapping("/checkUpdateEnableByIds")
    @ResponseBody
    public int checkUpdateEnableByIds(Long[] salesmanIds, HttpServletResponse response) throws IOException {
        //
        // 修改业务员信息 非空验证 会ong员姓名
        if (null != salesmanIds) {
            // 日志记录
            LOGGER.info("检查名称为【" + salesmanIds + "】的业务员是否存在关联用户");
        }
        return customerRelaSalesmanService.getSalesmanIdBySalaIds(salesmanIds);
    }

    /**
     * 按条件查询 会员
     *
     * @param salesman
     * @return
     * @throws java.text.ParseException
     */
    @RequestMapping("/queryBySalesman")
    public ModelAndView queryBySalesman(Salesman salesman, PageBean pageBean) throws UnsupportedEncodingException, ParseException {
        // 结果集
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            // 设置页面跳转路径
            pageBean.setUrl("queryBySalesman.htm");
            resultMap.put(PAGEBEAN, salesmanService.querySalesmanList(salesman, pageBean));
            resultMap.put("salesman", salesman);
            return new ModelAndView(SalesmanConstantStr.SALESMANLIST).addAllObjects(resultMap);
        } finally {
            resultMap = null;
        }
    }

    /**
     * 验证手机重复
     *
     * @return 0不存在 1存在
     */
    @RequestMapping("/checkMobileExist")
    @ResponseBody
    public Long checkMobileExist(HttpServletRequest request, String mobile) {
        if (null != mobile) {
            // 日志记录
            LOGGER.info("检查手机号为【" + mobile + "】的是否已被使用");
        }
        return salesmanService.checkMobileExist(mobile);
    }



}
