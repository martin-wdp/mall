package com.qpmall.controller;

import com.alibaba.fastjson.JSON;
import com.ningpai.api.util.JsonUtil;
import com.qpmall.bean.QpAutoStyleBean;
import com.qpmall.bean.VinObj;
import com.qpmall.service.QpAutoStyleService;
import com.qpmall.vo.AutoCarTypeVo;
import com.qpmall.vo.BrandBean;
import com.qpmall.vo.DetitleCarStyleBean;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Created by pl on 2015/10/9.
 * Desc:
 */
@Controller
public class CarStyleController {

    @Resource(name = "qpAutoStyleService")
    public QpAutoStyleService qpAutoStyleService;

    /**
     * 获取所有适配车型数据
     * zz:pl
     * @return json
     */
    @RequestMapping(value = "/carstylelist",produces="text/plain;charset=UTF-8")
    @ResponseBody
    public String allList()
    {
        List<QpAutoStyleBean> list=qpAutoStyleService.findAllList();
        return JSON.toJSONString(list);
    }

    /***
     * 查询所有年款
     * zz：pl
     * @return
     */
    @RequestMapping(value = "/carstylelistByYear",produces="text/plain;charset=UTF-8")
    @ResponseBody
    public String GetCarStyleByYear()
    {
        List<String> list=qpAutoStyleService.findListByYear();

        return JSON.toJSONString(list);
    }

