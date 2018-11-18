/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.thirdaudit.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.ningpai.customer.bean.Customer;
import com.ningpai.customer.dao.CustomerMapper;
import org.springframework.stereotype.Service;

import com.ningpai.thirdaudit.bean.StoreInfo;
import com.ningpai.thirdaudit.mapper.StoreInfoMapper;
import com.ningpai.thirdaudit.service.StoreInfoService;
/**
 * 商家信息服务层接口实现类
 * */
@Service("StoreService")
public class StoreInfoServiceImpl implements StoreInfoService {

    @Resource(name = "storeMapper")
    private StoreInfoMapper storeInfoMapper;
    @Resource(name = "customerMapper")
    private CustomerMapper customerMapper;

    /*
     * 
     * 
     * @see
     * com.ningpai.thirdaudit.service.StoreInfoService#queryAllStoreInfo(com
     * .ningpai.util.PageBean)
     * 
     * @Override public PageBean queryAllStoreInfo(PageBean pb) { // Map<String,
     * Object> pmap = new HashMap<String, Object>();
     * pmap.put("storeInfo",pb.getObjectBean());
     * pb.setRows(storeInfoMapper.selectCount(pmap)); pmap.put("startRowNum",
     * pb.getStartRowNum()); pmap.put("endRowNum", pb.getEndRowNum());
     * pb.setList(storeInfoMapper.queryAllStoreInfo(pmap)); return pb; }
     * 
     * 
     * 
     * 
     * @see
     * com.ningpai.thirdaudit.service.StoreInfoService#queryStoreByMallIdOrCityId
     * (com.ningpai.util.PageBean, java.lang.Long, java.lang.Long,
     * java.lang.Long, java.lang.String)
     * 
     * @Override public PageBean queryStoreByMallIdOrCityId(PageBean pageBean,
     * Long cityId, Long mallId, Long orderId,String title) { Map<String,Object>
     * pmap = new HashMap<String, Object>(); pmap.put("title", title);
     * pmap.put(ConstantUtil.ORDERID, orderId); pmap.put("mallId", mallId);
     * pmap.put("cityId", cityId);
     * pageBean.setRows(storeInfoVoMapper.selectStoreCountBySelective(pmap));
     * pmap.put("startRowNum", pageBean.getStartRowNum()); pmap.put("endRowNum",
     * pageBean.getEndRowNum());
     * pageBean.setList(storeInfoVoMapper.selectStoreBySelective(pmap)); return
     * pageBean; }
     * 
     * 
     * 
     * 
     * @see com.ningpai.thirdaudit.service.StoreInfoService#
     * queryStoreByrecommendedHomePage()
     * 
     * @Override public List<StoreInfoVo> queryStoreByrecommendedHomePage() {
     * return storeInfoVoMapper.selectStoreByrecommendedHomePage(); }
     *//**
     * 查询商家
     */
    /*
     * @Override public StoreInfo queryStoreInfoBySellerId(Long sellerId) {
     * 
     * return this.storeInfoMapper.queryStoreInfoBySellerId(sellerId); }
     */
    /**
     * 删除商家
     *
     * @param storeInfoIds
     * @return
     */
    @Override
    public int delStoreInfoById(String[] storeInfoIds, String username) {
        if (storeInfoIds != null && storeInfoIds.length > 0) {
            Long[] storeinfoIds = new Long[storeInfoIds.length];
            for (int i = 0; i < storeInfoIds.length; i++) {
                storeinfoIds[i] = Long.parseLong(storeInfoIds[i]);
                // 获取单个店铺的信息
                StoreInfo storeInfo = storeInfoMapper
                        .queryStoreBalanceByThirdId(storeinfoIds[i]);
                // 查询删除店铺的会员信息 把该会员改为普通会员 清除掉third
                Customer customer = customerMapper.getCustomerByCusId(storeInfo
                        .getCustomerid());
                // 删除商家员工
                customerMapper.deleteEmp(storeinfoIds[i]);
                storeInfoMapper.delmanagerauthority(storeInfo.getCustomerid());
                customer.setIsSeller("0"); // 是否是商家
                customer.setThirdId(null);

                // 更新会员信息
                customerMapper.updateCustomer(customer);
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("username", username);
            map.put("deltime", new Date());
            map.put("idItems", storeInfoIds);
            storeInfoMapper.deleGoodsInfo(map);
            storeInfoMapper.deleGoods(map);
            return this.storeInfoMapper.delStoreInfoById(storeinfoIds);
        } else {
            return 0;
        }
    }
    /**
     * 根据商家id查询
     *
     * @param storeId
     * */
    @Override
    public StoreInfo queryStorePointByThirdId(Long storeId) {

        return storeInfoMapper.queryStorePointByThirdId(storeId);
    }
    /**
     * 高级查询
     *
     * */
    @Override
    public int upStorePointByThirdId(Map<String, Object> paramMap) {

        return storeInfoMapper.upStorePointByThirdId(paramMap);
    }
    /**
     *根据map修改记录
     *
     * @param paramMap
     * */
    @Override
    public int upStoreBalanceByThirdId(Map<String, Object> paramMap) {

        return storeInfoMapper.upStoreBalanceByThirdId(paramMap);
    }
    /**
     * 查询记录
     *
     * @param storeId
     * */
    @Override
    public StoreInfo queryStoreBalanceByThirdId(Long storeId) {

        return storeInfoMapper.queryStoreBalanceByThirdId(storeId);
    }

}
