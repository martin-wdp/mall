<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    //String bsetaddress = (String)request.getSession().getAttribute("bsetaddress");
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
    <title></title>

    <!-- Bootstrap -->
    <link href="<%=basePath %>/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath %>/iconfont/iconfont.css" rel="stylesheet">
    <link href="<%=basePath %>css/style.css" rel="stylesheet">
    <script src="<%=basePath %>js/esl-1.6.10.js"></script>
</head>
<style>
    body {
        font-size: 12px;
    <%--overflow-y: visible;--%>
    }
</style>
<body>

<!-- 引用头 -->
<jsp:include page="page/header.jsp"></jsp:include>

<%--<input type="hidden" id="bsetaddress"/>--%>
<div class="container-fluid page_body">
    <div class="row">
        <!-- 引用左边导航 -->
        <jsp:include page="page/left.jsp"></jsp:include>
        <div class="col-lg-20 col-md-19 col-sm-18 main"
             style="height:100%;background: url(<%=basePath %>images/indexbg.jpg) repeat;min-height:590px;">
            <div class="selfMes">
                <c:if test="${!empty manager }">
                    <div class="portrait">
                        <c:if test="${empty manager.photoImg }">
                            <img class="vm mr10" id="m_img" alt="" src="<%=basePath %>images/avatar.jpg"
                                 height="110" style="margin-top:-3px;"/>
                        </c:if>

                        <c:if test="${not empty manager.photoImg }">
                            <img class="vm mr10" id="m_img" alt="" src="${manager.photoImg }" height="110"
                                 style="margin-top:-3px;"/>
                        </c:if>
                    </div>

                    <div class="nameTim">
                        <font size="22">
                            <small>${name }</small>
                            <a href="" id="toIndex" title="千品猫商城首页"><img src="<%=basePath%>images/tosite.png"></a>
                        </font>
                        <br><br>
                        <span style="font-size:18px;">上次登录：</span><br>
                        <span style="color:#F6AB00;font-size:18px;"><%--<fmt:formatDate value="${manager.loginTime}" type="date" pattern="yyyy年MM月dd日 HH时mm分"/>--%>
                        <fmt:formatDate value="${lastLonginTime}" type="date" pattern="yyyy年MM月dd日 HH时mm分"/></span>
                    </div>
                </c:if>
            </div>
        </div>


    </div>
</div>

<div id="feedback" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
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
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->


<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=basePath %>js/bootstrap.min.js"></script>
<script src="<%=basePath %>js/common.js"></script>

<script type="text/javascript">

    $(function(){
        $.ajax({
            url:"getBasicSetName.htm",
            async:false,
            success:function(data){
                if(data!="") {
                    //$("#bsetaddress").val(data.bsetAddress);
                    $("#toIndex").attr("href",data.bsetAddress);
                }
            }});
    });

    $(".main").height($(window).height()-84);
    $(window).resize(function(){
        $(".main").height($(window).height()-84);
    });

</script>
</body>
</html>
