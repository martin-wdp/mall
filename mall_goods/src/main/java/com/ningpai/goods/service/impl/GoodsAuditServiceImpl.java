package com.ningpai.goods.service.impl;

import com.ningpai.goods.dao.GoodsAuditMapper;
import com.ningpai.goods.service.GoodsAuditService;
import com.ningpai.goods.util.GoodsSearchBean;
import com.ningpai.goods.util.ValueUtil;
import com.ningpai.util.PageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 第三方商品审核实现类
 *
 * @author zhouxu
 *
 */
@Service("goodsAuditService")
public class GoodsAuditServiceImpl implements GoodsAuditService {

    private GoodsAuditMapper goodsAuditMapper;

    public GoodsAuditMapper getGoodsAuditMapper() {
        return goodsAuditMapper;
    }

    @Resource(name = "GoodsAuditMapper")
    public void setGoodsAuditMapper(GoodsAuditMapper goodsAuditMapper) {
        this.goodsAuditMapper = goodsAuditMapper;
    }

    /**
     * 获取审核商品信息
     * 
     * @param pageBean
     * @param searchBean
     * @return
     */
    @Override
    public PageBean selectAuditGoods(PageBean pageBean,
            GoodsSearchBean searchBean) {
        if (null != searchBean) {
            /*
             * 判断搜索框展示标记是否为1 如果是1就设置基本查询不可用 如果不是1就对其他参数进行赋值
             */
            if ("1".equals(searchBean.getShowFlag())) {
                // 设置基本查询为不可用
                searchBean.setCondition("-1");
                searchBean.setSearchText("");
            } else {
                searchBean.setGoodsBrandId("-1");
                searchBean.setGoodsCateId("-1");
                searchBean.setGoodsKeyword("");
                searchBean.setGoodsName("");
                searchBean.setGoodsNo("");
                searchBean.setIsThird("3");
                searchBean.setShowFlag("0");
                searchBean.setStatus("-1");
                searchBean.setThirdName("");
            }
            // 如果是否第三方标记为0,设置第三方名称为空
            if (null != searchBean.getIsThird() && "0".equals(searchBean.getIsThird())) {
                    searchBean.setThirdName("");
            }
        }
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        // 把searchBean放进map中
        map.put("searchBean", searchBean);
        // 把开始行数放进map中
        map.put(ValueUtil.STARTROWNUM, pageBean.getStartRowNum());
        // 把结束行数放进map中
        map.put(ValueUtil.ENDROWNUM, pageBean.getEndRowNum());
        // 设置PageBean的总行数
        pageBean.setRows(goodsAuditMapper.selectAuditRows(map));
        // 设置pageBean 的数据集合
        pageBean.setList(goodsAuditMapper.selectAuditList(map));
        // 把PageBean返回
        return pageBean;
    }

    /**
     * 获取审核开关标记
     * 
     * @return
     */
    @Override
    public String selectAuditAction() {
        // 返回结果
        return goodsAuditMapper.selectAuditAction();
    }

    /**
     * 开、关控制
     * 
     * @param isThirdAuditUsed
     * @return
     */
    @Override
    public int updateAuditAction(String isThirdAuditUsed) {
        // 返回结果
        return goodsAuditMapper.updateAuditAction(isThirdAuditUsed);
    }

    /**
     * 审核通过
     * 
     * @param goodsId
     * @return
     */
    @Override
    public int auditByGoodsId(Long goodsId) {
        // 返回结果
        return goodsAuditMapper.auditByGoodsId(goodsId);
    }

    /**
     * 拒绝审核
     * 
     * @param goodsId
     * @return
     */
    @Override
    public int refuseAuditByGoodsId(Long goodsId, String refuseReason) {
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("goodsId", goodsId);
        map.put("refuseReason", refuseReason);
        // 返回结果
        return goodsAuditMapper.refuseAuditByGoodsId(map);
    }
}
