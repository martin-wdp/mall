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
public interface InformationTypeMapper {
    /**
     * 根据主键删除资讯栏目
     * 
     * @param infoTypeId
     *            资讯栏目编号
     * @return
     */
    int deleteByPrimaryKey(Long infoTypeId);

    /**
     * 调用存储过程级联删除栏目下的子栏目和文章
     * 
     * @param infoTypeId
     * @return
     */
    int deleteByPrimaryKeyPro(Long infoTypeId);

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
     * 根据主键查找资讯栏目
     * 
     * @param infoTypeId
     *            资讯栏目编号
     * @return InformationType 资讯栏目对象
     */
    InformationType selectByPrimaryKey(Long infoTypeId);

    /**
     * 根据栏目名称查找资讯栏目数量
     * 
     * @param name
     *            栏目名称
     * @return Integer 资讯栏目数量
     */
    Integer selectInfoTypeCountByName(String name);

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
     * 查询所有的行数（分页用）
     * 
     * @return
     */
    Integer queryTotalCount(Map<String, Object> map);

    /**
     * 根据分页参数查询资讯栏目列表<br/>
     * startRowNum 起始行数<br/>
     * endRowNum 获取行数<br/>
     * 
     * @return
     */
    List<Object> queryByPageBean(Map<String, Object> map);

    /**
     * 查找所有的资讯栏目
     * 
     * @return List<InformationTypeVo> 资讯栏目VO List集合
     */
    List<InformationTypeVo> selectAll();

    /**
     * 根据栏目属性查找所有可发布文章的资讯类型，添加文章用<br/>
     * 
     * @return List<InformationTypeVo> 资讯栏目VO List集合
     */
    List<InformationTypeVo> selectInfoTypeByAttrForAddInfo();

    /**
     * 根据栏目属性查找所有可发布文章的资讯类型，关联模板、频道用<br/>
     * 
     * @return List<InformationTypeVo> 资讯栏目VO List集合
     */
    List<InformationTypeVo> selectInfoTypeByAttrForTemp();

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
     * 查询所有栏目
     * 
     * @param searchText
     * @return List
     */
    List<InformationType> selectAllType(String searchText);
}
