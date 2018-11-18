package com.ningpai.businesscircle.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.businesscircle.bean.BusinessCircle;
import com.ningpai.businesscircle.dao.BusinessCircleMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * 商圈操作实现类
 * 
 * @author ggn 2015-01-20
 *
 */
@Repository("BusinessCircleMapper")
public class BusinessCircleMapperImpl extends BasicSqlSupport implements BusinessCircleMapper {

    /*
     * 添加商圈
     * 
     * @see
     * com.ningpai.businesscircle.dao.BusinessCircleMapper#addBusinessCircle
     * (com.ningpai.businesscircle.bean.BusinessCircle)
     */
    @Override
    public int addBusinessCircle(BusinessCircle businessCircle) {

        return this.insert("com.bbw.web.dao.BusinessCircleMapper.insertSelective", businessCircle);
    }

    /*
     * 
     * 删除商圈
     * 
     * @see
     * com.ningpai.businesscircle.dao.BusinessCircleMapper#delBusinessCircleById
     * (java.lang.Long)
     */
    @Override
    public int delBusinessCircleById(Long businessCircleId) {

        return this.delete("com.bbw.web.dao.BusinessCircleMapper.deleteBusinessCircleById", businessCircleId);
    }

    /*
     * 修改商圈
     * 
     * @see
     * com.ningpai.businesscircle.dao.BusinessCircleMapper#updateBusinessCircle
     * (com.ningpai.businesscircle.bean.BusinessCircle)
     */
    @Override
    public int updateBusinessCircle(BusinessCircle businessCircle) {

        return this.update("com.bbw.web.dao.BusinessCircleMapper.updateByPrimaryKeySelective", businessCircle);

    }

    /*
     * 查询商圈信息(根据条件)分页
     * 
     * @see
     * com.ningpai.businesscircle.dao.BusinessCircleMapper#findBusinessCircles
     * (java.util.Map)
     */
    @Override
    public List<Object> findBusinessCircles(Map<String, Object> map) {

        return this.selectList("com.bbw.web.dao.BusinessCircleMapper.selectBusinessCircles", map);
    }

    /*
     * 查询商圈的个数
     * 
     * @see
     * com.ningpai.businesscircle.dao.BusinessCircleMapper#findBusinessCirclesCount
     * (java.util.Map)
     */
    @Override
    public int findBusinessCirclesCount(Map<String, Object> map) {

        return this.selectOne("com.bbw.web.dao.BusinessCircleMapper.selectBusinessCirclesCount", map);
    }

    /**
     * 根据商家Id获得商圈
     * 
     * @param id
     * @return
     */
    @Override
    public BusinessCircle findBusinessCircleById(Long id) {

        return this.selectOne("com.bbw.web.dao.BusinessCircleMapper.findBusinessCircleById", id);
    }

    /**
     * 修改所有商圈
     * 
     * @param businessCircle
     * @return
     */
    @Override
    public int delThirdIdToBusinessCircle(BusinessCircle businessCircle) {

        return this.update("com.bbw.web.dao.BusinessCircleMapper.updateAll", businessCircle);
    }

    /**
     * 查询商圈信息(根据条件)
     * 
     * @param map
     *            businessCircleIsOpen 开启状态 businessCircleThirdId 商家状态
     *            不为null查未绑定商家的
     *
     * @return
     */
    @Override
    public List<Object> findBusinessCirclesByMap(Map<String, Object> map) {

        return this.selectList("com.bbw.web.dao.BusinessCircleMapper.selectBusinessCirclesByMap", map);
    }

    /**
     * 修改商圈的开启状态
     * 
     * @param businessCircle
     * @return
     */
    @Override
    public int updatebusinessCircleIsOpen(BusinessCircle businessCircle) {

        return this.update("com.bbw.web.dao.BusinessCircleMapper.updatebusinessCircleIsOpen", businessCircle);
    }

    /**
     * 根据商商圈Id获得商圈信息
     * 
     * @param id
     * @return
     */
    @Override
    public BusinessCircle findBusinessCircleByBusinessCircleId(Long id) {

        return this.selectOne("com.bbw.web.dao.BusinessCircleMapper.findBusinessCircleByBusinessCircleId", id);
    }

    /**
     * 根据商圈名称获得商圈
     * 
     * @param name
     * @return
     */
    @Override
    public BusinessCircle findBusinessCircleByName(String name) {
        List<BusinessCircle> list = this.selectList(
                "com.bbw.web.dao.BusinessCircleMapper.findBusinessCircleByBusinessCircleName", name);
        if (!list.isEmpty()) {
            return list.get(0);
        } else {
            return null;
        }
    }

}
