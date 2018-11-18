<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<#include "../common/basepath.ftl"> 
<title>个人主页-${topmap.systembase.bsetName}</title>
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/css/group.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/css/jqtransform.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/face/qqFace.css" />

   <style type="text/css">
    .topic_cont {
		border: 1px solid #e9e9e9;
		width: 615px;
		padding: 20px;
		position: relative;
	}
    </style>
  
</head>
<body>
	<!--这是头部-->
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
    <div class="container clearfix pt30">
    	<div class="left_wp fl">
        	<div class="personal_wp clearfix">
            	<div class="myinfo fl">
                	<div class="myimg">
                    	<a href="${basePath}/customer/index.html" target="_blank">
                    	<#if (map.cc.customerImg)?? && (map.cc.customerImg)!="">
                    	<img alt="${map.cc.customerNickname}" src="${map.cc.customerImg}" width="180" height="180"/>
                    	<#else>
                    	<img alt="${map.cc.customerNickname}" src="${basePath}/images/default_head3.jpg" width="180" height="180"/>
                    	</#if>
                    	</a>
                    </div><!--/myimg-->
                    <ul class="user_atten clearfix mt10">
                    	<li><a href="${basePath}/mymutual/${map.cc.customerId}-1.html" target="_blank"><strong>${map.cb.guanzhu}</strong><span>关注</span></a></li>
                        <li><a href="${basePath}/mymutual/${map.cc.customerId}-0.html" target="_blank"><strong>${map.cb.fansCount}</strong><span>粉丝</span></a></li>
                        <li class="no_border"><a href="${basePath}/moodlist/${map.cc.customerId}.html" target="_blank"><strong>${map.cb.moodCount}</strong><span>心情</span></a></li>
                    </ul><!--/user_atten-->
                </div><!--/myinfo-->
                
                <div class="mycont fl ml20">
                	<div class="my_tit"><h2>${map.cc.customerNickname}</h2><a href="${basePath}/customer/myinfo.html" target="_blank">[编辑个人资料]</a></div>
                    <p class="my_mood mt5">心情：<a href="${basePath}/moodlist/${map.cc.customerId}.html" target="_blank"><span class="ml10">${(mood.moodContent)!''}</span></a></p>
                    <form id="moodForm" method="post">
	                    <div class="rowElem mt5 clearfix">
	                    <textarea class="mood_txa"  maxlength="140" onkeyup="length140();" id="content1" name="content1" placeHolder="今天心情怎么样，说来听听……"></textarea>
	                      <input type="hidden" name="moodContent" id="moodContent" />
	                    </div>
	                    <div class="release_mood clearfix mt10">
	                    	<div class="face_box fl"><img alt="" id="face1" src="${basePath}/images/face_img.gif" /><span class="ml10" id="a">最多可输入140个汉字</span></div>
	                        <div class="rowElem clearfix fr"><a href="javascript:void();" onclick="mood()"><input type="button" value="发布心情" /></a></div>
	                    </div><!--/release_mood-->
                    </form>
                    <div class="my_tags">
                    	<span class="my_sex female"></span>
                        <span>${(map.cc.province)!''}${(map.cc.city)!''}</span>
                        <#if (map.cc.province)?? || (map.cc.city)??>
                        <span class="mt_line">|</span>
                        </#if>
                        <span>积分：${map.cc.infoCount}</span>
                    </div><!--/my_tags-->
                    <!--<div class="tags_wp mt10 clearfix">
                    	<em class="fl">标签：</em>
                        <ul class="tags_list fl clearfix">
                        	<li><span>摄影</span></li>
                            <li><span>自信</span></li>
                            <li><span>纯真</span></li>
                            <li><span>天然萌物</span></li>
                            <li><span>巨蟹座</span></li>
                        </ul><!--/tags_list-->
                        <!--<a class="edit_tags fl" href="javascript:;">[编辑]</a>
                    </div><!--/tags_wp-->
                </div><!--/mycont-->
            </div><!--/personal_wp-->
            
            <ul class="per_nav mt20 clearfix">
            	<li class="cur"><a class="pn_01" href="javascript:;">我的主页</a></li>
            </ul><!--/per_nav-->
            
            <div class="friends_news mt20">
                <div class="topics_wp mt20">
                    <#if pbsome.list??>
                     	<#list pbsome.list as topic> 
                        <div class="topic_box clearfix">
                            <div class="head_box fl ml20"><a class="u_head" href="${basePath}/customerhomepage/${map.cc.customerId}.html" target="_blank">
                             <#if (map.cc.customerImg)?? && (map.cc.customerImg)!="">
                              <img alt="${map.cc.customerNickname}" src="${map.cc.customerImg}"  width="50" height="50"/>
                            <#else>
                              <img alt="${map.cc.customerNickname}" src="${basePath}/images/default_head3.jpg"  width="50" height="50"/>
                            </#if>
                            </a></div>
                            <div class="topic_cont fr">
                                <span class="t_arrow">&nbsp;</span>
                                <div class="fn_hd">
                                    <a class="mr10" href="${basePath}/customerhomepage/${map.cc.customerId}.html" target="_blank">
                                  		  我</a>在${topic.groupName}发表了新的话题<a class="ml10" href="${basePath}/topicdetail/${topic.groupId}-${topic.topicId}.html" target="_blank">${topic.topicTitle}</a>
                                </div><!--/fn_hd-->
                                <div class="fn_cont mt15 clearfix"> 
                                    <div class="fn_info fl">  
                                        <i class="q_marks_01">&nbsp;</i>
                                          <#assign content=(topic.topicContent)?replace("<.*?>","","r")>
                                    <#if content?length gt 250>
		                               ${content?substring(0,250)}...
		                               <#else>
		                               ${content} 
		                              </#if>
											  
                                        <i class="q_marks_02">&nbsp;</i>    
                                    </div><!--/fn_info-->  
                                    <div class="fn_imgs fr">  
                                    <#if topic.piclist??>
                                    		<#list topic.piclist as pic>
                                    			<#if (pic_index<3)>
                                                <a href="${basePath}/topicdetail/${topic.groupId}-${pic.topicId}.html" target="_blank"><img alt="" src="${pic.topicImgUrl}" width="60" height="auto" /></a>
                                        		</#if>
                                        	</#list>
                                      </#if>
                                    </div><!--/fn_imgs-->
                                </div><!--/fn_cont-->
                                <div class="fn_bt mt15 clearfix">
                                    <span class="date light fl">${topic.topicCreateTime?string("yyyy-MM-dd HH:mm:ss")}</span>
                                    <div class="tp_info clearfix fr">
                                        <a class="fl" href="javascript:;">热度（${topic.topicHot}）</a>
                                        <a class="fl" href="javascript:;">评论（${(topic.replyCount)!'0'}）</a>
                                        <!-- Baidu Button BEGIN -->
                                        <div id="bdshare" class="bdshare_t bds_tools get-codes-bdshare fl ml10">
                                            <span class="bds_more">分享</span>
                                        </div>
                                        <script type="text/javascript" id="bdshare_js" data="type=tools&amp;uid=3618405" ></script>
                                        <script type="text/javascript" id="bdshell_js"></script>
                                        <script type="text/javascript">
                                            document.getElementById("bdshell_js").src = "http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion=" + Math.ceil(new Date()/3600000)
                                        </script>
                                        <!-- Baidu Button END -->
                                    </div><!--/tp_info-->
                                </div><!--/fn_bt-->
                            </div><!--/topic_cont-->
                        </div><!--/topic_box-->
                        </#list>
                   </#if>
                   
                   
                </div><!--/topics_wp-->
            </div><!--/friends_news-->
              <#if (pbsome.list?size>0)>
                <form action="${basePath}/mycustomerhomepage/html?${map.cc.customerId }" method="post" name="group_page_form" id="group_page_form">
                 <input id="page" name="pageNo" type="hidden" value="${pbsome.pageNo }"/>
                
                  <div class="pages tc mt30">
                   <!--判断上一页-->
                     <a class="pg_prev" 
                      <#if ((pbsome.pageNo)>1)>
                       href="javascript:page('${pbsome.pageNo-1}')"
                       <#else>href="javascript:void(0)" style="color:#999;visibility:hidden"</#if>
                       >&lt;上一页</a>
                     <#if ((pbsome.startNo)>1)> 
                      <a href="javascript:page('1');" >1</a>
									...
                     </#if>
                     <#list pbsome.startNo..pbsome.endNo as i>
                      <a <#if pbsome.pageNo==i> class="cur"</#if> href="javascript:page('${i}');">${i}</a>
                     </#list>
                     <#if (pbsome.totalPages > pbsome.endNo)>
                        ...
                        <a href="javascript:page('${pbsome.totalPages}')"${pbsome.totalPages}></a>
                     </#if>
                     <!--判断下一页-->
                     <a class="pg_next" 
                       <#if ((pbsome.pageNo+1) > pbsome.totalPages)>
                       href="javascript:void(0)" style="color:#999;visibility:hidden"
                       <#else>href="javascript:page('${pbsome.pageNo+1}')"
                       </#if>>下一页&gt;
                     </a>
                  </div>
                 </form>
                </#if>
            
        </div><!--/left_wp-->
        
        <div class="right_wp fr">
        	<div class="remind_wp">
            	<h3 class="m_tit ml5">提醒</h3>
                <div class="remind_box mt10">
                	<p class="clearfix"><span class="fl">系统消息：</span><a class="fr" href="${basePath}/systemmsg.html" target="_blank">${map.cb.sysCount}</a></p>
                    <p class="clearfix"><span class="fl">私信：</span><a class="fr" href="${basePath}/lettermsg.html" target="_blank">${map.cb.mesCount}</a></p>
                    <p class="clearfix"><span class="fl">收到的评论：</span><a class="fr" href="${basePath}/commentmsg.html" target="_blank">${map.cb.replyCount}</a></p>
                </div><!--/remind_box-->
            </div><!--/remind_wp-->
            
            <div class="latest_viewer mt30 ml5">
            	<h3 class="m_tit">最新访客</h3>
            	<#if visitors??>
                <ul class="la_list clearfix mt20">
                   <#list visitors as visitor>
                	<li><a href="${basePath}/customerhomepage/${visitor.visitorscustomerId}.html" target="_blank">
                	<#if (visitor.infoHeadimg)?? && visitor.infoHeadimg !="">
                	<img alt="${visitor.memberNickname}" src="${visitor.infoHeadimg}" width="50px" height="50px" />
                	<#else>
                     <img alt="${visitor.memberNickname}" src="${basePath}/images/default_head3.jpg" width="50px" height="50px"/>
                	</#if>
                	${visitor.memberNickname}</a></li>
                   </#list>
                </ul><!--/la_list-->
                </#if>
            </div><!--/latest_viewer-->
            
            <div class="my_atten mt30 ml5">
            	<div class="my_title clearfix">
                	<h3 class="m_tit fl">我关注的<em>(${map.cb.guanzhu})</em></h3>
                    <a class="fr more"  href="${basePath}/mymutual/${map.cc.customerId}-1.html" target="_blank">更多>></a>
                </div><!--/my_title-->
                <#if (guanzhu)??>
	                <ul class="la_list clearfix mt20">
	                 <#list guanzhu as gz>
	                    <li><a href="${basePath}/customerhomepage/${gz.customerId}.html" target="_blank">
	                    <#if (gz.infoHeadimg)?? && (gz.infoHeadimg)!="">
	                    <img alt="${gz.customerName}" src="${gz.infoHeadimg}" width="50px" height="50px"/>
	                    <#else>
	                    <img alt="${gz.customerName}" src="${basePath}/images/default_head3.jpg" width="50px" height="50px"/>
	                    </#if>
	                    ${gz.customerName}</a></li>
	                </#list>
	                </ul><!--/la_list-->
                </#if>
            </div><!--/my_atten-->
            
            <div class="my_fans mt30 ml5">
            	<div class="my_title clearfix">
                	<h3 class="m_tit fl">我的粉丝<em>(${map.cb.fansCount})</em></h3>
                    <a class="fr more" href="${basePath}/mymutual/${map.cc.customerId}-0.html" target="_blank">更多>></a>
                </div><!--/my_title-->
                <#if fans??>
	                <ul class="la_list clearfix mt20">
	                 <#list fans as f>
	                    <li><a href="${basePath}/customerhomepage/${f.customerId}.html" target="_blank">
	                    <#if (f.infoHeadimg)?? && (f.infoHeadimg)!="">
	                   		 <img alt="${f.customerName}" src="${f.infoHeadimg}" width="50px" height="50px"/>
	                    <#else>
	                   		 <img alt="${f.customerName}" src="${basePath}/images/default_head3.jpg" width="50px" height="50px"/>
	                    </#if>
	                    ${f.customerName}</a></li>
	                 </#list>
	                </ul><!--/la_list-->
                </#if>
            </div><!--/my_fans-->
            
            <div class="users_recmd mt30 ml5">
            	<div class="my_title clearfix">
                	<h3 class="m_tit fl">用户推荐</h3>
                    <a class="change_users fr" href="javascript:huan();">换一换</a>
                </div><!--/my_title-->
                <#if recommend??>
                <ul class="ur_list mt20" id="tj">
                   <#list recommend as r>
                	<li class="clearfix">
                    	<a class="fl" href="${basePath}/customerhomepage/${r.customerId}.html" target="_blank">
                    	<#if (r.infoHeadimg)?? && (r.infoHeadimg)!="">
	                   		 <img alt="${(r.customerName)!''}" src="${r.infoHeadimg}" width="50px" height="50px"/>
	                    <#else>
	                   		 <img alt="${(r.customerName)!''}" src="${basePath}/images/default_head3.jpg" width="50px" height="50px"/>
	                    </#if>
                    	</a>
                        <div class="ur_info fl ml10">
                        	<div class="clearfix">
                        	<a class="ur_name fl" href="${basePath}/customerhomepage/${r.customerId}.html" target="_blank">
                        	${(r.customerName)!''}
                        	</a>
                        	<a class="atten_it fr" href="javascript:guanzhu('${r.customerId}','${(r.fansFlag)!''}')">关注TA</a></div>
                            <p class="mt10">
                            <#if r.focusFlag==1>共同小组</#if>
                            <#if r.focusFlag==2>共同城市</#if>
                            <#if r.focusFlag==3>共同好友</#if>
                            </p>
                        </div><!--/ur_info-->
                    </li>
                  </#list>
                </ul><!--/ur_list-->
               </#if>
            </div><!--/users_recmd-->
            
            <div id="gg_slides" class="mt50" style="width:245px; height:375px; overflow:hidden;">
            	<div class="slides_container">
                	<div class="slide"><a href="javascript:;"><img alt="" src="${basePath}/images/images_17.jpg" width="243" height="360" /></a></div>
                </div><!--/slides_container-->
            </div><!--/gg_slides-->
           
        </div><!--/right_wp-->
    </div><!--/container-->
     <#if (topmap.temp)??>
		<#if (topmap.temp.tempId==1)>
			<#include "../index/bottom.ftl">
		<#else>
		    <#include "../index/newbottom.ftl" />
		</#if>
	</#if>
	
	<script type="text/javascript" src="${basePath}/js/jquery.js"></script>
	<script type="text/javascript" src="${basePath}/js/common.js"></script>
	<script type="text/javascript" src="${basePath}/js/slides.min.jquery.js"></script>
	<script type="text/javascript" src="${basePath}/js/jquery.jqtransform.js"></script>
	<script type="text/javascript" src="${basePath}/js/social/default.js"></script>
	<script type="text/javascript" src="${basePath}/face/jquery.qqFace.min.js"></script>
	  <script type="text/javascript">
	    $(function(){
			$('#face1').qqFace({
				id : 'facebox1', //表情盒子的ID
				assign:'content1', //给那个控件赋值
				path:'face/face/', //表情存放的路径
				tip : 'em_'
		  });
		});
		
		$(function() {
			$(".rowElem").jqTransform();
		});
		
		//查看结果
		function view(){
			var str = $('#content1').val();
			str = str.replace(/\</g,'&lt;');
			str = str.replace(/\>/g,'&gt;');
			str = str.replace(/\n/g,'<br/>');
			str = str.replace(/\[\/em_([0-9]*)\]/g,'<img src="face/face/$1.gif" border="0" />');
			$('#moodContent').val(str);
		}
		
		function mood(){
		   var moodContent = $('#content1').val();
			if(moodContent==""){
				return ;
			}
			view();
			$.ajax({
			   type:'post',
			   data: $('#moodForm').serialize(),
			   url: "${basePath}/sendmood.htm",  
			   success: function(result){  
		             if (result==1){  
		            	 window.location.reload();
		           	 }else{   
		           		alert("发布失败");
		             }  
		           }
			  });
		 }
		 
		  function length140(){
	     		var len=$("#content1").val().length;
	     	       if(len>140){
	      			   $("#a").html("你还可以输入0/140字"); 
	     	       }else{
	     	    	   $("#a").html("你还可以输入"+parseInt(140-len)+"/140字");
	     	       }
	    	 }
	    	 
	    function huan(){
			$("#tj").html(""); 
			var memberId = ${(map.cc.customerId)!''};
				  $.ajax({
				    url: '${basePath}/tjMember.htm?memberId='+memberId,
				    type: 'POST',
				    dataType: 'text',
				    success:showResponse
				  });
		    }
		
		//根据ajax取出来的json数据转换成html    
		function showResponse(responseData) {  
		  var returnjson = eval("(" +responseData+")");
		  var nextpagehtml = ''; 
		   for(var i=0; i<returnjson[0].tjmember.length; i++) {   
			  nextpagehtml+='<li class="clearfix">';
			  nextpagehtml+='<a class="fl" href="${basePath}/customerhomepage/'+returnjson[0].tjmember[i].customerId+'.html" target="_blank"><img alt="" src="returnjson[0].tjmember[i].infoHeadimg" width="50" height="50" /></a>';
			  nextpagehtml+='<div class="ur_info fl ml10">';
			  nextpagehtml+='<div class="clearfix"><a class="ur_name fl" href="${basePath}/customerhomepage/'+returnjson[0].tjmember[i].customerId+'.html" target="_blank">'+returnjson[0].tjmember[i].customerName+'</a><a class="atten_it fr" href="javascript:guanzhus(\''+returnjson[0].tjmember[i].customerId+'\',\''+returnjson[0].tjmember[i].fansFlag+'\');">关注TA</a></div>';	
			  nextpagehtml+='<p class="mt10">';	
			  if(returnjson[0].tjmember[i].focusFlag==1){
				  nextpagehtml+='共同小组';	
			  }
			  if(returnjson[0].tjmember[i].focusFlag==2){
				  nextpagehtml+='共同城市';	
			  }
			  if(returnjson[0].tjmember[i].focusFlag==3){
				  nextpagehtml+='共同好友';	
			  }
			  nextpagehtml+=' </p>';	
			  nextpagehtml+='</div>';	    
			  nextpagehtml+='</li>';	
			 }
		  $("#tj").html(nextpagehtml); 
		}  
		
		function guanzhu(obj,flag){
			 if(flag ==''){
				addguanz(obj); 
			 }else{
				 changeguanz(obj);
			 }
 		}
 
		 function addguanz(obj){
			 $.ajax({
				 type:'post',
				 url: '${basePath}/guanzhu.htm',
				 data: 'fansCustomerId='+obj,
				 success: function(msg){
		      		if(msg==1){
		      			window.location.reload();
		      		}else{
		      			window.location.href="${basePath}/login.html";
		      		}
		      	 }   //操作成功后的操作！msg是后台传过来的值
			 });
		 }
 
		 function changeguanz(obj){
		 $.ajax({
				 type:'post',
				 url:'${basePath}/guanzhu.htm',
				 data: 'fansFlag=0&fansCustomerId='+obj,
				 success: function(msg){
		      		if(msg==1){
		      			window.location.reload();
		      		}else{
		      			window.location.href="${basePath}/login.html";
		      		}
		      	 }   //操作成功后的操作！msg是后台传过来的值
			 });
		 }
		 
		 //分页
  function page(page){
  		$("#page").val(page);
  		$("#group_page_form").submit();
  }
  
     $(function(){
			//选中首页社区
			 $(".sort_list li").removeClass("cur");
			 $(".sort_list li:eq(1)").addClass("cur");
         });
	</script>
</body>
</html>