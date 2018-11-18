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
    <link href="<%=basePath %>css/bootstrap.min.css" rel="stylesheet">
    <link rel="<%=basePath %>stylesheet" href="css/font-awesome.min.css">
    <link href="<%=basePath %>iconfont/iconfont.css" rel="stylesheet">
    <link href="<%=basePath %>css/summernote.css" rel="stylesheet">
    <link href="<%=basePath %>css/bootstrap-select.min.css" rel="stylesheet">
    <link href="<%=basePath %>css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <link href="<%=basePath %>css/zTreeStyle/zTreeStyle.css" rel="stylesheet">
    <link href="<%=basePath %>css/style.css" rel="stylesheet">
    <link href="<%=basePath %>css/vsStyle/jquery.treeTable.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<style>
    .main_cont{
        background: #fff;
        overflow-y: visible;
    }
    .lianjie{
        padding-left: 160px;
        color: red;
    }
</style>
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

                <div class="common_info order_details mt20">
                    <div role="tabpanel" class="tab-pane" id="tab4">
                        <div class="data_ctrl_area mb20">
                            <div class="data_ctrl_search pull-right"></div>
                            <div class="data_ctrl_brns pull-left">
                                <button type="button" class="btn btn-info" onclick="add_image()">
                                    <i class="glyphicon glyphicon-plus"></i> 添加
                                </button>
                                <button type="button" class="btn btn-info" onclick="doAjaxShowDeleteBatchConfirmAlert('delForm','adverId','deletetempadverajax.htm',15)">
                                    <i class="glyphicon glyphicon-trash"></i> 批量删除
                                </button>
                            </div>
                            <div class="clr"></div>
                        </div>
                        <form id="delForm">
                            <table class="table table-striped table-hover">
                                <thead>
                                <tr>
                                    <th><input type="checkbox" onclick="allunchecked(this,'adverId')" ></th>
                                    <th>广告名称</th>
                                    <th>图片</th>
                                    <th>链接地址</th>
                                    <th>排序</th>
                                    <th>是否启用</th>
                                    <th>图片描述</th>
                                    <th width="150">操作</th>
                                </tr>
                                </thead>
                                <input type="hidden" name="CSRFToken" value="${token}"/>
                                <c:if test="${not empty map.channelAdvers}">
                                    <c:forEach var="channelAdvers" items="${map.channelAdvers}">
                                        <tbody id="adverHtml">
                                            <tr>
                                                <td><input type="checkbox" name="adverId" value="${channelAdvers.channelAdverId}"></td>
                                                <td>${channelAdvers.adverName}</td>
                                                <td><img src="${channelAdvers.adverPath}" alt="${channelAdvers.adverName}" height="50"/></td>
                                                <td>${channelAdvers.adverHref}</td>
                                                <td>${channelAdvers.adverSort}</td>
                                                <td>

                                                    <c:if test="${channelAdvers.userStatus==1}">
                                                        <span class="label label-success">是</span>
                                                    </c:if>
                                                    <c:if test="${channelAdvers.userStatus==0}">
                                                        <span class="label label-default">否</span>
                                                    </c:if>
                                                </td>
                                                <td>${channelAdvers.des}</td>
                                                <td>
                                                    <div class="btn-group">
                                                    <button type="button" class="btn btn-default" onclick="showAdver('${channelAdvers.channelAdverId}')">编辑</button>
                                                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                                        <span class="caret"></span>
                                                        <span class="sr-only">Toggle Dropdown</span>
                                                    </button>
                                                    <ul class="dropdown-menu" role="menu">
                                                        <li><a href="javascript:void(0);" onclick="delAdverById('deletetempadverajax.htm?adverId='+${channelAdvers.channelAdverId})">删除</a></li>
                                                    </ul>
                                                </div>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </c:forEach>
                                </c:if>
                            </table>
                        </form>
                        <div class="table_foot" id="adverFootHtml">
                            <div class="table_pagenav pull-right">
                                <div class="meneame">
                                    <span class="disabled"> 上一页 </span>
                                    <span class="current"> 1 </span>
                                    <a href="#?page=2"> 下一页 </a>
                                </div>
                            </div>
                            <div class="pull-left">
                                <form role="form" class="form-horizontal">
                                    <label class="control-label col-sm-12">每页显示：</label>
                                    <div class="col-sm-12">
                                        <select class="form-control">
                                            <option>10</option>
                                            <option>20</option>
                                            <option>50</option>
                                            <option>100</option>
                                        </select>
                                    </div>
                                </form>
                            </div>
                            <div class="clr"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 添加成功 -->
