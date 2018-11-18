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
    <link href="<%=basePath %>/css/select2.min.css" rel="stylesheet">
    <link href="<%=basePath %>/css/style.css" rel="stylesheet">

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
<input type="hidden" value="queryInforSubjec" id="formId">
<input type="hidden" value="queryInforSubjectByPageBean.htm" id="formName">
<div class="page_body container-fluid">
    <div class="row">
        <!-- 引用昨天导航 -->
        <jsp:include page="../page/left.jsp"></jsp:include>

        <div class="col-lg-20 col-md-19 col-sm-18 main">
            <div class="main_cont">
                <jsp:include page="../page/breadcrumbs.jsp"></jsp:include>

                <h2 class="main_title">${pageNameChild} <small>(共${pb.rows}条)</small></h2>

                <div class="common_data p20">

                    <div class="filter_area">
                        <form role="form" class="form-inline" id="queryInforSubjec" method="post">
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">专题名</span>
                                        <input type="text" value="${titles}" name="titles" class="form-control" >
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
                            <button type="button" class="btn btn-info" onclick="$('#addSpecialTopic').modal('show')">
                                <i class="glyphicon glyphicon-plus"></i> 添加
                            </button>
                            <button type="button" class="btn btn-info" onclick="showAjaxDeleteBatchConfirmAlert('deleteFormId','inforSubjectIds')">
                                <i class="glyphicon glyphicon-trash"></i> 删除
                            </button>
                        </div>
                        <div class="clr"></div>
                    </div>
                    <form id="deleteFormId" action="delinforsubjectnew.htm">
                    <table class="table table-striped table-hover" >
                        <thead>
                        <tr>
                            <th><input type="checkbox" onclick="allunchecked(this,'inforSubjectIds')"></th>
                            <th>专题标题</th>
                            <th>专题地址</th>
                            <th>去除头尾部</th>
                            <th>是否启用</th>
                            <th class="w100">创建时间</th>
                            <th class="w100">更新时间</th>
                            <th width="150">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <input type="hidden" name="CSRFToken"  value="${token}">
                        <c:forEach var="subject" items="${pb.list }" varStatus="status">
                        <tr>
                            <td><input type="checkbox" name="inforSubjectIds"  value="${subject.subjectId }"/></td>
                            <td>${subject.title }</td>
                            <td><%
                                String siteAddress = (String)request.getAttribute("siteAddress");
                                if(siteAddress.endsWith("/")){
                            %>
                                    ${siteAddress }subject/${subject.subjectId }
                                <%
                                }else{
                                %>
                                    ${siteAddress }/subject/${subject.subjectId }
                                <%
                                    }
                                %></td>
                            <td>
                                <c:if test="${subject.temp1==1}"><span class='label label-success'>是</span></c:if>
                                <c:if test="${subject.temp1==0}"><span class='label label-default'>否</span></c:if>
                            </td>
                            <td>
                                <c:if test="${subject.isShow==1}"><span class='label label-success'>是</span></c:if>
                                <c:if test="${subject.isShow==0}"><span class='label label-default'>否</span></c:if>
                            </td>
                            <td><fmt:formatDate value="${subject.createDate }" var="cdate" type="both"/>
                                    ${cdate }
                            </td>
                            <td><fmt:formatDate value="${subject.updateDate }" var="cdate" type="both"/>
                                    ${cdate }
                            </td>
                            <td>
                                <div class="btn-group">
                                    <button type="button" class="btn btn-default" onclick="updateSubject(${subject.subjectId })">编辑</button>
                                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                        <span class="caret"></span>
                                        <span class="sr-only">Toggle Dropdown</span>
                                    </button>
                                    <ul class="dropdown-menu" role="menu">
                                        <li><a href="javascript:;" onclick="showDeleteOneConfirmAlert('delinforsubjectnew.htm?CSRFToken=${token}&inforSubjectIds=${subject.subjectId}')">删除</a></li>
                                    </ul>
                                </div>
                            </td>
                        </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    </form>
                    <c:import url="../page/searchPag.jsp">
                        <c:param name="pageBean" value="${pb }"/>
                    </c:import>

                </div>

            </div>
        </div>
    </div>
</div>

<%--修改专题页--%>
<div class="modal fade" id="updateSpecialTopic"  role="dialog">
    <div class="modal-dialog modal-lg">
        <form class="form-horizontal" id="subSubjectForm" method="post" action="updateInforSubjectNew.htm" >
            <input type="hidden" name="CSRFToken" id="CSRFToken" value="${token}">
            <input type="hidden" name="subjectId" id="up_infoOnePageid" value="${subject.subjectId }"/>
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 id="update_title" class="modal-title">修改专题</h4>
                </div>
                <div class="modal-body">

                    <div class="form-group">
                        <label class="control-label col-sm-4"><span class="text-danger">*</span>专题标题：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <input type="text" id="up_test" class="form-control required specstr" name="title">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">SEO关键字：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <textarea name="seoKeyword"   id="up_seoKeyword" class="form-control specstr" rows="5"></textarea>
                        </div>
                        <div class="col-sm-3">
                            <a href="javascript:;" class="zhuantiseokw help_tips">
                                <i class="icon iconfont">&#xe611;</i>
                            </a>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">SEO描述：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <textarea name="seoDesc" id="up_seoDesc" class="form-control specstr" rows="5"></textarea>
                        </div>
                        <div class="col-sm-3">
                            <a href="javascript:;" class="zhuantiseodesc help_tips">
                                <i class="icon iconfont">&#xe611;</i>
                            </a>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">去除头尾部：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" id="up_temp11" checked="checked" name="temp1" id="inlineRadio1" value="1"> 是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" id="up_temp10" name="temp1" id="inlineRadio2" value="0"> 否
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">是否启用：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" checked="checked" id="up_isShow1"  name="isShow" id="inlineRadio3" value="1"> 是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="isShow" id="up_isShow0" id="inlineRadio4" value="0"> 否
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">专题内容：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-17">
                            <div class="summernote" id="countStr_update"></div>
                            <input type="hidden" id="content" class="required_update" name="content">
                        </div>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" onclick="subForm_update()"  class="btn btn-primary">确定</button>
                    <button type="button" onclick="cls()" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </div>
        </form>
    </div>
