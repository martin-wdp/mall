package com.ningpai.thirdproject.bean;



import java.util.Date;
/**
 * 第三方专题bean
 * @author zhangsl
 * @since 2015年1月15日 上午10:33:04
 * @version
 */
public class ThirdProject {
    /**
     * 第三方专题ID
     */
    private Long thirdProjectId;
    /**
     * 第三方ID
     */
    private Long thirdId;
    /**
     * 第三方专题名称
     */
    private String thirdProjectName;
    /**
     * 第三方专题URL
     */
    private String thirdProjectUrl;
    /**
     * 第三方专题创建时间
     */
    private Date thirdProjectCreateTime;
    /**
     * 第三方专题修改时间
     */
    private Date thirdProjectModifyTime;
    /**
     * 第三方专题删除标示
     */
    private String thirdProjectDelflag;
    /**
     * 第三方专题内容
     */
    private String thirdProjectContext;

    public Long getThirdProjectId() {
        return thirdProjectId;
    }

    public void setThirdProjectId(Long thirdProjectId) {
        this.thirdProjectId = thirdProjectId;
    }

    public Long getThirdId() {
        return thirdId;
    }

    public void setThirdId(Long thirdId) {
        this.thirdId = thirdId;
    }

    public String getThirdProjectName() {
        return thirdProjectName;
    }

    public void setThirdProjectName(String thirdProjectName) {
        this.thirdProjectName = thirdProjectName;
    }

    public String getThirdProjectUrl() {
        return thirdProjectUrl;
    }

    public void setThirdProjectUrl(String thirdProjectUrl) {
        this.thirdProjectUrl = thirdProjectUrl;
    }
    /**
     * 获取创建时间
     * */
    public Date getThirdProjectCreateTime() {
        return (Date) thirdProjectCreateTime.clone();
    }
    /**
     * 设置创建时间
     * */
    public void setThirdProjectCreateTime(Date thirdProjectCreateTime) {
        this.thirdProjectCreateTime = thirdProjectCreateTime==null?null: (Date) thirdProjectCreateTime.clone();
    }
    /**
     * 获取修改时间
     * */
    public Date getThirdProjectModifyTime() {
        return (Date) thirdProjectModifyTime.clone();
    }
    /**
     * 设置修改时间
     * */
    public void setThirdProjectModifyTime(Date thirdProjectModifyTime) {
        this.thirdProjectModifyTime = thirdProjectModifyTime==null?null: (Date) thirdProjectModifyTime.clone();
    }

    public String getThirdProjectDelflag() {
        return thirdProjectDelflag;
    }

    public void setThirdProjectDelflag(String thirdProjectDelflag) {
        this.thirdProjectDelflag = thirdProjectDelflag;
    }

    public String getThirdProjectContext() {
        return thirdProjectContext;
    }

    public void setThirdProjectContext(String thirdProjectContext) {
        this.thirdProjectContext = thirdProjectContext;
    }
}
