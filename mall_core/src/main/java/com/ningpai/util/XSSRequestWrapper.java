package com.ningpai.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.regex.Pattern;

/**
 * 请求包装类
 * @author NP-HEHU
 * @date 2015-8-28 17:47:23
 */
public class XSSRequestWrapper extends HttpServletRequestWrapper {
    /**
     * 构照方法
     * */
    public XSSRequestWrapper(HttpServletRequest servletRequest) {
        super(servletRequest);
    }

    /**
     * 重写父类方法
     * */
    @Override
    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);
        if (values == null) {
            return new String[0];
        }
        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
            encodedValues[i] = stripXSS(values[i], parameter);
        }
        return encodedValues;
    }

    /**
     * 重写父类方法
     * */
    @Override
    public String getParameter(String parameter) {
        String value = super.getParameter(parameter);
        return stripXSS(value, parameter);
    }

    /**
     * 重写父类方法
     * */
    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        return stripXSS(value, name);
    }

    /**
     * 过滤参数
     * @param value 参数值
     * @param parameter 参数name名
     * @return
     */
    private String stripXSS(String value, String parameter) {
        String valueNew = value;

        if("keyWords".equals(parameter)){
            System.out.println("==========================");
        }
        if (getNoCheckParameter(parameter) && valueNew != null) {

            // NOTE: It's highly recommended to use the ESAPI library and
            // uncomment the following line to
            // avoid encoded attacks.
            // value = ESAPI.encoder().canonicalize(value);
            // Avoid null characters
            valueNew = valueNew.replaceAll("", "");
            // Avoid anything between script tags

            Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>",
                    Pattern.CASE_INSENSITIVE);
            valueNew = scriptPattern.matcher(valueNew).replaceAll("");

            scriptPattern = Pattern.compile("[%<>\"]+");
            valueNew = scriptPattern.matcher(valueNew).replaceAll("");

        }

        return valueNew;
    }

    /**
     * 判断name是否应该拦截
     * @param parameter 参数名
     * @return 不拦截返回true，拦截返回false
     */

    private boolean getNoCheckParameter(String parameter) {
        String[] noFilterURLs = new String[] { "goodsDetailDesc",
                "goodsMobileDesc", "bsetUseragreement","bsetEnterpriseagreement",
                "bsetUseragreementuser", "bsetCopyright", "content","thirdUserment",
                "helpContent", "marketingDes","giftDesc",
                "ipCont", "str", "pageDes", "title", "thirdProjectContext",
                "backInfoRemark","backPriceRemark","payHelp","autoStyle","jsonArr"// ,"chProvince","ch_city","ch_distinct"
        };

        for (String parameters : noFilterURLs) {
            if (parameter.equals(parameters)) {
                return false;
            }
        }
        return true;
    }

}