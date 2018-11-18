/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.third.auth.mapper;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.ningpai.third.auth.bean.CustomerConsume;

/**
 * <p>DAO-会员消费记录</p>
 * @author zhanghl
 * @since 20150730
 * @version 2.0
 */
public interface CustomerConsumeMapper {
    /**
     * 删除会员消费记录
     * 
     * @param balanceId
     *            删除会员消费记录 {@link java.lang.Long }
     * @return int {@link java.lang.Integer }
     */
    int deleteByPrimaryKey(Long balanceId);

    /**
     * 会员消费记录
     * @param record 消费记录的Id
     * @return
     */
    int insert(CustomerConsume record);

    /**
     * 添加会员消费记录按条件
     * 
     * @param record 消费记录的Id
     * @return int {@link java.lang.Integer }
     */
    int insertSelective(CustomerConsume record);

    /**
     * 按条件修改会员消费记录
     * 
     * @param record 消费记录的Id
     * @return int {@link java.lang.Integer }
     */
    int updateByPrimaryKeySelective(CustomerConsume record);

    /**
     * 根据会员消费记录编号修改会员等级
     * 
     * @param record 消费记录的Id
     * @return int {@link java.lang.Integer }
     */
    int updateByPrimaryKey(CustomerConsume record);

    /**
     * 根据会员消费记录编号获取会员消费记录
     * 
     * @param balanceId
     *            会员消费记录编号 {@link java.lang.Long}
     */
    CustomerConsume selectByPrimaryKey(Long balanceId);

    /**
     * 按会员编号和时间标记查询消费记录的条数<br/>
     * customerId 会员编号<br/>
     * date 1:近三个月记录 2:三个月前记录
     * 
     * @param map
     *            查询条件
     */
    int queryConsumeByCidCount(Map<String, Object> map);

    /**
     * 按会员编号和时间标记查询消费记录的分页数据<br/>
     * customerId 会员编号<br/>
     * date 1:近三个月记录 2:三个月前记录<br/>
     * startRowNum 起始行数 endRowNum 要查询的条数
     * 
     * @param map
     *            查询条件
     * @return List {@link java.util.List}
     */
    List<Object> queryAllConsumeByCid(Map<String, Object> map);

    /**
     * 根据会员编号查询消费总和
     * 
     * @param customerId
     *            会员编号
     * @return Long {@link java.lang.Long}
     */
    BigDecimal selectTotalNumByCid(Long customerId);

}
