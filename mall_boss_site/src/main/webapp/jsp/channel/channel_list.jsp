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
     <link href="<%=basePath %>/css/select2.min.css" rel="stylesheet">
    <link href="<%=basePath %>/css/style.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
        .styleRed{color: #ff0000;}
        /*#leftpage{margin-left: 20px;}*/
        /*#rightPage{margin-right: 20px;}*/
    </style>
</head>
<body>
<!-- 引用头 -->
<jsp:include page="../page/header.jsp"></jsp:include>
<div class="container-fluid page_body">
    <div class="row">
        <jsp:include page="../page/left.jsp"></jsp:include>
        <input type="hidden" value="goodsList" id="formId">
        <input type="hidden" value="queryChannelByPageBean.htm" id="formName">
        <div class="col-lg-20 col-md-19 col-sm-18 main">
            <!-- 需要替换的位置 -->
            <div class="main_cont">
                <jsp:include page="../page/breadcrumbs.jsp"></jsp:include>
                <h2 class="main_title">频道列表<small>(共${pb.rows }条)</small></h2>
                <div class="common_data p20">
                    <div class="filter_area">
                        <form role="form" action="findAllGoods.htm" method="post"  id="adv_form" class="form-inline">
                            <input type="hidden" class="token_val" id="token_val" name="CSRFToken" value=${token } />
                            <input type="hidden" name="isThird" value="0" />
                            <input type="hidden" name="condition" value="1" />
                              <input type="hidden" name="showFlag" value="1" />
                        </form>
                    </div>
                    <div class="data_ctrl_area mb20">
                        <div class="data_ctrl_search pull-right"></div>
                        <div class="data_ctrl_brns pull-left">
                            <button type="button" class="btn btn-info" onclick="toaddChannel();">
                                <i class="glyphicon glyphicon-plus"></i> 添加
                            </button>
                            <button type="button" class="btn btn-info" onclick="deleteChannelAll('channelId')">
                                <i class="glyphicon glyphicon-trash"></i> 删除
                            </button>
                            </div>
                        </div>
                        <div class="clr"></div>
                    <form action="batchDelGoods.htm?CSRFToken=${token }" method="post" id="goodsList">
                        <input type='hidden' name='tempId' value="${tempId}" id="tempId"/>
                        <table class="table table-striped table-hover">
                            <thead>
                            <tr>
                                <th width="25"><input  onclick="selectAll('channelId')" type="checkbox"></th>
                                <th width="120">频道名称</th>
                                <th width="120">频道URL</th>
                                <th width="80">更新时间</th>
                                <th width="80">是否开启</th>
                                <th width="150">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${pb.list}" var="channel" varStatus="sta">
                                <tr>
                                    <td><input name="channelId" value='${channel.channelId }'   type="checkbox"></td>
                                    <td>${channel.channelName }</td>
                                    <td>${channel.channelUrl }</td>
                                        <%--   <td> ${goods.stock }</td> --%>
                                    <td>
                                            <%--<c:if test="${goods.goodsAdded==0}"><a  title="点击上架" href="updateGoodsSta.htm?CSRFToken=${token }&goodsId=${goods.goodsId }&goodsAdded=1&pageNo=${pb.pageNo }"  href="javascript:;" class="label label-default">否</a></c:if>--%>
                                            <%--<c:if test="${goods.goodsAdded==1}"><a title="点击下架" href="updateGoodsSta.htm?CSRFToken=${token }&goodsId=${goods.goodsId }&goodsAdded=0&pageNo=${pb.pageNo }" class="label label-success">是</a></c:if>--%>
                                        <fmt:formatDate value="${channel.updateDate }" var="cdate" type="both"/>
                                            ${cdate }

                                    </td>
                                    <td>
                                        <c:if test="${channel.usedStart==0}"><a  title="点击开启" href="openChannelById.htm?CSRFToken=${token }&channelId=${channel.channelId }&tempId=${tempId}"  href="javascript:;" class="label label-default">否</a></c:if>
                                        <c:if test="${channel.usedStart==1}"><a title="点击关闭" href="closeChannelById.htm?CSRFToken=${token }&channelId=${channel.channelId }&tempId=${tempId}" class="label label-success">是</a></c:if>
                                    </td>
                                    <td>
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-default" onclick="location.href='showChannelSet.htm?channelId=${channel.channelId}&goodsCatId=${channel.goodsCatId}'">频道设置</button>
                                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                                <span class="caret"></span>

                                                <span class="sr-only">Toggle Dropdown</span>
                                            </button>
                                            <ul class="dropdown-menu" role="menu">
                                                <li><a href="javascript:void(0);" onclick='toModifyChannel(${channel.channelId})'>修改</a></li>
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

<!-- Modal -->
<div class="modal fade" id="addChannel"  role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">添加频道</h4>
            </div>
            <div class="modal-body">
                <form role="form" class="form-horizontal" id="channelForm" method="post" action="">
                    <input type="hidden" name="channelId" id="up_channelId">
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color: red;">*</span>频道名称：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control" name="channelName" id="up_channelName" value=""><span
                                id="channelName_tips" style="color: red;"></span>
                        </div>
                    </div>
                    <%--<div class="form-group">--%>
                        <%--<label class="control-label col-sm-5">频道URL：</label>--%>
                        <%--<div class="col-sm-1"></div>--%>
                        <%--<div class="col-sm-16">--%>
                            <%--<input type="text" class="form-control" name="channelUrl" id="up_channelUrl"><span--%>
                                <%--id="channelUrl_tips"></span>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color: red;">*</span>商品一级分类:</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <select name="goodsCatId" id="up_goodsCatId"
                                    class="ui-widget-content ui-corner-all tb_select" style="height: 34px;">
                                <c:forEach items="${map.goodsCateList }" var="goodsCate">
                                    <option value="${goodsCate.catId}">${goodsCate.catName }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color: red;">*</span>模板：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <select name="tempId" id="up_tempId"
                                    class="ui-widget-content ui-corner-all tb_select" style="height: 34px;">
                                <c:forEach items="${map.sysTempList }" var="sysTemp">
                                    <option value="${sysTemp.tempId }">${sysTemp.tempName }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">关键字：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" name="keyword" id="keyword" class="text ui-widget-content ui-corner-all" style="height: 34px;"> <font id="keyword_tips">默认为频道名称</font>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">频道描述：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <textarea class="form-control" id="des" name="des" style="width: 400px; height: 60px;"
                                      class="text ui-widget-content ui-corner-all"></textarea>
                            <font id="des_tips">默认为频道名称</font>
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="save">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!-- 修改Modal -->
<div class="modal fade" id="updateChannel"  role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">修改频道</h4>
            </div>
            <div class="modal-body">
                <form role="form" class="form-horizontal" id="updatechannelForm" method="post" action="">
                    <input type="hidden" name="channelId" id="update_channelId">
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color: red;">*</span>频道名称：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" class="form-control" name="channelName" id="update_channelName" value=""><span
                                id="updatechannelName_tips" style="color: red;"></span>
                        </div>
                    </div>
                    <div class="form-group">
                    <label class="control-label col-sm-5"><span style="color: red;">*</span>频道URL：</label>
                    <div class="col-sm-1"></div>
                    <div class="col-sm-16">
                    <input type="text" class="form-control" name="channelUrl" id="update_channelUrl"><span
                    id="updatechannelUrl_tips" style="color: red;"></span>
                    </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color: red;">*</span>商品一级分类:</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <select name="goodsCatId" id="update_goodsCatId"
                                    class="ui-widget-content ui-corner-all tb_select" style="height: 34px;">
                                <c:forEach items="${map.goodsCateList }" var="goodsCate">
                                    <option value="${goodsCate.catId}">${goodsCate.catName }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span style="color: red;">*</span>模板：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <select name="tempId" id="update_tempId"
                                    class="ui-widget-content ui-corner-all tb_select" style="height: 34px;">
                                <c:forEach items="${map.sysTempList }" var="sysTemp">
                                    <option value="${sysTemp.tempId }">${sysTemp.tempName }</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">关键字：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <input type="text" name="keyword" id="updatekeyword" class="text ui-widget-content ui-corner-all" style="height: 34px;"> <font id="updatekeyword_tips">默认为频道名称</font>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">频道描述：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-16">
                            <textarea class="form-control" id="updatedes" name="des" style="width: 400px; height: 60px;"
                                      class="text ui-widget-content ui-corner-all"></textarea>
                            <font id="updatedes_tips">默认为频道名称</font>
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="update">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!-- Modal -->
<div class="modal fade" id="channelTips"  role="dialog">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">操作提示</h4>
            </div>
            <div class="modal-body">
                <form role="form" class="form-horizontal">
                    <div class="summernote">请至少选择一条数据！</div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
                <%--<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>--%>
            </div>
        </div>
    </div>
</div>
<!-- Modal -->
<div class="modal fade" id="channelDeleteTips"  role="dialog">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">操作提示</h4>
            </div>
            <div class="modal-body">
                <form role="form" class="form-horizontal">
                    <div class="summernote">是否确定已选全部删除？</div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="channelDelete()">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>


<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=basePath %>/js/bootstrap.min.js"></script>
<script src="<%=basePath %>/js/summernote.min.js"></script>
<script src="<%=basePath %>/js/language/summernote-zh-CN.js"></script>
<script src="<%=basePath %>/js/bootstrap-select.min.js"></script>
<script src="<%=basePath %>/js/common.js"></script>
<script src="<%=basePath %>/js/goods/goods.js"></script>
<script src="<%=basePath %>/js/common/common_alert.js"></script>
<script src="<%=basePath %>/js/select2.min.js"></script>
<script>
    //频道页添加页面的展开
    function toaddChannel(){
        $(".modal-title").html("添加频道");
        $("#up_channelId").val("");
          $("#addChannel").modal('show');
    }
    //频道页修改页面的展开
    function toModifyChannel(obj){
//        $(".modal-title").html("修改频道");
        $.ajax({
            url:"queryChannelByChannelId.htm",
            Type:"POST",
            data:{channelId:obj},
            success:function(date){
                $("#update_channelId").val(date.channel.channelId);
                $("#update_channelName").val(date.channel.channelName);
                $("#update_channelUrl").val(date.channel.channelUrl);
                var selecthtml="";
                for(var i=0;i<date.goodsCateList.length;i++){
                    selecthtml=selecthtml+"<option value='"+date.goodsCateList[i].catId+"'";
                    if(date.goodsCateList[i].catId==date.channel.goodsCatId){
                        selecthtml=selecthtml+"selected='selected'";
                    }
                    selecthtml=selecthtml+">"+date.goodsCateList[i].catName+"</option>";
                }
                $("#update_goodsCatId").html(selecthtml);
                $("#updatekeyword").val(date.channel.keyword);
                $("#updatedes").val(date.channel.des);
            }

        })
        $("#updateChannel").modal('show');
    }
    //更新错误提示框的状态
    function updateTips( t, tip)
    {
        tip .text( t ) .addClass( "ui-state-highlight styleRed" );
        setTimeout(function ()
        {
            tips.removeClass( "ui-state-highlight styleRed", 1500 );
        }, 500 );
    }
    //验证特殊字符，将调试显示到页面中
    function checkSpecSymb(inputobj,Tipobj){
        var regexp=new RegExp("[`~!@#$^&*()={}':;',\\[\\]<>/?~！@#￥……&*（）{}【】‘；：”“'。，、？]");
        if (regexp.test( $("#"+inputobj).val() ) ) {
            $("#"+inputobj).addClass( "ui-state-error" );
            updateTips( "输入的内容包含特殊字符!", $("#"+Tipobj));
            $("#"+inputobj).focus();
            return false;
        }
        else {
            $("#"+Tipobj).text("").removeClass( "ui-state-highlight");
            $("#"+inputobj).removeClass( "ui-state-error" );
            return true;
        }
    }
    //验证特殊字符，将调试显示到页面中
    function checkSpecChannel(inputobj,Tipobj){
        var regexp=new RegExp("[`~@#$^&*()={}\\[\\]<>~@#￥……&*（）{}【】‘”“']");
        if (regexp.test( $("#"+inputobj).val() ) ) {
            $("#"+inputobj).addClass( "ui-state-error" );
            updateTips( "输入的内容包含特殊字符!", $("#"+Tipobj));
            $("#"+inputobj).focus();
            return false;
        }
        else {
            $("#"+Tipobj).text("").removeClass( "ui-state-highlight");
            $("#"+inputobj).removeClass( "ui-state-error" );
            return true;
        }
    }
    $(function(){
        $('#update').click(function(){
            var flag = true;
            //验证频道名称
            if($("#update_channelName").val()==""){
                $("#update_channelName").addClass( "ui-state-error" );
                updateTips( "请填写频道名称!", $("#updatechannelName_tips"));
                $("#update_channelName").focus();
                flag = false && flag;
            }else{
                flag = checkSpecSymb("update_channelName","updatechannelName_tips") && flag;
            }
            //验证URL
            if($("#update_channelUrl").val()==""){
                $("#update_channelUrl").addClass( "ui-state-error" );
                updateTips( "请填写URL!", $("#updatechannelUrl_tips"));
                $("#update_channelUrl").focus();
                flag = false  && flag;
            }else{
                var regexp = /^([a-zA-z]+:\/\/)?[^\s]*$/;
                if(!regexp.test($("#update_channelUrl").val())){
                    $("#update_channelUrl").addClass( "ui-state-error" );
                    updateTips( "网址格式不正确!", $("#updatechannelUrl_tips"));
                    $("#update_channelUrl").focus();
                    flag = false  && flag;
                }else{
                    $("#update_channelUrl").removeClass( "ui-state-error" );
                    $("#updatechannelUrl_tips").text("").removeClass( "ui-state-highlight" );
                    flag = true && flag;
                }
            }
            //验证关键字
            if($("#updatekeyword").val()==""){
                $("#updatekeyword").val($("#update_channelName").val());
            }
            flag = checkSpecSymb("updatekeyword","updatekeyword_tips") && flag;
            //验证频道描述
            if($("#updatedes").val()==""){
                $("#updatedes").val($("#update_channelName").val());
            }
            flag = checkSpecChannel("updatedes","updatedes_tips") && flag;
            if(flag){
                var channelurl = "";
//                var up_channelId = $("#up_channelId").val();
//                if(up_channelId==""){
//                    channelurl = "createChannel.htm";
//                }else{
                    channelurl = "updateChannel.htm";
//                }
                $('#updatechannelForm').attr('action',channelurl).submit();
            }
        });
        //异步提交表单
        $('#save').click(function() {
            var flag = true;
            //验证频道名称
            if($("#up_channelName").val()==""){
                $("#up_channelName").addClass( "ui-state-error" );
                updateTips( "请填写频道名称!", $("#channelName_tips"));
                $("#up_channelName").focus();
                flag = false && flag;
            }else{
                flag = checkSpecSymb("up_channelName","channelName_tips") && flag;
            }
            //验证URL
            if($("#up_channelUrl").val()==""){
                $("#up_channelUrl").addClass( "ui-state-error" );
                updateTips( "请填写URL!", $("#channelUrl_tips"));
                $("#up_channelUrl").focus();
                flag = false  && flag;
            }else{
                var regexp = /^([a-zA-z]+:\/\/)?[^\s]*$/;
                if(!regexp.test($("#up_channelUrl").val())){
                    $("#up_channelUrl").addClass( "ui-state-error" );
                    updateTips( "网址格式不正确!", $("#channelUrl_tips"));
                    $("#up_channelUrl").focus();
                    flag = false  && flag;
                }else{
                    $("#up_channelUrl").removeClass( "ui-state-error" );
                    $("#channelUrl_tips").text("").removeClass( "ui-state-highlight" );
                    flag = true && flag;
                }
            }
            //验证关键字
            if($("#keyword").val()==""){
                $("#keyword").val($("#up_channelName").val());
            }
            flag = checkSpecSymb("keyword","keyword_tips") && flag;
            //验证频道描述
            if($("#des").val()==""){
                $("#des").val($("#up_channelName").val());
            }
            flag = checkSpecChannel("des","des_tips") && flag;
            if(flag){
                var channelurl = "";
//                var up_channelId = $("#up_channelId").val();
//                if(up_channelId==""){
                    channelurl = "createChannel.htm";
//                }else{
//                    channelurl = "updateChannel.htm";
//                }
                $('#channelForm').attr('action',channelurl).submit();
            }
        });
    });
    //删除
    var channelArray;
    function deleteChannelAll(obj){
         channelArray=new Array();
        $("input[name='"+obj+"']:checked").each(function(){
            channelArray.push($(this).val());
        })
        if(channelArray.length>0){
            $("#channelDeleteTips").modal('show');
        }else{
            $("#channelTips").modal('show');
        }
    }
    function channelDelete(){
        location.href="deleteChannel.htm?channelIDs="+channelArray+"&tempId="+$("#tempId").val();
    }
</script>
</body>
</html>
