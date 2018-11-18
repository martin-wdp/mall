package com.qpmall.auto.controller;

import com.qpmall.auto.bean.*;
import com.qpmall.auto.service.AutoStyleService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2015/12/18.
 */
@Controller
@RequestMapping(value = "/autostyle")
public class AutoStyleController {

    @Resource(name = "autoStyleService")
    public AutoStyleService autoStyleService;

    @RequestMapping(value ="loadStyle")
    public void loadAutoStyleData(){
        autoStyleService.loadAutoStyleData();
    }

    /**
     * 获取所有品牌
     *
     * @return
     */
    @RequestMapping("/getAutoBrandBean")
    @ResponseBody
    public List<AutoBrandBean> getAutoBrandBean() {
        return autoStyleService.getAutoBrandBean();
    }

    /**
     * 获取品牌车系，入参为“车辆品牌”的键
     *
     * @return
     */
    @RequestMapping("/getAutoStyleBrandBean")
    @ResponseBody
    public List<AutoStyleBrandBean> getAutoStyleBrandBean(String AutoBrandID) {
        return autoStyleService.getAutoStyleBrandBean(AutoBrandID);
    }

    /**
     * 获取车系，入参为“车辆品牌”的键
     *
     * @return
     */
    @RequestMapping("/getAutoSystemBean")
    @ResponseBody
    public List<AutoSystemBean> getAutoSystemBean(String AutoStyleID) {
        return autoStyleService.getAutoSystemBean(AutoStyleID);
    }

    /**
     * 获取车型，入参为“车系”的键
     *
     * @return
     */
    @RequestMapping("/getAutoTypeBean")
    @ResponseBody
    public List<AutoTypeBean> getAutoTypeBean(String AutoSystemID) {
        return autoStyleService.getAutoTypeBean(AutoSystemID);
    }

    /**
     * 获取车辆参数，入参为“车型”的键
     *
     * @return
     */
    @RequestMapping("/getAutoParameterBean")
    @ResponseBody
    public List<AutoParameterBean> getAutoParameterBean(String AutoTypeID) {
        return autoStyleService.getAutoParameterBean(AutoTypeID);
    }

    /**
     * 获取生产年份，入参为"车辆参数"的键
     *
     * @return
     */
    @RequestMapping("/getAutoProductiveYearBean")
    @ResponseBody
    public List<AutoProductiveYearBean> getAutoProductiveYearBean(String AutoParameterID) {
        return autoStyleService.getAutoProductiveYearBean(AutoParameterID);
    }

    /**
     * 获取车辆（包含力扬ID等），入参为"生产年份"的键
     *
     * @return
     */
    @RequestMapping("/getAutoStyleBean")
    @ResponseBody
    public List<AutoStyleBean> getAutoStyleBean(String AutoYearID) {
        return autoStyleService.getAutoStyleBean(AutoYearID);
    }

