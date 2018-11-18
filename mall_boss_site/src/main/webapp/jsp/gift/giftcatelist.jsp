<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>积分商品分类</title>

    <!-- Bootstrap -->
    <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath%>css/font-awesome.min.css">
    <link href="<%=basePath%>iconfont/iconfont.css" rel="stylesheet">
    <link href="<%=basePath%>css/summernote.css" rel="stylesheet">
    <link href="<%=basePath%>css/bootstrap-select.min.css" rel="stylesheet">
    <link href="<%=basePath%>css/zTreeStyle/zTreeStyle.css" rel="stylesheet">
    <link href="<%=basePath %>/css/select2.min.css" rel="stylesheet">
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
        <!-- 引用左边 -->
        <jsp:include page="../page/left.jsp"></jsp:include>
        <div class="col-lg-20 col-md-19 col-sm-18 main">
            <div class="main_cont">
                <jsp:include page="../page/breadcrumbs.jsp"></jsp:include>

                <h2 class="main_title">${pageNameChild} <small id="totalCount"></small></h2>

                <div class="common_data p20">

                    <div class="data_ctrl_area mb20">
                        <div class="data_ctrl_search pull-right"></div>
                        <div class="data_ctrl_brns pull-left">
                            <button type="button" class="btn btn-info" onclick="openAddDiv()">
                                <i class="glyphicon glyphicon-plus"></i> 添加
                            </button>
                            <button type="button" class="btn btn-info" onclick="openDelBatchDiv()">
                                <i class="glyphicon glyphicon-trash"></i> 删除
                            </button>
                        </div>
                        <div class="clr"></div>
                    </div>

                    <form id="cateList" action="delallgiftcate.htm" method="post">
                    <!-- 这是另一种表格，带伸缩，不带分页 -->
                    <table id="list" class="treetable table table-striped table-hover" cellspacing="0" width="100%">
                        <thead>
                        <tr>
                            <th width="50"><input name="giftOrderId"  onclick="allunchecked(this,'giftCateIds')" type="checkbox"></th>
                            <th>分类名称</th>
                            <th width="150">操作</th>
                        </tr>
                        </thead>
                        <tbody id="treetable">


                        </tbody>
                    </table>
                    </form>
                </div>

            </div>
        </div>
    </div>
</div>

<!-- 添加分类Modal -->
<div class="modal fade" id="addPointsCate"  role="dialog">
    <form id="addForm" action="addgiftcate.htm" method="post" >
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">添加积分商品分类</h4>
            </div>

            <div class="modal-body">
                <div class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span class="text-danger">*</span>分类名称：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-12">
                            <input type="text" class="form-control required" maxlength="15" name="giftCateName">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span class="text-danger">*</span>上级分类：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-8">
                            <input type="hidden" name="giftParentId" id="giftParentId" value="0"/>
                            <input type="text" class="form-control required" id="cateChooseInput" data-toggle="dropdown"
                                   readonly>
                            <div class="dropdown-menu" role="menu" id="cateChoose">
                                <ul id="treeDemo" class="ztree"></ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="addSubmit()">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
        </form>
</div>

<!-- 编辑分类Modal -->
<div class="modal fade" id="editPointsCate"  role="dialog">
    <form id="editForm" action="updategiftcate.htm" method="post" >
        <input type="hidden" name="giftCateId" id="giftCateId"/>
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">编辑积分商品分类</h4>
                </div>

                <div class="modal-body">
                    <div class="form-horizontal">
                        <div class="form-group">
                            <label class="control-label col-sm-5"><span class="text-danger">*</span>分类名称：</label>
                            <div class="col-sm-1"></div>
                            <div class="col-sm-12">
                                <input type="text" class="form-control required" name="giftCateName" id="giftCateName2"
                                       >
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-5"><span class="text-danger">*</span>上级分类：</label>
                            <div class="col-sm-1"></div>
                            <div class="col-sm-8">
                                <input type="hidden" name="giftParentId" id="giftParentId2" value="0"/>
                                <input type="text" class="form-control required" id="cateChooseInput2" data-toggle="dropdown">
                                <div class="dropdown-menu" role="menu" id="cateChoose2">
                                    <ul id="treeDemo2" class="ztree"></ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onclick="editSubmit()">保存</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </div>
        </div>
    </form>
</div>
<!-- 删除Modal -->
<div class="modal fade" id="dialog-confirm2"  role="dialog" width="">
    <div class="modal-dialog">
        <div class="modal-content">


                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">操作提示</h4>
                </div>
                <div class="modal-body">
                    你确定要删除选中的记录吗?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onclick="delBatch()">确定</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>

        </div>
    </div>
</div>
<!-- 删除单个Modal -->
<div class="modal fade" id="dialog-confirm3"  role="dialog" width="">
    <div class="modal-dialog">
        <div class="modal-content">

            <input id="cateId" type="hidden"/>
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">操作提示</h4>
            </div>
            <div class="modal-body">
                你确定要删除该分类吗?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="delCate()">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>

        </div>
    </div>
