package com.ningpai.goods.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.ningpai.goods.dao.GoodsAuditMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * 第三放商品审核实现类
 *
 * @author zhouxu
 *
 */
@Repository("GoodsAuditMapper")
public class GoodsAuditMapperImpl extends BasicSqlSupport implements
        GoodsAuditMapper {

    /**
     * 获取审核数
     * 
     * @return
     */
    @Override
    public int selectAuditRows(Map<String, Object> map) {
        return this.selectOne(
                "com.ningpai.goods.dao.GoodsAuditMapper.selectAuditRows", map);
    }

    /**
     * 获取审核列表
     * 
     * @param map
     * @return
     */
    @Override
    public List<Object> selectAuditList(Map<String, Object> map) {
        return this.selectList(
                "com.ningpai.goods.dao.GoodsAuditMapper.selectAuditList", map);
    }

    /**
     * 获取审核开关标记
     * 
     * @return
     */
    @Override
    public String selectAuditAction() {
        return this
                .selectOne("com.ningpai.goods.dao.GoodsAuditMapper.selectAuditAction");
    }

    /**
     * 审核开、关控制
     * 
     * @param isThirdAuditUsed
     * @return
     */
    @Override
    public int updateAuditAction(String isThirdAuditUsed) {
        return this.update(
                "com.ningpai.goods.dao.GoodsAuditMapper.updateAuditAction",
                isThirdAuditUsed);
    }

    /**
     * 审核通过
     * 
     * @param goodsId
     * @return
     */
    @Override
    public int auditByGoodsId(Long goodsId) {
        return this.update(
                "com.ningpai.goods.dao.GoodsAuditMapper.auditByGoodsId",
                goodsId);
    }

    /**
     * 拒绝审核
     * 
     * @param map
     * @return
     */
    @Override
    public int refuseAuditByGoodsId(Map<String, Object> map) {
        return this.update(
                "com.ningpai.goods.dao.GoodsAuditMapper.refuseAuditByGoodsId",
                map);
    }

}
