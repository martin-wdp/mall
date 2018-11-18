package com.ningpai.site.thirdseller.service.impl;

import com.ningpai.site.thirdseller.bean.CollectionSeller;
import com.ningpai.site.thirdseller.bean.ThirdStoreInfo;
import com.ningpai.site.thirdseller.dao.CollectionSellerMapper;
import com.ningpai.site.thirdseller.service.CollectionSellerService;
import com.ningpai.util.PageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 店铺集合控制器
 * @Description 店铺集合控制器
 * @author Songhl
 * @since 2015年8月28日 15:26:54
 */
@Service("CollectionSellerService")
public class CollectionSellerServiceImpl implements CollectionSellerService {

    @Resource(name="CollectionSellerMapper")
    private CollectionSellerMapper collectionSellerMapper;

    @Override
    public ThirdStoreInfo selectStoreByCustomerId(Long storeId) {
       return collectionSellerMapper.selectStoreByCustomerId(storeId);
    }

    /**
     * 收藏此商家
     * @param collectionSeller
     * @return
     */
    @Override
    public int addCollectionSeller(CollectionSeller collectionSeller) {
        
        int count = collectionSellerMapper.selectCollectionSeller(collectionSeller);
        if(count==0){
            collectionSeller.setCollectionCreateTime(new Date());
            collectionSeller.setCollectionDelFlag("0");
            return collectionSellerMapper.addCollectionSeller(collectionSeller);
        }else{
            return 2;//已经存在
        }
    }

    /**
     * 查询商家列表
     * @param customerId
     * @param pageBean
     * @return
     */
    @Override
    public PageBean sellerMyFollw(Long customerId, PageBean pageBean) {
        
        Map<String,Object> paramMap =new HashMap<String, Object>();
        try{
            paramMap.put("collectionCustomerId", customerId);
            //设置总行数
            pageBean.setRows(collectionSellerMapper.sellerMyFollwCount(paramMap));
    
            paramMap.put("startRowNum",pageBean.getStartRowNum());
            paramMap.put("endRowNum", pageBean.getEndRowNum());
//          pageBean.setList(decorateMapper.getAll(paramMap));
            pageBean.setList(collectionSellerMapper.sellerMyFollwList(paramMap));
        }finally{
            paramMap=null;
        }
        return pageBean;
    }

    /**
     * 删除收藏
     * @param customerId
     * @param collectionSellerId
     * @return
     */
    @Override
    public int delMyFollw(Long customerId, Long collectionSellerId) {
        
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("collectionCustomerId", customerId);
        paramMap.put("collectionSellerId", collectionSellerId);
        return collectionSellerMapper.delMyFollw(paramMap);
    }

}
