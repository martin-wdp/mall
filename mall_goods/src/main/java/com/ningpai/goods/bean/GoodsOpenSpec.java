package com.ningpai.goods.bean;

/**
 * 商品开启规格表
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年6月30日 下午2:23:30
 * @version 1.0
 */
public class GoodsOpenSpec {
    /*
     * 主键ID
     */
    private Long id;
    /*
     * 商品ID
     */
    private Long goodsId;
    /*
     * 规格ID
     */
    private Long specId;
    /*
     * 删除标记 0:未删除 1:已删除
     */
    private String delFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getSpecId() {
        return specId;
    }

    public void setSpecId(Long specId) {
        this.specId = specId;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
