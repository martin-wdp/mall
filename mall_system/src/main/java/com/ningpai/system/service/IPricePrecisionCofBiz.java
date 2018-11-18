package com.ningpai.system.service;

import java.util.List;
import java.util.Map;

import com.ningpai.system.bean.PricePrecisionCof;
import com.ningpai.util.PageBean;

/**
 * 价格精度设置业务接口
 * 
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-20 17:16:10
 * @version V1.0
 */
public interface IPricePrecisionCofBiz {

    /**
     * 保存价格精度设置
     * 
     * @param pricePrecisionCof
     *            价格精度设置对象
     * @return 是否保存成功 @ 自定义异常对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-20 17:16:10
     */
    boolean savePricePrecisionCof(PricePrecisionCof pricePrecisionCof);

    /**
     * 修改价格精度设置
     * 
     * @param pricePrecisionCof
     *            待修改价格精度设置对象
     * @return 更新影响的条数 @ 自定义异常对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-20 17:16:10
     */
    int updatePricePrecisionCof(PricePrecisionCof pricePrecisionCof);

    /**
     * 根据价格精度设置对象的id查询价格精度设置对象
     * 
     * @param id
     *            价格精度设置id
     * @return 价格精度设置对象 @ 自定义异常对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-20 17:16:10
     */
    PricePrecisionCof getPricePrecisionCofById(int id);

    /**
     * 根据价格精度设置对象的id字符集合查询价格精度设置对象
     * 
     * @param ids
     *            价格精度设置id字符集合(如果多个使用,分割)
     * @return 价格精度设置对象集合 @ 自定义异常对象
     * @author system
     * @since 2014-03-20 17:16:10
     */
    List<PricePrecisionCof> getPricePrecisionCofByIds(String ids);

    /**
     * 根据价格精度设置对象的id字符集合删除价格精度设置对象
     * 
     * @param ids
     *            价格精度设置id字符集合(如果多个使用,分割)
     * @return 删除价格精度设置对象的数目 @ 自定义异常对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-20 17:16:10
     */
    int deletePricePrecisionCof(String ids);

    /**
     * 更新价格精度设置对象的单个字段 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 更新价格精度设置对象的数目 @ 自定义异常对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-20 17:16:10
     */
    int updatePricePrecisionCofFieldById(Map<String, Object> parameter);

    /**
     * 根据价格精度设置对象的单个字段查询价格精度设置对象信息总数 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End） 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 价格精度设置对象信息总数 @ 自定义异常对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-20 17:16:10
     */
    int getPricePrecisionCofByFieldTotal(Map<String, Object> parameter);

    /**
     * 根据价格精度设置对象的单个字段查询价格精度设置对象信息 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @param pageBean
     *            分页对象
     * @return 价格精度设置对象的集合 @ 自定义异常对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-20 17:16:10
     */
    PageBean getPricePrecisionCofByField(Map<String, Object> parameter, PageBean pageBean);

    /**
     * 查询价格精度设置对象信息总数
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 价格精度设置对象信息总数 @ 自定义异常对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-20 17:16:10
     */
    int queryPricePrecisionCofTotal(Map<String, Object> parameter);

    /**
     * 分页查询价格精度设置对象信息 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 价格精度设置对象的集合 @ 自定义异常对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-20 17:16:10
     */
    PageBean queryPricePrecisionCofByPage(Map<String, Object> parameter, PageBean pageBean);

}
