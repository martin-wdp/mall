<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<#include "../common/basepath.ftl"> 
<title>图片详情-${topmap.systembase.bsetName}</title>
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/css/group.css"/> 
<link rel="stylesheet" type="text/css" href="${basePath}/css/jqtransform.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/face/qqFace.css" />
<script type="text/javascript" charset="utf-8" src="${basePath}/js/plugin/kindeditor/kindeditor-min.js"></script>
<script type="text/javascript" charset="utf-8" src="${basePath}/js/plugin/kindeditor/lang/zh_CN.js"></script>

<style type="text/css">
.back_albums:hover{text-decoration:none; }
.pic_edit, .pic_move, .pic_fc {
color: #999;
background: url(../images/img_edit.gif) no-repeat;
padding-left: 20px;
margin-left: 10px;
margin-top: 5px;
}

.d_lk .ok_btn, .d_lk .cancel_btn {display:inline-block; zoom:1; *display:inline; background:url(../images/g_search_btn.gif) no-repeat; width:62px; height:24px; color:#fff; text-align:center; line-height:24px; font-weight:700;}
.create_albums dt {float:left; width:70px; line-height:30px;}
.create_albums dd {margin-bottom:15px; margin-left:65px; line-height:30px;}
.personal_reg {
    color: #E2393F;
    padding-left: 10px;
}
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
<input type="hidden" value="${(cusId)!''}" id="cusId"/>
<input type="hidden" value="${(custome.customerPower)!''}" id="cusPower"/>
<input type="hidden" value="${(group.limitReplyType)!''}" id="imgReplyFlag"/>
<input type="hidden" value="${basePath}" id="basePath"/>
<input type="hidden" id="isSiteManager" name="isSiteManager" value="${(cinfo.isSiteManager)!''}" />
<div class="wrapper clearfix">
    	<div class="left_cont fl">
        	<div class="pic_crumbs">
            	<h3 class="m_tit"><a href="${basePath}/groupdetail/${group.groupId}.html" target="_blank">${group.groupName}</a> 小组相册</h3>
                <span>&gt;</span>
                <span class="f14" id="groupImgTitle">${(img.groupImgTitle)!''}</span>
            </div><!--/pic_crumbs-->
            
            <div class="picsWp mt30">
            	<div id="p_slides" class="p_slides">
                	<div class="slides_container">
                        <#if imglist??>
                        <#list imglist as groupimg>
                        <div class="slide">
                        	<a href="${groupimg.groupImgUrl}" target="_blank"><img alt="${(img.groupImgTitle)!''}" src="${groupimg.groupImgUrl}"  width="${(groupimg.groupImgWidth)!'584'}" height="${(groupimg.groupImgHeight)!'436'}"/></a>
                        </div>
                        </#list>
                        </#if>
                    </div><!--/slides_container-->
                  <input type="hidden" value="${prev!''}" id="prev" />
                  <input type="hidden" value="${next!''}" id="next" />
	              <a class="prev" href="javascript:void();" onclick="prev();"></a>
	              <a class="next" href="javascript:void();" onclick="next();" ></a>
                </div><!--/p_slides-->
                
                <div class="pics_d">
                     <div class="albums_desc"><span id="remark">${(img.groupImgDes)!''} </span></div>
                      <div class="mt10" >来自：<span id="lz"><a class="from_user" href="${basePath}/customerhomepage/${img.customerId }.html">${(img.customerName)!'' }</a></span></div>
	                  <div class="b_grey mt10">上传时间 <span id="sc">${img.groupImgCreateTime?string('yyyy-MM-dd')}</span> </div> 
                    <div class="clearfix mt15">
                    	<!-- Baidu Button BEGIN -->
                        <div id="bdshare" class="bdshare_t bds_tools get-codes-bdshare fl">
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
			           <div id="dphoto">
			             <#if custome??>
			               <#if (custome.customerPower)=='1' ||(custome.customerPower)=='2'>
				                <a class="pic_delete fr" href="javascript:delgroupImg(${img.groupImgId});">删除</a>
				                <a  class="pic_edit fr" href="javascript:dia(2);">编辑</a>
			               </#if>
			               <#if (custome.customerPower)=='0' >
			                  <#if (custome.customerId)==(img.customerId)>
				                <a class="pic_delete fr" href="javascript:delgroupImg(${img.groupImgId});">删除</a>
				                <a  class="pic_edit fr" href="javascript:dia(2);">编辑</a>
				              </#if>
			               </#if>
			               <#elseif (cinfo ?? && cinfo.isSiteManager=='1')>
				                <a class="pic_delete fr" href="javascript:delgroupImg(${img.groupImgId});">删除</a>
				                <a  class="pic_edit fr" href="javascript:dia(2);">编辑</a>
					     </#if>
				       </div>
                    </div>
                </div><!--/pics_d-->
            </div><!--/picsWp-->
            
            <div class="pics_comment mt30">
            	<h3 class="m_tit">评论（<span id="pagecount">${(pb.rows)!'0'}</span>）</h3>
                <ul class="comment_list mt20" id="rep">
                   <#if pb.list??>
                     <#list pb.list as reply>
	                    <li class="clearfix">
	                        <div class="rowElem"><input type="checkbox" /></div>
	                        <div class="head_box fl"><a href="${basePath}/customerhomepage/${reply.customerId}.html">
	                        <#if (reply.customerHeadimg)?? && (reply.customerHeadimg)!="">
	                 	       <img alt="${reply.customerName}" width="50" height="50" src="${reply.customerHeadimg}" />
	                          <#else>
	                 	       <img alt="${reply.customerName}" width="50" height="50" src="${basePath}/images/default_head3.jpg" />
	                        </#if>
	                        
	                        </a></div>
	                        <div class="cmt_wp fl ml10">
	                            <div class="cmt_hd clearfix">
	                                <a class="cmt_name fl" href="${basePath}/customerhomepage/${reply.customerId}.html">${reply.customerName}</a>
	                                <#if !ta??>
	                                <a class="cmt_only fl" href="javascript:onlyhe('${reply.customerId }');" id="only">只看TA</a>
	                                </#if>
	                                <#if ta??>
	                                <a class="cmt_only fl" href="javascript:onlyhe('');" id="all">全部</a>
	                                </#if>
	                                <#if (custome?? && ((custome.customerId)==(reply.customerId) || (custome.customerPower)=='1'||(custome.customerPower)=='2'))||(cinfo?? && cinfo.isSiteManager=='1')>
	                                	<a class="cmt_del fr" href="javascript:delreply('${reply.replyId}');">删除</a>
	                                </#if>
	                            </div><!--/cmt_hd-->
	                            <#if (reply.replyRemark)??>
	                            <#assign content1 = (reply.replyRemark)?replace('<img src="face/face/','<img src="'+basePath+'/face/face/') >
	                            ${(content1)!''}
	                            </#if>
	                            <p class="cmt_cont">
	                            <#assign content = (reply.replyContent)?replace('<img src="face/face/','<img src="'+basePath+'/face/face/') >
	                            <!--${(reply.replyContent)!''}</p>-->
	                            ${(content)!''}</p>
	                            <p class="cmt_date">${(reply.replyTime)?string('yyyy-MM-dd HH:mm:ss')}</p>
	                            <div style="display:none;" id="hf${reply.replyId }">${reply.replyContent}</div> 
	                            <div class="cmt_op clearfix"><a class="fr" href="javascript:re('${reply.customerId}','${reply.customerName}','${reply.replyId}','1');">引用</a><a class="fr" href="javascript:re('${reply.customerId}','${reply.customerName}','${reply.replyId}','0');">回复</a></div>
	                        </div><!--/cmt_wp-->
	                    </li>
                     </#list>
                   </#if>
                </ul><!--/comment_list-->
               <input type="hidden" name="customerId"  value="${img.customerId}"/> 
 			   <input type="hidden" name="groupId" id="groupId" value="${img.groupId}"/>
 			   <input type="hidden" name="ocustomerId" id="ocustomerId" value="${cusId!''}"/> 
 			   <input type="hidden" id="groupImgId" value="${img.groupImgId }"/>
                
              
                <form action="${basePath}/groupimgdetail/${group.groupId}-${img.groupImgId}-${img.customerId}.html" method="post" name="group_page_form" id="group_page_form">
                 <input id="page" name="pageNo" type="hidden" value="${pb.pageNo }"/>
                 <input id="ta" name="ta" type="hidden" value="${ta!''}"/>
                  <div class="pages tc mt30" id="pages">
                   <!--判断上一页-->
                     <a class="pg_prev" 
                      <#if ((pb.pageNo)>1)>
                       href="javascript:page('${pb.pageNo-1}','${ta!''}')"
                       <#else>href="javascript:void(0)" style="color:#999;visibility:hidden"</#if>
                       >&lt;上一页</a>
                     <#if ((pb.startNo)>1)> 
                      <a href="javascript:page('1','${ta!''}');" >1</a>
									...
                     </#if>
                      <#if ((pb.list)?size>0)> 
	                     <#list pb.startNo..pb.endNo as i>
	                      <a <#if pb.pageNo==i> class="cur"</#if> href="javascript:page('${i}','${ta!''}');">${i}</a>
	                     </#list>
                     </#if>
                     <#if (pb.totalPages > pb.endNo)>
                        ...
                        <a href="javascript:page('${pb.totalPages}','${ta!''}')"${pb.totalPages}></a>
                     </#if>
                     <!--判断下一页-->
                     <a class="pg_next" 
                       <#if ((pb.pageNo+1) > pb.totalPages)>
                       href="javascript:void(0)" style="color:#999;visibility:hidden"
                       <#else>href="javascript:page('${pb.pageNo+1}','${ta!''}')"
                       </#if>>下一页&gt;
                     </a>
                  </div>
                 </form>
                <#if !cusId??>
                 <div class="quick_reply mt20">
                    <h4 class="m_tit">快速回复</h4>
                     <div class="replyWp mt10" style="height:150px;">
                        <div><img alt="" src="${basePath}/images/reply_bg1.gif" /></div><!--/更换为编辑器-->
                        <p class="noLogin" >您目前尚未登录！请先<a class="mr5 ml5" href="${basePath}/login.html">登录</a>或<a class="ml5" href="${basePath}/register.html">注册</a></p>
                    </div><!--/replyWp-->
                 </div><!--/quick_reply-->
                </#if>
                <#if cusId??> 
                <div class="quick_reply mt20">
                    <h4 class="m_tit">快速回复</h4>
                     <form method="post" id="moodForm" action=""> 
                     	<div id="re">
                     	
                     	</div>
                   		<#--><div class="rls_mood clearfix mt10"> 
			            	<div class="face_box fl"><img alt="" id="face" src="../images/face_img.gif"></div>
			            </div><!--/rls_mood-->  
			            <div class="mood_wp mt20 clearfix">  
			            	<textarea class="mood_txa" id="content1" name="replyContent"  onchange="replyOnkeyup(this);" onkeyup="replyOnkeyup(this);"  placeHolder="快点对TA说点什么吧~"></textarea>
			            	<input type="hidden" name="replyShipId" id="replyShipId" value="${img.groupImgId}"/>
			            	<input type="hidden" id="replyContent" name="replyContent"/>
			            	<input type="hidden" id="customerId" name="customerId" value="${cusId!''}"/>
			            	<input type="hidden" name="groupImgId"  id="rph" value="${img.groupImgId }"/>
			            	<input type="hidden" name="groupId" value="${img.groupId }"/>
			            </div><!--/row_elem-->
		            </form>
		           </br>
                    <div class="rowElem  clearfix">
                         <span class="fl" style="line-height:30px;font-family:'微软雅黑'; font-size:14px;">验证码：</span>
                     	 <input  type="text" style="width:120px;" name="varification" id="varification" maxLength="5" onfocus="getPatcha()"/>
			              <label>
			               <img id="checkCodeImg" class="vm ml5" src="${basePath}/patchca.htm" alt="验证码" style="cursor:pointer;" onclick="this.src=this.src+'?'+Math.random(); " />
			             </label>
			             <label class="small">看不清？<a id="checkCodeA" href="javascript:void(0)">换一张</a></label>
			             <span id="varification_error" class="personal_reg"></span>
                   </div>
                   </br>
                    <div class="rowElem clearfix mt10">
                   	 	<a href="javascript:void(0);" onclick="groupimgreply();" class="fl" >
                    		<input type="button" value="发布回复"/>
                    	</a></div>
                </div><!--/quick_reply-->
               </#if>
            </div><!--/pics_comment-->
        </div><!--/left_cont-->
        
        <div class="right_cont fr">
        	<a class="back_albums" href="${basePath}/groupimgalbums/${img.groupId}.html">&gt;&gt;返回小组相册</a>
        </div><!--/right_cont-->
    </div>
   
     <div class="dialog1 dia2" style="width:450px; height:220px;">
    	<a class="close" href="javascript:cls1();"></a>
        <h4>编辑</h4>
        <form id="editgroupImgForm" method="post" action="">
        <dl class="create_albums mt20 ml50 clearfix">
        	<dt>相片名称：</dt>
            <dd> <input type="hidden" name="groupImgId" id="editId" value="${img.groupImgId }"/>
                <input type="hidden" name="customerId" id="editCusId" value="${img.customerId }"/>
            <input type="text"  class="ca_text" name="groupImgTitle" maxlength="15" placeholder="最多15个字"  id="editName" value="${(img.groupImgTitle)!'' }"/>
            </dd>
           <dt>相片备注：</dt>
            <dd> 
            <textarea  class="ca_txa" name="groupImgDes"  maxlength="70" placeholder="最多70个字"  id="editRemark" >${(img.groupImgDes)!'' }</textarea>
            </dd>
        </dl><!--/create_albums-->
        </form>
        <div class="tc mt10 d_lk"><a class="ok_btn" href="javascript:void(0);" onclick="editgroupimg();">确定</a><a class="cancel_btn" href="javascript:cls1();">取消</a></div>
    </div><!--/dialog-->
    
 <div class="mask1"></div>
      <div class="dialog1 dia1"  style="width:300px; height:120px;">
    	<a class="close" href="javascript:cls1();"></a> 
        <h4>提示</h4>
        <div class="create_albums mt20 ml50 clearfix">
        	<span id="message"></span>
        </div><!--/create_albums-->
    </div><!--/dialog3-->
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
<script type="text/javascript" src="${basePath}/js/slides.min.jquery.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery.jqtransform.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${basePath}/face/jquery.qqFace.min.js"></script>
<script type="text/javascript" src="${basePath}/js/social/group_img.js"></script>
<script type="text/javascript" charset="utf-8" src="${basePath}/js/plugin/kindeditor/kindeditor-min.js"></script>
<script type="text/javascript" charset="utf-8" src="${basePath}/js/plugin/kindeditor/lang/zh_CN.js"></script>
	<script type="text/javascript">
				$(function() {
					$(".rowElem").jqTransform();
				});
				
			  $(function(){
					var startSlide = '${startSlide!''}';
					$('#p_slides').slides({
						preload: true,
						generatePagination: false,
						hoverPause: true,
						start: startSlide
					});
				});
				
			
				$(function(){ 
				      KindEditor.ready(function(K) {
				      editor= K.create('#content1', {
				    			items : ['emoticons','image','multiimage'],
				    			pasteType:1,
				    			cssPath : '${basePath}/js/plugin/kindeditor/plugins/code/prettify.css',
				    			uploadJson : '${basePath}/js/plugin/kindeditor/jsp/upload_json.jsp',
				    			allowFileManager : true,
				    			afterCreate : function() {
								var self = this;
								K.ctrl(document, 13, function() {
									self.sync();
									document.forms['example'].submit(); 
								});
								K.ctrl(self.edit.doc, 13, function() {
									self.sync();
									document.forms['example'].submit();
								});
							  }
				    		});
				    	});
					 });
				
			$(function(){
			//选中首页社区
			$(".sort_list li").removeClass("cur");
			$(".sort_list li:eq(1)").addClass("cur");
        });
	</script>
</body>
</html>


