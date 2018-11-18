package com.ningpai.goods.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.goods.bean.ImageSet;
import com.ningpai.goods.bean.InfoImageManage;

/**
 * 图片设置接口层
 *
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月17日 上午9:53:42
 * @version 1.0
 */
public interface ImageSetMapper {
    /**
     * 查询图片规则集合
     */
    List<ImageSet> queryImageSet();

    /**
     * 根据原图路径查询图片信息
     * 
     * @param paramMap
     *            包含原图路径url
     * @return 图片信息
     */
    InfoImageManage queryImageByUrl(Map<String, Object> paramMap);
}
