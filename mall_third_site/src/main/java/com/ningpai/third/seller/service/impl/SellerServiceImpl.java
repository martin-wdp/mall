/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.third.seller.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.ningpai.third.seller.mapper.ApplyBrandMapper;
import com.ningpai.util.MyLogger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.ningpai.customer.dao.CustomerMapper;
import com.ningpai.other.bean.CustomerAllInfo;
import com.ningpai.third.goods.service.ThirdOtherService;
import com.ningpai.third.seller.bean.StoreInfo;
import com.ningpai.third.seller.bean.ThirdMessageModel;
import com.ningpai.third.seller.bean.ThirdStoreMess;
import com.ningpai.third.seller.mapper.StoreInfoMapper;
import com.ningpai.third.seller.mapper.ThirdMessageModelMapper;
import com.ningpai.third.seller.service.SellerService;
import com.ningpai.util.UploadUtil;

/**
 * @author NINGPAI-zhangqiang
 * @version 0.0.1
 * @see com.ningpai.third.seller.service.SellerService
 * @since 2014年5月5日 下午6:27:05
 */
@Service("sellerService")
public final class SellerServiceImpl implements SellerService {

    private static final String CARDURLE = "cardUrlE";
    private static final String BUSSURLE = "bussUrlE";
    private static final String COMPANYRESEARCHURLE = "companyResearchUrlE";
    private static final String BUSSTAXCREDURLE = "bussTaxCredUrlE";
    private static final String BUSSTAXREGISTURLE = "bussTaxRegistUrlE";
    private static final String BANKURLE = "bankUrlE";
    private static final String THIRDID = "thirdId";

    /**
     * 日志
     * */
    public static final MyLogger LOGGER = new MyLogger(SellerServiceImpl.class);

    /**
     * 店铺接口类
     */
    private StoreInfoMapper sotreInfoMapper;

    /**
     * 自定义品牌接口
     */
    private ApplyBrandMapper applyBrandMapper;

    /**
     * 第三方接受
     */
    private ThirdMessageModelMapper thirdMessageModelMapper;

    /**
     * 会员接口
     */
    private CustomerMapper customerMapper;

    /**
     * 第三方Other service
     */
    private ThirdOtherService thirdOtherService;

    /**
     * 根据会员ID查询单个的店铺信息
     *
     * @param customerId
     *            商家会员编号
     * @return
     */
    @Override
    public StoreInfo selectByEmployeeId(Long customerId) {
        return sotreInfoMapper.selectByEmployeeId(customerId);
    }

    /**
     * 根据会员ID查询单个的店铺信息
     *
     * @param customerId
     *            商家会员编号
     * @return
     */
    public StoreInfo selectByCustomerId(Long customerId) {
        return sotreInfoMapper.selectByCustomerId(customerId);
    }

    /**
     * 跟新单个的店铺信息
     *
     * @param storeInfo
     *            商家信息 {@link StoreInfo}
     * @param multipartRequest
     * @param request
     * @return
     */
    public int updateByStoreInfo(StoreInfo storeInfo, MultipartHttpServletRequest multipartRequest, HttpServletRequest request) {
        // 上传文件 并且填充商家信息相应属性
        fillFileURL(storeInfo, multipartRequest, request);
        return sotreInfoMapper.updateByPrimaryKeySelective(storeInfo);
    }

    /**
     * 上传文件 并且填充商家信息相应属性
     *
     * @param storeInfo
     *            商家信息 {@link StoreInfo}
     * @param multipartRequest
     * @param request
     */
    private void fillFileURL(StoreInfo storeInfo, MultipartHttpServletRequest multipartRequest, HttpServletRequest request) {
        // 身份证
        if (multipartRequest.getFile(CARDURLE) != null && !"".equals(multipartRequest.getFile(CARDURLE).getOriginalFilename())) {
            // 使用上传后路径
            storeInfo.setCardUrl(UploadUtil.uploadFileOne(multipartRequest.getFile(CARDURLE), request));
        }
        // 电子执照
        if (multipartRequest.getFile(BUSSURLE) != null && !"".equals(multipartRequest.getFile(BUSSURLE).getOriginalFilename())) {
            // 使用上传后路径
            storeInfo.setBussUrl(UploadUtil.uploadFileOne(multipartRequest.getFile(BUSSURLE), request));
        }

        // 组织结构代码
        if (multipartRequest.getFile(COMPANYRESEARCHURLE) != null && !"".equals(multipartRequest.getFile(COMPANYRESEARCHURLE).getOriginalFilename())) {
            // 使用上传后路径
            storeInfo.setCompanyResearchUrl(UploadUtil.uploadFileOne(multipartRequest.getFile(COMPANYRESEARCHURLE), request));
        }
        // 一般纳税人资格证书
        if (multipartRequest.getFile(BUSSTAXCREDURLE) != null && !"".equals(multipartRequest.getFile(BUSSTAXCREDURLE).getOriginalFilename())) {
            // 使用上传后路径
            storeInfo.setBussTaxCredUrl(UploadUtil.uploadFileOne(multipartRequest.getFile(BUSSTAXCREDURLE), request));
        }
        // 税务登记电子版
        if (multipartRequest.getFile(BUSSTAXREGISTURLE) != null && !"".equals(multipartRequest.getFile(BUSSTAXREGISTURLE).getOriginalFilename())) {
            // 使用上传后路径
            storeInfo.setBussTaxRegistUrl(UploadUtil.uploadFileOne(multipartRequest.getFile(BUSSTAXREGISTURLE), request));
        }
        // 银行开户许可证
        if (multipartRequest.getFile(BANKURLE) != null && !"".equals(multipartRequest.getFile(BANKURLE).getOriginalFilename())) {
            // 使用上传后路径
            storeInfo.setBankUrl(UploadUtil.uploadFileOne(multipartRequest.getFile(BANKURLE), request));
        }
    }

