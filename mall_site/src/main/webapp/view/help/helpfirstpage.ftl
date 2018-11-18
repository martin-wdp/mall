<#include "../include/common.ftl">
<@htmlHead title='${topmap.systembase.bsetName}-帮助中心首页'>
<link href="${basePath}/homeFirstPage/css/base.min.css" type="text/css" rel="stylesheet"/>
<link href="${basePath}/homeFirstPage/css/style.css" type="text/css" rel="stylesheet"/>
</@htmlHead>
<@htmlBody>
<div>
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
	</div>
<div class="container">
	<h1 class="help_page_title">消费者帮助中心</h1>
	<div class="help_right fr">
    	<div class="safe_set mb10">
        	<h2>账户安全设置</h2>
            <ul>
            	<li>
                	<a href="${basePath}/customer/securitycenter.html"><img alt="" src="${basePath}/homeFirstPage/images/safe_set_01.jpg" />安全中心</a>
              </li>
                <li>
                	<a href="${basePath}/validate/validateidentity.html?type=email"><img alt="" src="${basePath}/homeFirstPage/images/safe_set_02.jpg" />验证邮箱</a>
                </li>
                <li>
                	<a href="${basePath}/validate/validateidentity.html?type=mobile"><img alt="" src="${basePath}/homeFirstPage/images/safe_set_03.jpg" />验证手机</a>
                </li>
                <li>
                	<a href="${basePath}/validate/validateidentity.html?type=pwd"><img alt="" src="${basePath}/homeFirstPage/images/safe_set_04.jpg" />修改密码</a>
                </li>
          </ul>
            <div class="cb"></div>
      </div><!-- safe_set -->
      <!--  <div class="quick_link mb10">
        	<ul>
        		<li><a href="${basePath}/help/1"><img alt="" src="${basePath}/homeFirstPage/images/quick_link_01.jpg" />支付方式</a></li>
                <li><a href="${basePath}/help/3"><img alt="" src="${basePath}/homeFirstPage/images/quick_link_02.jpg" />退换货</a></li>
                <li><a href="${basePath}/help/69"><img alt="" src="${basePath}/homeFirstPage/images/quick_link_03.jpg" />配送方式</a></li>
                <li><a href="${basePath}/help/3"><img alt="" src="${basePath}/homeFirstPage/images/quick_link_04.jpg" />退款说明</a></li>
                <li><a href="${basePath}/help/73"><img alt="" src="${basePath}/homeFirstPage/images/quick_link_05.jpg" />发票制度</a></li>
                <li><a href="${basePath}/help/56"><img alt="" src="${basePath}/homeFirstPage/images/quick_link_06.jpg" />价格保护</a></li>
            </ul>
            <div class="cb"></div>
        </div><!-- /quick_link -->

        <div class="ad mb10">
        	<a href="#"><img alt="" src="${basePath}/homeFirstPage/images/images_072.jpg" /></a>
        </div>
        <div class="faq mb10">
        	<h2>常见问题</h2>
            <div class="body">
            	
                <div class="faq_list">
                	<ul>
                		<#list map.help as help >
                		<#if (help_index==15)><#break/></#if>
                		<#if (help_index<3)>
                        <li class="top"><span>${help_index+1}</span><a href="${basePath}/help/${help.helpId}">${help.helpTitle}</a></li>
                  		<#else>
                        <li><span>${help_index+1}</span><a href="${basePath}/help/${help.helpId}">${help.helpTitle}</a></li>
                        </#if>
              		 </#list>
                    </ul>
                </div>
            </div>
        </div><!-- /faq -->
    </div>
    
    <div class="help_left fl">
    	<#list map.helpCates as helpcate>
    	<#if (helpcate.helpcateId==1 || helpcate.helpcateId==3 )>
    	<div class="self_service mb10">
        	<h2 >${helpcate.helpcateName!''}</h2>
            <div class="body">        
            	<ul> 
            	<#list helpcate.childs as child> 
            		<#if child.helpImg??&&child.helpId!='112'>
            		<li><a href="${basePath}/help/${child.helpId}"><img alt="" src="${child.helpImg!''}" /></a></li>
            		</#if>
                </#list>	
                </ul>
                <div class="cb"></div>
            </div>
        </div><!-- /self_service -->
        
 		</#if>
        </#list>
        
        <div class="novice_teach mb10">
        <#list map.helpCates as helpcate>
        <#if (helpcate.helpcateId==20)>
        	<h2>${helpcate.helpcateName!''}</h2>
            <div class="body">
            	<ul>
            	<#list helpcate.childs as child> 
            	<#if (child_index != 4)>
                	<li><a href="${basePath}/help/${child.helpId}"><img alt="" src="${child.helpImg!''}" />${child.helpTitle}</a></li>
                <#else>    
                    <li class="last"><a href="${basePath}/help/${child.helpId}"><img alt="" src="${child.helpImg!''}" />${child.helpTitle}</a></li>
               </#if>
               </#list>
                </ul>
             <div class="cb"></div>
          </#if>
          </#list>   
        </div>
            
      </div><!-- /novice_teach -->
        <div class="human_service mb10" style="display:none">
        	<h2>人工服务</h2>
            <div class="body">
            	<ul>
                	<li>
                    	<img alt="" src="${basePath}/homeFirstPage/images/human_service_01.jpg" />
                        <a class="red_btn" href="#">立即使用</a>
                    </li>
                    <li>
                    	<img alt="" src="${basePath}/homeFirstPage/images/human_service_02.jpg" />
                        <a class="red_btn" href="#">立即使用</a>
                    </li>
                    <li>
                    	<img alt="" src="${basePath}/homeFirstPage/images/human_service_03.jpg" />
                        <a class="red_btn" href="#">立即使用</a>
                    </li>
                    <li>
                    	<img alt="" src="${basePath}/homeFirstPage/images/human_service_04.jpg" />
                        <a class="red_btn" href="#">立即使用</a>
                    </li>
                    <li class="last">
                    	<img alt="" src="${basePath}/homeFirstPage/images/human_service_05.jpg" />
                        <a class="red_btn" href="#">立即使用</a>
                    </li>
                </ul>
                <div class="cb"></div>
            </div>
        </div><!-- /human_service -->
    </div>
    <div class="cb"></div>
</div>

<script type="text/javascript" src="${basePath}/js/common.js"></script>
<#--引入底部-->
    <#if (topmap.temp)??>
		<#if (topmap.temp.tempId==1)>
			<#include "../index/bottom.ftl">
		<#else>
		    <#include "../index/newbottom.ftl" />
		</#if>
	</#if>
</@htmlBody>