package com.ningpai.site.giftshop.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.site.giftshop.bean.Gift;
import com.ningpai.site.giftshop.bean.GiftCate;
import com.ningpai.site.giftshop.dao.GiftShopSiteMapper;
import com.ningpai.site.giftshop.service.GiftShopSiteService;
import com.ningpai.site.giftshop.vo.GiftSearchVo;
import com.ningpai.util.PageBean;

/**
 * 赠品分类service实现类
 * 
 * @author qiyuanyuan
 *
 */
@Service("GiftShopSiteService")
public class GiftShopSiteServiceImpl implements GiftShopSiteService {

    /**
     * 赠品分类DAO接口
     */
    private GiftShopSiteMapper giftShopSiteMapper;

    /**
     * 查询赠品分类
     * 
     * @return
     */
    @Override
    public List<GiftCate> searchGiftCate() {

        List<GiftCate> parentList = giftShopSiteMapper.selectGiftParentCateList();
        List<GiftCate> allCateList = giftShopSiteMapper.selectAllCate();
        List<GiftCate> cateVoList = new ArrayList<>();
        GiftCate cate = null;
        GiftCate cateVo = null;
        if (parentList != null && !parentList.isEmpty()) {
            for (int i = 0; i < parentList.size(); i++) {
                // 从父分类中取出每一个分类
                cate = (GiftCate) parentList.get(i);
                cateVo = new GiftCate();
                cateVo.setGiftCateId(cate.getGiftCateId());
                cateVo.setGiftCateName(cate.getGiftCateName());
                cateVo.setGiftParentId(cate.getGiftParentId());
                cateVo.setGiftCateRemark(cate.getGiftCateRemark());
                // 根据取得的父分类ID和所有的分类列表计算分类实体，并添加到需要返回的集合中
                cateVo.setCateVos(this.calcCateVo(cateVo.getGiftCateId(), allCateList));
                cateVoList.add(cateVo);
            }
        }
        return cateVoList;
    }

    /**
     * 返回分类
     * 
     * @param parentId
     * @param allCateList
     * @return
     */
    public List<GiftCate> calcCateVo(Long parentId, List<GiftCate> allCateList) {
        List<GiftCate> cateVoList = new ArrayList<GiftCate>();
        // 需要返回的分类实体
        GiftCate cateVo = null;
        // 返回的分类实体的所有子分类
        List<GiftCate> allSonCate = null;
        // 循环中的迭代分类
        GiftCate cate = null;
        try {
            // 进行递归
            for (int i = 0; i < allCateList.size(); i++) {
                if (parentId.equals(allCateList.get(i).getGiftParentId())) {
                    // 必须在这里new对象 否则会覆盖原先的数据
                    cateVo = new GiftCate();
                    cate = allCateList.get(i);
                    cateVo.setGiftCateId(cate.getGiftCateId());
                    cateVo.setGiftCateName(cate.getGiftCateName());
                    cateVo.setGiftParentId(cate.getGiftParentId());
                    cateVo.setGiftCateRemark(cate.getGiftCateRemark());
                    // 执行递归
                    allSonCate = calcCateVo(cate.getGiftCateId(), allCateList);
                    cateVo.setCateVos(allSonCate);
                    cateVoList.add(cateVo);
                }
            }
            // 返回计算好的所有的子分类列表
            return cateVoList;
        } finally {
            // 置空所有参数
            cateVoList = null;
            cateVo = null;
            allSonCate = null;
            cate = null;
        }
    }

    /**
     * 查询赠品列表
     * 
     * @param gift
     *            赠品对象{@link com.ningpai.site.giftshop.bean.Gift}
     * @param pb
     *            分页{@link com.ningpai.util.PageBean}
     * @return
     */
    @Override
    public PageBean searchGiftList(GiftSearchVo gift, PageBean pb) {

        gift.setDelFlag("0");
        // 分装实体类属性
     //   Map<String, Object> paramMap = MapUtil.getParamsMap(gift);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("startIntegral", gift.getStartIntegral());
        paramMap.put("endIntegral", gift.getEndIntegral());
        int rows = giftShopSiteMapper.searchGiftListCount(paramMap);
        if (rows > 0) {
            pb.setRows(rows);
        } else {
            pb.setRows(0);
        }
        pb.setObjectBean(gift);
        paramMap.put("start", pb.getStartRowNum());
        paramMap.put("number", pb.getEndRowNum());
        pb.setList(giftShopSiteMapper.searchGiftList(paramMap));
        return pb;
    }

    /**
     * 根据赠品Id查询赠品详情
     * 
     * @param giftId
     *            赠品Id{@link java.lang.Long}
     * @return
     */
    @Override
    public Gift selectByGiftId(Long giftId) {

        return giftShopSiteMapper.queryDetailByGiftId(giftId);
    }

    /**
     * 根据分类Id查询分类并且计算好所有的子级关系
     * 
     * @param cateId
     *            分类ID{@link java.lang.Long}
     * @return
     */
    @Override
    public GiftCate selectByParentId(Long cateId) {

        GiftCate parentCate;
        List<GiftCate> allCate;
        if (cateId != null) {
            parentCate = giftShopSiteMapper.selectByCateId(cateId);
            allCate = giftShopSiteMapper.selectAllCate();
            parentCate.setCateVos(this.calcCateVo(parentCate.getGiftCateId(), allCate));
            return parentCate;
        } else {
            return null;
        }
    }

    /**
     * 根据类型ID查询分类信息,仅是查询当前分类本身以及父分类
     * 
     * @param cateId
     *            分类ID{@link java.lang.Long}
     * @return
     */
    @Override
    public GiftCate selectByCateId(Long cateId) {

        GiftCate cate;
        if (cateId != null) {
            cate = giftShopSiteMapper.selectByCateId(cateId);
            cate.setParentCate(giftShopSiteMapper.selectByCateId(cate.getGiftParentId()));
            return cate;

        } else {
            return null;
        }
    }

    /**
     * 根据父分类ID查询第一个子分类
     * 
     * @param cateId
     * @return
     */
    @Override
    public String selectSonCateId(Long cateId) {

        String url;
        url = this.giftShopSiteMapper.querysCateIdBypCateId(cateId) + "-" + cateId.toString();
        return url;
    }

    public GiftShopSiteMapper getGiftShopSiteMapper() {
        return giftShopSiteMapper;
    }

    @Resource(name = "GiftShopSiteMapper")
    public void setGiftShopSiteMapper(GiftShopSiteMapper giftShopSiteMapper) {
        this.giftShopSiteMapper = giftShopSiteMapper;
    }

}
