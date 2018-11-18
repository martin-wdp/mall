package com.ningpai.goods.util;

/**
 * <p>
 * 查询的通用条件
 * </p>
 * 
 * @author liangck
 * @version 1.0
 * @since 15/8/28 10:03
 */
public class CommonConditions {

    /**
     * 查询关键字
     */
    private String keyWords;

    /**
     * 查询品牌
     */
    private String[] brands;

    /**
     * 查询的扩展参数
     */
    private String[] params;

    /**
     * 排序
     */
    private String sort;

    /**
     * 过滤的最小价格
     */
    private String priceMin;

    /**
     * 过滤的最大价格
     */
    private String priceMax;

    /**
     * boss分类ID
     */
    private String[] cats;

    /**
     * 仓库ID
     */
    private Long[] wareIds;

    /**
     * 第三方ID
     */
    private Long thirdId;

    /**
     * 第三方分类ID
     */
    private String[] thirdCats;

    /**
     * 只显示有货
     */
    private String showStock;

    /**
     * 手机端推荐
     */
    private String showMobile;

    /**
     * 是否为第三方商品
     */
    private String isThird;

    /**
     * 无参数构造器
     */
    public CommonConditions() {
    }

    /**
     * 构造一个包含关键词查询的查询条件
     * 
     * @param keyWords
     */
    public CommonConditions(String keyWords) {
        this.keyWords = keyWords;
    }

    /**
     * 构造通用查询条件实例
     * 
     * @param keyWords
     *            关键词
     * @param brands
     *            查询的品牌
     * @param params
     *            查询的扩展参数
     * @param sort
     *            排序
     */
    public CommonConditions(String keyWords, String[] brands, String[] params, String sort) {
        this.keyWords = keyWords;
        this.brands = brands == null ? null : brands.clone();
        this.params = params == null ? null : params.clone();
        this.sort = sort;
    }

    /**
     * 构造通用查询条件实例,不包含第三方查询条件
     * 
     * @param keyWords
     *            关键词
     * @param brands
     *            品牌
     * @param params
     *            扩展属性
     * @param sort
     *            排序
     * @param priceMin
     *            价格过滤下限
     * @param priceMax
     *            价格过滤上限
     * @param cats
     *            boss分类ID
     * @param wareIds
     *            仓库ID
     */
    public CommonConditions(String keyWords, String[] brands, String[] params, String sort, String priceMin, String priceMax, String[] cats, Long[] wareIds) {
        this.keyWords = keyWords;
        this.brands = brands == null ? null : brands.clone();
        this.params = params == null ? null : params.clone();
        this.sort = sort;
        this.priceMin = priceMin;
        this.priceMax = priceMax;
        this.cats = cats == null ? null : cats.clone();
        this.wareIds = wareIds == null ? null : wareIds.clone();
    }

    /**
     * 构造通用查询条件实例,包含第三方查询条件
     * 
     * @param keyWords
     *            关键词
     * @param brands
     *            品牌
     * @param params
     *            扩展属性
     * @param sort
     *            排序
     * @param priceMin
     *            价格过滤下限
     * @param priceMax
     *            价格过滤上限
     * @param cats
     *            boss分类ID
     * @param wareIds
     *            仓库ID
     * @param thirdId
     *            第三方ID
     * @param thirdCats
     */
    public CommonConditions(String keyWords, String[] brands, String[] params, String sort, String priceMin, String priceMax, String[] cats, Long[] wareIds, Long thirdId,
            String[] thirdCats) {
        this.keyWords = keyWords;
        this.brands = brands == null ? null : brands.clone();
        this.params = params == null ? null : params.clone();
        this.sort = sort;
        this.priceMin = priceMin;
        this.priceMax = priceMax;
        this.cats = cats == null ? null : cats.clone();
        this.wareIds = wareIds == null ? null : wareIds.clone();
        this.thirdId = thirdId;
        this.thirdCats = thirdCats == null ? null : thirdCats.clone();
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }
    /**
     * 获取品牌
     * */
    public String[] getBrands() {
        return brands.clone();
    }
    /**
     * 设置品牌
     * */
    public void setBrands(String[] brands) {
        this.brands = brands == null ? null : brands.clone();
    }
    /**
     * 获取Params
     * */
    public String[] getParams() {
        return params.clone();
    }
    /**
     * 设置Params
     * */
    public void setParams(String[] params) {
        this.params = params == null ? null : params.clone();
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getPriceMin() {
        return priceMin;
    }

    public void setPriceMin(String priceMin) {
        this.priceMin = priceMin;
    }

    public String getPriceMax() {
        return priceMax;
    }

    public void setPriceMax(String priceMax) {
        this.priceMax = priceMax;
    }

    public String[] getCats() {
        return cats;
    }

    public void setCats(String[] cats) {
        this.cats = cats;
    }
    /**
     * 获取WareIds
     * */
    public Long[] getWareIds() {
        return wareIds.clone();
    }
    /**
     * 设置WareId
     * */
    public void setWareIds(Long[] wareIds) {
        this.wareIds = wareIds == null ? null : wareIds.clone();
    }

    public Long getThirdId() {
        return thirdId;
    }

    public void setThirdId(Long thirdId) {
        this.thirdId = thirdId;
    }
    /**
     * 获取ThirdCates
     * */
    public String[] getThirdCats() {
        return thirdCats.clone();
    }
    /**
     * 设置ThirdCats
     * */
    public void setThirdCats(String[] thirdCats) {
        this.thirdCats = thirdCats == null ? null : thirdCats.clone();
    }

    public String getShowStock() {
        return showStock;
    }

    public void setShowStock(String showStock) {
        this.showStock = showStock;
    }

    public String getShowMobile() {
        return showMobile;
    }

    public void setShowMobile(String showMobile) {
        this.showMobile = showMobile;
    }

    public String getIsThird() {
        return isThird;
    }

    public void setIsThird(String isThird) {
        this.isThird = isThird;
    }
}
