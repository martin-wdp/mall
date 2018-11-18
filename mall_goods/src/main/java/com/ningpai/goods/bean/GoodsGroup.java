package com.ningpai.goods.bean;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

/**
 * 商品组合实体
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月31日 上午11:10:12
 * @version 1.0
 */
public class GoodsGroup {
    /*
     * 主键ID
     */
    private Long groupId;
    /*
     * 商品组合名称
     */
    @Length(min = 2, max = 16, message = "组合名称 长度必须在 2字符 ~ 16字符之间.")
    @Pattern(regexp = "[^''\\[\\]\\<\\>?!]+", message = "组合名称输入格式不正确.")
    private String groupName;
    /*
     * 组合优惠类型 0：特惠套装 1：人气组合
     */
    @NotNull
    private String groupPrefertype;
    /*
     * 优惠额度
     */
    private BigDecimal groupPreferamount;
    /*
     * 删除标记 默认0：不删除 1：已经删除
     */
    private String groupDelflag;
    /*
     * 创建人名称
     */
    private String groupCreateName;
    /*
     * 创建时间
     */
    private Date groupCreateTime;
    /*
     * 修改人名称
     */
    private String groupModifiedName;
    /*
     * 修改时间
     */
    private Date groupModifiedTime;
    /*
     * 删除人名称
     */
    private String groupDelName;
    /*
     * 删除时间
     */
    private Date groupDelTime;
    /*
     * 第三方商品组合标记
     */
    private String isThird;
    /*
     * 第三方商家ID
     */
    private Long thirdId;
    /*
     * 第三方商家ID
     */
    private String thirdName;

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName.trim();
    }

    public String getGroupPrefertype() {
        return groupPrefertype;
    }

    public void setGroupPrefertype(String groupPrefertype) {
        this.groupPrefertype = groupPrefertype;
    }

    public BigDecimal getGroupPreferamount() {
        return groupPreferamount;
    }

    public void setGroupPreferamount(BigDecimal groupPreferamount) {
        this.groupPreferamount = groupPreferamount;
    }

    public String getGroupDelflag() {
        return groupDelflag;
    }

    public void setGroupDelflag(String groupDelflag) {
        this.groupDelflag = groupDelflag;
    }

    public String getGroupCreateName() {
        return groupCreateName;
    }

    public void setGroupCreateName(String groupCreateName) {
        this.groupCreateName = groupCreateName;
    }
    /**
     * 获取创建时间
     * */
    public Date getGroupCreateTime() {
        if (this.groupCreateTime != null) {
            return new Date(this.groupCreateTime.getTime());
        }
        return null;
    }
    /**
     * 设置创建时间
     * */
    public void setGroupCreateTime(Date groupCreateTime) {
        if (groupCreateTime != null) {
            Date tEmp = (Date) groupCreateTime.clone();
            if (tEmp != null) {
                this.groupCreateTime = tEmp;
            }
        }
    }

    public String getGroupModifiedName() {
        return groupModifiedName;
    }

    public void setGroupModifiedName(String groupModifiedName) {
        this.groupModifiedName = groupModifiedName;
    }
    /**
     * 获取修改时间
     * */
    public Date getGroupModifiedTime() {
        if (this.groupModifiedTime != null) {
            return new Date(this.groupModifiedTime.getTime());
        }
        return null;
    }
    /**
     * 设置修改时间
     * */
    public void setGroupModifiedTime(Date groupModifiedTime) {
        if (groupModifiedTime != null) {
            Date tEmp = (Date) groupModifiedTime.clone();
            if (tEmp != null) {
                this.groupModifiedTime = tEmp;
            }
        }
    }

    public String getGroupDelName() {
        return groupDelName;
    }

    public void setGroupDelName(String groupDelName) {
        this.groupDelName = groupDelName;
    }
    /**
     * 获取删除时间
     * */
    public Date getGroupDelTime() {
        if (this.groupDelTime != null) {
            return new Date(this.groupDelTime.getTime());
        }
        return null;
    }
    /**
     * 设置删除时间
     * */
    public void setGroupDelTime(Date groupDelTime) {
        if (groupDelTime != null) {
            Date tEmp = (Date) groupDelTime.clone();
            if (tEmp != null) {
                this.groupDelTime = tEmp;
            }
        }
    }

    public String getIsThird() {
        return isThird;
    }

    public void setIsThird(String isThird) {
        this.isThird = isThird;
    }

    public Long getThirdId() {
        return thirdId;
    }

    public void setThirdId(Long thirdId) {
        this.thirdId = thirdId;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

}
