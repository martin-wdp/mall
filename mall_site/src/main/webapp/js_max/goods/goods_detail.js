$(document).ready(function(){
	/*加载商品咨询*/

	$(".loadAskComment").click(function(){
		loadCommentAsk(1,null);
	});
	/*加载评论信息*/
	$(".loadCommentValue").click(function(){
		loadComment(1,3);
	});
	
	/*绑定事件*/
	$(".commentTab").click(function(){
		var type = $(this).attr("data-role");
		$(".commentTab").removeClass("cur");
		$(this).addClass("cur");
		loadComment(1,type);
	});
	
	/*鼠标悬浮在图片上的时候*/
	$(".productImage").hover(function(){
		$(".showImage").attr("href",$(this).attr("data-artrole"));
		$(".readImg").attr("src",$(this).attr("data-bigrole"));
	});
	$(".big_img").mouseover(function(){
		var mh = $(".showImage").prop('href');
		$("#cloud-zoom-big").css("background-image","url("+mh+")");
	});
	
	/*点击保存资讯信息*/
	$(".ask_commit_btn").click(function(){
		var params="";
		var askType=$(".ask_type");
		for(var i =0;i<askType.length;i++){
			if($(askType[i]).attr("checked")){
				params+="type="+$(askType[i]).val();
			}
		}
		if($(".askComment").val()!=""){
			params+="&askComment="+$(".askComment").val();
		}
		params+="&productId="+$("#productId").val();
		$.post("../goods/saveCommentAsk.html?"+params,function(data){
			if(data==1){
				location.reload();
			}else if(data==0){
				alert("发布失败!");
			}else{
				alert("请登录!");
				location.href="../login.html";
			}
		});
	});
	/*判断是不是锚链接*/
	if(window.location.href.indexOf("comment")!=-1){
		$(".loadCommentValue").click();
	}
	/*点击已有几人评价*/
	$(".haveComment").click(function(){
		$(".loadCommentValue").click();
	});
});
/*加载商品咨询*/
function loadCommentAsk(pageNo,type){
	//初始化咨询列表
	$(".ask_list").html("");
	//初始化咨询分页
	$(".ask_paging_area").html("");
	var title=$(".common_text").val();
	var productId=$("#productId").val();
	var params="productId="+productId;
	params+="&pageNo="+pageNo;
	params+="&title="+title;
	if(type!=null){
		params+="&type="+type;
	}
	/*AJAX查询商品评论*/
	$.get("../goods/queryProducCommentForDetail.html?"+params,function(data){
		if(data!=null && data.list.length>0){
			var askHtml="";
			for(var i=0;i<data.list.length;i++){
				if(data.list[i].isDisplay==1){
					var ask=data.list[i];
					askHtml+="<div class='ask_item'>" +
							"<dl class='light'>" +
							"<dt>咨询用户：</dt>" +
							"<dd>"+ask.customerNickname+"（注册会员）</dd>" +
							"</dl>" +
							"<dl class='askCommentvalue askCommentvalue_"+ask.commentId+"' role_id="+ask.commentId+" >" +
							"<dt>咨询内容：</dt>" +
							"<dd>"+ask.commentContent+"</dd>" +
							"</dl><span class='date_time'>"+timeStamp2String(ask.publishTime)+"</span>"+
							"</div>";
				}
			}
			/*将数据显示到页面上*/
			$(".ask_list").html(askHtml);
			/*加载分页部分*/
			var paging="";
			var pageNo=0;
			if((data.pageNo-2)>0){
				pageNo=(data.pageNo-2);
			}else{
				pageNo=data.firstPageNo;
			}
			var endNo=0;
			
			if((data.lastPageNo-1)>0){
				endNo=(data.lastPageNo-2);
			}else{
				endNo=1;
			}
			if(data.pageNo==1){
				paging+="<span class='prev_null'>&lt;&nbsp;上一页</span>";
			}else{
				paging+="<a class='prev' ";
				if(data.nextPageNo>0){
					paging+="onClick='loadCommentAsk("+data.prePageNo+",null);'" ;
				}
				paging+=" href='javascript:;'>&lt;&nbsp;上一页</a>";
				if(data.pageNo>=4){
					paging+="<a class='num' href='javascript:void(0)' onClick='loadCommentAsk("+data.prePageNo+",null);'>"+data.firstPageNo+"</a>";
				}
			}
			if((data.pageNo-3)>1){
				paging+="<span class='ellipsis'>...</span>";
			}
			/*定义循环的标记*/
			var no_index=0;
			for(var i=pageNo;i<=data.endNo;i++){
				if(no_index<=4){
					if(i==data.pageNo){
						paging+="<a class='num_cur prev' href='javascript:void(0);'>"+i+"</a>";
					}else{
						paging+="<a class='num' href='javascript:void(0);' onClick='loadCommentAsk("+i+",null);'>"+i+"</a>";
					}
				}
				/*循环一次,标记加1*/
				no_index++;
			}
			if(data.pageNo!=data.lastPageNo){
				if((data.lastPageNo-data.pageNo)>3){
					if(data.lastPageNo>5){
						paging+="<span class='ellipsis'>...</span>";
					}
				}
			}
			if((data.pageNo==data.lastPageNo || data.endNo<=1)){
				if(data.lastPageNo>data.pageNo){
					paging+="<a class='num_cur' href='javascript:void(0);'>"+data.lastPageNo+"</a>";
				}
				paging+="<a class='next_null' href='javascript:void(0);'>下一页&nbsp;&gt;</a>";
			}else{
				if((data.lastPageNo-data.pageNo)>=3){
					if(data.lastPageNo>5){
						paging+="<a class='num' href='javascript:void(0);' onClick='loadCommentAsk("+data.lastPageNo+",null);' >"+data.lastPageNo+"</a>";
					}
				}
				paging+="<a class='' href='javascript:void(0);'";
				if(data.nextPageNo>0){
					paging+=" onClick='loadCommentAsk("+data.nextPageNo+",null);'";
				}
				paging+=" >下一页&nbsp;&gt;</a>";
			}
			$(".ask_paging_area").html(paging);
			/*加载分页部分 END*/
			loadCommentReplay();
			
		}else{
			$(".ask_list").html("<center>暂无咨询信息!</center>");
		}
		
		
	});
}

