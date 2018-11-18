jQuery.fn.isChildOf = function(b){ return (this.parents(b).length > 0); };
jQuery.fn.isChildAndSelfOf = function(b){ return (this.closest(b).length > 0); };


$(function() {
	$(".sub").button({ icons: { primary: "ui-icon-zoomout"  } });
	tabCounter = 2;
	//$("input[id^='datepicker']").datepicker();
	$("tr[id^='expand']").hide();
	$("#dialog-wrong-tip").hide();
	$("#expand").hide();
	$(".advancedsearch").hide();
	$("#dialog-confirm").hide();
	$("#dialog-tip").hide();
	$("#dialog-err-tip").hide();
	$("#district_a").hide();
	//$("#tabs").tabs();
	$("#expand_content").hide();
	$("#dialog-wrong-tip").hide();
	$("#dialog-authority").hide();
	$("#dialog-consult-confirm").hide();
	$("input[type=submit],button").button().click(function(event) {
		event.preventDefault();
	});
	$("#toggle").click(function() {
		$("#combobox, #moneyTypes").toggle();
	});
	$(document).tooltip();
	
	// 设置小组
	$("#modified-group").button().click(function() {
		if(checkSelected('groupId',1)){
			$('#updateForm').attr('action', 'updategroup.htm');
			doSearchGroup($("input[name='groupId']:checked").val());
		}else{
			 $("#dialog-tip").dialog({
	     			resizable : false,
	     			height : 150,
	     			width : 270,
	     			modal : true,
	     			autoOpen : true,
	     			buttons : {
	     				"确定" : function() {
	     					$(this).dialog("close");
	     				}
	     			}
	     		});
		}
	});
	
	//解散小组
	$("#dissolove-group").button().click(function(){
		if(checkSelected('groupId',0)){
			batchOperation("确认要解散该小组吗？","dissolvegroup.htm","togroup.htm");
			//dissolveGroup("dissolvegroup.htm","togroup.htm");
		}else{
			showTipAlert("请先选择一行!");
		}
	});


	/**
	 * 批量操作 提示框
	 * 通用
	 * @param tip 提示内容
	 */
	function batchOperation(tip,url,initUrl) {
		$("#modalDialog").remove();
		var dialogHtml =
			'<div class="modal fade" id="modalDialog" tabindex="-1" role="dialog" style="z-index:99999;">'+
			'    <div class="modal-dialog">'+
			'        <div class="modal-content">'+
			'            <div class="modal-header">'+
			'                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>'+
			'               <h4 class="modal-title">操作提示</h4>'+
			'           </div>'+
			'           <div class="modal-body" style="text-align: center;">'+
			tip+
			'           </div>'+
			'           <div class="modal-footer">'+
			'               <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="passMultiWin(\''+url+'\',\''+initUrl+'\');">确定</button>'+
			'			<button type="button" class="btn btn-default" data-dismiss="modal" onclick="$(\'#modalDialog\').modal(\'hide\');">取消</button>'+
			'           </div>'+
			'       </div>'+
			'   </div>'+
			'</div>';
		$(document.body).append(dialogHtml);
		$('#modalDialog').modal('show');
	}


	//通过待审核小组
	$("#pass-group").button().click(function(){
		if(checkSelected('groupIds',0)){
			batchOperation("确认要审核通过该小组吗？","passgroup.htm","checkgrouplist.htm");
		}else{
			showTipAlert("请先选择一行!");
		}
	});
	
	//拒绝待审核小组
	$("#refuse-group").click(function(){
		if(checkSelected('groupIds',1)){
			$("#dialog-refuse-tip2").modal('show');
			//$("#dialog-refuse").dialog("open");
		}else{
			showTipAlert("请先选择一行!");
			//$("#dialog-refuse-tip2").modal('show');
		}
	});
	
	
	$("#refuse").button().click(function(){
		var reason = $("#reason").val();
		refuseGroupMultiWin("refusegroup.htm",reason,"checkgrouplist.htm");	
		
	});
	
	$("#cancel").button().click(function(){
		$("#dialog-refuse-tip").dialog("close");
		
	});
	
	//活跃小组
	$("#active-group").click(function(){
		if(checkSelected('groupId',0)){
			activeGroup("activegroup.htm","activegrouplist.htm");
		}else{
			showTipAlert("请先选择一行！");
		}
	});
	
	//改活跃小组为一般小组
	$("#common-group").button().click(function(){
		if(checkSelected('groupId',0)){
			activeGroup("commongroup.htm","activegrouplist.htm");
		}else{
			 showTipAlert("请先选择一行！");
		}
	});
	
	//热门小组
	$("#hot-group").click(function(){
		if(checkSelected('groupId',0)){
			hotGroup('hotgroup.htm','activegrouplist.htm');
		}else{
			showTipAlert("请先选择一行！");
		}
	});
	
	//改热门小组为一般小组
	$("#cancel-group").button().click(function(){
		if(checkSelected('groupId',0)){
			cancelGroup('cancelhotgroup.htm','activegrouplist.htm')
		}else{
			showTipAlert("请先选择一行！");
		}
	});

	//推荐小组
	$("#recommend-group").button().click(function(){
		if(checkSelected('groupId',0)){
			recommendGroup('recommendgroup.htm','activegrouplist.htm');
		}else{
			showTipAlert("请先选择一行！");
		}
	});
	
	//改推荐小组为一般小组
	$("#unrecommend-group").button().click(function(){
		if(checkSelected('groupId',0)){
			unrecommendGroup('cancelrecommendgroup.htm','activegrouplist.htm');
		}else{
			showTipAlert("请先选择一行！");
		}
	});

	//清空搜索框
	$("#clear").button().click(function() {
		$(".advancedsearch input, .sp_search input").each(
			function(){
				$(this).val("");
			}
		);
		$(".advancedsearch select, .sp_search select").each(
			function(){
				$("option[value='']").prop("selected","selected");
			}
		);
	});

	/**
	 * 设置小组确定按钮
	 * */
	$("#updateFormSub").click(function(){
		$('#updateForm').submit();
	});
 });


