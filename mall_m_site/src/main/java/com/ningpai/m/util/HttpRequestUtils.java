package com.ningpai.m.util;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;


/**
 * post和get请求工具类
 * 
 * @author John
 * 
 */
public class HttpRequestUtils {

	/**
	 * 发送post请求 默认超时时间30秒
	 * 
	 * @param url
	 * @param objects
	 * @param parameterValue
	 * @return
	 * @throws Exception
	 */
	public static String post(String url, Object[] objects,
			Object[] parameterValue) throws Exception {
		if (objects == null || objects == null
				|| (objects.length != parameterValue.length)) {
			throw new NullPointerException("参数错误");
		}
		// HttpClient httpClient = new HttpClient();
		// httpClient.setConnectionTimeout(30000);
		// httpClient.setTimeout(3000);
		//
		// PostMethod p = new PostMethod(url);
		// p.addRequestHeader("Content-Type",
		// "application/json; charset=UTF-8");
		// //初始化参数
		// for (int i = 0; i < parameterValue.length; i++) {
		// p.setParameter(parameterName[i], parameterValue[i]);
		// }
		//
		// httpClient.executeMethod(p);
		// return p.getResponseBodyAsString();

		org.apache.http.client.HttpClient httpclient = new DefaultHttpClient();

		HttpPost httpPost = new HttpPost(url);
		HttpParams httpParams = httpclient.getParams();
		httpParams.setParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE, true);

		List<org.apache.http.NameValuePair> nvps = new ArrayList<org.apache.http.NameValuePair>();
		for (int i = 0; i < parameterValue.length; i++) {
			nvps.add(new BasicNameValuePair(objects[i].toString(),
					parameterValue[i].toString()));
		}
		httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
		HttpResponse httpResponse = httpclient.execute(httpPost);
		HttpEntity returnEntity = httpResponse.getEntity();
		String returnJson = EntityUtils.toString(returnEntity,"utf-8");
		return returnJson;
	}

	/**
	 * 发送get请求 默认超时时间30秒
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static String get(String url) throws Exception {

		String result = "scuess";
		HttpClient httpClient = new HttpClient();
		httpClient.setConnectionTimeout(30000);
		httpClient.setTimeout(3000);

		GetMethod g = new GetMethod(url);
		g.addRequestHeader("Content-Type", "application/json; charset=UTF-8");

		httpClient.executeMethod(g);
		return g.getResponseBodyAsString();

	}

}
