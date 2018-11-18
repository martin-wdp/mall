<#include "../include/common.ftl">
<@htmlHead title="${map.thirdProject.thirdProjectName!''}">
<link rel="stylesheet" href="${basePath}/store/css/base.min.css" type="text/css" />
<link rel="stylesheet" href="${basePath}/store/css/style.css" type="text/css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/thirdindex/style.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/css/index.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/index_two/css/header.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/store.style.css">
<script type="text/javascript" src="${basePath}/store/js/jquery.slides.min.js"></script>
<script type="text/javascript" src="${basePath}/store/js/default.js"></script>
</@htmlHead>
<@htmlBody>
 <#if (topmap.temp)??>
			<#if (topmap.temp.tempId==1)>
				<#include "../index/thirdtopnew.ftl">
			<#elseif (topmap.temp.tempId==3)>
				<#include "../index/thirdnewheader.ftl">
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

     <#include "newproStoreHead.ftl">
     <div style="min-height:476px">
      ${map.thirdProject.thirdProjectContext!''}
      </div>
     <!-- 提示框-->
	<#include "../infotips.ftl">
	
    <div class="side_tools">
        <a class="backtotop" href="javascript:;"><em>返回顶部</em><b></b></a>
    </div><!--/side_tools--> 
      <#include "../index/newbottom.ftl"/>

      <script type="text/javascript">
         $(function(){
              if($("#third_slide").length > 0 && $("#third_slide a").length > 1) {
			        $("#third_slide").slidesjs({
			            play: {
			                active: false,
			                effect: "fade",
			                auto: true,
			                interval: 4000,
			                pauseOnHover: true,
			                restartDelay: 2500
			            },
			            navigation: {
			                active: false
			            },
			            pagination: {
			                active: true,
			                effect: "fade"
			            }
			        });
			    }
         });

  /*收藏店铺*/
    $(".collectstore").click(function(){
    	$.post("${basePath}/addcollectionseller.htm?collectionThirdId=${map.thirdId}",function(data){
    		if(data=="2"){
    			/*初始化弹框样式*/
    			$(".info_tip_content2").html("您已经收藏过该商家！");
    			$(".info_tip_img2").attr("src","../images/collect.png");
    			$(".info_tip_cancel2").attr("href","javascript:;").hide();
    			$(".info_tip_submit2").attr("href","javascript:;").show().html("确定").unbind("click");
    			$(".info_tip_submit2").click(function(){
    				cls();
    			});
    			dia(2);
    		}else if(data=="3"){
    			/*初始化弹框样式*/
    			$(".info_tip_content2").html("是否跳转到登陆页?");
    			$(".info_tip_img2").attr("src","../images/index_ques.png");
    			$(".info_tip_cancel2").attr("href","javascript:;").html("取消").show();
    			$(".info_tip_submit2").attr("href","../login.html").show().html("确定").unbind("click");
    			$(".info_tip_cancel2").click(function(){
    				cls();
    			});
    			dia(2);
    		}else{
    			/*初始化弹框样式*/
    			$(".info_tip_content2").html("恭喜您收藏成功！");
    			$(".info_tip_img2").attr("src","../images/collect.png");
    			$(".info_tip_cancel2").attr("href","javascript:;").hide();
    			$(".info_tip_submit2").attr("href","javascript:;").show().html("确定").unbind("click");
    			$(".info_tip_submit2").click(function(){
    				cls();
    			});
    			dia(2);
    		}
    	});
    });
            $(".extra_info").hide(); 
			$(".third_store").hover(function() {
				$(".extra_info").show();
			}, function() {
				$(".extra_info").hide();
			});
	function searchTitleHead(obj){
        $("#title").val($(obj).val());
        $("#searchForm").submit();
    }
	</script>
</@htmlBody>