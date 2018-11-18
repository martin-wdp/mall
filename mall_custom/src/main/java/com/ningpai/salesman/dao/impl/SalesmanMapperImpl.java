package com.ningpai.salesman.dao.impl;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.salesman.bean.Salesman;
import com.ningpai.salesman.dao.SalesmanMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by ly-qpmall on 2015/12/16.
 */
@Repository("salesmanMapper")
public class SalesmanMapperImpl extends BasicSqlSupport implements SalesmanMapper {
    @Override
    public int deleteByPrimaryKey(Long salesmanId) {
        return this.update("com.ningpai.salesman.dao.SalesmanMapper.deleteByPrimaryKey", salesmanId);
    }

    @Override
    public List<Salesman> seletAllEnableSala() {
        return this.selectList("com.ningpai.salesman.dao.SalesmanMapper.seletAllEnableSala");
    }

    @Override
    public int insert(Salesman record) {
        return 0;
    }

    @Override
    public int insertSelective(Salesman record) {
        return this.insert("com.ningpai.salesman.dao.SalesmanMapper.insertSelective", record);
    }

    @Override
    public int selectCountByName(String salesmanName) {
        return this.selectOne("com.ningpai.salesman.dao.SalesmanMapper.selectCountByName",salesmanName);
    }

    /**
     * 分页查询业务员信息列表
     *
     * @param param
     * @return
     */
    @Override
    public List<Object> selectBySalesman(Map<String, Object> param) {
        return this.selectList("com.ningpai.salesman.dao.SalesmanMapper.selectBySalesman", param);
    }

    /**
     * 查询业务员的记录总条数
     *
     * @param salesman
     * @return
     */
    @Override
    public Integer selectCountBySalesman(Salesman salesman) {
        return this.selectOne("com.ningpai.salesman.dao.SalesmanMapper.selectCountBySalesman", salesman);
    }

    @Override
    public Salesman selectByPrimaryKey(Long salesmanId) {
        return this.selectOne("com.ningpai.salesman.dao.SalesmanMapper.selectByPrimaryKey",salesmanId);
    }

    @Override
    public int updateByPrimaryKeySelective(Salesman record) {
        return this.update("com.ningpai.salesman.dao.SalesmanMapper.updateByPrimaryKeySelective", record);
    }

    @Override
    public int updateByPrimaryKey(Salesman record) {
        return 0;
    }
}
