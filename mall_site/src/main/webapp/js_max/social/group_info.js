
/**
 * 提交修改后的小组表单
 */
 function updateGroup(){
	 var flag = true;
		//验证小组名称
		if($("#groupName").val()==""){
			$("#namelimit").html( "小组名称不能为空" );
			$("#namelength").show();
			$("#groupName").focus();
			flag = false && flag;
		}else{
			flag =  nameLengthFlag && flag;
		}
		//验证小组简介
		if($("#groupRemark").val()==""){
			$("#remarklimit").html( "小组简介不能为空" );
			$("#num").hide();
			$("#groupRemark").focus();
			flag = false && flag;
		}
		
		if(flag){
			$.ajax({
				type:'post',
				url:'updategroupinfo.htm',
				data:$('#updategroup').serialize(),
				dataType:'text',
				async:false,
				error:function(request){
					alert("Connection error");
				},
				success:function(result){
					if(result == 1){
						$("#message").html("修改成功！");
		        		dia1(1);
					}else{
						$("#message").html("修改失败！");
		        		dia1(1);
					}
				}
			});
		}
 }
 
	function changeGroupHead(){
//		var baseUrl= $("#baseUrl").val();
		 $('#group_form').form('submit',{  
	         url: "updategrouphead.htm",  
	         success: function(result){    
	        	$("#groupHead").attr("src",result); 
	        	$("#groupPic").val(result);
	        	$("#groupHead").show();
	         }   
	     });    

	} 
 
 //修改小组背景
 function updatebg(){
		$('#group_form').form('submit',{  
		    url: 'editgroupbg.htm',  
		    onSubmit: function(){  
		        return $(this).form('validate');  
		    },  
		    success: function(result){
		        if (result==1){
		        	$("#groupBackgroundImg").attr("src","");
		        	$("#message").html("修改成功！");
	        		dia1(1);
		        } else {   
		        	$("#message").html("修改失败！");
	        		dia1(1);
		        }   
		    }  
		});
	}
	function update(){
		$('#group_form').form('submit',{  
		    url: 'updategroupinfo.htm',  
		    onSubmit: function(){  
		        return $(this).form('validate');  
		    },  
		    success: function(result){
		        if (result==1){
		        	$("#message").html("修改成功！");
	        		dia1(1);
	        		 
		        } else {  
		        	$("#message").html("修改失败！");
	        		dia1(1);
		        }   
		    }  
		});
	}
	function changeBackground(){
//		var baseUrl= $("#baseUrl").val();
		 $('#group_form').form('submit',{  
	         url: "uploadbackground.htm",  
	         success: function(result){    
	        	$("#groupBackgroundImg").attr("src",result); 
	        	$("#groupBackGroundPath").val(result);
	        	$("#groupBackgroundImg").show();
	         }   
	     });    

	} 
	

 /**
  * 设置访问权限
  */
 function updateAccess(){
	 $.ajax({
			type:'post',
			url:'updateaccsee.htm',
			data:$('#accesslimit').serialize(),
			dataType:'text',
			async:false,
			error:function(request){
				alert("Connection error");
			},
			success:function(result){
				if(result == 1){
					$("#message").html("修改成功！");
	        		dia1(1);
				}else{
					$("#message").html("修改失败！");
	        		dia1(1);
				}
			}
		});
 }

 function groupsecret(){
	 if($("#groupSecret").prop("checked") == true){
		 $("#limitAddType3").prop("checked","checked");
		 $("input[name='limitAddType']").attr('disabled','disabled');
	 }else{
		 $("#limitAddType1").prop("checked","checked");
		 $("input[name='limitAddType']").removeAttr("disabled");
		 
	 }
 } 
 
/**
 * 设置删除权限
*/
function updateDelete(){
	 $.ajax({
			type:'post',
			url:'updateaccsee.htm',
			data:$('#deletelimit').serialize(),
			dataType:'text',
			async:false,
			error:function(request){
				alert("Connection error");
			},
			success:function(result){
				if(result == 1){
					$("#message").html("修改成功！");
	        		dia1(1);
				}else{
					$("#message").html("修改失败！");
	        		dia1(1);
				}
			}
		});
	 }
	 
