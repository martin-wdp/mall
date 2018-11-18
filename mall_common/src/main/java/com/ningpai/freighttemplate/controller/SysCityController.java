package com.ningpai.freighttemplate.controller;

import java.util.List;

import javax.annotation.Resource;

import com.ningpai.freighttemplate.bean.SysProvince;
import com.ningpai.freighttemplate.service.SysProvinceService;
import com.ningpai.util.MyLogger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ningpai.freighttemplate.bean.SysCity;
import com.ningpai.freighttemplate.service.SysCityService;

/**
 * 查询城市controller
 * 
 * @author ggn
 *
 */
@Controller
public class SysCityController {

    // Spring注入
    @Resource(name = "SysCityService")
    private SysCityService sysCityService;
    // Spring注入
    @Resource(name = "SysProvinceService")
    private SysProvinceService sysProvinceService;

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(SysCityController.class);

    /**
     * 查询城市下来
     * 
     * @param provinceId
     *            省ID
     * @return List
     */
    @RequestMapping("selectcityuseselect")
    @ResponseBody
    public List<SysCity> selectCityUseSelect(Long provinceId) {
        // 非空验证 省份ID
        if (null != provinceId) {
            // 根据ID获取省详细
            SysProvince sysProvince = sysProvinceService
                    .selectProvinceById(provinceId);
            if (null != sysProvince.getProvinceName()) {
                LOGGER.info("查询【" + sysProvince.getProvinceName()
                        + "】省下面所有的城市!");
            }

        }
        // 查询所有市区 根据省ID
        return sysCityService.selectAllCityByProvinceId(provinceId);
    }
}
