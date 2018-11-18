package com.ningpai.system.bean;

import java.util.Date;

/**
 * 开放接口-权限控制日志
 * @author lih
 * @version 2.0
 * @since 15/9/18
 */
public class EmpowerLog {

    /**
     * 日志id
     */
    private Long logId;

    /**
     * 权限id
     */
    private Long empowerId;

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

    /**
     * 日志id
     */
    public Long getLogId() {
        return logId;
    }
    /**
     * 日志id
     */
    public EmpowerLog setLogId(Long logId) {
        this.logId = logId;
        return this;
    }
    /**
     * 权限id
     */
    public Long getEmpowerId() {
        return empowerId;
    }
    /**
     * 权限id
     */
    public EmpowerLog setEmpowerId(Long empowerId) {
        this.empowerId = empowerId;
        return this;
    }
    /**
     * 日志内容
     */
    public String getLogContent() {
        return logContent;
    }
    /**
     * 日志内容
     */
    public EmpowerLog setLogContent(String logContent) {
        this.logContent = logContent;
        return this;
    }
    /**
     * 删除标记
     */
    public String getDelFlag() {
        return delFlag;
    }
    /**
     * 删除标记
     */
    public EmpowerLog setDelFlag(String delFlag) {
        this.delFlag = delFlag;
        return this;
    }
    /**
     * 时间
     * @return
     */
    public Date getCreateTime() {
        return createTime;
    }
    /**
     * 时间
     * @return
     */
    public EmpowerLog setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }
}
