/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.third.goods.service.impl;

import com.ningpai.third.goods.dao.ThirdWarnGoodMapper;
import com.ningpai.third.goods.service.ThirdWarnGoodService;
import com.ningpai.third.goods.vo.StockWarningVo;
import com.ningpai.util.PageBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 商家货品预警接口实现类
 */
@Service("ThirdWarnGoodService")
public class ThirdWarnGoodServiceImpl implements ThirdWarnGoodService {
    /**
     * 货品预警接口
     */
    private ThirdWarnGoodMapper thirdWarnGoodMapper;
    /**
     * 货品预警接口GET方法
     */
    public ThirdWarnGoodMapper getThirdWarnGoodMapper() {
        return thirdWarnGoodMapper;
    }
    /**
     * 货品预警接口SET方法
     */
    @Resource(name = "ThirdWarnGoodMapper")
    public void setThirdWarnGoodMapper(ThirdWarnGoodMapper thirdWarnGoodMapper) {
        this.thirdWarnGoodMapper = thirdWarnGoodMapper;
    }

    /**
     * 查询第三方库存预警值
     * @param thirdId 商家ID
     * @return
     */
    @Override
    public StockWarningVo selectstock(Long thirdId) {
        return thirdWarnGoodMapper.selectstock(thirdId);
    }

    /**
     * 更新库存下限
     * @param stockWarningVo 货品预警信息
     * @return
     */
    @Override
    @Transactional
    public int updatestockgoods(StockWarningVo stockWarningVo) {
        return thirdWarnGoodMapper.updatestockgoods(stockWarningVo);
    }

    /**
     * 查询第三方预警货品
     * @param stockWarningVo 货品预警信息
     * @param pb
     * @return
     */
    @Override
    public PageBean selectwarngoods(StockWarningVo stockWarningVo, PageBean pb) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        // List<Object> goodsList=new ArrayList<Object>();
        int no = 0;
        try {
            // 筛选的起始行数
            paramMap.put("startRowNum", pb.getStartRowNum());
            // 筛选的结束行数
            paramMap.put("endRowNum", pb.getEndRowNum());
            // 商家ID
            paramMap.put("infoid", stockWarningVo.getInfoid());
            // 商品名称
            paramMap.put("goodname", stockWarningVo.getGoodname());
            // 货品的编号
            paramMap.put("number", stockWarningVo.getNumber());
            // 货品销售价格
            paramMap.put("salprice", stockWarningVo.getSalprice());
            // 货品的库存
            paramMap.put("stock", stockWarningVo.getStock());
            // 货品进价
            paramMap.put("buyprice", stockWarningVo.getBuyprice());
            // 商家ID
            paramMap.put("thirdid", stockWarningVo.getThirdid());
            // 查询库存预警货品记录数
            int rows = this.thirdWarnGoodMapper.selectwarncount(paramMap);
            if (rows > 0) {
                // 设置分页的数据条数
                pb.setRows(rows);
            } else {
                pb.setRows(0);
            }
            // 进行分页处理
            no = pb.getRows() % pb.getPageSize() == 0 ? pb.getRows()
                    / pb.getPageSize() : (pb.getRows() / pb.getPageSize() + 1);
            no = no == 0 ? 1 : no;

            // 若页码超过最大页码，则显示最后一个
            if (pb.getPageNo() >= no) {
                pb.setPageNo(no);
                pb.setStartRowNum((no - 1) * pb.getPageSize());
            }
            // 装载数据（根据条件筛选出的货品信息）
            pb.setList(this.thirdWarnGoodMapper.selectwarngoods(paramMap));
        } finally {
            paramMap = null;
        }
        return pb;
    }

    /**
     * 更新库存
     * @param stockWarningVo  货品预警信息
     * @return
     */
    @Override
    public int updatewarnstock(StockWarningVo stockWarningVo) {
        return thirdWarnGoodMapper.updatewarnstock(stockWarningVo);
    }

}
