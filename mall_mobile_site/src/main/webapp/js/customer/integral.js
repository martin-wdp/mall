var iHeight = 0;
var iTop = 0;
var clientHeight = 0;
var iIntervalId = null;
var pageNo=1;
var typePagrNo=1;
var basePath = $("#basePath").val();
//getPageHeight();


//添加定时检测事件，每2秒检测一次
//iIntervalId = setInterval("_onScroll();", 500);
//取得当前页面显示所占用的高度
/*function getPageHeight() {
if(document.body.clientHeight && document.documentElement.clientHeight) {  
   clientHeight = (document.body.clientHeight < document.documentElement.clientHeight) ? document.body.clientHeight : document.documentElement.clientHeight;          
}else {  
  clientHeight = (document.body.clientHeight > document.documentElement.clientHeight) ? document.body.clientHeight : document.documentElement.clientHeight;      
}
iHeight = Math.max(document.body.scrollHeight,document.documentElement.scrollHeight);
}*/


//判断滚动条是否到达底部
/*function reachBottom() {
	 判断是否到底 
	//var flag = false;
	  $('.swiper-slide').scroll(function(){
	    if($(this).scrollTop() >= ($(this).find('.income_list').height() - $(this).height())){
	    	show();
	    //	flag= true;  
		} 
	  });
	//  return flag;
}*/

/*//检测事件，检测滚动条是否接近或到达页面的底部区域，0.99是为了更接近底部时
function _onScroll() {
	if($('.handle').html()!= "已无更多结果"){
	iTop = document.documentElement.scrollTop + document.body.scrollTop;
	//getPageHeight();
	if(reachBottom()=='true') {
		if(pageNo >= loadPage) {
			clearInterval(iIntervalId);
			$('#showmore').hide();
			return;
		}
		show();
	}
	}
};
*/
//调用ajax取服务端数据
function show() {
	 pageNo++;
	 var type = $("#type").val();
  $.ajax({
    url: basePath+'/allmyintegral.htm?pageNo='+pageNo+'&type='+type,
    type: 'GET',
    dataType: 'text',
    async:'false',
	beforeSend: showLoadingImg,
    error: showFailure,
    success:showResponse
  });
}

	
function showLoadingImg() {
	 $('#showmore').html(' <img src='+basePath+'/images/indicator.gif />');
}

function showFailure() {
	 $('#showmore').html('<font color=red> 获取查询数据出错 </font>');
}

//调用ajax取服务端数据
function showType() {
	pageNo=1;
	 var type = $("#type").val();
  $.ajax({
    url: basePath+'/allmyintegral.htm?pageNo='+typePagrNo+'&type='+type,
    type: 'GET',
    dataType: 'text',
    async:'false',
    success:showResponseType
  });
}

