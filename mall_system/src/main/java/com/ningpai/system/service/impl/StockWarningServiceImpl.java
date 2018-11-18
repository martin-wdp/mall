package com.ningpai.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ningpai.system.bean.StockWarning;
import com.ningpai.system.dao.StockWarningMapper;
import com.ningpai.system.service.StockWarningService;
import com.ningpai.system.util.StorkWarningUtil;
import com.ningpai.util.PageBean;
import com.ningpai.util.SelectBean;

/**
 * 库存预警下限查询业务层实现类
 * 
 * @author xiaogu
 *
 */

@Service("StockWarningService")
public class StockWarningServiceImpl implements StockWarningService {

    private static final String STARTROWNUM = "startRowNum";

    private static final String ENDROWNUM = "endRowNum";

    private StockWarningMapper stockwarningMapper;

    public StockWarningMapper getStockwarningMapper() {
        return stockwarningMapper;
    }

    @Resource(name = "StockWarningMapper")
    public void setStockwarningMapper(StockWarningMapper stockwarningMapper) {
        this.stockwarningMapper = stockwarningMapper;
    }

    @Override
    public StockWarning select() {

        return this.stockwarningMapper.select();
    }

    @Override
    public int update(StockWarning sw) {

        return this.stockwarningMapper.update(sw);
    }

    @Override
    public PageBean selectGoods(StorkWarningUtil storkWarningUtil, PageBean pageBean) {
        // 查询行数
        Map<String, Object> paramMap = new HashMap<String, Object>();
        List<Object> goodsList = new ArrayList<Object>();
        int no = 0;
        try {
            paramMap.put(STARTROWNUM, pageBean.getStartRowNum());
            paramMap.put(ENDROWNUM, pageBean.getEndRowNum());
            paramMap.put("id", storkWarningUtil.getId());
            paramMap.put("goodname", storkWarningUtil.getGoodname());
            paramMap.put("number", storkWarningUtil.getNumber());
            paramMap.put("storename", storkWarningUtil.getStorename());
            paramMap.put("stock", storkWarningUtil.getStock());
            paramMap.put("warename", storkWarningUtil.getWarename());
            paramMap.put("wareid", storkWarningUtil.getWareid());
            paramMap.put("thirdid", storkWarningUtil.getThirdid());
            paramMap.put("stockWarnSpec", storkWarningUtil.getStockWarnSpec());
            paramMap.put("goodsName", storkWarningUtil.getGoodsName());
            paramMap.put("goodsNo", storkWarningUtil.getGoodsNo());
            paramMap.put("isThird", storkWarningUtil.getIsThird());

            goodsList = this.stockwarningMapper.selectGoods(paramMap);
            pageBean.setList(goodsList);
            int rows = this.stockwarningMapper.selectcount(paramMap);
            if (rows > 0) {
                pageBean.setRows(rows);
            } else {
                pageBean.setRows(0);
            }
            no = pageBean.getRows() % pageBean.getPageSize() == 0 ? pageBean.getRows() / pageBean.getPageSize() : (pageBean.getRows() / pageBean.getPageSize() + 1);
            no = no == 0 ? 1 : no;

            // 若页码超过最大页码，则显示最后一个
            if (pageBean.getPageNo() >= no) {
                pageBean.setPageNo(no);
                pageBean.setStartRowNum((no - 1) * pageBean.getPageSize());
            }
        } finally {
            paramMap = null;
        }
        return pageBean;
    }

    @Override
    public PageBean selectWare(StorkWarningUtil storkWarningUtil, PageBean pageBean) {
        // 查询行数
        Map<String, Object> paramMap = new HashMap<String, Object>();
        List<Object> goodsList = new ArrayList<Object>();
        int no = 0;
        try {
            paramMap.put(STARTROWNUM, pageBean.getStartRowNum());
            paramMap.put(ENDROWNUM, pageBean.getEndRowNum());
            paramMap.put("id", storkWarningUtil.getId());
            paramMap.put("goodname", storkWarningUtil.getGoodname());
            paramMap.put("number", storkWarningUtil.getNumber());
            paramMap.put("storename", storkWarningUtil.getStorename());
            paramMap.put("stock", storkWarningUtil.getStock());
            paramMap.put("warename", storkWarningUtil.getWarename());
            paramMap.put("wareid", storkWarningUtil.getWareid());
            paramMap.put("thirdid", storkWarningUtil.getThirdid());
            goodsList = this.stockwarningMapper.selectWare(paramMap);
            pageBean.setList(goodsList);
            int rows = this.stockwarningMapper.selectcounts(paramMap);
            if (rows > 0) {
                pageBean.setRows(rows);
            } else {
                pageBean.setRows(0);
            }
            no = pageBean.getRows() % pageBean.getPageSize() == 0 ? pageBean.getRows() / pageBean.getPageSize() : (pageBean.getRows() / pageBean.getPageSize() + 1);
            no = no == 0 ? 1 : no;

            // 若页码超过最大页码，则显示最后一个
            if (pageBean.getPageNo() >= no) {
                pageBean.setPageNo(no);
                pageBean.setStartRowNum((no - 1) * pageBean.getPageSize());
            }
        } finally {
            paramMap = null;
        }
        return pageBean;
    }

    @Override
    public StorkWarningUtil selectbyId(Long wareid) {

        return stockwarningMapper.selectbyId(wareid);
    }

    @Override
    @Transactional
    public int updatestock(StorkWarningUtil storkWarningUtil) {

        return stockwarningMapper.updatestock(storkWarningUtil);
    }

    @Override
    public PageBean selectgoodLists(PageBean pageBean, SelectBean selectBean) {
        // 查询行数
        Map<String, Object> paramMap = new HashMap<String, Object>();
        int no = 0;
        try {
            paramMap.put("condition", selectBean.getCondition());
            paramMap.put("searchText", selectBean.getSearchText());

            paramMap.put("goodsName", selectBean.getGoodsName());
            paramMap.put("goodsNo", selectBean.getGoodsNo());

            paramMap.put(STARTROWNUM, pageBean.getStartRowNum());
            paramMap.put(ENDROWNUM, pageBean.getEndRowNum());
            pageBean.setList(this.stockwarningMapper.selectGoodsbyname(paramMap));
            int rows = this.stockwarningMapper.selectcountbyname(paramMap);
            if (rows > 0) {
                pageBean.setRows(rows);
            } else {
                pageBean.setRows(0);
            }
            no = pageBean.getRows() % pageBean.getPageSize() == 0 ? pageBean.getRows() / pageBean.getPageSize() : (pageBean.getRows() / pageBean.getPageSize() + 1);
            no = no == 0 ? 1 : no;

            // 若页码超过最大页码，则显示最后一个
            if (pageBean.getPageNo() >= no) {
                pageBean.setPageNo(no);
                pageBean.setStartRowNum((no - 1) * pageBean.getPageSize());
            }
        } finally {
            paramMap = null;
        }
        return pageBean;
    }

}
