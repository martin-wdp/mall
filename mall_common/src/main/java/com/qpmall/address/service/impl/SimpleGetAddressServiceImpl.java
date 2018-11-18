package com.qpmall.address.service.impl;

import com.qpmall.address.bean.AddressBean;
import com.qpmall.address.dao.SimpleGetAddressMapper;
import com.qpmall.address.service.SimpleGetAddressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ly-qpmall on 2016/1/9.
 */
@Service("simpleGetAddressService")
public class SimpleGetAddressServiceImpl implements SimpleGetAddressService{

    @Resource(name = "simpleGetAddressMapper")
    private SimpleGetAddressMapper simpleGetAddressMapper;
    /**
     * 查询所有的省份
     *
     * @return
     */
    @Override
    public List<AddressBean> selectAllProvince() {
        return simpleGetAddressMapper.selectAllProvince();
    }
    /**
     * 根据Id查询省份
     *
     * @return
     */
    @Override
    public AddressBean selectProvinceByPid(Long addressId) {
        return simpleGetAddressMapper.selectProvinceByPid(addressId);
    }
    /**
     * 查询所有城市
     * @return
     */
    @Override
    public List<AddressBean> selectAllCity() {
        return simpleGetAddressMapper.selectAllCity();
    }
    /**
     * 根据省份Id查询所属的城市
     * @param parentId
     * @return
     */
    @Override
    public List<AddressBean> selectCityByPid(Long parentId) {
        return simpleGetAddressMapper.selectCityByPid(parentId);
    }
    /**
     * 根据城市ID查询城市
     * @param addressId
     * @return
     */
    @Override
    public AddressBean selectCityByCid(Long addressId) {
        return simpleGetAddressMapper.selectCityByCid(addressId);
    }
    /**
     * 查询所有区
     * @return
     */
    @Override
    public List<AddressBean> selectAllDistrict() {
        return simpleGetAddressMapper.selectAllDistrict();
    }
    /**
     * 根据城市ID查询所属区
     * @param parentId
     * @return
     */
    @Override
    public List<AddressBean> selectDistrictByCid(Long parentId) {
        return simpleGetAddressMapper.selectDistrictByCid(parentId);
    }
    /**
     * 根据区ID查询区
     * @param addressId
     * @return
     */
    @Override
    public AddressBean selectDistrictById(Long addressId) {
        return simpleGetAddressMapper.selectDistrictById(addressId);
    }
}
