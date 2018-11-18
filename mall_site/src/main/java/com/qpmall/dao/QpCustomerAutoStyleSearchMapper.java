package com.qpmall.dao;

import com.qpmall.bean.QpCustomerAutoStyleSearchBean;

import java.util.List;
import java.util.Map;

/**
 * Created by pl on 2015/10/12.
 * Desc:
 */
public interface QpCustomerAutoStyleSearchMapper {
    List<QpCustomerAutoStyleSearchBean> findListByUserID(Long userId);
    int insert(QpCustomerAutoStyleSearchBean qpCustomerAutoStyleSearchBean);
    int updateRecordByIsDel(QpCustomerAutoStyleSearchBean qpCustomerAutoStyleSearchBean);
    QpCustomerAutoStyleSearchBean findRecordByUserIdAndAutoId(Map<String,Object> selectVal);
}
