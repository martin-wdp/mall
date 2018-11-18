<!DOCTYPE html>
<html>
<head>
<#assign basePath=request.contextPath>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta name="MobileOptimized" content="320">
    <meta name="format-detection" content="telephone=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link rel="stylesheet" type="text/css" href="${basePath}/css/top.css">
    <link rel="stylesheet" type="text/css" href="${basePath}/css/accountManage.css">

    <title>账户管理</title>
 <script>if(myObj&&myObj.setHomeFlag){myObj.setHomeFlag('false');}</script> <script>if(myObj&&myObj.setHomeFlag){myObj.setHomeFlag('false');}</script></head>
<body>
<#include "../publicHeader2_ftl.ftl" />
<div class="inp">
    <div class="homePic">
        <p class="zinp"><label>头像：</label></p>
        <input id="pic1" type="file" onchange="uploadUserAvatarAjax()" url="${basePath}/uploadimg.htm?customerId=${cust.customerId}" name="shareFile"
               customer_id="${cust.customerId}" class="upload_file" accept=".png,.jpg,.jpeg">
        <span data-id="#pic1" id="useravatar"
              style="background-image: url(<#if cust.customerImg??&&cust.customerImg!="">
                      ${cust.customerImg}<#else>${basePath}/images/newimage/mall.png</#if>)"></span>
    </div>
    <div class="zinp">
        <label>昵称：</label>

        <div class="nic">
            <input id="nikename" type="text" class="ncinp" <#if cust.customerNickname??&&cust.customerNickname!="">value="${cust.customerNickname}" </#if> placeholder="想好了叫什么了没？">
            <a href="#"><img src="${basePath}/images/03.png"></a>
        </div>
    </div>
    <div class="zinp" style="border-bottom:none;">
        <label>性别：</label>
        <input type="hidden" id="basesexid" value="${cust.infoGender}">
        <div class="sex" id="sexshow2"><i sexid="2" url="${basePath}/updateCustomerSexM.htm" custId="${cust.customerId}" class="woman"></i><span>女</span>
        </div>
        <div class="sex" id="sexshow1" ><i sexid="1" url="${basePath}/updateCustomerSexM.htm" custId="${cust.customerId}" class="man"></i><span>男</span></div>

    </div>
</div>
<div class="selfMes">
    <p class="pass">
        <a href="${basePath}/customer/toUpdatePwd.htm?tag=4"><span>修改密码：</span><img src="${basePath}/images/qp_sxq.png" width="20"></a>
    </p>

    <p class="pass">
        <a href="${basePath}/customer/toAttestation.htm?tag=4"><span>企业认证：</span><img src="${basePath}/images/qp_sxq.png" width="20">
            <em class="fin"><#if (cust.isEnterprise!'') =="1">已<#else>去</#if>认证</em></a>

    </p>

    <p class="pass">
        <a href="${basePath}/customer/showOrderAddressList.htm?needRetrun=0&tag=4"><span>收货地址：</span><img src="${basePath}/images/qp_sxq.png" width="20"></a>
    </p>

    <p class="pbtn">
        <input type="button" id="logout" class="btn" value="退出登录">
    </p>
</div>
<script src="${basePath}/js/customer/jquery-1.11.3.min.js"></script>
<#--<script src="${basePath}/js/customer/newjs/jquery-1.9.1.js"></script>-->
<script src="${basePath}/js/customer/public.js"></script>
<script src="${basePath}/js/customer/accountManage.js"></script>
<script src="${basePath}/js/customer/ajaxMutiUploadFile.js"></script>
<script src="${basePath}/js/fastclick.min.js"></script>
<script src="${basePath}/js/idangerous.swiper-2.1.min.js"></script>
<script src="${basePath}/js/jquery.keleyi.js"></script>
<script src="${basePath}/js/customer/wxforward.js"></script>
<script src="${basePath}/js/publicModel.js"></script>
</body>
<script>
    //选择上传文件后触发
    $(function () {
        if('${cust.infoGender}' == "1"){
            $("#sexshow1").css({"border":"1px solid #F6AB00","color":"#F6AB00"}).children("i").css("background-color","#F6AB00");
        }
        if('${cust.infoGender}' == "2"){
            $("#sexshow2").css({"border":"1px solid #F6AB00","color":"#F6AB00"}).children("i").css("background-color","#F6AB00");
        }
    })
    $("#logout").bind("click", function () {
        window.location.href = "${basePath}/customer/logout.htm";
    });
    function uploadUserAvatarAjax() {
        if ($("#pic1").val() != "") {
            var customerId = $("#pic1").attr("customer_id");
            var url = $("#pic1").attr("url");
            /*$("#upload_form").submit();*/
            $.ajaxMutiUpload({
                url: url,
                fileElementId: "pic1",
                async: false,
                success: function (data) {
                    /*//alert(data);
                    if (data != null) {
                        clearTip();
                        location.reload();
                        //$('#applyBrand').modal('show');
                    } else {
                        clearTip();
                    }*/
                }
            });
        }
    }


    /**
     * 上传文件成功后，触发。
     * @param msg
     */
    function callback(msg) {
        //上传失败
        if (msg.split(",").length < 2) {
            if (msg == '101') {
//			showAlert("操作提示","每张图片不超过4M!","warn");
                $("#titleerr").text("每张图片不超过4M!");
                dia(4);
                $("#customer_imgs").attr("src", img_src);
            } else if (msg == '102') {
//			alert("操作提示","图片格式不正确!","warn");
                $("#titleerr").text("图片格式不正确!");
                dia(4);
                $("#customer_imgs").attr("src", img_src);
            }
            return;
        }
        //上传成功
        var imageName = msg.split(",")[0];
        //alert(111);
        $("#useravatar").css("background-image", "url(" + imageName + ")");
        //img_src = $("#customer_imgs").attr("src");
    }

    $("#nikename").bind("blur",function(){
        //AJAX修改我的个人信息
        var nikeName = $("#nikename").val();
        if(nikeName!=""){
            $.ajax({
                url: '${basePath}/updateCustomerAjaxM.htm',
                type: 'post',
                async: false,
                data:{customerNickname:nikeName},
                success: function (data) {
                    $("#myoverOrderCount").text(data);
                }
            });
        }
    });
</script>
</html>
