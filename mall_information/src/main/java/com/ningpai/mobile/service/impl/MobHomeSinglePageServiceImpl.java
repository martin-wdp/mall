/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.mobile.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.w3c.dom.Document;

import com.ningpai.mobile.bean.MobHomeSinglePage;
import com.ningpai.mobile.dao.MobHomeSinglePageMapper;
import com.ningpai.mobile.service.MobHomeSinglePageService;
import com.ningpai.util.xml.XmlUtil;

/**
 * 移动版单页可视化配置 service层实现类
 * 
 * @author zhangsl
 * @since 2014年12月5日 下午2:31:34
 * @version
 */
@Service("MobHomeSinglePageService")
public class MobHomeSinglePageServiceImpl implements MobHomeSinglePageService {

    /** 移动版单页可视化配置 dao层接口 */
    @Resource(name = "MobHomeSinglePageMapper")
    private MobHomeSinglePageMapper mobHomeSinglePageMapper;

    static final String xmlFilePath = "mobile_home_page/xml/9gdemo2.xml";

    /**
     * 添加移动版可视化配置首页
     *
     * @Title: createHomePage
     * @Description: 添加移动版可视化配置首页
     * @param homePage
     * @see com.ningpai.mobile.service.MobHomeSinglePageService#createHomePage(com.ningpai.mobile.bean.MobHomeSinglePage)
     */
    @Override
    public void createHomePage(MobHomeSinglePage homePage) {
        homePage.setTemp2("0");
        /** 设置更新时间为当前时间 */
        homePage.setUpdateDate(new Date());
        /** 设置更新用户id为登录id */
        homePage.setUpdateUserId(getLoginUserId());
        /** 执行添加方法 */
        mobHomeSinglePageMapper.insertSelective(homePage);

    }

    /**
     * 修改移动版可视化配置首页
     *
     * @Title: updateHomePage
     * @Description: 修改移动版可视化配置首页
     * @param homePage
     * @see com.ningpai.mobile.service.MobHomeSinglePageService#updateHomePage(com.ningpai.mobile.bean.MobHomeSinglePage)
     */
    @Override
    public void updateHomePage(MobHomeSinglePage homePage) {
        /** 设置更新时间为当前时间 */
        homePage.setUpdateDate(new Date());
        /** 设置更新者id为登录id */
        homePage.setUpdateUserId(getLoginUserId());
        /** 执行更新方法 */
        mobHomeSinglePageMapper.updateByPrimaryKeySelective(homePage);

    }

    /**
     * 初始化商家的HomePage
     *
     * @Title: initHomePage
     * @Description: 初始化商家的HomePage
     * @see com.ningpai.mobile.service.MobHomeSinglePageService#initHomePage()
     */
    @Override
    public MobHomeSinglePage initHomePage() {
        if (queryinitInfoCount() > 0) {
            return queryinitInfoBySinglepageId().get(0);
        }

        XmlUtil xmlUtil = new XmlUtil();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String filePath = request.getSession().getServletContext().getRealPath("/");
        String fileName = filePath + xmlFilePath;
        Document document = xmlUtil.parserXml(fileName);
        String xmlStr = xmlUtil.document2Str(document);
        MobHomeSinglePage homePage = new MobHomeSinglePage();
        homePage.setSinglepageId(-1L);
        homePage.setDoc(xmlStr);
        homePage.setDocBac(xmlStr);
        homePage.setUpdateDate(new Date());
        homePage.setUpdateUserId(getLoginUserId());
        createHomePage(homePage);
        return homePage;
    }

    /**
     * 生成HTML
     *
     * @Title: makeHtml
     * @Description: 生成HTML
     * @see com.ningpai.mobile.service.MobHomeSinglePageService#makeHtml()
     */
    @Override
    public void makeHtml() {
        XmlUtil xmlUtil = new XmlUtil();
        xmlUtil.Transform("xmlFileName", "xslFileName", "htmlFileName");

    }

    /**
     * 根据ID获取模板信息
     *
     * @Title: getHomePageById
     * @Description: 根据ID获取模板信息
     * @param homepageId
     * @return
     * @see com.ningpai.mobile.service.MobHomeSinglePageService#getHomePageById(java.lang.Long)
     */
    @Override
    public MobHomeSinglePage getHomePageById(Long homepageId) {
        /** 执行查询方法并返回结果 */
        return mobHomeSinglePageMapper.selectByPrimaryKey(homepageId);
    }

    /**
     * 根据ID删除模板信息
     *
     * @Title: deleteHomePageById
     * @Description: 根据ID删除模板信息
     * @param homepageId
     * @see com.ningpai.mobile.service.MobHomeSinglePageService#deleteHomePageById(java.lang.Long)
     */
    @Override
    public void deleteHomePageById(Long homepageId) {
        /** 执行删除方法并返回结果 */
        mobHomeSinglePageMapper.deleteByPrimaryKey(homepageId);

    }

    /**
     * @Title:queryInfoBySinglepageId
     * @Description: 根据移动版单页ID获取移动版模板信息
     * @param singlepageId
     * @return
     * @see com.ningpai.mobile.service.MobHomeSinglePageService#queryInfoBySinglepageId(java.lang.Long)
     */
    @Override
    public MobHomeSinglePage queryInfoBySinglepageId(Long singlepageId) {
        /** 执行查询方法并返回结果 */
        return mobHomeSinglePageMapper.queryInfoBySinglepageId(singlepageId);
    }

    /**
     * @Title:queryinitInfoBySinglepageId
     * @Description:查询SinglepageId=-1的移动版模板信息的集合 即查询用于初始化的模板信息
     * @return List<MobHomeSinglePage>
     * @see com.ningpai.mobile.service.MobHomeSinglePageService#queryinitInfoBySinglepageId()
     */
    @Override
    public List<MobHomeSinglePage> queryinitInfoBySinglepageId() {
        /** 执行查询方法并返回结果 */
        return mobHomeSinglePageMapper.queryinitInfoBySinglepageId();
    }

    /**
     * @Title:queryinitInfoCount
     * @Description:查询SinglepageId=-1的移动版模板信息 即查询用于初始化的模板信息的总条数
     * @return
     * @see com.ningpai.mobile.service.MobHomeSinglePageService#queryinitInfoCount()
     */
    @Override
    public int queryinitInfoCount() {
        /** 执行查询方法并返回结果 */
        return mobHomeSinglePageMapper.queryinitInfoCount();
    }

    /**
     * 获取当前操作的用户ID
     */
    private Long getLoginUserId() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return (Long) request.getAttribute("loginUserId");
    }

}
