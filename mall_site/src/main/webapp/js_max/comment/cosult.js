$(function(){
	/*初始化加载所有的商品咨询*/
	loadCommentAsk(1,null);
	/*点击保存资讯信息*/
	$(".ask_commit_btn").click(function(){
		var params="";
		var askType=$(".ask_type");
		for(var i =0;i<askType.length;i++){
			if($(askType[i]).attr("checked")){
				params+="type="+$(askType[i]).val();
			}
		}
		if($(".askComment").val().indexOf("一般能找到答案")==-1){
			params+="&askComment="+$(".askComment").val();
		}else{
			/*初始化弹框样式*/
			$(".info_tip_content2").html("请输入咨询内容！");
			$(".info_tip_img2").attr("src","../images/error.png");
			$(".info_tip_cancel2").attr("href","javascript:;").hide();
			$(".info_tip_submit2").attr("href","javascript:;").html("确定").show().unbind("click");
			$(".info_tip_submit2").click(function(){
				cls();
			});
			dia(2);
			return;
		}
		params+="&productId="+$("#productId").val();
		
		var url = "../goods/saveCommentAsk.html?"+params;
		$.ajax({
			type: 'post',
			url:url,
			async:false,
			success: function(data) {
				if(data==1){
					/*初始化弹框样式*/
					$(".info_tip_content2").html("发布成功,请等待客服回复！");
					$(".info_tip_img2").attr("src","../images/error.png");
					$(".info_tip_cancel2").attr("href","javascript:;").hide();
					$(".info_tip_submit2").attr("href","javascript:;").html("确定").show().unbind("click");
					$(".info_tip_submit2").click(function(){
						location.reload();
					});
					dia(2);
				}else if(data==0){
					/*初始化弹框样式*/
					$(".info_tip_content2").html("发布失败！");
					$(".info_tip_img2").attr("src","../images/error.png");
					$(".info_tip_cancel2").attr("href","javascript:;").hide();
					$(".info_tip_submit2").attr("href","javascript:;").html("确定").show().unbind("click");
					$(".info_tip_submit2").click(function(){
						cls();
					});
					dia(2);
				}else{
					/*初始化弹框样式*/
					$(".info_tip_content2").html("是否跳转到登陆页?");
					$(".info_tip_img2").attr("src","../images/index_ques.png");
					$(".info_tip_cancel2").attr("href","javascript:;").html("取消").show();
					$(".info_tip_submit2").attr("href","../login.html?cosult/"+$("#productId").val()).show().html("确定").unbind("click");
					$(".info_tip_cancel2").click(function(){
						cls();
					});
					dia(2);
				}
			}
		});
	});
});