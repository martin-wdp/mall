package com.ningpai.common.util.tenpay.client;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.io.IOException;

import org.jdom.JDOMException;

import com.ningpai.common.util.tenpay.util.MD5Util;
import com.ningpai.common.util.tenpay.util.XMLUtil;

/**
 * ��̨Ӧ����<br/>
 * ========================================================================<br/>
 * api˵����<br/>
 * getKey()/setKey(),��ȡ/������Կ<br/>
 * getContent() / setContent(), ��ȡ/����ԭʼ����<br/>
 * getParameter()/setParameter(),��ȡ/���ò���ֵ<br/>
 * getAllParameters(),��ȡ���в���<br/>
 * isTenpaySign(),�Ƿ�Ƹ�ͨǩ��,true:�� false:��<br/>
 * getDebugInfo(),��ȡdebug��Ϣ<br/>
 * 
 * ========================================================================<br/>
 *
 */
public class ClientResponseHandler {

    /** Ӧ��ԭʼ���� */
    private String content;

    /** Ӧ��Ĳ��� */
    private SortedMap parameters;

    /** debug��Ϣ */
    private String debugInfo;

    /** ��Կ */
    private String key;

    /** �ַ� */
    private String charset;

    /**
     * ClientResponseHandler
     */
    public ClientResponseHandler() {
        this.content = "";
        this.parameters = new TreeMap();
        this.debugInfo = "";
        this.key = "";
        this.charset = "GBK";
    }

    /**
     * getContent
     * 
     * @return
     */
    public String getContent() {
        return content;
    }

    /**
     * setContent
     * 
     * @param content
     * @throws Exception
     */
    public void setContent(String content) throws Exception {
        this.content = content;

        this.doParse();
    }

    /**
     * ��ȡ����ֵ
     * 
     * @param parameter
     *            �������
     * @return String
     */
    public String getParameter(String parameter) {
        String s = (String) this.parameters.get(parameter);
        return (null == s) ? "" : s;
    }

    /**
     * ���ò���ֵ
     * 
     * @param parameter
     *            �������
     * @param parameterValue
     *            ����ֵ
     */
    public void setParameter(String parameter, String parameterValue) {
        String v = "";
        if (null != parameterValue) {
            v = parameterValue.trim();
        }
        this.parameters.put(parameter, v);
    }

    /**
     * �������еĲ���
     * 
     * @return SortedMap
     */
    public SortedMap getAllParameters() {
        return this.parameters;
    }

    public String getDebugInfo() {
        return debugInfo;
    }

    /**
     * ��ȡ��Կ
     */
    public String getKey() {
        return key;
    }

    /**
     * ������Կ
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * getCharset
     * 
     * @return
     */
    public String getCharset() {
        return this.charset;
    }

    /**
     * setCharset
     * 
     * @param charset
     */
    public void setCharset(String charset) {
        this.charset = charset;
    }

    /**
     * �Ƿ�Ƹ�ͨǩ��,������:���������a-z����,������ֵ�Ĳ���μ�ǩ��
     * 
     * @return boolean
     */
    public boolean isTenpaySign() {
        StringBuilder sb = new StringBuilder();
        Set es = this.parameters.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if (!"sign".equals(k) && null != v && !"".equals(v)) {
                sb.append(k + "=" + v + "&");
            }
        }

        sb.append("key=" + this.getKey());

        // ���ժҪ
        String sign = MD5Util.MD5Encode(sb.toString(), this.charset).toLowerCase();

        String tenpaySign = this.getParameter("sign").toLowerCase();

        // debug��Ϣ
        this.setDebugInfo(sb.toString() + " => sign:" + sign + " tenpaySign:" + tenpaySign);

        return tenpaySign.equals(sign);
    }

    /**
     * �Ƿ�Ƹ�ͨǩ��
     * 
     * @param signParameterArray
     *            ǩ��Ĳ�������
     * @return boolean
     */
    protected boolean isTenpaySign(String[] signParameterArray) {

        StringBuilder signPars = new StringBuilder();
        for (int index = 0; index < signParameterArray.length; index++) {
            String k = signParameterArray[index];
            String v = this.getParameter(k);
            if (null != v && !"".equals(v)) {
                signPars.append(k + "=" + v + "&");
            }
        }

        signPars.append("key=" + this.getKey());

        // ���ժҪ
        String sign = MD5Util.MD5Encode(signPars.toString(), this.charset).toLowerCase();

        String tenpaySign = this.getParameter("sign").toLowerCase();

        // debug��Ϣ
        this.setDebugInfo(signPars.toString() + " => sign:" + sign + " tenpaySign:" + tenpaySign);

        return tenpaySign.equals(sign);
    }

    /**
     * setDebugInfo
     * 
     * @param debugInfo
     */
    protected void setDebugInfo(String debugInfo) {
        this.debugInfo = debugInfo;
    }

    /**
     * ����XML����
     */
    protected void doParse() throws JDOMException, IOException {
        String xmlContent = this.getContent();

        // ����xml,�õ�map
        Map m = XMLUtil.doXMLParse(xmlContent);

        // ���ò���
        Iterator it = m.keySet().iterator();
        while (it.hasNext()) {
            String k = (String) it.next();
            String v = (String) m.get(k);
            this.setParameter(k, v);
        }

    }

}