</div>

<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=basePath%>js/bootstrap.min.js"></script>
<script src="<%=basePath%>js/summernote.min.js"></script>
<script src="<%=basePath%>js/language/summernote-zh-CN.js"></script>
<script src="<%=basePath%>js/bootstrap-select.min.js"></script>
<script src="<%=basePath%>js/jquery.ztree.core-3.5.min.js"></script>
<script src="<%=basePath%>js/jquery.ztree.excheck-3.5.min.js"></script>
<script src="<%=basePath%>js/jqtreetable.js"></script>
<script src="<%=basePath%>js/common.js"></script>
<script src="<%=basePath %>js/common/common_checked.js"></script>
<script src="<%=basePath %>js/common/common_alert.js"></script>
<script src="<%=basePath %>/js/select2.min.js"></script>
<script>



    /* 下面是关于树形菜单 */
    var setting = {
        data : {
            key : { }, simpleData : {
                enable : true
            }
        },
        callback: {
            onClick: zTreeOnClick
        }
    };


    var i=1;
    $(function(){




        /* 为选定的select下拉菜单开启搜索提示 */
        $('select[data-live-search="true"]').select2();
        /* 为选定的select下拉菜单开启搜索提示 END */




        //点击新增出现下拉内容,光标移开下拉内容消失
        $('#cateChooseInput').click(function(){
            $('#cateChoose').show().css({
                'left' : $(this).prev('.input-group-addon').width() + 'px',
                'minWidth' : '200px'
            });
            $('#cateChoose').mouseleave(function(){
                $(this).hide();
            });
        });

        //点击编辑出现下拉内容,光标移开下拉内容消失
        $('#cateChooseInput2').click(function(){
            $('#cateChoose2').show().css({
                'left' : $(this).prev('.input-group-addon').width() + 'px',
                'minWidth' : '200px'
            });
            $('#cateChoose2').mouseleave(function(){
                $(this).hide();
            });
        });

        dogetGiftCateList();
    });

    function dogetGiftCateList(){

        $.get("searchgiftcatelistNopage.htm?condition=1",
                function (data)
                {

                    var row=0;
                    if (data != null && data != "") {
                        calcSonCate(data);
                        //设置条数
                        row=data.length;

                    }
                    $("#totalCount").text("(共" + row + "条)");
                    var map = "";
                    $(".caidan").each(function(){
                        if($(this).text()==''){
                            map+="0,";
                        }else{
                            map+=$(this).text()+",";
                        }

                    });

                    var maps=map.substring(0,map.length-1).split(",");


                    if (data != null && data != "") {



                    $("#treetable").jqTreeTable(maps, {
                        openImg: "<%=basePath %>images/TreeTable/tv-collapsable.gif",
                        shutImg: "<%=basePath %>images/TreeTable/tv-expandable.gif",
                        leafImg: "<%=basePath %>images/TreeTable/tv-item.gif",
                        lastOpenImg: "<%=basePath %>images/TreeTable/tv-collapsable-last.gif",
                        lastShutImg: "<%=basePath %>images/TreeTable/tv-expandable-last.gif",
                        lastLeafImg: "<%=basePath %>images/TreeTable/tv-item-last.gif",
                        vertLineImg: "<%=basePath %>images/TreeTable/vertline.gif",
                        blankImg: "<%=basePath %>images/TreeTable/blank.gif",
                        collapse: true,
                        column: 1,
                        striped: true,
                        highlight: true,
                        state:false
                    });
                    }
                });
    }


    var  checkedList ='';
    //检查是否选中一行
    function checkSelected(objId,modifyFlag){
        checkedList= new Array();
        $("input[name='"+objId+"']:checked").each(function() {
            checkedList.push($(this).val());
        });
        if(modifyFlag!=0){
            if(checkedList.length ==1){
                return true;
            }else{
                return false;
            }
        }

        if(checkedList.length > 0){
            return true;
        }else{
            return false;
        }
    }


    function openDelBatchDiv(){

        if(checkSelected('giftCateIds',0)==false){
            showTipAlert("最少选择一行!");
            return false;
        }
        else{


            var bool2=true;
            for(var i = 0; i < checkedList.length; i++) {
                $.ajax({
                    url: "checkgiftcate.htm?cateId=" + checkedList[i],
                    type: 'post',
                    async: false,
                    success: function (data) {

                        if (data) {
                        }
                        else {
                            if (bool2) {

                                showTipAlert("分类下包含子分类，不允许删除!");
                                bool2 = false;
                                return;

                            }
                        }
                    }
                });
            }
            if(bool2){
                $('#dialog-confirm2').modal('show');
            }

        }
    }

    function delBatch(){
        $("#cateList").submit();
    }
    //当前排序
    var index=1;
    function calcSonCate(data){


        for (var i = 0; i < data.length; i++){

            var htm='';

            htm += ' <tr ';
            if (data[i].giftParentId != '0') {
                htm += 'class="collapsed"';
            }
            htm += '>';
            htm+='<td>';
            htm+='<input type="checkbox" value="'+data[i].giftCateId+'" name="giftCateIds">';
            htm+='</td>';
            htm+='<td style="text-align:left;"> <span style="display:none;" id="mep'+data[i].giftCateId+'">'+index+'</span>'+data[i].giftCateName+'<span class="caidan" style="display:none;" id="me'+data[i].giftCateId+'"></span><span style="display:none;" id="mes'+data[i].giftCateId+'"></span></td>';
            htm+='<td>';
            htm+='<div class="btn-group">';
            htm+=' <button type="button" class="btn btn-default" onclick="openEditDiv('+data[i].giftCateId+');">编辑</button>';
            htm+='<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">';
            htm+='  <span class="caret"></span>';
            htm+=' <span class="sr-only">Toggle Dropdown</span>';
            htm+='</button>';
            htm+=' <ul class="dropdown-menu" role="menu">';
            htm+='   <li><a href="javascript:void(0);" onclick="openDelDiv('+data[i].giftCateId+')">删除</a></li>';
            htm+=' </ul>';
            htm+='  </div>';
            htm+=' </td>';
            htm+='</tr>';
            $("#treetable").append(htm);
            $("#me"+data[i].giftCateId).text($("#mep"+data[i].giftParentId).text());

            if(data[i].cateVos!=null&&data[i].cateVos.length!=0){
                $("#mes"+data[i].giftCateId).text(1);
            }else{
                $("#mes"+data[i].giftCateId).text(0);
            }
            index++;

            calcSonCate(data[i].cateVos);



        }


    }

    function zTreeOnClick(event, treeId, treeNode) {

        if(treeNode.id==-1){

            $("#giftParentId").val(0);
            $("#giftParentId2").val(0);
        }
        else{
            $("#giftParentId").val(treeNode.id);
            $("#giftParentId2").val(treeNode.id);
        }
        $("#cateChooseInput").val(treeNode.name);
        $("#cateChooseInput2").val(treeNode.name);



    };
    var num=0;
    function addSubmit(){
        if($("#addForm").valid()&&num==0){
            num+=1;
            $("#addForm").submit();
        }
    }

    function editSubmit(){
        if($("#editForm").valid()){

            $("#editForm").submit();
        }
    }
    function openAddDiv(){

        num=0;
        $('#addPointsCate').modal('show');

        $("#cateChooseInput").val('---无---');
        $("#giftParentId").val(0);
        /*查询商品分类放在树形控件中*/
        $.get("querygiftcate.htm", function (data)
        {
            var zNodes = new Array();
            var no={
                id:-1,pId:null,name:'---无---',open:true
            }
            zNodes.push(no);
            for (var i = 0; i < data.length; i++)
            {
                var node = {
                    id : data[i].giftCateId, pId : data[i].giftParentId, name : data[i].giftCateName, open : true
                };
                zNodes.push(node);
            }
            $.fn.zTree.init($("#treeDemo"), setting, zNodes);
        });

    }
    function openEditDiv(giftCateId){
        $('#editPointsCate').modal('show');

        $.get("searchgiftcatebyid.htm?giftCateId=" + giftCateId, function (data)
        {
            $("#giftCateId").val(data.giftCateId);
            $("#giftCateName2").val(data.giftCateName);
            $("#giftParentId2").val(data.giftParentId);

            if(data.giftParentId==null||data.giftParentId==''){
                $("#cateChooseInput2").val('---无---');
            }
            /*查询商品分类放在树形控件中*/
            $.get("querygiftcate.htm?giftCateId=" + giftCateId, function (data)
            {
                var zNodes = new Array();
                var no={
                    id:-1,pId:null,name:'---无---',open:true
                }
                zNodes.push(no);
                for (var i = 0; i < data.length; i++)
                {
                    //设置父分类名称
                    if (data[i].giftCateId == $("#giftParentId2").val()) {
                        $("#cateChooseInput2").val(data[i].giftCateName);
                    }

                    var node = {
                        id : data[i].giftCateId, pId : data[i].giftParentId, name : data[i].giftCateName, open : true
                    };
                    zNodes.push(node);
                }
                $.fn.zTree.init($("#treeDemo2"), setting, zNodes);
            });

        });

    }
    function openDelDiv(cateId){
        $('#dialog-confirm3').modal('show');
        $("#cateId").val(cateId);

    }

    function delCate(){
        $('#dialog-confirm3').modal('hide');
        var cateId= $("#cateId").val();
        $.get("checkgiftcate.htm?cateId=" + cateId, function (data)
        {
            if(data){
                location.href = "delgiftcate.htm?giftCateId=" + cateId;
            }
            else{
                showTipAlert("分类下包含子分类，不允许删除!");
            }
        }
        );
    }
</script>
</body>
</html>
