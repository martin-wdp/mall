package com.ningpai.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.ningpai.api.bean.OGoodsProduct;
import com.ningpai.api.dao.IGoodsProductMapper;
import com.ningpai.api.service.IGoodsProductService;
import com.ningpai.api.util.OpenPageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lih
 * @version 2.0
 * @since 15/8/25
 */
@Service("OpenGoodsProductService")
public class GoodsProductServiceImpl implements IGoodsProductService {

    /**
     * 开放接口--货品dao
     */
    @Resource(name = "OpenGoodsProductMapper")
    private IGoodsProductMapper goodsProductMapper;

    /**
     * 获取货品列表
     * 
     * @param thirdId
     *            第三方编号
     * @param goodsId
     * @param pageNo
     *            页数(默认1)
     * @param pageSize
     *            每页条数（默认15条）
     *            签名 加密方式 thirdId+goodsId+"goods.get"+key 通过md5方法 为空则不加入签名
     * @return 货品列表
     */
    @Override
    public String list(String thirdId, Long goodsId, Integer pageNo, Integer pageSize) {
        Integer pageNoNew = pageNo;
        Integer pageSizeNew = pageSize;
        // 设置参数容器
        Map<String, Object> map = new HashMap<String, Object>();
        // 设置删除标记

        // 设置商品idmap.put("goodsInfoDelflag", "0");

        map.put("goodsId", goodsId);
        // 设置商家id
        map.put("thirdId", thirdId);
        //判断是否为空，如果为空，设为1
        if(pageNoNew==null){
            pageNoNew=1;
        }
        //判断每页数量是否为空
        if(pageSizeNew==null){
            pageSizeNew=15;
        }
        //获取总数量
        int count=goodsProductMapper.count(map);
        OpenPageBean pageBean=new OpenPageBean();
        //设置每页大小
        pageBean.setPageSize(pageSizeNew);
        //设置页数
        pageBean.setPageNo(pageNoNew);
        //设置总行数
        pageBean.setRows(count);
        //设置开始行数
        map.put("start",pageBean.getStartRowNum());
        //设置结束行数
        map.put("end", pageBean.getEndRowNum());

        // 获取货品列表
        List<OGoodsProduct> iGoodsProducts = goodsProductMapper.list(map);
        // 设置返回结果
        Map<String, Object> items = new HashMap<String, Object>();
        // 设置集合
        items.put("item", iGoodsProducts);
        // 设置返回行数
        items.put("total_results", iGoodsProducts.size());
        return JSON.toJSONString(items);
    }

    /**
     * 根据货品编号查询货品信息
     *
     * @param goodsInfoItemNo
     *            货品编号
     * @return 货品信息
     */
    @Override
    public String get(String goodsInfoItemNo) {
        OGoodsProduct o=goodsProductMapper.get(goodsInfoItemNo);
        return JSON.toJSONString(o);
    }

}
