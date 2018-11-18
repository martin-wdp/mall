package com.ningpai.system.service;

import com.ningpai.system.bean.SystemsSet;

/***
 * 是否允许退单
 * @author zhanghailong
 *
 */
public interface IsBackOrderService {
    /***
     * 是否退单
     * @return
     */
    SystemsSet getIsBackOrder();
    
    /***
     * 修改是否允许退单的状态
     * @param set
     * @return
     */
    int updatesetBackOrder(SystemsSet set);
    
    /***
     * 根据ID获取是否退单对象
     * @return
     */
    SystemsSet getIsBackOrderById(Long setId);

    /**
     * 设置系统自动更新未付款订单作废时间
     * @param bsetOrderTime
     * @return
     */
    Integer updatesetOrderTime(Long bsetOrderTime);

    /**
     * 新后台跟新退货说明
     * @param set
     * @return
     */
    Integer updateBackRemark(SystemsSet set);

    /**
     * 获取系统设置的未付款订单作废时间
     * @return
     */
    Long getTimeFromNpset();

    /**
     * 更新退货信息
     * @param backInfoRemark
     * @return
     */
    int updateBackInfo(String backInfoRemark);

    /**
     * 获取退货说明
     * @return
     */
    String queryBackInfoRemark();
}
