/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.service.impl;

import com.ningpai.customer.dao.CustomerMapper;
import com.ningpai.goods.bean.FollowAndCityVo;
import com.ningpai.goods.bean.WareCity;
import com.ningpai.goods.bean.WareHouse;
import com.ningpai.goods.dao.ProductWareMapper;
import com.ningpai.goods.dao.WareCityMapper;
import com.ningpai.goods.dao.WareHouseMapper;
import com.ningpai.goods.service.WareHouseService;
import com.ningpai.goods.util.ValueUtil;
import com.ningpai.goods.vo.WareHouseVo;
import com.ningpai.other.bean.CityBean;
import com.ningpai.other.bean.DistrictBean;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import com.ningpai.util.SelectBean;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 仓库Service实现
 *
 * @author NINGPAI-YuanKangKang
 * @version 1.0
 * @since 2014年4月23日 上午10:41:30
 */
@Service("WareHouseService")
public class WareHouseServiceImpl implements WareHouseService {

    /**
     * 记录日志对象
     */
    private static final MyLogger LOGGER = new MyLogger(WareHouseServiceImpl.class);

    private static final String WARE_ID = "wareId";

    private WareHouseMapper houseMapper;

    private WareCityMapper cityMapper;

    @Resource(name = "ProductWareMapper")
    private ProductWareMapper productWareMapper;

    @Resource(name = "customerMapper")
    private CustomerMapper customerMapper;

    /**
     * 查询所有仓库
     *
     * @return
     */
    @Override
    public List<WareHouse> findWares() {
        // 执行方法
        return houseMapper.findWares();
    }

    /**
     * 判断该仓库下的商品是否被收藏
     *
     * @param wereId
     */
    @Override
    public List<FollowAndCityVo> selectFollow(Long wereId, BigDecimal productPrices, BigDecimal productVipPrices, Long goodsId) {
        // 执行查询方法返回结果
        return cityMapper.selectFollow(wereId, productPrices, productVipPrices, goodsId);
    }

    /**
     * 根据仓库查询该仓库对应的 区县
     *
     * @param wareId
     * @return
     */
    @Override
    public List<WareCity> selectByWareId(Long wareId) {
        // 根据仓库ID查询所有的关联记录
        return cityMapper.queryAllByWareId(wareId);
    }

