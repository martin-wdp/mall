/**
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.comment.bean;

import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 评论实体
 * 
 * @author NINGPAI-zhangqiang
 * @since 2013年12月17日 下午5:39:11
 * @version 0.0.1
 */
public class Comment {
    /**
     * 评论编号
     * 
     * @see #getCommentId()
     * @see #setCommentId(Long)
     */
    private Long commentId;
    /**
     * 会员编号
     * 
     * @see #getCustomerId()
     * @see #setCustomerId(Long)
     */
    private Long customerId;
    /**
     * 商品编号
     * 
     * @see #getGoodsId()
     * @see #setGoodsId(Long)
     */
    private Long goodsId;
    /**
     * 订单商品编号
     *
     * @see #getGoodsId()
     * @see #setGoodsId(Long)
     */
    private Long orderGoodsId;
    /**
     * 商品名称
     * 
     * @see #getGoodsName()
     * @see #setGoodsName(String)
     */
    @Pattern(regexp = "[^\\<\\>]*")
    private String goodsName;
    /**
     * 关联的规格值的集合
      */

    private List<GoodsProductReleSpecVo> specVo;
    /**
     * 评分
     * 
     * @see #getCommentScore()
     * @see #setCommentScore(BigDecimal)
     */
    private BigDecimal commentScore;
    /**
     * 内容
     * 
     * @see #getCommentContent()
     * @see #setCommentContent(String)
     */
    @Pattern(regexp = "[^\\<\\>]*")
    private String commentContent;
    /**
     * 是否显示
     * 
     * @see #getIsDisplay()
     * @see #setIsDisplay(String)
     */
    private String isDisplay;
    /**
     * 是否匿名
     * 
     * @see #getIsAnonymous()
     * @see #setIsAnonymous(String)
     */
    private String isAnonymous;
    /**
     * 发表时间
     * 
     * @see #getPublishTime()
     * @see #setPublishTime(Date)
     */
    private Date publishTime;
    /**
     * 结束时间
     * 
     * @see #getPublishTimeTo()
     * @see #setPublishTimeTo(Date)
     */
    private Date publishTimeTo;
    /**
     * 发表IP
     * 
     * @see #getPublishIp()
     * @see #setPublishIp(String)
     */
    private String publishIp;
    /**
     * 修改时间
     * 
     * @see #getModifiedTime()
     * @see #setModifiedTime(Date)
     */
    private Date modifiedTime;
    /**
     * 删除时间
     * 
     * @see #getDelTime()
     * @see #setDelTime(Date)
     */
    private Date delTime;
    /**
     * 删除标记
     * 
     * @see #getDelFlag()
     * @see #setDelFlag(String)
     */
    private String delFlag;
    /**
     * 是否咨询
     * 
     * @see #getIsConsult()
     * @see #setIsConsult(String)
     */
    private String isConsult;
    /**
     * 咨询项目
     * 
     * @see #getConsultItem()
     * @see #setConsultItem(String)
     */
    @Pattern(regexp = "[^\\<\\>]*")
    private String consultItem;
    /**
     * 昵称
     * 
     * @see #getCustomerNickname()
     * @see #setCustomerNickname(String)
     */
    @Pattern(regexp = "[^\\<\\>]*")
    private String customerNickname;
    /**
     * 分页开始的条数
     * 
     * @see #getStartRowNum()
     * @see #setStartRowNum(int)
     */
    private int startRowNum;
    /**
     * 分页结束的条数
     * 
     * @see #getEndRowNum()
     * @see #setEndRowNum(int)
     */
    private int endRowNum;
    /*
     *商品图片
      */
    @Pattern(regexp = "[^\\<\\>]*")
    private String goodsImg;
    /*
     *评论类型
      */
    private String commentType;
    /*
    *回复
     */
    private List<CommentReplay> replays;
    /*
     *回复标记
      */
    private String eFlag;
    /*
     *评论标签
      */
    @Pattern(regexp = "[^\\<\\>]*")
    private String commentTag;

    /*
     *晒单图片
      */
    private List<ShareImg> imageList;
    /*
    *会员头像
     */
    @Pattern(regexp = "[^\\<\\>]*")
    private String customerImg;
    /*
     *购买时间
      */
    private Date buyTime;
    
    /*
    *服务态度评分
     */
    private BigDecimal sattitudeScore;