//添加管理员页面
function addManagement(){
	var groupId = $("#groupId").val(); 
	window.location = "groupaddmanager.html?groupId="+groupId;
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

//升级为管理员
function becomemanager(){
	if(checkSelected('customerId',0)){
		$('#updateForm').form('submit',{  
			url: 'addmanagermsg.htm',  
			onSubmit: function(){  
				return $(this).form('validate');  
			},  
			success: function(result){
				if (result==1){
					$("#message").html("操作成功！");
					dia1(1);
				}else if(result==2){
					$("#message").html("小组最多只能设置4个管理员");
					dia1(1);
				} else {  
					$("#message").html("操作失败！");
					dia1(1);
				}   
			}  
		});
	}else{
		alert("请先选择要升级的用户");
	}
}

//除去管理员
function removeManagement(){
	//var groupId = $("#groupId").val(); 
	if(checkSelected('customerId',1)){
		$('#updateForm').form('submit',{  
			url: 'removemanagerbatch.htm',  
			onSubmit: function(){  
				return $(this).form('validate');  
			},  
			success: function(result){
				if (result>0){
					$("#message").html("修改成功！");
					dia1(1);
				} else {  
					$("#message").html("修改失败！");
					dia1(1);
				}   
			}  
		});
	}else{
		alert("请先选择管理员！");
	}
}

//转让小组
function transferGroup(){
	if(checkSelected('customerId',0)){
		$('#updateForm').form('submit',{ 
			url: 'transfergroupmsg.htm',  
			onSubmit: function(){  
				return $(this).form('validate');  
			},  
			success: function(result){
				if (result>0){
					$("#message").html("操作成功");
					dia1(1);
					//window.location = "groupdetail-${group.groupId }.html";
				} else {  
					$("#message").html("操作失败！");
					dia1(1);
				}   
			}  
		});
	}else{
		alert("请选择一位你要转让的管理员");
	}
}
/**
 * 返回
 */
function returnback(){
	var groupId= $("#groupId").val();
	window.location.href="managerlimit.html?groupId="+groupId;
}

/**
 * 设置解散小组权限
*/
function dissolvedGroup(){
	var groupId= $("#groupId").val();
	 $.ajax({
		 type:'post',
		 url:'dissolvegroupbyid.htm?groupId='+groupId,
		 async:false,
		 error:function(request){
			 alert("Connection error");
		},
		 success:function(result){
			 if(result == 1){
				$("#message").html("修改成功！");
        		dia1(1);
			  window.location="group.html";
			 }else{
				 $("#message").html("修改失败！");
	        	dia1(1);
			 }
			}
		});
	 }

 var checkedList = new Array();
 function checkChecked(objId){
		 $("input[name='"+objId+"']:checked").each(function(){
			 checkedList.push($(this).val());
		 });
		 if(checkedList.length > 0){
			 return true;
		 }else{
			 return false;
		 }
	 }

 /**
  * 踢出小组成员
  */
 function delCustomer(){
	var groupId = $("#groupId").val();
		if(checkChecked('customerId')){
			$.ajax({
				type:'post',
				url:'delgroupgustomer.htm',
				data:{customerIds:checkedList,groupId:groupId},
				async:false,
				 error:function(request){
					 alert("Connection error");
				 },
				 success:function(result){
					 if(result > 0){
						 $("#message").html("操作成功！");
			        	  dia1(1);
						 //location.reload();
					 }else{
						 $("#message").html("操作失败！");
			        	dia1(1);
					 }
				 }
			});
		}else{
			alert("请先选择一个小组成员");
		}
	 }

 /**
  * 加入黑名单
  */
 function blackMember(){
	 var groupId = $("#groupId").val();
		if(checkChecked('customerId')){
			$.ajax({
				type:'post',
				url:'blackmember.htm',
				data:{customerIds:checkedList,groupId:groupId},
				async:false,
				 error:function(request){
					 alert("Connection error");
				 },
				 success:function(result){
					 if(result > 0){
						 $("#message").html("修改成功！");
				    	 dia1(1);
						// location.reload();
					 }else{
						 $("#message").html("修改失败！");
				    	dia1(1);
					 }
				 }
			});
		}else{
			alert("请先选择一个小组成员");
		}
 }
 
 /**
  * 解除黑名单
  */
 function dissolvedBlack(){
	 var groupId = $("#groupId").val();
	 if(checkChecked('customerId')){
		 $.ajax({
			 type:'post',
			 url:'dissolvedblack.htm',
			 data:{customerIds:checkedList,groupId:groupId},
			 async:false,
			 error:function(request){
				 alert("Connection error");
			 },
			 success:function(result){
				 if(result > 0){
					 $("#message").html("修改成功！");
			    	 dia1(1);
					// location.reload();
				 }else{
					 $("#message").html("修改失败！");
			    	 dia1(1);
				 }
			 }
		 });
	 }else{
		 alert("请先选择一个小组成员");
	 }
 }
 
 //允许回应
 function response(){
	 if(checkChecked('topicId')){
		  $('#topic_form').form('submit',{  
			  url: 'replytopicbatch.htm',  
			  onSubmit: function(){  
				  return $(this).form('validate');  
			  },  
			  success: function(result){
				  if (result>0){
					  $("#message").html("修改成功！");
					  dia1(1);
					 // location.reload();
				  } else {  
					  $("#message").html("修改失败！");
					  dia1(1);
				  }   
			  }  
		  });
	 }else{
		 alert("请先选择一个话题");
	 }
 }
 
 //还原话题
 function restore(){
	 if(checkChecked('topicId')){
		  $('#topic_form').form('submit',{  
			  url: 'restoretopic.htm',  
			  onSubmit: function(){  
				  return $(this).form('validate');  
			  },  
			  success: function(result){
				  if (result>0){
					  $("#message").html("修改成功！");
					  dia1(1);
					 // location.reload();
				  } else {  
					  $("#message").html("修改失败！");
					  dia1(1);
				  }   
			  }  
		  });
	 }else{
		 alert("请先选择一个话题");
	 } 
 }
 
 //话题删除（彻底删除）
 function del(){
	 if(checkChecked('topicId')){
		  $('#topic_form').form('submit',{  
			  url: 'deletetopic.htm',  
			  onSubmit: function(){  
				  return $(this).form('validate');  
			  },  
			  success: function(result){
				  if (result>0){
					  $("#message").html("修改成功！");
					  dia1(1);
					 // location.reload();
				  } else {  
					  $("#message").html("修改失败！");
					  dia1(1);
				  }   
			  }  
		  });
	 }else{
		 alert("请先选择一个话题");
	 }
 }
 
