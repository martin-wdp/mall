package com.ningpai.site.thirdseller.service.impl;

import com.ningpai.channel.bean.ChannelAdver;
import com.ningpai.channel.bean.ChannelStorey;
import com.ningpai.channel.bean.ChannelStoreyGoods;
import com.ningpai.channel.bean.ChannelStoreyTag;
import com.ningpai.channel.dao.ChannelStoreyMapper;
import com.ningpai.channel.service.ChannelAdverService;
import com.ningpai.channel.service.ChannelStoreyGoodsService;
import com.ningpai.channel.service.ChannelStoreyTagService;
import com.ningpai.site.thirdseller.bean.*;
import com.ningpai.site.thirdseller.dao.ThirdStoreInfoMapper;
import com.ningpai.site.thirdseller.service.ThirdSellerSiteService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 第三方店铺首页service
 * 
 * @author qiyuanyuan
 * 
 */
@Service("ThirdSellerSiteService")
public class ThirdSellerSiteServiceImpl implements ThirdSellerSiteService {

    private ChannelStoreyMapper channelStoreyMapper;
    /** 楼层商品业务接口 */
    private ChannelStoreyGoodsService channelStoreyGoodsService;
    /** 广告业务接口 */
    private ChannelAdverService channelAdverService;
    /** 楼层标签业务接口 */
    private ChannelStoreyTagService channelStoreyTagService;
    private ThirdStoreInfoMapper storeInfoMapper;
    private static final Long ATID3 = 161L;
    private static final Long ADVERTTYPE = 151L;

    /**
     * 第三方店铺首页楼层数据
     * 
     * @param tempId
     *            模板ID
     * @param thirdId
     *            商家ID
     * @return
     */
    public ThirdIndexFloor getStoreys(Long tempId, Long thirdId) {

        ThirdIndexFloor thirdIndexFloor = new ThirdIndexFloor();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("channelId", null);
        map.put("tempId", tempId);
        map.put("temp1", thirdId);
        List<ChannelStorey> storeyList = channelStoreyMapper.selectchannelStoreyByParamForSite(map);
        for (ChannelStorey s : storeyList) {
            ThirdIndexStoreyBean indexStoreyBean = new ThirdIndexStoreyBean();
            indexStoreyBean.setChannelStoreyId(s.getChannelStoreyId());
            indexStoreyBean.setStoreyName(s.getStoreyName());
            indexStoreyBean.setStoreySeo(s.getStoreySeo());
            indexStoreyBean.setStoreyImg(s.getStoreyImg());
            indexStoreyBean.setStoreyImgHref(s.getStoreyImgHref());
            indexStoreyBean.setTempId(s.getTempId());
            getAdverListForStorey(s, indexStoreyBean);
            getGoodsListForStorey(s, indexStoreyBean);
            getTagListForStorey(s, indexStoreyBean);
            thirdIndexFloor.getFloorList().add(indexStoreyBean);
        }
        return thirdIndexFloor;
    }

    /**
     * 根据楼层ID，获取楼层商品列表
     * 
     * @param s
     *            楼层对象
     * @param storey
     *            楼层信息封装bean
     */
    public void getGoodsListForStorey(ChannelStorey s, ThirdIndexStoreyBean storey) {
        List<ChannelStoreyGoods> storeyGoodsList = this.channelStoreyGoodsService.selectchannelStoreyGoodsByParamForSite(s.getChannelStoreyId(), null, null);
        // 遍历楼层商品列表，把商品列表塞到初始化楼层信息bean中 ChannelStoreyGoods
        for (int i = 0; i < storeyGoodsList.size(); i++) {
            ThirdIndexStoreyGoodsBean indexGoodsBean = new ThirdIndexStoreyGoodsBean();
            indexGoodsBean.setName(storeyGoodsList.get(i).getGoodsproductName());
            indexGoodsBean.setNumber(storeyGoodsList.get(i).getGoodsproductNo());
            indexGoodsBean.setPrice(String.valueOf(storeyGoodsList.get(i).getGoodsproductPrice()));
            indexGoodsBean.setUrlpic(storeyGoodsList.get(i).getGoodsproductImgsrc());
            indexGoodsBean.setId(String.valueOf(storeyGoodsList.get(i).getGoodsproductId()));
            storey.getIndexGoodsList().add(indexGoodsBean);
        }
    }

    /**
     * 根据楼层ID，获取楼层广告列表
     * 
     * @param s
     *            楼层对象
     * @param storey
     *            楼层信息封装bean
     */
    public void getAdverListForStorey(ChannelStorey s, ThirdIndexStoreyBean storey) {
        List<ChannelAdver> adverList = this.channelAdverService
                .selectchannelAdverByParamForSite(null, null, s.getChannelStoreyId(), null, ATID3, ADVERTTYPE, null, "0", null, null);
        // 遍历楼层广告列表，把广告列表塞到初始化楼层信息bean中
        for (ChannelAdver channelAdver : adverList) {
            ThirdIndexStoreyAdverBean adverBean = new ThirdIndexStoreyAdverBean();
            adverBean.setAdverName(channelAdver.getAdverName());
            adverBean.setAdverPath(channelAdver.getAdverPath());
            adverBean.setAdverHref(channelAdver.getAdverHref());
            adverBean.setAdverSort(channelAdver.getAdverSort());
            adverBean.setDes(channelAdver.getDes());
            adverBean.setTemp2(channelAdver.getTemp2());
            adverBean.setChannelAdverId(channelAdver.getChannelAdverId());
            storey.getIndexAdverList().add(adverBean);
        }
    }