//根据ajax取出来的json数据转换成html
function showResponseType(responseData) {
		 var returnjson = eval("(" +responseData+")");	
		 var nextpagehtml = ''; 
		 for(var i=0; i<returnjson.list.length; i++) {
		    nextpagehtml +='  <div class="income_item">';
		    nextpagehtml += '<dl>';  
		    nextpagehtml += '<dt><span>';  
		    var detail='';
		    var integral =  returnjson.list[i];
		    if(integral.pointDetail!=''&&integral.pointDetail!=null){
		    	detail = integral.pointDetail;
		    }
		    nextpagehtml +=''+detail +'';
		    nextpagehtml += '</span></dt>';  
		    nextpagehtml +='<dd><span class="light">';
		    if(integral.createTime!=''&&integral.createTime!=null){
		    	var date = new Date(integral.createTime);
		    	var now = date; 
		    	var nowStr = now.format("yyyy-MM-dd hh:mm:ss"); 
		    	nextpagehtml +=''+nowStr+'';
		    }
		    nextpagehtml += '</span></dd>'; 
		    nextpagehtml += '</dl>'; 
		    nextpagehtml += '<dl>'; 
		    nextpagehtml += '<dd>'; 
		    nextpagehtml += '<span class="red">'; 
		     if(integral.pointType!='' && integral.pointType != null){
		    	 if(integral.pointType ==1){
		    		 nextpagehtml +='+'+integral.point+'';
		    	 }else if(integral.pointType ==0){
		    		 nextpagehtml +='-'+integral.point+'';
		    	 }
		     }
		    nextpagehtml += '</span></dd>'; 
		    nextpagehtml += '</dl>'; 
		    nextpagehtml += '</div>'; 
	 } 
	 if(nextpagehtml != null && nextpagehtml != ""){
	 $('#items').html(nextpagehtml ); 
	  $newElems =$("#items .income_item");
	     // 渐显新的内容
	     $newElems.animate({ opacity: 1 });
	     if(returnjson.nextPageNo==pageNo){
	    	 clearInterval(iIntervalId);
	    	 $('#showmore').hide();
	     }else{
	    	 $('#showmore').html('<a class="handle" href="javascript:show()">显示更多结果</a>');
	     }
	 }else{
		 $('#showmore').html('<a class="handle" href="javascript:show()">已无更多结果</a>');
	 }
}

//根据ajax取出来的json数据转换成html
function showResponse(responseData) {
		 var returnjson = eval("(" +responseData+")");	
		 var nextpagehtml = ''; 
		 for(var i=0; i<returnjson.list.length; i++) {
		    nextpagehtml +='  <div class="income_item">';
		    nextpagehtml += '<dl>';  
		    nextpagehtml += '<dt><span>';  
		    var detail='';
		    var integral =  returnjson.list[i];
		    if(integral.pointDetail!=''&&integral.pointDetail!=null){
		    	detail = integral.pointDetail;
		    }
		    nextpagehtml +=''+detail +'';
		    nextpagehtml += '</span></dt>';  
		    nextpagehtml +='<dd><span class="light">';
		    if(integral.createTime!=''&&integral.createTime!=null){
		    	var date = new Date(integral.createTime);
		    	var now = date; 
		    	var nowStr = now.format("yyyy-MM-dd hh:mm:ss"); 
		    	nextpagehtml +=''+nowStr+'';
		    }
		    nextpagehtml += '</span></dd>'; 
		    nextpagehtml += '</dl>'; 
		    nextpagehtml += '<dl>'; 
		    nextpagehtml += '<dd>'; 
		    nextpagehtml += '<span class="red">'; 
		     if(integral.pointType!='' && integral.pointType != null){
		    	 if(integral.pointType ==1){
		    		 nextpagehtml +='+'+integral.point+'';
		    	 }else if(integral.pointType ==0){
		    		 nextpagehtml +='-'+integral.point+'';
		    	 }
		     }
		    nextpagehtml += '</span></dd>'; 
		    nextpagehtml += '</dl>'; 
		    nextpagehtml += '</div>'; 
	 } 
	 if(nextpagehtml != null && nextpagehtml != ""){
	 $('#items').append(nextpagehtml ); 
	  $newElems =$("#items .income_item");
	     // 渐显新的内容
	     $newElems.animate({ opacity: 1 });
	     if(returnjson.nextPageNo==pageNo){
	    	 clearInterval(iIntervalId);
	    	 $('#showmore').hide();
	     }else{
	    	 $('#showmore').html('<a class="handle" href="javascript:show()">显示更多结果</a>');
	     }
	 }else{
		 $('#showmore').html('<a class="handle" href="javascript:show()">已无更多结果</a>');
	 }

 // 当前页码数小于3页时继续显示更多提示框
/* if(page < 2) {
   $('#showmore').html('<a class="handle" href="javascript:show()">显示更多结果</a>');
 } else {
   clearInterval(iIntervalId);
   $('#showmore').hide();
 }*/
//bdShare.fn.init();
}