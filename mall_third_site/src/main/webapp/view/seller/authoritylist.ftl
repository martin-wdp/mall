<#macro authorityList roles >
<div class="role-cont">

    <#list roles as role>
    <#if role.pages?size==0>
        <div class="role-item <#if !(role_index==0)>hide</#if>">
            <p>暂未给此角色添加权限</p>
        </div>
    <#elseif (role.pages?size>0)>
        <div class="role-item <#if !(role_index==0)>hide</#if>">
        <#list role.pages as page>
        <h4>${page.designation}</h4>
        <dl>
            <#list page.menuVos as menu>
                <dt>${menu.designation}</dt>
                <dd class="no_cont"></dd>
            </#list>
        </dl>
        </#list>
    </div>
    </#if>
    </#list>
</div>
</#macro>