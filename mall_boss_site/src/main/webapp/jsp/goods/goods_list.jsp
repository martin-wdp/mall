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
</head>
<body>
<!-- 引用头 -->
<jsp:include page="../page/header.jsp"></jsp:include>

<div class="container-fluid page_body">
    <input type="hidden" id="token" value="${token }"/>
    <div class="row">

        <jsp:include page="../page/left.jsp"></jsp:include>

        <div class="col-lg-20 col-md-19 col-sm-18 main">


            <!-- 需要替换的位置 -->
            <div class="main_cont">
                <jsp:include page="../page/breadcrumbs.jsp"></jsp:include>

                <h2 class="main_title">${pageNameChild} <small>(商品共${pb.rows }条，货品共${productCnt}件)</small></h2>

                <div class="common_data p20">
                    <div class="filter_area">
                        <input type="hidden" value="adv_form" id="formId">
                        <input type="hidden" value="<%=basePath %>findAllGoods.htm" id="formName">
                        <form role="form" action="findAllGoods.htm" method="post"  id="adv_form" class="form-inline">
                            <input type="hidden" class="token_val" id="token_val" name="CSRFToken" value=${token } />
                            <input type="hidden" name="isThird" value="0" />
                            <input type="hidden" name="condition" value="1" />
                            <input type="hidden" name="showFlag" value="1" />
                            <input type="hidden" name="queryStatus" value="${searchBean.queryStatus}" id="queryStatus"/>

                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">商品标题</span>
                                    <input type="text" name="goodsName" class="form-control"  value="${searchBean.goodsName}">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">商品编号</span>
                                    <input type="text" name="goodsNo"  value="${searchBean.goodsNo}" class="form-control">
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">商品品牌</span>
                                    <select   class="cate_selector w150"  name="goodsBrandId" data-live-search="true">
                                        <option value="">选择品牌</option>
                                        <c:forEach items="${brandList }" var="brand">
                                            <option value="${brand.brandId }"
                                                    <c:if test="${searchBean.goodsBrandId==brand.brandId }">
                                                        selected="selected"
                                                    </c:if>
                                                    >${brand.brandName }</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">是否上架</span>
                                    <select class="cate_selector w150"  name="status" data-live-search="true">
                                        <option value="-1" <c:if test="${searchBean.status=='-1'}">selected="selected"</c:if>>全部</option>
                                        <option value="1"  <c:if test="${searchBean.status=='1'}">selected="selected"</c:if>>是</option>
                                        <option value="0"  <c:if test="${searchBean.status=='0'}">selected="selected"</c:if>>否</option>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">一级分类</span>
                                    <select class="cate_selector w150"  data-live-search="true" name="oneCateId" onchange="chooseCateOne(this);">
                                        <option value="">选择分类</option>
                                        <c:forEach items="${oneCateList }" var="cate">
                                            <option value="${cate.catId}"  <c:if test="${oneCateId==cate.catId }">selected="selected" </c:if> >${cate.catName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>


                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">二级分类</span>
                                    <select class="cate_selector w150"  data-live-search="true"  id="twoCateId" name="twoCateId" onchange="chooseCateTwo(this);">
                                        <c:forEach items="${twoCateList }" var="cate">
                                            <option value="${cate.catId}"  <c:if test="${twoCateId==cate.catId }">selected="selected" </c:if> >${cate.catName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">三级分类</span>
                                    <select class="cate_selector w150"  data-live-search="true" name="goodsCateId" id="threeCateId">
                                        <c:forEach items="${threeCateList }" var="cate">
                                            <option value="${cate.catId}"  <c:if test="${searchBean.goodsCateId==cate.catId }">selected="selected" </c:if> >${cate.catName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>



                            <div class="form-group">
                                <button type="button" class="btn btn-primary" id="search">搜索</button>
                            </div>
                        </form>

                    </div>
                    <div class="data_ctrl_area mb20">
                        <div class="data_ctrl_search pull-right"></div>
                        <div class="data_ctrl_brns pull-left">
                            <button type="button" class="btn btn-info" onclick="checkdeleteGoods('goodsList','goodsIds')">
                                <i class="glyphicon glyphicon-trash"></i> 删除
                            </button>
                            <%--<button type="button" class="btn btn-info" onclick="createIndex()">
                                <i class="glyphicon glyphicon-th-large"></i> 生成索引
                            </button>--%>
                            <%--2015-12-19 wuyanbo 隐藏批量生成索引的功能--%>
                            <button type="button" class="btn btn-info" onclick="createNewIndex()" style="display: none;">
                                <i class="glyphicon glyphicon-th-large"></i> 生成索引
                            </button>
                            <button type="button" class="btn btn-info" onclick="batchUp('goodsIds')">
                                <i class="glyphicon glyphicon-circle-arrow-up"></i> 批量上架
                            </button>
                            <button type="button" class="btn btn-info" onclick="batchDown('goodsIds')">
                                <i class="glyphicon glyphicon-circle-arrow-down"></i> 批量下架
                            </button>
                            <button type="button" class="btn btn-info batch_number" onclick="batchUpStock('goodsIds')">
                                批量修改库存
                            </button>
                            <div class="btn-group">
                                <button type="button" class="btn btn-info" onclick="exportList()">
                                    <i class="glyphicon glyphicon-export"></i> 导出所有
                                </button>
                                <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                    <span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a href="#" onclick="exportPage()">导出列表</a></li>
                                    <li><a href="#" onclick="exportCheck()">导出选中</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="clr"></div>
                    </div>
                    <div class="table_tabs" id="good_tabs">
                        <ul>
                            <li class="<c:if test="${searchBean.queryStatus == '1'}">active</c:if>">
                                <a href="javascript:" data-type="1">全部</a>
                            </li>
                            <li class="<c:if test="${searchBean.queryStatus == '2'}">active</c:if>">
                                <a href="javascript:" data-type="2">已下架</a>
                            </li>
                            <li class="<c:if test="${searchBean.queryStatus == '3'}">active</c:if>">
                                <a href="javascript:" data-type="3">缺货商品</a>
                            </li>
                            <li class="<c:if test="${searchBean.queryStatus == '4'}">active</c:if>">
                                <a href="javascript:" data-type="4">库存预警</a>
                            </li>
                        </ul>
                    </div>

                    <form action="batchDelGoods.htm?CSRFToken=${token }" method="post" id="goodsList">
                        <input type="hidden" name="stock" id="stocks"/>
                        <table class="table table-striped table-hover">
                            <thead>
                            <tr>
                                <th width="25"><input  onclick="selectAll('goodsIds')" type="checkbox"></th>
                                <th>图片</th>
                                <th>商品名称</th>
                                <th>商品编号</th>
                                <!--<th>库存</th>-->
                                <th>销售价</th>
                                <th>会员价</th>
                                <th>是否上架</th>
                                <th>分类</th>
                                <th>品牌</th>
                                <th>所属商家</th>
                                <th width="150">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                                <c:if test="${fn:length(pb.list)<=0}">
                                   <tr>
                                      <td style="text-align: center;" colspan="10">暂无数据...</td>
                                   </tr>
                                </c:if>
                            <c:forEach items="${pb.list}" var="goods" varStatus="sta">
                                <tr>
                                    <td><input name="goodsIds" value='${goods.goodsId }'   type="checkbox"></td>
                                    <td>

                                        <a href="${bset}/item/${goods.goodsInfoId}.html" target="_blank">
                                            <div class="data_item">
                                                <img  height="60" src='${goods.goodsImg }'/>


                                            </div>
                                        </a>
                                    </td>
                                    <td width="300" style="text-align:left;">
                                        <p title="${goods.goodsName}">${goods.goodsName}</p>
                                    </td>
                                    <td width="150">${goods.goodsNo}</td>
                                    <!--<td> ${goods.stock }</td>-->
                                    <td>
                                        <p class="text-muted">${goods.goodsPrice }</p>
                                    </td>
                                    <td>
                                        <p class="text-muted">${goods.goodsVipPrice }</p>
                                    </td>
                                    <td>
                                        <c:if test="${goods.goodsAdded==0}"><a  title="点击上架" href="updateGoodsSta.htm?CSRFToken=${token }&goodsId=${goods.goodsId }&goodsAdded=1&pageNo=${pb.pageNo }"  href="javascript:" class="label label-default">否</a></c:if>
                                        <c:if test="${goods.goodsAdded==1}"><a title="点击下架" href="updateGoodsSta.htm?CSRFToken=${token }&goodsId=${goods.goodsId }&goodsAdded=0&pageNo=${pb.pageNo }" class="label label-success">是</a></c:if>


                                    </td>
                                    <td> ${goods.goodsCate.catName}</td>
                                    <td> ${goods.goodsBrand.brandName }</td>
                                    <td width="75"><c:if test="${goods.thirdName==null}">BOSS</c:if>
                                        <c:if test="${goods.thirdName!=null}">${goods.thirdName }</c:if>
                                    </td>
                                    <td>
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-default" onclick="location.href='queryAllByGoodsId.htm?goodsId=${goods.goodsId}'">查看货品</button>
                                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                                <span class="caret"></span>
                                                <span class="sr-only">Toggle Dropdown</span>
                                            </button>
                                            <ul class="dropdown-menu" role="menu">
                                                <li><a href="toModifyGoodsNew.htm?CSRFToken=${token}&goodsId=${goods.goodsId}&pageNo=1&pageSize=15">修改</a></li>
                                            </ul>
                                        </div>
                                    </td>
                                </tr>

                            </c:forEach>
                            </tbody>
                        </table>
                        <input type="hidden" name="goodsName" value="${searchBean.goodsName}">
                        <input type="hidden" name="goodsNo" value="${searchBean.goodsNo}">
                        <input type="hidden" name="goodsCateId" value="${searchBean.goodsCateId}">
                        <input type="hidden" name="goodsBrandId" value="${searchBean.goodsBrandId}">
                        <input type="hidden" name="isThird" value="${searchBean.isThird}">
                        <input type="hidden" name="status" value="${searchBean.status}">
                        <input type="hidden" name="thirdName" value="${searchBean.thirdName}">
                        <input type="hidden" name="searchText" value="${searchBean.searchText}">
                        <input type="hidden" name="showFlag" value="1" />
                        <input type="hidden" name="condition" value="${searchBean.condition}" />
                    </form>

                    <c:import url="../page/searchPag.jsp">
                        <c:param name="pageBean" value="${pb }"/>
                        <c:param name="path" value="../"></c:param>
                    </c:import>
                </div>

            </div>
        </div>
    </div>
    </div>

    <!-- Modal -->
    <div class="modal fade" id="addSEO"  role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">添加SEO设置</h4>
                </div>
                <div class="modal-body">
                    <form role="form" class="form-horizontal">
                        <div class="form-group">
                            <label class="control-label col-sm-5">SEO标题：</label>
                            <div class="col-sm-1"></div>
                            <div class="col-sm-16">
                                <input type="text" class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-5">SEO关键字：</label>
                            <div class="col-sm-1"></div>
                            <div class="col-sm-16">
                                <input type="text" class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-5">是否启用：</label>
                            <div class="col-sm-1"></div>
                            <div class="col-sm-16">
                                <label class="radio-inline">
                                    <input type="radio" name="open" id="open1" value="option1" checked> 是
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="open" id="open2" value="option2"> 否
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-5">SEO描述：</label>
                            <div class="col-sm-1"></div>
                            <div class="col-sm-16">
                                <textarea class="form-control" rows="5"></textarea>
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

    <!-- Modal -->
    <div class="modal fade" id="multiEdit"  role="dialog">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">编辑版权信息</h4>
                </div>
                <div class="modal-body">
                    <form role="form" class="form-horizontal">
                        <div class="summernote"></div>
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
    <script src="<%=basePath %>/js/bootstrap.min.js"></script>
    <script src="<%=basePath %>/js/summernote.min.js"></script>
    <script src="<%=basePath %>/js/language/summernote-zh-CN.js"></script>
    <script src="<%=basePath %>/js/bootstrap-select.min.js"></script>
    <script src="<%=basePath %>/js/common.js"></script>
    <script src="<%=basePath %>/js/goods/goods.js"></script>
    <script src="<%=basePath %>/js/common/common_alert.js"></script>
    <script src="<%=basePath %>/js/select2.min.js"></script>
    <script>

        function checkdeleteGoods(goodsList,name){
            var checkboxs = $("input[name="+name+"]");
            var goodsIds=[];
            var oneSelect = false;
            for(var j = 0; j < checkboxs.length; j++) {
                if($(checkboxs[j]).is(':checked')==true) {
                    oneSelect = true;
                    goodsIds.push($(checkboxs[j]).val());
                }
            }
            if(!oneSelect) {
                showTipAlert("请至少选择一条记录！");

            }else{
                $.ajax({
                    url: 'checkDeleteByGoodsIds.htm?CSRFToken=${token }',
                    data: { "goodsIds": goodsIds },
                    dataType: "json",
                    type: "POST",
                    success: function (data) {
                     if(data=='0'){
                        showTipAlert("选择的商品下的货品已经被购买,不允许删除")
                     }else{
                         showDeleteBatchConfirmAlert(goodsList,name)
                     }
                    }
                });
            }

        }
        $(function(){

            $("#queryStatus").val($("#good_tabs").find(".active").find("a").attr("data-type"));



            /*收款单筛选交互演示用，无实际意义*/
            $('#good_tabs a').click(function(){
                $that = $(this);
                $that.parent().addClass('active');
                $("#queryStatus").val($that.attr("data-type"));
                $that.parent().siblings().removeClass('active');
                $('#adv_form').submit();

            });


            /**
             * 点击搜索按钮触发事件
             *
             * */

            $('#search').click(function(){
                $("#queryStatus").val($("#good_tabs").find(".active").find("a").attr("data-type"));
                $('#adv_form').submit();
            });

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

            /* 富文本编辑框 */
            $('.summernote').summernote({
                height: 300,
                tabsize: 2,
                lang: 'zh-CN'
            });

            /* 下面是表单里面的填写项提示相关的 */
            $('.kucunyujing').popover({
                content : '当商品库存低于该值则进行预警',
                trigger : 'hover'
            });
            /* 高级搜索 */
            /* 为选定的select下拉菜单开启搜索提示 */
            $('select[data-live-search="true"]').select2();
        });



        function chooseCateOne(obj){
            if($(obj).val()!=''){
                $.ajax({
                    url:'querySonCateByCatIdAndName.htm?CSRFToken=${token}&catId='+$(obj).val(),
                    success: function (data) {

                        var html;
                        for(var i=0;i<data.length;i++) {
                            var cate = data[i];

                            html += '<option  value="'+cate.catId+'"';
                            if(i==0){
                                html+=' selected="selected"';
                            }
                            html+='>'+cate.catName+'</option>';

                        }
                        $("#twoCateId").html(html);
                        $('select[data-live-search="true"]').select2();
                        chooseCateTwo($("#twoCateId"));
                    }
                });
            }else{
                var htmls = '<option  value="">选择分类</option>';
                $("#twoCateId").html(htmls);
                $("#threeCateId").html(htmls);
                $('select[data-live-search="true"]').select2();
            }
        }

        function chooseCateTwo(obj){
            if($(obj).val()!=''){
                $.ajax({
                    url:'querySonCateByCatIdAndName.htm?CSRFToken=${token}&catId='+$(obj).val(),
                    success: function (data) {
                        var html ;
                        for(var i=0;i<data.length;i++) {
                            var cate = data[i];

                            html += '<option  value="'+cate.catId+'"';
                            if(i==0){
                                html+=' selected="selected"';
                            }
                            html+='>'+cate.catName+'</option>';

                        }
                        $("#threeCateId").html(html);
                        $('select[data-live-search="true"]').select2();
                    }
                });
            }else{
                var htmls = '<option  value="">选择分类</option>';
                $("#threeCateId").html(htmls);
                $('select[data-live-search="true"]').select2();
            }
        }

    </script>
</body>
</html>
