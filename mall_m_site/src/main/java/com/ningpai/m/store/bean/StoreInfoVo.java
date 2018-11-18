/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.m.store.bean;

import com.ningpai.coupon.bean.Coupon;
import com.ningpai.goods.bean.GoodsProduct;
import com.ningpai.marketing.bean.Marketing;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 店铺信息 Bean
 *
 * @author NINGPAI-zhangqiang
 * @since 2014年5月5日 下午5:27:01
 * @version 0.0.1
 */
public class StoreInfoVo {

    /**
     * 店铺编号
     */
    private Long storeId;
    /**
     * 客户编号
     */
    private Long customerid;
    /**
     * 店铺名称
     */
    private String storeName;
    /**
     * 店铺开通状态 1：已开通 0：未开通
     */
    private String storeStatus;
    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 公司创建时间
     */
    private String companyCreTime;

    /**
     * 公司地域 省;市;区
     */
    private String companyAddr;
    /**
     * 公司地区id
     * */
    private String companyAddrId;

    /**
     * 公司详细地址
     */
    private String companyAddrDel;

    /**
     * 公司电话
     */
    private String companyTel;

    /**
     * 员工总数
     */
    private Long companyEmpNum;

    /**
     * 注册资金
     */
    private BigDecimal companyCapital;

    /**
     * 电子邮箱
     */
    private String companyEmail;

    /**
     * 企业类型 1:生产厂商 2:品牌商 3:代理商 4:经销商
     */
    private String companyType;

    /**
     * 联系人姓名
     */
    private String companyContactName;

    /**
     * 联系人电话
     */
    private String companyContactTel;

    /**
     * 调研资料电子表存储地址
     */
    private String companyResearchUrl;

    /**
     * SKU数量合计
     */
    private BigDecimal companySku;

    /**
     * 平均客单价1: 0-100 2: 100-200 3: 200-300 4: 300-400 5: 400-500 6: 500-1000
     * 7:1000以上
     */
    private String companyAvg;

    /**
     * 退货地址
     */
    private String returnAddr;

    /**
     * 退货邮编
     */
    private String returnMail;

    /**
     * 退货联系人
     */
    private String returnContactName;

    /**
     * 退货联系电话
     */
    private String returnContactTel;

    /**
     * 营业执照号
     */
    private String bussId;

    /**
     * 营业执照所在地 省;市;区
     */
    private String bussAddr;
    /**
     * 营业执照所在地id
     * */
    private String bussAddrId;

    /**
     * 营业执照有效期
     */
    private String bussDate;

    /**
     * 营业执照电子版存储地址
     */
    private String bussUrl;

    /**
     * 经营范围 1:生产厂商 2:品牌商 3:代理商 4:经销商
     */
    private String bussRange;

    /**
     * 组织机构代码
     */
    private String bussDeptNo;

    /**
     * 税务登记证号
     */
    private String bussTaxRegistId;

    /**
     * 税务登记证Url
     */
    private String bussTaxRegistUrl;

    /**
     * 纳税人识别号
     */
    private String bussTaxPayerId;

    /**
     * 纳税人资格证URL
     */
    private String bussTaxCredUrl;

    /**
     * 税务类型
     */
    private String bussTaxType;

    /**
     * 税务类型税码
     */
    private String bussTaxTypeId;

    /**
     * 法人代表
     */
    private String bussLegalName;

    /**
     * 银行开户名
     */
    private String bankUsername;

    /**
     * 公司银行账号
     */
    private String bankCardId;

    /**
     * 开户银行所在地 省;市;区
     */
    private String bankAddr;
    /**
     * 开户银行所在地id
     */
    private String bankAddrId;

    /**
     * 开户行支行名称
     */
    private String bankName;

    /**
     * 开户支行联行号
     */
    private String bankId;

    /**
     * 审核状态 0:审核中 1:已审核 2:未通过
     */
    private String checkStatus;

    /**
     * 合同电子版存储地址
     */
    private String contractUrl;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date modTime;

    /**
     * 店铺到期时间
     */
    private Date expiryTime;

