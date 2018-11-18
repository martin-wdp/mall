package com.ningpai.manager.mapper.impl;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.manager.bean.ManagerLoginHistory;
import com.ningpai.manager.mapper.ManagerLoginHistoryMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author hanxiaoying
 * @since 2016/1/20
 */
@Repository("managerLoginHistoryMapper")
public class ManagerLoginHistoryMapperImpl extends BasicSqlSupport implements ManagerLoginHistoryMapper {

    @Override
    @Transactional
    public int insert(ManagerLoginHistory loginHistory) {
        return this.insert("com.ningpai.manager.mapper.ManagerLoginHistoryMapper.insert",
                loginHistory);
    }

}
