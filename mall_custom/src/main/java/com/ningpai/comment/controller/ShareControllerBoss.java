package com.ningpai.comment.controller;

import com.ningpai.comment.bean.Share;
import com.ningpai.comment.bean.ShareReply;
import com.ningpai.comment.bean.ValueUtil;
import com.ningpai.comment.service.ShareService;
import com.ningpai.comment.vo.ShareVo;
import com.ningpai.customer.service.CustomerServiceMapper;
import com.ningpai.logcore.util.OperaLogUtil;
import com.ningpai.other.util.CustomerConstantStr;
import com.ningpai.system.service.BasicSetService;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import com.ningpai.util.UtilDate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 晒单Controller
 *
 * @author NINGPAI-zhangqiang
 * @version 0.0.1
 * @since 2014年7月1日 上午9:59:36
 */
@Controller
public class ShareControllerBoss {

    /**
     * 记录日志对象
     */
    private static final MyLogger LOGGER = new MyLogger(ShareControllerBoss.class);

    private static final String INFO = "】商品的晒单信息！";

    // spring 注解 会员service
    private CustomerServiceMapper customerServiceMapper;

    // 站点信息
    @Resource(name = "basicSetService")
    private BasicSetService basicSetService;

    // 晒单Service
    private ShareService shareServiceMapper;

