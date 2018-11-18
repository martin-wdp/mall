<%--
  Created by IntelliJ IDEA.
  User: NP-Heh
  Date: 2015/4/13
  Time: 14:36
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
    <link href="<%=basePath %>css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath %>css/font-awesome.min.css">
    <link href="<%=basePath %>iconfont/iconfont.css" rel="stylesheet">
    <link href="<%=basePath %>css/summernote.css" rel="stylesheet">
    <link href="<%=basePath %>css/bootstrap-select.min.css" rel="stylesheet">
    <link href="<%=basePath %>/css/select2.min.css" rel="stylesheet">
    <link href="<%=basePath %>css/style.css" rel="stylesheet">

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

                <h2 class="main_title" >${pageNameChild}&nbsp;<small><a  href="javascript:void(0);" onclick="$('#helpCateTips').modal('show')" style="diaplay:block; float:right; padding-right: 12px">查看帮助<i class="icon iconfont">&#xe611;</i></a></small></h2>

                <div class="cate_set container-fluid mt20">

                    <div class="row">
                        <input type="hidden" id="CSRFToken" value="${token}"/>
                        <div class="col-xs-8 cate_set_column">
                            <div class="cate_set_item">
                                <%--<div class="cate_set_ctrl">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <button type="button" class="btn btn-info btn-block round5" onClick="showAddCate()"><span class="glyphicon glyphicon-plus-sign"></span> 新增同级分类</button>
                                        </div>
                                        <div class="col-sm-1"></div>
                                        <div class="col-sm-11">
                                            <button type="button" class="btn btn-info btn-block round5" onClick="showEditCate(1)"><span class="glyphicon glyphicon-edit"></span> 修改当前分类</button>
                                        </div>
                                    </div>
                                </div>--%>
                                <div class="cate_set_cont">
                                    <div class="cate_search">
                                        <input type="hidden" id="parentId0" value="0"/>
                                        <input type="hidden" id="parentId1"/>
                                        <input type="text" id="search_name1" class="cate_search_box" placeholder="输入名称查找">
                                        <a href="javascript:;" onclick="findFirstGoodsCate()"><span class="glyphicon glyphicon-search"></span></a>
                                    </div>
                                    <div class="cate_set_list" id="cate_list1">

                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-xs-8 cate_set_column">
                            <div class="cate_set_item">
                                <%--<div class="cate_set_ctrl">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <button type="button" class="btn btn-info btn-block round5" onClick="showAddCate(0)"><span class="glyphicon glyphicon-plus-sign"></span> 新增同级分类</button>
                                        </div>
                                        <div class="col-sm-1"></div>
                                        <div class="col-sm-11">
                                            <button type="button" class="btn btn-info btn-block round5" onClick="showEditCate(2)"><span class="glyphicon glyphicon-edit"></span> 修改当前分类</button>
                                        </div>
                                    </div>
                                </div>--%>
                                <div class="cate_set_cont">
                                    <div class="cate_search">
                                        <input type="hidden" id="parentId2"/>
                                        <input type="text" id="search_name2" class="cate_search_box" placeholder="输入名称查找">
                                        <a href="javascript:;" onclick="findSecondGoodsCate()"><span class="glyphicon glyphicon-search"></span></a>
                                    </div>
                                    <div class="cate_set_list" id="cate_list2">

                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-xs-8 cate_set_column">
                            <div class="cate_set_item">
                              <%--  <div class="cate_set_ctrl">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <button type="button" class="btn btn-info btn-block round5" onClick="showAddCate(1)"><span class="glyphicon glyphicon-plus-sign"></span> 新增同级分类</button>
                                        </div>
                                        <div class="col-sm-1"></div>
                                        <div class="col-sm-11">
                                            <button type="button" class="btn btn-info btn-block round5" onClick="showEditCate(3)"><span class="glyphicon glyphicon-edit"></span> 修改当前分类</button>
                                        </div>
                                    </div>
                                </div>--%>
                                <div class="cate_set_cont">
                                    <div class="cate_search">
                                        <input type="hidden" id="parentId3"/>
                                        <input type="text" id="search_name3" class="cate_search_box" placeholder="输入名称查找">
                                        <a href="javascript:;" onclick="findThirdGoodsCate()"><span class="glyphicon glyphicon-search"></span></a>
                                    </div>
                                    <div class="cate_set_list" id="cate_list3">

                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>

                </div>

            </div>
        </div>
    </div>
</div>

