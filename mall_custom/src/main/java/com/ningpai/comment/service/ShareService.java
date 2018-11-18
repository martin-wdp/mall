/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.comment.service;

import java.util.List;

import com.ningpai.comment.bean.Share;
import com.ningpai.comment.bean.ShareReply;
import com.ningpai.comment.vo.ShareReplyVo;
import com.ningpai.comment.vo.ShareVo;
import com.ningpai.util.PageBean;

/**
 * 晒单Service
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年7月1日 上午10:16:42
 * @version 0.0.1
 */
public interface ShareService {

    /**
     * 查询前size个热门晒单
     * 
     * @param size
     * @return 晒单集合 {@link List}
     */
    List<Object> getTopShare(int size);

    /**
     * 保存晒单
     * 
     * @param orderGoodsId
     *            订单-商品Id
     * @param orderGoodsId2
     *            晒单内容
     * @param imageNames
     *            晒单图片路径
     */
    void saveShare(Share share, Long customerId, Long orderGoodsId, String imageNames, Long orderId);
    /**
     * 保存晒单
     *
     * @param orderGoodsId
     *            订单-商品Id
     * @param share
     *            晒单
     * @param imageNames
     *            晒单图片路径
     */
    int saveShare(Long goodsInfoId,Share share, Long customerId, Long orderGoodsId, String imageNames);

    /**
     * 根据晒单Id查询晒单详情
     * 
     * @param shareId
     * @return {@link Object}
     */
    Object queryShareById(Long shareId);

    /**
     * 保存晒单回复
     * 
     * @param reply
     */
    int saveShareReply(ShareReply reply);

    /**
     *根据评论编号获取所有回复内容
     *
     * @param shareId
     * */
    List<ShareReplyVo> queryShareReplyByShareId(Long shareId);

    /**
     * 根据条件查询晒单列表
     * 
     * @param pageBean
     *            分页辅助类 {@link PageBean}
     * @param share
     *            晒单类 {@link Share}
     * @return PageBean 分页辅助类 {@link PageBean}
     */
    PageBean selectAllShareByShare(PageBean pageBean, ShareVo share);

    /**
     * 查询晒单详情
     * 
     * @param shareId
     *            晒单编号
     * @return 晒单详情 {@link ShareVo}
     */
    ShareVo selectShareDetail(Long shareId);

    /**
     * 修改晒单显示
     * 
     * @param share
     *            晒单对象 {@link Share}
     * @return 0失败 1成功
     */
    int updateShare(Share share);

    /**
     * 删除晒单
     * 
     * @param parameterValues
     *            数组 晒单编号
     * @return 0失败 1成功
     */
    int deleteShare(String[] parameterValues);

    /**
     * 将晒单推荐到首页
     * 
     * @param parameterValues
     *            数组 晒单编号
     * @return 0失败 1成功
     */
    int updateShareToIndex(String[] parameterValues);

    /**
     * 判断首页展示的晒单条数是否超过限制 8条
     * 
     * @param
     * @return true 没超过 false已满或者已超过限制数量
     */
    boolean checkIndexShareCount(Long count);

    /**
     * 将单条显示到首页
     * 
     * @param share
     *            晒单对象 {@link Share}
     * @return 0失败 1成功
     */
    int updateShareToIndexOne(Share share);

    /**
     * 修改评论是否显示
     *
     * */
    int updateShareRep(ShareReply replay);
 }
