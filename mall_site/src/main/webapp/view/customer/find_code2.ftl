<#include "../include/common.ftl">
<@htmlHead title="找回密码"  lang="en">
<link rel="stylesheet" type="text/css" href="${basePath}/css/jd.base.min.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/css/jd.style.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/css/index.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/pages.css"/>
<script type="text/javascript" src="${basePath}/js/jquery.js"></script>
<script type="text/javascript" src="${basePath}/js/app.js"></script>
<script type="text/javascript" src="${basePath}/js/customer/findcode.js"></script>
<script type="text/javascript" src="${basePath}/js/default.js"></script>
<style>
    .reg_success .notice2 {
        font-size: 25px;
        font-family: "微软雅黑", "宋体";
        color: #f60;
        margin-bottom: 20px;
    }
</style>
</@htmlHead>
<#--<html lang="en">
<head>
<#assign basePath=request.contextPath>
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
	<title>找回密码</title>
	<link rel="stylesheet" type="text/css" href="${basePath}/css/jd.base.min.css"/>
	<link rel="stylesheet" type="text/css" href="${basePath}/css/jd.style.css"/>
    <link rel="stylesheet" type="text/css" href="${basePath}/css/index.css" />
    <link rel="stylesheet" type="text/css" href="${basePath}/css/pages.css"/>
    <script type="text/javascript" src="${basePath}/js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="${basePath}/js/jquery.js"></script>
	<script type="text/javascript" src="${basePath}/js/app.js"></script>
	<script type="text/javascript" src="${basePath}/js/customer/findcode.js"></script>
    <script type="text/javascript" src="${basePath}/js/default.js"></script>
	<style>
		.reg_success .notice2 {
            font-size: 25px;
            font-family: "微软雅黑", "宋体";
            color: #f60;
            margin-bottom: 20px;
        }
	</style>
</head>-->
<@htmlBody>
<input type="hidden" id="basePath" value="${basePath}"/>
    <#if (topmap.temp)??>
        <#if (topmap.temp.tempId==8)>
            <#include "../index/newtop3.ftl">
        <#elseif (topmap.temp.tempId==9)>
            <#include "../index/newtop4.ftl">
        <#elseif (topmap.temp.tempId==10)>
            <#include "../index/newtop5.ftl">
        <#elseif (topmap.temp.tempId==11)>
            <#include "../index/newtop6.ftl">
        <#elseif (topmap.temp.tempId==12)>
            <#include "../index/newtop7.ftl">
        <#elseif (topmap.temp.tempId==13)>
            <#include "../index/newtop8.ftl">
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
            <#include "../index/newtop.ftl"/>
        </#if>
    </#if>
