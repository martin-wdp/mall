<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
  <title>已处理投诉列表</title>

  <!-- Bootstrap -->
  <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="<%=basePath%>css/font-awesome.min.css">
  <link href="<%=basePath%>iconfont/iconfont.css" rel="stylesheet">
  <link href="<%=basePath%>css/summernote.css" rel="stylesheet">
  <link href="<%=basePath%>css/bootstrap-select.min.css" rel="stylesheet">
  <link href="<%=basePath %>/css/select2.min.css" rel="stylesheet">
  <link href="<%=basePath%>css/style.css" rel="stylesheet">

  <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
  <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>
<body>
<input type="hidden" name="CSRFToken" value="${token}">
<!-- 引用头部 -->
<jsp:include page="../page/header.jsp"></jsp:include>
<div class="page_body container-fluid">
  <div class="row">
    <!-- 引用左侧 -->
    <jsp:include page="../page/left.jsp"></jsp:include>
    <input type="hidden" value="ordercomplainhadbackList" id="formId">
    <input type="hidden" value="complainhad.htm" id="formName">
    <div class="col-lg-20 col-md-19 col-sm-18 main">
      <div class="main_cont">
        <jsp:include page="../page/breadcrumbs.jsp"></jsp:include>

        <h2 class="main_title">${pageNameChild} <small>(共${pageBean.rows }条)</small></h2>

        <div class="common_data p20">
          <div class="filter_area">
            <form role="form" class="form-inline" id="search_fromhad" method="post">
              <div class="form-group">
                <div class="input-group">
                  <span class="input-group-addon">订单编号</span>
                  <input type="text" class="form-control search_text" value="${orderCB.orderNo }">
                </div>
              </div>
              <div class="form-group">
                <select class="form-control" id="search_select" value="${orderCB.complainType}">
                  <option value="">选择投诉类型</option>
                  <option value="产品相关"  <c:if test="${'产品相关'==orderCB.complainType}">selected="selected"</c:if>>产品相关</option>
                  <option value="网站相关" <c:if test="${'网站相关'==orderCB.complainType}">selected="selected"</c:if>>网站相关</option>
                  <option value="预约相关" <c:if test="${'预约相关'==orderCB.complainType}">selected="selected"</c:if>>预约相关</option>
                  <option value="物流相关" <c:if test="${'物流相关'==orderCB.complainType}">selected="selected"</c:if>>物流相关</option>
                  <option value="价格相关" <c:if test="${'价格相关'==orderCB.complainType}">selected="selected"</c:if>>价格相关</option>
                  <option value="财务相关" <c:if test="${'财务相关'==orderCB.complainType}">selected="selected"</c:if>>财务相关</option>
                  <option value="售后相关" <c:if test="${'售后相关'==orderCB.complainType}">selected="selected"</c:if>>售后相关</option>
                  <option value="服务相关" <c:if test="${'服务相关'==orderCB.complainType}">selected="selected"</c:if>>服务相关</option>
                  <option value="活动相关" <c:if test="${'活动相关'==orderCB.complainType}">selected="selected"</c:if>>活动相关</option>
                  <option value="其他方面" <c:if test="${'其他方面'==orderCB.complainType}">selected="selected"</c:if>>其他方面</option>
                </select>
              </div>
              <div class="form-group">
                <button id="search_comhad" class="btn btn-primary">搜索</button>
              </div>
            </form>
          </div>
          <!--删除  暂时不实现
          <div class="data_ctrl_area mb20">
            <div class="data_ctrl_search pull-right"></div>
            <div class="data_ctrl_brns pull-left">
              <button type="button" class="btn btn-info">
                <i class="glyphicon glyphicon-trash"></i> 删除
              </button>
            </div>
            <div class="clr"></div>
          </div>-->
          <form action="complainhad.htm?CSRFToken=${token }" method="post" id="ordercomplainhadbackList">
          <table class="table table-striped table-hover">
            <thead>
            <tr>
              <th width="10"><input type="checkbox" onclick="allunchecked(this,'complainId');"></th>
              <th>投诉类型</th>
              <th>订单编号</th>
              <th width="23%">投诉内容</th>
              <th>回复人</th>
              <th width="23%">回复内容</th>
              <th class="w100">投诉时间</th>
              <th width="100">操作</th>
            </tr> 
            </thead>
            <tbody>
            <c:forEach items="${pageBean.list}" var="orderCB" varStatus="i">
              <fmt:formatDate value="${orderCB.complainTime }" pattern="yyyy-MM-dd HH:mm:ss" var="complainTime" />
              <tr>
                <!--<td class="tc">${i.count}</td>  -->
                <td><input type="checkbox" name=complainId  value="${orderCB.complainId }"></td>
               <!-- <td><input type="image" src="images/finder_drop_arrow.gif" id="btn_add" onclick="expandDiv(${orderCB.complainId },this,'selectcombyid.htm?CSRFToken=${token}&complainId',300)"></td>-->
                <td title="${orderCB.complainType }">
                    ${orderCB.complainType }
                </td>
                <td title="${orderCB.orderNo }">${orderCB.orderNo }
                </td>
                <td title="${orderCB.complainContext}" style="text-align:left;">
                  <c:if test="${fn:length(orderCB.complainContext)>35 }">
                    ${fn:substring(orderCB.complainContext,0,35) }...
                  </c:if>
                  <c:if test="${fn:length(orderCB.complainContext)<=35 }">
                    ${orderCB.complainContext}
                  </c:if>
                </td>
                <td>${orderCB.replayUsername }</td>
                <td title="${orderCB.replayContext}" style="text-align:left;">
                  <c:if test="${fn:length(orderCB.replayContext)>35 }">
                    ${fn:substring(orderCB.replayContext,0,35) }...
                  </c:if>
                  <c:if test="${fn:length(orderCB.replayContext)<=35 }">
                    ${orderCB.replayContext}
                  </c:if>
                </td>
                <td>${complainTime}</td>
                <td>
                  <button type="button" class="btn btn-default" onclick="replyComplaintsDetail('${orderCB.complainId }','${orderCB.orderNo }','${complainTime}','${orderCB.complainType }','${orderCB.complainContext}','${orderCB.replayContext}')">查看</button>
                </td>
              </tr>
              <!--<tr id="expand${orderCB.complainId }">
                <td colspan="9"></td>
              </tr>-->

            </c:forEach>

            </tbody>
          </table>
          </form>
          <div class="table_foot">
            <c:import url="../page/searchPag.jsp">
              <c:param name="pageBean" value="${pb }"/>
            </c:import>
          </div>

        </div>

      </div>
    </div>
  </div>
