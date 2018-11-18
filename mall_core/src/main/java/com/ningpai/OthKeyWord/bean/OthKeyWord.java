package com.ningpai.OthKeyWord.bean;

import java.util.Date;

/**
 * Created by pl on 2016/1/23.
 * Desc:
 */
public class OthKeyWord {
    private long id;
    private String keyWord;
    private String keyWordConver;
    private String keyWordNotConver;
    private Date createTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getKeyWordConver() {
        return keyWordConver;
    }

    public void setKeyWordConver(String keyWordConver) {
        this.keyWordConver = keyWordConver;
    }

    public String getKeyWordNotConver() {
        return keyWordNotConver;
    }

    public void setKeyWordNotConver(String keyWordNotConver) {
        this.keyWordNotConver = keyWordNotConver;
    }
    public Date getCreateTime() {
        if (this.createTime != null) {
            return new Date(this.createTime.getTime());
        }
        return null;
    }

    /**
     * 设置创建<code>Authority</code>创建时间
     *
     * @param createTime
     *            {@link com.ningpai.manager.bean.Authority#createTime}
     */
    public void setCreateTime(Date createTime) {
        if (createTime != null) {
            Date tEmp = (Date) createTime.clone();
            if (tEmp != null) {
                this.createTime = tEmp;
            }
        }
    }
}