    /*
    *发货速度评分
     */
    private BigDecimal dgoodsScore;
    
    /*
    *商品评价平均分
     */
    private BigDecimal cscoreAvg;
    
    /*
    *服务态度评价平均分
     */
    private BigDecimal attscoteAvg;
    
    /*
    *发货速度平均分
     */
    private BigDecimal dscoreAvg;
    
    
    /**
     * 获取购买时间
     * */
    public Date getBuyTime() {
        if (this.buyTime != null) {
            return new Date(this.buyTime.getTime());
        } else {
            return null;
        }
    }
    /**
     * 设置购买时间
     * */
    public void setBuyTime(Date buyTime) {
        if (buyTime != null) {
            Date tEmp = (Date) buyTime.clone();
            if (tEmp != null) {
                this.buyTime = tEmp;
            }
        }
    }

    public void setOrderGoodsId(Long orderGoodsId){
        this.orderGoodsId=orderGoodsId;
    }
    public Long getOrderGoodsId(){
        return orderGoodsId;
    }
    public String getCustomerImg() {
        return customerImg;
    }

    public void setCustomerImg(String customerImg) {
        this.customerImg = customerImg;
    }

    public List<ShareImg> getImageList() {
        return imageList;
    }

    public void setImageList(List<ShareImg> imageList) {
        this.imageList = imageList;
    }

    public String getCommentTag() {
        return commentTag;
    }

    public void setCommentTag(String commentTag) {
        this.commentTag = commentTag;
    }

    public String geteFlag() {
        return eFlag;
    }

    public void seteFlag(String eFlag) {
        this.eFlag = eFlag;
    }

    public List<CommentReplay> getReplays() {
        return replays;
    }

    public void setReplays(List<CommentReplay> replays) {
        this.replays = replays;
    }

    public String getCommentType() {
        return commentType;
    }

