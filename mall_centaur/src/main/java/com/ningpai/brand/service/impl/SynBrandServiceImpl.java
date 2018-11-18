package com.ningpai.brand.service.impl;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ningpai.brand.bean.GoodsBrand;
import com.ningpai.brand.bean.TempBrand;
import com.ningpai.brand.dao.SynBrandMapper;
import com.ningpai.brand.service.SynBrandService;
import com.ningpai.category.bean.ReturnObject;
import com.ningpai.util.EstoreUtil;
import com.ningpai.util.HttpMethodUtil;
import com.ningpai.util.JsonUtil;

/**
 * E店宝同步品牌Service接口实现类
 * 
 * @author qiyuanyuan
 *
 */
@Service("SynBrandService")
public class SynBrandServiceImpl implements SynBrandService {

    /** 记录日志对象 */
    private static final Logger LOGGER = Logger.getLogger(SynBrandServiceImpl.class);

    // 返回格式
    public static final String format = "JSON";

    @Resource(name = "SynBrandMapper")
    private SynBrandMapper synBrandMapper;

    // 初始化插入数据条数为0
    int inserttemp = 0;

    // 初始化更新数据条数为0
    int updatetemp = 0;

    /**
     * 同步商品品牌信息
     * 
     * @see com.ningpai.brand.service.SynBrandService#SynchronousGoodsBrand()
     */
    public ReturnObject SynchronousGoodsBrand() {
        // 计算数量
        inserttemp = 0;
        updatetemp = 0;
        // 将请求放入TreeMap
        TreeMap<String, String> apiparamsMap = EstoreUtil.eStoreConfig();
        // 添加请求参数——返回格式
        apiparamsMap.put("format", format);
        // 添加请求参数——接口名称
        apiparamsMap.put("method", "edbProductBrandGet");

        // 添加请求参数——返回结果是否加密（0，为不加密 ，1.加密）
        apiparamsMap.put("slencry", "0");

        // 添加请求参数——IP地址
        apiparamsMap.put("ip", "192.168.60.80");

        // 添加请求参数——版本号（目前只提供2.0版本）
        apiparamsMap.put("v", "2.0");

        // 添加请求参数-fields
        apiparamsMap.put("fields", "brand_code,brand_name,enable");

        // 时间格式化
        String timestamp = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());

        // 添加请求参数——时间戳
        apiparamsMap.put("timestamp", timestamp);

        /*
         * apiparamsMap.put("page_no", "1");//分页 apiparamsMap.put("page_size",
         * "10");//页数量
         */
        // 获取数字签名
        String sign = HttpMethodUtil.md5Signature(apiparamsMap, apiparamsMap.get("appkey"));
        // 添加请求参数-sign
        apiparamsMap.put("sign", sign);

        // 定义StringBuilder
        StringBuilder param = new StringBuilder();

        // 定义返回对象
        ReturnObject ro = new ReturnObject();
        // for循环
        for (Iterator<Map.Entry<String, String>> it = apiparamsMap.entrySet().iterator(); it.hasNext();) {
            // 遍历请求map
            Map.Entry<String, String> e = it.next();
            // 判断key和token值
            if (!"appscret".equals(e.getKey()) && !"token".equals(e.getKey())) {
                // 判断key值
                if ("shop_id".equals(e.getKey()) || "wangwang_id".equals(e.getKey()) || "date_type".equals(e.getKey())) {
                    try {
                        // 拼接key值 加密后的value值
                        param.append("&").append(e.getKey()).append("=").append(HttpMethodUtil.encodeUri(e.getValue()));
                    } catch (UnsupportedEncodingException e1) {
                        // 异常日志信息
                        LOGGER.error("同步商品品牌信息失败！", e1);
                    }
                } else {
                    // 拼接key值 加密后的value值
                    param.append("&").append(e.getKey()).append("=").append(e.getValue());
                }
            }
        }
        // 定义String类型的变量PostData
        String postData;
        // param转成string类型并截取
        postData = param.toString().substring(1);
        // 定义String类型的result
        String result = "";
        try {
            // 连接到TOP服务器并获取数据 赋值给result
            result = HttpMethodUtil.getResult(EstoreUtil.eStoreUrl(), postData);
        } catch (Exception e) {
            LOGGER.error("同步商品品牌信息，请查看原因：", e);
            // 异常时返回状态0
            ro.setStatus(0);
            // 返回信息
            ro.setMessage("同步失败");
            // 返回对象
            return ro;
        }
        // 分隔\"item\":
        String s = result.split("\"item\":")[1];
        // 截取
        result = s.substring(0, s.length() - 3);
        // 将装换为json类型的数组赋值给dlist
        Object[] dlist = JsonUtil.getDTOArray(result, TempBrand.class);
        // 提示信息
        String mes = "";
        // 数组不为null并且数组长度不为0
        if (dlist != null && dlist.length != 0) {
            // 遍历dlist
            for (int i = 0; i < dlist.length; i++) {
                // 将遍历的dlist赋值给同步商品品牌对象
                TempBrand brand = (TempBrand) dlist[i];

                // 检测品牌是否存在
                int brandcheck = synBrandMapper.checkBrand(Long.valueOf(brand.getBrandCode()));
                // 定义商品品牌对象
                GoodsBrand gb = new GoodsBrand();
                // 品牌id设值
                gb.setBrandId(Long.valueOf(brand.getBrandCode()));
                // 品牌名称设值
                gb.setBrandName(brand.getBrandName());
                // 品牌昵称设值
                gb.setBrandNickname(brand.getBrandName());
                // 品牌为删除
                gb.setBrandDelflag("0");
                // 品牌SEOTitle设值
                gb.setBrandSeoTitle(brand.getBrandName());
                // 品牌SeoKeyword设值
                gb.setBrandSeoKeyword(brand.getBrandName());
                // 品牌SEodesc设值
                gb.setBrandSeoDesc(brand.getBrandName());
                // 品牌排序
                gb.setBrandSort(0);
                // 判断品牌是否存在
                if (brandcheck == 0) {
                    // 不存在 添加品牌
                    // 品牌创建来源
                    gb.setBrandCreateName("e店宝同步");
                    // 品牌创建时间
                    gb.setBrandCreateTime(new Date());
                    // 执行品牌插入操作
                    synBrandMapper.insertBrand(gb);
                    // 插入记录
                    inserttemp++;
                } else {
                    // 存在修改品牌
                    gb.setBrandModifiedName("e店宝同步");
                    // 品牌修改时间
                    gb.setBrandModifiedTime(new Date());
                    // 执行修改品牌操作
                    synBrandMapper.updateBrand(gb);
                    // 更新条数记录
                    updatetemp++;
                }

            }

            // E店铺同步数据信息
            mes = "共：" + (inserttemp + updatetemp) + "条,同步插入" + inserttemp + "条,同步修改" + updatetemp + "条";
            // 返回对象状态设值1
            ro.setStatus(1);
            // 返回对象消息设置mes
            ro.setMessage(mes);
        } else {
            // E店铺同步数据信息
            mes = "共：0条,同步插入" + inserttemp + "条,同步修改" + updatetemp + "条";
            // 返回对象状态设值1
            ro.setStatus(1);
            // 返回对象消息设置mes
            ro.setMessage(mes);
        }

        // return 返回对象
        return ro;

    }

}
