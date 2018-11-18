package com.ningpai.weixin.mapper;

import java.util.List;

import com.ningpai.weixin.bean.WXGroup;

/**
 * 微信群发租Mapper
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年9月3日 下午3:25:25
 * @version0.0.1
 */
public interface WXGroupMapper {
    /**
     * 查询所有群发用户
     * 
     * @return List
     */
    List<WXGroup> selectAllGroup();

}
