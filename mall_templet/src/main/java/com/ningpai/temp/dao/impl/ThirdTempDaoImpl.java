package com.ningpai.temp.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.temp.bean.SysTemp;
import com.ningpai.temp.dao.ThirdTempDao;

/**
 * 模板设置数据操作类
 * 
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-29 17:43:53
 * @version V1.0
 */
@Repository("ThirdTempDao")
public class ThirdTempDaoImpl extends BasicSqlSupport implements ThirdTempDao {

    /**
     * 修改模板设置
     * 
     * @param sysTemp
     *            待修改模板设置对象
     * @return 更新影响的条数
     * @author NINGPAI_xiaomm
     * @since 2014-03-29 17:43:53
     */
    public final int updateSysTemp(final SysTemp sysTemp) {
        return this.update("com.ningpai.temp.dao.ThirdTempDao.updateSysTemp", sysTemp);
    }

    /**
     * 根据模板设置对象的id查询模板设置对象
     * 
     * @param id
     *            模板设置id
     * @return 模板设置对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-29 17:43:53
     */
    public final SysTemp getSysTempById(final int id) {
        return this.selectOne("com.ningpai.temp.dao.ThirdTempDao.getSysTempById", id);
    }

    /**
     * 根据模板设置对象的单个字段查询模板设置对象信息 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End） 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 模板设置对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-29 17:43:53
     */
    public final List<SysTemp> getSysTempByField(final Map<String, Object> parameter) {
        return this.selectList("com.ningpai.temp.dao.ThirdTempDao.getSysTempByField", parameter);
    }

}
