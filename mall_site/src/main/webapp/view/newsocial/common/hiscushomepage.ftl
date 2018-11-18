<input type="hidden" id="customerId" value="${cusId!''}">
<input type="hidden" id="basePath" value="${basePath}">
	<div class="mb20">
		<div class="m_focus_list border_left_dashed fr">
        	<div class="title">
            	<h2>TA的关注</h2>
               <#-- <a class="more" href="#">更多&gt;&gt;</a>-->
            </div>
            <div class="body">
            	<ul>
                    <#if fanslist??>
                     <#list fanslist as fans>
                    <li>
                    	<a href="${basePath}/customerhomepage/${fans.customerId}.html"  target="_blank">
                    	<#if (fans.infoHeadimg)??>
                    		<img  width="50px" height="50px" alt="${fans.customerName}" src="${baseUrl}${fans.infoHeadimg}" />
                    	<#else>
                    	    <img  width="50px" height="50px" alt="${fans.customerName}" src="${basePath}/images/default_head2.jpg" />
                    	</#if>
                    	</a>
                        <a class="name" href="${basePath}/customerhomepage/${fans.customerId}.html"  target="_blank">${fans.customerName}</a>               
                     </li>
                     </#list>
                    </#if>
                </ul>
                <div class="cb"></div>
            </div>
        </div><!-- /m_focus_list -->
        <div class="member_area2 mb10 fl">
            <div class="member_info fl">
            	<div class="img fl">
                	<a href="${basePath}/customerhomepage/${map.cc.customerId}.html">
                	<#if (map.cc.customerImg)??>
                	  <img alt="" src="${baseUrl}${map.cc.customerImg}" width="118" height="102"/>
                	<#else>
                	  <img alt="" src="${basePath}/images/default_head2.jpg" width="118" height="102"/>
                	</#if>
                	</a>
                </div>
                <div class="word fl ml10">
           	    <h4>${map.cc.customerNickname}</h4>
                    <ul>
                        <li>
                           <#if (map.cc.province)?? || (map.cc.city)??>
                        	<span class="locate">${(map.cc.province)!''}${(map.cc.city)!''}</span>
                            <span class="ml20">他的积分：${map.cc.infoCount}</span>
                            <#else> <span >他的积分：${map.cc.infoCount}</span>
                           </#if>
                        </li>
                        <li class="btns">
                           <#if !fansFlag?? || fansFlag=='0'>
                        	<a class="m_green_btn" href="javascript:void(0);" onclick="guanzhu('${fanscustomerId}','${(fansFlag)!''}')"><span>加关注</span></a>
                           </#if>
                           <#if fansFlag?? && fansFlag=='1'>
                        	<div class="mutual fl">已关注<a href="javascript:cancelguanzhu('${fanscustomerId }');">取消</a></div>
                           </#if>
                           <#if fansFlag?? && fansFlag=='2'>
                        	<div class="mutual fl" >互相关注<a href="javascript:cancelguanz('${fanscustomerId}');">取消</a></div>
                           </#if>
                            <a class="m_gray_btn" href="javascript:sendmessage();"><span>发私信</span></a>
                        </li>
                    </ul>
                    <div class="about_nums">
                    	<div class="item">
                    	   <a href="${basePath}/mymutual/${map.cc.customerId}-1.html" target="_blank">
                        	<em>${map.cb.guanzhu}</em>
                            <span>关注</span>
                            </a>
                        </div>
                        <div class="item">
                           <a href="${basePath}/mymutual/${map.cc.customerId}-0.html" target="_blank">
                        	<em>${map.cb.fansCount}</em>
                            <span>粉丝</span>
                           </a>
                        </div>
                        <div class="cb"></div>
                    </div>
                </div>
            </div><!-- /member_info -->
            <div class="cb"></div>
        </div><!-- /member_area2 -->
        <div class="m_group_list fl ml10">
        	<div class="title">
            	<h2>TA加入的小组</h2>
            </div>
            <div class="body">
            	<ul>
                    <#if joinedgroup??>
                     <#list joinedgroup as group>
                      <li>
                    	<a href="${basePath}/groupdetail/${group.groupId}.html"  target="_blank">
                    	<#if (group.groupHead)?? && (group.groupHead)!="">
                    		<img width="50px" height="50px" alt="${group.groupName}" src="${baseUrl}${group.groupHead}" /></a>
                    	 <#else>
                    		<img width="50px" height="50px" alt="${group.groupName}" src="${basePath}/images/default_head3.jpg" /></a>
                    	</#if>
                        <a class="name" href="${basePath}/groupdetail/${group.groupId}.html"  target="_blank">${group.groupName}</a>
                    </li>
                     </#list>
                    </#if>
                </ul>
                <div class="cb"></div>
            </div>
        </div><!-- /m_group_list -->
        <div class="cb"></div>
    </div>