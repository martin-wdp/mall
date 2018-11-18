package com.ningpai.manager.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 管理员Bean
 * 
 * @author AthrunNatu
 * @since 2013年11月20日下午3:00:18
 * @version 0.0.1
 */
public class Manager implements Serializable {
    /**
     * 序列化ID
     */
    private static final long serialVersionUID = -449909232999422484L;
    /**
     * 管理员ID
     * 
     * @see #getId()
     * @see #setId(Long)
     */
    private Long id;
    /**
     * 管理员名称
     * 
     * @see #getUsername()
     * @see #setUsername(String)
     */
    private String username;
    /**
     * 管理员密码
     * 
     * @see #getUserkey()
     * @see #setUserkey(String)
     */
    private String userkey;
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
     * @see #getModTime()
     * @see #setModTime(Date)
     */
    private Date modTime;
    /**
     * 最后登录时间
     * 
     * @see #getLoginTime()
     * @see #setLoginTime(Date)
     */
    private Date loginTime;
    /**
     * 状态标记
     * 
     * @see #getFlag()
     * @see #setFlag(String)
     */
    private String flag;
    // 分页开始的条数
    private int startRowNum;
    // 分页结束的条数
    private int endRowNum;

    // 管理员手机
    private String mobile;
    /**
     * 手机验证码
     * 
     * @see #getCaptcha()
     * @see #setCaptcha(String)
     */
    private String captcha;
    /**
     * 验证码失效时间
     * 
     * @see #getAeadTime()
     * @see #setAeadTime(Date)
     */
    private Date aeadTime;
    // 管理员头像
    private String photoImg;

    /**
     * 创建者ID
     */
    private Long createId;

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    public String getPhotoImg() {
        return photoImg;
    }

    public void setPhotoImg(String photoImg) {
        this.photoImg = photoImg;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    /**
     * 获取验证码有效时间
     * @return 验证码有效时间
     */
    public Date getAeadTime() {
        if (this.aeadTime != null) {
            return new Date(this.aeadTime.getTime());
        }
        return null;
    }

    /**
     * 设置验证码有效时间
     * @param aeadTime 验证码有效时间，一般为当前时间
     */
    public void setAeadTime(Date aeadTime) {
        if (aeadTime != null) {
            Date tEmp = (Date) aeadTime.clone();
            if (tEmp != null) {
                this.aeadTime = tEmp;
            }
        }
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getStartRowNum() {
        return startRowNum;
    }

    public void setStartRowNum(int startRowNum) {
        this.startRowNum = startRowNum;
    }

    public int getEndRowNum() {
        return endRowNum;
    }

    public void setEndRowNum(int endRowNum) {
        this.endRowNum = endRowNum;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    /**
     * 获取<code>Manager</code>ID值
     * 
     * @return id{@link com.ningpai.manager.bean.Manager#id}
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置<code>Manager</code>ID值
     * 
     * @param id
     *            {@link com.ningpai.manager.bean.Manager#id}
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取<code>Manager</code>username
     * 
     * @return username{@link com.ningpai.manager.bean.Manager#username}
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置<code>Manager</code>username
     * 
     * @param username
     *            {@link com.ningpai.manager.bean.Manager#username}
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取<code>Manager</code>userkey
     * 
     * @return userkey{@link com.ningpai.manager.bean.Manager#userkey}
     */
    public String getUserkey() {
        return userkey;
    }

    /**
     * 设置<code>Manager</code>userkey
     * 
     * @param userkey
     *            {@link com.ningpai.manager.bean.Manager#userkey}
     */
    public void setUserkey(String userkey) {
        this.userkey = userkey;
    }

    /**
     * 获取<code>Manager</code>createTime
     * 
     * @return createTime{@link com.ningpai.manager.bean.Manager#createTime}
     */
    public Date getCreateTime() {
        if (this.createTime != null) {
            return new Date(this.createTime.getTime());
        }
        return null;
    }

    /**
     * 设置<code>Manager</code>createTime
     * 
     * @param createTime
     *            {@link com.ningpai.manager.bean.Manager#createTime}
     */
    public void setCreateTime(Date createTime) {
        if (createTime != null) {
            Date tEmp = (Date) createTime.clone();
            if (tEmp != null) {
                this.createTime = tEmp;
            }
        }
    }

    /**
     * 获取<code>Manager</code>modTime
     * 
     * @return modTime{@link com.ningpai.manager.bean.Manager#modTime}
     */
    public Date getModTime() {
        if (this.modTime != null) {
            return new Date(this.modTime.getTime());
        }
        return null;
    }

    /**
     * 设置<code>Manager</code>modTime
     * 
     * @param modTime
     *            {@link com.ningpai.manager.bean.Manager#modTime}
     */
    public void setModTime(Date modTime) {
        if (modTime != null) {
            Date tEmp = (Date) modTime.clone();
            if (tEmp != null) {
                this.modTime = tEmp;
            }
        }
    }

    /**
     * 获取<code>Manager</code>loginTime
     * 
     * @return loginTime{@link com.ningpai.manager.bean.Manager#loginTime}
     */
    public Date getLoginTime() {
        if (this.loginTime != null) {
            return new Date(this.loginTime.getTime());
        }
        return null;
    }

    /**
     * 设置<code>Manager</code>loginTime
     * 
     * @param loginTime
     *            {@link com.ningpai.manager.bean.Manager#loginTime}
     */
    public void setLoginTime(Date loginTime) {
        if (loginTime != null) {
            Date tEmp = (Date) loginTime.clone();
            if (tEmp != null) {
                this.loginTime = tEmp;
            }
        }
    }

    /**
     * 获取<code>Manager</code>flag
     * 
     * @return flag{@link com.ningpai.manager.bean.Manager#flag}
     */
    public String getFlag() {
        return flag;
    }

    /**
     * 设置<code>Manager</code>flag
     * 
     * @param flag
     *            {@link com.ningpai.manager.bean.Manager#flag}
     */
    public void setFlag(String flag) {
        this.flag = flag;
    }
}
