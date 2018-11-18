package com.ningpai.information.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.information.bean.InformationType;
import com.ningpai.information.vo.InformationTypeVo;

/**
 * 资讯栏目DAO
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年02月14日 11:18:35
 * @version
 */
public interface ThirdInforTypeMapper {
    /**
     * 根据主键删除资讯栏目
     * 
     * @param infoTypeId
     *            资讯栏目编号
     * @return
     */
    int deleteByPrimaryKey(Long infoTypeId);

    /**
     * 添加资讯栏目
     * 
     * @param record
     *            资讯栏目对象
     * @return
     */
    int insert(InformationType record);

    /**
     * 添加资讯栏目-字段可选择
     * 
     * @param record
     *            资讯栏目对象
     * @return
     */
    int insertSelective(InformationType record);

    /**
     * 更新资讯栏目-字段可选择
     * 
     * @param record
     *            资讯栏目对象
     * @return
     */
    int updateByPrimaryKeySelective(InformationType record);

    /**
     * 更新资讯栏目
     * 
     * @param record
     *            资讯栏目对象
     * @return
     */
    int updateByPrimaryKey(InformationType record);

    /**
     * 根据主键查找资讯栏目
     * 
     * @param infoTypeId
     *            资讯栏目编号
     * @return InformationType 资讯栏目对象
     */
    InformationType selectByPrimaryKey(Long infoTypeId);

    /**
     * 根据栏目名称和第三方商家ID查找文章栏目数量
     * 
     * @param map
     * @return
     */
    Integer selectInfoTypeCountByName(Map<String, Object> map);

    /**
     * 根据父编号查找子资讯栏目
     * 
     * @param parentId
     *            资讯栏目编号
     * @return List<InformationType> 资讯栏目对象集合
     */
    List<InformationTypeVo> selectByParentId(Long parentId);

    /**
     * 根据父编号查找子资讯栏目数量
     * 
     * @param parentId
     *            资讯栏目编号
     * @return
     */
    int selectCountByParentId(Long parentId);

    /**
     * 根据查询参数和第三方商家ID查询所有的行数（分页用）
     * 
     * @param map
     * @return
     */
    Integer queryTotalCount(Map<String, Object> map);

    /**
     * 根据分页参数和第三方商家ID查询资讯类型列表
     * 
     * @param map
     * @return
     */
    List<Object> queryByPageBean(Map<String, Object> map);

    /**
     * 根据第三方商家ID查找所有文章栏目
     * 
     * @param thirdId
     * @return
     */
    List<InformationTypeVo> selectAllByThirdId(String thirdId);

    /**
     * 根据第三方商家ID查找所有可发布文章的栏目<br/>
     * 
     * @param thirdId
     *            第三方商家ID
     * @return List<InformationTypeVo> 资讯栏目VO List集合
     */
    List<InformationType> selectInfoTypeByAttr(String thirdId);

    /**
     * 根据查询参数和第三方商家ID查询所有的行数（查询分页用）
     * 
     * @param map
     * @return
     */
    Integer queryTotalCountForSearch(Map<String, Object> map);

    /**
     * 根据分页参数和第三方商家ID查询资讯类型列表（查询分页）
     * 
     * @param map
     * @return
     */
    List<Object> queryByPageBeanForSearch(Map<String, Object> map);

}
