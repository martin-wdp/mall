package com.ningpai.index.bean;

/**
 * 索引分类
 * @author Songhl
 */
public class IndexCate {
    /**
     * 等级id
     */
    private Long leverid;
    /**
     * 编号
     */
    private Long id;
    /**
     * 名字
     */
    private String name;

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

    public Long getLeverid() {
        return leverid;
    }

    public void setLeverid(Long leverid) {
        this.leverid = leverid;
    }
}

