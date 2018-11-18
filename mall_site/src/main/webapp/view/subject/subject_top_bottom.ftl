<#include "../include/common.ftl">
<@htmlHead title='${topmap.systembase.bsetName}'>
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<style>
    .containerbackground {background-image: url(${(subject.backgroundImg)!''});}
    .backgroundYellow {background-color:#eb6122;}
    body{font-family:"微软雅黑"}
    .section_headerTop .slot_headerTop01 .siteNav li > a{height:35px;}
</style>
</@htmlHead>
<@htmlBody>
	<div>
		<#if (topmap.temp)??>
			<#if (topmap.temp.tempId==1)>
				<#include "../index/topnew.ftl">
			<#elseif (topmap.temp.tempId==3)>
				<#include "../index/newheader.ftl">
			<#elseif (topmap.temp.tempId==9)>
				<#include "../index/newheader4.ftl">
			<#elseif (topmap.temp.tempId==11)>
				<#include "../index/newheader6.ftl">	
			<#elseif (topmap.temp.tempId==10)>
				<#include "../index/newheader5.ftl">
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
	<div class="containerbackground">
	 ${(subject.content)!''}
	</div>
<script type="text/javascript">
    $(document).ready(function(){
        $(".pro_sort").addClass("pro_sort_close");
        $("#navbg").addClass("backgroundYellow");

    });
    $(function(){
        $.ajax({ url:"${basePath}/getnavsort.htm", async:false ,success: function(data){
            $(".navLinks ul").find("li").each(function(){
                $(this).find("a").removeClass("on");
                if($(this).index()+1==data){
                    $(this).find("a").addClass("on");
                }
            });
        }
        });
    });
    //根据登录人员的会员类型，加载价格
    $(function(){
        var vip = $("#vip").val();
        $.each($("p[class='topic_price']"), function(i,n){
            var price = $($(this).find("strong")).html();
            var priceArr = price.split(" ");
            var vipPrice,normalPrice;
            if(priceArr.length > 1){
                normalPrice = priceArr[0];
                vipPrice = priceArr[1];
            }else{
                normalPrice = priceArr[0];
                vipPrice = normalPrice;
            }
            if(vip == "0"){
                $($(this).find("strong")).html(normalPrice);
            }else if(vip == "1"){
                $($(this).find("strong")).html(vipPrice);
            }
        });
    });
</script>
<#--引入底部
-->
	<#if (topmap.temp)??>
		<#if (topmap.temp.tempId==1)>
			<#include "../index/bottom.ftl">
		<#else>
		    <#include "../index/newbottom.ftl" />
		</#if>
	</#if>
</@htmlBody>