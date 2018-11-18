<%--
  Created by IntelliJ IDEA.
  User: Zhoux
  Date: 2015/3/25
  Time: 13:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
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
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>在线客服</title>
    <!-- Bootstrap -->
    <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath%>css/font-awesome.min.css">
    <link href="<%=basePath%>iconfont/iconfont.css" rel="stylesheet">
    <link href="<%=basePath%>css/summernote.css" rel="stylesheet">
    <link href="<%=basePath%>css/bootstrap-select.min.css" rel="stylesheet">
    <link href="<%=basePath%>css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <link href="<%=basePath %>css/select2.min.css" rel="stylesheet">
    <link href="<%=basePath%>css/style.css" rel="stylesheet">
    <script type="text/javascript">
        function updQQ(id){
            var sNumber=$("#item_"+id+" input:eq(1)").val();
            var sName=$("#item_"+id+" input:eq(2)").val();
            var serviceId=$("#item_"+id+" input:eq(3)").val();
            <%--$.ajax({
                url:"updateOnLineServiceItem.htm?CSRFToken=${token}",
                type:"post",
                dataType:"json",
                data:{onLineServiceItemId:id,name:sName,contactNumber:sNumber,onLineServiceId:serviceId},
                success:function(data){
                    alert(data);
                },
                error:function(data){
                    alert(data);
                }

            });--%>
            $.post("updateOnLineServiceItem.htm?CSRFToken=${token}",
                    {onLineServiceItemId:id,name:sName,contactNumber:sNumber,onLineServiceId:serviceId},
                    function(data) {
                        if(data){
                            showTipAlert("修改成功！");
                        }else{
                            showTipAlert("修改失败！");
                        }
                    });
        }

    </script>

</head>
<body >
<!-- 引用头 -->
<jsp:include page="../../page/header.jsp"></jsp:include>

