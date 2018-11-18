/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.order.service.impl;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ningpai.order.bean.Order;
import com.ningpai.order.bean.OrderELog;
import com.ningpai.order.bean.OrderGoods;
import com.ningpai.order.bean.OrderInfo;
import com.ningpai.order.bean.ProductItem;
import com.ningpai.order.bean.TempOrder;
import com.ningpai.order.mapper.OrderELogMapper;
import com.ningpai.order.service.OrderService;
import com.ningpai.order.service.SynOrderService;
import com.ningpai.system.estore.bean.EStore;
import com.ningpai.system.estore.service.EStoreService;
import com.ningpai.util.HttpMethodUtil;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import com.ningpai.util.Util;
import com.ningpai.util.XStreamUtil;

/**
 * E店宝同步订单Service接口实现类
 * 
 * @author qiyuanyuan
 * 
 */
@Service("SynOrderService")
public class SynOrderServiceImpl implements SynOrderService {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(SynOrderServiceImpl.class);

    private static final String SLENCRY = "slencry";
    private static final String APPSCRET = "appscret";
    private static final String TOKEN = "token";

    // 返回格式
    public static final String format = "JSON";

    // Spring注入
    @Resource(name = "OrderService")
    private OrderService orderService;
    // Spring注入
    @Resource(name = "estoreService")
    private EStoreService estoreService;

    /* e店宝日志 */
    @Resource(name = "OrderELogMapper")
    private OrderELogMapper eLogMapper;

    /**
     * E店宝同步订单Service接口
     * 
     * @see com.ningpai.order.service.SynOrderService#SynOrder(java.lang.Long)
     */
    @Transactional
    public int SynOrder(Long orderId) {
        EStore eStore = estoreService.findEStore();
        if ("1".equals(eStore.getIsopen())) {
            return 2;
        }
        TreeMap<String, String> apiparamsMap = new TreeMap<String, String>();

        // 添加请求参数——主帐号
        apiparamsMap.put("dbhost", eStore.getDbhost());

        // 添加请求参数——返回格式
        apiparamsMap.put("format", "xml");

        // 添加请求参数——接口名称
        apiparamsMap.put("method", "edbTradeAdd");

        // 添加请求参数——返回结果是否加密（0，为不加密，1.加密）
        apiparamsMap.put(SLENCRY, "0");

        // 添加请求参数——IP地址
        apiparamsMap.put("ip", "192.168.60.80");

        // 添加请求参数——appkey
        apiparamsMap.put("appkey", eStore.getAppkey());

        // 添加请求参数——appscret
        apiparamsMap.put(APPSCRET, eStore.getAppscret());

        // 添加请求参数——token
        apiparamsMap.put(TOKEN, eStore.getToken());

        // 添加请求参数——版本号（目前只提供2.0版本）
        apiparamsMap.put("v", "2.0");

        // 添加请求参数-fields
        apiparamsMap.put("fields", "result");

        // 时间格式化
        String timestamp = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());

