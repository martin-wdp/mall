/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.m.catebar.controller;

import com.alibaba.fastjson.JSONObject;
import com.ningpai.freighttemplate.service.SysCityService;
import com.ningpai.freighttemplate.service.SysDistrictService;
import com.ningpai.m.util.AuthUtil;
import com.ningpai.system.service.CityService;
import com.ningpai.system.service.DistrictService;
import com.ningpai.system.service.ProvinceService;
import com.ningpai.util.MyLogger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@RequestMapping("/pcd")
public class ProCityDistrictController {
    /**
     * 日志
     * */
    public final MyLogger LOGGER = new MyLogger(ProCityDistrictController.class);

    @Resource(name = "SysCityService")
    private SysCityService sysCityService;

    @Resource(name = "SysDistrictService")
    private SysDistrictService sysDistrictService;

    @Resource(name = "authUtil")
    private AuthUtil authUtil;

    @RequestMapping(value="/citys",produces="html/text;charset=UTF-8")
    @ResponseBody
    public String getCitys(HttpServletRequest request){

        Map result = new  ConcurrentHashMap();

        try{
            if(authUtil.isPassAuth(request)){
                result.put("code",0);
                result.put("msg","success");
                result.put("data",sysCityService.selectAllCityByProvinceId(48L));
            }else{
                result.put("code",100);
                result.put("msg","appcode不合法");
            }

        }catch (Exception e){
            result.put("code",-1);
            result.put("msg","查询异常"+e.getMessage());
            e.printStackTrace();
            LOGGER.error(Arrays.asList(e.getStackTrace()).toString());
        }

        return JSONObject.toJSONString(result);
    }

    @RequestMapping(value="/districts",produces="html/text;charset=UTF-8")
    @ResponseBody
    public String getDistrictsByCity(HttpServletRequest request,Long cityId){
        Map result = new  ConcurrentHashMap();

        try{
            if(authUtil.isPassAuth(request)){
                result.put("code",0);
                result.put("msg","success");
                result.put("data",sysDistrictService.selectAllDistrictByCityId(cityId));
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

}
