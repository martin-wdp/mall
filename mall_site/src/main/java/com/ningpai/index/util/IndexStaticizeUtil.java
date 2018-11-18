/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.index.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ningpai.common.util.ConstantUtil;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 头部静态化工具类
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年8月5日下午4:03:03
 */
public class IndexStaticizeUtil {

    private static final Logger LOGGER = Logger.getLogger(IndexStaticizeUtil.class);

    private IndexStaticizeUtil() {
    }

    /**
     * FreeMarker生成静态页面
     * 
     * @param templatePath
     *            模板文件存放的文件夹路径
     * @param templateName
     *            模板文件名称
     * @param fileName
     *            生成的文件路径和名称
     * @param root
     */
    public static void analysisTemplate(String templatePath, String templateName, String fileName, Map<?, ?> root) {
        try {
            Configuration config = new Configuration();
            // 设置要解析的模板所在的目录，并加载模板文件
            config.setDirectoryForTemplateLoading(new File(templatePath));
            // 设置包装器，并将对象包装为数据模型
            config.setObjectWrapper(new DefaultObjectWrapper());

            // 获取模板,并设置编码方式，这个编码必须要与页面中的编码格式一致
            // 否则会出现乱码
            Template template = config.getTemplate(templateName, ConstantUtil.UTF);
            // 合并数据模型与模板
            FileOutputStream fos = new FileOutputStream(fileName);
            Writer out = new OutputStreamWriter(fos, ConstantUtil.UTF);
            template.process(root, out);
            out.flush();
            out.close();
        } catch (IOException e) {
            LOGGER.error("IO错误", e);
        } catch (TemplateException e) {
            LOGGER.error("模板生成错误", e);
        }
    }

}
