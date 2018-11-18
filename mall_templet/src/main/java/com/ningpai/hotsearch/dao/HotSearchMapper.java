package com.ningpai.hotsearch.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.hotsearch.bean.HotSearch;

/**
 * 热门搜索
 * 
 * @author ggn
 *
 */
public interface HotSearchMapper {
    /**
     * 根据热门搜索Id删除
     * 
     * @param hotSearchId
     * @return
     */
    int deleteByPrimaryKey(Long hotSearchId);

    /**
     * 批量删除
     * 
     * @param list
     * @return
     */
    int batchDelHotSearch(List<Object> list);

    /**
     * 添加搜索记录
     * 
     * @param record
     * @return
     */
    int insert(HotSearch record);

    /**
     * 选择性添加搜索记录
     * 
     * @param record
     * @return
     */
    int insertSelective(HotSearch record);

    /**
     * 根据Id选择修改属性
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(HotSearch record);

    /**
     * 根据主键修改所有热门搜索记录
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKey(HotSearch record);

    /**
     * 根据主键查询热门搜索
     * 
     * @param hotSearchId
     * @return
     */
    HotSearch selectByPrimaryKey(Long hotSearchId);

    /**
     * 根据条件查询热门搜索的总条数
     * 
     * @param map
     * @return
     */
    int selectHotSearchCount(Map<String, Object> map);

    /**
     * 热门搜索查询分页
     * 
     * @param map
     * @return
     */
    List<Object> selectHotBySelectivePageSize(Map<String, Object> map);

    /**
     * 根据模板ID、频道ID查询热门搜索-前台展示用
     * 
     * @param map
     * @return
     */
    List<HotSearch> selectHotBySelectiveForSite(Map<String, Object> map);

}
