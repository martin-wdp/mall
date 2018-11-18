package com.ningpai.comment.bean;

import java.util.Date;

/**
 * 消息设置类
 * 
 * @author NINGPAI-zhangqiang
 * @since 2013年12月31日 下午6:34:24
 * @version 0.0.1
 */
public class Infosetting {
    /**
     * 编号
     * 
     * @see #getSetId()
     * @see #setSetId(Long)
     */
    private Long setId;
    /**
     * 缺省文字
     * 
     * @see #getDefaultWord()
     * @see #setDefaultWord(String)
     */
    private String defaultWord;
    /**
     * 审核文字
     * 
     * @see #getAuditWord()
     * @see #setAuditWord(String)
     */
    private String auditWord;
    /**
     * 成功文字
     * 
     * @see #getSuccessWord()
     * @see #setSuccessWord(String)
     */
    private String successWord;
    /**
     * 验证码
     * 
     * @see #getIsCheck()
     * @see #setIsCheck(String)
     */
    private String isCheck;
    /**
     * 审核设置
     * 
     * @see #getAduitSet()
     * @see #setAduitSet(String)
     */
    private String aduitSet;
    /**
     * 是否开启咨询
     * 
     * @see #getIsConsult()
     * @see #setIsConsult(String)
     */
    private String isConsult;
    /**
     * 咨询权限
     * 
     * @see #getConsultSet()
     * @see #setConsultSet(String)
     */
    private String consultSet;
    /**
     * 是否开启评论
     * 
     * @see #getIsComment()
     * @see #setIsComment(String)
     */
    private String isComment;
    /**
     * 评论设置
     * 
     * @see #getCcommentSet()
     * @see #setCcommentSet(String)
     */
    private String ccommentSet;
    /**
     * 是否开启评分
     * 
     * @see #getIsScore()
     * @see #setIsScore(String)
     */
    private String isScore;
    /**
     * 创建时间
     * 
     * @see #getCreateTime()
     * @see #setCreateTime(Date)
     */
    private Date createTime;
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

    public Long getSetId() {
        return setId;
    }

    public void setSetId(Long setId) {
        this.setId = setId;
    }

    public String getDefaultWord() {
        return defaultWord;
    }

    public void setDefaultWord(String defaultWord) {
        this.defaultWord = defaultWord;
    }

    public String getAuditWord() {
        return auditWord;
    }

    public void setAuditWord(String auditWord) {
        this.auditWord = auditWord;
    }

    public String getSuccessWord() {
        return successWord;
    }

    public void setSuccessWord(String successWord) {
        this.successWord = successWord;
    }

    public String getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(String isCheck) {
        this.isCheck = isCheck;
    }

    public String getAduitSet() {
        return aduitSet;
    }

    public void setAduitSet(String aduitSet) {
        this.aduitSet = aduitSet;
    }

    public String getIsConsult() {
        return isConsult;
    }

    public void setIsConsult(String isConsult) {
        this.isConsult = isConsult;
    }

    public String getConsultSet() {
        return consultSet;
    }

    public void setConsultSet(String consultSet) {
        this.consultSet = consultSet;
    }

    public String getIsComment() {
        return isComment;
    }

    public void setIsComment(String isComment) {
        this.isComment = isComment;
    }

    public String getCcommentSet() {
        return ccommentSet;
    }

    public void setCcommentSet(String ccommentSet) {
        this.ccommentSet = ccommentSet;
    }

    public String getIsScore() {
        return isScore;
    }

    public void setIsScore(String isScore) {
        this.isScore = isScore;
    }
    /**
     * 获取创建时间
     * */
    public Date getCreateTime() {
        if (this.createTime != null) {
            return new Date(this.createTime.getTime());
        } else {
            return null;
        }
    }
    /**
     * 设置创建时间
     * */
    public void setCreateTime(Date createTime) {
        if (this.createTime != null) {
            Date tEmp = (Date) createTime.clone();
            if (tEmp != null) {
                this.createTime = tEmp;
            }
        }
    }
    /**
     * 获取修改时间
     * */
    public Date getModifiedTime() {
        if (this.modifiedTime != null) {
            return new Date(this.modifiedTime.getTime());
        } else {
            return null;
        }
    }
    /**
     * 设置修改时间
     * */
    public void setModifiedTime(Date modifiedTime) {
        if (this.modifiedTime != null) {
            Date tEmp = (Date) modifiedTime.clone();
            if (tEmp != null) {
                this.modifiedTime = tEmp;
            }
        }
    }
    /**
     * 获取删除时间
     * */
    public Date getDelTime() {
        if (this.delTime != null) {
            return new Date(this.delTime.getTime());
        } else {
            return null;
        }
    }
    /**
     * 设置删除时间
     * */
    public void setDelTime(Date delTime) {
        if (this.delTime != null) {
            Date tEmp = (Date) delTime.clone();
            if (tEmp != null) {
                this.delTime = tEmp;
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
