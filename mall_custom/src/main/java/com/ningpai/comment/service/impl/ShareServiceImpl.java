/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.comment.service.impl;

import com.ningpai.comment.bean.Share;
import com.ningpai.comment.bean.ShareImg;
import com.ningpai.comment.bean.ShareReply;
import com.ningpai.comment.dao.ShareMapper;
import com.ningpai.comment.service.ShareService;
import com.ningpai.comment.vo.ShareReplyVo;
import com.ningpai.comment.vo.ShareVo;
import com.ningpai.common.util.ConstantUtil;
import com.ningpai.customer.service.CustomerPointServiceMapper;
import com.ningpai.other.util.CustomerConstantStr;
import com.ningpai.util.PageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 晒单Service
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年7月1日 上午10:16:42
 * @version 0.0.1
 */
@Service("shareServiceNew")
public class ShareServiceImpl implements ShareService {

    private ShareMapper shareMapper;
    private CustomerPointServiceMapper customerPointServiceMapper;

    /*
     * 
     * 
     * @see com.ningpai.comment.service.ShareService#getTopShare(int)
     */
    @Override
    public List<Object> getTopShare(int size) {
        return shareMapper.queryTopShare(size);
    }

    /*
     * 
     * 
     * @see com.ningpai.comment.service.ShareService#saveShare(com.ningpai.comment.bean.Share, java.lang.Long, java.lang.Long, java.lang.String)
     */
    @Override
    public void saveShare(Share share, Long customerId, Long orderGoodsId, String imageNames, Long orderId) {
        // 保存晒单内容
        share.setCustomerId(customerId);
        share.setCreateTime(new Date());
        share.setGoodsId(orderGoodsId);
        shareMapper.saveShare(share);
        // 保存晒单图片
        String[] imageArray = imageNames.split(",");
        for (String imageName : imageArray) {
            if ("".equals(imageName)) {
                continue;
            }
            ShareImg shareImg = new ShareImg();
            shareImg.setImageName(imageName);
            shareImg.setShareId(share.getShareId());
            shareMapper.saveShareImg(shareImg);
        }
        // 更新订单“晒单标记”
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("shareId", share.getShareId());
        paramMap.put("orderGoodsId", orderGoodsId);
        paramMap.put(ConstantUtil.ORDERID, orderId);
        int count=shareMapper.updateShareIdByOrderGoodsId(paramMap);
        // 增加登录积分
        if (count>0) {
            customerPointServiceMapper.addIntegralByType(customerId, "11");
        }
    }

    @Override
    public int saveShare(Long goodsInfoId,Share share, Long customerId, Long orderGoodsId, String imageNames) {
        // 保存晒单内容
        share.setCustomerId(customerId);
        share.setCreateTime(new Date());
        //货品Id
        share.setGoodsId(goodsInfoId);
        //订单货品Id
        share.setOrderGoodsId(orderGoodsId);
        shareMapper.saveShare(share);
        // 保存晒单图片
        String[] imageArray = imageNames.split(",");
        for (String imageName : imageArray) {
            if ("".equals(imageName)) {
                continue;
            }
            ShareImg shareImg = new ShareImg();
            shareImg.setImageName(imageName);
            shareImg.setShareId(share.getShareId());
            shareMapper.saveShareImg(shareImg);
        }
        // 更新订单“晒单标记”
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("shareId", share.getShareId());
        paramMap.put("orderGoodsId", orderGoodsId);
        int count=shareMapper.updateShareIdByOrderGoodsId(paramMap);
        // 增加登录积分
        if (count>0) {
            customerPointServiceMapper.addIntegralByType(customerId, "11");
        }
        return 0;
    }

    /*
     * 
     * 
     * @see com.ningpai.comment.service.ShareService#queryShareById(java.lang.Long)
     */
    @Override
    public Object queryShareById(Long shareId) {
        return shareMapper.selectShareById(shareId);
    }

    /*
     * 
     * 
     * @see com.ningpai.comment.service.ShareService#saveShareReply(com.ningpai.comment.bean.ShareReply)
     */
    @Override
    public int saveShareReply(ShareReply reply) {
        reply.setCreateTime(new Date());
        return shareMapper.saveShareReply(reply);
    }

