function applydetail(){
	var topicdel= $('input:radio[name="topicDelFlag"]:checked').val();
	var item = $(":radio:checked"); 
	var len=item.length; 
	if(len==0){
		$("#message").html("请选择处理操作！");
		dia1(1);
	}else {
		if(topicdel=='1'){
			dia1(2);
		}else{
			$.ajax({
				url:"applydetail.htm",
				dataType:"text",	
				type:'post',
				data:$("#applydetail").serialize(),
				async: false,
				error: function(request) {
					alert("Connection error");
				},
				success: function(result) {
					if(result > 0){
						cls1();
						$("#message").html("处理成功！");
						dia1(1);
					}else{
						cls1();
						$("#message").html("处理失败！");
						dia1(1);
					}
				 }
			});
		}
	}
}

function refuseapply(){
		$.ajax({
			url:"applydetail.htm",
			dataType:"text",	
			type:'post',
			data:$("#refuseapply").serialize(),
			async: false,
			error: function(request) {
				alert("Connection error");
			},
			success: function(result) {
				if(result > 0){
					cls1();
					$("#message").html("处理成功！");
					dia1(1);
				}else{
					cls1();
					$("#message").html("处理失败！");
					dia1(1);
				}
			}
		});
}