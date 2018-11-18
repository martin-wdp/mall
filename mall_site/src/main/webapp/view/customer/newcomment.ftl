<#include "../include/common.ftl">
<@htmlHead title="商品评价-${topmap.systembase.bsetName}">
<link rel="stylesheet" type="text/css" href="${basePath}/css/pages.css" />
<script type="text/javascript">
    var oStaraLi,oUl,oSpan,oP;
    var i = iScore = iStar = 0;
    function commentstar(obj){
        oStar = document.getElementById(obj);
        aLi = oStar.getElementsByTagName("li");
        oUl = oStar.getElementsByTagName("ul")[0];
        oSpan = oStar.getElementsByTagName("span")[1];
        oP = oStar.getElementsByTagName("p")[0];

        if(obj == 'star1'){
            var aMsg = [
                "很不满意|差得太离谱，与卖家描述的严重不符，非常不满",
                "不满意|部分有破损，与卖家描述的不符，不满意",
                "一般|质量一般，没有卖家描述的那么好",
                "满意|质量不错，与卖家描述的基本一致，还是挺满意的",
                "非常满意|质量非常好，与卖家描述的完全一致，非常满意"
            ]
        }else if(obj=='star2'){
            var aMsg = [
                "很不满意|卖家态度很差，还骂人、说脏话，简直不把顾客当回事",
                "不满意|卖家有点不耐烦，承诺的服务也兑现不了",
                "一般|卖家回复问题很慢，态度一般，谈不上沟通顺畅",
                "满意|卖家服务挺好的，沟通挺顺畅的，总体满意",
                "非常满意|卖家的服务太棒了，考虑非常周到，完全超出期望值"
            ]
        }else{
            var aMsg = [
                "很不满意|再三提醒下，卖家超过一天才发货，耽误我的时间",
                "不满意|卖家发货有点慢的，催了几次终于发货了",
                "一般|卖家发货速度一般，提醒后才发货的",
                "满意|卖家发货还算及时",
                "非常满意|卖家发货速度非常快"
            ]
        }

        for (i = 1; i <= aLi.length; i++)
        {
            aLi[i - 1].index = i;
            //鼠标移过显示分数
            aLi[i - 1].onmouseover = function ()
            {
                oStar = document.getElementById(obj);
                aLi = oStar.getElementsByTagName("li");
                oUl = oStar.getElementsByTagName("ul")[0];
                oSpan = oStar.getElementsByTagName("span")[1];
                oP = oStar.getElementsByTagName("p")[0];
                fnPoint(this.index,aLi);
                //浮动层显示
                oP.style.display = "block";
                //计算浮动层位置
                oP.style.left = oUl.offsetLeft + this.index * this.offsetWidth - 104 + "px";
                //匹配浮动层文字内容
                oP.innerHTML = "<em><b>" + this.index + "</b> 分 " + aMsg[this.index - 1].match(/(.+)\|/)[1] + "</em>" + aMsg[this.index - 1].match(/\|(.+)/)[1]
            };
            //鼠标离开后恢复上次评分
            aLi[i - 1].onmouseout = function ()
            {
                oStar = document.getElementById(obj);
                aLi = oStar.getElementsByTagName("li");
                oUl = oStar.getElementsByTagName("ul")[0];
                oSpan = oStar.getElementsByTagName("span")[1];
                oP = oStar.getElementsByTagName("p")[0];
                fnPoint();
                //关闭浮动层
                oP.style.display = "none"
            };
            //点击后进行评分处理
            aLi[i - 1].onclick = function ()
            {
                oStar = document.getElementById(obj);
                aLi = oStar.getElementsByTagName("li");
                oUl = oStar.getElementsByTagName("ul")[0];
                oSpan = oStar.getElementsByTagName("span")[1];
                oP = oStar.getElementsByTagName("p")[0];
                sinput =  oStar.getElementsByTagName("input")[0];
                iStar = this.index;
                oP.style.display = "none";
                oSpan.innerHTML = "<strong>" + (this.index) + " 分</strong> (" + aMsg[this.index - 1].match(/\|(.+)/)[1] + ")";
                sinput.value=this.index;
            }
        }

    }
    //评分处理
    function fnPoint(iArg)
    {
        //分数赋值
        iScore = iArg || iStar;
        for (i = 0; i < aLi.length; i++) aLi[i].className = i < iScore ? "on" : "";
    }


