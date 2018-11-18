package com.ningpai.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.ningpai.api.bean.OCustomer;
import com.ningpai.api.bean.OCustomerAllInfo;
import com.ningpai.api.dao.ICustomerMapper;
import com.ningpai.api.service.ICustomerService;
import com.ningpai.api.util.OpenPageBean;
import com.ningpai.api.util.OpenUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 开放接口-会员service实现
 * @author lih
 * @version 2.0
 * @since 15/8/26
 */
@Service("openCustomerService")
public class CustomerServiceImpl implements ICustomerService{



    @Resource(name="openCustomerMapper")
    private ICustomerMapper customerMapper;

    /**
     * 获取会员列表
     *
     * @param pageNo 页数 （默认1）
     * @param pageSize 每页数量（默认15）
     * @return 会员列表
     */
    @Override
    public String list(Integer pageNo,Integer pageSize) {
        Integer pageNoNew = pageNo;
        Integer pageSizeNew = pageSize;
        //判断是否为空，如果为空，设为1
        if(pageNoNew==null){
            pageNoNew=1;
        }
        //判断每页数量是否为空
        if(pageSizeNew==null){
            pageSizeNew=15;
        }

        //获取总数量
        int count=customerMapper.count();
        OpenPageBean pageBean=new OpenPageBean();
        //设置每页大小
        pageBean.setPageSize(pageSizeNew);
        //设置页数
        pageBean.setPageNo(pageNoNew);
        //设置总行数
        pageBean.setRows(count);
        Map<String,Object> map=new HashMap<String,Object>();
        //设置开始行数
        map.put("start",pageBean.getStartRowNum());
        //设置结束行数
        map.put("end", pageBean.getEndRowNum());
        List<OCustomer> oCustomers= customerMapper.list(map);
        //设置返回json
        Map<String,Object> items=new HashMap<String,Object>();
        //设置会员集合
        items.put("item",oCustomers);
        //设置总行数
        items.put("total_results",oCustomers.size());

        try{
            return JSON.toJSONString(items);
        }finally {
            map=null;
            items=null;
            oCustomers=null;
        }
    }

    /**
     * 获取会员详情
     *
     * @param customerUserName 会员id
     * @return 会员详情
     */
    @Override
    public String get(String customerUserName) {
        OCustomerAllInfo oCustomerAllInfo=customerMapper.get(customerUserName);
        //返回
        return JSON.toJSONString(customerMapper.get(customerUserName));
    }

    /**
     * 检查用户是否存在
     *
     * @param userName
     *            用户名 {@link String}
     * @return 0 不存在 1 存在 {@link Long}
     */
    @Override
    public Long checkExistsByCustNameAndType(String userName) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (userName.indexOf("@") != -1) {
            paramMap.put(OpenUtil.UTYPE, "email");
        } else if (Pattern.compile("^0?(13|15|17|18|14)[0-9]{9}$")
                .matcher(userName).find()) {
            paramMap.put(OpenUtil.UTYPE, "mobile");
        } else {
            paramMap.put(OpenUtil.UTYPE, "username");
        }
        paramMap.put("username", userName);
        return customerMapper.checkExistsByCustNameAndType(paramMap);
    }



    /**
     * 验证邮箱存在性
     *
     * @param email
     *            目标邮箱 {@link String}
     * @return 0不存在 1存在
     */
    @Override
    public Long checkEmailExist(String email) {
        return customerMapper.checkEmailExist(email);
    }

    /**
     * 验证手机存在性
     *
     * @param mobile
     *            目标手机 {@link String}
     * @return 0不存在 1存在
     */
    @Override
    public Long checkMobileExist(String mobile) {
        return customerMapper.checkMobileExist(mobile);
    }

    /**
     * 添加会员
     *
     * @param customerUsername 会员名
     * @param customerPassword 密码
     * @param customerNickname 会员别名
     * @param phone            手机
     * @param email            邮箱
     * @return 0:异常 1：添加成功 2：会员已存在 3：手机或邮箱已存在 4：名称以及密码为空或格式不正确 5：签名异常
     */
    @Override
    public int addCustomer(String customerUsername, String customerPassword, String customerNickname, String phone, String email) {
        String customerNicknameNew = customerNickname;
        OCustomerAllInfo o=new OCustomerAllInfo();
        /**
         * 验证会员名和密码是否为空
         */
        if(customerNicknameNew==null||customerPassword==null && customerUsername.length()>=6 && customerUsername.length()<=20){
            return 4;
        }

        //验证会员名是否存在
        Long exitName= checkExistsByCustNameAndType(customerUsername);
        if(exitName>0){
            return 2;
        }
        //验证手机是否为空
        if(phone!=null){
            //验证手机是否存在
            Long exitPhone=checkMobileExist(phone);

            if(exitPhone>0){
                return 3;
            }
            o.setIsMobile("1");
        }
        //判断邮箱是否为空
        if(email!=null){
            //验证邮箱是否存在
            Long exitEmail=checkEmailExist(email);
            if(exitEmail>0){
                return 3;
            }
            o.setIsEmail("1");
        }




        // 通过邮箱注册用户
        if (customerUsername.indexOf("@") != -1) {
            o.setInfoEmail(customerUsername);
            o.setCustomerNickname(customerUsername
                    .substring(0, customerUsername.indexOf("@")));
        } else if (Pattern.compile("^0?(13|15|17|18|14)[0-9]{9}$")
                .matcher(customerUsername).find()) {
            // 手机注册用户
            o.setInfoMobile(customerUsername);
            o.setCustomerNickname(customerUsername);
        }else{
            //设置邮箱
            o.setInfoEmail(email);
            //设置手机
            o.setInfoMobile(phone);
        }
        //设置别名
        if(customerNicknameNew==null){
            customerNicknameNew=customerUsername;
        }
        o.setCustomerNickname(customerNicknameNew);
        //设置会员名
        o.setCustomerUsername(customerUsername);
        //设置密码
        o.setCustomerPassword(customerPassword);
        o.setPointLevelId(2L);
        //返回添加结果
        return customerMapper.addCustomer(o);
    }




}