<div class="container-fluid page_body">
    <div class="row">
        <jsp:include page="../../page/left.jsp"></jsp:include>
        <div class="col-lg-20 col-md-19 col-sm-18 main">
            <div class="main_cont" >
                 <jsp:include page="../../page/breadcrumbs.jsp"></jsp:include>

                <h2 class="main_title">${pageNameChild}</h2>

                <div class="common_form common_form_max p20">
                    <div class="alert alert-warning alert-dismissible" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <strong>注意!</strong> 管理员可为网站添加配置客服联系方式，用于首页显示。
                    </div>
                    <form class="form-horizontal" id="online_save" action="addOnLineService.htm" method="post">
                        <input type="hidden" name="CSRFToken" value="${token}">
                        <input id="onLineServiceId" type="hidden" value="${onLineService.onLineServiceId }" name="onLineServiceId">
                        <div class="form-group">
                            <label class="control-label col-sm-2">客服显示：</label>
                            <div class="col-sm-1"></div>
                            <div class="col-sm-5">
                                <select class="form-control" name="onlineIndex" id="displayWay">
                                    <option value="Y" <c:if test='${onLineService.onlineIndex == "Y"}'>selected="selected" </c:if>>页面右侧</option>
                                    <option value="N" <c:if test='${onLineService.onlineIndex == "N"}'>selected="selected" </c:if>>隐藏</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2">标题说明：</label>
                            <div class="col-sm-1"></div>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="title" value="${onLineService.title}">
                                <span id="titleTips"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-3 col-sm-10">
                                <button type="button" class="btn btn-primary" id="saveOnLine">保存</button>
                            </div>
                        </div>
                    </form>
                    <form class="form-horizontal">
                        <div class="form-group">
                            <label class="control-label col-sm-2">客服列表：</label>
                            <div class="col-sm-1"></div>
                            <div class="col-sm-3">
                                <button type="button" class="btn btn-info" id="addQQ">添加QQ</button>
                                <%--<button type="button" class="btn btn-info" id="addIphone">添加电话</button>--%>
                                <%--<button type="button" class="btn btn-info" id="addWW">添加旺旺</button>--%>
                            </div>
                            <div class="col-sm-2">
                                <a href="javascript:;" class="kflb help_tips">
                                    <i class="icon iconfont">&#xe611;</i>
                                </a>
                            </div>
                        </div>
                        <div class="service_list">

                            <c:if test="${not empty onLineService.itemList }">
                                <c:forEach items="${onLineService.itemList }" var="item" varStatus="sta">
                                    <c:choose>
                                        <c:when test="${item.contactType == 0 }">
                                            <div class="form-group" id="item_${item.onLineServiceItemId}">
                                                <input type="hidden" id="sta_${sta.index }" value="${item.onLineServiceItemId}" />
                                                <label class="control-label col-sm-offset-3 col-sm-2">客服QQ：</label>
                                                <div class="col-sm-4">
                                                    <input onblur="" type="text" class="form-control qqNumber" name="sNumber"  value="${item.contactNumber }">
                                                </div>
                                                <label class="control-label col-sm-2">客服名称：</label>
                                                <div class="col-sm-4">
                                                    <input type="text" class="form-control" name="sName" maxlength="6" value="${item.name }">
                                                </div>
                                                <div class="col-sm-1"></div>
                                                <div class="col-sm-4">
                                                    <p class="pt5">
                                                        <a onclick="updQQ(${item.onLineServiceItemId});" href="javascript:void(0);">修改</a>
                                                        <a onclick="removeItem(${item.onLineServiceItemId})" href="javascript:void(0);">删除</a>
                                                    </p>
                                                </div>
                                                <input type="hidden" value="${onLineService.onLineServiceId }" name="onLineServiceId">
                                                <input id="sOrder" type="hidden" value="0" class="stOrder">
                                                <input id="sType" type="hidden" value="0" class="stType" name="sType">
                                            </div>
                                        </c:when>
                                        <c:when test="${item.contactType == 1 }">
                                            <div class="form-group" id="item_${item.onLineServiceItemId}">
                                                <input type="hidden" id="sta_${sta.index }" value="${item.onLineServiceItemId}" />
                                                <label class="control-label col-sm-offset-3 col-sm-2">客服电话：</label>
                                                <div class="col-sm-4">
                                                    <input onblur="" type="text" class="form-control" name="sNumber"  value="${item.contactNumber }">
                                                </div>
                                                <label class="control-label col-sm-2">客服名称：</label>
                                                <div class="col-sm-4">
                                                    <input type="text" class="form-control" name="sName" value="${item.name }" maxlength="6">
                                                </div>
                                                <div class="col-sm-1"></div>
                                                <div class="col-sm-4">
                                                    <p class="pt5">
                                                        <a onclick="updQQ(${item.onLineServiceItemId});" href="javascript:void(0);">修改</a>
                                                        <a onclick="removeItem(${item.onLineServiceItemId})" href="javascript:void(0);">删除</a>
                                                    </p>
                                                </div>
                                                <input type="hidden" value="${onLineService.onLineServiceId }" name="onLineServiceId">
                                                <input  type="hidden" value="0" class="stOrder">
                                                <input  type="hidden" value="1" class="stType" name="sType">
                                            </div>
                                        </c:when>
                                        <c:when test="${item.contactType == 2 }">
                                            <div class="form-group" id="item_${item.onLineServiceItemId}">
                                                <input type="hidden" id="sta_${sta.index }"  value="${item.onLineServiceItemId}" />
                                                <label class="control-label col-sm-offset-3 col-sm-2">客服旺旺：</label>
                                                <div class="col-sm-4">
                                                    <input onblur="" type="text" class="form-control" name="sNumber"  value="${item.contactNumber }">
                                                </div>
                                                <label class="control-label col-sm-2">客服名称：</label>
                                                <div class="col-sm-4">
                                                    <input type="text" class="form-control" name="sName" value="${item.name }" maxlength="6">
                                                </div>
                                                <div class="col-sm-1"></div>
                                                <div class="col-sm-4">
                                                    <p class="pt5">
                                                        <a onclick="updQQ(${item.onLineServiceItemId});" href="javascript:void(0);">修改</a>
                                                        <a onclick="removeItem(${item.onLineServiceItemId})" href="javascript:void(0);">删除</a>
                                                    </p>
                                                </div>
                                                <input type="hidden" value="${onLineService.onLineServiceId }" name="onLineServiceId">
                                                <input  type="hidden" value="0" class="stOrder">
                                                <input  type="hidden" value="2" class="stType" name="sType">
                                            </div>
                                        </c:when>
                                    </c:choose>
                                </c:forEach>
                            </c:if>

                        </div>



                        <div class="service_list" id="online_form"></div>


                    </form>

                </div>

            </div>
        </div>
    </div>
</div>

