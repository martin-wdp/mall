package com.ningpai.system.controller;

import com.ningpai.common.safe.CSRFTokenManager;
import com.ningpai.system.bean.Receivables;
import com.ningpai.system.service.ReceivablesService;
import com.ningpai.system.util.ReceivablesUtil;
import com.ningpai.util.PageBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 收款单控制处理器
 * Created by houyichang on 2015/8/18.
 */
@Controller
public class ReceivablesController {

    @Resource(name = "receivablesService")
    private ReceivablesService receivablesService;

    /**
     * 分页查询收款单全部信息
     *后台收款单列表处调用
     *
     * */
    @RequestMapping("/receivablesList")
    public ModelAndView receivablesList(HttpServletRequest request,PageBean pageBean,String searchText,String condition,String status){
        String statusNew = status;
        String conditionNew = condition;
        if(statusNew == null ){
            statusNew = "";
        }
        if(conditionNew == null){
            conditionNew = "1";
        }
        pageBean.setUrl(ReceivablesUtil.INITRECEIVABLES);

        String token = request.getSession().getAttribute(CSRFTokenManager.CSRF_TOKEN_FOR_SESSION_ATTR_NAME).toString();

        return new ModelAndView(ReceivablesUtil.RECEIVABLESLIST,"pageBean",receivablesService.searchReceivables(pageBean, searchText, conditionNew,statusNew)).addObject("token",token).addObject("status",statusNew).addObject("searchText",searchText).addObject("condition",conditionNew);
    }

    /**
     * 查看收款单详情
     * 后台收款单列表查看详情处调用
     *
     * @author houyichang 2015/8/19
     *
     * */
    @RequestMapping("/receivablesDetail")
    @ResponseBody
    public Receivables queryDetail(HttpServletRequest request,Long cashRegisterId){
        return this.receivablesService.queryReceivablesDetail(cashRegisterId);
    }

  }
