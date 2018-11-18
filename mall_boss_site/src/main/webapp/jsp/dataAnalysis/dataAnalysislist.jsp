
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>商家销售分析</title>

    <!-- Bootstrap -->
    <link href="<%=basePath %>css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath %>css/font-awesome.min.css">
    <link href="<%=basePath %>iconfont/iconfont.css" rel="stylesheet">
    <link href="<%=basePath %>css/summernote.css" rel="stylesheet">
    <link href="<%=basePath %>css/bootstrap-select.min.css" rel="stylesheet">
    <link href="<%=basePath %>css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <link href="<%=basePath %>/css/select2.min.css" rel="stylesheet">
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

                <h2 class="main_title">${pageNameChild}<small>(共${pb.rows }条)</small></h2>

                <div class="common_data p20">
                    <div class="filter_area">
                        <form role="form" class="form-inline" id="searchForm" method="post" action="showThirdInfo.htm">
                            <input type="hidden" value="searchForm" id="formId">
                            <input type="hidden" value="showThirdInfo.htm" id="formName">
                            <input type="hidden" name="CSRFToken" id="CSRFToken" value="${token}">

                            <input type="hidden" name="condition" id="condition" value="1"/>

                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">商铺名称</span>
                                    <input type="text" class="form-control"  name="searchText" value="${selectBean.searchText }" id="searchText">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group date form_date" id="startpicker">
                                    <span class="input-group-addon">开始时间</span>
                                    <input class="form-control" type="text" style="width:110px;" readonly
                                           value="${startTime }" name="startTime" id="startTime">

                                      <span class="input-group-addon"><span
                                              class="glyphicon glyphicon-calendar"></span></span>

                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group date form_date" id="endpicker">
                                    <span class="input-group-addon">结束时间</span>
                                    <input class="form-control" style="width:110px;" type="text" value="${endTime }"
                                           name="endTime" id="endTime" readonly>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <a href="javascript:;" class="btn btn-primary" id="search-button1">搜索</a>
                            </div>
                        </form>
                    </div>

                    <table class="table table-striped table-hover">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>商铺名称</th>
                            <th>交易额</th>
                            <th>成功订单量</th>
                            <th>商品销售量</th>
                            <th>收藏量</th>
                            <th>转化率</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="dataUtil" items="${pb.list}" varStatus="status">
                        <tr>
                            <td >
                                    ${(status.count)}
                            </td>
                            <td >${dataUtil.storeName}</td>
                            <td ><c:if test="${dataUtil.sumPrice==null }">0.00</c:if>${dataUtil.sumPrice }</td>
                            <td ><c:if test="${dataUtil.orderCount==null }">0</c:if>${dataUtil.orderCount }</td>
                            <td ><c:if test="${dataUtil.sumPro==null }">0</c:if>${dataUtil.sumPro }</td>
                            <td >
                                <c:if test="${dataUtil.followCount==null }">0</c:if> ${dataUtil.followCount }
                            </td>

                            <td c>
                                <fmt:formatNumber value="${dataUtil.takeRates }" pattern="0.00" var="fTakeRates"/>
                                <c:if test="${fTakeRates==null }">0.00</c:if>${fTakeRates }%
                            </td>
                        </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                    <c:import url="../page/searchPag.jsp">
                        <c:param name="pageBean" value="${pb}"/>
                    </c:import>

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
        $('.zhekoulv').popover({
            content : '有效值0~1,如果输入0.85,表示该会员等级以销售价85折购买商品',
            trigger : 'hover'
        });
        $('.morendengji').popover({
            content : '如果选择"是",顾客注册会员时,初始等级为当前等级',
            trigger : 'hover'
        });
        $('.suoxujifen').popover({
            content : '按积分升级时,会员积分达到此标准后会自动升级为当前等级',
            trigger : 'hover'
        });

        /* 高级搜索 */
        $('.advanced_search').popover({
            html : true,
            title : '高级搜索',
            content : $('.advanced_search_cont').html(),
            trigger : 'click',
            placement : 'bottom'
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
        /*下面是时间选择器开始时间不能大于结束时间设置  END*/
        //搜索
        $("#search-button1").click(function(){

            var d1=$("#startTime").val();
            var d2=$("#endTime").val();
            if(date_day(d1,d2)==false){
                showTipAlert("开始时间不能大于结束时间,请重新选择!");
                return false;
            }
            else{
                $("#searchForm").submit();
            }



        });

    });
</script>
</body>
</html>
