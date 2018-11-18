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
<link rel="stylesheet"  href="${basePath}/js/plugin/kindeditor/themes/default/default.css" />
<link rel="stylesheet"  href="${basePath}/js/plugin/kindeditor/plugins/code/prettify.css" />
    <style type="text/css">
      .topic_cont {
			border: 1px solid #e9e9e9;
			width: 615px;
			padding: 20px;
			position: relative;
		}
		.mood_list .ke-container{
			min-width:500px!important;
		}
		
		.ke-toolbar {
		    background-color: #fff!important;
		 }
		
		.ke-statusbar {
		   background-color: #fff!important;
		 }
		.ke-statusbar{
			display:none!important;
		 }
		
		.c_tx3{
		    color:red!important;
		}
		.mood_reply {display:inline;}
		.mp_wp {margin-bottom: 20px;}
		.rpy_box {display: none;}
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
    <div class="wrapper clearfix">
        <div class="left_cont fl">
            <h3 class="m_tit"><#if cusId?? && cusId == memberId>我<#else>TA</#if> 的心情</h3>
            <form method="post" id="moodForm"  <#if !cusId?? || cusId != memberId>class="none"  </#if>>
	            <div class="mood_wp mt20 clearfix">
	                <textarea class="mood_txa" id="moodContent" name="moodContent" maxlength="140"></textarea>
	            </div><!--/row_elem-->
	            <div class="rls_mood clearfix mt10">
	                <div class="face_box fl" id="sp"><span class="ml10">最多可输入140个汉字</span></div>
	                <div class="rowElem clearfix fr"><a href="javascript:void();" onclick="mood()"><input type="button" value="发布心情" /></a></div>
	            </div><!--/rls_mood-->
           </form>
           
           <#if (moodMap.list)?size gt 0> 
            <ul class="mood_list mt30">
                 <#list moodMap.list as mood> 
	                  <li class="clearfix">
	                    <a class="fl" href="javascript:;">
	                    <#if mood.infoHeadimg?? && mood.infoHeadimg !="">
	                   	 <img alt="${mood.memberNickname}" src="${mood.infoHeadimg}" width="50" height="50"/>
	                    <#else>
	                   	 <img alt="${mood.memberNickname}" src="${basePath}/images/default_head3.jpg" width="50" height="50"/>
	                    </#if>
	                    </a>
	                    <div class="mood_box fl ml10">
	                        <div class="clearfix">
	                            <a class="m_uname fl f14" href="${basePath}/customerhomepage/${memberId}.html" target="_blank">${mood.memberNickname}:</a>
	                            <#if cusId?? && cusId == memberId>
	                            <a class="mood_delete fr ml10" href="javascript:void(0);" onclick="delMood('${mood.moodId }')" >删除</a>
	                            </#if>
	                            <a class="mood_reply fr" href="javascript:void(0);" >评论</a>
	                        </div>
	                        <p class="lh180 mood_cont mt5">${mood.moodContent}</p>
	                        <p class="mood_date mt5">${mood.moodCreateTime?string('yyyy-MM-dd HH:mm:ss')}</p>
	                        
	                        <div class="m_reply none">
	                        	<div class="mr_arrow"></div>
	                            <div class="m_reply_box">
	                            <#if (mood.moodReply)??>
	                              <#list mood.moodReply as mReply>
	                              <div class="mp_wp">
                                    <div class="mp_cont mb20 clearfix">
                                        <a class="fl" href="${basePath}/customerhomepage/${mReply.customerId}.html" target="_blank">
                                        <#if (mReply.customerHeadimg)?? &&  (mReply.customerHeadimg)!="">
					    	            	<img alt="${mReply.customerName}" width="50" height="50" src="${mReply.customerHeadimg}"  /></a>
					                	<#else>
						                	<img alt="${mReply.customerName}" width="50" height="50" src="${basePath}/images/default_head3.jpg"  /></a>
					                	</#if>
                                        </a>
                                        <div class="mp_word fl ml15">
                                            <p class="mp_01 lh180"><a class="b_blue" href="${basePath}/customerhomepage/${mReply.customerId}.html"  target="_blank">${mReply.customerName}：</a>${mReply.replyContent }</p> 
                                            <p class="mp_02 mt5 b_grey">${(mReply.replyTime)?string('yyyy-MM-dd HH:mm:ss')}<a class="b_blue sec_reply ml30" href="javascript:void(0);" onclick="erji('${mReply.customerId}','${mReply.customerName}','${mReply_index}','${mood_index}')">回复</a>
                                             <#if  cusId?? && cusId == memberId><a class="b_blue ml10" href="javascript:void(0);" onclick="delReplys('${mReply.replyId }')" >删除</a></#if> 
                                        </p>
                                        </div><!--/mp_word-->
                                    </div><!--/mp_cont-->
                                    <#if (mReply.rlist)??>
                                     <#list mReply.rlist as re> 
										<div class="sec_mp ml50">
	                                        <div class="mp_cont mb20 clearfix">
	                                            <a class="fl" href="${basePath}/customerhomepage/${re.customerId}.html" target="_blank">
	                                            <#if (re.customerHeadimg)?? &&  (re.customerHeadimg)!="">
							    	            	<img alt="${re.customerName}" width="50" height="50" src="${re.customerHeadimg}"  /></a>
							                	<#else>
								                	<img alt="${re.customerName}" width="50" height="50" src="${basePath}/images/default_head3.jpg"  /></a>
							                	</#if>
	                                            <div class="mp_word fl ml15" style="width:420px;">
	                                                <p class="mp_01 lh180"><a class="b_blue" href="selectmaincenter-${re.customerId}.html">${re.customerName}：</a>${re.replyContent }</p>
	                                                <p class="mp_02 mt5 b_grey">${(re.replyTime)?string('yyyy-MM-dd HH:mm:ss')}<a class="b_blue sec_reply ml30" href="javascript:void(0);"  onclick="erji('${re.customerId}','${re.customerName}','${mReply_index}','${mood_index}')">回复</a>
	                                                <#if cusId?? && cusId == memberId><a class="b_blue ml10" href="javascript:void(0);" onclick="delReplys('${re.replyId}')" >删除</a></#if> 
	                                                </p>
	                                            </div><!--/mp_word-->
	                                        </div><!--/mp_cont-->
	                                   </div><!--/sec_mp-->
                                     </#list> 
                                    </#if>
                                    
                                    <form id="replyForm${mReply_index}${mood_index}" method="post">
	                                    <div class="rpy_box ml50">
	                                    	<input type="hidden" name="replyShipId" value="${mood.moodId}"/>
	                                     	<input type="hidden" name="customerId" value="${cusId!'' }"/>
	                                     	<input type="hidden" name="replyParentId" id="replyParentId${mReply_index}${mood_index}"/>
	                                     	<input type="hidden" name="rmemberId" id="rmemberId${mReply_index}${mood_index}" value="${mReply.customerId}"/>
	                                        <div class="mp_txa clearfix"><textarea  id="replyContent${mReply_index}${mood_index}" name="replyContent" maxlength="140"  ></textarea></div><!--/mp_txa-->
	                                        <div class="rowElem mp_pub clearfix"><a href="javascript:void(0);" onclick="sendTreply('${mReply_index}','${mood_index}','${mReply.replyId}');"><input class="fr" type="button" value="发表回复" /></a></div>
	                                    </div><!--/rpy_box-->
                                    </form>
                                  </div><!--/mp_wp-->
                                     <script type="text/javascript">
                                	   $(function(){ 
                                           KindEditor.ready(function(K) {
	                                          e["${mReply_index}${mood_index}"]= K.create('#replyContent${mReply_index}${mood_index}', {
	                                        	  pasteType : 1,
	                                        	  items : ['emoticons','image','multiimage'],
					                			  width :550,  
												  cssPath : '${basePath}/js/plugin/kindeditor/plugins/code/prettify.css',
								    			 uploadJson : '${basePath}/js/plugin/kindeditor/jsp/upload_json.jsp'
	                                       		});
	                                       	});
                                 		});
							        </script>
	                              </#list>
	                            </#if>
                                 <form id="replyForm${mood_index}" method="post">     
                                        <div class="rpy_box t_rpy">
	                                	<input type="hidden" name="replyShipId" value="${mood.moodId}"/>
                                     	<input type="hidden" name="customerId" value="${cusId !''}"/>
	                                    <div class="mp_txa clearfix"><textarea name="replyContent" id="replyContent${mood_index}" ></textarea></div><!--/mp_txa-->
	                                    <div class="rowElem mp_pub clearfix"><a href="javascript:void(0);" onclick="sendReply('${mood_index}','${mood.moodId}');"><input class="fr" type="button" value="发表评论" /></a></div>
	                                </div><!--/rpy_box-->
                                </form>
                            </div><!--/m_reply_box-->
                        </div><!--/m_reply-->
	                    </div><!--/mood_box-->
	                </li>
	                  <script type="text/javascript">
		                $(function(){ 
		                    KindEditor.ready(function(K) {
		                   e["${mood_index}"]= K.create('#replyContent${mood_index}', {
		                	   pasteType : 1,
		                			items : ['emoticons','image','multiimage'],
		                			width :550,  
									cssPath : '${basePath}/js/plugin/kindeditor/plugins/code/prettify.css',
					    			uploadJson : '${basePath}/js/plugin/kindeditor/jsp/upload_json.jsp'
		                		});
		                	});
		          		 });
		          	 </script>
                 </#list>
               </#if>
               
            </ul><!--/mood_list-->
            
            <#if (moodMap.list?size>0)>
                <form action="${basePath}/moodlist/${memberId}.html" method="post" name="group_page_form" id="group_page_form">
                 <input id="page" name="pageNo" type="hidden" value="${moodMap.pageNo }"/>
                
                  <div class="pages tc mt30">
                   <!--判断上一页-->
                     <a class="pg_prev" 
                      <#if ((moodMap.pageNo)>1)>
                       href="javascript:page('${moodMap.pageNo-1}')"
                       <#else>href="javascript:void(0)" style="color:#999;visibility:hidden"</#if>
                       >&lt;上一页</a>
                     <#if ((moodMap.startNo)>1)> 
                      <a href="javascript:page('1');" >1</a>
									...
                     </#if>
                     <#list moodMap.startNo..moodMap.endNo as i>
                      <a <#if moodMap.pageNo==i> class="cur"</#if> href="javascript:page('${i}');">${i}</a>
                     </#list>
                     <#if (moodMap.totalPages > moodMap.endNo)>
                        ...
                        <a href="javascript:page('${moodMap.totalPages}')"${moodMap.totalPages}></a>
                     </#if>
                     <!--判断下一页-->
                     <a class="pg_next" 
                       <#if ((moodMap.pageNo+1) > moodMap.totalPages)>
                       href="javascript:void(0)" style="color:#999;visibility:hidden"
                       <#else>href="javascript:page('${moodMap.pageNo+1}')"
                       </#if>>下一页&gt;
                     </a>
                  </div>
                 </form>
                </#if>
        </div><!--/left_cont-->
        
         <input type="hidden"  id="rem"/>
        <script type="text/javascript">
            $(function() {
                $(".rowElem").jqTransform();
            });
        </script>
        
        <div class="right_cont fr">
            <a class="back_albums" href="${basePath}/customerhomepage/${memberId}.html" target="_blank">&gt;&gt;返回<#if  cusId?? && cusId == memberId>我<#else>TA</#if>的个人主页</a>
            
            <div class="latest_viewer mt30">
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
            
           <#if cusId?? && cusId == memberId>
            <div class="latest_comment mt20">
                <h3 class="m_tit">最新评论</h3>
                <#if moodreply??>
	                <ul class="lc_list mt20">
	                   <#list moodreply as reply>
		                   <li class="clearfix">
		                        <a class="fl" href="${basePath}/customerhomepage/${reply.customerId}.html" target="_blank">
				                	<#if (reply.customerHeadimg)?? && reply.customerHeadimg !="">
				                	<img alt="${reply.customerName}" src="${reply.customerHeadimg}" width="50px" height="50px" />
				                	<#else>
				                     <img alt="${reply.customerName}" src="${basePath}/images/default_head3.jpg" width="50px" height="50px"/>
				                	</#if>
					             </a>
		                        <div class="lc_info fl ml10">
		                            <div class="comment_cont clearfix lh150">
		                                <div class="fl head_box"><a href="javascript:;">${reply.customerName}:</a></div>
			                             <#assign content = reply.replyContent?trim>
			                             <#if content?length gt 20>
			                             ${content?substring(0,20)}...
			                             <#else>${content}
			                             </#if>
		                            </div><!--/comment_cont-->
		                            <p class="cm_date">${(reply.replyTime)?string('yyyy-MM-dd HH:mm:ss')}</p>
		                        </div><!--/lc_info-->
		                    </li>
	                   </#list>
	                </ul><!--/lc_list-->
                </#if>
            </div><!--/latest_comment-->
            </#if>
        </div><!--/right_cont-->
    </div><!--/wrapper-->
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
	 <script type="text/javascript" charset="utf-8" src="${basePath}/js/plugin/kindeditor/kindeditor-min.js"></script>
