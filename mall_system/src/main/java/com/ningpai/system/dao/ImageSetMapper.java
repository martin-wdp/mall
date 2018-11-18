package com.ningpai.system.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.system.bean.ImageSet;
import com.ningpai.system.util.SelectBean;

/**
 * 图片设置接口层
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月17日 上午9:53:42
 * @version 1.0
 */
public interface ImageSetMapper {
    /**
     * 删除图片设置
     * 
     * @param ruleId
     * @return
     */
    int deleteByPrimaryKey(Long ruleId);

    /**
     * 添加图片设置
     * 
     * @param record
     * @return
     */
    int insert(ImageSet record);

    /**
     * 添加图片设置--可选设置
     * 
     * @param record
     * @return
     */
    int insertSelective(ImageSet record);

    /**
     * 查询单个图片设置
     * 
     * @param ruleId
     * @return
     */
    ImageSet selectByPrimaryKey(Long ruleId);

    /**
     * 修改图片设置--可选字段
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(ImageSet record);

    /**
     * 修改图片设置
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKey(ImageSet record);

    /**
     * 分页显示图片设置
     * 
     * @return List
     */
    List<Object> findByPageBean(Map<String, Object> map);

    /**
     * 查询总行数
     * 
     * @return
     */
    int findTotalCount(SelectBean selectBean);
}
