/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.third.auth.service;
import java.math.BigDecimal;
import com.ningpai.third.auth.bean.CustomerConsume;
import com.ningpai.util.PageBean;

/**
 * <p>会员消费记录service</p>
 * 
 * @author NINGPAI-wanghy
 * @since 2014年3月21日 下午3:00:32
 * @version 2.0
 */
public interface CustomerConsumeService {
    /**
     * 删除会员消费记录
     * 
     * @param balanceId
     *            删除会员消费记录 {@link java.lang.Long }
     * @return int {@link java.lang.Integer }
     */
    int deleteConsume(Long balanceId);

    /**
     * 添加会员消费记录
     * 
     * @param record 消费记录对象
     * @return int {@link java.lang.Integer }
     */
    int saveConsume(CustomerConsume record);

    /**
     * 根据会员消费记录编号修改会员等级
     * 
     * @param record 消费记录对象
     * @return int {@link java.lang.Integer }
     */
    int updateConsume(CustomerConsume record);

    /**
     * 根据会员消费记录编号获取会员消费记录
     * 
     * @param balanceId 会员消费ID
     *            会员消费记录编号 {@link java.lang.Long}
     */
    CustomerConsume getConsumeById(Long balanceId);

    /**
     * 按会员编号和时间标记查询消费记录的分页数据<br/>
     * customerId 会员编号<br/>
     * date 1:近三个月记录 2:三个月前记录<br/>
     * startRowNum 起始行数 endRowNum 要查询的条数
     * 
     *            查询条件
     * @return List {@link java.util.List}
     */
    PageBean queryAllConsumeByCid(PageBean pb, Long customerId, Integer date);

    /**
     * 根据会员编号查询消费总和
     * @param customerId
     *            会员编号
     * @return Long {@link java.lang.Long}
     */
    BigDecimal selectTotalNumByCid(Long customerId);

}
