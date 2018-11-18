package com.qpmall.service.impl;

import com.qpmall.bean.QpAutoOem;
import com.qpmall.dao.QpAutoOemMapper;
import com.qpmall.service.QpAutoOemService;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by ly-qpmall on 2015/12/2.
 */

@Repository("qpAutoOemService")
public class QpAutoOemServiceImpl implements QpAutoOemService{
    @Resource(name = "qpAutoOemMapper")
    private QpAutoOemMapper qpAutoOemMapper;

    /**
     * 根据OEM码查询OEM信息
     * @param autoOemCode
     * @return
     */
    @Override
    public QpAutoOem selectByAutoOemCode(String autoOemCode) {
        return qpAutoOemMapper.selectByAutoOemCode(autoOemCode);
    }
}
