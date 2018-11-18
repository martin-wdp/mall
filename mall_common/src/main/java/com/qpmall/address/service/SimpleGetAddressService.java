package com.qpmall.address.service;

import com.qpmall.address.bean.AddressBean;

import java.util.List;

/**
 * Created by ly-qpmall on 2016/1/9.
 */
public interface SimpleGetAddressService {
    /**
     * 查询所有的省份
     *
     * @return
     */
    List<AddressBean> selectAllProvince();

    /**
     * 根据Id查询省份
     *
     * @return
     */
    AddressBean selectProvinceByPid(Long addressId);

    /**
     * 查询所有城市
     * @return
     */
    List<AddressBean> selectAllCity();

    /**
     * 根据省份Id查询所属的城市
     * @param parentId
     * @return
     */
    List<AddressBean> selectCityByPid(Long parentId);

    /**
     * 根据城市ID查询城市
     * @param addressId
     * @return
     */
    AddressBean selectCityByCid(Long addressId);

    /**
     * 查询所有区
     * @return
     */
    List<AddressBean> selectAllDistrict();

    /**
     * 根据城市ID查询所属区
     * @param parentId
     * @return
     */
    List<AddressBean> selectDistrictByCid(Long parentId);

    /**
     * 根据区ID查询区
     * @param addressId
     * @return
     */
    AddressBean selectDistrictById(Long addressId);

}
