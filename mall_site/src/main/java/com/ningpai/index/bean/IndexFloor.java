package com.ningpai.index.bean;

import com.ningpai.util.PageBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 楼层
 * @author AthrunNatu
 * 
 * @since 2014年4月10日上午10:52:51
 */
public class IndexFloor {
    /**
     * 实体类-楼层集合
     */
    private List<IndexSiteStoreyBean> floorList = new ArrayList<IndexSiteStoreyBean>();
    /**
     * 分页辅助类
     */
    private PageBean pageBean;

    public PageBean getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean pageBean) {
        this.pageBean = pageBean;
    }

    public List<IndexSiteStoreyBean> getFloorList() {
        return floorList;
    }

    public void setFloorList(List<IndexSiteStoreyBean> floorList) {
        this.floorList = floorList;
    }
}