    /***
     * 根据条件查询适配车型数据
     * zz：pl
     * @param qpAutoStyleBean
     * @return
     */
    @RequestMapping(value = "/GetCarStyleByType",produces="text/plain;charset=UTF-8")
    @ResponseBody
    public String GetCarStyleByType(QpAutoStyleBean qpAutoStyleBean)
    {
        List<QpAutoStyleBean> newlist=new ArrayList<>() ;
        List<QpAutoStyleBean> list=qpAutoStyleService.findAllList();
        //<QpAutoStyleBean> list=qpAutoStyleService.findListByType(qpAutoStyleBean);
        /*<if test="autoStyleEngine!=null and autoStyleEngine!=''">
            and  auto_style_engine = #{autoStyleEngine,jdbcType=VARCHAR}
        </if>
        <if test="autoStyleGearbox!=null and autoStyleGearbox!=''">
            and  auto_style_gearbox = #{autoStyleGearbox,jdbcType=VARCHAR}
        </if>
        <if test="goodsBrandName!=null and goodsBrandName!=''">
            and  goods_brand_name = #{goodsBrandName,jdbcType=VARCHAR}
        </if>
        <if test="autoStyleSystem!=null and autoStyleSystem!=''">
            and  auto_style_system = #{autoStyleSystem,jdbcType=VARCHAR}
        </if>
        <if test="autoStyleType!=null and autoStyleType!=''">
            and  auto_style_type = #{autoStyleType,jdbcType=VARCHAR}
        </if>*/

        for (int i = 0; i < list.size(); i++) {
            if(qpAutoStyleBean.getGoodsBrandName()!=null&&!"".equals(qpAutoStyleBean.getGoodsBrandName())&&"".equals(qpAutoStyleBean.getAutoStyleSystem())&&"".equals(qpAutoStyleBean.getAutoStyleType())&&"".equals(qpAutoStyleBean.getAutoStyleGearbox())&&"".equals(qpAutoStyleBean.getAutoStyleEngine())&&list.get(i).getGoodsBrandName().equals(qpAutoStyleBean.getGoodsBrandName())){
                newlist.add(list.get(i));
            }
            if(qpAutoStyleBean.getGoodsBrandName()!=null&&!"".equals(qpAutoStyleBean.getGoodsBrandName())&&qpAutoStyleBean.getAutoStyleSystem()!=null&&!"".equals(qpAutoStyleBean.getAutoStyleSystem())&&"".equals(qpAutoStyleBean.getAutoStyleType())&&"".equals(qpAutoStyleBean.getAutoStyleGearbox())&&"".equals(qpAutoStyleBean.getAutoStyleEngine())&&list.get(i).getAutoStyleSystem().equals(qpAutoStyleBean.getAutoStyleSystem())&&list.get(i).getGoodsBrandName().equals(qpAutoStyleBean.getGoodsBrandName())){
                newlist.add(list.get(i));
            }
            if(qpAutoStyleBean.getGoodsBrandName()!=null&&!"".equals(qpAutoStyleBean.getGoodsBrandName())&&qpAutoStyleBean.getAutoStyleSystem()!=null&&!"".equals(qpAutoStyleBean.getAutoStyleSystem())&&qpAutoStyleBean.getAutoStyleType()!=null&&!"".equals(qpAutoStyleBean.getAutoStyleType())&&"".equals(qpAutoStyleBean.getAutoStyleGearbox())&&"".equals(qpAutoStyleBean.getAutoStyleEngine())&&list.get(i).getAutoStyleType().equals(qpAutoStyleBean.getAutoStyleType())&&list.get(i).getGoodsBrandName().equals(qpAutoStyleBean.getGoodsBrandName())&&list.get(i).getAutoStyleSystem().equals(qpAutoStyleBean.getAutoStyleSystem())) {
                newlist.add(list.get(i));
            }
            if(qpAutoStyleBean.getGoodsBrandName()!=null&&!"".equals(qpAutoStyleBean.getGoodsBrandName())&&qpAutoStyleBean.getAutoStyleSystem()!=null&&!"".equals(qpAutoStyleBean.getAutoStyleSystem())&&qpAutoStyleBean.getAutoStyleType()!=null&&!"".equals(qpAutoStyleBean.getAutoStyleType())&&qpAutoStyleBean.getAutoStyleGearbox()!=null&&qpAutoStyleBean.getAutoStyleEngine()!=null&&(!"".equals(qpAutoStyleBean.getAutoStyleEngine())||!"".equals(qpAutoStyleBean.getAutoStyleGearbox()))&&list.get(i).getAutoStyleGearbox().equals(qpAutoStyleBean.getAutoStyleGearbox())&&list.get(i).getAutoStyleEngine().equals(qpAutoStyleBean.getAutoStyleEngine())&&list.get(i).getGoodsBrandName().equals(qpAutoStyleBean.getGoodsBrandName())&&list.get(i).getAutoStyleSystem().equals(qpAutoStyleBean.getAutoStyleSystem())&&list.get(i).getAutoStyleType().equals(qpAutoStyleBean.getAutoStyleType())){
                newlist.add(list.get(i));
            }
        }

        /*if(qpAutoStyleBean.getGoodsBrandName()!=null&&!"".equals(qpAutoStyleBean.getGoodsBrandName())&&qpAutoStyleBean.getAutoStyleSystem()!=null&&!"".equals(qpAutoStyleBean.getAutoStyleSystem())&&qpAutoStyleBean.getAutoStyleType()!=null&&!"".equals(qpAutoStyleBean.getAutoStyleType())&&qpAutoStyleBean.getAutoStyleGearbox()!=null&&qpAutoStyleBean.getAutoStyleEngine()!=null&&(!"".equals(qpAutoStyleBean.getAutoStyleEngine())||!"".equals(qpAutoStyleBean.getAutoStyleGearbox()))){

        }
        if(qpAutoStyleBean.getGoodsBrandName()!=null&&!"".equals(qpAutoStyleBean.getGoodsBrandName())&&"".equals(qpAutoStyleBean.getAutoStyleSystem())&&"".equals(qpAutoStyleBean.getAutoStyleType())&&"".equals(qpAutoStyleBean.getAutoStyleGearbox())&&"".equals(qpAutoStyleBean.getAutoStyleEngine())){
            for (int i = 0; i < list.size(); i++) {

            }
        }
        if(qpAutoStyleBean.getGoodsBrandName()!=null&&!"".equals(qpAutoStyleBean.getGoodsBrandName())&&qpAutoStyleBean.getAutoStyleSystem()!=null&&!"".equals(qpAutoStyleBean.getAutoStyleSystem())&&"".equals(qpAutoStyleBean.getAutoStyleType())&&"".equals(qpAutoStyleBean.getAutoStyleGearbox())&&"".equals(qpAutoStyleBean.getAutoStyleEngine())){
            for (int i = 0; i < list.size(); i++) {

            }
        }
        if(qpAutoStyleBean.getGoodsBrandName()!=null&&!"".equals(qpAutoStyleBean.getGoodsBrandName())&&qpAutoStyleBean.getAutoStyleSystem()!=null&&!"".equals(qpAutoStyleBean.getAutoStyleSystem())&&qpAutoStyleBean.getAutoStyleType()!=null&&!"".equals(qpAutoStyleBean.getAutoStyleType())&&"".equals(qpAutoStyleBean.getAutoStyleGearbox())&&"".equals(qpAutoStyleBean.getAutoStyleEngine())){
            for (int i = 0; i < list.size(); i++) {

            }
        }*/
        List<String> brandMake=new ArrayList<>();
        for (int i = 0; i < newlist.size(); i++) {
            if(!brandMake.contains(newlist.get(i).getAutoStyleBrandMake())){
                brandMake.add(newlist.get(i).getAutoStyleBrandMake());
            }
        }

        /*Map<String,List<QpAutoStyleBean>> map=new HashedMap();
        List<QpAutoStyleBean> nelist=new ArrayList<>();
        for (int i = 0; i < brandMake.size(); i++) {
            for (int j = 0; j < newlist.size(); j++) {
                if(brandMake.get(i).equals(newlist.get(j).getAutoStyleBrandMake())){
                    nelist.add(newlist.get(j));
                }
            }
            map.put(brandMake.get(i),nelist);
        }*/

        //map.put()
        Map map=new HashedMap();
        map.put("brandMake",brandMake);
        map.put("newlist",newlist);
        return JSON.toJSONString(map);

    }

