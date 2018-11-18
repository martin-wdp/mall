package com.ningpai.marketing.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ningpai.marketing.bean.PromotionLogo;
import com.ningpai.marketing.dao.PromotionLogoMapper;
import com.ningpai.marketing.service.PromotionLogoService;
import com.ningpai.util.PageBean;

/**
 * 促销LOGO接口实现类
 * 
 * @author qiyuanyuan
 *
 */
@Service("PromotionLogoService")
public class PromotionLogoServiceImpl implements PromotionLogoService {

    @Resource(name = "PromotionLogoMapper")
    private PromotionLogoMapper logoMapper;

    /*
     * 
     * @see
     * com.ningpai.marketing.service.PromotionLogoService#addPromotionLogo(com
     * .ningpai.marketing.bean.PromotionLogo)
     */
    @Override
    @Transactional
    public int addPromotionLogo(PromotionLogo promotionLogo) {
        promotionLogo.setCreateTime(new Date());
        promotionLogo.setDelFlag("0");
        return logoMapper.insertSelective(promotionLogo);
    }

    /*
     * 
     * @see
     * com.ningpai.marketing.service.PromotionLogoService#updatePromotionLogo
     * (com.ningpai.marketing.bean.PromotionLogo)
     */
    @Override
    @Transactional
    public int updatePromotionLogo(PromotionLogo promotionLogo) {
        promotionLogo.setModifyTime(new Date());
        return logoMapper.updateByPrimaryKeySelective(promotionLogo);
    }

    /*
     * 
     * @see
     * com.ningpai.marketing.service.PromotionLogoService#delAllPromotionLogo
     * (java.lang.Long[])
     */
    @Override
    @Transactional
    public int delAllPromotionLogo(Long[] promotionLogoId) {
        // 定义List集合
        List<Long> list = new ArrayList<Long>();
        for (Long s : promotionLogoId) {
            // 遍历促销LOGOid
            // 加入list集合
            list.add(s);
        }
        // 执行删除促销LOGO操作，返回执行结果
        return logoMapper.delAllPromotionLogo(list);
    }

    /*
     * 
     * @see
     * com.ningpai.marketing.service.PromotionLogoService#queryAllPromotionLogo
     * (com.ningpai.util.PageBean)
     */
    @Override
    public PageBean queryAllPromotionLogo(PageBean pageBean, PromotionLogo logo) {
        // 查询参数集合
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (logo.getPromotionLogoName() != null && !"".equals(logo.getPromotionLogoName())) {
            paramMap.put("promotionLogoName", logo.getPromotionLogoName());
        }
        // 查询促销LOGO数目
        int rows = logoMapper.queryPromotionLogoCount(paramMap);
        if (rows > 0) {
            // 判断促销LOGO数目 大于0 则赋值给分页行数
            pageBean.setRows(rows);
        } else {
            // 否则为0
            pageBean.setRows(0);
        }
        pageBean.setObjectBean(logo);
        // 开始数目
        paramMap.put("start", pageBean.getStartRowNum());
        // 结束数目
        paramMap.put("number", pageBean.getEndRowNum());
        // 将促销LOGO集合赋值给分页List
        pageBean.setList(logoMapper.queryPromotionLogoList(paramMap));
        // 返回分页列表
        return pageBean;
    }

    /*
     * 
     * @see
     * com.ningpai.marketing.service.PromotionLogoService#checkLogoName(java
     * .lang.String)
     */
    @Override
    public boolean checkLogoName(String promotionLogoName) {
        return this.logoMapper.checkLogoName(promotionLogoName) > 0 ? false : true;
    }

    /*
     * 
     * @see
     * com.ningpai.marketing.service.PromotionLogoService#selectByLogoId(java
     * .lang.Long)
     */
    @Override
    public PromotionLogo selectByLogoId(Long promotionLogoId) {
        return this.logoMapper.selectByPrimaryKey(promotionLogoId);
    }

    /*
     * 
     * @see
     * com.ningpai.marketing.service.PromotionLogoService#queryAllLogoList()
     */
    @Override
    public List<PromotionLogo> queryAllLogoList() {
        return this.logoMapper.queryAllLogoList();
    }

    /*
     * 
     * @see
     * com.ningpai.marketing.service.PromotionLogoService#selectLastLogo(java
     * .lang.Long)
     */
    @Override
    public PromotionLogo selectLastLogo() {
        Long promotionLogoId = logoMapper.selectLastId();
        return this.logoMapper.selectByPrimaryKey(promotionLogoId);
    }

}
