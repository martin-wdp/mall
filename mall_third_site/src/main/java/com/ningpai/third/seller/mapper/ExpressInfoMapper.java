package com.ningpai.third.seller.mapper;

import java.util.List;

import com.ningpai.third.seller.bean.Express;

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
     * 根据商家ID和物流编号 查询单个物流信息
     * @param expCompany
     * @param storeId
     * @return
     */
    Express selectExpressByCom(String expCompany,Long storeId);
    /**
     * 根据主键删除单个对象信息
     * @param shoreExpId
     * @return
     */
    int deleteByPrimaryKey(Long shoreExpId);

    /**
     * 新增一个物流信息
     * @param record
     * @return
     */
    int insert(Express record);


    /**
     * 新增一个物流信息
     * @param record
     * @return
     */
    int insertSelective(Express record);


    /**
     * 根据主键获取单个的物流信息
     * @param shoreExpId
     * @return
     */
    Express selectByPrimaryKey(Long shoreExpId);

    /**
     * 根据主键更新单个物流信息
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Express record);

    /**
     * 根据主键修改单个物流信息
     * @param record
     * @return
     */
    int updateByPrimaryKey(Express record);

    /**
     * 查询物流支持信息
     * 
     * @param express
     *            店铺编号<@link Long>
     * @return 查询到的实体
     */
    List<Express> selectByStoreId(Express express);

    /**
     * 查询物流支持信息
     *
     * @param express
     *            店铺编号<@link Long>
     * @return 查询到的实体
     */
    List<Express> selectByStoreIds(Express express);

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
     * @param express
     *            =1 <@link Express>
     * @return 0失败 1成功
     */
    int updateStateByShoreExpId(Express express);

    /**
     * 修改物流相关信息,取消默认设置
     * 
     * @param express
     *            =0 <@link Express>
     * @return 0失败 1成功
     */
    int updateStateBackByShoreExpId(Express express);

    /**
     * 删除物流,修改标记,
     * 
     * @param delflag
     *            =1 <@link Express>
     * @return 0失败 1成功
     */
    int deleteByShoreExpIdUpdate(Express delflag);

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
