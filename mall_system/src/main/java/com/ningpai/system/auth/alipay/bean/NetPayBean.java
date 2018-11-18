/*
 * 工 程 名: wg
                                                                                                                                                        
 * http://www.fsti.com                                               
 * CopyRright (c) 2009-xxxx: 
 */
package com.ningpai.system.auth.alipay.bean;

import java.io.UnsupportedEncodingException;

import com.ningpai.common.util.ConstantUtil;
import com.ningpai.system.auth.alipay.util.DeployInfoUtil;

/**
 * 支付实体类
 * 
 * @author jiping
 * @since 2015年8月21日 下午7:44:13
 * @version 0.0.1
 */
public class NetPayBean implements java.io.Serializable {

    /** 标识 */
    private static final long serialVersionUID = -4536014964593363611L;

    /*
     * 商户号，长度为15个字节的数字串，由ChinaPay分配。
     */
    private String merId;
    /*
     * MerPrK.key的路径
     */
    private String merPrk;
    /*
     * PgPubk.key的路径
     */
    private String pgPubk;
    /*
     * 订单号，长度为16个字节的数字串，由商户系统生成，失败的订单号允许重复支付。
     */
    private String ordId;
    /*
     * 交易金额，长度为12个字节的数字串，例如：数字串"000000001234"表示12.34元。
     */
    private String transAmt;
    /*
     * 货币代码, 长度为3个字节的数字串，目前只支持人民币，取值为"156" 。
     */
    private String curyId;
    /*
     * 交易日期，长度为8个字节的数字串，表示格式为：YYYYMMDD。
     */
    private String transDate;
    /*
     * 交易类型，长度为4个字节的数字串，取值范围为："0001"和"0002"， 其中"0001"表示消费交易，"0002"表示退货交易。
     */
    private String transType;
    /*
     * 网银支付版本
     */
    private String version;
    /*
     * 交易状态，长度为4个字节的数字串。详见交易状态码说明。
     */
    private String orderStatus;
    /*
     * 即NetPayClient根据上述输入参数生成的商户数字签名，长度为256字节的字符串。
     */
    private String checkValue;
    /*
     * 后台交易接收URL，长度不要超过80个字节
     */
    private String bgRetUrl;
    /*
     * 页面交易接收URL，长度不要超过80个字节
     */
    private String pageRetUrl;
    /** 付款 */
    private int payment;
    private double transAmt2;

    /**
     * 无参构造
     */
    public NetPayBean() {
        this.bgRetUrl = new DeployInfoUtil().getBackMerchantUrl();
        this.pageRetUrl = new DeployInfoUtil().getMerchantUrl();
        this.version = new DeployInfoUtil().getNetPayVersion();
    }

    /**
     * 用于支付
     * 
     * @param orderId
     * @param transAmt
     * @param transDate
     * @param transType
     * @throws java.io.UnsupportedEncodingException
     */
    public NetPayBean(String orderId, String transAmt, String transDate, String transType) throws UnsupportedEncodingException {
        this();
        this.merId = new DeployInfoUtil().getNetPayMerId();
        this.merPrk = java.net.URLDecoder.decode(this.getClass().getClassLoader().getResource("MerPrK.key").getPath(), ConstantUtil.UTF);
        this.ordId = orderId;
        this.transAmt = transAmt;
        this.curyId = "156";
        this.transDate = transDate;
        this.transType = transType;
    }

