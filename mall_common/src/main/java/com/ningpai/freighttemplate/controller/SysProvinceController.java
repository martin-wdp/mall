package com.ningpai.freighttemplate.controller;

import java.util.List;

import javax.annotation.Resource;

import com.ningpai.util.MyLogger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ningpai.freighttemplate.bean.SysProvince;
import com.ningpai.freighttemplate.service.SysCityService;
import com.ningpai.freighttemplate.service.SysProvinceService;

/***
 * 
 * @author ggn 省控制器
 */
@Controller
public class SysProvinceController {

    // Spring注入
    @Resource(name = "SysProvinceService")
    private SysProvinceService sysProvinceService;
    // Spring注入
    @Resource(name = "SysCityService")
    private SysCityService sysCityService;
    private static final MyLogger LOGGER = new MyLogger(SysProvinceController.class);

    /**
     * 查询省
     * 
     * @return List
     */
    @RequestMapping("selectprovincelist")
    @ResponseBody
    public List<SysProvince> selectProvinceList() {
        // 获取所有省
        List<SysProvince> prolist = sysProvinceService.selectAllProvince();
        if (prolist != null && !prolist.isEmpty()) {
            // 根据省获取 该省下的所有市
            for (int i = 0; i < prolist.size(); i++) {
                prolist.get(i).setCityList(sysCityService.selectAllCityByProvinceId(prolist.get(i).getProvinceId()));
            }
        }
        LOGGER.info("获取所有的省份信息");
        return prolist;
    }
}
