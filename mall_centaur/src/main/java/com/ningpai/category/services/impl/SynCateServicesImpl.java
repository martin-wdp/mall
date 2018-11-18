package com.ningpai.category.services.impl;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.category.bean.GoodsCategory;
import com.ningpai.category.bean.ReturnObject;
import com.ningpai.category.bean.TempCategory;
import com.ningpai.category.dao.SynCateMapper;
import com.ningpai.category.services.SynCateServices;
import com.ningpai.util.MyLogger;
import com.ningpai.util.EstoreUtil;
import com.ningpai.util.HttpMethodUtil;
import com.ningpai.util.JsonUtil;

/**
 * 同步e店宝分类Service接口实现类
 * 
 * @author ggn
 * 
 */
@Service("SynCateServices")
public class SynCateServicesImpl implements SynCateServices {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(SynCateServicesImpl.class);

    // 返回格式
    public static final String format = "JSON";

    // Spring注入
    @Resource(name = "SynCateMapper")
    private SynCateMapper synCateMapper;

    // 初始化插入数目 0
    int inserttemp = 0;
    // 初始化更新数目 0
    int updatetemp = 0;

    /**
     * 同步e店宝分类
     * 
     * @see com.ningpai.category.services.SynCateServices#SynchronousGoodsCateGory()
     */
    public ReturnObject SynchronousGoodsCateGory() {
        // 插入数目赋值0
        inserttemp = 0;
        // 更新数目赋值0
        updatetemp = 0;
        // 将请求放入TreeMap
        TreeMap<String, String> apiparamsMap = EstoreUtil.eStoreConfig();

        // 添加请求参数——返回格式
        apiparamsMap.put("format", format);

        // 添加请求参数——接口名称
        apiparamsMap.put("method", "edbProductClassGet");

        // 添加请求参数——返回结果是否加密（0，为不加密，1.加密）
        apiparamsMap.put("slencry", "0");

        // 添加请求参数——IP地址
        apiparamsMap.put("ip", "192.168.60.80");

        // 添加请求参数——版本号（目前只提供2.0版本）
        apiparamsMap.put("v", "2.0");

        // 添加请求参数-fields
        apiparamsMap.put("fields", "class_code,class_name,parent_class_code,is_pack");

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
            if (!"appscret".equals(e.getKey()) && "token".equals(e.getKey())) {
                // 判断key值
                if ("shop_id".equals(e.getKey()) || "wangwang_id".equals(e.getKey()) || "date_type".equals(e.getKey())) {
                    try {
                        // 拼接key值 加密后的value值
                        param.append("&").append(e.getKey()).append("=").append(HttpMethodUtil.encodeUri(e.getValue()));
                    } catch (UnsupportedEncodingException e1) {
                        // 异常日志信息
                        LOGGER.error("同步e店宝分类失败！", e1);
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
            LOGGER.error("",e);
            // 异常时返回状态0
            ro.setStatus(0);
            // 返回信息
            ro.setMessage("同步失败");
        }
        // 分隔\"item\":
        String s = result.split("\"item\":")[1];
        // 截取
        result = s.substring(0, s.length() - 3);

        Object[] dlist = JsonUtil.getDTOArray(result, TempCategory.class);
        // 提示信息
        String mes = "";
        // 数组不为null
        if (dlist != null) {
            // 定义List集合
            List<TempCategory> tempzero = new ArrayList<TempCategory>();
            // 定义List集合
            List<TempCategory> tempall = new ArrayList<TempCategory>();
            // 遍历dlist
            for (Object cate : dlist) {
                TempCategory ca = (TempCategory) cate;
                // 如果该分类是一级目录 设置层级为1
                if ("0".equals(ca.getParent_class_code())) {
                    // 分类等级设值
                    ca.setCat_grade("1");
                    // 分类排序设值
                    ca.setSort(1);
                    // 添加到集合tempzero
                    tempzero.add(ca);
                } else {
                    // 添加到集合tempall
                    tempall.add(ca);
                }

            }
            // 遍历临时分类对象
            for (TempCategory cate : tempzero) {
                // 执行操作数据的操作
                opeartionData(cate, tempall);
            }
            // 赋值mes插入和更新信息数量
            mes = "共：" + (inserttemp + updatetemp) + "条,同步插入" + inserttemp + "条,同步修改" + updatetemp + "条";
            // 返回对象状态设值1
            ro.setStatus(1);
            // 返回对象消息设置mes
            ro.setMessage(mes);
        } else {
            // 赋值mes插入和更新信息数量
            mes = "共：0条,同步插入" + inserttemp + "条,同步修改" + updatetemp + "条";
            // 返回对象状态设值1
            ro.setStatus(1);
            // 返回对象消息设置mes
            ro.setMessage(mes);
        }

        return ro;
    }

    /**
     * 操作对象
     * 
     * @param cate
     *            临时分类对象@{link com.ningpai.category.bean.TempCategory}
     * @param tempall
     *            list集合
     */
    public void opeartionData(TempCategory cate, List<TempCategory> tempall) {
        // cate传给临时分类对象ca
        TempCategory ca = cate;
        // 查询分类是否存在
        int ischeck = synCateMapper.checkCate(Long.valueOf(ca.getClass_code()));
        // 定义商品分类对象
        GoodsCategory category = new GoodsCategory();
        // 商品分类id设值ca的类编码
        category.setCatId(Long.valueOf(ca.getClass_code()));
        // 商品分类名称设值ca类名称
        category.setCatName(ca.getClass_name());
        // 判断是否是一级分类
        if (ca.getClass_code().equals(ca.getParent_class_code())) {
            // 一级分类
            ca.setParent_class_code("0");
        }
        // 父分类id
        category.setCatParentId(Long.valueOf(ca.getParent_class_code()));
        // 分类创建时间
        category.setCatCreateTime(new Date());
        // 分类来源
        category.setCatCreateName("e店宝同步新增");
        // 分类未删除
        category.setCatDelflag("0");
        // 分类排序
        category.setCatSort(ca.getSort());
        // 分类等级
        category.setCatGrade(Integer.parseInt(ca.getCat_grade()));
        // 检查是否存在
        if (ischeck == 0) {
            // 不存在执行插入 并计数
            category.setTypeId(0L);
            // 插入数量叠加
            inserttemp += synCateMapper.insertGoodsCate(category);
            // 遍历集合
            for (int i = 0; i < tempall.size(); i++) {
                // 判断分类子集关系
                if (cate.getClass_code().equals(tempall.get(i).getParent_class_code())) {
                    tempall.get(i).setCat_grade(addNumber(cate.getCat_grade()));
                    // 操作数据
                    opeartionData(tempall.get(i), tempall);
                }
            }
        } else {
            // 存在 执行修改
            category.setCatModifiedName("e店宝同步修改");
            // 修改时间
            category.setCatModifiedTime(new Date());
            // 更新数目叠加
            updatetemp += synCateMapper.updateGoodsCate(category);
            // 遍历集合
            for (int i = 0; i < tempall.size(); i++) {
                // 判断分类子集关系
                if (cate.getClass_code().equals(tempall.get(i).getParent_class_code())) {
                    // 分类等级
                    tempall.get(i).setCat_grade(addNumber(cate.getCat_grade()));
                    // 执行数据操作
                    opeartionData(tempall.get(i), tempall);
                }
            }
        }

    }

    /**
     * 数目+1
     * 
     * @param start
     * @return
     */
    public String addNumber(String start) {
        // String转换类型Integer
        Integer s = Integer.parseInt(start) + 1;
        // 转成String类型
        return s.toString();
    }

}
