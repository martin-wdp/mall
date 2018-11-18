package com.ningpai.temp.bean;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 页面说明
 * 
 * @ClassName: Megawizard
 * @Description: 模板配置中的页面说明
 * @author Wanghy
 * @date 2014年10月25日 上午10:13:50
 * 
 */
public class Megawizard {
    /**
     * 编号
     */
    private Long id;
    /**
     * 模板id
     */
    private Long tempId;
    /**
     * 页面说明分类
     */
    private Integer sort;
    /**
     * 创建人id
     */
    private Long createUserId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改人id
     */
    private Long updateUserId;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 删除标记
     */
    private String delflag;
    /**
     * 备用
     */
    private String reserve1;
    /**
     * 备用
     */
    private String reserve2;
    /**
     * 备用
     */
    private String reserve3;
    /**
     * 备用
     */
    private String reserve4;
    /**
     * 备用
     */
    private String reserve5;
    /**
     * 页面说明内容
     */
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+|()")
    private String content;
    /**
     * 页面说明名称
     */
    @NotNull
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String megawizardName;

    /**
     * 页面说明名称
     * 
     * @return
     */
    public String getMegawizardName() {
        return megawizardName;
    }

    /**
     * 页面说明名称
     * 
     * @param megawizardName
     */
    public void setMegawizardName(String megawizardName) {
        this.megawizardName = megawizardName;
    }

    /**
     * 编号
     * 
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     * 编号
     * 
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 模板id
     * 
     * @return
     */
    public Long getTempId() {
        return tempId;
    }

    /**
     * 模板id
     * 
     * @param tempId
     */
    public void setTempId(Long tempId) {
        this.tempId = tempId;
    }

    /**
     * 页面说明分类
     * 
     * @return
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 页面说明分类
     * 
     * @param sort
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 创建人id
     * 
     * @return
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * 创建人id
     * 
     * @param createUserId
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 创建时间
     * 
     * @return
     */
    public Date getCreateTime() {
        if (this.createTime != null) {
            return new Date(this.createTime.getTime());
        } else {
            return null;
        }
    }

    /**
     * 创建时间
     * 
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

    /**
     * 修改人id
     * 
     * @return
     */
    public Long getUpdateUserId() {
        return updateUserId;
    }

    /**
     * 修改人id
     * 
     * @param updateUserId
     */
    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    /**
     * 修改时间
     * 
     * @return
     */
    public Date getUpdateTime() {
        if (this.updateTime != null) {
            return new Date(this.updateTime.getTime());
        } else {
            return null;
        }
    }

    /**
     * 修改时间
     * 
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        if (updateTime != null) {
            Date tEmp = (Date) updateTime.clone();
            if (tEmp != null) {
                this.updateTime = tEmp;
            }
        }
    }

    /**
     * 删除标记
     * 
     * @return
     */
    public String getDelflag() {
        return delflag;
    }

    /**
     * 删除标记
     * 
     * @param delflag
     */
    public void setDelflag(String delflag) {
        this.delflag = delflag;
    }

    public String getReserve1() {
        return reserve1;
    }

    public void setReserve1(String reserve1) {
        this.reserve1 = reserve1;
    }

    public String getReserve2() {
        return reserve2;
    }

    public void setReserve2(String reserve2) {
        this.reserve2 = reserve2;
    }

    public String getReserve3() {
        return reserve3;
    }

    public void setReserve3(String reserve3) {
        this.reserve3 = reserve3;
    }

    public String getReserve4() {
        return reserve4;
    }

    public void setReserve4(String reserve4) {
        this.reserve4 = reserve4;
    }

    public String getReserve5() {
        return reserve5;
    }

    public void setReserve5(String reserve5) {
        this.reserve5 = reserve5;
    }

    /**
     * 页面说明内容
     * 
     * @return
     */
    public String getContent() {
        return content;
    }

    /**
     * 页面说明内容
     * 
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }
}