    /**
     * 删除仓库信息
     *
     * @param wareId
     *            仓库ID
     * @param adminName
     *            操作人名称
     * @return 删除的行数
     */
    @Transactional
    public int deleteWareById(Long wareId, String adminName) {
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 设置参数
            map.put(WARE_ID, wareId);
            map.put("adminName", adminName);
            // 删除操作
            this.cityMapper.deleteCityByWareId(wareId);
            // 删除操作
            productWareMapper.deleteWareCity(wareId);
            // 删除操作
            return this.houseMapper.deleteByPrimaryKey(map);
        } finally {
            map = null;
        }
    }

    /**
     * 保存仓库信息
     *
     * @param wareHouse
     *            带保存的仓库实体
     * @return 插入的ID
     */
    @Transactional
    public Long saveWareHouse(WareHouse wareHouse, String adminName, Long[] distinctIds) {
        wareHouse.setExecName(adminName);
        // 新建仓库信息
        Long wareId = this.houseMapper.insertSelective(wareHouse);
        // 定义一个对象
        WareCity wareCity = null;
        try {
            if (null != distinctIds && distinctIds.length > 0) {
                for (int i = 0; i < distinctIds.length; i++) {
                    wareCity = new WareCity();
                    wareCity.setWareId(wareId);
                    wareCity.setCityId(distinctIds[i]);
                    wareCity.setDelFlag("0");
                    // 插入记录
                    this.cityMapper.insertSelective(wareCity);
                }
            }
            return wareId;
        } finally {
            // 打印日志
            LOGGER.info(ValueUtil.SAVEWAREHOUSE + adminName);
            wareId = null;
            wareCity = null;
        }
    }

    /**
     * 根据主键查询仓库信息
     *
     * @param wareId
     *            仓库ID
     * @return 查询到的仓库实体
     */
    public WareHouseVo selectWareByWareId(Long wareId) {
        // 根据主键查询仓库信息
        return this.houseMapper.selectByPrimaryKey(wareId);
    }

    /**
     * 更新仓库信息
     *
     * @param wareHouse
     *            待更新的仓库信息
     * @return 更新的行数
     */
    @Transactional
    public int updateWareHouse(WareHouse wareHouse, String adminName, Long[] districtIds) {
        wareHouse.setExecName(adminName);
        // 先删除未被选中的记录
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("distinctIds", districtIds);
        map.put(WARE_ID, wareHouse.getWareId());
        // 根据选择的城市ID和仓库ID删除未被选中的记录
        this.cityMapper.delNotInChecked(map);
        WareCity city = null;
        if (null != districtIds && districtIds.length > 0) {
            for (int i = 0; i < districtIds.length; i++) {
                map.clear();
                map.put(WARE_ID, wareHouse.getWareId());
                map.put("distinctId", districtIds[i]);
                // 根据仓库ID和城市ID查询是否已经关联过记录
                if (this.cityMapper.queryByWareIdAndDistinctId(map) <= 0) {
                    city = new WareCity();
                    city.setWareId(wareHouse.getWareId());
                    city.setCityId(districtIds[i]);
                    // 插入记录
                    this.cityMapper.insertSelective(city);
                }
            }
        }
        // 打印日志
        LOGGER.info(ValueUtil.UPDATEWAREHOUSE + adminName);
        // 更新仓库信息
        return this.houseMapper.updateByPrimaryKeySelective(wareHouse);
    }

    /**
     * 查询所有的仓库记录
     *
     * @return 查询到的集合
     */
    public List<WareHouse> queryAllWareHouse() {
        // 查询所有的仓库信息
        return this.houseMapper.queryAllWareHouse();
    }

    /**
     * 根据参数查询仓库数量
     *
     * @return 查询到的数量
     */
    public int queryCountByParams() {
        // 根据参数查询仓库的数量
        return this.houseMapper.queryCountByParams(null);
    }

    /**
     * 根据分页信息和查询参宿查询仓库集合
     *
     * @param pb
     *            分页帮助Bean
     * @param selectBean
     *            查询Bean
     * @return 封装近查询到的集合的PageBean
     */
    public PageBean queryAllWareHouseByPageBean(PageBean pb, SelectBean selectBean) {
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 设置参数
            map.put(ValueUtil.CONDITION, selectBean.getCondition());
            map.put(ValueUtil.SEARCHTEXT, selectBean.getSearchText());
            // 设置总行数
            pb.setRows(this.houseMapper.queryCountByParams(map));
            // 设置开始行数
            map.put(ValueUtil.STARTROWNUM, pb.getStartRowNum());
            // 设置结束行数
            map.put(ValueUtil.ENDROWNUM, pb.getEndRowNum());
            // 设置集合数据
            pb.setList(this.houseMapper.queryAllWareHouseByPageBean(map));
            return pb;
        } finally {
            map = null;
        }
    }

    /**
     * 验证仓库名称是否可用
     *
     * @param wareName
     *            仓库名称
     * @return true 可用 false不可用
     */
    public boolean checkWareName(String wareName) {
        // 查询仓库
        List<WareHouse> wh = this.houseMapper.queryWareCountByWareName(wareName);
        // 判断仓库名称是否存在
        if (wh != null && !wh.isEmpty()) {
            // 存在返回false
            return false;
        } else {
            // 不存在返回true
            return true;
        }
    }

    /**
     * 查询所有已经保存的仓库的所在城市
     *
     * @return List
     */
    @Override
    public List<DistrictBean> getAllWareHouseDistrict() {
        List<DistrictBean> c = customerMapper.selectAllDistrict();
        List<WareCity> wc = this.houseMapper.getAllWareHouseDistrict();
        for (int i = 0; i < c.size(); i++) {
            if (wc != null && !wc.isEmpty()) {
                for (int j = 0; j < wc.size(); j++) {
                    if (c.get(i).getDistrictId().equals(wc.get(j).getCityId())) {
                        c.get(i).setChkDisabled(true);
                    }
                }
            }
        }
        return c;
    }

    /**
     * 批量删除仓库信息
     *
     * @param wareIds
     *            待删除的仓库ID数组
     * @param adminName
     *            操作人名称
     * @return 删除的行数
     */
    @Transactional
    public int batchDelWare(Long[] wareIds, String adminName) {
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 设置参数
            map.put("wareIds", wareIds);
            map.put("adminName", adminName);
            // 批量删除仓库信息
            return this.houseMapper.batchDelWare(map);
        } finally {
            // 打印日志
            LOGGER.info(ValueUtil.BATCHDELWARE + adminName);
            map = null;
        }
    }

    /**
     * 查询添加或者修改仓库不需要的id
     *
     * @param wareId
     *            地址id
     * @return
     */
    @Override
    public List<DistrictBean> selectCityIdByWareId(Long wareId) {
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        // 设置参数
        map.put(WARE_ID, wareId);
        // List<Long> list=cityMapper.selectCityId(map);
        // //是否需要查询所有的地区信息
        // if(list==null||list.size()==0){
        // return customerMapper.selectAllDistrict();
        // }
        // return customerMapper.selectDistrictInId(list);
        // 查询所有区县
        return customerMapper.selectAllDistrict();
    }

    /**
     * 查询添加或者修改仓库不需要的id
     *
     * @param wareId
     *            地址id
     * @return
     */
    @Override
    public List<CityBean> selectCityIdByDid(Long wareId) {
        // 定义一个Hashap集合
        Map<String, Object> map = new HashMap<String, Object>();
        // 设置参数
        map.put(WARE_ID, wareId);
        // List<Long> list=cityMapper.selectCityId(map);
        // //是否需要查询所有的地区信息
        // if(list==null||list.size()==0){
        // return customerMapper.selectAllCity();
        // }
        // return customerMapper.selectAllCityByDistrict(list);
        // 查询所有城市
        return customerMapper.selectAllCity();
    }

    /**
     * 判断标识是否存在
     *
     * @param identifyId
     *            标识
     * @return 查询到的结果条数
     */
    @Override
    public int identifyIsExist(String identifyId) {
        // 判断标识是否已经存在
        return houseMapper.identifyIsExist(identifyId);
    }

    /**
     * 检测仓库名称是否重复
     *
     * @param wareName
     * @param wareId
     * @return boolean
     */
    @Override
    public boolean checkWareNameHaveId(String wareName, Long wareId) {
        // 查询仓库名称
        List<WareHouse> s = this.houseMapper.queryWareCountByWareName(wareName);
        // 判断仓库是否存在
        if (s != null && !s.isEmpty()) {
            // 如果存在判断是否是自己
            if (s.size() == 1) {
                // 判断是自己 通过
                if (s.get(0).getWareId().equals(wareId)) {
                    return true;
                } else {
                    // 不是自己失败
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    public WareHouseMapper getHouseMapper() {
        return houseMapper;
    }

    @Resource(name = "WareHouseMapper")
    public void setHouseMapper(WareHouseMapper houseMapper) {
        this.houseMapper = houseMapper;
    }

    public WareCityMapper getCityMapper() {
        return cityMapper;
    }

    @Resource(name = "WareCityMapper")
    public void setCityMapper(WareCityMapper cityMapper) {
        this.cityMapper = cityMapper;
    }

    /**
     * 查询仓库ID
     *
     * @param distinctId
     * @return Long
     */
    @Override
    public Long selectWareIdByDistinctId(Long distinctId) {
        // 根据当前地区查询所在库存
        return houseMapper.selectWareIdByDistinctId(distinctId);
    }

}
