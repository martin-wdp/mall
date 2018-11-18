<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>促销分组</title>


    <!-- Bootstrap -->
    <link href="<%=basePath %>css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath %>css/font-awesome.min.css">
    <link href="<%=basePath %>iconfont/iconfont.css" rel="stylesheet">
    <link href="<%=basePath %>css/summernote.css" rel="stylesheet">
    <link href="<%=basePath %>css/bootstrap-select.min.css" rel="stylesheet">
    <link href="<%=basePath %>css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <link href="<%=basePath %>css/zTreeStyle/zTreeStyle.css" rel="stylesheet">
    <link href="<%=basePath %>/css/select2.min.css" rel="stylesheet">
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
<jsp:include page="../../page/header.jsp"></jsp:include>

<div class="page_body container-fluid">
    <div class="row">

        <jsp:include page="../../page/left.jsp"></jsp:include>
        <input type="hidden" value="0" id="checkStatus">
        <input type="hidden" value="selectempForm" id="formId"/>
        <input type="hidden" value="selectemp.htm" id="formName"/>
        <div class="col-lg-20 col-md-19 col-sm-18 main">
            <div class="main_cont">
                <jsp:include page="../../page/breadcrumbs.jsp"></jsp:include>

                <h2 class="main_title">${pageNameChild} <small>(共${pageBean.rows }条)</small></h2>
                <div class="common_data p20">

                    <div class="data_ctrl_area mb20">
                        <div class="data_ctrl_search pull-right"></div>
                        <div class="data_ctrl_brns pull-left">
                            <button type="button" class="btn btn-info" onclick="openAddEmpower()">
                                <i class="glyphicon glyphicon-plus"></i> 添加
                            </button>
                        </div>
                        <div class="clr"></div>
                    </div>
                    <form action="selectemp.htm" id="selectempForm">
                    <table class="table table-striped table-hover">
                        <thead>
                        <tr>
                            <th width="50">序号</th>
                            <th>用户名</th>
                            <th width="150">key</th>
                            <th width="150">开始时间</th>
                            <th width="150">结束时间</th>
                            <th width="150">是否启用</th>
                            <th width="150">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${pageBean.list}" var="emper" varStatus="i">
                            <tr>
                                <td>${i.count}</td>
                                <td>${emper.appUserName }</td>
                                <td>${emper.appKey }</td>
                                <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${emper.beginTime}" type="both"/></td>
                                <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${emper.endTime}" type="both"/></td>
                                <td>
                                    <c:if test="${emper.status==0}"><span class='label label-success'>是</span></c:if>
                                    <c:if test="${emper.status==1}"><span class='label label-default'>否</span></c:if></td>
                                <td>
                                    <div class="btn-group">
                                        <c:if test="${emper.status==0}">
                                            <button type="button" onclick="modifyStatus('1',${emper.appId})" class="btn btn-default">禁用</button>
                                        </c:if>
                                        <c:if test="${emper.status==1}">
                                            <button type="button" onclick="modifyStatus('0',${emper.appId})" class="btn btn-default">启用</button>
                                        </c:if>
                                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                            <span class="caret"></span>
                                            <span class="sr-only">Toggle Dropdown</span>
                                        </button>
                                        <ul class="dropdown-menu" role="menu">
                                            <li><a href="javascript:void(0);" onclick="delEmopower(${emper.appId})">删除</a></li>
                                            <li><a href="javascript:void(0);" onclick="empowerlog(${emper.appId })">日志</a></li>
                                        </ul>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                </form>
                    <div class="table_foot">
                        <c:import url="../../page/searchPag.jsp">
                            <c:param name="pageBean" value="${pb }"/>
                            <c:param name="path" value="../"></c:param>
                        </c:import>
                    </div>

                </div>

            </div>
        </div>
    </div>
</div>



