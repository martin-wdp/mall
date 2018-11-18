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
    <link href="<%=basePath %>css/style.css" rel="stylesheet">
      <script src="<%=basePath %>js/esl-1.6.10.js"></script>
  </head>
  <style>
      body{
          font-size: 12px;
      <%--overflow-y: visible;--%>
      }
  </style>
<body>

   <!-- 引用头 -->
   <jsp:include page="page/header.jsp"></jsp:include>


    <div class="container-fluid page_body">
      <div class="row">
      			<!-- 引用昨天导航 -->
    		<jsp:include page="page/left.jsp"></jsp:include>

        <div class="col-lg-20 col-md-19 col-sm-18 main">

            <div class="main_cont">

                <div class="dashboard row">
                    <div class="col-lg-18 col-sm-16">
                        <div class="dashboard_left">

                            <div class="dashboard_left_col1 row">
                                <h2><i class="icon-th-large"></i> 销售情况概览</h2>
                                <div class="col-md-8">
                                    <div class="db_box spd">
                                        <div class="db_box_t">
                                            <div class="pull-right">
                                                <span></span>
                                            </div>
                                            <h4>销售额统计</h4>
                                            <P>
                                                <i class="icon-star-empty"></i> 最后更新：<span class="updateTime">06-23 12:22</span>
                                            </P>
                                            <div id="spdMap" style="height:150px;"></div>
                                        </div>
                                        <div class="db_box_b row">
                                            <div class="col-xs-12">
                                                <div class="db_data db_data_l">
                                                    <h5 id="weekPrice">￥290120</h5>
                                                    <span>本周销售额</span>
                                                </div>
                                            </div>
                                            <div class="col-xs-12">
                                                <div class="db_data db_data_r">
                                                    <h5 id="priceNew">54000</h5>
                                                    <span>今日销售额</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-8">
                                    <div class="db_box opd">
                                        <div class="db_box_t">
                                            <div class="pull-right">
                                                <span></span>
                                            </div>
                                            <h4>订单数统计</h4>
                                            <P>
                                                <i class="icon-star-empty"></i> 最后更新：<span class="updateTime">06-23 12:22</span>
                                            </P>
                                            <div id="opdMap" style="height:150px;"></div>
                                        </div>
                                        <div class="db_box_b row">
                                            <div class="col-xs-12">
                                                <div class="db_data db_data_l">
                                                    <h5  id="weekCount">2890</h5>
                                                    <span>本周订单数</span>
                                                </div>
                                            </div>
                                            <div class="col-xs-12">
                                                <div class="db_data db_data_r">
                                                    <h5  id="countNew">159</h5>
                                                    <span>今日订单数</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-8">
                                    <div class="db_box upd">
                                        <div class="db_box_t">
                                            <div class="pull-right">
                                                <span></span>
                                            </div>
                                            <h4>新用户统计</h4>
                                            <P>
                                                <i class="icon-star-empty"></i> 最后更新：<span class="updateTime">06-23 12:22</span>
                                            </P>
                                            <div id="updMap" style="height:150px;"></div>
                                        </div>
                                        <div class="db_box_b row">
                                            <div class="col-xs-12">
                                                <div class="db_data db_data_l">
                                                    <h5 id="customerCount">4520</h5>
                                                    <span>本周新增用户</span>
                                                </div>
                                            </div>
                                            <div class="col-xs-12">
                                                <div class="db_data db_data_r">
                                                    <h5 id="customerCountNew">510</h5>
                                                    <span>今日新增用户</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="dashboard_left_col2 row">
                                <div class="col-md-10">
                                    <div class="orders_pending db_box">
                                        <h4>
                                            <a href="#">待处理订单</a>
                                            <small id="orderCount" class="text-muted">您有4条未处理订单</small>
                                        </h4>

                                        <div id="orderList">

                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-14">
                                    <div class="latest_customers db_box">
                                        <h4>
                                            <a href="#">新注册会员</a>
                                            <small class="text-muted">最新注册的5个新会员</small>
                                        </h4>
                                        <table class="customer_table table table-striped table-hover">
                                            <thead>
                                            <tr>
                                                <th>序号</th>
                                                <th>用户名</th>
                                                <th>昵称</th>
                                                <th>登陆时间</th>
                                            </tr>
                                            </thead>
                                            <tbody id="newCustomerInfo">

                                            </tbody>
                                        </table>
                                        <div id="customerMap" style="height:118px;"></div>
                                    </div>

                                </div>
                            </div>
                            <%--2016-01-09 wuyanbo 屏蔽Boss后台 充值缴费、供货网、快消品、农产品 的显示--%>
                            <div class="dashboard_col3 row hideElement">
                                <div class="col-md-6">
                                    <div class="friend_sys db_box">
                                        <p class="h2">充值缴费</p>
                                        <a href="#">
                                            <img src="images/dashboard_col3_icon1.png" alt="充值缴费" style="width:auto;margin:20px auto;">
                                        </a>
                                        <p style="display:block;height:40px;line-height:20px;overflow:hidden;">包括系统、货源、品牌、运营支撑在内的一整套本地生活电商解决方案+线下”全渠道分销</p>
                                        <a href="http://elife.qianmi.com/czjf.html" target="_blank" role="button" class="btn btn-warning">了解更多 <i class="icon-chevron-right"></i></a>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="friend_sys db_box">
                                        <p class="h2">供货网</p>
                                        <a href="#">
                                            <img src="images/dashboard_col3_icon2.png" alt="供货网" style="width:auto;margin:20px auto;">
                                        </a>
                                        <p style="display:block;height:40px;line-height:20px;overflow:hidden;">供应链全程采用二维码商品标识，不受任何设备限制完整提供商品追踪</p>
                                        <a href="http://http://yunxiao.qianmi.com/solution_o2o.html" target="_blank" role="button" class="btn btn-warning">了解更多 <i class="icon-chevron-right"></i></a>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="friend_sys db_box">
                                        <p class="h2">快消品</p>
                                        <a href="#">
                                            <img src="images/dashboard_col3_icon3.png" alt="快消品" style="width:auto;margin:20px auto;">
                                        </a>
                                        <p style="display:block;height:40px;line-height:20px;overflow:hidden;">构建“电商+店商”的综合性平台,实现快消品行业的区域性垄断经营</p>
                                        <a href="http://yunxiao.qianmi.com/kuaixiaopin.html" target="_blank" role="button" class="btn btn-warning">了解更多 <i class="icon-chevron-right"></i></a>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="friend_sys db_box">
                                        <p class="h2">农产品</p>
                                        <a href="#">
                                            <img src="images/dashboard_col3_icon4.png" alt="农产品" style="width:auto;margin:20px auto;">
                                        </a>
                                        <p style="display:block;height:40px;line-height:20px;overflow:hidden;">以二维码贯穿供应链全程,成就“多向直达”的农产品电商新模式</p>
                                        <a href="http://yunxiao.qianmi.com/ncp.html" target="_blank" role="button" class="btn btn-warning">了解更多 <i class="icon-chevron-right"></i></a>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                    <div class="col-lg-6 col-sm-8">
                        <div class="dashboard_right">
                            <div class="timeline">

                                <ul class="timeline_tab" role="tablist">
                                    <li role="presentation"><a href="#tab2" aria-controls="profile" role="tab" data-toggle="tab">操作日志</a></li>
                                </ul>

                                <div class="tab-cont ent">
                                    <div role="tabpanel" class="tab-pane" id="tab1">
                                        <div class="timeline_cont">
                                            <ul id="opearList">



                                            </ul>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>

            </div>

        </div>
      </div>
    </div>

   <div id="feedback" class="modal fade" role="dialog">
       <div class="modal-dialog">
           <div class="modal-content">
               <div class="modal-header">
                   <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                   <h4 class="modal-title">用户反馈</h4>
               </div>
               <div class="modal-body">
                   <form class="form-horizontal">
                       <div class="form-group">
                           <label class="control-label col-sm-5">反馈内容：</label>
                           <div class="col-sm-1"></div>
                           <div class="col-sm-10">
                               <textarea class="form-control" rows="5"></textarea>
                           </div>
                           <div class="col-sm-1"></div>
                           <div class="col-sm-7">
                               <p class="text-muted">限定输入250字</p>
                           </div>
                       </div>

                   </form>
               </div>
               <div class="modal-footer">
                   <button type="button" class="btn btn-primary">确定</button>
                   <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
               </div>
           </div><!-- /.modal-content -->
       </div><!-- /.modal-dialog -->
   </div><!-- /.modal -->


   <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<%=basePath %>js/bootstrap.min.js"></script>
    <script src="<%=basePath %>js/common.js"></script>

   <script  type="text/javascript">
       function timeObject(obj){
           var date = "/Date(" +notNull(obj)+")/";
           var time = new Date(parseInt(date.replace("/Date(", "").replace(")/", ""), 10));
           var result = time.getFullYear() + "-" + (time.getMonth() + 1 < 10 ? "0" + (time.getMonth() + 1) : time.getMonth() + 1) + "-" + (time.getDate() < 10 ? "0" + time.getDate() : time.getDate()) + " " + (time.getHours() < 10 ? "0" + time.getHours() : time.getHours()) + ":" + (time.getMinutes() < 10 ? "0" + time.getMinutes() : time.getMinutes())+ ":" + (time.getSeconds() < 10 ? "0" + time.getSeconds() : time.getSeconds());
           return result;
       }


       //加法函数
       function accAddInt(arg1, arg2) {
           var r1, r2, m;
           try {
               r1 = arg1.toString().split(".")[1].length;
           }
           catch (e) {
               r1 = 0;
           }
           try {
               r2 = arg2.toString().split(".")[1].length;
           }
           catch (e) {
               r2 = 0;
           }
           m = Math.pow(10, Math.max(r1, r2));

           return (arg1 * m + arg2 * m) / m;
       }
       //加法函数
       function accAdd(arg1, arg2) {
           var r1, r2, m;
           try {
               r1 = arg1.toString().split(".")[1].length;
           }
           catch (e) {
               r1 = 0;
           }
           try {
               r2 = arg2.toString().split(".")[1].length;
           }
           catch (e) {
               r2 = 0;
           }
           m = Math.pow(10, Math.max(r1, r2));

           return ((arg1 * m + arg2 * m) / m).toFixed(2);
       }

       function timeObject2(obj){
           var date = "/Date(" +notNull(obj)+")/";
           var time = new Date(parseInt(date.replace("/Date(", "").replace(")/", ""), 10));
           var result = time.getFullYear() + "-" + (time.getMonth() + 1 < 10 ? "0" + (time.getMonth() + 1) : time.getMonth() + 1) + "-" + (time.getDate() < 10 ? "0" + time.getDate() : time.getDate());
           return result;
       }

       //判断数据是否为空为空返回“”
       function notNull(obj){
           if(obj != null && obj != undefined){
               return obj;
           }else{
               return "";
           }
       }
       $(function(){

           $.ajax({
               url: "initNotice.htm?"+Math.random(),
               context: document.body,
               async:true,
               success: function(data){

                   /* 顶部右侧功能辅助导航 */
                   var message_num = data.count,//信息数量
                           $message = '';
                   $("#orderCount").html("您有"+message_num+"条未处理订单");
                   var html="";
                   if(data.list!=null&&data.list.length!=0){
                       for (var i = 0; i < data.list.length; i++) {
                           if(i<4){
                               html+='<div class="order_tip"> <a href="orderlist.htm?menuId=89&menuParentId=1165&myselfId=947" class="row">  <div class="col-xs-3"> <img alt="" src="images/1132078.png"> </div><div class="col-xs-1"></div><div class="col-xs-20">';
                               html+='<h5>订单号：'+data.list[i].orderNo+'</h5>';
                               html+='<p>'+data.list[i].shippingPerson+' '+data.list[i].shippingMobile+' '+data.list[i].shippingProvince+'省'+data.list[i].shippingCity+'市'+data.list[i].shippingCounty+data.list[i].shippingAddress+'</p>';
                               html+='</div></a></div>';
                           }
                       }
                   }
                   $("#orderList").html(html);
               }});
           $.ajax({
               url: "selectnewcustomer.htm",
               context: document.body,
               async:true,
               success: function(data){
                   var html="";
                   if(data!=null&&data.length!=0){
                       for (var i = 0; i < data.length; i++) {
                           if(data[i].customerUsername==null){
                               return null;
                           }
                           html+="<tr>";
                           html+="<td>"+(i+1)+"</td>";
                           html+="<td>"+data[i].customerUsername+"</td>";
                           html+="<td>"+data[i].customerNickname+"</td>";
                           html+="<td>"+timeObject(data[i].loginTime)+"</td>";
                           html+="</tr>"
                       }
                   }

                   $("#newCustomerInfo").html(html);
               }});
           $.ajax({
               url: "operalogajax.htm?pageSize=8",
               context: document.body,
               async:true,
               success: function(data){
                   var html="";
                   if(data!=null&&data.list.length!=0){
                       for (var i = 0; i < data.list.length; i++) {
                           html+='<li><a href="initoperalog.htm?menuId=1&menuParentId=1927&myselfId=1930" ><div class="timeline_icon"></div><div class="timeline_word">';
                           html+='<h4>'+data.list[i].opName+' 操作了订单</h4>';

                           html+='<p>'+data.list[i].opContent.substr(0,35)+'</p>';
                           html+='<p><i class="icon-time"></i> '+timeObject(data.list[i].opTime)+'</p>';
                           html+='</div></a></li>';
                       }
                   }

                   $("#opearList").html(html);
               }});
           var myDate = new Date();
           var date=myDate.getMonth()+"-"+myDate.getDate()+" "+myDate.getHours()+":"+myDate.getMinutes();
           $(".updateTime").html(date);
           $('.todo a.edit').click(function(){
               $(this).next().find('i').attr('class','icon-circle');
               $(this).next().css({
                   color : '#ccc',
                   textDecoration : 'line-through'
               });
               $(this).addClass('delete').find('i').attr('class','glyphicon glyphicon-trash');
           });
           $('#addTask').click(function(){
               if($('#taskFill').val() == ''){
               }
               else{
                   var taskHTML = '<li><a class="edit pull-right"href="javascript:;"><i class="glyphicon glyphicon-ok"></i></a><p><i class="icon-circle-blank"></i> '+ $("#taskFill").val() +'</p></li>';
                   $('.todo ul').append(taskHTML);
                   $('.todo a.edit').click(function(){
                       $(this).next().find('i').attr('class','icon-circle');
                       $(this).next().css({
                           color : '#ccc',
                           textDecoration : 'line-through'
                       });
                       $(this).addClass('delete').find('i').attr('class','glyphicon glyphicon-trash');
                   });
                   $('#taskFill').val('');
               }
           });
       });

       // 路径配置
       require.config({
           paths:{
               'echarts' : 'http://echarts.baidu.com/build/echarts',
               'echarts/chart/line' : 'http://echarts.baidu.com/build/echarts'
           }
       });
       // 使用
       require(
               [
                   'echarts',
                   'echarts/chart/line'
               ],
               function (ec) {
                   var orderPriceTime="";
                   var priceHtml=[];
                   var dateHtml=[];
                   var countHtml=[];
                   var customerHtml=[];
                   var price=0;
                   var priceNew=0;
                   var weekCount=0;
                   var coutNew=0;
                   var customerCount=0;
                   var customerCountNew=0;
                   $.ajax({
                       url: "querystatistics.htm",
                       context: document.body,
                       async:false,
                       success: function(data){
                          for(var j=data.timeList.length-1;j>=0;j--){
                              for(var i=0;i<data.orderPriceList.length;i++){
                                  if(timeObject2(data.orderPriceList[i].payTime)==data.timeList[j]){
                                      priceHtml.push(data.orderPriceList[i].dayMoney);
                                      dateHtml.push(data.timeList[j]);
                                      price=accAdd(data.orderPriceList[i].dayMoney,price);
                                      if(j==0){
                                          priceNew=data.orderPriceList[i].dayMoney;
                                      }
                                      break;
                                  }else if(i==data.orderPriceList.length-1) {
                                      priceHtml.push(0);
                                      dateHtml.push(data.timeList[j]);
                                  }
                              }
                              for(var i=0;i<data.orderList.length;i++){
                                  if(timeObject2(data.orderList[i].payTime)==data.timeList[j]){
                                      countHtml.push(data.orderList[i].dayCount);
                                      weekCount=accAddInt(data.orderList[i].dayCount,weekCount);
                                      if(j==0){
                                          coutNew=data.orderList[i].dayCount;
                                      }
                                      break;
                                  }else if(i==data.orderList.length-1) {
                                      countHtml.push(0);
                                  }
                              }
                          }
                       }});

                   $.ajax({
                       url: "selectcountbyweek.htm?startTime="+dateHtml[0]+"&endTime="+dateHtml[6],
                       context: document.body,
                       async:false,
                       success: function(data){
                           customerHtml=data;
                       }});

                   customerCount=customerHtml[6];
                   for(var i=0;i<customerHtml.length;i++){
                       customerCountNew=accAddInt(customerHtml[i],customerCountNew);
                   }
                   $("#weekPrice").html("￥"+price);
                   $("#priceNew").html("￥"+priceNew);
                   $("#countNew").html(coutNew);
                   $("#weekCount").html(weekCount);
                   $("#customerCountNew").html(customerCount);
                   $("#customerCount").html(customerCountNew);
                   $(".updateTime").html(dateHtml[6]);
                   // 基于准备好的dom，初始化echarts图表
                   var myChart = ec.init(document.getElementById('spdMap'));
                   var myChart2 = ec.init(document.getElementById('opdMap'));
                   var myChart3 = ec.init(document.getElementById('updMap'));
                   var myChart4 = ec.init(document.getElementById('customerMap'));

                   var option = {
                       color : ['#fff'],
                       tooltip : {
                           trigger: 'axis',
                           axisPointer : {
                               lineStyle : {
                                   color : '#D4396D',
                                   width : 1
                               }
                           }
                       },
                       grid:{
                           borderWidth : 0,
                           x : 10,
                           y : 10,
                           x2 : 10,
                           y2 : 10
                       },
                       toolbox: {
                           show : false
                       },
                       xAxis : [
                           {
                               axisLine : false,
                               splitLine : false,
                               axisTick : false,
                               type : 'category',
                               axisLabel : {
                                   show : false
                               },
                               boundaryGap : false,
                               data :dateHtml
                           }
                       ],
                       yAxis : [
                           {
                               axisLine : false,
                               splitLine : false,
                               type : 'value',
                               axisLabel : {
                                   show : false
                               }
                           }
                       ],
                       series : [
                           {
                               name:'销售额',
                               type:'line',
                               color : '#fff',
                               symbolSize : 5,
                               itemStyle : {
                                   normal : {
                                       lineStyle : {
                                           color : '#fff'
                                       }
                                   }
                               },
                               data:priceHtml
                           }
                       ]
                   };

                   var option2 = {
                       color : ['#fff'],
                       tooltip : {
                           trigger: 'axis',
                           axisPointer : {
                               lineStyle : {
                                   color : '#3B94DC',
                                   width : 1
                               }
                           }
                       },
                       grid:{
                           borderWidth : 0,
                           x : 10,
                           y : 10,
                           x2 : 10,
                           y2 : 10
                       },
                       toolbox: {
                           show : false
                       },
                       xAxis : [
                           {
                               axisLine : false,
                               splitLine : false,
                               axisTick : false,
                               type : 'category',
                               axisLabel : {
                                   show : false
                               },
                               boundaryGap : false,
                               data : dateHtml
                           }
                       ],
                       yAxis : [
                           {
                               axisLine : false,
                               splitLine : false,
                               type : 'value',
                               axisLabel : {
                                   show : false
                               }
                           }
                       ],
                       series : [
                           {
                               name:'订单数',
                               type:'line',
                               color : '#fff',
                               symbolSize : 5,
                               itemStyle : {
                                   normal : {
                                       lineStyle : {
                                           color : '#fff'
                                       }
                                   }
                               },
                               data:countHtml
                           }
                       ]
                   };
                   var option3 = {
                       color : ['#fff'],
                       tooltip : {
                           trigger: 'axis',
                           axisPointer : {
                               lineStyle : {
                                   color : '#5BA85F',
                                   width : 1
                               }
                           }
                       },
                       grid:{
                           borderWidth : 0,
                           x : 10,
                           y : 10,
                           x2 : 10,
                           y2 : 10
                       },
                       toolbox: {
                           show : false
                       },
                       xAxis : [
                           {
                               axisLine : false,
                               splitLine : false,
                               axisTick : false,
                               type : 'category',
                               axisLabel : {
                                   show : false
                               },
                               boundaryGap : false,
                               data :dateHtml
                           }
                       ],
                       yAxis : [
                           {
                               axisLine : false,
                               splitLine : false,
                               type : 'value',
                               axisLabel : {
                                   show : false
                               }
                           }
                       ],
                       series : [
                           {
                               name:'新用户',
                               type:'line',
                               color : '#fff',
                               symbolSize : 5,
                               itemStyle : {
                                   normal : {
                                       lineStyle : {
                                           color : '#fff'
                                       }
                                   }
                               },
                               data:customerHtml
                           }
                       ]
                   };

                   var option4 = {
                       color : ['#C1E4FF'],
                       tooltip : {
                           trigger: 'axis',
                           axisPointer : {
                               lineStyle : {
                                   color : '#E5E5E5',
                                   width : 1
                               }
                           }
                       },
                       grid:{
                           borderWidth : 0,
                           x : 0,
                           y : 0,
                           x2 : 0,
                           y2 : 0
                       },
                       toolbox: {
                           show : false
                       },
                       xAxis : [
                           {
                               axisLine : false,
                               splitLine : false,
                               axisTick : false,
                               type : 'category',
                               axisLabel : {
                                   show : false
                               },
                               boundaryGap : false,
                               data : ['1日','2日','3日','4日','5日','6日','7日','1日','2日','3日','4日','5日','6日','7日','1日','2日','3日','4日','5日','6日','7日','1日','2日','3日','4日','5日','6日','7日','1日','2日','3日','4日','5日','6日','7日','1日','2日','3日','4日','5日','6日','7日']
                           }
                       ],
                       yAxis : [
                           {
                               axisLine : false,
                               splitLine : false,
                               type : 'value',
                               axisLabel : {
                                   show : false
                               }
                           }
                       ],
                       series : [
                           {
                               name:' ',
                               type:'line',
                               color : '#fff',
                               smooth:false,
                               symbolSize : 0,
                               itemStyle : {
                                   normal : {
                                       lineStyle : {
                                           color : '#fff'
                                       },
                                       areaStyle: {type: 'default'}
                                   }
                               },
                               data:[578, 756, 634, 512, 657, 776, 610,578, 656, 634, 512, 657, 876, 510, 657, 776, 510,578, 856, 434, 512,578, 756, 634, 512, 657, 876, 510,578, 856, 634, 512, 657, 876, 510, 657, 776, 510,578, 856, 434, 512]
                           }
                       ]
                   };


                   // 为echarts对象加载数据
                   myChart.setOption(option);
                   myChart2.setOption(option2);
                   myChart3.setOption(option3);
                   myChart4.setOption(option4);


               }
       );


   </script>
</body>
</html>
