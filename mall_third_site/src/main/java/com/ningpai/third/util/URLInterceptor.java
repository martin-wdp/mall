/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.third.util;

import com.ningpai.customer.bean.Customer;
import com.ningpai.fastdfs.bean.FastDFSInfo;
import com.ningpai.fastdfs.service.FastDFSInfoService;
import com.ningpai.util.MyLogger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * URL拦截器
 *
 * @author NINGPAI-zhangqiang
 * @since 2014年1月24日 上午11:04:35
 * @version 0.0.1
 */
public class URLInterceptor extends HandlerInterceptorAdapter {

    private static final String BASEURL = "baseUrl";
    private static final String HTML_DIV = "</div>";

    private static FastDFSInfoService fastDFSService;

    /**
     * 日志
     * */
    public static final MyLogger LOGGER = new MyLogger(URLInterceptor.class);

    /**
     * ERVICE-FastDFS设置信息接口
     * 
     * @return
     */
    public FastDFSInfoService getFastDFSService() {
        return fastDFSService;
    }

    /**
     * SET方法
     * 
     * @param fastDFSService
     */
    @Resource(name = "FastDFSInfoService")
    public void setFastDFSService(FastDFSInfoService fastDFSService) {
        URLInterceptor.fastDFSService = fastDFSService;
    }

