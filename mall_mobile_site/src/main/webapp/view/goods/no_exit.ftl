<!doctype html>
<#assign basePath=request.contextPath>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>已下架</title>
    <style>
        * {margin:0;padding:0;}
        html {font-size: 62.5%;  }
        body {font-family: "Helvetica Neue","Helvetica","tahoma","arial","\5FAE\8F6F\96C5\9ED1","\5b8b\4f53";font-size:1.4rem;}
        .wp {padding:20px;}
        .error-cont {padding:50px 0;text-align:center;}
        .error-cont img {width:50%;}
        .error-cont p {font-size:1.6rem;color:#666;margin-top:20px;}
        .back-link {text-align:center;position:relative;}
        .back-link:before {content:"";border-bottom:1px solid #ddd;position:absolute;width:100%;left:0;top:10px;}
        .back-link a {color:#eb6122;text-decoration:none;font-size:1.6rem;display:inline-block;padding:0 15px;background:#fff;position:relative;z-index:9;}
        .footer {height:60px;line-height:60px;background:#eee;text-align:center;position:absolute;bottom:0;left:0;width:100%;color:#666;}
    </style>
</head>
<body>
<div class="wp">
    <div class="error-cont">
        <img src="${basePath}/images/no-pros.jpg" alt=""/>
        <p>抱歉商品已下架或过期不存在</p>
    </div>
    <div class="back-link">
        <a href="${basePath}/main.html">返回首页</a>
    </div>
</div>
<div class="footer">
    <p>由${(mobSiteBasic.technicalSupport)!''}提供技术支持</p>
</div>
</body>
</html>