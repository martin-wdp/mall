package com.ningpai.index.service;

import com.ningpai.channel.bean.*;
import com.ningpai.channel.service.*;
import com.ningpai.goods.bean.GoodsCate;
import com.ningpai.index.bean.*;
import com.ningpai.site.goods.service.GoodsCateService;
import com.ningpai.site.goods.service.GoodsProductService;
import com.ningpai.temp.service.ClassifyBarCateService;
import com.ningpai.temp.service.ClassifyBarQuickService;
import com.ningpai.temp.service.ClassifyBarService;
import com.ningpai.temp.vo.ClassifyBarVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * SERVICE-首页业务逻辑接口 获取首页楼层数据
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年5月9日下午5:25:04
 */
@Service("IndexSiteService")
public class IndexSiteService {

    private static final Long ATID3 = 161L;
    private static final Long ADVERTTYPE = 151L;

    /** 楼层业务接口 */
    private ChannelStoreyService channelStoreyService;
    /** 楼层商品业务接口 */
    private ChannelStoreyGoodsService channelStoreyGoodsService;
    /** 广告业务接口 */
    private ChannelAdverService channelAdverService;
    /** 品牌业务接口 */
    private ChannelTrademarkService channelTrademarkService;
    /** 楼层标签业务接口 */
    private ChannelStoreyTagService channelStoreyTagService;

    /** 商品分类业务接口 */
    private GoodsCateService goodsCateService;
    /** 货品业务接口 */
    private GoodsProductService goodsProductService;

    /** 分类导航业务接口 */
    private ClassifyBarService classifyBarService;

    /** 一级分类导航关联商品分类业务接口 */
    @Resource(name = "ClassifyBarCateService")
    private ClassifyBarCateService barCateService;
    /** 一级分类导航关联快捷分类业务接口 */
    @Resource(name = "ClassifyBarQuickService")
    private ClassifyBarQuickService barQuickService;

    /**
     * 初始化首页分类导航数据
     * 
     * @param tempId
     * @return
     */
    public IndexClassifyBar getClassifyBar(Long tempId) {
        // 初始化分页导航容器
        IndexClassifyBar indexClassifyBar = new IndexClassifyBar();

        // 获取分类导航框广告
        getBoxAdvertList(tempId, indexClassifyBar);

        // 获取分类导航框品牌
        getBoxBrandList(tempId, indexClassifyBar);

        // 获取分类导航列表
        getClassifyBar(tempId, indexClassifyBar);

        return indexClassifyBar;
    }

    /**
     * 初始化首页分类导航数据
     *
     * @param tempId
     * @return
     */
    public IndexClassifyBar getClassifyBar2(Long tempId) {
        // 初始化分页导航容器
        IndexClassifyBar indexClassifyBar = new IndexClassifyBar();

        // 获取分类导航列表
        getClassifyBar2(tempId, indexClassifyBar);

        return indexClassifyBar;
    }
    /**
     * 初始化首页分类导航数据
     *
     * @return
     */
    public IndexClassifyBar getIndexClassificationByfir(Long parentID) {
        // 初始化分页导航容器
        IndexClassifyBar indexClassifyBar = new IndexClassifyBar();

        // 获取分类导航列表
        getIndexClassificationByfir(parentID, indexClassifyBar);

        return indexClassifyBar;
    }

