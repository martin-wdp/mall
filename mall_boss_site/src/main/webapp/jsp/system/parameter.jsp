<%--
  Created by IntelliJ IDEA.
  User: NP-Heh
  Date: 2015/3/12
  Time: 15:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

    <script type="text/javascript" src="<%=basePath%>js/artDialog4.1.7/artDialog.source.js?skin=default"></script>
    <script type="text/javascript" src="<%=basePath%>js/artDialog4.1.7/plugins/iframeTools.js"></script>
</head>
<body>
<!-- 引用头 -->
<jsp:include page="../page/header.jsp"></jsp:include>
<div class="container-fluid page_body">
    <div class="row">
        <jsp:include page="../page/left.jsp"></jsp:include>
        <div class="col-lg-20 col-md-19 col-sm-18 main">
            <div class="main_cont">
                <jsp:include page="../page/breadcrumbs.jsp"></jsp:include>

                <h2 class="main_title">${pageNameChild}</h2>
                <div class="common_data p20">
                    <div class="alert alert-warning alert-dismissible" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <strong>注意!</strong> 基本信息设置，若修改不当，会影响相关网站地址和logo，会造成页面显示和跳转出错，在不了解的情况下请联系我们的工作人员进行修改。
                    </div>
                    <div class="common_info order_details mt20">
                        <ul class="nav nav-tabs" role="tablist">
                            <li role="presentation" class="active"><a href="#tab1" aria-controls="tab1" role="tab" data-toggle="tab">前台基本信息</a></li>
                            <li role="presentation"><a href="#tab2" aria-controls="tab2" role="tab" data-toggle="tab">后台基本信息</a></li>
                        </ul>
                        <div class="tab-content">
                            <div role="tabpanel" class="tab-pane active" id="tab1">
                                <div class="common_form p20">
                                    <form class="form-horizontal" id="editParameterForm">
                                        <input type="hidden" id="bsetId" value=" ${parameter.bsetId}"/>
                                        <input type="hidden" id="CSRFToken" value="${token}">

                                        <%--<div class="form-group">
                                            <label class="control-label col-sm-6">网站描述：</label>
                                            <div class="col-sm-1"></div>
                                            <div class="col-sm-14 form_item">
                                                <span class="form_value form_value_parameter" attr_id="bsetDesc">${parameter.bsetDesc}&nbsp; </span>
                                                <div class="form_fill">
                                                    <textarea id="bsetDesc" class="form-control w300" rows="4" clazz="required specstr">${parameter.bsetDesc}</textarea>
                                                </div>
                                            </div>
                                            <div class="col-sm-3"></div>
                                        </div>--%>
                                        <div class="form-group">
                                            <label class="control-label col-sm-6"><span class="text-danger">*</span>商城前台网址：</label>
                                            <div class="col-sm-1"></div>
                                            <div class="col-sm-14 form_item">
                                                <span class="form_value form_value_parameter" attr_id="bsetAddress">${parameter.bsetAddress }&nbsp;</span>
                                                <div class="form_fill">
                                                    <input type="text" id="bsetAddress" class="form-control w300" value="${parameter.bsetAddress }" clazz="required url">
                                                </div>
                                            </div>
                                            <div class="col-sm-3"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-6"><span class="text-danger">*</span>商家入口：</label>
                                            <div class="col-sm-1"></div>
                                            <div class="col-sm-10 form_item">
                                                <span class="form_value form_value_parameter" attr_id="bsetThirdAddress">${parameter.bsetThirdAddress }&nbsp;</span>
                                                <div class="form_fill">
                                                    <input type="text" id="bsetThirdAddress" class="form-control w250" value="${parameter.bsetThirdAddress }" clazz="required url">
                                                </div>
                                            </div>
                                            <div class="col-sm-3">
                                                <a href="javascript:;" class="bsetThirdAddress help_tips">
                                                    <i class="icon iconfont">&#xe611;</i>
                                                </a>
                                            </div>
                                        </div>
                                        <%--<div class="form-group">
                                            <label class="control-label col-sm-6">商城主域名：</label>
                                            <div class="col-sm-1"></div>
                                            <div class="col-sm-14 form_item">
                                                <span class="form_value form_value_parameter" attr_id="bsetDomain">${parameter.bsetDomain }&nbsp;</span>
                                                <div class="form_fill">
                                                    <input type="text" id="bsetDomain" class="form-control w300" value="${parameter.bsetDomain }" clazz="required url">
                                                </div>
                                            </div>
                                            <div class="col-sm-3"></div>
                                        </div>--%>
                                        <%--<div class="form-group">
                                            <label class="control-label col-sm-6">电子邮箱：</label>
                                            <div class="col-sm-1"></div>
                                            <div class="col-sm-14 form_item">
                                                <span class="form_value form_value_parameter" attr_id="bsetEmail">${parameter.bsetEmail }</span>
                                                <div class="form_fill">
                                                    <input type="text" id="bsetEmail" class="form-control w300" value="${parameter.bsetEmail }" clazz="required email">
                                                </div>
                                            </div>
                                            <div class="col-sm-3"></div>
                                        </div>--%>
                                        <div class="form-group">
                                            <label class="control-label col-sm-6">网站LOGO：</label>
                                            <div class="col-sm-1"></div>
                                            <div class="col-sm-10">
                                                <a href="javascript:;" class="choose_img_btn" attr_id="bsetLogo">
                                                    <img src="${parameter.bsetLogo }" alt="" id="bsetLogo_pic" style="max-width:290px;max-height:300px;">
                                                </a>
                                            </div>
                                            <div class="col-sm-3">
                                                <a href="javascript:;" class="weblogo help_tips">
                                                    <i class="icon iconfont">&#xe611;</i>
                                                </a>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-6">商家登陆LOGO：</label>
                                            <div class="col-sm-1"></div>
                                            <div class="col-sm-10">
                                                <a href="javascript:;" class="choose_img_btn_third" attr_id="bsetThirdLogo">
                                                    <img src="${parameter.bsetThirdLogo }" alt="" id="bsetThirdLogo" style="max-width:290px;max-height:300px;">
                                                </a>
                                            </div>
                                            <div class="col-sm-3">
                                                <a href="javascript:;" class="weblogothird help_tips">
                                                    <i class="icon iconfont">&#xe611;</i>
                                                </a>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-6">标签页图标：</label>
                                            <div class="col-sm-1"></div>
                                            <div class="col-sm-14">
                                                <a href="javascript:;" class="choose_img_btn" attr_id="bsetHotline">
                                                    <img src="${parameter.bsetHotline }" alt="" id="bsetHotline_pic" style="max-width:290px;max-height:300px;">
                                                </a>
                                            </div>
                                            <div class="col-sm-3">
                                                <a href="javascript:;" class="bsetHotline help_tips">
                                                    <i class="icon iconfont">&#xe611;</i>
                                                </a>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-6">前台登录页面图片：</label>
                                            <div class="col-sm-1"></div>
                                            <div class="col-sm-14">
                                                <a href="javascript:;" class="choose_img_btn" attr_id="siteLoginImg" >
                                                    <c:if test="${parameter.siteLoginImg==''||parameter.siteLoginImg==null}">
                                                        <img src="${basePath}/images/images_30.jpg" alt="" id="siteLoginImg_pic" style="max-width:290px;max-height:300px;">
                                                    </c:if>
                                                    <img src="${parameter.siteLoginImg }" alt="" id="siteLoginImg_pic" style="max-width:290px;max-height:300px;">
                                                </a>
                                            </div>
                                            <div class="col-sm-3">
                                                <a href="javascript:;" class="loginImg help_tips">
                                                    <i class="icon iconfont">&#xe611;</i>
                                                </a>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-6">前台注册页面图片：</label>
                                            <div class="col-sm-1"></div>
                                            <div class="col-sm-14">
                                                <a href="javascript:;" class="choose_img_btn" attr_id="siteRegImg" >
                                                    <c:if test="${parameter.siteLoginImg==''|| parameter.siteLoginImg==null}">
                                                        <img src="${basePath}/images/images_30.jpg" alt="" id="siteRegImg_pic" style="max-width:290px;max-height:300px;">
                                                    </c:if>
                                                    <img src="${parameter.siteRegImg }" alt="" id="siteRegImg_pic" style="max-width:290px;max-height:300px;">
                                                </a>
                                            </div>
                                            <div class="col-sm-3">
                                                <a href="javascript:;" class="regImg help_tips">
                                                    <i class="icon iconfont">&#xe611;</i>
                                                </a>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-6">前台注册成功后页面图片：</label>
                                            <div class="col-sm-1"></div>
                                            <div class="col-sm-14">
                                                <a href="javascript:;" class="choose_img_btn" attr_id="siteRegSuccImg" >
                                                    <c:if test="${parameter.siteRegSuccImg==''|| parameter.siteRegSuccImg==null}">
                                                        <img src="${basePath}/images/bg_success.jpg" alt="" id="siteRegSuccImg_pic" style="max-width:290px;max-height:300px;">
                                                    </c:if>
                                                    <c:if test="${parameter.siteRegSuccImg!=''&& parameter.siteRegSuccImg!=null}">
                                                        <img src="${parameter.siteRegSuccImg }" alt="" id="siteRegSuccImg_pic" style="max-width:290px;max-height:300px;">
                                                    </c:if>

                                                </a>
                                            </div>
                                            <div class="col-sm-3">
                                                <a href="javascript:;" class="regImg help_tips">
                                                    <i class="icon iconfont">&#xe611;</i>
                                                </a>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-6">商家登录页面图片：</label>
                                            <div class="col-sm-1"></div>
                                            <div class="col-sm-14">
                                                <a href="javascript:;" class="choose_img_btn" attr_id="thirdLoginImg">
                                                    <c:if test="${parameter.thirdLoginImg==''||parameter.thirdLoginImg==null}">
                                                        <img src="${basePath}/images/log_pic.jpg" alt="" id="thirdLoginImg_pic" style="max-width:290px;max-height:300px;">&nbsp;&nbsp;
                                                    </c:if>
                                                    <img src="${parameter.thirdLoginImg }" alt="" id="thirdLoginImg_pic" style="max-width:290px;max-height:300px;">&nbsp;&nbsp;
                                                </a>
                                            </div>
                                            <div class="col-sm-3">
                                                <a href="javascript:;" class="loginImg_third help_tips">
                                                    <i class="icon iconfont">&#xe611;</i>
                                                </a>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-6">商家注册页面图片：</label>
                                            <div class="col-sm-1"></div>
                                            <div class="col-sm-14">
                                                <a href="javascript:;" class="choose_img_btn" attr_id="thirdRegImg">
                                                    <c:if test="${parameter.thirdRegImg==''||parameter.thirdRegImg==null}">
                                                        <img src="${basePath}/images/log_pic.jpg" alt="" id="thirdRegImg_pic" style="max-width:290px;max-height:300px;">&nbsp;&nbsp;
                                                    </c:if>
                                                    <img src="${parameter.thirdRegImg }" alt="" id="thirdRegImg_pic" style="max-width:290px;max-height:300px;">&nbsp;&nbsp;
                                                </a>
                                            </div>
                                            <div class="col-sm-3">
                                                <a href="javascript:;" class="loginImg_third help_tips">
                                                    <i class="icon iconfont">&#xe611;</i>
                                                </a>
                                            </div>
                                        </div>

                                        <%--Copyright © 2004-2014  江苏千米网络科技有限公司 版权所有--%>
                                        <div class="form-group">
                                            <label class="control-label col-sm-6"><span class="text-danger"></span>商家版权信息：</label>
                                            <div class="col-sm-1"></div>
                                            <div class="col-sm-16 form_item">
                                                <span class="form_value form_value_parameter" attr_id="thirdCopyright">${parameter.thirdCopyright }&nbsp;</span>
                                                <div class="form_fill">
                                                    <input type="text" id="thirdCopyright" class="form-control w250" value="${parameter.thirdCopyright }" >
                                                </div>
                                            </div>
                                            <div class="col-sm-3">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-6">前台版权信息：</label>
                                            <div class="col-sm-1"></div>
                                            <div class="col-sm-14 form_item">
                                                <button type="button" class="btn btn-default" onclick="$('#rightinfo').modal('show')">查看并修改</button>
                                            </div>
                                            <div class="col-sm-3"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-6">用户注册协议：</label>
                                            <div class="col-sm-1"></div>
                                            <div class="col-sm-14 form_item">
                                                <button type="button" class="btn btn-default" onclick="$('#protocal').modal('show')">查看并修改</button>
                                            </div>
                                            <div class="col-sm-3"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-6">企业认证协议：</label>
                                            <div class="col-sm-1"></div>
                                            <div class="col-sm-14 form_item">
                                                <button type="button" class="btn btn-default" onclick="$('#enterpriseprotocal').modal('show')">查看并修改</button>
                                            </div>
                                            <div class="col-sm-3"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-6">商家协议：</label>
                                            <div class="col-sm-1"></div>
                                            <div class="col-sm-14 form_item">
                                                <button type="button" class="btn btn-default" onclick="$('#thirdprotocal').modal('show')">查看并修改</button>
                                            </div>
                                            <div class="col-sm-3"></div>
                                        </div>

                                        <div class="form-group">
                                            <label class="control-label col-sm-6">商家开店协议：</label>
                                            <div class="col-sm-1"></div>
                                            <div class="col-sm-14 form_item">
                                                <button type="button" class="btn btn-default" onclick="$('#protocal_user').modal('show')">查看并修改</button>
                                            </div>
                                            <div class="col-sm-3"></div>
                                        </div>
                                        <div class="form_btns popover right" id="form_btns_parameter">
                                            <div class="arrow" style="top:50%"></div>
                                            <h3 class="popover-title">确定修改？</h3>
                                            <div class="popover-content">
                                                <div class="text-center">
                                                    <button type="button" class="btn btn-primary form_sure" id="form_sure_parameter">确定</button>
                                                    &nbsp;&nbsp;
                                                    <button type="button" class="btn btn-default form_cancel" id="form_cancel_parameter">取消</button>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <div role="tabpanel" class="tab-pane" id="tab2">
                                <div class="common_form p20">
                                    <form class="form-horizontal" id="editParameterForm1">
                                        <div class="form-group">
                                            <label class="control-label col-sm-6"><span class="text-danger">*</span>商城负责人：</label>
                                            <div class="col-sm-1"></div>
                                            <div class="col-sm-14 form_item">
                                                <span class="form_value form_value_parameter1" attr_id="bsetAdmin">${parameter.bsetAdmin }&nbsp;</span>
                                                <div class="form_fill">
                                                    <input type="text" id="bsetAdmin" class="form-control w300" value="${parameter.bsetAdmin }" clazz="required specstr">
                                                </div>
                                            </div>
                                            <div class="col-sm-3"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-6"><span class="text-danger">*</span>联系电话：</label>
                                            <div class="col-sm-1"></div>
                                            <div class="col-sm-14 form_item">
                                                <span class="form_value form_value_parameter1" attr_id="bsetPhone">${parameter.bsetPhone }&nbsp;</span>
                                                <div class="form_fill">
                                                    <input type="text" id="bsetPhone" class="form-control w300" value="${parameter.bsetPhone }" clazz="required mobile">
                                                </div>
                                            </div>
                                            <div class="col-sm-3"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-6"><span class="text-danger">*</span>网站后台名称：</label>
                                            <div class="col-sm-1"></div>
                                            <div class="col-sm-14 form_item">
                                                <span class="form_value form_value_parameter1" attr_id="bsetName">${parameter.bsetName }&nbsp;</span>
                                                <div class="form_fill">
                                                    <input type="text" id="bsetName" name="bsetName" class="form-control w300" value="${parameter.bsetName }" clazz="required specstr">
                                                </div>
                                            </div>
                                            <div class="col-sm-3"></div>
                                        </div>
                                        <div class="form_btns popover right" id="form_btns_parameter1">
                                            <div class="arrow" style="top:50%"></div>
                                            <h3 class="popover-title">确定修改？</h3>
                                            <div class="popover-content">
                                                <div class="text-center">
                                                    <button type="button" class="btn btn-primary form_sure" id="form_sure_parameter1">确定</button>
                                                    &nbsp;&nbsp;
                                                    <button type="button" class="btn btn-default form_cancel" id="form_cancel_parameter1">取消</button>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                    <form class="form-horizontal">
                                        <input type="hidden" id="basicId" value="${sysBasic.basicId}"/>
                                        <div class="form-group">
                                            <label class="control-label col-sm-6">后台登录版权：</label>
                                            <div class="col-sm-1"></div>
                                            <div class="col-sm-14 form_item">
                                                <span class="form_value form_value_basic" attr_id="temp1">&nbsp;${sysBasic.temp1}</span>
                                                <div class="form_fill">
                                                    <input type="text" class="form-control w200" value="${sysBasic.temp1}" id="temp1">
                                                </div>
                                            </div>
                                            <div class="col-sm-3"></div>
                                        </div>

                                        <div class="form-group">
                                            <label class="control-label col-sm-6">网站后台备案：</label>
                                            <div class="col-sm-1"></div>
                                            <div class="col-sm-14 form_item">
                                                <span class="form_value form_value_basic" attr_id="temp2">${sysBasic.temp2}&nbsp;</span>
                                                <div class="form_fill">
                                                    <input type="text" class="form-control w200" value="${sysBasic.temp2}" id="temp2">
                                                </div>
                                            </div>
                                            <div class="col-sm-3"></div>
                                        </div>

                                        <div class="form-group">
                                            <label class="control-label col-sm-6">后台登陆框LOGO：</label>
                                            <div class="col-sm-1"></div>
                                            <div class="col-sm-14" style="margin-top: 10px;background-color:#C0C0C0;">
                                                <a href="javascript:;" class="choose_img_btn" attr_id="loginLogo">
                                                    <img src="${sysBasic.loginLogo}" alt="${sysBasic.bsetName}" id="loginLogo_pic">
                                                </a>
                                            </div>
                                            <div class="col-sm-3">
                                                <a href="javascript:;" class="loginlogo help_tips">
                                                    <i class="icon iconfont">&#xe611;</i>
                                                </a>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-6">后台首页LOGO：</label>
                                            <div class="col-sm-1"></div>
                                            <div class="col-sm-14" style="margin-top: 10px;background-color:#C0C0C0;">
                                                <a href="javascript:;" class="choose_img_btn" attr_id="indexLogo">
                                                    <img src="${sysBasic.indexLogo}" alt="" id="indexLogo_pic">
                                                </a>
                                            </div>
                                            <div class="col-sm-3">
                                                <a href="javascript:;" class="indexlogo help_tips">
                                                    <i class="icon iconfont">&#xe611;</i>
                                                </a>
                                            </div>
                                        </div>
                                        <div class="form_btns popover right" id="form_btns_basic">
                                            <div class="arrow" style="top:50%"></div>
                                            <h3 class="popover-title">确定修改？</h3>
                                            <div class="popover-content">
                                                <div class="text-center">
                                                    <button type="button" class="btn btn-primary" id="form_sure_basic">确定</button>
                                                    &nbsp;&nbsp;
                                                    <button type="button" class="btn btn-default form_cancel" id="form_cancel_basic">取消</button>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <input type="hidden" id="suffixArray" value="${ufs.suffixArray}"/>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="rightinfo"  role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">编辑版权信息</h4>
            </div>
            <div class="modal-body">
                <form role="form" class="form-horizontal">
                    <div class="summernote" id="bsetCopyright">${parameter.bsetCopyright}</div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="saveBigText('bsetCopyright',this)">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="enterpriseprotocal"  role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">编辑版权信息</h4>
            </div>
            <div class="modal-body">
                <form role="form" class="form-horizontal">
                    <div class="summernote" id="bsetEnterpriseagreement">${parameter.bsetEnterpriseagreement}</div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="saveBigText('bsetEnterpriseagreement',this)">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--编辑企业认证协议-->
