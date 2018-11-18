package com.ningpai.system.sitemap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 根据url得到所有的链接
 */
public class HtmlParser {

    /** 日志 */
    // static Logger LOGGER = Logger.getLogger(HtmlParser.class);
    /**
     * 要分析的网页
     */
    String htmlUrl;
    /**
     * 分析结果
     */
    Collection<Url> hrefList = new ArrayList<Url>();
    /**
     * 网页编码方式
     */
    String charSet;

    /**
     * 构造
     *
     * @param htmlUrl
     */
    public HtmlParser(String htmlUrl) {
        // 自动生成的构造函数存根
        this.htmlUrl = htmlUrl;
    }

    /**
     * 获取分析结果
     *
     * @throws IOException
     */
    public Collection<Url> getHrefList() throws IOException {
        parser();
        return hrefList;
    }

    /**
     * 解析网页链接
     *
     * @return
     * @throws IOException
     */
    private void parser() throws IOException {
        URL url = new URL(htmlUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        String contenttype = connection.getContentType();
        charSet = getCharset(contenttype);
        InputStreamReader isr = new InputStreamReader(connection.getInputStream(), charSet);
        BufferedReader br = new BufferedReader(isr);
        String str = null, rs = null;
        String flag = "";
        List<String> flagstr = new ArrayList<>();
        while ((str = br.readLine()) != null) {

            flag = flag + br.readLine();
            rs = getHref(str);
            if (rs != null && !flagstr.contains(rs)) {
                    flagstr.add(rs);
                    hrefList.add(new Url(rs));
            }

        }
        flag = flag + "";
        br.close();
        connection.disconnect();
    }

    /**
     * 获取网页编码方式
     *
     * @param str
     */
    private String getCharset(String str) {
        Pattern pattern = Pattern.compile("charset=.*");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            return matcher.group(0).split("charset=")[1];
        }
        return null;
    }

    /**
     * 从一行字符串中读取链接
     *
     * @return
     */
    private String getHref(String str) {
        Pattern pattern = Pattern.compile("<a href=.*</a>");
        // 分类链接
        Pattern pattern2 = Pattern.compile("list.*html");
        Matcher matcher = pattern.matcher(str);
        Matcher matcher2 = pattern2.matcher(str);
        String term = "";
        if (matcher.find()) {
            String[] terms = matcher.group().split("href=\"");
            term = terms[1];
            int index = term.indexOf("\"");
            if (index != -1) {
                term = term.substring(0, index);
                if ("".equals(term)) {
                    return null;
                }
            }
        } else {
            if (matcher2.find()) {
                term = matcher2.group();
            }
        }
        if ("".equals(term)) {
            return null;
        }
        String urlflag = htmlUrl;
        String flag = urlflag;
        // 截取路径 http://qpmall.qianmi.com/index.html-->http://qpmall.qianmi.com
        int a = urlflag.indexOf("/");
        int b = 0;
        int c = 0;
        while (a > -1) {
            b = a + b + 1;
            c++;
            urlflag = urlflag.substring(a + 1, urlflag.length());
            a = urlflag.indexOf("/");
            if (c == 3) {
                flag = flag.substring(0, b - 1);
                break;
            }
        }

        String start = flag;
        if (term.contains("http://")) {
            return term;
        }

        if (!term.contains(start) && !term.contains("javascript:;")) {
            if (term.startsWith("/")) {
                start = flag;
            } else {
                start = flag + "/";
            }
            if (term.contains(".html")) {
                term = start + term;
            } else {
                term = start + term + ".html";
            }
        }

        if (!term.contains("javascript:;") && checkLink(term)) {
                return term;
        }

        return null;

    }

    /**
     * 判断链接地址是否有效
     *
     * @param a
     * @return
     */
    private boolean checkLink(String a) {

        String regex = "(https?|ftp):\\/\\/(((([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|"
                + "(%[\\da-f]{2})|[!\\$&'\\(\\)\\*\\+,;=]|:)*@)?(((\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5])\\."
                + "(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5])\\." + "(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5]))|(("
                + "([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(" + "([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\"
                + ".|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*" + "([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.)+("
                + "([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(" + "([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\"
                + ".|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*" + "([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.?)(:\\d*)?)(\\/((([a-z]|\\d|-|\\"
                + ".|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(%[\\da-f]{2})|[!\\$&'\\(\\)\\*\\+,;=]|:|@)+"
                + "(\\/(([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(%[\\da-f]{2})|[!\\$&'\\" + "(\\)\\*\\+,;=]|:|@)*)*)?)?(\\?((([a-z]|\\d|-|\\"
                + ".|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(%[\\da-f]{2})|[!\\$&'\\(\\)\\*\\+,;=]|:|@)" + "|[\\uE000-\\uF8FF]|\\/|\\?)*)?(\\#((([a-z]|\\d|-|\\"
                + ".|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(%[\\da-f]{2})|[!\\$&'\\(\\)\\*\\+,;=]|:|@)" + "|\\/|\\?)*)?";
        return Pattern.matches(regex, a);
    }

}