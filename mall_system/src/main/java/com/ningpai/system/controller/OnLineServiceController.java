package com.ningpai.system.controller;

import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.system.bean.OnLineService;
import com.ningpai.system.bean.ShopKuaiShang;
import com.ningpai.system.service.IOnLineServiceBiz;
import com.ningpai.system.service.ShopKuaiShangService;
import com.ningpai.util.JsonUtil;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 在线客服Controller
 *
 * @author NINGPAI_xiaomm
 * @version V1.0
 * @Description:
 * @since 2014-03-27 17:40:56
 */
@Controller("onLineServiceController")
public class OnLineServiceController extends BaseController {

    /**
     * 在线客服业务类
     */
    @Resource(name = "onLineServiceBizImpl")
    private IOnLineServiceBiz onLineServiceBizImpl;
    /**
     * spring注解
     */
    @Resource(name = "ShopKuaiShangService")
    private ShopKuaiShangService shopKuaiShangService;

    /**
     * 添加在线客服JSP页面
     */
    private static final String ADD_ONLINESERVICE_JSP = "jsp/system/online/onlineservice_add";
    /**
     * 对象删除标识
     */
    private static final String DELETESTATUS = "deleteStatus";
    /**
     * 常量MSG
     */
    private static final String MSG = "msg";
    /**
     * 常量LOGINUSERID
     */
    private static final String LOGINUSERID = "loginUserId";
    /**
     * 常量ONLINESERVICE
     */
    private static final String ONLINESERVICE = "onLineService";

    /**
     * 用户名称
     */
    public static final String NAME = "name";

    /**
     * "operaPath"
     */
    public static final String OPERAPATH = "operaPath";

    /**
     * 记录日志对象
     */
    private static final MyLogger LOGGER = new MyLogger(OnLineServiceController.class);

