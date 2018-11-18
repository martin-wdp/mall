package com.tenpay;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tenpay.client.TenpayHttpClient;
import com.tenpay.util.ConstantUtil;
import com.tenpay.util.JsonUtil;
import com.tenpay.util.WXUtil;

public class AccessTokenRequestHandler extends RequestHandler {

	public AccessTokenRequestHandler(HttpServletRequest request,
			HttpServletResponse response) {
		super(request, response);
	}

	private static String access_token = "";

	/**
	 * ��ȡƾ֤access_token
	 * @return
	 */
	public static String getAccessToken() {
		if ("".equals(access_token)) {// ���Ϊ��ֱ�ӻ�ȡ
			return getTokenReal();
		}

		if (tokenIsExpire(access_token)) {// ����������»�ȡ
			return getTokenReal();
		}
		return access_token;
	}

	/**
	 * ʵ�ʻ�ȡaccess_token�ķ���
	 * @return
	 */
	protected static String getTokenReal() {
		String requestUrl = ConstantUtil.TOKENURL + "?grant_type=" + ConstantUtil.GRANT_TYPE + "&appid="
				+ ConstantUtil.APP_ID + "&secret=" + ConstantUtil.APP_SECRET;
		String resContent = "";
		TenpayHttpClient httpClient = new TenpayHttpClient();
		httpClient.setMethod("GET");
		httpClient.setReqContent(requestUrl);
		if (httpClient.call()) {
			resContent = httpClient.getResContent();
			if (resContent.indexOf(ConstantUtil.ACCESS_TOKEN) > 0) {
				access_token = JsonUtil.getJsonValue(resContent, ConstantUtil.ACCESS_TOKEN);
			} else {
				System.out.println("��ȡaccess_tokenֵ���ش��󣡣���");
			}
		} else {
			System.out.println("��̨����ͨ��ʧ��");
			System.out.println(httpClient.getResponseCode());
			System.out.println(httpClient.getErrInfo());
			// �п�����Ϊ����ԭ�������Ѿ�������δ�յ�Ӧ��
		}

		return access_token;
	}

	/**
	 * �жϴ��ݹ����Ĳ���access_token�Ƿ����
	 * @param access_token
	 * @return
	 */
	private static boolean tokenIsExpire(String access_token) {
		boolean flag = false;
		PrepayIdRequestHandler wxReqHandler = new PrepayIdRequestHandler(null, null);
		wxReqHandler.setParameter("appid", ConstantUtil.APP_ID);
		wxReqHandler.setParameter("appkey",ConstantUtil.APP_KEY);
		wxReqHandler.setParameter("noncestr", WXUtil.getNonceStr());
		wxReqHandler.setParameter("package", ConstantUtil.packageValue);
		wxReqHandler.setParameter("timestamp", WXUtil.getTimeStamp());
		wxReqHandler.setParameter("traceid", ConstantUtil.traceid);

		// ����֧��ǩ��
		String sign = wxReqHandler.createSHA1Sign();
		wxReqHandler.setParameter("app_signature", sign);
		wxReqHandler.setParameter("sign_method", ConstantUtil.SIGN_METHOD);
		String gateUrl = ConstantUtil.GATEURL + access_token;
		wxReqHandler.setGateUrl(gateUrl);

		// ��������
		String accesstoken = wxReqHandler.sendAccessToken();
		if (ConstantUtil.EXPIRE_ERRCODE.equals(accesstoken) || ConstantUtil.FAIL_ERRCODE.equals(accesstoken))
			flag = true;
		return flag;
	}

}
