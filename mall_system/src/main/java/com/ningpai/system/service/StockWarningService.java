package com.ningpai.system.service;


import com.ningpai.system.bean.StockWarning;
import com.ningpai.system.util.StorkWarningUtil;
import com.ningpai.util.PageBean;
import com.ningpai.util.SelectBean;

/**
 * 库存报警查询库存下限业务接口
 * @author xiaogu
 *
 */

public interface StockWarningService {
    /**
     * 查询库存下限
     * @return
     */
    StockWarning select();
    
    
    /**
     * 更新库存下限
     * @param sw
     * @return
     */
    int update(StockWarning sw);
    
    /**
     * 查询库存预警货品信息
     * @param selectBean
     * @param bean
     * @return
     */
    PageBean selectGoods(StorkWarningUtil storkWarningUtil, PageBean pageBean);
    
    /**
     * 查询库存预警仓库信息
     * @param selectBean
     * @param bean
     * @return
     */
    PageBean selectWare(StorkWarningUtil storkWarningUtil, PageBean pageBean);


    /**
     *  根据id查询货品库存用于修改
     * @param id
     * @return
     */
    StorkWarningUtil selectbyId(Long wareid);


    /**
     * 跟新库存
     * @param storkWarningUtil
     * @return
     */
    int updatestock(StorkWarningUtil storkWarningUtil);


    /**
     * 按货品名字搜索货品
     * @param storkWarningUtil
     * @param pageBean
     * @return
     */
    PageBean selectgoodLists(PageBean pageBean, SelectBean selectBean);
}
