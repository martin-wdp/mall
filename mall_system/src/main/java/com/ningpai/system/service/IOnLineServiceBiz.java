package com.ningpai.system.service;

import java.util.List;
import java.util.Map;

import com.ningpai.system.bean.OnLineService;
import com.ningpai.util.PageBean;

/**
 * 在线客服业务接口
 * 
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-27 17:40:56
 * @version V1.0
 */
public interface IOnLineServiceBiz {

    /**
     * 保存在线客服
     * 
     * @param onLineService
     *            在线客服对象
     * @return 在线客服记录ID @ 自定义异常对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-27 17:40:56
     */
    int saveOnLineService(OnLineService onLineService);

    /**
     * 修改在线客服
     * 
     * @param onLineService
     *            待修改在线客服对象
     * @return 更新影响的条数 @ 自定义异常对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-27 17:40:56
     */
    int updateOnLineService(OnLineService onLineService);

    /**
     * 根据在线客服对象的id查询在线客服对象
     * 
     * @param id
     *            在线客服id
     * @return 在线客服对象 @ 自定义异常对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-27 17:40:56
     */
    OnLineService getOnLineServiceById(int id);

    /**
     * 根据在线客服对象的id字符集合查询在线客服对象
     * 
     * @param ids
     *            在线客服id字符集合(如果多个使用,分割)
     * @return 在线客服对象集合 @ 自定义异常对象
     * @author system
     * @since 2014-03-27 17:40:56
     */
    List<OnLineService> getOnLineServiceByIds(String ids);

    /**
     * 根据在线客服对象的id字符集合删除在线客服对象
     * 
     * @param ids
     *            在线客服id字符集合(如果多个使用,分割)
     * @return 删除在线客服对象的数目 @ 自定义异常对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-27 17:40:56
     */
    int deleteOnLineService(String ids);

    /**
     * 更新在线客服对象的单个字段 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 
     * @param parameter
     *            参数Map(key: 字段名 value: 字段值)
     * @return 更新在线客服对象的数目 @ 自定义异常对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-27 17:40:56
     */
    int updateOnLineServiceFieldById(Map<String, Object> parameter);

    /**
     * 根据在线客服对象的单个字段查询在线客服对象信息总数 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End） 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            参数Map(key: 字段名 value: 字段值)
     * @return 在线客服对象信息总数 @ 自定义异常对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-27 17:40:56
     */
    int getOnLineServiceByFieldTotal(Map<String, Object> parameter);

    /**
     * 根据在线客服对象的单个字段查询在线客服对象信息 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 
     * @param parameter
     *            参数Map(key: 字段名 value: 字段值)
     * @param pageBean
     *            分页对象
     * @return 在线客服对象的集合 @ 自定义异常对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-27 17:40:56
     */
    PageBean getOnLineServiceByField(Map<String, Object> parameter, PageBean pageBean);

    /**
     * 查询在线客服对象信息总数
     * 
     * @param parameter
     *            参数Map(key: 字段名 value: 字段值)
     * @return 在线客服对象信息总数 @ 自定义异常对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-27 17:40:56
     */
    int queryOnLineServiceTotal(Map<String, Object> parameter);

    /**
     * 分页查询在线客服对象信息 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            参数Map(key: 字段名 value: 字段值)
     * @return 在线客服对象的集合 @ 自定义异常对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-27 17:40:56
     */
    PageBean queryOnLineServiceByPage(Map<String, Object> parameter, PageBean pageBean);

    /**
     * 查询在线客服设置
     * @return
     */
    OnLineService selectSetting();

}
