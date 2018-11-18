package com.ningpai.customer.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ningpai.customer.bean.Educationinfo;

/**
 * 教育接口
 * 
 * @author jiping
 * @since 2014年7月7日 下午3:22:01
 * @version 0.0.1
 */
@Repository
public interface EducationinfoMapper {
    /**
     * 根据主键删除教育信息
     * 
     * @param educationId
     *            教育编号
     * @return int {@link java.util.Integer}
     */
    int deleteByPrimaryKey(Long educationId);

    /**
     * 插入教育信息
     * 
     * @param 教育bean
     *            com.ningpai.customer.bean.Educationinfo
     *            {@link com.ningpai.customer.bean.Educationinfo}
     * @return int {@link java.util.Integer}
     */
    int insert(Educationinfo educationinfo);

    /**
     * 根据主键查询教育信息
     * 
     * @param educationId
     *            教育编号
     * @return 教育信息 com.ningpai.customer.bean.Educationinfo
     *         {@link com.ningpai.customer.bean.Educationinfo}
     */
    Educationinfo selectByPrimaryKey(Long educationId);

    /**
     * 根据主键修改教育信息
     * 
     * @param 教育bean
     *            com.ningpai.customer.bean.Educationinfo
     *            {@link com.ningpai.customer.bean.Educationinfo}
     * @return int {@link java.util.Integer}
     */
    int updateByPrimaryKey(Educationinfo educationinfo);

    /**
     * 查询全部教育信息
     * 
     * @return {@link List#}
     */
    List<Educationinfo> selectAll(long customerId);
}
