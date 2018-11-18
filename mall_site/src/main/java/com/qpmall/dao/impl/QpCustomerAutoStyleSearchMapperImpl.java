package com.qpmall.dao.impl;

import com.ningpai.manager.base.BasicSqlSupport;
import com.qpmall.bean.QpCustomerAutoStyleSearchBean;
import com.qpmall.dao.QpCustomerAutoStyleSearchMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by pl on 2015/10/12.
 * Desc:
 */
@Repository("qpCustomerAutoStyleSearchMapper")
public class QpCustomerAutoStyleSearchMapperImpl extends BasicSqlSupport implements QpCustomerAutoStyleSearchMapper {
    @Override
    public List<QpCustomerAutoStyleSearchBean> findListByUserID(Long userId) {
        return this.selectList("com.qpmall.dao.QpCustomerAutoStyleSearchMapper.findAllList",userId);
    }

    @Override
    public int insert(QpCustomerAutoStyleSearchBean qpCustomerAutoStyleSearchBean) {
        return this.insert("com.qpmall.dao.QpCustomerAutoStyleSearchMapper.insert",qpCustomerAutoStyleSearchBean);
    }

    @Override
    public int updateRecordByIsDel(QpCustomerAutoStyleSearchBean qpCustomerAutoStyleSearchBean) {
        return this.update("com.qpmall.dao.QpCustomerAutoStyleSearchMapper.updateRecordByIsDel", qpCustomerAutoStyleSearchBean);
    }

    @Override
    public QpCustomerAutoStyleSearchBean findRecordByUserIdAndAutoId(Map<String,Object> selectVal) {
        return this.selectOne("com.qpmall.dao.QpCustomerAutoStyleSearchMapper.findRecordByUserIdAndAutoId",selectVal);
    }
}
