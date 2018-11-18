<#assign basePath=request.contextPath>
<link rel="stylesheet" type="text/css" href="${basePath}/index_three/css/base.min.css"/>
	<link rel="stylesheet" type="text/css" href="${basePath}/index_three/css/style.css"/>
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
	</script>
	<input type="hidden" id="basePath" value="${basePath}"/>
	<div class="top">
    	<div class="top_wp clearfix">
        	<div class="top_left fl" style="height:30px;">
            	<a class="mr10 fl" href="javascript:;" onclick="addToFavorite('${topmap.systembase.bsetName}');">收藏${topmap.systembase.bsetName}</a>
                <dl id="home_area">
                	<dt><b></b><strong class="province_text">江苏</strong><a href="javascript:;">[更换]</a></dt>       
                	<dd class="dd">
                		<ul class="lh  province_list"></ul><!--/locate_list-->	
                    <div class="close" onclick="$('#home_area').removeClass('hover')">×</div></dd></dl>
            </div><!--/top_left-->
            
            <ul class="top_nav fr clearfix">
            	<li>
	            	<#if cust?? >
						<span class="ml10"><#if cust??>${cust.customerNickname!''}</#if>，欢迎来到${topmap.systembase.bsetName}！</span>
						<a class="t_login ml10" href="${basePath}/logout.html">退出</a>
					<#else>
						<a href="${basePath}/login.html">[请登录]</a><a href="${basePath}/register.html">[免费注册]</a><span class="t_line">|</span>
					</#if>
				</li>
            	<li><a href="${basePath}/customer/myorder.html">我的订单</a><span class="t_line">|</span></li>
            	<li class="mobile ms-01" id="app-np"><i></i><span class="outline"></span><a class="m_store" href="javascript:;">手机版<b></b></a><span class="t_line">|</span>
                   <div class="mobile_con">
                       <img src="${basePath}/index_three/images/weixin.gif" class="fl weixin"/>
                       <div class="client fl ">
                       	   <p>手机客户端</p>
                       	   <a href="###"><img src="${basePath}/index_three/images/adro_btn.gif"/></a>
                       	   <a href="###"><img src="${basePath}/index_three/images/app_btn.gif"/></a>                      	   
                       </div>	
                   </div><!--mobile_con-->
            	</li>
            	<li class="wxo ms-02" id="app-np"><i></i><span class="outline_pp"></span><a class="m_store" href="javascript:;">关注我们<b></b></a>
                   <div class="mobile_con" style="padding:20px 10px; right:-1px;">
                       <img src="${basePath}/index_three/images/weixin.gif" class="fl mr20 ml10"/>
                       <img src="${basePath}/index_three/images/weixin.gif" class="fl ml10"/>	
                   </div><!--mobile_con-->
            	</li>
            </ul><!--/top_nav-->
        </div><!--/top_wp-->
    </div><!--/top-->
    <div class="dialog dia9">
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
    
     <script type="text/javascript" src="${basePath}/index_three/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="${basePath}/index_three/js/common.js"></script>
    <script type="text/javascript" src="${basePath}/index_three/js/jquery.slides.min.js"></script>
    <script type="text/javascript" src="${basePath}/index_three/js/jcarousellite_1.0.1.js"></script>
    <script type="text/javascript" src="${basePath}/index_three/js/jquery.lazyload.min.js"></script>
    <script type="text/javascript" src="${basePath}/index_three/js/search.js"></script>