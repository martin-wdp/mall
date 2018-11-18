package com.ningpai.salesman.dao.impl;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.salesman.bean.CustomerRelaSalesman;
import com.ningpai.salesman.dao.CustomerRelaSalesmanMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ly-qpmall on 2015/12/16.
 */
@Repository("customerRelaSalesmanMapper")
public class CustomerRelaSalesmanMapperImpl extends BasicSqlSupport implements CustomerRelaSalesmanMapper {
    @Override
    public int deleteByPrimaryKey(Long relaId) {
        return 0;
    }

    @Override
    public Long getSalesmanIdByCustId(Long customerId) {
        return this.selectOne("com.ningpai.salesman.dao.CustomerRelaSalesmanMapper.getSalesmanIdByCustId",customerId);
    }
    @Override
    public int getSalesmanIdBySalaId(Long salesmanId) {
        return this.selectOne("com.ningpai.salesman.dao.CustomerRelaSalesmanMapper.getSalesmanIdBySalaId",salesmanId);
    }

    @Override
    public int deleteByCustId(Long customerId) {
        return this.delete("com.ningpai.salesman.dao.CustomerRelaSalesmanMapper.deleteByCustId",customerId);
    }

    @Override
    public int insertSelective(CustomerRelaSalesman customerRelaSalesman) {
        return this.insert("com.ningpai.salesman.dao.CustomerRelaSalesmanMapper.insertSelective",customerRelaSalesman);
    }

}
