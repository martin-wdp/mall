<#include "../include/common.ftl">
<@htmlHead title="账户信息-${topmap.systembase.bsetName}">
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/index.css" />
<style>
    .tips_succ{color:green;}
    .tips_error{color:red;}
    .text_error{border:1px solid #F15A21;color:red;}
</style>
</@htmlHead>
<#--<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>账户信息-${topmap.systembase.bsetName}</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta name="Keywords" content="${topmap.seo.meteKey}">
<meta name="description" content="${topmap.seo.meteDes}">
<#assign basePath=request.contextPath>
<#if (topmap.systembase.bsetHotline)??>
	<link rel = "Shortcut Icon" href="${(topmap.systembase.bsetHotline)!''}">
<#else>
	<link rel = "Shortcut Icon" href="${basePath}/images/Paistore.ico">
</#if>
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/index.css" />
<style>
	.tips_succ{color:green;}
	.tips_error{color:red;}
	.text_error{border:1px solid #F15A21;color:red;}
</style>
</head>-->
<@htmlBody>
     <input type="hidden" name="CSRFToken" value="${token}">
    	<#--一引入头部 <#include "/index/topnew.ftl" /> -->	
		<#if (topmap.temp)??>
			<#if (topmap.temp.tempId==1)>
				<#include "../index/topnew.ftl">
			<#elseif (topmap.temp.tempId==3)>
				<#include "../index/newheader.ftl">
			<#elseif (topmap.temp.tempId==9)>
				<#include "../index/newheader4.ftl">
			<#elseif (topmap.temp.tempId==10)>
				<#include "../index/newheader5.ftl">
			<#elseif (topmap.temp.tempId==11)>
				<#include "../index/newheader6.ftl">
			<#elseif (topmap.temp.tempId==12)>
				<#include "../index/newheader7.ftl">
			<#elseif (topmap.temp.tempId==13)>
				<#include "../index/newheader8s.ftl">
			<#elseif (topmap.temp.tempId==14)>
				<#include "../index/newheader9.ftl">
			<#elseif (topmap.temp.tempId==15)>
				<#include "../index/newheader10.ftl">
			<#elseif (topmap.temp.tempId==16)>
				<#include "../index/newheader11.ftl">
			<#elseif (topmap.temp.tempId==17)>
				<#include "../index/newheader12.ftl">
			<#elseif (topmap.temp.tempId==18)>
				<#include "../index/newheader13.ftl">
			<#elseif (topmap.temp.tempId==19)>
				<#include "../index/newheader14.ftl">
            <#elseif (topmap.temp.tempId==20)>
                <#include "../index/newheader15.ftl">
			<#else>
				<#include "../index/newheader3.ftl">
			</#if>
		</#if>
		 <div class="container">
		<input type="hidden" value="${token!''}" id="hi_token" />
        <div class="location">
        	<a href="${basePath}/customer/index.html"><strong>会员中心</strong></a><span>&gt;</span>
            <a href="${basePath}/customer/myinfo.html">账户中心</a><span>&gt;</span>
            <span>账户信息</span>
        </div>
        <div class="member_box mb20">
        	<#include "leftmenu.ftl" />
             <div class="member_right fl ml10">
                <div class="memebr_title mb20">
                    <h2 class="f14 fb">账户信息</h2>
                </div>
                <span style="float:right">
		               注：修改邮箱、手机请到<a href="${basePath}/customer/securitycenter.html"><span style="color:#CC0000">账户安全</span></a>
		        </span>
                <div class="order_list switch_border">
                    <div class="tagMenu">
                        <ul class="menu">
                           <li onclick="myInfo();"><em></em><span></span>基本信息</li>
                           <li onclick="selectJob(),selectEdu()"><em></em><span></span>更多个人信息</li>
                        </ul>
                    </div>
                    <script>
                    	function myInfo(){
                    		location.reload();
                    	}
                    </script>
                    <div class="content">
                    	
                    	<#if customer??>
	                        <div class="layout">
	                            <div class="basic_info member_info mb20">
	                                <dl>
	                                    <dt>用户名：</dt><input type="hidden" id="hi_uid" value="${customer.customerId}" />
	                                    <dd><b>
	                                    <#if customer.customerUsername??>
	                                    	${customer.customerUsername}
	                                    </#if>
	                                    </b></dd>
	                                </dl>
	                                <dl>
	                                    <dt>会员等级：</dt>
	                                    <dd><b>
	                                    <#if customer.pointLevelName??>
											${customer.pointLevelName}
										<#else>
                                            &nbsp;&nbsp;
										</#if>
	                                    </b></dd>
	                                </dl>
	                                <form method="post" action="${basePath}/modifyInfo.html">
		                                <dl>
		                                    <dt><em>*</em>昵称：</dt>
		                                    <dd>
		                                    <input class="text" id="nickName" type="text" name="customerNickname" value="<#if customer.customerNickname??>${customer.customerNickname!''}</#if>" />
		                                    <img id="nickName_img" src="${basePath}/images/succ-ico.png" />
		                                    <label id="nickName_msg"></label>
		                                    <input class="hidden" id="nickName_" value="${customer.customerNickname!''}"  />
		                                    </dd>
		                                </dl>
		                                <dl>
		                                    <dt><em>*</em>性别：</dt>
		                                    <dd>
		                                        <input type="radio" name="infoGender" value="0" <#if customer.infoGender=="0" >checked=checked</#if> /><label>保密</label>
		                                        <input type="radio" name="infoGender" value="1" <#if customer.infoGender=="1" >checked=checked</#if> /><label>男</label>
		                                        <input type="radio" name="infoGender" value="2" <#if customer.infoGender=="2" >checked=checked</#if> /><label>女</label>
		                                    </dd>
		                                </dl>
		                                <dl>
		                                    <dt>真实姓名：</dt>
		                                    <dd>
		                                    <input class="text" id="realName" type="text" name="infoRealname" value="<#if customer.infoRealname??>${customer.infoRealname}</#if>" />
		                                    <img id="realName_img" src="${basePath}/images/succ-ico.png" />
		                                    <label id="realName_msg"></label>
		                                    </dd>
		                                </dl>
		                                <dl>
		                                    <dt>身份证号码：</dt>
		                                    <dd>
		                                    <#if customer.infoCardid??>
												<#if (customer.infoCardid?trim)?length==0>	
													<input class="text" id="idnum" name="infoCardid"  />
													<img id="idnum_img" src="${basePath}/images/succ-ico.png" />
													<label id="idnum_msg"></label>
												<#else>
													<#assign cd="${customer.infoCardid?substring(3,customer.infoCardid?length-3)}" />
													<#assign cid="${customer.infoCardid?replace(cd,'******')}" /> 
													${cid}
													<#--<a href="#">修改</a>-->
												</#if>
											<#else>
												<input class="text" id="idnum" name="infoCardid"  />
												<img id="idnum_img" src="${basePath}/images/succ-ico.png" />
												<label id="idnum_msg"></label>
											</#if>
											<input class="hidden" id="idnum_" value="${customer.infoCardid!''}"  />
		                                    </dd>
		                                </dl>
		                                <dl>
		                                    <dt>所在地：</dt>
		                                    <dd>
		                                        <select class="select" name="infoProvince" id=infoProvince >
												</select>
												<select class="select" name="infoCity" id=infoCity>
													<option selected value="">请选择</option>
												</select>
												<select class="select" name="infoCounty" id=infoCounty>
													<option selected value="">请选择</option>
												</select>
												<select class="select" name="infoStreet" id=infoStreet>
													<option selected value="">请选择</option>
												</select>
		                                    </dd>
		                                </dl>
		                                <dl>
		                                    <dt>详细地址：</dt>
		                                    <dd>
		                                    	<input class="text address" id="address" name="infoAddress" type="text"value="<#if customer.infoAddress??>${customer.infoAddress?default("")}</#if>" />
		                                    	<img id="address_img" src="${basePath}/images/succ-ico.png" />
		                                    	<label id="address_msg"></label>
		                                    </dd>
		                                </dl>
		                            </div>
		                            <div class="ex_info member_info mb20">
		                                <h4 class="f13 fb">选填信息</h4>
		                                <dl>
		                                    <dt>生日：</dt>
		                                    <dd>
		                                        <select class="select" id=year name="year" > </select> 年
												<select class="select" id=month name="month"> </select>月
												<select class="select" id=day name="day"> </select>  日 
		                                    </dd>
		                                </dl>
		                                <dl>
		                                    <dt>婚姻状况：</dt>
		                                    <dd>
		                                        <input type="radio" name="infoMarital" value="0" <#if customer.infoMarital=="0" >checked=checked</#if> /><label>保密</label>
		                                        <input type="radio" name="infoMarital" value="1" <#if customer.infoMarital=="1" >checked=checked</#if> /><label>未婚</label>
		                                        <input type="radio" name="infoMarital" value="2" <#if customer.infoMarital=="2" >checked=checked</#if> /><label>已婚</label>
		                                    </dd>
		                                </dl>
		                                <dl>
		                                    <dt>月收入：</dt>
		                                    <dd>
		                                        <select class="select" id="infoSalary" name="income">
		                                            <option selected value="-1">无收入</option>
													<option  value="0">2000元以下</option>
													<option  value="1">2000-3999元</option>
													<option  value="2">4000-5999元</option>
													<option  value="3">6000-7999元</option>
													<option  value="4">8000元以上</option>
		                                        </select>
		                                    </dd>
		                                </dl>
		                                <dl>
		                                    <dt>兴趣爱好：</dt>
		                                    <dd>
		                                        <textarea cols="80" rows="5" id="infoInterest" name="infoInterest"  class="text_area"><#if customer.infoInterest??>${customer.infoInterest} </#if></textarea>
		                                        <img id="infos_img" src="${basePath}/images/succ-ico.png" />
		                                        <label id="infos_msg"></label>
		                                    </dd>
		                                </dl>
		                                <dl>
		                                    <dt></dt>
		                                    <dd><input type="button" class="sub_btn" onclick="updateUserInfo()" value="提交" /></dd>
		                                </dl>
	                                </form>
	                            </div>
	                        </div>
                        </#if>
                        <div class="layout">
                            <div class="more_info">
                                <div class="tips">
                                    <p>完善更多个人信息，有助于我们为您提供更加个性化的服务；我们将尊重并保护您的隐私。</p>
                                </div>
                                <div class="education more_item">
                                    <h4><a class="add_education" href="javascript:void(0)" onclick="updateclick()">添加教育信息</a><span class="f14 fb">教育信息</span></h4>
                                    <table id="edu">
                                        
                                    </table>
                                    <form method="post" name="addedu">
									
                                    <div class="education_edit edit_box">
                                    
                                        <em></em>
                                        <dl>
                                            <dt>学校类型：</dt>
                                            <dd>
                                                <select class="select" name="schoolType" id="schoolType">
                                                    <option selected="selected" value="大学">大学</option>
                                                    <option value="高中">高中</option>
                                                    <option value="初中">初中</option>
                                                    <option value="小学">小学</option>
                                                </select>
                                            </dd>
                                        </dl>
                                        <dl>
                                            <dt>学校名称：</dt>
                                            <dd><input type="text" class="text" name="schoolName" id="schoolName"/><span id="titleSN" style="color:red;"></span></dd>
                                        </dl>
                                        <dl>
                                            <dt>院系或班级：</dt>
                                            <dd><input type="text" class="text" name="className" id="className"/><span id="titleClN" style="color:red;"></span>
                                            <input type="hidden" id="hi_uid" value="${customer.customerId}" name="customerId"/>
                                            <input type="hidden" id="eduId" name="educationId"/>
                                            </dd>
                                        </dl>
                                        <dl>
                                            <dt>入学时间：</dt>
                                            <dd>
                                            <select class="select" id=comeyear name="comeTime" > </select> 
                                            <p id="titleedu" style="color:red;"></p>
                                            </dd>
                                        </dl>
                                        <dl>
                                            <dt></dt>
                                            <dd><input type="button" class="sub_btn" value="提交" name="updateEdu" onclick="addEdu()"/><a class="education_cancel" href="javascript:void(0)" onclick="clearEdu()">取消</a></dd>
                                        </dl>
                                    </div>
                                    </form>
                                </div>
                                <div class="job_info more_item">
                                    <h4><a class="add_job" href="javascript:void(0)" onclick="updatejobclick()">添加职业信息</a><span class="f14 fb">职业信息</span></h4>
                                    <table id="voc">
                                        <tr >
                                            <th>单位名称</th>
                                            <th>工作时间</th>
                                            <th>操作</th>
                                        </tr>
                                    </table>
                                   <form name="addjob" method="post">
	                                    <div class="job_edit edit_box">
	                                        <em></em>
	                                        <dl>
	                                            <dt>单位名称：</dt>
	                                            <dd><input type="text" class="text" name="companyName" id="companyName"/><span id="titleCN" style="color:red;"></span>
	                                            <input type="hidden" id="job_uid" value="${customer.customerId}" name="customerId"/>
	                                             <input type="hidden" id="vocId" name="vocationId"/>
	                                            </dd>
	                                        </dl>
	                                        <dl>
	                                            <dt>工作时间：</dt>
	                                            <dd>
	                                                <select class="select" name="workTime" id=jobBegin> </select>
	                                                <label>-</label>
	                                                <select class="select" name="endTime" id=jobEnd> </select>
	                                                <span id="titleWT" style="color:red;"></span>
	                                            </dd>
	                                        </dl>
	                                        <dl>
	                                            <dt></dt>
	                                            <dd><input type="button" class="sub_btn" value="提交" onclick="addVoca()" name="updateVoc"/><a class="job_cancel" href="javascript:void(0)" onclick="clearJob()">取消</a></dd>
	                                        </dl>
	                                    </div>
	                                   
	                                   
	                                   <#--<div  class="titlevoca edit_box" style="width:170px;height:150px;margin-left:330px;">
	                                    	 <dl>
	                                            <dt></dt>
	                                            <dd>gfgddgdgdgdgd</dd>
	                                        </dl>
	                                         <dl>
	                                            <dt></dt>
	                                            <dd><input type="button" class="sub_btn" value="确定" onclick="vocaup()"/></dd>
	                                        </dl>
	                                    </div>--> 
                                    <form>
                                </div>
                            </div>
                        </div>
                        
                    </div>
                </div>
            </div><!-- END OF member_right -->
            <div class="cb"></div>
        </div><!-- END OF member_box -->
    </div>
    <div class="dialog s_dia dia2">
    	<div class="dia_tit clearfix">
            <h4 class="fl">提示</h4>
            <a class="dia_close fr" href="javascript:;" onclick="cls()"></a>
        </div><!--/dia_tit-->
        <div class="dia_cont">
        	<div class="dia_intro no_tc pt30">
        		<img class="vm mr10" id="f_img" alt="" src="${basePath}/images/mod_succ.png" />
            	<em id="con_00">修改成功！</em>
            </div>
            <div class="dia_ops mt20 tr">       
                 <a class="go_pay" id="go_pay_00" href="javascript:cls();">确定</a>
            </div><!--/dia_ops-->
        </div><!--/dia_cont-->
    </div><!--/dialog-->
     <div class="dialog s_dia dia4">
    	<div class="dia_tit clearfix">
            <h4 class="fl">提示</h4>
            <a class="dia_close fr" href="javascript:;" onclick="cls()"></a>
        </div><!--/dia_tit-->
        <div class="dia_cont">
        	<div class="dia_intro no_tc pt30">
        		<img class="vm mr10" alt="" src="${basePath}/images/q_mark.png" />
            	<em id="titleerr">头像上传成功！</em>
            </div>
            <div class="dia_ops mt20 tr">       
                 <a class="go_pay" id="go_pay_00" href="javascript:cls();">确定</a>
            </div><!--/dia_ops-->
        </div><!--/dia_cont-->
    </div><!--/dialog-->
	<script type="text/javascript" src="${basePath}/js/jquery-1.7.2.min.js"></script>
   <#--引入底部 <#include "/index/bottom.ftl" /> -->
    <#if (topmap.temp)??>
		<#if (topmap.temp.tempId==1)>
			<#include "../index/bottom.ftl">
		<#else>
		    <#include "../index/newbottom.ftl" />
		</#if>
	</#if>
    
    <input type=hidden id=Province value=
	<#if customer.infoProvince??>
		${customer.infoProvince}
	</#if> />
	<input type=hidden id=City value=
	<#if customer.infoCity??>
		${customer.infoCity}
	</#if> />
	<input type=hidden id=County value=
	<#if customer.infoCounty??>
		${customer.infoCounty}
	</#if> />
	<input type=hidden id=Street value=
	<#if customer.infoStreet??>
		${customer.infoStreet}
	</#if> />
	<input type=hidden id=birth value=
	<#if customer.infoBirthday??>
		${customer.infoBirthday}
	</#if> />
	<input type=hidden id=salary value=
	<#if customer.infoSalary??>
		${customer.infoSalary}
	</#if> />

<script type="text/javascript" src="${basePath}/js/default.js"></script>
<script type="text/javascript" src="${basePath}/js/jcarousellite_1.0.1.js"></script>
<script type="text/javascript" src="${basePath}/js/tab-switch.js"></script>
<script type="text/javascript" src="${basePath}/js/customer/customer.js"></script>
<script type="text/javascript" src="${basePath}/js/customer/scroller.js"></script>
<script type="text/javascript" src="${basePath}/js/customer/userInfo.js"></script>

<script type="text/javascript" src="${basePath}/js/customer/dateclass.js"></script>
<script type="text/javascript" src="${basePath}/js/customer/yearclass.js"></script>
<script type="text/javascript" src="${basePath}/js/customer/education.js"></script>
<script type="text/javascript">
$(document).ready(function(){
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
	$('.add_education').click(function(){
		$('.education_edit').slideDown('fast');
	});
	$('.education_cancel').click(function(){
		$('.education_edit').slideUp('fast');
		$("#schoolType").val("大学");
		$("#schoolName").val("");
		$("#className").val("");
		$("#comeyear").val("");
		$("#titleedu").text("");
	});
	$('.add_job').click(function(){
		$('.job_edit').slideDown('fast');
	});
	$('.job_cancel').click(function(){
		$('.job_edit').slideUp('fast');
		$("#companyName").val("");
		$("#jobBegin").val("");
		$("#jobEnd").val("");
		$("#title").text("");
	});
    $(".pro_sort").addClass("pro_sort_close");
    $(".memeber_left div:eq(2) ul li:eq(0)").addClass("cur");
	loadProvice();
	selectLocationOption($("#Province").val(),$("#City").val(),$("#County").val(),$("#Street").val(),'infoProvince','infoCity','infoCounty','infoStreet');
	selectBirthday($("#birth").val());
	selectSalary($("#salary").val());
    
});

var selYear = window.document.getElementById("year");
var selMonth = window.document.getElementById("month");
var selDay = window.document.getElementById("day"); 
new DateSelector(selYear,selMonth ,selDay);
var comeYear= window.document.getElementById("comeyear");
new YearSelector(comeYear);
var jobBegin= window.document.getElementById("jobBegin");
new YearSelector(jobBegin);
var jobEnd= window.document.getElementById("jobEnd");
new YearSelector(jobEnd);

function win(){
	var _wd = $(window).width();
	var _hd = $(window).height();
	$(".dialog").css("top",(_hd - $(".dialog").height())/2).css("left",(_wd - $(".dialog").width())/2);
};
</script>
</@htmlBody>
