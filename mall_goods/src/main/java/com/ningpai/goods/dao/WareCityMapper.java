package com.ningpai.goods.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.ningpai.goods.bean.FollowAndCityVo;
import com.ningpai.goods.bean.WareCity;

/**
 * 仓库关联城市DAO
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年4月28日 下午2:42:27
 * @version 1.0
 */
public interface WareCityMapper {

    /**
     * 判断该仓库下的商品是否被收藏
     * 
     * @param wereId
     */
    List<FollowAndCityVo> selectFollow(Long wereId, BigDecimal productPrices, BigDecimal productVipPrices,
            Long goodsId);

    /**
     * 根据仓库ID查询所有的关联记录
     * 
     * @param wareId
     *            仓库ID
     * @return 查询到的集合
     */
    List<WareCity> queryAllByWareId(Long wareId);

    /**
     * 插入记录
     * 
     * @param record
     *            待插入的实体
     * @return 插入的行数
     */
    int insertSelective(WareCity record);

    /**
     * 根据选择的城市ID和仓库ID删除未被选中的记录
     * 
     * @param map
     *            封装参数的Map
     * @return 删除的行数
     */
    int delNotInChecked(Map<String, Object> map);

    /**
     * 根据仓库ID和城市ID查询是否已经关联过记录
     * 
     * @param map
     *            封装参数的Map
     * @return 查询到的行数
     */
    int queryByWareIdAndDistinctId(Map<String, Object> map);

    /**
     * 根据仓库id删除地址
     * 
     * @param wareId
     *            仓库id
     * @return
     */
    int deleteCityByWareId(Long wareId);

    /**
     * 查询不可用的地区id
     * 
     * @param wareId
     *            修改需要传入修改的仓库id，进行排除
     * @return
     */
    List<Long> selectCityId(Map<String, Object> map);

}
