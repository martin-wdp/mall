package com.ningpai.freighttemplate.controller;

import com.ningpai.freighttemplate.bean.SysCity;
import com.ningpai.freighttemplate.bean.SysDistrict;
import com.ningpai.freighttemplate.service.SysDistrictService;
import com.ningpai.util.MyLogger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 区县controller
 * 
 * @author ggn
 *
 */
@Controller
public class SysDistrictController {

    // Spring注入
    @Resource(name = "SysDistrictService")
    private SysDistrictService sysDistrictService;

    private static final MyLogger LOGGER = new MyLogger(
            SysDistrictController.class);

    /***
     * 获取指定城市下面的所有区县ID
     * 
     * @param cityId
     *            市区ID
     * @return List
     */
    @RequestMapping("selectdistrictlistuserselect")
    @ResponseBody
    public List<SysDistrict> selectDistrictListUserSelect(Long cityId) {
        // 非空验证城市id
        if (null != cityId) {
            // 根据市ID 获取市的详情
            SysCity sysCity = sysDistrictService.selectCityById(cityId);
            if (null != sysCity.getCityName()) {
                LOGGER.info("获取【" + sysCity.getCityName() + "】下面所有的区县");
            }
        }
        // 根据市的详情获取 区县列表
        return sysDistrictService.selectAllDistrictByCityId(cityId);
    }
}
