$(function() {
	win();
	$(window).resize(function() {
		win();
	});
});
var reg= /^([\u4e00-\u9fa5_A-Za-z0-9 \\`\\~\\!\\@\\#\\$\\^\\&\*\(\)\=\{\}\'\:\;\'\,[\]\.\/\?\~\！\@\#\￥\…\…\&\*\（\）\;\—\|\{\}\【\】\‘\；\：\”\“\'\。\，\、\？])+$/;
var token=$("input[name='CSRFToken']").val();
/**
 * 添加教育信息
 */
function addEdu(){
		var schoolType=$("#schoolType").val();
		var schoolName=$("#schoolName").val();
		var className=$("#className").val();
		var comeTime=$("#comeyear").val();
		var customerId= $("#hi_uid").val();
		if(schoolName==null||schoolName==""){
	        $("#titleSN").text("请填写学校名称");
	        return;
	    }
		$("#titleSN").text("");
		if(className==null||className==""){
	        $("#titleClN").text("请填写班级名称");
	        return;
	    }
		if(!reg.test(schoolName)){
			$("#titleSN").text("请正确填写学校名称");
			return false;
		}
		if(!reg.test(className)){
			$("#titleClN").text("请正确填写班级名称");
			return false;
		}
		
		$("#titleClN").text("");
		var datas= "1=1";
		datas += "&schoolType=" + schoolType;
		datas += "&schoolName=" + schoolName;
		datas += "&className=" + className;
		datas += "&comeTime=" + comeTime;
		datas += "&customerId=" + customerId;
		jQuery.ajax({
	        type : "post",
	        url : "../addeducation.htm?CSRFToken="+token,
	        data : datas,
	        success : function(html) {
	        	if (html==1) {
	            	selectEdu();
	            	$('.education_edit').slideUp('fast');
	            	clearEdu();
	            } else {
	                $("#titleerr").text("添加失败，请稍后再试!");
					dia(4);
	            }
	        }
	    });
	}


/**
 * 添加职业信息
 */
function addVoca(){
	var companyName=$("#companyName").val();
	var customerId=$("#job_uid").val();
	var jobBegin=$("#jobBegin").val();
	var jobEnd= $("#jobEnd").val();
	if(companyName==null||companyName==""){
		$("#titleCN").text("请填写公司名称");
        return;
    }
	if(!reg.test(companyName)){
		$("#titleCN").text("请正确填写公司名称");
		return false;
	}
	$("#titleCN").text("");
	var count=jobBegin-jobEnd;
	if(count>0){
		$("#titleWT").text("请填写正确时间");
		return;
	}
	$("#titleWT").text("");
	var datas = "1=1";
	datas += "&companyName="+companyName;
	datas += "&workTime="+jobBegin;
	datas += "&endTime="+jobEnd;
	datas += "&customerId="+customerId;
	jQuery.ajax({
        type : "post",
        url : "../insertvocation.htm?CSRFToken="+token,
        data : datas,
        success : function(html) {
        	if (html==1) {
            	selectJob();
            	$('.job_edit').slideUp('fast');
            	clearJob();
            } else {
                $("#titleerr").text("添加失败，请稍后再试!");
				dia(4);
            }
        }
    });
}

/**
 * 循环遍历职业信息
 */
function selectJob(){
	var customerId=$("#job_uid").val();
	jQuery.ajax({
        url : "../selectall.htm?CSRFToken="+token+"&customerId="+customerId,
        success : function(html) {
        	var voc = "<tr > <th style='width: 50%;'>单位名称</th> <th style='width: 30%;'>工作时间</th> <th style='width: 20%;'>操作</th>  </tr>";
        	if (html.length!=0) {
        		$("#voc").html("");
        		for(var i = 0; i<html.length; i++){
        			var vocation= html[i];
        			voc +="<tr> <td>"+vocation.companyName+"</td><td>"+vocation.workTime+"年-" +vocation.endTime+"年"+"</td><td><a href='javascript:void(0);'  onclick='updateVoc("+vocation.vocationId+")'>修改</a><a href='javascript:void(0);' onclick='deleteVoc("+vocation.vocationId+")'>删除</a></td></tr>";
        		}
        		$("#voc").append(voc);
            } else if(html.length==0){
            	$("#voc").html(voc);
            }else {
                $("#titleerr").text("无信息，请稍后再试!");
				dia(4);
            }
        }
    });
}

/**
 * 循环遍历教育信息
 */
function selectEdu(){
	var customerId=$("#job_uid").val();
	jQuery.ajax({
		url : "../selectalledu.htm?CSRFToken="+token+"&customerId="+customerId,
        success : function(html) {
        	var voc = "<tr> <th style='width: 10%;'>学校类型</th> <th style='width: 30%;'>学校名称</th><th style='width: 20%;'>学院或班级</th><th style='width: 20%;'>入学时间</th> <th style='width: 20%;'>操作</th></tr>";
        	if (html.length!=0) {
//        		$("#edu tr").each(function(i){
//        			if(i != 0){
//        				$("#edu tr:eq("+i+")").remove();
//        			}
//        		});
        		$("#edu").html("");
        		for(var i = 0; i<html.length; i++){
        			var edu= html[i];
        			voc +="<tr> <td>"+edu.schoolType+"</td><td>"+edu.schoolName+"</td><td>"+edu.className+"</td><td>"+edu.comeTime+"</td>"+"<td><a href='javascript:void(0);' onclick='updateEdu("+edu.educationId+")'>修改</a><a href='javascript:void(0);' onclick='deleteEdu("+edu.educationId+")'>删除</a></td></tr>";
        		}
        		$("#edu").append(voc);
            } else if(html.length==0){
            	$("#edu").html(voc);
            } else {
            	$("#titleerr").text("无信息，请稍后再试!");
				dia(4);
            }
        }
    });
}

/**
 * 根据id删除职业信息
 * @param vocId 职业id
 */
function deleteVoc(vocId){
	jQuery.ajax({
		url:"../delvocation.htm?CSRFToken="+token+"&vocationId="+vocId,
		success:function(html){
			if(html==1){
				selectJob();
			}else{
				$("#titleerr").text("删除失败，请稍后再试!");
				dia(4);
			}
		}
	});
}

/**
 * 根据id删除教育信息
 * @param eduId 教育id
 */
function deleteEdu(eduId){
	jQuery.ajax({
		url:"../deleducation.htm?CSRFToken="+token+"&educationId="+eduId,
		success:function(html){
			if(html==1){
				selectEdu();
			}else{
				$("#titleerr").text("删除失败，请稍后再试!");
				dia(4);
			}
		}
	});
}


/**
 * 根据id获得指定职业信息
 */
function updateVoc(vocId){
	jQuery.ajax({
		url:"../selectvobyid.htm?CSRFToken="+token+"&vocationId="+vocId,
		success:function(html){
			if(html != null){
				$('.job_edit').slideDown('fast');
				$("input[name='companyName']").val(html.companyName);
				$("input[name='vocationId']").val(html.vocationId);
				$("select[name='workTime']").val(html.workTime);
				$("select[name='endTime']").val(html.endTime);
				$("input[name='updateVoc']").attr("onclick","updateVocSucc()");
			}else{
				$("#titleerr").text("修改失败，请稍后再试!");
				dia(4);
			}
		}
	});
}


/**
 * 根据id获得指定教育信息
 */
function updateEdu(eduId){
	jQuery.ajax({
		url:"../selectedubyid.htm?CSRFToken="+token+"&educationId="+eduId,
		success:function(html){
			if(html != null){
				$('.education_edit').slideDown('fast');
				$("select[name='schoolType']").val(html.schoolType);
				$("input[name='schoolName']").val(html.schoolName);
				$("input[name='educationId']").val(html.educationId);
				$("input[name='className']").val(html.className);
				$("select[name='comeTime']").val(html.comeTime);
				$("input[name='updateEdu']").attr("onclick","updateEduSucc()");
			}else{
				$("#titleerr").text("修改失败，请稍后再试!");
				dia(4);
			}
		}
	});
}


/**
 * 修改职业信息
 */
function updateVocSucc(){
	var companyName=$("#companyName").val();
	var customerId=$("#job_uid").val();
	var vocationId=$("#vocId").val();
	var jobBegin=$("#jobBegin").val();
	var jobEnd= $("#jobEnd").val();
	if(companyName==null||companyName==""){
        $("#titleCN").text("请填写公司名称");
        return;
    }
	if(!reg.test(companyName)){
		$("#titleCN").text("请正确填写公司名称");
		return false;
	}
	$("#titleCN").text("");
	var count=jobBegin-jobEnd;
	if(count>0){
		$("#titleWT").text("请填写正确时间");
		return;
	}
	$("#titleWT").text("");
	var datas = "1=1";
	datas += "&companyName="+companyName;
	datas += "&workTime="+jobBegin;
	datas += "&endTime="+jobEnd;
	datas += "&vocationId="+vocationId;
	datas += "&customerId="+customerId;
	
	jQuery.ajax({
		url:"../updatevocation.htm?CSRFToken="+token,
		data:datas,
		success:function(html){
			if(html==1){
				$('.job_edit').slideUp('fast');
				selectJob();
				clearJob();
			}else{
				$("#titleerr").text("修改失败，请稍后再试!");
				dia(4);
			}
		}
	});
}


/**
 * 修改教育信息
 */
function updateEduSucc(){
	var schoolType=$("#schoolType").val();
	var educationId=$("#eduId").val();
	var schoolName=$("#schoolName").val();
	var className=$("#className").val();
	var comeTime=$("#comeyear").val();
	var customerId= $("#hi_uid").val();
	if(schoolName==null||schoolName==""){
        $("#titleSN").text("请填写学校名称");
        return;
    }
	$("#titleSN").text("");
	if(className==null||className==""){
        $("#titleClN").text("请填写班级名称");
        return;
    }
	if(!reg.test(schoolName)){
		$("#titleSN").text("请正确填写学校名称");
		return false;
	}
	if(!reg.test(className)){
		$("#titleClN").text("请正确填写班级名称");
		return false;
	}
	$("#titleClN").text("");
	var datas= "1=1";
	datas += "&schoolType=" + schoolType;
	datas += "&schoolName=" + schoolName;
	datas += "&className=" + className;
	datas += "&comeTime=" + comeTime;
	datas += "&customerId=" + customerId;
	datas += "&educationId=" + educationId;
	
	jQuery.ajax({
		url:"../updateeducation.htm?CSRFToken="+token,
		data:datas,
		success:function(html){
			if(html==1){
				$('.education_edit').slideUp('fast');
				clearEdu();
				selectEdu();
			}else{
				$("#titleerr").text("修改失败，请稍后再试!");
				dia(4);
			}
		}
	});
}




/**
 * 清空教育信息
 */
function clearEdu(){
	$("#schoolType").val("大学");
	$("#schoolName").val("");
	$("#className").val("");
	$("#comeyear").val("");
	$("#titleClN").text("");
	$("#titleSN").text("");
}

/**
 * 清空职业信息
 */
function clearJob(){
	$("#companyName").val("");
	$("#jobBegin").val("");
	$("#jobEnd").val("");
	$("#titleCN").text("");
	$("#titleWT").text("");
}



/**
 * 更改添加教育信息onclick事件
 */
function updateclick(){
	$("input[name='updateEdu']").attr("onclick","addEdu()");
}

/**
 * 更改添加职业信息onclick事件
 */
function updatejobclick(){
	$("input[name='updateVoc']").attr("onclick","addVoca()");
}

function win() {
	var _wd = $(window).width();
	var _hd = $(window).height();
	$(".s_dia").css("top", (_hd - $(".dialog").height()) / 2).css("left",
			(_wd - $(".s_dia").width()) / 2);

};

function dia(n) {
	$(".mask").fadeIn();
	$(".dia" + n).fadeIn();
};

function cls() {
	$(".dialog").fadeOut();
	$(".mask").fadeOut();
    history.go(0);
};