<div class="modal fade" id="add_Success"  role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">操作提示</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label class="modal-body" style="text-align: center;">添加成功！</label>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button"   class="btn btn-default" data-dismiss="modal">确定</button>
            </div>
        </div>
    </div>
</div>


<!-- 添加店铺街轮播大广告弹出框 -->
<div class="modal fade" id="addSlideAd"  role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="showAdverTitle">添加广告</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="subForm_Adver">
                    <input type="hidden" name="CSRFToken" value="${token}"/>
                    <input type="hidden" name="temp5" id="up_temp1" value="3">
                    <input type="hidden" name="channelAdverId" id="upChannelAdverId"/>
                    <div class="form-group">
                        <label class="control-label col-sm-6"><span style="color: red;">*</span>广告名称：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <input type="text" id="up_adverName" name="adverName" class="form-control required specstr">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-6">广告图片：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <input name="adverPath" id="adverPath" type="hidden" value="">
                            <img alt="" id="adverPath_y" src="<%=basePath %>images/default_head.jpg" height="50"> <input type="button"   onclick="saveImg(3)" value="选择"/>
                        </div>
                       <%-- <div class="col-sm-3">
                            <a href="javascript:;" class="bigAdv help_tips">
                                <i class="icon iconfont">&#xe611;</i>
                            </a>
                        </div>--%>

                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-6">广告链接地址：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <input type="text" id="up_adverHerf" name="adverHref" class="form-control">
                        </div>
                        <sapn></sapn>
                    </div>
                    <p class="lianjie">注:链接地址以https开头,例:https://www.baidu.com/</p>
                    <div class="form-group">
                        <label class="control-label col-sm-6"><span style="color: red;">*</span>排序：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-4">
                            <input type="text" id="up_adverSort"
                                   onkeyup="this.value=this.value.replace(/[^\d]/g,'')"
                                   onafterpaste="this.value=this.value.replace(/[^\d]/"

                                   name="adverSort" class="form-control w100 digits  isNumber  required specstr">
                        </div>
                        <%--<div class="col-sm-3">
                            <a href="javascript:;" class="paixu help_tips">
                                <i class="icon iconfont">&#xe611;</i>
                            </a>
                        </div>--%>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-6">是否启用：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <label class="radio-inline">
                                <input type="radio" id="up_adverStatus1" name="userStatus" checked="checked" value="1"> 是
                            </label>
                            <label class="radio-inline">
                                <input type="radio" id="up_adverStatus0" name="userStatus" value="0"> 否
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-6">广告描述：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <textarea rows="5" id="up_adverDes" name="des" class="form-control"></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" id="subAdverButton" onclick="submitAdver()" class="btn btn-primary">确定</button>
                <button type="button"   class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>


<div class="modal fade" id="addFloorBrands"  role="dialog"></div>

<c:forEach items="${brand}" var="b" varStatus="sta">
    <input type="hidden" value="${b.brandLogo}" brandName="${b.brandName}" id="trademark${b.brandName}">
</c:forEach>
<input type="hidden" id="CSRFToken" value="${token}"/>

<input type="hidden" id="imgPath" value="">

<input type="hidden" id="imgPathValue" value="">
<input type="hidden" id="isAdver" value="">
<input type="hidden" id="isBrand" value="">

