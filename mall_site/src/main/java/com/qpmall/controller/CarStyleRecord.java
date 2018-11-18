package com.qpmall.controller;

import com.alibaba.fastjson.JSON;
import com.ningpai.customer.bean.Customer;
import com.ningpai.site.customer.service.impl.CustomerServiceMapper;
import com.qpmall.bean.QpAutoStyleBean;
import com.qpmall.bean.QpCustomerAutoStyleSearchBean;
import com.qpmall.service.QpAutoStyleService;
import com.qpmall.service.QpCustomerAutoStyleSearchService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by pl on 2015/10/12.
 * Desc:
 */
@Controller
public class CarStyleRecord {
    /***
     * 会员浏览记录service
     */
    @Resource(name ="qpCustomerAutoStyleSearchService")
    public QpCustomerAutoStyleSearchService qpCustomerAutoStyleSearchService;
    /***
     * 适配车款service
     */
    @Resource(name="qpAutoStyleService")
    public QpAutoStyleService qpAutoStyleService;

    @Resource(name = "customerServiceSite")
    public CustomerServiceMapper customerServiceMapper;

    /***
     * 根据用户id查询浏览记录
     * zz：pl
     * @param request
     * @return
     */
    @RequestMapping(value = "/GetRecordByUserId",produces="text/plain;charset=UTF-8")
    @ResponseBody
    public String GetRecordByUserId(HttpServletRequest request)
    {//,HttpSession httpSession
        Long userId = (Long) request.getSession().getAttribute("customerId");
        List<QpCustomerAutoStyleSearchBean> list=new ArrayList<QpCustomerAutoStyleSearchBean>();
        //Set<QpAutoStyleBean> qpAutoStyleBeanList=new HashSet<>();
        List<QpAutoStyleBean> qpAutoStyleBeanList=new ArrayList<QpAutoStyleBean>();
        if(userId!=null)
        {
            list=qpCustomerAutoStyleSearchService.findListByUserID(userId);
            for (int i = 0; i < list.size(); i++) {
                List<QpAutoStyleBean> qpAutoStyleBean = qpAutoStyleService.findObjByAutoID(list.get(i).getAutoStyleId());
                if(qpAutoStyleBean!=null&&qpAutoStyleBean.size()>0){
                    qpAutoStyleBeanList.add(qpAutoStyleBean.get(0));
                }
            }
        }
        Cookie[] cookies = request.getCookies();
        if(null!=cookies){
            for(Cookie cookie : cookies){
                List<QpAutoStyleBean> qpAutoStyleBean = qpAutoStyleService.findObjByAutoID(cookie.getValue());

                if(qpAutoStyleBean!=null&&qpAutoStyleBean.size()>0) {
                    int n=0;
                    for(QpAutoStyleBean model:qpAutoStyleBeanList){
                        if(model.getAutoStyleIdLiyangyasuoId().equals(qpAutoStyleBean.get(0).getAutoStyleIdLiyangyasuoId())){
                            n++;
                        }
                    }
                    if(n==0){
                        qpAutoStyleBeanList.add(qpAutoStyleBean.get(0));
                    }
                }
            }
        }
        return JSON.toJSONString(qpAutoStyleBeanList);
    }


    /***
     * 添加浏览记录
     * zz：pl
     * @param request
     * @param response
     * @param qpAutoStyleBean
     * @return
     */
    @RequestMapping(value = "/AddRecord",produces="text/plain;charset=UTF-8")
    @ResponseBody
    public String AddCarStyle(HttpServletRequest request,HttpServletResponse response,QpAutoStyleBean qpAutoStyleBean,Long customerId)
    {

        String selectVal=""+(qpAutoStyleBean.getGoodsBrandName()==null?"暂无":qpAutoStyleBean.getGoodsBrandName())+" "+(qpAutoStyleBean.getAutoStyleType()==null?"暂无":qpAutoStyleBean.getAutoStyleType())+" "+(qpAutoStyleBean.getAutoStyleSalesName()==null?"暂无":qpAutoStyleBean.getAutoStyleSalesName())+" "+(qpAutoStyleBean.getAutoStyleYear()==null?"暂无":qpAutoStyleBean.getAutoStyleYear())+"年产";
        request.getSession().setAttribute("selectVal",selectVal);

        Long userId = (Long) request.getSession().getAttribute("customerId");
        Customer customer = (Customer) request.getSession().getAttribute("cust");
        QpCustomerAutoStyleSearchBean qpCustomerAutoStyleSearchBean=new QpCustomerAutoStyleSearchBean();
        if(userId==null&&customerId!=null){
            userId=customerId;
        }
        if(customer==null&&customerId!=null)
        {
            customer=customerServiceMapper.queryAddressByCustomerId(customerId);
        }
        List<QpAutoStyleBean> qpAutoStyleBeans=null;
        if(userId!=null&&customer!=null) {
            java.util.Date date1=new java.util.Date();
            //qpCustomerAutoStyleSearchBean.setAutoStyleId(qpAutoStyleBean.getAutoStyleIdLiyangId());
            qpCustomerAutoStyleSearchBean.setAutoStyleId(qpAutoStyleBean.getAutoStyleIdLiyangyasuoId());
            qpCustomerAutoStyleSearchBean.setCustomerId(userId);
            qpCustomerAutoStyleSearchBean.setCustomerAutoStyleSearchCreateName(customer.getCustomerUsername());
            qpCustomerAutoStyleSearchBean.setCustomerAutoStyleSearchCreateTime(date1);
            qpCustomerAutoStyleSearchBean.setCustomerAutoStyleSearchDelflag("0");

            Map<String,Object>map=new HashMap<>();
            map.put("userId",userId);
            map.put("autoId",qpAutoStyleBean.getAutoStyleIdLiyangyasuoId());
            QpCustomerAutoStyleSearchBean qpCustomerAutoStyleSearchBean2 = qpCustomerAutoStyleSearchService.findRecordByUserIdAndAutoId(map);
            qpAutoStyleBeans = qpAutoStyleService.findObjByAutoID(qpAutoStyleBean.getAutoStyleIdLiyangyasuoId());
            if(qpCustomerAutoStyleSearchBean2!=null)
            {
                return "ok";
            }

            int temp = qpCustomerAutoStyleSearchService.insert(qpCustomerAutoStyleSearchBean);
            if(temp==0)
            {
                return "err";
            }
        }else{
            Cookie[] cookies = request.getCookies();
            if(null!=cookies){
                for(Cookie cookie : cookies){
                    qpAutoStyleBeans = qpAutoStyleService.findObjByAutoID(cookie.getValue());
                    if(cookie.getValue().equals(qpAutoStyleBean.getAutoStyleIdLiyangyasuoId())){
                        return "ok";
                    }
                }
            }
            Cookie cookie = new Cookie(qpAutoStyleBean.getAutoStyleIdLiyangyasuoId(),qpAutoStyleBean.getAutoStyleIdLiyangyasuoId());
            cookie.setPath("/");
            cookie.setMaxAge(1 * 24 * 3600);
            response.addCookie(cookie);
        }

        return "ok";
    }

