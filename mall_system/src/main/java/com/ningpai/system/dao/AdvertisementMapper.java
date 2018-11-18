package com.ningpai.system.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.system.bean.Advertisement;
import com.ningpai.system.util.SelectBean;

/**
 * 广告设置数据接口层
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2014年1月14日 下午6:55:51
 * @version 1.0
 */
public interface AdvertisementMapper {

    /**
     * 删除广告信息
     * 
     * @param adverId
     * @return int
     */
    int deleteByPrimaryKey(Long adverId);

    /**
     * 添加首页广告
     * 
     * @param record
     * @return int
     */
    int insert(Advertisement record);

    /**
     * 添加首页广告
     * 
     * @param record
     * @return int
     */
    int insertSelective(Advertisement record);

    /**
     * 根据Id查询首页广告信息
     * 
     * @param adverId
     * @return Advertisement
     */
    Advertisement selectByPrimaryKey(Long adverId);

    /**
     * 修改首页广告信息
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(Advertisement record);

    /**
     * 修改首页广告信息
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(Advertisement record);

    /**
     * 分页查询广告信息
     * 
     * @param map
     * @return
     */
    List<Object> findByPageBean(Map<String, Object> map);

    /**
     * 查询总行数
     * 
     * @return int
     */
    int findTotalCount(SelectBean selectBean);
}