    /**
     * 获取分类导航列表
     * 
     * @param tempId
     * @param indexClassifyBar
     */
    private void getClassifyBar(Long tempId, IndexClassifyBar indexClassifyBar) {
        List<ClassifyBarVo> classifyBar = this.classifyBarService.selectClassifyBarByParamSite(tempId, null, null);
        /* 遍历分类导航列表，把分类导航塞到分类导航容器中 */
        for (ClassifyBarVo cbv : classifyBar) {
            // 分类导航
            IndexClassifyBarBean cb = new IndexClassifyBarBean();
            cb.setClassifyBarId(cbv.getClassifyBarId());
            cb.setGoodsCatId(cbv.getGoodsCatId());
            cb.setName(cbv.getName());
            cb.setUrl(cbv.getUrl());
            cb.setImgSrc(cbv.getTemp2());
            cb.setChilds(cbv.getChilds());
            cb.setImgString(cbv.getTemp5());

            // 获取一级分类导航关联的商品分类
            cb.setBarCate(barCateService.selectByClassifyBarId(cbv.getClassifyBarId()));
            // 获取一级分类导航关联的快捷分类
            cb.setBarQuick(barQuickService.selectByClassifyBarId(cbv.getClassifyBarId()));

            // 获取分类导航广告
            getClassifyAdvertList(cb);

            // 获取分类导航品牌
            getClassifyBrandList(cb);

            indexClassifyBar.getClassifyBarList().add(cb);
        }
    }

    /**
     * 获取分类导航列表
     *
     * @param tempId
     * @param indexClassifyBar
     */
    private void getClassifyBar2(Long tempId, IndexClassifyBar indexClassifyBar) {
        List<ClassifyBarVo> classifyBar = this.classifyBarService.selectClassifyBarByParamSite2(tempId, null, null);
        /* 遍历分类导航列表，把分类导航塞到分类导航容器中 */
        for (ClassifyBarVo cbv : classifyBar) {
            // 分类导航
            IndexClassifyBarBean cb = new IndexClassifyBarBean();
            cb.setClassifyBarId(cbv.getClassifyBarId());
            cb.setGoodsCatId(cbv.getGoodsCatId());
            cb.setName(cbv.getName());
            cb.setUrl(cbv.getUrl());
            cb.setImgSrc(cbv.getTemp2());
            cb.setChilds(cbv.getChilds());
            cb.setImgString(cbv.getTemp5());

            // 获取一级分类导航关联的商品分类
            cb.setBarCate(barCateService.selectByClassifyBarId(cbv.getClassifyBarId()));
            // 获取一级分类导航关联的快捷分类
            cb.setBarQuick(barQuickService.selectByClassifyBarId(cbv.getClassifyBarId()));

            // 获取分类导航广告
            getClassifyAdvertList(cb);

            // 获取分类导航品牌
            getClassifyBrandList(cb);

            indexClassifyBar.getClassifyBarList().add(cb);
        }
    }

    /**
     * 获取分类导航列表
     *
     * @param parentID
     * @param indexClassifyBar
     */
    private void getIndexClassificationByfir(Long parentID, IndexClassifyBar indexClassifyBar) {
        List<ClassifyBarVo> classifyBar = this.classifyBarService.getIndexClassificationByfir(parentID);
        /* 遍历分类导航列表，把分类导航塞到分类导航容器中 */
        for (ClassifyBarVo cbv : classifyBar) {
            // 分类导航
            IndexClassifyBarBean cb = new IndexClassifyBarBean();
            cb.setClassifyBarId(cbv.getClassifyBarId());
            cb.setGoodsCatId(cbv.getGoodsCatId());
            cb.setName(cbv.getName());
            cb.setUrl(cbv.getUrl());
            cb.setImgSrc(cbv.getTemp2());
            cb.setChilds(cbv.getChilds());
            cb.setImgString(cbv.getTemp5());

            // 获取一级分类导航关联的商品分类
            cb.setBarCate(barCateService.selectByClassifyBarId(cbv.getClassifyBarId()));
            // 获取一级分类导航关联的快捷分类
            cb.setBarQuick(barQuickService.selectByClassifyBarId(cbv.getClassifyBarId()));

            // 获取分类导航广告
            getClassifyAdvertList(cb);

            // 获取分类导航品牌
            getClassifyBrandList(cb);

            indexClassifyBar.getClassifyBarList().add(cb);
        }
    }