</div>


<%--新增专题页--%>
<div class="modal fade" id="addSpecialTopic"  role="dialog">
    <div class="modal-dialog modal-lg">
        <form class="form-horizontal" id="addSubjectForm"  method="post" action="addinforsubjectnew.htm" >
            <input type="hidden" name="CSRFToken"  value="${token}">
            <input type="hidden" name="subjectId" value="${subject.subjectId }"/>
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4  class="modal-title">添加专题</h4>
            </div>
            <div class="modal-body">

                    <div class="form-group">
                        <label class="control-label col-sm-4"><span class="text-danger">*</span>专题标题：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <input type="text"  class="form-control required specstr" name="title">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">SEO关键字：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <textarea name="seoKeyword"    class="form-control specstr" rows="5"></textarea>
                        </div>
                        <div class="col-sm-3">
                            <a href="javascript:;" class="zhuantiseokw help_tips">
                                <i class="icon iconfont">&#xe611;</i>
                            </a>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">SEO描述：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <textarea name="seoDesc"  class="form-control specstr" rows="5"></textarea>
                        </div>
                        <div class="col-sm-3">
                            <a href="javascript:;" class="zhuantiseodesc help_tips">
                                <i class="icon iconfont">&#xe611;</i>
                            </a>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">去除头尾部：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio"  checked="checked" name="temp1"  value="1"> 是
                            </label>
                            <label class="radio-inline">
                                <input type="radio"  name="temp1"  value="0"> 否
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">是否启用：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" checked="checked"  name="isShow"  value="1"> 是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="isShow"   value="0"> 否
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">专题内容：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-17">
                            <div class="summernote upcountStr" ></div>
                            <input type="hidden"   class="required_add" name="content">
                        </div>
                    </div>

            </div>
            <div class="modal-footer">
                <button type="button" onclick="subForm()"  class="btn btn-primary">确定</button>
                <button type="button" onclick="cls()" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
        </form>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="changePackage"  role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">更换包裹</h4>
            </div>
            <div class="modal-body">
                <form role="form" class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-sm-6">选择装箱单:</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-11">
                            <select class="form-control">
                                <option>请选择</option>
                                <option>装箱单1</option>
                                <option>装箱单2</option>
                                <option>装箱单3</option>
                            </select>
                        </div>
                        <div class="col-sm-6"></div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>

            </div>
        </div>
    </div>
</div>

<div class="advanced_search_cont none">
    <div class="advanced_search_form">
        <form class="form-horizontal">
            <div class="form-group">
                <label class="control-label col-sm-7">收货人：</label>
                <div class="col-sm-15">
                    <input type="text" class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-7">联系电话：</label>
                <div class="col-sm-15">
                    <input type="text" class="form-control">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-7 col-sm-15">
                    <button type="submit" class="btn btn-primary btn-sm">确认搜索</button>
                </div>
            </div>
        </form>
    </div>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=basePath %>/js/bootstrap.min.js"></script>
<script src="<%=basePath %>/js/summernote.min.js"></script>
<script src="<%=basePath %>/js/language/summernote-zh-CN.js"></script>
<script src="<%=basePath %>/js/bootstrap-select.min.js"></script>
<script src="<%=basePath %>/js/bootstrap-datetimepicker.min.js"></script>
<script src="<%=basePath %>/js/language/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="<%=basePath %>/js/common.js"></script>
<script src="<%=basePath %>js/common/common_alert.js"></script>
<script src="<%=basePath %>js/common/common_checked.js"></script>
<script src="<%=basePath %>js/information/subject_list.js"></script>
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
            lang: 'zh-CN',
            onImageUpload: function(files, editor, $editable) {
                sendFile(files,editor,$editable);
            }
        });

        /* 下面是表单里面的填写项提示相关的 */
        $('.zhuantiseokw').popover({
            content : '默认为专题名称(最大字数75)',
            trigger : 'hover'
        });
        $('.zhuantiseodesc').popover({
            content : '默认为专题名称(最大字数255)',
            trigger : 'hover'
        });

        $("#choose").click(function(){
            i=1;
            art.dialog.open('queryImageManageByPbAndCidForChoose.htm?CSRFToken=${token}&size=10000', {
                lock: true,
                opacity:0.3,
                width: '900px',
                height: '400px',
                title: '选择图片'
            });
        });

        /* 高级搜索 */
        $('.advanced_search').popover({
            html : true,
            title : '高级搜索',
            content : $('.advanced_search_cont').html(),
            trigger : 'click',
            placement : 'bottom'
        }).click(function(){
        	 $('select[data-live-search="true"]').select2();
        });

        /* 下面是表单里面的日期时间选择相关的 */
        $('.form_datetime').datetimepicker({
            format: 'yyyy-mm-dd hh:ii:00:00',
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
    });
</script>
</body>
</html>
