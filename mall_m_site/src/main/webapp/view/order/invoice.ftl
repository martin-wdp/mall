<!doctype html>
<#assign basePath=request.contextPath>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>发票信息</title>
    <link rel="stylesheet" href="${basePath}/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="${basePath}/css/mui.min.css"/>
    <link rel="stylesheet" href="${basePath}/css/orderstyle.css"/>
    <link rel="stylesheet" href="${basePath}/css/style.css"/>
    <link rel="stylesheet" href="${basePath}/css/address.css"/>
 <script>if(myObj&&myObj.setHomeFlag){myObj.setHomeFlag('false');}</script></head>
<body style="background:#efefef;">
<#include "../publicHeader2_ftl.ftl" />
<div class="wrap">
    <div class="mesBoxReise">
        <form id="subFormNone" action="" method="post">
            <input type="hidden" value="${ch_pay!''}" name="ch_pay">
            <input type="hidden" value="${typeId!''}" name="typeId">
            <input type="hidden" value="${deliveryPointId!''}" name="deliveryPointId" >
            <input type="hidden" value="${addressId!''}" name="addressId" i>
            <input type="hidden" value="${invoiceType!''}" name="invoiceType" id="invoiceTypeNoInv">
        </form>
        <form action="" method="post" id="subForm">
            <input type="hidden" value="${invoiceType!''}" name="invoiceType" id="invoiceType">
            <input type="hidden" value="${ch_pay!''}" name="ch_pay">
            <input type="hidden" value="${typeId!''}" name="typeId">
            <input type="hidden" value="${deliveryPointId!''}" name="deliveryPointId" >
            <input type="hidden" value="${addressId!''}" name="addressId" i>
        <#--<legend>发票内容：</legend>-->
        <#--<div class="mui-form-group">
            <input type="text" class="mui-form-control" name="invoiceTitle" id="invoiceTitle"  value="${invoiceTitle!''}"/>
            <label class="mui-form-floating-label">发票抬头（可输入个人/单位名称）</label>
        </div>-->
            <div class="mui-form-group invoice-group">

                <div class="mui-radio invoice_no receipt">
                    <span class="receip-sp1 noInvoice">不开发票</span>
                    <label>
                        <input type="radio" id="noInvoice" value="不开发票" name="needInvice"
                               <#if invoiceType??&&invoiceType=='不开发票'>checked="checked" </#if>/>

                    </label>
                </div>
                <div class="mui-radio invoice_ok receipt">
                    <span class="receip-sp1 detailed">开发票</span>
                    <label>
                        <input id="detailed" type="radio" value="开发票" name="needInvice"
                               <#if invoiceType??&&invoiceType=='明细'>checked="checked" </#if>/>
                    </label>
                </div>

            <#--<div style="width:100%;height:8px;background: #efefef;"></div>
            <div class="receipt">
                <p style="border-bottom: none;padding-bottom: none;">发票内容：明细</p>

            </div>-->
            <#--<div class="mui-radio">
                <label>
                    <input type="radio" value="办公用品" name="invoice"<#if invoiceType??&invoiceType=='办公用品'>checked="checked" </#if>/>
                    办公用品
                </label>
            </div>
            <div class="mui-radio">
                <label>
                    <input type="radio" value="电脑配件" name="invoice"<#if invoiceType??&invoiceType=='电脑配件'>checked="checked" </#if>/>
                    电脑配件
                </label>
            </div>-->
            </div>
            <div class="mui-form-group rise dis">
                <p class="receipt-p" style="border-bottom: none;padding-bottom: none;">发票类型：纸质发票</p>
                <legend>发票抬头：</legend>
                <input type="text" class="inpName" name="invoiceTitle" id="invoiceTitle" value="${invoiceTitle!}" placeholder="请输入名字或公司名"/>
                <div style="width:100%;height:8px;background: #efefef;margin-top:8px;"></div>
                <div class="receipt">
                    <p style="border-bottom: none;padding-bottom: none;">发票内容：明细</p>

                </div>
            </div>

        </form>
    </div>
    <button class="mui-btn mui-btn-danger btnOk" onclick="checkForm()" type="button">确认</button>
</div>


<script src="${basePath}/js/jquery-1.11.1.min.js"></script>
<script src="${basePath}/js/mui.min.js"></script>
<script src="${basePath}/js/app.js"></script>
<script>
    if($("#detailed").attr("checked")=="checked"){
        $(".rise").show();
    }
    $(function(){
        var item = $(":radio:checked");
        if(item.length==0){
            $($(":radio")[0]).attr("checked","checked");
        }
        //发票抬头验证
        $("#invoiceTitle").blur(function(){
            if($(this).val().trim()==''){
                $("#invoiceTitle").parent().addClass("error");
            }else{
                $("#invoiceTitle").parent().removeClass("error");
            }
        })
    });

    $(".noInvoice").click(function(){
        $(".rise").hide();
        $(".receip-sp1").removeAttr("style");
        $(this).css({"border":"1px solid #f55a4e","color":"#f55a4e"});
        $("#invoiceTypeNoInv").val("不开发票");
        $("#invoiceType").val("不开发票");
    });
    $(".detailed").click(function(){
        $(".rise").show();
        $(".receip-sp1").removeAttr("style");
        $(this).css({"border":"1px solid #f55a4e","color":"#f55a4e"});
        $("#invoiceTypeNoInv").val("明细");
        $("#invoiceType").val("明细");
    });
    function checkForm(){
        // alert($('input:radio[name="needInvice"]:checked').val());
        var needInv = $('input:radio[name="needInvice"]:checked').val();
        if($("#invoiceTitle").val().trim()=='' && needInv=="1"){
            $("#invoiceTitle").parent().addClass("error");
        }else{
            $("#invoiceTitle").parent().removeClass("error");
            if(needInv=="不开发票"){
                $("#subFormNone").attr("action","suborder.htm").submit();
            }
            $("#subForm").attr("action","suborder.htm").submit();
        }


    }

</script>
<script>

    if('${invoiceType!''}'=="不开发票"){
        $(".noInvoice").css({"border":"1px solid #f55a4e","color":"#f55a4e"});
        $("#invoiceType").val($('input:radio[name="needInvice"]:checked').val());
    }
    if('${invoiceType!''}'=="明细"){
        $("#invoiceTypeNoInv").val("明细");
        $(".detailed").css({"border":"1px solid #f55a4e","color":"#f55a4e"});
        $("#invoiceType").val($('input:radio[name="needInvice"]:checked').val());
    }
</script>
</body>
</html>