</script>
</@htmlHead>
<#--<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#assign basePath=request.contextPath>
<base href="${basePath}/">
<title>商品评价-${topmap.systembase.bsetName}</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta name="Keywords" content="${topmap.seo.meteKey}">
<meta name="description" content="${topmap.seo.meteDes}">
<meta name="renderer" content="webkit">
<#if (topmap.systembase.bsetHotline)??>
	<link rel = "Shortcut Icon" href="${(topmap.systembase.bsetHotline)!''}">
<#else>
	<link rel = "Shortcut Icon" href="${basePath}/images/Paistore.ico">
</#if>
<link rel="stylesheet" type="text/css" href="${basePath}/css/pages.css" />
    <script type="text/javascript" src="${basePath}/js/jquery-1.7.2.min.js"></script>
</head>
<script type="text/javascript">
    var oStaraLi,oUl,oSpan,oP;
    var i = iScore = iStar = 0;
    function commentstar(obj){
        oStar = document.getElementById(obj);
        aLi = oStar.getElementsByTagName("li");
        oUl = oStar.getElementsByTagName("ul")[0];
        oSpan = oStar.getElementsByTagName("span")[1];
        oP = oStar.getElementsByTagName("p")[0];

        if(obj == 'star1'){
            var aMsg = [
                "很不满意|差得太离谱，与卖家描述的严重不符，非常不满",
                "不满意|部分有破损，与卖家描述的不符，不满意",
                "一般|质量一般，没有卖家描述的那么好",
                "满意|质量不错，与卖家描述的基本一致，还是挺满意的",
                "非常满意|质量非常好，与卖家描述的完全一致，非常满意"
            ]
        }else if(obj=='star2'){
            var aMsg = [
                "很不满意|卖家态度很差，还骂人、说脏话，简直不把顾客当回事",
                "不满意|卖家有点不耐烦，承诺的服务也兑现不了",
                "一般|卖家回复问题很慢，态度一般，谈不上沟通顺畅",
                "满意|卖家服务挺好的，沟通挺顺畅的，总体满意",
                "非常满意|卖家的服务太棒了，考虑非常周到，完全超出期望值"
            ]
        }else{
            var aMsg = [
                "很不满意|再三提醒下，卖家超过一天才发货，耽误我的时间",
                "不满意|卖家发货有点慢的，催了几次终于发货了",
                "一般|卖家发货速度一般，提醒后才发货的",
                "满意|卖家发货还算及时",
                "非常满意|卖家发货速度非常快"
            ]
        }

        for (i = 1; i <= aLi.length; i++)
        {
            aLi[i - 1].index = i;
            //鼠标移过显示分数
            aLi[i - 1].onmouseover = function ()
            {
                oStar = document.getElementById(obj);
                aLi = oStar.getElementsByTagName("li");
                oUl = oStar.getElementsByTagName("ul")[0];
                oSpan = oStar.getElementsByTagName("span")[1];
                oP = oStar.getElementsByTagName("p")[0];
                fnPoint(this.index,aLi);
                //浮动层显示
                oP.style.display = "block";
                //计算浮动层位置
                oP.style.left = oUl.offsetLeft + this.index * this.offsetWidth - 104 + "px";
                //匹配浮动层文字内容
                oP.innerHTML = "<em><b>" + this.index + "</b> 分 " + aMsg[this.index - 1].match(/(.+)\|/)[1] + "</em>" + aMsg[this.index - 1].match(/\|(.+)/)[1]
            };
            //鼠标离开后恢复上次评分
            aLi[i - 1].onmouseout = function ()
            {
                oStar = document.getElementById(obj);
                aLi = oStar.getElementsByTagName("li");
                oUl = oStar.getElementsByTagName("ul")[0];
                oSpan = oStar.getElementsByTagName("span")[1];
                oP = oStar.getElementsByTagName("p")[0];
                fnPoint();
                //关闭浮动层
                oP.style.display = "none"
            };
            //点击后进行评分处理
            aLi[i - 1].onclick = function ()
            {
                oStar = document.getElementById(obj);
                aLi = oStar.getElementsByTagName("li");
                oUl = oStar.getElementsByTagName("ul")[0];
                oSpan = oStar.getElementsByTagName("span")[1];
                oP = oStar.getElementsByTagName("p")[0];
                sinput =  oStar.getElementsByTagName("input")[0];
                iStar = this.index;
                oP.style.display = "none";
                oSpan.innerHTML = "<strong>" + (this.index) + " 分</strong> (" + aMsg[this.index - 1].match(/\|(.+)/)[1] + ")";
                sinput.value=this.index;
            }
        }

    }
    //评分处理
    function fnPoint(iArg)
    {
        //分数赋值
        iScore = iArg || iStar;
        for (i = 0; i < aLi.length; i++) aLi[i].className = i < iScore ? "on" : "";
    }


