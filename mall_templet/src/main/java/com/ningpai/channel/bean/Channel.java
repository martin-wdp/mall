package com.ningpai.channel.bean;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 实体类-频道
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年3月27日下午1:38:13
 */
public class Channel {
    /**
     * 频道ID
     * 
     */
    private Long channelId;
    /**
     * 频道名称
     */
    @NotNull
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String channelName;
    /**
     * 频道链接地址
     */
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+|()")
    private String channelUrl;
    /**
     * 关联的商品一级分类ID
     */
    private Long goodsCatId;
    /**
     * 模板ID
     */
    private Long tempId;
    /**
     * 公告模块关联的文章栏目ID
     */
    private Long infoTypeId;
    /**
     * 公告模块的名称
     */
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+|()")
    private String infoName;
    /**
     * 关键字
     */
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+|()")
    private String keyword;
    /**
     * 描述
     */
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+|()")
    private String des;
    /**
     * 删除标记
     */
    private String delflag;
    /**
     * 是否开启 0：不开启 1：开启
     */
    private String usedStart;
    /**
     * 创建人ID
     */
    private Long createUserId;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 修改人ID
     */
    private Long updateUserId;
    /**
     * 修改时间
     */
    private Date updateDate;
    /**
     * 临时字段1(分类导航级数)
     */
    private String temp1 = "2";
    /**
     * 临时字段2(分类导航是否显示广告)
     */
    private String temp2;
    /**
     * 临时字段3(分类导航是否显示品牌)
     */
    private String temp3;
    /**
     * 临时字段4(关联的页面导航ID)
     */
    private String temp4;
    /**
     * 临时字段5
     */
    private String temp5;

    /**
     * 获取频道ID
     * 
     * @return
     */
    public Long getChannelId() {
        return channelId;
    }

    /**
     * 设置频道ID
     * 
     * @param channelId
     */
    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    /**
     * 获取频道名称
     * 
     * @return
     */
    public String getChannelName() {
        return channelName;
    }

    /**
     * 设置频道名称
     * 
     * @param channelName
     */
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    /**
     * 获取频道链接地址
     * 
     * @return
     */
    public String getChannelUrl() {
        return channelUrl;
    }

    /**
     * 设置频道链接地址
     * 
     * @param channelUrl
     */
    public void setChannelUrl(String channelUrl) {
        this.channelUrl = channelUrl;
    }

    /**
     * 获取关联的商品一级分类ID
     * 
     * @return
     */
    public Long getGoodsCatId() {
        return goodsCatId;
    }

    /**
     * 设置关联的商品一级分类ID
     * 
     * @param goodsCatId
     */
    public void setGoodsCatId(Long goodsCatId) {
        this.goodsCatId = goodsCatId;
    }

    /**
     * 获取模板ID
     * 
     * @return
     */
    public Long getTempId() {
        return tempId;
    }

    /**
     * 设置模板ID
     * 
     * @param tempId
     */
    public void setTempId(Long tempId) {
        this.tempId = tempId;
    }

    /**
     * 获取公告模块关联的文章栏目ID
     * 
     * @return
     */
    public Long getInfoTypeId() {
        return infoTypeId;
    }

    /**
     * 设置公告模块关联的文章栏目ID
     * 
     * @param infoTypeId
     */
    public void setInfoTypeId(Long infoTypeId) {
        this.infoTypeId = infoTypeId;
    }

    /**
     * 获取公告模块的名称
     * 
     * @return
     */
    public String getInfoName() {
        return infoName;
    }

    /**
     * 设置公告模块的名称
     * 
     * @param infoName
     */
    public void setInfoName(String infoName) {
        this.infoName = infoName;
    }

    /**
     * 获取关键字
     * 
     * @return
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * 设置关键字
     * 
     * @param keyword
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    /**
     * 获取描述
     * 
     * @return
     */
    public String getDes() {
        return des;
    }

    /**
     * 设置描述
     * 
     * @param des
     */
    public void setDes(String des) {
        this.des = des;
    }

    /**
     * 获取是否开启
     * 
     * @return
     */
    public String getUsedStart() {
        return usedStart;
    }

    /**
     * 设置是否开启
     * 
     * @param usedStart
     */
    public void setUsedStart(String usedStart) {
        this.usedStart = usedStart;
    }

    /**
     * 获取删除标记
     * 
     * @return
     */
    public String getDelflag() {
        return delflag;
    }

    /**
     * 设置删除标记
     * 
     * @param delflag
     */
    public void setDelflag(String delflag) {
        this.delflag = delflag;
    }

    /**
     * 获取创建人ID
     * 
     * @return
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * 设置关键人ID
     * 
     * @param createUserId
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 获取创建时间
     * 
     * @return
     */
    public Date getCreateDate() {
        if (this.createDate != null) {
            return new Date(this.createDate.getTime());
        } else {
            return null;
        }
    }

    /**
     * 设置创建时间
     * 
     * @param createDate
     */
    public void setCreateDate(Date createDate) {
        if (createDate != null) {
            Date tEmp = (Date) createDate.clone();
            if (tEmp != null) {
                this.createDate = tEmp;
            }
        }
    }

    /**
     * 获取修改人ID
     * 
     * @return
     */
    public Long getUpdateUserId() {
        return updateUserId;
    }

    /**
     * 设置修改人ID
     * 
     * @param updateUserId
     */
    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    /**
     * 获取修改时间
     * 
     * @return
     */
    public Date getUpdateDate() {
        if (this.updateDate != null) {
            return new Date(this.updateDate.getTime());
        } else {
            return null;
        }
    }

    /**
     * 设置修改时间
     * 
     * @param updateDate
     */
    public void setUpdateDate(Date updateDate) {
        if (updateDate != null) {
            Date tEmp = (Date) updateDate.clone();
            if (tEmp != null) {
                this.updateDate = tEmp;
            }
        }
    }

    /**
     * 获取临时字段1
     * 
     * @return
     */
    public String getTemp1() {
        return temp1;
    }

    /**
     * 设置临时字段1
     * 
     * @param temp1
     */
    public void setTemp1(String temp1) {
        this.temp1 = temp1;
    }

    /**
     * 获取临时字段2
     * 
     * @return
     */
    public String getTemp2() {
        return temp2;
    }

    /**
     * 设置临时字段2
     * 
     * @param temp2
     */
    public void setTemp2(String temp2) {
        this.temp2 = temp2;
    }

    /**
     * 获取临时字段3
     * 
     * @return
     */
    public String getTemp3() {
        return temp3;
    }

    /**
     * 设置临时字段3
     * 
     * @param temp3
     */
    public void setTemp3(String temp3) {
        this.temp3 = temp3;
    }

    /**
     * 获取临时字段4
     * 
     * @return
     */
    public String getTemp4() {
        return temp4;
    }

    /**
     * 设置临时字段4
     * 
     * @param temp4
     */
    public void setTemp4(String temp4) {
        this.temp4 = temp4;
    }

    /**
     * 获取临时字段5
     * 
     * @return
     */
    public String getTemp5() {
        return temp5;
    }

    /**
     * 设置临时字段5
     * 
     * @param temp5
     */
    public void setTemp5(String temp5) {
        this.temp5 = temp5;
    }
}
