package com.ningpai.goods.bean;

/**
 * 审核开关实体类
 *
 * */
public class GetOnOff {
    /*
     * 开关id
     */
    private Long id;
    /*
     * 1开启、0关闭
     */
    private String flag;
    /*
     * 开关类型 (1为第三方商家审核控制开关)
     */
    private Long type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

}
