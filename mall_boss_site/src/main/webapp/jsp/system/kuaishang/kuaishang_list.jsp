<%--
  Created by IntelliJ IDEA.
  User: NP-Heh
  Date: 2015/3/25
  Time: 17:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
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
    <title>快商通设置</title>

    <!-- Bootstrap -->
    <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath%>css/font-awesome.min.css">
    <link href="<%=basePath%>iconfont/iconfont.css" rel="stylesheet">
    <link href="<%=basePath%>css/summernote.css" rel="stylesheet">
    <link href="<%=basePath%>css/style.css" rel="stylesheet">

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
        <div class="col-lg-20 col-md-19 col-sm-18 main">
            <div class="main_cont">
                <jsp:include page="../../page/breadcrumbs.jsp"></jsp:include>
                <h2 class="main_title">${pageNameChild}</h2>

                <div class="common_data p20">
                    <div class="alert alert-warning alert-dismissible" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <strong>注意!</strong> 管理员可以为网站添加配置客服联系方式,用于首页显示!
                    </div>
                    <div class="box_data container-fluid p20">
                        <div class="row">

                            <div class="col-sm-6">
                                <style>
                                    .kst{position:relative;width:700px;padding:10px;height:260px;}
                                    .kst:after{content:'';display:block;clear:both;}
                                    .kst .status{position:absolute;right:10px;top:10px;}
                                    .kst h3{font-size:16px;font-weight:bold;margin:20px 0;}
                                    .kst h4{font-size:14px;margin-bottom:20px;}
                                    .kst .links{margin-bottom:10px;}
                                    .kst .links a{margin-right:10px;cursor:pointer;}
                                    .kst_info{position:absolute;left:600px;top:120px}
                                    .kst_info ul li{line-height:20px;}
                                </style>
                                <div class="box_item kst">
                                    <img src="<%=basePath%>${list.shangLogo}">
                                    <h3>智慧在线客服传统企业版—企业首选:强推广/多功能/云分析/超管理</h3>
                                    <h4>传统企业版智慧在线客服,全网功能最优,提升盈利65%</h4>
                                    <div class="status">
                                        <c:if test="${list.isuseing == 0 }">
                                            <a onclick="window.location.href='updateKuaiShangByPrimaryKey.htm?isuseing=1&CSRFToken=${token}&shangId=${list.shangId}'"><span class="label label-success" style="cursor: pointer">已启用</span></a>
                                        </c:if>
                                        <c:if test="${list.isuseing == 1}">
                                            <a onclick="window.location.href='updateKuaiShangByPrimaryKey.htm?isuseing=0&CSRFToken=${token}&shangId=${list.shangId}'">  <span class="label label-default" style="cursor: pointer">已停用</span></a>
                                        </c:if>
                                    </div>

                                    <div class="links">
                                        <%--<a>操作手册</a>--%>
                                        <%--<a>升级版介绍</a>--%>
                                        <a href="http://www.kuaishang.cn/download.html">客户端下载</a>
                                    </div>

                                    <c:if test="${list.mobilephone == null||list.mobilephone == '' }">
                                        <button class="btn btn-primary pull-right" onclick=" $('#editKuaiShang').modal('show')">申请</button>

                                    </c:if>
                                    <c:if test="${list.mobilephone != null&&list.mobilephone != '' }">
                                        <button class="btn btn-primary pull-right" onclick="showkuaishang()">查看</button>
                                    </c:if>


                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>


<!-- 查看详细信息 -->
<div class="modal fade" id="showKuaiShang"  role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">快商通明细</h4>
            </div>
            <div class="modal-body">
                <form role="form" class="form-horizontal" >
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color:red;"></span>登录名：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-5">
                            <label class="radio-inline">  ${list.shangLoginName}</label>

                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color:red;"></span>登录ID：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-5">
                            <label class="radio-inline">  ${list.shangLongId}</label>

                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color:red;">*</span>公司名称：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-5">

                            <label class="radio-inline"> ${list.companyName}</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color:red;">*</span>公司网址：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">

                            <label class="radio-inline">  ${list.companyUrl} </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color:red;">*</span>行业：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-5">

                            <label class="radio-inline">  ${list.trade}</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color:red;">*</span>联系人：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-5">

                            <label class="radio-inline">     ${list.linkman}</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color:red;">*</span>联系电话：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-5">

                            <label class="radio-inline">  ${list.telephone}</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color:red;">*</span>邮箱：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-5">

                            <label class="radio-inline">  ${list.email}</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color:red;">*</span>手机号码：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-5">

                            <label class="radio-inline">  ${list.mobilephone}</label>
                        </div>
                    </div>
                </form>
                <div class="kst_info">
                    <ul>
                        <li><img src="<%=basePath%>${list.shangLogo}"></li>
                        <li>申请对象:${list.shangLinkman}</li>
                        <li>联系电话:${list.shangTelephone}</li>
                        <li>联系QQ:${list.shangContantQq}</li>
                    </ul>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="hidekuaishang()">取消</button>

            </div>
        </div>
    </div>
