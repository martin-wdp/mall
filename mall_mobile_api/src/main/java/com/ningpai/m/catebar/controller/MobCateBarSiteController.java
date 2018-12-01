/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.m.catebar.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.ningpai.m.goods.service.GoodsService;
import com.ningpai.m.main.controller.MobMainSiteController;
import com.ningpai.m.util.AuthUtil;
import com.ningpai.util.MyLogger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
    @Resource(name = "authUtil")
    private AuthUtil authUtil;

    /**
     * 推荐分类
     * @return
     */
    @RequestMapping(value = "/cates/recommend",produces="html/text;charset=UTF-8")
    @ResponseBody
    public String getRecommendCates(HttpServletRequest request){

        Map result = new ConcurrentHashMap();

        try{
            if(authUtil.isPassAuth(request)){
                result.put("code",0);
                result.put("msg","success");
                result.put("data",mobCateBarService.selectMobCateBarForMobChoose());

            }else{
                result.put("code",100);
                result.put("msg","appcode不合法");
            }
        }catch (Exception e){
            result.put("code",-1);
            result.put("msg","查询异常");
            LOGGER.error(Arrays.asList(e.getStackTrace()).toString());
            result.put("data",null);
        }

        return JSONObject.toJSONString(result);
    }

    /**
     * 移动端一级分类
     * @return
     */
//    @RequestMapping("/cates/first")
//    public Map getFirstCates(){
//
//        Map result = new ConcurrentHashMap();
//
//        try{
//            result.put("code",0);
//            result.put("msg","success");
//            result.put("data",mobAdverService.selectByStoreyIdForSite(48L));
//        }catch (Exception e){
//            result.put("code",-1);
//            result.put("msg","查询异常");
//            LOGGER.error(Arrays.asList(e.getStackTrace()).toString());
//            result.put("data",null);
//        }
//
//        return result;
//    }

    /**
     * 移动端二级分类
     * @return
     */
//    @RequestMapping("/cates/second")
//    public Map getSecondCates(){
//
//        Map result = new ConcurrentHashMap();
//
//        try{
//            result.put("code",0);
//            result.put("msg","success");
//            result.put("data",mobAdverService.selectByStoreyIdForSite(48L));
//        }catch (Exception e){
//            result.put("code",-1);
//            result.put("msg","查询异常");
//            LOGGER.error(Arrays.asList(e.getStackTrace()).toString());
//            result.put("data",null);
//        }
//
//        return result;
//    }

    /**
     * 当前分类下推荐的商品
     * @param catId
     * @return
     */
    @RequestMapping(value = "/cates/second/goods/top10",produces="html/text;charset=UTF-8")
    @ResponseBody
    public String getTop10SecondGoods(HttpServletRequest request,Long catId){

        Map result = new ConcurrentHashMap();

        try{
            if(authUtil.isPassAuth(request)){
                result.put("code",0);
                result.put("msg","success");
                result.put("data",goodsService.queryTopThreeNew(catId));
            }else{
                result.put("code",100);
                result.put("msg","appcode不合法");
            }
        }catch (Exception e){
            result.put("code",-1);
            result.put("msg","查询异常");
            LOGGER.error(Arrays.asList(e.getStackTrace()).toString());
            result.put("data","");
        }

        return JSONObject.toJSONString(result);
    }
}