    /**
     * 获取分类导航品牌
     * 
     * @param cb
     */
    private void getClassifyBrandList(IndexClassifyBarBean cb) {
        List<ChannelTrademark> brandList = this.channelTrademarkService.selectChannelTrademarkByParamForSite(null, null, null, null, cb.getClassifyBarId().toString(), null, null);
        /* 遍历分类导航品牌列表，把品牌列表塞到分类导航容器中 */
        for (ChannelTrademark brand : brandList) {
            IndexBrandBean indexBrandBean = new IndexBrandBean();
            indexBrandBean.setTrademarkId(brand.getChannelTrademarkId());
            indexBrandBean.setTrademarkName(brand.getTrademarkName());
            indexBrandBean.setLogoSrc(brand.getLogoSrc());
            indexBrandBean.setDes(brand.getDes());
            indexBrandBean.setSort(brand.getSort());
            indexBrandBean.setUrl(brand.getUrl());
            indexBrandBean.setShowType(brand.getShowType());
            indexBrandBean.setTitle(brand.getTitle());
            indexBrandBean.setBrandId(brand.getTemp4());
            cb.getIndexBrandList().add(indexBrandBean);
        }
    }

    /**
     * 获取分类导航广告
     * 
     * @param cb
     */
    private void getClassifyAdvertList(IndexClassifyBarBean cb) {
        List<ChannelAdver> adverList = this.channelAdverService.selectchannelAdverByParamForSite(null, null, null, null, ATID3, ADVERTTYPE, cb.getClassifyBarId().toString(), null,
                null, null);
        /* 遍历分类导航广告列表，把广告列表塞到分类导航bean中 */
        for (ChannelAdver channelAdver : adverList) {
            IndexAdvertBean indexAdvertBean = new IndexAdvertBean();
            indexAdvertBean.setAdverName(channelAdver.getAdverName());
            indexAdvertBean.setAdverPath(channelAdver.getAdverPath());
            indexAdvertBean.setAdverHref(channelAdver.getAdverHref());
            indexAdvertBean.setAdverSort(channelAdver.getAdverSort());
            indexAdvertBean.setTemp2(channelAdver.getTemp2());
            indexAdvertBean.setDes(channelAdver.getDes());
            indexAdvertBean.setChannelAdverId(channelAdver.getChannelAdverId());
            cb.getIndexAdvertList().add(indexAdvertBean);
        }
    }

    /**
     * 获取分类导航框品牌
     * 
     * @param tempId
     * @param indexClassifyBar
     */
    private void getBoxBrandList(Long tempId, IndexClassifyBar indexClassifyBar) {
        List<ChannelTrademark> boxBrandList = this.channelTrademarkService.selectChannelTrademarkByParamForSite(null, tempId, null, null, null, "1", null);
        /* 遍历分类导航框品牌列表，把品牌列表塞到分类导航容器中 */
        for (ChannelTrademark brand : boxBrandList) {
            IndexBrandBean indexBrandBean = new IndexBrandBean();
            indexBrandBean.setTrademarkId(brand.getChannelTrademarkId());
            indexBrandBean.setTrademarkName(brand.getTrademarkName());
            indexBrandBean.setLogoSrc(brand.getLogoSrc());
            indexBrandBean.setDes(brand.getDes());
            indexBrandBean.setSort(brand.getSort());
            indexBrandBean.setTitle(brand.getTitle());
            indexClassifyBar.getIndexBrandList().add(indexBrandBean);
        }
    }

