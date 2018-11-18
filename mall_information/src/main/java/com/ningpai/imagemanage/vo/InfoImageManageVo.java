package com.ningpai.imagemanage.vo;

import java.util.Date;

import javax.validation.constraints.Pattern;

import com.ningpai.imagemanage.bean.InfoImageClassify;

/**
 * 实体类-资源图片信息
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月25日上午11:12:29
 */
public class InfoImageManageVo {
    /** 图片信息编号 */
    private Long imageManageId;
    /** 图片名称 */
    private String imageManageName;
    /** 图片地址 */
    @Pattern(regexp = "[^\\<\\>]+|()")
    private String imageManageUrl;
    /** 图片类型编号 */
    private Long classifyId;
    /** 上传时间 */
    private Date imageOnlineDate;
    /** 创建时间 */
    private Date createDate;
    /** 修改时间 */
    private Date updateDate;
    /** 删除标记 */
    private String delflag;
    /** 第三方商家ID */
    private Long thirdId;

    private InfoImageClassify imgClassify;

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
     * 取得：上传时间
     *
     * @return Date 上传时间 {@link com.ningpai.imagemanage.vo.InfoImageManageVo#imageOnlineDate}
     */
    public Date getImageOnlineDate() {
        if (this.imageOnlineDate != null) {
            return new Date(this.imageOnlineDate.getTime());
        } else {
            return null;
        }
    }

    /**
     * 设置：上传时间
     *
     * @param imageOnlineDate
     *            上传时间
     */
    public void setImageOnlineDate(Date imageOnlineDate) {
        if (imageOnlineDate != null) {
            Date tEmp = (Date) imageOnlineDate.clone();
            if (tEmp != null) {
                this.imageOnlineDate = tEmp;
            }
        }
    }
    /**
     * 取得：创建时间
     *
     * @return Date 创建时间 {@link com.ningpai.imagemanage.vo.InfoImageManageVo#createDate}
     */
    public Date getCreateDate() {
        if (this.createDate != null) {
            return new Date(this.createDate.getTime());
        } else {
            return null;
        }
    }
    /**
     * 设置：创建时间
     *
     * @param createDate
     *            创建时间
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
     * 取得：修改时间
     *
     * @return Date 修改时间 {@link com.ningpai.imagemanage.vo.InfoImageManageVo#updateDate}
     */
    public Date getUpdateDate() {
        if (this.updateDate != null) {
            return new Date(this.updateDate.getTime());
        } else {
            return null;
        }
    }
    /**
     * 设置：修改时间
     *
     * @param updateDate
     *            修改时间
     */
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

    public InfoImageClassify getImgClassify() {
        return imgClassify;
    }

    public void setImgClassify(InfoImageClassify imgClassify) {
        this.imgClassify = imgClassify;
    }

    public Long getThirdId() {
        return thirdId;
    }

    public void setThirdId(Long thirdId) {
        this.thirdId = thirdId;
    }

}
