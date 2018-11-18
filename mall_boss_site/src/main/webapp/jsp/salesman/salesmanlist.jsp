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
    <title>业务员列表</title>

    <!-- Bootstrap -->
    <link href="<%=basePath %>css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath %>css/font-awesome.min.css">
    <link href="<%=basePath %>iconfont/iconfont.css" rel="stylesheet">
    <link href="<%=basePath %>css/summernote.css" rel="stylesheet">
    <link href="<%=basePath %>css/bootstrap-select.min.css" rel="stylesheet">
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
<jsp:include page="../page/header.jsp"></jsp:include>

<div class="page_body container-fluid">
    <div class="row">
        <jsp:include page="../page/left.jsp"></jsp:include>

        <div class="col-lg-20 col-md-19 col-sm-18 main">
            <div class="main_cont">
                <jsp:include page="../page/breadcrumbs.jsp"></jsp:include>
                <input type="hidden" value="${token }" id="hi_token"/>

                <h2 class="main_title">${pageNameChild}
                    <small>(共${pageBean.rows}条)</small>
                </h2>
                <div class="common_data p20">
                    <div class="filter_area">
                        <form role="form" class="form-inline" action="" id="searchForm" method="post">
                            <input type="hidden" value="searchForm" id="formId">
                            <input type="hidden" value="${pageBean.url}" id="formName">

                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">业务员</span>
                                    <input type="text" class="form-control" name="salesmanName"
                                           value="${pageBean.objectBean.salesmanName }">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">联系电话</span>
                                    <input type="text" class="form-control" name="salesmanPhone"
                                           value="${pageBean.objectBean.salesmanPhone }">
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">启用状态</span>
                                    <select class="form-control cate_selector" name="isEnabled">
                                        <option value=""
                                                <c:if test="${empty pageBean.objectBean.isEnabled }">selected="selected"</c:if>>
                                            选择状态
                                        </option>
                                        <option value="0"
                                                <c:if test="${not empty pageBean.objectBean.isEnabled && pageBean.objectBean.isEnabled==0 }">selected="selected"</c:if>>
                                            启用
                                        </option>
                                        <option value="1"
                                                <c:if test="${not empty pageBean.objectBean.isEnabled && pageBean.objectBean.isEnabled==1 }">selected="selected"</c:if>>
                                            未启用
                                        </option>
                                    </select>
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
                            <button type="button" class="btn btn-info" onclick="toaddSalesman();">
                                <i class="glyphicon glyphicon-plus"></i> 添加
                            </button>
                            <button type="button" class="btn btn-info" onclick="delallSales();">
                                <i class="glyphicon glyphicon-trash"></i> 删除
                            </button>
                        </div>
                        <div class="clr"></div>
                    </div>
                    <form action="deleteAllSalesman.htm?CSRFToken=${token}" id="delForm" method="post">
                        <input type="hidden" value="delForm" id="formId">
                        <input type="hidden" value="deleteAllNewCustomer.htm" id="formName">
                        <table class="table table-striped table-hover">
                            <thead>
                            <tr>
                                <th width="10"><input type="checkbox" onclick="allunchecked(this,'salesmanId');"></th>
                                <th>业务员</th>
                                <th>联系电话</th>
                                <th>所属部门</th>
                                <th>是否启用</th>
                                <th width="150">操作</th>
                            </tr>
                            </thead>
                            <tbody>

                            <c:forEach items="${pageBean.list}" var="salesman" varStatus="i">
                                <tr>
                                    <td><input type="checkbox" name="salesmanId" value="${salesman.salesmanId}"></td>
                                    <td>${salesman.salesmanName}</td>
                                    <td>${salesman.salesmanPhone}</td>
                                    <td>${salesman.salesmanDepartment}</td>
                                    <td>
                                        <c:if test="${salesman.isEnabled ==0}">
                                            <a title="点击禁用"
                                               href="updateSalesman.htm?CSRFToken=${token }&salesmanId=${salesman.salesmanId }&isEnabled=1&pageNo=${pageBean.pageNo }"
                                               class="label label-success">是</a>
                                        </c:if>
                                        <c:if test="${salesman.isEnabled ==1}">
                                            <a title="点击启用"
                                               href="updateSalesman.htm?CSRFToken=${token }&salesmanId=${salesman.salesmanId }&isEnabled=0&pageNo=${pageBean.pageNo }&lastSalesmanName=${pageBean.objectBean.isEnabled }&lastSalesmanPhone=${pageBean.objectBean.salesmanPhone}&lastSalesmanDepartment=${pageBean.objectBean.salesmanDepartment}&lastIsEnabled=${pageBean.objectBean.isEnabled}"
                                               class="label label-default">否</a>
                                        </c:if>
                                    </td>
                                    <td>
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-default"
                                                    onclick="oneditSales(${salesman.salesmanId});">编辑
                                            </button>
                                            <button type="button" class="btn btn-default dropdown-toggle"
                                                    data-toggle="dropdown">
                                                <span class="caret"></span>
                                                <span class="sr-only">Toggle Dropdown</span>
                                            </button>
                                            <ul class="dropdown-menu" role="menu">
                                                <li><a href="javascript:"
                                                       onclick="querySales(${salesman.salesmanId});">查看</a></li>
                                                <li><a href="javascript:void(0);"
                                                       onclick="delSales(${salesman.salesmanId});">删除</a></li>
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

