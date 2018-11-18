package com.ningpai.temp.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.temp.bean.ClassifyBar;
import com.ningpai.temp.vo.ClassifyBarVo;

/**
 * DAO-分类导航
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年5月6日下午4:00:30
 */
public interface ClassifyBarMapper {
    /**
     * 删除分类导航
     * 
     * @param classifyBarId
     * @return
     */
    int deleteByPrimaryKey(Long classifyBarId);

    /**
     * 添加分类导航
     * 
     * @param record
     * @return
     */
    int insert(ClassifyBar record);

    /**
     * 添加分类导航-字段可选
     * 
     * @param record
     * @return
     */
    int insertSelective(ClassifyBar record);

    /**
     * 修改分类导航-字段可选
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(ClassifyBar record);

    /**
     * 修改分类导航
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKey(ClassifyBar record);

    /**
     * 根据主键查询分类导航
     * 
     * @param classifyBarId
     * @return
     */
    ClassifyBar selectByPrimaryKey(Long classifyBarId);

    /**
     * 根据分页参数和频道ID、模板ID查询分类导航总行数<br/>
     * tempId 模板ID<br/>
     * channelId 频道ID<br/>
     * 
     * @param map
     * @return
     */
    Integer selectClassifyBarCountByParam(Map<String, Object> map);

    /**
     * 根据分页参数和频道ID、模板ID查询分类导航<br/>
     * tempId 模板ID<br/>
     * channelId 频道ID<br/>
     * startRowNum 起始行数<br/>
     * endRowNum 查询条数<br/>
     * 
     * @param map
     * @return
     */
    List<Object> selectClassifyBarByParam(Map<String, Object> map);

    /**
     * 根据分页参数和频道ID、模板ID查询分类导航<br/>
     * tempId 模板ID<br/>
     * channelId 频道ID<br/>
     * 
     * @param map
     * @return
     */
    List<ClassifyBarVo> selectClassifyBarByParamSite(Map<String, Object> map);

    /**
     * 根据分页参数和频道ID、模板ID查询分类导航<br/>
     * tempId 模板ID<br/>
     * channelId 频道ID<br/>
     *
     * @param map
     * @return
     */
    List<ClassifyBarVo> selectClassifyBarByParamSite2(Map<String, Object> map);


    List<ClassifyBarVo> getIndexClassificationByfir(Long tempId);
    /**
     * 调用存储过程级联删除分类导航
     * 
     * @param classifyBarId
     * @return
     */
    int deleteByPrimaryKeyAndPro(Long classifyBarId);
    
    /**
     * 删除店铺分类导航
     * @param map 条件
     * @return int
     */
    int deleteClassBarById(Map<String,Object> map);
    

}
