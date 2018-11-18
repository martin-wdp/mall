package com.ningpai.index.bean;

import com.ningpai.system.bean.HelpCenter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 实体类-帮助分类
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月1日下午4:03:36
 */
public class HelpCateBean implements Serializable {
    /**
     * 帮助中心分类ID
     */
    private Long helpcateId;
    /**
     * 帮助中心分类名称
     */
    private String helpcateName;
    /**
     * 分类图片
     */
    private String helpcateImg;
    /**
     * 帮助集合
     */
    private List<HelpCenter> helpCenters = new ArrayList<HelpCenter>();

    public Long getHelpcateId() {
        return helpcateId;
    }

    public void setHelpcateId(Long helpcateId) {
        this.helpcateId = helpcateId;
    }

    public String getHelpcateName() {
        return helpcateName;
    }

    public void setHelpcateName(String helpcateName) {
        this.helpcateName = helpcateName;
    }

    public String getHelpcateImg() {
        return helpcateImg;
    }

    public void setHelpcateImg(String helpcateImg) {
        this.helpcateImg = helpcateImg;
    }

    public List<HelpCenter> getHelpCenters() {
        return helpCenters;
    }

    public void setHelpCenters(List<HelpCenter> helpCenters) {
        this.helpCenters = helpCenters;
    }

}
