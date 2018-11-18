<!DOCTYPE HTML>
<head>
<#assign basePath=request.contextPath>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width,initial-scale=0.9,user-scalable=no"/>
<meta name="MobileOptimized" content="320">
<meta name="format-detection" content="telephone=no">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<link rel="stylesheet" href="${basePath}/css/suppliers.css">
<link href="${basePath}/css/style.css" rel="stylesheet">
    <title>我的认证信息</title>
 <script>if(myObj&&myObj.setHomeFlag){myObj.setHomeFlag('false');}</script></head>
<body style="background:#EFEFEF;">
<#include "../publicHeader2_ftl.ftl" />
<#if submitFlag=="0">
<div class="enterCert">
<form method="post" id="authentication_from" action="${basePath}/saveEnCertInfo.htm"
      enctype="multipart/form-data">
    <input type="hidden" name="enterpriseProvince" value="${enter.enterpriseProvince!"1"}"/>
    <input type="hidden" name="enterpriseCity" value="${enter.enterpriseCity!"1"}"/>
    <input type="hidden" name="enterpriseCounty" value="${enter.enterpriseCounty!"1"}"/>
    <input type="hidden" name="companyType" value="${enter.companyType!""}"/>
    <input type="hidden" class="picUpload-pic1" name="companyPicUrl" value="${enter.companyPicUrl!""}"/>
    <input type="hidden" class="picUpload-pic5" name="busscardUrl" value="${enter.busscardUrl!""}"/>
    <input type="hidden" class="picUpload-pic6" name="bussDeptNoUrl" value="${enter.bussDeptNoUrl!""}"/>
    <input type="hidden" class="picUpload-pic7" name="bussTaxRegistUrl" value="${enter.bussTaxRegistUrl!""}"/>
    <input type="hidden" id="basePath" value="${basePath}"/>
	<div class="comMes bgColor">
		<p class="pborder must"  data-mes="请输入店名"><label><i>*</i>店名：</label><input type="text" name="companyName" value="${enter.companyName!""}"  placeholder=请填写营业执照名称></p>
		<div class="genre must" data-mes="请选择类别"><label><i>*</i>类别：</label>
			<ul>
				<li data-em="1">直营店</li>
				<li data-em="2">联营店</li>
				<li data-em="3">个人代理</li>
				<li data-em="4">其他</li>
			</ul>
		</div>
	</div>

	<div class="comPic bgColor must" style="height:90px;" data-mes="请上传门头照">
		<p><label><i>*</i>门头照：</label></p>
		<div class="homePic" >
			<#--<input id="pic1" type="file" name="specImg" style="overflow:hidden;">-->
            <input type="file" id="pic1" style="overflow:hidden;" name="pic1"
                   onchange="picUploadRe(this,'pic1')"/>
			<span data-id="#pic1" id="pic1Url"><em>x</em></span>

		</div>
	</div>

	<div class="comAddr bgColor">
        <p id="area" class="pborder must" num="1"><label><i>*</i>所属区域：</label><map><span id="Province">${enter.enterpriseProvinceName!"北京"}</span><span id="prefecture">${enter.enterpriseCityName!"北京市"}</span><span id="county">${enter.enterpriseCountyName!"东城区"}</span></map><img src="${basePath}/images/qp_sxq.png" width="20"></p>
		<p class="pborder must"  data-mes="请输入详细地址"><label><i>*</i>详细地址：</label><input type="text"  name="companyContactAddr" placeholder=请填写您的详细地址></p>
		<p class="setUp"><input type="checkbox" id="addr" name="addDefault" value="1"><label for="addr">设置为收货地址</label></p>
	</div>

	<div class="comCert bgColor must" data-mes="请上传证件照">
		<p><label><i>*</i>证件照：</label></p>
		<#--<div class="homePic homeNew dis">
			<input id="pic4" type="file" onchange="picUploadRe(this,'pic4')">
			<span data-id="#pic4" id="pic4Url"><em>x</em></span><br>
			<small style="margin-left: -10px;">社会信用代码</small>
			<a id="new">上传三证执照&nbsp;&gt;</a>
		</div>-->
		<div class="homePic homeOld">
			<ul>
				<li><input id="pic5" type="file" name="pic5"  onchange="picUploadRe(this,'pic5')"><span data-id="#pic5" id="pic5Url"><em>x</em></span><br><small>营业执照注册号</small><strong>+</strong></li>
				<li><input id="pic6" type="file" name="pic6" onchange="picUploadRe(this,'pic6')"><span data-id="#pic6" id="pic6Url"><em>x</em></span><br><small>组织机构代码</small><strong>+</strong></li>
				<li><input id="pic7" type="file" name="pic7" onchange="picUploadRe(this,'pic7')"><span data-id="#pic7" id="pic7Url"><em>x</em></span><br><small>税务登记证</small></li>
			</ul>
			<#--<a id="old">上传新执照&nbsp;&gt;</a>-->
		</div>
	</div>

	<div class="comCon bgColor">
		<p class="pborder must" data-mes="请输入联系人"><label><i>*</i>联系人：</label><input type="text" name="companyContactName" value="${enter.companyContactName!""}"  placeholder=请填写联系人姓名></p>
		<p class="pborder must" data-mes="请输入联系电话"><label><i>*</i>联系电话：</label><input id="mobile" type="text" value="${enter.company_contact_moble!""}" name="company_contact_moble" maxlength="11"  placeholder=请填写联系电话></p>
		<p class="must" data-mes="请输入邮箱"><label><i>*</i>Email：</label><input type="text" id="email" value="${enter.companyEmail!""}" name="companyEmail" placeholder=请填写邮箱地址></p>
	</div>
