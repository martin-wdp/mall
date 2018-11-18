<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>系统提示</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta name="description" content="${topmap.systembase.bsetName}">
<#assign aa=topmap.systembase.bsetDesc >
<#if (aa?index_of('>',0) != -1)>
    <#assign bb=aa?substring(aa?index_of('>',0)+1,aa?index_of('<',1)) >
<#else>
    <#assign bb=aa >
</#if>
    <meta name="Keywords" content="${bb}">
<#assign basePath=request.contextPath>
<#if (topmap.systembase.bsetHotline)??>
    <link rel = "Shortcut Icon" href="${(topmap.systembase.bsetHotline)!''}">
<#else>
    <link rel = "Shortcut Icon" href="${basePath}/images/Paistore.ico">
</#if>
    <link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css" />
    <link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
    <style>
        .container{width: 1200px; margin: 0px auto;}
        .no_exit{ width: 550px; margin:0px auto; background: url(../../images/bgb.png) no-repeat left top; padding-left: 90px; font-family: "微软雅黑"; }
        .no_exit .title{ font-size: 18px;color: #333;}
        .else{ margin-top:30px; }
        .else p{ color: #999999;}
        .else ul li{color: #666666; line-height: 22px;}
    </style>
</head>

<body>
<#include "../index/newtop7.ftl">
<div class="head2">
    <a href="${topmap.systembase.bsetAddress}"><img id="logo_pic" alt="" src="${topmap.systembase.bsetLogo}" /></a><h1>系统提示</h1>
</div>
<div class="container pt50" id="excepContainer">
    <div class="no_exit">
        <p class="title">很抱歉，您查看的宝贝不存在，可能已下架或者被转移。</p>
        <div class="else">
            <!--<p>您可以：</p>-->
            <ul class="mt30">
                <li>1. 该宝贝可能已经下架，您可以联系掌柜找找宝贝。</li>
                <li>2. 在顶部搜索框重新输入关键词搜索</li>
                <li>3. 为你推荐一些你可能喜欢的内容，将在<span id="time">5</span>秒后自动跳转<a href="${topmap.systembase.bsetAddress}">首页</a></li>
            </ul>
        </div>
    </div>
</div>
<script type="text/javascript" src="${basePath}/js/jquery-1.7.2.min.js"></script>
<#--引入底部 <#include "/index/bottom.ftl" /> -->
<#if (topmap.temp)??>
    <#if (topmap.temp.tempId==1)>
        <#include "../index/bottom.ftl">
    <#else>
        <#include "../index/newbottom.ftl" />
    </#if>
</#if>
<script>

    setExcepContainerMinHeight();
    setTimeout(countDown, 1000);

    function countDown(){
        var time = $("#time").text();
        $("#time").text(time - 1);
        if (time == 1) {
            location.href='${topmap.systembase.bsetAddress}';
        } else {
            setTimeout(countDown, 1000);
        }
    }
    /*$.ajax({
        url: 'loadlogo.htm',
        success: function(data){
            $("#logo_pic").prop("src",data.logo.bsetLogo);
        }
    });*/

    /* *
     * 计算商品未找到的最小高度，避免网站底部出现空白区域
     * 2015.11.04 wuyanbo add
     * */
    function setExcepContainerMinHeight(){
        var bodyHeight = $(window).height();//当前屏幕的可视区域高度
        var topHeight = 120;
        var bottomHeight = 450;
        var excepHeight = (bodyHeight - topHeight- bottomHeight - 20);
        if(excepHeight <= 350){
            excepHeight = 350;
        }
        $("div[id='excepContainer']").css({"min-height":excepHeight+"px"});
    }
</script>
</body>
</html>
