package com.qpmall.address.controller;

import com.qpmall.address.bean.AddressBean;
import com.qpmall.address.service.SimpleGetAddressService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ly-qpmall on 2016/1/9.
 */

@Controller
public class SimpleGetAddressController {

    @Resource(name = "simpleGetAddressService")
     private SimpleGetAddressService simpleGetAddressService;
    /**
     * 查询所有的省份
     *
     * @return
     */
    @RequestMapping("simpleGetAllProvince")
    @ResponseBody
    List<AddressBean> selectAllProvince(){
       return simpleGetAddressService.selectAllProvince();
    }

    /**
     * 根据Id查询省份
     *
     * @return
     */
    @RequestMapping("simpleGetProvinceByPid")
    @ResponseBody
    AddressBean selectProvinceByPid(Long addressId){
        return simpleGetAddressService.selectProvinceByPid(addressId);
    }

    /**
     * 查询所有城市
     * @return
     */
    @RequestMapping("simpleGetAllCity")
    @ResponseBody
    List<AddressBean> selectAllCity(){
        return simpleGetAddressService.selectAllCity();
    }

    /**
     * 根据省份Id查询所属的城市
     * @param parentId
     * @return
     */
    @RequestMapping("simpleGetCityByPid")
    @ResponseBody
    List<AddressBean> selectCityByPid(Long parentId){
        return simpleGetAddressService.selectCityByPid(parentId);
    }

    /**
     * 根据城市ID查询城市
     * @param addressId
     * @return
     */
    @RequestMapping("simpleGetCityByCid")
    @ResponseBody
    AddressBean selectCityByCid(Long addressId){
        return simpleGetAddressService.selectCityByCid(addressId);
    }

    /**
     * 查询所有区
     * @return
     */
    @RequestMapping("simpleGetAllDistrict")
    @ResponseBody
    List<AddressBean> selectAllDistrict(){
        return simpleGetAddressService.selectAllDistrict();
    }

    /**
     * 根据城市ID查询所属区
     * @param parentId
     * @return
     */
    @RequestMapping("simpleGetDistrictByCid")
    @ResponseBody
    List<AddressBean> selectDistrictByCid(Long parentId){
        return simpleGetAddressService.selectDistrictByCid(parentId);
    }

    /**
     * 根据区ID查询区
     * @param addressId
     * @return
     */
    @RequestMapping("simpleGetDistrictById")
    @ResponseBody
    AddressBean selectDistrictById(Long addressId){
        return simpleGetAddressService.selectDistrictById(addressId);
    }
}
