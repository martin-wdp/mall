package com.ningpai.system.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.system.bean.StatisticsCode;

/**
 * 统计代码数据操作接口
 * 
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-21 17:03:45
 * @version V1.0
 */
@Repository
public interface IStatisticsCodeDao {

    /**
     * 保存统计代码
     * 
     * @param statisticsCode
     *            统计代码对象
     * @return 是否保存成功
     * @author NINGPAI_xiaomm
     * @since 2014-03-21 17:03:45
     */
    boolean saveStatisticsCode(StatisticsCode statisticsCode);

    /**
     * 修改统计代码
     * 
     * @param statisticsCode
     *            待修改统计代码对象
     * @return 更新影响的条数
     * @author NINGPAI_xiaomm
     * @since 2014-03-21 17:03:45
     */
    int updateStatisticsCode(StatisticsCode statisticsCode);

    /**
     * 根据统计代码对象的id查询统计代码对象
     * 
     * @param id
     *            统计代码id
     * @return 统计代码对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-21 17:03:45
     */
    StatisticsCode getStatisticsCodeById(int id);

    /**
     * 根据统计代码对象的id字符集合查询统计代码对象
     * 
     * @param ids
     *            统计代码id字符集合(如果多个使用,分割)
     * @return 统计代码对象集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-21 17:03:45
     */
    List<StatisticsCode> getStatisticsCodeByIds(String ids);

    /**
     * 根据统计代码对象的id字符集合删除统计代码对象
     * 
     * @param ids
     *            统计代码id字符集合(如果多个使用,分割)
     * @return 删除统计代码对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-21 17:03:45
     */
    int deleteStatisticsCode(String ids);

    /**
     * 更新统计代码对象的单个字段 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 更新统计代码对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-21 17:03:45
     */
    int updateStatisticsCodeFieldById(Map<String, Object> parameter);

    /**
     * 根据统计代码对象的单个字段查询统计代码对象信息总数 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 统计代码对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-21 17:03:45
     */
    int getStatisticsCodeByFieldTotal(Map<String, Object> parameter);

    /**
     * 根据统计代码对象的单个字段查询统计代码对象信息 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 统计代码对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-21 17:03:45
     */
    List<StatisticsCode> getStatisticsCodeByField(Map<String, Object> parameter);

    /**
     * 查询统计代码对象信息总数 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 统计代码对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-21 17:03:45
     */
    int queryStatisticsCodeTotal(Map<String, Object> parameter);

    /**
     * 分页查询统计代码对象信息 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 统计代码对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-21 17:03:45
     */
    List<StatisticsCode> queryStatisticsCodeByPage(Map<String, Object> parameter);

    /**
     * 查询所有统计代码
     * 
     * @return
     */
    List<StatisticsCode> selectAllStatisticsCode();

    /**
     * 修改所有统计代码为不启用
     * 
     * @return
     */
    int updateUserdStatusToNo();
}
