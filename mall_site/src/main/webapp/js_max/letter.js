$(function(){
	win();
	$(window).resize(function() {
		win();
	});
	$(".msg_det,.msg_read").hide();
	$("#dialog-tip").hide();
	//消息列表删除
	var msg_t="";
	$(".msg_list li").bind('mouseenter',function(){
		var cur = $(this);
		msg_t = setTimeout(function(){
			cur.find(".msg_det,.msg_read").show();
			},200);
	});
	$(".msg_list li").bind('mouseleave',function(){
		clearTimeout(msg_t);
		$(this).find(".msg_det,.msg_read").hide();
	});
});
var token=$("input[name='CSRFToken']").val();
var customerId=$("#custId").val();

/**
 * 已读删除
 * @param letterId
 */
function deleteLetter(relaId,letterId){
		$.ajax({
			type : "post",
			url:"deleteletter.htm?CSRFToken="+token+"&relaId="+relaId+"&letterId="+letterId,
			success:function(html){
				if(html==1){
					window.location="insideletter.html";
				}else{
					dia(2);
				}
			}
		});
}


/**
 * 删除全部
 */
function deleteAll(){
	var letterId="";
	var lettersitem=document.getElementsByName('selectletter');
	if(checkSelected('selectletter')){
		if(lettersitem.length==0){
			dia(5);
		}else{
			for (var i = 0; i < lettersitem.length; i++) {
				if($(lettersitem)[i].checked==true){
					letterId=lettersitem[i].value;
					letterIsReadDelete(letterId);
				}
			}
		}
		window.location="insideletter.html";
	}else{
		dia(5);
	}
}

//删除--判断是否已读
function letterIsReadDelete(letterId){
	var datas = "1=1";
	datas += "&letterId="+letterId;
	jQuery.ajax({
		type : "post",
		async : false,
        url : "letterisread.htm?CSRFToken="+token,
        data : datas,
		success:function(html){
			if(html==0){
				jQuery.ajax({
					type : "post",
					async : false,
			        url : "deleteletterno.htm?CSRFToken="+token+"&letterId="+letterId,
					success:function(html){
//						if(html==1){
//						}else{
//							dia(2);
//						}
						if(html!=1){
							dia(2);
						}
					}
				});
			}else{
				deleteByLCid(letterId);
			}
		}
	});
}

/**
 * 未读删除
 */
function deleteNoLetter(letterId){
	var datas = "1=1";
	datas += "&customerId="+customerId;
	datas += "&letterId="+letterId;
	jQuery.ajax({
		type : "post",
        url : "deleteletterno.htm?CSRFToken="+token,
        data : datas,
		success:function(html){
			if(html==1){
				window.location="insideletter.html";
			}else{
				dia(2);
			}
		}
	});
}

/**
 * 根据letterId,customerId删除
 */
function deleteByLCid(letterId){
	var datas = "1=1";
	datas += "&letterId="+letterId;
	jQuery.ajax({
		type : "post",
		async : false,
        url : "deletebylcid.htm?CSRFToken="+token,
        data : datas,
		success:function(html){
			if(html!=1){
				dia(2);
			}
		}
	});
}


/**
 * 全部标记为已读
 */
function readAll(){
	var letterId="";
	var lettersitem=document.getElementsByName('selectletter');
	if(checkSelected('selectletter')){
		if(lettersitem.length==0){
			dia(5);
		}else{
			for (var i = 0; i < lettersitem.length; i++) {
				if($(lettersitem)[i].checked==true){
					letterId=lettersitem[i].value;
					letterIsRead(letterId);
				}
			}
		}
		window.location="insideletter.html";
	}else{
		dia(5);
	}
}

//判断是否已读
function letterIsRead(letterId){
	var datas = "1=1";
	datas += "&letterId="+letterId;
	jQuery.ajax({
		type : "post",
		async : false,
        url : "letterisread.htm?CSRFToken="+token,
        data : datas,
		success:function(html){
			if(html==0){
				readLetter(letterId);
			}
		}
	});
}

//标记为已读
function readLetter(letterId){
	var datas = "1=1";
	datas += "&letterId="+letterId;
	$.ajax({
		type : "post",
		async : false,
        url : "readletter.htm?CSRFToken="+token,
        data : datas,
		success:function(html){
			if(html!=1){
				dia(2);
			}
		}
	});
	
}

//全选
function selectAll(obj){  
		var objs=document.getElementsByName('selectletter');
		if(obj.checked==true){
			for(var i=0;i<objs.length;i++){
				if($(objs)[i].checked==false){
					$(objs)[i].checked = true;
				}
			}
		}else{
			for(var i=0;i<objs.length;i++){
				if($(objs)[i].checked==true){
					$(objs)[i].checked = false;
					$(objs)[i].value="";
				}
			}
		}
}

function oncheck(){
	var obj=document.getElementsByName('selectletter');
	for(var i=0; i<obj.length; i++){    
		if(obj[i].checked){
			$(".check_all").prop("checked","checked");
		}else{
	    	$(".check_all").prop("checked","");
	    	return null;
		}
	}
}

//检查是否选中一行
function checkSelected(objId){
	var checkedList = new Array();
  	$("input[name='"+objId+"']:checked").each(function() {
  		checkedList.push($(this).val());
  	});
  	if(checkedList.length > 0){
  		return true;
  	}else{
  		return false;
  	}
};

function clas(){
	$(".dialog").fadeOut();
	window.location="insideletter.html";
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
};

