/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.third.seller.service;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.ningpai.third.seller.bean.StoreInfo;
import com.ningpai.third.seller.bean.ThirdMessageModel;
import com.ningpai.third.seller.bean.ThirdStoreMess;

/**
 * 商家信息Service
 *
 * @author NINGPAI-zhangqiang
 * @since 2014年5月5日 下午6:27:09
 * @version 0.0.1
 */
public interface SellerService {


    /**
     * 根据店铺员工ID获取店铺信息
     *
     * @param customerId
     *            商家会员编号
     * @return SotreInfo {@link com.ningpai.third.seller.bean.StoreInfo}
     */
    StoreInfo selectByEmployeeId(Long customerId);

    /**
     * 很据卖家编号查找店铺信息
     * 
     * @param customerId
     *            商家会员编号
     * @return SotreInfo {@link com.ningpai.third.seller.bean.StoreInfo}
     */
    StoreInfo selectByCustomerId(Long customerId);

    /**
     * 修改商家信息
     * 
     * @param storeInfo
     *            商家信息 {@link StoreInfo}
     * @return 0失败 1成功
     */
    int updateByStoreInfo(StoreInfo storeInfo, MultipartHttpServletRequest multipartRequest, HttpServletRequest request);

    /**
     * 根据商家编号查找商家消息设置
     * 
     * @param storeId
     *            商家编号 {@link Long}
     * @return List<ThirdMessageModel> 商家消息接收设置集合 {@link List} {@link ThirdMessageModel}
     */
    List<ThirdMessageModel> selectMessByStoreId(Long storeId);

    /**
     * 查询当前要设置的消息设置
     * 
     * @param request
     * @param mid
     *            模块编号
     * @return ThirdStoreMess 消息设置 {@link ThirdStoreMess}
     */
    ThirdStoreMess queryStoreMessBySidAndMid(HttpServletRequest request, Long mid);

    /**
     * 修改消息接收设置
     * 
     * @param request
     * @param mess
     *            消息设置 {@link ThirdStoreMess}
     * @return 0失败 1成功
     */
    int updateStoreMess(HttpServletRequest request, ThirdStoreMess mess);

    /**
     * 添加消息接收设置
     * 
     * @param request
     * @param mess
     *            消息设置 {@link ThirdStoreMess}
     * @return 0失败 1成功
     */
    int addStoreMess(HttpServletRequest request, ThirdStoreMess mess);

    /**
     * 保存商家信息
     * 
     * @param storeInfo
     *            商家信息 {@link StoreInfo}
     * @return 商家编号 {@link Long}
     */
    Long saveStoreInfo(HttpServletRequest request, StoreInfo storeInfo, MultipartHttpServletRequest multipartRequest);

    /**
     * 保存商家选中的分类 品牌
     * 
     * @param storeId
     *            商家编号 {@link Long}
     * @param cids
     *            商家选中的分类逗号隔开 {@link String}
     * @param bids
     *            商家选中的品牌逗号隔开 {@link String}
     * @return 0失败 1成功 {@link Integer}
     */
    int saveStoreCateAndBrand(HttpServletRequest request, Long storeId, String cids, String bids);

    /**
     * 修改第三方店铺信息-不修改文件相关字段
     * 
     * @author NINGPAI-WangHaiYang
     * @param storeInfo
     * @return
     */
    int updateStoreIndexState(StoreInfo storeInfo);

    /**
     * 查询打回信息
     * 
     * @param customerId
     *            会员编号 {@link Long}
     * @return 包含打回信息的商家信息 {@link StoreInfo}
     */
    StoreInfo selectRefuseInfo(Long customerId);

    /**
     * 修改第三方店铺信息-不修改文件相关字段
     * 
     * @author NINGPAI-WangHaiYang
     * @param storeInfo
     *            商家信息 {@link StoreInfo}
     * @return 0失败 1成功 {@link Integer}
     */
    int updateByStoreInfo(StoreInfo storeInfo, Long customerId);

    /**
     * 根据店铺名称查找
     * @param storeName
     * @return
     */
    StoreInfo selectByStoreName(String storeName);

    /**
     * 很据卖家编号查找店铺信息
     * 
     * @param storeId
     *            商家会员编号
     * @return SotreInfo {@link com.ningpai.third.seller.bean.StoreInfo}
     */
    StoreInfo selectByStoreId(Long storeId);

    /**
     * 查询所有已开通的店铺信息
     * 
     * @return List<StoreInfo> {@link com.ningpai.third.seller.bean.StoreInfo}
     */
    List<StoreInfo> selectAll();

    /**
     * 查询店铺名称是否已使用
     *第三方后台开通店铺时调用
     *
     * @param storeName    页面传入的店铺名称
     * @return              查询出的数量
     * @author houyichang  2015/9/18
     * */
    int queryCountByStoreName(String storeName);
}
