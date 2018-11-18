var iHeight = 0;
var iTop = 0;
var clientHeight = 0;
var iIntervalId = null;
var pageNo=1;
var loadPage = 4;
var basePath = $("#basePath").val();
var baseUrl = $("#baseUrl").val();
getPageHeight();


//添加定时检测事件，每2秒检测一次
iIntervalId = setInterval("_onScroll();", 500);
//取得当前页面显示所占用的高度
function getPageHeight() {
if(document.body.clientHeight && document.documentElement.clientHeight) {  
   clientHeight = (document.body.clientHeight < document.documentElement.clientHeight) ? document.body.clientHeight : document.documentElement.clientHeight;          
}else {  
  clientHeight = (document.body.clientHeight > document.documentElement.clientHeight) ? document.body.clientHeight : document.documentElement.clientHeight;      
}
iHeight = Math.max(document.body.scrollHeight,document.documentElement.scrollHeight);
}


//判断滚动条是否到达底部
function reachBottom() {
	var scrollTop = 0;
	if(document.documentElement && document.documentElement.scrollTop) {  
		scrollTop = document.documentElement.scrollTop;  
	} else if (document.body) {  
		scrollTop = document.body.scrollTop;  
	}
	if((scrollTop > 0) && (scrollTop + clientHeight == iHeight)) {
		return true;  
	} else {  
		return false; 
	}
}

//检测事件，检测滚动条是否接近或到达页面的底部区域，0.99是为了更接近底部时
function _onScroll() {
	iTop = document.documentElement.scrollTop + document.body.scrollTop;
	getPageHeight();
	if(((iTop+clientHeight)>parseInt(iHeight*0.99))||reachBottom()) {
		if(pageNo >= loadPage) {
			clearInterval(iIntervalId);
			$('#showmore').hide();
			return;
		}
		show();
	}
};

//调用ajax取服务端数据
function show() {
	 pageNo++;
  $.ajax({
    url: 'topicessencelist.htm?pageNo='+pageNo,
    type: 'GET',
    dataType: 'text',
	 beforeSend: showLoadingImg,
    error: showFailure,
    success:showResponse
  });
}

	
function showLoadingImg() {
	 $('#showmore').html(' <img src="images/indicator.gif" />');
}

function showFailure() {
	 $('#showmore').html('<font color=red> 获取查询数据出错 </font>');
}

