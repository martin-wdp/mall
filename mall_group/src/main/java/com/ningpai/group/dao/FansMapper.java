package com.ningpai.group.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.group.bean.Fans;

/**
 * 粉丝接口
 * @author ggn
 *
 */
public interface FansMapper {
     /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2014-08-11 12:07:55
     */
    int deleteByPrimaryKey(Long fansId);
   
    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2014-08-11 12:07:55
     */
    int insert(Fans record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2014-08-11 12:07:55
     */
    int insertSelective(Fans record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2014-08-11 12:07:55
     */
    Fans selectByPrimaryKey(Long fansId);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2014-08-11 12:07:55
     */
    int updateByPrimaryKeySelective(Fans record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2014-08-11 12:07:55
     */
    int updateByPrimaryKey(Fans record);
    
    /**
     * 查询当前登录和访问用户的粉丝状态
     * @param paramMap 参数设置
     * @return fans 
     */
    Fans fansFlag(Map<String, Object> paramMap);

    /**
     * 修改我的关注状态为2 互为粉丝
     * @param paramMap 参数设置
     * @return int
     */
    int guanzhu(Map<String, Object> paramMap);
    
    /**
     * 修改粉丝和我的状态为2 互粉
     * @param paramMap 参数设置
     * @return int
     */
    int fansi(Map<String, Object> paramMap);
    
    /**
     * 取消我的关注 互粉状态
     * @param paramMap 参数设置
     * @return int
     */
    int cancelGuanZhu(Map<String, Object> paramMap);
    
    /**
     * 取消粉丝关注  互粉状态
     * @param paramMap 参数设置
     * @return int
     */
    int cancelFansi(Map<String, Object> paramMap);
    
    /**
     * 取消我的关注 仅仅单方关注
     * @param paramMap 参数设置
     * @return int
     */
    int delFansFlag(Map<String, Object> paramMap);
    
    /**
     * 取消我是别人粉丝
     * @param paramMap 参数查询
     * @return int
     */
    int delOFansFlag(Map<String, Object> paramMap);
    
    /**
     * 他的关注
     * @param paramMap 参数
     * @return List
     */
    List<Fans> fansList(Map<String, Object> paramMap);
    
    /**
     * 当前登录用户相互关注的好友列表
     * @param paramMap 参数
     * @return list
     */
    List<Object> eachfansByCustomerId(Map<String, Object> paramMap);
    
    /**
     * 当前登录用户相互关注的好友数目
     * @param paramMap 参数
     * @return int
     */
    int eachfansCountByCustomerId(Map<String, Object> paramMap);
    
    /**
     * 查询用户的关注和粉丝
     * @param paramMap 参数{@link java.util.Map}
     * @return list
     */
    List<Object> selectMyMtual(Map<String, Object> paramMap);
    
    /**
     * 查询用户的关注和粉丝的数目
     * @param paramMap 参数{@link java.util.Map}
     * @return int
     */
    int myMtualCount(Map<String, Object> paramMap);
    
    /**
     * 查询我的关注和我的粉丝（不分页）
     * @param paramMap  参数{@link java.util.Map}
     * @return
     */
    List<Fans> selectFocus(Map<String, Object> paramMap);
}
