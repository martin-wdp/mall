<#assign basePath=request.contextPath>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <link href="${basePath}/mobile_home_page/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="${basePath}/mobile_home_page/css/style.css" rel="stylesheet"/>
    <link href="${basePath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${basePath}/css/idangerous.swiper.css" rel="stylesheet">
    <link href="${basePath}/css/style.css" rel="stylesheet">
    <script src="${basePath}/js/jquery.js"></script>
    <script src="js/html5shiv.min.js"></script>
    <script src="js/respond.min.js"></script>
    <script>
        //页面加载查询 商家设置的功能路径
        var thirdstoreurl;
        var a = false;
        $(function(){
            $.ajax({
                url:$('#basePath').val()+"/getThirdStoreURL.htm",
                success:function(data){
                    thirdstoreurl = data;
                },
                async: false
            });
            $.ajax({
                url:$('#basePath').val()+"/checkThirdIndex.htm?thirdId="+$('#thirdId').val(),
                success:function(data){
                    a = data.state;
                },
                async: false
            });
            if(a){
                window.location.href=thirdstoreurl+"/thirdstoreindex/"+$('#thirdId').val();
            }

        });
        //没1秒执行一次这个方法
        function remainTime(){
            if(i==0){
                //到时间跳转到首页
                window.location.href=thirdstoreurl;
            }
            $('#endtime').html(i--);
            setTimeout("remainTime()",1000);
        }
        var i = 5;
        $('#endtime').html(i--);
        setTimeout("remainTime()",1000);
    </script>
</head>
<body>
<style>
    <style>
    .curtain_wp p {position:absolute; width:100%; left:0; bottom:10%; text-align:center; color:#fff; font-family:Arial; font-size:16px;}
    .ps-back a{ display: block; background: url(images/arrow_left.png) no-repeat center center; width: 100%; background-size: auto 50%; height: 2.8em;  text-indent: -99999px;}
    .ps-search input{ width: 85%;height: 2.1em;
        background:#fff; border:none;margin-top:0;float:left;}
    .ps-search button{float:right;width:15%;background: url(${basePath}/images/search.png) no-repeat center center; text-indent:1.8em;
        background-size: auto 50%; border:none; cursor: pointer; height: 2.1em; border-left:1px solid #d6d6d6;text-indent: -99999px;}
    .ps-clk a{display: block; background: url(imagesst.png) no-repeat center center; width: 100%; background-size: auto 50%; height: 2.8em; text-indent: -99999px;}
</style>
<style>
    * {margin:0;padding:0;}
    html {font-size: 62.5%;  }
    body {font-family: "Helvetica Neue","Helvetica","tahoma","arial","\5FAE\8F6F\96C5\9ED1","\5b8b\4f53";font-size:1.4rem;}
    .wp {padding:20px;}
    .error-cont {padding:50px 0;text-align:center;}
    .error-cont img {width:50%;}
    .error-cont p {font-size:1.6rem;color:#666;margin-top:20px;}
    .back-link {text-align:center;position:relative;}
    .back-link:before {content:"";border-bottom:1px solid #ddd;width:100%;left:0;top:10px;}
    .back-link a {color:#eb6122;text-decoration:none;font-size:1.6rem;display:inline-block;padding:0 15px;background:#fff;position:relative;z-index:9;}
</style>
<div>
    <input type="hidden" id="thirdId" value="<#if thirdId??>${thirdId}</#if>"/>
    <input type="hidden" id="basePath" value="${basePath}"/>
    <div class="wp">
        <div class="error-cont">
            <img src="${basePath}/images/no-pros.jpg" alt=""/>
            <p>抱歉此商家没有开启商家模板！</p>
        </div>
        <div class="back-link">
            <span>将会在<span style="color:red;font-size: 18px;" id="endtime"></span>秒后跳回店铺首页</span>
        </div>
    </div>
</div>
</body>
</html>