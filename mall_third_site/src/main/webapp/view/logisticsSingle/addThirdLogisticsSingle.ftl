<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增物流单</title>
<#assign basePath=request.contextPath>
<link rel="stylesheet" href="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/css/bootstrap.min.css"/>
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/css/bootstrap-datepicker.min.css" >
<link href="${basePath}/css/material.css" rel="stylesheet">
<link href="${basePath}/css/ripples.css" rel="stylesheet">
<link rel="stylesheet" href="${basePath}/css/style.css"/>
<script type="text/javascript" src="http://cdn.staticfile.org/jquery/1.11.1/jquery.min.js"></script>
<script type="text/javascript" src="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery-ui/jquery-ui-1.10.3.custom.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery-1.9.1.js"></script>
<script src="${basePath}/js/ripples.min.js"></script>
<script src="${basePath}/js/material.min.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery.pep.js"></script>
<script type="text/javascript" src="${basePath}/js/app.js"></script>
<script type="text/javascript" src="${basePath}/js/third.js"></script>
<script type="text/javascript" src="${basePath}/js/sell/express.js"></script>
<script type="text/javascript" src="${basePath}/js/system/system.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery.slimscroll.min.js"></script>
<script type="text/javascript" src="${basePath}/js/common.js"></script>
<script src="${basePath}/js/drag.jquery.js"></script>
<script type="text/javascript" src="${basePath}/js/artDialog4.1.7/artDialog.source.js?skin=default"></script>
<script type="text/javascript" src="${basePath}/js/artDialog4.1.7/plugins/iframeTools.js"></script>



