
//加载事件
$(function(){
	if($(".tabStatus").val()==0||$(".tabStatus").val()==""){
		$(".tb_backOrderSH").addClass("cur");
	}else if($(".tabStatus").val()==1){
		$(".tb_backOrderTg").addClass("cur");
	}else if($(".tabStatus").val()==2){
		$(".tb_backOrderJj").addClass("cur");
	}
});

function changTbl(obj){
	$(".tabStatus").attr("value",obj);
	$(".searchThirdOrderList").submit();
}

/*
 * 更改页数
 */
function changePageNo(obj){
	$(".searchThirdOrderList").append("<input type='hidden' name='pageNo' value='"+$(obj).attr("data-role")+"' />").submit();
}

//拣货，装箱，出库操作 
function sub_Form(obj,id){
	if(obj==0){
		//拣货
		$(".sub_orderid").attr("value",id);
//		$(".sub_from").attr("action","thirdorderpicking.html").submit();
		toWindow("thirdorderpicking.html?orderId="+id,1000,400);
	}else if(obj==1){
		//装箱
	$(".sub_orderid").attr("value");
//		$(".sub_from").attr("action","thirdorderdeliverys.html").submit();
		toWindow("thirdorderdeliverys.html?orderId="+id,1000,500);
		
	}else{
		$(".sub_orderid").attr("value",id);
//		$(".sub_from").attr("action","thirdordersendgoods.html").submit();
		toWindow("tothirdordersendgoods.htm?orderId="+id,1000,500);
	}
}

function  toWindow(url,width,height){
	 var winName="newWin";
	    var awidth=screen.availWidth/3*2;       	//窗口宽度,需要设置  
	    var aheight=screen.availHeight/2.5*2;       //窗口高度,需要设置   
	    var atop=(window.screen.availHeight+30- aheight)/2;  //窗口顶部位置,一般不需要改  
	    var aleft=(window.screen.availWidth - awidth)/2;   //窗口放中央,一般不需要改
		var param0="scrollbars=0,status=0,menubar=0,resizable=no,location=no"; //新窗口的参数  
	    var params="top=" + atop + ",left=" + aleft + ",width=" + width+ ",height=" + height + "," + param0 ;  
	    win=window.open(url,winName,params); //打开新窗口  
	    win.focus(); //新窗口获得焦点  
}

