package com.ningpai.index.service;

import com.ningpai.channel.bean.*;
import com.ningpai.channel.service.*;
import com.ningpai.index.bean.*;
import com.ningpai.temp.service.ClassifyBarService;
import com.ningpai.temp.vo.ClassifyBarVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * SERVICE-第三方商家店铺数据获取业务
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年5月23日下午5:07:28
 */
@Service("ThirdIndexSiteService")
public class ThirdIndexSiteService {

    private static final Long ATID3 = 161L;
    private static final Long ADVERTTYPE = 151L;

    /** 楼层业务接口 */
    @Resource(name = "ChannelStoreyService")
    private ChannelStoreyService channelStoreyService;
    /** 楼层标签业务接口 */
    @Resource(name = "ChannelStoreyTagService")
    private ChannelStoreyTagService channelStoreyTagService;
    /** 楼层商品业务接口 */
    @Resource(name = "ChannelStoreyGoodsService")
    private ChannelStoreyGoodsService channelStoreyGoodsService;
    /** 广告业务接口 */
    @Resource(name = "ChannelAdverService")
    private ChannelAdverService channelAdverService;
    /** 商品分类业务接口 */
    @Resource(name = "ChannelGoodsCateService")
    private GoodsCateService goodsCateService;
    /** 分类导航业务接口 */
    @Resource(name = "ClassifyBarService")
    private ClassifyBarService classifyBarService;

    /**
     * 初始化首页分类导航数据
     * 
     * @param tempId
     * @return
     */
    public IndexClassifyBar getClassifyBar(Long tempId, String thirdId) {
        // 初始化分页导航容器
        IndexClassifyBar indexClassifyBar = new IndexClassifyBar();

        // 获取分类导航列表
        List<ClassifyBarVo> classifyBar = this.classifyBarService.selectClassifyBarByParamSite(tempId, null, thirdId);
        /* 遍历分类导航列表，把分类导航塞到分类导航容器中 */
        for (ClassifyBarVo cbv : classifyBar) {
            // 分类导航
            IndexClassifyBarBean cb = new IndexClassifyBarBean();
            cb.setClassifyBarId(cbv.getClassifyBarId());
            cb.setGoodsCatId(cbv.getGoodsCatId());
            cb.setName(cbv.getName());
            cb.setUrl(cbv.getUrl());
            cb.setChilds(cbv.getChilds());

            indexClassifyBar.getClassifyBarList().add(cb);
        }
        return indexClassifyBar;
    }

    /**
     * 初始化首页楼层数据
     * 
     * @param tempid
     *            模板ID
     * @return
     */
    public IndexFloor getStoreys(Long tempid, String thirdId) {
        // 初始化楼层容器
        IndexFloor indexFloor = new IndexFloor();

        // 根据模板ID获取楼层列表
        List<ChannelStorey> storeyList = channelStoreyService.selectchannelStoreyByParamForSite(null, tempid, thirdId);

        /* 遍历楼层列表，获取楼层商品、广告、 */
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

            // 根据楼层ID，获取楼层商品列表
            getGoodsListForStorey(s, storeys);

            // 根据楼层ID，获取楼层广告列表
            getAdvertListForStorey(s, storeys);

            // 根据楼层ID，获取楼层标签列表
            getTagListForStorey(s, storeys);

            indexFloor.getFloorList().add(storeys);
        }

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

            storeys.getIndexStoreyTagList().add(storeyTag);
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
     * 根据楼层ID，获取楼层商品列表
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
        List<GoodsCate> listcate = goodsCateService.queryGoosCateByParentId(s.getGoodsCatId());
        /* 遍历子商品分类列表，把子商品分类列表塞到初始化楼层信息bean中 */
        for (int i = 0; i < listcate.size(); i++) {
            IndexCate indexCate = new IndexCate();
            indexCate.setId(listcate.get(i).getCatId());
            indexCate.setName(listcate.get(i).getCatName());
            storeys.getIndexCateList().add(indexCate);
        }
    }

}