    /**
     * 获取分类导航框广告
     * 
     * @param tempId
     * @param indexClassifyBar
     */
    private void getBoxAdvertList(Long tempId, IndexClassifyBar indexClassifyBar) {
        List<ChannelAdver> boxAdverList = this.channelAdverService.selectchannelAdverByParamForSite(null, tempId, null, null, ATID3, ADVERTTYPE, null, "1", null, null);
        /* 遍历分类导航框广告列表，把广告列表塞到分类导航容器中 */
        for (ChannelAdver channelAdver : boxAdverList) {
            IndexAdvertBean indexAdvertBean = new IndexAdvertBean();
            indexAdvertBean.setAdverName(channelAdver.getAdverName());
            indexAdvertBean.setAdverPath(channelAdver.getAdverPath());
            indexAdvertBean.setAdverHref(channelAdver.getAdverHref());
            indexAdvertBean.setAdverSort(channelAdver.getAdverSort());
            indexAdvertBean.setTemp2(channelAdver.getTemp2());
            indexAdvertBean.setDes(channelAdver.getDes());
            indexAdvertBean.setChannelAdverId(channelAdver.getChannelAdverId());
            indexClassifyBar.getIndexAdvertList().add(indexAdvertBean);
        }
    }

    /**
     * 初始化首页楼层数据
     * 
     * @param tempid
     *            模板ID
     * @return
     */
    public IndexFloor getStoreys(Long tempid) {
        // 初始化楼层容器
        IndexFloor indexFloor = new IndexFloor();

        // 根据模板ID获取楼层列表
        List<ChannelStorey> storeyList = channelStoreyService.selectchannelStoreyByParamForSite(null, tempid, null);

        /* 遍历楼层列表，获取楼层商品、广告、品牌、标签 */
        for (ChannelStorey s : storeyList) {
            // 初始化楼层信息bean
            IndexSiteStoreyBean storeys = new IndexSiteStoreyBean();
            storeys.setChannelStoreyId(s.getChannelStoreyId());
            storeys.setStoreyName(s.getStoreyName());
            storeys.setStoreySeo(s.getStoreySeo());
            storeys.setStoreyImg(s.getStoreyImg());
            storeys.setStoreyImgHref(s.getStoreyImgHref());
            storeys.setFloorId(s.getFloorId());
            storeys.setGoodsCatId(s.getGoodsCatId());
            storeys.setTempId(s.getTempId());
            storeys.setStoreyRightImg(s.getTemp2());

            // 根据楼层商品分类ID，获取子商品分类列表
            getGoodsCateListForStorey(s, storeys);

            // 根据楼层ID，获取楼层商品列表，用于在楼层左侧“热销推荐”展示
            getGoodsListForStorey(s, storeys);

            // 根据楼层ID，获取楼层广告列表
            getAdvertListForStorey(s, storeys);

            // 根据楼层ID，获取楼层品牌列表
            getBrandListForStorey(s, storeys);

            // 根据楼层ID，获取楼层标签列表
            getTagListForStorey(s, storeys);

            indexFloor.getFloorList().add(storeys);
        }

        return indexFloor;
    }

    /**
     * 根据模板ID，获取页面标签列表
     * 
     * @param tempId
     * @return
     */
    public List<IndexStoreyTagBean> getTagListForTempId(Long tempId) {
        List<IndexStoreyTagBean> tags = new ArrayList<IndexStoreyTagBean>();
        List<ChannelStoreyTag> tagList = this.channelStoreyTagService.selectchannelStoreyTagByParamForSite(null, tempId, null);
        /* 遍历楼层标签列表，把标签列表塞到初始化楼层信息bean中 */
        for (ChannelStoreyTag tag : tagList) {
            IndexStoreyTagBean storeyTag = new IndexStoreyTagBean();
            storeyTag.setChannelStoreyTagId(tag.getChannelStoreyTagId());
            storeyTag.setName(tag.getName());
            storeyTag.setSort(tag.getSort());

            // 根据标签ID，获取楼层标签商品列表
            getGoodsListForStoreyTag(storeyTag);

            // 根据标签ID，获取楼层标签广告列表
            getAdvertListForStoreyTag(storeyTag);

            // 根据标签ID，获取楼层标签品牌列表
            getBrandListForStoreyTag(storeyTag);
            tags.add(storeyTag);
        }
        return tags;
    }

