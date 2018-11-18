package com.ningpai.cloudshop.invoker;

import com.ningpai.cloudshop.bean.CloudshopAuthorInfo;
import com.ningpai.cloudshop.common.Commons;
import com.ningpai.cloudshop.util.ItemPageBean;
import com.qianmi.open.api.ApiException;
import com.qianmi.open.api.DefaultOpenClient;
import com.qianmi.open.api.OpenClient;
import com.qianmi.open.api.domain.cloudshop.Item;
import com.qianmi.open.api.request.ItemGetRequest;
import com.qianmi.open.api.request.ItemSkusGetRequest;
import com.qianmi.open.api.request.ItemsInventoryGetRequest;
import com.qianmi.open.api.request.ItemsOnsaleGetRequest;
import com.qianmi.open.api.response.*;
import com.qianmi.open.api.tool.util.OAuthUtils;
import com.qianmi.open.api.tool.util.QianmiContext;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 调用平台sdk获取并封装数据 Created by liangck on 15/6/26.
 */
@Component("OpenSdkInvoker")
public class OpenSdkInvoker {

    /** 日志打印 **/
    private static Logger logger = Logger.getLogger(OpenSdkInvoker.class);

    /**
     * 获取用户库存中的分销商品（已下架商品）
     * 
     * @param pb
     *            分页工具类
     * @return 填充好分页数据的分页工具类
     */
    public ItemPageBean getInventoryItems(ItemPageBean pb, String accessToken) throws ApiException {
        OpenClient client = getDefaultClient();
        ItemsInventoryGetRequest req = new ItemsInventoryGetRequest();
        /* 设置返回字段 */
        req.setFields("cid,seller_cids,pic_url,num,list_time,delist_time,price,modified,item_imgs,approve_status,is_virtual,num_iid,title,nick,desc,skus,props_name,created,is_fenxiao,unit,sub_stock,item_weight,item_size,outer_id,brand_id,brand_name,type_id,type_name");
        /* 设置页码 */
        req.setPageNo(pb.getPageNo() - 1);// 页码从0开始
        /* 设置每页条数 */
        req.setPageSize(pb.getPageSize());
        /* 执行调用，获取结果 */
        ItemsInventoryGetResponse response = client.execute(req, accessToken);
        /* 打印调用结果 */
        logger.info("调用结果 ：" + response.getErrorCode() + " msg:" + response.getMsg());
        if (response.getErrorCode() != null) {
            throw new ApiException(response.getMsg());
        }
        /* 设置列表数据 */
        pb.setItems(response.getItems());
        pb.setRows(response.getTotalResults());
        /* 返回分页数据 */
        return pb;
    }

    /**
     * 获取用户出售中（已上架的商品信息）
     * 
     * @param pb
     *            分页工具类
     * @return 填充好分页数据的分页工具类
     */
    public ItemPageBean getOnsaleItems(ItemPageBean pb, String accessToken) throws ApiException {
        OpenClient client = getDefaultClient();
        ItemsOnsaleGetRequest req = new ItemsOnsaleGetRequest();
        /* 设置返回字段 */
        req.setFields("cid,seller_cids,pic_url,num,list_time,delist_time,price,modified,item_imgs,approve_status,is_virtual,num_iid,title,nick,desc,props_name,created,is_fenxiao,unit,sub_stock,item_weight,item_size,outer_id,brand_id,brand_name,type_id,type_name");
        /* 设置页码 */
        req.setPageNo(pb.getPageNo() - 1);// 页码从0开始
        /* 每页条数 */
        req.setPageSize(pb.getPageSize());
        /* 执行调用获取结果 */
        ItemsOnsaleGetResponse response = client.execute(req, accessToken);
        /* 打印调用结果 */
        logger.info("调用结果 ：" + response.getErrorCode() + " msg:" + response.getMsg());
        if (response.getErrorCode() != null) {
            throw new ApiException(response.getMsg());
        }
        /* 填充分页数据 */
        pb.setItems(response.getItems());
        pb.setRows(response.getTotalResults());
        /* 返回分页数据 */
        return pb;
    }

