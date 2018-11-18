var addCount=1;
function addTeam(){
	var addType=$("#addtype").val();
	var addCondition=$("#addcondition").val();
	var groupid = $("#groupid").val();
	params="limitAddType="+addType+"&limitCondition="+addCondition+"&groupId="+groupid;
	if(addCount==1){
		addCount++;
	}else{
		return;
	}
	$.ajax({
     type:'post',
     url:"../addgroup.html?"+params,
     async:false,
     success:function(data){
    	 if(data == 1){
    		// $("#dig1").show();
    		/* $("#message").html("添加成功！");
				dia1(1);*/
    		 location.reload();
    	 }
    	 else if(data == -2){
    		 $("#message").html("该小组拒绝该用户加入,请联系管理员！");
				dia1(1);
    	 }
    	 else if(data == -1){
    		 $("#message").html("加入请求已经发出,请等待管理员审核！");
    		 dia1(1);
    	 }
    	 else if(data == 0){
    		 $("#message").html("此小组不允许任何人加入！");
				dia1(1);
    	 }else{
    		 window.location.href=data;
    	 }
     }
	});
  }; 
  
  function outTeam(){
	  var customerid = $("#customerid").val();
	  var groupid = $("#groupid").val();
	  var param = "customerId="+customerid+"&groupId="+groupid;
	  if(confirm('你确定要退出此小组?')){
		  $.ajax({
			  type:'post',
			  url:"../outgroup.htm?"+param,
			  success:function(data){
				  if(data>0){
				  location.reload();
				  }else{
					  $("#message").html("退出小组失败");
				         dia1(1);
				  }
			  }
		  });
	  }
  }
  
//话题排序
  function topicSearch(type,obj){
  	if(type=="0"){
  		$("#screening").val(obj);
  	}else{
  		$("#sort").val(obj);
  	}
  	document.topic_search_form.submit();
  }
 
//分页
  function page(page){
  		$("#page").val(page);
  	//	$("#page_groupId").val($("#groupId").val());
  		$("#group_page_form").submit();
  }
  
//批量删除话题
  function delBatchTopic(){
  	var checkboxs = document.getElementsByName("topicId");
  	var dlflag = false;
  	for ( var i = 0; i < checkboxs.length; i++) {
  		var e = checkboxs[i];
  		if(e.checked==true){
  			dlflag=true;
  		}
  	}
  	if(!dlflag){
  		$("#message").html("请选择话题！");
  		dia1(1);
  		return;
  	}
  	if (confirm('您确定要删除此话题吗?')){ 
	  	$('#topic_form').form('submit',{  
	  	    url: '../deltopicbatch.htm',  
	  	    onSubmit: function(){  
	  	        return $(this).form('validate');  
	  	    },  
	  	    success: function(result){
	  	        if (result>0){
	  	        	var p = $("#page").val();
	  	        	page(p);
	  	        } else {  
	  	        	$("#message").html("删除失败！");
	          		dia1(1);
	  	        }   
	  	    }  
	  	});
  	}
 }
  
//禁止回应话题
  function unreplyBatchTopic(){
	  var checkboxs = document.getElementsByName("topicId");
	  var dlflag = false;
	  for ( var i = 0; i < checkboxs.length; i++) {
		  var e = checkboxs[i];
		  if(e.checked==true){
			  dlflag=true;
		  }
	  }
	  if(!dlflag){
		  $("#message").html("请选择话题！");
		  dia1(1);
		  return;
	  }
	  if (confirm('您确定要禁止回应话题吗?')){ 
		  $('#topic_form').form('submit',{  
			  url: '../unreplytopicbatch.htm',  
			  onSubmit: function(){  
				  return $(this).form('validate');  
			  },  
			  success: function(result){
				  if (result>0){
					  var p = $("#page").val();
					  page(p);
				  } else {  
					  $("#message").html("设置失败！");
					  dia1(1);
				  }   
			  }  
		  });
	  }
  }
  