    /**
     * 根据楼层ID，获取楼层标签列表
     * 
     * @param s
     *            楼层对象
     * @param storeys
     *            楼层信息封装bean
     */
    private void getTagListForStorey(ChannelStorey s, IndexSiteStoreyBean storeys) {
        List<ChannelStoreyTag> tagList = this.channelStoreyTagService.selectchannelStoreyTagByParamForSite(s.getChannelStoreyId(), null, null);
        /* 遍历楼层标签列表，把标签列表塞到初始化楼层信息bean中 */
        for (ChannelStoreyTag tag : tagList) {
            IndexStoreyTagBean storeyTag = new IndexStoreyTagBean();
            storeyTag.setChannelStoreyTagId(tag.getChannelStoreyTagId());
            storeyTag.setName(tag.getName());
            storeyTag.setSort(tag.getSort());

            // 根据标签ID，获取楼层标签商品列表
            getGoodsListForStoreyTag(storeyTag);

            // 根据标签ID，获取楼层标签广告列表
            getAdvertListForStoreyTag(storeyTag);

            // 根据标签ID，获取楼层标签品牌列表
            getBrandListForStoreyTag(storeyTag);

            storeys.getIndexStoreyTagList().add(storeyTag);
        }
    }

    /**
     * 根据标签ID，获取楼层标签品牌列表
     * 
     * @param storeyTag
     *            楼层标签信息封装bean
     */
    private void getBrandListForStoreyTag(IndexStoreyTagBean storeyTag) {
        List<ChannelTrademark> tagBrandList = this.channelTrademarkService.selectChannelTrademarkByParamForSite(null, null, null, storeyTag.getChannelStoreyTagId(), null, null,
                null);
        /* 遍历楼层标签品牌列表，把品牌列表塞到楼层标签信息bean中 */
        for (ChannelTrademark brand : tagBrandList) {
            IndexBrandBean indexBrandBean = new IndexBrandBean();
            indexBrandBean.setTrademarkId(brand.getChannelTrademarkId());
            indexBrandBean.setTrademarkName(brand.getTrademarkName());
            indexBrandBean.setLogoSrc(brand.getLogoSrc());
            indexBrandBean.setDes(brand.getDes());
            indexBrandBean.setSort(brand.getSort());
            indexBrandBean.setBrandId(brand.getTemp4());
            storeyTag.getIndexBrandList().add(indexBrandBean);
        }
    }

    /**
     * 根据标签ID，获取楼层标签广告列表
     * 
     * @param storeyTag
     *            楼层标签信息封装bean
     */
    private void getAdvertListForStoreyTag(IndexStoreyTagBean storeyTag) {
        List<ChannelAdver> tagAdverList = this.channelAdverService.selectchannelAdverByParamForSite(null, null, null, storeyTag.getChannelStoreyTagId(), ATID3, ADVERTTYPE, null,
                "0", null, null);
        /* 遍历楼层标签广告列表，把广告列表塞到楼层标签信息bean中 */
        for (ChannelAdver channelAdver : tagAdverList) {
            IndexAdvertBean indexAdvertBean = new IndexAdvertBean();
            indexAdvertBean.setAdverName(channelAdver.getAdverName());
            indexAdvertBean.setAdverPath(channelAdver.getAdverPath());
            indexAdvertBean.setAdverHref(channelAdver.getAdverHref());
            indexAdvertBean.setAdverSort(channelAdver.getAdverSort());
            indexAdvertBean.setTemp2(channelAdver.getTemp2());
            indexAdvertBean.setDes(channelAdver.getDes());
            indexAdvertBean.setChannelAdverId(channelAdver.getChannelAdverId());
            storeyTag.getIndexAdvertList().add(indexAdvertBean);
        }
    }