</script>-->
<@htmlBody>
<#--一引入头部 <#include "/index/topnew.ftl" />  -->
<#if (topmap.temp)??>
    <#if (topmap.temp.tempId==8)>
        <#include "../index/newtop3.ftl">
    <#elseif (topmap.temp.tempId==9)>
        <#include "../index/newtop4.ftl">
    <#elseif (topmap.temp.tempId==10)>
        <#include "../index/newtop7.ftl">
    <#elseif (topmap.temp.tempId==11)>
        <#include "../index/newtop6.ftl">
    <#elseif (topmap.temp.tempId==12)>
        <#include "../index/newtop7.ftl">
    <#elseif (topmap.temp.tempId==13)>
        <#include "../index/newtop8.ftl">
    <#elseif (topmap.temp.tempId==14)>
        <#include "../index/newtop9.ftl">
    <#elseif (topmap.temp.tempId==15)>
        <#include "../index/newtop8.ftl">
    <#elseif (topmap.temp.tempId==16)>
        <#include "../index/newtop11.ftl">
    <#elseif (topmap.temp.tempId==17)>
        <#include "../index/newtop12.ftl">
    <#elseif (topmap.temp.tempId==18)>
        <#include "../index/newtop13.ftl">
    <#elseif (topmap.temp.tempId==19)>
        <#include "../index/newtop14.ftl">
    <#elseif (topmap.temp.tempId==20)>
        <#include "../index/newtop15.ftl">
    <#else>
        <#include "../index/newtop.ftl"/>
    </#if>
</#if>
<#include "newtop.ftl"/>
<input type="hidden" value="${token!''}" id="hi_token" />

