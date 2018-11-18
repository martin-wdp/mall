package com.ningpai.login.qq;

import com.ningpai.util.MyLogger;
import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.PageFans;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.PageFansBean;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.javabeans.weibo.Company;
import com.qq.connect.oauth.Oauth;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Date: 12-12-4 Time: 下午4:36
 */
public class AfterLoginRedirectServlet extends HttpServlet {

    private static final long serialVersionUID = -230787606167439597L;

    private static final String PEX_HTM1 = "<br/>";
    private static final String PEX_HTM2 = "<image src=";
    private static final String PEX_HTM3 = "/><br/>";
    private static final String LOGGERINFO1 = "很抱歉，我们没能正确获取到您的信息，原因是： ";

    /**
     * 日志
     * */
    public static final MyLogger LOGGER = new MyLogger(AfterLoginRedirectServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=utf-8");

        PrintWriter out = response.getWriter();

        try {
            AccessToken accessTokenObj = (new Oauth()).getAccessTokenByRequest(request);

            String accessToken = null, openID = null;
            long tokenExpireIn = 0L;

            if (!"".equals(accessTokenObj.getAccessToken())) {
                accessToken = accessTokenObj.getAccessToken();
                tokenExpireIn = accessTokenObj.getExpireIn();

                request.getSession().setAttribute("demo_access_token", accessToken);
                request.getSession().setAttribute("demo_token_expirein", String.valueOf(tokenExpireIn));

                // 利用获取到的accessToken 去获取当前用的openid -------- start
                OpenID openIDObj = new OpenID(accessToken);
                openID = openIDObj.getUserOpenID();

                out.println("欢迎你，代号为 " + openID + " 的用户!");
                request.getSession().setAttribute("demo_openid", openID);
                out.println("<a href=" + "/shuoshuoDemo.html" + " target=\"_blank\">去看看发表说说的demo吧</a>");
                // 利用获取到的accessToken 去获取当前用户的openid --------- end

                out.println("<p> start -----------------------------------利用获取到的accessToken,openid 去获取用户在Qzone的昵称等信息 ---------------------------- start </p>");
                UserInfo qzoneUserInfo = new UserInfo(accessToken, openID);
                UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
                out.println(PEX_HTM1);
                if (userInfoBean.getRet() == 0) {
                    out.println(userInfoBean.getNickname() + PEX_HTM1);
                    out.println(userInfoBean.getGender() + PEX_HTM1);
                    out.println("黄钻等级： " + userInfoBean.getLevel() + PEX_HTM1);
                    out.println("会员 : " + userInfoBean.isVip() + PEX_HTM1);
                    out.println("黄钻会员： " + userInfoBean.isYellowYearVip() + PEX_HTM1);
                    out.println(PEX_HTM2 + userInfoBean.getAvatar().getAvatarURL30() + PEX_HTM3);
                    out.println(PEX_HTM2 + userInfoBean.getAvatar().getAvatarURL50() + PEX_HTM3);
                    out.println(PEX_HTM2 + userInfoBean.getAvatar().getAvatarURL100() + PEX_HTM3);
                } else {
                    out.println(LOGGERINFO1 + userInfoBean.getMsg());
                }
                out.println("<p> end -----------------------------------利用获取到的accessToken,openid 去获取用户在Qzone的昵称等信息 ---------------------------- end </p>");

                out.println("<p> start ----------------------------------- 验证当前用户是否为认证空间的粉丝------------------------------------------------ start <p>");
                PageFans pageFansObj = new PageFans(accessToken, openID);
                PageFansBean pageFansBean = pageFansObj.checkPageFans("97700000");
                if (pageFansBean.getRet() == 0) {
                    out.println("<p>验证您" + (pageFansBean.isFans() ? "是" : "不是") + "QQ空间97700000官方认证空间的粉丝</p>");
                } else {
                    out.println(LOGGERINFO1 + pageFansBean.getMsg());
                }
                out.println("<p> end ----------------------------------- 验证当前用户是否为认证空间的粉丝------------------------------------------------ end <p>");

                out.println("<p> start -----------------------------------利用获取到的accessToken,openid 去获取用户在微博的昵称等信息 ---------------------------- start </p>");
                com.qq.connect.api.weibo.UserInfo weiboUserInfo = new com.qq.connect.api.weibo.UserInfo(accessToken, openID);
                com.qq.connect.javabeans.weibo.UserInfoBean weiboUserInfoBean = weiboUserInfo.getUserInfo();
                if (weiboUserInfoBean.getRet() == 0) {
                    // 获取用户的微博头像----------------------start
                    out.println(PEX_HTM2 + weiboUserInfoBean.getAvatar().getAvatarURL30() + PEX_HTM3);
                    out.println(PEX_HTM2 + weiboUserInfoBean.getAvatar().getAvatarURL50() + PEX_HTM3);
                    out.println(PEX_HTM2 + weiboUserInfoBean.getAvatar().getAvatarURL100() + PEX_HTM3);
                    // 获取用户的微博头像 ---------------------end

                    // 获取用户的生日信息 --------------------start
                    out.println("<p>尊敬的用户，你的生日是： " + weiboUserInfoBean.getBirthday().getYear() + "年" + weiboUserInfoBean.getBirthday().getMonth() + "月"
                            + weiboUserInfoBean.getBirthday().getDay() + "日");
                    // 获取用户的生日信息 --------------------end

                    StringBuilder sb = new StringBuilder();
                    sb.append("<p>所在地:" + weiboUserInfoBean.getCountryCode() + "-" + weiboUserInfoBean.getProvinceCode() + "-" + weiboUserInfoBean.getCityCode()
                            + weiboUserInfoBean.getLocation());

                    // 获取用户的公司信息---------------------------start
                    ArrayList<Company> companies = weiboUserInfoBean.getCompanies();
                    if (!companies.isEmpty()) {
                        // 有公司信息
                        for (int i = 0, j = companies.size(); i < j; i++) {
                            sb.append("<p>曾服役过的公司：公司ID-" + companies.get(i).getID() + " 名称-" + companies.get(i).getCompanyName() + " 部门名称-" + companies.get(i).getDepartmentName()
                                    + " 开始工作年-" + companies.get(i).getBeginYear() + " 结束工作年-" + companies.get(i).getEndYear());
                        }
                    }
                    // 获取用户的公司信息---------------------------end

                    out.println(sb.toString());

                } else {
                    out.println(LOGGERINFO1 + weiboUserInfoBean.getMsg());
                }

                out.println("<p> end -----------------------------------利用获取到的accessToken,openid 去获取用户在微博的昵称等信息 ---------------------------- end </p>");

            }
        } catch (QQConnectException e) {
            LOGGER.error(""+e);
            out = null;
        }
    }
}
