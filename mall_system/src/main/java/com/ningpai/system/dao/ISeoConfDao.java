package com.ningpai.system.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.system.bean.SeoConf;

/**
 * SEO设置数据操作接口
 * 
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-24 13:35:13
 * @version V1.0
 */
@Repository
public interface ISeoConfDao {

    /**
     * 保存SEO设置
     * 
     * @param seoConf
     *            SEO设置对象
     * @return 是否保存成功
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 13:35:13
     */
    boolean saveSeoConf(SeoConf seoConf);

    /**
     * 修改SEO设置
     * 
     * @param seoConf
     *            待修改SEO设置对象
     * @return 更新影响的条数
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 13:35:13
     */
    int updateSeoConf(SeoConf seoConf);

    /**
     * 根据SEO设置对象的id查询SEO设置对象
     * 
     * @param id
     *            SEO设置id
     * @return SEO设置对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 13:35:13
     */
    SeoConf getSeoConfById(int id);

    /**
     * 根据SEO设置对象的id字符集合查询SEO设置对象
     * 
     * @param ids
     *            SEO设置id字符集合(如果多个使用,分割)
     * @return SEO设置对象集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 13:35:13
     */
    List<SeoConf> getSeoConfByIds(String ids);

    /**
     * 根据SEO设置对象的id字符集合删除SEO设置对象
     * 
     * @param ids
     *            SEO设置id字符集合(如果多个使用,分割)
     * @return 删除SEO设置对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 13:35:13
     */
    int deleteSeoConf(String ids);

    /**
     * 更新SEO设置对象的单个字段 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 更新SEO设置对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 13:35:13
     */
    int updateSeoConfFieldById(Map<String, Object> parameter);

    /**
     * 根据SEO设置对象的单个字段查询SEO设置对象信息总数 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return SEO设置对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 13:35:13
     */
    int getSeoConfByFieldTotal(Map<String, Object> parameter);

    /**
     * 根据SEO设置对象的单个字段查询SEO设置对象信息 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return SEO设置对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 13:35:13
     */
    List<SeoConf> getSeoConfByField(Map<String, Object> parameter);

    /**
     * 查询SEO设置对象信息总数 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return SEO设置对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 13:35:13
     */
    int querySeoConfTotal(Map<String, Object> parameter);

    /**
     * 分页查询SEO设置对象信息 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return SEO设置对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 13:35:13
     */
    List<SeoConf> querySeoConfByPage(Map<String, Object> parameter);

    /**
     * 获取已启用的SEO设置对象
     * 
     * @return
     */
    SeoConf querySeoByUsedStatus();

    /**
     * 修改启用状态
     * 
     * @return
     */
    int updateSeoConfByUsedStatus();

    /**
     * 删除单个SEO配置记录
     * 
     * @param seoId
     * @return
     */
    int deleteSeoByPrimaryKey(Long seoId);
}