<!-- Modal -->
<div class="modal fade" id="empowerDialog"  role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">添加角色</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="empForm" method="post" action="insertempoer.htm"  enctype="multipart/form-data">
                    <div class="form-group">
                        <label class="control-label col-sm-7">用户名：</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control required numandletter specstr"  maxlength="10" minlength="3" name="appUserName" id="appUserName"  value="">
                            <label id="appUserNametip" class="error"  style="display: none">用户名已存在</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-7">开始时间：</label>
                        <div class="col-sm-15">
                            <div class="input-group date form_datetime w200" id="startpicker">
                                <input class="form-control required" type="text" id="startTime" value="" readonly="" name="sTime">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-7">结束时间：</label>
                        <div class="col-sm-15">
                            <div class="input-group date form_datetime w200" id="endpicker">
                                <input class="form-control required" type="text"  id="endTime" value="" readonly="" name="eTime">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-7">开启状态：</label>
                        <div class="col-sm-15">
                            <label class="radio-inline">
                                <input type="radio" name="status"  value="1" > 冻结
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="status" checked="checked" value="0" > 正常
                            </label>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="saveEmpower();">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="orderLogmodal"  role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">操作日志</h4>
            </div>
            <div class="modal-body">
                <div class="common_info common_tabs mt20">
                    <ul class="nav nav-tabs" role="tablist">
                        <li role="presentation" class="active"><a href="#tab3" aria-controls="tab3" role="tab" data-toggle="tab">操作日志</a></li>
                        <%--<li role="presentation"><a href="#tab4" aria-controls="tab4" role="tab" data-toggle="tab">退款日志</a></li>--%>
                    </ul>
                    <div class="tab-content">
                        <div role="tabpanel" class="tab-pane active" id="tab3">
                            <table class="table table-striped table-hover table-bordered">
                                <thead>
                                <tr>
                                    <th>序号</th>
                                    <th>内容</th>
                                    <th>操作时间</th>
                                </tr>
                                </thead>
                                <tbody id="eLog">
                                <tr>
                                    <td colspan="4"><p class="text-center">暂无可用数据！</p></td>
                                </tr>

                                </tbody>
                            </table>
                            <div class="table_foot">
                                <div class="table_pagenav pull-right">
                                    <div class="meneame" id="pageorder">

                                    </div>
                                </div>
                                <div class="clr"></div>
                            </div>
                        </div>
                        <div role="tabpanel" class="tab-pane" id="tab4">
                            <table class="table table-striped table-hover table-bordered">
                                <thead>
                                <tr>
                                    <th>操作类型</th>
                                    <th>操作时间</th>
                                    <th>操作详情</th>
                                </tr>
                                </thead>
                                <tbody id="pageLog">
                                <tr>
                                    <td colspan="3"><p class="text-center">暂无可用数据！</p></td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <div role="tabpanel" class="tab-pane" id="tab5">
                            <table class="table table-striped table-hover table-bordered">
                                <thead>
                                <tr>
                                    <th>商品编号</th>
                                    <th>商品图片</th>
                                    <th width="250">商品名称</th>
                                    <th>商品价格</th>
                                    <th>关注时间</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>3170</td>
                                    <td><img alt="" src="images/good_1_small.jpg" height="50"> </td>
                                    <td>花落红*娘子写新款中式真丝香云纱桑蚕丝时尚花色七分袖旗袍上衣</td>
                                    <td>99.00</td>
                                    <td>2014-07-02 14:16:41</td>
                                </tr>
                                <tr>
                                    <td>3170</td>
                                    <td><img alt="" src="images/good_1_small.jpg" height="50"> </td>
                                    <td>花落红*娘子写新款中式真丝香云纱桑蚕丝时尚花色七分袖旗袍上衣</td>
                                    <td>99.00</td>
                                    <td>2014-07-02 14:16:41</td>
                                </tr>
                                <tr>
                                    <td>3170</td>
                                    <td><img alt="" src="images/good_1_small.jpg" height="50"> </td>
                                    <td>花落红*娘子写新款中式真丝香云纱桑蚕丝时尚花色七分袖旗袍上衣</td>
                                    <td>99.00</td>
                                    <td>2014-07-02 14:16:41</td>
                                </tr>
                                </tbody>
                            </table>
                            <div class="table_foot">
                                <div class="table_pagenav pull-right">
                                    <div class="meneame">
                                        <span class="disabled"> 上一页 </span>
                                        <span class="current"> 1 </span>
                                        <a href="#?page=2"> 2 </a>
                                        <a href="#?page=3"> 3 </a>
                                        <a href="#?page=4"> 4 </a>
                                        <a href="#?page=5"> 5 </a>
                                        ...
                                        <a href="#?page=199"> 199 </a>
                                        <a href="#?page=200"> 200 </a>
                                        <a href="#?page=2"> 下一页 </a>
                                    </div>
                                </div>
                                <div class="clr"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>



