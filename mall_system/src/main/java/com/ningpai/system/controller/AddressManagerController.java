/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.system.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ningpai.system.bean.District;
import com.ningpai.system.bean.Province;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.ningpai.util.MyLogger;

import com.ningpai.util.MenuSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.system.bean.City;
import com.ningpai.system.service.CityService;
import com.ningpai.system.service.DefaultAddressService;
import com.ningpai.system.service.DistrictService;
import com.ningpai.system.service.ProvinceService;
import com.ningpai.system.service.StreetService;
import com.ningpai.system.util.AddressUtil;
import com.ningpai.system.util.SelectBean;
import com.ningpai.util.PageBean;

/**
 * 地区管理控制器
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年2月12日 下午4:59:37
 * @version 1.0
 */
@Controller
public class AddressManagerController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(AddressManagerController.class);
    private static final String NUMBER = "^[0-9]*$";
    private static final String ISNEW = "isNew";
    /** 地址管理 */
    private String addressManager = "jsp/system/addressManager";
    /** 新地址管理 */
    private String addressManagerNew = "jsp/system/addressManagerNew";
    /** 查找所有省份 */
    private String findAllProvince = "findAllProvince.htm";
    /** 新查找所有省份 */
    private String findAllProvinceNew = "findAllProvinceNew.htm";
    /** 地址名称 */
    private String addressName = "addressName";
    /** 父节点 */
    private String parentId = "parentId";
    /** 地址id */
    private String addressId = "addressId";
    /** 地址排序 */
    private String addressSort = "addressSort";
    /** 省份service注解 */
    private ProvinceService provinceService;
    /** 城市service注解 */
    private CityService cityService;
    /** 区县service注解 */
    private DistrictService districtService;
    /** 街道service注解 */
    private StreetService streetService;

    @Resource(name = "DefaultAddressService")
    private DefaultAddressService addressService;

    /**
     * 批量删除所有
     * 
     * @param request
     *            请求
     */
    @RequestMapping("/batchAllAddress")
    public ModelAndView batchAllAddress(HttpServletRequest request) {
        // 省份数组
        String[] provinceIds = request.getParameterValues("province");
        // 城市数组
        String[] cityIds = request.getParameterValues("city");
        // 区县数组
        String[] districtIds = request.getParameterValues("district");
        // 街道
        String[] streetIds = request.getParameterValues("street");
        // 批量删除省份
        this.provinceService.batchDelProvince(provinceIds);
        // 批量删除城市
        this.cityService.batchDelCity(cityIds);
        // 批量删除区县
        this.districtService.batchDelDistrict(districtIds);
        // 批量删除街道
        this.streetService.batchDelStreet(streetIds);
        // 返回查询所有省份
        return new ModelAndView(new RedirectView(findAllProvince));
    }

    /**
     * 根据参数和分页参数查询省份的列表
     * 
     * @param pb
     *            分页辅助类
     * @param request
     *            请求对象
     * @param selectBean
     *            查询参数Bean
     */
    @RequestMapping("/findAllProvince")
    public ModelAndView findAllProvince(PageBean pb, HttpServletRequest request, SelectBean selectBean) {
        // 设置导航
        MenuSession.sessionMenu(request);
        // 默认地区ID
        Long defaultDistrictId = addressService.getDefaultIdService();
        // 默认地区信息
        District district = null;
        if (defaultDistrictId != null) {
            district = districtService.findDistrictByPrimaryKey(defaultDistrictId);
        }
        // 默认城市
        City city = null;
        if (district != null) {
            city = cityService.findCityByPrimaryKey(district.getCityId());
        }
        // 默认省份
        Province province = null;
        if (city != null) {
            province = provinceService.findProvinceByPrimaryKey(city.getProvinceId());
        }
        // 返回地址管理
        return new ModelAndView(addressManager).addObject("selectBean", selectBean).addObject("defaultProvince", province).addObject("defaultCity", city)
                .addObject("defaultDistrict", district).addObject("pb", this.provinceService.findListByPageBean(pb, selectBean));
    }

    /**
     * 查询所有省份（新）
     * 
     * @param pb
     *            分页辅助类
     * @param request
     *            请求对象
     * @param selectBean
     *            查询参数Bean
     * @return 地区列表
     */
    @RequestMapping("/findAllProvinceNew")
    public ModelAndView findAllProvinceNew(PageBean pb, HttpServletRequest request, SelectBean selectBean) {
        // 设置导航
        MenuSession.sessionMenu(request);
        // 返回新地址管理
        return new ModelAndView(addressManagerNew).addObject("selectBean", selectBean).addObject("pb", this.provinceService.findListByPageBeanNew(pb, selectBean));
    }

    /**
     * 查询所有的省份信息
     * 
     * @param provinceName
     *            省份名称
     * @return 查询到的省份列表
     */
    @RequestMapping("/queryAllProvince")
    @ResponseBody
    public List<Object> queryAllProvince(String provinceName) {
        // 返回所有省份列表
        return this.provinceService.queryAllProvinceByName(provinceName);
    }

    /**
     * 添加省份信息实体
     * 
     */
    @RequestMapping("/saveProvince")
    public ModelAndView saveProvince(HttpServletRequest request) {
        // 省份名称
        String provinceName = request.getParameter(addressName);
        // 省份排序
        String provinceSort = request.getParameter(addressSort);

        // 验证省份名称
        Matcher mat = checkRegExForT(provinceName);
        // 验证省份排序
        Matcher mat2 = checkRegEx(NUMBER, provinceSort);

        // 验证通过后
        if ((!mat.find()) && (mat2.find())) {
            // 保存省份
            this.provinceService.saveProvince(provinceName, provinceSort);
        }
        // 返回查找所有省份
        return new ModelAndView(new RedirectView(findAllProvince));
    }

    /**
     * 检查是否有特殊字符
     * 
     * @param provinceName
     * @return
     */
    private Matcher checkRegExForT(String provinceName) {
        // 正则
        String regEx = "[''\\[\\]<>?\\\\!]";
        // pat
        Pattern pat = Pattern.compile(regEx);
        // 检查是否有特殊字符
        return pat.matcher(provinceName);
    }

    /**
     * 验证正则表达式
     * 
     * @param regEx
     *            正则表达式
     * @param provinceName
     *            要验证的字符串
     * @return
     */
    private Matcher checkRegEx(String regEx, String provinceName) {
        // 正则
        Pattern pat = Pattern.compile(regEx);
        // 验证正则表达式
        return pat.matcher(provinceName);
    }

    /**
     * 删除省份实体
     * 
     * @param provinceId
     *            待删除的省份ID
     */
    @RequestMapping("/delProvince")
    public ModelAndView deleteProvince(Long provinceId) {
        // 非空验证省份ID
        if (null != provinceId) {
            // 日志记录
            LOGGER.info("删除省份实体，省份的名称为：" + provinceService.findProvinceByPrimaryKey(provinceId).getProvinceName());
        }
        // 删除省份
        this.provinceService.delProvince(provinceId);
        // 返回查找所有省份
        return new ModelAndView(new RedirectView(findAllProvince));
    }

    /**
     * 更新省份实体
     * 
     */
    @RequestMapping("/updateProvince")
    public ModelAndView updateProvince(HttpServletRequest request) {
        // 省份名称
        String provinceName = request.getParameter(addressName);
        // 省份id
        String provinceId = request.getParameter(addressId);
        // 省份排序
        String provinceSort = request.getParameter(addressSort);
        // 验证省份名称
        Matcher mat = checkRegExForT(provinceName);
        // 验证省份排序
        Matcher mat2 = checkRegEx(NUMBER, provinceSort);

        // 非空验证省份ID
        if (null != provinceId) {
            // 日志记录
            LOGGER.info("更新省份实体，省份的名称为：" + provinceService.findProvinceByPrimaryKey(Long.valueOf(provinceId)).getProvinceName());
        }
        // 验证通过
        if ((!mat.find()) && (mat2.find())) {
            // 修改省份
            this.provinceService.updateProvince(provinceId, provinceName, provinceSort);
        }

        // isNew新后台标记
        if (request.getParameter(ISNEW) != null) {
            // 返回新查找所有省份
            return new ModelAndView(new RedirectView(findAllProvinceNew));
        } else {
            // 返回查找所有省份
            return new ModelAndView(new RedirectView(findAllProvince));
        }
    }

    /**
     * 根据省份ID查询省份实体
     * 
     * @param provinceId
     *            省份ID
     * @return 查询到的省份实体
     */
    @ResponseBody
    @RequestMapping("/searchProvinceById")
    public Province searchProvinceById(Long provinceId) {
        // 非空验证省份ID
        if (null != provinceId) {
            // 日志记录
            LOGGER.info("更新省份实体，省份的名称为：" + provinceService.findProvinceByPrimaryKey(Long.valueOf(provinceId)).getProvinceName());
        }
        // 单个省份
        return this.provinceService.findProvinceByPrimaryKey(provinceId);
    }

    /**
     * 批量删除省份信息
     * 
     * @param provinceIds
     *            待删除的省份的ID数组
     */
    @RequestMapping("/batchDelProvince")
    public ModelAndView batchDelProvince(String[] provinceIds) {
        // 批量删除省份
        this.provinceService.batchDelProvince(provinceIds);
        return null;
    }

    /**
     * 验证省份名称是否可用
     * 
     * @return 验证的结果 true表示可用 false 表示不可用
     */
    @RequestMapping("/checkProvinceName")
    @ResponseBody
    public boolean checkProvinceName(HttpServletRequest request) {
        // 验证省份名称是否可用
        return this.provinceService.checkProvinceName(request.getParameter("provinceName"));
    }

    /**
     * 根据参数查询列表
     * 
     * @param pb
     *            分页查询参数
     * @param selectBean
     *            查询参数Bean
     */
    @RequestMapping("/findAllCity")
    public ModelAndView findAllCity(PageBean pb, SelectBean selectBean) {
        // 根据参数查询列表
        this.cityService.findListByPageBean(pb, selectBean);
        return null;
    }

    /**
     * 根据省份ID查询城市的列表
     * 
     * @param provinceId
     *            省份ID
     * @return 查询到的城市的列表
     */
    @RequestMapping("/queryCityByProvinceId")
    @ResponseBody
    public List<Object> queryCityByProvinceId(Long provinceId, String cityName) {
        // 根据省份ID和城市名称查询城市的列表
        return this.cityService.queryCityByProvinceIdAndCityName(provinceId, cityName);
    }

    /**
     * 保存城市信息
     * 
     */
    @RequestMapping("/saveCity")
    public ModelAndView saveCity(HttpServletRequest request) {
        // 城市名称
        String cityName = request.getParameter(addressName);
        // 省份id
        String provinceId = request.getParameter(parentId);
        // 城市排序
        String citySort = request.getParameter(addressSort);

        // 城市名称验证
        Matcher mat = checkRegExForT(cityName);
        // 城市排序验证
        Matcher mat2 = checkRegEx(NUMBER, citySort);
        // 验证通过
        if ((!mat.find()) && (mat2.find())) {
            // 保存城市
            this.cityService.saveCity(cityName, provinceId, citySort);
        }

        return new ModelAndView(new RedirectView(findAllProvince));
    }

    /**
     * 删除城市信息
     * 
     * @param cityId
     *            城市信息ID
     */
    @RequestMapping("/delCity")
    public ModelAndView delCity(Long cityId) {
        // 根据id删除城市
        this.cityService.delPCity(cityId);
        return new ModelAndView(new RedirectView(findAllProvince));
    }

    /**
     * 批量删除城市信息
     * 
     * @param cityIds
     *            待删除的城市信息ID的数组
     */
    @RequestMapping("/batchDelCity")
    public ModelAndView batchDelCity(String[] cityIds) {
        // 批量删除城市
        this.cityService.batchDelCity(cityIds);
        return null;
    }

    /**
     * 修改城市信息
     * 
     */
    @RequestMapping("/updateCity")
    public ModelAndView updateCity(HttpServletRequest request) {
        // 城市名称
        String cityName = request.getParameter(addressName);
        // 城市id
        String cityId = request.getParameter(addressId);
        // 城市排序
        String citySort = request.getParameter(addressSort);

        // 验证城市名称
        Matcher mat = checkRegExForT(cityName);
        // 验证城市排序
        Matcher mat2 = checkRegEx(NUMBER, citySort);

        // 验证通过
        if ((!mat.find()) && (mat2.find())) {
            // 修改城市
            this.cityService.updateCity(cityId, cityName, citySort);
        }

        // isNew新后台标记
        if (request.getParameter(ISNEW) != null) {
            return new ModelAndView(new RedirectView(findAllProvinceNew));
        } else {
            return new ModelAndView(new RedirectView(findAllProvince));
        }
    }

    /**
     * 根据城市信息ID查询实体
     * 
     * @param cityId
     *            城市信息的主键ID
     * @return 查询到的实体
     */
    @RequestMapping("/queryCityByPrimaryKey")
    @ResponseBody
    public City queryCityByPrimaryKey(Long cityId) {
        // 根据城市id查询城市
        return this.cityService.findCityByPrimaryKey(cityId);
    }

    /**
     * 验证城市名称是否可用
     * 
     * @return 验证的结果 true 表示可用 false 表示不可用
     */
    @RequestMapping("/checkCityName")
    @ResponseBody
    public boolean checkCityName(HttpServletRequest request) {
        return this.cityService.checkCityName(request.getParameter("cityName"));
    }

    /**
     * 根据城市ID查询所述的省份的名称
     * 
     * @param cityId
     *            城市ID
     * @return 查询到的地址帮助Bean
     */
    @RequestMapping("/queryAddressUtilByCityId")
    @ResponseBody
    public AddressUtil queryAddressUtilByCityId(Long cityId) {
        return this.cityService.queryAddressNameByCityId(cityId);
    }

    /**
     * 根据分页参数以及查询参数查询列表
     * 
     * @param pb
     * @param selectBean
     * @return
     */
    @RequestMapping("/findAllDistrict")
    public ModelAndView findAllDistrict(PageBean pb, SelectBean selectBean) {
        this.districtService.findListByPageBean(pb, selectBean);
        return null;
    }

    /**
     * 根据城市ID查询所有的区县信息
     * 
     * @param cityId
     *            城市ID
     * @return 查询到的区县的集合
     */
    @RequestMapping("/queryDistrictByCityId")
    @ResponseBody
    public List<Object> queryDistrictByCityId(Long cityId, String districtName) {
        return this.districtService.queryDistrictByCityIdAndDistName(cityId, districtName);
    }

    /**
     * 保存区县信息
     * 
     */
    @RequestMapping("/saveDistrict")
    public ModelAndView saveDistrict(HttpServletRequest request) {
        // 区县名称
        String districtName = request.getParameter(addressName);
        // 区县排序
        String districtSort = request.getParameter(addressSort);
        // 城市id
        String cityId = request.getParameter(parentId);

        // 验证区县名称
        Matcher mat = checkRegExForT(districtName);
        // 验证区县排序
        Matcher mat2 = checkRegEx(NUMBER, districtSort);

        // 验证通过
        if ((!mat.find()) && (mat2.find())) {
            // 保存区县
            this.districtService.saveDistrict(districtName, cityId, districtSort);
        }

        return new ModelAndView(new RedirectView(findAllProvince));
    }

    /**
     * 删除区县信息
     * 
     * @param districtId
     *            待删除的区县信息的ID
     */
    @RequestMapping("/delDistrict")
    public ModelAndView delDistrict(Long districtId) {
        // 删除区县信息
        this.districtService.delDistrict(districtId);
        return new ModelAndView(new RedirectView(findAllProvince));
    }

    /**
     * 批量删除区县信息
     * 
     * @param districtIds
     *            待删除的区县信息的ID数组
     * @return
     */
    @RequestMapping("/batchDelDistrict")
    public ModelAndView batchDelDistrict(String[] districtIds) {
        // 批量删除区县信息
        this.districtService.batchDelDistrict(districtIds);
        return null;
    }

    /**
     * 更新区县信息
     * 
     * @param request
     *            请求对象
     * @return 查询所有的省份控制器
     */
    @RequestMapping("/updateDistrict")
    public ModelAndView updateDistrict(HttpServletRequest request) {
        // 区县名称
        String districtName = request.getParameter(addressName);
        // 区县id
        String districtId = request.getParameter(addressId);
        // 区县排序
        String distrcitSort = request.getParameter(addressSort);

        // 验证区县名称
        Matcher mat = checkRegExForT(districtName);
        // 验证区县排序
        Matcher mat2 = checkRegEx(NUMBER, distrcitSort);

        // 通过验证
        if ((!mat.find()) && (mat2.find())) {
            // 修改区县信息
            this.districtService.updateDistrict(districtId, districtName, distrcitSort);
        }

        // isNew新后台标记
        if (request.getParameter(ISNEW) != null) {
            return new ModelAndView(new RedirectView(findAllProvinceNew));
        } else {
            return new ModelAndView(new RedirectView(findAllProvince));
        }
    }

    /**
     * 验证区县名称是否可用
     * 
     * @return 验证的结果 true 表示可用 false 表示不可用
     */
    @RequestMapping("/checkDistrictName")
    @ResponseBody
    public boolean checkDistrictName(HttpServletRequest request) {
        return this.districtService.checkDistrictName(request.getParameter("districtName"));
    }

    /**
     * 根据区县ID查询所属的城市和省份ID
     * 
     * @param districtId
     *            区县ID
     * @return 查询到的地址帮助Bean
     */
    @RequestMapping("/queryAddressNameByDistrictId")
    @ResponseBody
    public AddressUtil queryAddressNameByDistrictId(Long districtId) {
        return this.districtService.queryAddressNameByDistrictId(districtId);
    }

    /**
     * 根据分页参数以及查询参数查询列表
     * 
     * @param pb
     *            分页参数
     * @param selectBean
     *            查询参数
     */
    @RequestMapping("/findAllStreet")
    public ModelAndView findAllStreet(PageBean pb, SelectBean selectBean) {
        // 根据分页参数以及查询参数查询列表
        this.streetService.findListByPageBean(pb, selectBean);
        return null;
    }

    /**
     * 根据区县ID查询所有的街道信息
     * 
     * @param districtId
     *            区县ID
     * @return 查询到的街道的集合
     */
    @RequestMapping("queryStreetByDistrictId")
    @ResponseBody
    public List<Object> queryStreetByDistrictId(Long districtId, String streetName) {
        return this.streetService.queryStreetByDistrictIdAndStreetName(districtId, streetName);
    }

    /**
     * 保存街道信息
     * 
     */
    @RequestMapping("/saveStreet")
    public ModelAndView saveStreet(HttpServletRequest request) {
        // 街道名称
        String streetName = request.getParameter(addressName);
        // 区县id
        String districtId = request.getParameter(parentId);
        // 街道排序
        String streetSort = request.getParameter(addressSort);

        // 验证街道名称
        Matcher mat = checkRegExForT(streetName);
        // 验证街道排序
        Matcher mat2 = checkRegEx(NUMBER, streetSort);

        // 通过验证
        if ((!mat.find()) && (mat2.find())) {
            // 保存街道信息
            this.streetService.saveStreet(streetName, districtId, streetSort);
        }

        return new ModelAndView(new RedirectView(findAllProvince));
    }

    /**
     * 删除街道信息
     * 
     * @param streetId
     *            街道的ID
     */
    @RequestMapping("delStreet")
    public ModelAndView delStreet(Long streetId) {
        // 删除街道信息
        this.streetService.delStreet(streetId);
        return new ModelAndView(new RedirectView(findAllProvince));
    }

    /**
     * 批量删除街道信息
     * 
     * @param streetIds
     *            街道的ID数组
     */
    @RequestMapping("/batchDelStreet")
    public ModelAndView batchDelStreet(String[] streetIds) {
        // 批量删除街道信息
        this.streetService.batchDelStreet(streetIds);
        return new ModelAndView(new RedirectView(findAllProvince));
    }

    /**
     * 更新街道信息
     * 
     * @param request
     *            请求对象
     */
    @RequestMapping("/updateStreet")
    public ModelAndView updateStreet(HttpServletRequest request) {
        // 街道名称
        String streetName = request.getParameter(addressName);
        // 街道id
        String streetId = request.getParameter(addressId);
        // 街道排序
        String streetSort = request.getParameter(addressSort);

        // 验证街道名称
        Matcher mat = checkRegExForT(streetName);
        // 验证街道排序
        Matcher mat2 = checkRegEx(NUMBER, streetSort);

        // 验证通过
        if ((!mat.find()) && (mat2.find())) {
            // 修改街道信息
            this.streetService.updateStreet(streetId, streetName, streetSort);
        }

        // isNew新后台标记
        if (request.getParameter(ISNEW) != null) {
            return new ModelAndView(new RedirectView(findAllProvinceNew));
        } else {
            return new ModelAndView(new RedirectView(findAllProvince));
        }
    }

    /**
     * 验证街道名称是否可用
     * 
     * @return 验证的结果 true表示可用 false表示不可用
     */
    @RequestMapping("/checkStreetName")
    @ResponseBody
    public boolean checkStreetName(HttpServletRequest request) {
        return this.streetService.checkStreetName(request.getParameter("streetName"));
    }

    /**
     * 根据街道ID查询所属的区县，城市和省份名称
     * 
     * @param streetId
     *            街道ID
     * @return 查询到的地址帮助Bean
     */
    @RequestMapping("/queryAddressNameByStreetId")
    @ResponseBody
    public AddressUtil queryAddressNameByStreetId(Long streetId) {
        return this.streetService.queryAddressNameByStreetId(streetId);
    }

    /**
     * 设置默认地址
     * 
     * @param districtId
     *            地址id
     * @return
     */
    @RequestMapping("setthedefault")
    @ResponseBody
    public int setTheDefault(Long districtId) {
        return addressService.insertAddressDefaultService(districtId);
    }

    public ProvinceService getProvinceService() {
        return provinceService;
    }

    @Resource(name = "ProvinceService")
    public void setProvinceService(ProvinceService provinceService) {
        this.provinceService = provinceService;
    }

    public CityService getCityService() {
        return cityService;
    }

    @Resource(name = "CityService")
    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }

    public DistrictService getDistrictService() {
        return districtService;
    }

    @Resource(name = "DistrictService")
    public void setDistrictService(DistrictService districtService) {
        this.districtService = districtService;
    }

    public StreetService getStreetService() {
        return streetService;
    }

    @Resource(name = "StreetService")
    public void setStreetService(StreetService streetService) {
        this.streetService = streetService;
    }
}