/*加载咨询回复*/
function loadCommentReplay(){
	var askComment=$(".askCommentvalue");
	if(askComment.length>0){
		for(var i =0;i<askComment.length;i++){
			var postUrl='../goods/queryCommentReplay.html?commentId='+$(askComment[i]).attr("role_id");
			$.ajax({
				type: 'post',
				url:postUrl,
				async:false,
				success: function(data2) {
					if(data2!=null && data2.length>0){
						for(var j =0;j<data2.length;j++){
							var askHtml22="";
							askHtml22+="<dl class='orange mt10'>" +
							"<dt>商城回复：</dt>" +
							"<dd>"+data2[j].commentContent+"</dd>" +
							"</dl>";
							$(".askCommentvalue_"+$(askComment[i]).attr("role_id")).after(askHtml22);
						}
					}
				}
			});
		}
		
	}
}


/*加载商品评论*/
function loadComment(pageNo,type){
	/*清空相关的div*/
	$(".evalue_list").html("");
	$(".evalue_paging_area").html("");
	var productId=$("#productId").val();
	var params="&productId="+productId;
	params+="&pageNo="+pageNo;
	var totalCount=0;
	var haoCount=0;
	var zhongCount=0;
	var chaCount=0;
	/*AJAX查询商品评论总行数*/
	$.get("../goods/queryProducCommentForDetail.html?type=3"+params,function(data){
		/*设置所有的行数*/
		totalCount=data.rows;
		if(type==3){
			putPageComment(type,data);
		}
		$(".commentTotalCount").text(totalCount);
		$(".allCount").text(totalCount);
		/*计算所占的百分比*/
		calcCommentPercent(totalCount,haoCount,zhongCount,chaCount);
	});
	/*AJAX查询商品好评*/
	$.get("../goods/queryProducCommentForDetail.html?type=0"+params,function(data){
		/*设置所有的行数*/
		haoCount=data.rows;
		if(type==0){
			putPageComment(type,data);
		}
		$(".haoCount").text(haoCount);
		/*计算所占的百分比*/
		calcCommentPercent(totalCount,haoCount,zhongCount,chaCount);
	});
	/*AJAX查询商品中评*/
	$.get("../goods/queryProducCommentForDetail.html?type=1"+params,function(data){
		/*设置所有的行数*/
		zhongCount=data.rows;
		if(type==1){
			putPageComment(type,data);
		}
		$(".zhongCount").text(zhongCount);
		/*计算所占的百分比*/
		calcCommentPercent(totalCount,haoCount,zhongCount,chaCount);
	});
	/*AJAX查询商品差评*/
	$.get("../goods/queryProducCommentForDetail.html?type=2"+params,function(data){
		/*设置所有的行数*/
		chaCount=data.rows;
		if(type==2){
			putPageComment(type,data);
		}
		$(".chaCount").text(chaCount);
		/*计算所占的百分比*/
		calcCommentPercent(totalCount,haoCount,zhongCount,chaCount);
	});
	
}
/*将查询到的评论加载到页面中*/
function putPageComment(type,data){
	var commentHtml="";
	if(data.list!=null && data.list.length>0){
		for(var i=0;i<data.list.length;i++){
			var comment=data.list[i];
			commentHtml+="<div class='evalue_item'>" +
					"<div class='evalue_main fr'>" +
					"<div class='conner'></div>" +
					"<div class='evalue_topic'>" +
					"<span class='time fr'>"+timeStamp2String(comment.publishTime)+"</span>" +
					 "<span class='star star_"+comment.commentScore+" fl'></span>"+
					"</div>" +
					"<dl class='commentvalue commentvalue_"+comment.commentId+"' role_id="+comment.commentId+"><dt>用户评价："+comment.commentContent+"</dt></dl></div>"+
					"<div class='evalue_member fl mt20'>" +
					"<p class='name'><font color='#005AA0'>"+comment.customerNickname+"</font></p>" +
					"<p class='lv'>注册会员</p>" +
					"</div>" +
					"<div class='cb'></div>" +
					"</div>";
		}

		/*加载分页部分*/
		var paging="";
		var pageNo=0;
		if((data.pageNo-2)>0){
			pageNo=(data.pageNo-2);
		}else{
			pageNo=data.firstPageNo;
		}
		var endNo=0;
		
		if((data.lastPageNo-1)>0){
			endNo=(data.lastPageNo-2);
		}else{
			endNo=1;
		}
		if(data.pageNo==1){
			paging+="<span class='prev_null'>&lt;&nbsp;上一页</span>";
		}else{
			paging+="<a class='prev' ";
			if(data.nextPageNo>0){
				paging+="onClick='loadComment("+data.prePageNo+","+type+");'" ;
			}
			paging+=" href='javascript:;'>&lt;&nbsp;上一页</a>";
			if(data.pageNo>=4){
				paging+="<a class='num' href='javascript:void(0)' onClick='loadComment("+data.prePageNo+","+type+");'>"+data.firstPageNo+"</a>";
			}
		}
		if((data.pageNo-3)>1){
			paging+="<span class='ellipsis'>...</span>";
		}
		/*定义循环的标记*/
		var no_index=0;
		for(var i=pageNo;i<=data.endNo;i++){
			if(no_index<=4){
				if(i==data.pageNo){
					paging+="<a class='num_cur prev' href='javascript:void(0);'>"+i+"</a>";
				}else{
					paging+="<a class='num' href='javascript:void(0);' onClick='loadComment("+i+","+type+");'>"+i+"</a>";
				}
			}
			/*循环一次,标记加1*/
			no_index++;
		}
		if(data.pageNo!=data.lastPageNo){
			if((data.lastPageNo-data.pageNo)>3){
				if(data.lastPageNo){
					paging+="<span class='ellipsis'>...</span>";
				}
			}
		}
		if((data.pageNo==data.lastPageNo || data.endNo<=1)){
			if(data.lastPageNo>data.pageNo){
				paging+="<a class='num_cur' href='javascript:void(0);'>"+data.lastPageNo+"</a>";
			}
			paging+="<a class='next_null' href='javascript:void(0);'>下一页&nbsp;&gt;</a>";
		}else{
			if((data.lastPageNo-data.pageNo)>=3){
				if(data.lastPageNo>5){
					paging+="<a class='num' href='javascript:void(0);' onClick='loadComment("+data.lastPageNo+","+type+");' >"+data.lastPageNo+"</a>";
				}
			}
			paging+="<a class='' href='javascript:void(0);'";
			if(data.nextPageNo>0){
				paging+=" onClick='loadComment("+data.nextPageNo+","+type+");'";
			}
			paging+=" >下一页&nbsp;&gt;</a>";
		}
		$(".evalue_paging_area").html(paging);
		/*加载分页部分 END*/
	}else{
		commentHtml+="<center>暂无评论数据!</center>";
	}
	$(".evalue_list").html(commentHtml);

	/*加载评论回复*/
		loadCommentValReplay();
}



