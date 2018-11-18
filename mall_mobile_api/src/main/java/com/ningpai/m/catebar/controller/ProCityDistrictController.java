/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.m.catebar.controller;

import com.ningpai.system.service.CityService;
import com.ningpai.system.service.DistrictService;
import com.ningpai.system.service.ProvinceService;
import com.ningpai.util.MyLogger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
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

    @Resource(name = "ProvinceService")
    ProvinceService provinceService;

    @Resource(name = "CityService")
    CityService cityService;

    @Resource(name = "DistrictService")
    DistrictService districtService;

    @RequestMapping("/citys")
    public Map getCitys(){

        Map response = new  ConcurrentHashMap();

        try{
            response.put("code",0);
            response.put("msg","success");
            response.put("data",cityService.queryCityByProvinceId(48L));
        }catch (Exception e){
            response.put("code",-1);
            response.put("msg","查询异常");
            LOGGER.error(Arrays.asList(e.getStackTrace()).toString());
            response.put("data",null);
        }

        return response;
    }

    @RequestMapping("/districts/${cityId}")
    public Map getDistrictsByCity(@RequestParam Long cityId){
        Map response = new  ConcurrentHashMap();

        try{
            response.put("code",0);
            response.put("msg","success");
            response.put("data",districtService.queryDistrictByCityId(cityId));
        }catch (Exception e){
            response.put("code",-1);
            response.put("msg","查询异常");
            LOGGER.error(Arrays.asList(e.getStackTrace()).toString());
            response.put("data",null);
        }

        return response;
    }

}
