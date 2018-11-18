package com.ningpai.category.bean;

import java.util.List;

/**
 * 临时存放分类对象
 * 
 * @author qiyuanyuan
 *
 */
public class TempCategory {

    /*
     * 类编号
     */
    private String class_code;
    /*
     * 类名称
     */
    private String class_name;
    /*
     * 父类编号
     */
    private String parent_class_code;
    /*
     * 是否打包
     */
    private String is_pack;
    /*
     * 分类等级
     */
    private String cat_grade;
    /*
     * 排序
     */
    private Integer sort;
    /*
     * 类编号条目
     */
    private List<ClassCodeItem> class_code_item;

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getCat_grade() {
        return cat_grade;
    }

    public void setCat_grade(String catGrade) {
        this.cat_grade = catGrade;
    }

    public String getClass_code() {
        return class_code;
    }

    public void setClass_code(String classCode) {
        this.class_code = classCode;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String className) {
        this.class_name = className;
    }

    public String getParent_class_code() {
        return parent_class_code;
    }

    public void setParent_class_code(String parentClassCode) {
        this.parent_class_code = parentClassCode;
    }

    public String getIs_pack() {
        return is_pack;
    }

    public void setIs_pack(String isPack) {
        this.is_pack = isPack;
    }

    public List<ClassCodeItem> getClass_code_item() {
        return class_code_item;
    }

    public void setClass_code_item(List<ClassCodeItem> classCodeItem) {
        this.class_code_item = classCodeItem;
    }

}
