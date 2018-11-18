/*
 * Copyright 2003-2009 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package com.ningpai.system.sitemap;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 创建xml的元素 实现效果如下 <sitemap> <ioc>http://www.aaa.com/sitemap/sitemap01.xml
 * </ioc> </sitemap> 引用多个xml文件
 */
@XmlRootElement(name = "sitemap")
public class Sitemap {
    /** 图标 */
    private String ioc;

    /**
     * 构造
     */
    public Sitemap() {
    }

    /**
     * 构造
     * @param ioc
     */
    public Sitemap(String ioc) {
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