<!-- Modal -->
<div class="modal fade" id="addMember" role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">添加业务员</h4>
            </div>

            <div class="modal-body">
                <form class="form-horizontal" id="salesmanForm" method="post" action="">
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span class="text-danger">*</span>业务员姓名：</label>

                        <div class="col-sm-1"></div>
                        <div class="col-sm-8">
                            <P id="nameDiv" class="radio-inline"></P>
                            <input type="hidden" id="salesmanIdSaler" name="salesmanId"/>
                            <input type="text" class="form-control required" maxlength="20" minlength="2"
                                   name="salesmanName" id="salesmanName" onblur="checkFormSala(this.value)">
                            <label id="checkOK" class="checkOK" style="display: none">业务员名可以使用</label>
                            <label id="salesmanNametip" class="error" style="display: none">业务员名已存在</label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-5"><span class="text-danger">*</span>联系电话：</label>

                        <div class="col-sm-1"></div>
                        <div class="col-sm-8">
                            <P id="mobileDiv" class="radio-inline"></P>
                            <input type="text" class="form-control mobile" name="salesmanPhone" id="salesmanPhone">
                            <input type="hidden" id="oldmobile">
                            <label id="mobiletip" class="error" style="display: none">联系电话已使用</label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-5"><span class="text-danger">*</span>所属部门：</label>

                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <input type="text" class="form-control" name="salesmanDepartment" id="salesmanDepartment">
                            <label id="salesmanDepartmenttip" class="error" style="display: none">所属部门不能为空</label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-5"><span class="text-danger">*</span>是否启用：</label>

                        <div class="col-sm-1"></div>
                        <div class="col-sm-15">
                            <label class="radio-inline">
                                <input type="radio" name="isEnabled" value="0" id="isEnabled0"
                                       <c:if test="${pageBean.objectBean.isEnabled==0 }">checked="checked"</c:if>> 启用
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="isEnabled" value="1" id="isEnabled1"
                                       <c:if test="${pageBean.objectBean.isEnabled==1 }">checked="checked"</c:if>> 未启用
                            </label>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="savecustomer();">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>


