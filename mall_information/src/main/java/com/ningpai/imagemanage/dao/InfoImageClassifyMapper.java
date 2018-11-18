package com.ningpai.imagemanage.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.imagemanage.bean.InfoImageClassify;
import com.ningpai.imagemanage.vo.InfoImageClassifyVo;

/**
 * DAO-资源图片类型
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月25日上午11:28:45
 */
public interface InfoImageClassifyMapper {
    /**
     * 根据ID删除
     * 
     * @param classifyId
     * @return
     */
    int deleteByPrimaryKey(Long classifyId);

    /**
     * 添加
     * 
     * @param record
     * @return
     */
    int insert(InfoImageClassify record);

    /**
     * 添加-字段可选
     * 
     * @param record
     * @return
     */
    int insertSelective(InfoImageClassify record);

    /**
     * 修改-字段可选
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(InfoImageClassify record);

    /**
     * 修改
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKey(InfoImageClassify record);

    /**
     * 根据主键查询
     * 
     * @param classifyId
     * @return
     */
    InfoImageClassify selectByPrimaryKey(Long classifyId);

    /**
     * 查询图片管理分类总行数
     * 
     * @return
     */
    Integer selectImageClassifyCountByParam();

    /**
     * 根据分类ID查询子分类
     * 
     * @param classifyId
     * @return
     */
    List<InfoImageClassifyVo> selectByParentId(Long classifyId);

    /**
     * 查询图片管理分类
     * 
     * @param map
     * @return
     */
    List<Object> selectImageClassifyByParam(Map<String, Object> map);

    /**
     * 查询所有图片管理分类<br/>
     * 分类管理选择父分类
     * 
     * @return
     */
    List<InfoImageClassify> selectAllImageClassify();

    /**
     * 查询所有图片管理分类<br/>
     * 上传图片选择分类用
     * 
     * @return
     */
    List<InfoImageClassify> selectAllImageClassifyForImg();
}
