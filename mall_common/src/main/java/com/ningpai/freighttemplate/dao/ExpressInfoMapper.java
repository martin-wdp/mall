package com.ningpai.freighttemplate.dao;

import java.util.List;

import com.ningpai.freighttemplate.bean.Express;

/**
 * 物流信息Mapper
 * 
 * @author Zhouh
 * @since 2014.5.21
 * @version 0.0.1
 * 
 */
public interface ExpressInfoMapper {
    /**
     * 删除
     * 
     * @param shoreExpId
     * @return int
     */
    int deleteByPrimaryKey(Long shoreExpId);

    /**
     * 插入
     * 
     * @param record
     * @return int
     */
    int insert(Express record);

    /**
     * 插入
     * 
     * @param record
     * @return int
     */
    int insertSelective(Express record);

    /**
     * 查询物流详细
     * 
     * @param shoreExpId
     * @return Express
     */
    Express selectByPrimaryKey(Long shoreExpId);

    /**
     * 修改物流
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(Express record);

    /**
     * 修改物流
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(Express record);

    /**
     * 查询物流支持信息
     * 
     * @param storeId
     *            店铺编号<@link Long>
     * @return 查询到的实体
     */
    List<Express> selectByStoreId(Long storeId);

    /**
     * 添加物流相关信息
     * 
     * @param express
     *            <@link Express>
     * @return 添加物流信息
     */
    int insertExpress(Express express);

    /**
     * 查询default是否唯一默认
     * 
     * @param storeId
     *            店铺编号<@link Long>
     * @return
     */
    int selectDefaultRows(Long storeId);

    /**
     * 修改
     * 
     * @param express
     *            <@link Express>
     * @return 修改物流信息
     */
    int updateExpress(Express express);

    /**
     * 修改物流相关信息,设置为默认,默认唯一
     * 
     * @param state
     *            =1 <@link Express>
     * @return 0失败 1成功
     */
    int updateStateByShoreExpId(Express state);

    /**
     * 修改物流相关信息,取消默认设置
     * 
     * @param state
     *            =0 <@link Express>
     * @return 0失败 1成功
     */
    int updateStateBackByShoreExpId(Express state);

    /**
     * 删除物流,修改标记,
     * 
     * @param delFlag
     *            =1 <@link Express>
     * @return 0失败 1成功
     */
    int deleteByShoreExpIdUpdate(Express delFlag);

    /**
     * 根据ID查询单条数据
     * 
     * @param shoreExpId
     *            <@link Long>
     * @return
     */
    Express selectByshoreExpId(Long shoreExpId);

    /**
     * 批量修改操作
     * 
     * @param list
     *            <@link List<Express>>
     * @return
     */
    int updateBatch(List<Express> list);
}
