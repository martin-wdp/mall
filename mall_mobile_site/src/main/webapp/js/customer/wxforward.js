var imgsrc="";
var wxinfo;
$.ajax({url:$("#basePath").val()+"/ajaxQueryMobSiteBasicNew.htm",async:false,success:function(data){
	var logo = data.logo, title=data.title, 
	shareTimelineDesc=data.shareTimelineDesc, sendAppMessageDesc=data.sendAppMessageDesc;
	if(logo=="" || logo==null || logo==undefined){
		logo = "http://shop.ningpai.com/mobile/getwxcode1.htm";
	}
	if(title=="" || title==null || title==undefined){
		title = $("#bsetName").val();
	}
	if(shareTimelineDesc=="" || shareTimelineDesc==null || shareTimelineDesc==undefined){
		shareTimelineDesc = $("#bsetDesc").val();
	}
	if(sendAppMessageDesc=="" || sendAppMessageDesc==null || sendAppMessageDesc==undefined){
		sendAppMessageDesc = $("#bsetDesc").val();
	}
	
	wxinfo = {
			imgsrc : logo,
			title : title,
			shareTimelineDesc : shareTimelineDesc,
			sendAppMessageDesc : sendAppMessageDesc
	};
}});
var dataForWeixin;
if(wxinfo){
	dataForWeixin={
			   MsgImg:wxinfo.imgsrc,
			   url:"http://shop.ningpai.com/mobile/getwxcode1.htm",
			   title:wxinfo.title,
			   shareTimelineDesc:wxinfo.shareTimelineDesc,
			   sendAppMessageDesc:wxinfo.sendAppMessageDesc,
			   callback:function(
				// 这里是分享成功后的回调功能
			   ){}
	};
}else{
	dataForWeixin={
			MsgImg:"http://shop.ningpai.com/mobile/images/intro.jpg",
			url:"http://shop.ningpai.com/mobile/getwxcode1.htm",
			title:$("#bsetName").val(),
			shareTimelineDesc:$("#bsetDesc").val(),
			sendAppMessageDesc:$("#bsetDesc").val(),
			callback:function(
					// 这里是分享成功后的回调功能
			){}
	};
}
(function(){
		   var onBridgeReady=function(){
		   // 发送给朋友
			   WeixinJSBridge.on('menu:share:appmessage', function(argv){
			      WeixinJSBridge.invoke('sendAppMessage',{
			         "img_url":dataForWeixin.MsgImg,
			         "img_width":"120",
			         "img_height":"120",
			         "link":dataForWeixin.url,
			         "desc":dataForWeixin.sendAppMessageDesc,
			         "title":dataForWeixin.title
			      }, function(res){(dataForWeixin.callback)();});
			   });
			   // 发送到朋友圈
			   WeixinJSBridge.on('menu:share:timeline', function(argv){
			      (dataForWeixin.callback)();
			      WeixinJSBridge.invoke('shareTimeline',{
			         "img_url":dataForWeixin.MsgImg,
			         "img_width":"120",
			         "img_height":"120",
			         "link":dataForWeixin.url,
			         "desc":dataForWeixin.shareTimelineDesc,
			         "title":dataForWeixin.title
			      }, function(res){(dataForWeixin.callback)();});
			   });
		   };
		   if(document.addEventListener){
			   document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
			}else if(document.attachEvent){
			   document.attachEvent('WeixinJSBridgeReady'   , onBridgeReady);
			   document.attachEvent('onWeixinJSBridgeReady' , onBridgeReady);
			}
})();
