package com.ningpai.OthKeyWord.dao.impl;

import com.ningpai.OthKeyWord.bean.OthKeyWord;
import com.ningpai.OthKeyWord.dao.OthKeyWordMapper;
import com.ningpai.manager.base.BasicSqlSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by pl on 2016/1/23.
 * Desc:
 */
@Repository("othKeyWordMapper")
public class OthKeyWordIMpl extends BasicSqlSupport implements OthKeyWordMapper {
    @Override
    public List<OthKeyWord> GetAllOthKey() {
        return this.selectList("com.ningpai.OthKeyWord.dao.OthKeyWordMapper.selectOthKey");
    }
}