//根据ajax取出来的json数据转换成html
function showResponse(responseData) {
 var returnjson = eval("(" +responseData+")");	
 var nextpagehtml = ''; 
 for(var i=0; i<returnjson.list.length; i++) {
	    nextpagehtml +='<div class="topic_box clearfix">';
	    nextpagehtml +='<div class="head_box fl tc"><a class="u_head" href="customerhomepage/'+returnjson.list[i].customerId+'.html" target="_blank">';
	   if(returnjson.list[i].customerHead == null){
		   nextpagehtml +='<img width="50" height="50"  title="'+returnjson.list[i].customerName+'" src="'+basePath+'/images/default_head3.jpg" />'+ returnjson.list[i].customerName.substring(0,8)  +'</a></div>';
	   }else{
		   nextpagehtml +='<img width="50" height="50"  title="'+returnjson.list[i].customerName+'" src="'+baseUrl+returnjson.list[i].customerHead +'" />'+ returnjson.list[i].customerName.substring(0,8)  +'</a></div>';
	   }
	    nextpagehtml +='  <div class="topic_cont fr">';
	    nextpagehtml +='  	<span class="t_arrow"></span>';
	    nextpagehtml +='      <div class="tp_hd clearfix">';
	    nextpagehtml +='      	<a class="topic_name fl" href="topicdetail/'+returnjson.list[i].groupId+'-'+returnjson.list[i].topicId+'.html"  target="_blank">'+returnjson.list[i].topicTitle;
	    nextpagehtml +='</a>';
	    var browse = returnjson.list[i].topicHot;
	    if(browse==null){
	    	browse=0;
	    }
	    
	    var reply = returnjson.list[i].replyCount;
	    if(reply==null){
	    	reply=0;
	    }
	    
	    nextpagehtml +='          <div class="tp_info clearfix fr">';
	    nextpagehtml +='          	<a class="fl" href="topicdetail/'+returnjson.list[i].groupId+'-'+returnjson.list[i].topicId+'.html"  target="_blank" >热度（'+browse+'）</a>';
	    nextpagehtml +='              <a class="fl"  href="topicdetail/'+returnjson.list[i].groupId+'-'+returnjson.list[i].topicId+'.html" target="_blank">评论（'+reply+'）</a>';
	    nextpagehtml +='              <!-- Baidu Button BEGIN -->';
	
	    nextpagehtml +='              <div id="bdshare" class="bdshare_t bds_tools get-codes-bdshare fl ml10"  data="{\'url\':\''+basePath+'/topicdetail/'+returnjson.list[i].groupId+'-'+returnjson.list[i].topicId+'.html\',\'text\':\'《'+returnjson.list[i].topicTjTitle+'》,摆摆网热帖大推荐。'+returnjson.list[i].topicContent.replace(/<[^>]+>/g,"").substring(0,80)+'(分享来自@摆摆网)\''+
	    '}">';	    																										
	    nextpagehtml +='                  <span class="bds_more">分享</span>';
	    nextpagehtml +='              </div>';
       nextpagehtml +='             <!-- Baidu Button END -->';
       nextpagehtml +='          </div><!--/tp_info-->';
       nextpagehtml +='      </div><!--/tp_hd-->';
       nextpagehtml +='      <div class="tp_cont mt15 clearfix">';
       nextpagehtml +='       	<div class="tp_imgs mr10 fl clearfix">'; 
       var topicImg =  returnjson.list[i].topicImgs;
       if(topicImg.length>0){
       	for(var j=0;j<topicImg.length;j++){
       		if(topicImg[j].topicType=='0' && j <2){
       			nextpagehtml +='<a class="fl"  href="topicdetail/'+returnjson.list[i].groupId+'-'+returnjson.list[i].topicId+'.html" target="_blank"><img  class="lodingimg" width="160" height="120" src="'+topicImg[j].topicImgUrl+'" /></a>';
	        	};
       	};
       }
       
       nextpagehtml +='          </div><!--/tp_imgs-->';
       var topicContent = returnjson.list[i].topicContent.replace(/<[^>]+>/g,"").replace(/(^\s*)|(\s*$)/g, "");
       if(topicImg.length==1){
       	topicContent = topicContent.substring(0,250);
       }
       if(topicImg.length>1){
       	topicContent = topicContent.substring(0,200);
       }
       if(topicImg.length==0){
       	topicContent = topicContent.substring(0,270);
       }
       var date = new Date(returnjson.list[i].topicCreateTime);
       var now = date; 
       var nowStr = now.format("yyyy-MM-dd hh:mm:ss"); 
       nextpagehtml +='          <p>'+topicContent+'</p>';
       nextpagehtml +='      </div><!--/tp_cont-->'; 
       nextpagehtml +='      <div class="tp_bt mt15">';
       nextpagehtml +='      	来自：<a href=" groupdetail/'+returnjson.list[i].groupId+'.html"  target="_blank">'+returnjson.list[i].groupName+'</a>小组';
       nextpagehtml +='           <span class="date ml30">'+nowStr+' </span>';
       nextpagehtml +='      </div><!--/tp_bt-->';
       nextpagehtml +='    </div><!--/topic_cont-->';
       nextpagehtml +=' </div><!--/topic_box-->';
 } 
 $('#items').append(nextpagehtml ); 
 if(returnjson.nextPageNo==pageNo){
	  clearInterval(iIntervalId);
	    $('#showmore').hide();
 }else{
	  $('#showmore').html('<a class="handle" href="javascript:show()">显示更多结果</a>');
 }

 // 当前页码数小于3页时继续显示更多提示框
/* if(page < 2) {
   $('#showmore').html('<a class="handle" href="javascript:show()">显示更多结果</a>');
 } else {
   clearInterval(iIntervalId);
   $('#showmore').hide();
 }*/
bdShare.fn.init();
}