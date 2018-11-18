package com.ningpai.site.customer.controller;

import com.ningpai.comment.bean.Comment;
import com.ningpai.comment.bean.Share;
import com.ningpai.comment.bean.ShareReply;
import com.ningpai.comment.service.ShareService;
import com.ningpai.customer.service.CustomerServiceMapper;
import com.ningpai.index.service.TopAndBottomService;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.other.util.CustomerConstantStr;
import com.ningpai.other.util.LoginUtil;
import com.ningpai.site.customer.service.CustomerServiceInterface;
import com.ningpai.site.customer.service.GoodsCommentService;
import com.ningpai.site.customer.vo.CustomerAllInfo;
import com.ningpai.temp.service.MegawizardService;
import com.ningpai.temp.service.TempService;
import com.ningpai.util.MyLogger;
import com.ningpai.util.UploadUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
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
 * @since 2014年7月1日 上午9:59:36
 * @version 0.0.1
 */
@Controller
public class ShareController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(ShareController.class);

    private static final String ERRORINFO = "{error:-1}";

    /**
     * spring 注解 会员service
     */
    private CustomerServiceMapper customerServiceMapper;

    /**
     * 晒单Service
     */
    private ShareService shareServiceMapper;

    /**
     * 页面说明Service
     */
    private MegawizardService megawizardSerivce;

    /** 模板设置业务类 */
    private TempService tempService;

    /**
     * 获取头尾
     */
    private TopAndBottomService topAndBottomService;

    /**
     * spring 注解 会员service
     */
    private CustomerServiceInterface customerServiceInterface;

    /**
     * 评论
     */
    @Resource(name = "goodsCommentService")
    private GoodsCommentService goodsCommentService;

    /**
     * 跳转会员中心--晒单页
     *
     * @param orderId
     * @return
     */
    @RequestMapping("/toshare")
    public ModelAndView toShare(HttpServletRequest request, Long orderId) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        ModelAndView mav = null;
        if (LoginUtil.checkLoginStatus(request)) {
            // 根据订单编号查找订单信息
            resultMap.put("order", customerServiceInterface.queryOrderByCustIdAndOrderId(orderId, (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID)));
            // 根据页面类型和模板ID查询单个页面说明
            resultMap.put("explain", megawizardSerivce.selectByType(2, Long.parseLong(tempService.getCurrTemp().getTempId() + "")));
            mav = new ModelAndView("/customer/share");
            mav.addAllObjects(resultMap);
            return topAndBottomService.getTopAndBottom(mav);
        } else {
            return new ModelAndView(new RedirectView("login.html?url=customer/myorder.html"));
        }
    }

    /**
     * 保存晒单回复
     * 
     * @param request
     * @param reply
     * @return String ok 成功
     */
    @RequestMapping("/saveShareReply")
    @ResponseBody
    public String saveShareReply(HttpServletRequest request, ShareReply reply) {
        // 验证是否登录
        if (LoginUtil.checkLoginStatus(request)) {
            // 会员编号
            Long customerId = (Long) request.getSession().getAttribute("customerId");
            reply.setCustomerId(customerId);
            // 保存回复
            shareServiceMapper.saveShareReply(reply);
            return "ok";
        } else {
            return "timeout";
        }
    }

    /**
     * 保存晒单信息
     *
     * @param comment
     *            评论
     * @param orderGoodsId
     *            订单-商品Id
     * @param share
     *            晒单
     * @param imageNames
     *            晒单图片
     * @param request
     * @return
     */
    @RequestMapping("/saveShare")
    @ResponseBody
    public String saveShare(@Valid Share share, Comment comment, Long orderGoodsId, String imageNames, HttpServletRequest request) {
        if (LoginUtil.checkLoginStatus(request)) {
            // 当前登录会员id
            Long customerId = (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);
            // 当前登录成功的会员信息
            CustomerAllInfo customer = customerServiceInterface.selectByPrimaryKey(customerId);
            // 会员编号
            comment.setCustomerId(customerId);
            // 会员昵称
            comment.setCustomerNickname(customer.getCustomerNickname());
            // 判断是否是添加评论
            if (comment.getCommentScore() != null) {
                // 判断是订单商品是否评论
                if (!goodsCommentService.checkCommGoodIsUser(orderGoodsId, customerId, "1")) {
                    return ERRORINFO;
                }
                // 添加评论
                Long commentId = goodsCommentService.addGoodsComment(request, comment);
                if (commentId == null) {
                    return ERRORINFO;
                }
            }
            if (!"".equals(imageNames)) {
                // 判断是订单商品是否晒单
                if (!goodsCommentService.checkCommGoodIsUser(orderGoodsId, customerId, "2")) {
                    return ERRORINFO;
                }
                // 晒单内容
                share.setShareContent(comment.getCommentContent());
                // 添加晒单
                shareServiceMapper.saveShare(comment.getGoodsId(), share, customerId, orderGoodsId, imageNames);
                // 非空验证 需要保存的晒单信息标题
                if (null != share.getShareTitle()) {
                    OperaLogUtil.addOperaLog(request, customer.getCustomerUsername(), "保存晒单信息",
                            "保存晒单信息-->晒单标题【" + share.getShareTitle() + "】-->用户 名：" + customer.getCustomerUsername());
                    LOGGER.info("保存晒单信息成功！晒单标题【" + share.getShareTitle());
                }

            }
            return "ok";
        } else {
            return ERRORINFO;
        }

    }

    /**
     * 晒单时，上传图片文件
     *
     * @param request
     * @param resp
     * @param orderGoodsId
     *            订单-商品Id
     * @throws IOException
     */
    @RequestMapping("share/upload")
    public void uploadShareImg(MultipartHttpServletRequest request, HttpServletResponse resp, Long orderGoodsId) throws IOException {
        PrintWriter out = resp.getWriter();
        String msg = null;
        MultipartFile file = request.getFile("shareFile");
        file.getOriginalFilename();
        // 检查文件大小
        if (file.getSize() > CustomerConstantStr.FOUR * CustomerConstantStr.NUM1024 * CustomerConstantStr.NUM1024) {
            msg = "101";
        } else if (!checkExtendsName(file.getOriginalFilename())) {
            msg = "102";
        } else {
            msg = UploadUtil.uploadFileCustomerHeadOne(request.getFile("shareFile"), request) + "," + orderGoodsId;
        }
        out.append("<script>parent.callback('" + msg + "');</script>");
    }

    /**
     * 检查文件扩展名是否为图片
     *
     * @param fileName
     *            上传的文件的文件名
     * @return
     */
    private boolean checkExtendsName(String fileName) {
        // 非空验证 文件拓展名
        if (null != fileName) {
            LOGGER.info("检查文件扩展名【" + fileName + "】是否为图片");
        }
        if (fileName.indexOf(".") < 0) {
            return false;
        }
        String extend = fileName.substring(fileName.lastIndexOf(".") + 1);
        String[] extendNames = { "jpg", "jpeg", "bmp", "png", "gif" };
        for (String extendName : extendNames) {
            if (extend.equals(extendName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 查看晒单
     *
     * @param productId
     *            货品id
     * @param distinctId
     *            地区id
     * @param shareId
     *            晒单id
     */
    @RequestMapping("/share")
    public ModelAndView shareList(Long productId, Long distinctId, Long shareId) {
        Long emp = distinctId;
        if (null == distinctId) {
            emp = 1L;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("distinctId", emp);
        // 晒单详情
        map.put("share", shareServiceMapper.queryShareById(shareId));
        // 热门晒单
        map.put("topShare", shareServiceMapper.getTopShare(CustomerConstantStr.NUM24));
        return topAndBottomService.getTopAndBottom(new ModelAndView("goods/share").addObject("map", map));
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

    public MegawizardService getMegawizardSerivce() {
        return megawizardSerivce;
    }

    @Resource(name = "MegawizardService")
    public void setMegawizardSerivce(MegawizardService megawizardSerivce) {
        this.megawizardSerivce = megawizardSerivce;
    }

    public TempService getTempService() {
        return tempService;
    }

    @Resource(name = "TempService")
    public void setTempService(TempService tempService) {
        this.tempService = tempService;
    }

    public TopAndBottomService getTopAndBottomService() {
        return topAndBottomService;
    }

    @Resource(name = "TopAndBottomService")
    public void setTopAndBottomService(TopAndBottomService topAndBottomService) {
        this.topAndBottomService = topAndBottomService;
    }

    public CustomerServiceInterface getCustomerServiceInterface() {
        return customerServiceInterface;
    }

    /**
     * spring 注入属性
     * 
     * @param customerServiceInterface
     */
    @Resource(name = "customerServiceSite")
    public void setCustomerServiceInterface(CustomerServiceInterface customerServiceInterface) {
        this.customerServiceInterface = customerServiceInterface;
    }
}