/**
 * 新boss设置小组
 * */
function modifiedsGroup(groupId){
	$('#updateForm').attr('action', 'updategroup.htm');
	doSearchGroup(groupId);
}

function checkNull(o, n, obj) {
	if (o.val() == null || o.val()=='') {
		o.addClass("ui-state-error");
		updateTips(n, obj);
		return false;    
	} else {
		obj.text('').removeClass("ui-state-highlight");
		return true;
	}
}

/*ajax 通过ID查询并塞值到修改页面*/
function doSearchGroup(groupId){
	modified=1;
	$.post("querygroupvobyid.htm?groupId="+groupId,function(data){
		 	// $("input[id^='customerUsername']").val(data.customerUsername);
		      $("#groupupdateId").val(data.groupId);
		     if(data.groupSecret == 0){
		    	 $("#groupSecret1").prop('checked', 'checked');
		     }else {
		    	 $("#groupSecret2").prop('checked', 'checked');
		     }
		     if(data.groupStatus == 0){
		    	 $("#groupStatus1").prop('checked', 'checked');
		     }else {
		    	 $("#groupStatus2").prop('checked', 'checked');
		     }
		     if(data.groupIsHot == 0){
		    	 $("#groupIsHot1").prop('checked', 'checked');
		     }else {
		    	 $("#groupIsHot2").prop('checked', 'checked'); 
		     }
		     if(data.limitAddType == 0){
		    	 $("#limitAddType1").prop('checked', 'checked');
		     }else if(data.limitAddType == 1){
		    	 $("#limitAddType2").prop('checked', 'checked');
		     }else{
		    	 $("#limitAddType3").prop('checked', 'checked');
		     }
		     if(data.limitCondition == 0){
		    	 $("#limitCondition1").prop('checked', 'checked');
		     }else if(data.limitAddType == 1){
		    	 $("#limitCondition2").prop('checked', 'checked');
		     }else{
		    	 $("#limitCondition3").prop('checked', 'checked');
		     }
		     if(data.limitReplyDelType == 0){
		    	 $("#limitReplyDelType1").prop('checked', 'checked');
		     }else{
		    	 $("#limitReplyDelType2").prop('checked', 'checked');
		     }
		     if(data.limitModifyTopicType == 0){
		    	 $("#limitModifyTopicType1").prop('checked', 'checked');
		     }else{
		    	 $("#limitModifyTopicType2").prop('checked', 'checked');
		     }
		     if(data.limitDelPicType == 0){
		    	 $("#limitDelPicType1").prop('checked', 'checked');
		     }else{
		    	 $("#limitDelPicType1").prop('checked', 'checked');
		     }
		     if(data.limitReplyType == 0){
		    	 $("#limitReplyType1").prop('checked', 'checked');
		     }else if(data.limitReplyType == 1){
		    	 $("#limitReplyType2").prop('checked', 'checked');
		     }else{
		    	 $("#limitReplyType3").prop('checked', 'checked');
		     }
		     //$("#customerUsername").append("<input type='hiden' name='customerId' value='"+customerId+"' />");
			 $("#setGroup").modal('show');
	});
}

