package com.ningpai.api.util;

import com.ningpai.api.bean.OEmpower;
import com.ningpai.api.dao.IEmpowerLogMapper;
import com.ningpai.api.dao.IEmpowerMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 开放接口--工具类
 * @author lih
 * @version 2.0
 * @since 15/8/26
 */
@Service
public class OpenUtil {
    /**
     * json转换格式
     */
    public static  final String PRODUCESSTR="application/json; charset=utf-8";

    /**
     * 类型
     */
    public static final String UTYPE="uType";


    /**
     * 的货品库存
     */
    public  static final String GOODSSTR="\"的货品库存\"";

    /**
     * 权限dao
     */
    private static IEmpowerMapper empowerMapper;

    /**
     *权限日志dao
     */
    public  static  IEmpowerLogMapper logMapper;

    /**
     * 获取日志dao
     * @return
     */
    public  IEmpowerLogMapper getLogMapper() {
        return logMapper;
    }

    /**
     * 权限dao
     * @param logMapper
     */
    @Resource(name="openEmpowerLogMapper")
    public  void setLogMapper(IEmpowerLogMapper logMapper) {
        OpenUtil.logMapper = logMapper;
    }

    /**
     *  权限dao
     * @return
     */
    public  IEmpowerMapper getEmpowerMapper() {
        return empowerMapper;
    }
    /**
     * spring注入
     * */
    @Resource(name="openEmpowerMapper")
    public  void setEmpowerMapper(IEmpowerMapper empowerMapper) {
        OpenUtil.empowerMapper = empowerMapper;
    }

    /**
     * 判断时间戳
     * @param timestamp
     * @return
     */
    public static boolean isTimestamp(String timestamp){
        SimpleDateFormat bartDateFormat =
                new SimpleDateFormat("YYYYMMddHHmm");

        String date= bartDateFormat.format(new Date());

        if(timestamp==null){
            return false;
        }
        Long s=Long.parseLong(date)-Long.parseLong(timestamp);
        //判断传过来的时间戳是否符合
        if(s>6||s<-6){
            return false;
        }
        return true;

    }


    /**
     * 判断是否符合规范，并记录日志
     * @param timestamp 时间戳
     * @param appUserName 用户名
     * @param sign 签名验证
     * @param token token
     * @param logContent 日志内容
     * @return 成功返回‘1’ 失败返回报错信息
     */
    public static String isCheck(String timestamp,String appUserName,String sign,String token,String logContent){
            String msg="1";
        //判断时间戳是否符合
        if(!OpenUtil.isTimestamp(timestamp)){
            return  "时间戳异常";
        }
         SimpleDateFormat bartDateFormat =
                new SimpleDateFormat("YYYYMMddHHmm");

        //获取授权用户信息
         OEmpower ower= empowerMapper.getKey(appUserName);

        //判断token是否过期
        if(ower==null){
            return  "用户不存在";
        }
        //获取token的时间
        String date= bartDateFormat.format(ower.getTokenTime());
        //获取当前时间
        String  newDate= bartDateFormat.format(new Date());
        //判断token的存活时间
        Long s = Long.parseLong(date)-Long.parseLong(newDate);
        String mdStr=appUserName+timestamp+token+ower.getAppKey();
        //判断token是否符合
        if(token==null||!token.equals(ower.getToken())){
            msg="token异常";
        }else if(!MD5Util.md5Hex(mdStr).equals(sign)){
            msg= "签名异常";
        }else if(s>10000||s<-10000){
                msg="token过期";
        }else{
            //记录日志
            Map<String,Object> map=new HashMap<String,Object>();
            //添加角色id
            map.put("empowerId",ower.getAppId());
            //添加日志内容
            map.put("logContent",logContent);
            logMapper.addLog(map);
        }
        return msg;
    }








}