    /**
     * 删除标志 1：删除 0：未删除
     */
    private String delFlag;
    /**
     * 银行开户许可证URL
     */
    private String bankUrl;
    /**
     * 法人身份证URL
     */
    private String cardUrl;
    /**
     * 法人身份证号码
     */
    private String bussLegalCardId;
    /**
     * 资料提交
     */
    private String isSubmit;
    /**
     * 打回原因
     */
    private String refuseContent;

    /**
     * 是否显示商家首页 0否 1是
     */
    private String isStoreIndex;
    /**
     * 结算周期
     */
    private String billingCycle;

    /**
     * 店铺承诺
     */
    private String storePromise;

    /**
     * 旗舰店
     */
    private String storeQi;
    /**
     *collectionSellerId
     */
    private Long collectionSellerId;

    /**
     *总金额
     * */
    private int sumPoint;

    /**
     * 商家积分
     */
    private int storePoint;

    /**
     * 商家账户余额
     */
    private BigDecimal storeBalance;

    /**
     * 客服QQ
     */
    private String serviceQq;

    /**
     * 是否为供应商 1:是供应商 0:不是供应商
     */
    private String isSupplier;

    /**
     * 店铺被关注的状态
     */
    private int isCollection;

    /**
     * 优惠券
     */
    List<Coupon> couponlist = new ArrayList<>();

    /**
     * 促销
     */
    List<Marketing> marketinglist = new ArrayList<>();

    /**
     * 该店铺下面所有的货品信息
     */
    List<GoodsProduct> storeProductList = new ArrayList<>();

    /**
     * 该店铺下面广告信息
     */
    List<StoreStreetThirdImage> store_images = new ArrayList<>();

    /**
     * 该店铺最近指定时间内上架的商品数量
     */
    private Long storeNewProcudtCount;

    /**
     * 获取图片
     *
     * @return
     */
    public List<StoreStreetThirdImage> getStore_images() {
        return store_images;
    }

    /**
     * 设置图片
     *
     * @param storeImages
     */
    public void setStore_images(List<StoreStreetThirdImage> storeImages) {
        this.store_images = storeImages;
    }

    /**
     * 获取新的货品数量
     *
     * @return
     */
    public Long getStoreNewProcudtCount() {
        return storeNewProcudtCount;
    }

