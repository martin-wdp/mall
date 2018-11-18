package com.ningpai.system.service.impl;

import com.ningpai.system.bean.SystemsSet;
import com.ningpai.system.dao.IsBackOrderMapper;
import com.ningpai.system.service.IsBackOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/***
 * 是否允许退单 接口实现
 *
 * @author zhanghailong
 *
 */
@Service("IsBackOrderService")
public class IsBackOrderServiceImpl implements IsBackOrderService {
    /** spring注解 */
    private IsBackOrderMapper isBackOrderMapper;

    /**
     * 是否退单
     *
     * @return
     */
    @Override
    public SystemsSet getIsBackOrder() {
        return this.isBackOrderMapper.getIsBackOrder();
    }

    /***
     * 根据ID获取是否退单对象
     *
     * @return
     */
    @Override
    public SystemsSet getIsBackOrderById(Long setId) {
        return this.isBackOrderMapper.getIsBackOrderById(setId);
    }

    /**
     * 设置系统自动更新未付款订单作废时间
     *
     * @param bsetOrderTime
     * @return
     */
    @Override
    public Integer updatesetOrderTime(Long bsetOrderTime) {
        return this.isBackOrderMapper.updatesetOrderTime(bsetOrderTime);
    }

    /**
     * 新后台更新退货说明
     *
     * @return
     */
    @Override
    public Integer updateBackRemark(SystemsSet set) {
        return this.isBackOrderMapper.updateBackRemark(set);
    }

    public IsBackOrderMapper getIsBackOrderMapper() {
        return isBackOrderMapper;
    }

    /**
     * 获取系统设置的未付款订单作废时间
     *
     * @return
     */
    public Long getTimeFromNpset() {
        return this.isBackOrderMapper.getTimeFromNpset();
    }

    /**
     * 更新退货信息
     *
     * @param bcakInfoRemark
     * @return
     */
    public int updateBackInfo(String bcakInfoRemark) {
        return this.isBackOrderMapper.updateBackInfo(bcakInfoRemark);
    }

    @Resource(name = "IsBackOrderMapper")
    public void setIsBackOrderMapper(IsBackOrderMapper isBackOrderMapper) {
        this.isBackOrderMapper = isBackOrderMapper;
    }

    /**
     * 获取退货说明
     *
     * @return
     */
    public String queryBackInfoRemark() {
        return this.isBackOrderMapper.queryBackInfoRemark();
    }


    /***
     * 修改是否允许退单的状态
     * 
     * @param set
     * @return
     */
    public int updatesetBackOrder(SystemsSet set) {
        return isBackOrderMapper.updatesetBackOrder(set);
    }
}
