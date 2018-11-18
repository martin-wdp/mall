package com.qpmall.auto.service.impl;


import com.ningpai.core.ListSearch;
import com.ningpai.manager.base.BasicSqlSupport;
import com.qpmall.auto.bean.*;
import com.qpmall.auto.dao.AutoStyleMapper;
import com.qpmall.auto.service.AutoStyleService;
import com.qpmall.db.service.DataService;
import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/*import org.apache.poi.ss.formula.functions.T;*/

/**
 * Created by Administrator on 2015/12/18.
 */
@Repository("autoStyleService")
public class AutoStyleServiceImpl extends BasicSqlSupport implements AutoStyleService {

    /**
     * spring 注入
     */
    @Resource(name = "autoStyleMapper")
    private AutoStyleMapper autoStyleMapper;

    /**
     * spring 注入
     */
    @Resource(name = "dataService")
    private DataService dataService ;

    private List<AutoBrandBean> AutoBrandBean_List; //品牌
    private List<AutoStyleBrandBean> AutoStyleBrandBean_List; //品牌车系（品牌厂家）
    private List<AutoSystemBean> AutoSystemBean_List; //车系
    private List<AutoTypeBean> AutoTypeBean_List; //车型
    private List<AutoParameterBean> AutoParameterBean_List; //车辆参数
    private List<AutoProductiveYearBean> AutoProductiveYearBean_List; //生产年份
    private List<AutoStyleBean> AutoStyleBean_List; //车辆

    /**
     * 刷新车型信息
     */
    public boolean loadAutoStyleData() {

        //读取所有的车辆品牌
        AutoBrandBean_List = dataService.dbQueryRowMapper(AutoBrandBean.AutoBrandBean_Sql, new BeanPropertyRowMapper(AutoBrandBean.class));
        //读取所有的车辆品牌厂家
        AutoStyleBrandBean_List = dataService.dbQueryRowMapper(AutoStyleBrandBean.AutoStyleBrandBean_Sql, new BeanPropertyRowMapper(AutoStyleBrandBean.class));
        //读取所有的车系
        AutoSystemBean_List = dataService.dbQueryRowMapper(AutoSystemBean.AutoSystemBean_Sql, new BeanPropertyRowMapper(AutoSystemBean.class));
        //读取所有的车型
        AutoTypeBean_List = dataService.dbQueryRowMapper(AutoTypeBean.AutoTypeBean_Sql, new BeanPropertyRowMapper(AutoTypeBean.class));
        //读取所有的车辆参数
        AutoParameterBean_List = dataService.dbQueryRowMapper(AutoParameterBean.AutoParameterBean_Sql, new BeanPropertyRowMapper(AutoParameterBean.class));
        //读取所有的生产年
        AutoProductiveYearBean_List = dataService.dbQueryRowMapper(AutoProductiveYearBean.AutoProductiveYearBean_Sql, new BeanPropertyRowMapper(AutoProductiveYearBean.class));
        //读取所有的车辆
        AutoStyleBean_List = dataService.dbQueryRowMapper(AutoStyleBean.AutoStyleBean_Sql, new BeanPropertyRowMapper(AutoStyleBean.class));

        return true;
    }

    /**
     * 获取所有品牌
     */
    public List<AutoBrandBean> getAutoBrandBean() {
        return AutoBrandBean_List;
    }

    /**
     * 获取品牌车系，入参为“车辆品牌”的键
     */
    public List<AutoStyleBrandBean> getAutoStyleBrandBean(String AutoBrandID) {
        ListSearch<AutoStyleBrandBean> Search = new ListSearch<AutoStyleBrandBean>();
        return Search.SearchOrder(AutoStyleBrandBean_List, "AutoBrandID", AutoBrandID);
    }

    /**
     * 获取车系，入参为“车辆品牌”的键
     */
    public List<AutoSystemBean> getAutoSystemBean(String AutoStyleID) {
        ListSearch<AutoSystemBean> Search = new ListSearch<AutoSystemBean>();
        return Search.SearchOrder(AutoSystemBean_List, "AutoStyleID", AutoStyleID);
    }

    /**
     * 获取车型，入参为“车系”的键
     */
    public List<AutoTypeBean> getAutoTypeBean(String AutoSystemID) {
        ListSearch<AutoTypeBean> Search = new ListSearch<AutoTypeBean>();
        return Search.SearchOrder(AutoTypeBean_List, "AutoSystemID", AutoSystemID);
    }

    /**
     * 获取车辆参数，入参为“车型”的键
     */
    public List<AutoParameterBean> getAutoParameterBean(String AutoTypeID) {
        ListSearch<AutoParameterBean> Search = new ListSearch<AutoParameterBean>();
        return Search.SearchOrder(AutoParameterBean_List, "AutoTypeID", AutoTypeID);
    }

    /**
     * 获取生产年份，入参为"车辆参数"的键
     */
    public List<AutoProductiveYearBean> getAutoProductiveYearBean(String AutoParameterID) {
        ListSearch<AutoProductiveYearBean> Search = new ListSearch<AutoProductiveYearBean>();
        return Search.SearchOrder(AutoProductiveYearBean_List, "AutoParameterID", AutoParameterID);
    }

