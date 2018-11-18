package com.ningpai.customer.bean;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.ningpai.other.bean.CityBean;
import com.ningpai.other.bean.DistrictBean;
import com.ningpai.other.bean.ProvinceBean;
import com.ningpai.other.bean.StreetBean;

/**
 * 会员收货地址
 * 
 * @author NINGPAI-zhangqiang
 * @since 2013年12月17日 下午4:18:26
 * @version
 */
public class CustomerAddress implements Serializable {

    /**
     * 序列化
     */
    private static final long serialVersionUID = -933349561383364868L;
    /**
     * 地址编号
     * 
     * @see #getAddressId()
     * @see #setAddressId(Long)
     */
    private Long addressId;
    /**
     * 会员编号
     * 
     * @see #getCustomerId()
     * @see #setCustomerId(Long)
     */
    private Long customerId;
    /**
     * 收货人姓名
     * 
     * @see #getAddressName()
     * @see #setAddressName(String)
     */
    @NotNull
    @Pattern(regexp = "[^`~!@#$^&*()={}':;',\\[\\]<>/?~！@#￥……&*（）{}【】‘；：”“'。，、？]+")
    private String addressName;

    /**
     * 地址明细
     * 
     * @see #getAddressDetail()
     * @see CustomerAddress#setAddressDetail(String)
     */
    @NotNull
    @Pattern(regexp = "[^`~!@#$^&*()={}':;',\\[\\]<>/?~！@#￥……&*（）{}【】‘；：”“'。，、？]+")
    private String addressDetail;
    /**
     * 联系手机
     * 
     * @see #getAddressMoblie()
     */
    @NotNull
    @Pattern(regexp = "^0?1[0-9]{10}$")
    private String addressMoblie;
    /**
     * 电话
     * 
     * @see #getAddressPhone()
     * @see #setAddressPhone(String)
     */
    @Pattern(regexp = "^((0[0-9]{2,3}\\-)|(\\(0[0-9]{2,3}\\)))?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?|()$")
    private String addressPhone;
    /**
     * 邮编
     * 
     * @see #getAddressZip()
     * @see #setAddressZip(String)
     */
    @Pattern(regexp = "^[1-9]\\d{5}(?!\\d)|()$")
    private String addressZip;
    /**
     * 支付方式
     * 
     * @see #getAddressPay()
     * @see #setAddressPay(String)
     */
    private String addressPay;
    /**
     * 配送方式
     * 
     * @see #getAddressShip()
     * @see #setAddressShip(String)
     */
    private String addressShip;
    /**
     * 送货时间
     * 
     * @see #getAddressTime()
     * @see #setAddressTime(String)
     */
    private String addressTime;
    /**
     * 发票类型
     * 
     * @see #getAddressBillType()
     * @see #setAddressBillType(String)
     */
    private String addressBillType;
    /**
     * 发票抬头
     * 
     * @see #getAddressBillTitle()
     * @see #setAddressBillTitle(String)
     */
    private String addressBillTitle;
    /**
     * 发票内容
     * 
     * @see #getAddressBillContent()
     * @see #setAddressBillContent(String)
     */
    private String addressBillContent;
    /**
     * 电话确认
     * 
     * @see #getAddressConfirm()
     * @see #setAddressConfirm(String)
     */
    private String addressConfirm;
    /**
     * 地址别名
     * 
     * @see #getAddressAlias()
     * @see #setAddressAlias(String)
     */
    @Pattern(regexp = "[^`~!@#$^&*()={}':;',\\[\\]<>/?~！@#￥……&*（）{}【】‘；：”“'。，、？]+|()")
    private String addressAlias;
    /**
     * 是否默认
     * 
     * @see #getIsDefault()
     * @see #setIsDefault(String)
     */
    private String isDefault;
    /**
     * 创建时间
     * 
     * @see #getCreateTime()
     * @see #setCreateTime(Date)
     */
    private Date createTime;
    /**
     * 修改时间
     * 
     * @see #getModifiedTime()
     * @see #setModifiedTime(Date)
     */
    private Date modifiedTime;
    /**
     * 删除时间
     * 
     * @see #getDelTime()
     * @see #setDelTime(Date)
     */
    private Date delTime;
    /**
     * 删除标记
     * 
     * @see #getDelFlag()
     * @see #setDelFlag(String)
     */
    private String delFlag;
    /**
     * 省份id
     * 
     * @see #getInfoProvince()
     * @see #setInfoProvince(String)
     */
    private String infoProvince;
    /**
     * 城市id
     * 
     * @see #getInfoCity()
     * @see #setInfoCity(String)
     */
    private String infoCity;
    /**
     * 乡镇id
     * 
     * @see #getInfoCounty()
     * @see #setInfoCounty(String)
     */
    private String infoCounty;
    /**
     * 地址对象
     * 
     * @see #getCustomerAddress()
     * @see #setCustomerAddress(CustomerAddress)
     */
    private CustomerAddress customerAddress;
    /**
     * 省份
     * 
     * @see #getProvince()
     * @see #setProvince(ProvinceBean)
     */
    private ProvinceBean province;
    /**
     * 城市
     * 
     * @see #getCity()
     * @see #setCity(CityBean)
     */
    private CityBean city;
    /**
     * 区县
     * 
     * @see #getDistrict()
     * @see #setDistrict(DistrictBean)
     */
    private DistrictBean district;
    /**
     * 街道
     * 
     * @see #getInfoStreet()
     * @see #setInfoStreet(String)
     */
    private String infoStreet;
    // 街道
    private StreetBean street;

