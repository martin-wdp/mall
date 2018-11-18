<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title></title>

    <!-- Bootstrap -->
    <link href="<%=basePath %>/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath %>/css/font-awesome.min.css">
    <link href="<%=basePath %>/iconfont/iconfont.css" rel="stylesheet">
    <link href="<%=basePath %>/css/summernote.css" rel="stylesheet">
    <link href="<%=basePath %>/css/bootstrap-select.min.css" rel="stylesheet">
    <link href="<%=basePath %>/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <link href="<%=basePath %>/css/style.css" rel="stylesheet">
    <style media=print>
        .Noprint{display:none;}
        .PageNext{page-break-after:   always;}
    </style>
    <style type="text/css">
        .express_board{position:relative;border:0px;height:600px;margin:10px;padding:20px;}
    </style>
    <title>打印</title>
</head>
<body style="background-color: #ffffff;width: 100%;">
<!--startprint1-->
${str }
<!--endprint1-->

</body>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=basePath %>js/jquery.min.js"></script>
<script src="<%=basePath %>js/bootstrap.min.js"></script>
<script src="<%=basePath %>js/summernote.min.js"></script>
<script src="<%=basePath %>js/language/summernote-zh-CN.js"></script>
<script src="<%=basePath %>js/bootstrap-select.min.js"></script>
<script src="<%=basePath %>js/bootstrap-datetimepicker.min.js"></script>
<script src="<%=basePath %>js/common/common_alert.js"></script>
<script src="<%=basePath %>js/language/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="<%=basePath %>js/common.js"></script>
<script type="text/javascript">
    $(function(){
    	$(".express_img").remove();
        doPrint();

    });
    function doPrintSetup(){
        //打印设置
        document.all.WebBrowser.ExecWB(8,1)
    }
    function doPrintPreview(){
        //打印预览
        document.all.WebBrowser.ExecWB(7,1)
    }
    function doPrint() {

        window.print();

    }
</script>
</html>