<div style="background: #f5f5f5;">
    <div class="container clearfix pt20 pb10">
        <!--new_member_left-->
    <#include "newleftmenu.ftl"/>
        <div class="new_member-right">
            <div class="air-info-judge">
                <div class="n-title">商品评价</div>
            <#--<div class="simple mt20 mb20 clearfix">-->
            <#--<div class="fl">-->
            <#--<select>-->
            <#--<option>全部商品</option>-->
            <#--</select>-->
            <#--</div>-->
            <#--</div>-->
                <div class="content">
                    <div class="layout">
                        <table class="air-judge-table mt10">
                            <thead>
                            <tr>
                                <th width="480">商品信息</th>
                                <th>购买时间</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                                <#if pb.list?size!=0>
                                    <#list pb.list as good>
                                    <input type="hidden" id="goodInfoId${good.orderGoodsId}" name="commentScore" value="${(good.goodsInfoId)!''}" />
                                    <tr class="air-judge-bd">
                                        <td colspan="3">
                                            <ul class="clearfix">
                                                <li class="baobei">
                                                    <a class="pic" target="_blank" href="${basePath}/item/${(good.goodsInfoId)!''}.html"><img alt="${(good.goodsInfoName)!'已下架'}" src="${(good.goodsProductVo.goodsInfoImgId)!''}" width="100" height="100" /></a>
                                                    <div class="desc ml20">
                                                        <a target="_blank" href="${basePath}/item/${(good.goodsInfoId)!''}.html">
                                                                    <#if good.goodsInfoName??>
                                                                      ${good.goodsInfoName}
                                                                    <#else>
                                                                     ${(good.goodsInfoName)!'已下架'}
                                                                    </#if>
                                                        </a>
                                                        <p class="col9">
                                                            <#list good.goodsProductVo.specVo as  specVo>
                                                                <#if specVo.spec.specName??&&specVo.spec.specName == '.'>
                                                                <#else>
                                                                    ${specVo.spec.specName}:<#if specVo.specValueRemark??>${specVo.specValueRemark}<#else>${specVo.goodsSpecDetail.specDetailName!''} </#if>&nbsp;
                                                                </#if>
                                                            </#list>

                                                        </p>
                                                    </div>
                                                </li>
                                                <li class="buy-time">
                                                    <p><#if good.buyTime??>${good.buyTime?string("yyyy-MM-dd HH:mm:ss")}</#if></p>
                                                </li>
                                                <li class="bask-order">
                                                    <#if good.evaluateFlag == '0'>
                                                        <a href="javascript:" onclick="addShare(${good.orderGoodsId!''},${(good.goodsInfoId)!''})" class="bask">发表评价</a>
                                                    <#elseif good.shareFlag =='0'&& good.evaluateFlag == '1'>
                                                        <a href="javascript:" class="bask" onclick="showShare(${good.orderGoodsId!''})">发表晒单</a>
                                                    <#else>
                                                        <a href="javascript:" class="bask" onclick="showShare(${good.orderGoodsId!''})">查看晒单</a>
                                                    </#if>

                                                </li>
                                            </ul>
                                            <#if good.evaluateFlag == '0'>
                                                <div class="air-judgeBox"><b class="topArrow"><i></i></b>
                                                        <input type="hidden" id="title${good.orderGoodsId}" value="${good.goodsInfoName}"/>
                                                        <div class="item clearfix">
                                                            <div class="label">
                                                               <span style="color:red;">*</span> <label for="">评分：</label>
                                                            </div>
                                                            <div class="put" id="star${good.orderGoodsId}" >
                                                                <input type="hidden" id="commentScore${good.orderGoodsId}" name="commentScore" value="0" />
                                                                <span class="commstar pik-commstar clearfix">
                                                                     <ul>
                                                                         <li class="star1" onmouseover="commentstar('star${good.orderGoodsId}')" style="width:15px;"><a href="javascript:">1</a></li>
                                                                         <li  class="star2"onmouseover="commentstar('star${good.orderGoodsId}')"  style="width:15px;"><a href="javascript:">2</a></li>
                                                                         <li class="star3"onmouseover="commentstar('star${good.orderGoodsId}')"  style="width:15px;"><a href="javascript:">3</a></li>
                                                                         <li class="star4"onmouseover="commentstar('star${good.orderGoodsId}')"  style="width:15px;"><a href="javascript:">4</a></li>
                                                                         <li class="star5"onmouseover="commentstar('star${good.orderGoodsId}')" style="width:15px;"><a href="javascript:">5</a></li>
                                                                     </ul>
                                                                </span>
                                                                <span></span>
                                                                <p></p>
                                                            </div>
                                                        </div>
                                                        <div class="item clearfix">
                                                            <div class="label">
                                                                <label for="">标签：</label>
                                                            </div>
                                                            <div class="put">
                                                                <ul id="g_tag${good.orderGoodsId}" class="tags pik-tags clearfix">
                                                                    <li ></li>
                                                                </ul>
                                                            </div>
                                                        </div>
                                                        <div class="item clearfix">
                                                            <div class="label">
                                                                <span style="color:red;">*</span> <label for="">心得：</label>
                                                            </div>
                                                            <div class="put">
                                                                <textarea id="complaincon${(good.orderGoodsId)!''}" name="commentContent"></textarea>
                                                                <label id="commTip${(good.orderGoodsId)!''}">&nbsp;</label>
                                                            </div>
                                                        </div>
                                                        <div class="item clearfix">
                                                            <div class="label">
                                                                <label for="">晒单：</label>
                                                            </div>
                                                            <iframe name="hidden_frame" style="display:none"></iframe>
                                                            <div class="put">
                                                                <form id="upload_form${good.orderGoodsId}" name="upload_form" method="post" enctype="multipart/form-data" action="${basePath}/share/upload.htm?orderGoodsId=${good.orderGoodsId}&CSRFToken=${token}" target="hidden_frame">
                                                                <ul class="imgList fl" id="share_imgs${good.orderGoodsId}">
                                                                </ul>
                                                                    <div>
                                                                        <input class="addImg up_photo"style="width: 80px;height: 80px;border: 1px solid #efefef;margin-right: 10px;padding: 0;display: inline-block;" type="button" value="添加图片"/>
                                                                        <input type="file" class="upload_file" id="imageFile" order_goods_id="${good.orderGoodsId}" name="shareFile" style="width:80px;margin-left:-80px;filter:alpha(opacity=0);-moz-opacity:0.5;-khtml-opacity: 0;opacity: 0; ">

                                                                        <a class="order_btn delete_btn" order_goods_id="${good.orderGoodsId}" href="javascript:void(0)"><span>删除</span></a><span class="ml5">选中的图片</span>
                                                                    </div>
                                                                <p class="imgNum"><sapn class="imgNum${good.orderGoodsId}">1</sapn>/10</p>
                                                               </form>
                                                            </div>
                                                        </div>
                                                        <div class="item clearfix">
                                                            <div class="label">
                                                                <label for="">&nbsp;</label>
                                                            </div>
                                                            <div class="put">
                                                                <button class="subComm" type="button" onclick="saveShare(${good.orderGoodsId},1)">评论并继续</button>
                                                                <#--<input class="niMin ml10" type="checkbox"/>-->
                                                                <#--<label>匿名评价</label>-->
                                                            </div>
                                                        </div>
                                                </div>
                                            <#elseif good.evaluateFlag == '1' && good.shareFlag=='0'>
                                                <div class="air-judgeBox"><b class="topArrow"><i></i></b>
                                                    <div class="item clearfix">
                                                        <div class="label">
                                                            <span style="color:red;">*</span> <label for="">评分：</label>
                                                        </div>
                                                        <div class="put" id="star${good.orderGoodsId}">
                                                              <span class="commstar pik-commstar clearfix">
                                                                <ul>
                                                                    <li class="star1"  style="width:15px;"><a href="javascript:">1</a></li>
                                                                    <li  class="star2"  style="width:15px;"><a href="javascript:">2</a></li>
                                                                    <li class="star3"  style="width:15px;"><a href="javascript:">3</a></li>
                                                                    <li class="star4"  style="width:15px;"><a href="javascript:">4</a></li>
                                                                    <li class="star5"style="width:15px;"><a href="javascript:">5</a></li>
                                                                </ul>
                                                               </span>
                                                        </div>
                                                    </div>
                                                    <div class="item clearfix">
                                                        <div class="label" >
                                                            <label for="">标签：</label>
                                                        </div>
                                                        <div class="put">
                                                            <ul class="tags clearfix" id="tag${good.orderGoodsId}">
                                                            </ul>
                                                        </div>
                                                    </div>
                                                    <div class="item clearfix">
                                                        <div class="label">
                                                            <span style="color:red;">*</span>  <label for="">心得：</label>
                                                        </div>
                                                        <div class="put">
                                                            <p id="content${good.orderGoodsId}" >声音有点大，恒温效果不错，总体满意</p>
                                                        </div>
                                                    </div>
                                                    <div class="item clearfix">
                                                        <div class="label">
                                                            <label for="">晒单：</label>
                                                        </div>
                                                        <iframe name="hidden_frame" style="display:none"></iframe>
                                                        <div class="put">
                                                            <input type="hidden" id="title${good.orderGoodsId}" value="${good.goodsInfoName}"/>
                                                            <form id="upload_form${good.orderGoodsId}" name="upload_form" method="post" enctype="multipart/form-data" action="${basePath}/share/upload.htm?orderGoodsId=${good.orderGoodsId}&CSRFToken=${token}" target="hidden_frame">
                                                                <ul class="imgList fl" id="share_imgs${good.orderGoodsId}">
                                                                </ul>
                                                                <div>

                                                                    <input class="addImg up_photo" style="width: 80px;height: 80px;border: 1px solid #efefef;margin-right: 10px;padding: 0;display: inline-block;"type="button" value="添加图片"/>
                                                                    <input type="file" class="upload_file" id="imageFile" order_goods_id="${good.orderGoodsId}" name="shareFile" style="width:80px;margin-left:-80px;filter:alpha(opacity=0);-moz-opacity:0.5;-khtml-opacity: 0;opacity: 0; ">

                                                                    <a class="order_btn delete_btn" order_goods_id="${good.orderGoodsId}" href="javascript:void(0)"><span>删除</span></a><span class="ml5">选中的图片</span>
                                                                </div>
                                                                <p class="imgNum"><sapn class="imgNum${good.orderGoodsId}">1</sapn>/10</p>
                                                            </form>
                                                        </div>
                                                    </div>
                                                    <div class="item clearfix">
                                                        <div class="label">
                                                            <label for="">&nbsp;</label>
                                                        </div>
                                                        <div class="put">
                                                            <button class="subComm" type="button" onclick="saveShare(${good.orderGoodsId},2)">晒单</button>
                                                        <#--<input class="niMin ml10" type="checkbox"/>-->
                                                        <#--<label>匿名评价</label>-->
                                                        </div>
                                                    </div>
                                                    </div>
                                                </div>
                                            <#elseif good.evaluateFlag == '1' && good.shareFlag=='1'>
                                                <div class="air-judgeBox"><b class="topArrow"><i></i></b>
                                                    <div class="item clearfix">
                                                        <div class="label">
                                                            <span style="color:red;">*</span>  <label for="">评分：</label>
                                                        </div>
                                                        <div class="put" id="star${good.orderGoodsId}">
                                                              <span class="commstar pik-commstar clearfix">
                                                                <ul>
                                                                    <li class="star1"  style="width:15px;"><a href="javascript:">1</a></li>
                                                                    <li  class="star2"  style="width:15px;"><a href="javascript:">2</a></li>
                                                                    <li class="star3"  style="width:15px;"><a href="javascript:">3</a></li>
                                                                    <li class="star4"  style="width:15px;"><a href="javascript:">4</a></li>
                                                                    <li class="star5"style="width:15px;"><a href="javascript:">5</a></li>
                                                                </ul>
                                                               </span>
                                                        </div>
                                                    </div>
                                                    <div class="item clearfix">
                                                        <div class="label" >
                                                            <label for="">标签：</label>
                                                        </div>
                                                        <div class="put">
                                                            <ul class="tags clearfix" id="tag${good.orderGoodsId}">
                                                            </ul>
                                                        </div>
                                                    </div>
                                                    <div class="item clearfix">
                                                        <div class="label">
                                                            <span style="color:red;">*</span>  <label for="">心得：</label>
                                                        </div>
                                                        <div class="put">
                                                            <p id="content${good.orderGoodsId}" >声音有点大，恒温效果不错，总体满意</p>
                                                        </div>
                                                    </div>
                                                    <div class="item clearfix">
                                                        <div class="label">
                                                            <label for="">晒单：</label>
                                                        </div>
                                                        <div class="put">
                                                            <ul class="imgList" id="imgList${good.orderGoodsId}">
                                                                <li><img src="${basePath}/images/images_l4.jpg" alt=""/></li>
                                                                <li><img src="${basePath}/images/images_l4.jpg" alt=""/></li>
                                                                <li><img src="${basePath}/images/images_l4.jpg" alt=""/></li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                </div>
                                            </#if>
                                        </td>
                                    </tr>
                                    </#list>
                                <#else>
                                <tr>
                                    <td colspan="3" style="text-align:center; height:60px;font-size: 18px;">暂无商品评价！</td>
                                </tr>
                                </#if>
                            </tbody>
                        </table>
                        <div class="paging_area">
                        <#if (pb.list?size!=0)>
                                     <#-- 分页 -->
                                     <#import "../pagination/pageBean.ftl" as page>
                                     <@page.pagination pb />
                                </#if>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="mask"></div>
