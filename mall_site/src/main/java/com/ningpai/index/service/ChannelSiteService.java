package com.ningpai.index.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.ningpai.channel.bean.GoodsSiteSearchBean;

import com.ningpai.util.PageBean;
import org.springframework.stereotype.Service;

import com.ningpai.channel.bean.ChannelAdver;
import com.ningpai.channel.bean.ChannelStorey;
import com.ningpai.channel.bean.ChannelStoreyGoods;
import com.ningpai.channel.bean.ChannelStoreyTag;
import com.ningpai.channel.bean.ChannelTrademark;
import com.ningpai.channel.service.ChannelAdverService;
import com.ningpai.channel.service.ChannelStoreyGoodsService;
import com.ningpai.channel.service.ChannelStoreyService;
import com.ningpai.channel.service.ChannelStoreyTagService;
import com.ningpai.channel.service.ChannelTrademarkService;
import com.ningpai.goods.bean.GoodsCate;
import com.ningpai.index.bean.IndexAdvertBean;
import com.ningpai.index.bean.IndexBrandBean;
import com.ningpai.index.bean.IndexCate;
import com.ningpai.index.bean.IndexClassifyBar;
import com.ningpai.index.bean.IndexClassifyBarBean;
import com.ningpai.index.bean.IndexFloor;
import com.ningpai.index.bean.IndexGoodsBean;
import com.ningpai.index.bean.IndexSiteStoreyBean;
import com.ningpai.index.bean.IndexStoreyTagBean;
import com.ningpai.site.goods.service.GoodsCateService;
import com.ningpai.temp.service.ClassifyBarService;
import com.ningpai.temp.vo.ClassifyBarVo;

/**
 * SERVICE-首页业务逻辑接口 获取首页楼层数据
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年5月9日下午5:25:04
 */
@Service("ChannelSiteService")
public class ChannelSiteService {

    private static final Long ATID3 = 161L;
    private static final Long ADVERTTYPE = 151L;

    /** 楼层业务接口 */
    @Resource(name = "ChannelStoreyService")
    private ChannelStoreyService channelStoreyService;
    /** 楼层商品业务接口 */
    @Resource(name = "ChannelStoreyGoodsService")
    private ChannelStoreyGoodsService channelStoreyGoodsService;
    /** 广告业务接口 */
    @Resource(name = "ChannelAdverService")
    private ChannelAdverService channelAdverService;
    /** 品牌业务接口 */
    @Resource(name = "ChannelTrademarkService")
    private ChannelTrademarkService channelTrademarkService;
    /** 楼层标签业务接口 */
    @Resource(name = "ChannelStoreyTagService")
    private ChannelStoreyTagService channelStoreyTagService;

    /** 商品分类业务接口 */
    @Resource(name = "HsiteGoodsCateService")
    private GoodsCateService goodsCateService;

    /** 分类导航业务接口 */
    @Resource(name = "ClassifyBarService")
    private ClassifyBarService classifyBarService;

    /**
     * 通过楼层标签id查询商品列表
     * 
     * @param storeyTagId
     *            楼层标签id
     * @return List<IndexGoodsBean>
     */
    public List<IndexGoodsBean> selectStoreyTagProductsByTagId(Long storeyTagId) {
        List<ChannelStoreyGoods> tagGoodsList = this.channelStoreyGoodsService.selectchannelStoreyGoodsByParamForSite(null, storeyTagId, null);
        List<IndexGoodsBean> pl = new ArrayList<IndexGoodsBean>();
        /* 遍历楼层标签商品列表，把商品列表塞到楼层标签信息bean中 */
        for (int i = 0; i < tagGoodsList.size(); i++) {
            IndexGoodsBean indexGoodsBean = new IndexGoodsBean();
            indexGoodsBean.setName(tagGoodsList.get(i).getGoodsproductName());
            indexGoodsBean.setNumber(String.valueOf(tagGoodsList.get(i).getGoodsproductNo()));
            indexGoodsBean.setPrice(String.valueOf(tagGoodsList.get(i).getGoodsproductPrice()));
            indexGoodsBean.setUrlpic(tagGoodsList.get(i).getGoodsproductImgsrc());
            indexGoodsBean.setId(String.valueOf(tagGoodsList.get(i).getGoodsproductId()));
            pl.add(indexGoodsBean);
        }
        return pl;
    }

    /**
     * 初始化频道分类导航数据
     * 
     * @param tempId
     * @return
     */
    public IndexClassifyBar getClassifyBar(Long channelId, Long tempId) {
        // 初始化分类导航容器
        IndexClassifyBar indexClassifyBar = new IndexClassifyBar();

        // 获取分类导航框广告
        getBoxAdvertList(channelId, indexClassifyBar);

        // 获取分类导航框品牌
        getBoxBrandList(channelId, indexClassifyBar);

        // 获取分类导航列表
        getClassifyBar(channelId, indexClassifyBar);
        return indexClassifyBar;
    }