    /***
     * 根据年款查询品牌
     * zz：pl
     * @param carYearStr
     * @return
     */
    @RequestMapping(value = "/GetAutoStyleByBrand",produces="text/plain;charset=UTF-8")
    @ResponseBody
    public String GetAutoStyleByBrand(String carYearStr)
    {
        /*if("".equals(carYearStr)||carYearStr==null)
        {
            return null;
        }*/
        List<BrandBean> list=qpAutoStyleService.findAutoStyleByBrand(carYearStr);
        Map<String,List<BrandBean>>map=new HashMap<String,List<BrandBean>>();
        for (int i = 0; i < list.size(); i++) {
            BrandBean brandBean=list.get(i);
            String init=brandBean.getBrand_name_initial();
            if(map.containsKey(init)){

                map.get(init).add(brandBean);
            }else {
                List<BrandBean> newList=new ArrayList<>();
                newList.add(brandBean);
                map.put(init,newList);
            }
        }
        List<Map.Entry<String, List<BrandBean>>> infoIds =
                new ArrayList<Map.Entry<String, List<BrandBean>>>(map.entrySet());

        //排序
        Collections.sort(infoIds, new Comparator<Map.Entry<String, List<BrandBean>>>() {
            public int compare(Map.Entry<String, List<BrandBean>> o1, Map.Entry<String, List<BrandBean>> o2) {
                //return (o2.getValue() - o1.getValue());
                return (o1.getKey()).toString().compareTo(o2.getKey());
            }
        });

        return JSON.toJSONString(infoIds);
    }

    /***
     * 查询所有适配车型
     * @return
     */
    @RequestMapping(value = "/GetAllAutoStyle",produces="text/plain;charset=UTF-8")
    @ResponseBody
    public String GEtAllAutoStyleData()
    {
        /*List<QpAutoStyleBean> list =new ArrayList<>();
        String[] str=null;
        for (int i = 5092; i < 5563; i++) {
            str;
            list.add(qpAutoStyleService.findVoObjByAutoID(str));
        }
        //List<QpAutoStyleBean> list = qpAutoStyleService.findAllList();
        List<DetitleCarStyleBean> list1=groupByName(list);
        //List<DetitleCarStyleBean> list2=new ArrayList<>()
        for (int i = 0; i < list1.size(); i++) {
            DetitleCarStyleBean detitleCarStyleBean=list1.get(i);
            List<DetitleCarStyleBean> list2 = groupBySystem(list1.get(i).getValList());
            detitleCarStyleBean.setValList2(list2);

            for (int j = 0; j <list2.size(); j++) {
                DetitleCarStyleBean detitleCarStyleBean2=list2.get(j);
                List<DetitleCarStyleBean> list3 = groupByType(list2.get(j).getValList());
                detitleCarStyleBean2.setValList2(list3);
            }
        }*/
        return JSON.toJSONString("");
    }

    /**
     * 分组
     *
     * @param target
     *            <code>List</code>待分组的集合
     * @return <code>List</code>分组完成后的容器对象集合
     */
    public List<DetitleCarStyleBean> groupByName(List<AutoCarTypeVo> target) {
        List<DetitleCarStyleBean> result = new ArrayList<DetitleCarStyleBean>();
        for (int i = 0; i < target.size(); i++) {
            AutoCarTypeVo myObject = target.get(i);
            target.remove(myObject);
            i=i-1;
            DetitleCarStyleBean gc = new DetitleCarStyleBean();
            gc.getValList().add(myObject);
            gc.setYear(myObject.getAutoStyleProductiveYear());
            gc.setKey(myObject.getGoodsBrandName());
            for (int j = 0; j < target.size();) {
                AutoCarTypeVo _myObject = target.get(j);
                // 相同，分组，并加入到组容器集合
                if (_myObject.getGoodsBrandName().equals(myObject.getGoodsBrandName())) {
                    gc.getValList().add(_myObject);

                    target.remove(_myObject);
                } else {
                    //gc.setKey(_myObject.getGoodsBrandName());
                    j++;
                }
            }
            result.add(gc);
        }
        return result;
    }


