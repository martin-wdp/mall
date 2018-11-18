package com.ningpai.customer.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ningpai.customer.bean.Vocationinfo;

/**
 * 职业接口
 * 
 * @author jiping
 * @since 2014年7月7日 下午3:03:37
 * @version 0.0.1
 */
@Repository
public interface VocationinfoMapper {
    /**
     * 根据主键删除职业信息
     * 
     * @param vocationId
     *            职业编号
     * @return int {@link java.util.Integer}
     */
    int deleteByPrimaryKey(Long vocationId);

    /**
     * 插入职业信息
     * 
     * @param 职业bean
     *            com.ningpai.customer.bean.Vocationinfo
     *            {@link com.ningpai.customer.bean.Vocationinfo}
     * @return int {@link java.util.Integer}
     */
    int insert(Vocationinfo vocationinfo);

    /**
     * 根据主键查询职业信息
     * 
     * @param vocationId
     *            纸业编号
     * @return 职业bean com.ningpai.customer.bean.Vocationinfo
     *         {@link com.ningpai.customer.bean.Vocationinfo}
     */
    Vocationinfo selectByPrimaryKey(Long vocationId);

    /**
     * 根据主键修改职业信息
     * 
     * @param 职业bean
     *            com.ningpai.customer.bean.Vocationinfo
     *            {@link com.ningpai.customer.bean.Vocationinfo}
     * @return int {@link java.util.Integer}
     */
    int updateByPrimaryKey(Vocationinfo vocationinfo);

    /**
     * 查询全部职业信息
     * 
     * @return {@link List#}
     */
    List<Vocationinfo> selectAllVocation(long customerId);
}
