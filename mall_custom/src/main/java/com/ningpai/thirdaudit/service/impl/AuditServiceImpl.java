/**
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.thirdaudit.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ningpai.other.util.CustomerConstantStr;
import com.ningpai.thirdaudit.bean.DeduBrokeage;
import com.ningpai.thirdaudit.bean.DeduBrokeageVo;
import com.ningpai.thirdaudit.bean.StoreInfo;
import com.ningpai.thirdaudit.mapper.DeduBrokeageMapper;
import com.ningpai.thirdaudit.mapper.StoreCommonMapper;
import com.ningpai.thirdaudit.mapper.StoreInfoMapper;
import com.ningpai.thirdaudit.mapper.ThirdManagerAuthorityMapper;
import com.ningpai.thirdaudit.service.AuditService;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;

/**
 * @see com.ningpai.thirdaudit.service.AuditService
 * @author NINGPAI-zhangqiang
 * @since 2014年5月26日 下午4:46:30
 * @version 0.0.1
 */
@Service("auditService")
public class AuditServiceImpl implements AuditService {

    private static final MyLogger LOGGER = new MyLogger(AuditServiceImpl.class);

    private static final String STOREID = "storeId";

    private StoreInfoMapper storeInfoMapper;

    private ThirdManagerAuthorityMapper managerAuthorityMapper;

    private DeduBrokeageMapper deduBrokeageMapper;

    @Resource(name = "StoreCommonMapper")
    private StoreCommonMapper storeCommonMapper;

    /**
     * 查询会员id
     * 
     * @param storeId
     * @return
     */
    @Override
    public int findcid(Long storeId) {
        return storeInfoMapper.findcid(storeId);
    }

    /**
     */
    @Override
    public PageBean selectAuditList(StoreInfo storeInfo, PageBean pageBean) {
        Map<String, Object> paramMap;
        int no = 0;

        // 设置总行数
        pageBean.setRows(Integer.parseInt(storeInfoMapper.selectAuditListSize(storeInfo).toString()));
        no = pageBean.getRows() % pageBean.getPageSize() == 0 ? pageBean.getRows() / pageBean.getPageSize() : (pageBean.getRows() / pageBean.getPageSize() + 1);
        no = no == 0 ? 1 : no;
        // 若页码超过最大页码 则显示最后一个
        if (pageBean.getPageNo() >= no) {
            pageBean.setPageNo(no);
            pageBean.setStartRowNum((no - 1) * pageBean.getPageSize());
        }
        // 设置查询条件
        paramMap = new HashMap<String, Object>();
        if (storeInfo != null) {
            // 如果店铺信息不为空 把店铺信息赋给objectBean
            pageBean.setObjectBean(storeInfo);
        }
        paramMap.put("storeInfo", storeInfo);
        // 分页开始
        paramMap.put("startRowNum", pageBean.getStartRowNum());
        // 分页结束
        paramMap.put("endRowNum", pageBean.getEndRowNum());
        paramMap.put("companyName", storeInfo.getCompanyName());
        // 查询会员信息
        pageBean.setList(storeInfoMapper.selectAuditList(paramMap));

        return pageBean;
    }

    /**
     */
    @Override
    @Transactional
    public Integer updateStore(String[] parameterValues, DeduBrokeage brokeage, String time, String payIds, String storeQi) {
        Integer count = 0;
        Map<String, Object> paramMap = new HashMap<String, Object>();

        paramMap.put("parameterValues", parameterValues);
        paramMap.put("time", time);
        paramMap.put("storeQi", storeQi);
        count = storeInfoMapper.updateStore(paramMap);
        for (int i = 0; i < parameterValues.length; i++) {
            // 分配第三方管理员权限
            paramMap.clear();
            paramMap.put(STOREID, Long.parseLong(parameterValues[i]));
            managerAuthorityMapper.addRecord(paramMap);

            storeInfoMapper.auditBrand(Long.parseLong(parameterValues[i]));

            DeduBrokeage de = new DeduBrokeage();
            de.setThirdId(Long.parseLong(parameterValues[i]));
            String[] brokerages = brokeage.getBrokerage().split(CustomerConstantStr.COMMA);
            String[] dedus = brokeage.getDeduction().split(CustomerConstantStr.COMMA);
            String[] pIds = payIds.split(CustomerConstantStr.COMMA);
            for (int j = 0; j < brokerages.length; j++) {
                de.setBrokerage(brokerages[j]);
                de.setDeduction(dedus[j]);
                de.setPayId(Long.parseLong(pIds[j]));
                // 添加扣率
                deduBrokeageMapper.insertSelective(de);
            }
        }

        return count;
    }

