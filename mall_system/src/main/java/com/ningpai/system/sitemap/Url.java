package com.ningpai.system.sitemap;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 创建xml的元素 实现效果如下 <url> <ioc>http://qpmall.qianmi.com/index.html </ioc> </url>
 * 引用网站的超链接
 */
@XmlRootElement(name = "url")
public class Url {
    /** 图标 */
    private String ioc;

    /**
     * 构造
     */
    public Url() {
    }

    /**
     * 构造
     * 
     * @param ioc
     */
    public Url(String ioc) {
        super();
        this.ioc = ioc;
    }

    @XmlElement(name = "ioc")
    public String getIoc() {
        return ioc;
    }

    public void setIoc(String ioc) {
        this.ioc = ioc;
    }

}
