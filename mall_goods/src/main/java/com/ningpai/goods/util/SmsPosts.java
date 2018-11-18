package com.ningpai.goods.util;

import com.ningpai.goods.bean.GoodsProduct;
import com.ningpai.goods.bean.Sms;
import com.ningpai.system.service.BasicSetService;
import com.ningpai.util.MSMSendUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * 短信验证码辅助类
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年6月12日 下午3:44:51
 * @version 0.0.1
 */
@Service
public class SmsPosts {

    /** 站点信息业务接口 */
    private static BasicSetService basicSetService;

    /**
     * 构造函数
     * */
    private SmsPosts() {

    }

    /**
     * 短信发送
     * 
     * @param sms
     *            接口帮助类
     * @return
     * @throws IOException
     */
    public static boolean sendPost(Sms sms, GoodsProduct goodsProduct, String mobile) throws IOException {
        // 调用zyer.cn的短信接口
        String param= "";
        if (null != goodsProduct.getOfollowPrice()) {
            param += "&MsgContext=" + "您所关注的" + goodsProduct.getDisName() + "地区的，商品为：" + goodsProduct.getGoodsInfoName()
                    + "的商品，由" + goodsProduct.getOfollowPrice() + "元,降到了" + goodsProduct.getNfollowPrice() + "元,快来选购吧" ;
        } else {
            param += "&MsgContext=" + "您好," + basicSetService.findBasicSet().getBsetName() + "提醒您,我们的" + goodsProduct.getGoodsInfoName() + "商品降价了" ;
        }
        sms.setMsgContext(param);
        return MSMSendUtil.sendMsm("", sms.getLoginName(), sms.getPassword(), new String[]{mobile}, sms.getMsgContext(),sms.getSmsKind(),sms.getHttpUrl());
    }


    /**
     * 获取
     * */
    public BasicSetService getBasicSetService() {
        return basicSetService;
    }
    /**
     * Spring注入
     * */
    @Resource(name = "basicSetService")
    public void setBasicSetService(BasicSetService basicSetService) {
        SmsPosts.basicSetService = basicSetService;
    }

}
