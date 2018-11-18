package com.ningpai.m.weixin.dao;

import com.ningpai.m.weixin.bean.WXGroup;

/**
 * 微信群发租Mapper
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年9月3日 下午3:25:25
 * @version0.0.1
 */
public interface WXGroupMapper {
    /**
     * 根据主键删除
     *
     * @param wxId
     * */
    int deleteByPrimaryKey(Long wxId);

    /**
     * 新增一条记录
     *
     * @param record
     * */
    int insert(WXGroup record);

    /**
     * 新增记录
     *
     * @param record
     * */
    int insertSelective(WXGroup record);

    /**
     * 根据主键查询
     *
     * @param wxId
     * */
    WXGroup selectByPrimaryKey(Long wxId);

    /**
     * 根据主键修改
     *
     * @param record
     * */
    int updateByPrimaryKeySelective(WXGroup record);

    /**
     * 根据主键进行修改
     *
     * @param record
     * */
    int updateByPrimaryKey(WXGroup record);

    /**
     * 检查OpenID
     * 
     * @param openId
     * @return
     */
    Long checkOpenIdExist(String openId);
}