    /**
     * 邮箱
     */
    private String addressEmail;

    public String getAddressEmail() {
        return addressEmail;
    }

    public void setAddressEmail(String addressEmail) {
        this.addressEmail = addressEmail;
    }

    public StreetBean getStreet() {
        return street;
    }

    public void setStreet(StreetBean street) {
        this.street = street;
    }

    public String getInfoStreet() {
        return infoStreet;
    }

    public void setInfoStreet(String infoStreet) {
        this.infoStreet = infoStreet;
    }

    /**
     * 获取infoProvince的值
     * 
     * @return infoProvince
     *         {@link com.ningpai.customer.bean.CustomerAddress#infoProvince}
     */
    public String getInfoProvince() {
        return infoProvince;
    }

    /**
     * 设置infoProvince的值
     * 
     * @param infoProvince
     *            {@link com.ningpai.customer.bean.CustomerAddress#delTime}
     */
    public void setInfoProvince(String infoProvince) {
        this.infoProvince = infoProvince;
    }

    /**
     * 获取infoCity的值
     * 
     * @return infoCity
     *         {@link com.ningpai.customer.bean.CustomerAddress#infoCity}
     */
    public String getInfoCity() {
        return infoCity;
    }

    /**
     * 设置infoCity的值
     * 
     * @param infoCity
     *            {@link com.ningpai.customer.bean.CustomerAddress#infoCity}
     */
    public void setInfoCity(String infoCity) {
        this.infoCity = infoCity;
    }

    /**
     * 获取infoCounty的值
     * 
     * @return infoCounty
     *         {@link com.ningpai.customer.bean.CustomerAddress#infoCounty}
     */
    public String getInfoCounty() {
        return infoCounty;
    }

    /**
     * 设置infoCounty的值
     * 
     * @param infoCounty
     *            {@link com.ningpai.customer.bean.CustomerAddress#infoCounty}
     */
    public void setInfoCounty(String infoCounty) {
        this.infoCounty = infoCounty;
    }

    public CustomerAddress getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(CustomerAddress customerAddress) {
        this.customerAddress = customerAddress;
    }

    public ProvinceBean getProvince() {
        return province;
    }

    public void setProvince(ProvinceBean province) {
        this.province = province;
    }

    public CityBean getCity() {
        return city;
    }

    public void setCity(CityBean city) {
        this.city = city;
    }

    public DistrictBean getDistrict() {
        return district;
    }

