/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.comment.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.comment.bean.Share;
import com.ningpai.comment.bean.ShareImg;
import com.ningpai.comment.bean.ShareReply;
import com.ningpai.comment.vo.ShareReplyVo;
import com.ningpai.comment.vo.ShareVo;

/**
 * 晒单Mapper
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年7月1日 下午4:34:33
 * @version 0.0.1
 */
public interface ShareMapper {

    /**
     * 查询前size条晒单
     * 
     * @param size
     * @return
     */
    List<Object> queryTopShare(int size);

    /**
     * 保存晒单。
     * 
     * @param shareImg
     * @return 返回上传后的文件路径
     */
    void saveShare(Share share);

    /**
     * 保存晒单文件路径
     * 
     * @param shareImg
     */
    void saveShareImg(ShareImg shareImg);

    /**
     * 根据晒单Id查询晒单详情
     * 
     * @param shareId
     * @return
     */
    Object selectShareById(Long shareId);

    /**
     * 保存晒单回复
     * 
     * @param reply
     */
    int saveShareReply(ShareReply reply);

    /**
     * 根据评论id查询所有回复内容
     *
     * @param shareId
     * */
    List<ShareReplyVo> queryShareReplyByShareId(Long shareId);

    /**
     * 修改晒单状态
     * 
     * @param paramMap
     * @return 0修改失败 1修改成功
     */
    int updateShareIdByOrderGoodsId(Map<String, Object> paramMap);

    /**
     * 按条件查询晒单数量
     * 
     * @param share
     *            晒单对象 {@link Share}
     * @return Long 数量 {@link Long}
     */
    Long selectAllShareCount(ShareVo share);

    /**
     * 按条件查询晒单集合
     * 
     * @param paramMap
     *            条件Map {@link Map}
     * @return List<Object> 列表集合 {@link List}
     */
    List<Object> selectAllShare(Map<String, Object> paramMap);

    /**
     * 查询晒单详情
     * 
     * @param shareId
     *            晒单编号
     * @return {@link ShareVo}
     */
    ShareVo selectShareDetail(Long shareId);

    /**
     * 修改晒单显示
     * 
     * @param share
     * @return 0失败 1成功
     */
    int updateShare(Share share);

    /**
     * 修改晒单显示
     *
     * @param replay
     * @return 0失败 1成功
     */
    int updateShareReplay(ShareReply replay);

    /**
     * 删除晒单
     * 
     * @param paramMap
     * @return 0失败 1成功
     */
    int deleteShareByBids(Map<String, Object> paramMap);

    /**
     * 将晒单推荐到首页
     * 
     * @param paramMap
     *            晒单参数 {@link Map}
     * @return 0失败 1成功
     */
    int updateShareToIndex(Map<String, Object> paramMap);

    /**
     * 判断首页展示的晒单条数是否超过限制 8条
     * 
     * @return 目前显示晒单的条数 {@link Long}
     */
    Long queryIndexShareCount();
}

