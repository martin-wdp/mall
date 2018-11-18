package com.ningpai.logcore.bean;

import java.util.Date;

/**
 * 日志实体
 * 
 * @author NINGPAI-SunKefeng
 * @since 2014年06月24日 下午4:59
 * @version 0.0.1
 */
public class OperationLog {
    /**
     * 日志编号op_id
     * 
     * @see #getOpId()
     * @see #setOpId(Long)
     */
    private Long opId;

    /**
     * 操作人
     * 
     * @see #getOpName()
     * @see #setOpName(String)
     */
    private String opName;

    // 操作时间
    private Date opTime;

    // 操作IP
    private String opIp;

    // 操作关键字
    private String opCode;

    // 操作内容
    private String opContent;

    // 第三方标示0否1是
    private String opIsSeller;

    // 第三方编号
    private Long thirdId;

    public Long getOpId() {
        return opId;
    }

    public void setOpId(Long opId) {
        this.opId = opId;
    }

    public String getOpName() {
        return opName;
    }

    public void setOpName(String opName) {
        this.opName = opName;
    }

    /**
     * 获取操作时间
     * */
    public Date getOpTime() {
        return (Date) opTime.clone();
    }

    /**
     *
     * 设置操作时间
     * @param opTime
     */
    public void setOpTime(Date opTime) {
        this.opTime = opTime == null ? null : (Date) opTime.clone();
    }

    public String getOpIp() {
        return opIp;
    }

    public void setOpIp(String opIp) {
        this.opIp = opIp;
    }

    public String getOpCode() {
        return opCode;
    }

    public void setOpCode(String opCode) {
        this.opCode = opCode;
    }

    public String getOpContent() {
        return opContent;
    }

    public void setOpContent(String opContent) {
        this.opContent = opContent;
    }

    public String getOpIsSeller() {
        return opIsSeller;
    }

    public void setOpIsSeller(String opIsSeller) {
        this.opIsSeller = opIsSeller;
    }

    public Long getThirdId() {
        return thirdId;
    }

    public void setThirdId(Long thirdId) {
        this.thirdId = thirdId;
    }
}