<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=basePath %>js/bootstrap.min.js"></script>
<script src="<%=basePath %>js/bootstrap-select.min.js"></script>
<script src="<%=basePath %>js/bootstrap-datetimepicker.min.js"></script>
<script src="<%=basePath %>js/language/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="<%=basePath %>js/summernote.min.js"></script>
<script src="<%=basePath %>js/language/summernote-zh-CN.js"></script>
<script src="<%=basePath %>js/jquery.ztree.core-3.5.min.js"></script>
<script src="<%=basePath %>js/jquery.ztree.excheck-3.5.min.js"></script>
<script src="<%=basePath %>js/common.js"></script>
<script src="<%=basePath %>/js/select2.min.js"></script>
<script src="<%=basePath %>js/common/common_alert.js"></script>
<script src="<%=basePath %>js/common/common_checked.js"></script>
<script>

    /**
     * propName 属性名
     * value 	属性值
     * url 		查询路径
     * tipStr	提示内容
     */
    function checkExist(propName,value,url,tipStr){
        if(value!=""){
            url=url+"?CSRFToken="+$("#hi_token").val()+"&"+propName+"="+value;
            $.ajax({
                type: 'post',
                url:url,
                async:false,
                success: function(data) {
                    if (data > 0){
                        $("#"+propName).addClass("error");
                        $("#"+propName+"tip").show();
                        $("#checkStatus").val(1);
                    } else {
                        $("#"+propName).removeClass("error");
                        $("#"+propName+"tip").hide();
                        $("#checkStatus").val(0);
                    }
                }
            });
        }
    }

    function saveEmpower(){
        if($("#checkStatus").val()==1){
            $("#appUserName").addClass("error");
            $("#appUserNametip").show();
            return
        }
        if($("#empForm").valid()) {
            $("#empForm").submit();
        }
    }

    $(function(){

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

        /**
         * 检查用户名是否存在
         */
        $("#appUserName").blur(function(){
            checkExist("appUserName",$("#appUserName").val(),"checkusername.htm","用户名");
        });


    });

    function modifyStatus(obj,id){
        location.href="updateempower.htm?status="+obj+"&appId="+id;
    }

    function delEmopower(obj){
        showDeleteOneConfirmAlert("delempower.htm?appId="+obj);
    }

    function openAddEmpower(){
        $('#empowerDialog').modal('show');
    }



    function empowerlog(oId){
        $.ajax({
            type : "POST",
            url:"selectlog.htm",
            data : "empowerId=" + oId,
            success:function(data){
                $("#eLog").html("");
                //订单操作日志
                var eLogs = data;
                if(eLogs !=null && eLogs != undefined){
                    var str="";
                    $.each(eLogs,function(idx,item) {
                        str+="<tr>"
                        str+="<td>"+(idx+1)+"</td>"
                        str+="<td>"+item.logContent+"</td>"
                        str+="<td>"+timeObject(item.createTime)+"</td>"
                        str+="</tr>"
                    });
                    $("#eLog").html(str);
                }
                $('#orderLogmodal').modal('show');
            }
        });
    }
    //判断数据是否为空为空返回“”
    function notNull(obj){
        if(obj != null && obj != undefined){
            return obj;
        }else{
            return "";
        }
    }
    //转换时间格式
    function timeObject(obj){
        var date = "/Date(" +notNull(obj)+")/";
        var time = new Date(parseInt(date.replace("/Date(", "").replace(")/", ""), 10));
        var result = time.getFullYear() + "-" + (time.getMonth() + 1 < 10 ? "0" + (time.getMonth() + 1) : time.getMonth() + 1) + "-" + (time.getDate() < 10 ? "0" + time.getDate() : time.getDate()) + " " + (time.getHours() < 10 ? "0" + time.getHours() : time.getHours()) + ":" + (time.getMinutes() < 10 ? "0" + time.getMinutes() : time.getMinutes())+ ":" + (time.getSeconds() < 10 ? "0" + time.getSeconds() : time.getSeconds());
        return result;
    }
</script>
</body>
</html>
