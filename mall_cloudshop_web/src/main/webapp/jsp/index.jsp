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
    <link href="<%=basePath %>/iconfont/iconfont.css" rel="stylesheet">
    <link href="<%=basePath %>/css/style.css" rel="stylesheet">
  </head>
<body>
	
   <!-- 引用头 -->
   <jsp:include page="page/header.jsp"></jsp:include>
   
   
    <div class="container-fluid page_body">
      <div class="row">
      			<!-- 引用昨天导航 -->
    		<jsp:include page="page/left.jsp"></jsp:include>
				
        <div class="col-lg-20 col-md-19 col-sm-18 main">
            
          <div class="main_cont">
				 <ol class="breadcrumb">
	              <li><a href="#">首页</a></li>
	              <li class="active">桌面</li>
	            </ol>
            <div class="simple_statistics mb20">
              <h4>订单</h4>
              <div class="container-fluid">
                <div class="row">
                  <div class="col-lg-20">
                    <div class="statistics_item">
                      <ul>
                        <li style="width:20%;">
                          <h5><a href="#">${indexBean.subOrderCount }</a></h5>
                          <p>昨日下单笔数</p>
                        </li>
                        <li style="width:20%;">
                          <h5><a href="#">${indexBean.payOrderCount }</a></h5>
                          <p>昨日付款订单</p>
                        </li>
                          <li style="width:20%;">
                          <h5><a href="#">${indexBean.sendOrderCount }</a></h5>
                          <p>昨日发货订单</p> 
                        </li>
                         <li style="width:20%;">
                          <h5><a href="#">￥${indexBean.yesterdayTurnover }</a></h5>
                          <p>昨日交易额</p>
                        </li>
                           <li style="width:20%;">
                          <h5><a href="#">￥${indexBean.sumTunover }</a></h5>
                          <p>总交易额</p>
                        </li>
                      </ul>
                    </div>
                  </div>
                  
                 
                </div>
              </div>
            </div>

            <div class="simple_statistics mb20">
              <h4>商品</h4>
              <div class="container-fluid">
                <div class="row">
                  <div class="col-lg-12">
                    <div class="statistics_item">
                      <ul>
                        <li>
                          <h5><a href="#">${indexBean.warningCount }</a></h5>
                          <p>库存预警</p>
                        </li>
                        <li>
                          <h5><a href="#">${indexBean.outOfStock }</a></h5>
                          <p>缺货商品数量</p>
                        </li>
                        <li>
                          <h5><a href="#">${indexBean.shelvesCount }</a></h5>
                          <p>已下架商品数</p>
                        </li>
                        <li>
                          <h5><a href="#">${indexBean.sumProduct }</a></h5>
                          <p>商品总款</p>
                        </li>
                      </ul>
                    </div>
                  </div>
                 
                 
                </div>
              </div>
            </div>

        
        
        
        
            <div class="simple_statistics mb20">
              <h4>会员</h4>
              <div class="container-fluid">
                <div class="row">
                  <div class="col-lg-12">
                    <div class="statistics_item">
                      <ul>
                        <li>
                          <h5><a href="#">${indexBean.newCustomerCount }</a></h5>
                          <p>昨日新增会员</p>
                        </li>
                        <li>
                          <h5><a href="#">${indexBean.sumCustomerCount }</a></h5>
                          <p>会员总数</p>
                        </li>
                        <li>
                          <h5><a href="#">${indexBean.newConsulting }</a></h5>
                          <p>新咨询</p>
                        </li>
                        <li>
                          <h5><a href="#">${indexBean.newComments }</a></h5>
                          <p>新评论</p>
                        </li>
                      </ul>
                    </div>
                  </div>
                 
                 
                </div>
              </div>
            </div>

        
            <div class="simple_statistics mb20">
              <h4>订单统计</h4>
              <div class="container-fluid">
                <div class="row">
               
                  <div id="ChartMain" style="height:350px;"></div>
                 
                </div>
              </div>
            </div>

        
        
          </div>
          
        </div>
      </div>
    </div>

    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<%=basePath %>js/bootstrap.min.js"></script>
    <script src="<%=basePath %>js/common.js"></script> 
	 <script src="<%=basePath %>js/echarts-2.2.1/echarts.js"></script>

   
   	<script type="text/javascript">
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
            'echarts/chart/line' // 
        ],
        function (ec) {
            // 基于准备好的dom，初始化echarts图表
            var myChart = ec.init(document.getElementById('ChartMain')); 
            
            option = {
                    tooltip : {
                        trigger: 'axis'
                    },
                    legend: {
                        data:${legend}
                    },
                    toolbox: {
                        show : false,
                        feature : {
                            mark : {show: true},
                            dataView : {show: true, readOnly: false},
                            magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
                            restore : {show: true},
                            saveAsImage : {show: true}
                        }
                    },
                    calculable : true,
                    xAxis : [
                        {
                            type : 'category',
                            boundaryGap : false,
                            data :${data}
                        }
                    ],
                    yAxis : [
                        {
                            type : 'value'
                        }
                    ],
                    series :${series}
                };
                     
            // 为echarts对象加载数据 
            myChart.setOption(option); 
        }
    );
   	</script>
  </body>
</html>
