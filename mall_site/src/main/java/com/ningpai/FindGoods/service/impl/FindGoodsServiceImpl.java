package com.ningpai.FindGoods.service.impl;

import com.ningpai.FindGoods.bean.FindGoodsBean;
import com.ningpai.FindGoods.dao.FindGoodsMapper;
import com.ningpai.FindGoods.service.FindGoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by pl on 2015/9/15.
 * Desc:
 */
@Service("findGoodsService")
public class FindGoodsServiceImpl implements FindGoodsService {
    @Resource(name = "findGoodsMapper")
    private FindGoodsMapper findGoodsMapper;
    @Override
    public List<FindGoodsBean> findGoodsList() {
        return findGoodsMapper.findGoodsList();
    }
}
