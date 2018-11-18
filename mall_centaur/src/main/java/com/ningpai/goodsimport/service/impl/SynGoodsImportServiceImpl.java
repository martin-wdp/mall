package com.ningpai.goodsimport.service.impl;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ningpai.category.bean.ReturnObject;
import com.ningpai.goods.controller.GoodsAtteController;
import com.ningpai.goodsimport.bean.GoodsImport;
import com.ningpai.goodsimport.bean.TempGoodsImport;
import com.ningpai.goodsimport.dao.SynGoodsImportMapper;
import com.ningpai.goodsimport.service.SynGoodsImportService;
import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.util.EstoreUtil;
import com.ningpai.util.HttpMethodUtil;
import com.ningpai.util.JsonUtil;

/**
 * E店宝商品导入Service接口实现类
 * 
 * @author qiyuanyuan
 * 
 */
@Service("SynGoodsImportService")
public class SynGoodsImportServiceImpl extends BasicSqlSupport implements SynGoodsImportService {

    /** 记录日志对象 */
    private static final Logger LOGGER = Logger.getLogger(GoodsAtteController.class);

    // 返回格式
    public static final String format = "JSON";

    @Resource(name = "SynGoodsImportMapper")
    private SynGoodsImportMapper synGoodsImportMapper;

    // 初始化插入条数0
    int inserttemp = 0;
    // 初始化更新条数0
    int updatetemp = 0;

    /**
     * 
     * 同步
     * 
     * @see com.ningpai.brand.service.SynBrandService#SynchronousGoodsBrand()
     */
    public ReturnObject SynchronousGoodsImport() {
        inserttemp = 0;
        updatetemp = 0;
        TreeMap<String, String> apiparamsMap = EstoreUtil.eStoreConfig();

        // 添加请求参数——返回格式
        apiparamsMap.put("format", format);

        // 添加请求参数——接口名称
        apiparamsMap.put("method", "edbProductBaseInfoGet");

        // 添加请求参数——返回结果是否加密（0，为不加密 ，1.加密）
        apiparamsMap.put("slencry", "0");

        // 添加请求参数——IP地址
        apiparamsMap.put("ip", "192.168.60.80");

        // 添加请求参数——版本号（目前只提供2.0版本）
        apiparamsMap.put("v", "2.0");

        // 添加请求参数-fields
        apiparamsMap.put("fields", "proBI_id,pro_code,pro_name,market_price,pro_intro,pro_class,sell_price,weight,pro_picture,cost,avg_taxCost");

        // 日期格式化
        String timestamp = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());

        // 添加请求参数——时间戳
        apiparamsMap.put("timestamp", timestamp);

        /*
         * apiparamsMap.put("page_no", "1");//分页 apiparamsMap.put("page_size",
         * "10");//页数量
         */
        // 获取数字签名
        String sign = HttpMethodUtil.md5Signature(apiparamsMap, apiparamsMap.get("appkey"));

        // 添加请求参数-数字签名
        apiparamsMap.put("sign", sign);

        // 定义StringBuilder类型的param
        StringBuilder param = new StringBuilder();

        // 定义返回对象
        ReturnObject ro = new ReturnObject();
        // 遍历参数
        for (Iterator<Map.Entry<String, String>> it = apiparamsMap.entrySet().iterator(); it.hasNext();) {
            Map.Entry<String, String> e = it.next();
            // 判断key值和token
            if (!"appscret".equals(e.getKey()) && !"token".equals(e.getKey())) {
                if ("shop_id".equals(e.getKey()) || "wangwang_id".equals(e.getKey()) || "date_type".equals(e.getKey())) {
                    try {
                        // 拼接参数传给param
                        param.append("&").append(e.getKey()).append("=").append(HttpMethodUtil.encodeUri(e.getValue()));
                    } catch (UnsupportedEncodingException e1) {
                        // 日志记录异常信息
                        LOGGER.error("同步e店宝分类", e1);
                    }
                } else {
                    // 拼接参数传给param
                    param.append("&").append(e.getKey()).append("=").append(e.getValue());
                }
            }
        }
        // 定义String类型的PostData
        String postData;
        // 将param转换成字符串类型并且截取再赋值给PostData
        postData = param.toString().substring(1);
        // 定义String类型的result
        String result;

