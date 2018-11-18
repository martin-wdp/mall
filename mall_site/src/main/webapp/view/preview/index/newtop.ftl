	<#assign basePath=request.contextPath>
	<script>
	//加入收藏
	function addToFavorite(siteName){
		try {   
			window.external.AddFavorite($("#basePath").val(),siteName);
	    } catch (e) {   
	        try {   
	            window.sidebar.addPanel($("#basePath").val(), siteName, "");   
	        } catch (e) {   
	            $(".collect_tip_cancel").click(function(){
	            	cls();
	            });
	            dia(9);
	            
	        }   
	    }   
	}
	//表单提交
	function loginthird(){
		$("#loginthirds").submit();
	}
		
		
	
		
	
	//分享链接给好友 弹出层
	function shareUrl(){
	    var customerId = $('#customer_id').val(); 
		//截取当前url IP
		var str = window.location.href;
		var b = str.indexOf('/',str.indexOf('/')+2);
		var a = str.indexOf("//")+2;
		var local = str.substring(a,b);
		var customerId = $('#customer_id').val(); //当前登录的用户ID
		if(customerId==null||customerId==""){
			 dia(20);
		}else{
			//给会员id加密
			 var b = new Base64(); 
		     var str = b.encode('"'+customerId+'"');  
		     var url = local+basePath+'/register.html'+'?customer_id='+str; 
			 $('#content').html(url);
	  		 dia(19);
		}
	}
	
	//推荐该网站给好友- 登录
	function login(){
	    var basePath = $('#basePath').val();
		//获取页面登录的 href
		window.location.href=basePath+'/login.html';
		
	}	
	//复制成功弹出复制成功窗口
	function fuzhi(){
		dia(21);
	}
	
	//对会员ID进行加密
	function Base64() {
	_keyStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
	this.encode = function (input) {
		var output = "";
		var chr1, chr2, chr3, enc1, enc2, enc3, enc4;
		var i = 0;
		input = _utf8_encode(input);
		while (i < input.length) {
			chr1 = input.charCodeAt(i++);
			chr2 = input.charCodeAt(i++);
			chr3 = input.charCodeAt(i++);
			enc1 = chr1 >> 2;
			enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
			enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
			enc4 = chr3 & 63;
			if (isNaN(chr2)) {
				enc3 = enc4 = 64;
			} else if (isNaN(chr3)) {
				enc4 = 64;
			}
			output = output +
			_keyStr.charAt(enc1) + _keyStr.charAt(enc2) +
			_keyStr.charAt(enc3) + _keyStr.charAt(enc4);
		}
		return output;
	}
 
	// public method for decoding
	this.decode = function (input) {
		var output = "";
		var chr1, chr2, chr3;
		var enc1, enc2, enc3, enc4;
		var i = 0;
		input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");
		while (i < input.length) {
			enc1 = _keyStr.indexOf(input.charAt(i++));
			enc2 = _keyStr.indexOf(input.charAt(i++));
			enc3 = _keyStr.indexOf(input.charAt(i++));
			enc4 = _keyStr.indexOf(input.charAt(i++));
			chr1 = (enc1 << 2) | (enc2 >> 4);
			chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
			chr3 = ((enc3 & 3) << 6) | enc4;
			output = output + String.fromCharCode(chr1);
			if (enc3 != 64) {
				output = output + String.fromCharCode(chr2);
			}
			if (enc4 != 64) {
				output = output + String.fromCharCode(chr3);
			}
		}
		output = _utf8_decode(output);
		return output;
	}
 
	// private method for UTF-8 encoding
	_utf8_encode = function (string) {
		string = string.replace(/\r\n/g,"\n");
		var utftext = "";
		for (var n = 0; n < string.length; n++) {
			var c = string.charCodeAt(n);
			if (c < 128) {
				utftext += String.fromCharCode(c);
			} else if((c > 127) && (c < 2048)) {
				utftext += String.fromCharCode((c >> 6) | 192);
				utftext += String.fromCharCode((c & 63) | 128);
			} else {
				utftext += String.fromCharCode((c >> 12) | 224);
				utftext += String.fromCharCode(((c >> 6) & 63) | 128);
				utftext += String.fromCharCode((c & 63) | 128);
			}
 
		}
		return utftext;
	}
 
	// private method for UTF-8 decoding
	_utf8_decode = function (utftext) {
		var string = "";
		var i = 0;
		var c = c1 = c2 = 0;
		while ( i < utftext.length ) {
			c = utftext.charCodeAt(i);
			if (c < 128) {
				string += String.fromCharCode(c);
				i++;
			} else if((c > 191) && (c < 224)) {
				c2 = utftext.charCodeAt(i+1);
				string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
				i += 2;
			} else {
				c2 = utftext.charCodeAt(i+1);
				c3 = utftext.charCodeAt(i+2);
				string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));
				i += 3;
			}
		}
		return string;
	}
}
	
		
	
		
	</script>
	<input type="hidden" id="basePath" value="${basePath}"/>

	<div class="top">
    	<div class="top_wp clearfix">
        	<div class="top_left fl">
            	<a class="collect_me mr10" href="javascript:;" onclick="addToFavorite('${topmap.systembase.bsetName}');">收藏${topmap.systembase.bsetName}</a>
                <span class="t_line">|</span>
                <span class="ml10">您好<#if cust??>${cust.customerNickname!''}</#if>，欢迎来到${topmap.systembase.bsetName}！</span>
                <input type="hidden" id="customer_id" value="<#if cust??>${cust.customerId!''}</#if>" />
                <#if cust?? >
					<a class="t_login ml10" href="${basePath}/logout.html">退出</a>
				<#else>
					<a class="t_login ml10" href="${basePath}/login.html">请登录</a>
					<!--<a class="t_login ml10" href="${basePath}/iconpay.htm">农行支付</a>-->
					
					<a class="t_reg ml10" href="${basePath}/register.html">免费注册</a>
				</#if>
            </div><!--/top_left-->
            
            <ul class="top_nav fr clearfix">
                <li><a href="${basePath}/systemmsg.html" class="fl mes_tips" target="_blank">
				 <span id="noreadmsg" class="noread"></span><img src="${basePath}/images/mail_red.png" alt=""/>
				 </a></li>
            <!--	<#if cust??>
            		<li><a href="javascript:;" onclick="loginthird();">商家入口</a></li>
				<#else>
					<li><a href="${basePath}/login.html">商家入口</a></li>
            	</#if>-->
            	<li><span class="t_line">|</span><a href="javascript:;" onclick="shareUrl()">分享给好友</a></li>
				<li><span class="t_line">|</span><a href="${basePath}/customer/myorder.html">我的订单</a></li>
                <li class="my_store pr">
                	<span class="t_line">|</span>
                    <span class="outline"></span>
                    <a class="m_store" href="${basePath}/customer/index.html">我的${topmap.systembase.bsetName}<b></b></a>
                    <div class="ms_cont pa">
                    	<a href="${basePath}/myshoppingcart.html">我的购物车</a>
                    	<!--
                        <a href="javascript:;" style="text-decoration:line-through;">我的购物金</a>
                        <a href="javascript:;" style="text-decoration:line-through;">我的积分卡</a>
                        <a href="javascript:;" style="text-decoration:line-through;">你推荐我送金</a>
                    	-->
                    </div><!--/ms_cont-->
                </li>
                   <li><span class="t_line">|</span><a href="${basePath}/help/2">帮助中心</a></li>
            </ul><!--/top_nav-->
            <#if cust??>
            	<form method="post" enctype="multipart/form-data" action="${(topmap.systembase.bsetThirdAddress)!''}/isloginthird.htm" id="loginthirds">
						<input type="hidden" value=${(customerId)!''} name="customerId" id="customerId"/>
						<input type="hidden" value=${(cust.loginKey)!''} name="loginKey" id="loginKey"/>
					</form>
            </#if>
        </div><!--/top_wp-->
    </div><!--/top-->
    <div class="mask"></div><!--/mask-->
    <div class="dialog dia9" style="height:auto!important">
        <div class="dia_tit clearfix">
            <h4 class="fl collect_title">加入收藏</h4>
            <a class="dia_close fr" href="javascript:;" onclick="cls()"></a>
        </div><!--/dia_tit-->
        <div class="dia_cont">
            <div class="dia_collect_intro tc pt30"><img class="vm collect_img" alt="" src="" />
            <em class="collect_content">加入收藏失败，请使用菜单栏或Ctrl+D进行添加!</em></div>
            <div class="dia_ops mt20 tc">
                <a class="collect_ok collect_tip_cancel" href="javascript:;">确定</a>
            </div><!--/dia_ops-->
        </div><!--/dia_cont-->
    </div><!--/dialog-->
    
    
    <div class="mask"></div><!--/分享链接给好友-->
	    <div id="fenxiang" class="dialog dia19" style="height:auto!important">
		    <div class="dia_tit clearfix">
			    <h4 class="fl collect_title">分享好友</h4>
			    <a class="dia_close fr" href="javascript:;" onclick="cls()"></a>
			</div>
			<div class="dia_cont">
				    <div  class="dia_collect_intro tc pt30">
				    	<span id="content" style="font-size:16px"></span>
				    </div>
			    <div class="dia_ops mt20 tc">
			   		<a class="collect_ok collect_tip_cancel"  id="copy" onclick="fuzhi()"  data-clipboard-target="content" href="javascript:;">复制</a>
			   		<a class="collect_ok collect_tip_cancel"  onclick="cls()" href="javascript:;">取消</a>
			   		<br/>
			    	<span>&nbsp;</span>
			   		<span style="color: red; font-size:12px">亲！如果您的好友成功注册为会员，会有积分送给您哦！</span>
			    </div><!--/dia_ops-->
	     </div><!--/dia_cont-->
    </div><!--/dialog-->
    
    
       <div class="mask"></div><!--/分享链接提示先登录-->
	    <div id="fenxiang" class="dialog dia20" style="height:auto!important">
		    <div class="dia_tit clearfix">
			    <h4 class="fl collect_title">登录提示</h4>
			    <a class="dia_close fr" href="javascript:;" onclick="cls()"></a>
			</div>
			<div class="dia_cont">
				    <div  class="dia_collect_intro tc pt30">
				    	<span id="content" style="font-size:16px">您未登录！现在确认要登录吗？</span>
				    </div>
			    <div class="dia_ops mt20 tc">
			   		<a class="collect_ok collect_tip_cancel" onclick="login()" href="javascript:;">确定</a>
			   		<a class="collect_ok collect_tip_cancel" onclick="cls()" href="javascript:;">取消</a>
			    </div><!--/dia_ops-->
	     </div><!--/dia_cont-->
    </div><!--/dialog-->
    
    
       <div class="mask"></div><!--/分享链接复制成功提示-->
	    <div id="fenxiang" class="dialog dia21" style="height:auto!important">
		    <div class="dia_tit clearfix">
			    <h4 class="fl collect_title">操作提示</h4>
			    <a class="dia_close fr" href="javascript:;" onclick="cls()"></a>
			</div>
			<div class="dia_cont">
				    <div  class="dia_collect_intro tc pt30">
				    	<span style="font-size:16px">复制成功！</span>
				    </div>
			    <div class="dia_ops mt20 tc">
			   		<a class="collect_ok collect_tip_cancel" onclick="cls()" href="javascript:;">关闭</a>
			    </div><!--/dia_ops-->
	     </div><!--/dia_cont-->
    </div><!--/dialog-->
    
    
    
    
   <script type="text/javascript" src="${basePath}/js/jquery.js"></script>

  <script type="text/javascript">
 
	 $.ajax({
		    url:'${basePath}/messagecount.html?'+Math.random(),
		    dataType:'json',
		    success:function(result){
		         if(result.noreadcount > 0){
		            $("#noreadmsg").html(result.noreadcount);
		         }else{
		         	$("#noreadmsg").hide();
		         }
		     }
		 });
	</script>
	  <script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/zeroclipboard/2.1.6/ZeroClipboard.min.js" ></script>
		<script type="text/javascript">
		// 将【复制】按钮充当复制数据的元素载体
		var clip = new ZeroClipboard( document.getElementById("copy"));
	</script>
	