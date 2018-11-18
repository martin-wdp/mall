package com.ningpai.information.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.information.bean.Information;
import com.ningpai.information.vo.InformationVo;

/**
 * 资讯DAO
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年02月14日 16:05:10
 * @version
 */
public interface InformationMapper {
    /**
     * 根据主键删除
     * 
     * @param infoId
     * @return
     */
    int deleteByPrimaryKey(Long infoId);

    /**
     * 添加
     * 
     * @param infoId
     * @return
     */
    int insert(Information infoId);

    /**
     * 添加-字段可选
     * 
     * @param record
     * @return
     */
    int insertSelective(Information record);

    /**
     * 根据主键查询
     * 
     * @param infoId
     * @return
     */
    Information selectByPrimaryKey(Long infoId);

    /**
     * 更新-字段可选-不包含内容
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Information record);

    /**
     * 更新-包含内容
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKeyWithBLOBs(Information record);

    /**
     * 更新-不包含内容
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKey(Information record);

    /**
     * 查询全部
     * 
     * @return
     */
    List<InformationVo> selectAll();

    /**
     * 根据map参数查询所有行数
     * 
     * @param map
     * @return
     */
    Integer queryTotalCount(Map<String, Object> map);

    /**
     * 根据分页参数查询资讯列表
     * 
     * @param map
     * @return
     */
    List<Object> queryByPageBean(Map<String, Object> map);

    /**
     * 根据栏目ID查询资讯数量
     * 
     * @param infoTypeId
     * @return
     */
    Integer selectInfoCountByTypeId(Long infoTypeId);

    /**
     * 根据文章标题查询文章数量
     * 
     * @param title
     * @return
     */
    Integer selectInfoCountByTitle(String title);

    /**
     * 根据栏目ID查询文章
     * 
     * @param infoTypeId
     * @return
     */
    List<InformationVo> selectByInfoType(Long infoTypeId);
}
