package com.ningpai.api.service;

/**
 * 开放接口-商品库存
 * @author lih
 * @version 2.0
 * @since 15/9/7
 */
public interface IProductWareService {

    /**
     * 根据货号查询库存
     * @param goodsInfoItemNo 货品编号
     * @return
     */
     String get(String goodsInfoItemNo);


    /**
     * 添加库存
     *
     * @param goodsInfoItemNo 货品编号
     * @param identifyNo      仓库标识
     * @param count           减去的数量
     * @return 添加结果
     */
    int addStock(String goodsInfoItemNo,String identifyNo,Long count);

    /**
     * 删除库存
     *
     * @param goodsInfoItemNo 货品编号
     * @param identifyNo      仓库标识
     * @param count           减去的数量
     * @return 删除结果
     */
    int minStock(String goodsInfoItemNo,String identifyNo,Long count);
}
