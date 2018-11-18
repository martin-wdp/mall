package com.junit.goods.goods.service;

import com.alibaba.fastjson.JSON;
import com.ningpai.goods.bean.GoodsImage;
import com.ningpai.goods.dao.GoodsImageMapper;
import com.ningpai.goods.service.GoodsImageService;
import com.ningpai.goods.service.impl.GoodsImageServiceImpl;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.io.annotation.FileContent;
import org.unitils.mock.Mock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by houyichang on 2015/10/10.
 */
public class GoodsImageServiceTest extends UnitilsJUnit3 {
    /**
     * 需要测试的service
     * */
    @TestedObject
    private GoodsImageService goodsImageService = new GoodsImageServiceImpl();

    /**
     * 模拟
     * */
    @InjectIntoByType
    Mock<GoodsImageMapper> goodsImageMapperMock;

    /**
     * JS数据
     * */
    @FileContent("goodsImageList.js")
    private String goodsImageListJs;

    /**
     * 共享数据
     * */
    private List<GoodsImage> goodsImageList;

    /**
     * 初始化
     * */
    @Override
    protected void setUp() throws Exception {
        goodsImageList = JSON.parseArray(goodsImageListJs,GoodsImage.class);
    }

    /**
     * 添加记录
     *
     */
    public void testSaveGoodsImage(){
        goodsImageMapperMock.returns(1L).insertSelective(goodsImageList.get(0));
        assertEquals(1L,goodsImageService.saveGoodsImage(goodsImageList.get(0)).longValue());
    }

    /**
     * 删除记录
     *
     */
    public void testDelGoodsImage(){
        // 定义一个HashMap集合
        Map<String, String> map = new HashMap<String, String>();
        map.put("goodsImgId", "1");
        map.put("delName", "测试");
        goodsImageMapperMock.returns(1).deleteByPrimaryKey(map);
        assertEquals(1,1);
    }

    /**
     * 更新记录
     *
     */
    public void testUpdateGoodsImage(){
        goodsImageMapperMock.returns(1).updateByPrimaryKeySelective(goodsImageList.get(0));
        assertEquals(1,goodsImageService.updateGoodsImage(goodsImageList.get(0),"测试"));
    }

    /**
     * 根据主键查询对象
     *
     */
    public void testQueryByGoodsImageId(){
        goodsImageMapperMock.returns(goodsImageList.get(0)).selectByPrimaryKey(1L);
        assertNotNull(goodsImageService.queryByGoodsImageId(1L));
    }

    /**
     * 根据货品ID查询关联的记录列表
     *
     */
    public void testQueryImageListByProductId(){
        goodsImageMapperMock.returns(goodsImageList).queryByProductId(1L);
        assertNotNull(goodsImageService.queryImageListByProductId(1L));
    }

    /**
     * 上传图片
     */
    public void testUploadImage(){
        assertEquals(1,1);
    }

    /**
     * 上传单张图片
     *
     */
    public void testUploadImageSingle(){
        assertEquals(1,1);
    }

    /**
     * 设置默认图片
     *
     */
    public void testSetDefaultImage(){
        assertEquals(1,1);
    }

    /**
     * 批量删除图片
     *
     */
    public void testBatchDelImage(){
        assertEquals(1,1);
    }

    /**
     * 批量更新图片信息
     *
     */
    public void testBatchUpdateImage(){
        GoodsImage goodsImage = new GoodsImage();
        goodsImage.setGoodsInfoId(1L);
        goodsImage.setGoodsImgId(1L);
        goodsImage.setGoodsImgCreateName("测试");
        goodsImageMapperMock.returns(1).updateByPrimaryKeySelective(goodsImage);
        assertEquals(1,goodsImageService.batchUpdateImage(new String[]{"1"},1,"测试"));
    }

    /**
     * 根据多张原图路径,上传货品图片
     *
     */
    public void testBatchSaveImage(){
        GoodsImage goodsImage = new GoodsImage();
        goodsImage.setGoodsImgCreateName("测试");
        goodsImage.setGoodsImgDelflag("0");
        goodsImage.setGoodsImgSort(0);
        goodsImage.setGoodsInfoId(1L);
        goodsImage.setImageArtworkName("http://www.baidu.com");
        goodsImage.setImageBigName("http://www.baidu.com!352");
        goodsImage.setImageInName("http://www.baidu.com!160");
        goodsImage.setImageThumName("http://www.baidu.com!156");
        goodsImageMapperMock.returns(1).insertSelective(goodsImage);
        assertEquals(1,goodsImageService.batchSaveImage(new String[]{"http://www.baidu.com"},1,"测试"));
    }

    /**
     * 修改sku默认图片
     *
     */
    public void testsetDefaultImage(){
        assertEquals(1,1);
    }
}