<div class="member-dialog dia2">
    <div class="member-dialog-body">
        <div class="title"><a href="javascript:" onclick="cls()">×</a>提示</div>
        <div class="tc">
            <div class="que-delete clearfix">
                <img src="${basePath}/images/images_l6.png"/>
                <div class="fl tl">
                    <p class="f16 red" id="errorT2">请先选择要删除的图片！</p>
                    <div class="m-btn mt20">
                        <a  id="go_pay_01" href="javascript:cls();">确定</a>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
<div class="member-dialog dia3">
    <div class="member-dialog-body">
        <div class="title"><a href="javascript:" onclick="cls()">×</a>提示</div>
        <div class="tc">
            <div class="que-delete clearfix">
                <img src="${basePath}/images/images_l6.png"/>
                <div class="fl tl">
                    <p class="f16 red" id="errorT">请先选择要删除的图片！</p>
                    <div class="m-btn mt20">
                        <a  id="go_pay_01" href="javascript:cls();">确定</a>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>

<div class="member-dialog dia4">
    <div class="member-dialog-body">
        <div class="title"><a href="javascript:" onclick="cls()">×</a>提示</div>
        <div class="tc">
            <div class="que-delete clearfix">
                <img src="${basePath}/images/images_l6.png"/>
                <div class="fl tl">
                    <p class="f16 red" id="titleerr">头像上传成功！</p>

                    <div class="m-btn mt20">
                        <a id="go_pay_00" href="javascript:cls();">确定</a>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
