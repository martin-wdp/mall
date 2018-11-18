package com.ningpai.FindGoods.dao.impl;

import com.ningpai.FindGoods.bean.FindGoodsBean;
import com.ningpai.FindGoods.dao.FindGoodsMapper;
import com.ningpai.FindGoods.service.FindGoodsService;
import com.ningpai.manager.base.BasicSqlSupport;
import org.junit.Test;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by pl on 2015/9/15.
 * Desc:
 */
@Repository("findGoodsMapper")
public class FindGoodsMapperImpl extends BasicSqlSupport implements FindGoodsMapper {

    @Override
    public List<FindGoodsBean> findGoodsList() {
        return this.selectList("com.ningpai.FindGoods.dao.FindGoodsMapper.findGoodsList");
    }
}
