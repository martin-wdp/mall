
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<#assign basePath=request.contextPath>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${basePath}/css/base.min.css" />
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css" />
<script src="http://l.tbcdn.cn/apps/top/x/sdk.js?appkey=21790336"></script>
<script type="text/javascript">
	//加入收藏
	function addToFavorite(){
		try {   
			window.external.AddFavorite('http://localhost:8080/npstore_H_web/','福气商城');
	    } catch (e) {   
	        try {   
	            window.sidebar.addPanel('http://localhost:8080/npstore_H_web/', '福气商城', "");   
	        } catch (e) {   
	            alert("加入收藏失败，请使用菜单栏或Ctrl+D进行添加");   
	        }   
	    }   
	}
	var empbo = 0;
	window.onload = function(){
		$.ajax({ url: "${basePath}/tops_1.html", async:false ,success: function(date){
	        var logo = date.systembase;
	        
	        $("#logo").append("<a class='logo fl' href='"+logo.bsetAddress+"'><img alt=''src='"+logo.bsetLogo+"' /></a>");
	     
	 }});
	}
	  

</script>
</head>



</body>
<script type="text/javascript" src="${basePath}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery.slides.min.js"></script>
<script type="text/javascript" src="${basePath}/js/jcarousellite_1.0.1.js"></script>
<script type="text/javascript" src="${basePath}/js/default.js"></script>
<script type="text/javascript" src="${basePath}/js/index.js"></script>

</html>