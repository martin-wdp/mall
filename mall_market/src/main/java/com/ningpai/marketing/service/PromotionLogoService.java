package com.ningpai.marketing.service;

import java.util.List;

import com.ningpai.marketing.bean.PromotionLogo;
import com.ningpai.util.PageBean;

/**
 * 促销LOGO 接口
 * 
 * @author qiyuanyuan
 *
 */
public interface PromotionLogoService {

    /**
     * 添加促销LOGO
     * 
     * @param promotionLogo
     *            促销LOGO{@link }
     * @return int
     */
    int addPromotionLogo(PromotionLogo promotionLogo);

    /**
     * 修改促销LOGO
     * 
     * @param promotionLogo
     * @return
     */
    int updatePromotionLogo(PromotionLogo promotionLogo);

    /**
     * 根据促销LOGO ID删除促销LOGO
     * 
     * @param promotionLogoId
     *            促销LOGOId
     * @return int
     */
    int delAllPromotionLogo(Long[] promotionLogoId);

    /**
     * 查询促销LOHGO列表
     * 
     * @param pageBean
     * @return
     */
    PageBean queryAllPromotionLogo(PageBean pageBean, PromotionLogo logo);

    /**
     * 验证促销LOGO名称是否可用
     * 
     * @param promotionLogoName
     *            待验证的LOGO名称
     * @return 可用返回true 不可用返回false
     */
    boolean checkLogoName(String promotionLogoName);

    /**
     * 根据logoId查询logo详情
     * 
     * @param promotionLogoId
     *            logoId{@link java.lang.Long}
     * @return 促销LOGO对象
     */
    PromotionLogo selectByLogoId(Long promotionLogoId);

    /**
     * 查询所有的促销logo集合
     * 
     * @return list
     */
    List<PromotionLogo> queryAllLogoList();

    /**
     * 查询最新插入的LOGO对象
     * 
     * @return 对象
     */
    PromotionLogo selectLastLogo();
}