    @RequestMapping(value = "/getTreeDatas",produces ="text/plain;charset=UTF-8")
    @ResponseBody
    public String getTreeDatas(String pId,String levelNum,String liYangID,String parentName){
        StringBuffer stringBuffer = new StringBuffer("[");
        if(StringUtils.isEmpty(pId)){
            //
            List<AutoBrandBean> brandBeans = autoStyleService.getAutoBrandBean();
            if(brandBeans!= null && brandBeans.size()>0){
                for(AutoBrandBean bean:brandBeans){
                    stringBuffer.append("{\"id\":\""+bean.getAutoBrandID())
                            .append("\",\"pId\":\"").append(pId)
                            .append("\",\"name\":\"").append(bean.getgoods_brand_name())
                            .append("\",\"parentName\":\"").append(bean.getgoods_brand_name())
                            .append("\",\"nocheck\":true,\"levelNum\":\"1\",\"isParent\":\"true\"},");
                }
            }
            if(StringUtils.isNotEmpty(liYangID)){
                List<String> checkNodeId=autoStyleService.getCheckNodesId(liYangID);
                //
                if(checkNodeId!= null && checkNodeId.size()>0){
                    stringBuffer.append("{\"id\":\"-1\",\"pId\":\"\",\"checkNoId\":\"").append(checkNodeId.toString())
                            .append("\",\"isHidden\":\"true\"},");
                }
            }
        }else{
            if("1".equals(levelNum)){
                //第二级
                List<AutoStyleBrandBean> autoStyleBrandBeanList =autoStyleService.getAutoStyleBrandBean(pId);
                if(autoStyleBrandBeanList!= null && autoStyleBrandBeanList.size()>0){
                    for(AutoStyleBrandBean autoStyleBrandBean:autoStyleBrandBeanList){
                        stringBuffer.append("{\"id\":\""+autoStyleBrandBean.getAutoStyleID())
                                .append("\",\"pId\":\"").append(pId)
                                .append("\",\"name\":\"").append(autoStyleBrandBean.getauto_style_brand_make());
                        if(StringUtils.isNotEmpty(parentName)){
                            stringBuffer.append("\",\"parentName\":\"").append(parentName+","+autoStyleBrandBean.getauto_style_brand_make());
                        }
                        stringBuffer.append("\",\"nocheck\":true,\"levelNum\":\"2\",\"isParent\":\"true\"},");
                    }
                }
            }else if("2".equals(levelNum)){
                List<AutoSystemBean> autoSystemBeanList =autoStyleService.getAutoSystemBean(pId);
                if(autoSystemBeanList!= null && autoSystemBeanList.size()>0){
                    for(AutoSystemBean autoSystemBean:autoSystemBeanList){
                        List<AutoTypeBean> autoTypeBeanList =autoStyleService.getAutoTypeBean(autoSystemBean.getAutoSystemID());
                        if(autoTypeBeanList!= null && autoTypeBeanList.size()>0){
                            for(AutoTypeBean autoTypeBean:autoTypeBeanList){
                                stringBuffer.append("{\"id\":\""+autoTypeBean.getAutoTypeID())
                                        .append("\",\"pId\":\"").append(pId)
                                        .append("\",\"name\":\"").append(autoTypeBean.getauto_style_type());
                                if(StringUtils.isNotEmpty(parentName)){
                                    stringBuffer.append("\",\"parentName\":\"").append(parentName+","+autoTypeBean.getauto_style_type());
                                }
                                stringBuffer.append("\",\"chkDisabled\":\"false\",\"levelNum\":\"3\",\"isParent\":\"true\"},");
                            }
                        }
                    }
                }
            }else if("3".equals(levelNum)){
                List<AutoParameterBean> autoParameterBeanList = autoStyleService.getAutoParameterBean(pId);
                if(autoParameterBeanList!= null && autoParameterBeanList.size()>0){
                    for(AutoParameterBean autoParameterBean:autoParameterBeanList){
                        stringBuffer.append("{\"id\":\""+autoParameterBean.getAutoParameterID())
                                .append("\",\"pId\":\"").append(pId)
                                .append("\",\"name\":\"").append(autoParameterBean.getauto_style_sales_version());
                        if(StringUtils.isNotEmpty(parentName)){
                            stringBuffer.append("\",\"parentName\":\"").append(parentName+","+autoParameterBean.getauto_style_sales_version() );
                        }
                        stringBuffer.append("\",\"chkDisabled\":\"false\",\"levelNum\":\"4\",\"isParent\":\"true\"},");
                    }
                }
            }else if("4".equals(levelNum)){
                List<AutoProductiveYearBean> autoProductiveYearBeanList =autoStyleService.getAutoProductiveYearBean(pId);
                if(autoProductiveYearBeanList!= null && autoProductiveYearBeanList.size()>0){
                    for(AutoProductiveYearBean autoProductiveYearBean:autoProductiveYearBeanList){
                        stringBuffer.append("{\"id\":\""+autoProductiveYearBean.getAutoYearID())
                                .append("\",\"pId\":\"").append(pId)
                                .append("\",\"name\":\"").append(autoProductiveYearBean.getauto_style_productive_year());
                        if(StringUtils.isNotEmpty(parentName)){
                            stringBuffer.append("\",\"parentName\":\"").append(parentName+","+autoProductiveYearBean.getauto_style_productive_year() );
                        }
                        stringBuffer.append("\",\"chkDisabled\":\"false\",\"levelNum\":\"5\",\"isParent\":\"false\"},");
                    }
                }
//            }else if("6".equals(levelNum)){
//                List<AutoStyleBean> autoStyleBeanList = autoStyleService.getAutoStyleBean(pId);
//                if(autoStyleBeanList!= null && autoStyleBeanList.size()>0){
//                    for(AutoStyleBean autoStyleBean:autoStyleBeanList){
//                        stringBuffer.append("{\"id\":\""+autoStyleBean.getauto_style_id_LiYang_ID())
//                                .append("\",\"pId\":\"").append(pId)
//                                .append("\",\"name\":\"").append(autoStyleBean.getauto_style_year())
//                                .append("\",\"chkDisabled\":\"false\",\"levelNum\":\"7\",\"isParent\":\"false\"},");
//                    }
//                }
//
            }
        }
        if(stringBuffer.length()>1){
            stringBuffer =new StringBuffer(stringBuffer.substring(0, stringBuffer.length()-1));
        }
        return stringBuffer.append("]").toString();
    }

    @RequestMapping(value = "/getAutoStyleId",produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object getAutoStyleByNode(String jsonArr){
        if(StringUtils.isEmpty(jsonArr)){
            return null;
        }

        JSONArray styleInfo = JSONArray.fromObject(jsonArr);
        if(styleInfo == null){
            return null;
        }
//        StringBuffer result = new StringBuffer("[");
        for(int i =0;i < styleInfo.size();i++){
//            if(i!=0){
//                result.append(",");
//            }
            StringBuffer autoStyleIdLiYangID = new StringBuffer();
            StringBuffer goodsInfoAutoStyle = new StringBuffer();
            JSONObject jsonObject = (JSONObject) styleInfo.get(i);
            String levelNum =jsonObject.getString("levelNum");
            String nodeId = jsonObject.getString("id");
            autoStyleService.getAutoStyleId(levelNum, nodeId, autoStyleIdLiYangID, goodsInfoAutoStyle);
            jsonObject.put("liyangId",autoStyleIdLiYangID.toString());
            jsonObject.put("yasuoId",goodsInfoAutoStyle.toString());
            jsonObject.remove("levelNum");
            jsonObject.remove("id");
//            result.append("{\"liyangId\":\"").append(autoStyleIdLiYangID).append("\",\"yasuoId\":\""+goodsInfoAutoStyle+"\",\"path\":\""+jsonObject.getString("id")+"\"}");
        }

//        return result.append("]").toString();
        return styleInfo;
    }
}