    /**
     * 分组
     *
     * @param target
     *            <code>List</code>待分组的集合
     * @return <code>List</code>分组完成后的容器对象集合
     */
    public List<DetitleCarStyleBean> groupBySystem(List<AutoCarTypeVo> target) {
        List<DetitleCarStyleBean> result = new ArrayList<DetitleCarStyleBean>();
        for (int i = 0; i < target.size(); i++) {
            AutoCarTypeVo myObject = target.get(i);
            target.remove(myObject);
            i=i-1;
            DetitleCarStyleBean gc = new DetitleCarStyleBean();
            gc.getValList().add(myObject);
            gc.setKey(myObject.getAutoStyleSystem());
            for (int j = 0; j < target.size();) {
                AutoCarTypeVo _myObject = target.get(j);
                // 相同，分组，并加入到组容器集合
                if (_myObject.getAutoStyleSystem().equals(myObject.getAutoStyleSystem())) {
                    gc.getValList().add(_myObject);

                    target.remove(_myObject);
                } else {
                    //gc.setKey(_myObject.getGoodsBrandName());
                    j++;
                }
            }
            result.add(gc);
        }
        return result;
    }

    /**
     * 分组
     *
     * @param target
     *            <code>List</code>待分组的集合
     * @return <code>List</code>分组完成后的容器对象集合
     */
    public List<DetitleCarStyleBean> groupByType(List<AutoCarTypeVo> target) {
        List<DetitleCarStyleBean> result = new ArrayList<DetitleCarStyleBean>();
        for (int i = 0; i < target.size(); i++) {
            AutoCarTypeVo myObject = target.get(i);
            target.remove(myObject);
            i=i-1;
            DetitleCarStyleBean gc = new DetitleCarStyleBean();
            gc.getValList().add(myObject);
            gc.setKey(myObject.getAutoStyleType());
            for (int j = 0; j < target.size();) {
                AutoCarTypeVo _myObject = target.get(j);
                // 相同，分组，并加入到组容器集合
                if (_myObject.getAutoStyleType().equals(myObject.getAutoStyleType())) {
                    gc.getValList().add(_myObject);

                    target.remove(_myObject);
                } else {
                    //gc.setKey(_myObject.getGoodsBrandName());
                    j++;
                }
            }
            result.add(gc);
        }
        return result;
    }

    /***
     * 根据Vin码查车型
     * zz：pl
     * @return
     */
    @RequestMapping(value = "/GetCarAutoStyleByVin",produces="text/plain;charset=UTF-8")
    @ResponseBody
    public String GetCarAutoStyleByVin(String vin){
        String result="";
        String model_id="";
        String backCode="";
        String msg="";
        try {
            result = VinWebService.GetVinObj("http://58.221.57.73:8088/webService/NIDCXService.asmx",vin,"CJ6");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Map retMap = parseXmlToList2(result);
        if(result.contains("Model_ID")){
            Pattern p = Pattern.compile("<Model_ID>(.*?)</Model_ID>");
            Matcher m = p.matcher(result);
            while (m.find()) {
                model_id=m.group(1);
            }
            List<QpAutoStyleBean> qpAutoStyleBean= qpAutoStyleService.findObjByAutoID(model_id);
            VinObj obj=new VinObj();
            obj.setStat("ok");
            obj.setQpAutoStyleBean(qpAutoStyleBean.get(0));
            return JsonUtil.getJSONString(obj);
        }else{
            Pattern p = Pattern.compile("<Check>(.*?)</Check>");
            Matcher m = p.matcher(result);
            while (m.find()) {
                backCode=m.group(1);
            }
            if(backCode.equals("E0")){
                msg="未查到此VIN码对应车型！";
            }
            else if(backCode.equals("E1")||backCode.equals("E2")||backCode.equals("E3")||backCode.equals("E4")||backCode.equals("E5")){
                msg="VIN格式不正确！";
            }
            else if(backCode.equals("E6")||backCode.equals("E7")){
                msg="系统服务错误，请联系客服！";
            }
            VinObj obj=new VinObj();
            obj.setStat("err");
            obj.setMsg(msg);
            return JsonUtil.getJSONString(obj);
        }
    }

}
