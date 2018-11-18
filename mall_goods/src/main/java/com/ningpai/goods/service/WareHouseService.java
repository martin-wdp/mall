/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.service;

import com.ningpai.goods.bean.FollowAndCityVo;
import com.ningpai.goods.bean.WareCity;
import com.ningpai.goods.bean.WareHouse;
import com.ningpai.goods.vo.WareHouseVo;
import com.ningpai.other.bean.CityBean;
import com.ningpai.other.bean.DistrictBean;
import com.ningpai.util.PageBean;
import com.ningpai.util.SelectBean;

import java.math.BigDecimal;
import java.util.List;

/**
 * 仓库Service
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年4月23日 上午10:32:08
 * @version 1.0
 */
public interface WareHouseService {
    /**
     * 查询所有仓库
     * 
     * @return
     */
    List<WareHouse> findWares();

    /**
     * 判断该仓库下的商品是否被收藏
     * 
     * @param wereId
     */
    List<FollowAndCityVo> selectFollow(Long wereId, BigDecimal productPrices, BigDecimal productVipPrices,
            Long goodsId);

    /***
     * 根据仓库查询该仓库对应的 区县
     * 
     * @param wareId
     * @return
     */
    List<WareCity> selectByWareId(Long wareId);

    /**
     * 删除仓库信息
     * 
     * @param wareId
     *            仓库ID
     * @param adminName
     *            操作人名称
     * @return 删除的行数
     */
    int deleteWareById(Long wareId, String adminName);

    /**
     * 批量删除仓库信息
     * 
     * @param wareIds
     *            待删除的仓库ID数组
     * @param adminName
     *            操作人名称
     * @return 删除的行数
     */
    int batchDelWare(Long[] wareIds, String adminName);

    /**
     * 保存仓库信息
     * 
     * @param wareHouse
     *            带保存的仓库实体
     * @return 插入的ID
     */
    Long saveWareHouse(WareHouse wareHouse, String adminName, Long[] distinctIds);

    /**
     * 根据主键查询仓库信息
     * 
     * @param wareId
     *            仓库ID
     * @return 查询到的仓库实体
     */
    WareHouseVo selectWareByWareId(Long wareId);

    /**
     * 更新仓库信息
     * 
     * @param wareHouse
     *            待更新的仓库信息
     * @return 更新的行数
     */
    int updateWareHouse(WareHouse wareHouse, String adminName,
            Long[] districtIds);

    /**
     * 查询所有的仓库记录
     * 
     * @return 查询到的集合
     */
    List<WareHouse> queryAllWareHouse();

    /**
     * 根据参数查询仓库数量
     * 
     * @return 查询到的数量
     */
    int queryCountByParams();

    /**
     * 根据分页信息和查询参宿查询仓库集合
     * 
     * @param pb
     *            分页帮助Bean
     * @param selectBean
     *            查询Bean
     * @return 封装近查询到的集合的PageBean
     */
    PageBean queryAllWareHouseByPageBean(PageBean pb, SelectBean selectBean);

    /**
     * 验证仓库名称是否可用
     * 
     * @param wareName
     *            仓库名称
     * @return true 可用 false不可用
     */
    boolean checkWareName(String wareName);

    /**
     * 判断标识是否存在
     * 
     * @param identifyId
     *            标识
     * @return 查询到的结果条数
     */
    int identifyIsExist(String identifyId);

    /**
     * 查询添加或者修改仓库不需要的id
     * 
     * @param wareId
     *            地址id
     * @return
     */
    List<DistrictBean> selectCityIdByWareId(Long wareId);

    /**
     * 查询添加或者修改仓库不需要的id
     * 
     * @param wareId
     *            地址id
     * @return
     */
    List<CityBean> selectCityIdByDid(Long wareId);

    /**
     * 查询所有已经保存的仓库的所在城市
     * 
     * @return List
     */
    List<DistrictBean> getAllWareHouseDistrict();

    /**
     * 检测仓库名称是否重复
     * 
     * @param wareName
     * @param wareId
     * @return boolean
     */
    boolean checkWareNameHaveId(String wareName, Long wareId);

    /**
     * 查询仓库ID
     * 
     * @param distinctId
     * @return Long
     */
    Long selectWareIdByDistinctId(Long distinctId);
}
