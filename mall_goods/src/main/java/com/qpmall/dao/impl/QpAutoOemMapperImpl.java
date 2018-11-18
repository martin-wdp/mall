package com.qpmall.dao.impl;

import com.ningpai.manager.base.BasicSqlSupport;
import com.qpmall.bean.QpAutoOem;
import com.qpmall.dao.QpAutoOemMapper;
import org.springframework.stereotype.Repository;

/**
 * Created by ly-qpmall on 2015/12/2.
 */
@Repository("qpAutoOemMapper")
public class QpAutoOemMapperImpl extends BasicSqlSupport implements QpAutoOemMapper{
    /**
     * 根据OEM码查询OEM信息
     * @param autoOemCode
     * @return
     */
    @Override
    public QpAutoOem selectByAutoOemCode(String autoOemCode) {
        return this.selectOne("com.qpmall.dao.AutoOemMapper.selectByAutoOemCode",autoOemCode);
    }
}
