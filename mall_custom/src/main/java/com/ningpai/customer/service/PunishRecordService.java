/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.customer.service;

import java.util.List;

import com.ningpai.customer.bean.PunishRecord;
import com.ningpai.util.PageBean;
import com.ningpai.util.SelectBean;

/**
 * 处罚记录接口
 *
 * */
public interface PunishRecordService {
    /**
     * 添加一条记录
     *
     * @param punishRecord
     * */
    int addPunishRecord(PunishRecord punishRecord);

    /**
     * 根据商家id查询
     *
     * @param thirdId
     * */
    PunishRecord queryInfoByThirdId(Long thirdId);

    /**
     * 查询记录
     *
     * @param thirdId
     * */
    List<PunishRecord> queryInfoByTidandDate(Long thirdId);

    /**
     * 第三方显示处罚记录
     * 
     * @param pageBean
     * @param thirdId
     * @return
     */
    PageBean selectRecordByPage(PageBean pageBean, Long thirdId);

    /**
     * 后台显示处罚记录
     */
    PageBean selectPunishedRecordByPage(PageBean pageBean, SelectBean selectBean);

}