    public void setCommentType(String commentType) {
        this.commentType = commentType;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    /**
     * 获取startRowNum的值
     * 
     * @return startRowNum {@link com.ningpai.comment.bean.Comment#startRowNum}
     */
    public int getStartRowNum() {
        return startRowNum;
    }

    /**
     * 设置startRowNum的值
     * 
     * @param startRowNum
     *            {@link com.ningpai.comment.bean.Comment#startRowNum}
     */
    public void setStartRowNum(int startRowNum) {
        this.startRowNum = startRowNum;
    }

    /**
     * 获取endRowNum的值
     * 
     * @return endRowNum {@link com.ningpai.comment.bean.Comment#endRowNum}
     */
    public int getEndRowNum() {
        return endRowNum;
    }

    /**
     * 设置endRowNum的值
     * 
     * @param endRowNum
     *            {@link com.ningpai.comment.bean.Comment#endRowNum}
     */
    public void setEndRowNum(int endRowNum) {
        this.endRowNum = endRowNum;
    }

    /**
     * 获取publishTimeTo的值
     * 
     * @return publishTimeTo {@link com.ningpai.comment.bean.Comment#publishTimeTo}
     */
    public Date getPublishTimeTo() {
        if (this.publishTimeTo != null) {
            return new Date(this.publishTimeTo.getTime());
        } else {
            return null;
        }
    }

    /**
     * 设置publishTimeTo的值
     * 
     * @param publishTimeTo
     *            {@link com.ningpai.comment.bean.Comment#publishTimeTo}
     */
    public void setPublishTimeTo(Date publishTimeTo) {
        if (publishTimeTo != null) {
            Date tEmp = (Date) publishTimeTo.clone();
            if (tEmp != null) {
                this.publishTimeTo = tEmp;
            }
        }
    }

    /**
     * 获取customerNickname的值
     * 
     * @return customerNickname {@link com.ningpai.comment.bean.Comment#customerNickname}
     */
    public String getCustomerNickname() {
        return customerNickname;
    }

    /**
     * 设置customerNickname的值
     * 
     * @param customerNickname
     *            {@link com.ningpai.comment.bean.Comment#customerNickname}
     */
    public void setCustomerNickname(String customerNickname) {
        this.customerNickname = customerNickname;
    }

    /**
     * 获取commentId的值
     * 
     * @return commentId {@link com.ningpai.comment.bean.Comment#commentId}
     */
    public Long getCommentId() {
        return commentId;
    }

    /**
     * 设置commentId的值
     * 
     * @param commentId
     *            {@link com.ningpai.comment.bean.Comment#commentId}
     */
    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    /**
     * 获取customerId的值
     * 
     * @return customerId {@link com.ningpai.comment.bean.Comment#customerId}
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * 设置customerId的值
     * 
     * @param customerId
     *            {@link com.ningpai.comment.bean.Comment#customerId}
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    /**
     * 获取goodsId的值
     * 
     * @return goodsId {@link com.ningpai.comment.bean.Comment#goodsId}
     */
    public Long getGoodsId() {
        return goodsId;
    }

    /**
     * 设置goodsId的值
     * 
     * @param goodsId
     *            {@link com.ningpai.comment.bean.Comment#goodsId}
     */
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 获取goodsName的值
     * 
     * @return goodsName {@link com.ningpai.comment.bean.Comment#goodsName}
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * 设置goodsName的值
     * 
     * @param goodsName
     *            {@link com.ningpai.comment.bean.Comment#goodsName}
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    /**
     * 获取commentScore的值
     * 
     * @return commentScore {@link com.ningpai.comment.bean.Comment#commentScore}
     */
    public BigDecimal getCommentScore() {
        return commentScore;
    }

    /**
     * 设置commentScore的值
     * 
     * @param commentScore
     *            {@link com.ningpai.comment.bean.Comment#commentScore}
     */
    public void setCommentScore(BigDecimal commentScore) {
        this.commentScore = commentScore;
    }

    /**
     * 获取commentContent的值
     * 
     * @return commentContent {@link com.ningpai.comment.bean.Comment#commentContent}
     */
    public String getCommentContent() {
        return commentContent;
    }

    /**
     * 设置commentContent的值
     * 
     * @param commentContent
     *            {@link com.ningpai.comment.bean.Comment#commentContent}
     */
    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    /**
     * 获取isDisplay的值
     * 
     * @return isDisplay {@link com.ningpai.comment.bean.Comment#isDisplay}
     */
    public String getIsDisplay() {
        return isDisplay;
    }

    /**
     * 设置isDisplay的值
     * 
     * @param isDisplay
     *            {@link com.ningpai.comment.bean.Comment#isDisplay}
     */
    public void setIsDisplay(String isDisplay) {
        this.isDisplay = isDisplay;
    }

    /**
     * 获取isAnonymous的值
     * 
     * @return isAnonymous {@link com.ningpai.comment.bean.Comment#isAnonymous}
     */
    public String getIsAnonymous() {
        return isAnonymous;
    }

    /**
     * 设置isAnonymous的值
     * 
     * @param isAnonymous
     *            {@link com.ningpai.comment.bean.Comment#isAnonymous}
     */
    public void setIsAnonymous(String isAnonymous) {
        this.isAnonymous = isAnonymous;
    }

    /**
     * 获取publishTime的值
     * 
     * @return publishTime {@link com.ningpai.comment.bean.Comment#publishTime}
     */
    public Date getPublishTime() {
        if (this.publishTime != null) {
            return new Date(this.publishTime.getTime());
        } else {
            return null;
        }
    }

    /**
     * 设置publishTime的值
     * 
     * @param publishTime
     *            {@link com.ningpai.comment.bean.Comment#publishTime}
     */
    public void setPublishTime(Date publishTime) {
        if (publishTime != null) {
            Date tEmp = (Date) publishTime.clone();
            if (tEmp != null) {
                this.publishTime = tEmp;
            }
        }
    }

    /**
     * 获取publishIp的值
     * 
     * @return publishIp {@link com.ningpai.comment.bean.Comment#publishIp}
     */
    public String getPublishIp() {
        return publishIp;
    }

    /**
     * 设置publishIp的值
     * 
     * @param publishIp
     *            {@link com.ningpai.comment.bean.Comment#publishIp}
     */
    public void setPublishIp(String publishIp) {
        this.publishIp = publishIp;
    }

    /**
     * 获取modifiedTime的值
     * 
     * @return modifiedTime {@link com.ningpai.comment.bean.Comment#modifiedTime}
     */
    public Date getModifiedTime() {
        if (this.modifiedTime != null) {
            return new Date(this.modifiedTime.getTime());
        } else {
            return null;
        }
    }

    /**
     * 设置modifiedTime的值
     * 
     * @param modifiedTime
     *            {@link com.ningpai.comment.bean.Comment#modifiedTime}
     */
    public void setModifiedTime(Date modifiedTime) {
        if (modifiedTime != null) {
            Date tEmp = (Date) modifiedTime.clone();
            if (tEmp != null) {
                this.modifiedTime = tEmp;
            }
        }
    }

    /**
     * 获取delTime的值
     * 
     * @return delTime {@link com.ningpai.comment.bean.Comment#delTime}
     */
    public Date getDelTime() {
        if (this.delTime != null) {
            return new Date(this.delTime.getTime());
        } else {
            return null;
        }
    }

    /**
     * 设置delTime的值
     * 
     * @param delTime
     *            {@link com.ningpai.comment.bean.Comment#delTime}
     */
    public void setDelTime(Date delTime) {
        if (delTime != null) {
            Date tEmp = (Date) delTime.clone();
            if (tEmp != null) {
                this.delTime = tEmp;
            }
        }
    }

    /**
     * 获取delFlag的值
     * 
     * @return delFlag {@link com.ningpai.comment.bean.Comment#delFlag}
     */
    public String getDelFlag() {
        return delFlag;
    }

    /**
     * 设置delFlag的值
     * 
     * @param delFlag
     *            {@link com.ningpai.comment.bean.Comment#delFlag}
     */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * 获取isConsult的值
     * 
     * @return isConsult {@link com.ningpai.comment.bean.Comment#isConsult}
     */
    public String getIsConsult() {
        return isConsult;
    }

    /**
     * 设置isConsult的值
     * 
     * @param isConsult
     *            {@link com.ningpai.comment.bean.Comment#isConsult}
     */
    public void setIsConsult(String isConsult) {
        this.isConsult = isConsult;
    }

    /**
     * 获取consultItem的值
     * 
     * @return consultItem {@link com.ningpai.comment.bean.Comment#consultItem}
     */
    public String getConsultItem() {
        return consultItem;
    }

    /**
     * 设置consultItem的值
     * 
     * @param consultItem
     *            {@link com.ningpai.comment.bean.Comment#consultItem}
     */
    public void setConsultItem(String consultItem) {
        this.consultItem = consultItem;
    }

    /**
     * 获取SattitudeScore的值
     * */
    public BigDecimal getSattitudeScore() {
        return sattitudeScore;
    }

    /**
     * 获取DGoodsScore的值
     * */
    public BigDecimal getDgoodsScore() {
        return dgoodsScore;
    }
    /**
     * 设置DgoodsScore的值
     * */
    public void setDgoodsScore(BigDecimal dgoodsScore) {
        this.dgoodsScore = dgoodsScore;
    }
    /**
     *
     * 设置SattitudeScore的值
     * */
    public void setSattitudeScore(BigDecimal sattitudeScore) {
        this.sattitudeScore = sattitudeScore;
    }
    /**
     * 获取CscoreAvg的值
     * */
    public BigDecimal getCscoreAvg() {
        return cscoreAvg;
    }
    /**
     * 设置CscoreAvg的值
     * */
    public void setCscoreAvg(BigDecimal cscoreAvg) {
        this.cscoreAvg = cscoreAvg;
    }
    /**
     * 获取AttscoteAvg的值
     * */
    public BigDecimal getAttscoteAvg() {
        return attscoteAvg;
    }
    /**
     * 设置AttscoteAvg的值
     * */
    public void setAttscoteAvg(BigDecimal attscoteAvg) {
        this.attscoteAvg = attscoteAvg;
    }
    /**
     * 获取DscoreAvg的值
     * */
    public BigDecimal getDscoreAvg() {
        return dscoreAvg;
    }
    /**
     * 设置DscoreAvg的值
     * */
    public void setDscoreAvg(BigDecimal dscoreAvg) {
        this.dscoreAvg = dscoreAvg;
    }
    /**
     * 设置SpevVO的值
     * */
    public void setSpecVo(List<GoodsProductReleSpecVo> specVo){
        this.specVo=specVo;
    }
    /**
     * 获取SpecVO的值
     * */
    public List<GoodsProductReleSpecVo> getSpecVo(){
        return this.specVo;
    }
}
