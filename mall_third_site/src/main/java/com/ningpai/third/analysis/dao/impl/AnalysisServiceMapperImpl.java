package com.ningpai.third.analysis.dao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.third.analysis.bean.OCustomerFollow;
import com.ningpai.third.analysis.bean.OOrder;
import com.ningpai.third.analysis.dao.AnalysisServiceMapper;
/**
 *<p>数据分析</p>
 *@author zhanghl
 *@versuin 2.0
 *@since 2015.07.30
 */
@Repository("AnalysisServiceMapper")
public class AnalysisServiceMapperImpl extends BasicSqlSupport implements AnalysisServiceMapper {

    /**
     * 根据条件查询所有的会员关注信息
     * @param paramMap
     * @return
     */
    @Override
    public List<OCustomerFollow> selectThirdFollowGoods(Map<String, Object> paramMap) {
        return this.selectList("com.bbw.web.dao.OCustomerFollowMapper.selectThirdFollowGoods", paramMap);
    }

    /**
     * 根据条件查询所有的会员关注信息
     * @param paramMap
     * @return
     */
    @Override
    public List<OCustomerFollow> selectThirdFollowGoodsCount(Map<String, Object> paramMap) {
        return this.selectList("com.bbw.web.dao.OCustomerFollowMapper.selectThirdFollowGoodsCount", paramMap);
    }

    /**
     * 根据条件查询 所有的订单信息
     * @param paramMap
     * @return
     */
    public List<OOrder> queryCountByDay(Map<String, Object> paramMap) {
        return this.selectList("com.ningpai.third.analysis.dao.OOderMapper.queryCountByDay", paramMap);
    }

    /**
     * 查询单个的订单信息
     * @param paramMap
     * @return
     */
    public OOrder queryNoSuccCountByDay(Map<String, Object> paramMap) {
        return this.selectOne("com.ningpai.third.analysis.dao.OOderMapper.queryNoSuccCountByDay", paramMap);
    }

    /**
     * 查询单个的订单信息
     * @param paramMap
     * @return
     */
    public OOrder querySuccCountByTime(Map<String, Object> paramMap) {
        return this.selectOne("com.ningpai.third.analysis.dao.OOderMapper.querySuccCountByTime", paramMap);
    }

}