/**
 * 验证是否有非法字符
 *
 * */
function checkInputContent(){
	var regx=/^[\u4e00-\u9fa5_A-Za-z0-9]*$/;
	var ff=regx.test($(".search_text").val());
	if(!ff){
		$("#dialog-refuse-tip1").modal('show');
	}
	return ff;
}

// 展开详细信息 收回详细信息
function expandDiv(obj,o,url,hei) {
	var divid="#expand"+obj;
	var expand = $(divid);
	if (expand.is(":hidden")) {
		$(divid +" td").html("");
		$(divid +" td").append("<iframe frameborder='0' scrolling='yes' width='100%' height='"+hei+"' src='"+url+"="+obj+"'></iframe>");
//		$(divid +" td").append($("#expand_content").html());
		expand.show("slow");
//		$("#expand_content").html("");
		o.src = "images/finder_up_arrow.gif";
	} else {
		o.src = "images/finder_drop_arrow.gif";
		$(divid +" td").html("");
		expand.hide();
	}
}


/*改变每页显示的行数*/
function changePageShowC(url,totalPages){
	  var size=$("#list_size").val();
	  var pageno=$("#list_pageno").val();
	  if(pageno>totalPages){
		  pageno=totalPages;
	  }
	  pageno=(pageno==0?1:pageno);
	  if(isNaN(pageno)|isNaN(size)|size<=0|pageno<=0){
		  $("#dialog-err-tip").dialog({
				resizable : false,
				height : 150,
				width : 270,
				modal : true,
				autoOpen : true,
				buttons : {
					"确定" : function() {
						$(this).dialog("close");
					}
				}
		});
	  }else{
		  test(url, pageno, size);
	  }
}

//点击a实现分页
function test(url,pageNo,pageSize){
	$('#pageFoot').attr('action', url);
	$("#pageSize").val("");
	$("#pageNo").val("");
	$("#pageSize").val(pageSize);
	$("#pageNo").val(pageNo);
	$('#search_from').submit();
}

// 显示高级搜索
function showadvancedsearch(t) {
	var advs = $(".sp_search");
	if (advs.is(":hidden")) {
		$(t).find("span").text("关闭搜索");
		//selectOption();
		advs.show();
		advs.animate({
			right: 0
		},300);
	} else {
		$(t).find("span").text("高级搜索");
		advs.fadeOut("fast",function(){
			advs.css("right",-(advs.width()+42));
		});
	}
	$(document).click(function(event){
		if($(event.target).isChildAndSelfOf($(".sp_search, .sub"))) {
			
		} else {
			$(".sp_search").fadeOut("fast",function(){
				$(t).find("span").text("高级搜索");
				$(".sp_search").css("right",-($(".sp_search").width()+42));
			});
		};
	});
}

//检查是否选中一行
function checkSelected(objId,modifyFlag){
	checkedList = new Array();
  	$("input[name='"+objId+"']:checked").each(function() {
  		checkedList.push($(this).val());
  	});
  	if(modifyFlag!=0){
  		if(checkedList.length ==1){
  	  		return true;
  	  	}else{
  	  		return false;
  	  	}
  	}
  	
  	if(checkedList.length > 0){
  		return true;
  	}else{
  		return false;
  	}
}; 

/**
 * 批量通过 通用
 * @param url 			通过路径
 * @param initUrl   	成功跳转路径
 */
function passMultiWin(url,initUrl) {
      $.post(url,{groupIds:checkedList},function(result){  
	      if (result > 0){
	          location.href=initUrl;
	      } else { 
	    	  
          }
      },'json');
}

/**
 * 小组待审核拒绝 通用
 * @param url 			拒绝路径
 * @param initUrl   	成功跳转路径
 */
function refuseGroupMultiWin(url,initUrl) {
	var reason=$("#reason").val();
	reason=encodeURI(encodeURI(reason));
      $.post(url,{groupIds:checkedList,refuseReason:reason},function(result){
	      if (result > 0){
	          location.href=initUrl;
	      } else { 
	    	  
          }
      },'json');
}

/**
 * 批量拒绝 通用
 * @param url 			拒绝路径
 * @param initUrl   	成功跳转路径
 */
function refuseMultiWin(url,initUrl) {
      $.post(url,{groupIds:checkedList},function(result){  
	      if (result > 0){
	          location.href=initUrl;
	      } else { 
	    	  
          }
      },'json');
}

/**
 * 批量解散小组
 * @param url 解散路径
 * @param initUrl  成功跳转路径
 */
