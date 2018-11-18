<#include "../include/common.ftl">
<@htmlHead title="账户安全-${topmap.systembase.bsetName}">
<link rel="stylesheet" type="text/css" href="${basePath}/css/pages.css" />
<style>
    .n_password .n_item span.label{width: 140px;}
</style>
</@htmlHead>
<#--<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>账户安全-${topmap.systembase.bsetName}</title>
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
<link rel="stylesheet" type="text/css" href="${basePath}/css/pages.css" />
    <script type="text/javascript" src="${basePath}/js/jquery-1.7.2.min.js"></script>
    <style>
    .n_password .n_item span.label{width: 140px;}
</style>
</head>-->

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
        <div style="background: #f5f5f5;">
            <div class="container clearfix pt20 pb10">
                <!--new_member_left-->
            <#include "newleftmenu.ftl"/>
                <div class="new_member-right">
                    <div class="new_order_list">
                        <div class="n-title">
                        <#if type??>
                            <#if (type=='pwd')>
                                修改密码
                            </#if>
                            <#if (type=='mobile') && cust.isMobile == '1'>
                                修改手机
                            <#elseif (type=='mobile') && cust.isMobile == '0'>
                                验证手机
                            </#if>
                            <#if (type=='email') && cust.isEmail == '1'>
                                修改邮箱
                            <#elseif (type=='email') && cust.isEmail == '0'>
                                验证邮箱
                            </#if>
                        </#if>
                        </div>
                        <div class="new-safe-level pt20">
                            安全级别：
                            <div class="n_per_bar" style="width: 100px;"><span style="width: <#if  cust.isEmail == '0' &&  cust.isMobile == '0'> 33%;<#elseif (cust.isEmail == '1' &&  cust.isMobile == '0')||(cust.isEmail == '0' &&  cust.isMobile == '1')>66% <#elseif cust.isEmail == '1' &&  cust.isMobile == '1'>100%</#if>"></span></div>
                        <#if  cust.isEmail == '0' &&  cust.isMobile == '0'>
                            <span style="color: #71b247;" class="pl20">低级</span>
                            <span class="safe_bar low_sf"></span>
                        <#elseif cust.isEmail == '1' &&  cust.isMobile == '0'>
                            <span style="color: #71b247;" class="pl20">中级</span>
                            <span class="safe_bar md_sf"></span>
                        <#elseif cust.isEmail == '0' &&  cust.isMobile == '1'>
                            <span style="color: #71b247;" class="pl20">中级</span>
                            <span class="safe_bar md_sf"></span>
                        <#elseif cust.isEmail == '1' &&  cust.isMobile == '1'>
                            <span style="color: #71b247;" class="pl20">高级</span>
                            <span class="safe_bar hg_sf"></span>
                        </#if>
                            <em>建议您启动全部安全设置，以保障账户及资金安全。</em>
                        </div>
                        <div class="s-amend">
                            <div class="amend-step">
                                <div class="amend-step-2"></div>
                                <ul class="clearfix">
                                    <li>验证身份</li>
                                    <li><#if type??><#if (type=='pwd')>填写密码</#if><#if (type=='mobile')>填写手机</#if><#if (type=='email')>填写邮箱</#if></#if></li>
                                    <li>完成</li>
                                </ul>
                                <form method="post" action="${basePath}/validate/modifypem.htm" id="upMobile">
                                    <input value="${(type)!''}" type="hidden" id="e_type" name="type" />
                                    <input type="hidden" id="CSRFToken" name="csrFToken" value="${CSRFToken!''}"/>
                                    <input value="${checkedUsing!''}" type="hidden" id="checkedUsing">
                                        <#if (type=='pwd' )>
                                            <div class="n_password ml30">
                                                <div class="n_item clearfix mb20">
                                                    <span class="label fl"><span class="tip-d">*</span>新的登录密码：</span>
                                                    <div class="fl">
                                                    <#--<input type="hidden" value="" name="password" class="hi_pwd"/>-->
                                                        <input type="password" style="display:none">
                                                        <input type="password"tabindex="1" name="newStr" id="npwd" class="mr20" autocomplete="off">
                                                        <div class="npwd_tip vd_tip" style="display:none;color:#dd6330">密码错误</div>
                                                    </div>
                                                </div>
                                                <div class="n_item clearfix mb20">
                                                    <span class="label fl"><span class="tip-d">*</span>请再输入一次密码：</span>
                                                    <div class="fl">
                                                        <input type="password" tabindex="2" id="repwd" class="mr20">
                                                        <div class="repwd_tip ne_tips hide" id="repwdtip">您输入的密码不一致</div>
                                                    </div>
                                                </div>
                                                <div class="n_item clearfix mb20">
                                                    <span class="label fl"><span class="tip-d">*</span>验证码：</span>
                                                    <div class="fl">
                                                        <input type="text" tabindex="3"id="code" name="code" placeholder="请输入验证码" class="short_text mr20">
                                                        <img id="checkCodeImg" class="vm ml5" src="${basePath}/patchca.htm" alt="验证码" style="cursor:pointer;" onclick="this.src=this.src+'?'+Math.random(); " />
                                                        <a id="checkCodeA" href="javascript:void(0)" class="ml20 ju_s f14"><span class="col6">看不清？</span ">换一张</a>
                                                        <div class="ne_tips hide code_tip">您输入的验证码有误</div>
                                                    </div>
                                                </div>
                                                <div class="n_item clearfix mb20">
                                                    <span class="label fl">&nbsp;</span>
                                                    <input type="button" class="nw-submit" onclick="updatepem('${type}')"  value="提交" >
                                                    <script>
                                                        document.onkeydown=function(event){
                                                            var e = event || window.event || arguments.callee.caller.arguments[0];
                                                            if(e && e.keyCode==13){ // enter 键
                                                                updatepem('${type}');
                                                            }
                                                        };
                                                    </script>
                                                </div>
                                            </div>
                                        <#elseif (type == 'mobile')>
                                            <div class="n_password ml30">
                                                <div class="n_item clearfix mb20">
                                                    <span class="label fl">我的手机号：</span>
                                                    <div class="fl">
                                                        <input class="text mr20" name="newStr" maxLength="11" tabindex="1" id="mobile" type="text" />
                                                        <button class="send-mes-prev" id="sendCode"  onclick="getCode()" disabled="disabled" >发送短信验证码</button>
                                                        <div class="mobile_tip vd_tip" style="display:block;color:#cc0000;line-height:normal;">&nbsp;</div>
                                                        <div id="m_tip" class="m_tip vd_tip pt10" style="display: none;">校验码已发出，请注意查收短信，如果没有收到，您可以在<span class="timeleft">59</span>秒后要求系统重新发送</div>
                                                    </div>
                                                </div>
                                                <div class="n_item clearfix mb20">
                                                    <span class="label fl">请输入短信验证码：</span>
                                                    <div class="fl">
                                                        <input class="text yz_text" tabindex="2" id="mcode" type="text" style="width:110px;" disabled="disabled" />
                                                        <div class="mcode_tip vd_tip" style="display:none;color:#cc0000">短信验证码错误</div>
                                                    </div>
                                                </div>
                                                <div class="n_item clearfix mb20">
                                                    <span class="label fl"><span class="tip-d">*</span>验证码：</span>
                                                    <div class="fl">
                                                        <input type="text" tabindex="3"id="code" name="code" placeholder="请输入验证码" class="short_text mr20">
                                                        <img id="checkCodeImg" class="vm ml5" src="${basePath}/patchca.htm" alt="验证码" style="cursor:pointer;" onclick="this.src=this.src+'?'+Math.random(); " />
                                                        <a id="checkCodeA" href="javascript:void(0)" class="ml20 ju_s f14"><span class="col6">看不清？</span ">换一张</a>
                                                        <div class="ne_tips hide code_tip">您输入的验证码有误</div>
                                                    </div>
                                                </div>
                                                <div class="n_item clearfix mb20">
                                                    <span class="label fl">&nbsp;</span>
                                                    <input type="button" class="nw-submit" onclick="updatepem('${type}')"  value="提交" >
                                                    <script>
                                                        document.onkeydown=function(event){
                                                            var e = event || window.event || arguments.callee.caller.arguments[0];
                                                            if(e && e.keyCode==13){ // enter 键
                                                                updatepem('${type}');
                                                            }
                                                        };
                                                    </script>
                                                </div>
                                            </div>
                                        <#elseif (type == 'email')>
                                            <div class="n_password ml30">
                                                <div class="n_item clearfix mb20">
                                                    <span class="label fl">我的邮箱：</span>
                                                    <div class="fl">
                                                        <input class="text mr20" id="email" type="text" />
                                                        <div class="email_tip vd_tip" style="display:none;color:#cc0000">&nbsp;</div>
                                                        <input value="${(custInfo.infoEmail)!''}" type="hidden" id="e_hide" />
                                                    </div>
                                                </div>
                                                <div class="n_item clearfix mb20">
                                                    <span class="label fl"><span class="tip-d">*</span>验证码：</span>
                                                    <div class="fl">
                                                        <input type="text" tabindex="3"id="code" name="code" placeholder="请输入验证码" class="short_text mr20">
                                                        <img id="checkCodeImg" class="vm ml5" src="${basePath}/patchca.htm" alt="验证码" style="cursor:pointer;" onclick="this.src=this.src+'?'+Math.random(); " />
                                                        <a id="checkCodeA" href="javascript:void(0)" class="ml20 ju_s f14"><span class="col6">看不清？</span ">换一张</a>
                                                        <div class="ne_tips hide">您输入的验证码有误</div>
                                                    </div>
                                                </div>
                                                <div class="n_item clearfix mb20">
                                                    <span class="label fl">&nbsp;</span>
                                                    <div class="fl">
                                                        <input id="sendValidEmail" type="button" class="send-mail-yj" onclick="updatepem('${type}')" value="发送验证邮件" >
                                                    </div>
                                                </div>
                                                <script>
                                                    document.onkeydown=function(event){
                                                        var e = event || window.event || arguments.callee.caller.arguments[0];
                                                        if(e && e.keyCode==13){ // enter 键
                                                            updatepem('${type}');
                                                        }
                                                    };
                                                </script>
                                            </div>
                                        </#if>
                                    </form>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
        <div class="mask"></div>
        <div class="member-dialog dia2">
            <div class="member-dialog-body">
                <div class="title"><a href="javascript:;" onclick="cls()">×</a>提示</div>
                <div class="tc">
                    <div class="que-delete clearfix">
                        <img src="${basePath}/images/images_l6.png"/>
                        <div class="fl tl">
                            <p class="f16 red">网络连接超时，请您稍后重试</p>
                            <div class="m-btn mt20">
                                <a class="" href="javascript:cls();">确定</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


