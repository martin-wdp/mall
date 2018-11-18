<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
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
    <title>会员地区统计</title>

    <!-- Bootstrap -->
    <link href="<%=basePath %>css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath %>css/font-awesome.min.css">
    <link href="<%=basePath %>iconfont/iconfont.css" rel="stylesheet">
    <link href="<%=basePath %>css/summernote.css" rel="stylesheet">
    <link href="<%=basePath %>css/bootstrap-select.min.css" rel="stylesheet">
    <link href="<%=basePath %>css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <link href="<%=basePath %>css/style.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<!-- 引用头 -->
<jsp:include page="../page/header.jsp"></jsp:include>
<div class="page_body container-fluid">
    <div class="row">
        <!-- 引用左边 -->
        <jsp:include page="../page/left.jsp"></jsp:include>
        <div class="col-lg-20 col-md-19 col-sm-18 main">
            <div class="main_cont">
               <jsp:include page="../page/breadcrumbs.jsp"></jsp:include>

                <h2 class="main_title">${pageNameChild}</h2>

                <div class="common_data p20">

                    <div class="map_area" id="main" style="height:700px">

                    </div>

                </div>

            </div>
        </div>
    </div>
</div>


<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=basePath %>js/bootstrap.min.js"></script>
<script src="<%=basePath %>js/summernote.min.js"></script>
<script src="<%=basePath %>js/language/summernote-zh-CN.js"></script>
<script src="<%=basePath %>js/bootstrap-select.min.js"></script>
<script src="<%=basePath %>js/bootstrap-datetimepicker.min.js"></script>
<script src="<%=basePath %>js/language/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="<%=basePath %>js/common.js"></script>
<script src="<%=basePath %>js/report/report.js"></script>
<script src="<%=basePath %>js/common/common_alert.js"></script>
<script src="<%=basePath%>js/esl.js"></script>
<script src="http://echarts.baidu.com/doc/example/www/js/echarts.js"></script>

<script>
    //跳转首页
    function jumpIndex(){
        window.parent.location.href='index.htm';
    }
    $(function(){


        // 路径配置
        require.config({
            paths:{
                'echarts' : 'http://echarts.baidu.com/doc/example/www/js'
            }
        });
        require(
                [
                    'echarts',
                    'echarts/chart/map'
                    // 使用柱状图就加载bar模块，按需加载
                ],
                function (ec) {
                    // 基于准备好的dom，初始化echarts图表
                    var myChart = ec.init(document.getElementById('main'));
                    myChart.setOption({
                        title : {
                            text: '会员地区分布',
                            subtext: '未填写地区:${qita}',
                            x:'center'
                        },
                        tooltip : {
                            trigger: 'item'
                        },
                        legend: {
                            orient: 'vertical',
                            x:'left',
                            data:['会员分布']
                        },
                        toolbox: {
                            show: true,
                            orient : 'vertical',
                            x: 'right',
                            y: 'center',
                            feature : {
                                mark : {show: true},
                                dataView : {show: true, readOnly: false},
                                restore : {show: true},
                                saveAsImage : {show: true}
                            }
                        },
                        roamController: {
                            show: true,
                            x: 'right',
                            mapTypeControl: {
                                'china': true
                            }
                        },
                        series : [
                            {
                                name: '会员分布',
                                type: 'map',
                                mapType: 'china',
                                roam: false,
                                itemStyle:{
                                    normal:{label:{show:true}},
                                    emphasis:{label:{show:true}}
                                },
                                data:${addmap}
                            }
                        ]
                    });

                }
        );

    });
</script>
</body>
</html>
