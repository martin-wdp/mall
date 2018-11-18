package com.ningpai.goods.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.goods.bean.WareCity;
import com.ningpai.goods.bean.WareHouse;
import com.ningpai.goods.vo.WareHouseVo;

/**
 * 仓库DAO
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年4月23日 上午9:47:22
 * @version 1.0
 */
public interface WareHouseMapper {
    /**
     * 查询库存信息
     *
     * */
    List<WareHouse> findWares();

    /**
     * 删除仓库信息
     * 
     * @param map
     *            参数Map
     * @return 删除的行数
     */
    int deleteByPrimaryKey(Map<String, Object> map);

    /**
     * 批量删除仓库信息
     * 
     * @param map
     *            参数Map
     * @return 删除的行数
     */
    int batchDelWare(Map<String, Object> map);

    /**
     * 新建仓库信息
     * 
     * @param record
     *            待插入的实体
     * @return 插入的ID
     */
    Long insertSelective(WareHouse record);

    /**
     * 根据主键查询仓库信息
     * 
     * @param wareId
     *            仓库ID
     * @return 查询到的实体
     */
    WareHouseVo selectByPrimaryKey(Long wareId);

    /**
     * 更新仓库信息
     * 
     * @param record
     *            待更新的实体
     * @return 更新的行数
     */
    int updateByPrimaryKeySelective(WareHouse record);

    /**
     * 查询所有的仓库信息
     * 
     * @return 查询到的仓库集合
     */
    List<WareHouse> queryAllWareHouse();

    /**
     * 根据参数查询仓库的数量
     * 
     * @param map
     * @return
     */
    int queryCountByParams(Map<String, Object> map);

    /**
     * 根据分页参数查询仓库的集合
     * 
     * @param map
     * @return
     */
    List<Object> queryAllWareHouseByPageBean(Map<String, Object> map);

    /**
     * 根据仓库名称查询数量
     * 
     * @param wareName
     *            仓库名称
     * @return 查询到的行货
     */
    List<WareHouse> queryWareCountByWareName(String wareName);

    /**
     * 判断标识是否已经存在
     * 
     * @param identifyId
     *            标识编号
     * @return 查询到结果数量
     */
    int identifyIsExist(String identifyId);

    /**
     * 查询已经存在的仓库的所在城市ID组
     * 
     * @return List
     */
    List<WareCity> getAllWareHouseDistrict();

    /**
     * 根据当前地区查询所在库存
     * 
     * @param distinctId
     * @return Long
     */
    Long selectWareIdByDistinctId(Long distinctId);
}
