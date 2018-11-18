package com.ningpai.site.giftshop.service;

import com.ningpai.site.giftshop.bean.Gift;
import com.ningpai.site.giftshop.bean.GiftCate;
import com.ningpai.site.giftshop.vo.GiftSearchVo;
import com.ningpai.util.PageBean;

import java.util.List;

/**
 * 赠品分类service接口
 * @author qiyuanyuan
 *
 */
public interface GiftShopSiteService {

 /**
  * 查询赠品分类
  * @return list
  */
  List<GiftCate> searchGiftCate();
  
  /**
   * 查询赠品列表
   * @param gift 赠品对象{@link com.ningpai.site.giftshop.bean.Gift}
   * @param pb 分页{@link com.ningpai.util.PageBean}
   * @return pb
   */
  PageBean searchGiftList(GiftSearchVo gift,PageBean pb);
  
  
  /**
   * 根据赠品Id查询赠品详情
   * @param giftId 赠品Id{@link java.lang.Long}
   * @return gift对象
   */
  Gift selectByGiftId(Long giftId);
  
  /**
   * 根据分类Id查询分类并且计算好所有的子级关系
   * @param cateId 分类ID{@link java.lang.Long}
   * @return GiftCate对象
   */
  GiftCate selectByParentId(Long cateId);
  
  /**
   * 根据类型ID查询分类信息,仅是查询当前分类本身以及父分类
   * @param cateId 分类ID{@link java.lang.Long}
   * @return GiftCate对象
   */
  GiftCate selectByCateId(Long cateId);
  
  /**
   * 根据父分类ID查询第一个子分类
   * @param cateId
   * @return
   */
  String selectSonCateId(Long cateId);
}

