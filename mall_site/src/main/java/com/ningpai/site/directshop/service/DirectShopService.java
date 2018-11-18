package com.ningpai.site.directshop.service;

import com.ningpai.site.directshop.bean.DirectShop;

import java.util.List;

/**
 * 直营店Service接口
 * @author qiyuanyuan
 *
 */
public interface DirectShopService {

   /**
    * 根据直营店ID查询直营店信息 
    * @param directShopId 直营店id
    * @return 对象
    */
   DirectShop selectInfoById(Long directShopId);
   
   /**
    * 根据区县id查询直营店列表
    * @param countyId 区县列表
    * @return List
    */
   List<DirectShop> queryDirectShopList(Long countyId);
}
