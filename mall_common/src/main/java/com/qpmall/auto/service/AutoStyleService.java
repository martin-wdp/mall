package com.qpmall.auto.service;

import com.qpmall.auto.bean.*;

import java.util.List;

/**
 * Created by Administrator on 2015/12/18.
 */
public interface AutoStyleService {

    /**
     * 刷新车型信息
     */
     boolean loadAutoStyleData();
    /**
     * 获取所有品牌
     */
    public List<AutoBrandBean> getAutoBrandBean();
    /**
     * 获取品牌车系，入参为“车辆品牌”的键
     */
    public List<AutoStyleBrandBean> getAutoStyleBrandBean(String AutoBrandID);
    /**
     * 获取车系，入参为“车辆品牌”的键
     */
    public List<AutoSystemBean> getAutoSystemBean(String AutoStyleID);
    /**
     * 获取车型，入参为“车系”的键
     */
    public List<AutoTypeBean> getAutoTypeBean(String AutoSystemID);
    /**
     * 获取车辆参数，入参为“车型”的键
     */
    public List<AutoParameterBean> getAutoParameterBean(String AutoTypeID);
    /**
     * 获取生产年份，入参为"车辆参数"的键
     */
    public List<AutoProductiveYearBean> getAutoProductiveYearBean(String AutoParameterID);
    /**
     * 获取车辆（包含力扬ID等），入参为"生产年份"的键
     */
    public List<AutoStyleBean> getAutoStyleBean(String AutoYearID);

    /**
     * 根据力扬id，获取选中的车型节点
     * @param autoStyleIdLiYangID
     * @return
     */
    public List<String> getCheckNodesId(String autoStyleIdLiYangID);

    /**
     * 根据选中的节点和层级获取力扬ID和压缩ID
     * @param levelNum
     * @param nodeId
     * @param autoStyleIdLiYangID 力扬IDStringBuffer
     * @param goodsInfoAutoStyle  压缩ID的StringBuffer
     */
    public void getAutoStyleId(String levelNum,String nodeId,StringBuffer autoStyleIdLiYangID,StringBuffer goodsInfoAutoStyle);
}