    /**
     * 获取分类导航列表
     * 
     * @param channelId
     * @param indexClassifyBar
     */
    private void getClassifyBar(Long channelId, IndexClassifyBar indexClassifyBar) {
        List<ClassifyBarVo> classifyBar = this.classifyBarService.selectClassifyBarByParamSite(null, channelId, null);
        /* 遍历分类导航列表，把分类导航塞到分类导航容器中 */
        for (ClassifyBarVo cbv : classifyBar) {
            IndexClassifyBarBean cb = new IndexClassifyBarBean();
            cb.setClassifyBarId(cbv.getClassifyBarId());
            cb.setGoodsCatId(cbv.getGoodsCatId());
            cb.setName(cbv.getName());
            cb.setUrl(cbv.getUrl());
            cb.setChilds(cbv.getChilds());
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
     * @param channelId
     * @param indexClassifyBar
     */
    private void getBoxBrandList(Long channelId, IndexClassifyBar indexClassifyBar) {
        List<ChannelTrademark> boxBrandList = this.channelTrademarkService.selectChannelTrademarkByParamForSite(channelId, null, null, null, null, "1", null);
        /* 遍历分类导航框品牌列表，把品牌列表塞到分类导航容器中 */
        for (ChannelTrademark brand : boxBrandList) {
            IndexBrandBean indexBrandBean = new IndexBrandBean();
            indexBrandBean.setTrademarkId(brand.getChannelTrademarkId());
            indexBrandBean.setTrademarkName(brand.getTrademarkName());
            indexBrandBean.setLogoSrc(brand.getLogoSrc());
            indexBrandBean.setDes(brand.getDes());
            indexBrandBean.setSort(brand.getSort());
            indexClassifyBar.getIndexBrandList().add(indexBrandBean);
        }
    }

    /**
     * 获取分类导航框广告
     * 
     * @param channelId
     * @param indexClassifyBar
     */
    private void getBoxAdvertList(Long channelId, IndexClassifyBar indexClassifyBar) {
        List<ChannelAdver> boxAdverList = this.channelAdverService.selectchannelAdverByParamForSite(channelId, null, null, null, ATID3, ADVERTTYPE, null, "1", null, null);
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
     * @param channelId
     *            频道ID
     * @param tempId
     *            模板ID
     * @return
     */
    public IndexFloor getStoreys(Long channelId, Long tempId) {
        // 初始化楼层容器
        IndexFloor indexFloor = new IndexFloor();

        // 根据频道ID、模板ID获取楼层列表
        List<ChannelStorey> storeyList = channelStoreyService.selectchannelStoreyByParamForSite(channelId, null, null);

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
     * 初始化频道页楼层数据
     *
     * @param channelId
     *            频道ID
     * @param tempId
     *            模板ID
     * @return
     */
    public IndexFloor getChannelStoreys(HttpServletRequest request, Long channelId, Long tempId, GoodsSiteSearchBean searchBean, PageBean pageBean) {
        // 初始化楼层容器
        IndexFloor indexFloor = new IndexFloor();

        // 根据频道ID、模板ID获取楼层列表
        List<ChannelStorey> storeyList = channelStoreyService.selectchannelStoreyByParamForSite(channelId, null, null);

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

            // 根据楼层商品分类ID，获取子商品分类列表
            getGoodsCateListForStorey(s, storeys);

            // 根据楼层ID，获取楼层商品列表，用于在楼层左侧“热销推荐”展示
            getGoodsListForChannelStorey(request, s, storeys, searchBean, pageBean);

            // 根据楼层ID，获取楼层广告列表
            getAdvertListForStorey(s, storeys);

            // 根据楼层ID，获取楼层品牌列表
            getBrandListForStorey(s, storeys);

            // 根据楼层ID，获取楼层标签列表
            getTagListForStorey(s, storeys);

            indexFloor.getFloorList().add(storeys);
        }
        indexFloor.setPageBean(pageBean);
        return indexFloor;
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
            indexGoodsBean.setStoreyGoodsFlag(storeyGoodsList.get(i).getTemp3());
            indexGoodsBean.setId(String.valueOf(storeyGoodsList.get(i).getGoodsproductId()));
            storeys.getIndexGoodsList().add(indexGoodsBean);
        }
        // pageBean.setList(storeyGoodsList);
    }

    /**
     * 根据楼层ID，获取频道页楼层商品列表
     *
     * @param s
     *            楼层对象
     * @param storeys
     *            楼层信息封装bean
     */
    private void getGoodsListForChannelStorey(HttpServletRequest request, ChannelStorey s, IndexSiteStoreyBean storeys, GoodsSiteSearchBean searchBean, PageBean pageBean) {
        List<ChannelStoreyGoods> storeyGoodsList = this.channelStoreyGoodsService.selectchannelStoreyGoodsByParamForChannelSite(request, s.getChannelStoreyId(), null, null,
                searchBean, pageBean);
        /* 遍历楼层商品列表，把商品列表塞到初始化楼层信息bean中 */
        for (int i = 0; i < storeyGoodsList.size(); i++) {
            IndexGoodsBean indexGoodsBean = new IndexGoodsBean();
            indexGoodsBean.setName(storeyGoodsList.get(i).getGoodsproductName());
            indexGoodsBean.setNumber(String.valueOf(storeyGoodsList.get(i).getGoodsproductNo()));
            indexGoodsBean.setPrice(String.valueOf(storeyGoodsList.get(i).getGoodsproductPrice()));
            indexGoodsBean.setUrlpic(storeyGoodsList.get(i).getGoodsproductImgsrc());
            indexGoodsBean.setStoreyGoodsFlag(storeyGoodsList.get(i).getTemp3());
            indexGoodsBean.setId(String.valueOf(storeyGoodsList.get(i).getGoodsproductId()));
            storeys.getIndexGoodsList().add(indexGoodsBean);
        }
        // pageBean.setList(storeyGoodsList);
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
        if (null != listcate && !listcate.isEmpty()) {
            listcate.remove(0);
            /* 遍历子商品分类列表，把子商品分类列表塞到初始化楼层信息bean中 */
            for (int i = 0; i < listcate.size(); i++) {
                IndexCate indexCate = new IndexCate();
                indexCate.setId(listcate.get(i).getCatId());
                indexCate.setName(listcate.get(i).getCatName());
                storeys.getIndexCateList().add(indexCate);
            }
        }
    }

}