</div>


<!-- 查看弹出层开始 -->
<div class="modal fade" id="replyComplaintsDetail"  role="dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">查看详情</h4>
      </div>
      <div class="modal-body">
        <form class="form-horizontal">
          <div class="form-group">
            <label class="control-label col-sm-5">涉及订单：</label>
            <div class="col-sm-1"></div>
            <div class="col-sm-10">
              <label class="control-label" id="replayOrderNoDetail"></label>
            </div>
          </div>
          <div class="form-group">
            <label class="control-label col-sm-5">投诉时间：</label>
            <div class="col-sm-1"></div>
            <div class="col-sm-10">
              <label class="control-label" id="replaycomplainTimeDetail"></label>
            </div>
          </div>
          <div class="form-group">
            <label class="control-label col-sm-5">投诉类型：</label>
            <div class="col-sm-1"></div>
            <div class="col-sm-10">
              <label class="control-label" id="replaycomplainTypeDetail"></label>
            </div>
          </div>
          <div class="form-group">
            <label class="control-label col-sm-5">投诉内容：</label>
            <div class="col-sm-1"></div>
            <div class="col-sm-10">
              <label class="control-label" id="replaycomplainContextDetail" style="text-align:left"></label>
            </div>
          </div>
          <div class="form-group">
            <label class="control-label col-sm-5">回复内容：</label>
            <div class="col-sm-1"></div>
            <div class="col-sm-10">
              <label class="control-label" id="replayContext"></label>
            </div>
          </div>
          <!--<div class="form-group">
            <label class="control-label col-sm-5">回复：</label>
            <div class="col-sm-1"></div>
            <div class="col-sm-15">
              <input type="hidden" name="replayUsername" value="ningpai"/>
              <textarea class="form-control" rows="5" name="replayContext"></textarea><br/>
              <span id="treplay" style="color: red;"></span>
            </div>
          </div>-->
        </form>
      </div>
      <div class="modal-footer">
        <!--<button type="button" class="btn btn-primary" id="okSubmit">确定提交</button>-->
        <button type="button" class="btn btn-default" data-dismiss="modal">确定</button>
      </div>
    </div>
  </div>
</div>
<!-- 查看弹出层结束 -->


<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=basePath%>js/bootstrap.min.js"></script>
<script src="<%=basePath%>js/summernote.min.js"></script>
<script src="<%=basePath%>js/language/summernote-zh-CN.js"></script>
<script src="<%=basePath%>js/bootstrap-select.min.js"></script>
<script src="<%=basePath%>js/common.js"></script>
<script src="<%=basePath%>js/complain.js"></script>
<script src="<%=basePath %>js/common/common_alert.js"></script>
<script src="<%=basePath %>js/common/common_checked.js"></script>
<script src="<%=basePath %>/js/select2.min.js"></script>
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

    /* 为选定的select下拉菜单开启搜索提示 */
    $('select[data-live-search="true"]').select2();
    /* 为选定的select下拉菜单开启搜索提示 END */

    /* 富文本编辑框 */
    $('.summernote').summernote({
      height: 300,
      tabsize: 2,
      lang: 'zh-CN'
    });

    /* 选择规格值 */
    $('.spec_set input').change(function(){
      if($(this).is(':checked')){
        $(this).parent().parent().next().slideDown('fast');
      }
      else {
        $(this).parent().parent().next().slideUp('fast');
      }
    });

    /* 下面是表单里面的填写项提示相关的 */
    $('.xiaoshoujia').popover({
      content : '此价格只用于显示，以商品定价为商品销售价',
      trigger : 'hover'
    });
  });
</script>
</body>
</html>
