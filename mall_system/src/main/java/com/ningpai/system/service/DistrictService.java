/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.system.service;

import java.util.List;

import com.ningpai.system.bean.District;
import com.ningpai.system.util.AddressUtil;
import com.ningpai.system.util.SelectBean;
import com.ningpai.system.vo.DistrictVo;
import com.ningpai.util.PageBean;

/**
 * 区县Service
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年2月13日 上午10:53:45
 * @version 1.0
 */
public interface DistrictService {
    /**
     * 增加区县信息
     * 
     * @param districtName
     *            待增加的实体名称
     * @param cityId
     *            所属的城市ID
     * @return 增加的行数
     */
    int saveDistrict(String districtName, String cityId, String districtSort);

    /**
     * 删除区县信息
     * 
     * @param districtId
     *            待删除的区县信息的ID
     * @return 删除的行数
     */
    int delDistrict(Long districtId);

    /**
     * 更新区县信息
     * 
     * @param districtId
     *            待更新的区县信息的实体ID
     * @param districtName
     *            待更新的区县信息的实体名称
     * @return 更新的行数
     */
    int updateDistrict(String districtId, String districtName, String districtSort);

    /**
     * 根据主键查询实体
     * 
     * @param districtId
     *            区县的主键ID
     * @return 查询到的实体
     */
    District findDistrictByPrimaryKey(Long districtId);

    /**
     * 根据分页辅助Bean以及查询参数查询列表
     * 
     * @param pb
     *            分页辅助类
     * @param selectBean
     *            封装了查询参数的Bean
     * @return 封装进查询到的列表的分页辅助类
     */
    PageBean findListByPageBean(PageBean pb, SelectBean selectBean);

    /**
     * 批量删除区县信息
     * 
     * @param districtIds
     *            待删除的去区县信息的抓奸ID的数组
     * @return 删除的行数
     */
    int batchDelDistrict(String[] districtIds);

    /**
     * 根据城市信息查询所有的区县信息
     * 
     * @param cityId
     *            城市信息的ID
     * @return 查询到的区县信息的集合
     */
    List<DistrictVo> queryDistrictByCityId(Long cityId);

    /**
     * 验证区县名称是否可用
     * 
     * @param districtName
     *            待验证的区县名称
     * @return 验证的结果 可用返回true 否则返回false
     */
    boolean checkDistrictName(String districtName);

    /**
     * 根据区县ID查询所属的城市和省份名称
     * 
     * @param districtId
     * @return
     */
    AddressUtil queryAddressNameByDistrictId(Long districtId);

    /**
     * 根据城市id和县区名称查询所有县区
     * @param cityId 城市id
     * @param districtName 县区名称
     * @return
     */
    List<Object> queryDistrictByCityIdAndDistName(Long cityId, String districtName);
}