<div class="form-group" id="serviceQQ" style="display: none">
    <form action="addOnLineServiceItem.htm" method="post" onsubmit="return checkQQ();">
        <input type="hidden" name="CSRFToken" value="${token}">
        <input type="hidden" value="${onLineService.onLineServiceId }" name="onLineServiceId">
        <label class="control-label col-sm-offset-3 col-sm-2">客服QQ：</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" name="contactNumber" value="" id="addQQNo">
        </div>
        <label class="control-label col-sm-2">客服名称：</label>
        <input type="hidden" value="0" class="stType" name="contactType">
        <div class="col-sm-4">
            <input type="text" class="form-control" name="name" value="" id="addQQName" maxlength="6">
        </div>
        <div class="col-sm-1"></div>
        <div class="col-sm-6">
            <input type="submit" class="btn btn-primary" value="提交"/>
            <input type="button" class="btn btn-primary" value="取消" onclick="cacleBtn();"/>
            <span generated="true" id="addQQ_tips" class="error" data-index="" style="color:#a94442;"></span>
        </div>
    </form>
</div>

<div class="form-group" id="serviceTel" style="display: none">
    <form action="addOnLineServiceItem.htm" method="post">
        <input type="hidden" name="CSRFToken" value="${token}">
        <input type="hidden" value="${onLineService.onLineServiceId }" name="onLineServiceId">
        <label class="control-label col-sm-offset-3 col-sm-2">客服电话：</label>
        <input type="hidden" value="1" class="stType" name="contactType">
        <div class="col-sm-4">
            <input type="text" class="form-control" name="contactNumber" value="" id="addTelNo">
        </div>
        <label class="control-label col-sm-2">客服名称：</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" name="name" value="" id="addTelName">
        </div>
        <div class="col-sm-1"></div>
        <div class="col-sm-6">
            <input type="submit" class="btn btn-primary" value="提交">
            <span generated="true" id="addTel_tips" class="error" data-index=""></span>
        </div>
    </form>
</div>

<div class="form-group" id="serviceWW" style="display: none">
    <form action="addOnLineServiceItem.htm" method="post">
        <input type="hidden" name="CSRFToken" value="${token}">
        <input type="hidden" value="${onLineService.onLineServiceId }" name="onLineServiceId">
        <label class="control-label col-sm-offset-3 col-sm-2">客服旺旺：</label>
        <input type="hidden" value="2" class="stType" name="contactType">
        <div class="col-sm-4">
            <input type="text" class="form-control" name="contactNumber" value="" id="addWWNo">
        </div>
        <label class="control-label col-sm-2">客服名称：</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" name="name" value="" id="addWWName">
        </div>
        <div class="col-sm-1"></div>
        <div class="col-sm-6">
            <input type="submit" class="btn btn-primary" value="提交">
            <span generated="true" id="addWW_tips" class="error" data-index=""></span>
        </div>
    </form>
</div>


<!-- Modal -->
<div class="modal fade" id="addSpecialTopic"  role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">添加专题</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-sm-4">专题标题：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <input type="text" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">SEO关键字：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <textarea class="form-control" rows="5"></textarea>
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
                            <textarea class="form-control" rows="5"></textarea>
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
                                <input type="radio" name="inlineRadioOptions" id="inlineRadio1" value="option1"> 是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="inlineRadioOptions" id="inlineRadio2" value="option2"> 否
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">是否启用：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="inlineRadioOptions2" id="inlineRadio3" value="option1"> 是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="inlineRadioOptions2" id="inlineRadio4" value="option2"> 否
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">背景图片：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <p class="pt5"><input type="file"></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">预览图片：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <img alt="" src="images/qpmall_logo.jpg">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-4">专题内容：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-17">
                            <div class="summernote"></div>
                        </div>
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
<input type="hidden" name="CSRFToken" id="CSRFToken" value="${token}">
<!-- jQuery (necessary for Bootstrap's JavaScript plugins)
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=basePath%>js/bootstrap.min.js"></script>
<script src="<%=basePath%>js/summernote.min.js"></script>
<script src="<%=basePath%>js/language/summernote-zh-CN.js"></script>
<script src="<%=basePath%>js/bootstrap-select.min.js"></script>
<script src="<%=basePath%>js/bootstrap-datetimepicker.min.js"></script>
<script src="<%=basePath%>js/language/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="<%=basePath%>js/system/onlineservice_add.js"></script>
<script src="<%=basePath%>js/common.js"></script>
<script src="<%=basePath %>js/common/common_alert.js"></script>
<script src="<%=basePath %>js/select2.min.js"></script>
</body>
</html>