    /**
     * private static final String ALL_NUMBERS = "0123456789";
     */
    /**
     * 保存在线客服
     *
     * @param onLineService
     *            货币设置对象
     * @param request
     *            请求对象
     * @return 视图对象
     */
    @RequestMapping("/addOnLineService")
    public ModelAndView addOnLineService(@Valid final OnLineService onLineService, BindingResult bindingResult, final HttpServletRequest request, final HttpServletResponse response)
            throws UnsupportedEncodingException {
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView("readOnLineService.htm?deleteStatus=0"));
        }
        ModelAndView mav = new ModelAndView();
        String msg = "";
        try {
            // 如果在线服务id为0，则保存在线服务
            if (onLineService.getOnLineServiceId() == 0) {
                onLineService.setInsertDate(new Date());
                onLineService.setInsertId(((Long) request.getSession().getAttribute(LOGINUSERID)).intValue());
                onLineService.setDeleteStatus(0);
                // 添加在线客服
                int flag = onLineServiceBizImpl.saveOnLineService(onLineService);
                if (flag == 0) {
                    mav.addObject(MSG, "保存在线客服失败！");
                }
                // 更新
            } else {
                onLineService.setModifyDate(new Date());
                onLineService.setModifyId(((Long) request.getSession().getAttribute(LOGINUSERID)).intValue());
                // 修改在线客服
                if (onLineServiceBizImpl.updateOnLineService(onLineService) == 0) {
                    mav.addObject(MSG, "更新在线客服失败！");
                } else {
                    String customerName = (String) request.getSession().getAttribute(NAME);
                    OperaLogUtil.addOperaLog(request, customerName, "修改在线客服", request.getSession().getAttribute(OPERAPATH) + ",用户名:" + customerName);
                }
            }

        } catch (Exception e) {
            LOGGER.error("保存在线客服错误：=>",e);
            mav.addObject(MSG, "保存在线客服失败！");
        }
        // 返回在线客服列表
        mav.addObject(MSG, msg);
        mav.setView(new RedirectView("readOnLineService.htm?deleteStatus=0"));
        return mav;
    }

    /**
     * 查看在线客服
     *
     * @param deleteStatus
     *            对象删除标识
     * @return 视图对象
     */
    @RequestMapping("/readOnLineService")
    public ModelAndView readOnLineService(@RequestParam(value = DELETESTATUS, required = false, defaultValue = "0") final Integer deleteStatus, final HttpServletRequest request) {

        ModelAndView mav = new ModelAndView();
        mav.addObject(DELETESTATUS, deleteStatus);
        // 判断查询在线客服id是否为0
        mav.setViewName(ADD_ONLINESERVICE_JSP);
        // 查询当前系统中默认的在线客服配置
        Map<String, Object> pa = new HashMap<String, Object>(2);
        pa.put(DELETESTATUS, deleteStatus);
        PageBean pg = new PageBean();
        pg.setPageSize(1);
        List<Object> list = ((PageBean) onLineServiceBizImpl.getOnLineServiceByField(pa, pg)).getList();
        mav.addObject(ONLINESERVICE, list != null && !list.isEmpty() ? list.get(0) : new OnLineService());
        mav.addObject(MSG, request.getAttribute(MSG));
        mav.addObject(MSG, request.getParameter(MSG));
        return mav;
    }

    /**
     * 快商通
     *
     * @return
     */
    @RequestMapping("/shopkuaishang")
    public ModelAndView shopkuaishang() {

        return new ModelAndView("jsp/system/kuaishang/kuaishang_list", "list", shopKuaiShangService.selectInitone());
    }

    /**
     * 打开,关闭快商通.
     *
     * @param shopKuaiShang
     * @return
     */
    @RequestMapping("/updateKuaiShangByPrimaryKey")
    public ModelAndView updateKuaiShangByPrimaryKey(ShopKuaiShang shopKuaiShang) {
        shopKuaiShangService.updateKuaiShangByPrimaryKey(shopKuaiShang);
        return new ModelAndView(new RedirectView("shopkuaishang.htm"));
    }

    /**
     * 申请快商通
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/applyKuaiShang")
    public String applyKuaiShang(ShopKuaiShang shopKuaiShang) {
        String url = null;
        url = "http://shop.kuaishang.cn/api/thirdregist.php?";
        String returnString = "";
        PostMethod postMethod = new PostMethod(url);
        // 设置post请求头的编码格式
        postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        // 传递数据
        NameValuePair[] param = { new NameValuePair("shangId", shopKuaiShang.getShangId().toString()), new NameValuePair("username", shopKuaiShang.getShangLoginName()),
                new NameValuePair("password", shopKuaiShang.getPassword()), new NameValuePair("company", shopKuaiShang.getCompanyName()),
                new NameValuePair("weburl", shopKuaiShang.getCompanyUrl()), new NameValuePair("industryCategory", shopKuaiShang.getTrade()),
                new NameValuePair("linkman", shopKuaiShang.getLinkman()), new NameValuePair("phone", shopKuaiShang.getTelephone()),
                new NameValuePair("email", shopKuaiShang.getEmail()), new NameValuePair("mobile", shopKuaiShang.getMobilephone()), new NameValuePair("keytype", "qianmi"),
                new NameValuePair("keynumber", shopKuaiShang.getShangLoginName()) };
        postMethod.setRequestBody(param);
        HttpClient client = new HttpClient();

        try {
            client.executeMethod(postMethod);
            returnString = postMethod.getResponseBodyAsString();
            // 处理带有bom头的文本
            if (returnString.startsWith("\uFEFF")) {
                returnString = returnString.substring(1);
            }
            // 处理接口返回值得到合格的json数据
            returnString = returnString.replaceAll("'", "\\\\'");
        } catch (IOException e) {
            LOGGER.error("申请快商通失败：", e);
        }

        Map<String, Object> result = JsonUtil.getMapFromJson(returnString);
        // 注册成功
        if ((Boolean) result.get("success")) {
            shopKuaiShang.setShangLongId(result.get("compId").toString());
            shopKuaiShang.setOperation(result.get("code").toString());
            shopKuaiShangService.updateKuaiShangByPrimaryKey(shopKuaiShang);
            return "1";
        } else {
            return result.get("msg").toString();
        }

    }
}