<#--引入底部 <#include "/index/bottom.ftl" /> -->
<#if (topmap.temp)??>
    <#if (topmap.temp.tempId==1)>
        <#include "../index/bottom.ftl">
    <#else>
        <#include "../index/newbottom.ftl" />
    </#if>
</#if>
<script type="text/javascript" src="${basePath}/js/default.js"></script>
<script type="text/javascript" src="${basePath}/js/jcarousellite_1.0.1.js"></script>
<script type="text/javascript" src="${basePath}/js/tab-switch.js"></script>
<script type="text/javascript" src="${basePath}/js/customer/member.js"></script>
<script type="text/javascript" src="${basePath}/js/customer/customer_share.js"></script>
<script type="text/javascript" src="${basePath}/js/newapp.js"></script>
<script type="text/javascript" src="${basePath}/js/customer/findcode.js"></script>
<script type="text/javascript" src="${basePath}/js/jsOperation.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
        $(function(){
            $('.air-judge-table .bask').click(function(){
                $('.air-judgeBox').hide();
                var $judgeBox=$(this).parents('.air-judge-bd').find('.air-judgeBox');
                if($judgeBox.css('display')=='none'){
                    $judgeBox.show(200);
                }else{
                    $judgeBox.hide(200);
                }
            });
        });
        $('.item_title').each(function(){
            $(this).click(function(){
                $(this).next().toggle('fast',function(){
                    if($(this).is(':visible')){
                        $(this).prev().removeClass('up');
                        $(this).prev().addClass('down');
                    }
                    else{
                        $(this).prev().removeClass('down');
                        $(this).prev().addClass('up');
                    }
                });
            });
        });
        $(".new_member_left div:eq(4) ul li:eq(0)").addClass("cur");
        $(".pro_sort").addClass("pro_sort_close");
    });

    //发表评价晒单
    function addShare(orderGoodsId,goodInfoId){
        var tUrl = "${basePath}/loadgoodstag.htm?CSRFToken="+$("#hi_token").val()+"&goodsId="+goodInfoId;
        $.ajax({
            type : 'post',
            url : tUrl,
            async : false,
            success : function(data) {
                var s = "";
                for(var i = 0;i< data.length;i++){
                    s+='<li onclick="selectedTag(this)" title="'+data[i].goodsTag.tagName+'">'+data[i].goodsTag.tagName+'</li>';
                }
                $("#g_tag"+orderGoodsId).html('');
                if(s.trim().length == 0 ){
                    $("#g_tag"+orderGoodsId).append("&nbsp;");
                }else{
                    $("#g_tag"+orderGoodsId).append(s);
                }

            }
        });
    }
    //标签选中
    function selectedTag(tag){
    $(tag).siblings().removeClass("tags1");
        $(tag).addClass("tags1");
    }
