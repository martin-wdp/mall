package com.ningpai.system.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.system.bean.StockWarning;
import com.ningpai.system.util.StorkWarningUtil;

/**
 * 库存预警数据接口层
 * 
 * @author xiaogu
 *
 */

public interface StockWarningMapper {

    /**
     * 查询库存预警下限
     * 
     * @return
     */
    StockWarning select();

    /**
     * 更新库存预警下限
     * 
     * @param sw
     * @return
     */
    int update(StockWarning sw);

    /**
     * 查询库存预警货品信息
     * 
     * @param map
     * @return
     */
    List<Object> selectGoods(Map<String, Object> map);

    /**
     * 查询库存预警货品信息
     * 
     * @param map
     * @return
     */
    List<Object> selectWare(Map<String, Object> map);

    /**
     * 查询分页记录数
     * 
     * @param map
     * @return
     */
    int selectcount(Map<String, Object> map);

    /**
     * 查询仓库分页记录数
     * 
     * @param map
     * @return
     */
    int selectcounts(Map<String, Object> map);

    /**
     * 根据id查询货品库存用于修改
     * 
     * @param id
     * @return
     */
    StorkWarningUtil selectbyId(Long wareid);

    /**
     * 更新库存
     * 
     * @param storkWarningUtil
     * @return
     */
    int updatestock(StorkWarningUtil storkWarningUtil);

    /**
     * 按货品名称查询库存预警货品信息
     * 
     * @param map
     * @return
     */
    List<Object> selectGoodsbyname(Map<String, Object> map);

    /**
     * 按货品名称查询分页记录数
     * 
     * @param map
     * @return
     */
    int selectcountbyname(Map<String, Object> map);

}