    /**
     * 获取车辆（包含力扬ID等），入参为"生产年份"的键
     */
    public List<AutoStyleBean> getAutoStyleBean(String AutoYearID) {
        ListSearch<AutoStyleBean> Search = new ListSearch<AutoStyleBean>();
        return Search.SearchOrder(AutoStyleBean_List, "AutoYearID", AutoYearID);
    }

    @Override
    public List<String> getCheckNodesId(String autoStyleIdLiYangID) {
        List<String> result = new ArrayList<String>();
        if(StringUtils.isNotEmpty(autoStyleIdLiYangID)){
            String[] ids = autoStyleIdLiYangID.split(";");
            for(String id:ids){
                ListSearch<AutoStyleBean> Search = new ListSearch<AutoStyleBean>();
                List<AutoStyleBean> autoStyleBeans=Search.SearchOrder(AutoStyleBean_List, "auto_style_id_LiYang_ID", id);
                for(AutoStyleBean autoStyleBean:autoStyleBeans){
                    if(result.contains(autoStyleBean.getAutoYearID())){
                        continue;
                    }
                    result.add(autoStyleBean.getAutoYearID());
                    ListSearch<AutoProductiveYearBean> Search5 = new ListSearch<AutoProductiveYearBean>();
                    List<AutoProductiveYearBean> beans5=Search5.SearchOrder(AutoProductiveYearBean_List, "AutoYearID", autoStyleBean.getAutoYearID());
                    for(AutoProductiveYearBean productiveYearBean:beans5){
                        if(result.contains(productiveYearBean.getAutoParameterID())){
                            continue;
                        }
                        result.add(productiveYearBean.getAutoParameterID());
                        ListSearch<AutoParameterBean> Search4 = new ListSearch<AutoParameterBean>();
                        List<AutoParameterBean> beans4=Search4.SearchOrder(AutoParameterBean_List, "AutoParameterID", productiveYearBean.getAutoParameterID());
                        for(AutoParameterBean autoParameterBean:beans4){
                            if(result.contains(autoParameterBean.getAutoTypeID())){
                                continue;
                            }
                            result.add(autoParameterBean.getAutoTypeID());
                            ListSearch<AutoTypeBean> Search3= new ListSearch<AutoTypeBean>();
                            List<AutoTypeBean> beans3=Search3.SearchOrder(AutoTypeBean_List, "AutoTypeID", autoParameterBean.getAutoTypeID());
                            for(AutoTypeBean autoTypeBean:beans3){
                                if(result.contains(autoTypeBean.getAutoSystemID())){
                                    continue;
                                }
                                result.add(autoTypeBean.getAutoSystemID());
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    public void getAutoStyleId(String levelNum,String nodeId,StringBuffer autoStyleIdLiYangID,StringBuffer goodsInfoAutoStyle){
        if("5".equals(levelNum)){
            List<AutoStyleBean> autoStyleBeanList = getAutoStyleBean(nodeId);
            if(autoStyleBeanList!= null && autoStyleBeanList.size()>0){
                for(AutoStyleBean autoStyleBean:autoStyleBeanList){
                    if(autoStyleIdLiYangID.length()>0){
                        autoStyleIdLiYangID.append(";");
                    }
                    if(goodsInfoAutoStyle.length()>0){
                        goodsInfoAutoStyle.append(";");
                    }
                    autoStyleIdLiYangID.append(autoStyleBean.getauto_style_id_LiYang_ID());
                    goodsInfoAutoStyle.append(autoStyleBean.getauto_style_id_LiYangYaSuo_ID());
                }

            }
        }else if("4".equals(levelNum)){
            List<AutoProductiveYearBean> autoProductiveYearBeanList =getAutoProductiveYearBean(nodeId);
            if(autoProductiveYearBeanList!= null && autoProductiveYearBeanList.size()>0){
                for(AutoProductiveYearBean autoProductiveYearBean:autoProductiveYearBeanList){
                    getAutoStyleId(String.valueOf(Integer.valueOf(levelNum) + 1), autoProductiveYearBean.getAutoYearID(), autoStyleIdLiYangID, goodsInfoAutoStyle);
                }
            }
        }else if("3".equals(levelNum)){
            List<AutoParameterBean> autoParameterBeanList = getAutoParameterBean(nodeId);
            if(autoParameterBeanList!= null && autoParameterBeanList.size()>0) {
                for (AutoParameterBean autoParameterBean : autoParameterBeanList) {
                    getAutoStyleId(String.valueOf(Integer.valueOf(levelNum)+1),autoParameterBean.getAutoParameterID(),autoStyleIdLiYangID,goodsInfoAutoStyle);
                }
            }
        }

    }

        public static void main(String[] args) {
        AutoStyleService AutoStyleService1 = new AutoStyleServiceImpl();
        AutoStyleService1.loadAutoStyleData();
        List<String> list= AutoStyleService1.getCheckNodesId("FDS0116A0001");


    }
}