</form>
<button id="sub">提交</button>
</div>
<#else>
<div class="enterCert">
        <input type="hidden" name="enterpriseProvince" value="${enter.enterpriseProvince!"1"}"/>
        <input type="hidden" name="enterpriseCity" value="${enter.enterpriseCity!"1"}"/>
        <input type="hidden" name="enterpriseCounty" value="${enter.enterpriseCounty!"1"}"/>
        <input type="hidden" name="companyType" value="${enter.companyType!""}"/>
        <input type="hidden" class="picUpload-pic1" name="companyPicUrl" value="${enter.companyPicUrl!""}"/>
        <input type="hidden" class="picUpload-pic5" name="busscardUrl" value="${enter.busscardUrl!""}"/>
        <input type="hidden" class="picUpload-pic6" name="bussDeptNoUrl" value="${enter.bussDeptNoUrl!""}"/>
        <input type="hidden" class="picUpload-pic7" name="bussTaxRegistUrl" value="${enter.bussTaxRegistUrl!""}"/>
        <input type="hidden" id="basePath" value="${basePath}"/>
        <div class="comMes bgColor">
            <p class="pborder must"><label><i>*</i>店名：</label><input type="text" name="companyName" readonly value="${enter.companyName!""}"  placeholder=请填写营业执照名称></p>
            <div class="genre must"><label><i>*</i>类别：</label>
                <ul>
                    <li data-em="1">直营店</li>
                    <li data-em="2">联营店</li>
                    <li data-em="3">个人代理</li>
                    <li data-em="4">其他</li>
                </ul>
            </div>
        </div>

        <div class="comPic bgColor must">
            <p><label><i>*</i>门头照：</label></p>
            <div class="homePic" >
            <#--<input id="pic1" type="file" name="specImg" style="overflow:hidden;">-->
                <#--<input id="pic1" type="text" style="hidden"/>-->
                <span data-id="#pic1" id="pic1Url"><em>x</em></span>
            </div>
        </div>

        <div class="comAddr bgColor">
            <p id="area" class="pborder must" num="1"><label><i>*</i>所属区域：</label><map><span id="Province">${enter.enterpriseProvinceName!"北京"}</span><span id="prefecture">${enter.enterpriseCityName!"北京市"}</span><span id="county">${enter.enterpriseCountyName!"东城区"}</span></map><img src="${basePath}/images/qp_sxq.png" width="20"></p>
            <p class="pborder must"><label><i>*</i>详细地址：</label><input type="text" readonly value="${enter.companyAddress!""}"  name="companyContactAddr" placeholder=请填写您的详细地址></p>
        </div>

        <div class="comCert bgColor must">
            <p><label><i>*</i>证件照：</label></p>
        <#--<div class="homePic homeNew dis">
            <input id="pic4" type="file" onchange="picUploadRe(this,'pic4')">
            <span data-id="#pic4" id="pic4Url"><em>x</em></span><br>
            <small style="margin-left: -10px;">社会信用代码</small>
            <a id="new">上传三证执照&nbsp;&gt;</a>
        </div>-->
            <div class="homePic homeOld">
                <ul>
                    <li><span data-id="#pic5" id="pic5Url"><em>x</em></span><br><small>营业执照注册号</small><strong>+</strong></li>
                    <li><span data-id="#pic6" id="pic6Url"><em>x</em></span><br><small>组织机构代码</small><strong>+</strong></li>
                    <li><span data-id="#pic7" id="pic7Url"><em>x</em></span><br><small>税务登记证</small></li>
                </ul>
            <#--<a id="old">上传新执照&nbsp;&gt;</a>-->
            </div>
        </div>

        <div class="comCon bgColor">
            <p class="pborder must"><label><i>*</i>联系人：</label><input type="text" readonly value="${enter.companyContactName!""}"  name="companyContactName"  placeholder=请填写联系人姓名></p>
            <p class="pborder must"><label><i>*</i>联系电话：</label><input id="mobile" readonly value="${enter.company_contact_moble!""}" type="text" name="company_contact_moble" maxlength="11"  placeholder=请填写联系电话></p>
            <p class="must"><label><i>*</i>Email：</label><input type="text" id="email" readonly value="${enter.companyEmail!""}" name="companyEmail" placeholder=请填写邮箱地址></p>
        </div>
