/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.common.safe;

import com.ningpai.common.util.ConstantUtil;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * AOP验证token
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年8月12日 下午5:42:04
 * @version 1.0
 */
@Component
@Aspect
public class TokenAOPUtil {

    private static final String ORDERSENDGOODS = "/ordersendgoods.htm";
    private static final String QUERYTEMPADVERBYPAGEBEAN = "/queryTempAdverByPageBean.htm";

    String[] urls = new String[] {
            "/newgetLoginPatcha.htm",
            "/updateTempInfo.htm",
            "/uploadChannelShowImg.htm",
            "/createChannelStoreyForChannel.htm",
            "/querypagetagbypagebeanajax.htm",
            "/queryChannelStoreyByPageBean.htm",
            "/updateChannelAndTemp.htm",
            "/createChannelBar.htm",
            "/queryChannelBarByPageBean.htm",
            "/showChannelSet.htm",
            "/queryChannelByChannelId.htm",
            "/updateChannel.htm",
            "/deleteChannel.htm",
            "/queryChannelByPageBean.htm",
            "/createChannel.htm",
            "/backorderlog.htm",
            "/informList.htm",
            "/informUpdateAction.htm",
            "/informUpdate.htm",
            "/informFind.htm",
            "/login.htm",
            "/iflogin.htm",
            "/index.htm",
            "/patchca.htm",
            "/patchcaSession.htm",
            "/loadMenus.htm",
            "/initSetting.htm",
            "/orderlist.htm",
            "/orderpickinglist.htm",
            "/queryyorderlist.htm",
            ORDERSENDGOODS,
            "/queryyorderlist.htm",
            "/queryysendgoodsorderlist.htm",
            "/queryydeorderlist.htm",
            ORDERSENDGOODS,
            ORDERSENDGOODS,
            "/couponlist.htm",
            ORDERSENDGOODS,
            "/searchcodexlist.htm",
            "/marketgoodslist.htm",
            "/queryprelist.htm",
            "/orderdeliverylist.htm",
            "/deliveryPrintView.htm",
            "/ordervicelistbyrush.htm",
            "/ordervicelistbygroupon.htm",
            "/toaddcoupon.htm",
            "/toaddgoodsmarketing.htm",
            "/queryrushtimelist.htm",
            "/initversion.htm",
            "/selectEmp.htm",
            "/printorderpickinglist.htm",
            // 应用市场
            "/downloadapp.htm",
            "/appdetail.htm",
            "/addAppBoss.htm",
            "/appBoss.htm",
            "/queryImageManageByPbAndCidForChoose.htm",
            "/checkAppName.htm",
            "/delAppBoss.htm",
            "/selectAppBoss.htm",
            "/updateAppBoss.htm",
            "/upAppBoss.htm",
            "/downAppBoss.htm",
            "/applist.htm",
            "/appclient.htm",
            "/downloadapp.htm",
            "/installapp.htm",
            "/downloadappfromserver.htm",
            "/queryAllInstallApps.htm",
            "/uninstallApp.htm",
            "/selectMenuId.htm",
            "/getAppMarketKey.htm",
            "/startapp.htm",
            "/stopapp.htm",
            "/uninstallapp.htm",
            "/appAuthList.htm",
            "/appServerList.htm",
            // 商品部分
            "/newUploadGoods.htm",
            "/findAllGoods.htm",
            "/findallgoodsatte.htm",
            "/findAllCate.htm",
            "/findAllType.htm",
            "/findAllSpec.htm",
            "/findAllBrand.htm",
            "/findAllTag.htm",
            "/findAllGroup.htm",
            "/queryWareHouseByPageBean.htm",
            "/uploadImgSingle.htm",
            "/uploadProductImageSingle.htm",
            "/checkProductNo.htm",
            "/checkWareName.htm",
            "/checkTagName.htm",
            "/checkBrandName.htm",
            "/checkParam.htm",
            "/checkGoodsNo.htm",
            "/queryAllImport.htm",
            "/saveBrand.htm",
            "/updateBrand.htm",
            "/warninggoods.htm",
            "/selectgoodbyId.htm",
            "/updatestock.htm",
            "/querybyname.htm",
            "/showwarninggoods.htm",
            "/auditaction.htm",
            "/changeAuditAction.htm",
            "/goodsAudit.htm",
            "/auditByGoodsId.htm",
            "/refuseAuditByGoodsId.htm",
            "/queryAuditByGoodsId.htm",
            "/updateStoreValidTime.htm",
            "/savebackorderdetail.htm",
            "/newsavebackorderdetail.htm",
            "/queryProductAutoStyleByProductId.htm",//查询货品的适配车型
            "/updateProductAutoStyle.htm",//更新货品的适配车型
            // 系统部分
            "/basicset.htm",
            "/initSeoConf.htm",
            "/toAdvancedSet.htm",
            "/businessList.htm",
            "/auditProductAction.htm",
            "/refuseAuditProductAction.htm",
            "/getCouponNoByCouponId.htm",
            "/openGetCouponPage.htm",

            "/getPageRows.htm",
            "/getLoginPatcha.htm",
            "/getFastDFSPath.htm",
            "/getUploadFileSet.htm",

            "/emailset.htm",
            "/authset.htm",
            "/messageset.htm",
            "/payset.htm",
            "/getBasicSetName.htm",

            "/readOnLineService.htm",
            "/initStatisticsCode.htm",
            "/shopkuaishang.htm",

            "/tohelpcate.htm",
            "/findcenter.htm",
            "/selectServiceSupportList.htm",

            "/initUpyunConf.htm",
            "/stockwarningsel.htm",
            "/updatesw.htm",

            "/initExpressConf.htm",
            "/initLogisticsCompany.htm",
            "/findAllProvince.htm",
            "/pointset.htm",
            "/queryWebCertByPageBean.htm",
            "/initSysErrorPage.htm",
            "/initAuthCheckConf.htm",
            "/initShopConf.htm",
            "/initSysDictionary.htm",
            "/initAreaPackage.htm",
            "/friendlink.htm",
            "/initCurrencyConf.htm",
            "/initPricePrecisionCof.htm",
            // 资讯部分
            "/jumpForInfoTypeView.htm",

            "/queryInfoVoList.htm",
            "/queryInfoUserDefined.htm",
            "/toUpdateTempInfo.htm",

            "/queryInfoOnePagesByPageBean.htm",
            "/queryInfoOPTag.htm",
            "/showTempInfo.htm",

            "/queryImageClassifyByPb.htm",
            "/queryImageManageByPbAndCid.htm",

            // 模板部分
            "/queryTempByType.htm",
            "/queryInforSubjectByPageBean.htm",
            "/deleteTempBarAjxs.htm",
            // 待定
            "/queryManagerById.htm",
            "/shownewversion.htm",
            "/queryStockWarnCount.htm",

            "/getProjectUrlForSite.htm",
            "/getProjectUrlForMob.htm",
            "/setProjectUrl.htm",
            // 查找前台页面地址
            "/getBasicset.htm",
            "/showtempbarbyid.htm",
            // 后台Logo设置
            "/ajaxGetSysBasic.htm",
            "/ajaxUpdateSysBasic.htm",

            "/selectBusinessCircles.htm",

            "/ajaxQueryImageForChoose.htm",
            // 短信接口
            "/queryMessageSetByPb.htm",
            "/showMessageSet.htm",
            "/changeMessageSetUserdStatus.htm",

            // 图片管理上传图片Ajax
            "/uploadFileOneForManage.htm",

            // 批量删除图片信息
            "/batchDeleteImageManageAction.htm",

            // 会员
            "/initCustomer.htm",
            "/initPointLevel.htm",
            "/querycustomerconsume.htm",
            "/querycustpointbycustpoint.htm",
            "/initComment.htm",
            "/initrecharge.htm",
            "/initConsult.htm",
            "/initInfoSetting.htm",
            "/initsharelist.htm",
            "/queryByCustomerAllInfo.htm",
            "/queryByComment.htm",
            "/queryByConsult.htm",
            "/auditlist.htm",
            "/querygrandbrandlists.htm",
            "/ordercomplainback.htm",
            "/complainhad.htm",
            "/initNotice.htm",
            "/shownewversion.htm",
            "/queryStockWarnCount.htm",
            "/initwxmenu.htm",
            "/initwxgroup.htm",
            "/sharedetailAjaxNew.htm",
            "/addShareReplay.htm",
            // 移动版
            "/queryMobCateBarByPb.htm",
            "/setMobMain.htm",
            "/queryMobAdverByStoreyIdAndPb.htm",
            "/queryMobStoreyByPageBean.htm",

            // 管理员权限
            "/initManager.htm",
            "/initAuthority.htm",
            "/jumpForPageView.htm",
            "/allversion.htm",
            "/initoperalog.htm",
            "/queryMenuVoList.htm",
            "/orderdetail.htm",
            "/orderdetailajax.htm",

            // 自提点
            "/queryDeliveryPointByPb.htm",
            // 移动版站点设置
            "/showMobSiteBasic.htm",
            // 支付方式
            "/queryPaymentByPb.htm",
            "/ajaxGetPayment.htm",
            "/updateOpenStatus.htm",
            "/ajaxCheckOpenCount.htm",
            "/ajaxCheckDelete.htm",
            // 设置新移动版模板
            "/setMobHomePage.htm",
            // 启用新移动版模板
            "/openHomePage.htm",
            // 删除新移动版模板
            "/deleteHomePage.htm",
            "/updateSort.htm",
            "/saveText.htm",
            "/deleteText.htm",
            "/saveFullRoll.htm",
            "/deleteFullRoll.htm",
            "/clearAll.htm",
            "/searchMp3.htm",
            "/updateMobSiteBasicForShare.htm",
            "/ajaxGetRSV.htm",
            "/ajaxQueryMobCateBarForSite.htm",
            "/saveBlankbox.htm",
            "/deleteBlankbox.htm",
            // 查询移动版货品用于内连接
            "/ajaxQueryMobProductForInnerJoin.htm",
            "/queryMobProductForGoods.htm",
            "/ajaxQueryMobCateBarForChoose.htm",
            "/saveGoodsMob.htm",
            "/deleteGoodsMob.htm",
            "/addLine.htm",
            "/deleteLine.htm",
            "/getMobHomePage.htm",
            "/queryMobHomePage.htm",
            "/saveAllMod.htm",
            "/updateMobHomePageForShare.htm",

            // 移动版单页列表
            "/showAllMobInfoByPage.htm",
            "/toshowMobSinglePage.htm",
            "/insertMobInfo.htm",
            "/updateMobInfo.htm",
            "/updateMobDelStatus.htm",
            "/showAllMobMarkInfo.htm",
            "/addMobMarkInfo.htm",
            "/updateMobMarkInfo.htm",
            "/deleteMobMarkInfo.htm",
            "/checkDelExist.htm",
            "/checkNameExist.htm",
            "/searchSingleMp3.htm",

            // 小组
            "/checkgrouplist.htm",
            "/togroup.htm",
            "/querygroupvobyid.htm",
            "/activegrouplist.htm",
            "/querygroupvo.htm",
            "/passgroup.htm",
            "/refusegroup.htm",
            "/dissolvegroup.htm",
            "/activegroup.htm",
            "/commongroup.htm",
            "/hotgroup.htm",
            "/cancelhotgroup.htm",
            "/recommendgroup.htm",
            "/cancelrecommendgroup.htm",
            "/querybycheckedgroup.htm",
            "/querybygroup.htm",
            "/querybyactivegroup.htm",
            "/updategroup.htm",
            "/grouptype.htm",
            "/addgrouptype.htm",
            "/updategrouptype.htm",
            "/querygrouptypebyid.htm",
            "/deletebyid.htm",
            "/disablegroup.htm",
            "/recoverygroup.htm",
            "/checktypename.htm",
            "/querybygrouptype.htm",
            "/labellist.htm",
            "/addlabel.htm",
            "/checklabelname.htm",
            "/modifylabel.htm",
            "/unuselabel.htm",
            "/recoverylabel.htm",
            "/querygrouplabelbyid.htm",
            "/publictopic.htm",
            "/privatetopic.htm",
            "/updategrouptopic.htm",
            "/savegrouptopic.htm",
            "/deletegrouptopic.htm",
            "/toindex.htm",
            "/publictopicdetail.htm",
            "/privatetopicdetail.htm",
            "/privatetopicdetailNew.htm",
            "/useexperience.htm",
            "/querygroupvoNew.htm",

            // 后台商家审核查看详情
            "/sellerinfo.htm",
            // 修改后台商家
            "/updatesellerinfo.htm",
            // 删除后台商家
            "/delectstore.htm",
            // 自定义品牌列表
            "/queryapplybrandlist.htm",
            "/updateapply.htm",
            // 数据分析
            "/showThirdInfo.htm",
            "/saleCount.htm",
            "/saleMoney.htm",
            "/goodsProductSalesRank.htm",
            "/customerRank.htm",

            // 后台签约分类
            "/queryallgoodcate.htm",
            "/updatethridcate.htm",
            "/deletesellerinfocate.htm",
            "/querySonCateByCatId.htm",
            "/queryCateById.htm",
            "/queryBrandById.htm",
            "/freighttemplatelist.htm",
            "/newajaxgetpagefoot.htm",

            // 后台签约分类
            "/queryallgoodcate.htm",
            "/updatethridcate.htm",
            "/deletesellerinfocate.htm",
            "/querySonCateByCatId.htm",
            "/queryCateById.htm",
            "/queryBrandById.htm",
            // 退单
            "/backorderlist.htm",
            "/modifyBackOrder.htm",
            "/backorderdetail.htm",
            "/setBackOrder.htm",
            "/backorderlististhird.htm",
            "/ordervicelistbygrouponisthird.htm",
            "/ordervicelistbyrushisthird.htm",
            "/selectprovincelist.htm",
            "/selectdistrictlistuserselect.htm",
            "/selectcityuseselect.htm",
            // B2B
            "/findAllAgencyGoodsProduct.htm", "/updateCustomerAmount.htm", "/selectAgencyGoodsByLimiteId.htm", "/queryAgencyGoodsByParam.htm", "/updateAgencyGoods.htm",
            "/queryGoodsProductByCatesAndBrands.htm", "/updateGoodsToB2B.htm", "/agencyList.htm", "/addCustomerAgency.htm", "/findAllCustomer.htm", "/updateCustomerToAgency.htm",
            "/agencyTransaction.htm", "/agencyAddress.htm", "/queryAgencyTempByType.htm", "/agencyTempSet.htm", "/showAgencyTempInfo.htm", "/toUpdateAgencyTempInfo.htm",
            "/updateAgencyTempInfo.htm", "/queryAgencyTempStoreyByPageBean.htm", "/queryAgencyChannelStoreyGoodsByPageBean.htm", "/showAgencyChannelStoreyGoods.htm",
            "/queryAgencyChannelGoodsForStorey.htm", "/agencyOrderList.htm", "/sendAgencyOrder.htm", "/agencyOrderDetail.htm", "/queryAllLogisticsInfo.htm",
            "/updateOrderTrackingInfo.htm", "/agencyBackOrderList.htm", "/agencyBackOrderDetail.htm", "/updateAgencyBackOrderCheck.htm", "/modifyBackOrder.htm",
            "/queryGoodsCateVo.htm", "/getPointLevel.htm",
            "/getAllProvince.htm",
            "/getAllCityByPid.htm",
            "/getAllDistrictByCid.htm",
            "/queryTempBarByPageBean.htm",
            QUERYTEMPADVERBYPAGEBEAN,
            QUERYTEMPADVERBYPAGEBEAN,
            QUERYTEMPADVERBYPAGEBEAN,
            "/queryAgencyTempStoreyByPageBean.htm",
            "/jumpForClassifyBarView.htm",
            "/updateChannelStoreyGoods.htm",
            "/queryAllCate.htm",
            "/queryProductViewVoByProductId.htm",
            "/checkReport.htm",
            "/queryReportCate.htm",
            "/hadauditlist.htm",

            // 去添加模板页面
            "/toAddFreightTemplate.htm",
            "/addFreight.htm",

            "/searchgiftlist.htm",
            "/searchgiftcatelist.htm",
            "/marketorderlist.htm",
            "/dosearchgiftcatelist.htm",
            // 赠品分类
            "/addgiftcate.htm", "/searchgiftcatebyid.htm",
            "/querygiftcate.htm",
            "/updategiftcate.htm",
            "/delgiftcate.htm",
            "/delallgiftcate.htm",
            "/toaddgift.htm",
            "/doaddgift.htm",
            "/toupdategift.htm",
            "/doupdategift.htm",
            "/delgift.htm",
            "/batchdeletegoodsatte.htm",
            "/newbatchSaveGiftImage.htm",
            "/delallgift.htm",
            "/selectgiftpicbyId.htm",
            "/deletepicbypicid.htm",
            "/batchSaveGiftImage.htm",
            "/querygiftlistall.htm",

            // 刷新
            "/refurbish.htm",
            // 页面广告
            "/selectbbadvlist.htm", "/toeditbbadv.htm",
            "/toeditbbadvNew.htm",
            "/addbbadv.htm",
            "/updatebbadv.htm",
            "/deleteMuiltiBbadv.htm",
            "/selectadvlist.htm",
            "/exportGoodsCate.htm",
            "/exportGoodsCateTemp.htm",
            "/exportGoodsCateTemp.htm",

            "/selectordereloglist.htm",
            "/syssubmitorder.htm",
            "/ordeedbplist.htm",
            // 积分订单
            "/giftorderlist.htm",
            "/updategiftorder.htm",
            "/selectgiftdetails.htm",

            // 处罚规则
            "/queryAllPenaltylist.htm", "/addPunishInfo.htm", "/queryPunishInfoById.htm", "/updatePunishInfo.htm",
            "/deletePunishInfo.htm",
            "/punishShop.htm",
            "/queryAllPointByCusId.htm",
            "/queryStoreBalanceByThirdId.htm",

            "/queryregisterpoint.htm",
            "/checkgiftcate.htm",
            "/setcustomer.htm",

            // 营销
            "/searchCouponByIdAjax.htm",
            "/selectcouponnolistajax.htm",
            "/delallcoupon.htm",
            "/ajaxSelectGiftDetail.htm",
            "/ajaxupdategift.htm",
            "/searchgiftcatelistNopage.htm",

            // 会员统计
            "/selectcountbytime.htm",
            "/selectcountbyaddress.htm",
            "/tomemberLevel.htm",
            "/upCusLevel.htm",
            "/basicsetNew.htm",
            "/uploadImg.htm",
            "/selectAllLogistics.htm",
            "/findAllProvinceNew.htm",
            "/logisticssingle.htm",
            // 注册营销
            "/registerMarketing.htm",
            // 店铺
            "/findAllGoodsisthird.htm", "/registerMarketing.htm", "/createproductmarket.htm", "/uploadImageBackUrl.htm", "/queryProductVoByProductId.htm",
            "/queryAllByGoodsId.htm", "/orderlististhird.htm",
            "/uploadImageBackUrl.htm",
            "/groupgoodslist.htm",
            "/panicgoodslist.htm",
            // 查询货品
            "/queryAllByGoodsId.htm", "/newupdatestore.htm", "/newrefusestore.htm", "/newupdatesellerinfo.htm", "/newdeletesellerinfocate.htm", "/newupdatethridcate.htm",
            "/selectDeduByStoreId.htm", "/updateClassifyPay.htm", "/newupdateclassifypay.htm", "/queryAllPunishedBusiness.htm", "/grouppointset.htm", "/checkbossemailexist.htm",
            "/checkbossmobileexist.htm", "/findalllogo.htm", "/addlogo.htm", "/dellogo.htm", "/newdoaddmarketing.htm", "/jdcatelist.htm", "/jdtriggerlist.htm",
            "/jdtriggeraddgoods.htm", "/selectTempView.htm", "/configManager.htm", "/delCloudManager.htm", "/enableCloudManager.htm", "/modifybossmarketing.htm",
            "/newdomodifymarketing.htm", "/newqueryProductForCoupon.htm", "/newselectcouponnolistajax.htm", "/freightTemp.htm", "/batchUpGoods.htm", "/batchDownGoods.htm",
            // 店铺街 轮播大广告
            "/storeliststreetimage.htm", "/showtempadverajax.htm", "/updatetempadverajax.htm", "/deletetempadverajax.htm", "/setstore.htm",

            "/selectnewcustomer.htm", "/querystatistics.htm", "/operalogajax.htm", "/selectcountbyweek.htm",

            // 订单 退款
            "/newsavebackorderprice.htm", "/insertelasticgoods.htm",
            // 导出订单
            "/exportallorder.htm",
            // 收款单列表
            "/receivablesList.htm", "/receivablesDetail.htm", "/exportallorder.htm",

            // 搜索模块
            "/insertelasticgoods.htm", "/searchGoodsFromEs.htm", "/batchUpdateStock.htm", "/deleteRelatedProduct.htm",
            "/insertIndex.htm","/insertBatchIndex.htm","/deleteIndex.htm","/updateIndex.htm",
            // 修改物流公司设置
            "/updateLogisticsCompany.htm","/updatepayhelp.htm",
            // 添加物流公司设置
            "/addLogisticsCompany.htm",
            //外部接口模块
            "/selectemp.htm",
            "/updateempower.htm",
            "/insertempoer.htm",
            "/checkusername.htm",
            "/delempower.htm",
            "/checkusername.htm",
            "/selectlog.htm",
            "/queryEnterpriseAllInfos.htm",
            "/queryEnterpriseAllInfoIsEn.htm",
            "/queryEnterpriseAllInfoById.htm",
            "/enterpriseExamine.htm",
            "/loadLogisticsTemplate.htm",
            "/deleteEnterpriseAllInfoById.htm",
            //业务员数据维护
            "/initSalesman.htm",
            "/addSalesman.htm",
            "/deleteSalesman.htm",
            "/updateSalesman.htm",
            "/querySalesman.htm",
            "/querySalesmanById.htm",
            "/checkExistSalesmanName.htm",
            "/queryBySalesman.htm",
            "/checkUpdateEnable.htm",
            "/checkUpdateEnableByIds.htm",
            "/deleteAllSalesman.htm",
            "/orderLogAjax.htm",
            "/checkMobileExist.htm",
            "/autostyle/loadStyle.htm",
            "/autostyle/getTreeDatas.htm"
    };

