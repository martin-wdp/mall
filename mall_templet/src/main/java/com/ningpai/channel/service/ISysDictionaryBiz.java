package com.ningpai.channel.service;

import java.util.List;

import com.ningpai.channel.bean.SysDictionary;

/**
 * 系统字典业务接口
 * 
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-20 11:03:23
 * @version V1.0
 */
public interface ISysDictionaryBiz {

    /**
     * 根据系统字典对象的id查询系统字典对象
     * 
     * @param id
     *            系统字典id
     * @return 系统字典对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-20 11:03:23
     */
    SysDictionary getSysDictionaryById(int id);

    /**
     * 根据系统字典对象的单个字段查询系统字典对象信息
     * 
     * @param map
     *            参数Map(key: 字段名 value: 字段值)
     * @return 系统字典对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-20 11:03:23
     */
    List<SysDictionary> getSysDictionaryByField(Long parentId);

}
