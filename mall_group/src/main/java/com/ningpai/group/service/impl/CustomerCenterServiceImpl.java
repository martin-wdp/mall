package com.ningpai.group.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.group.bean.CommonBean;
import com.ningpai.group.bean.CommonCustomer;
import com.ningpai.group.bean.Visitors;
import com.ningpai.group.dao.CustomerCenterMapper;
import com.ningpai.group.service.CustomerCenterService;


/**
 * 个人中心Service
 * @author ggn
 *
 */
@Service("CustomerCenterService")
public class CustomerCenterServiceImpl implements CustomerCenterService {

    /**
     * 粉丝Dao
     */
    private CustomerCenterMapper customerCenterMapper;
    /**
     * 查询个人中心信息
     * @param customerId
     * @return Map
     */
    public Map<String, Object> selectCenterMessage(Long customerId) {
        
        Map<String,Object> pa = new HashMap<String,Object>();
        CommonBean cb = new CommonBean();
        //粉丝数
        cb.setFansCount(customerCenterMapper.selectFansCount(customerId));
        //关注数
        cb.setGuanzhu(customerCenterMapper.selectGuanZhuCount(customerId));
        //私信
        cb.setMesCount(customerCenterMapper.selectMesCount(customerId));
        //评论
        cb.setReplyCount(customerCenterMapper.selectReplyCount(customerId));
        //系统
        cb.setSysCount(customerCenterMapper.selectSysCount(customerId));
        //心情
        cb.setMoodCount(customerCenterMapper.selectMoodCount(customerId));
        pa.put("cb", cb);
        
        CommonCustomer cc =customerCenterMapper.selectCommonCustomer(customerId);
        pa.put("cc", cc);
        return pa;
    }

    /**
     * 查询访客信息
     * @param customerId 用户id{@link java.lang.Long}
     * @param number 显示数目
     * @return List
     */
    public List<Visitors> selectVisitors(Long customerId, int number) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        // 用户Id
        paramMap.put("customerId", customerId);
        // 查询数量
        paramMap.put("number", number);
        return customerCenterMapper.selectVisitors(paramMap);
    }

    /**
     * 插入访客信息
     * @param memberId
     * @param customerId
     * @return int
     */
    public int insertVistitor(Long memberId, Long customerId,String customerIp) {
        
        Visitors vi = new Visitors();
        vi.setVisitorscustomerId(memberId);
        vi.setCustomerId(customerId);
        vi.setVisitorsTime(new Date());
        vi.setVisitorsIp(customerIp);
        return customerCenterMapper.insertVisitor(vi);
    }

    /**
     * 查询访客
     * @param memberId 访客Id {@link java.lang.Long}
     * @param customerId 用户Id{@link java.lang.Long}
     * @return Visitors
     */
    public Visitors selectOneVisitors(Long memberId, Long customerId) {
        
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("visitorsCustomerId", customerId);
        paramMap.put("customerId", memberId);
        return customerCenterMapper.selectOneVisitors(paramMap);
    }
    
    /**
     * Get
     * @return CustomerCenterMapper
     */
    public CustomerCenterMapper getCustomerCenterMapper() {
        return customerCenterMapper;
    }
    //spring注入
    @Resource(name="CustomerCenterMapper")
    public void setCustomerCenterMapper(CustomerCenterMapper customerCenterMapper) {
        this.customerCenterMapper = customerCenterMapper;
    }


}
