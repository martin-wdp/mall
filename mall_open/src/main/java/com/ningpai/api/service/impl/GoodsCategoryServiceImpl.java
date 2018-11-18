package com.ningpai.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.ningpai.api.bean.OGoodsCategory;
import com.ningpai.api.dao.IGoodsCategoryMapper;
import com.ningpai.api.service.IGoodsCategoryService;
import com.ningpai.api.util.OpenPageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lih
 * @version 2.0
 * @since 15/9/6
 */
@Service("openGoodsCategoryService")
public class GoodsCategoryServiceImpl implements IGoodsCategoryService {



    /**
     * 商品分类dao
     */
    @Resource(name="openGoodsCategoryMapper")
    private IGoodsCategoryMapper mapper;

    /**
     * 获取商品分类列表
     *
     * @param pageNo   页数
     * @param pageSize 每页大小
     * @return
     */
    @Override
    public String     list( Integer pageNo, Integer pageSize) {
        Integer pageNoNew = pageNo;
        Integer pageSizeNew = pageSize;
        //设置参数容器
        Map<String,Object> map = new HashMap<String,Object>();
        //判断是否为空，如果为空，设为1
        if(pageNoNew==null){
            pageNoNew=1;
        }
        //判断每页数量是否为空
        if(pageSizeNew==null){
            pageSizeNew=15;
        }

        //获取总数量
        int count=mapper.count();
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
        //查询商品分类
        List<OGoodsCategory> oGoodsCategories=mapper.list(map);
        //设置返回结果
        Map<String,Object> items=new HashMap<String,Object>();
        //设置集合
        items.put("item",oGoodsCategories);
        //设置返回行数
        items.put("total_results", oGoodsCategories.size());
        return JSON.toJSONString(items);
    }

}