    /**
     * 调用平台接口查询商品详细信息
     * 
     * @param numIid
     *            商品编号ID
     * @param accessToken
     *            用户授权token值
     * @return
     * @throws ApiException
     *             平台API异常
     */
    public Item getItemDetail(String numIid, String accessToken) throws ApiException {
        OpenClient client = getDefaultClient();
        ItemGetRequest req = new ItemGetRequest();
        /*
         * 设置返回字段，Item商品结构中的所有字段均可返回，多个字段用”,”分隔，如获取sku全部字段，只需要传skus,如只需要sku部分字段，
         * 请按照以下格式：sku.sku_id,sku.properties
         */
        req.setFields("cid,seller_cids,pic_url,num,list_time,delist_time,price,cost_price,mkt_price,modified,item_imgs,approve_status,is_virtual,num_iid,title,nick,desc,props_name,created,is_fenxiao,unit,sub_stock,item_weight,item_size,outer_id,brand_id,brand_name,type_id,type_name,skus");
        /* 设置商品编号ID */
        req.setNumIid(numIid);
        /* 执行调用并获取结果 */
        ItemGetResponse response = client.execute(req, accessToken);
        /* 当未返回响应商品详细信息的时候 */
        if (response.getItem() == null) {
            /* 错误码为01030001，即为商品不存在，直接返回空值 */
            if (response.getErrorCode() != null && "01030001".equals(response.getErrorCode())) {
                return null;
            } else if (response.getMsg() != null) {/* 抛出异常信息 */
                throw new ApiException(response.getMsg() + ", " + response.getSubMsg());
            }
        }
        /* 返回商品详细信息 */
        return response.getItem();
    }

    /**
     * 获取默认的openclient
     * 
     * @return OpenClient
     */
    private OpenClient getDefaultClient() {
        return new DefaultOpenClient(Commons.OPEN_API_URL, Commons.APPKEY, Commons.APPSECRET);
    }

    /**
     * 刷新token值
     * 
     * @param refreshToken
     * @return
     * @throws ApiException
     */
    public CloudshopAuthorInfo refresgToken(String refreshToken) throws ApiException {
        QianmiContext context = OAuthUtils.refreshToken(Commons.APPKEY, Commons.APPSECRET, refreshToken);
        TokenResponse response = context.getTokenResponse();
        if ((response.getErrorCode() == null || "0".equals(response.getErrorCode())) || response.getMsg() == null) {
            CloudshopAuthorInfo authorInfo = new CloudshopAuthorInfo.Builder(response.getUserId()).setAccessToken(response.getAccessToken())
                    // lastRefreshTime设置为当前请求时间
                    .setExpiresIn(response.getExpiresIn()).setLastRefreshTime(new Date()).setReExpiresIn(response.getReExpiresIn()).setRefreshToken(response.getRefreshToken())
                    .build();
            /* 设置access_token过期日期 */
            authorInfo.setAccessTokenExpiresDate(new Date(authorInfo.getLastRefreshTime().getTime() + authorInfo.getExpiresIn() * 1000));
            /* 设置refresh_token过期日期 */
            authorInfo.setRefreshTokenExpiresDate(new Date(authorInfo.getLastRefreshTime().getTime() + authorInfo.getReExpiresIn() * 1000));

            return authorInfo;
        }
        return null;
    }

    /**
     * main方法
     * */
    public static void main(String[] args) {
        OpenClient client = new DefaultOpenClient(Commons.OPEN_API_URL, Commons.APPKEY, Commons.APPSECRET);
        /*
         * ItemGetRequest req = new ItemGetRequest(); req.setFields(
         * "cid,seller_cids,pic_url,num,list_time,delist_time,price,modified,item_imgs,approve_status,is_virtual,num_iid,title,nick,desc,props_name,created,is_fenxiao,unit,sub_stock,item_weight,item_size,outer_id,brand_id,brand_name,type_id,type_name,skus"
         * ); req.setNumIid("p72093"); try { ItemGetResponse response =
         * client.execute(req, "e41bc82774424afa96e9296cb39eeb8a");
         * System.out.println(response.getItem().getSkus()); } catch
         * (ApiException e) { e.printStackTrace(); }
         */

        ItemSkusGetRequest req = new ItemSkusGetRequest();
        req.setFields("sku_id,num_iid,quantity,price,cost_price,created,modified,status,properties_name,properties,outer_id,barcode");
        req.setNumIids("183072");
        try {
            ItemSkusGetResponse response = client.execute(req, "e41bc82774424afa96e9296cb39eeb8a");
            System.out.println(response.getSkus());
        } catch (ApiException e) {
            logger.error("",e);
        }

    }

}
