package com.qpmall.service;

import com.qpmall.bean.QpAutoOem;

/**
 * Created by ly-qpmall on 2015/12/2.
 */
public interface QpAutoOemService {
    /**
     * 根据OEM码查询OEM信息
     * @param autoOemCode
     * @return
     */
    QpAutoOem selectByAutoOemCode(String autoOemCode);
}