<!-- Modal -->
<div class="modal fade" id="scanMember" role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">查看业务员详情</h4>
            </div>
            <div class="modal-body">
                <iframe id="mainFrame" frameborder="0" style="width:100%;" src=""></iframe>

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
<script src="<%=basePath %>js/common.js"></script>
<script src="<%=basePath %>js/common/common_alert.js"></script>
<script src="<%=basePath %>js/common/common_checked.js"></script>
<script type="text/javascript" src="<%=basePath%>js/salesman/salesman.js"></script>
<script src="<%=basePath %>/js/select2.min.js"></script>
<script>
    var modified = 0;
    var bValid = true;
    var oldemail = $("#oldemail").val();
    var oldmobile = $("#oldmobile").val();
    $(function () {
        $("#salesmanForm").validate();
        /* 表单项的值点击后转换为可编辑状态 */
        $('.form_value').click(function () {
            var formItem = $(this);
            if (!$('.form_btns').is(':visible')) {
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
        $('.spec_set input').change(function () {
            if ($(this).is(':checked')) {
                $(this).parent().parent().next().slideDown('fast');
            }
            else {
                $(this).parent().parent().next().slideUp('fast');
            }
        });

    });

    //检查是否选中一行
    var checkedList;
    function checkSelected(objId) {
        checkedList = [];
        $("input[name='" + objId + "']:checked").each(function () {
            checkedList.push($(this).val());
        });
        if (checkedList.length > 0) {
            return true;
        } else {
            return false;
        }
    }

    //弹出添加业务员
    function toaddSalesman() {
        modified = 0;
        $("input[id^='customerUsername']").val('');
        $('#customerUsername').attr('readonly', 'readonly');
        $('#customerUsername').show();
        $('#password').show();
        $('#repassword').show();
        $("#nameDiv").hide();
        $("#passDiv").hide();
        $("#repassDiv").hide();
        $('#customerUsername').removeAttr('readonly');
        $('#customerUsername').removeClass('error');
        $("#customerUsernametip").hide();
        $('#email').removeClass('error');
        $("#emailtip").hide();
        $('#salesmanPhone').removeClass('error');
        $("#mobiletip").hide();
        $("#email").val('');
        $("#password").val("");
        $('#password').removeAttr('readonly');
        $("#repassword").val("");
        $('#repassword').removeAttr('readonly');
        $("#salesmanPhone").val('');
        $("#img").attr("src", 'images/default_head.jpg');
        $("#customerImg").val('');
        $('#gender1').prop('checked', 'checked');
        //将收货地址 省份 城市 区县 选中
        $("#realname").val('');
        $("#salesmanPhone").val('');
        $("#card_id").val('');
        $("#salesmanDepartment").val('');
        $("#salesmanId").val('');
        $("input[id^='salesmanName']").val("");
        $("input[id^='salesmanName']").attr("disabled",false);
        $('#salesmanForm').attr('action', 'addSalesman.htm?CSRFToken=' + $("#hi_token").val());
        //fillPandL();
        $('.modal-title').text("添加业务员");
        $('#addMember').modal('show');
        c = 0;
    }

    //弹出修改业务员框
    function oneditSales(salesmanId) {
        c = 0;
        modified = 1;
        $('#customerUsername').removeClass('error');
        $("#customerUsernametip").hide();
        $('#email').removeClass('error');
        $("#emailtip").hide();
        $('#salesmanPhone').removeClass('error');
        $("#mobiletip").hide();
        $("#nameDiv").hide();
        $("#mobileDiv").hide();
        $('#salesmanForm').attr('action', 'updateSalesman.htm?CSRFToken=' + $("#hi_token").val());
        doSearchSaler(salesmanId);
        $('.modal-title').text("修改业务员");
        $('#addMember').modal('show');
    }
    function doSearchSaler(salesmanId){
       // fillPandL();
        modified=1;
        $.post("querySalesmanById.htm?CSRFToken="+$("#hi_token").val()+"&salesmanId="+salesmanId,function(data){
            $("input[id^='salesmanName']").val(data.salesmanName);
            $("input[id^='salesmanName']").attr("disabled",true);
            $("#salesmanPhone").val(data.salesmanPhone);
            //选中会员等级
            $("#pointLevel option[value='"+data.pointLevelId+"']").prop("selected","selected");
            $("#salesmanDepartment").val(data.salesmanDepartment);
            $("#salesmanIdSaler").val(data.salesmanId);
            if(data.isEnabled=='0'){
                $("#isEnabled0").attr("checked","checked");
            }
            if(data.isEnabled=='1'){
                $("#isEnabled1").attr("checked","checked");
            }
            $("#dialog-form").dialog("open").dialog("option", { title : "编辑业务员" });
        });
    }
    //.保存业务员
    var c = 0;
    function savecustomer() {
        if (bValid && $("#salesmanForm").valid() && c == 0&&!check()) {
            c = 1;
            $("#salesmanForm").submit();
        }
    }

    //删除业务员信息
    function delSales(salesmanId) {
        $.post("checkUpdateEnable.htm?CSRFToken=${token}&salesmanId=" + salesmanId, function (data) {
            if (data!=0) {
                showTipAlert("删除的业务员存在关联企业用户,不允许删除");
                return;
            }
            showDeleteOneConfirmAlert('deleteSalesman.htm?CSRFToken=${token}&salesmanId=' + salesmanId, '确定要删除此业务员？');
        });
    }


    //批量删除业务员信息
    function delallSales() {
        var ids = [];
        var flag = true;
        $("input[name='salesmanId']:checked").each(function (i) {
            ids[i] = $(this).val();
        });
        jQuery.ajax({
            url: "checkUpdateEnableByIds.htm?CSRFToken=${token}&salesmanIds=" + ids,
            dataType: "json",
            type: "POST",
            success: function (data) {
               if(data !=0) {
                   showTipAlert("批量删除的业务员存在关联企业用户,不允许删除!");
                   flag = false;
               }
                if (flag) {
                    showDeleteBatchConfirmAlert('delForm', 'salesmanId', '确定要删除所选中的业务员吗？');
                }
            }
        });
    }

    function querySales(salesmanId) {
        $("#mainFrame").attr("src", 'querySalesman.htm?CSRFToken=${token}&salesmanId=' + salesmanId);
        $('#scanMember').modal('show');
        $('#mainFrame').css('minHeight', '450px');
    }

    function check() {
        var flag = false;
        if($("#salesmanName").val()==""){
            $("#salesmanNametip").text("业务员名不能为空");
            $("#salesmanNametip").show();
            flag = true;
        }
        if (!checkFormSala($("#salesmanName").val(),$("#salesmanId").val())) {
            flag = true;
        }
        if (!checkMob($("#salesmanPhone").val())) {
            $("#mobiletip").text("手机号码输入不对");
            $("#mobiletip").show();
            flag = true;
        }
        if($("#salesmanDepartment").val() ==""){
            $("#salesmanDepartmenttip").show();
            flag = true;
        }
        return flag;
    }

    function checkMob(phone) {
        var reg = /^0?1[3|4|7|5|8][0-9]\d{8}$/;
        if (reg.test(phone)) {
            return true;
        } else {
            return false;
        }
    }

    function checkFormSala(salesmanName,salesmanId) {
        var flagre = true;
        if(modified=="0"){
            $("#salesmanNametip").text("业务员名已存在");
            $("#salesmanNametip").hide();
            $.ajax({
                url : "checkExistSalesmanName.htm?CSRFToken=${token}&salesmanName=" + salesmanName,
                async : false, // 注意此处需要同步，因为返回完数据后，下面才能让结果的第一条selected
                type : "POST",
                dataType : "json",
                success :  function (data) {
                    if (data != 0) {
                        $("#salesmanNametip").show();
                        flagre = false;
                    }
                }
            });
        }
        if(modified=="1"){
            $("#salesmanNametip").text("业务员存在关联企业用户不可以删除");
            $("#salesmanNametip").hide();
            $.ajax({
                url : "checkUpdateEnable.htm?CSRFToken=${token}&salesmanId=" + salesmanId,
                async : false, // 注意此处需要同步，因为返回完数据后，下面才能让结果的第一条selected
                type : "POST",
                dataType : "json",
                success :  function (data) {
                    if (data != 0) {
                        $("#salesmanNametip").show();
                        flagre = false;
                    }
                }
            });
        }
        return flagre;
    }

</script>
</body>
</html>
