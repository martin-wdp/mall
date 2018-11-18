package com.ningpai.third.seller.mapper;

import java.util.List;

import com.ningpai.third.seller.bean.ThirdMessageModel;

/**
 * 第三方消息接收Mapper
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年5月20日 上午11:37:34
 * @version 0.0.1
 */
public interface ThirdMessageModelMapper {
    /**
     * 根据主键删除第三方消息接收Bean
     * @param messModId
     * @return
     */
    int deleteByPrimaryKey(Long messModId);

    /**
     * 新增第三方消息接收Bean
     * @param record
     * @return
     */
    int insert(ThirdMessageModel record);

    /**
     * 新增第三方消息接收Bean
     * @param record
     * @return
     */
    int insertSelective(ThirdMessageModel record);

    /**
     * 根据主键获取第三方消息接收Bean
     * @param messModId
     * @return
     */
    ThirdMessageModel selectByPrimaryKey(Long messModId);

    /**
     * 根据主键修改第三方消息接收Bean
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(ThirdMessageModel record);

    /**
     * 根据主键修改第三方消息接收Bean
     * @param record
     * @return
     */
    int updateByPrimaryKey(ThirdMessageModel record);

    /**
     * 根据商家编号查找商家消息设置
     * 
     * @param storeId
     *            商家编号 {@link Long}
     * @return List<ThirdMessageModel> 商家消息接收设置集合 {@link List} {@link ThirdMessageModel}
     */
    List<ThirdMessageModel> selectAllMessModel(Long storeId);
}
