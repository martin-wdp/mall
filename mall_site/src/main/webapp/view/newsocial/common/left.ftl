 <div class="left_menu fl">
        	<h2 class="hide">管理小组</h2>
            <div class="menu_wp">
            	<div class="menu_box <#if flag?? && (flag ==0 || flag ==1 || flag == 2 || flag == 3)>cur</#if>">
                	<h3><a href="javascript:void(0);">基本信息</a></h3>
                    <ul class="menu_list">
                        <li <#if flag ?? && flag == 1>class="current"</#if>><a href="${basePath}/groupinfo.html?groupId=${groupInfo.groupId}">小组资料</a></li>
                        <li <#if flag ?? && flag == 2>class="current"</#if>><a href="${basePath}/grouphead.html?groupId=${groupInfo.groupId}">小组头像</a></li>
                        <li <#if flag ?? && flag == 3>class="current"</#if>><a href="${basePath}/background.html?groupId=${groupInfo.groupId}">小组背景</a></li>
                    </ul><!--/menu_list-->
                </div><!--/menu_box-->
                <div class="menu_box <#if flag?? && (flag ==4 || flag == 5 || flag == 6 || flag == 7)>cur</#if>" >
                	<h3><a href="javascript:void(0);">权限管理</a></h3>
                    <ul class="menu_list">
                        <li <#if flag ?? && flag == 4>class="current"</#if>><a href="${basePath}/accesslimit.html?groupId=${groupInfo.groupId}">访问权限</a></li>
                        <li <#if flag ?? && flag == 5>class="current"</#if>><a href="${basePath}/deletelimit.html?groupId=${groupInfo.groupId}">删除权限</a></li>
                        <#if (custome?? && custome.customerPower =='2') || (cinfo?? && cinfo.isSiteManager=='1')>
                        <li <#if flag ?? && flag == 6>class="current"</#if>><a href="${basePath}/managerlimit.html?groupId=${groupInfo.groupId}">管理员权限</a></li>
                        <li <#if flag ?? && flag == 7>class="current"</#if>><a href="${basePath}/dissolvedgroup.html?groupId=${groupInfo.groupId}">解散小组</a></li>
                        </#if>
                     
                    </ul><!--/menu_list-->
                </div><!--/menu_box-->
                <div class="menu_box <#if flag?? && (flag ==8 || flag == 9 )>cur</#if>">
                	<h3><a href="javascript:void(0);">成员管理</a></h3>
                    <ul class="menu_list">
                        <li <#if flag ?? && flag == 8>class="current"</#if>><a href="${basePath}/groupmember.html?groupId=${groupInfo.groupId}">小组成员</a></li>
                        <li <#if flag ?? && flag == 9>class="current"</#if>><a href="${basePath}/blacklist.html?groupId=${groupInfo.groupId}">黑名单</a></li>
                    </ul><!--/menu_list-->
                </div><!--/menu_box-->
                <div class="menu_box <#if flag?? && flag ==10 >cur</#if>">
                	<h3><a href="javascript:void(0);">禁止回应的话题</a></h3>
                    <ul class="menu_list">
                    	<li <#if flag ?? && flag == 10>class="current"</#if>><a href="${basePath}/noresponsetopic.html?groupId=${groupInfo.groupId}">禁止回应的话题</a></li>
                    </ul><!--/menu_list-->
                </div><!--/menu_box-->
                <div class="menu_box <#if flag?? && flag ==11 >cur</#if>">
                	<h3><a href="javascript:void(0);">回收站</a></h3>
                    <ul class="menu_list">
                    	<li <#if flag?? && flag ==11 >class="current"</#if>><a href="${basePath}/topicrecycle.html?groupId=${groupInfo.groupId}">回收站</a></li>
                    </ul><!--/menu_list-->
                </div><!--/menu_box-->
            </div><!--/menu_wp-->
        </div><!--/left_menu-->