<!DOCTYPE HTML>
<html>
<head>
<#assign basePath=request.contextPath>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width,initial-scale=0.9,user-scalable=no"/>
<meta name="MobileOptimized" content="320">
<meta name="format-detection" content="telephone=no">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<link rel="stylesheet" href="${basePath}/css/evaluateList.css">
    <link rel="stylesheet" type="text/css" href="${basePath}/css/top.css">
    <title>评论列表</title>
 <script>if(myObj&&myObj.setHomeFlag){myObj.setHomeFlag('false');}</script> <script>if(myObj&&myObj.setHomeFlag){myObj.setHomeFlag('false');}</script></head>
<body>

<#include "../publicHeader2_ftl.ftl" />
<div class="navBox">
	<nav>
		<ul>
			<li id="allCommon">全部(<small>${commDetail.sumNumber!}</small>)</li><li id="goodCommon">好评(<small>${commDetail.goodSumNumber!}</small>)</li><li id="midCommon">中评
            (<small>${commDetail.middleSumNumber!}</small>)</li><li id="badCommon">差评(<small>${commDetail.badSumNumber!}</small>)</li><li id="SDlist">晒单(<small>${commDetail.SDSumNumber}</small>)</li>
		</ul>
	</nav>
	<div class="evalMes">
		<p><em><#if (commDetail.sumNumber>0)>${(((commDetail.goodSumNumber?number)/(commDetail.sumNumber?number))*100)?string("0")}
        <#else >0</#if>%</em><br>商品好评率</p>
		<ul>
			<li>性价比高(<em>${commDetail.badSumNumber!}</em>)</li><li>送货快(<em>${commDetail.SHKSumNumber!}</em>)</li>
            <li>整体较好(<em>${commDetail.ZTHSumNumber!}</em>)</li><li>服务好(<em>${commDetail.FWHSumNumber!}</em>)</li>
		</ul>
	</div>
</div>

<div class="commentList">
	<h3>评论</h3>
	<ul>
    <#if (commDetail.commList?? && commDetail.commList?size>0)>
        <#list commDetail.commList as list  >
                <li >
                    <div class="myMes" score="${list.commentScore?string("0")}">
                        <span><img src="${list.customerImg!}"></span>
                        <strong style="font-weight:300;">${list.customerNickname!}</strong><br>
                        <em style="font-size:12px;">${list.publishTime?string("yyyy年MM月dd日 HH时mm分")}</em>
                        <ol><li></li><li></li><li></li><li></li><li></li></ol>
                    </div>
                    <div class="talk">
                        <p style="word-wrap:break-word;">${list.commentContent!}</p>
                        <#if (list.imageList?? && list.imageList?size>0)>
                            <#list list.imageList as imga>
                                <img src="${imga.imageName}">
                            </#list></#if>
                    </div>

                </li>
        </#list>

    <#else>
        <span style="margin-left:20px;">暂无评论!</span>
    </#if>
	</ul>
</div>

</body>

<script src="${basePath}/js/customer/jquery-1.11.3.min.js"></script>
<script>
    $("#allCommon").click(function(){
        window.location.href = "${basePath}/toEvaluateList.htm?goodsId=${commDetail.goodsId}";
    });
    $("#goodCommon").click(function(){
        window.location.href = "${basePath}/toEvaluateList.htm?goodsId=${commDetail.goodsId}&type=0";
    });
    $("#midCommon").click(function(){
        window.location.href = "${basePath}/toEvaluateList.htm?goodsId=${commDetail.goodsId}&type=1";
    });
    $("#badCommon").click(function(){
        window.location.href = "${basePath}/toEvaluateList.htm?goodsId=${commDetail.goodsId}&type=2";
    });
    $("#SDlist").click(function(){
        window.location.href = "${basePath}/toEvaluateList.htm?goodsId=${commDetail.goodsId}&type=3&share=1";
    });
    if('${commDetail.commentType!''}'=="" &&'${commDetail.share!''}'==""){
        $("nav li").css({"color":"#000","border-bottom":"none"});
        $("#allCommon").css({"color":"#F6AB00","border-bottom":"2px solid #F6AB00"});
    }
    if('${commDetail.commentType!''}'=="0"){
        $("nav li").css({"color":"#000","border-bottom":"none"});
        $("#goodCommon").css({"color":"#F6AB00","border-bottom":"2px solid #F6AB00"});
    }
    if('${commDetail.commentType!''}'=="1"){
        $("nav li").css({"color":"#000","border-bottom":"none"});
        $("#midCommon").css({"color":"#F6AB00","border-bottom":"2px solid #F6AB00"});
    }
    if('${commDetail.commentType!''}'=="2"){
        $("nav li").css({"color":"#000","border-bottom":"none"});
        $("#badCommon").css({"color":"#F6AB00","border-bottom":"2px solid #F6AB00"});
    }
    if('${commDetail.commentType!''}'=="3"&&'${commDetail.share!''}'=="1"){
        $("nav li").css({"color":"#000","border-bottom":"none"});
        $("#SDlist").css({"color":"#F6AB00","border-bottom":"2px solid #F6AB00"});
    }

    $(".myMes").each(function(){
        var score = $(this).attr("score");
        for(i=1;i<=score;i++){
            $($(this).find("ol li")[i-1]).css("background-image","url(${basePath}/images/qp_xqpl.png)");
        }
        //n++;
    });
</script>

</html>