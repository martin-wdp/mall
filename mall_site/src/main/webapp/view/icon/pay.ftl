<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<title>农行测试</title>
    <title>农行网上支付平台-商户接口范例-支付请求</title>
</head>
<body bgcolor='#FFFFFF' text='#000000' link='#0000FF' vlink='#0000FF' alink='#FF0000'>
    <form name="form1" action='doiconpay.htm' method="post">
        <table>
            <tr>
                <td>
                    OrderNo
                </td>
                <td>
                    <input name='OrderNo' value='ON200306300001'></td>
            </tr>
            <tr>
                <td>
                    ExpiredDate
                </td>
                <td>
                    <input name='ExpiredDate' value='30'></td>
            </tr>
            <tr>
                <td>
                    OrderDesc
                </td>
                <td>
                    <input name='OrderDesc' value='Game Card Order'></td>
            </tr>
            <tr>
                <td>
                    OrderDate
                </td>
                <td>
                    <input name='OrderDate' value='2003/11/12'>（YYYY/MM/DD）</td>
            </tr>
            <tr>
                <td>
                    OrderTime
                </td>
                <td>
                    <input name='OrderTime' value='23:55:30'>（HH:MM:SS）</td>
            </tr>
            <tr>
                <td>
                    OrderAmount
                </td>
                <td>
                    <input name='OrderAmount' value='280'></td>
            </tr>
            <tr>
                <td>
                    OrderURL
                </td>
                <td>
                    <input name='OrderURL' value='http://127.0.0.1/Merchant/MerchantQueryOrder.jsp?ON=ON200306300001&QueryType=1'></td>
            </tr>
            <tr>
                <td>
                    BuyIP
                </td>
                <td>
                    <input name='BuyIP' value='172.30.7.75'></td>
            </tr>
            <tr>
                <td>
                    ProductType
                </td>
                <td>
                    <input name='ProductType' value='1'></td>
            </tr>
            <tr>
                <td>
                    PaymentType
                </td>
                <td>
                    <input name='PaymentType' value='1'>1：农行卡支付 2：国际卡支付 3：农行贷记卡支付 5:基于第三方的跨行支付 A:支付方式合并</td>
            </tr>
            <tr>
                <td>
                    PaymentLinkType
                </td>
                <td>
                    <input name='PaymentLinkType' value='1'>1：internet网络接入 2：手机网络接入 3:数字电视网络接入 4:智能客户端</td>
            </tr>
            <tr>
                <td>
                    NotifyType
                </td>
                <td>
                    <input name='NotifyType' value='0'>0：URL页面通知 1：服务器通知</td>
            </tr>
            <tr>
                <td>
                    ResultNotifyURL</td>
                <td>
                    <input name='ResultNotifyURL' value='http://127.0.0.1/Merchant/MerchantResult.jsp'></td>
            </tr>
            <tr>
                <td>
                    MerchantRemarks</td>
                <td>
                    <input name='MerchantRemarks' value='Hi!'></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit">
        </table>
    </form>
    <center>
        
</body>
</html>