package com.ningpai.system.bean;

import java.util.Date;

/**
 * 异常页面实体
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年2月25日 15:11:25
 * @version 1.0
 */
public class ErrorPage {
    /**
     * 编号
     */
    private Long id;
    /**
     * 页面名称
     */
    private String name;
    /**
     * 页面标题
     */
    private String title;
    /**
     * 页面描述
     */
    private String pagedesc;
    /**
     * 页面路径
     */
    private String pageurl;
    /**
     * 是否删除
     */
    private String delflag;
    /**
     * 创建人
     */
    private Long createUserId;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 更新人
     */
    private Long updateUserId;
    /**
     * 更新时间
     */
    private Date updateDate;
    /**
     * 页面提示内容
     */
    private String prompt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPagedesc() {
        return pagedesc;
    }

    public void setPagedesc(String pagedesc) {
        this.pagedesc = pagedesc;
    }

    public String getPageurl() {
        return pageurl;
    }

    public void setPageurl(String pageurl) {
        this.pageurl = pageurl;
    }

    public String getDelflag() {
        return delflag;
    }

    public void setDelflag(String delflag) {
        this.delflag = delflag;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }
    /**
     * 时间
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
     * 时间
     * @return
     */
    public void setCreateDate(Date createDate) {
        if (createDate != null) {
            Date tEmp = (Date) createDate.clone();
            if (tEmp != null) {
                this.createDate = tEmp;
            }
        }
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }
    /**
     * 时间
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
     * 时间
     * @return
     */
    public void setUpdateDate(Date updateDate) {
        if (updateDate != null) {
            Date tEmp = (Date) updateDate.clone();
            if (tEmp != null) {
                this.updateDate = tEmp;
            }
        }
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }
}