    public void setDistrict(DistrictBean district) {
        this.district = district;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getAddressMoblie() {
        return addressMoblie;
    }

    public void setAddressMoblie(String addressMoblie) {
        this.addressMoblie = addressMoblie;
    }

    public String getAddressPhone() {
        return addressPhone;
    }

    public void setAddressPhone(String addressPhone) {
        this.addressPhone = addressPhone;
    }

    public String getAddressZip() {
        return addressZip;
    }

    public void setAddressZip(String addressZip) {
        this.addressZip = addressZip;
    }

    public String getAddressPay() {
        return addressPay;
    }

    public void setAddressPay(String addressPay) {
        this.addressPay = addressPay;
    }

    public String getAddressShip() {
        return addressShip;
    }

    public void setAddressShip(String addressShip) {
        this.addressShip = addressShip;
    }

    public String getAddressTime() {
        return addressTime;
    }

    public void setAddressTime(String addressTime) {
        this.addressTime = addressTime;
    }

    public String getAddressBillType() {
        return addressBillType;
    }

    public void setAddressBillType(String addressBillType) {
        this.addressBillType = addressBillType;
    }

    public String getAddressBillTitle() {
        return addressBillTitle;
    }

    public void setAddressBillTitle(String addressBillTitle) {
        this.addressBillTitle = addressBillTitle;
    }

    public String getAddressBillContent() {
        return addressBillContent;
    }

    public void setAddressBillContent(String addressBillContent) {
        this.addressBillContent = addressBillContent;
    }

    public String getAddressConfirm() {
        return addressConfirm;
    }

    public void setAddressConfirm(String addressConfirm) {
        this.addressConfirm = addressConfirm;
    }

    public String getAddressAlias() {
        return addressAlias;
    }

    public void setAddressAlias(String addressAlias) {
        this.addressAlias = addressAlias;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    /**
     * 获取createTime的值
     * 
     * @return createTime
     *         {@link com.ningpai.customer.bean.CustomerAddress#createTime}
     */
    public Date getCreateTime() {
        if (this.createTime != null) {
            return new Date(this.createTime.getTime());
        } else {
            return null;
        }
    }

    /**
     * 设置createTime的值
     * 
     * @param createTime
     *            {@link com.ningpai.customer.bean.CustomerAddress#createTime}
     */
    public void setCreateTime(Date createTime) {
        if (createTime != null) {
            Date timeTemp = (Date) createTime.clone();
            if (timeTemp != null) {
                this.createTime = timeTemp;
            }
        }
    }

    /**
     * 获取modifiedTime的值
     * 
     * @return modifiedTime
     *         {@link com.ningpai.customer.bean.CustomerAddress#modifiedTime}
     */
    public Date getModifiedTime() {
        if (this.modifiedTime != null) {
            return new Date(this.modifiedTime.getTime());
        } else {
            return null;
        }
    }

    /**
     * 设置modifiedTime的值
     * 
     * @param modifiedTime
     *            {@link com.ningpai.customer.bean.CustomerAddress#modifiedTime}
     */
    public void setModifiedTime(Date modifiedTime) {
        if (modifiedTime != null) {
            Date timeTemp = (Date) modifiedTime.clone();
            if (timeTemp != null) {
                this.modifiedTime = timeTemp;
            }
        }
    }

    /**
     * 获取delFlag的值
     * 
     * @return delFlag {@link com.ningpai.customer.bean.CustomerAddress#delFlag}
     */
    public String getDelFlag() {
        return delFlag;
    }

    /**
     * 设置delFlag的值
     * 
     * @param delFlag
     *            {@link com.ningpai.customer.bean.CustomerAddress#delFlag}
     */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * 获取delTime的值
     * 
     * @return delTime {@link com.ningpai.customer.bean.CustomerAddress#delTime}
     */
    public Date getDelTime() {
        if (this.delTime != null) {
            return new Date(this.delTime.getTime());
        } else {
            return null;
        }
    }

    /**
     * 设置delTime的值
     * 
     * @param delTime
     *            {@link com.ningpai.customer.bean.CustomerAddress#delTime}
     */
    public void setDelTime(Date delTime) {
        if (delTime != null) {
            Date timeTemp = (Date) delTime.clone();
            if (timeTemp != null) {
                this.delTime = timeTemp;
            }
        }
    }

}
