package com.ningpai.system.dao.impl;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.system.bean.SystemsSet;
import com.ningpai.system.dao.IsBackOrderMapper;

/**
 * 是否允许退单dao实现类
 * @author jiping 
 * @since 2015年8月21日 下午6:51:13 
 * @version 0.0.1
 */
@Repository("IsBackOrderMapper")
public class IsBackOrderMapperImpl extends BasicSqlSupport implements
        IsBackOrderMapper {
    /**
     * 是否允许退单返回的状态
     * 
     * @return
     */
    @Override
    public SystemsSet getIsBackOrder() {
        return this
                .selectOne("com.ningpai.system.dao.AdvertisementMapper.getIsBackOrder");
    }

    /***
     * 修改是否允许退单
     */
    @Override
    public int updatesetBackOrder(SystemsSet set) {
        return this
                .update("com.ningpai.system.dao.AdvertisementMapper.updatesetBackOrder",
                        set);
    }

    /***
     * 根据ID获取是否退单对象
     * 
     * @return
     */
    @Override
    public SystemsSet getIsBackOrderById(Long setId) {
        return this
                .selectOne(
                        "com.ningpai.system.dao.AdvertisementMapper.getIsBackOrderById",
                        setId);
    }

    /**
     * 设置系统自动更新未付款订单作废时间
     * 
     * @param bsetOrderTime
     * @return
     */
    public Integer updatesetOrderTime(Long bsetOrderTime) {
        return this
                .update("com.ningpai.system.dao.AdvertisementMapper.updatesetOrderTime",
                        bsetOrderTime);
    }

    /**
     * 新后台更新退货说明
     * 
     * @param set
     * @return
     */
    @Override
    public Integer updateBackRemark(SystemsSet set) {
        return this.update(
                "com.ningpai.system.dao.AdvertisementMapper.updateBackInfo",
                set);
    }

    /**
     * 获取系统设置未付款订单作废时间
     * 
     * @return
     */
    public Long getTimeFromNpset() {
        return this
                .selectOne("com.ningpai.system.dao.AdvertisementMapper.getTimeFromNpset");
    }

    /**
     * 更新退货说明
     * 
     * @param backInfoRemark
     * @return
     */
    @Override
    public int updateBackInfo(String backInfoRemark) {
        return this.update(
                "com.ningpai.system.dao.AdvertisementMapper.updateBackInfo",
                backInfoRemark);
    }

    /**
     * 获取退货说明
     * 
     * @return
     */
    @Override
    public String queryBackInfoRemark() {
        return this
                .selectOne("com.ningpai.system.dao.AdvertisementMapper.queryBackInfoRemark");
    }
}
