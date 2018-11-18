package com.qpmall.service;

import com.qpmall.bean.QpCustomerAutoStyleSearchBean;

import java.util.List;
import java.util.Map;

/**
 * Created by pl on 2015/10/12.
 * Desc:
 */
public interface QpCustomerAutoStyleSearchService {
    List<QpCustomerAutoStyleSearchBean> findListByUserID(Long userId);
    int insert(QpCustomerAutoStyleSearchBean qpCustomerAutoStyleSearchBean);
    int updateRecordByIsDel(QpCustomerAutoStyleSearchBean qpCustomerAutoStyleSearchBean);
    QpCustomerAutoStyleSearchBean findRecordByUserIdAndAutoId(Map<String,Object> selectVal);
}
