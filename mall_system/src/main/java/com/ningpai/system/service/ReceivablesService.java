package com.ningpai.system.service;

import com.ningpai.system.bean.Receivables;
import com.ningpai.util.PageBean;

import java.util.List;
import java.util.Map;

/**
 * 收款单逻辑实现接口
 * Created by houyichang on 2015/8/17.
 */

public interface ReceivablesService {
    /**
     * 添加收款单信息
     *
     * @author houyichang 2015/8/17
     * @param receivables
     * @return int
     * */
    int addReceivables(Receivables receivables);

    /**
     * 分页按条件查询收款单信息
     *
     * @author houyichang 2015/8/17
     * @param map
     * @return pageBean
     * */

    List<Object> queryReceivableByPageBean(Map<String,Object> map);

    /**
     * 根据参数查询行数
     *
     * @author houyichang 2015/8/17
     * @param map
     * @return int
     * */
    int queryCountByMap(Map<String,Object> map);

    /**
     * 根据订单编号查询收款单信息
     * 前端支付成功回调函数方法内调用
     *
     * @author houyichang 2015/8/18
     * @param orderCode
     * @return receivables
     * */

    Receivables queryByOrderCode(String orderCode);

    /**
     * 根据订单编号修改订单支付状态为已支付
     * 前端支付成功回调函数方法内调用
     *
     * @author houyichang 2015/8/18
     * @param receivables
     * @return
     * */

    int updatePayStatus(Receivables receivables);

    /**
     * 分页查询收款单信息
     *
     * @author houyichang 2015/8/18
     * @param pageBean
     * */

    PageBean searchReceivables(PageBean pageBean,String searchText,String condition,String status);

    /**
     * 根据收款单编号查询收款单详细信息
     * 前端收款单查看详情处调用
     *
     * @author houyichang 2015/8/19
     * @param cashRegisterId
     * */

    Receivables queryReceivablesDetail(Long cashRegisterId);

}