function dissolveGroup(url,initUrl){
	$.post(url,{groupIds:checkedList},function(result){
		if(result > 0){
			location.href=initUrl;
		}else{
			
		}
	},'json');
}

/**
 * 活跃小组
 * @param url 活跃路径
 * @param initUrl 成功跳转路径
 */
function activeGroup(url,initUrl){
	$.post(url,{groupIds:checkedList},function(result){
		if(result > 0){
			location.href = initUrl;
		}
	},'json');
}

/**
 * 批量设置活跃小组为一般小组
 * @param url 一般小组路径
 * @param initUrl 成功跳转路径
 */
function commmonGroup(url,initUrl){
	$.post(url,{groupIds:checkedList},function(result){
		if(result > 0){
			location.href = initUrl;
		}
	},'json');
}


/**
 * 单个操作 提示框
 * 通用
 * @param tip 提示内容
 */
function singleOperation(tip,url,initUrl,groupId) {
	$("#modalDialog").remove();
	var dialogHtml =
		'<div class="modal fade" id="modalDialog" tabindex="-1" role="dialog" style="z-index:99999;">'+
		'    <div class="modal-dialog">'+
		'        <div class="modal-content">'+
		'            <div class="modal-header">'+
		'                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>'+
		'               <h4 class="modal-title">操作提示</h4>'+
		'           </div>'+
		'           <div class="modal-body" style="text-align: center;">'+
		tip+
		'           </div>'+
		'           <div class="modal-footer">'+
		'               <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="singleSet(\''+url+'\',\''+initUrl+'\',\''+groupId+'\');">确定</button>'+
		'			<button type="button" class="btn btn-default" data-dismiss="modal" onclick="$(\'#modalDialog\').modal(\'hide\');">取消</button>'+
		'           </div>'+
		'       </div>'+
		'   </div>'+
		'</div>';
	$(document.body).append(dialogHtml);
	$('#modalDialog').modal('show');
}


/**
 * 设置单个活跃小组为一般小组
 * @param url 一般小组路径
 * @param initUrl 成功跳转路径
 */
function singleSet(url,initUrl,groupId){
	checkedList=new Array();
	checkedList.push(groupId);
	$.post(url,{groupIds:checkedList},function(result){
		if(result > 0){
			location.href = initUrl;
		}
	},'json');
}

/**
 * 热门小组
 * @param url 热门路径
 * @param initUrl 成功跳转路径
 */
function hotGroup(url,initUrl){
	$.post(url,{groupIds:checkedList},function(result){
		if(result > 0){
			location.href = initUrl;
		}
	},'json');
}

/**
 * 批量设置热门小组为一般小组
 * @param url 一般小组路径
 * @param initUrl 成功跳转路径
 */
function cancelGroup(url,initUrl){
	$.post(url,{groupIds:checkedList},function(result){
		if(result > 0){
			location.href = initUrl;
		}
	},'json');
}

/**
 * 推荐小组
 * @param url 推荐路径
 * @param initUrl 成功跳转路径
 */
function recommendGroup(url,initUrl){
	$.post(url,{groupIds:checkedList},function(result){
		if(result > 0){
			location.href = initUrl;
		}
	},'json');
}

/**
 * 设置推荐小组为一般小组
 * @param url 一般小组路径
 * @param initUrl 成功跳转路径
 */
function unrecommendGroup(url,initUrl){
	$.post(url,{groupIds:checkedList},function(result){
		if(result > 0){
			location.href = initUrl;
		}
	},'json');
}


function jumpIndex(){
	window.parent.location.href='index.htm';
}