/*加载评论回复*/
function loadCommentValReplay(){
	var askComment=$(".commentvalue");
	if(askComment.length>0){
		for(var i =0;i<askComment.length;i++){
			var postUrl='../goods/queryCommentReplay.html?commentId='+$(askComment[i]).attr("role_id");
			$.ajax({
				type: 'post',
				url:postUrl,
				async:false,
				success: function(data2) {
					if(data2!=null && data2.length>0){
						for(var j =0;j<data2.length;j++){
							var askHtml="";
							askHtml+="<dl class='orange mt10'>" +
								"<dt>商城回复："+data2[j].commentContent+"</dt>" +
							"</dl>";
							$(".commentvalue_"+$(askComment[i]).attr("role_id")).after(askHtml);
						}
					}
				}
			});
		}
		
	}
}

/*计算商品的评论所占百分比*/
function calcCommentPercent(totalCount,haoCount,zhongCount,chaCount){
	var haoPercent=((haoCount/totalCount)*100).toFixed(0);
	haoPercent=isNaN(haoPercent)?0:haoPercent;
	var zhongPercent=((zhongCount/totalCount)*100).toFixed(0);
	zhongPercent=isNaN(zhongPercent)?0:zhongPercent;
	var chaPercent=((chaCount/totalCount)*100).toFixed(0);
	chaPercent=isNaN(chaPercent)?0:chaPercent;
	$(".haoPercent").text(haoPercent+"%");
	$(".haoPercentLine").width(haoPercent+"%");
	$(".bigHaoPercent").html(haoPercent+"<em>%</em>");
	$(".zhongPercent").text(zhongPercent+"%");
	$(".zhongPercentLine").width(zhongPercent+"%");
	$(".chaPercent").text(chaPercent+"%");
	$(".chaPercentLine").width(chaPercent+"%");
}

/*处理时间格式化*/
function timeStamp2String(time){
		var date=new Date(parseFloat(time));
	    var datetime = new Date();
	    datetime.setTime(date);
	    var year = datetime.getFullYear();
	    var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
	    var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
	    var hour = datetime.getHours()< 10 ? "0" + datetime.getHours() : datetime.getHours();
	    var minute = datetime.getMinutes()< 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
	    var second = datetime.getSeconds()< 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
	    return year + "-" + month + "-" + date+" "+hour+":"+minute+":"+second;
}