        // 添加请求参数——时间戳
        apiparamsMap.put("timestamp", timestamp);
        // 根据订单id查询订单详情
        Order netorder = orderService.orderDetail(orderId);
        // 创建TempOrder对象
        TempOrder order = new TempOrder();
        // 判断订单是否为空
        if (netorder != null) {

            // 封装订单信息
            OrderInfo orderInfo = new OrderInfo();
            // 订单联系号码
            orderInfo.setMobilPhone(netorder.getShippingMobile());
            // 实收运费
            orderInfo.setActual_freight_get(netorder.getExpressPrice().doubleValue());
            // 实付运费
            orderInfo.setActual_freight_pay(netorder.getExpressPrice().doubleValue());
            // 订单实收金额
            orderInfo.setActual_RP(netorder.getOrderPrice().doubleValue());
            // 收货地址
            orderInfo.setAddress(netorder.getShippingAddress());
            // 仓库id
            orderInfo.setStorage_id(1);
            // 线上请使用注释内容
            // orderInfo.setExpress(netorder.getOrderExpress().getExpressName());
            // orderInfo.setWuLiu(netorder.getOrderExpress().getExpressName());
            // 快递
            orderInfo.setExpress("自发物流");
            // 物流
            orderInfo.setWuLiu("自发物流");
            // 地区
            orderInfo.setArea(netorder.getShippingCounty());
            // 买家ID
            orderInfo.setBuyer_id(String.valueOf(netorder.getCustomerId()));
            // 买家留言
            orderInfo.setBuyer_msg(netorder.getCustomerRemark());
            // 收货城市
            orderInfo.setCity(netorder.getShippingCity());
            // 收货人
            orderInfo.setConsignee(netorder.getShippingPerson());
            // 预计发货日期
            orderInfo.setDeliver_date_plan(null);
            // 默认发货状态
            orderInfo.setDeliver_status("未发货");
            // 优惠金额:订单总优惠金额
            orderInfo.setFavorable_money(netorder.getOrderPrePrice().doubleValue());
            // 发票内容
            orderInfo.setInvoice_msg(netorder.getInvoiceContent());
            // 发票抬头
            orderInfo.setInvoice_title(netorder.getInvoiceTitle());
            // 发票类型
            orderInfo.setInvoice_type(netorder.getInvoiceType());
            // 开票情况(是否已开发票0：无1：有)默认0
            orderInfo.setIs_invoiceOpened(0);
            // 是否需要开发票（0：不需要1：需要）默认0
            orderInfo.setIs_needInvoice(0);
            // 是否积分换购（0：不是1：是）默认：0
            orderInfo.setIs_scorePay(0);
            // 订单总金额
            orderInfo.setOrder_totalMoney(netorder.getOrderPrice().doubleValue());
            // 订单类型（等货订单囤货订单换货订单其他订单预售订单正常订单中断订单）默认：正常订单
            orderInfo.setOrder_type("正常订单");
            // 其他备注
            orderInfo.setOther_remark(netorder.getCustomerRemark());
            // 外部平台快递方式
            orderInfo.setOut_express_method("");
            // 外部平台快递订单状态
            orderInfo.setOut_order_status("");
            // 外部平台付款单号
            orderInfo.setOut_payNo(netorder.getOrderCode());
            // 外部平台单号
            orderInfo.setOut_tid(netorder.getOrderCode());
            // 判断订单状态 未付款
            if ("0".equals(netorder.getOrderStatus()) && "1".equals(netorder.getOrderLinePay())) {
                orderInfo.setPay_status("未付款");
            } else if ("4".equals(netorder.getOrderStatus())) {
                // 判断订单状态 已作废
                orderInfo.setPay_status("已作废");
            } else {
                // 判断订单状态 已付款
                orderInfo.setPay_status("已付款");
                // 付款时间
                orderInfo.setPay_date(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));

            }
            if ("0".equals(netorder.getOrderLinePay())) {
                // 是否货到付款
                orderInfo.setIs_COD(1);
                // 支付方式
                orderInfo.setPay_method("货到付款");
            } else {
                // 是否货到付款
                orderInfo.setIs_COD(0);
                // 支付方式
                orderInfo.setPay_method("在线支付");
            }

            // 支付积分
            orderInfo.setPay_score(0);
            // 收货人邮编
            orderInfo.setPostcode(netorder.getShippingPostcode());
            // 收货人省份
            orderInfo.setPrivince(netorder.getShippingProvince());
            // 处理状态（未确认已财务审核已归档已确认已作废）默认：未确认
            orderInfo.setProcess_status("未确认");
            // 产品总金额
            orderInfo.setProduct_totalMoney(netorder.getOrderPrice().doubleValue());
            // 返点积分
            orderInfo.setReturn_score(0);
            // 客服备注
            orderInfo.setSeller_remark("");
            // 货到付款服务费
            orderInfo.setServerCost_COD(new Double(0));
            // 配送方式
            orderInfo.setShip_method("");
            // 店铺编号店铺编号（E店宝中档案管理—基本档案—店铺设置查看）
            orderInfo.setShop_id("1");
            orderInfo.setTelephone(netorder.getShippingMobile());
            // 终端类型(电脑-手机-电话)
            orderInfo.setTerminal_type("电脑");
            orderInfo.setTid(netorder.getOrderCode());
            // 获取订单商品信息
            List<OrderGoods> goodslist = netorder.getOrderGoodsList();
            // 货品类目
            List<ProductItem> prolist = new ArrayList<ProductItem>();
            if (goodslist != null && !goodslist.isEmpty()) {
                for (int i = 0; i < goodslist.size(); i++) {

                    ProductItem pi = new ProductItem();
                    // 条形码
                    pi.setBarCode(goodslist.get(i).getGoodsInfoItemNo());
                    // 成本价
                    pi.setCost_Price(goodslist.get(i).getGoodsInfoPrice().doubleValue());
                    // 优惠金额:订单总优惠金额
                    pi.setFavorite_money(goodslist.get(i).getGoodsCouponPrice().doubleValue());
                    // 赠品数量
                    pi.setGift_Num(new Double(0));
                    // 是否预约
                    pi.setIs_Book(0);
                    // 是否赠品
                    pi.setIs_Gift(0);
                    // 是否预售
                    pi.setIs_presell(0);
                    // 订货数量
                    pi.setOrderGoods_Num(goodslist.get(i).getGoodsInfoNum());
                    // 外部平台条形码
                    pi.setOut_barCode(goodslist.get(i).getGoodsInfoItemNo());
                    // 外部价格
                    pi.setOut_price(goodslist.get(i).getGoodsInfoPrice().doubleValue());
                    // 外部平台产品Id
                    pi.setOut_productId(goodslist.get(i).getGoodsId().toString());
                    // 外部平台单号
                    pi.setOut_tid(netorder.getOrderCode());
                    // 产品运费
                    pi.setProduct_freight(new Double(0));
                    // 产品简介
                    pi.setProduct_intro(goodslist.get(i).getGoodsProductVo().getGoodsInfoSubtitle());
                    // 产品缺货情况
                    pi.setProduct_stockout("");
                    // 铺编号
                    pi.setShop_id("1");
                    // 网店品名
                    pi.setProduct_title(goodslist.get(i).getGoodsProductVo().getGoodsInfoName());
                    // 网店规格
                    pi.setStandard("默认");
                    // 订单编号
                    pi.setTid(netorder.getOrderCode());
                    // 添加到prolist
                    prolist.add(pi);
                }
            }
            // 订单信息
            order.setOrderInfo(orderInfo);
            // 货品信息
            order.setProductInfo(prolist);
            String xmlValues = XStreamUtil.BeanToXml(order);

