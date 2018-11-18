package com.ningpai.common.util;

import com.ningpai.fastdfs.bean.FastDFSInfo;
import com.ningpai.fastdfs.service.FastDFSInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登陆验证拦截器
 * 
 * @author qiyuanyuan
 *
 */
@Controller
public class SystemInterceptor extends HandlerInterceptorAdapter {

    private static final String BASEURL = "baseUrl";

    /**
     * SERVICE-FastDFS设置信息
     */
    private static FastDFSInfoService fastDFSService;

    /**
     * 获取FastDFS设置信息
     * 
     * @return
     */
    public FastDFSInfoService getFastDFSService() {
        return fastDFSService;
    }

    /**
     * 设置FastDFS设置信息
     * 
     * @param fastDFSService
     */
    @Resource(name = "FastDFSInfoService")
    public void setFastDFSService(FastDFSInfoService fastDFSService) {
        SystemInterceptor.fastDFSService = fastDFSService;
    }

    /**
     * 在请求处理前拦截URL 进行相应处理
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取uri
        String uri1 = request.getServletPath();
        if (uri1.indexOf("uploadgallery") == -1) {
            // 获取基本地址
            String baseUrl = (String) request.getSession().getAttribute(BASEURL);
            if (baseUrl == null || "".equals(baseUrl)) {
                // 获取当前使用的FastDFS设置
                FastDFSInfo fastdfs = fastDFSService.getFastDFSInfoByCurr();
                if (fastdfs != null) {
                    // 存放fastdfs保存文件的调用路径
                    request.getSession().setAttribute(BASEURL, fastdfs.getResultPath());
                } else {
                    // 置空baseUrl
                    request.getSession().setAttribute(BASEURL, "");
                }
            }
        }
        String[] noFilterUrls = new String[] { "/togroupcreate.htm",// 创建小组
                "/togrouptype.htm",// 创建公开小组
                "/togrouptype.htm",// 创建私密小组
                "/toaddgroupimg.htm",// 上传照片
                "/topubtopic.htm",// 发表话题
                "/groupinfo.htm",// 小组资料
                "/grouphead.htm",// 小组头像
                "/background.htm",// 小组背景
                "/accesslimit.htm",// 访问权限
                "/deletelimit.htm",// 删除权限
                "/managerlimit.htm",// 管理员权限
                "/groupaddmanager.htm",// 添加管理员
                "/dissolvedgroup.htm",// 解散小组
                "/groupmember.htm",// 小组成员
                "/blacklist.htm",// 黑名单
                "/noresponsetopic.htm",// 禁止回应的话题
                "/topicrecycle.htm",// 回收站
                "/invitefriends.htm",// 邀请好友
                "/applytopicdetail.htm",// 处理话题恢复申请
                "/mycustomerhomepage.htm",// 个人主页

        };

        String uri = request.getServletPath();
        boolean beFilter = false;
        boolean isDecorateFilter = false;
        // 查看是否拦截
        for (String s : noFilterUrls) {
            if (uri.indexOf(s) != -1) {
                beFilter = true;
                break;
            }
        }
        // 获取path
        String path = request.getContextPath();
        // 获取session
        HttpSession session = request.getSession();
        // 如果要拦截
        if (beFilter && uri1.indexOf("uploadgallery") == -1) {
            // Customer customer = (Customer) session.getAttribute("/customer");
            // 获取用户id
            Long customerId = (Long) session.getAttribute("customerId");
            // 获取装修公司id
            Long decorateId = (Long) session.getAttribute("decorateId");
            // 如果用户id为空
            if (customerId == null) {
                response.sendRedirect(path + "/login.html"); // 返回提示页面
                return false;
                // 如果是装修公司中心 并且未登录 跳转到首页
            } else if (isDecorateFilter && decorateId == null) {
                response.sendRedirect(path + "/index.html"); // 返回提示页面
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

}
