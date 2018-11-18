package com.ningpai.system.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.system.bean.Empower;
import com.ningpai.system.bean.EmpowerLog;
import com.ningpai.system.util.ConstantStr;
import com.ningpai.util.PageBean;
import org.springframework.stereotype.Controller;

import com.ningpai.system.service.EmpowerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 外部接口权限查询Controller
 * 
 * @author xiaogu
 * 
 */
@Controller
public class EmpowerController {
    /** 权限信息服务层接口 */
    @Resource(name = "EmpowerService")
    private EmpowerService empowerService;
    /**
     * 查询授权
     * 
     * @return
     */
    @RequestMapping("/selectemp")
    public ModelAndView selectEmp(PageBean pageBean) {
       return new ModelAndView(ConstantStr.EMPOWERLIST).addObject("pageBean", empowerService.list(pageBean));
    }

    /**
     * 添加外部接口角色
     * @param empower 参数
     * @param sTime 开始时间
     * @param eTime 结束时间
     * @param request 请求
     * @return 列表页
     */
    @RequestMapping("insertempoer")
    public ModelAndView insertEmpoer(Empower empower,String sTime,String eTime,HttpServletRequest request){
        //时间转换
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 权限
        try {
            //设置开始时间
            empower.setBeginTime((formatDate.parse(sTime)));
            //设置结束时间
            empower.setEndTime(formatDate.parse(eTime));
        } catch (ParseException e) {
            // 日志
            String operaCode = "外部接口";
            String operaContent = request.getSession().getAttribute(ConstantStr.OPERAPATH) + "添加外部接口角色时间转换错误";
            //记录日志
            OperaLogUtil.addOperaLog(request, request.getSession().getAttribute("name").toString(), operaCode, operaContent);
        }
        //添加信息
        empowerService.insertEmpower(empower);
        //返回到页面
        return new ModelAndView(new RedirectView(ConstantStr.SELECTEMP));
    }

    /**
     * 开启/关闭对外开放接口角色
     * @param status 0：开启 1：关闭
     * @param appId 主键id
     * @return
     */
    @RequestMapping("updateempower")
    public ModelAndView updateEmpower(String status,Long appId){
        //添加方法
        empowerService.updateEmpower(status, appId);
        return new ModelAndView(
                new RedirectView(ConstantStr.SELECTEMP));
    }


    /**
     * 删除角色
     * @param appId 主键id
     * @return 删除结果
     */
    @RequestMapping("delempower")
    public ModelAndView delEmpower(Long appId){
        //删除方法
        empowerService.delEmpower(appId);
        return new ModelAndView(
                new RedirectView(ConstantStr.SELECTEMP));
    }


    /**
     * 验证名字是否存在
     * @param appUserName 用户名
     * @return 1：存在 0：不存在
     */
    @RequestMapping("checkusername")
    @ResponseBody
    public int checkUserName(String appUserName){
        return empowerService.checkUserName(appUserName);
    }

    /**
     * 查询日志
     * @param empowerId 根据用户查询id
     * @return 日志列表
     */
    @RequestMapping("selectlog")
    @ResponseBody
    public List<EmpowerLog>  selectLog(Long empowerId){
        return empowerService.selectLog(empowerId);
    }
}