<script type="text/javascript" charset="utf-8" src="${basePath}/js/plugin/kindeditor/lang/zh_CN.js"></script>
	 <script type="text/javascript">
		 var e = new Array();
	     var e1 = new Array();
	     var ep;
		$(function() {
		 $(".rowElem").jqTransform();
	     KindEditor.ready(function(K) {  
			ep=K.create('#moodContent', {
				pasteType : 1,
				items : ['emoticons','image','multiimage'],
				cssPath : '${basePath}/js/plugin/kindeditor/plugins/code/prettify.css',
    			uploadJson : '${basePath}/js/plugin/kindeditor/jsp/upload_json.jsp',
    			allowFileManager : true,
				afterChange : function() {
				    var limitNum = 140;  //设定限制字数
				    if(this.count('text') > limitNum) {
				        pattern = '字数超过限制，请适当删除部分内容';
				        //超过字数限制自动截取
				        } else {
				        //计算剩余字数
				        var result = limitNum - this.count('text');
				        pattern = '还可以输入' +  result + '字';
				        }
				    $('#sp').html(pattern); 
				}
			 });
		 });
	  }); 
				
				
	 function mood(){
	   if(ep.count('text')>140){
		   return ;
	   }           
	   ep.sync();
	    $.ajax({
			   type:'post',
			   data: $('#moodForm').serialize(),
		       url: "${basePath}/sendmood.htm",  
		       success: function(result){  
		            if (result==1){  
		            	 window.location.href="${basePath}/moodlist/${memberId}.html";
		           	}else{   
		             	alert("发布失败");
		             }  
	            }  
	     });   
	 }
   
	//删除心情
	function delMood(obj){  
            if (confirm("你确定要删除此心情？")){  
                $.post('${basePath}/delmood.htm',{moodId:obj},function(result){  
                    if (result>0){
                    	window.location.href="${basePath}/moodlist/${memberId}.html";
                    } else { 
                    	 $("#message").html('删除失败');
        	           	 dia(1);
                    }  
                },'json');  
            }  
	}	
	
	function sendReply(i,objId){
	   e[i].sync();
	   $.ajax({
	     type:'post',
	     data: $('#replyForm'+i).serialize(),
	     url:"${basePath}/pubmoodreply.htm", 
	     success: function(result){ 
             if (result==1){    
            	window.location.href="${basePath}/moodlist/${memberId}.html";
           	 }else if (result==-1){  
           	  $("#message").html('您未登录,请<a href="login.html" style="color:blue;">登录</a>后操作！');
           	  dia(1);
           	 }else{   
           	  $("#message").html('发布失败');
           	  dia(1);
            } 
           }   
	   });
   } 
   
   function sendTreply(plindex,pindex,replyId){
	 	e[plindex+""+pindex].sync(); 
	   $("#replyParentId"+plindex+""+pindex).val(replyId);
	   $("#rmemberId"+plindex+""+pindex).val($("#rem").val());
	   $.ajax({
	       type:'post',
	       data: $('#replyForm'+plindex+""+pindex).serialize(),
	       url:"${basePath}/pubmoodreply.htm", 
	       success: function(result){ 
	             if (result==1){    
	            	window.location.href="${basePath}/moodlist/${memberId}.html";
	           	 }else if (result==-1){  
	           	    $("#message").html('您未登录,请<a href="login.html" style="color:blue;">登录</a>后操作！');
	           	    dia(1);
	           	}else{   
	           	 	$("#message").html('发布失败');
	           	 	dia(1);
	             }  
	         }  
	   });  
   }
   
   function erji(customerId,customername,plindex,pindex){
	   $("#rem").val(customerId); 
	  if(customername!=''){
		e[plindex+pindex].html('回复 '+customername+"：");
	  }else{
		e[plindex+pindex].html(''); 
	  }
   } 
   
     function delReplys(replyId){
	 		 $.post('${basePath}/deletereply.htm',{replyId:replyId},function(result){  
   	          if (result>01){  
   	        	  topage(1);
   	          } else { 
   	        	  alert("删除失败！");
   	          }  
   	      },'json');  
	  }
	  
	  function topage(page){ 
        		var memberId=$("#memberId").val();
        		window.location.href="${basePath}/moodlist/${memberId}.html";  
       } 
        	
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