    /**
     * 设置货品数量
     *
     * @param storeNewProcudtCount
     */
    public void setStoreNewProcudtCount(Long storeNewProcudtCount) {
        this.storeNewProcudtCount = storeNewProcudtCount;
    }
    /**
     * 获取货品集合
     * */
    public List<GoodsProduct> getStoreProductList() {
        return storeProductList;
    }
    /**
     * 设置货品集合
     * */
    public void setStoreProductList(List<GoodsProduct> storeProductList) {
        this.storeProductList = storeProductList;
    }
    /**
     * 获取促销集合
     * */
    public List<Marketing> getMarketinglist() {
        return marketinglist;
    }
    /**
     * 设置促销集合
     * */
    public void setMarketinglist(List<Marketing> marketinglist) {
        this.marketinglist = marketinglist;
    }
    /**
     * 获取优惠集合
     * */
    public List<Coupon> getCouponlist() {
        return couponlist;
    }
    /**
     * 设置优惠集合
     * */
    public void setCouponlist(List<Coupon> couponlist) {
        this.couponlist = couponlist;
    }
    /**
     * 获取IsCollection
     * */
    public int getIsCollection() {
        return isCollection;
    }
    /**
     * 设置IsCollection
     * */
    public void setIsCollection(int isCollection) {
        this.isCollection = isCollection;
    }
    /**
     * 获取IsSupplier
     * */
    public String getIsSupplier() {
        return isSupplier;
    }
    /**
     * 设置IsSupplier
     * */
    public void setIsSupplier(String isSupplier) {
        this.isSupplier = isSupplier;
    }
    /**
     * 获取qq
     * */
    public String getServiceQq() {
        return serviceQq;
    }
    /**
     * 设置qq
     * */
    public void setServiceQq(String serviceQq) {
        this.serviceQq = serviceQq;
    }
    /**
     * 获取StorePoint
     * */
    public int getStorePoint() {
        return storePoint;
    }
    /**
     * 设置StorePoint
     * */
    public void setStorePoint(int storePoint) {
        this.storePoint = storePoint;
    }
    /**
     * 获取店铺余额
     * */
    public BigDecimal getStoreBalance() {
        return storeBalance;
    }
    /**
     * 设置店铺余额
     * */
    public void setStoreBalance(BigDecimal storeBalance) {
        this.storeBalance = storeBalance;
    }
    /**
     * 获取总金额
     * */
    public int getSumPoint() {
        return sumPoint;
    }
    /**
     * 设置总金额
     * */
    public void setSumPoint(int sumPoint) {
        this.sumPoint = sumPoint;
    }
    /**获取BillingCycle
     *
     * */
    public String getBillingCycle() {
        return billingCycle;
    }
    /**
     * 设置BillingCycle
     * */
    public void setBillingCycle(String billingCycle) {
        this.billingCycle = billingCycle;
    }
    /**
     * 获取IsStoreIndex
     * */
    public String getIsStoreIndex() {
        return isStoreIndex;
    }
    /**
     * 设置IsStoreIndex
     * */
    public void setIsStoreIndex(String isStoreIndex) {
        this.isStoreIndex = isStoreIndex;
    }
    /**
     * 获取RefuseContent
     * */
    public String getRefuseContent() {
        return refuseContent;
    }
    /**
     * 设置RefuseCOnotent
     * */
    public void setRefuseContent(String refuseContent) {
        this.refuseContent = refuseContent;
    }
    /**
     * 获取IsSubmit
     * */
    public String getIsSubmit() {
        return isSubmit;
    }
    /**
     * 设置IsSubmit
     * */
    public void setIsSubmit(String isSubmit) {
        this.isSubmit = isSubmit;
    }
    /**
     * 获取BussLegalCardId
     * */
    public String getBussLegalCardId() {
        return bussLegalCardId;
    }
    /**
     * 设置BussLegalCardId
     * */
    public void setBussLegalCardId(String bussLegalCardId) {
        this.bussLegalCardId = bussLegalCardId;
    }
    /**
     * 获取CardUrl
     * */
    public String getCardUrl() {
        return cardUrl;
    }
    /**
     * 设置CardUrl
     * */
    public void setCardUrl(String cardUrl) {
        this.cardUrl = cardUrl;
    }
    /**
     * 获取BankUrl
     * */
    public String getBankUrl() {
        return bankUrl;
    }
    /**
     * 设置BankUrl
     * */
    public void setBankUrl(String bankUrl) {
        this.bankUrl = bankUrl;
    }
    /**
     * 获取CompanAddrId
     * */
    public String getCompanyAddrId() {
        return companyAddrId;
    }
    /**
     * 设置CompanyAddrId
     * */
    public void setCompanyAddrId(String companyAddrId) {
        this.companyAddrId = companyAddrId;
    }
    /**
     * 获取BussAddrId
     * */
    public String getBussAddrId() {
        return bussAddrId;
    }
    /**
     * 设置BUssAddrId
     * */
    public void setBussAddrId(String bussAddrId) {
        this.bussAddrId = bussAddrId;
    }
    /**
     * 获取BandAddrId
     * */
    public String getBankAddrId() {
        return bankAddrId;
    }
    /**
     * 设置BankAddrId
     * */
    public void setBankAddrId(String bankAddrId) {
        this.bankAddrId = bankAddrId;
    }
    /**
     * 获取BussTaxRegistUrl
     * */
    public String getBussTaxRegistUrl() {
        return bussTaxRegistUrl;
    }
    /**
     * 设置BussTaxRegistUrl
     * */
    public void setBussTaxRegistUrl(String bussTaxRegistUrl) {
        this.bussTaxRegistUrl = bussTaxRegistUrl;
    }
    /**
     * 获取BUssTaxCredUrl
     * */
    public String getBussTaxCredUrl() {
        return bussTaxCredUrl;
    }
    /**
     * 设置BussTaxCredUrl
     * */
    public void setBussTaxCredUrl(String bussTaxCredUrl) {
        this.bussTaxCredUrl = bussTaxCredUrl;
    }
    /**
     * 获取CompanyCreTime
     * */
    public String getCompanyCreTime() {
        return companyCreTime;
    }
    /**
     * 设置CompanyCreTime
     * */
    public void setCompanyCreTime(String companyCreTime) {
        this.companyCreTime = companyCreTime;
    }
    /**
     * 获取StoreId
     * */
    public Long getStoreId() {
        return storeId;
    }
    /**
     * 设置StoreId
     * */
    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }
    /**
     * 获取CustomerId
     * */
    public Long getCustomerid() {
        return customerid;
    }
    /**
     * 设置CUstomerId
     * */
    public void setCustomerid(Long customerid) {
        this.customerid = customerid;
    }
    /**
     * 获取StoreName
     * */
    public String getStoreName() {
        return storeName;
    }
    /**
     * 设置StoreName
     * */
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
    /**
     * 获取StoreStatus
     * */
    public String getStoreStatus() {
        return storeStatus;
    }
    /**
     * 设置StoreStatus
     * */
    public void setStoreStatus(String storeStatus) {
        this.storeStatus = storeStatus;
    }
    /**
     * 获取CompanyName
     * */
    public String getCompanyName() {
        return companyName;
    }
    /**
     * 设置ComapnyName
     * */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    /**
     * 获取CompanAddr
     * */
    public String getCompanyAddr() {
        return companyAddr;
    }
    /**
     * 设置CompanyAddr
     * */
    public void setCompanyAddr(String companyAddr) {
        this.companyAddr = companyAddr;
    }
    /**
     * 获取CompanyAddrDel
     * */
    public String getCompanyAddrDel() {
        return companyAddrDel;
    }
    /**
     * 设置CompanyAddrDel
     * */
    public void setCompanyAddrDel(String companyAddrDel) {
        this.companyAddrDel = companyAddrDel;
    }
    /**
     * 获取CompanyTel
     * */
    public String getCompanyTel() {
        return companyTel;
    }
    /**
     * 设置CompanyTel
     * */
    public void setCompanyTel(String companyTel) {
        this.companyTel = companyTel;
    }
    /**
     * 获取CompanyEmpNum
     * */
    public Long getCompanyEmpNum() {
        return companyEmpNum;
    }
    /**
     * 设置CompanyEmpNum
     * */
    public void setCompanyEmpNum(Long companyEmpNum) {
        this.companyEmpNum = companyEmpNum;
    }
    /**
     * 获取CopayCapital
     * */
    public BigDecimal getCompanyCapital() {
        return companyCapital;
    }
    /**
     * 设置CompanyCapital
     * */
    public void setCompanyCapital(BigDecimal companyCapital) {
        this.companyCapital = companyCapital;
    }
    /**
     * 获取COmpanyEmail
     * */
    public String getCompanyEmail() {
        return companyEmail;
    }
    /**
     * 设置CompanyEmail
     * */
    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }
    /**
     * 获取CompanyType
     * */
    public String getCompanyType() {
        return companyType;
    }
    /**
     * 设置CompanyType
     * */
    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }
    /**
     * 获取CompanyCOntactName
     * */
    public String getCompanyContactName() {
        return companyContactName;
    }
    /**
     * 设置CompanyContactName
     * */
    public void setCompanyContactName(String companyContactName) {
        this.companyContactName = companyContactName;
    }
    /**
     * 获取CompanyContactTel
     * */
    public String getCompanyContactTel() {
        return companyContactTel;
    }
    /**
     * 设置CompanyContactTel
     * */
    public void setCompanyContactTel(String companyContactTel) {
        this.companyContactTel = companyContactTel;
    }
    /**
     * 获取CompanyReserchUrl
     * */
    public String getCompanyResearchUrl() {
        return companyResearchUrl;
    }
    /**
     * 设置COmpanyResearchUrl
     * */
    public void setCompanyResearchUrl(String companyResearchUrl) {
        this.companyResearchUrl = companyResearchUrl;
    }
    /**
     * 获取CompanySku
     * */
    public BigDecimal getCompanySku() {
        return companySku;
    }
    /**
     * 设置CompanySku
     * */
    public void setCompanySku(BigDecimal companySku) {
        this.companySku = companySku;
    }
    /**
     * 获取COmpanyAvg
     * */
    public String getCompanyAvg() {
        return companyAvg;
    }
    /**
     * 设置CompanyAvg
     * */
    public void setCompanyAvg(String companyAvg) {
        this.companyAvg = companyAvg;
    }
    /**
     * 获取ReturnAddr
     * */
    public String getReturnAddr() {
        return returnAddr;
    }
    /**
     * 设置ReturnAddr
     * */
    public void setReturnAddr(String returnAddr) {
        this.returnAddr = returnAddr;
    }
    /**
     * 获取RturnMail
     * */
    public String getReturnMail() {
        return returnMail;
    }
    /**
     * 设置ReturnMail
     * */
    public void setReturnMail(String returnMail) {
        this.returnMail = returnMail;
    }
    /**
     * 获取ReturnCOntactName
     * */
    public String getReturnContactName() {
        return returnContactName;
    }
    /**
     * 设置ReturnCOntactName
     * */
    public void setReturnContactName(String returnContactName) {
        this.returnContactName = returnContactName;
    }
    /**
     * 获取ReturnContactTel
     * */
    public String getReturnContactTel() {
        return returnContactTel;
    }
    /**
     * 设置ReturnCOntactTel
     * */
    public void setReturnContactTel(String returnContactTel) {
        this.returnContactTel = returnContactTel;
    }
    /**
     * 获取BUssId
     * */
    public String getBussId() {
        return bussId;
    }
    /**
     * 设置BUssId
     * */
    public void setBussId(String bussId) {
        this.bussId = bussId;
    }
    /**
     * 获取BUssAddr
     * */
    public String getBussAddr() {
        return bussAddr;
    }
    /**
     * 设置BUssAddr
     * */
    public void setBussAddr(String bussAddr) {
        this.bussAddr = bussAddr;
    }
    /**
     * 获取BUsDate
     * */
    public String getBussDate() {
        return bussDate;
    }
    /**
     * 设置BUssDate
     * */
    public void setBussDate(String bussDate) {
        this.bussDate = bussDate;
    }
    /**
     * 获取BussDeptNo
     * */
    public String getBussDeptNo() {
        return bussDeptNo;
    }
    /**
     * 设置BUssDeptNo
     * */
    public void setBussDeptNo(String bussDeptNo) {
        this.bussDeptNo = bussDeptNo;
    }
    /**
     * 获取BUssTaxRegistId
     * */
    public String getBussTaxRegistId() {
        return bussTaxRegistId;
    }
    /**
     * 设置BUsstaxRegisterId
     * */
    public void setBussTaxRegistId(String bussTaxRegistId) {
        this.bussTaxRegistId = bussTaxRegistId;
    }
    /**
     * 获取BUssTaxPayerId
     * */
    public String getBussTaxPayerId() {
        return bussTaxPayerId;
    }
    /**
     * 设置BUsssTaxPayerId
     * */
    public void setBussTaxPayerId(String bussTaxPayerId) {
        this.bussTaxPayerId = bussTaxPayerId;
    }
    /**
     * 获取BussTaxType
     * */
    public String getBussTaxType() {
        return bussTaxType;
    }
    /**
     * 设置BUssTaxType
     * */
    public void setBussTaxType(String bussTaxType) {
        this.bussTaxType = bussTaxType;
    }
    /**
     * 获取BusstaxtypeId
     * */
    public String getBussTaxTypeId() {
        return bussTaxTypeId;
    }
    /**
     * 设置BUssTaxTypeId
     * */
    public void setBussTaxTypeId(String bussTaxTypeId) {
        this.bussTaxTypeId = bussTaxTypeId;
    }
    /**
     * 获取BussLegalName
     * */
    public String getBussLegalName() {
        return bussLegalName;
    }
    /**
     * 设置BUssLegalName
     * */
    public void setBussLegalName(String bussLegalName) {
        this.bussLegalName = bussLegalName;
    }
    /**
     * 获取BUssUrl
     * */
    public String getBussUrl() {
        return bussUrl;
    }
    /**
     * 设置BussUrl
     * */
    public void setBussUrl(String bussUrl) {
        this.bussUrl = bussUrl;
    }
    /**
     * 获取BussRange
     * */
    public String getBussRange() {
        return bussRange;
    }
    /**
     * 设置BUssRange
     * */
    public void setBussRange(String bussRange) {
        this.bussRange = bussRange;
    }
    /**
     * 获取BankUsername
     * */
    public String getBankUsername() {
        return bankUsername;
    }
    /**
     * 设置BankUserName
     * */
    public void setBankUsername(String bankUsername) {
        this.bankUsername = bankUsername;
    }
    /**
     * 获取BankCardId
     * */
    public String getBankCardId() {
        return bankCardId;
    }
    /**
     * 设置BankCardId
     * */
    public void setBankCardId(String bankCardId) {
        this.bankCardId = bankCardId;
    }
    /**
     * 获取BankAddr
     * */
    public String getBankAddr() {
        return bankAddr;
    }
    /**
     * 设置BankAddr
     * */
    public void setBankAddr(String bankAddr) {
        this.bankAddr = bankAddr;
    }
    /**
     * 获取BankName
     * */
    public String getBankName() {
        return bankName;
    }
    /**
     * 设置BankName
     * */
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
    /**
     * 获取BankId
     * */
    public String getBankId() {
        return bankId;
    }
    /**
     * 设置BankId
     * */
    public void setBankId(String bankId) {
        this.bankId = bankId;
    }
    /**
     * 获取CheckStatus
     * */
    public String getCheckStatus() {
        return checkStatus;
    }
    /**
     * 设置CheckStatus
     * */
    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus;
    }
    /**
     * 获取ContractUrl
     * */
    public String getContractUrl() {
        return contractUrl;
    }
    /**
     * 设置ContractUrl
     * */
    public void setContractUrl(String contractUrl) {
        this.contractUrl = contractUrl;
    }

    /**
     * 获取创建时间
     * */
    public Date getCreateTime() {
        if (this.createTime != null) {
            return new Date(this.createTime.getTime());
        } else {
            return null;
        }
    }

    /**
     * 设置创建时间
     * */
    public void setCreateTime(Date createTime) {
        if (createTime != null) {
            Date timeTemp = (Date) createTime.clone();
            if (timeTemp != null) {
                this.createTime = timeTemp;
            }
        }
    }

    /**
     * 获取修改时间
     * */
    public Date getModTime() {
        if (this.modTime != null) {
            return new Date(this.modTime.getTime());
        } else {
            return null;
        }
    }

    /**
     * 设置修改时间
     * */
    public void setModTime(Date modTime) {
        if (modTime != null) {
            Date timeTemp = (Date) modTime.clone();
            if (timeTemp != null) {
                this.modTime = timeTemp;
            }
        }
    }
    /**
     * 获取DelFlag
     * */
    public String getDelFlag() {
        return delFlag;
    }
    /**
     * 设置DelFlag
     * */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
    /**
     * 设置StorePromise
     * */
    public String getStorePromise() {
        return storePromise;
    }
    /**
     * 设置StorePromise
     * */
    public void setStorePromise(String storePromise) {
        this.storePromise = storePromise;
    }
    /**
     * 获取StoreQi
     * */
    public String getStoreQi() {
        return storeQi;
    }
    /**
     * 设置StoreQi
     * */
    public void setStoreQi(String storeQi) {
        this.storeQi = storeQi;
    }
    /**
     * 获取CollectionSellerId
     * */
    public Long getCollectionSellerId() {
        return collectionSellerId;
    }
    /**
     * 设置CollectionSellerId
     * */
    public void setCollectionSellerId(Long collectionSellerId) {
        this.collectionSellerId = collectionSellerId;
    }

    /**
     * 得到店铺到期时间
     *
     * @return
     */
    public Date getExpiryTime() {
        return expiryTime;
    }

    /**
     * 设置店铺到期时间
     *
     * @param expiryTime
     */
    public void setExpiryTime(Date expiryTime) {
        this.expiryTime = expiryTime;
    }
}
