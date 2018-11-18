/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.m.catebar.controller;

import javax.annotation.Resource;

import com.ningpai.m.goods.service.GoodsService;
import com.ningpai.m.main.controller.MobMainSiteController;
import com.ningpai.util.MyLogger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ningpai.m.common.service.SeoService;
import com.ningpai.mobile.service.MobCateBarService;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 控制器-移动版分类导航接口
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年8月20日下午7:44:20
 */
@Controller
public class MobCateBarSiteController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(MobMainSiteController.class);

    @Resource(name = "MobCateBarService")
    private MobCateBarService mobCateBarService;
    @Resource(name = "SeoService")
    private SeoService seoService;
    @Resource(name = "HsiteGoodsService")
    private GoodsService goodsService;


    /**
     * 推荐分类
     * @return
     */
    @RequestMapping("/cates/recommend")
    public Map getRecommendCates(){

        Map response = new ConcurrentHashMap();

        try{
            response.put("code",0);
            response.put("msg","success");
            response.put("data",mobCateBarService.selectMobCateBarForMobChoose());
        }catch (Exception e){
            response.put("code",-1);
            response.put("msg","查询异常");
            LOGGER.error(Arrays.asList(e.getStackTrace()).toString());
            response.put("data",null);
        }

        return response;
    }

    /**
     * 移动端一级分类
     * @return
     */
//    @RequestMapping("/cates/first")
//    public Map getFirstCates(){
//
//        Map response = new ConcurrentHashMap();
//
//        try{
//            response.put("code",0);
//            response.put("msg","success");
//            response.put("data",mobAdverService.selectByStoreyIdForSite(48L));
//        }catch (Exception e){
//            response.put("code",-1);
//            response.put("msg","查询异常");
//            LOGGER.error(Arrays.asList(e.getStackTrace()).toString());
//            response.put("data",null);
//        }
//
//        return response;
//    }

    /**
     * 移动端二级分类
     * @return
     */
//    @RequestMapping("/cates/second")
//    public Map getSecondCates(){
//
//        Map response = new ConcurrentHashMap();
//
//        try{
//            response.put("code",0);
//            response.put("msg","success");
//            response.put("data",mobAdverService.selectByStoreyIdForSite(48L));
//        }catch (Exception e){
//            response.put("code",-1);
//            response.put("msg","查询异常");
//            LOGGER.error(Arrays.asList(e.getStackTrace()).toString());
//            response.put("data",null);
//        }
//
//        return response;
//    }

    /**
     * 当前分类下推荐的商品
     * @param catId
     * @return
     */
    @RequestMapping("/cates/second/goods/top10")
    public Map getTop10SecondGoods(Long catId){

        Map response = new ConcurrentHashMap();

        try{
            response.put("code",0);
            response.put("msg","success");
            response.put("data",goodsService.queryTopThreeNew(catId));
        }catch (Exception e){
            response.put("code",-1);
            response.put("msg","查询异常");
            LOGGER.error(Arrays.asList(e.getStackTrace()).toString());
            response.put("data",null);
        }

        return response;
    }
}
