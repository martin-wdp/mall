/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.system.service;

import java.util.List;

import com.ningpai.system.bean.Province;
import com.ningpai.system.util.SelectBean;
import com.ningpai.util.PageBean;

/**
 * 地区管理Service
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年2月12日 下午4:22:56
 * @version 1.0
 */
public interface ProvinceService {
    /**
     * 保存省份信息
     * 
     * @param provinceName
     *            需要保存的省份实体名称
     * @return 返回插入的行数
     */
    int saveProvince(String provinceName, String provinceSort);

    /**
     * 删除省份信息
     * 
     * @param provinceId
     *            待删除的省份的主键ID
     * @return 删除的行数
     */
    int delProvince(Long provinceId);

    /**
     * 更新省份信息
     * 
     * @param provinceId
     *            待更新的省份信息的实体Id
     * @param provinceName
     *            待更新的省份信息的实体名称
     * @return 更新的行数
     */
    int updateProvince(String provinceId, String provinceName, String provinceSort);

    /**
     * 根据主键ID查询省份信息
     * 
     * @param provinceId
     *            省份的主键ID
     * @return 查询到的省份信息的实体
     */
    Province findProvinceByPrimaryKey(Long provinceId);

    /**
     * 根据分页实体以及查询参数查询列表
     * 
     * @param pb
     *            分页帮助类
     * @return 封住了查询到的列表的实体
     */
    PageBean findListByPageBean(PageBean pb, SelectBean selectBean);

    /**
     * 批量删除省份实体
     * 
     * @param provinceIds
     *            省份ID数组
     * @return 删除的行数
     */
    int batchDelProvince(String[] provinceIds);

    /**
     * 验证省份名称是否可用
     * 
     * @param provinceName
     *            待验证的省份名称
     * @return 验证的结果 可用返回true 否则返回false
     */
    boolean checkProvinceName(String provinceName);

    /**
     * 查询所有的省份信息
     * 
     * @return 查询到的列表
     */
    List<Object> queryAllProvince();

    /**
     * 查询所有省份、城市、地区、街道
     * @param pb 分页对象
     * @param selectBean 查询对象
     * @return 分页列表对象
     */
    PageBean findListByPageBeanNew(PageBean pb, SelectBean selectBean);

    /**
     * 根据名称查询所有省份
     * @param provinceName 省份名称
     * @return
     */
    List<Object> queryAllProvinceByName(String provinceName);
}
