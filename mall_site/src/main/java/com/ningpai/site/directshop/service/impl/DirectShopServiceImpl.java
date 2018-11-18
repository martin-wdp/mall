package com.ningpai.site.directshop.service.impl;

import com.ningpai.site.directshop.bean.DirectShop;
import com.ningpai.site.directshop.dao.DirectshopMapper;
import com.ningpai.site.directshop.service.DirectShopService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 移动端直营店Service接口实现类
 * @author qiyuanyuan
 *
 */
@Service("SiteDirectShopService")
public class DirectShopServiceImpl implements DirectShopService {
    
    @Resource(name="SiteDirectshopMapper")
    private DirectshopMapper directshopMapper;

    /*
     * 根据直营店ID查询直营店信息
     * @see com.ningpai.directshop.service.DirectShopService#selectInfoById(java.lang.Long)
     */
    @Override
    public DirectShop selectInfoById(Long directShopId) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("directShopStatus", "1");
        paramMap.put("directShopId", directShopId);
        return directshopMapper.selectInfoById(paramMap);
    }

    /*
     * 根据区县id查询直营店列表
     * @see com.ningpai.directshop.service.DirectShopService#queryDirectShopList(java.lang.Long)
     */
    @Override
    public List<DirectShop> queryDirectShopList(Long countyId) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("directShopStatus", "1");
        paramMap.put("countyId", countyId);
        return directshopMapper.directShops(paramMap);
    }

}