</div>
</#if>
<div class="addrBox dis">
	<div class="district">
		<p><span>省 | 市 | 自治区</span><span>地级市</span><span>区县</span><i id="line" data-left="40%"></i></p>
		<div class="city">
			<ul data-count="0" id="addressInit">
                <li></li>
			</ul>
		</div>
	</div>
</div>

<#--<div class="prompt dis">
	<div class="box">
		<img src="images/qp_cxcg.png"><br>
		<h3>恭喜您成功提交认证</h3>
		<p>我们将在24小时内完成审核，请您耐心等待</p>
		<span><i id="time">2</i>s后自动跳转</span>
	</div>
</div>-->

<div class="auth dis">
	<div class="box mesBox">
		<h3>完成企业认证有专享价格哦</h3>
		<button id="leave" style="color:#000;background:none;border:1px solid #EDEDED;">离开</button>
		<button id="continue" style="background:#F6AB00;">继续认证</button>
	</div>
</div>
</body>
<script src="${basePath}/js/customer/jquery-1.11.3.min.js"></script>
<script src="${basePath}/js/customer/ajaxMutiUploadFile.js"></script>
<script src="${basePath}/js/customer/public.js"></script>
<script src="${basePath}/js/customer/suppliers/suppliers-logic.js"></script>
<script src="${basePath}/js/customer/suppliers/suppliers-effect.js"></script>
<script src="${basePath}/js/customer/selectAddress.js"></script>
<script>
   var emp = '${enter!"0"}';
   if(emp != '0'){
       var n = 0;
       var m = '${enter!'0'}';
       if(m != '0'){
           var n = '${enter.companyType!'0'}';
       }
   }
   if(n != '0'){
       $(".genre li:nth-child("+n+")").css({"border":"2px solid #F5AC02","background-color":"#fff","background-image":"url(${basePath}/images/ok.png)","color":"#F5AC02"}).parents(".must").attr("num","1");
       // 给公司类型属性赋值
        $('input[name="companyType"]').val(n);
   }
   var companyPicUrl = '${enter.companyPicUrl!""}';
   var busscardUrl = '${enter.busscardUrl!""}';
   var bussDeptNoUrl = '${enter.bussDeptNoUrl!""}';
   var bussTaxRegistUrl = '${enter.bussTaxRegistUrl!""}';
    if (companyPicUrl!="") {
        Image.Show("span#pic1Url",companyPicUrl);
        Write.Must.Ok("span#pic1Url");
    }
   if(busscardUrl!=""){
       Image.Show("span#pic5Url",companyPicUrl);
   }
   if(bussDeptNoUrl!=""){
       Image.Show("span#pic6Url",bussDeptNoUrl);
   }
   if(bussTaxRegistUrl!=""){
       Image.Show("span#pic7Url",bussTaxRegistUrl);
   }
    if (busscardUrl!="" &&
            bussDeptNoUrl!="" &&
            bussTaxRegistUrl!="") {
        Write.Must.Ok("span#pic5Url");
    }
    if('${submitFlag!'0'}'!=0){
        $(".genre li").unbind();
        $("#area").unbind();
        $("em").hide();
    }
</script>