<div class="member-dialog dia3">
    <div class="member-dialog-body">
        <div class="title"><a href="javascript:;" onclick="cls()">×</a>提示</div>
        <div class="tc">
            <div class="que-delete clearfix">
                <img src="${basePath}/images/images_l6.png"/>
                <div class="fl tl">
                    <p class="f16 red" id="errorContent"></p>

                    <div class="m-btn mt20">
                        <a href="javascript:;" onclick="cls()">确定</a>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>

<div class="member-dialog dia4">
    <div class="member-dialog-body">
        <div class="title"><a href="javascript:;" onclick="cls()">×</a>提示</div>
        <div class="tc">
            <div class="que-delete clearfix">
                <img src="${basePath}/images/images_l6.png"/>
                <div class="fl tl">
                    <p class="f16 red">是否去登陆？</p>

                    <div class="m-btn mt20">
                        <a href="javascript:;" onclick="javascript:window.location.href="${basePath}/login.html">确定</a>
                        <a href="javascript:;" onclick="cls()">确定</a>
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
<script type="text/javascript" src="${basePath}/js/customer/validatepwd.js"></script>
<script type="text/javascript" src="${basePath}/js/newapp.js"></script>
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
    $(".pro_sort").addClass("pro_sort_close");
    $(".new_member_left div:eq(3) ul li:eq(1)").addClass("cur");
});
</script>
</@htmlBody>
