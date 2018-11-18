<%--
  Created by IntelliJ IDEA.
  User: NP-Heh
  Date: 2015/3/18
  Time: 9:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <title>高级设置</title>

    <!-- Bootstrap -->
    <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath%>iconfont/iconfont.css" rel="stylesheet">
    <link href="<%=basePath%>css/bootstrap-select.min.css" rel="stylesheet">
    <link href="<%=basePath %>css/select2.min.css" rel="stylesheet">
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
<jsp:include page="../page/header.jsp"></jsp:include>
<div class="page_body container-fluid">
    <div class="row">
        <jsp:include page="../page/left.jsp"></jsp:include>
        <div class="col-lg-20 col-md-19 col-sm-18 main">
            <div class="main_cont">
                 <jsp:include page="../page/breadcrumbs.jsp"></jsp:include>

                <h2 class="main_title">${pageNameChild}</h2>

                <div class="common_data p20">
                    <div class="alert alert-warning alert-dismissible" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <strong>注意!</strong> 高级设置，主要配置后台的相关个性化设置，在不了解的情况下请联系我们的工作人员进行修改。
                    </div>
                    <div class="common_info order_details mt20">
                        <ul class="nav nav-tabs" role="tablist">
                            <li role="presentation" class="active"><a href="#tab1" aria-controls="tab1" role="tab" data-toggle="tab">默认分页行数</a></li>
                            <li role="presentation"><a href="#tab2" aria-controls="tab2" role="tab" data-toggle="tab">登录验证码开关设置</a></li>
                            <li role="presentation"><a href="#tab3" aria-controls="tab2" role="tab" data-toggle="tab">分布式文件系统设置</a></li>
                            <li role="presentation"><a href="#tab4" aria-controls="tab2" role="tab" data-toggle="tab">文件上传设置</a></li>
                            <%--<li role="presentation"><a href="#tab5" aria-controls="tab2" role="tab" data-toggle="tab">后台设置</a></li>--%>
                        </ul>
                        <div class="tab-content">
                            <div role="tabpanel" class="tab-pane active" id="tab1">
                                <div class="common_form p20">
                                    <form class="form-horizontal">
                                        <div class="form-group">
                                            <label class="control-label col-sm-6">默认分页行数：</label>
                                            <div class="col-sm-1"></div>
                                            <div class="col-sm-6 form_item">
                                                <span class="form_value" id="pageRowsSpan">${pageRows }&nbsp;</span>
                                                <div class="form_fill">
                                                    <select class="form-control w100" id="up_pageRows">
                                                        <option>10</option>
                                                        <option selected>15</option>
                                                        <option>20</option>
                                                        <option>30</option>
                                                        <option>50</option>
                                                        <option>100</option>
                                                    </select>
                                                </div>
                                            </div>
                                                <div class="col-sm-3">
				                                    <a href="javascript:;" class="up_pageRowsTip help_tips">
				                                        <i class="icon iconfont">&#xe611;</i>
				                                    </a>
				                                </div>
                                        </div>

                                        <div class="form_btns popover right">
                                            <div class="arrow" style="top:50%"></div>
                                            <h3 class="popover-title">确定修改？</h3>
                                            <div class="popover-content">
                                                <div class="text-center">
                                                    <button type="button" class="btn btn-primary" onclick="savePageRows(this)">确定</button>
                                                    &nbsp;&nbsp;
                                                    <button type="button" class="btn btn-default form_cancel">取消</button>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <div role="tabpanel" class="tab-pane" id="tab2">
                                <div class="common_form p20">
                                    <form class="form-horizontal">
                                        <div class="form-group">
                                            <label class="control-label col-sm-6">后台登录验证码：</label>
                                            <div class="col-sm-1"></div>
                                            <div class="col-sm-14 form_item">
                                            <span class="form_value" id="showpatchaSpan">
                                                <c:if test="${showpatcha==1 }"><span class="label label-success">启用</span></c:if>
                                                <c:if test="${showpatcha==0 }"><span class="label label-default">不启用</span></c:if>
                                            </span>
                                                <div class="form_fill">
                                                    <label class="radio-inline">
                                                        <input type="radio" name="showpatcha" id="open5" value="1" <c:if test="${showpatcha==1 }">checked</c:if>> 启用
                                                    </label>
                                                    <label class="radio-inline">
                                                        <input type="radio" name="showpatcha" id="open6" value="0" <c:if test="${showpatcha==0 }">checked</c:if>> 不启用
                                                    </label>
                                                </div>
                                            </div>
                                            <div class="col-sm-3"></div>
                                        </div>

                                        <div class="form_btns popover right">
                                            <div class="arrow" style="top:50%"></div>
                                            <h3 class="popover-title">确定修改？</h3>
                                            <div class="popover-content">
                                                <div class="text-center">
                                                    <button type="button" class="btn btn-primary" onclick="savePathcSet(this)">确定</button>
                                                    &nbsp;&nbsp;
                                                    <button type="button" class="btn btn-default form_cancel">取消</button>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <div role="tabpanel" class="tab-pane" id="tab3">
                                <%--<div class="alert alert-warning alert-dismissible" role="alert">
                                    <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                    <strong>注意!</strong> 系统相关接口，若修改不当，会影响又拍云不开启情况下的图片上传，请不要随意修改!
                                </div>--%>
                                <div class="common_form p20">
                                    <form class="form-horizontal">
                                        <input type="hidden" id="fastdfsId" value="${fastDFSInfo.fastdfsId}"/>
                                        <div class="form-group">
                                            <label class="control-label col-sm-7">FastDFS路径：</label>
                                            <div class="col-sm-1"></div>
                                            <div class="col-sm-14 form_item">
                                                <span class="form_value" attr_id="serverPath">${fastDFSInfo.serverPath}&nbsp;</span>
                                                <div class="form_fill">
                                                    <input type="text" class="form-control w200" value="${fastDFSInfo.serverPath}" id="serverPath">
                                                </div>
                                            </div>
                                            <div class="col-sm-2"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-7">FastDFS HTTP端口号：</label>
                                            <div class="col-sm-1"></div>
                                            <div class="col-sm-14 form_item">
                                                <span class="form_value" attr_id="httpPort">${fastDFSInfo.httpPort}&nbsp;</span>
                                                <div class="form_fill">
                                                    <input type="text" class="form-control w200" value="${fastDFSInfo.httpPort}" id="httpPort">
                                                </div>
                                            </div>
                                            <div class="col-sm-2"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-7">FastDFS返回路径：</label>
                                            <div class="col-sm-1"></div>
                                            <div class="col-sm-14 form_item">
                                                <span class="form_value" attr_id="resultPath">${fastDFSInfo.resultPath}&nbsp;</span>
                                                <div class="form_fill">
                                                    <input type="text" class="form-control w200" value="${fastDFSInfo.resultPath}" id="resultPath">
                                                </div>
                                            </div>
                                            <div class="col-sm-2"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-7">启用FastDFS：</label>
                                            <div class="col-sm-1"></div>
                                            <div class="col-sm-14 form_item">
                                            <span class="form_value" attr_id="userd" attr_type="radio">
                                                <c:if test="${fastDFSInfo.userd==0 }"><span class="label label-default">不启用</span></c:if>
                                                <c:if test="${fastDFSInfo.userd==1 }"><span class="label label-success">启用</span></c:if>

                                            </span>
                                                <div class="form_fill">
                                                    <label class="radio-inline">
                                                        <input type="radio" name="userd" id="open3" value="1" <c:if test="${fastDFSInfo.userd==1 }">checked</c:if>> 启用
                                                    </label>
                                                    <label class="radio-inline">
                                                        <input type="radio" name="userd" id="open4" value="0" <c:if test="${fastDFSInfo.userd==0 }">checked</c:if>> 不启用
                                                    </label>
                                                </div>
                                            </div>
                                            <div class="col-sm-2"></div>
                                        </div>

                                        <div class="form_btns popover right">
                                            <div class="arrow" style="top:50%"></div>
                                            <h3 class="popover-title">确定修改？</h3>
                                            <div class="popover-content">
                                                <div class="text-center">
                                                    <button type="button" class="btn btn-primary form_fastdfs" id="form_fastdfs">确定</button>
                                                    &nbsp;&nbsp;
                                                    <button type="button" class="btn btn-default form_cancel">取消</button>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <div role="tabpanel" class="tab-pane" id="tab4" style="height:350px;">
                                <div class="common_form p20">
                                    <form class="form-horizontal">
                                        <input type="hidden" id="uploadfilesetId" value="${ufs.uploadfilesetId}">
                                        <div class="form-group">
                                            <label class="control-label col-sm-6">可上传的文件大小：</label>
                                            <div class="col-sm-1"></div>
                                            <div class="col-sm-14 form_item">
                                                <span class="form_value" attr_id="maxSize">${ufs.maxSize}&nbsp;</span>
                                                <div class="form_fill">
                                                    <div class="input-group w200">
                                                        <input type="text" class="form-control" value="${ufs.maxSize}" id="maxSize">
                                                        <span class="input-group-addon">B</span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-sm-3"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-6">可上传文件类型：</label>
                                            <div class="col-sm-1"></div>
                                            <div class="col-sm-14 form_item">
                                                <span class="form_value" attr_id="suffixArray">${ufs.suffixArray}</span>
                                                <div class="form_fill">
                                                    <select class="form-control w200" data-live-search="true" multiple id="suffixArray">
                                                        <c:forEach items="${extendNames}" var="extendName">
                                                        <option <c:if test="${fn:contains(ufs.suffixArray,extendName)}"> selected</c:if>>${extendName}</option>
                                                       <%-- <option <c:if test="${fn:contains(ufs.suffixArray, 'jpeg')}"> selected</c:if>>jpeg</option>
                                                        <option <c:if test="${fn:contains(ufs.suffixArray, 'png')}"> selected</c:if>>png</option>
                                                        <option <c:if test="${fn:contains(ufs.suffixArray, 'gif')}"> selected</c:if>>gif</option>
                                                        <option <c:if test="${fn:contains(ufs.suffixArray, 'bmp')}"> selected</c:if>>bmp</option>
                                                        <option <c:if test="${fn:contains(ufs.suffixArray, 'ico')}"> selected</c:if>>ico</option>--%>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-sm-3"></div>
                                        </div>

                                        <div class="form_btns popover right">
                                            <div class="arrow" style="top:50%"></div>
                                            <h3 class="popover-title">确定修改？</h3>
                                            <div class="popover-content">
                                                <div class="text-center">
                                                    <button type="button" class="btn btn-primary form_sure_upload" id="form_sure_upload">确定</button>
                                                    &nbsp;&nbsp;
                                                    <button type="button" class="btn btn-default form_cancel">取消</button>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <div role="tabpanel" class="tab-pane" id="tab5">
                                <div class="common_form p20">
                                    <%--<form class="form-horizontal">
                                        <input type="hidden" id="basicId" value="${sysBasic.basicId}"/>
                                        <div class="form-group">
                                            <label class="control-label col-sm-6">后台登录版权：</label>
                                            <div class="col-sm-1"></div>
                                            <div class="col-sm-14 form_item">
                                                <span class="form_value" attr_id="temp1">${sysBasic.temp1}&nbsp;</span>
                                                <div class="form_fill">
                                                    <input type="text" class="form-control w200" value="${sysBasic.temp1}" id="temp1">
                                                </div>
                                            </div>
                                            <div class="col-sm-3"></div>
                                        </div>

                                         <div class="form-group">
                                            <label class="control-label col-sm-6">网站备案：</label>
                                            <div class="col-sm-1"></div>
                                            <div class="col-sm-14 form_item">
                                                <span class="form_value" attr_id="temp2">${sysBasic.temp2}&nbsp;</span>
                                                <div class="form_fill">
                                                    <input type="text" class="form-control w200" value="${sysBasic.temp2}" id="temp2">
                                                </div>
                                            </div>
                                            <div class="col-sm-3"></div>
                                        </div>

                                        <div class="form-group">
                                            <label class="control-label col-sm-6">登陆框LOGO：</label>
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

                                        <div class="form_btns popover right">
                                            <div class="arrow" style="top:50%"></div>
                                            <h3 class="popover-title">确定修改？</h3>
                                            <div class="popover-content">
                                                <div class="text-center">
                                                    <button type="button" class="btn btn-primary" id="form_sure_basic">确定</button>
                                                    &nbsp;&nbsp;
                                                    <button type="button" class="btn btn-default form_cancel">取消</button>
                                                </div>
                                            </div>
                                        </div>
                                    </form>--%>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <input type="hidden" id="token" value="${token}">

            </div>

        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="picEdit"  role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">编辑图片</h4>
            </div>
            <div class="modal-body">
                <form role="form" class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-sm-5">选择图片：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <p class="pt5"><input type="file"></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">预览图片：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <img alt="" src="images/qpmall_logo.jpg">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=basePath%>js/bootstrap.min.js"></script>
<script src="<%=basePath%>js/bootstrap-select.min.js"></script>
<script src="<%=basePath%>js/common.js"></script>
<script src="<%=basePath%>js/common/common_alert.js"></script>
<script type="text/javascript" src="<%=basePath%>js/artDialog4.1.7/artDialog.source.js?skin=default"></script>
<script type="text/javascript" src="<%=basePath%>js/artDialog4.1.7/plugins/iframeTools.js"></script>
<script src="<%=basePath%>js/system/advanced_set.js"></script>
<script src="<%=basePath %>js/select2.min.js"></script>
<script type="text/javascript">
$(function(){
	  $('.up_pageRowsTip').popover({
	        content : '只用于后台所有列表页分页行数',
	        trigger : 'hover'
	    });
	
});
</script>
</body>
</html>

