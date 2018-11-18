<%--
  Created by IntelliJ IDEA.
  User: NP-Heh
  Date: 2015/4/18
  Time: 10:00
  To change this template use File | Settings | File Templates.
--%>
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
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link href="iconfont/iconfont.css" rel="stylesheet">
    <link href="css/summernote.css" rel="stylesheet">
    <link href="css/bootstrap-select.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">

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

                <h2 class="main_title">${pageNameChild}</h2>

                <div class="common_info common_tabs mt20">
                    <ul class="nav nav-tabs" role="tablist">
                        <li role="presentation" <c:if test="${location=='1' || location==null}">class="active"</c:if>><a href="#tab1" aria-controls="tab1" role="tab" data-toggle="tab">基本设置</a></li>
                        <li role="presentation" <c:if test="${location=='2'}">class="active"</c:if>><a href="#tab2" aria-controls="tab2" role="tab" data-toggle="tab">咨询设置</a></li>
                        <li role="presentation" <c:if test="${location=='3'}">class="active"</c:if>><a href="#tab3" aria-controls="tab3" role="tab" data-toggle="tab">评论设置</a></li>
                        <%--<li role="presentation" <c:if test="${location=='4'}">class="active"</c:if>><a href="#tab4" aria-controls="tab4" role="tab" data-toggle="tab">留言设置</a></li>--%>
                    </ul>
                    <div class="tab-content">
                        <div role="tabpanel" class="tab-pane <c:if test="${location=='1' || location==null}">active</c:if>" id="tab1">
                            <div class="common_form common_form_lg p20">
                                <form class="form-horizontal" id="common_from" action="updateInfoSetting.htm" method="post">
                                    <input type="hidden" name="CSRFToken" value="${token}"/>
                                    <input type="hidden" name="location" value="1"/>
                                    <div class="form-group">
                                        <label class="control-label col-sm-6">无评论时缺省文字：</label>
                                        <div class="col-sm-1"></div>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control required specstr" name="defaultWord" value="${infoSetting.defaultWord}">
                                        </div>
                                        <div class="col-sm-3"></div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-6">等待审核显示：</label>
                                        <div class="col-sm-1"></div>
                                        <div class="col-sm-10">
                                            <input type="text" name="auditWord" class="form-control required specstr" value="${infoSetting.auditWord}">
                                        </div>
                                        <div class="col-sm-3"></div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-6">发表成功显示：</label>
                                        <div class="col-sm-1"></div>
                                        <div class="col-sm-10">
                                            <input type="text" name="successWord" class="form-control required specstr" value="${infoSetting.successWord}">
                                        </div>
                                        <div class="col-sm-3"></div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-6">发表时输入验证码：</label>
                                        <div class="col-sm-1"></div>
                                        <div class="col-sm-10">
                                            <label class="radio-inline">
                                                <input type="radio" id="Radio1" name="isCheck"  <c:if test="${infoSetting.isCheck!=0 }"> checked="checked" </c:if> value="1"> 是
                                            </label>
                                            <label class="radio-inline">
                                                <input type="radio" id="Radio2" name="isCheck" <c:if test="${infoSetting.isCheck==0 }"> checked="checked" </c:if>   value="0"> 否
                                            </label>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-6">审核设置：</label>
                                        <div class="col-sm-1"></div>
                                        <div class="col-sm-10">
                                            <label class="radio-inline">
                                                <input type="radio" id="Radio3" name="aduitSet" <c:if test="${infoSetting.aduitSet==0 }">checked="checked"  </c:if>  value="0"> 管理员审核后显示
                                            </label>
                                            <label class="radio-inline">
                                                <input type="radio" id="Radio4" name="aduitSet" <c:if test="${infoSetting.aduitSet!=0 }">checked="checked"  </c:if> value="1"> 用户发表后立即显示
                                            </label>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-offset-7 col-sm-9">
                                            <button type="submit" class="btn btn-primary">保存</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div role="tabpanel" class="tab-pane <c:if test="${location=='2'}">active</c:if>" id="tab2">
                            <div class="common_form common_form_lg p20">
                                <form class="form-horizontal" id="consult_from" action="updateInfoSetting.htm" method="post">
                                    <input type="hidden" name="CSRFToken" value="${token}"/>
                                    <input type="hidden" name="location" value="2"/>
                                    <div class="form-group">
                                        <label class="control-label col-sm-6">是否开启咨询功能：</label>
                                        <div class="col-sm-1"></div>
                                        <div class="col-sm-10">
                                            <label class="radio-inline">
                                                <input type="radio" name="isConsult" <c:if test="${infoSetting.isConsult!=0 }"> checked="checked" </c:if> id="Radio5" value="1"> 开启
                                            </label>
                                            <label class="radio-inline">
                                                <input type="radio"  name="isConsult" <c:if test="${infoSetting.isConsult==0 }"> checked="checked" </c:if> id="Radio6" value="0"> 关闭
                                            </label>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-6">发表咨询权限：</label>
                                        <div class="col-sm-1"></div>
                                        <div class="col-sm-10">
                                            <label class="radio-inline">
                                                <input type="radio" name="consultSet" <c:if test="${infoSetting.consultSet!=0 }">checked="checked"  </c:if>  id="Radio7" value="1"> 所有顾客都可咨询
                                            </label>
                                            <label class="radio-inline">
                                                <input type="radio" name="consultSet" <c:if test="${infoSetting.consultSet==0 }">checked="checked"  </c:if> id="Radio8" value="0"> 只有注册会员才能咨询
                                            </label>
                                        </div>
                                    </div>
                                    <%--<div class="form-group">
                                        <label class="control-label col-sm-6">咨询项目：</label>
                                        <div class="col-sm-1"></div>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control">
                                        </div>
                                        <div class="col-sm-3"></div>
                                    </div>--%>
                                    <div class="form-group">
                                        <div class="col-sm-offset-7 col-sm-9">
                                            <button type="submit" class="btn btn-primary">保存</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div role="tabpanel" class="tab-pane <c:if test="${location=='3'}">active</c:if>" id="tab3">
                            <div class="common_form common_form_lg p20">
                                <form class="form-horizontal" action="updateInfoSetting.htm" method="post" id="comment_from">
                                    <input type="hidden" name="CSRFToken" value="${token}"/>
                                    <input type="hidden" name="location" value="3"/>
                                    <div class="form-group">
                                        <label class="control-label col-sm-6">是否开启评论功能：</label>
                                        <div class="col-sm-1"></div>
                                        <div class="col-sm-10">
                                            <label class="radio-inline">
                                                <input type="radio" name="isComment" <c:if test="${infoSetting.isComment!=0 }"> checked="checked" </c:if>  id="Radio9" value="1"> 开启
                                            </label>
                                            <label class="radio-inline">
                                                <input type="radio" name="isComment" <c:if test="${infoSetting.isComment==0 }"> checked="checked" </c:if> id="Radio10" value="0"> 关闭
                                            </label>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-6">发表评论权限：</label>
                                        <div class="col-sm-1"></div>
                                        <div class="col-sm-10">
                                            <label class="radio-inline">
                                                <input type="radio"  name="ccommentSet" <c:if test="${infoSetting.ccommentSet!=0 }">checked="checked"  </c:if> id="Radio11" value="1"> 所有顾客都可咨询
                                            </label>
                                            <label class="radio-inline">
                                                <input type="radio" name="ccommentSet" <c:if test="${infoSetting.ccommentSet==0 }">checked="checked"  </c:if> id="Radio12" value="0"> 只有注册会员才能咨询
                                            </label>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-6">是否开启评分：</label>
                                        <div class="col-sm-1"></div>
                                        <div class="col-sm-10">
                                            <label class="radio-inline">
                                                <input type="radio" name="isScore"<c:if test="${infoSetting.isScore!=0 }">checked="checked"</c:if> id="Radio13" value="1"> 开启
                                            </label>
                                            <label class="radio-inline">
                                                <input type="radio" name="isScore"<c:if test="${infoSetting.isScore==0 }">checked="checked"</c:if> id="Radio14" value="0"> 关闭
                                            </label>
                                        </div>
                                    </div>
                                    <%--<div class="form-group">
                                        <label class="control-label col-sm-6">评分项目：</label>
                                        <div class="col-sm-1"></div>
                                        <div class="col-sm-10">
                                            <label class="radio-inline">
                                                <input type="radio" name="scoreType" checked="checked" id="Radio15" value=""> 综合评分
                                            </label>
                                            <label class="radio-inline">
                                                <input type="radio" name="scoreType" type="radio" name="is_default"  value=""> 尺码
                                            </label>
                                            <label class="radio-inline">
                                                <input type="radio" name="scoreType" type="radio" name="is_default" id="Radio17" value=""> 外观
                                            </label>
                                        </div>
                                    </div>--%>
                                    <div class="form-group">
                                        <div class="col-sm-offset-7 col-sm-9">
                                            <button type="submit" class="btn btn-primary">保存</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <%--<div role="tabpanel" class="tab-pane <c:if test="${location=='4'}">active</c:if>" id="tab4">
                            <div class="common_form common_form_lg p20">
                                <form class="form-horizontal">
                                    <input type="hidden" name="CSRFToken" value="${token}"/>
                                    <div class="form-group">
                                        <label class="control-label col-sm-6">是否开启商店留言功能：</label>
                                        <div class="col-sm-1"></div>
                                        <div class="col-sm-10">
                                            <label class="radio-inline">
                                                <input type="radio" name="inlineRadio9" id="Radio18" checked="checked" value="1"> 开启
                                            </label>
                                            <label class="radio-inline">
                                                <input type="radio" name="inlineRadio9" id="Radio19" value="0"> 关闭
                                            </label>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-6">发表留言权限：</label>
                                        <div class="col-sm-1"></div>
                                        <div class="col-sm-10">
                                            <label class="radio-inline">
                                                <input type="radio" name="inlineRadio10" checked="checked" id="Radio20" value="1"> 是
                                            </label>
                                            <label class="radio-inline">
                                                <input type="radio" name="inlineRadio10" id="Radio21" value="0"> 否
                                            </label>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-offset-7 col-sm-9">
                                            <button type="button" class="btn btn-primary">保存</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>--%>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=basePath%>js/bootstrap.min.js"></script>
<script src="<%=basePath%>js/summernote.min.js"></script>
<script src="<%=basePath%>js/language/summernote-zh-CN.js"></script>
<script src="<%=basePath%>js/bootstrap-select.min.js"></script>
<script src="<%=basePath%>js/common.js"></script>
<script>
$(function() {
    $("#common_from").validate();
    $("#consult_from").validate();
    $("#comment_from").validate();
});
</script>

</body>
</html>

