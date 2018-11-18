package com.qpmall.dao;


import com.qpmall.bean.QpAutoOem;

public interface QpAutoOemMapper {
    /**
     * 根据OEM码查询OEM信息
     * @param autoOemCode
     * @return
     */

    QpAutoOem selectByAutoOemCode(String autoOemCode);


}