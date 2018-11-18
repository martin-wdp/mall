package com.qpmall.address.dao.impl;

import com.ningpai.manager.base.BasicSqlSupport;
import com.qpmall.address.bean.AddressBean;
import com.qpmall.address.dao.SimpleGetAddressMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ly-qpmall on 2016/1/9.
 */
@Repository("simpleGetAddressMapper")
public class SimpleGetAddressMapperImpl  extends BasicSqlSupport  implements SimpleGetAddressMapper{
    /**
     * 查询所有的省份
     *
     * @return
     */
    @Override
    public List<AddressBean> selectAllProvince() {
        return this.selectList("com.qpmall.address.dao.SimpleGetAddressMapper.selectAllProvince");
    }
    /**
     * 根据Id查询省份
     *
     * @return
     */
    @Override
    public AddressBean selectProvinceByPid(Long addressId) {
        return this.selectOne("com.qpmall.address.dao.SimpleGetAddressMapper.selectProvinceByPid",addressId);
    }
    /**
     * 查询所有城市
     * @return
     */
    @Override
    public List<AddressBean> selectAllCity() {
        return this.selectList("com.qpmall.address.dao.SimpleGetAddressMapper.selectAllCity");
    }
    /**
     * 根据省份Id查询所属的城市
     * @param parentId
     * @return
     */
    @Override
    public List<AddressBean> selectCityByPid(Long parentId) {
        return this.selectList("com.qpmall.address.dao.SimpleGetAddressMapper.selectCityByPid",parentId);
    }
    /**
     * 根据城市ID查询城市
     * @param addressId
     * @return
     */
    @Override
    public AddressBean selectCityByCid(Long addressId) {
        return this.selectOne("com.qpmall.address.dao.SimpleGetAddressMapper.selectCityByCid",addressId);
    }
    /**
     * 查询所有区
     * @return
     */
    @Override
    public List<AddressBean> selectAllDistrict() {
        return this.selectList("com.qpmall.address.dao.SimpleGetAddressMapper.selectAllDistrict");
    }
    /**
     * 根据城市ID查询所属区
     * @param parentId
     * @return
     */
    @Override
    public List<AddressBean> selectDistrictByCid(Long parentId) {
        return this.selectList("com.qpmall.address.dao.SimpleGetAddressMapper.selectDistrictByCid",parentId);
    }
    /**
     * 根据区ID查询区
     * @param addressId
     * @return
     */
    @Override
    public AddressBean selectDistrictById(Long addressId) {
        return this.selectOne("com.qpmall.address.dao.SimpleGetAddressMapper.selectDistrictById",addressId);
    }
}
