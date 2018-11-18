package com.ningpai.api.service.impl;

import com.ningpai.api.bean.OEmpower;
import com.ningpai.api.dao.IEmpowerMapper;
import com.ningpai.api.service.IEmpowerService;
import com.ningpai.api.util.MD5Util;
import com.ningpai.api.util.OpenUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author lih
 * @version 2.0
 * @since 15/9/1
 */
@Service("openEmpowerService")
public class EmpowerServiceImpl implements IEmpowerService {

    @Resource(name = "openEmpowerMapper")
    private IEmpowerMapper empowerMapper;

    /**
     * 获取token
     * 
     * @param appUserName
     *            用户名
     * @param sign
     *            签名 appUserName+用户key+timestamp
     * @param timestamp
     *            时间戳 YYYYMMddHm
     * @return
     */
    @Override
    public String getKey(String appUserName, String sign, String timestamp) {

        // 判断时间戳是否符合
        if (!OpenUtil.isTimestamp(timestamp)) {
            return "时间戳异常";
        }

        // 获取授权用户信息
        OEmpower ower = empowerMapper.getKey(appUserName);
        if (ower == null) {
            return "用户不存在";
        }
        String mdStr = appUserName + timestamp + ower.getAppKey();
        // 判断签名是否正确
        if (!MD5Util.md5Hex(mdStr).equals(sign)) {
            return "签名异常";
        }

        // 设置字符
        String base = "ABCDEFGHIJKLMNGPQRSTUVWXYZ";
        // 创建对象
        Random random = new Random();
        // 创建对象
        StringBuffer sb = new StringBuffer();
        // 获取值
        for (int i = 0; i < 30; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("appUserName", appUserName);
        map.put("token", sb.toString());

        empowerMapper.updateEmpower(map);
        // 设置key
        return sb.toString();

    }

}
