package com.qpmall.service.impl;

import com.qpmall.bean.QpCustomerAutoStyleSearchBean;
import com.qpmall.dao.QpAutoStyleMapper;
import com.qpmall.dao.QpCustomerAutoStyleSearchMapper;
import com.qpmall.service.QpCustomerAutoStyleSearchService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by pl on 2015/10/12.
 * Desc:
 */
@Service("qpCustomerAutoStyleSearchService")
public class QpCustomerAutoStyleSearchServiceImpl implements QpCustomerAutoStyleSearchService {
    @Resource(name = "qpCustomerAutoStyleSearchMapper")
    private QpCustomerAutoStyleSearchMapper qpCustomerAutoStyleSearchMapper;
    @Override
    public List<QpCustomerAutoStyleSearchBean> findListByUserID(Long userId) {
        return qpCustomerAutoStyleSearchMapper.findListByUserID(userId);
    }

    @Override
    public int insert(QpCustomerAutoStyleSearchBean qpCustomerAutoStyleSearchBean) {
        return qpCustomerAutoStyleSearchMapper.insert(qpCustomerAutoStyleSearchBean);
    }

    @Override
    public int updateRecordByIsDel(QpCustomerAutoStyleSearchBean qpCustomerAutoStyleSearchBean) {
        return qpCustomerAutoStyleSearchMapper.updateRecordByIsDel(qpCustomerAutoStyleSearchBean);
    }

    @Override
    public QpCustomerAutoStyleSearchBean findRecordByUserIdAndAutoId(Map<String,Object> selectVal) {
        return qpCustomerAutoStyleSearchMapper.findRecordByUserIdAndAutoId(selectVal);
    }
}
