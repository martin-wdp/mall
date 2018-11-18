package com.ningpai.system.service.impl;

import com.ningpai.system.bean.Receivables;
import com.ningpai.system.dao.ReceivablesMapper;
import com.ningpai.system.service.ReceivablesService;
import com.ningpai.util.PageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 收款单业务实现类 Created by houyichang on 2015/8/17.
 */
@Service("receivablesService")
public class ReceivablesServiceImpl implements ReceivablesService {
    /** 收款单dao层 */
    @Resource(name = "receivablesMapper")
    private ReceivablesMapper receivablesMapper;

    /**
     * 添加收款单信息
     *
     * @param receivables
     * @return int
     * @author houyichang 2015/8/17
     */
    @Override
    public int addReceivables(Receivables receivables) {
        return this.receivablesMapper.addReceivables(receivables);
    }

    /**
     * 分页按条件查询收款单信息
     *
     * @param map
     * @return pageBean
     * @author houyichang 2015/8/17
     */
    @Override
    public List<Object> queryReceivableByPageBean(Map<String, Object> map) {
        return this.receivablesMapper.queryReceivableByPageBean(map);
        // 回头补功能
    }

    /**
     * 根据参数查询行数
     *
     * @param map
     * @return int
     * @author houyichang 2015/8/17
     */
    @Override
    public int queryCountByMap(Map<String, Object> map) {
        return this.receivablesMapper.queryCountByMap(map);
        // 回头补功能
    }

    /**
     * 根据订单编号查询收款单信息 前端支付成功回调函数方法内调用
     *
     * @param orderCode
     * @return receivables
     * @author houyichang 2015/8/18
     */
    @Override
    public Receivables queryByOrderCode(String orderCode) {
        return this.receivablesMapper.queryByOrderCode(orderCode);
    }

    /**
     * 根据订单编号修改订单支付状态为已支付 前端支付成功回调函数方法内调用
     *
     * @param receivables
     * @return
     * @author houyichang 2015/8/18
     */
    @Override
    public int updatePayStatus(Receivables receivables) {
        return this.receivablesMapper.updatePayStatus(receivables);
    }

    /**
     * 分页查询收款单信息
     *
     * @param pageBean
     * @author houyichang 2015/8/18
     */
    @Override
    public PageBean searchReceivables(PageBean pageBean, String searchText,
            String condition, String status) {
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("startRowNum", pageBean.getStartRowNum());
        map.put("endRowNum", pageBean.getEndRowNum());

        map.put("condition", condition);
        map.put("searchText", searchText);
        map.put("status", status);

        // 查询总数
        pageBean.setRows(this.receivablesMapper.queryCountByMap(map));

        // 查询条件封装
        map.put("start", pageBean.getStartRowNum());
        map.put("number", pageBean.getEndRowNum());

        try {
            // 查询列表页
            pageBean.setList(this.receivablesMapper
                    .queryReceivableByPageBean(map));
        } finally {
            map = null;
        }

        return pageBean;
    }

    /**
     * 根据收款单编号查询收款单详细信息 前端收款单查看详情处调用
     *
     * @param cashRegisterId
     * @author houyichang 2015/8/19
     */
    @Override
    public Receivables queryReceivablesDetail(Long cashRegisterId) {
        return this.receivablesMapper.queryReceivablesDetail(cashRegisterId);
    }
}
