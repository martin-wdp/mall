package com.ningpai.m.selectcartype;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by pl on 2015/12/14.
 * Desc:
 */
@Controller
public class Selectcartype {
    /***
     * 适配车型首页
     * @return
     */
    @RequestMapping(value = "/selectcarindex",produces="text/plain;charset=UTF-8")
    public ModelAndView index(){
        ModelAndView mod=new ModelAndView();
        mod.setViewName("selectcartype/motorcycleType");
        return mod;
    }

    /***
     * 保存浏览记录
     */
    @RequestMapping(value = "/saveRecode",produces="text/plain;charset=UTF-8")
    @ResponseBody
    public void saveRecode(String selectVal,HttpServletRequest request){
        request.getSession().setAttribute("selectVal",selectVal);
    }
    /***
     * 保存浏览记录
     */
    @RequestMapping(value = "/delRecode",produces="text/plain;charset=UTF-8")
    @ResponseBody
    public void delRecode(String selectVal,HttpServletRequest request){
        request.getSession().setAttribute("selectVal",null);
    }
}