</div>

<!-- 编辑支付方式 -->
<div class="modal fade" id="editKuaiShang"  role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">申请快商通</h4>
            </div>
            <div class="modal-body">
                <form role="form" class="form-horizontal" id="editKuaiShangForm" >
                    <input type="hidden" name="CSRFToken" value="${token}"/>
                    <input type="hidden" name="shangId" id="shangId" value="${list.shangId}"/>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color:red;">*</span>登录名：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-5">
                            <input type="text" class="form-control required" name="shangLoginName" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color:red;">*</span>密码：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-5">
                            <input type="password" class="form-control required" name="password" id="password" onblur="checkPassword()">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color:red;">*</span>确定密码：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-5">
                            <input type="password" class="form-control required"  id="repassword" onblur="checkPassword()">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color:red;">*</span>公司名称：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-5">
                            <input type="text" class="form-control required" name="companyName" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color:red;">*</span>公司网址：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-5">

                                <input type="text" class="form-control required url " placeholder="例:http://www.baidu.com" name="companyUrl" id="companyUrl" >


                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color:red;">*</span>行业：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-5">
                            <input type="text" class="form-control required" name="trade" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color:red;">*</span>联系人：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-5">
                            <input type="text" class="form-control required" name="linkman" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color:red;">*</span>联系电话：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-5">
                            <input type="text" class="form-control required isPhone" name="telephone" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color:red;">*</span>邮箱：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-5">
                            <input type="text" class="form-control required email" name="email" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color:red;">*</span>手机号码：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-5">
                            <input type="text" class="form-control required mobile" name="mobilephone" >
                        </div>
                    </div>
                </form>
                <div class="kst_info">
                    <ul>
                        <li><img src="<%=basePath%>${list.shangLogo}"></li>
                        <li>申请对象:${list.shangLinkman}</li>
                        <li>联系电话:${list.shangTelephone}</li>
                        <li>联系QQ:${list.shangContantQq}</li>
                    </ul>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="submitForm()">立即注册</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=basePath%>js/bootstrap.min.js"></script>
<script src="<%=basePath%>js/summernote.min.js"></script>
<script src="<%=basePath%>js/language/summernote-zh-CN.js"></script>
<script src="<%=basePath%>js/common.js"></script>
<script src="<%=basePath%>js/common/common_alert.js"></script>
<script src="<%=basePath%>js/system/payment.js"></script>

<script>

    var falg=false;
    function checkPassword(){
        var password=$("#password").val();
        var repassword=$("#repassword").val();
        if(password!=null && password!='' && repassword!=null && repassword!='' ){
            if(password!=repassword){
                showTipAlert("两次输入的密码不一致!")
                flag=false;
            }else{
                flag=true;
            }
        }
    }
    $(function () {
        $("#editKuaiShangForm").validate();
    });
    function showkuaishang(){
        $("#showKuaiShang").modal("show");

    }
    function hidekuaishang(){
        $("#showKuaiShang").modal("hide");
    }
    function submitForm(){
        if( $("#editKuaiShangForm").valid()&&flag){

            $.ajax({
                type: "post",
                url: "<%=basePath%>applyKuaiShang.htm",
                async: false,
                data: $("#editKuaiShangForm").serialize(),
                traditional: true,
                success: function (data) {
                if("1"==data){
                    window.location.href="<%=basePath%>shopkuaishang.htm";
                }else{
                    showTipAlert("激活快商通失败");
                }
                }
            });

        }

    }
</script>
</body>
</html>