    /**
     * 根据店铺ID查询 第三方消息接收
     *
     * @param storeId
     *            商家编号 {@link Long}
     * @return
     */
    public List<ThirdMessageModel> selectMessByStoreId(Long storeId) {
        return thirdMessageModelMapper.selectAllMessModel(storeId);
    }

    /**
     * 模块消息接收方式
     *
     * @param request
     * @param mid
     *            模块编号
     * @return
     */
    public ThirdStoreMess queryStoreMessBySidAndMid(HttpServletRequest request, Long mid) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        try {
            paramMap.put("storeId", (Long) request.getSession().getAttribute(THIRDID));
            paramMap.put("messModId", mid);
            return sotreInfoMapper.queryStoreMessBySidAndMid(paramMap);
        } finally {
            paramMap = null;
        }

    }

    /**
     * 修改单个店铺信息
     *
     * @param request
     * @param mess
     *            消息设置 {@link ThirdStoreMess}
     * @return
     */
    public int updateStoreMess(HttpServletRequest request, ThirdStoreMess mess) {
        mess.setStoreId((Long) request.getSession().getAttribute(THIRDID));
        return sotreInfoMapper.updateStoreMess(mess);
    }

    /**
     * 添加消息接收设置
     *
     * @param request
     * @param mess
     *            消息设置 {@link ThirdStoreMess}
     * @return
     */
    public int addStoreMess(HttpServletRequest request, ThirdStoreMess mess) {
        mess.setStoreId((Long) request.getSession().getAttribute(THIRDID));
        return sotreInfoMapper.addStoreMess(mess);
    }

    /**
     * 新增商铺信息
     *
     * @param request
     * @param storeInfo
     *            商家信息 {@link StoreInfo}
     * @param multipartRequest
     * @return
     */
    @Transactional
    public Long saveStoreInfo(HttpServletRequest request, StoreInfo storeInfo, MultipartHttpServletRequest multipartRequest) {
        StoreInfo sinfo = null;
        CustomerAllInfo customer = new CustomerAllInfo();
        try {
            // 防止前台人为改动商家编号和会员编号 判断商家编号/会员编号是否为空
            if (storeInfo.getStoreId() == null) {
                if (storeInfo.getCustomerid() == null) {
                    storeInfo.setCustomerid((Long) request.getSession().getAttribute("customerId"));
                }
                // 上传文件 并且填充商家信息相应属性
                fillFileURL(storeInfo, multipartRequest, request);
                // 添加前先查找记录是否存在 存在则修改 不存在则为添加
                sinfo = sotreInfoMapper.selectByCustomerId(storeInfo.getCustomerid());
                if (sinfo == null) {
                    storeInfo.setIsSubmit("0");
                    sotreInfoMapper.insertSelective(storeInfo);
                    customer.setThirdId(storeInfo.getStoreId());
                    customer.setCustomerId(storeInfo.getCustomerid());
                    customerMapper.updateByPrimaryKeySelective(customer);
                    return storeInfo.getStoreId();
                } else {
                    String[] appId = request.getParameterValues("appId");
                    Map<String, Object> map = new HashMap<String, Object>();
                    if (null != appId && appId.length > 0) {
                        map.put("storeId", sinfo.getStoreId());
                        map.put("applyBrandIds", appId);
                        applyBrandMapper.updateThirdApplyBrand(map);
                    }
                    storeInfo.setStoreId(sinfo.getStoreId());
                }
            }
            sotreInfoMapper.updateByPrimaryKeySelective(storeInfo);
            return storeInfo.getStoreId();
        } finally {
            sinfo = null;
        }
    }

    /**
     * 新增商铺类型和品牌
     *
     * @param request
     * @param storeId
     *            商家编号 {@link Long}
     * @param cids
     *            商家选中的分类逗号隔开 {@link String}
     * @param bids
     *            商家选中的品牌逗号隔开 {@link String}
     * @return
     */
    public int saveStoreCateAndBrand(HttpServletRequest request, Long storeId, String cids, String bids) {
        String[] cateIds = cids.split("\\|");

        StoreInfo storeInfo = null;
        try {
            for (int i = 0; i < cateIds.length; i++) {
                thirdOtherService.saveGrantCat(storeId, Long.parseLong(cateIds[i]));
            }
            if (bids != null && bids.length() != 0) {
                String[] brandIds = bids.split("\\|");
                for (int i = 0; i < brandIds.length; i++) {
                    thirdOtherService.saveGrantBrand(storeId, Long.parseLong(brandIds[i]));
                }
            }
            storeInfo = sotreInfoMapper.selectByCustomerId((Long) request.getSession().getAttribute("customerId"));
            storeInfo.setIsSubmit("1");
            sotreInfoMapper.updateByPrimaryKeySelective(storeInfo);
        } catch (Exception e) {
            LOGGER.error(""+e);
            return 0;
        } finally {
            cateIds = null;
            storeInfo = null;
        }
        return 1;
    }

    /**
     * 根据会员查询店铺信息
     *
     * @param customerId
     *            会员编号 {@link Long}
     * @return
     */
    public StoreInfo selectRefuseInfo(Long customerId) {
        return sotreInfoMapper.selectRefuseInfo(customerId);
    }

    /**
     * 修改第三方店铺信息-不修改文件相关字段
     *
     * @param storeInfo
     * @return
     * @author NINGPAI-WangHaiYang
     */
    public int updateStoreIndexState(StoreInfo storeInfo) {
        return sotreInfoMapper.updateByPrimaryKeySelective(storeInfo);
    }

    /**
     * 修改店铺信息
     *
     * @param storeInfo
     *            商家信息 {@link StoreInfo}
     * @param customerId
     * @return
     */
    @Override
    public int updateByStoreInfo(StoreInfo storeInfo, Long customerId) {
        storeInfo.setCustomerid(customerId);
        storeInfo.setRefuseContent("");
        return sotreInfoMapper.updateRefuseInfo(storeInfo);
    }

    /**
     * 获取所有的店铺信息
     *
     * @return
     */
    @Override
    public List<StoreInfo> selectAll() {
        return sotreInfoMapper.selectAll();
    }

    /**
     * 查询店铺名称是否已使用
     * 第三方后台开通店铺时调用
     *
     * @param storeName 页面传入的店铺名称
     * @return 查询出的数量
     * @author houyichang  2015/9/18
     */
    @Override
    public int queryCountByStoreName(String storeName) {
        return sotreInfoMapper.queryCountByStoreName(storeName.trim());
    }

    @Override
    public StoreInfo selectByStoreName(String storeName) {
        return sotreInfoMapper.selectByStoreName(storeName.trim());
    }

    /**
     * 根据主键查询单个店铺信息
     *
     * @param storeId
     * @return
     */
    @Override
    public StoreInfo selectByStoreId(Long storeId) {
        return sotreInfoMapper.selectByPrimaryKey(storeId);
    }

    public StoreInfoMapper getSotreInfoMapper() {
        return sotreInfoMapper;
    }

    @Resource(name = "sotreInfoMapper")
    public void setSotreInfoMapper(StoreInfoMapper sotreInfoMapper) {
        this.sotreInfoMapper = sotreInfoMapper;
    }

    public ThirdMessageModelMapper getThirdMessageModelMapper() {
        return thirdMessageModelMapper;
    }

    @Resource(name = "thirdMessageModelMapper")
    public void setThirdMessageModelMapper(ThirdMessageModelMapper thirdMessageModelMapper) {
        this.thirdMessageModelMapper = thirdMessageModelMapper;
    }

    public CustomerMapper getCustomerMapper() {
        return customerMapper;
    }

    @Resource(name = "customerMapper")
    public void setCustomerMapper(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    public ThirdOtherService getThirdOtherService() {
        return thirdOtherService;
    }

    @Resource(name = "ThirdOtherService")
    public void setThirdOtherService(ThirdOtherService thirdOtherService) {
        this.thirdOtherService = thirdOtherService;
    }

    public ApplyBrandMapper getApplyBrandMapper() {
        return applyBrandMapper;
    }

    @Resource(name = "ApplyBrandMapper")
    public void setApplyBrandMapper(ApplyBrandMapper applyBrandMapper) {
        this.applyBrandMapper = applyBrandMapper;
    }
}
