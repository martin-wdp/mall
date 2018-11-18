/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.third.login.bean;
import java.util.Date;

/**
 * IpRecord Bean
 * @author NINGPAI-zhangqiang
 * @since 2014年5月5日 上午10:39:42
 * @version 2.0
 */
public class IpRecord {
    /**
     * Ip编号
     */
    private Long ipid;
    /**
     * IP号码
     */
    private String ip;
    /**
     * 失败次数
     */
    private Long errCount;
    /**
     * 验证码开始时间
     */
    private Date captTime;
    /**
     * 结束时间
     */
    private Date endCaptTime;
    /**
     * 删除标记
     */
    private String delFlag;

    public Long getIpid() {
        return ipid;
    }

    public void setIpid(Long ipid) {
        this.ipid = ipid;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Long getErrCount() {
        return errCount;
    }

    public void setErrCount(Long errCount) {
        this.errCount = errCount;
    }

    /**
     * 获取上传日期
     * @return
     */
    public Date getCaptTime() {
        if (this.captTime != null) {
            return new Date(this.captTime.getTime());
        }
        return null;
    }

    /**
     * 设置上传日期
     * @return
     */
    public void setCaptTime(Date captTime) {
        if (captTime != null) {
            Date tEmp = (Date) captTime.clone();
            if (tEmp != null) {
                this.captTime = tEmp;
            }
        }
    }

    /**
     * 获取删除上传日期
     * @return
     */
    public Date getEndCaptTime() {
        if (this.endCaptTime != null) {
            return new Date(this.endCaptTime.getTime());
        }
        return null;
    }

    /**
     * 设置删除上传日期
     * @return
     */
    public void setEndCaptTime(Date endCaptTime) {
        if (endCaptTime != null) {
            Date tEmp = (Date) endCaptTime.clone();
            if (tEmp != null) {
                this.endCaptTime = tEmp;
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
