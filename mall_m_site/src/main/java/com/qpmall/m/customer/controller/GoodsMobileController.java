package com.qpmall.m.customer.controller;

import com.ningpai.comment.bean.Comment;
import com.ningpai.comment.bean.Share;
import com.ningpai.comment.service.CommentServiceMapper;
import com.ningpai.comment.service.ShareService;
import com.ningpai.customer.service.CustomerServiceMapper;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.m.common.service.SeoService;
import com.ningpai.m.customer.service.CustomerOrderService;
import com.ningpai.m.customer.vo.OrderInfoBean;
import com.ningpai.m.util.LoginUtil;
import com.ningpai.other.bean.CustomerAllInfo;
import com.ningpai.other.util.CustomerConstantStr;
import com.ningpai.util.PageBean;
import com.ningpai.util.UploadUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by ly-qpmall on 2016/1/23.
 */
@Controller
public class GoodsMobileController {

    @Resource(name = "commentServiceMapper")
    private CommentServiceMapper commentServiceMapper;

    @Resource(name = "SeoService")
    private SeoService seoService;


    @Resource(name = "customerServiceMapper")
    private CustomerServiceMapper customerServiceMapper;


    /**
     * 晒单Service
     */
    @Resource(name = "shareServiceNew")
    private ShareService shareServiceMapper;
    @Resource(name = "customerOrderServiceM")
    private CustomerOrderService customerOrderService;




    /**
     * 根据货品编号查询商品的评论
     *
     * @return 分页对象
     */
    @RequestMapping("/queryProducCommentForDetailM")
    public PageBean queryProducCommentForDetail(PageBean pb, Long productId, String title, Character type, String askType) {
        if (null != askType && !"".equals(askType)) {
            return this.commentServiceMapper.selectCommByGoodsId(pb, productId, type, title, askType);
        } else {
            return this.commentServiceMapper.selectCommByGoodsId(pb, productId, type, title);
        }
    }
    /**
     *  跳转货品的评价提交页
     *
     * @return 分页对象
     */
    @RequestMapping("/toInitComment")
    public ModelAndView toInitComment(HttpServletRequest request,Long productId,
                                      Long orderId,Double goodsPrice,Long orderGoodsId,
                                      String goodsName,String orderNo,String goodsImg
                                        ,String goodsNum,Long commentId) {
        if (!LoginUtil.checkLoginStatus(request)) {
            return seoService.getCurrSeo(new ModelAndView("redirect:/login.html?url=customer/index.html?tag=4"));
        }
        ModelAndView mav = new ModelAndView("customer/goodsEvaluate");
        mav.addObject("productId",productId);
        mav.addObject("orderId",orderId);
        mav.addObject("goodsPrice",goodsPrice);
        mav.addObject("goodsName",goodsName);
        mav.addObject("orderNo",orderNo);
        mav.addObject("goodsImg",goodsImg);
        mav.addObject("goodsNum",goodsNum);
        mav.addObject("orderGoodsId",orderGoodsId);
        if(commentId !=null){
            mav.addObject("comment",commentServiceMapper.selectByCommentId(commentId));
        }
       return seoService.getCurrSeo(mav);
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
    public ModelAndView saveShare( Share share, Comment comment,Long orderId, Long orderGoodsId, String imageNames, HttpServletRequest request) {
        ModelAndView mav = seoService.getCurrSeo(new ModelAndView("redirect:/comment-"+orderId+".html"));
        if (LoginUtil.checkLoginStatus(request)) {
            // 当前登录会员id
            Long customerId = (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);
            // 当前登录成功的会员信息
            CustomerAllInfo customer = customerServiceMapper.selectByPrimaryKey(customerId);
            // 会员编号
            comment.setCustomerId(customerId);
            // 会员昵称
            comment.setCustomerNickname(customer.getCustomerNickname());
            // 判断是否是添加评论
            if (comment.getCommentScore() != null&&comment.getCommentId()==null) {
                // 判断是订单商品是否评论
                if (!commentServiceMapper.checkNewCommGoodIsUser(orderGoodsId, customerId, "1")) {
                    return mav;
                }
                // 添加评论
                //int commentId = commentServiceMapper.addGoodsComment(comment);
               customerOrderService.addGoodsComment(request, comment, orderId);;
            }
            if (!"".equals(imageNames)) {
                // 判断是订单商品是否晒单
                if (!commentServiceMapper.checkNewCommGoodIsUser(orderGoodsId, customerId, "2")) {
                    return mav;
                }
                // 晒单内容
                share.setShareContent(comment.getCommentContent());
                // 添加晒单
                shareServiceMapper.saveShare(comment.getGoodsId(), share, customerId, orderGoodsId, imageNames);
            }
            return mav;
        } else {
            return seoService.getCurrSeo(new ModelAndView("redirect:/login.html?url=customer/index.html?tag=4"));
        }

    }

    /**
     * 晒单时，上传图片文件
     *
     * @param request
     * @param resp
     * @param
     *
     * @throws java.io.IOException
     */
    @RequestMapping("shareUploadM")
    public void uploadShareImg(MultipartHttpServletRequest request, HttpServletResponse resp, String id) throws IOException {
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
            msg = UploadUtil.uploadFileCustomerHeadOne(request.getFile("shareFile"), request) + "," + id;
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
}