    /**
     * 在请求处理前拦截URL 进行相应处理
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // System.out.print(request.getContextPath());
        String baseUrl = (String) request.getSession().getAttribute(BASEURL);
        if (baseUrl == null || "".equals(baseUrl)) {
            FastDFSInfo fastdfs = fastDFSService.getFastDFSInfoByCurr();
            if (fastdfs != null) {
                request.getSession().setAttribute(BASEURL, fastdfs.getResultPath());
            } else {
                request.getSession().setAttribute(BASEURL, "");
            }

        }
        /**
         * 设置直接放过的控制器 不必拦截
         */
        String[] noFilterURLs = getNoFilters();
        /**
         * 获取当前请求路径
         */
        String currentURL = request.getServletPath();
        boolean isFilter = true;
        // boolean hasAuty=false;
        /**
         * 判断请求路径是否需要拦截
         */
        for (String url : noFilterURLs) {
            if (currentURL.indexOf(url) != -1) {
                isFilter = false;
                break;
            }
        }
        StringBuilder builderHead = new StringBuilder();
        StringBuilder builderTip = new StringBuilder();
        StringBuilder builderFoot = new StringBuilder();
        builderHead.append("<html>");
        builderHead.append("<head>" + "<link rel='stylesheet' href='http://cdn.staticfile.org/twitter-bootstrap/3.3.1/css/bootstrap.min.css'>"
                + "<link rel='stylesheet' href='http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/css/bootstrap-datepicker.min.css'>" + "<link rel='stylesheet' href="
                + request.getContextPath() + "/css/style.css'>" + "<link rel='stylesheet' href=" + request.getContextPath() + "/css/base.min.css'>");
        builderHead.append("<script type='text/javascript' src='" + request.getContextPath() + "/js/jquery-1.9.1.js'></script>" + "<script type='text/javascript' src='"
                + request.getContextPath() + "/js/third.js'></script>");
        builderHead.append("<script type='text/javascript' src='http://cdn.staticfile.org/twitter-bootstrap/3.3.1/js/bootstrap.min.js'></script>");
        builderHead.append("</head>" + "<html> <body>");
        builderFoot.append("</body></html><script type=\"text/javascript\">");
        builderFoot.append("window.onload=function(){$('#errorpage').modal('show')}");
        // 本地运行 请求login.html 114||28请求去newthird
        // "var url = window.location.href;if(url.indexOf(\"localhost\")>0){$(\"#href\").attr(\"onclick\",\"javascript:window.location.href='login.html'\");}$('#errorpage').modal('show')}");
        builderFoot.append("</script>");
        HttpSession session = request.getSession();
        Customer customerId = (Customer) session.getAttribute("cust");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out;
        /**
         * 开始拦截
         */
        if (isFilter) {
            /**
             * 验证登陆
             */
            if (customerId == null) {
                try {
                    out = response.getWriter();
                    builderTip.append("<div class=\"modal fade\" id=\"errorpage\" role=\"dialog\" aria-hidden=\"true\">");
                    builderTip.append("     <div class=\"modal-dialog\">");
                    builderTip.append("         <div class=\"modal-content\">");
                    builderTip.append("             <div class=\"modal-header\">");
                    builderTip.append("                 <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">"
                            + "<span aria-hidden=\"true\">&times;</span>" + "</button>" + "<h4 class=\"modal-title\" style=\"font-family:微软雅黑,宋体\">登录失效</h4>" + HTML_DIV
                            + "<div  class=\"modal-body\" style=\"text-align: center;\">" + "对不起！您的登录状态已经失效，需要重新登录。" + HTML_DIV
                            + "<div class=\"modal-footer\" style=\"text-align: center;\">"
                            + "<button type=\"button\" class=\"btn btn-default\"  id=\"href\"  onclick=\"javascript:window.location.href='" + request.getContextPath()
                            + "/login.html'\" style=\"color:#fff;background-color:#428bca;border-color:#357ebd;\"  data-dismiss=\"modal\">确定</button>");
                    builderTip.append("             </div>");
                    builderTip.append("         </div>");
                    builderTip.append("     </div>");
                    builderTip.append(HTML_DIV);
                    out.print(builderHead.append(builderTip).append(builderFoot).toString());
                    out.close();
                } catch (IOException e) {
                    LOGGER.error(""+e);
                    out = null;
                }
                return false;
            } else {
                /*
                 * for (MenuVo menu : menuServiceInterface.getAllMenus(name)) {
                 * String url=menu.getUrl();
                 * if(menu.getUrl()!=null&&menu.getUrl().indexOf("?")!=-1){
                 * url=menu.getUrl().substring(0,menu.getUrl().indexOf("?")); }
                 * if(menu.getUrl()!=null&&currentURL.indexOf(url)!=-1){
                 * hasAuty=true; break; } } if(!hasAuty){ try { out =
                 * response.getWriter();
                 * builderTip.append("<div id='dialog-tip' title='操作提示'>"+
                 * "<span><br>对不起您暂时没有权限，请与管理员联系!</span>"+ HTML_DIV);
                 * out.print(builderHead
                 * .append(builderTip).append(builderFoot).toString()
                 * .replace("window.parent.location.href='login.htm'",
                 * "window.parent.history.go(-1)")); out.close(); } catch
                 * (IOException e) { } return false; }
                 */
                return true;
            }
        } else {
            /*
             * if (currentURL.indexOf(noFilterURLs[0]) != -1 && customerId ==
             * null) { try { out = response.getWriter(); builderTip.append(
             * "<div class=\"dialog s_dia dia3\" style=\"width:300px;height:150px;\">"
             * + "<div class=\"sd_tit clearfix\">"+
             * "<h3 class=\"fl\">操作提示</h3>"+
             * "<a class=\"sd_close fr\" href=\"javascript:;\" onclick=\"cls()\"></a>"
             * + HTML_DIV+ "<div class=\"pmt_wp\" style='height: 50px;' >"+
             * "<p class=\"tc f14\" style=\"line-height:70px;\">检测到你当前未登录,请先登录!</p>"
             * + HTML_DIV+ "<div class=\"tc mt20\">"+
             * "<a class=\"sop_btn\" href=\"javascript:location.href='login.html';\">确定</a>"
             * +
             * "<a class=\"sop_btn\" href=\"javascript:;\" onclick=\"cls()\">取消</a>"
             * + HTML_DIV+ HTML_DIV);
             * out.print(builderHead.append(builderTip).append
             * (builderFoot).toString()); out.close(); return false; } catch
             * (IOException e) {
             * 
             * } }else if (currentURL.indexOf(noFilterURLs[1])!=-1&& customerId
             * != null) { if(((Integer)
             * session.getAttribute("managerFlag")+"").equals("0")){ try { out =
             * response.getWriter(); builderTip.append(
             * "<div class=\"dialog s_dia dia3\" style=\"width:300px;height:150px;\">"
             * + "<div class=\"sd_tit clearfix\">"+
             * "<h3 class=\"fl\">操作提示</h3>"+
             * "<a class=\"sd_close fr\" href=\"javascript:;\" onclick=\"cls()\"></a>"
             * + HTML_DIV+ "<div class=\"pmt_wp\" style='height: 50px;' >"+
             * "<p class=\"tc f14\" style=\"line-height:70px;\">对不起您暂时没有权限，请与管理员联系!</p>"
             * + HTML_DIV+ "<div class=\"tc mt20\">"+
             * "<a class=\"sop_btn\" href=\"javascript:;\" onclick=\"cls()\">确定</a>"
             * + HTML_DIV+ HTML_DIV);
             * out.print(builderHead.append(builderTip).append
             * (builderFoot).toString()
             * .replace("window.parent.location.href='login.htm'",
             * "window.parent.history.go(-1)")); out.close(); } catch
             * (IOException e) { } return false; } }
             */

            return true;
        }
    }

    /**
     * 设置不用拦截的htm
     *
     * @return String []
     */
    private String[] getNoFilters() {
        return new String[] { "/thirdsubsendgoodsorder.htm","/checkthirdmobileexist", "/logisticssingle.htm","/checkUserNameExist.htm","/checkmobilecode.htm","/sendcodetovalidate.htm","/findCodeOne.htm","/checkUserNameExistForforgeting.htm","/findCodeTwo.htm","/findCodeThree.htm","/findCodeFour.htm","/updatePassword.htm","/checkpatchca.htm","/thirdRegisterCustomer.htm", "/jumplogin.htm", "/checkExistCustomerUsername.htm","/getBasicSetName.htm", "/customer/login.htm", "/checklogin.htm",
                "/updatelogisticssingle.htm", "/tothirdordersendgoods.htm", "/toAddThirdLogisticsSingle.htm", "/addThirdlogisticssingle.htm", "/toUpdateThirdLogisticsSingle.htm",
                "/patchca.htm", "/patchcaSession.htm", "/isloginthird.htm", "/writeCaptcha.htm", "/customer/register.htm", "/register.htm", "/checkCustomerUsername", "/thirdsendcode.htm",
                // 根据货品编号获得此货品参加的促销
                "/queryGoodsMarket.htm" };
    }

}
