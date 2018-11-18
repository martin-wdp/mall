package com.qpmall.site.register.bean;

import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by ly-qpmall on 2015/10/13.
 */
public class QpEnterpriseCertificationInfo {
    private Long enterprise_id;
    private Long customerId;
    private String company_name;
    private String company_address;
    private BigDecimal company_capital;
    @Pattern(regexp = "[^\\<\\>]*")
    private String company_email;
    private String company_type;
    @Pattern(regexp = "[^\\<\\>]*")
    private String company_contact_name;
    private String card_url;
    @Pattern(regexp = "[^\\<\\>]*")
    private String company_contact_tel;
    @Pattern(regexp = "[^\\<\\>]*")
    private String buss_brand;
    @Pattern(regexp = "[^\\<\\>]*")
    private String buss_range;
    private String busscard_url;
    @Pattern(regexp = "[^\\<\\>]*")
    private String buss_legal_card_id;
    @Pattern(regexp = "[^\\<\\>]*")
    private String buss_legal_name;
    private String buss_tax_regist_url;
    private String buss_dept_no_url;
    private Date create_time;
    private Date mod_time;
    private Integer enterprise_province;
    private Integer enterprise_city;
    private Integer enterprise_county;
    @Pattern(regexp = "[^\\<\\>]*")
    private String enterprise_aptitude;
    private String check_status;
    private String company_pic_url;
    private String company_contact_addr;
    @Pattern(regexp = "[^\\<\\>]*")
    private String company_contact_cz;
    @Pattern(regexp = "[^\\<\\>]*")
    private String company_contact_yb;
    @Pattern(regexp = "(0?(13|15|17|18|14)[0-9]{9})|()")
    private String company_contact_moble;
    private Date audit_time;
    private String audit_manager;
    private String audit_feedback;

    public Date getAudit_time() {
        return audit_time;
    }

    public void setAudit_time(Date audit_time) {
        this.audit_time = audit_time;
    }

    public String getAudit_manager() {
        return audit_manager;
    }

    public void setAudit_manager(String audit_manager) {
        this.audit_manager = audit_manager;
    }

    public String getAudit_feedback() {
        return audit_feedback;
    }

    public void setAudit_feedback(String audit_feedback) {
        this.audit_feedback = audit_feedback;
    }

    public String getCompany_contact_moble() {
        return company_contact_moble;
    }

    public void setCompany_contact_moble(String company_contact_moble) {
        this.company_contact_moble = company_contact_moble;
    }

    public Long getEnterprise_id() {
        return enterprise_id;
    }

    public void setEnterprise_id(Long enterprise_id) {
        this.enterprise_id = enterprise_id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_address() {
        return company_address;
    }

    public void setCompany_address(String company_address) {
        this.company_address = company_address;
    }

    public BigDecimal getCompany_capital() {
        return company_capital;
    }

    public void setCompany_capital(BigDecimal company_capital) {
        this.company_capital = company_capital;
    }

    public String getCompany_email() {
        return company_email;
    }

    public void setCompany_email(String company_email) {
        this.company_email = company_email;
    }

    public String getCompany_type() {
        return company_type;
    }

    public void setCompany_type(String company_type) {
        this.company_type = company_type;
    }

    public String getCompany_contact_name() {
        return company_contact_name;
    }

    public void setCompany_contact_name(String company_contact_name) {
        this.company_contact_name = company_contact_name;
    }

    public String getCard_url() {
        return card_url;
    }

    public void setCard_url(String card_url) {
        this.card_url = card_url;
    }

    public String getCompany_contact_tel() {
        return company_contact_tel;
    }

    public void setCompany_contact_tel(String company_contact_tel) {
        this.company_contact_tel = company_contact_tel;
    }

    public String getBuss_brand() {
        return buss_brand;
    }

    public void setBuss_brand(String buss_brand) {
        this.buss_brand = buss_brand;
    }

    public String getBuss_range() {
        return buss_range;
    }

    public void setBuss_range(String buss_range) {
        this.buss_range = buss_range;
    }

    public String getBusscard_url() {
        return busscard_url;
    }

    public void setBusscard_url(String busscard_url) {
        this.busscard_url = busscard_url;
    }

    public String getBuss_legal_card_id() {
        return buss_legal_card_id;
    }

    public void setBuss_legal_card_id(String buss_legal_card_id) {
        this.buss_legal_card_id = buss_legal_card_id;
    }

    public String getBuss_legal_name() {
        return buss_legal_name;
    }

    public void setBuss_legal_name(String buss_legal_name) {
        this.buss_legal_name = buss_legal_name;
    }

    public String getBuss_tax_regist_url() {
        return buss_tax_regist_url;
    }

    public void setBuss_tax_regist_url(String buss_tax_regist_url) {
        this.buss_tax_regist_url = buss_tax_regist_url;
    }

    public String getBuss_dept_no_url() {
        return buss_dept_no_url;
    }

    public void setBuss_dept_no_url(String buss_dept_no_url) {
        this.buss_dept_no_url = buss_dept_no_url;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getMod_time() {
        return mod_time;
    }

    public void setMod_time(Date mod_time) {
        this.mod_time = mod_time;
    }

    public Integer getEnterprise_province() {
        return enterprise_province;
    }

    public void setEnterprise_province(Integer enterprise_province) {
        this.enterprise_province = enterprise_province;
    }

    public Integer getEnterprise_city() {
        return enterprise_city;
    }

    public void setEnterprise_city(Integer enterprise_city) {
        this.enterprise_city = enterprise_city;
    }

    public Integer getEnterprise_county() {
        return enterprise_county;
    }

    public void setEnterprise_county(Integer enterprise_county) {
        this.enterprise_county = enterprise_county;
    }

    public String getEnterprise_aptitude() {
        return enterprise_aptitude;
    }

    public void setEnterprise_aptitude(String enterprise_aptitude) {
        this.enterprise_aptitude = enterprise_aptitude;
    }

    public String getCheck_status() {
        return check_status;
    }

    public void setCheck_status(String check_status) {
        this.check_status = check_status;
    }

    public String getCompany_pic_url() {
        return company_pic_url;
    }

    public void setCompany_pic_url(String company_pic_url) {
        this.company_pic_url = company_pic_url;
    }

    public String getCompany_contact_addr() {
        return company_contact_addr;
    }

    public void setCompany_contact_addr(String company_contact_addr) {
        this.company_contact_addr = company_contact_addr;
    }

    public String getCompany_contact_cz() {
        return company_contact_cz;
    }

    public void setCompany_contact_cz(String company_contact_cz) {
        this.company_contact_cz = company_contact_cz;
    }

    public String getCompany_contact_yb() {
        return company_contact_yb;
    }

    public void setCompany_contact_yb(String company_contact_yb) {
        this.company_contact_yb = company_contact_yb;
    }
}