            apiparamsMap.put("xmlValues", xmlValues);

            // 获取数字签名
            String sign = Util.md5Signature(apiparamsMap, eStore.getAppkey());

            // 添加请求参数-sign
            apiparamsMap.put("sign", sign);

            StringBuilder param = new StringBuilder();

            for (Iterator<Map.Entry<String, String>> it = apiparamsMap.entrySet().iterator(); it.hasNext();) {
                Map.Entry<String, String> e = it.next();
                if (!APPSCRET.equals(e.getKey()) && !TOKEN.equals(e.getKey())) {
                    if ("xmlValues".equals(e.getKey())) {
                        try {
                            param.append("&").append(e.getKey()).append("=").append(Util.encodeUri(e.getValue()));
                        } catch (UnsupportedEncodingException e1) {
                            LOGGER.error("",e1);
                            // 订单编号
                            OrderELog log = new OrderELog();
                            // 创建时间
                            log.setCreateTime(new Date());
                            log.setFlag("0");
                            // 订单编号
                            log.setOrderCode(netorder.getOrderCode());
                            // 订单id
                            log.setOrderId(netorder.getOrderId());
                            // 日志内容
                            log.setOrderLogContent("订单导入异常！");
                            // 执行添加日志操作
                            eLogMapper.addLog(log);
                        }
                    } else {
                        param.append("&").append(e.getKey()).append("=").append(e.getValue());
                    }
                }
            }
            String postData;
            postData = param.toString().substring(1);
            String result = Util.getResult(eStore.getServer(), postData);
            // xml解析
            Document document;
            try {
                document = DocumentHelper.parseText(result);
                Element root = document.getRootElement();
                @SuppressWarnings("unchecked")
                List<Element> elements = root.elements();
                for (Iterator<Element> it = elements.iterator(); it.hasNext();) {
                    Element element = it.next();
                    if (!"Rows".equals(element.getName())) {
                        continue;
                    }
                    @SuppressWarnings("unchecked")
                    List<Element> es = element.elements();
                    for (Iterator<Element> its = es.iterator(); its.hasNext();) {
                        Element e = its.next();
                        // 判断发货状态
                        if ("is_success".equals(e.getName())) {
                            if ("True".equals(e.getText())) {
                                return 1;
                            } else {
                                addLog(netorder, result);
                            }
                        }
                    }
                }
            } catch (DocumentException e1) {
                LOGGER.error("",e1);
                addLog(netorder, result);
            }

