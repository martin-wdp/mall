package com.ningpai.FindGoods.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by pl on 2015/9/15.
 * Desc: 找货bean
 */
public class FindGoodsBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String goodsname;
    private String goodspic;
    private String cartype;
    private String linkman;
    private String mobile;
    private Integer state;
    private Date createtime;
    private Long customerid;
    private String ordertemp;

    public void setId(Long id){
        this.id=id;
    }
    public Long getId(){
        return id;
    }
    public void setGoodsname(String goodsname){
        this.goodsname=goodsname;
    }
    public String getGoodsname(){
        return goodsname;
    }
    public void setGoodspic(String goodspic){
        this.goodspic=goodspic;
    }
    public String getGoodspic(){
        return goodspic;
    }
    public void setCartype(String cartype){
        this.cartype=cartype;
    }
    public String getCartype(){
        return cartype;
    }
    public void setLinkman(String linkman){
        this.linkman=linkman;
    }
    public String getLinkman(){
        return linkman;
    }
    public void setMobile(String mobile){
        this.mobile=mobile;
    }
    public String getMobile(){
        return mobile;
    }
    public void setState(Integer state){
        this.state=state;
    }
    public Integer getState(){
        return state;
    }
    public void setCreatetime(Date createtime){
        this.createtime=createtime;
    }
    public Date getCreatetime(){
        return createtime;
    }
    public void setCustomerid(Long customerid){
        this.customerid=customerid;
    }
    public Long getCustomerid(){
        return customerid;
    }
    public void setOrdertemp(String ordertemp){
        this.ordertemp=ordertemp;
    }
    public String getOrdertemp(){
        return ordertemp;
    }
}
