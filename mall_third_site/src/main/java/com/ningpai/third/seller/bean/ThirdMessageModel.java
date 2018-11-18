package com.ningpai.third.seller.bean;

import java.util.Date;

/**
 * 第三方消息接收Bean
 * 
 * @author zhangqiang
 * @since 2014年5月20日 上午11:34:26
 * @version 2.0
 */
public class ThirdMessageModel {
    /**
     * 模块编号
     */
    private Long messModId;
    /**
     * 父页面编号
     */
    private Long parentPageId;
    /**
     * 模块名称
     */
    private String messModName;
    /**
     * 模块备注
     */
    private String messModCom;
    /**
     * 消息接收类型
     */
    private String messRecType;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 删除标记
     */
    private String delFlag;

    public Long getMessModId() {
        return messModId;
    }

    public void setMessModId(Long messModId) {
        this.messModId = messModId;
    }

    public Long getParentPageId() {
        return parentPageId;
    }

    public void setParentPageId(Long parentPageId) {
        this.parentPageId = parentPageId;
    }

    public String getMessModName() {
        return messModName;
    }

    public void setMessModName(String messModName) {
        this.messModName = messModName;
    }

    public String getMessModCom() {
        return messModCom;
    }

    public void setMessModCom(String messModCom) {
        this.messModCom = messModCom;
    }

    public String getMessRecType() {
        return messRecType;
    }

    public void setMessRecType(String messRecType) {
        this.messRecType = messRecType;
    }

    /**
     * 获取创建日期
     * @return
     */
    public Date getCreateTime() {
        if (this.createTime != null) {
            return new Date(this.createTime.getTime()); // 正确值
        } else {
            return null;
        }
    }

    /**
     * 设置创建日期
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        if (createTime != null) {
            Date tEmp = (Date) createTime.clone();
            if (tEmp != null) {
                this.createTime = tEmp;
            }
        }
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