            addLog(netorder, "参数错误");
            return 0;
        } else {
            return 0;
        }

    }

    /**
     * 添加日志
     * 
     * @param netorder
     * @param result
     */
    public void addLog(Order netorder, String result) {
        // 创建OrderELog对象
        OrderELog log = new OrderELog();
        // 创建时间
        log.setCreateTime(new Date());
        // 删除标志
        log.setFlag("0");
        // 订单code
        log.setOrderCode(netorder.getOrderCode());
        // 订单id
        log.setOrderId(netorder.getOrderId());
        // 日志订单内容
        log.setOrderLogContent("订单导入失败！" + result);
        // 执行添加日志操作
        eLogMapper.addLog(log);
    }

    /**
     * 获取E店宝订单信息并修改当前订单信息
     *
     */
    @Transactional
    public int getOrder(Long[] orderId) {
        EStore eStore = estoreService.findEStore();
        // 判断E店宝是否开启
        if ("1".equals(eStore.getIsopen())) {
            // 不开启则返回2
            return 2;
        }
        for (int i = 0; i < orderId.length; i++) {
            Order netorder = orderService.getPayOrder(orderId[i]);
            // 判断订单状态是否符合条件 (如果订单状态不正确，则不进行该订单的查询)
            if (!"1".equals(netorder.getOrderStatus()) && !"0".equals(netorder.getOrderStatus())) {
                continue;
            }
            TreeMap<String, String> apiparamsMap = new TreeMap<String, String>();

            // 添加请求参数——主帐号
            apiparamsMap.put("dbhost", eStore.getDbhost());

            // 添加请求参数——返回格式
            apiparamsMap.put("format", "XML");

            // 添加请求参数——返回结果是否加密（0，为不加密，1.加密）
            apiparamsMap.put(SLENCRY, "0");

            // 添加请求参数——appkey
            apiparamsMap.put("appkey", eStore.getAppkey());

            // 添加请求参数——appscret
            apiparamsMap.put(APPSCRET, eStore.getAppscret());

            // 添加请求参数——token
            apiparamsMap.put(TOKEN, eStore.getToken());

            // 添加请求参数——版本号（目前只提供2.0版本）
            apiparamsMap.put("v", "2.0");

            // 添加请求参数——接口名称
            apiparamsMap.put("method", "edbTradeGet");

            // 添加请求参数——返回结果是否加密（0，为不加密，1.加密）
            apiparamsMap.put(SLENCRY, "0");

            // 添加请求参数——IP地址
            apiparamsMap.put("ip", "192.168.60.80");

            // 添加请求参数——fields
            apiparamsMap
                    .put("fields",
                            "storage_id,tid,transaction_id,customer_id,distributor_id,shop_name,out_tid,out_pay_tid,voucher_id,shopid,serial_num,order_channel,order_from,buyer_id,buyer_name,type,status,abnormal_status,merge_status,receiver_name,receiver_mobile,phone,province,city,district,address,post,email,is_bill,invoice_name,invoice_situation,invoice_title,invoice_type,invoice_content,pro_totalfee,order_totalfee,reference_price_paid,invoice_fee,cod_fee,other_fee,refund_totalfee,discount_fee,discount,channel_disfee,merchant_disfee,order_disfee,commission_fee,is_cod,point_pay,cost_point,point,superior_point,royalty_fee,external_point,express_no,express,express_coding,online_express,sending_type,real_income_freight,real_pay_freight,gross_weight,gross_weight_freight,net_weight_freight,freight_explain,total_weight,tid_net_weight,tid_time,pay_time,get_time,order_creater,business_man,payment_received_operator,payment_received_time,review_orders_operator,review_orders_time,finance_review_operator,finance_review_time,advance_printer,printer,print_time,is_print,adv_distributer,adv_distribut_time,distributer,distribut_time,is_inspection,inspecter,inspect_time,cancel_operator,cancel_time,revoke_cancel_er,revoke_cancel_time,packager,pack_time,weigh_operator,weigh_time,book_delivery_time,delivery_operator,delivery_time,locker,lock_time,book_file_time,file_operator,file_time,finish_time,modity_time,is_promotion,promotion_plan,out_promotion_detail,good_receive_time,receive_time,verificaty_time,enable_inte_sto_time,enable_inte_delivery_time,alipay_id,alipay_status,pay_mothed,pay_status,platform_status,rate,currency,delivery_status,buyer_message,service_remarks,inner_lable,distributor_mark,system_remarks,other_remarks,message,message_time,is_stock,related_orders,related_orders_type,import_mark,delivery_name,is_new_customer,distributor_level,cod_service_fee,express_col_fee,product_num,sku,item_num,single_num,flag_color,is_flag,taobao_delivery_order_status,taobao_delivery_status,taobao_delivery_method,order_process_time,is_break,breaker,break_time,break_explain,plat_send_status,plat_type,is_adv_sale,provinc_code,city_code,area_code,express_code,last_returned_time,last_refund_time,deliver_centre,deliver_station,is_pre_delivery_notice,jd_delivery_time,Sorting_code,cod_settlement_vouchernumber,originCode,destCode,big_marker,total_num,child_storage_id,child_tid,child_pro_detail_code,child_pro_name,child_specification,child_barcode,child_combine_barcode,child_iscancel,child_isscheduled,child_stock_situation,child_isbook_pro,child_iscombination,child_isgifts,child_gift_num,child_book_storage,child_pro_num,child_send_num,child_refund_num,child_refund_renum,child_inspection_num,child_timeinventory,child_cost_price,child_sell_price,child_average_price,child_original_price,child_sys_price,child_ferght,child_item_discountfee,child_inspection_time,child_weight,child_shopid,child_out_tid,child_out_proid,child_out_prosku,child_proexplain,child_buyer_memo,child_seller_remark,child_distributer,child_distribut_time,child_second_barcode,child_product_no,child_brand_number,child_brand_name,child_book_inventory,child_product_specification,child_discount_amount,child_credit_amount,child_MD5_encryption");
            // 开始时间
            apiparamsMap.put("begin_time", "2000-1-1");
            // 结束时间
            apiparamsMap.put("end_time", new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
            // 订单外部id
            apiparamsMap.put("out_tid", netorder.getOrderCode());
            String timestamp = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
            // 添加请求参数——时间戳
            apiparamsMap.put("timestamp", timestamp);
            // 分页
            apiparamsMap.put("page_no", "1");
            // 页数量
            apiparamsMap.put("page_size", "10");

            // 获取数字签名
            String sign = Util.md5Signature(apiparamsMap, eStore.getAppkey());

            apiparamsMap.put("sign", sign);

            StringBuilder param = new StringBuilder();

            for (Iterator<Map.Entry<String, String>> it = apiparamsMap.entrySet().iterator(); it.hasNext();) {
                Map.Entry<String, String> e = it.next();
                if (!APPSCRET.equals(e.getKey()) && !TOKEN.equals(e.getKey())) {
                    if ("shop_id".equals(e.getKey()) || "wangwang_id".equals(e.getKey()) || "date_type".equals(e.getKey())) {
                        try {
                            param.append("&").append(e.getKey()).append("=").append(HttpMethodUtil.encodeUri(e.getValue()));
                        } catch (UnsupportedEncodingException e1) {
                            LOGGER.error("获取产品信息失败！", e1);
                        }
                    } else {
                        param.append("&").append(e.getKey()).append("=").append(e.getValue());
                    }
                }
            }

            String postData;
            postData = param.toString().substring(1);
            String result;
            result = HttpMethodUtil.getResult(eStore.getServer(), postData);
            // xml解析
            try {
                Document document = DocumentHelper.parseText(result);
                Element root = document.getRootElement();
                @SuppressWarnings("unchecked")
                List<Element> elements = root.elements();
                for (Iterator<Element> it = elements.iterator(); it.hasNext();) {
                    Element element = it.next();
                    if (!"Rows".equals(element.getName())) {
                        continue;
                    }
                    @SuppressWarnings("unchecked")
                    List<Element> es = element.elements();
                    for (Iterator<Element> its = es.iterator(); its.hasNext();) {
                        Element e = its.next();
                        // 判断发货状态
                        if ("delivery_status".equals(e.getName())) {
                            if ("已发货".equals(e.getText())) {
                                // 将发货状态改为已发货
                                orderService.modifyOrderByKey(netorder.getOrderId(), "2");
                                orderService.updateSetCargoStatusByOrderId(netorder.getOrderId(), "3");
                            } else if ("未发货".equals(e.getText())) {
                                return 0;
                            }
                        }
                        // 获取运单号
                        if ("express_no".equals(e.getName())) {
                            orderService.addExpress(e.getText(), netorder.getOrderId());
                        }
                    }

                }

            } catch (DocumentException e) {
                LOGGER.error("",e);
            }

        }

        return 1;
    }

    /**
     * 订单日志
     * 
     * @see com.ningpai.order.service.SynOrderService#selectOrderELogList(com.ningpai.util.PageBean)
     */
    public PageBean selectOrderELogList(PageBean pb) {
        pb.setRows(eLogMapper.selectOrderELogCount());
        // 设置分页参数
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", pb.getStartRowNum());
        map.put("end", pb.getEndRowNum());
        pb.setList(eLogMapper.selectOrderELogList(map));
        return pb;
    }

    /**
     * 重新导入e店宝订单
     *
     */
    @Transactional
    public int upLog(Long orderId, Long eId) {
        // E店宝同步订单Service接口
        SynOrder(orderId);
        // 订单e店宝日志
        OrderELog eLog = new OrderELog();
        eLog.setOrderELogId(eId);
        eLog.setFlag("1");
        // 更新日志
        return eLogMapper.upLog(eLog);
    }

}
