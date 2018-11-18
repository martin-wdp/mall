package com.ningpai.mobile.dao;

import java.util.List;

import com.ningpai.mobile.bean.MobHomePage;

/**
 * @ClassName: MobHomePageMapper
 * @Description: DAO-移动版首页
 * @author Wanghy
 * @date 2014年10月11日 下午1:51:49
 */
public interface MobHomePageMapper {
    /**
     * @Title: deleteByPrimaryKey
     * @Description: 根据ID删除
     * @param homepageId
     * @return
     */
    int deleteByPrimaryKey(Long homepageId);

    /**
     * @Title: insert
     * @Description: 添加
     * @param record
     * @return
     */
    int insert(MobHomePage record);

    /**
     * @Title: insertSelective
     * @Description: 添加-字段可选
     * @param record
     * @return
     */
    int insertSelective(MobHomePage record);

    /**
     * 得到第三方模板信息
     * @return
     */
    MobHomePage selectThirdMob(Long storeId);

    /**
     * @Title: updateByPrimaryKeySelective
     * @Description: 修改-字段可选
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(MobHomePage record);

    /**
     * @Title: updateByPrimaryKeyWithBLOBs
     * @Description: 修改-包括xml字符串
     * @param record
     * @return
     */
    int updateByPrimaryKeyWithBLOBs(MobHomePage record);

    /**
     * @Title: updateByPrimaryKey
     * @Description: 修改-包括xml字符串
     * @param record
     * @return
     */
    int updateByPrimaryKey(MobHomePage record);

    /**
     * @Title: selectByMerchantId
     * @Description: 根据商家ID查询
     * @param merchantId
     * @return
     */
    MobHomePage selectByMerchantId(Long merchantId);

    /**
     * @Title: selectByPrimaryKey
     * @Description: 根据ID获取模板信息
     * @param homepageId
     * @return
     */
    MobHomePage selectByPrimaryKey(Long homepageId);

    /**
     * @Title:selectAllHomePageByMerchantId
     * @Description:根据商家ID查询该商家的所有模板
     * @param merchantId
     * @return
     */
    List<MobHomePage> selectAllUnstatusByMerchantId(Long merchantId);

    /**
     * @Title:updateByMerchantId
     * @Description:根据商家ID修改模板的启用状态,都改成不启用
     * @param merchantId
     * @return
     */
    int updateByMerchantId(Long merchantId);

    /**
     * @Title:updateByhomepageIdAndMerchantId
     * @Description:根据商家ID和模板ID找到模板，修改启用状态为启用
     * @param homepageId
     * @param merchantId
     * @return
     */
    int updateByhomepageIdAndMerchantId(MobHomePage record);

    /**
     * @Title:queryCurrHomePageByMerchantId
     * @Description:根据商家ID获取该商家当前使用的模板信息
     * @param merchantId
     * @return
     */
    MobHomePage queryCurrHomePageByMerchantId(Long merchantId);
    /**
     * 根据商家ID获取该商家当前使用的模板信息
     *
     * @Description: 根据商家ID获取该商家当前使用的模板信息
     * @param storeId
     * @return
     */

    MobHomePage getCurrHomePageByStoreId(Long storeId);

}