    /**
     * 根据标签ID，获取楼层标签商品列表
     * 
     * @param storeyTag
     *            楼层标签信息封装bean
     */
    private void getGoodsListForStoreyTag(IndexStoreyTagBean storeyTag) {
        List<ChannelStoreyGoods> tagGoodsList = this.channelStoreyGoodsService.selectchannelStoreyGoodsByParamForSite(null, storeyTag.getChannelStoreyTagId(), null);
        /* 遍历楼层标签商品列表，把商品列表塞到楼层标签信息bean中 */
        for (int i = 0; i < tagGoodsList.size(); i++) {
            IndexGoodsBean indexGoodsBean = new IndexGoodsBean();
            indexGoodsBean.setName(tagGoodsList.get(i).getGoodsproductName());
            indexGoodsBean.setNumber(String.valueOf(tagGoodsList.get(i).getGoodsproductNo()));
            indexGoodsBean.setPrice(String.valueOf(tagGoodsList.get(i).getGoodsproductPrice()));
            indexGoodsBean.setUrlpic(tagGoodsList.get(i).getGoodsproductImgsrc());
            indexGoodsBean.setId(String.valueOf(tagGoodsList.get(i).getGoodsproductId()));
            storeyTag.getIndexGoodsList().add(indexGoodsBean);
        }
    }

    /**
     * 根据楼层ID，获取楼层品牌列表
     * 
     * @param s
     *            楼层对象
     * @param storeys
     *            楼层信息封装bean
     */
    private void getBrandListForStorey(ChannelStorey s, IndexSiteStoreyBean storeys) {
        List<ChannelTrademark> brandList = this.channelTrademarkService.selectChannelTrademarkByParamForSite(null, null, s.getChannelStoreyId(), null, null, null, null);
        /* 遍历楼层品牌列表，把品牌列表塞到初始化楼层信息bean中 */
        for (ChannelTrademark brand : brandList) {
            IndexBrandBean indexBrandBean = new IndexBrandBean();
            indexBrandBean.setTrademarkId(brand.getChannelTrademarkId());
            indexBrandBean.setTrademarkName(brand.getTrademarkName());
            indexBrandBean.setLogoSrc(brand.getLogoSrc());
            indexBrandBean.setDes(brand.getDes());
            indexBrandBean.setSort(brand.getSort());
            storeys.getIndexBrandList().add(indexBrandBean);
        }
    }

    /**
     * 根据楼层ID，获取楼层广告列表
     * 
     * @param s
     *            楼层对象
     * @param storeys
     *            楼层信息封装bean
     */
    private void getAdvertListForStorey(ChannelStorey s, IndexSiteStoreyBean storeys) {
        List<ChannelAdver> adverList = this.channelAdverService
                .selectchannelAdverByParamForSite(null, null, s.getChannelStoreyId(), null, ATID3, ADVERTTYPE, null, "0", null, null);
        /* 遍历楼层广告列表，把广告列表塞到初始化楼层信息bean中 */
        for (ChannelAdver channelAdver : adverList) {
            IndexAdvertBean indexAdvertBean = new IndexAdvertBean();
            indexAdvertBean.setAdverName(channelAdver.getAdverName());
            indexAdvertBean.setAdverPath(channelAdver.getAdverPath());
            indexAdvertBean.setAdverHref(channelAdver.getAdverHref());
            indexAdvertBean.setAdverSort(channelAdver.getAdverSort());
            indexAdvertBean.setTemp2(channelAdver.getTemp2());
            indexAdvertBean.setDes(channelAdver.getDes());
            indexAdvertBean.setChannelAdverId(channelAdver.getChannelAdverId());
            storeys.getIndexAdvertList().add(indexAdvertBean);
        }
    }

