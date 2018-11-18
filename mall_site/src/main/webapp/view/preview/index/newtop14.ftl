<#assign basePath=request.contextPath>
<link rel="stylesheet" href="${basePath}/index_fourteen/css/style.css"/>
<script src="${basePath}/index_fourteen/js/jquery-1.11.1.min.js"></script>
<script src="${basePath}/index_fourteen/js/jquery.slides.min.js"></script>
<script src="${basePath}/index_fourteen/js/jcarousellite_1.0.1.js"></script>
<script src="${basePath}/index_fourteen/js/angular.min.js"></script>
<script src="${basePath}/index_fourteen/js/template.js"></script>
<script src="${basePath}/index_fourteen/js/app.js"></script>
<div class="section_headerTop">
        <div class="slot slot_headerTop01">
            <ul class="left">
                <li><a class="favorite" href="javascript:;" onclick="addToFavorite('${topmap.systembase.bsetName}');"><s></s>加入收藏</a></li>
                <li><span class="welcome">您好<#if cust??>${cust.customerNickname!''}</#if>，欢迎来到${topmap.systembase.bsetName}!</span></li>
                <li class="login_info">
                <#if cust??>
            	<#else>
                    <a class="site_login" href="${basePath}/login.html">请登录</a>
                    <a class="site_register" href="${basePath}/register.html">免费注册</a>
                </#if>
                </li>
                <li class="local-choose">
                    <a href="javascript:;"><span class="province_text"></span><i class="iconfont"></i></a>
                    <div class="localInfo">
                        <div class="localPanel">
                            <!--<a href="javascript:;">北京</a>
                            <a href="javascript:;">上海</a>
                            <a href="javascript:;">天津</a>
                            <a href="javascript:;">重庆</a>
                            <a href="javascript:;">江苏</a>
                            <a href="javascript:;">浙江</a>
                            <a href="javascript:;">广东</a>
                            <a href="javascript:;">安徽</a>-->
                        </div>
                    </div>
                </li>
                <li class="loginout_info">
                <#if cust??>
                    <a class="site_loginout" href="${basePath}/logout.html">退出</a>
                </#if>
                </li>
            </ul>
            <ul class="siteNav">
                <li class="nav_item"><a href="${basePath}/customer/myorder.html">我的订单</a></li>
                <li class="nav_item"><a href="${basePath}/customer/index.html">会员俱乐部</a></li>
                <li class="nav_item navCenter">
                    <a href="${basePath}/customer/myfollw.html">收藏夹<s></s></a>
                    <div class="navInfo">
                        <div class="navPanel">
                            <a href="${basePath}/customer/sellermyfollw.html">收藏的店铺</a>
                            <a href="${basePath}/customer/myfollw.html">收藏的商品</a>
                        </div>
                    </div>
                </li>
                <li class="nav_item navCenter">
                    <a href="${basePath}/help/2">客户服务<s></s></a>
                    <div class="navInfo">
                        <div class="navPanel">
                            <a href="${basePath}/help/2">帮助中心</a>
                            <a href="${basePath}/help/65">售后服务</a>
                            <!--<a href="javascript:;">在线客服</a>-->
                            <a href="${basePath}/customer/ordercomplain.html">投诉中心</a>
                            <!--<a href="javascript:;">客服邮箱</a>-->
                        </div>
                    </div>
                </li>
            </ul>
        </div>
        <div class="slot slot_headerTop02"></div>
    </div>
    <div class="bh-mask"></div>
<div id="ctDia" class="bh-dialog dia0">
    <div class="dia-tit">
        <h4>加入收藏</h4>
        <a class="dia-close" href="javascript:;" onclick="scls(this)"></a>
    </div>
    <div class="dia-cont">
        <p style="text-align: center">请使用菜单栏或Ctrl+D进行添加！</p>
    </div>
    <div class="dia-btn"><a href="javascript:;" onclick="scls(this)">确定</a></div>
</div>
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
	            sdia('ctDia');
	        }   
	    }   
	}	
</script>  
    