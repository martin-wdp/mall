package com.ningpai.system.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.system.bean.StockWarning;
import com.ningpai.system.dao.StockWarningMapper;
import com.ningpai.system.util.StorkWarningUtil;

/**
 * 库存预警数据层实现类
 * 
 * @author xiaogu
 *
 */
@Repository("StockWarningMapper")
public class StockWarningMapperImpl extends BasicSqlSupport implements
        StockWarningMapper {
    /**
     * 查询库存预警下限
     * 
     * @return
     */
    @Override
    public StockWarning select() {

        return this
                .selectOne("com.ningpai.system.dao.StockWarningMapper.select");
    }

    /**
     * 更新库存预警下限
     * 
     * @param sw
     * @return
     */
    @Override
    public int update(StockWarning sw) {

        return this.update("com.ningpai.system.dao.StockWarningMapper.update",
                sw);
    }

    /**
     * 查询库存预警货品信息
     * 
     * @param map
     * @return
     */
    @Override
    public List<Object> selectGoods(Map<String, Object> map) {

        return this.selectList(
                "com.ningpai.system.dao.StockWarningMapper.selectGoods", map);
    }

    /**
     * 查询分页记录数
     * 
     * @param map
     * @return
     */
    @Override
    public int selectcount(Map<String, Object> map) {

        return this.selectOne(
                "com.ningpai.system.dao.StockWarningMapper.selectcount", map);
    }

    /**
     * 根据id查询货品库存用于修改
     * 
     * @param id
     * @return
     */
    @Override
    public StorkWarningUtil selectbyId(Long wareid) {

        return this.selectOne(
                "com.ningpai.system.dao.StockWarningMapper.selectbyId", wareid);
    }

    /**
     * 更新库存
     * 
     * @param storkWarningUtil
     * @return
     */
    @Override
    public int updatestock(StorkWarningUtil storkWarningUtil) {

        return this.update(
                "com.ningpai.system.dao.StockWarningMapper.updatestock",
                storkWarningUtil);
    }

    /**
     * 按货品名称查询库存预警货品信息
     * 
     * @param map
     * @return
     */
    @Override
    public List<Object> selectGoodsbyname(Map<String, Object> map) {

        return this.selectList(
                "com.ningpai.system.dao.StockWarningMapper.selectGoodsbyname",
                map);
    }

    /**
     * 按货品名称查询分页记录数
     * 
     * @param map
     * @return
     */
    @Override
    public int selectcountbyname(Map<String, Object> map) {

        return this.selectOne(
                "com.ningpai.system.dao.StockWarningMapper.selectcountbyname",
                map);
    }

    /**
     * 查询库存预警货品信息
     * 
     * @param map
     * @return
     */
    @Override
    public List<Object> selectWare(Map<String, Object> map) {

        return this.selectList(
                "com.ningpai.system.dao.StockWarningMapper.selectWare", map);
    }

    /**
     * 查询仓库分页记录数
     * 
     * @param map
     * @return
     */
    @Override
    public int selectcounts(Map<String, Object> map) {

        return this.selectOne(
                "com.ningpai.system.dao.StockWarningMapper.selectcounts", map);
    }

}
