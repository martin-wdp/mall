/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.customer.service.impl;

import com.ningpai.customer.bean.Customer;
import com.ningpai.customer.bean.CustomerAddress;
import com.ningpai.customer.bean.CustomerInfo;
import com.ningpai.customer.dao.CustomerAddressMapper;
import com.ningpai.customer.dao.CustomerInfoMapper;
import com.ningpai.customer.dao.CustomerMapper;
import com.ningpai.customer.dao.CustomerPointLevelMapper;
import com.ningpai.customer.service.CustomerServiceMapper;
import com.ningpai.customer.vo.CustomerStatisticVo;
import com.ningpai.other.bean.*;
import com.ningpai.other.util.SelectBean;
import com.ningpai.salesman.bean.CustomerRelaSalesman;
import com.ningpai.salesman.dao.CustomerRelaSalesmanMapper;
import com.ningpai.util.PageBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * 会员服务处理接口实现类
 * 
 * @author NINGPAI-zhangqiang
 * @since 2013年12月14日 下午2:57:09
 * @version 1.0
 */
@Service("customerServiceMapper")
public class CustomerServiceMapperImpl implements CustomerServiceMapper {

    private static final String CUSTOMERID = "customerId";
    private static final String STARTROWNUM = "startRowNum";
    private static final String ENDROWNUM = "endRowNum";
    private static final String UTYPE = "uType";

    // spring注解
    private CustomerMapper customerMapper;
    private CustomerInfoMapper customerInfoMapper;
    private CustomerAddressMapper customerAddressMapper;
    private CustomerPointLevelMapper customerPointLevelMapper;
    @Resource(name = "customerRelaSalesmanMapper")
    private CustomerRelaSalesmanMapper customerRelaSalesmanMapper;

