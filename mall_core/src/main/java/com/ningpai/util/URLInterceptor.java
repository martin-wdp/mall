/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.util;

import com.ningpai.manager.bean.valuebean.MenuVo;
import com.ningpai.manager.service.impl.MenuService;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * URL拦截器
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年1月24日 上午11:04:35
 * @version 0.0.1
 */
public class URLInterceptor extends HandlerInterceptorAdapter {

    private MenuService menuServiceInterface;
    private Boolean urlFlag = false;
    private int depth = 0;
    private String operaPath = "";

    private static final String MANAGERFLAG = "managerFlag";
    private static final String PEX1 = " </div>";
    private static final String PEX2 = "<div id='dialog-tip' title='操作提示'>";

    /**
     * 在请求处理前拦截URL 进行相应处理
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 设置直接放过的控制器 不必拦截
        String[] noFilterURLs = getNoFilters();
        // 获取当前请求路径
        String currentURL = request.getServletPath();
        boolean isFilter = true;
        boolean hasAuty = false;
        depth = 0;
        urlFlag = false;
        // 判断请求路径是否需要拦截
        for (String url : noFilterURLs) {
            if (currentURL.indexOf(url) != -1) {
                isFilter = false;
                break;
            }
        }
        StringBuilder builderHead = new StringBuilder();
        StringBuilder builderTip = new StringBuilder();
        StringBuilder builderFoot = new StringBuilder();
        String noLogin = " <div class='modal fade' id='loginFail'>";
        noLogin += "<div class='modal-dialog'>";
        noLogin += " <div class='modal-content'>";
        noLogin += " <div class='modal-header'>";
        noLogin += " <h4 class='modal-title'>登录失效</h4>";
        noLogin += "</div>";
        noLogin += " <div class='modal-body'>";
        noLogin += " <p>对不起！您的登录状态已经失效，需要重新登录。</p>";
        noLogin += PEX1;
        noLogin += " <div class='modal-footer'>";
        noLogin += " <button type='button' class='btn btn-primary' id='tologin'>确定</button>";
        noLogin += PEX1;
        noLogin += PEX1;
        noLogin += PEX1;
        noLogin += PEX1;
        builderHead.append("<head>" + "<link href='css/bootstrap.min.css' rel='stylesheet'>" + "<script src='js/jquery.min.js'></script>"
                + "<script src='js/bootstrap.min.js'></script>" + "</head>" + "<html> <body>");
        builderFoot.append("</body></html><script type=\"text/javascript\">   ");
        builderFoot.append("window.onload=function(){   $('#loginFail').modal('show');" + "$('#tologin').click(function(){top.location.href='login.htm'});" + "}");
        /*
         * builderFoot.append(
         * "   function tologin(){window.location.href='login.htm'}");
         */
        builderFoot.append("</script>");
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("name");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out;
        // 开始拦截
        if (isFilter) {
            // 验证登陆
            if (name == null) {
                try {
                    out = response.getWriter();

                    builderTip.append(noLogin);
                    out.print(builderHead.append(builderTip).append(builderFoot).toString());
                    out.close();
                } catch (IOException e) {
                    out = null;
                }
                return false;
            } else {
                List<MenuVo> menuVos = menuServiceInterface.getAllMenus(name);
                if (currentURL.indexOf("index.htm") != -1 || currentURL.indexOf("loadMenus.htm") != -1) {
                    return true;
                }
                // 强制只能查询自己的信息
                if (currentURL.indexOf("queryManagerById.htm") != -1
                        && ((Long) request.getSession().getAttribute("loginUserId")).toString().equals(
                                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getParameter("id"))
                        && "0".equals(((Integer) session.getAttribute(MANAGERFLAG)).toString())) {
                    return true;
                } else if (currentURL.indexOf("queryManagerById.htm") != -1 && "1".equals(((Integer) session.getAttribute(MANAGERFLAG)).toString())) {
                    return true;
                }
                for (MenuVo menu : menuVos) {
                    String url = menu.getUrl();
                    if (menu.getUrl() != null && menu.getUrl().indexOf("?") != -1) {
                        url = menu.getUrl().substring(0, menu.getUrl().indexOf("?"));
                    }
                    if (menu.getUrl() != null && currentURL.indexOf(url) != -1) {
                        if (!urlFlag) {
                            String path = findOperaPath(menuVos, menu, menu.getDesignation());
                            request.getSession().setAttribute("operaPath", path.substring(path.indexOf(">") + 1));
                            urlFlag = true;
                        }
                        hasAuty = true;
                        break;
                    }
                }
                if (!hasAuty) {
                    try {
                        out = response.getWriter();
                        builderTip.append(PEX2 + "<span><br>对不起您暂时没有权限，请与管理员联系!</span></div>");
                        out.print(builderHead.append(builderTip).append(builderFoot).toString().replace("top.location.href='login.htm'", "window.parent.parent.history.go(-1)"));
                        out.close();
                    } catch (IOException e) {
                        out = null;
                    }
                    return false;
                }
                return true;
            }
        } else {
            // 检测访问URL 是否为需要拦截的管理员权限
            if (checkManagerHtm(currentURL) && name == null) {
                try {
                    out = response.getWriter();
                    builderTip.append(PEX2 + "<span>您还未登录，请先登录!</span></div>");
                    out.print(builderHead.append(builderTip).append(builderFoot).toString());
                    out.close();
                    return false;
                } catch (IOException e) {
                    out = null;
                }
            } else if (checkManagerHtm(currentURL) && name != null && "0".equals(((Integer) session.getAttribute(MANAGERFLAG)).toString())) {
                try {
                    out = response.getWriter();
                    builderTip.append(PEX2 + "<span><br>对不起您暂时没有权限，请与管理员联系!</span>" + "</div>");
                    out.print(builderHead.append(builderTip).append(builderFoot).toString().replace("top.location.href='login.htm'", "window.parent.parent.history.go(-1)"));
                    out.close();
                } catch (IOException e) {
                    out = null;
                }
                return false;
            }
            return true;
        }
    }

    /**
     * 递归路径
     * 
     * @param menuVos
     * @param menuVo
     * @param path
     * @return
     */
    public String findOperaPath(List<MenuVo> menuVos, MenuVo menuVo, String path) {
        for (MenuVo mv : menuVos) {
            if (depth == 4) {
                break;
            }
            if (mv.getId() != null && menuVo.getParentId() != null && menuVo.getParentId().toString().equals(mv.getId().toString())) {
                operaPath = mv.getDesignation() + "-->" + path;
                depth++;
                findOperaPath(menuVos, mv, operaPath);
            }
        }
        return operaPath;
    }

    /**
     * 管理员权限验证
     * 
     * @param currnetURL
     * @return
     */
    public boolean checkManagerHtm(String currnetURL) {
        // 判断请求路径是否为管理员权限URL
        String[] urls = getManagerFilters();
        for (String url : urls) {
            if (currnetURL.indexOf(url) != -1) {
                return true;
            }
        }
        return false;
    }

    /**
     * 设置不用拦截的htm
     * 
     * @return String []
     */
    private String[] getManagerFilters() {
        return new String[] { "/initSetting.htm"
        // 以下勿动
        // "/index.htm",
        /*
         * "/loadMenus.htm", "/initSetting.htm", "/initManager.htm",
         * "/initAuthority.htm", "/jumpForPageView.htm",
         * "/queryAllAuthority.htm", "/queryAuthorByManagerId.htm",
         * "/queryAuthority.htm", "/deleteAuthority.htm",
         * "/updateAuthority.htm", "/queryAuthorityById.htm",
         * "/queryAuthorityByAId.htm", "/initAuthority.htm",
         * "/addAuthority.htm", "/queryManagerById.htm", "/queryByManager.htm",
         * "/addManager.htm", "/batchDelLack.htm", "/deleteManager.htm",
         * "/updateManager.htm", "/queryMenuVoList.htm", "/delPage.htm",
         * "/batchDelPage.htm", "/savePage.htm", "/updatePage.htm",
         * "/queryPageById.htm", "/checkDelPage.htm", "/queryAllMenuVo.htm",
         * "/queryAllMenu.htm", "/checkauthexist.htm", "/checkmanagerexist.htm",
         * // "/modifymanager.htm", "/selectEmp.htm",
         */

        };
    }

    /**
     * 设置不用拦截的htm
     * 
     * @return String []
     */
    private String[] getNoFilters() {
        return new String[] {
                // "/index.htm",
                "/login.htm", "/iflogin.htm", "/patchca.htm", "/patchcaSession.htm", "/tochromehelp.htm",
                // "/loadMenus.htm",
                "/getBasicset.htm", "/ajaxGetSysBasic.htm", "/verifyLoginServlet.htm", "/getBasicSetName.htm", "/updatesetOrderTime.htm", "/newgetLoginPatcha.htm"
                /*
                 * "/index.htm", "/login.htm", "/iflogin.htm", "/patchca.htm",
                 * "/patchcaSession.htm", "/loadMenus.htm",
                 * "/getBasicset.htm","/ajaxGetSysBasic.htm"
                 * ,"/verifyLoginServlet.htm", "/initSetting.htm", "/login.htm",
                 * "/iflogin.htm", "/patchca.htm", "/patchcaSession.htm",
                 * "/loadMenus.htm", "/getBasicset.htm",
                 *//** 以下勿动 **/
                /*
                 * "/initManager.htm", "/jumpForPageView.htm",
                 * "/queryAllAuthority.htm", "/queryAuthorByManagerId.htm",
                 * "/queryAuthority.htm", "/deleteAuthority.htm",
                 * "/updateAuthority.htm", "/queryAuthorityById.htm",
                 * "/queryAuthorityByAId.htm", "/initAuthority.htm",
                 * "/addAuthority.htm", // "/queryManagerById.htm",
                 * "/queryByManager.htm", "/addManager.htm",
                 * "/batchDelLack.htm", "/deleteManager.htm",
                 * "/updateManager.htm", "/queryMenuVoList.htm", "/delPage.htm",
                 * "/batchDelPage.htm", "/savePage.htm", "/updatePage.htm",
                 * "/queryPageById.htm", "/checkDelPage.htm",
                 * "/queryAllMenuVo.htm", "/queryAllMenu.htm",
                 * "/checkauthexist.htm", "/checkmanagerexist.htm",
                 * "/modifymanager.htm", "/getLoginPatcha.htm",
                 */
                /** 商圈 **/
                /*
                 * "/getBasicset.htm","findBusinessCirclesById.htm",
                 * "/updeBusinessCircleById.htm",
                 * "/delBusinessCircleById.htm","/findBusinessCircleByName.htm"
                 * ,"/addBusinessCircle.htm",
                 * "/findBusinessCircleByBusinessCircleId.htm", // 验证物流公司是否可删除
                 * "/checkExpressCount.htm", "/getUploadFileSet.htm",
                 * "/updateUploadFileSet.htm", // 项目路径设置
                 * "/getProjectUrlForSite.htm", "/getProjectUrlForMob.htm",
                 * "/setProjectUrl.htm", // Ajax获取图片列表
                 * "/ajaxQueryImageForChoose.htm", // 图片管理上传图片Ajax
                 * "/uploadFileOneForManage.htm",
                 * "batchDeleteImageManageAction.htm",
                 * 
                 * "/addCommReplay.htm", "/getAllProvince.htm",
                 * "/getAllCityByPid.htm", "/getAllDistrictByCid.htm",
                 * "/getAllStreetByDid.htm", "/checkUserKey.htm",
                 * "/modifiedUserKey.htm", "/checkExistCustomerUsername.htm",
                 * "/checkExistPointLevelName.htm",
                 * "/checkExistDefaultPointLevel.htm", "/getPointLevel.htm",
                 * "/initNotice.htm", "/sendcodecore.htm", "/getcodecore.htm",
                 * "/getmobile.htm", "/initversion.htm", "/allversion.htm",
                 * "/addversion.htm", "/showversion.htm", "/updateversion.htm",
                 * "/shownewversion.htm", "/initoperalog.htm",
                 * "/exportexcel.htm", "/deletelog.htm",
                 * 
                 * "/sendemailusersite.htm", // 验证前台晒单显示条数
                 * "/checkindexsharecount.htm", //查询未绑定的商圈
                 * "/findBusinessCircles.htm",
                 * 
                 * // 促销部分 "/expressdetail.htm",
                 * 
                 * // 用户反馈邮箱设置 "/showFeedBackEmail.htm",
                 * "/updateFeedBackEmail.htm",
                 * 
                 * // 临时 "/showUserAgre.htm", "/updateUserAgre.htm",
                 * "/ajaxGetSysBasic.htm", "/ajaxUpdateSysBasic.htm",
                 * 
                 * "/downImportExcel.htm", // 设置新移动版模板 "/setMobHomePage.htm", //
                 * 启用新移动版模板 "/openHomePage.htm", // 删除新移动版模板
                 * "/deleteHomePage.htm", "/addMF.htm", "/updateMF.htm",
                 * "/deleteMF.htm", "/saveAdv.htm", "/deleteAdv.htm",
                 * "/updateSort.htm", "/saveRollAdv.htm", "/deleteRollAdv.htm",
                 * "/saveText.htm", "/deleteText.htm", "/saveFullRoll.htm",
                 * "/deleteFullRoll.htm", "/clearAll.htm", "/searchMp3.htm",
                 * "/updateMobSiteBasicForShare.htm", "/ajaxGetRSV.htm",
                 * "/ajaxQueryMobCateBarForSite.htm", "/saveBlankbox.htm",
                 * "/deleteBlankbox.htm", // 查询移动版货品用于内连接
                 * "/ajaxQueryMobProductForInnerJoin.htm",
                 * "/queryMobProductForGoods.htm",
                 * "/ajaxQueryMobCateBarForChoose.htm", "/saveGoodsMob.htm",
                 * "/deleteGoodsMob.htm", "/addLine.htm", "/deleteLine.htm",
                 * "/getMobHomePage.htm", "/queryMobHomePage.htm",
                 * "/saveAllMod.htm", "/updateMobHomePageForShare.htm",
                 * 
                 * // 频道商品
                 * 
                 * "/querySalesChannelGoodsByPageBean.htm",
                 * "showChannelSalesGoods.htm", "updateSalesChannelGoods.htm",
                 * "createSalesChannelGoods.htm", "deleteSalesChannelGoods.htm",
                 * 
                 * 
                 * // 小组分类
                 *//**
                 * "/grouptypelist.htm", "/togrouplist.htm",
                 * "/grouptype.htm", "/checktypename.htm",
                 * "/querygrouptypebyid.htm", "/disablegroup.htm",
                 * "/recoverygroup.htm", "/querybygrouptype.htm",
                 * "/querygroupvobyid.htm", "/querygroupvo.htm",
                 * "/checkgrouplist.htm", "/passgroup.htm", "/refusegroup.htm",
                 * "/dissolvegroup.htm", "/activegrouplist.htm",
                 * "/activegroup.htm", "/commongroup.htm",
                 * "/querybycheckedgroup.htm", "/querybyactivegroup.htm",
                 * "/querybygroup.htm", "/updategroup.htm",
                 **/
                /*
                 * // 仓库管理 "/checkWareName.htm",
                 * "/queryAllManagerForWareHouse.htm", // 添加商品
                 * "/uploadImgSingle.htm", "/uploadProductImageSingle.htm",
                 * "/newUploadGood.htm", "/newUploadProduct.htm",
                 * "/newUploadSaveGoodsDesc.htm", // 商品列表 "/checkGoodsNo.htm",
                 * // 货品列表 "/queryProductForCoupon.htm", "/checkProductNo.htm",
                 * "/checkParam.htm", "/ownImportExcel.htm", // 商品关注
                 * "/checkDel.htm", // 商品分类 "/checkCateName.htm", // 商品规格
                 * "/checkSpecName.htm", // 商品品牌 "/checkBrandName.htm", // 商品标签
                 * "/checkTagName.htm",
                 * 
                 * "/showAllMobInfoByPage.htm", "/toshowMobSinglePage.htm",
                 * "/insertMobInfo.htm", "/updateMobInfo.htm",
                 * "/updateMobDelStatus.htm", "/showAllMobMarkInfo.htm",
                 * "/addMobMarkInfo.htm", "/updateMobMarkInfo.htm",
                 * "/deleteMobMarkInfo.htm", "/checkDelExist.htm",
                 * "/checkNameExist.htm", "/selectEmp.htm",
                 * "/searchSingleMp3.htm", "/selectprovincelist.htm",
                 * "/selectdistrictlistuserselect.htm",
                 * 
                 * "/selectcityuseselect.htm", "/savefreight.htm",
                 * 
                 * "/selectcityuseselect.htm",
                 * 
                 * //数据分析 "/showThirdInfo.htm", "/selectDeduByStoreId.htm",
                 * "/updateClassifyPay.htm", "/generatReport.htm",
                 * 
                 * //去添加模板页面 "/toAddFreightTemplate.htm","/addFreight.htm",
                 * 
                 * //刷新 "/refurbish.htm", "/exportGoodsCate.htm",
                 * "/exportGoodsCateTemp.htm", "/importGoodsCate.htm",
                 * "/exportGoodsBrand.htm", "/exportGoodsBrandTemp.htm",
                 * "/importGoodsBrand.htm", "/exportGoodsType.htm",
                 * "/exportComment.htm", "/exportCommentTemp.htm",
                 * "/importComment.htm", //分类频道页 "/toUpdateTempInfo.htm",
                 * "/showTempInfo.htm", "/queryChannelByPageBean.htm",
                 * 
                 * "/queryAllMenuByLogin.htm",
                 * 
                 * //"/loadMenus.htm", "/jumpForPageView.htm",
                 * "/queryAllAuthority.htm", "/queryAuthorByManagerId.htm",
                 * "/queryAuthority.htm", "/deleteAuthority.htm",
                 * "/updateAuthority.htm", "/queryAuthorityById.htm",
                 * "/queryAuthorityByAId.htm", "/initAuthority.htm",
                 * "/addAuthority.htm", "/queryManagerById.htm",
                 * "/queryByManager.htm", "/addManager.htm",
                 * "/batchDelLack.htm", "/deleteManager.htm",
                 * "/updateManager.htm", "/queryMenuVoList.htm", "/delPage.htm",
                 * "/batchDelPage.htm", "/savePage.htm", "/updatePage.htm",
                 * "/queryPageById.htm", "/checkDelPage.htm",
                 * "/queryAllMenuVo.htm", "/queryAllMenu.htm",
                 * "/checkauthexist.htm", "/checkmanagerexist.htm", //
                 * "/modifymanager.htm", "/selectEmp.htm",
                 * "/createAgencyChannelStoreyGoods.htm",
                 * "/updateAgencyChannelStoreyGoods.htm",
                 * "/deleteAgencyChannelStoreyGoods.htm",
                 * "/updateAgencyChannelStoreyGoods.htm", //处罚规则
                 * "/queryAllPenaltylist.htm"
                 * ,"/addPunishInfo.htm","/queryPunishInfoById.htm"
                 * ,"/updatePunishInfo.htm",
                 * "/deletePunishInfo.htm","/punishShop.htm"
                 * ,"/queryAllPointByCusId.htm"
                 * ,"/queryStoreBalanceByThirdId.htm"
                 * ,"/queryAllPunishedBusiness.htm",
                 * 
                 * //会员等级统计 "/tomemberLevel.htm","/upCusLevel.htm",
                 * "logisticssingle.htm", "/uploadImg.htm",
                 * "/verifyLoginServlet.htm", "/deleteAllAuthority.htm",
                 * "/deleteallmanager.htm"
                 */
                // 验证品牌是否重复
                , "selectByBrandName.htm" };
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    }

    public MenuService getMenuServiceInterface() {
        return menuServiceInterface;
    }

    @Resource(name = "menuServiceInterface")
    public void setMenuServiceInterface(MenuService menuServiceInterface) {
        this.menuServiceInterface = menuServiceInterface;
    }

}
