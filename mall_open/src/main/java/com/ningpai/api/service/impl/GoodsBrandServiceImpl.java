package com.ningpai.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.ningpai.api.bean.OGoodsBrand;
import com.ningpai.api.dao.IGoodsBrandMapper;
import com.ningpai.api.service.IGoodsBrandService;
import com.ningpai.api.util.OpenPageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * 开放接口--商品品牌
 * @author lih
 * @version 2.0
 * @since 15/9/2
 */
@Service("openGoodsBrandService")
public class GoodsBrandServiceImpl implements IGoodsBrandService{

    @Resource(name="openGoodsBrandMapper")
    private IGoodsBrandMapper goodsBrandMapper;

    /**
     * 获取订单列表
     *
     * @param pageNo   页数
     * @param pageSize 每页大小
     * @return
     */
    @Override
    public String list(Integer pageNo, Integer pageSize) {
        Integer pageNoNew = pageNo;
        Integer pageSizeNew = pageSize;
        //判断是否为空，如果为空，设为1
        if(pageNoNew==null){
            pageNoNew=1;
        }
        //判断每页数量是否为空
        if(pageSizeNew==null){
            pageSizeNew=15;
        }

        //获取总数量
        int count=goodsBrandMapper.count();
        OpenPageBean pageBean=new OpenPageBean();
        //设置每页大小
        pageBean.setPageSize(pageSizeNew);
        //设置页数
        pageBean.setPageNo(pageNoNew);
        //设置总行数
        pageBean.setRows(count);
        //设置参数容器
        Map<String,Object> map=new HashMap<String,Object>();
        //设置开始行数
        map.put("start",pageBean.getStartRowNum());
        //设置结束行数
        map.put("end", pageBean.getEndRowNum());
        List<OGoodsBrand> oGoodsBrands= goodsBrandMapper.list(map);
        //设置返回结果
        Map<String,Object> items=new HashMap<String,Object>();
        //设置集合
        items.put("item",oGoodsBrands);
        //设置返回行数
        items.put("total_results", oGoodsBrands.size());
        return JSON.toJSONString(items);
    }

}
