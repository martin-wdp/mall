package com.ningpai.system.dao;

import com.ningpai.system.bean.SystemsSet;

/***
 * 是否允许退单
 * 
 * @author zhanghailong
 *
 */
public interface IsBackOrderMapper {
    /***
     * 是否允许退单返回的状态
     * 
     * @return
     */
    SystemsSet getIsBackOrder();

    /***
     * 修改是否允许退单
     */
    int updatesetBackOrder(SystemsSet set);

    /***
     * 根据ID获取是否退单对象
     * 
     * @return
     */
    SystemsSet getIsBackOrderById(Long setId);

    /**
     * 设置系统自动更新未付款订单作废时间
     * 
     * @param bsetOrderTime
     * @return
     */
    Integer updatesetOrderTime(Long bsetOrderTime);

    /**
     * 新后台更新退货说明
     * 
     * @param set
     * @return
     */
    Integer updateBackRemark(SystemsSet set);

    /**
     * 获取系统设置未付款订单作废时间
     * 
     * @return
     */
    Long getTimeFromNpset();

    /**
     * 更新退货说明
     * 
     * @param backInfoRemark
     * @return
     */
    int updateBackInfo(String backInfoRemark);

    /**
     * 获取退货说明
     * 
     * @return
     */
    String queryBackInfoRemark();

}
