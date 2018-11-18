<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<#include "../common/basepath.ftl"> 
<title>小组相册-${topmap.systembase.bsetName}</title>
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/css/group.css"/> 
<link rel="stylesheet" type="text/css" href="${basePath}/css/jqtransform.css"/>

<style type="text/css">
#container.item {
	width: 165px;heigth:217px
	border: 1px solid #c6bfbf;
	box-shadow: 0 1px 2px #ddd;
	margin-bottom: 25px;
}
.remove_photo {position:absolute; top:0; left:0; width:100%; height:30px; line-height:30px; color:#fff; text-align:center; background:rgba(0,0,0,.5); filter:alpha(opacity=50); display:none;}
.remove_photo a {color:#fff; padding-right:10px;}
.photo_ops {position:absolute; top:0; left:0; width:100%; height:30px; line-height:30px; color:#fff; text-align:center; background:url(../images/fk_ie.png) no-repeat; background:rgba(0,0,0,.5); display:none;}
.choose_photo input {opacity:0; filter:alpha(opacity=0); margin-left:-10px;}
.choose_photo, .rmv_photo {color:#fff; margin:0 5px; cursor:pointer;}
.rmv_photo:hover {color:#fff;}
#container .itema {width:165px; border:1px solid #ff0000; box-shadow:0 1px 2px #ddd; margin-bottom:25px;}
</style>

</head>
<body>
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
<input type="hidden" name="mymemberId" id="mymemberId" value="${(custome.customerId)!'' }"/> 
<input type="hidden" name="groupId" id="groupId" value="${(group.groupId)!'' }"/> 
<input type="hidden" value="${basePath}" id="basePath"/>
<div class="wrapper clearfix">
    	<div class="left_cont fl">
        	<div class="albums_wp clearfix">
        	   <#if (group.groupHead)??>
            	<img class="fl" alt="${group.groupName}" src="${group.groupHead}"  width="120" height="120"/>
        	   <#else>
            	<img class="fl" alt="${group.groupName}" src="${basePath}/images/default_head2.jpg"  width="120" height="120"/>
        	   </#if>
                <div class="albums_info fl ml15">
                    <h3>${group.groupName}<span>（${imgcount}张）</span></h3>
                    <#if (custome?? && (custome.customerPower??)) || (cinfo?? && cinfo.isSiteManager=='1')>
                    <div class="add_button mt20">
                    <a class="add_photos" href="${basePath}/toaddgroupimg/${group.groupId}.html">上传照片</a>
                    <a class="add_photos" href="${basePath}/groupimgalbums/${group.groupId}.html">全部照片</a>
                    <a class="add_photos" href="${basePath}/mygroupimg/${group.groupId}.html">我的照片</a>
	                <a class="add_photos" href="javascript:delgroupImgs();">批量删除</a>
                    </div>
                    </#if>
                    <!-- Baidu Button BEGIN -->
                    <div id="bdshare" class="bdshare_t bds_tools get-codes-bdshare mt20">
                        <span class="bds_more">分享到：</span>
                        <a class="bds_qzone"></a>
                        <a class="bds_tsina"></a>
                        <a class="bds_tqq"></a>
                        <a class="bds_renren"></a>
                        <a class="bds_t163"></a>
                        <a class="shareCount"></a>
                    </div>
                    <script type="text/javascript" id="bdshare_js" data="type=tools&amp;uid=3618405" ></script>
                    <script type="text/javascript" id="bdshell_js"></script>
                    <script type="text/javascript">
                    document.getElementById("bdshell_js").src = "http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion=" + Math.ceil(new Date()/3600000)
                    </script>
                    <!-- Baidu Button END -->
                </div><!--/albums_info-->
            </div><!--/albums_wp-->
            
            <div id="container" class="mt30 infinite_scroll" style="width:900px;">
               <#if pb.list??>
                 <#list pb.list as img>
	            	<div class="item" >
	                	<div class="item_img"><a href="${basePath}/groupimgdetail/${img.groupId}-${img.groupImgId}-${img.customerId}.html" target="_blank">
	                	<#if (img.groupImgUrl)?? && (img.groupImgUrl)!="">
  	                	   <img alt="${(img.groupImgTitle)!''}" src="${img.groupImgUrl}" width="155" /></a></div>
	                	<#else>
  	                	   <img alt="${(img.groupImgTitle)!''}" src="${basePath}/images/default_head3.jpg" width="155" /></a></div>
	                	</#if>
	                    <div class="item_info clearfix"><span class="item_date fl">${img.groupImgCreateTime?string('yyyy-MM-dd')}</span><a class="item_comment fr" href="${basePath}/groupimgdetail/${img.groupId}-${img.groupImgId}-${img.customerId}.html" target="_blank">${(img.replyCount)!'0'}</a></div>
	                    <div class="item_des">
	                     <#if (img.groupImgTitle)??&&(img.groupImgTitle)?length gt 10>
	                              ${(img.groupImgTitle)?substring(0,10)}
	                              <#else>${(img.groupImgTitle)!''}
	                     </#if>
	                    <div class= "mt10" >来自：
						     <a class="from_user" href="${basePath}/customerhomepage/${img.customerId }.html">
			                 <#if (img.customerName)?? && (img.customerName)?length gt 15>
	                           ${(img.customerName)?substring(0,15)}
	                          <#else>${(img.customerName)!''}
	                      	</#if>
			                 </a>
			            </div>
			          </div>
	                 <#if cusId??>
	                   <#if (custome.customerPower)??>
		                  <#if (custome.customerPower)=='1' || (custome.customerPower)=='2' || (cinfo ?? && cinfo.isSiteManager=='1')>
						       <div class="photo_ops">
							       <label class="choose_photo"><input type="checkbox" value="${img.groupImgId}" name="groupImgId" onclick="changeClass()"/><span class="choep">选择</span></label>
							       <a class="rmv_photo" href="javascript:delgroupImg(${img.groupImgId})">删除</a>
							    </div>
		                </#if>
		                  <#if (custome.customerPower) =='0' >
		                     <#if (cusId==(img.customerId ) && (group.limitDelPicType)=='0') || (cinfo ?? && cinfo.isSiteManager=='1')>
						     	 <div class="photo_ops">
							          <label class="choose_photo"><input type="checkbox" value="${img.groupImgId}" name="groupImgId" onclick="changeClass()"><span class="choep">选择</span></label>
							          <a class="rmv_photo" href="javascript:delgroupImg(${img.groupImgId});">删除</a>
							      </div>
						     </#if>
		                  </#if>
		                </#if>
		                <#if !(custome.customerPower)?? >
		                     <#if cusId==(img.customerId ) && (group.limitDelPicType)=='0' || (cinfo ?? && cinfo.isSiteManager=='1')>
						     	 <div class="photo_ops">
							          <label class="choose_photo"><input type="checkbox" value="${img.groupImgId}" name="groupImgId" onclick="changeClass()"><span class="choep">选择</span></label>
							          <a class="rmv_photo" href="javascript:delgroupImg(${img.groupImgId});">删除</a>
							      </div>
						     </#if>
		                  </#if>
	                 </#if>
	               </div><!--/item-->
                 </#list>
               </#if>
               
            </div><!--/container-->
            
              <#if (pb.list?size>0)>
                <form action="${basePath}/groupimgalbums/${group.groupId}.html" method="post" name="group_page_form" id="group_page_form">
                 <input id="page" name="pageNo" type="hidden" value="${pb.pageNo }"/>
                  <div class="pages tc mt30">
                   <!--判断上一页-->
                     <a class="pg_prev" 
                      <#if ((pb.pageNo)>1)>
                       href="javascript:page('${pb.pageNo-1}')"
                       <#else>href="javascript:void(0)" style="color:#999;visibility:hidden"</#if>
                       >&lt;上一页</a>
                     <#if ((pb.startNo)>1)> 
                      <a href="javascript:page('1');" >1</a>
									...
                     </#if>
                     <#list pb.startNo..pb.endNo as i>
                      <a <#if pb.pageNo==i> class="cur"</#if> href="javascript:page('${i}');">${i}</a>
                     </#list>
                     <#if (pb.totalPages > pb.endNo)>
                        ...
                        <a href="javascript:page('${pb.totalPages}')">${pb.totalPages}</a>
                     </#if>
                     <!--判断下一页-->
                     <a class="pg_next" 
                       <#if ((pb.pageNo+1) > pb.totalPages)>
                       href="javascript:void(0)" style="color:#999;visibility:hidden"
                       <#else>href="javascript:page('${pb.pageNo+1}')"
                       </#if>>下一页&gt;
                     </a>
                  </div>
                 </form>
                </#if>
        </div><!--/left_cont-->
        
        <div class="right_cont fr">
        	<div class="team_box clearfix">
            	<a class="fl" href="${basePath}/groupdetail/${group.groupId}.html">
            	<#if (group.groupHead)??>
            	 <img alt="${group.groupName}" src="${group.groupHead}" height="110" width="110"/></a>
            	<#else>
            	 <img alt="${group.groupName}" src="${basePath}/images/default_head2.jpg" height="110" width="110"/></a>
            	</#if>
                <div class="tb_info fl pt5 ml10">
                	<a href="${basePath}/groupdetail/${group.groupId}.html">${group.groupName}</a>
                    <p>本小组共有${group.groupmember}个成员</p>
                </div><!--/tb_info-->
            </div><!--/team_box-->
            
            <div class="latest_comment mt20">
            	<h3 class="m_tit">最新评论</h3>
                <ul class="lc_list mt20">
                   <#if reply??>
                     <#list reply as r>
	                	<li class="clearfix">
	                    	<a class="fl" href="${basePath}/customerhomepage/${r.customerId}.html">
	                    	<#if (r.customerHeadimg)?? && (r.customerHeadimg)!="">
		                    	<img width="50" height="50" alt="${r.customerName}" src="${r.customerHeadimg}" />
	                    	<#else>
		                    	<img width="50" height="50" alt="${r.customerName}" src="${basePath}/images/default_head3.jpg" />
	                    	</#if>
	                    	</a>
	                        <div class="lc_info fl ml10">
	                        	<div class="comment_cont clearfix lh150" style="width:150px;height:43px">
	                            	<div class="fl head_box"><a href="${basePath}/customerhomepage/${r.customerId}.html">${r.customerName}：</a></div>
	                                  <#assign content = (r.replyContent)?replace('<img src="face/face/','<img src="'+basePath+'/face/face/') >
	                                 <#assign content1=(content)?replace("<img[^>]*>","","ri")>
	                                  <#if content1?length gt 12>
	                                  ${(content1[0..12])?default("")}...
	                          			<#else>${content}
	                      			 </#if> 
	                            </div><!--/comment_cont-->
	                            <p class="cm_date">${(r.replyTime)?string('yyyy-MM-dd HH:mm:ss')}</p>
	                        </div><!--/lc_info-->
	                    </li>
                     </#list>
                   </#if>
                </ul><!--/lc_list-->
            </div><!--/latest_comment-->
        </div><!--/right_cont-->
        
       
    </div>
   
<#if (topmap.temp)??>
		<#if (topmap.temp.tempId==1)>
			<#include "../index/bottom.ftl">
		<#else>
		    <#include "../index/newbottom.ftl" />
		</#if>
</#if>
<script type="text/javascript" src="${basePath}/js/jquery.js"></script>
<script type="text/javascript" src="${basePath}/js/common.js"></script>
<script type="text/javascript" src="${basePath}/js/social/default.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery.masonry.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery.jqtransform.js"></script>
<script type="text/javascript" src="${basePath}/js/social/group_img.js"></script>
<script type="text/javascript">
		$(function() {
			$(".rowElem").jqTransform();
		});
		
		var $container = $('.infinite_scroll');  
		$container.imagesLoaded( function(){  
			  $container.masonry({  
			   itemSelector: '.item',
			   columnWidth: 177,
			   gutter: 10
			   });  
		}); 	
		
			
		function changeClass(){
		 $("input[type='checkbox']").each(function(){
			  if($(this).prop('checked'))
				{
				   $(this).next("span").html("取消");
				   $(this).parents('.item').addClass('itema');
				}else{
					$(this).next("span").html("选择");
					$(this).parents('.item').removeClass('itema');
				}
			 });  
		 }
		 
		$(function(){
			//选中首页社区
			$(".sort_list li").removeClass("cur");
			$(".sort_list li:eq(1)").addClass("cur");
        });	
</script>
</body>
</html>