//允许回应话题
  function replyBatchTopic(){
	  var checkboxs = document.getElementsByName("topicId");
	  var dlflag = false;
	  for ( var i = 0; i < checkboxs.length; i++) {
		  var e = checkboxs[i];
		  if(e.checked==true){
			  dlflag=true;
		  }
	  }
	  if(!dlflag){
		  $("#message").html("请选择话题！");
		  dia1(1);
		  return;
	  }
	  if (confirm('您确定要回应话题吗?')){ 
		  $('#topic_form').form('submit',{  
			  url: '../replytopicbatch.htm',  
			  onSubmit: function(){  
				  return $(this).form('validate');  
			  },  
			  success: function(result){
				  if (result>0){
					  var p = $("#page").val();
					  page(p);
				  } else {  
					  $("#message").html("设置失败！");
					  dia1(1);
				  }   
			  }  
		  });
	  }
  }
  
  //申请到首页
  function recommendTopicBatch(){
		var checkboxs = document.getElementsByName("topicId");
		var dlflag = false;
		for ( var i = 0; i < checkboxs.length; i++) {
			var e = checkboxs[i];
			if(e.checked==true){
				dlflag=true;
			}
		}
		if(!dlflag){
			$("#message").html("请选择话题！");
			dia1(1);
			return;
		}
		$('#topic_form').form('submit',{  
		    url: '../recommendtopicbatch.htm',  
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

  //设精华
  function essenceTopicBatch(val){
	    var groupId = $("#groupid").val();
		var checkboxs = document.getElementsByName("topicId");
		var dlflag = false;
		for ( var i = 0; i < checkboxs.length; i++) {
			var e = checkboxs[i];
			if(e.checked==true){
				dlflag=true;
			}
		}
		if(!dlflag){
			$("#message").html("请选择话题！");
			dia1(1);
			return;
		}
		$('#essenceH').val(val);
		$('#topic_form').form('submit',{  
		    url: '../essencetopicbatch.htm',  
		    onSubmit: function(){  
		        return $(this).form('validate');  
		    },  
		    success: function(result){
		        if (result==1){
		        	window.location.href='../groupdetail/'+groupId+'.html';
		        } else {  
		        	$("#message").html("修改失败！");
	        		dia1(1);
		        }   
		    }  
		});
	}

  //去除精华
	function noEssenceTopicBatch(val){
		var groupId = $("#groupid").val();
		var checkboxs = document.getElementsByName("topicId");
		var dlflag = false;
		for ( var i = 0; i < checkboxs.length; i++) {
			var e = checkboxs[i];
			if(e.checked==true){
				dlflag=true;
			}
		}
		if(!dlflag){
			$("#message").html("请选择话题！");
			dia1(1);
			return;
		}
		$('#essenceH').val(val);
		$('#topic_form').form('submit',{  
		    url: '../essencetopicbatch.htm',  
		    onSubmit: function(){  
		        return $(this).form('validate');  
		    },  
		    success: function(result){
		        if (result==1){
		        	window.location.href='../groupdetail/'+groupId+'.html';
		        } else {  
		        	$("#message").html("修改失败！");
	        		dia1(1);
		        }   
		    }  
		});
	}


	//设热帖
	function hotTopicBatch(val){
		var groupId = $("#groupid").val();
		var checkboxs = document.getElementsByName("topicId");
		var dlflag = false;
		for ( var i = 0; i < checkboxs.length; i++) {
			var e = checkboxs[i];
			if(e.checked==true){
				dlflag=true;
			}
		}
		if(!dlflag){
			$("#message").html("请选择话题！");
			dia1(1);
			return;
		}
		$('#hotH').val(val);
		$('#topic_form').form('submit',{  
		    url: '../fevertopicbatch.htm',  
		    onSubmit: function(){  
		        return $(this).form('validate');  
		    },  
		    success: function(result){
		        if (result==1){
		        	window.location.href='../groupdetail/'+groupId+'.html';
		        } else {  
		        	$("#message").html("修改失败！");
	        		dia1(1);
		        }   
		    }  
		});
	}
	
	//去除热帖
	function noHotTopicBatch(val){
		var groupId = $("#groupid").val();
		var checkboxs = document.getElementsByName("topicId");
		var dlflag = false;
		for ( var i = 0; i < checkboxs.length; i++) {
			var e = checkboxs[i];
			if(e.checked==true){
				dlflag=true;
			}
		}
		if(!dlflag){
			$("#message").html("请选择话题！");
			dia1(1);
			return;
		}
		$('#hotH').val(val);
		$('#topic_form').form('submit',{  
		    url: '../fevertopicbatch.htm',  
		    onSubmit: function(){  
		        return $(this).form('validate');  
		    },  
		    success: function(result){
		        if (result==1){
		        	window.location.href='../groupdetail/'+groupId+'.html';
		        } else {  
		        	$("#message").html("修改失败！");
	        		dia1(1);
		        }   
		    }  
		});
	}
	
	
	//话题置顶设置
	function topTopicBatch(val){
		var groupId = $("#groupid").val();
		var checkboxs = document.getElementsByName("topicId");
		var dlflag = false;
		var flag = 0;
		for ( var i = 0; i < checkboxs.length; i++) {
			var e = checkboxs[i];
			if(e.checked==true){
				flag ++;
				dlflag=true;
			}
		}
		if(!dlflag){
			$("#message").html("请选择话题！");
			dia1(1);
			return;
		}
		
		if(val=='2'){
			if(flag >1){
				$("#message").html("此次操作数据超出限定条数！");
				dia1(1);
				return;
			}
		}
		$('#topH').val(val);
		$('#topic_form').form('submit',{  
		    url: '../toptopicbatch.htm',  
		    onSubmit: function(){  
		        return $(this).form('validate');  
		    },  
		    success: function(result){
		        if (result>0){
		         	window.location.href='../groupdetail/'+groupId+'.html';
		        } else {  
		        	$("#message").html("设置已经上限！");
	        		dia1(1);
		        }   
		    }  
		});
	}
