var agreementFlag = true;
var addCount =1;
$(document).ready(function(){
    var customerId = $('#customerid').val();
    if(customerId){
   	 $('#my_group').show();
   	 $('#mymanager').attr('href','myjoinedgroup.html?groupCreateAuthorId='+customerId);
   	 $('#nologin').hide();
   	 $('#login').show();  
   	}else{
   		$('nologin').show();
   		$('login').hide();
   		$('#my_group').hide();
   	}
   });

//验证是否阅读小组指导原则
/*function showAgree(){
	if($("#agreement").prop("checked") == true){
		$("#unagreement").show();
		agreementFlag = false;
	}else{
		$("#unagreement").hide();
		agreementFlag = true;
	}
}*/


//中文替换成英文
function changeChineseChar(str)
{
	 var r = "%uFF0C";
	 if((escape(str)).match(r))
	 {
	
	  str = unescape(escape(str).replace(/%uFF0C/g,"%2C"));
	 }
	 return str;
}
//验证小组标签
var labelFlag = true;
function checkLabel(){
	var arr = new Array();
	var label = $("#groupLabel").val();
	label = changeChineseChar(label);
	//正则表达式验证(数字 字母 汉子长度不超过10位)
	var reg = /^[\da-zA-Z\u4E00-\u9FA5]{1,10}$/;
	//ReplaceDot(label);
	if(label !=''){
		arr=label.split(',');
		if(arr.length>5){
			alert("最多5个字符,以','分隔!");
			labelFlag=false;
		}else{
			labelFlag=true;
		}
		for(var i=0;i<arr.length;i++){
			if(!reg.test(arr[i])){
				alert("标签由数字、字母、汉子组成限制10个字符");
				labelFlag=false;
			}
		}
	}
}

function creategroup(){
	/*var customerId = $("#customerid").val();
	if(customerId ==null|| customerId==''){
		$("#message").html('您未登录,请<a href="login.html" style="color:blue;">登录</a>后操作！');
		dia(1);
		return ;
	}else{
		window.location.href = "togroupcreate.html";
	}*/
	window.open("togroupcreate.html",'','');
  }   
//验证文本框的字符长度
var nameLengthFlag = true;
function textLimt(thisArea,maxLength){
	if(thisArea.type == "text"){
		if(thisArea.value.length > maxLength){
			$("#namelength").show();
			$("#namelimit").html( "长度不能超过 15 字!" );
			nameLengthFlag =  false;
		}else{
			$("#namelength").hide();
			nameLengthFlag = true;
		}
	}
	else if(thisArea.type =="textarea"){
		$("#remarklimit").html("简介不得超过500个字，你还可以输入");
		$("#num").show();
		$("#num").html(maxLength - thisArea.value.length);
		return true;
	}
}


//验证特殊字符，将调试显示到页面中
function checkSpecSymb(inputobj,Tipobj){
	 var regexp=new RegExp("[`~!@#$^&*()={}':;',\\[\\]<>/?~！@#￥……&*（）{}【】‘；：”“'。，、？]");
	 if (regexp.test( $("#"+inputobj).val() ) ) {
		// $("#"+inputobj).addClass( "ui-state-error" );
         //updateTips( "输入的内容包含特殊字符!", $("#"+Tipobj));
		 $("#"+Tipobj).html("输入的内容包含特殊字符!");
         $("#"+inputobj).focus();
         return false;
     }
     else {
    	 /*$("#"+Tipobj).text("").removeClass( "ui-state-highlight");
    	 $("#"+inputobj).removeClass( "ui-state-error" );*/
         return true;
     }
}

 function changeHead(){
	 if($("#upload").val()==""){
			$("#groupHead").show();
			$("#headgroup").hide();
			flag = false && flag;
		}else{
			$("#headgroup").show();
			$("#groupHead").hide();
		}
 }
//异步提交表单
	function create(flag) {
		var flag = true;
		//验证小组名称
		if($("#groupName").val()==""){
			$("#namelimit").html( "小组名称不能为空!" );
			$("#namelength").show();
			$("#groupName").focus();
			flag = false && flag;
		}else{
			flag =  nameLengthFlag && flag;
		}
		//验证小组头像
		if($("#upload").val()==""){
			$("#groupHead").show();
			$("#headgroup").hide();
			flag = false && flag;
		}else{
			$("#headgroup").show();
			$("#groupHead").hide();
		}
		//验证小组简介
		if($("#groupRemark").val()==""){
			$("#remarklimit").html( "小组简介不能为空" );
			$("#num").hide();
			if($("#groupName").val()!=""){
				$("#groupRemark").focus();
			}
			flag = false && flag;
		}
		if(flag !=1){
			checkLabel();
		}
		if(flag && agreementFlag && labelFlag){
		/*	$.ajax({
				url:"savegroup.html",
				dataType:"text",	
				type:'post',
				data:$("#creategroup").serialize(),
				async: false,
		        error: function(request) {
		            alert("Connection error");
		        },
		        success: function(result) {
		        	if(result > 0){
		        		$("#remarklength").hide();
		        		$("#successDialog").show();
		        	}
			     }
			});*/
			$('#creategroup').form('submit',{ 
				type:'post',
			    url: 'savegroup.html',  
			    async: false,
			    onSubmit: function(){  
			        return $(this).form('validate');  
			    },  
			    success: function(result){
			    	if(result > 0){
		        		$("#remarklength").hide();
		        		$("#successDialog").show();
		        	}
			    	if(result<0){
			    		alert("头像大小超过2M");
			    	}
			    }  
			});
		}
	};
	
	//加入小组
	function addGroup(tid,gid){
		if(addCount==1){
			addCount++;
		}else{
			return;
		}
		//var groupName = $("#groupName").val();
		params="limitAddType="+tid+"&groupId="+gid;
		$.ajax({
			type:'post',
		     url:"addgroup.html?"+params,
		     async:false,
		     success:function(data){
		    	 if(data == 1){
		    		// $("#dig1").show();
		    		 /*$("#message").html("添加成功！");
						dia(1);*/
				/*	if(groupName !=null && groupName !=''){
						window.location.href ="groupSearchList.html?groupName="+groupName;
					}else{
						window.location.href ="groupSearchList.html";
					}*/
		    		 var pageNo = $("#page").val();
		    		 page(pageNo);
		    	 }
		    	 else if(data == -1){
		    		 $("#message").html("加入请求已经发出,请等待管理员审核！");
						dia1(1);
		    	 }
		    	 else if(data == -2){
		    		 $("#message").html("该小组拒绝该用户加入,请联系管理员！");
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
		}
	
	//退出小组
	 function outGroup(cid,gid){
		 //var groupName = $("#groupName").val();
		  var param = "customerId="+cid+"&groupId="+gid;
		  if(confirm('你确定要退出此小组?')){
			  $.ajax({
				  url:"outgroup.htm?"+param,
				  success:function(data){
					 if(data>0){
						/* if(groupName !=null && groupName !=''){
							 window.location.href ="groupSearchList.html?groupName="+groupName;
						 }else{
							 window.location.href ="groupSearchList.html";
						 }*/
						 var pageNo = $("#page").val();
			    		 page(pageNo);
					 }else{
						 $("#message").html("退出小组失败");
				         dia1(1);
					 }
				  }
			  });
		  }
	  }
	 
	 //分页
	 function page(page){
			$("#page").val(page);
			$("#page_groupCategoryId").val($("#groupTypeId").val());
			$("#group_page_form").submit();
	}
	 
	 //小组列表排序
	 function groupSort(sort){
		 $("#sort").val(sort);
		 document.group_sort_form.submit();
	 }
	 
	
	
	