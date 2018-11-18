package com.ningpai.goods.bean;

/**
 * 仓库关联城市表
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年4月28日 下午2:41:15
 * @version 1.0
 */
public class WareCity {
    /*
     * 主键ID
     */
    private Long id;
    /*
     * 仓库ID
     */
    private Long wareId;
    /*
     * 区县ID
     */
    private Long cityId;
    /*
     * 删除标记
     */
    private String delFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWareId() {
        return wareId;
    }

    public void setWareId(Long wareId) {
        this.wareId = wareId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
