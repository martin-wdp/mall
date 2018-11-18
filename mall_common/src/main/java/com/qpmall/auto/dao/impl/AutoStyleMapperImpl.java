package com.qpmall.auto.dao.impl;

import com.ningpai.manager.base.BasicSqlSupport;
import com.qpmall.auto.bean.AutoBrandBean;
import com.qpmall.auto.bean.AutoSystemBean;
import com.qpmall.auto.dao.AutoStyleMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2015/12/18.
 */
@Repository("autoStyleMapper")
public class AutoStyleMapperImpl extends BasicSqlSupport implements AutoStyleMapper {


    /**
     * 获取所有的车品牌
     *
     */
    @Override
    public List<AutoBrandBean> getAllAutoBrand() {
        return this.selectList("com.qpmall.web.dao.AutoStyleMapper.getAllAutoBrand");
    }

    /**
     * 获取车系，入参为品牌的名称
     * @param goodsBrandName
     * @return
     */
    @Override
    public List<AutoSystemBean> getBrandMakeAndSystem(String goodsBrandName) {
        return this.selectList("com.qpmall.web.dao.AutoStyleMapper.getBrandMakeAndSystem", goodsBrandName);
    }
}
