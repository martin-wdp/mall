package com.ningpai.manager.bean;

/**
 * Echarts 数据库返回类型
 * 
 * @author ggn
 *
 */
public class EchartsTimeCount {

    // 时间
    private String etime;
    // 数量
    private Long ecount;

    public String getEtime() {
        return etime;
    }

    public void setEtime(String etime) {
        this.etime = etime;
    }

    public Long getEcount() {
        return ecount;
    }

    public void setEcount(Long ecount) {
        this.ecount = ecount;
    }

}
