<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>修改物流单</title>
<#assign basePath=request.contextPath>
    <link rel="stylesheet" href="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/css/bootstrap-datepicker.min.css" >
    <link rel="stylesheet" href="${basePath}/css/style.css"/>

    <script type="text/javascript" src="http://cdn.staticfile.org/jquery/1.11.1/jquery.min.js"></script>
    <script type="text/javascript" src="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${basePath}/js/jquery-ui/jquery-ui-1.10.3.custom.js"></script>
    <script type="text/javascript" src="${basePath}/js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="${basePath}/js/angular.min.js"></script>
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
        .express_board .express_img{width:900px;height:30px;position:absolute;left:20px;top:20px;}
        .express_container{position:relative;background:no-repeat left top 100% auto;}
        .express_form{display:inline-block;margin-right:50px;}
        .express_form{*display:inline;}
        .express_form select{margin-top:6px;}
        .express_label{display:block;height:25px;line-height:25px;padding:0 10px;border:1px solid #999;background:#fff;}
        .express_label .close_btn{display:none;width:15px;height:15px;line-height:15px;text-align:center;position:absolute;right:-8px;top:-8px;background:url(images/express_close_btn.png) no-repeat;cursor:pointer;}
        .express_label:hover .close_btn{display:block;}
        .express_label .resize_btn{display:block;width:10px;height:10px;background:#ccc;position:absolute;right:0;bottom:0;}
        #shoow{ width:70px;height:35px;}
        body{padding:0px;}
    </style>

    <script type="text/javascript">
        $(function(){
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
        });
        //修改Form提交
        function addform(){
            $("#logisticsSingleContent").val($(".express_container").html());
            $("#addForm").attr("action","updatelogisticssingles.htm");
            $("#addForm").submit();
        }


        function saveChoooseTrademark(id, path){
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
        <input type="hidden" name="logisticsSingleImg" value="${logisticsSingle.logisticsSingleImg}" id="logisticsSingleImg"  />
        <input type="hidden" name="logisticsSingleContent" id="logisticsSingleContent" />
        <input type="hidden" name="logisticsSingleWidth" value="${logisticsSingle.logisticsSingleWidth }"  class="logisticsSingleWidth"     />
        <input type="hidden" name="logisticsSingleHeight" value="${logisticsSingle.logisticsSingleHeight }"  class="logisticsSingleHeight"   />
        <input type="hidden" name="thirdId" value="${logisticsSingle.thirdId}"/>
        <input type="hidden" name="logisticsSingleId" value="${logisticsSingle.logisticsSingleId }"/>
    </form>

    <div class="app show_text" style="display: none;"">
        <div class="app-container">
            <ol class="breadcrumb">
                <li>您所在的位置</li>
                <li><a href="javascript:;">物流单管理</a></li>
                <li class="active">修改物流单</li>
            </ol>

            <div class="app-content">
                <div class="bill-cont">
                    <div class="express-form">
                        单据名称：${logisticsSingle.companyName }
                    </div>
                    <div class="express-form">
                        单据尺寸：
                        <input class="form-control w50"  id="logisticsSingleWidth" type="text" value="${logisticsSingle.logisticsSingleWidth }"  />
                        ×
                        <input class="form-control w50"  id="logisticsSingleHeight" type="text" value="${logisticsSingle.logisticsSingleHeight }" />
                        mm
                    </div>
                    <div class="express-form">
                        单据背景图：
                        <button  id="shoow"  role="button" aria-disabled="false"><span>选择图片</span></button>
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
                <div class="express_board" style='width:${logisticsSingle.logisticsSingleWidth }px;height:${logisticsSingle.logisticsSingleHeight}px'>
                    <img id="express_img" class="express_img" src="${logisticsSingle.logisticsSingleImg }" style='width:${logisticsSingle.logisticsSingleWidth }px;height:${logisticsSingle.logisticsSingleHeight}px'/>
                    <div class="express_container" id="express_container" style='width:${logisticsSingle.logisticsSingleWidth }px;height:${logisticsSingle.logisticsSingleHeight}px'>
                         ${logisticsSingle.logisticsSingleContent}
                    </div>
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
        <div class="sd_tit clearfix"><h3 class="fl">操作提示333</h3>
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
<script>
    $(function(){
        /*var sendArea1Style=$("#sendArea1").attr("style");
        var sendArea2Style=$("#sendArea2").attr("style");
        var sendArea3Style=$("#sendArea3").attr("style");*/
        var sendAddrStyle=$("#sendAddr").attr("style");
        var addresseeNameStyle=$("#addresseeName").attr("style");
        var addresseeArea1Style=$("#addresseeArea1").attr("style");
        var addresseeArea2Style=$("#addresseeArea2").attr("style");
        var addresseeArea3Style=$("#addresseeArea3").attr("style");
        var addresseeAddrStyle=$("#addresseeAddr").attr("style");
        var goodsNameStyle=$("#goodsName").attr("style");
        var addresseePhoneStyle=$("#addresseePhone").attr("style");
        var addresseeMobileStyle=$("#addresseeMobile").attr("style");
        var adderseePostcodeStyle=$("#adderseePostcode").attr("style");
        var sendNameStyle=$("#sendName").attr("style");
        var sendPhoneStyle=$("#sendPhone").attr("style");
        var sendMobileStyle=$("#sendMobile").attr("style");

        $(".express_label").dragDrop({
            fixarea:[0,$('.express_container').width()-100,0,$('.express_container').height()-50]
        });
        $(".close_btn").click(function () {
            $(this).parent().remove();
        });
        $('#sendName').dragDrop({
            fixarea:[0,$('.express_container').width()-100,0,$('.express_container').height()-50]
        });

        $("#sendName").attr("style",sendNameStyle);
        /*$("#sendArea1").attr("style",sendArea1Style);
        $("#sendArea2").attr("style",sendArea2Style);
        $("#sendArea3").attr("style",sendArea3Style);*/
        $("#sendAddr").attr("style",sendAddrStyle);
        $("#addresseeName").attr("style",addresseeNameStyle);
        $("#addresseeArea1").attr("style",addresseeArea1Style);
        $("#addresseeArea2").attr("style",addresseeArea2Style);
        $("#addresseeArea3").attr("style",addresseeArea3Style);
        $("#addresseeAddr").attr("style",addresseeAddrStyle);
        $("#addresseePhone").attr("style",addresseePhoneStyle);
        $("#goodsName").attr("style",goodsNameStyle);
        $("#addresseeMobile").attr("style",addresseeMobileStyle);
        $("#adderseePostcode").attr("style",adderseePostcodeStyle);
        $("#sendPhone").attr("style",sendPhoneStyle);
        $("#sendMobile").attr("style",sendMobileStyle);



        $('#addPrint').change(function(){
            var theId = $(this).val();
            var canMove = true;
            if($('#'+ theId).length >0){
                $('.warning').html("注：已经存在，请勿重复添加");
                //$("#dialog-tip").dialog("open");
            }else{
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
