package com.ningpai.api.bean;

import java.util.Date;

/**
 * 开放接口-权限控制日志
 * @author lih
 * @version 2.0
 * @since 15/9/18
 */
public class OEmpowerLog {

    /**
     * 日志id
     */
    private Long logId;

    /**
     * 权限id
     */
    private Long epowerId;

    /**
     * 日志内容
     */
    private String logContent;

    /**
     * 删除标记
     */
    private String delFlag;

    /**
     * 创建时间
     */
    private Date createTime;


    public Long getLogId() {
        return logId;
    }

    /**
     * 日志id
     * @param logId
     * @return
     */
    public OEmpowerLog setLogId(Long logId) {
        this.logId = logId;
        return this;
    }

    public Long getEpowerId() {
        return epowerId;
    }

    /**
     * 权限id
     * @param epowerId
     * @return
     */
    public OEmpowerLog setEpowerId(Long epowerId) {
        this.epowerId = epowerId;
        return this;
    }

    public String getLogContent() {
        return logContent;
    }

    /**
     * 日志内容
     * @param logContent
     * @return
     */
    public OEmpowerLog setLogContent(String logContent) {
        this.logContent = logContent;
        return this;
    }

    public String getDelFlag() {
        return delFlag;
    }

    /**
     * 删除标记
     * @param delFlag
     * @return
     */
    public OEmpowerLog setDelFlag(String delFlag) {
        this.delFlag = delFlag;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime
     * @return
     */
    public OEmpowerLog setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }
}