<#include "newtop.ftl"/>
    <#if (user?? && utype??) >
    <div class="container pb50">
    	<div class="n_step">
    		<p class="title">找回密码</p>
    		<div class="n_step_con">
    			<div class="n_step2"></div>
    			<ul class="ml10 clearfix">
    				<li class="p100 prev">填写账户名</li>
    				<li class="p130 cur">验证身份</li>
    				<li class="p130">设置新密码</li>
    				<li>完成</li>
    			</ul>
    		</div><!--n_step_con-->
    		<#if (utype=='mobile')>
	    		<#if (user.isMobile=='1')>
	    		<div class="n_password">
	    			<div class="n_item clearfix mb20">
	    				<span class="label fl">验证方式：</span>
	    				<div class="fl">
	    					<select class="vdSel">
	                            <option value="v01">已验证手机</option>
	                            <#if ((user.isEmail=='1'))><option value="v02">已验证邮箱</option></#if>             
	                        </select>
	    					<div class="ne_tips hide">您输入的用户名有误</div>
	    				</div>
	    			</div><!--n_item-->
	                <div class="n_item clearfix mb20">
	                    <span class="label fl">昵称：</span>
	                    <div class="fl">
	                        <strong>${user.customerNickname}</strong>
	                    </div>
	                </div><!--n_item-->
	                <div class="n_item clearfix mb20">
	                    <span class="label fl">已验证手机：</span>
	                    <div class="fl">
	                        <strong>

                    <#if (user.infoMobile?? && user.infoMobile?length>3)>
                    <#assign mo="${user.infoMobile?substring(3,user.infoMobile?length-3)}" />
                    <#assign mob="${user.infoMobile?replace(mo,'*****')}" />
                    ${mob}
                    </#if>
							</strong>
	                    </div>
	                </div><!--n_item-->
	    			<div class="n_item clearfix mb20">
	    				<span class="label fl">短信验证码：</span>
	    				<div class="fl">
	    					<input type="text" id="mcode" placeholder="请输入验证码" class="short_text mr20"/>
	    					<a href="#" class="hq_code" id="sendCode" onclick="getCode()" >获取短信验证码</a>
	                        <div></div>
	    					<div class="ne_tips mcode_tip hide">您输入的验证码有误</div>
	                        <div class="mt20 col6 hide" id="code_tip">校验码已发出，请注意查收短信，如果没有收到，你可以在<span class="ju_s">117</span>秒后要求系统重新发送</div>
	    				</div>
	    			</div><!--n_item-->
	    			<div class="n_item clearfix mb20">
	    				<span class="label fl">&nbsp;</span>
	    				<div class="fl">
	    					<button class="n_nextstep sub_btn" onclick="tofindpwd()" >下一步</button>
	    				</div>
	    			</div><!--n_item-->
	    		</div><!--n_password-->
	    		<script>
		                document.onkeydown=function(event){
							var e = event || window.event || arguments.callee.caller.arguments[0];
							if(e && e.keyCode==13){ // enter 键
								tofindpwd();
							}
						}; 
		        </script>
	    		<#else>
			    		<#if ((user.isEmail=='1'))>
		    			<div class="n_password">
	    			<div class="n_item clearfix mb20">
	    				<span class="label fl">验证方式：</span>
	    				<div class="fl">
	    					<select class="vdSel">
	    					   <option value="v02">已验证邮箱</option> 
	                           <#if (user.isMobile=='1')> <option value="v01">已验证手机</option></#if>        
	                        </select>
	    					<div class="ne_tips hide">您输入的用户名有误</div>
	    				</div>
	    			</div><!--n_item-->	                
	                <div class="n_item clearfix mb20">
	                    <span class="label fl">已验证邮箱：</span>
	                    <div class="fl">
	                        <strong id="vemail">
                            <#if (user.infoEmail?? && user.infoEmail?length>3)>
	                        <#assign email="${user.infoEmail?substring(1,user.infoEmail?index_of('@')-1)}" />
							<#assign emailc="${user.infoEmail?replace(email,'*****')}" />
							${emailc}
                            </#if>
	                        </strong>
	                    </div>
	                </div><!--n_item-->
	    			<div class="n_item clearfix mb20">
	    				<span class="label fl">验证码：</span>
	    				<div class="fl">
	    					<input type="text" placeholder="请输入验证码" class="short_text mr20" id="code"/>
	    					<img id="checkCodeImg" src="${basePath}/patchca.htm" alt="验证码" style="cursor:pointer;" onclick="this.src=this.src+'?'+Math.random(); "/>
    						<a href="javascript:void(0)" id="checkCodeA" class="ml20 ju_s">换一张</a>
	                        <div></div>
	    					<div class="ne_tips patchca_tips hide">您输入的验证码有误</div>
	    				</div>
	    			</div><!--n_item-->
	    			<div class="n_item clearfix mb20">
	    				<span class="label fl">&nbsp;</span>
	    				<div class="fl">
	    					<button class="n_nextstep" onclick="sendEmail()">发送验证邮件</button>
	    				</div>
	    			</div><!--n_item-->
	    		</div><!--n_password-->
	    		<script>
                	document.onkeydown=function(event){
						var e = event || window.event || arguments.callee.caller.arguments[0];
					  	if(e && e.keyCode==13){ // enter 键
					     	sendEmail();
					    }
					}; 
		       </script>
			    		<#else>
			    		<div class="reg_success" style="margin-top: 20px; padding-left:30px;">
							<div class="notice2">
							很抱歉,您没设置验证手机,也没设置验证邮箱,暂时无法找回密码！<span>
							 </div>
						</div>
			    		</#if>
	    		</#if>
    		<#else>
	    		<#if ((user.isEmail=='1'))>
	    		<div class="n_password">
	    			<div class="n_item clearfix mb20">
	    				<span class="label fl">验证方式：</span>
	    				<div class="fl">
	    					<select class="vdSel">
	    						<option value="v02">已验证邮箱</option>
	                            <#if (user.isMobile=='1')><option value="v01">已验证手机</option></#if>	                                          
	                        </select>
	    					<div class="ne_tips hide">您输入的用户名有误</div>
	    				</div>
	    			</div><!--n_item-->	                
	                <div class="n_item clearfix mb20">
	                    <span class="label fl">已验证邮箱：</span>
	                    <div class="fl">
	                        <strong>
                    <#if (user.infoEmail?? && user.infoEmail?length>3)>
	                        <#assign email="${user.infoEmail?substring(1,user.infoEmail?index_of('@')-1)}" />
							<#assign emailc="${user.infoEmail?replace(email,'*****')}" />
							${emailc}
                    </#if>
							</strong>
	                    </div>
	                </div><!--n_item-->
	    			<div class="n_item clearfix mb20">
	    				<span class="label fl">验证码：</span>
	    				<div class="fl">
	    					<input type="text" placeholder="请输入验证码" class="short_text mr20" id="code"/>
	    					<img id="checkCodeImg" src="${basePath}/patchca.htm" alt="验证码" style="cursor:pointer;" onclick="this.src=this.src+'?'+Math.random(); "/>
    						<a href="javascript:void(0)" id="checkCodeA" class="ml20 ju_s">换一张</a>
	                        <div></div>
	    					<div class="ne_tips patchca_tips hide">您输入的验证码有误</div>
	    				</div>
	    			</div><!--n_item-->
	    			<div class="n_item clearfix mb20">
	    				<span class="label fl">&nbsp;</span>
	    				<div class="fl">
	    					<button class="n_nextstep" onclick="sendEmail()">发送验证邮件</button>
	    				</div>
	    			</div><!--n_item-->
	    		</div><!--n_password-->
	    		<script>
                	document.onkeydown=function(event){
						var e = event || window.event || arguments.callee.caller.arguments[0];
					  	if(e && e.keyCode==13){ // enter 键
					     	sendEmail();
					    }
					}; 
		       </script>
	    		<#else>
		    		<#if (user.isMobile=='1')>
			    		<div class="n_password">
	    			<div class="n_item clearfix mb20">
	    				<span class="label fl">验证方式：</span>
	    				<div class="fl">
	    					<select class="vdSel">
	                            <option value="v01">已验证手机</option>
	                            <#if ((user.isEmail=='1'))><option value="v02">已验证邮箱</option></#if>             
	                        </select>
	    					<div class="ne_tips hide">您输入的用户名有误</div>
	    				</div>
	    			</div><!--n_item-->
	                <div class="n_item clearfix mb20">
	                    <span class="label fl">昵称：</span>
	                    <div class="fl">
	                        <strong>${user.customerNickname}</strong>
	                    </div>
	                </div><!--n_item-->
	                <div class="n_item clearfix mb20">
	                    <span class="label fl">已验证手机：</span>
	                    <div class="fl">
	                        <strong>
                                <#if (user.infoMobile?? && user.infoMobile?length>3)>
	                        <#assign mo="${user.infoMobile?substring(3,user.infoMobile?length-3)}" />
							<#assign mob="${user.infoMobile?replace(mo,'*****')}" />
							${mob}
                                </#if>
							</strong>
	                    </div>
	                </div><!--n_item-->
	    			<div class="n_item clearfix mb20">
	    				<span class="label fl">短信验证码：</span>
	    				<div class="fl">
	    					<input type="text" id="mcode" placeholder="请输入验证码" class="short_text mr20"/>
	    					<a href="#" class="hq_code" id="sendCode" onclick="getCode()" >获取短信验证码</a>
	                        <div></div>
	    					<div class="ne_tips mcode_tip hide">您输入的验证码有误</div>
	                        <div class="mt20 col6 hide" id="code_tip">校验码已发出，请注意查收短信，如果没有收到，你可以在<span class="ju_s">117</span>秒后要求系统重新发送</div>
	    				</div>
	    			</div><!--n_item-->
	    			<div class="n_item clearfix mb20">
	    				<span class="label fl">&nbsp;</span>
	    				<div class="fl">
	    					<button class="n_nextstep sub_btn" onclick="tofindpwd()" >下一步</button>
	    				</div>
	    			</div><!--n_item-->
	    		</div><!--n_password-->
	    		<script>
                	document.onkeydown=function(event){
						var e = event || window.event || arguments.callee.caller.arguments[0];
					  	if(e && e.keyCode==13){ // enter 键
					     	tofindpwd();
					    }
					}; 
		       </script>
		    		<#else>
		    		<div class="reg_success" style="margin-top: 20px; padding-left:30px;">
				    	<div class="notice2">
				        	很抱歉,您没设置验证手机,也没设置验证邮箱,暂时无法找回密码！<span>
				        </div>
					</div>
		    		
		    		
		    		</#if>
	    		
	    		</#if>
    		
    		</#if>
    	</div><!--n_step-->
    </div><!--container-->
