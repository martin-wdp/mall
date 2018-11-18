package com.ningpai.system.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.system.bean.PricePrecisionCof;

/**
 * 价格精度设置数据操作接口
 * 
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-20 17:16:10
 * @version V1.0
 */
@Repository
public interface IPricePrecisionCofDao {

    /**
     * 保存价格精度设置
     * 
     * @param pricePrecisionCof
     *            价格精度设置对象
     * @return 是否保存成功
     * @author NINGPAI_xiaomm
     * @since 2014-03-20 17:16:10
     */
    boolean savePricePrecisionCof(PricePrecisionCof pricePrecisionCof);

    /**
     * 修改价格精度设置
     * 
     * @param pricePrecisionCof
     *            待修改价格精度设置对象
     * @return 更新影响的条数
     * @author NINGPAI_xiaomm
     * @since 2014-03-20 17:16:10
     */
    int updatePricePrecisionCof(PricePrecisionCof pricePrecisionCof);

    /**
     * 根据价格精度设置对象的id查询价格精度设置对象
     * 
     * @param id
     *            价格精度设置id
     * @return 价格精度设置对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-20 17:16:10
     */
    PricePrecisionCof getPricePrecisionCofById(int id);

    /**
     * 根据价格精度设置对象的id字符集合查询价格精度设置对象
     * 
     * @param ids
     *            价格精度设置id字符集合(如果多个使用,分割)
     * @return 价格精度设置对象集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-20 17:16:10
     */
    List<PricePrecisionCof> getPricePrecisionCofByIds(String ids);

    /**
     * 根据价格精度设置对象的id字符集合删除价格精度设置对象
     * 
     * @param ids
     *            价格精度设置id字符集合(如果多个使用,分割)
     * @return 删除价格精度设置对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-20 17:16:10
     */
    int deletePricePrecisionCof(String ids);

    /**
     * 更新价格精度设置对象的单个字段 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 更新价格精度设置对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-20 17:16:10
     */
    int updatePricePrecisionCofFieldById(Map<String, Object> parameter);

    /**
     * 根据价格精度设置对象的单个字段查询价格精度设置对象信息总数 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 价格精度设置对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-20 17:16:10
     */
    int getPricePrecisionCofByFieldTotal(Map<String, Object> parameter);

    /**
     * 根据价格精度设置对象的单个字段查询价格精度设置对象信息 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 价格精度设置对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-20 17:16:10
     */
    List<PricePrecisionCof> getPricePrecisionCofByField(
            Map<String, Object> parameter);

    /**
     * 查询价格精度设置对象信息总数 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 价格精度设置对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-20 17:16:10
     */
    int queryPricePrecisionCofTotal(Map<String, Object> parameter);

    /**
     * 分页查询价格精度设置对象信息 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 价格精度设置对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-20 17:16:10
     */
    List<PricePrecisionCof> queryPricePrecisionCofByPage(
            Map<String, Object> parameter);
}