    /**
     * 根据楼层id 获取楼层标签列表
     * 
     * @param s
     *            楼层对象
     * @param storey
     *            楼层信息封装bean
     */
    public void getTagListForStorey(ChannelStorey s, ThirdIndexStoreyBean storey) {
        List<ChannelStoreyTag> tagList = this.channelStoreyTagService.selectchannelStoreyTagByParamForSite(s.getChannelStoreyId(), null, null);
        for (ChannelStoreyTag tag : tagList) {
            ThirdIndexStoreyTagBean storeyTagBean = new ThirdIndexStoreyTagBean();
            storeyTagBean.setChannelStoreyTagId(tag.getChannelStoreyTagId());
            storeyTagBean.setName(tag.getName());
            storeyTagBean.setSort(tag.getSort());
            getAdverListForStoreyTag(storeyTagBean);
            getGoodsListForStoreyTag(storeyTagBean);

        }
    }

    /**
     * 根据楼层标签id，获取楼层标签商品列表
     * 
     * @param storeyTag
     *            楼层标签信息封装bean
     */
    public void getGoodsListForStoreyTag(ThirdIndexStoreyTagBean storeyTag) {
        List<ChannelStoreyGoods> tagGoodsList = this.channelStoreyGoodsService.selectchannelStoreyGoodsByParamForSite(null, storeyTag.getChannelStoreyTagId(), null);
        // 遍历楼层标签商品列表，把商品列表塞到楼层标签信息bean中
        for (ChannelStoreyGoods goods : tagGoodsList) {
            ThirdIndexStoreyGoodsBean indexStoreyGoodsBean = new ThirdIndexStoreyGoodsBean();
            indexStoreyGoodsBean.setName(goods.getGoodsproductName());
            indexStoreyGoodsBean.setNumber(goods.getGoodsproductNo());
            indexStoreyGoodsBean.setPrice(String.valueOf(goods.getGoodsproductPrice()));
            indexStoreyGoodsBean.setUrlpic(goods.getGoodsproductImgsrc());
            indexStoreyGoodsBean.setId(String.valueOf(goods.getGoodsproductId()));
            storeyTag.getIndexGoodsList().add(indexStoreyGoodsBean);
        }
    }

    /**
     * 根据分页参数和频道ID、模板ID、楼层ID、楼层标签ID查询频道广告列表-前台展示用 channelId 频道ID tempId 模板ID
     * floorId 楼层ID floorTagId 楼层标签ID atId 广告分类ID 157大广告；159小广告；161页面广告
     * adverType 广告类型ID 现用151 temp1 分类导航ID temp3 是否是导航分类父框 0：不是 1：是 temp5 广告显示位置
     * 
     * @param storeyTag
     */
    public void getAdverListForStoreyTag(ThirdIndexStoreyTagBean storeyTag) {
        List<ChannelAdver> tagAdverList = this.channelAdverService.selectchannelAdverByParamForSite(null, null, null, storeyTag.getChannelStoreyTagId(), ATID3, ADVERTTYPE, null,
                "0", null, null);
        // 遍历楼层标签广告列表，把广告列表塞到楼层标签信息的bean中
        for (ChannelAdver adv : tagAdverList) {
            ThirdIndexStoreyAdverBean adverBean = new ThirdIndexStoreyAdverBean();
            adverBean.setAdverName(adv.getAdverName());
            adverBean.setAdverPath(adv.getAdverPath());
            adverBean.setAdverHref(adv.getAdverHref());
            adverBean.setAdverSort(adv.getAdverSort());
            adverBean.setDes(adv.getDes());
            adverBean.setTemp2(adv.getTemp2());
            adverBean.setChannelAdverId(adv.getChannelAdverId());
            storeyTag.getIndexAdverList().add(adverBean);
        }
    }

    /**
     * 根据店铺Id查询店铺信息
     * 
     * @param thirdId
     *            店铺Id{@link java.lang.Long}
     * @return
     */
    @Override
    public ThirdStoreInfo selectByThirdId(Long thirdId) {

        return storeInfoMapper.selectByThirdId(thirdId);
    }

    @Resource(name = "ChannelStoreyMapper")
    public void setChannelStoreyMapper(ChannelStoreyMapper channelStoreyMapper) {
        this.channelStoreyMapper = channelStoreyMapper;
    }

    @Resource(name = "ChannelStoreyGoodsService")
    public void setChannelStoreyGoodsService(ChannelStoreyGoodsService channelStoreyGoodsService) {
        this.channelStoreyGoodsService = channelStoreyGoodsService;
    }

    @Resource(name = "ChannelAdverService")
    public void setChannelAdverService(ChannelAdverService channelAdverService) {
        this.channelAdverService = channelAdverService;
    }

    @Resource(name = "ChannelStoreyTagService")
    public void setChannelStoreyTagService(ChannelStoreyTagService channelStoreyTagService) {
        this.channelStoreyTagService = channelStoreyTagService;
    }

    @Resource(name = "ThirdStoreInfoMapper")
    public void setStoreInfoMapper(ThirdStoreInfoMapper storeInfoMapper) {
        this.storeInfoMapper = storeInfoMapper;
    }

}
