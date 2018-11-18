/*
 * Copyright 2014 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.mobile.service.impl;

import com.ningpai.mobile.bean.MobHomePage;
import com.ningpai.mobile.dao.MobHomePageMapper;
import com.ningpai.mobile.service.MobHomePageService;
import com.ningpai.util.xml.XmlUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.w3c.dom.Document;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * SERVICE实现类-移动版可视化配置首页
 *
 * @ClassName: MobHomePageServiceImpl
 * @Description: 移动版可视化配置首页的SERVICE实现
 * @author Wanghy
 * @date 2014年10月17日 上午10:02:33
 *
 */
@Service("MobHomePageService")
public class MobHomePageServiceImpl implements MobHomePageService {

    static final String xmlFilePath = "mobile_home_page/xml/9gdemo.xml";

    @Resource(name = "MobHomePageMapper")
    private MobHomePageMapper mobHomePageMapper;

    @Override
    public void createHomePage(MobHomePage homePage) {
        homePage.setTemp2("0");
        homePage.setUpdateDate(new Date());
        homePage.setUpdateUserId(getLoginUserId());
        this.mobHomePageMapper.insertSelective(homePage);
    }

    @Override
    public void updateHomePage(MobHomePage homePage) {
        homePage.setUpdateDate(new Date());
        homePage.setUpdateUserId(getLoginUserId());
        this.mobHomePageMapper.updateByPrimaryKeySelective(homePage);
    }

    @Override
    public MobHomePage selectHomePageByMerchantId(Long merchantId) {
        MobHomePage homePage = mobHomePageMapper.selectByMerchantId(merchantId);
        if (null == homePage) {
            // 第二个参数判断是否为第三方,第三个参数为第三方的店铺id
            initHomePage(merchantId, null, null);
            homePage = mobHomePageMapper.selectByMerchantId(merchantId);
        }
        return homePage;
    }

    @Override
    public MobHomePage initHomePage(Long merchantId, String isThird, Long storeId) {
        XmlUtil xmlUtil = new XmlUtil();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String filePath = request.getSession().getServletContext().getRealPath("/")+"/";
        String fileName = filePath + xmlFilePath;
        Document document = xmlUtil.parserXml(fileName);

        String xmlStr = xmlUtil.document2Str(document);
        MobHomePage homePage = new MobHomePage();
        homePage.setDoc(xmlStr);
        homePage.setDocBac(xmlStr);
        homePage.setMerchantId(merchantId);
        homePage.setUpdateDate(new Date());
        homePage.setUpdateUserId(getLoginUserId());
        if (StringUtils.isNotEmpty(isThird)) {
            homePage.setIsThird(isThird);
            homePage.setStoreId(storeId);
        }
        createHomePage(homePage);
        return homePage;
    }

    @Override
    public void makeHtml() {
        XmlUtil xmlUtil = new XmlUtil();
        xmlUtil.Transform("xmlFileName", "xslFileName", "htmlFileName");
    }

    @Override
    public void deleteHomePageById(Long homepageId) {
        mobHomePageMapper.deleteByPrimaryKey(homepageId);

    }

    @Override
    public MobHomePage getHomePageById(Long homepageId) {

        return mobHomePageMapper.selectByPrimaryKey(homepageId);
    }

    @Override
    public MobHomePage selectThirdMob(Long storeId) {

        return mobHomePageMapper.selectThirdMob(storeId);
    }

    @Override
    public void openHomePageByHIdAndMId(Long homepageId, Long merchantId) {

        mobHomePageMapper.updateByMerchantId(merchantId);
        MobHomePage mobHomePage = new MobHomePage();
        mobHomePage.setHomepageId(homepageId);
        mobHomePage.setMerchantId(merchantId);
        mobHomePageMapper.updateByhomepageIdAndMerchantId(mobHomePage);

    }

    @Override
    public List<MobHomePage> selectAllUnstatusByMerchantId(Long merchantId) {

        return mobHomePageMapper.selectAllUnstatusByMerchantId(merchantId);
    }

    @Override
    public MobHomePage getCurrHomePageByMerchantId(Long merchantId) {

        return mobHomePageMapper.queryCurrHomePageByMerchantId(merchantId);
    }

    /*
     * 根据商家ID获取该商家当前使用的模板信息
     * 
     * @Description: 根据商家ID获取该商家当前使用的模板信息
     * 
     * @param storeId
     * 
     * @return
     */
    @Override
    public MobHomePage getCurrHomePageByStoreId(Long storeId) {
        return mobHomePageMapper.getCurrHomePageByStoreId(storeId);
    }

    /*
     * 获取当前操作的用户ID
     */
    private Long getLoginUserId() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return (Long) request.getAttribute("loginUserId");
    }

}
