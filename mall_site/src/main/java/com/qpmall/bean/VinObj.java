package com.qpmall.bean;

/**
 * Created by pl on 2015/10/28.
 * Desc:
 */
public class VinObj {
    public QpAutoStyleBean qpAutoStyleBean;
    public String stat;
    public String msg="";

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public QpAutoStyleBean getQpAutoStyleBean() {
        return qpAutoStyleBean;
    }

    public String getStat() {
        return stat;
    }

    public void setQpAutoStyleBean(QpAutoStyleBean qpAutoStyleBean) {
        this.qpAutoStyleBean = qpAutoStyleBean;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }
}
