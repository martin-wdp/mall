package com.ningpai.group.service;

import java.util.List;

import com.ningpai.group.bean.Fans;
import com.ningpai.util.PageBean;

/**
 * 粉丝service接口
 * @author qiyuanyuan
 *
 */
public interface FansSiteService {
    
    /**
     * 查询当前登录用户与当前主页的粉丝状态
     * @param customerId {@link java.lang.Long}
     * @param fansCustomerId {@link java.lang.Long}
     * @return
     */
    String fansFlag(Long customerId,Long fansCustomerId);
    
    /**
     * 加关注
     * @param customerId 登陆用户ID {@link java.lang.Long}
     * @param fansCustomerId 粉丝用户Id {@link java.lang.Long}
     * @param fansFlag 粉丝状态 {@link java.lang.String}
     * @return int
     */
    int guanzhu(Long customerId,Long fansCustomerId,String fansFlag);
    
    /**
     * 取消关注
     * @param customerId 登陆用户ID {@link java.lang.Long}
     * @param fansCustomerId 粉丝用户Id {@link java.lang.Long}
     * @param fansFlag 粉丝状态  {@link java.lang.String}
     * @return int
     */
    int cancelGuanzhu(Long customerId,Long fansCustomerId,String fansFlag);
    
    /**
     * 他的关注
     * @param customerId {@link java.lang.String}
     * @return  list
     */
    List<Fans> fansList(Long customerId);
    
    /**
     * 查询相互关注的好友
     * @param pb 分页
     * @param customerId 用户id {@link java.lang.Long}
     * @param customerName 用户姓名 {@link java.lang.String}
     * @return pb
     */
    PageBean fansCustomer(PageBean pb,Long customerId,String customerName);
    
    /**
     * 用户的关注和粉丝
     * @param pb 分页
     * @param customerId 用户id {@link java.lang.Long}
     * @param fansFlag 粉丝状态  0我的粉丝 1我的关注 2互粉 {@link java.lang.String}
     * @return
     */
    PageBean selectMyMtual(PageBean pb,Long customerId,String fansFlag,Long cusId);
    
    /**
     * 批量取消关注
     * @param customerId   用户id {@link java.lang.Long}
     * @param customerIds
     * @return
     */
    int cancelAllGuanzhu(Long customerId, String[] customerIds);
    
    /**
     * 我的关注和我的粉丝
     * @param customerId 用户ID {@link java.lang.Long}
     * @param number 显示数目
     * @param flag 标志
     * @return
     */
    List<Fans> selectFocus(Long customerId,int number,String flag);

}