    /**
     * 禁用/启用商家员工
     *
     * @param custId
     *            用户编号
     * @param flag
     *            disable 禁用 否则 启用
     * @return 0 失败 1成功
     */
    @Override
    public int modifyEmpToDisableThird(Long custId, String flag, Long thirdId) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("custId", custId);
        paramMap.put("flag", flag);
        paramMap.put("thirdId", thirdId);
        return customerMapper.modifyEmpToDisableThird(paramMap);
    }

    /**
     * 删除商家员工
     *
     * @param custId
     *            要删除的员工ID
     * @param thirdId
     *            当前商家ID
     * @return 成功1 失败0
     */
    @Override
    @Transactional
    public int deleteCustomerThird(String[] custId, Long thirdId) {
        Integer count = 0;
        Map<String, Object> paramMap = new HashMap<String, Object>();
        try {
            paramMap.put(CUSTOMERID, custId);
            paramMap.put("thirdId", thirdId);
            count = customerMapper.deleteCustomerThird(paramMap);
        } finally {
            paramMap = null;
        }

        return count;
    }

    /***
     * 修改会员信息 登陆错误的次数
     *
     * @param customer
     * @return
     */
    @Override
    public int updateCusErrorCount(Customer customer) {
        return customerMapper.updateCusErrorCount(customer);
    }

    /***
     * 修改会员信息 达到登陆错误次数 就插入锁定时间
     *
     * @param customer
     * @return
     */
    @Override
    public int updateCusLock(Customer customer) {
        return customerMapper.updateCusLock(customer);
    }

    /***
     * 根据会员登录名获取单个的会员信息（用户 会员登陆次数限制）
     *
     * @param username
     *            会员登陆名称
     * @return
     */
    @Override
    public Customer getCustomerByUsername(String username) {
        return customerMapper.getCustomerByUsername(username);
    }

    /**
     * 删除商家为普通会员
     * 
     * @param ids
     * @return
     */
    @Override
    public int deleteStore(String[] ids) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", ids);
        return customerMapper.deleteStore(map);
    }

    /**
     * 修改会员状态为商家
     * 
     * @param cId
     * @return
     */
    @Override
    public int updateStatus(int cId) {
        return customerMapper.updateStatus(cId);
    }

    /**
     * 查询单个会员信息 详细
     *
     * @param customerId
     *            用户编号
     * @see java.lang.Long {@link java.lang.Long}
     * @see com.ningpai.customer.bean.Customer
     *      {@link com.ningpai.customer.bean.Customer}
     * @return @see com.ningpai.customer.bean.Customer
     *         {@link com.ningpai.customer.bean.Customer}
     */
    @Override
    public CustomerAllInfo selectByPrimaryKey(Long customerId) {

        return customerMapper.selectByPrimaryKey(customerId);
    }

    /**
     * 根据会员id查询出所有订单信息
     *
     * @param customerIds
     * @return
     */
    @Override
    public List<CustomerAllInfo> selectBycustomerIds(Map<String, Object> customerIds) {
        return customerMapper.selectBycustomerIds(customerIds);
    }

    /**
     * 查询所用会员用户 分页
     *
     * @see com.ningpai.util.PageBean {@link com.ningpai.util.PageBean}
     * @see com.ningpai.customer.bean.Customer
     *      {@link com.ningpai.customer.bean.Customer}
     * @return @see com.ningpai.util.PageBean {@link com.ningpai.util.PageBean}
     */
    @Override
    public PageBean selectAllCustmer(PageBean pageBean) {
        Map<String, Integer> paramMap = null;
        int no = 0;
        try {
            pageBean.setRows(customerMapper.selectAllCustomerCount());
            no = pageBean.getRows() % pageBean.getPageSize() == 0 ? pageBean.getRows() / pageBean.getPageSize() : (pageBean.getRows() / pageBean.getPageSize() + 1);
            no = no == 0 ? 1 : no;
            if (pageBean.getPageNo() >= no) {
                pageBean.setPageNo(no);
                pageBean.setStartRowNum((no - 1) * pageBean.getPageSize());
            }
            paramMap = new HashMap<String, Integer>();
            paramMap.put(STARTROWNUM, pageBean.getStartRowNum());
            paramMap.put(ENDROWNUM, pageBean.getEndRowNum());
            pageBean.setList(customerMapper.selectCustomerByLimit(paramMap));
        } finally {
            paramMap = null;
        }
        return pageBean;
    }

    /**
     * <strong> 添加会员</strong> <br>
     * 注意事项：<br>
     * 1.可用于添加会员所有信息 所有信息参考 {@link CustomerAllInfo}<br>
     * 2.添加普通会员时,不用填充第三方字段属性<br>
     * 3.添加第三方会员或者店铺管理员时,需要填充会员信息中的第三方信息<br>
     *
     * @param allinfo
     *            {@link CustomerAllInfo} 查询、添加、修改等操作时用到的辅助类 包含会员的所有信息
     * @return 返回结果：int 0 添加失败 1 添加成功
     */
    @Override
    @Transactional
    public int addCustomer(CustomerAllInfo allinfo) {
        /*if (allinfo.getCustomerNickname() == null) {
            allinfo.setCustomerNickname(allinfo.getCustomerUsername());
        }*/
        if (allinfo.getPointLevelId() == null) {
            allinfo.setPointLevelId(customerPointLevelMapper.selectDefaultCustomerLevel().getPointLelvelId());
        }
        // 通过邮箱注册用户
        if (allinfo.getCustomerUsername().indexOf("@") != -1) {
            //allinfo.setInfoEmail(allinfo.getCustomerUsername());
            //allinfo.setCustomerNickname(allinfo.getCustomerUsername().substring(0, allinfo.getCustomerUsername().indexOf("@")));
            return 0;
        } else if (Pattern.compile("^0?(13|15|17|18|14)[0-9]{9}$").matcher(allinfo.getCustomerUsername()).find()) {
            // 手机注册用户
            allinfo.setInfoMobile(allinfo.getCustomerUsername());
            //allinfo.setCustomerNickname(allinfo.getCustomerUsername());
            allinfo.setCustomerNickname("qpmall"+(int)((Math.random() * 9 + 1) * 100000));
        }
        // 设置登录key
        UUID uuid = UUID.randomUUID();
        allinfo.setLoginKey(uuid.toString());
        return customerMapper.addCustomer(allinfo);
    }


    @Override
    @Transactional
    public int addThirdCustomer(CustomerAllInfo allinfo) {
        if (allinfo.getCustomerNickname() == null) {
            allinfo.setCustomerNickname(allinfo.getCustomerUsername());
        }
        if (allinfo.getPointLevelId() == null) {
            allinfo.setPointLevelId(customerPointLevelMapper.selectDefaultCustomerLevel().getPointLelvelId());
        }
        // 通过邮箱注册用户
        if (allinfo.getCustomerUsername().indexOf("@") != -1) {
            allinfo.setInfoEmail(allinfo.getCustomerUsername());
            allinfo.setCustomerNickname(allinfo.getCustomerUsername().substring(0, allinfo.getCustomerUsername().indexOf("@")));
        } else if (Pattern.compile("^0?(13|15|17|18|14)[0-9]{9}$").matcher(allinfo.getCustomerUsername()).find()) {
            // 手机注册用户
            allinfo.setInfoMobile(allinfo.getCustomerUsername());
            allinfo.setCustomerNickname(allinfo.getCustomerUsername());
        }
        // 设置登录key
        UUID uuid = UUID.randomUUID();
        allinfo.setLoginKey(uuid.toString());
        return customerMapper.addCustomer(allinfo);
    }

    /**
     * 查询会员对应的商家id
     *
     * @param customerId
     * @return
     */
    @Override
    public String findStore(String customerId) {
        return customerMapper.findStoreId(customerId);
    }

    /**
     * 删除会员
     *
     * @see
     * @see java.lang.Integer {@link java.lang.Integer}
     * @return 0 失败 1 成功
     */
    @Override
    @Transactional
    public int deleteCustomer(String[] customerId) {
        Integer count = 0;
        Map<String, Object> paramMap = new HashMap<String, Object>();
        try {
            paramMap.put(CUSTOMERID, customerId);
            count = customerMapper.deleteCustomerByIds(paramMap);
        } finally {
            paramMap = null;
        }

        return count;
    }

    /**
     * 查询会员类型
     *
     * @param customerId
     * @return
     */
    @Override
    public String selectStatus(String customerId) {
        return customerMapper.selectStatus(customerId);
    }

    /**
     * 修改会员信息
     *
     * @see
     * @see java.lang.Integer {@link java.lang.Integer}
     * @return 0 失败 1 成功
     */
    @Override
    @Transactional
    public int updateCustomer(CustomerAllInfo allinfo) {
        // 保存会员表
        int result = customerMapper.updateByPrimaryKeySelective(allinfo);
        if (result == 1) {
            result = 0;
            // 保存会员拓展表
            result = customerInfoMapper.updateByPrimaryKeySelective(allinfo);
        }
        if(allinfo.getSalesmanId()!=null&&allinfo.getSalesmanId()!=0){
            //删除旧的
            customerRelaSalesmanMapper.deleteByCustId(allinfo.getCustomerId());
            //添加业务员绑定信息
            CustomerRelaSalesman customerRelaSalesman = new CustomerRelaSalesman();
            customerRelaSalesman.setSalesmanId(allinfo.getSalesmanId());
            customerRelaSalesman.setCustomerId(allinfo.getCustomerId());
            result =   customerRelaSalesmanMapper.insertSelective(customerRelaSalesman);
        }
        return result;
    }

    /**
     * 检查会员名是否存在
     *
     * @param customerName
     *            等级名称
     * @return 1 存在 0 不存在
     * @see java.lang.Long {@link java.lang.Long}
     */
    @Override
    public Long selectCustomerByName(String customerName) {
        return customerMapper.selectCustomerByName(customerName);
    }

    @Override
    public Long selectCustomerByNameForThird(String customerName) {
        return customerMapper.selectCustomerByNameForThird(customerName);
    }

    /**
     * 根据订单编号查找订单信息
     *
     * @param orderId
     *            订单编号 java.lang.Long {@link java.lang.Long}
     * @return
     */
    @Override
    public CustomerAllInfo selectCustomerOrder(Long orderId) {
        return customerMapper.selectCustomerOrder(orderId);
    }

    /**
     * 查询所有省份 用于添加会员页面填充省份
     *
     * @return
     */
    @Override
    public List<ProvinceBean> selectAllProvince() {
        return customerMapper.selectAllProvince();
    }

    /**
     * 根据省份编号 查询所有城市
     *
     * @param provinceId
     *            省份编号 java.lang.Long {@link java.lang.Long}
     * @return
     */
    @Override
    public List<CityBean> selectAllCityByPid(Long provinceId) {
        return customerMapper.selectAllCityByPid(provinceId);
    }

    /**
     * 根据城市编号 查询所有区县
     *
     * @param cityId
     *            城市编号 java.lang.Long {@link java.lang.Long}
     * @return
     */
    @Override
    public List<DistrictBean> selectAllDistrictByCid(Long cityId) {
        return customerMapper.selectAllDistrictByCid(cityId);
    }

    /**
     * 按条件查询会员
     *
     * @param customerAllInfo
     *
     * @return
     */
    @Override
    public PageBean selectCustmerByAllInfo(CustomerAllInfo customerAllInfo, PageBean pageBean) {
        Map<String, Integer> paramMap = null;
        int no = 0;
        try {
            // 设置总行数
            pageBean.setRows(Integer.parseInt(customerMapper.selectCustmerSizeFilterThird(customerAllInfo) + ""));
            no = pageBean.getRows() % pageBean.getPageSize() == 0 ? pageBean.getRows() / pageBean.getPageSize() : (pageBean.getRows() / pageBean.getPageSize() + 1);
            no = no == 0 ? 1 : no;
            // 若页码超过最大页码 则显示最后一个
            if (pageBean.getPageNo() >= no) {
                pageBean.setPageNo(no);
                pageBean.setStartRowNum((no - 1) * pageBean.getPageSize());
            }
            // 设置查询条件
            if (customerAllInfo != null) {
                customerAllInfo.setStartRowNum(pageBean.getStartRowNum());
                customerAllInfo.setEndRowNum(pageBean.getEndRowNum());
                pageBean.setObjectBean(customerAllInfo);
            }
            paramMap = new HashMap<String, Integer>();
            paramMap.put(STARTROWNUM, pageBean.getStartRowNum());
            paramMap.put(ENDROWNUM, pageBean.getEndRowNum());
            // 查询会员信息
            pageBean.setList(customerAllInfo == null ? customerMapper.selectCustomerByLimitFilterThird(paramMap) : customerMapper.selectCustmerByAllInfoFilterThird(customerAllInfo));
        } finally {
            paramMap = null;
        }
        return pageBean;
    }

    /**
     * 根据会员编号查找对应的默认收货地址
     *
     * @param customerId
     *            会员编号
     * @return 会员收货地址
     */
    @Override
    public CustomerAddress queryDefaultAddr(Long customerId) {
        return customerAddressMapper.selectDefaultAddr(customerId);
    }

    /**
     * 查询用户上一次收货地址
     *
     * @param customerId
     * @return
     */
    @Override
    public CustomerAddress selectByCIdFirst(Long customerId) {
        return customerAddressMapper.selectByCIdFirst(customerId);
    }

    /**
     * 根据会员编号修改会员
     *
     * @param
     *
     * @return java.lang.Integer {@link java.lang.Integer}
     */
    @Override
    public int updateByPrimaryKeySelective(CustomerAllInfo record) {
        return customerMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 按区县编号获取对应街道集合
     *
     * @param dId
     *            区县编号 java.lang.Long {@link java.lang.Long}
     * @return
     */
    @Override
    public List<StreetBean> getAllStreetByDid(Long dId) {
        return customerMapper.getAllStreetByDid(dId);
    }

    /**
     * 查询订单信息
     *
     * @param orderId
     *            java.lang.Long {@link java.lang.Long}
     * @return OrderInfoBean {@link com.ningpai.other.bean.OrderInfoBean}
     */
    @Override
    public OrderInfoBean queryByDetail(Long orderId) {
        return customerMapper.queryByDetail(orderId);
    }

    /**
     * 查询会员信息
     *
     * @param customerId
     *            用户编号
     * @see java.lang.Long {@link java.lang.Long}
     * @see com.ningpai.customer.bean.Customer
     *      {@link com.ningpai.customer.bean.Customer}
     * @return @see com.ningpai.customer.bean.Customer
     *         {@link com.ningpai.customer.bean.Customer}
     */
    @Override
    public CustomerAllInfo queryCustomerInfo(Long customerId) {
        return customerMapper.queryCustomerInfo(customerId);
    }

    /**
     * 禁用/启用用户
     *
     * @param custId
     *            用户编号
     * @param flag
     *            disable 禁用 否则 启用
     * @return 0 失败 1成功
     */
    @Override
    public int modifyEmpToDisable(Long custId, String flag) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("flag", flag);
        paramMap.put("custId", custId);
        return customerMapper.modifyEmpToDisable(paramMap);
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
            paramMap.put(UTYPE, "email");
        } else if (Pattern.compile("^0?(13|15|17|18|14)[0-9]{9}$").matcher(userName).find()) {
            paramMap.put(UTYPE, "mobile");
        } else {
            paramMap.put(UTYPE, "username");
        }
        paramMap.put("username", userName);
        return customerMapper.checkExistsByCustNameAndType(paramMap);
    }

    public CustomerMapper getCustomerMapper() {
        return customerMapper;
    }

    @Resource(name = "customerMapper")
    public void setCustomerMapper(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    public CustomerInfoMapper getCustomerInfoMapper() {
        return customerInfoMapper;
    }

    @Resource(name = "customerInfoMapper")
    public void setCustomerInfoMapper(CustomerInfoMapper customerInfoMapper) {
        this.customerInfoMapper = customerInfoMapper;
    }

    public CustomerAddressMapper getCustomerAddressMapper() {
        return customerAddressMapper;
    }

    @Resource(name = "customerAddressMapper")
    public void setCustomerAddressMapper(CustomerAddressMapper customerAddressMapper) {
        this.customerAddressMapper = customerAddressMapper;
    }

    public CustomerPointLevelMapper getCustomerPointLevelMapper() {
        return customerPointLevelMapper;
    }

    @Resource(name = "customerPointLevelMapper")
    public void setCustomerPointLevelMapper(CustomerPointLevelMapper customerPointLevelMapper) {
        this.customerPointLevelMapper = customerPointLevelMapper;
    }

    /**
     * 查询所有城市 by yuankk
     *
     * @param
     * @return
     */
    public List<CityBean> selectAllCity() {
        return this.customerMapper.selectAllCity();
    }

    /**
     * 查询所有区县
     *
     * @param
     * @return
     */
    public List<DistrictBean> selectAllDistrict() {
        return this.customerMapper.selectAllDistrict();
    }

    /**
     * 根据订单编号查找订单信息
     *
     * @param orderId
     *            订单编号
     * @return OrderInfoBean
     */
    @Override
    public Object queryOrderByOrderId(Long orderId) {
        return customerMapper.queryOrderByOrderId(orderId);
    }

    /**
     * 根据用户名查询用户
     *
     * @param customerUserName
     * @return 对象
     */
    @Override
    public Customer customer(String customerUserName) {
        return customerMapper.customer(customerUserName);
    }

    /**
     * 根据用户名查询用户
     *
     * @param customerUserName
     * @return list
     */
    @Override
    public List<Customer> customerList(String customerUserName) {
        return customerMapper.customerList(customerUserName);
    }

    /***
     * 修改会员信息
     *
     * @param customer
     * @return
     */
    @Override
    @Transactional
    public int updateCus(Customer customer) {
        return customerMapper.updateCus(customer);
    }

    /**
     * 查询邮箱
     *
     * @return
     */
    @Override
    public CustomerInfo email(Long customerId) {

        return customerInfoMapper.email(customerId);
    }

    /**
     * 查询手机
     *
     * @return
     */
    @Override
    public CustomerInfo mobile(Long customerId) {

        return customerInfoMapper.mobile(customerId);
    }

    /**
     * 根据用户名查询用户信息
     *
     * @param userName
     * @return
     */
    @Override
    public Customer selectCustomerByUserName(String userName) {
        return customerMapper.selectCustomerByUserName(userName);
    }

    // @Override
    // public int updateCusSumPoint(Map<String, Object> paraMap) {
    //
    // return customerInfoMapper.updateCusSumPoint(paraMap);
    // }

    /**
     * 根据时间统计会员个数
     */
    @Override
    public List<CustomerStatisticVo> selectCountByTime() {
        return customerMapper.selectCountByTime();
    }

    /**
     * 根据地区统计会员个数
     */
    @Override
    public List<CustomerStatisticVo> selectCountByAddress() {
        return customerMapper.selectCountByAddress();
    }

    /**
     * 修改会员信息
     *
     * @see
     * @see java.lang.Integer {@link java.lang.Integer}
     * @return 0 失败 1 成功
     */
    @Override
    public int setCustomer(CustomerAllInfo allinfo) {

        return customerMapper.updateByPrimaryKeySelective(allinfo);
    }

    @Override
    public int updatePassword(Map<String, Object> map) {

        return customerMapper.updateThirdPassword(map);
    }

    /**
     * 设置会员类型
     *
     * @see
     * @see java.lang.Integer {@link java.lang.Integer}
     * @return 0 失败 1 成功
     */
    @Override
    public int setCustomer(String[] customerId, String isSiteManager) {
        Integer count = 0;
        Map<String, Object> paramMap = new HashMap<String, Object>();
        try {
            paramMap.put(CUSTOMERID, customerId);
            paramMap.put("isSiteManager", isSiteManager);
            count = customerMapper.setCustomerByIds(paramMap);
        } finally {
            paramMap = null;
        }

        return count;
    }

    /**
     * 查询会员消费排行
     *
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public PageBean queryCustomerRank(PageBean pageBean, SelectBean selectBean, String startTime, String endTime) {
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("startTime", startTime);
        paraMap.put("endTime", endTime);
        paraMap.put(STARTROWNUM, pageBean.getStartRowNum());
        paraMap.put(ENDROWNUM, pageBean.getEndRowNum());
        if (selectBean.getCondition() == null) {
            paraMap.put("condition", "0");
        } else {
            paraMap.put("condition", selectBean.getCondition());
        }
        pageBean.setRows(customerMapper.selectAllSize(paraMap));
        pageBean.setList(customerMapper.queryCustomerRank(paraMap));
        return pageBean;
    }

    /**
     * 查询会员等级情况
     *
     * @return
     */
    @Override
    public List<Customer> queryCusLevleInfo() {
        return customerMapper.queryCusLevleInfo();
    }

    /**
     * 查询信息
     * */
    @Override
    public PageBean queryCusAndOrderInfo(PageBean pageBean, Long pointLevelId, String customerNickname) {
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put(STARTROWNUM, pageBean.getStartRowNum());
        paraMap.put(ENDROWNUM, pageBean.getEndRowNum());
        paraMap.put("pointLevelId", pointLevelId);
        paraMap.put("customerNickname", customerNickname);
        pageBean.setRows(customerMapper.selectCusCount(paraMap));
        pageBean.setList(customerMapper.queryCusAndOrderInfo(paraMap));
        return pageBean;
    }

    /**
     * 查询会员，咨询，评论，晒单的数量
     *
     * @return
     */
    @Override
    public Map<String, Object> getCustomerCount() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("customerCount", customerMapper.selectCustmerSize(null));
        return map;
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
     * 查询最新的会员信息
     *
     * @author lih
     * @return
     */
    @Override
    public List<Customer> selectNewCustoer() {
        return customerMapper.selectCustmerNewLimit();
    }

    /**
     * 手动更新会员等级
     *
     * @return
     */
    @Override
    public int upCusLevel(Map<String, Object> paraMap) {

        return customerInfoMapper.upCusLevel(paraMap);
    }

    /**
     * 查询商家员工用户名是否存在
     *
     * @return
     */
    @Override
    public Integer checkUsernameExitOrNot(Map<String, Object> map) {
        return customerMapper.checkUsernameExitOrNot(map);
    }

    /**
     * 查询所有会员
     * 
     * @return List
     */
    public List<CustomerAllInfo> selectCustomerAllInfomation() {
        return customerMapper.selectCustomerAllInfomation();
    }


    /**
     * 根据主键获取单个的会员信息
     *
     * @param customerId
     * @return
     */
    @Override
    public Customer getCustomerByCusId(Long customerId) {
        return customerMapper.getCustomerByCusId(customerId);
    }
}