    /*
     * 
     * 
     * @see com.ningpai.comment.service.ShareService#selectAllShareByShare (com.ningpai.util.PageBean, com.ningpai.comment.bean.Share)
     */
    @Override
    public PageBean selectAllShareByShare(PageBean pageBean, ShareVo share) {
        Map<String, Object> paramMap = null;
        int no = 0;
        try {
            // 设置pageBean的总行数
            pageBean.setRows(Integer.parseInt(shareMapper.selectAllShareCount(share).toString()));
            no = pageBean.getRows() % pageBean.getPageSize() == 0 ? pageBean.getRows() / pageBean.getPageSize() : (pageBean.getRows() / pageBean.getPageSize() + 1);
            no = no == 0 ? 1 : no;
            // 页码大于最后一页 则设为最好一页的页码
            if (pageBean.getPageNo() >= no) {
                pageBean.setPageNo(no);
                pageBean.setStartRowNum((no - 1) * pageBean.getPageSize());
            }
            // 设置查询对象的起始编号和结束编号
            paramMap = new HashMap<String, Object>();
            paramMap.put("share", share);
            paramMap.put("startRowNum", pageBean.getStartRowNum());
            paramMap.put("endRowNum", pageBean.getEndRowNum());
            pageBean.setList(shareMapper.selectAllShare(paramMap));
        } finally {
            // 置空对象
            paramMap = null;
        }
        return pageBean;
    }

    /*
     * 
     * 
     * @see com.ningpai.comment.service.ShareService#selectShareDetail(java.lang.Long)
     */
    @Override
    public ShareVo selectShareDetail(Long shareId) {
        return shareMapper.selectShareDetail(shareId);
    }

    /*
     * 
     * 
     * @see com.ningpai.comment.service.ShareService#updateShare(com.ningpai.comment.bean.Share)
     */
    @Override
    public int updateShare(Share share) {
        return shareMapper.updateShare(share);
    }

    @Override
    public int updateShareRep(ShareReply replay) {
        return shareMapper.updateShareReplay(replay);
    }

    /*
         * 
         *
         * @see com.ningpai.comment.service.ShareService#deleteShare(java.lang.String[])
         */
    @Override
    public int deleteShare(String[] parameterValues) {
        int count = 0;
        Map<String, Object> paramMap = new HashMap<String, Object>();
        try {
            paramMap.put(CustomerConstantStr.PARAMETERVALUES, parameterValues);
            count = shareMapper.deleteShareByBids(paramMap);
        } finally {
            paramMap = null;
        }

        return count;
    }

    /*
     * 
     * 
     * @see com.ningpai.comment.service.ShareService#updateShareToIndexOne(com.ningpai.comment.bean.Share)
     */
    @Override
    public int updateShareToIndexOne(Share share) {
        int count = 0;
        Map<String, Object> paramMap = new HashMap<String, Object>();
        try {
            paramMap.put(CustomerConstantStr.PARAMETERVALUES, new String[] { share.getShareId().toString() });
            paramMap.put("isDisplay", '2');
            count = shareMapper.updateShareToIndex(paramMap);
        } finally {
            paramMap = null;
        }
        return count;
    }

    /*
     * 
     * 
     * @see com.ningpai.comment.service.ShareService#updateShareToIndex(java.lang.String[])
     */
    @Override
    public int updateShareToIndex(String[] parameterValues) {
        int count = 0;
        Map<String, Object> paramMap = new HashMap<String, Object>();
        try {
            paramMap.put(CustomerConstantStr.PARAMETERVALUES, parameterValues);
            paramMap.put("isDisplay", '2');
            count = shareMapper.updateShareToIndex(paramMap);
        } finally {
            paramMap = null;
        }

        return count;
    }

    /*
     * 
     * 
     * @see com.ningpai.comment.service.ShareService#checkIndexShareCount(java.lang.Long)
     */
    @Override
    public boolean checkIndexShareCount(Long count) {
        // 数量小于8返回true
        if ((shareMapper.queryIndexShareCount() + count) <= CustomerConstantStr.EIGHT) {
            return true;
        }
        return false;
    }

    public ShareMapper getShareMapper() {
        return shareMapper;
    }

    @Override
    public List<ShareReplyVo> queryShareReplyByShareId(Long shareId) {
        return shareMapper.queryShareReplyByShareId(shareId);
    }

    @Resource(name = "shareMapper")
    public void setShareMapper(ShareMapper shareMapper) {
        this.shareMapper = shareMapper;
    }

    public CustomerPointServiceMapper getCustomerPointServiceMapper() {
        return customerPointServiceMapper;
    }

    @Resource(name = "customerPointServiceMapper")
    public void setCustomerPointServiceMapper(CustomerPointServiceMapper customerPointServiceMapper) {
        this.customerPointServiceMapper = customerPointServiceMapper;
    }
}