    /**
     * 根据楼层ID，获取楼层商品列表，用于在楼层左侧“热销推荐”展示
     * 
     * @param s
     *            楼层对象
     * @param storeys
     *            楼层信息封装bean
     */
    private void getGoodsListForStorey(ChannelStorey s, IndexSiteStoreyBean storeys) {
        List<ChannelStoreyGoods> storeyGoodsList = this.channelStoreyGoodsService.selectchannelStoreyGoodsByParamForSite(s.getChannelStoreyId(), null, null);
        /* 遍历楼层商品列表，把商品列表塞到初始化楼层信息bean中 */
        for (int i = 0; i < storeyGoodsList.size(); i++) {
            IndexGoodsBean indexGoodsBean = new IndexGoodsBean();
            indexGoodsBean.setName(storeyGoodsList.get(i).getGoodsproductName());
            indexGoodsBean.setNumber(String.valueOf(storeyGoodsList.get(i).getGoodsproductNo()));
            indexGoodsBean.setPrice(String.valueOf(storeyGoodsList.get(i).getGoodsproductPrice()));
            indexGoodsBean.setUrlpic(storeyGoodsList.get(i).getGoodsproductImgsrc());
            indexGoodsBean.setId(String.valueOf(storeyGoodsList.get(i).getGoodsproductId()));
            storeys.getIndexGoodsList().add(indexGoodsBean);
        }
    }

    /**
     * 根据楼层商品分类ID，获取子商品分类列表
     * 
     * @param s
     *            楼层对象
     * @param storeys
     *            楼层信息封装bean
     */
    private void getGoodsCateListForStorey(ChannelStorey s, IndexSiteStoreyBean storeys) {
        List<GoodsCate> listcate = goodsCateService.queryCatIdsByCatId(s.getGoodsCatId());
        listcate.remove(0);
        /* 遍历子商品分类列表，把子商品分类列表塞到初始化楼层信息bean中 */
        for (int i = 0; i < listcate.size(); i++) {
            IndexCate indexCate = new IndexCate();
            indexCate.setId(listcate.get(i).getCatId());
            indexCate.setName(listcate.get(i).getCatName());
            storeys.getIndexCateList().add(indexCate);
        }
    }

    public GoodsCateService getGoodsCateService() {
        return goodsCateService;
    }

    @Resource(name = "HsiteGoodsCateService")
    public void setGoodsCateService(GoodsCateService goodsCateService) {
        this.goodsCateService = goodsCateService;
    }

    public GoodsProductService getGoodsProductService() {
        return goodsProductService;
    }

    @Resource(name = "HsiteGoodsProductService")
    public void setGoodsProductService(GoodsProductService goodsProductService) {
        this.goodsProductService = goodsProductService;
    }

    public ChannelStoreyService getChannelStoreyService() {
        return channelStoreyService;
    }

    @Resource(name = "ChannelStoreyService")
    public void setChannelStoreyService(ChannelStoreyService channelStoreyService) {
        this.channelStoreyService = channelStoreyService;
    }

    public ChannelStoreyGoodsService getChannelStoreyGoodsService() {
        return channelStoreyGoodsService;
    }

    @Resource(name = "ChannelStoreyGoodsService")
    public void setChannelStoreyGoodsService(ChannelStoreyGoodsService channelStoreyGoodsService) {
        this.channelStoreyGoodsService = channelStoreyGoodsService;
    }

    public ChannelAdverService getChannelAdverService() {
        return channelAdverService;
    }

    @Resource(name = "ChannelAdverService")
    public void setChannelAdverService(ChannelAdverService channelAdverService) {
        this.channelAdverService = channelAdverService;
    }

    public ChannelTrademarkService getChannelTrademarkService() {
        return channelTrademarkService;
    }

    @Resource(name = "ChannelTrademarkService")
    public void setChannelTrademarkService(ChannelTrademarkService channelTrademarkService) {
        this.channelTrademarkService = channelTrademarkService;
    }

    public ChannelStoreyTagService getChannelStoreyTagService() {
        return channelStoreyTagService;
    }

    @Resource(name = "ChannelStoreyTagService")
    public void setChannelStoreyTagService(ChannelStoreyTagService channelStoreyTagService) {
        this.channelStoreyTagService = channelStoreyTagService;
    }

    public ClassifyBarService getClassifyBarService() {
        return classifyBarService;
    }

    @Resource(name = "ClassifyBarService")
    public void setClassifyBarService(ClassifyBarService classifyBarService) {
        this.classifyBarService = classifyBarService;
    }

}