<style type="text/css">
  .express_board{position:relative;margin:10px;padding:20px;}
  .express_board .express_img{width:770px;height:30px;position:absolute;left:0;top:20px;}
  .express_container{position:relative;background:no-repeat left top 100% auto;}
  .express_form{display:inline-block;margin-right:50px;}
  .express_form{*display:inline;}
  .express_form select{margin-top:6px;}
  .express_label{display:block;height:25px;line-height:25px;padding:0 10px;border:1px solid #999;background:#fff;}
  .express_label .close_btn{display:none;width:15px;height:15px;line-height:15px;text-align:center;position:absolute;right:-8px;top:-8px;background:url(images/express_close_btn.png) no-repeat;cursor:pointer;}
  .express_label:hover .close_btn{display:block;}
  .express_label .resize_btn{display:block;width:10px;height:10px;background:#ccc;position:absolute;right:0;bottom:0;}
  #shoow{}
  body{padding:0px;}
</style>

<script type="text/javascript">
    $(function(){
    	$.material.init();
    
        $('.s_dia').hide();
        //上传图片
        $("#shoow").click(function(){
            art.dialog.open('queryImageManageByChoose.htm?size=10000', {
                lock: true,
                width: '800px',
                height: '400px',
                title: '选择图片'
            });
        });

 
        //更改宽度
        $("#logisticsSingleWidth").blur(function(){

            var lwidth=$(this).val();
            if (lwidth == "" || lwidth == "0" || isNaN(lwidth) || lwidth<=0)
            {

                $('.warning').html("注：请填写数字")
                return null;
            }else{
                $('.logisticsSingleWidth').val(lwidth);
                $(".express_container").css("width",lwidth);
                $(".express_img").css("width",lwidth);
                $(".express_board").css("width",lwidth+50);
                $('.warning').html('');
            }
        });
        //初始化宽度
        $("#logisticsSingleWidth").blur();
           
        //更改高度
        $("#logisticsSingleHeight").blur(function(){
            var lheight=$(this).val();
            if (lheight == "" || lheight == "0" || isNaN(lheight) || lheight <= 0 )
            {
                $('.warning').html("注：请填写数字")
                return null;
            }else{
                $('.logisticsSingleHeight').val(lheight);
                $(".express_container").css("height",lheight);
                $(".express_img").css("height",lheight);
                $(".express_board").css("height",lheight);
                $('.warning').html('');
            }
        });
        //初始化高度
         $("#logisticsSingleHeight").blur();
    });
    //通过From 提交快递单信息
    function addform(){
        $.ajax({
            type : 'post',
            url : 'selectLogisticsSingle.htm?companyId='+$("#companyId option:selected").val(),
            async : false,
            success : function(data) {
                if(data==""){
                    $('.companyId').val($('#companyId').val());
                    $(".logisticsSingleContent").val($("#express_container").html());
                    $("#addForm").attr("action","addThirdlogisticssingle.htm");
                    $("#addForm").submit();

                }else{
                    $("#dialog-wrong-tip").dialog("open");
                }
            }
        });

    }
    function saveChoooseTrademark(path,id){

// 			$("#adverPath").attr("src",path).attr("style", "display:block");
// 			$("#imageSrcVal").text(path);
        $("#logisticsSingleImg").val(path);
        $("#logisticsSingleImg").prop("name","logisticsSingleImg");
        $(".express_img").attr("src",path);

    }
    /*用于控制显示div层  先显示头部和左边 稍后在显示里面的内容*/
    function show(){
        $(".show_text").fadeOut(1000).fadeIn(1000);
    }
    setTimeout("show()",1000);
</script>

</head>

<body>
<#--引入头部-->
<#include "../index/indextop.ftl">

<div class="wp">
    <div class="ui-sidebar">
        <div class="sidebar-nav">
        <#import "../index/indexleft.ftl" as leftmenu>
        <@leftmenu.leftmenu '${basePath}' />
        </div>
    </div>

    <form id="addForm" method="post" enctype="multipart/form-data">
        <input  id="logisticsSingleImg" name="logisticsSingleImg" type="hidden" />
        <input  class="logisticsSingleContent" name="logisticsSingleContent" type="hidden"/>
        <input  class="companyId" name="companyId" type="hidden"/>
        <input  class="logisticsSingleWidth"  name="logisticsSingleWidth"  type="hidden"/>
        <input  class="logisticsSingleHeight" name="logisticsSingleHeight"  type="hidden"/>
    </form>

    <div class="app show_text" style="display: none;"">
        <div class="app-container">
            <ol class="breadcrumb">
                <li>您所在的位置</li>
                <li><a href="javascript:;">我的店铺</a></li>
                <li class="active">添加物流单</li>
            </ol>

            <div class="app-content">
                <div class="bill-cont">
                    <div class="express-form">
                        单据名称：
                        <select class="form-control" id="companyId">
                            <#if companys?? && companys?size!=0 >
                                <#list companys  as info >
                                    <option value="${info.shoreExpId }">${info.expName }</option>
                                </#list>
                            <#else>
                                <option value="">--无--</option>
                            </#if>
                        </select>
                    </div>
                    <div class="express-form">
                        单据尺寸：
                        <input class="form-control w50"  id="logisticsSingleWidth" type="text" value="800"/>
                        ×
                        <input class="form-control w50"  id="logisticsSingleHeight" type="text" value="600"/>
                        mm
                    </div>
                    <div class="express-form">
                        单据背景图：
                        <button class="btn btn-primary btn-sm" id="shoow"  role="button" aria-disabled="false"><span>选择图片</span></button>
                        <#--<button class="btn btn-primary btn-sm btn-file" type="button"><input type="file"/>添加图片</button>-->
                    </div>
                    <div class="express-form">
                        添加打印项：
                        <select class="form-control" id="addPrint">
                            <option value="0">添加打印项</option>
                            <option value="sendName">发件人-姓名</option>
                            <#--<option value="sendArea1">发件人-地区1级</option>
                            <option value="sendArea2">发件人-地区2级</option>
                            <option value="sendArea3">发件人-地区3级</option>-->
                            <option value="sendAddr">发件人-地址</option>
                            <option value="sendPhone">发件人-联系电话</option>
                            <option value="sendMobile">发件人-手机号码</option>
                            <option value="addresseeName">收件人-姓名</option>
                            <option value="addresseeArea1">收件人-地区1级</option>
                            <option value="addresseeArea2">收件人-地区2级</option>
                            <option value="addresseeArea3">收件人-地区3级</option>
                            <option value="addresseeAddr">收件人-地址</option>
                            <option value="addresseePhone">收件人-联系电话</option>
                            <option value="goodsName">货品名称</option>
                            <option value="addresseeMobile">收件人-手机号码</option>
                            <option value="adderseePostcode">收件人-邮政编号</option>
                        </select>
                    </div>
                </div>
                <span class="warning" style="color:red;"></span>

                <div class="express_board">
                    <img id="express_img" class="express_img" />
                    <div class="express_container" id="express_container"></div>
               </div>
        </div>
    </div>
        <div class="info-item" style="padding-left: 350px; margin-top:20px;">
            <div class="controls">
                <button class="btn btn-primary" onclick="addform()" type="button">保存</button>&nbsp;&nbsp;&nbsp;
                <button class="btn btn-default" onclick="history.back(-1)" type="button">返回</button>
            </div>
        </div>
</div>



<#--<div class="service-wrap">-->
    <#--<span class="service-close">×</span>-->
    <#--<a href="javascript:;" class="service-box">联系客服</a>-->
<#--</div>-->

<div class="back-to-top">
    <a href="javascript:;"><i></i>返回顶部</a>
</div>

<#--<div class="notice-center">-->
    <#--<div class="notice-center-ring"><i></i></div>-->
    <#--<div class="notice-center-wrapper">-->
        <#--<div class="nt-header">-->
            <#--<h3>系统通知（<span>1</span>）</h3>-->
            <#--<a href="javascript:;" class="nt-close">收起》</a>-->
        <#--</div>-->
        <#--<ul class="nt-content">-->
            <#--<li>-->
                <#--<div class="nt-item unread">-->
                    <#--<p>刘仙升于2015-04-08 15:41:23申请提现1.00元，已提现成功，请注意查询您的银行账户。</p>-->
                    <#--<a href="javascript:;">查看提现记录 》</a>-->
                <#--</div>-->
            <#--</li>-->
        <#--</ul>-->
        <#--<div class="nt-footer">-->
            <#--<a href="javascript:;" class="mark-read">全部标记已读</a>-->
            <#--<a href="javascript:;" class="nt-all">查看全部信息</a>-->
        <#--</div>-->
    <#--</div>-->
<#--</div>-->

<#--<div class="page-help-btn">帮助</div>-->
<div class="page-help-container">
    <div class="page-help-content">
        <p style="color:#f00;">不知道从哪里开始？</p>
        <p>完成掌柜任务，简简单单开店铺！</p>
        <p>点击开始》》<a href="javascript:;">掌柜成长之旅</a></p>
    </div>
    <div class="page-help-operation">
        <a href="javascript:;" class="btn btn-primary btn-sm">进入帮助中心</a>
        <a href="javascript:;" class="btn btn-default btn-sm">建议反馈</a>
    </div>
</div>



<div class="dialog s_dia" style="width: 300px; height: 150px;">
    <div class="sd_tit clearfix"><h3 class="fl">操作提示</h3>
        <a class="sd_close fr" href="javascript:;" onclick="cls();"></a>
    </div>
    <div class="pmt_wp" style="height: 50px;">
        <p class="tc f14" id="tcText" style="line-height:70px;">包裹内存在商品，不可删除！</p>
    </div>
    <div class="tc mt20">
        <a class="sop_btn" href="javascript:void(0);" onclick="cls();">确定</a>
    </div>
</div>



</body>

<script type="text/javascript">
    $(function(){

        $('#sendName').dragDrop({
            fixarea:[0,$('.express_container').width()-100,0,$('.express_container').height()-50]
        });
        $('#addPrint').change(function(){
            var theId = $(this).val();
            var canMove = true;
            if($('#'+ theId).length > 0){
                $('.warning').html("注：已经存在，请勿重复添加");
                //$("#tcText").html($(this).find('option:selected').text() + ' 已经存在，请勿重复添加。');
                //dia(2);
                return;
            }
            else {
                $('.warning').html("");
                if(theId!="0"){
                    $('.express_container').append('<div id="' + theId + '" class="express_label">' + $(this).find('option:selected').text() + '<i class="close_btn">&nbsp;</i><i class="resize_btn"></i></div>');
                    $('#' + theId).dragDrop({
                        fixarea: [0, $('.express_container').width() - 100, 0, $('.express_container').height() - 50]
                    });
                    $('#' + theId + ' .close_btn').click(function () {
                        $(this).parent().remove();
                    });
                    $('#' + theId + ' .resize_btn').click(function(){
                        if(canMove) {
                            $(this).bind('mousemove',function (e) {
                                $('#' + theId).width(e.pageX - $('#' + theId).offset().left - 15);
                                $('#' + theId).height(e.pageY - $('#' + theId).offset().top + 5);
                            });
                        }
                        else{
                            $(this).unbind('mousemove');
                        }
                        canMove = !canMove;
                    });

                }
            }
        });

    });
</script>


<script type="text/javascript" src="${basePath}/js/navmenu/navmenu.js"></script>
<#import "../common/selectindex.ftl" as selectindex>
<@selectindex.selectindex "${n!''}" "${l!''}" />
</html>
