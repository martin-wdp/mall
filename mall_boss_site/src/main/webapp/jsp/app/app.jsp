<%--
  Created by IntelliJ IDEA.
  User: NP-Heh
  Date: 2015/3/12
  Time: 15:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<!DOCTYPE html>
<html>
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

    <link href="<%=basePath%>css/jquery-ui.min.css" rel="stylesheet">
    <link href="<%=basePath%>css/app.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <script type="text/javascript" src="<%=basePath%>js/artDialog4.1.7/artDialog.source.js?skin=default"></script>
    <script type="text/javascript">var basePath='<%=basePath%>';</script>
</head>
<body>
<!-- 引用头 -->
<jsp:include page="../page/header.jsp"></jsp:include>
<div class="container-fluid page_body" style="padding-left: 0px!important;">
    <div class="row">
        <div class="app-wrap">
            <div class="slides-box">
                <div class="app-slides">
                    <ul>
                        <li><a href="javascript:;"><img src="<%=basePath%>images/slide_01.jpg" class="slide_img" alt="" height="260"></a></li>
                        <li><a href="javascript:;"><img src="<%=basePath%>images/slide_02.jpg" class="slide_img" alt="" height="260"></a></li>
                        <li><a href="javascript:;"><img src="<%=basePath%>images/slide_03.jpg" class="slide_img" alt="" height="260"></a></li>
                    </ul>
                </div>
                <a href="javascript:;" class="slide-prev"></a>
                <a href="javascript:;" class="slide-next"></a>
            </div>
            <div class="app-container">
                <div class="app-sidebar">
                    <h2>授权信息</h2>
                    <ul class="side-box">
                        <li>网站名称：${basicSet.bsetName}</li>
                        <li>商城负责人：${basicSet.bsetAdmin}</li>
                        <li>联系电话：${basicSet.bsetPhone}</li>
                        <li>联系邮箱：${basicSet.bsetEmail}</li>
                        <fmt:formatDate  value="${marketKey.endTime}" pattern="yyyy-MM-dd" var="endTime"  />
                        <li>秘钥有效期至：${endTime}</li>
                    </ul>
                    <%--<ul class="side-box">
                        <li><a href="javascript:;">编辑选荐</a></li>
                        <li><a href="javascript:;">App专题</a></li>
                        <li><a href="javascript:;">游戏专题</a></li>
                        <li><a href="javascript:;">按类别浏览</a></li>
                        <li><a href="javascript:;">Apple App</a></li>
                    </ul>--%>
                </div>
                <div class="app-content">
                    <div class="app-header">
                        <ul class="app-tabs">
                            <li><a class="on" href="javascript:;">我的应用</a></li>
                            <li><a href="javascript:;" id="freeAppsBtn">免费应用</a></li>
                            <li class="underline"></li>
                        </ul>
                        <div class="app-search">
                            <i onclick="loadApps()"></i>
                            <input type="text" class="search-input" placeholder="搜索" id="appName" onkeyup="queryApp()">
                        </div>
                        <div class="select-menu">
                            <strong><span>全部应用</span><i></i></strong>
                            <div class="pop-menu">
                                <a class="selected" href="javascript:;">全部应用</a>
                                <%--<a href="javascript:;">游戏</a>
                                <a href="javascript:;">教育</a>
                                <a href="javascript:;">儿童</a>
                                <a href="javascript:;">报刊杂志</a>
                                <a href="javascript:;">摄影与录像</a>
                                <a href="javascript:;">效率</a>
                                <a href="javascript:;">生活</a>
                                <a href="javascript:;">健康与健美</a>
                                <a href="javascript:;">旅游</a>
                                <a href="javascript:;">音乐</a>--%>
                            </div>
                        </div>
                    </div>
                    <div class="app-lists">
                        <div class="app-list on">
                            <ul id="installedAppsUL">
                                <%--<li>
                                    <a href="javascript:;" class="app-img">
                                        <img src="<%=basePath%>images/placeholder.jpg" data-layzr="<%=basePath%>images/app_01.png" alt="" width="100" height="100">
                                        <span class="app-mask"></span>
                                    </a>
                                    <a class="app-infomation" href="javascript:;">
                                        <h3 class="app-name">QQ</h3>
                                        <p class="app-desc">完美兼容主流浏览器</p>
                                    </a>
                                    <p class="app-version">版本 v0.1</p>
                                    <div class="app-menu">
                                        <strong>
                                            <a class="app-install" href="javascript:;">安装</a>
                                            <span class="menu-down"><i></i></span>
                                        </strong>
                                        <div class="extra-menu">
                                            <a href="javascript:;">赠送此App</a>
                                            <a href="javascript:;">添加至预购清单</a>
                                            <a href="javascript:;">告诉朋友</a>
                                            <a href="javascript:;">在Twitter上分享</a>
                                            <a href="javascript:;">在Facebook上分享</a>
                                            <a href="javascript:;">复制链接</a>
                                        </div>
                                    </div>
                                    <div class="install-process none"><b></b></div>
                                </li>
                                <li>
                                    <a href="javascript:;" class="app-img">
                                        <img src="<%=basePath%>images/placeholder.jpg" data-layzr="<%=basePath%>images/app_02.jpeg" alt="" width="100" height="100">
                                        <span class="app-mask"></span>
                                    </a>
                                    <a class="app-infomation" href="javascript:;">
                                        <h3 class="app-name">地球万象</h3>
                                        <p class="app-desc">深入到地球万象以及塑造它的地质力量</p>
                                    </a>
                                    <p class="app-version">版本 v0.1</p>
                                    <div class="app-menu">
                                        <strong>
                                            <a class="" href="javascript:;">未启用</a>
                                            <span class="menu-down"><i></i></span>
                                        </strong>
                                        <div class="extra-menu">
                                            <a href="javascript:;">赠送此App</a>
                                            <a href="javascript:;">添加至预购清单</a>
                                            <a href="javascript:;">告诉朋友</a>
                                            <a href="javascript:;">在Twitter上分享</a>
                                            <a href="javascript:;">在Facebook上分享</a>
                                            <a href="javascript:;">复制链接</a>
                                        </div>
                                    </div>
                                    <div class="install-process none"><b></b></div>
                                </li>
                                <li>
                                    <a href="javascript:;" class="app-img">
                                        <img src="<%=basePath%>images/placeholder.jpg" data-layzr="<%=basePath%>images/app_03.jpeg" alt="" width="100" height="100">
                                        <span class="app-mask"></span>
                                    </a>
                                    <a class="app-infomation" href="javascript:;">
                                        <h3 class="app-name">Pocket</h3>
                                        <p class="app-desc">Pocket 被 App Store 评为 iPhone 和 iPad 的最佳应用程序之一</p>
                                    </a>
                                    <p class="app-version">版本 v0.1</p>
                                    <div class="app-menu">
                                        <strong>
                                            <a class="" href="javascript:;">已启用</a>
                                            <span class="menu-down"><i></i></span>
                                        </strong>
                                        <div class="extra-menu">
                                            <a href="javascript:;">赠送此App</a>
                                            <a href="javascript:;">添加至预购清单</a>
                                            <a href="javascript:;">告诉朋友</a>
                                            <a href="javascript:;">在Twitter上分享</a>
                                            <a href="javascript:;">在Facebook上分享</a>
                                            <a href="javascript:;">复制链接</a>
                                        </div>
                                    </div>
                                    <div class="install-process none"><b></b></div>
                                </li>
                                <li>
                                    <a href="javascript:;" class="app-img">
                                        <img src="<%=basePath%>images/placeholder.jpg" data-layzr="<%=basePath%>images/app_04.jpeg" alt="" width="100" height="100">
                                        <span class="app-mask"></span>
                                    </a>
                                    <a class="app-infomation" href="javascript:;">
                                        <h3 class="app-name">熊猫博士游乐园</h3>
                                        <p class="app-desc">熊猫博士游乐园来啦</p>
                                    </a>
                                    <p class="app-version">版本 v0.1</p>
                                    <div class="app-menu none">
                                        <strong>
                                            <a class="" href="javascript:;">已启用</a>
                                            <span class="menu-down"><i></i></span>
                                        </strong>
                                        <div class="extra-menu">
                                            <a href="javascript:;">赠送此App</a>
                                            <a href="javascript:;">添加至预购清单</a>
                                            <a href="javascript:;">告诉朋友</a>
                                            <a href="javascript:;">在Twitter上分享</a>
                                            <a href="javascript:;">在Facebook上分享</a>
                                            <a href="javascript:;">复制链接</a>
                                        </div>
                                    </div>
                                    <div class="install-process"><b style="width:60%;"></b></div>
                                </li>--%>
                            </ul>
                        </div>
                        <div class="app-list">
                            <ul id="appListUL">
                                <%--<li>
                                    <a href="javascript:;" class="app-img">
                                        <img src="<%=basePath%>images/placeholder.jpg" data-layzr="<%=basePath%>images/app_01.png" alt="" width="100" height="100">
                                        <span class="app-mask"></span>
                                    </a>
                                    <a class="app-infomation" href="javascript:;">
                                        <h3 class="app-name">QQ</h3>
                                        <p class="app-desc">完美兼容主流浏览器</p>
                                    </a>
                                    <p class="app-version">版本 v0.1</p>
                                    <div class="app-menu">
                                        <strong>
                                            <a class="app-install" href="javascript:;">安装</a>
                                            <span class="menu-down"><i></i></span>
                                        </strong>
                                        <div class="extra-menu">
                                            <a href="javascript:;">赠送此App</a>
                                            <a href="javascript:;">添加至预购清单</a>
                                            <a href="javascript:;">告诉朋友</a>
                                            <a href="javascript:;">在Twitter上分享</a>
                                            <a href="javascript:;">在Facebook上分享</a>
                                            <a href="javascript:;">复制链接</a>
                                        </div>
                                    </div>
                                    <div class="install-process none"><b></b></div>
                                </li>
                                <li>
                                    <a href="javascript:;" class="app-img">
                                        <img src="<%=basePath%>images/placeholder.jpg" data-layzr="<%=basePath%>images/app_02.jpeg" alt="" width="100" height="100">
                                        <span class="app-mask"></span>
                                    </a>
                                    <a class="app-infomation" href="javascript:;">
                                        <h3 class="app-name">地球万象</h3>
                                        <p class="app-desc">深入到地球万象以及塑造它的地质力量</p>
                                    </a>
                                    <p class="app-version">版本 v0.1</p>
                                    <div class="app-menu">
                                        <strong>
                                            <a class="" href="javascript:;">未启用</a>
                                            <span class="menu-down"><i></i></span>
                                        </strong>
                                        <div class="extra-menu">
                                            <a href="javascript:;">赠送此App</a>
                                            <a href="javascript:;">添加至预购清单</a>
                                            <a href="javascript:;">告诉朋友</a>
                                            <a href="javascript:;">在Twitter上分享</a>
                                            <a href="javascript:;">在Facebook上分享</a>
                                            <a href="javascript:;">复制链接</a>
                                        </div>
                                    </div>
                                    <div class="install-process none"><b></b></div>
                                </li>
                                <li>
                                    <a href="javascript:;" class="app-img">
                                        <img src="<%=basePath%>images/placeholder.jpg" data-layzr="<%=basePath%>images/app_04.jpeg" alt="" width="100" height="100">
                                        <span class="app-mask"></span>
                                    </a>
                                    <a class="app-infomation" href="javascript:;">
                                        <h3 class="app-name">熊猫博士游乐园</h3>
                                        <p class="app-desc">熊猫博士游乐园来啦</p>
                                    </a>
                                    <p class="app-version">版本 v0.1</p>
                                    <div class="app-menu none">
                                        <strong>
                                            <a class="" href="javascript:;">已启用</a>
                                            <span class="menu-down"><i></i></span>
                                        </strong>
                                        <div class="extra-menu">
                                            <a href="javascript:;">赠送此App</a>
                                            <a href="javascript:;">添加至预购清单</a>
                                            <a href="javascript:;">告诉朋友</a>
                                            <a href="javascript:;">在Twitter上分享</a>
                                            <a href="javascript:;">在Facebook上分享</a>
                                            <a href="javascript:;">复制链接</a>
                                        </div>
                                    </div>
                                    <div class="install-process"><b style="width:60%;"></b></div>
                                </li>--%>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div><!-- row end -->
