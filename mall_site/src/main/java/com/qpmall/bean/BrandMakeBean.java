package com.qpmall.bean;

import java.util.List;
import java.util.Set;

/**
 * Created by pl on 2015/12/12.
 * Desc:
 */
public class BrandMakeBean {
    private String brandmake;
    private List<QpAutoStyleBean> list;

    public List<QpAutoStyleBean> getList() {
        return list;
    }

    public String getBrandmake() {
        return brandmake;
    }

    public void setBrandmake(String brandmake) {
        this.brandmake = brandmake;
    }

    public void setList(List<QpAutoStyleBean> list) {
        this.list = list;
    }

}
