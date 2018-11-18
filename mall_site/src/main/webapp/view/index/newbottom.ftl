<script type="text/javascript" src="http://dl.ntalker.com/js/b2b/ntkfstat.js?siteid=cj_1000" charset="utf-8"></script>
<#if shopKuaiShang?? && shopKuaiShang.operation?? && shopKuaiShang.isuseing?? && shopKuaiShang.isuseing =='0'>
${shopKuaiShang.operation !''}
</#if>
<#assign basePath=request.contextPath>
	<link rel="stylesheet" type="text/css" href="${basePath}/index_seven/css/footer.css" />
<div style="background-color: #2d2d2d;">
    <div class="wp">
    <#-- 2015.10.12 wuyanbo 添加七品猫网站服务项 -->
        <div style="height:auto;">
            <img src="${basePath}/images/site_foot_service.png"/>
        </div>
        <div class="service clearfix" style="padding-left:20px">
        <#if (bottom.helpCates)?? && (bottom.helpCates?size>0)>
            <#list bottom.helpCates as helpcate>
                <#if (helpcate_index>=0) && (helpcate_index<5)>
                    <dl class="svc_box svc_01 fl"
                        style="background-image: url(${(helpcate.helpcateImg)!''});width:150px;">
                        <dt>${(helpcate.helpcateName)!''}</dt>
                        <dd>
                            <#if (helpcate.helpCenters)?? && (helpcate.helpCenters?size>0)>
                                <#list helpcate.helpCenters as helpcenter>
                                    <#if (helpcenter_index>=0) && (helpcenter_index<6)>
                                        <#if (helpcenter.isFoot=='1')>
                                            <div>
                                                <a href="${basePath}/help/${helpcenter.helpId}">${(helpcenter.helpTitle)!''}</a>
                                            </div>
                                        </#if>
                                    </#if>
                                </#list>
                            </#if>

                        </dd>
                    </dl>
                </#if>
            </#list>
        </#if>
            <dl class="svc_box foot_tel"></dl>
        </div>
        <!--/service-->
    </div>
    <!--/wp-->
    <div class="footer">
       <#-- <ul class="ft_links ft_links_tc">
            <li>北京市公安局朝阳分局备案编号110105014669</li>
            <li>京ICP证070359号</li>
            <li>互联网药品信息服务资格证编号(京)-非经营性-2011-0034</li>
            <li>Copyright © 2004-2014 北京市千品猫科技有限公司，版权所有</li>
        </ul>-->
        <!--/ft_links-->

        <div id="bq">
            ${topmap.systembase.bsetCopyright}

            <#--<ul style="text-align:center;margin-top:20px;">
                &lt;#&ndash;站长统计&ndash;&gt;
                <li>
                <#if sCodeList?? && (sCodeList?size>0)>
			    	<#list sCodeList as sCode>
                    <#if (sCode.code)??>
                        <#if (sCode.code?starts_with("<script") && sCode.code?ends_with("</script>"))>
                        ${(sCode.code)!''}
                        </#if>
                    </#if>
                </#list>
				</#if>
                </li>
            </ul>-->
        </div>

    </div><!--/footer-->
</div>
<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1257347068'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s11.cnzz.com/z_stat.php%3Fid%3D1257347068%26show%3Dpic' type='text/javascript'%3E%3C/script%3E"));</script>