</div><!-- container end -->

<div class="modal fade" id="appDetail" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">应用信息</h4>
            </div>
            <div class="modal-body">
                <div class="app-header">
                    <img class="app-icon" src="" alt="">
                    <h3></h3>
                    <p class="app-description" ></p>
                    <p class="app-description"></p>
                    <p class="app-description"></p>
                </div>
                <div class="app-intro">
                    <h3 >应用介绍</h3>
                    <div class="intro-content">

                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <%--<button type="button" class="btn btn-primary" data-dismiss="modal">立即关闭</button>--%>
            </div>
        </div>
    </div>
</div>
<div class="app-wrap">
    <div class="dia-mask"></div>
    <div class="app-dialog">
        <div class="dia-header">
            <h3>应用信息</h3>
            <a href="javascript:;" class="dia-close"></a>
        </div>
        <div class="dia-content">
            <div class="info-sidebar">
                <p class="dia-app-img">
                    <img src="<%=basePath%>images/app_01.png" alt="" width="175" height="175" id="appDetailLogo">
                    <span class="mask"></span>
                </p>
                <div class="dia-app-info">
                    <p>更新日期：<span id="updateTime"></span></p>
                    <p>开发商：千米网</p>
                    <p>兼容性：完美兼容主流浏览器</p>
                    <p>© 2013-2015 Qianmi.com</p>
                </div>
            </div>
            <div class="dia-info-content">
                <div class="dia-app-header">
                    <h2 id="appDetailName"></h2>
                    <p class="app-author">江苏千米网络科技有限公司提供开发</p>
                    <p class="app-sinfo"><span id="appDetailVersion"></span></p>
                    <p class="app-sinfo"><span  id="appMemory"></span></p>
                </div>
                <div class="dia-app-article">
                    <div class="app-intro">
                        <h3>应用简介</h3>
                        <p id="appDetailIntroduction"></p>
                    </div>
                    <div class="app-snapshot">
                        <h3>应用快照</h3>
                        <div class="snapshot-list" id="appDetailDesc">

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<input type="hidden" id="serverUrl" value="${serverUrl}"/>
<input type="hidden" id="CSRFToken" value="${token}"/>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=basePath%>js/bootstrap.min.js"></script>
<script src="<%=basePath%>js/summernote.min.js"></script>
<script src="<%=basePath%>js/language/summernote-zh-CN.js"></script>
<script src="<%=basePath%>js/common/common_date.js"></script>
<script src="<%=basePath%>js/common/common_alert.js"></script>
<script src="<%=basePath%>js/common.js"></script>
<script src="<%=basePath%>js/jquery-ui/jquery-ui-1.10.3.custom.js"></script>
<script src="<%=basePath%>js/app/app.min.js"></script>
<script src="<%=basePath%>js/app/app.js"></script>
<script src="<%=basePath%>js/app/layzr.min.js"></script>
<script src="<%=basePath%>js/app/jcarousellite_1.0.1.js"></script>
</body>
</html>

