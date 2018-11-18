package com.ningpai.system.dao.impl;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.system.bean.Receivables;
import com.ningpai.system.dao.ReceivablesMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 收款单实现类 Created by houyichang on 2015/8/17.
 */
@Repository("receivablesMapper")
public class ReceivablesMapperImpl extends BasicSqlSupport implements
        ReceivablesMapper {
    /**
     * 添加收款单信息
     *
     * @param receivables
     * @return int
     * @author houyichang 2015/8/17
     */
    @Override
    public int addReceivables(Receivables receivables) {
        return this.insert("com.ningpai.system.dao.ReceivablesMapper.insert",
                receivables);
    }

    /**
     * 分页按条件查询收款单信息
     *
     * @param map
     * @return pageBean
     * @author houyichang 2015/8/17
     */
    @Override
    public List<Object> queryReceivableByPageBean(Map<String, Object> map) {
        return this.selectList(
                "com.ningpai.system.dao.ReceivablesMapper.queryReceivables",
                map);
    }

    /**
     * 根据参数查询行数
     *
     * @param map
     * @return int
     * @author houyichang 2015/8/17
     */
    @Override
    public int queryCountByMap(Map<String, Object> map) {
        return this
                .selectOne(
                        "com.ningpai.system.dao.ReceivablesMapper.queryCountByMap",
                        map);
    }

    /**
     * 根据订单编号查询收款单信息 前端支付成功回调函数方法内调用
     *
     * @param orderCode
     * @return receivables
     * @author houyichang 2015/8/18
     */
    @Override
    public Receivables queryByOrderCode(String orderCode) {
        return this.selectOne(
                "com.ningpai.system.dao.ReceivablesMapper.queryByOrderCode",
                orderCode);
    }

    /**
     * 根据订单编号修改订单支付状态为已支付 前端支付成功回调函数方法内调用
     *
     * @param receivables
     * @return
     * @author houyichang 2015/8/18
     */
    @Override
    public int updatePayStatus(Receivables receivables) {
        return this.update(
                "com.ningpai.system.dao.ReceivablesMapper.updateByOrderCode",
                receivables);
    }

    /**
     * 根据收款单编号查询收款单详细信息 前端收款单查看详情处调用
     *
     * @param cashRegisterId
     * @author houyichang 2015/8/19
     */
    @Override
    public Receivables queryReceivablesDetail(Long cashRegisterId) {
        return this
                .selectOne(
                        "com.ningpai.system.dao.ReceivablesMapper.queryReceivablesDetail",
                        cashRegisterId);
    }

}
