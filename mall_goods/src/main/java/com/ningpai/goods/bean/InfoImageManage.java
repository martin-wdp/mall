package com.ningpai.goods.bean;

import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * 实体类-资源图片信息
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月25日上午11:12:29
 */
public class InfoImageManage {
    /*
     * 图片信息编号
     */
    private Long imageManageId;
    /*
     * 图片名称
     */
    private String imageManageName;
    /*
     * 图片地址
     */
    @Pattern(regexp = "[^\\<\\>]+|()")
    private String imageManageUrl;
    /*
     * 大图地址
     */
    private String bigImgUrl;
    /*
     * 中图地址
     */
    private String middleImgUrl;
    /*
     * 小图地址
     */
    private String smallImgUrl;
    /*
     * 图片类型编号
     */
    private Long classifyId;
    /*
     * 上传时间
     */
    private Date imageOnlineDate;
    /*
     * 创建时间
     */
    private Date createDate;
    /*
     * 修改时间
     */
    private Date updateDate;
    /*
     * 删除标记
     */
    private String delflag;
    /*
     * 商家ID
     */
    private Long thirdId;

    public Long getImageManageId() {
        return imageManageId;
    }

    public void setImageManageId(Long imageManageId) {
        this.imageManageId = imageManageId;
    }

    public String getImageManageName() {
        return imageManageName;
    }

    public void setImageManageName(String imageManageName) {
        this.imageManageName = imageManageName;
    }

    public String getImageManageUrl() {
        return imageManageUrl;
    }

    public void setImageManageUrl(String imageManageUrl) {
        this.imageManageUrl = imageManageUrl;
    }

    public Long getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(Long classifyId) {
        this.classifyId = classifyId;
    }
    /**
     * 获取上传时间
     * */
    public Date getImageOnlineDate() {
        if (this.imageOnlineDate != null) {
            return new Date(this.imageOnlineDate.getTime());
        } else {
            return null;
        }
    }
    /**
     * 设置上传时间
     * */
    public void setImageOnlineDate(Date imageOnlineDate) {
        if (imageOnlineDate != null) {
            Date tEmp = (Date) imageOnlineDate.clone();
            if (tEmp != null) {
                this.imageOnlineDate = tEmp;
            }
        }
    }
    /**
     * 获取创建时间
     * */
    public Date getCreateDate() {
        if (this.createDate != null) {
            return new Date(this.createDate.getTime());
        } else {
            return null;
        }
    }
    /**
     * 设置创建时间
     * */
    public void setCreateDate(Date createDate) {
        if (createDate != null) {
            Date tEmp = (Date) createDate.clone();
            if (tEmp != null) {
                this.createDate = tEmp;
            }
        }
    }
    /**
     * 获取修改时间
     * */
    public Date getUpdateDate() {
        if (this.updateDate != null) {
            return new Date(this.updateDate.getTime());
        } else {
            return null;
        }
    }
    /**
     * 设置修改时间
     * */
    public void setUpdateDate(Date updateDate) {
        if (updateDate != null) {
            Date tEmp = (Date) updateDate.clone();
            if (tEmp != null) {
                this.updateDate = tEmp;
            }
        }
    }

    public String getDelflag() {
        return delflag;
    }

    public void setDelflag(String delflag) {
        this.delflag = delflag;
    }

    public Long getThirdId() {
        return thirdId;
    }

    public void setThirdId(Long thirdId) {
        this.thirdId = thirdId;
    }

    public String getBigImgUrl() {
        return bigImgUrl;
    }

    public void setBigImgUrl(String bigImgUrl) {
        this.bigImgUrl = bigImgUrl;
    }

    public String getMiddleImgUrl() {
        return middleImgUrl;
    }

    public void setMiddleImgUrl(String middleImgUrl) {
        this.middleImgUrl = middleImgUrl;
    }

    public String getSmallImgUrl() {
        return smallImgUrl;
    }

    public void setSmallImgUrl(String smallImgUrl) {
        this.smallImgUrl = smallImgUrl;
    }
}
