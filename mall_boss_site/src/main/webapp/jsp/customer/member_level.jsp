<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>会员等级统计</title>

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


    <style>

        .level_left {
            width: 580px;
            overflow: hidden;
            min-height: 420px;
            float: left;
            border: 1px solid #ddd;
        }

        .level_left .title, .level_rig .title {
            font-size: 14px;
            padding: 10px 0px;
        }

    </style>
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

                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th width="50%">会员等级概况</th>
                            <th>会员等级</th>
                            <th>数量</th>
                            <th>占比</th>
                        </tr>
                        </thead>

                        <tbody>
                        <tr>
                            <td rowspan="${fn:length(cusList)+1}">
                                <div id="main" class="level_left"></div>
                            </td>
                        </tr>
                        <c:forEach items="${cusList }" var="customer">
                            <tr>
                                <td><c:if test="${customer.pointLevelName==null }">其他</c:if>
                                        ${customer.pointLevelName }
                                </td>
                                <td>${customer.levelCount }</td>
                                <fmt:formatNumber value="${customer.levelRate }" pattern="0.00" var="levelRate"/>
                                <td>${levelRate }%</td>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>


                </div>


            </div>
        </div>

    </div>
</div>


<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=basePath%>js/bootstrap.min.js"></script>
<script src="<%=basePath%>js/summernote.min.js"></script>
<script src="<%=basePath%>js/language/summernote-zh-CN.js"></script>
<script src="<%=basePath%>js/bootstrap-select.min.js"></script>
<script src="<%=basePath%>js/bootstrap-datetimepicker.min.js"></script>
<script src="<%=basePath%>js/language/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="<%=basePath%>js/common.js"></script>
<script src="<%=basePath%>js/esl.js"></script>
<script src="<%=basePath %>js/echarts-2.2.1/echarts.js"></script>
</body>
<script type="text/javascript">
    $(function(){ 
        // 路径配置
        require.config({
            paths: {
                echarts: 'http://echarts.baidu.com/build/dist'
            }
        });
        
        // 使用
        require(
            [
                'echarts',
                'echarts/chart/pie' // 
            ],
                function (ec) {
                    // 基于准备好的dom，初始化echarts图表
                    var myChart = ec.init(document.getElementById('main'));
                    var option = {
                        title: {
                            text: '会员等级概况',
                            x: 'center'
                        },
                        tooltip: {
                            trigger: 'item',
                            formatter: "{a} <br/>{b} : {c} ({d}%)"
                        },
                        legend: {
                            orient: 'vertical',
                            x: 'left',
                            data:${levelLists}
                        },
                        toolbox: {
                            show: true,
                            feature: {
                                mark: {show: true},
                                dataView: {show: true, readOnly: false},
                                magicType: {
                                    show: false,
                                    type: ['pie', 'funnel'],
                                    option: {
                                        funnel: {
                                            x: '25%',
                                            width: '50%',
                                            funnelAlign: 'left',
                                            max: 1548
                                        }
                                    }
                                },
                                restore: {show: true},
                                saveAsImage: {show: true}
                            }
                        },
                        calculable: true,
                        series: [
                            {
                                name: '会员比例',
                                type: 'pie',
                                radius: '55%',
                                center: ['50%', '60%'],
                                data:${cusLists}
                            }
                        ]
                    };
                    // 为echarts对象加载数据
                    myChart.setOption(option);
                });
    });
    //跳转首页
    function jumpIndex() {
        window.parent.location.href = 'index.htm';
    }

</script>
</html>