    /**
     * @param pageBean
     * @param shareVo
     * @return
     */
    @RequestMapping("/initsharelist")
    public ModelAndView initShareList(PageBean pageBean, @Valid ShareVo shareVo, String[] attr, String create, String createT) {
        // 设置访问路径
        pageBean.setUrl("initsharelist.htm");
        // 结果集
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            shareVo.setCreateTime(create == null || "".equals(create) ? null : UtilDate.stringToDateMM(create));
            shareVo.setCreateTimeTo(createT == null || "".equals(createT) ? null : UtilDate.stringToDateMM(createT));
            resultMap.put("attr", attr);
            resultMap.put(CustomerConstantStr.SHARE, shareVo);
            resultMap.put(CustomerConstantStr.PAGEBEAN, shareServiceMapper.selectAllShareByShare(pageBean, shareVo));
            return new ModelAndView(CustomerConstantStr.SHARELIST).addAllObjects(resultMap);
        } finally {
            resultMap = null;
        }

    }

    /**
     * 查询晒单详情
     *
     * @param shareId
     *            晒单编号
     * @return
     */
    @RequestMapping("/sharedetailboss")
    public ModelAndView showShareDetail(Long shareId) {
        // 非空验证 晒单ID
        if (null != shareId) {
            // 获取晒单对象
            ShareVo shareVo = shareServiceMapper.selectShareDetail(shareId);
            // 非空验证 晒单对应的商品名称
            if (null != shareVo.getGoodsName()) {
                LOGGER.info("查询【" + shareVo.getGoodsName() + INFO);
                return new ModelAndView(CustomerConstantStr.SHAREDETAIL).addObject(CustomerConstantStr.SHARE, shareVo);
            }

        }
        return null;

    }

    /**
     * ajax查询晒单详情
     *
     * @param shareId
     *            晒单编号
     * @return
     */
    @RequestMapping("/sharedetailAjax")
    @ResponseBody
    public ShareVo sharedetailAjax(Long shareId) {
        ShareVo shareVo = null;
        // 非空验证 晒单ID
        if (null != shareId) {

            // 获取晒单对象
            shareVo = shareServiceMapper.selectShareDetail(shareId);
            // 非空验证 晒单对应的商品名称
            if (null != shareVo.getGoodsName()) {
                LOGGER.info("查询【" + shareVo.getGoodsName() + INFO);
            }
        }
        return shareVo;
    }

    /**
     * 新版boss ajax查询晒单详情
     *
     * @param shareId
     *            晒单编号
     * @return
     */
    @RequestMapping("/sharedetailAjaxNew")
    @ResponseBody
    public Map<String, Object> sharedetailAjaxNew(Long shareId) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        ShareVo shareVo = null;
        try {
            // 获取晒单对象
            shareVo = shareServiceMapper.selectShareDetail(shareId);
            resultMap.put("shareVo", shareVo);
            // 获取晒单回复对象
            resultMap.put("shareReply", shareServiceMapper.queryShareReplyByShareId(shareId));
            // 查询站点信息
            resultMap.put("bs", basicSetService.findBasicSet());
            return resultMap;
        } finally {
            resultMap = null;
        }
    }

    /**
     * 修改评论
     *
     * @param request
     * @return
     */
    @RequestMapping("/updatesharerep")
    @ResponseBody
    public int updateShareRep(HttpServletRequest request, @Valid ShareReply reply) {
        return shareServiceMapper.updateShareRep(reply);
       /* return new ModelAndView(new RedirectView("queryByCommentId.htm?shareId=" + reply.getShareId() + "&CSRFToken=" + request.getSession().getAttribute("token")));*/
    }

    /**
     * 修改晒单详情
     *
     * @param share
     *            晒单编号
     * @return
     */
    @RequestMapping("/updateshare")
    @ResponseBody
    public int updateShare(@Valid Share share) {
        return  shareServiceMapper.updateShare(share);
        // 非空验证 晒单ID
        /*if (null != share.getShareId()) {
            // 获取晒单对象
            ShareVo shareVo = shareServiceMapper.selectShareDetail(share.getShareId());
            // 非空验证 晒单对应的商品名称
            if (null != shareVo.getGoodsName()) {
                LOGGER.info("修改【" + shareVo.getGoodsName() + INFO);
            }

        }
        shareServiceMapper.updateShare(share);
        return new ModelAndView(CustomerConstantStr.SHAREDETAIL).addObject(CustomerConstantStr.SHARE, shareServiceMapper.selectShareDetail(share.getShareId()));*/
    }

    /**
     * 批量删除晒单
     *
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping("/deleteshare")
    public ModelAndView deleteShare(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter pr = null;
        try {
            pr = response.getWriter();
            String[] shareIds = request.getParameterValues("parameterIds[]");
            if (shareIds == null) {
                shareIds = request.getParameterValues("parameterIds");
            }
            // 输出结果
            pr.print(shareServiceMapper.deleteShare(shareIds));
            // 操作日志
            OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), "批量删除晒单", (String) request.getSession().getAttribute(ValueUtil.OPERAPATH)
                    + "-->批量删除晒单,用户名：" + (String) request.getSession().getAttribute(ValueUtil.NAME));
        } finally {
            if (pr != null) {
                pr = null;
            }
        }
        return null;
    }

    /**
     * 批量显示到首页
     *
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping("/updatesharetoindex")
    public ModelAndView updateShareToIndex(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter pr = null;
        try {
            pr = response.getWriter();
            String[] shareIds = request.getParameterValues("parameterIds[]");
            if (shareIds == null) {
                shareIds = request.getParameterValues("parameterIds");
            }
            // 输出结果
            pr.print(shareServiceMapper.updateShareToIndex(shareIds));
            // 操作日志
            OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), "批量显示到首页", (String) request.getSession()
                    .getAttribute(ValueUtil.OPERAPATH) + "-->批量显示到首页,用户名：" + (String) request.getSession().getAttribute(ValueUtil.NAME));
        } finally {
            if (pr != null) {
                pr = null;
            }
        }
        return null;
    }

    /**
     * 显示到首页
     *
     * @return
     */
    @RequestMapping("/updatesharetoindexone")
    @ResponseBody
    public int updateShareToIndex(@Valid Share share) {
      return  shareServiceMapper.updateShareToIndexOne(share);
        /*// 非空验证 晒单ID
        if (null != share.getShareId()) {
            // 获取晒单对象
            ShareVo shareVo = shareServiceMapper.selectShareDetail(share.getShareId());
            // 非空验证 晒单对应的商品名称
            if (null != shareVo.getGoodsName()) {
                LOGGER.info("删除【" + shareVo.getGoodsName() + INFO);
                return new ModelAndView(CustomerConstantStr.SHAREDETAIL).addObject(CustomerConstantStr.SHARE, shareVo);
            }

        }
        return new ModelAndView(CustomerConstantStr.SHAREDETAIL).addObject(CustomerConstantStr.SHARE, shareServiceMapper.selectShareDetail(share.getShareId()));*/
    }

    /**
     * 删除晒单
     *
     * @param count
     *            晒单个数
     * @param count
     * @return
     * @throws IOException
     */
    @RequestMapping("/checkindexsharecount")
    @ResponseBody
    public int checkIndexShareCount(Long count) {
        // 判断首页展示的晒单条数是否超过限制
        if (shareServiceMapper.checkIndexShareCount(count)) {
            return 1;
        } else {
            return 0;
        }
    }

    public CustomerServiceMapper getCustomerServiceMapper() {
        return customerServiceMapper;
    }

    @Resource(name = "customerServiceMapper")
    public void setCustomerServiceMapper(CustomerServiceMapper customerServiceMapper) {
        this.customerServiceMapper = customerServiceMapper;
    }

    public ShareService getShareServiceMapper() {
        return shareServiceMapper;
    }

    @Resource(name = "shareServiceNew")
    public void setShareServiceMapper(ShareService shareServiceMapper) {
        this.shareServiceMapper = shareServiceMapper;
    }

    /**
     * 添加评论回复
     *
     * @param request
     * @param replay
     * @return
     */
    @RequestMapping("/addShareReplay")
    public ModelAndView addShareReplay(HttpServletRequest request, HttpServletResponse response, @Valid ShareReply replay) throws IOException {
        // 设置IP
        // replay.setReplayIp(IPAddress.getIpAddr(request));
        // String rep=new
        // String(replay.getReplyContent().getBytes("ISO-8859-1"),"UTF-8");
        // replay.setReplyContent(rep);
        replay.setCustomerId(null);
        // 插入评论回复
         shareServiceMapper.saveShareReply(replay);
        // 新后台才用到的代码，返回新插入的回复id
        try {
            PrintWriter pr = null;
            pr = response.getWriter();

            // 输出结果shareReplyId
            pr.print(replay.getShareReplyId());
            return null;
        } catch (IOException e) {
            LOGGER.error("",e);
        }
        return new ModelAndView(new RedirectView("queryByCommentId.htm?shareId=" + replay.getShareId() + "&CSRFToken=" + request.getSession().getAttribute("token")));
    }

}
