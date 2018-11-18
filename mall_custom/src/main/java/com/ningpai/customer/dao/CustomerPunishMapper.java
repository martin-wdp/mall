package com.ningpai.customer.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.customer.bean.CustomerPunish;

/**
 * 
 * @author Zhangsl
 *
 */
public interface CustomerPunishMapper {
    /**
     * 根据主键进行删除
     *
     * @param id
     * */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入一条记录
     *
     * @param record
     * */
    int insert(CustomerPunish record);

    /**
     * 添加
     * 
     * @param record
     * @return
     */
    int insertSelective(CustomerPunish record);

    /**
     * 根据ID查询
     * 
     * @param id
     * @return
     */
    CustomerPunish selectByPrimaryKey(Long id);

    /**
     * 更新
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(CustomerPunish record);
    /**
     * 根据主键进行修改
     *
     * @param record
     * */
    int updateByPrimaryKey(CustomerPunish record);

    /**
     * 分页查询
     * 
     * @param paramMap
     * @return
     */
    List<Object> selectPunishInfoByPage(Map<String, Object> paramMap);

    /**
     * 查询总条数
     * 
     * @return
     */
    int selectPunishInfoCount();

    /**
     * 删除(逻辑删除)
     * 
     * @param id
     * @return
     */
    int updateDelflag(Long id);
    /**
     * 查询所有
     * */
    List<CustomerPunish> queryAllRules();

    /**
     *根据条件查询
     * */
    CustomerPunish queryIdByRule(CustomerPunish customerPunish);

}