    @Pointcut("execution(* com.ningpai.*.controller.*.*(..))")
    private void pointCut() {
    }

    /**
     * 验证token的AOP
     */
    @Before("pointCut()")
    public void valiToken() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String path = request.getServletPath();
        boolean bool = true;
        try {
            /* 如果是放开的控制器就不验证token */
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>path：" + path);
            for (String url : urls) {
                if (url.equals(path)) {
                    bool = false;
                }
            }

            if (bool) {
                String token = request.getParameter(ConstantUtil.CSRFTOKEN);
                if (null != token && !"".equals(token)) {
                    if (!CSRFTokenManager.valiToken(token, request.getSession())) {
                        throw new RuntimeException("token不匹配");

                    }
                } else {
                    // token不匹配就抛出异常
                    throw new RuntimeException("token不匹配");
                }
            }
        } finally {
            request = null;
        }
    }

    @Pointcut("execution(* com.ningpai.manager.controller.ManagerController.ifLogin(..))")
    private void loginPointCut() {
    }

    /**
     * 登陆成功后创建token
     */
    @After("loginPointCut()")
    public void afterLogin() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        try {
            // 如果登陆成功创建token
            if (null != request.getSession().getAttribute("name") && !"".equals(request.getSession().getAttribute("name"))) {
                CSRFTokenManager.getTokenForSession(request.getSession());
            }
        } finally {
            request = null;
        }
    }
}
