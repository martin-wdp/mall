<html lang="zh-cn">
<head>
<#assign basePath=request.contextPath>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <meta name="keywords" content="${seo.meteKey}">
    <meta name="description" content="${seo.meteDes}">
<#if (sys.bsetName)??>
    <title>我的收藏-${(sys.bsetName)!''}</title>
    <input type="hidden" id="bsetName" value="${(sys.bsetName)!''}">
    <input type="hidden" id="bsetDesc" value="${(sys.bsetDesc)!''}">
<#else>
    <title>我的收藏-${(seo.mete)!''}</title>
    <input type="hidden" id="bsetName" value="${(seo.mete)!''}">
    <input type="hidden" id="bsetDesc" value="${(sys.bsetDesc)!''}">
</#if>

    <!-- Bootstrap -->
    <link href="${basePath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${basePath}/css/idangerous.swiper.css" rel="stylesheet">
    <link href="${basePath}/css/style.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="${basePath}/js/html5shiv.min.js"></script>
    <script src="${basePath}/js/respond.min.js"></script>
    <![endif]-->

    <style>
        <!--
        .mn_sel {
            display: inline-block;
            zoom: 1;
            *display: inline;
            border: 1px solid #ddd;
            width: 205px;
            height: 25px;
            line-height: 25px;
            padding: 0 5px;
            vertical-align: middle;
            background: url(${basePath}/images/area_arrow.gif) no-repeat 195px center;
        }

        .selCont {
            width: 300px;
            min-height: 50px;
            border: 1px solid #ddd;
            padding: 5px 10px;
            margin-top: -4px;
            display: none;
        }

        .selCont label {
            display: inline-block;
            zoom: 1;
            *display: inline;
            width: 145px;
            margin: 0 10px 10px 0;
            font-size: 10px;
            font-weight: normal;
        }

        .mr5 {
            margin-right: 5px;
        }

        .mr10 {
            margin-right: 10px;
        }

        .vm {
            vertical-align: middle;
        }

        -->
        .sel_txa {
            width: 340px;
            height: 100px;
            padding: 5px;
            border: 1px solid #ddd;
            margin-top: 5px;
        }
    </style>
 <script>if(myObj&&myObj.setHomeFlag){myObj.setHomeFlag('false');}</script> <script>if(myObj&&myObj.setHomeFlag){myObj.setHomeFlag('false');}</script></head>
<body style="background:#EFEFEF;">
<#include "../publicHeader2_ftl.ftl" />

<div class="order_list">
<#if (myfollwList?size!=0)>
    <#list myfollwList as follow>
        <#if follow.good??>
            <ul>
                <li>
                    <div>
                       <a href="${basePath}/item/${follow.goodsId!}.html"> <img src="<#if follow.good.goodsImg??>${follow.good.goodsImg}</#if>"></a>
                        <span><#if follow.good.goodsName??>${follow.good.goodsName}</#if></span>
                        <i><#if vip?? && vip =="1">
                            <#if follow.good.goodsVipPrice??>${follow.good.goodsVipPrice?string('0.00')}</#if>
                            <#else >
                            <#if follow.good.goodsPreferPrice??>${follow.good.goodsPreferPrice?string('0.00')}</#if>
                        </#if>
                        </i>
                        <a href="${basePath}/customer/cancelfollow-${follow.followId}.html"><button>取消收藏</button></a>
                    </div>
                </li>
            </ul>
        </#if>
    </#list>
<#else>
    <div style="width:100%;text-align:center;margin-top:20px;font-size:16px;">
        <img src="${basePath}/images/no-pros.png" style="width:50%;"/>
        <br>
        暂无收藏！
    </div>
</#if>
</div>
<!-- /order_list -->


<div class="foot">
    <p>由${(mobSiteBasic.technicalSupport)!''}提供技术支持</p>
</div>
<!-- /foot -->


<div class="modal fade" role="dialog" id="cancel_order">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">商城消息</h4>
            </div>
            <div class="modal-body">
                <div class="dia_intro no_tc pt30" style="height:170px;/*margin-left:20px;*/">
                    <img class="vm mr10" alt="" src="${basePath}/images/q_mark.png"/>
                    <em>取消收藏原因:</em><br>
                    <textarea class="sel_txa" id="sel_txa" style="width:98%;"></textarea>

                    <p id="titlereason" style="color:red;"></p>
                <#--<div class="input_tip" style="color:red;text-align:right;margin-top: 10px;display:none;"></div>-->
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" onclick="clearmess()">取消</button>
                <button type="button" class="btn btn-primary" onClick="changeUrl();">确定</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>


<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="${basePath}/js/jquery.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${basePath}/js/bootstrap.min.js"></script>
<script src="${basePath}/js/fastclick.min.js"></script>
<script src="${basePath}/js/idangerous.swiper-2.1.min.js"></script>
<script src="${basePath}/js/jquery.keleyi.js"></script>
<script src="${basePath}/js/customer/memberorder.js"></script>
<script src="${basePath}/js/customer/wxforward.js"></script>
<script src="${basePath}/js/publicModel.js"></script>
<script>

</script>
</body>
</html>
