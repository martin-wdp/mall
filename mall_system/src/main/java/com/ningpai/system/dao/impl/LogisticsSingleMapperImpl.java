package com.ningpai.system.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.system.bean.LogisticsSingle;
import com.ningpai.system.dao.LogisticsSingleMapper;

/**
 * 快递单模板Dao的实现类
 * 
 * @author Dell
 *
 */
@Repository("LogisticsSingleMapper")
public class LogisticsSingleMapperImpl extends BasicSqlSupport implements LogisticsSingleMapper {

    private static final String THIRDID = "thirdId";

    /**
     * 添加
     * 
     * @param logisticsSingle
     * @return int
     */
    @Override
    public int addLogisticsSingle(LogisticsSingle logisticsSingle) {

        return this.insert("com.ningpai.system.dao.LogisticsSingleMapper.addLogisticsSingle", logisticsSingle);
    }

    /**
     * 修改
     * 
     * @param logisticsSingle
     * @return int
     */
    @Override
    public int updateLogisticsSingle(LogisticsSingle logisticsSingle) {

        return this.insert("com.ningpai.system.dao.LogisticsSingleMapper.updateLogisticsSingle", logisticsSingle);
    }

    /**
     * 根据物流单模板编号删除物流单模板
     * 
     * @param id
     * @return int
     */
    @Override
    public int delLogisticsSingleById(Long id) {

        return this.delete("com.ningpai.system.dao.LogisticsSingleMapper.delLogisticsSingleById", id);
    }

    /**
     * 根据物流单模板编号和thirdId删除物流单模板
     * 
     * @param map
     * @return int
     */
    @Override
    public int delLogisticsSingle(Map<String, Object> map) {

        return this.delete("com.ningpai.system.dao.LogisticsSingleMapper.delLogisticsSingle", map);
    }

    /**
     * 根据商家编号查询所有物流单模板(分页显示)
     * 
     * @return List<LogisticsSingle>
     */
    @Override
    public List<Object> selectAll(Map<String, Object> map) {
        // 如果是boss
        if ((Long) map.get(THIRDID) == 0L) {
            return this.selectList("com.ningpai.system.dao.LogisticsSingleMapper.selectAll", map);
        } else {
            // 是商家
            return this.selectList("com.ningpai.system.dao.LogisticsSingleMapper.selectThirdAll", map);
        }

    }

    /**
     * 根据条件查询模板
     * 
     * @param map
     *            thirdId 商家编号 companyId 物流公司编号 logisticsSingleId 模板编号
     * @return LogisticsSingle
     */
    @Override
    public LogisticsSingle selectLogisticsSingle(Map<String, Object> map) {
        // 如果是boss
        if ((Long) map.get(THIRDID) == 0L) {
            return this.selectOne("com.ningpai.system.dao.LogisticsSingleMapper.selectLogisticsSingle", map);
        } else {
            // 商家
            return this.selectOne("com.ningpai.system.dao.LogisticsSingleMapper.selectThirdLogisticsSingle", map);
        }
    }

    /**
     * 根据商家编号查询物流模板个数
     * 
     * @param map
     * @return int
     */
    @Override
    public int selectLogisticsSingleCount(Map<String, Object> map) {
        // 如果是boss
        if ((Long) map.get(THIRDID) == 0L) {

            return this.selectOne("com.ningpai.system.dao.LogisticsSingleMapper.selectAllCount");

        } else {
            // 第三方商家
            return this.selectOne("com.ningpai.system.dao.LogisticsSingleMapper.selectThirdCount", map);
        }
    }
}