        // 将连接到TOP服务器并获取数据赋值给result
        result = HttpMethodUtil.getResult(EstoreUtil.eStoreUrl(), postData);
        // 不捕获异常
        try {
            // 用\"item\":分隔result并赋值给s
            String s = result.split("\"item\":")[1];
            // 截取s并赋值给result
            result = s.substring(0, s.length() - 3);
        } catch (Exception e) {
            LOGGER.error("",e);
            // 返回对象设置状态值‘0’
            ro.setStatus(0);
            // 返回对象设置message“同步失败”
            ro.setMessage("同步失败");
            // return 返回对象
            return ro;
        }
        // 将装换为json类型的数组赋值给dlist
        Object[] dlist = JsonUtil.getDTOArray(result, TempGoodsImport.class);
        // 提示信息
        String mes = "";
        // 数组不为null并且数组长度不为0
        if (dlist != null && dlist.length != 0) {
            // 遍历dlist
            for (int i = 0; i < dlist.length; i++) {
                // 将遍历的dlist赋值给同步商品对象
                TempGoodsImport tempGood = (TempGoodsImport) dlist[i];

                // 检测品牌是否存在
                int brandcheck = synGoodsImportMapper.checkGoods(tempGood.getPro_code());
                // 判断商品是否存在
                if (brandcheck == 0) {
                    // 不存在 添加商品
                    GoodsImport goodImport = new GoodsImport();
                    // 商品名称设值
                    goodImport.setGoodsName(tempGood.getPro_name());
                    // 商品副标题设置
                    goodImport.setGoodsSubtitle(tempGood.getPro_name());
                    // 商品销售价设值
                    goodImport.setGoodsPrice(tempGood.getSell_price());
                    // 商品成本价设值
                    goodImport.setGoodsCostPrice(tempGood.getAvg_taxCost());
                    // SEOkey设值
                    goodImport.setSeoKey(tempGood.getPro_name());
                    // SEOtitle设值
                    goodImport.setSeoTit(tempGood.getPro_name());
                    // SEOdec设值
                    goodImport.setSeoDes(tempGood.getPro_name());
                    // 商品描述设值
                    goodImport.setGoodsDesp(tempGood.getPro_intro());
                    // 商品促销价设值
                    goodImport.setGoodsMarketPrice(tempGood.getMarket_price());
                    // 商品上架
                    goodImport.setAddFlag("0");
                    // 商品为删除
                    goodImport.setDelFlag("0");
                    // 平台商品
                    goodImport.setThirdId(0L);
                    // 商品来源
                    goodImport.setThirdName("e店宝");
                    // 商品id
                    goodImport.seteGoodsId(Long.valueOf(tempGood.getProBI_id()));
                    // 商品编号
                    goodImport.seteGoodsNo(tempGood.getPro_code());
                    // 插入商品
                    synGoodsImportMapper.insertGoods(goodImport);
                    // 插入记录
                    inserttemp++;
                }

            }
            // 消息记录数据
            mes = "共：" + (inserttemp + updatetemp) + "条,同步插入" + inserttemp + "条";
            // 返回对象设置状态为1
            ro.setStatus(1);
            // 将消息传给返回对象
            ro.setMessage(mes);
        } else {
            // 同步消息记录
            mes = "共：0条,同步插入" + inserttemp + "条";
            // 返回对象设置状态为1
            ro.setStatus(1);
            // 将消息传给返回对象
            ro.setMessage(mes);
        }
        // 返回对象
        return ro;

    }

    /**
     * 同步更新
     * 
     * @see com.ningpai.goodsimport.service.SynGoodsImportService#
     *      SynchronousUpdateGoodsImport(java.lang.String)
     */
    public ReturnObject SynchronousUpdateGoodsImport(String goodsNo) {
        // 赋值插入条数0
        inserttemp = 0;
        // 赋值更新条数0
        updatetemp = 0;
        // 定义请求参数
        TreeMap<String, String> apiparamsMap = EstoreUtil.eStoreConfig();

        // 添加请求参数——返回格式
        apiparamsMap.put("format", format);

        // 添加请求参数——接口名称
        apiparamsMap.put("method", "edbProductBaseInfoGet");

        // 添加请求参数——返回结果是否加密（0，为不加密 ，1.加密）
        apiparamsMap.put("slencry", "0");

        // 添加请求参数——IP地址
        apiparamsMap.put("ip", "192.168.60.80");

        // 添加请求参数——版本号（目前只提供2.0版本）
        apiparamsMap.put("v", "2.0");

        // 添加请求参数-fields
        apiparamsMap.put("fields", "proBI_id,pro_code,pro_name,market_price,pro_intro,pro_class,sell_price,weight,pro_picture,cost,avg_taxCost");

        // 日期格式化
        String timestamp = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());

        // 添加请求参数——时间戳
        apiparamsMap.put("timestamp", timestamp);

        // 商品编号
        // apiparamsMap.put("ProductNum", goodsNo);
        // 获取数字签名
        String sign = HttpMethodUtil.md5Signature(apiparamsMap, apiparamsMap.get("appkey"));

        // 添加请求参数-数字签名
        apiparamsMap.put("sign", sign);

        // 定义StringBuilder类型的param
        StringBuilder param = new StringBuilder();

        // 定义返回对象
        ReturnObject ro = new ReturnObject();
        // 遍历参数
        for (Iterator<Map.Entry<String, String>> it = apiparamsMap.entrySet().iterator(); it.hasNext();) {
            Map.Entry<String, String> e = it.next();
            // 判断key值和token
            if (!"appscret".equals(e.getKey()) && !"token".equals(e.getKey())) {
                if ("shop_id".equals(e.getKey()) || "wangwang_id".equals(e.getKey()) || "date_type".equals(e.getKey())) {
                    try {
                        // 拼接参数传给param
                        param.append("&").append(e.getKey()).append("=").append(HttpMethodUtil.encodeUri(e.getValue()));
                    } catch (UnsupportedEncodingException e1) {
                        // 日志记录异常信息
                        LOGGER.error("同步修改商品失败！", e1);
                    }
                } else {
                    // 拼接参数传给param
                    param.append("&").append(e.getKey()).append("=").append(e.getValue());
                }
            }
        }

        // 定义String类型的PostData
        String postData;
        // 将param转换成字符串类型并且截取再赋值给PostData
        postData = param.toString().substring(1);
        // 定义String类型的result
        String result;

        // 将连接到TOP服务器并获取数据赋值给result
        result = HttpMethodUtil.getResult(EstoreUtil.eStoreUrl(), postData);
        // 不捕获异常
        try {
            // 用\"item\":分隔result并赋值给s
            String s = result.split("\"item\":")[1];
            // 截取s并赋值给result
            result = s.substring(0, s.length() - 3);
        } catch (Exception e) {
            LOGGER.error("",e);
            // 返回对象设置状态值‘0’
            ro.setStatus(0);
            // 返回对象设置message“同步失败”
            ro.setMessage("同步失败");
            // return 返回对象
            return ro;
        }
        // 将装换为json类型的数组赋值给dlist
        Object[] dlist = JsonUtil.getDTOArray(result, TempGoodsImport.class);
        // 提示信息
        String mes = "";
        // 数组不为null并且数组长度不为0
        if (dlist != null && dlist.length != 0) {
            // 将遍历的dlist赋值给同步商品对象
            TempGoodsImport tempGood = (TempGoodsImport) dlist[0];

            // 检测品牌是否存在
            int brandcheck = synGoodsImportMapper.checkGoods(tempGood.getPro_code());
            // 判断商品是否存在
            if (brandcheck == 1) {
                // 不存在 添加商品
                GoodsImport goodImport = new GoodsImport();
                // 商品名称设值
                goodImport.setGoodsName(tempGood.getPro_name());
                // 商品副标题设置
                goodImport.setGoodsSubtitle(tempGood.getPro_name());
                // 商品销售价设值
                goodImport.setGoodsPrice(tempGood.getSell_price());
                // 商品成本价设值
                goodImport.setGoodsCostPrice(tempGood.getAvg_taxCost());
                // SEOkey设值
                goodImport.setSeoKey(tempGood.getPro_name());
                // SEOtitle设值
                goodImport.setSeoTit(tempGood.getPro_name());
                // SEOdec设值
                goodImport.setSeoDes(tempGood.getPro_name());
                // 商品描述设值
                goodImport.setGoodsDesp(tempGood.getPro_intro());
                // 商品促销价设值
                goodImport.setGoodsMarketPrice(tempGood.getMarket_price());
                // 商品上架
                goodImport.setAddFlag("0");
                // 商品为删除
                goodImport.setDelFlag("0");
                // 平台商品
                goodImport.setThirdId(0L);
                // 商品来源
                goodImport.setThirdName("e店宝");
                // 商品id
                goodImport.seteGoodsId(Long.valueOf(tempGood.getProBI_id()));
                // 商品编号
                goodImport.seteGoodsNo(tempGood.getPro_code());
                // 更新商品
                synGoodsImportMapper.updateGoods(goodImport);
                // 更新记录
                updatetemp++;
            }
            // 消息记录数据
            mes = "共：" + (inserttemp + updatetemp) + "条,同步更新" + updatetemp + "条";
            // 返回对象设置状态为1
            ro.setStatus(1);
            // 将消息传给返回对象
            ro.setMessage(mes);
        } else {
            // 同步更新商品的记录
            mes = "共：0条,同步更新" + updatetemp + "条";
            // 返回对象设置状态为1
            ro.setStatus(1);
            // 将消息传给返回对象
            ro.setMessage(mes);
        }
        // return 返回对象
        return ro;
    }

}