<input type="hidden" id="status" value="${status}">
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=basePath %>js/bootstrap.min.js"></script>
<script src="<%=basePath %>js/summernote.min.js"></script>
<script src="<%=basePath %>js/language/summernote-zh-CN.js"></script>
<script src="<%=basePath %>js/bootstrap-select.min.js"></script>
<script src="<%=basePath %>js/bootstrap-datetimepicker.min.js"></script>
<script src="<%=basePath %>js/language/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="<%=basePath %>js/jquery.ztree.core-3.5.min.js"></script>
<script src="<%=basePath %>js/jquery.ztree.excheck-3.5.min.js"></script>
<script src="<%=basePath %>js/jqtreetable.js"></script>
<script src="<%=basePath %>js/common.js"></script>
<script src="<%=basePath %>/js/storelist/store_list_image.js"></script>
<script src="<%=basePath %>/js/temp/temp_classifyBar_list.js"></script>
<script src="<%=basePath %>/js/common/common_alert.js"></script>
<script src="<%=basePath %>/js/common/common_temp_alert.js"></script>
<script src="<%=basePath %>/js/common/common_checked.js"></script>
<script src="<%=basePath %>/js/temp/tag.js"></script>
<script src="<%=basePath %>/js/jquery.treeTable.js"></script>
<script>
    $(function(){
        if($("#status").val()==1){
            $(".tab-pane").removeClass("active");
            $("#tab2").addClass("active");
            $("#_tab1").addClass("active");
            $("#_tabl12").removeClass("active");
            showTempCategory();
        }else if($("#status").val()==2){
            $(".tab-pane").removeClass("active");
            $("#tab4").addClass("active");
            $("#_tab2").addClass("active");
            $("#_tabl12").removeClass("active");
            showAdverPage(157);
        }else if($("#status").val()==3){
            $(".tab-pane").removeClass("active");
            $("#tab4").addClass("active");
            $("#_tab3").addClass("active");
            $("#_tabl12").removeClass("active");
            showAdverPage(159);
        }else if($("#status").val()==4){
            //新闻公告
            $(".tab-pane").removeClass("active");
            $("#tab6").addClass("active");
            $("#_tab5").addClass("active");
            $("#_tabl12").removeClass("active");
            showTempInfoType();
        }else if($("#status").val()==5){
            //页面广告
            showAdverPage(161)
            $(".tab-pane").removeClass("active");
            $("#tab4").addClass("active");
            $("#_tab4").addClass("active");
            $("#_tabl12").removeClass("active");
        }else if($("#status").val()==6){
            //页面广告
            $(".tab-pane").removeClass("active");
            $("#tab7").addClass("active");
            $("#_tab6").addClass("active");
            $("#_tabl12").removeClass("active");
            showTempStorey();
        }else if($("#status").val()==7){
            //页面广告
            showAdverPage(161)
            $(".tab-pane").removeClass("active");
            $("#tab8").addClass("active");
            $("#_tab7").addClass("active");
            $("#_tabl12").removeClass("active");
            showHotSearch();
        }else if($("#status").val()==8){
            //页面广告
            showAdverPage(161)
            $(".tab-pane").removeClass("active");
            $("#tab9").addClass("active");
            $("#_tab8").addClass("active");
            $("#_tabl12").removeClass("active");
            showStoreyGoods();
        }
        $("#goodsCateName1").val($("#goodsCate1").find("option:selected").text());
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
        $('select[data-live-search="true"]').selectpicker();
        /* 为选定的select下拉菜单开启搜索提示 END */

        /* 富文本编辑框 */
        $('.summernote').summernote({
            height: 300,
            tabsize: 2,
            lang: 'zh-CN'
        });

        /* 下面是表单里面的填写项提示相关的 */
        $('.paixu').popover({
            content : '数字越小越靠前',
            trigger : 'hover'
        });
        $('.dhurl').popover({
            content : '外部地址需要加：http://',
            trigger : 'hover'
        });
        $('.xsfldh').popover({
            content : '此设置作用于除首页以外的页面',
            trigger : 'hover'
        });
        $('.ymsmnr').popover({
            content : '多条以“|”隔开',
            trigger : 'hover'
        });

        /* 高级搜索 */
        $('.advanced_search').popover({
            html : true,
            title : '高级搜索',
            content : $('.advanced_search_cont').html(),
            trigger : 'click',
            placement : 'bottom'
        }).click(function(){
            $('.cate_selector').selectpicker('refresh');
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

        /* 单选按钮点击切换 */
        $('input[name="zdyspfl"]').change(function(){
            if($(this).val() == 0){
                $('.zdyfl_yes').hide();
                $('.zdyfl_no').show();
                $(isdefi).val(-1);
            }
            else{
                $('.zdyfl_yes').show();
                $('.zdyfl_no').hide();
                $(isdefi).val(0);

            }
        });
        $('input[name="showType"]').change(function(){
            if($(this).val() == 1){
                $("#up_classifyCate").removeClass("required");
                $("#up_classifyCate").removeClass("specstr");
                $('.zslx_yes').hide();
                $('.zslx_no').show();
            }
            else{
                $("#up_classifyCate").addClass("required");
                $("#up_classifyCate").addClass("specstr");
                $('.zslx_yes').show();
                $('.zslx_no').hide();
            }
        });

        /* 下面是关于树形菜单 */
        var setting = {
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback: {
                onClick: onCheck
            }
        };
        $.post("queryallgoodcate.htm?CSRFToken=${token}",function(data) {
            var zNodes = new Array();
            for(var i=0;i<data.length;i++){
                if(data[i].catParentId==null || data[i].catParentId==0){
                    var node = {
                        id: data[i].catId , pId: 0, name:  data[i].catName , open: false
                    }
                    zNodes.push(node);
                }else{
                    var node = {
                        id: data[i].catId, pId:data[i].catParentId, name:data[i].catName
                    }
                    zNodes.push(node);
                }
            }
            var zTree;
            $.fn.zTree.init($("#kjfl1"), setting, zNodes);
            $.fn.zTree.init($("#kjfl2"), setting, zNodes);
            $.fn.zTree.init($("#kjfl3"), setting, zNodes);
            $.fn.zTree.init($("#kjfl4"), setting, zNodes);
        });
    });

    function onCheck(e, treeId, treeNode){
        if(treeId=="kjfl1"){
            $("#barQuickName1").val("");
            $("#barQuickIds1").val(0);
            var zTree = $.fn.zTree.getZTreeObj("kjfl1");
            nodes = zTree.getSelectedNodes(true);
            var s = '';
            if(nodes != null && nodes.length>0){
                for(var i = 0; i< nodes.length;i++){
                    if(nodes[i].level==3){
                        $("#barQuickName1").val(nodes[i].name);
                        $("#barQuickIds1").val(nodes[i].id);
                    }
                }
            }
        }
        if(treeId=="kjfl2"){
            $("#barQuickName2").val("");
            $("#barQuickIds2").val(0);
            var zTree = $.fn.zTree.getZTreeObj("kjfl2");
            nodes = zTree.getSelectedNodes(true);
            var s = '';
            if(nodes != null && nodes.length>0){
                for(var i = 0; i< nodes.length;i++){
                    if(nodes[i].level==3){
                        $("#barQuickName2").val(nodes[i].name);
                        $("#barQuickIds2").val(nodes[i].id);
                    }
                }
            }
        }
        if(treeId=="kjfl3"){
            $("#barQuickName3").val("");
            $("#barQuickIds3").val(0);
            var zTree = $.fn.zTree.getZTreeObj("kjfl3");
            nodes = zTree.getSelectedNodes(true);
            var s = '';
            if(nodes != null && nodes.length>0){
                for(var i = 0; i< nodes.length;i++){
                    if(nodes[i].level==3){
                        $("#barQuickName3").val(nodes[i].name);
                        $("#barQuickIds3").val(nodes[i].id);

                    }
                }
            }
        }
        if(treeId=="kjfl4"){
            $("#barQuickName4").val("");
            $("#barQuickIds4").val(0);
            var zTree = $.fn.zTree.getZTreeObj("kjfl4");
            nodes = zTree.getSelectedNodes(true);
            var s = '';
            if(nodes != null && nodes.length>0){
                for(var i = 0; i< nodes.length;i++){
                    if(nodes[i].level==3){
                        $("#barQuickName4").val(nodes[i].name);
                        $("#barQuickIds4").val(nodes[i].id);
                    }
                }
            }
        }

    }
</script>
</body>
</html>