<#else>
   <div class="reg_success">
		    	<div class="notice2">
		        	<img alt="" src="${basePath}/images/mod_war.png">参数异常！<span>
		        </div>
		        <div class="notice3">
		            <strong><span id="time">5</span>秒自动进入<a href="${basePath}/index.html">“首页”</a></strong></span> 
		        </div>
	</div>
</#if><!--user??-->

<div class="bh-mask"></div>
	<div id="ctDia" class="bh-dialog dia0">
	    <div class="dia-tit">
	        <h4>加入收藏</h4>
	        <a class="dia-close" href="javascript:;" onclick="scls(this)"></a>
	    </div>
	    <div class="dia-cont">
	        <p style="text-align: center">请使用菜单栏或Ctrl+D进行添加！</p>
	    </div>
	    <div class="dia-btn"><a href="javascript:;" onclick="scls(this)">确定</a></div>
	</div>



<div class="dialog s_dia dia2">
    <div class="dia_tit clearfix">
        <h4 class="fl info1">提示</h4>
        <a class="dia_close fr" href="javascript:;" onclick="cls()"></a>
    </div><!--/dia_tit-->
    <div class="dia_cont" style="text-align: center;">
        <div class="dia_intro no_tc pt30">
            <img class="vm mr10 info_tip_img2" alt="" width="32px" height="32px" src="${basePath}/images/q_mark.png" />
            <em class="info_tip_content2 info2">确定取消收藏该商品吗？</em>
            <em class="info_tip_content2_1"></em>
        </div>
        <div class="dia_ops mt20 tr" style="display:inline-block;">
            <a class="info_tip_submit2 go_pay" href="javascript:cls();">确定</a>
        </div><!--/dia_ops-->
    </div><!--/dia_cont-->
</div><!--/dialog-->


<#if (topmap.temp)??>
		<#if (topmap.temp.tempId==1)>
			<#include "../index/bottom.ftl">
		<#else>
		    <#include "../index/newbottom.ftl" />
		</#if>
	</#if>
	
	<script>
		$(function(){
			$(".vdSel").change(function(){
				if($(this).val() == "v01") {
					window.location.href = "${basePath}/validuser.html?type=mobile";
				} else if($(this).val() == "v02") {
					window.location.href = "${basePath}/validuser.html?type=email";
				}; 
			});
		});
	</script>
</@htmlBody>