    /**
     * 用于回调
     * 
     * @param merid
     * @param orderId
     * @param transAmt
     * @param curyId
     * @param transDate
     * @param transType
     * @param status
     * @param checkvalue
     * @throws java.io.UnsupportedEncodingException
     */
    public NetPayBean(String merid, String orderId, String transAmt, String curyId, String transDate, String transType, String status, String checkvalue)
            throws UnsupportedEncodingException {
        this();
        this.merId = merid;
        this.pgPubk = java.net.URLDecoder.decode(this.getClass().getClassLoader().getResource("PgPubk.key").getPath(), ConstantUtil.UTF);
        this.ordId = orderId;
        this.transAmt = transAmt;
        this.curyId = curyId;
        this.transDate = transDate;
        this.transType = transType;
        this.orderStatus = status;
        this.checkValue = checkvalue;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("merId -> " + merId).append(" ,merPrk -> " + merPrk).append(" ,pgPubk -> " + pgPubk).append(" ,ordId -> " + ordId).append(" ,transAmt -> " + transAmt)
                .append(" ,curyId -> " + curyId).append(" ,transDate -> " + transDate).append(" ,transType -> " + transType).append(" ,version -> " + version)
                .append(" ,orderStatus -> " + orderStatus).append(" ,checkValue -> " + checkValue);
        return sb.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bgRetUrl == null) ? 0 : bgRetUrl.hashCode());
        result = prime * result + ((checkValue == null) ? 0 : checkValue.hashCode());
        result = prime * result + ((curyId == null) ? 0 : curyId.hashCode());
        result = prime * result + ((merId == null) ? 0 : merId.hashCode());
        result = prime * result + ((merPrk == null) ? 0 : merPrk.hashCode());
        result = prime * result + ((ordId == null) ? 0 : ordId.hashCode());
        result = prime * result + ((orderStatus == null) ? 0 : orderStatus.hashCode());
        result = prime * result + ((pageRetUrl == null) ? 0 : pageRetUrl.hashCode());
        result = prime * result + ((pgPubk == null) ? 0 : pgPubk.hashCode());
        result = prime * result + ((transAmt == null) ? 0 : transAmt.hashCode());
        result = prime * result + ((transDate == null) ? 0 : transDate.hashCode());
        result = prime * result + ((transType == null) ? 0 : transType.hashCode());
        result = prime * result + ((version == null) ? 0 : version.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NetPayBean other = (NetPayBean) obj;
        if (bgRetUrl == null) {
            if (other.bgRetUrl != null) {
                return false;
            }
        } else if (!bgRetUrl.equals(other.bgRetUrl)) {
            return false;
        }
        if (checkValue == null) {
            if (other.checkValue != null) {
                return false;
            }
        } else if (!checkValue.equals(other.checkValue)) {
            return false;
        }
        if (curyId == null) {
            if (other.curyId != null) {
                return false;
            }
        } else if (!curyId.equals(other.curyId)) {
            return false;
        }
        if (merId == null) {
            if (other.merId != null) {
                return false;
            }
        } else if (!merId.equals(other.merId)) {
            return false;
        }
        if (merPrk == null) {
            if (other.merPrk != null) {
                return false;
            }
        } else if (!merPrk.equals(other.merPrk)) {
            return false;
        }
        if (ordId == null) {
            if (other.ordId != null) {
                return false;
            }
        } else if (!ordId.equals(other.ordId)) {
            return false;
        }
        if (orderStatus == null) {
            if (other.orderStatus != null) {
                return false;
            }
        } else if (!orderStatus.equals(other.orderStatus)) {
            return false;
        }
        if (pageRetUrl == null) {
            if (other.pageRetUrl != null) {
                return false;
            }
        } else if (!pageRetUrl.equals(other.pageRetUrl)) {
            return false;
        }
        if (pgPubk == null) {
            if (other.pgPubk != null) {
                return false;
            }
        } else if (!pgPubk.equals(other.pgPubk)) {
            return false;
        }
        if (transAmt == null) {
            if (other.transAmt != null) {
                return false;
            }
        } else if (!transAmt.equals(other.transAmt)) {
            return false;
        }
        if (transDate == null) {
            if (other.transDate != null) {
                return false;
            }
        } else if (!transDate.equals(other.transDate)) {
            return false;
        }
        if (transType == null) {
            if (other.transType != null) {
                return false;
            }
        } else if (!transType.equals(other.transType)) {
            return false;
        }
        if (version == null) {
            if (other.version != null) {
                return false;
            }
        } else if (!version.equals(other.version)) {
            return false;
        }
        return true;
    }

    public String getMerId() {
        return merId;
    }

    public void setMerId(String merId) {
        this.merId = merId;
    }

    public String getMerPrk() {
        return merPrk;
    }

    public void setMerPrk(String merPrk) {
        this.merPrk = merPrk;
    }

    public void setPgPubk(String pgPubk) {
        this.pgPubk = pgPubk;
    }

    public String getPgPubk() {
        return pgPubk;
    }

    public String getOrdId() {
        return ordId;
    }

    public void setOrdId(String ordId) {
        this.ordId = ordId;
    }

    public String getTransAmt() {
        return transAmt;
    }

    public void setTransAmt(String transAmt) {
        this.transAmt = transAmt;
    }

    public String getCuryId() {
        return curyId;
    }

    public void setCuryId(String curyId) {
        this.curyId = curyId;
    }

    public String getTransDate() {
        return transDate;
    }

    public void setTransDate(String transDate) {
        this.transDate = transDate;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setCheckValue(String checkValue) {
        this.checkValue = checkValue;
    }

    public String getCheckValue() {
        return checkValue;
    }

    public String getBgRetUrl() {
        return bgRetUrl;
    }

    public void setBgRetUrl(String bgRetUrl) {
        this.bgRetUrl = bgRetUrl;
    }

    public String getPageRetUrl() {
        return pageRetUrl;
    }

    public void setPageRetUrl(String pageRetUrl) {
        this.pageRetUrl = pageRetUrl;
    }

    public double getTransAmt2() {
        return transAmt2;
    }

    public void setTransAmt2(double transAmt2) {
        this.transAmt2 = transAmt2;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }
}
