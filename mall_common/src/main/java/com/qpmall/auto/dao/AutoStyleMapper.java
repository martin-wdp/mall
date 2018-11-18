package com.qpmall.auto.dao;

import com.ningpai.manager.base.BasicSqlSupport;
import com.qpmall.auto.bean.AutoBrandBean;
import com.qpmall.auto.bean.AutoSystemBean;
import com.qpmall.auto.dao.impl.AutoStyleMapperImpl;
import com.qpmall.auto.service.AutoStyleService;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2015/12/18.
 */
public interface AutoStyleMapper{
    /**
     * 获取所有的车品牌
     *
     */
    List<AutoBrandBean> getAllAutoBrand();

    /**
     * 获取所有的车品牌
     *
     */
    List<AutoSystemBean> getBrandMakeAndSystem(String goodsBrandName);


}