//查看评论晒单
    function showShare(orderGoodsId){
        $.ajax({
            type : 'post',
            url : "${basePath}/findCommentShare.htm?orderGoodsId="+orderGoodsId+"&CSRFToken="+$("#hi_token").val(),
            async : false,
            success : function(data) {
                //设置评分
               var score= $("#star"+orderGoodsId).find("li");
                for(var i=0;i<score.length;i++){
                    $(score[i]).removeClass("on");
                    if(i<data.commentScore){
                        $(score[i]).addClass("on");
                    }
                }
                //设置评价标签
                $("#tag"+orderGoodsId).find("li").remove();
                if(data.commentTag!=null){
                    $("#tag"+orderGoodsId).append("<li>"+data.commentTag+"</li>");
                }
                //设置评价内容
                $("#content"+orderGoodsId).html(data.commentContent);
                //晒单图片
                var imgs=data.imageList;
                if(imgs !=null &&imgs.length>0){
                    var imgStr="";
                    for(var i=0;i<imgs.length;i++){
                        imgStr=imgStr+"<li><img src='"+imgs[i].imageName+"' alt=''/></li>"
                    }
                    $("#imgList"+orderGoodsId).find("li").remove();
                    $("#imgList"+orderGoodsId).append(imgStr);
                }

            }
        });
    }
</script>
</@htmlBody>