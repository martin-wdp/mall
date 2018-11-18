package com.ningpai.system.dao;

import java.util.List;

import com.ningpai.system.bean.AdverType;

/**
 * 首页广告图片类型数据接口层
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2014年1月14日 下午6:58:07
 * @version 1.0
 */
public interface AdverTypeMapper {
    /**
     * 删除首页广告图片
     * 
     * @param atId
     * @return
     */
    int deleteByPrimaryKey(Long atId);

    /**
     * 添加首页广告图片
     * 
     * @param record
     * @return
     */
    int insert(AdverType record);

    /**
     * 添加首页广告图片
     * 
     * @param record
     * @return
     */
    int insertSelective(AdverType record);

    /**
     * 查找单个首页广告图片
     * 
     * @param atId
     * @return
     */
    AdverType selectByPrimaryKey(Long atId);

    /**
     * 修改首页广告图片
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(AdverType record);

    /**
     * 修改首页广告图片
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKey(AdverType record);

    /**
     * 查询所有广告分类
     * 
     * @return List
     */
    List<AdverType> selectAll();
}