    /**
     */
    @Override
    public StoreInfo selectByCustomerId(Long storeId) {
        StoreInfo storeInfo = storeInfoMapper.selectByStoreId(storeId);
        if (storeInfo != null && storeInfo.getServiceQq() != null) {
            storeInfo.setServiceQq(storeInfo.getServiceQq().split("\\|")[0]);
        }
        return storeInfo;
    }

    /**
     */
    @Override
    @Transactional
    public Integer refuseStore(StoreInfo storeInfo) {
        storeInfo.setIsSubmit("0");
        // 拒绝同时删除签约分类
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("thirdId", storeInfo.getStoreId());
        storeCommonMapper.deleteSellerinfoCate(paramMap);
        // 拒绝同时删除签约品牌
        storeCommonMapper.delThirdbrandBystoreId(storeInfo.getStoreId());
        return storeInfoMapper.refuseStore(storeInfo);
    }

    @Override
    public List<DeduBrokeageVo> selectBrokeageByStoreId(Long storeId) {
        return deduBrokeageMapper.selectBrokeageByStoreId(storeId);
    }

    @Override
    public Integer updatePayMent(Long storeId, String billingCycle) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(STOREID, storeId);
        paramMap.put("billingCycle", billingCycle);
        return storeInfoMapper.updatePayMent(paramMap);
    }

    @Override
    public Integer updatePayWay(Long storeId, Long[] payIds, String deduction, String brokerage) {
        deduBrokeageMapper.deleteByStoreId(storeId);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (brokerage != null) {
            String[] brokerages = brokerage.split(CustomerConstantStr.COMMA);
            String[] dedus = deduction.split(CustomerConstantStr.COMMA);
            for (int i = 0; i < payIds.length; i++) {
                paramMap.put(STOREID, storeId);
                paramMap.put("payId", payIds[i]);
                paramMap.put("deduction", dedus[i]);
                paramMap.put("brokerage", brokerages[i]);
                deduBrokeageMapper.insertByStoreId(paramMap);
            }
        }
        return 1;
    }

    @Override
    public int updateStoreValidTime(String bussDate, Long goodsBelo) {
        Map<String, Object> param = new HashMap<String, Object>();
        String expiry;
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        expiry = bussDate + " " + sdf.format(new Date());
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date expiryTime = null;
        try {
            expiryTime = sf.parse(expiry);
        } catch (ParseException e) {
            LOGGER.error("修改店铺时间时转换时间错误：" + e);
        }
        param.put("expiryTime", expiryTime);
        param.put(STOREID, goodsBelo);
        return storeInfoMapper.updateStoreValidTime(param);
    }

    /**
     */
    @Override
    public StoreInfo selectNameAndIsStoreBySId(Long storeId) {
        return storeInfoMapper.selectNameAndIsStoreBySId(storeId);
    }

    public StoreInfoMapper getStoreInfoMapper() {
        return storeInfoMapper;
    }

    @Resource(name = "storeMapper")
    public void setStoreInfoMapper(StoreInfoMapper storeInfoMapper) {
        this.storeInfoMapper = storeInfoMapper;
    }

    public ThirdManagerAuthorityMapper getManagerAuthorityMapper() {
        return managerAuthorityMapper;
    }

    @Resource(name = "auditManagerAuthorityMapper")
    public void setManagerAuthorityMapper(ThirdManagerAuthorityMapper managerAuthorityMapper) {
        this.managerAuthorityMapper = managerAuthorityMapper;
    }

    public DeduBrokeageMapper getDeduBrokeageMapper() {
        return deduBrokeageMapper;
    }

    @Resource(name = "deduBrokeageMapper")
    public void setDeduBrokeageMapper(DeduBrokeageMapper deduBrokeageMapper) {
        this.deduBrokeageMapper = deduBrokeageMapper;
    }

    @Override
    public int selectStoreTimeByThirdId(Long thirdId) {
        return storeInfoMapper.selectStoreTimeByThirdId(thirdId);
    }

    @Override
    public int setStore(Long setTore, String isShow, Long storeId) {
        return this.storeInfoMapper.setStore(setTore, isShow, storeId);
    }
}
