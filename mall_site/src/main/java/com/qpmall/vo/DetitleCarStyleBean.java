package com.qpmall.vo;

import com.ningpai.goods.bean.GoodsBrand;
import com.qpmall.bean.QpAutoStyleBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pl on 2015/10/13.
 * Desc:
 */
public class DetitleCarStyleBean implements Comparable<DetitleCarStyleBean>{
    public String key=new String();
    public List<AutoCarTypeVo> valList=new ArrayList<>();
    public List<DetitleCarStyleBean> valList2=new ArrayList<>();
    public String imageLog;
    public String year;

    public void setYear(String year) {
        this.year = year;
    }

    public String getYear() {
        return year;
    }

    public void setImageLog(String imageLog) {
        this.imageLog = imageLog;
    }

    public String getImageLog() {
        return imageLog;
    }

    public List<GoodsBrand> vlist3=new ArrayList<>();;

    public void setVlist3(List<GoodsBrand> vlist3) {
        this.vlist3 = vlist3;
    }

    public List<GoodsBrand> getVlist3() {
        return vlist3;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setValList(List<AutoCarTypeVo> valList) {
        this.valList = valList;
    }

    public List<AutoCarTypeVo> getValList() {
        return valList;
    }

    public void setValList2(List<DetitleCarStyleBean> valList2) {
        this.valList2 = valList2;
    }

    public List<DetitleCarStyleBean> getValList2() {
        return valList2;
    }

    @Override
    public int compareTo(DetitleCarStyleBean arg0) {
        return this.getYear().compareTo(arg0.getYear());
    }

}