    /***
     * 删除浏览记录
     * zz：pl
     * @param request
     * @param response
     * @param autoId
     * @return
     */
    @RequestMapping(value = "/DelRecord",produces="text/plain;charset=UTF-8")
    @ResponseBody
    public String DelRecord(HttpServletRequest request,HttpServletResponse response,String autoId)
    {
        Long userId = (Long) request.getSession().getAttribute("customerId");
        Customer customer = (Customer) request.getSession().getAttribute("cust");
        QpCustomerAutoStyleSearchBean qpCustomerAutoStyleSearchBean=new QpCustomerAutoStyleSearchBean();
        if(userId!=null&&customer!=null) {
            Map<String,Object>map=new HashMap<>();
            map.put("userId",userId);
            map.put("autoId",autoId);
            qpCustomerAutoStyleSearchBean = qpCustomerAutoStyleSearchService.findRecordByUserIdAndAutoId(map);
            if (qpCustomerAutoStyleSearchBean!=null)
            {
                qpCustomerAutoStyleSearchBean.setCustomerAutoStyleSearchDelflag("1");
                int temp = qpCustomerAutoStyleSearchService.updateRecordByIsDel(qpCustomerAutoStyleSearchBean);
                if(temp==0)
                {
                    return "err";
                }
            }
            
        }
        Cookie[] cookies = request.getCookies();
        if(null!=cookies){
            for(Cookie cookie : cookies){
                if(cookie.getValue().equals(autoId)){
                    Cookie cookie2 = new Cookie(autoId,autoId);
                    cookie2.setPath("/");
                    cookie2.setMaxAge(0);
                    response.addCookie(cookie2);
                }
            }
        }
        request.getSession().setAttribute("selectVal",null);
        return "ok";
    }

    @RequestMapping(value = "/TestRecord",produces="text/plain;charset=UTF-8")
    @ResponseBody
    public String test()
    {
        Map<String,Object>map=new HashMap<>();
        map.put("userId",11);
        map.put("autoId","12");
        QpCustomerAutoStyleSearchBean qpCustomerAutoStyleSearchBean = qpCustomerAutoStyleSearchService.findRecordByUserIdAndAutoId(map);

        //List<QpCustomerAutoStyleSearchBean> list=qpCustomerAutoStyleSearchService.findListByUserID(11);

        return JSON.toJSONString(qpCustomerAutoStyleSearchBean);
    }

    @RequestMapping(value = "/getSeeeionVal",produces="text/plain;charset=UTF-8")
    @ResponseBody
    public String getSeeeionVal(HttpServletRequest request)
    {
        String val="请先选车型";
        Object selectVal = request.getSession().getAttribute("selectVal");
        Object carstyleID = request.getSession().getAttribute("carstyleID");
        if(selectVal!=null){
            val= (String) selectVal;
        }
        return val+"&"+carstyleID;
    }

    @RequestMapping(value = "/addSeeeionVal",produces="text/plain;charset=UTF-8")
    @ResponseBody
    public void addSeeeionVal(HttpServletRequest request,String val,String carstyleID)
    {
        //String val="请先选车型";
        request.getSession().setAttribute("selectVal", val);
        request.getSession().setAttribute("carstyleID", carstyleID);
    }


}
