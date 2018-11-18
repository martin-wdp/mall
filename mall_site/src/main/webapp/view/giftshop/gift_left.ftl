<#if (topmap.systembase.bsetHotline)??>
	<link rel = "Shortcut Icon" href="${(topmap.systembase.bsetHotline)!''}">
<#else>
	<link rel = "Shortcut Icon" href="${basePath}/images/Paistore.ico">
</#if>
<div class="container">
		<div class="location">
        	<a href="${basePath}/giftshop.html" target="_blank"><strong>积分商城</strong></a>
        	<#if pCate??>
        	<span>&gt;</span>
            <a href="${basePath}/jumpgift/${pCate.giftCateId}.html">${pCate.giftCateName}</a>
            </#if>
        	<#if sCate??>
        	<span>&gt;</span>
            ${sCate.giftCateName}
            </#if>
        </div>
</div>
<div class="container clearfix">
		<div class="pss_left">
			<div class="gift_sort">
				<div class="title">全部礼品分类</div>
                <div class="sub_cate2">
                   <#if catelist??>
                    <#list catelist as cate>
	                    <dl <#if (pCate.giftCateId)?? &&pCate.giftCateId==cate.giftCateId >class="hover"</#if>>
	                        <dt><i></i><a href="javascript:void(0)">${cate.giftCateName}</a></dt>
	                        <#if (cate.cateVos)??>
			                   <dd class="clearfix">
		                       	 <#list cate.cateVos as soncate>
			                         <a href="${basePath}/giftshop/${soncate.giftCateId}-${(soncate.giftParentId)!''}.html" target="_blank"  <#if (sCate.giftCateId)?? && sCate.giftCateId == soncate.giftCateId>class="cur"</#if>>
			                         ${soncate.giftCateName}</a>
		                         </#list>
			                    </dd>
	                        </#if>
	                    </dl>
                    </#list>
                   </#if>
                </div>
			</div>	
			<div class="rank border mt10">
            	<h2>兑换排行</h2>
            	<#if orderlist??>
	                <ul> 
	                   <#list orderlist as order >
		                	<li <#if order_index==0>class="hover"</#if>>
		                    	<span>${order_index+1}</span>
		                        <div class="small_good">
		                        	<p class="img"><a href="${basePath}/giftdetail/${order.temp1}.html" target="_blank" title="${order.giftName}"><img alt="" src="${order.giftPicList[0].picLittle}" /></a></p>
		                            <p class="name"><a href="${basePath}/giftdetail/${order.temp1}.html" target="_blank" title="${order.giftName}">${order.giftName}</a></p>
		                            <p class="price">积分:${order.giftIntegral}</p>
		                            <div class="cb"></div>
		                        </div>
		                    </li>
	                    </#list>
	                </ul>
                </#if>
            </div>
		</div>