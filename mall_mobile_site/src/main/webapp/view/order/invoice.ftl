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
    <link rel="stylesheet" href="${basePath}/css/address.css"/>
</head>
<body>
<div class="mui-appbar">
    <h2 class="mui-text-center">发票信息</h2>
    <a href="javascript:history.go(-1);" class="back-btn"><i class="fa fa-angle-left"></i></a>
</div>
<div class="wrap">
    <div class="mui-container">
        <form action="" method="post" id="subForm">
            <input type="hidden" value="${invoiceType!''}" name="invoiceType" id="invoiceType">
            <input type="hidden" value="${ch_pay!''}" name="ch_pay">
            <input type="hidden" value="${typeId!''}" name="typeId">
            <input type="hidden" value="${deliveryPointId!''}" name="deliveryPointId" >
            <input type="hidden" value="${addressId!''}" name="addressId" i>
        <legend>普通发票</legend>
            <div class="mui-form-group">
                <input type="text" class="mui-form-control" name="invoiceTitle" id="invoiceTitle"  value="${invoiceTitle!''}"/>
                <label class="mui-form-floating-label">发票抬头（可输入个人/单位名称）</label>
            </div>
            <div class="mui-form-group invoice-group">
                <div class="mui-radio">
                    <label>
                        <input type="radio" value="明细" name="invoice"<#if invoiceType??&&invoiceType=='明细'>checked="checked" </#if>/>
                        明细
                    </label>
                </div>
                <div class="mui-radio">
                    <label>
                        <input type="radio" value="耗材" name="invoice" <#if invoiceType??&invoiceType=='耗材'>checked="checked" </#if>/>
                        耗材
                    </label>
                </div>
                <div class="mui-radio">
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
                </div>
                <label>发票内容</label>
            </div>
            <button class="mui-btn mui-btn-danger" onclick="checkForm()" type="button">确认</button>
        </form>
    </div>
</div>


<script src="${basePath}/js/jquery-1.11.1.min.js"></script>
<script src="${basePath}/js/mui.min.js"></script>
<script src="${basePath}/js/app.js"></script>
<script>
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
    })

    function checkForm(){
        $("#invoiceType").val($(":radio:checked").val());
        if($("#invoiceTitle").val().trim()==''){
            $("#invoiceTitle").parent().addClass("error");
        }else{
            $("#invoiceTitle").parent().removeClass("error");
            $("#subForm").attr("action","suborder.htm").submit();
        }


    }
</script>
</body>
</html>