<!-- 添加分类 -->
<div class="modal fade" id="cateAdd"  role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">添加分类</h4>
            </div>
            <div class="modal-body">
                <div class="modal_form">
                    <form role="form" class="form-horizontal" action="addGoodsCate.htm?CSRFToken=${token }" method="post" id="saveGoodsCateForm">
                        <input type="hidden" id="cur_level" />
                        <div class="form-group">
                            <label class="col-sm-4 control-label">
                                <span class="text-danger">*</span>分类名称：
                            </label>
                            <div class="col-sm-16">
                                <input type="text" class="form-control required" name="catName" onblur="checkCatNameExist(this)">
                            </div>
                            <div class="col-sm-4">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">
                                <span class="text-danger">*</span>上级分类：
                            </label>
                            <div class="col-sm-16">
                                <select class="w100" data-live-search="true" id="parentCate_add" name="catParentId">

                                </select>
                            </div>
                            <div class="col-sm-4">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">
                                <span class="text-danger">*</span>分类排序：
                            </label>
                            <div class="col-sm-16">
                                <input type="text" class="form-control w100 required digits" name="catSort">
                            </div>
                            <div class="col-sm-4">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">
                                <span class="text-danger">*</span>分类扣率：
                            </label>

                            <div class="col-sm-5">
                                <input type="text" class="form-control w100 required number" name="catRate"
                                       style="display: inline-block" id="add_cate_rate">
                            </div>
                            <div class="col-sm-4">
                                %&nbsp;
                          	  <a href="javascript:;" class="flkl help_tips">

		                        <i class="icon iconfont">&#xe611;</i>
		                      </a>
                            </div>
                        </div>
                        <!-- 下面这个商品类型最后一级分类才需要填写  -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label">
                                <span class="text-danger">*</span>商品类型：
                            </label>
                            <div class="col-sm-6">
                                <select data-live-search="true" name="typeId" class="required w150">
                                    <c:forEach items="${typeList}" var="type">
                                        <option value="${type.typeId }">${type.typeName }</option>
                                    </c:forEach>
                                </select>
                            </div>  
                            <div class="col-sm-4">
                            </div>
                        </div>
                        <%--<div class="form-group">
                            <label class="col-sm-4 control-label">
                                <span class="text-danger">*</span>是否显示：
                            </label>
                            <div class="col-sm-16">
                                <label class="radio-inline">
                                    <input type="radio" name="home" value="option1"> 是
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="home" value="option2"> 否
                                </label>
                            </div>
                            <div class="col-sm-4">
                            </div>
                        </div>--%>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="submitSaveGoodsCateForm()">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<!-- 编辑分类 -->
<div class="modal fade" id="cateEdit"  role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">编辑分类</h4>
            </div>
            <div class="modal-body">
                <div class="modal_form">
                    <form role="form" class="form-horizontal" id="updateCateForm">
                        <input type="hidden" name="catId" id="update_catId">
                        <input type="hidden" id="oldCatName">
                        <div class="form-group">
                            <label class="col-sm-4 control-label">
                                <span class="text-danger">*</span>分类名称：
                            </label>
                            <div class="col-sm-16">
                                <input type="text" class="form-control required specstr" name="catName" id="update_cate_name" onblur="checkUpdateCatNameExist(this)">
                            </div>
                            <div class="col-sm-4">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">
                                上级分类：
                            </label>
                            <div class="col-sm-16">
                                <select class="w100" data-live-search="true" name="catParentId" id="update_parent_cate">
                                    <option>无</option>
                                </select>
                            </div>
                            <div class="col-sm-4">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">
                                <span class="text-danger">*</span>分类排序：
                            </label>
                            <div class="col-sm-16">
                                <input type="text" class="form-control w100 required number" name="catSort"
                                       id="update_cate_sort">
                            </div>
                            <div class="col-sm-4">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">
                                <span class="text-danger">*</span>分类扣率：
                            </label>
                            <div class="col-sm-16">
                                <input type="text" class="form-control w100 required number" name="catRate"
                                       style="display: inline-block" id="update_cat_rate">&nbsp;&nbsp;%
                            </div>
                            <div class="col-sm-4">
                            </div>
                        </div>
                        <!-- 下面这个商品类型最后一级分类才需要填写  -->
                        <div class="form-group">
                            <label class="col-sm-4 control-label">
                                <span class="text-danger">*</span>商品类型：
                            </label>
                            <div class="col-sm-16">
                                <select class="required w150" data-live-search="true" name="typeId"
                                        id="update_goods_type">
                                    <c:forEach items="${typeList}" var="type">
                                        <option value="${type.typeId }">${type.typeName }</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-sm-4">
                            </div>
                        </div>
                        <%--<div class="form-group">
                            <label class="col-sm-4 control-label">
                                <span class="text-danger">*</span>是否启用：
                            </label>
                            <div class="col-sm-16">
                                <label class="radio-inline">
                                    <input type="radio" name="home" id="inlineRadio1" value="option1"> 是
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="home" id="inlineRadio2" value="option2"> 否
                                </label>
                            </div>
                            <div class="col-sm-4">
                            </div>
                        </div>--%>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="submitUpdateGoodsCateForm()">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

  
    <div class="modal fade" id="helpCateTips"  role="dialog">
      <div class="modal-dialog modal-lg">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">添加商品分类</h4>
          </div>
          <div class="modal-body">
            <div class="modal-article">
              <p><em>1.</em>添加商品分类时，需要添加三级分类，添加分类如下</p>
              <img src="./images/syshelp/img_f01.png" alt="">
              <p><em>2.</em>点击“添加分类”，就可以添加相应的一二三级分类了</p>
              <img src="./images/syshelp/img_f02.png" alt="">  
            </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
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
<script src="<%=basePath%>js/bootstrap-select.min.js"></script>
<script src="<%=basePath%>js/common.js"></script>
<script src="<%=basePath%>js/common/common_alert.js"></script>
<script src="<%=basePath%>js/goods/goods_cate.js"></script>
<script src="<%=basePath %>/js/select2.min.js"></script>
<script>
    $(function(){
    	 /* 下面是表单里面的填写项提示相关的 */
        $('.flkl').popover({
          content : '(针对第三方商家进行分类扣率结算时使用)',
          trigger : 'hover'
        });
        /* 为选定的select下拉菜单开启搜索提示 */
        $('select[data-live-search="true"]').select2();
        /* 为选定的select下拉菜单开启搜索提示 END */

        /* 下面是表单里面的填写项提示相关的 */
        $('.xiaoshoujia').popover({
            content : '此价格只用于显示，以商品定价为商品销售价',
            trigger : 'hover'
        });

        /* 双击编辑分类 */
        $('.cate_item').dblclick(function(){
            $('#cateEdit').modal('show');
        });
        $('.cate_item').click(function(){
            $(this).parent().find("div.cate_item").each(function () {
                $(this).removeClass("active");
            });
            $(this).addClass("active");
        });

    });
</script>
</body>
</html>

