package com.ningpai.temp.vo;

import java.util.Date;
import java.util.List;

/**
 * VO实体类-分类导航
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年5月6日下午3:42:03
 */
public class ClassifyBarVo {
    /** 编号 */
    private Long classifyBarId;
    /** 商品分类ID */
    private Long goodsCatId;
    /** 模板ID */
    private Long tempId;
    /** 频道ID */
    private Long channelId;
    /** 分类名称 */
    private String name;
    /** 分类链接 */
    private String url;
    /** 层级 */
    private Integer grade;
    /** 排序 */
    private Integer sort;
    /** 父分类 */
    private Long parentId;
    /** 是否启用 */
    private String isUsing;
    /** 是否显示广告 */
    private String showAdvert;
    /** 是否显示品牌 */
    private String showBrand;
    /** 删除标记 */
    private String delflag;

    /** 子分类 */
    private List<ClassifyBarVo> childs;

    /** 创建人ID */
    private Long createUserId;
    /** 创建时间 */
    private Date createDate;
    /** 修改人ID */
    private Long updateUserId;
    /** 修改时间 */
    private Date updateDate;
    /** 备用字段1 */
    private String temp1;
    /** 备用字段2 */
    private String temp2;
    /** 备用字段3 */
    private String temp3;
    /** 备用字段4 */
    private String temp4;
    /** 备用字段5 */
    private String temp5;

    public Long getClassifyBarId() {
        return classifyBarId;
    }

    public void setClassifyBarId(Long classifyBarId) {
        this.classifyBarId = classifyBarId;
    }

    public Long getGoodsCatId() {
        return goodsCatId;
    }

    public void setGoodsCatId(Long goodsCatId) {
        this.goodsCatId = goodsCatId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getIsUsing() {
        return isUsing;
    }

    public void setIsUsing(String isUsing) {
        this.isUsing = isUsing;
    }

    public String getShowAdvert() {
        return showAdvert;
    }

    public void setShowAdvert(String showAdvert) {
        this.showAdvert = showAdvert;
    }

    public String getShowBrand() {
        return showBrand;
    }

    public void setShowBrand(String showBrand) {
        this.showBrand = showBrand;
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
     * @return Date
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
     * @return Date
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
     */
    public void setUpdateDate(Date updateDate) {
        if (updateDate != null) {
            Date tEmp = (Date) updateDate.clone();
            if (tEmp != null) {
                this.updateDate = tEmp;
            }
        }
    }

    public void setTemp1(String temp1) {
        this.temp1 = temp1;
    }

    public String getTemp2() {
        return temp2;
    }

    public void setTemp2(String temp2) {
        this.temp2 = temp2;
    }

    public String getTemp3() {
        return temp3;
    }

    public void setTemp3(String temp3) {
        this.temp3 = temp3;
    }

    public String getTemp4() {
        return temp4;
    }

    public void setTemp4(String temp4) {
        this.temp4 = temp4;
    }

    public String getTemp5() {
        return temp5;
    }

    public void setTemp5(String temp5) {
        this.temp5 = temp5;
    }

    public Long getTempId() {
        return tempId;
    }

    public void setTempId(Long tempId) {
        this.tempId = tempId;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public List<ClassifyBarVo> getChilds() {
        return childs;
    }

    public void setChilds(List<ClassifyBarVo> childs) {
        this.childs = childs;
    }

    public String getTemp1() {
        return temp1;
    }

}
