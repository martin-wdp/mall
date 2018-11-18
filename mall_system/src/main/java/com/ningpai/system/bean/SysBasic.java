package com.ningpai.system.bean;

import java.util.Date;

/**
 * 实体类-后台logo设置
 *
 * @author NINGPAI-WangHaiYang
 * @since 2014年9月24日下午8:30:36
 */
public class SysBasic {
    /** 编号 */
    private Long basicId;
    /** 名称 */
    private String bsetName;

    /** 后台登录logo地址 */
    private String loginLogo;
    /** 后台首页logo地址 */
    private String indexLogo;
    /** 创建时间 */
    private Date createDate;
    /** 修改时间 */
    private Date updateDate;
    /** 扩展参数1(后天登录页版权) */
    private String temp1;
    /** 扩展参数2 */
    private String temp2;
    /** 扩展参数3 */
    private String temp3;
    /** 扩展参数4 */
    private String temp4;
    /** 扩展参数5 */
    private String temp5;

    public Long getBasicId() {
        return basicId;
    }

    public void setBasicId(Long basicId) {
        this.basicId = basicId;
    }

    public String getLoginLogo() {
        return loginLogo;
    }

    public void setLoginLogo(String loginLogo) {
        this.loginLogo = loginLogo;
    }

    public String getBsetName() {
        return bsetName;
    }

    public void setBsetName(String bsetName) {
        this.bsetName = bsetName;
    }

    public String getIndexLogo() {
        return indexLogo;
    }

    public void setIndexLogo(String indexLogo) {
        this.indexLogo = indexLogo;
    }
    /**
     * 时间
     * @return
     */
    public Date getCreateDate() {
        if(this.createDate != null) {
            return createDate==null?null:(Date) createDate.clone();
        }else{
            return null;
        }
    }
    /**
     * 时间
     * @return
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate == null ? null : (Date) createDate.clone();

    }
    /**
     * 时间
     * @return
     */
    public Date getUpdateDate() {

        return this.updateDate == null?null:(Date) updateDate.clone();
    }
    /**
     * 时间
     * @return
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate == null ? null : (Date) updateDate.clone();
    }

    public String getTemp1() {
        return temp1;
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
}