<%--<div class="modal fade" id="enterpriseprotocal"  role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">编辑企业认证协议</h4>
            </div>
            <div class="modal-body">
                <form role="form" class="form-horizontal">
                    <div class="summernote" id="bsetEnterpriseagreement">${parameter.bsetEnterpriseagreement}</div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="saveBigText('bsetEnterpriseagreement',this)">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>--%>

<!--用户注册协议-->
<div class="modal fade" id="protocal_user"  role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">编辑商家开店协议</h4>
            </div>
            <div class="modal-body">
                <form role="form" class="form-horizontal">
                    <div class="summernote" id="bsetUseragreementuser">${parameter.bsetUseragreementuser}</div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="saveBigText('bsetUseragreementuser',this)">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!-- Modal -->
<div class="modal fade" id="protocal"  role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">编辑用户注册协议</h4>
            </div>
            <div class="modal-body">
                <form role="form" class="form-horizontal">
                    <div class="summernote" id="bsetUseragreement">${parameter.bsetUseragreement}</div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="saveBigText('bsetUseragreement',this)">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
    <%--商家用户协议--%>
</div><div class="modal fade" id="thirdprotocal"  role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">编辑商家用户协议</h4>
            </div>
            <div class="modal-body">
                <form role="form" class="form-horizontal">
                    <div class="summernote" id="thirdUserment">${parameter.thirdUserment}</div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="saveBigText('thirdUserment',this)">保存</button>
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
<script src="<%=basePath%>js/system/parameter.js"></script>
</body>
</html>