$(function() {
	//搜索选择
	$(".search_sel span").text($(".search_sel select").find("option:selected").text());
	$(".search_sel select").change(function() {
		var val = $(this).find("option:selected").text();
		//** 会员 **//*
		if(val=="小组名称"){
			$(".search_text").attr("name","groupName");
		}else if(val=="小组创建者"){
			$(".search_text").attr("name","groupCreateAuthorName");
		}
		//** 咨询 **//*
		$(".search_sel span").text(val);
	 });
	});
	
	/**
	 * 按条件搜索小组列表
	 */
	function searchgroup(){
		if(checkInputContent()){
			var convalue = $(".search_text").val();
			var condition = $(".search_sel span").text();
			convalue =  encodeURI(encodeURI(convalue));
			condition =  encodeURI(encodeURI(condition));
			$('#search_from').attr("action", "querybygroup.htm?showflag=1&convalue="+convalue+"&condition="+condition);
			$("#pageSize").val(5);
			$("#pageNo").val(1);
			$('#search_from').submit();
		}else{
		}
	}
    
	function searchadvancedgroup(){
		if(checkInputContent()){
			var convalue = $(".search_text").val();
			var condition = $(".search_sel span").text();
			convalue =  encodeURI(encodeURI(convalue));
			condition =  encodeURI(encodeURI(condition));
			$('#advanced_from').attr("action", "querybygroup.htm?showflag=1&convalue="+convalue+"&condition="+condition);
			$("#advancedpageSize").val(5);
			$("#advancedpageNo").val(1);
			$('#advanced_from').submit();
		 }else{
		}
	}


   /**
	* 查看详情
	*
	* */
   function scanGroup(groupId,groupRemark,groupSecret,groupCreateTime,groupLimitMember,groupIsHot,groupName,groupHead,groupTypeName){
	   $("#groupName").text("小组名称："+groupName);
	   var year1=groupCreateTime.substring(24,29);
	   var month1=groupCreateTime.substring(4,7);
	   var month2="";
	   if(month1=="Jan"){
		   month2="01";
	   }else if(month1=="Feb"){
		   month2="02";
	   }else if(month1=="Mar"){
		   month2="03";
	   }else if(month1=="Apr"){
			month2="04";
	   }else if(month1=="May"){
		   month2="05";
	   }else if(month1=="June"){
		   month2="06";
	   }else if(month1=="July"){
		   month2="07";
	   }else if(month1=="Aug"){
		   month2="08";
	   }else if(month1=="Sept"){
		   month2="09";
	   }else if(month1=="Oct"){
		   month2="10";
	   }else if(month1=="Nov"){
		   month2="11";
	   }else if(month1=="Dec"){
		   month2="12";
	   }
	   var ri1=groupCreateTime.substring(8,10);
	   var shi1=groupCreateTime.substring(11,13);
	   var fen1=groupCreateTime.substring(14,16);
	   var miao1=groupCreateTime.substring(17,19);
	   var createTime=""+year1+"-"+month2+"-"+ri1+" "+shi1+":"+fen1+":"+miao1;
	   $("#groupCreateTime").text("创建时间："+createTime);
	   if(groupSecret=="0"){
		   $("#groupSecrets").text("类型：	公开");
	   }else{
		   $("#groupSecrets").text("类型：	私密");
	   }
	   $("#groupLimitMember").text("小组上限：	"+groupLimitMember);
	   if(groupIsHot=="0"){
		   $("#groupIsHot").text("热门：	普通");
	   }else{
		   $("#groupIsHot").text("热门：	热门");
	   }

	   $("#groupRemark").text("小组描述：	"+groupRemark);
	   $("#groupHead").attr("src",""+groupHead);
	   $("#groupTypeName").text("小组分类：	"+groupTypeName);
	   $("#scanGroup").modal("show");

   }

/**
 *
 * 查看详情
 * */
function modifiedGroup(groupId){
	$.get("querygroupvoNew.htm?groupId=" + groupId, function (data)
	{
		$("#groupName").text("小组名称："+data.groupName);
		var date=new Date(data.groupCreateTime);
		Date.prototype.format = function(format){
			var o = {
				"M+" : this.getMonth()+1, //month
				"d+" : this.getDate(), //day
				"h+" : this.getHours(), //hour
				"m+" : this.getMinutes(), //minute
				"s+" : this.getSeconds(), //second
				"q+" : Math.floor((this.getMonth()+3)/3), //quarter
				"S" : this.getMilliseconds() //millisecond
			}
			if(/(y+)/.test(format)) {
				format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
			}
			for(var k in o) {
				if (new RegExp("(" + k + ")").test(format)) {
					format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
				}
			}
				return format;
		}
		var createTime=date.format("yyyy-MM-dd hh:mm:ss");
		$("#groupCreateTime").text("创建时间："+createTime);
		if(data.groupSecret=="0"){
			$("#groupSecrets").text("类型：	公开");
		}else{
			$("#groupSecrets").text("类型：	私密");
		}
		$("#groupLimitMember").text("小组上限：	"+data.groupLimitMember);
		if(data.groupIsHot=="0"){
			$("#groupIsHot").text("热门：	普通");
		}else{
			$("#groupIsHot").text("热门：	热门");
		}

		$("#groupRemark").text("小组描述：	"+data.groupRemark);
		$("#groupHead").attr("src",""+data.groupHead);
		$("#groupTypeName").text("小组分类：	"+data.groupTypeName);
	});
	$("#scanGroup").modal('show');
}

