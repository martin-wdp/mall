package com.ningpai.system.sitemap;

import javax.xml.bind.*;
import java.io.*;
import java.util.Iterator;

/**
 * 生成网站地图的xml文件
 */
public class CreateSiteMap {

    /**
     * 网站的所有超链接xml文件设置 开头部分
     */
    protected static final String URLSET_START = "<?xml version='1.0' encoding='UTF-8'?>\n" + "<urlset xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"
            + "         xsi:schemaLocation=\"http://www.sitemaps.org/schemas/sitemap/0.9 http://www.sitemaps.org/schemas/sitemap/0.9/sitemap.xsd\"\n"
            + "         xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">\n";
    /**
     * 结尾部分
     */
    protected static final String URLSET_END = "\n</urlset>";

    /**
     * 如果网站的超链接xml文件不止一个, 可用<sitemap>
     * <ioc>http://www.aaa.com/sitemap/sitemap01.xml </ioc> </sitemap>
     * 进行引用,如果有一个则不需要引用
     */
    protected static final String SITEMAPINDEX_START = "<?xml version='1.0' encoding='UTF-8'?>\n" + "<sitemapindex xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"
            + "         xsi:schemaLocation=\"http://www.sitemaps.org/schemas/sitemap/0.9 http://www.sitemaps.org/schemas/sitemap/0.9/siteindex.xsd\"\n"
            + "         xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">\n";
    protected static final String SITEMAPINDEX_END = "\n</sitemapindex>";

    /**
     * 生成多个引用.xml文件
     * 
     * @param writer
     * @param mappings
     * @throws IOException
     */
    public static void writeSitemapIndex(Writer writer, Iterator<? extends Sitemap> mappings) throws IOException {
        writeXml(writer, SITEMAPINDEX_START, mappings, SITEMAPINDEX_END);
    }

    /**
     * 生成页面的所有超链接
     * 
     * @param writer
     * @param urls
     * @return
     * @throws IOException
     */
    public static long writeUrlset(Writer writer, Iterator<? extends Url> urls) throws IOException {
        return writeXml(writer, URLSET_START, urls, URLSET_END);
    }

    /**
     *
     * @param writer
     * @param start
     *            xml文件开头
     * @param it
     *            xml文件中间部分即<url><ioc></ioc></url>
     * @param end
     *            xml结尾
     * @return
     * @throws IOException
     */
    private static long writeXml(Writer writer, String start, Iterator<?> it, String end) throws IOException {
        writer.write(start);
        long count = writeSubtree(writer, it);
        writer.write(end);
        return count;
    }

    /**
     * 生成xml元素树 效果如下 <url> <ioc> </ioc> </url>
     * 
     * @param writer
     * @param it
     * @return
     * @throws IOException
     */
    public static long writeSubtree(Writer writer, Iterator<?> it) throws IOException {
        long size = 0;
        Marshaller m;
        try {
            JAXBContext jc = JAXBContext.newInstance(Sitemap.class, Url.class);
            m = jc.createMarshaller();
            m.setProperty(Marshaller.JAXB_FRAGMENT, true);
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        } catch (PropertyException e) {
            throw new DataBindingException(e);
        } catch (JAXBException e) {
            throw new DataBindingException(e);
        }

        boolean first = true;
        while (it.hasNext()) {
            if (first) {
                first = false;
            } else {
                writer.write("\n");
            }
            try {
                m.marshal(it.next(), writer);
            } catch (JAXBException e) {
                throw new IOException(e);
            }
            size++;
        }
        return size;
    }

}