package com.ningpai.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.ningpai.api.bean.OGoodsProductWare;
import com.ningpai.api.dao.IProductWareMapper;
import com.ningpai.api.service.IProductWareService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 开放接口--商品库存service
 * @author lih
 * @version 2.0
 * @since 15/9/7
 */
@Service("openProductWareService")
public class ProductWareServiceImpl  implements IProductWareService{


    /**
     * 开放接口--商品库存dao
     */
    @Resource(name="openProductWareMapper")
    private IProductWareMapper mapper;

    /**
     * 根据货号查询库存
     *
     * @param goodsInfoItemNo 货品编号
     * @return
     */
    @Override
    public String get(String goodsInfoItemNo) {

        if(goodsInfoItemNo==null){
            return "商品编号不能为空";
        }
        //获取库存列表
        List<OGoodsProductWare> oGoodsProductWares=mapper.get(goodsInfoItemNo);
        //设置容器
        Map<String,Object> items=new HashMap<String,Object>();
        //设置集合
        items.put("item",oGoodsProductWares);
        //设置集合大小
        items.put("total_results", oGoodsProductWares.size());
        return JSON.toJSONString(items);
    }

    /**
     * 添加库存
     *
     * @param goodsInfoItemNo 货品编号
     * @param identifyNo      仓库标识
     * @param count           减去的数量
     * @param sign            签名 goodsInfoItemNo+IdentifyNo+count+productware.addstock 通过MD5方式进行加密
     * @return 添加结果 1；成功 0：签名错误 2：商品编号为空
     */
    @Override
    public int addStock(String goodsInfoItemNo, String identifyNo, Long count) {
        //商品编号不能为空
        if(goodsInfoItemNo==null){
            return 2;
        }
        //设置容器
        Map<String,Object> map=new HashMap<String,Object>();
        //商品编号
        map.put("goodsInfoItemNo",goodsInfoItemNo);
        //仓库标识
        map.put("identifyId",identifyNo);
        //数量
        map.put("count",count);
        return mapper.addStock(map);
    }

    /**
     * 删除库存
     *
     * @param goodsInfoItemNo 货品编号
     * @param identifyNo      仓库标识
     * @param count           减去的数量
     * @return 减库存结果 1；成功 0：签名错误 2：商品编号为空
     */
    @Override
    public int minStock(String goodsInfoItemNo, String identifyNo, Long count) {
        //商品编号不能为空
        if(goodsInfoItemNo==null){
            return 2;
        }
        //设置容器
        Map<String,Object> map=new HashMap<String,Object>();
        //商品编号
        map.put("goodsInfoItemNo",goodsInfoItemNo);
        //仓库标识
        map.put("identifyId",identifyNo);
        //数量
        map.put("count",count);
        return mapper.minStock(map);
    }
}
