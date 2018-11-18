function doPrintSetup(){
    //打印设置
    document.all.WebBrowser.ExecWB(8,1)
}
function doPrintPreview(){

    //打印预览
    document.all.WebBrowser.ExecWB(7,1)
}
//装箱
function doPrint(obj) {
    if($("#printNo2").val()!="1"){
        $('#ZXout_success').modal('show');
        return;
    }
    $.post("thirdmodifyorderbyparam.htm", { orderId: obj, orderStatus: "2" ,token:"L"},
			function(data){
            window.opener.location.href="querythirdoutstock.html?orderCargoStatus=1";
            window.close();
    		//dia(5);
	});
}    

//新增装箱单
function addContainers(obj){
	location.href="thirdaddcontainer.html?orderId="+obj;
}

//更换包裹
function changeContainers(obj,goodsNums){
    $("#container_id").prop("value",obj);
    $("#goodsNums").val(goodsNums);
}

//装箱
function changeContainerRe(){
    if($(".re_id").val()!=""){
        if($("#Text2").val()==null||$("#Text2").val()==''){
            $("#errorInfo").text("请填写包裹数量!");

        }
       else if($("#goodsNums").val() >= $("#Text2").val()){
            $(".changeContainer").submit();
        }else{
            $("#errorInfo").text("请输入正整数，且不能大于此包裹中商品的数量!");
        }
    }else{
        $("#errorInfo").text("请选择包裹!");
    }
}

function delContainers(obj){
	$.post("verifycount.htm", { relationId: obj },
			   function(data){
			     if(data>0){
                     $('#deleteBox').modal('show');
			    	 return null;
			     }else{
			    	 $("#del_relation_id").prop("value",obj);
			    	 $("#del_relation").submit();
			     }
	});
	
}