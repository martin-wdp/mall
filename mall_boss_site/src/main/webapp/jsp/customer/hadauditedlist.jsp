<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="zh-cn">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>已审核列表</title>

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
        <jsp:include page="../page/left.jsp"></jsp:include>
        <div class="col-lg-20 col-md-19 col-sm-18 main">
          <div class="main_cont">
            <jsp:include page="../page/breadcrumbs.jsp"></jsp:include>
            <h2 class="main_title">${pageNameChild} <small>(共${pageBean.rows }条)</small></h2>

            <div class="common_data p20">
            <div class="filter_area">
                <form role="form" class="form-inline" action="hadauditlist.htm" method="post" id="searchForm">
                 <input type="hidden" value="searchForm" id="formId">
                 <input type="hidden" value="hadauditlist.htm" id="formName">
                <div class="form-group">
                  <div class="input-group">
                    <span class="input-group-addon">公司名称</span>
                    <input type="text" class="form-control" name="companyName" value="${pageBean.objectBean.companyName }">
                  </div>
                </div>
                <div class="form-group">
                  <div class="input-group">
                    <span class="input-group-addon">店铺名称</span>
                    <input type="text" class="form-control" name="storeName" value="${pageBean.objectBean.storeName }">
                  </div>
                </div>
                <div class="form-group">
                  <button type="submit" class="btn btn-primary">搜索</button>
                </div>
              </form>
            </div>
            <div class="data_ctrl_area mb20">
              <div class="data_ctrl_search pull-right"></div>
              <div class="data_ctrl_brns pull-left">
                <button type="button" class="btn btn-info" onclick="publishstore()">
                  <i class="glyphicon glyphicon-minus-sign"></i> 处罚
                </button>
                <button type="button" class="btn btn-info" onclick="delstorelist()">
                  <i class="glyphicon glyphicon-trash"></i> 删除
                </button>
              </div>
              <div class="clr"></div>
            </div>

 		<form id="delallform" method="post" action="delnewallstore.htm" method="post" >
             <input type="hidden" name="CSRFToken" value="${token}" id="CSRFToken">

            <table class="table table-striped table-hover">
            <thead>
            <tr>
              <th width="10"><input type="checkbox" onclick="allunchecked(this,'storeId')"></th>
              <th>公司名称</th>
              <th>店铺名称</th>
              <th>法人姓名</th>
              <th>公司电话</th>
              <th>注册资本(万元)</th>
              <th>是否通过审核</th>
              <th>店铺街排序</th>
              <th>是否显示店铺街</th>
              <th class="w100">申请时间</th>
              <th class="w100">到期时间</th>
              <th width="150">操作</th>
            </tr>
            </thead>
            <tbody>
             <c:forEach items="${pageBean.list}" var="store" varStatus="i">
		    	<fmt:formatDate value="${store.createTime }" pattern="yyyy-MM-dd HH:mm" var="auditTime" />
		    	<fmt:formatDate value="${store.expiryTime }" pattern="yyyy-MM-dd HH:mm" var="expiryTime" />
		    	<tr class="tableListTr">
		    		<td><input type="checkbox" name="storeId"  value="${store.storeId }"></td>
		    		<td>${store.companyName}</td>
		    		<td>${store.storeName}</td>
		    		<td>${store.bussLegalName}</td>
		    		<td>${store.companyTel}</td>
		    		<td>${store.companyCapital}</td>
                    <c:if test="${store.checkStatus=='1' && store.isSubmit=='1'}">
                        <td>
                            <span class="label label-success">是</span>
                        </td>
                    </c:if>
                    <c:if test="${store.checkStatus=='0' && store.isSubmit=='0'}">
                        <td>
                            <span class="label label-default">否</span>
                        </td>
                    </c:if>
                    <td>${store.storeStreetort}</td>

                    <c:if test="${store.storeStreetIsShow=='0'}">
                        <td>
                            <span class="label label-success">是</span>
                        </td>
                    </c:if>

                    <c:if test="${store.storeStreetIsShow=='1' || store.storeStreetIsShow=='' || store.storeStreetIsShow==null}">
                        <td>
                            <span class="label label-default">否</span>
                        </td>
                    </c:if>


		    		<td>${auditTime}</td>
		    		<td>${expiryTime}</td>
		    		<td>
		                <div class="btn-group">
		                  <button type="button" class="btn btn-default" onclick="queryStore(${store.storeId})">查看详情</button>
		                  <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
		                    <span class="caret"></span>
		                    <span class="sr-only">Toggle Dropdown</span>
		                  </button>
		                  <ul class="dropdown-menu" role="menu">
                              <c:if test="${store.checkStatus=='1' && store.isSubmit=='1'}">
                                  <li><a href="javascript:;" onclick="updatestore('${store.storeId }')">设置</a></li>
                                  <li><a href="javascript:;" onclick="querystorecate(${store.storeId})">签约信息</a></li>
                                  <li><a href="javascript:;" onclick="updatePay('${store.storeId }','${store.billingCycle}')">结算信息</a></li>
                                  <li><a href="javascript:;" onclick="updateStoreValidTime('${store.storeId }','<fmt:formatDate value='${store.expiryTime }' type="both"/>');">修改期限</a></li>
                              </c:if>
                              <c:if test="${store.checkStatus=='0' && store.isSubmit=='0'}">
                                  <li><a href="javascript:;" onclick="refuseReason('${store.refuseContent}')">打回原因</a></li>
                              </c:if>
		                  </ul>
		                </div>
		             </td>
		    	</tr>
		    </c:forEach>
           
            </tbody>
            </table>
           </form>

            <div class="table_foot">
        		<c:import url="../page/searchPag.jsp">
			     	<c:param name="pageBean" value="${pageBean }"/>
			    	<c:param name="path" value="../"></c:param>
				</c:import>
            </div>
            </div>

            </div>

          </div>
        </div>
      </div>
    </div>

    <!-- Modal -->
    <div class="modal fade" id="shopDetails"  role="dialog">
      <div class="modal-dialog modal-lg">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">店铺详细信息</h4>
          </div>
          <div class="modal-body">
             <iframe id="mainFrame" frameborder="0" style="width:100%;" src=""></iframe> 
          </div>
        </div>
      </div>
    </div>

    <!-- Modal -->
    <div class="modal fade" id="signInfo"  role="dialog">
      <div class="modal-dialog modal-lg">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">签约信息</h4>
          </div>
          <div class="modal-body">
           <iframe id="cateFrame" frameborder="0" style="width:100%;" src="" scrolling="no"></iframe> 
          </div>
          <div class="modal-footer">
            <!-- <button type="button" class="btn btn-primary">保存</button>-->
            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
          </div>
        </div>
      </div>
    </div>

   <%--设置商铺在店铺街是否显示 以及排序--%>
   <div class="modal fade" id="set_store_street"  role="dialog">
       <div class="modal-dialog modal-lg">
           <div class="modal-content">
               <div class="modal-header">
                   <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                   <h4 class="modal-title">修改店铺状态</h4>
               </div>
               <form  method="post" class="save_store">
                   <input type="hidden" name="storeId" id="storeId_update" value=""/>
                   <input type="hidden" name="isShow" id="is_Show" value=""/>
                   <div class="modal-body">
                       <div class="form-group">
                           <label class="control-label">排序：</label>
                           <input maxlength="10" style="width: 150px;" onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" class="set_tore" name="setTore" value=""/>
                       </div>
                       <div class="form-group">
                           <label class="control-label">是否在店铺街显示：</label>
                           <label class="radio-inline">
                               <input type="radio" id="storeStreetIsShow1" value="0" class="is_Show0" name="show" >是
                           </label>
                           <label class="radio-inline">
                               <input type="radio" id="storeStreetIsShow2" value="1" class="is_Show1" name="show" >否
                           </label>
                       </div>
                       <span id="set_tore_tip" style="color: red;"></span>
                   </div>
               </form>
               <div class="modal-footer">
                   <button type="button" class="btn btn-primary"onclick="save_store()">保存</button>
                   <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
               </div>
           </div>
       </div>
   </div>

    <!-- Modal -->
    <div class="modal fade" id="settleInfo"  role="dialog">
      <div class="modal-dialog modal-lg">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">结算信息</h4>
          </div>
      <form id="pay_form" method="post">
         <input type="hidden" id="storeId" name="storeId" value=""/>
         <input name="billingCycle" id="aTime" type="hidden" />
          <div class="modal-body">
            <div class="form-inline mb20">
              <div class="form-group">
                <label class="control-label">添加支付方式</label>
                <select class="form-control" id="payway">
                </select>
              </div>
              <div class="form-group">
                <button type="button" class="btn btn-default" id="addPay">添加</button>
              </div>
            </div>
            <table class="table table-striped table-hover table-bordered">
              <thead>
              <tr>
                <th>支付方式</th>
                <th>平台扣点</th>
                <th>佣金</th>
                <th>操作</th>
              </tr>
              </thead>
              <tbody id="pay_tb">
             <!--  <tr>
                <td>微信支付</td>
                <td>
                  <div class="form-inline">
                    <div class="form-group">
                      <input type="text" class="form-control" value="0.1">
                      <label class="control-label">%</label>
                    </div>
                  </div>
                </td>
                <td>
                  <div class="form-inline">
                    <div class="form-group">
                      <input type="text" class="form-control" value="0.1">
                      <label class="control-label">%</label>
                    </div>
                  </div>
                </td>
                <td><button type="button" class="btn btn-default">删除</button></td>
              </tr> -->
              </tbody>
            </table>
            <div class="form-horizontal">
              <div class="form-group">
                <label class="control-label col-sm-4">添加结算周期：</label>
                <div class="col-sm-1"><label class="control-label">每月</label></div>
                <div class="col-sm-15">
                  <div class="pt5">
                    <div class="tags"></div>
                  </div>
                </div>
              </div>
            </div>
          </div>
       </form>
          <div class="modal-footer">
            <button type="button" class="btn btn-primary" id="savepay">保存</button>
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
          </div>
        </div>
      </div>
    </div>

    <!-- Modal -->
    <div class="modal fade" id="changeData"  role="dialog">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">修改期限</h4>
          </div>
          <div class="modal-body">
            <div class="form-horizontal">
              <div class="form-group">
                <label class="control-label col-sm-5">店铺期限：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-12">
                  <div class="input-group date form_datetime">
                    <input class="form-control" type="text" value="" name="expiryTime" id="expiryTime" readonly >
                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-primary" onclick="savetime()">保存</button>
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
          </div>
        </div>
      </div>
    </div>

    <!-- Modal -->
    <div class="modal fade" id="punishment"  role="dialog">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">商家处罚</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal" id="punishForm" method="post">
            <input type="hidden" name="thirdId" id="up_id"/>
              <div class="form-group">
                <label class="control-label col-sm-5">处罚原因：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-14">
                  <textarea class="form-control required specstr" rows="5" name="punishReason" id="punishReason"></textarea>
                </div>
              </div>
              <div class="form-group">
                <label class="control-label col-sm-5">处罚规则：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-6">
                  <select class="form-control" id="punishmentChoose" name="rule">
                    <option value="">请选择</option>
                    <c:forEach items="${punishList}" var="customerPunish">
                    <option value="${customerPunish.rule }">${customerPunish.remark}</option>
                    </c:forEach>
                  </select>
                </div>
              </div>
              <div class="form-group punishment_1" style="display: none">
                <label class="control-label col-sm-5">扣金额：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-6">
                  <input type="text" class="form-control" name="reduceMoney" id="reduceMoney">
                </div>
                <div class="col-sm-6">
                  <label class="control-label" id="moneylabel">总金额：</label>
                </div>
              </div>
              <div class="form-group punishment_2" style="display: none;">
                <label class="control-label col-sm-5">开始时间：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-12">
                    <div class="input-group date form_datetime" id="stapicker">
                    <input class="form-control" type="text" value="" name="limitSstime" id="staTime" readonly>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                  </div>
                </div>
              </div>
              <div class="form-group punishment_2" style="display: none;">
                <label class="control-label col-sm-5">结束时间：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-12">
                    <div class="input-group date form_datetime endpicker" id="enpicker">
                    <input class="form-control" type="text" value="" name="limitEetime" id="enTime" readonly>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                  </div>
                </div>
              </div>
              <div class="form-group punishment_3" style="display: none;">
                <label class="control-label col-sm-5">关店开始时间：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-12">
                    <div class="input-group date form_datetime" id="startpicker">
                    <input class="form-control" type="text" value=""  name="closeSstime" id="startTime" readonly>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                  </div>
                </div>
              </div>
              <div class="form-group punishment_3" style="display: none;">
                <label class="control-label col-sm-5">关店结束时间：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-12">
                    <div class="input-group date form_datetime" id="endpicker">
                    <input class="form-control" type="text" value="" name="closeEetime" id="endTime"  readonly>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                  </div>
                </div>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-primary" onclick="savepublish()">保存</button>
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
          </div>
        </div>
      </div>
    </div>

   <!--Model 打回原因-->
   <div class="modal fade" id="refuseReason"  role="dialog">
       <div class="modal-dialog">
           <div class="modal-content">
               <div class="modal-header">
                   <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                   <h4 class="modal-title">打回原因</h4>
               </div>
               <div class="modal-body">
                   <div class="form-horizontal">
                       <div class="form-group" style="margin: 15px;">
                           <span id="refuseContent" style="margin-left: 30px;font-size: 15px;"></span>
                       </div>
                   </div>
               </div>
               <div class="modal-footer">
                   <button type="button" class="btn btn-default" data-dismiss="modal">确定</button>
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
    <script src="<%=basePath %>js/tabControl.js"></script>
    <script src="<%=basePath %>js/common.js"></script>
    <script src="<%=basePath %>js/common/common_alert.js"></script>
    <script src="<%=basePath %>js/common/common_checked.js"></script>
    <script src="<%=basePath %>js/customer/auditlist.js"></script>
    <script>
      $(function(){
        /* 表单项的值点击后转换为可编辑状态 */
        $('.form_value').click(function(){
          var formItem = $(this);
          if(!$('.form_btns').is(':visible')) {
            formItem.parent().addClass('form_open');
            $('.form_btns').show();
            $('.form_btns').css({
              'left': formItem.next().offset().left + 70 + 'px',
              'top': formItem.next().offset().top - 30 + 'px'
            });
            $('.form_sure,.form_cancel').click(function () {
              $('.form_btns').hide();
              formItem.parent().removeClass('form_open');
            });
          }
        });

        $('#punishmentChoose').change(function(){
          var thirdId=$("input[name='storeId']:checked").val();

          $("#up_id").val(thirdId);
          if($(this).val() == ''){
            $('.punishment_1,.punishment_2').hide();
          }
          else if($(this).val() == 2){
            $('.punishment_1,.punishment_2').hide();
            $('.punishment_1').show();
            $("#reduceMoney").addClass("required isNoInteger");
            $("#staTime").removeClass("required error");
            $("#enTime").removeClass("required error");
            $("#moneylabel span").html("");	
			$.post("queryStoreBalanceByThirdId.htm?thirdId="+thirdId,function(data){
				if(data!=null){
					$("#moneylabel").html("总金额：<span id='moneyspan'>"+data.storeBalance+"</span>");
				}
			});
          }
          else if($(this).val() == 3){
            $('.punishment_1,.punishment_2').hide();
            $('.punishment_2').show();
            $("#reduceMoney").removeClass("required isNoInteger");
            $("#staTime").addClass("required");
            $("#enTime").addClass("required");
          }
        });
        /* 下面是表单里面的日期时间选择相关的 */
        $('.form_datetime').datetimepicker({
            format: 'yyyy-mm-dd hh:ii:00',
          weekStart : 1,
          autoclose : true,
          language : 'zh-CN',
          pickerPosition : 'bottom-left',
          todayBtn : true,
          viewSelect : 'hour'
        });
        $('.form_date').datetimepicker({
          format : 'yyyy-mm-dd',
          weekStart : 1,
          autoclose : true,
          language : 'zh-CN',
          pickerPosition : 'bottom-left',
          minView : 2,
          todayBtn : true
        });

          /* 下面是表单里面的日期时间选择相关的 END */


          /*下面是时间选择器开始时间不能大于结束时间设置  START*/
          //关店的开始结束时间配置
          var startTime = $("#startTime").val();
          var endTime = $("#endTime").val();
          $('#endpicker').datetimepicker('setStartDate', startTime);
          $('#startpicker').datetimepicker('setEndDate', endTime);
          $('#endpicker')
                  .datetimepicker()
                  .on('show', function (ev) {
                      startTime = $("#startTime").val();
                      endTime = $("#endTime").val();
                      $('#endpicker').datetimepicker('setStartDate', startTime);
                      $('#startpicker').datetimepicker('setEndDate', endTime);
                  });
          $('#startpicker')
                  .datetimepicker()
                  .on('show', function (ev) {
                      endTime = $("#endTime").val();
                      startTime = $("#startTime").val();
                      $('#startpicker').datetimepicker('setEndDate', endTime);
                      $('#endpicker').datetimepicker('setStartDate', startTime);
                  });

          // 开始时间的配置
          var staTime = $("#staTime").val();
          var enTime = $("#enTime").val();
          $('#enpicker').datetimepicker('setStartDate', staTime);
          $('#stapicker').datetimepicker('setEndDate', enTime);
          $('#enpicker')
                  .datetimepicker()
                  .on('show', function (ev) {
                      startTime = $("#staTime").val();
                      endTime = $("#enTime").val();
                      $('#enpicker').datetimepicker('setStartDate', staTime);
                      $('#stapicker').datetimepicker('setEndDate', enTime);
                  });
          $('#stapicker')
                  .datetimepicker()
                  .on('show', function (ev) {
                      endTime = $("#enTime").val();
                      startTime = $("#staTime").val();
                      $('#stapicker').datetimepicker('setEndDate', enTime);
                      $('#enpicker').datetimepicker('setStartDate', staTime);
                  });


          /*下面是时间选择器开始时间不能大于结束时间设置  END*/

        /**************  下面是关键字标签选择  ********************/
        /**************  获取所有标签值的方法 var v = $("#tag").getTabVals(); **************/
       // $('.tags').tabControl({
         // regularEx: '^[A-Z]+$',
          //maxTabCount:5/*最大标签数*/,
          //tabW:80/*标签最大长度*/,
          //tabH : 25,errorcontent:'请输入1-31之间的正整数',regularEx:/^([1-9]|[1-2]\d|3[0-1])$/ /*匹配正则*/});
      }); 
      
      //查看店铺信息
      function queryStore(storeId){
    	  $("#mainFrame").attr("src",'sellerinfo.htm?CSRFToken=${token}&storeId='+storeId);
    	  $('#shopDetails').modal('show');
    	  $('#mainFrame').css('minHeight','950px');
      }
      
      function publishstore(){
    	  if(checkselect("storeId",1)){
	    	  $('#punishment').modal('show');
    	  }else{
    		  return ;
    	  }
      }
      
      function savepublish(){
    	  if($("#punishForm").valid()){
	    	  var rule=$("#punishmentChoose").val();
			    if(rule==''){
                    $(".error-tips").remove();
                    $("#punishForm").after("<p class='error-tips' style='color:#ff0000;text-indent:100px;'>请选择处罚规则</p>");
				  return;
				} else {
				  $(".error-tips").remove();
			    }		
	    	  if(rule==2){
	    		  	var sumMoney=parseInt($("#moneyspan").html());
					var reduceMoney=parseInt($("#reduceMoney").val());
					if(reduceMoney>sumMoney){
						$("#punishForm").after("<p class='error-tips' style='color:#ff0000;text-indent:100px;'>扣减的金额不能大于商家的总金额</p>");
						return;
					} else {
						$(".error-tips").remove();
					};		
	    	  }
              if(rule==3){
                  $(".error-tips").remove();
              }
	    	  $.ajax({
	    		  url:'punishShop.htm?CSRFToken=${token}',
	    		  type:"post",
	    		  data:$("#punishForm").serialize(),
			        success: function (data) {
			            location.reload();
			        }
	    	  }); 
    	  }
      }
      
      //删除店铺
      function delstorelist(){
    	  showAjaxDeleteBatchConfirmAlert("delallform","storeId");
      }
      
      //根据店铺id查询签约分类
      function querystorecate(storeId){
    	  $("#cateFrame").attr("src",'newupdatesellerinfo.htm?CSRFToken=${token}&storeId='+storeId);
    	  $('#signInfo').modal('show');
    	  $('#cateFrame').css('minHeight','520px');
    	  $('#cateFrame').css('minWidth','860px');
    	  
      }
      
      var goodsBelo=''
      function updateStoreValidTime(glo,time){
  	    goodsBelo = glo;
  		$("#expiryTime").val(time);
  		$('#changeData').modal('show');
     }

      function savetime(){
    	  location.href='updateStoreValidTime.htm?goodsBelo='+ goodsBelo+'&endTime='+$("#expiryTime").val();
      }
      //打回原因
      function refuseReason(refuseContent){
          $("#refuseContent").html(refuseContent);
          $('#refuseReason').modal('show');

      }
      
      /* function updatePay(oId,bCycle){
    	  $.ajax({
		         type: 'post',
		         url:'findpayAll.htm?CSRFToken='+$("#CSRFToken").val(),
		         async:false,
		         success: function(data) {
		        	 var ss = "";
		        	 for(var i = 0 ;i< data.length;i++){
		        		 ss+='<option value="'+data[i].payId+'">'+data[i].payName+'</option>';
		        	 }
		        	 $("#payway").html(ss);
		         }
			 }); 
    	  $.ajax({
    			url:'selectDeduByStoreId.htm?storeId='+oId+"&CSRFToken="+$("#hi_token").val(), 
    			success:function(data) {
    				var html = '';
    				for(var i=0;i<data.length;i++) {
    					html += '<tr><input type="hidden" name="payIds" value="'+data[i].payId+'" /><td>'+data[i].payName+'</td><td><div class="form-inline">',
                        html +='<div class="form-group"><input type="text" class="form-control" name="deduction" value='+data[i].deduction+'><label class="control-label">%</label></div></div></td>',
                        html +='<td><div class="form-inline"><div class="form-group"><input type="text" class="form-control" name="brokerage" value='+data[i].brokerage+'><label class="control-label">%</label></td><td><button type="button" class="btn btn-default" onclick="delpay(this)">删除</button></td></tr>';
    				}
    				$("#pay_tb").html(html);
    			}
    		});
    		$("#payTime").val(bCycle);
    		$("#aTime").val(bCycle);
    		$("#storeId").val(oId);
    	    $("#pageNo2").val($("#list_pageno").val());
    		$("#audit_from").attr("action","updateClassifyPay.htm");
    		$("#dialog-audit-confirm1").dialog("open");
    	    $('#settleInfo').modal('show');
      }
      
      function delpay(obj){
    		$(obj).parent().parent().remove();
    	} */
    </script>
  </body>
</html>
