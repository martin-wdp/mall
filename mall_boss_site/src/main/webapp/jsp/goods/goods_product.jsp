<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
  <link href="<%=basePath%>css/bootstrap-select.min.css" rel="stylesheet">
  <link href="<%=basePath %>css/select2.min.css" rel="stylesheet">
  <link href="<%=basePath%>css/style.css" rel="stylesheet">
  <link href="<%=basePath%>css/goods/goods.css" rel="stylesheet">
  <link href="<%=basePath %>css/zTreeStyle/zTreeStyle.css" rel="stylesheet">
  <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="<%=basePath %>js/html5shiv.min.js"></script>
  <script src="<%=basePath %>js/respond.min.js"></script>
  <![endif]-->
    <script type="text/javascript">
        var zSetting = {
            data: {
                simpleData: {
                    enable: true
                }
            },
            check: {
                enable: true,
                chkboxType: { "Y" : "ps", "N" : "s" },
                nocheckInherit:false,
                nocheck:true
            },
            async: {
                enable: true,
                url: "autostyle/getTreeDatas.htm",
                autoParam: ["id=pId", "levelNum"],
                otherParam: { "liYangID":""}
            },
            callback: {
                onAsyncSuccess: function(event, treeId, treeNode, msg) {
                    var treeObj = $.fn.zTree.getZTreeObj(treeId);
                    if(treeObj){
                        var node = treeObj.getNodeByParam("isHidden", true, null);
                        if(node){
                            var childNodes = eval(msg);
                            for(var i=0;i < childNodes.length;i++){
                                if(node.checkNoId.indexOf(childNodes[i].id) !=-1){
                                    treeObj.checkNode(treeObj.getNodeByParam("id",childNodes[i].id,treeNode), true, false);
                                }
                            }

                        }
                    }

                }

            }, view: {
                expandSpeed: 0
            }
        };
        function checkDeleteGoodsInfo(method, name, tips) {

            var checkboxs = $("input[name=" + name + "]");
            var goodsInfoIds = [];
            var oneSelect = false;
            for (var j = 0; j < checkboxs.length; j++) {
                if ($(checkboxs[j]).is(':checked') == true) {
                    oneSelect = true;
                    goodsInfoIds.push($(checkboxs[j]).val());
                }
            }
            if (!oneSelect) {
                showTipAlert("请至少选择一条记录！");

            } else {
                $.ajax({
                    url: 'checkDeleteByGoodsInfoIds.htm?CSRFToken=${token }',
                    data: {"goodsInfoIds": goodsInfoIds},
                    dataType: "json",
                    type: "POST",
                    success: function (data) {
                        if (data == '0') {
                            showTipAlert("选择的货品已经被购买,不允许删除")
                        } else {
                            showDeleteBatchConfirmAlert(method, name, tips)
                        }
                    }
                });
            }

        }

        //修改货品适配车型
        function modifiedProductAutoStyle(productId){
            $("#style_goodsInfoIds").val(productId);
            $.get("queryProductAutoStyleByProductId.htm?CSRFToken=" + $(".token_val").val() + "&productId=" + productId, function (data) {
               if(data){
                   zSetting.async.otherParam.liYangID=data['autoStyleIdLiYangID'];

               }
                //初始化车类品牌数据
                $.fn.zTree.init($("#treeDemo"), zSetting, []);
                $("#productAutoStyle").modal('show');
            });
        }

        /* 修改货品信息 */
        function modifiedProduct(productId) {
            $.get("queryProductVoByProductId.htm?CSRFToken=" + $(".token_val").val() + "&productId=" + productId, function (data) {
                $(".oldParam").remove();
                for (var i = 0; i < data.productWares.length; i++) {
                    $("#productStock" + data.productWares[i].wareId).val(data.productWares[i].wareStock);
                    $("#productPrice" + data.productWares[i].wareId).val(data.productWares[i].warePrice);
                    $("#productVipPrice" + data.productWares[i].wareId).val(data.productWares[i].wareVipPrice);
                }
                $("#goodsInfoIds").val(data.goodsInfoId);
                $("#update_goodsId").val(data.goodsId);
                $("#update_goodsInfoName").val(data.goodsInfoName);
                $("#update_goodsInfoSubtitle").val(data.goodsInfoSubtitle);
                $("#update_goodsInfoItemNo").val(data.goodsInfoItemNo);
                $(".nowProductNo").val(data.goodsInfoItemNo);
                $("#oldPrice").val(data.goodsInfoPreferPrice);
                $("#oldVipPrice").val(data.goodsInfoVipPrice);
                //$("#update_goodsInfoStock").val(data.goodsInfoStock);
                $("#update_goodsInfoPreferPrice").val(data.goodsInfoPreferPrice);//销售价
                $("#update_goodsInfoVipPrice").val(data.goodsInfoVipPrice);//会员价
                $("#update_goodsInfoCostPrice").val(data.goodsInfoCostPrice);
                $("#update_goodsInfoMarketPrice").val(data.goodsInfoMarketPrice);
                $("#update_goodsInfoWeight").val(data.goodsInfoWeight);
                $("#goodsInfoAutoPartsType").val(data.goodsInfoAutoPartsType);
                $("#goodsInfoOem").val(data.goodsInfoOem);

                if (data.isCustomerDiscount == 0) {
                    $("#isCustomer").attr("checked", true);
                } else {
                    $("#isCustomerAdded").attr("checked", true);
                }
                if (data.goodsInfoAdded == 1) {
                    $("#update_added").attr("checked", true);
                }
                else {
                    $("#update_unadded").attr("checked", true);
                }
                if (data.showList == 1) {
                    $("#showList1").attr("checked", true);
                } else {
                    $("#showList2").attr("checked", true);
                }
                if (data.showMobile == 1) {
                    $("#showMobile1").attr("checked", true);
                } else {
                    $("#showMobile2").attr("checked", true);
                }
                //if (data.isMailBay == 1) {
                //$("#isMailBayOk").attr("checked", true);
                // }
                // else {
                // $("#isMailBayNo").attr("checked", true);
                //}
                /*服务支持信息*/
                if (null != data.suppList && data.suppList.length > 0) {
                    for (var _i = 0; _i < data.suppList.length; _i++) {
                        $("#productSupp" + data.suppList[_i].suppId).prop("checked", true);

                    }
                }
                if (data.specVo.length > 0) {
                    for (var i = 0; i < data.specVo.length; i++) {
                        $("#sel" + data.specVo[i].spec.specId).val(data.specVo[i].goodsSpecDetail.specDetailId);
                        $(".oldParamDiv").append("<input type='hidden' class='oldParam' value='" + data.specVo[i].goodsSpecDetail.specDetailId + "' />");
                    }
                }
            });
            $("#productTitle").text("修改货品");
            $("#productTitle2").text("修改货品");
            $("#updateProduct").append("<input id=\"paramLength\" type=\"hidden\" value=\"${fn:length(specs)}\"/>");
            $("#updateProduct").attr("action", "updateProduct.htm?CSRFToken=${token }");
            $("#addSku1").modal('show');
        }

        /*更新货品*/
        var num = 0;
        function updateProduct() {
            if (checkUpdateSpec() && num == 0) {
                num += 1;
                $("#subUPForm").addClass("ui-state-disabled");
                $("#updateProduct").submit();
            }
        }


        /*添加编辑图片*/
        function modifiedImage(productId) {
            $("#imageProductId").val(productId);
            $.get("queryImageListByProductId.htm?CSRFToken=" + $(".token_val").val() + "&productId=" + productId, function (data) {
                var imageListHtml = "";
                for (var i = 0; i < data.length; i++) {
                    imageListHtml = imageListHtml + "<div class='col-sm-6 imgdiv' id='imagediv" + i + "'><div class='pic_item'><div class='img'><input name='customerImg' id='customerImg' type='hidden' value=''><img id='img' alt='' src='" + data[i].imageInName + "' /></div>" +
                    "<p><button type='buttom' class='btn btn-default btn-sm pull-right' onclick=" + "delImage(" + "'" + data[i].goodsImgId + "',this);" + ">删除</button>" +
                    "<button type='button' class='btn btn-default btn-sm default_btns' onclick=" + "setDefaultImage(this," + "'" + data[i].imageInName.replace('\\', "\\\\") + "'," + data[i].goodsImgId + ");" + ">" +
                    "设为默认</button></p></div></div>";
                }
                $("#imageList").html(imageListHtml);
                //$("input[type=button]").button();
            });
            $.get("queryProductVoByProductId.htm?CSRFToken=" + $(".token_val").val() + "&productId=" + productId, function (data) {
                $("#defaultImage").val(data.goodsInfoImgId);
            });
            $("#picEdit").modal("show");
        }


        /*设置货品默认图片*/
        function setDefaultImage(obj,imageName, imgId) {
            $(".default_btns").css({"background":"#fff","color":"#000"});
            $(obj).css({"background":"#46b8da","color":"#fff"});
            $("#defaultImage").val(imageName);
            $("#updateGoodsImgId").val(imgId);
        }

        /*删除图片*/
        function delImage(imageId, btn) {
            $("#delImage").append("<input type='hidden' name='delImages' value='" + imageId + "' />");
            $(btn).parents('.imgdiv').remove();
        }

        //删除货品信息
        function delProduct(productId) {

            var goodsInfoIds = [];
            var oneSelect = false;
            goodsInfoIds.push(productId);
            $.ajax({
                url: 'checkDeleteByGoodsInfoIds.htm?CSRFToken=${token }',
                data: {"goodsInfoIds": goodsInfoIds},
                dataType: "json",
                type: "POST",
                success: function (data) {
                    if (data == '0') {
                        showTipAlert("该货品已经被购买,不允许删除")
                    } else {
                        var str = '确定要删除此货品吗？';
                        $.ajax({
                            type: 'post',
                            url: 'queryGoodsMarket.htm?CSRFToken=${token}&goodsInfoId=' + productId,
                            async: false,
                            success: function (data) {
                                if (data.length > 0) {
                                    str = "此货品已参加过促销活动，确定要删除此货品吗？";
                                }
                            }
                        });
                        var goodsId = $("#goodsId").val();
                        showDeleteOneConfirmAlert('delProduct.htm?CSRFToken=${token}&productId=' + productId + '&goodsId=' + goodsId, str);

                    }
                }
            });


        }

        //弹出图片选择框
        function chooseAlert() {
            i = 1;
            art.dialog.open('queryImageManageByPbAndCidForChoose.htm?CSRFToken=${token}&size=10000', {
                lock: true,
                opacity: 0.3,
                width: '900px',
                height: '400px',
                title: '选择图片'
            });
        }


        //图片回调
        function saveChoooseImage(url) {
            if (url != "") {
                var param = "batchSaveProductImage.htm?CSRFToken=" + $(".token_val").val() + "&productId=" + $("#imageProductId").val() + "&image=" + url;
                $.ajax({
                    type: 'post',
                    url: param,
                    async: false,
                    success: function (data) {
                        //上传成功后执行回调
                        modifiedImage($("#imageProductId").val());
                    }
                });
            }

//        if(typeof (url) != 'string') {
//        url = url[0];
//      }
//      if(url.indexOf(',')!=-1){
//        url=url.substring(0,url.indexOf(','));
//      }

            $("#img").attr("src", url);
            $("#customerImg").val(url);

        }

        function saveImage() {
            var defaultImg = $("#defaultImage").val();
            if (defaultImg == null || defaultImg == "") {
                if ($(".default_btns").length > 0) {
                    $(".default_btns")[0].click();
                }
            }
            $("#updateProductImage").submit();
        }

        /**
         * 是否上架
         * */
        function updateGoodsInfoAdded(num, goodsId, goodInfoId) {
            window.location = "updateProSta.htm?CSRFToken=${token }&goodsId=" + goodsId + "&goodsInfoId=" + goodInfoId + "&goodsInfoAdded=" + num;

        }

        /**
         * 列表是否显示
         * */
        function updateShowList(num, goodsId, goodInfoId) {
            window.location = "updateProSta.htm?CSRFToken=${token }&goodsId=" + goodsId + "&goodsInfoId=" + goodInfoId + "&showList=" + num;

        }

        /**
         * 移动版是否显示
         * */
        function updateShowMobile(num, goodsId, goodInfoId) {
            window.location = "updateProSta.htm?CSRFToken=${token }&goodsId=" + goodsId + "&goodsInfoId=" + goodInfoId + "&showMobile=" + num;

        }

        /**
         * 批量上架
         * */
        function batchUpload(obj) {
            var bool = false;
            var brandIds = document.getElementsByName(obj);
            for (var i = 0; i < brandIds.length; i++) {
                var e = brandIds[i];
                if (e.checked == true) {
                    bool = true;
                }
            }
            if (bool == false) {
                showTipAlert("请先选择一行！");
            } else {
                $("#queryProduct").attr("action", "batchUpload.htm?CSRFToken=" + $(".token_val").val());
                $("#queryProduct").submit();
            }
        }

        /**
         * 批量下架batchDown
         * */
        function batchDown(obj) {
            var bool = false;
            var brandIds = document.getElementsByName(obj);
            for (var i = 0; i < brandIds.length; i++) {
                var e = brandIds[i];
                if (e.checked == true) {
                    bool = true;
                }
            }
            if (bool == false) {
                showTipAlert("请先选择一行！");
            } else {
                $("#queryProduct").attr("action", "batchDown.htm?CSRFToken=" + $(".token_val").val());
                $("#queryProduct").submit();
            }
        }

        /**
         * 列表批量 显示和隐藏
         * */
        function batchShow(obj, type) {
            var bool = false;
            var brandIds = document.getElementsByName(obj);
            for (var i = 0; i < brandIds.length; i++) {
                var e = brandIds[i];
                if (e.checked == true) {
                    bool = true;
                }
            }
            if (bool == false) {
                showTipAlert("请先选择一行！");
            } else {
                if (type == "1") {
                    $("#queryProduct").attr("action", "batchShow.htm?CSRFToken=" + $(".token_val").val() + "&sta=1");
                }
                if (type == "0") {
                    $("#queryProduct").attr("action", "batchShow.htm?CSRFToken=" + $(".token_val").val() + "&sta=0");
                }
                $("#queryProduct").submit();
            }
        }

        /**
         * 手机版批量 显示和隐藏
         * */
        function batchShowMobile(obj, type) {
            var bool = false;
            var brandIds = document.getElementsByName(obj);
            for (var i = 0; i < brandIds.length; i++) {
                var e = brandIds[i];
                if (e.checked == true) {
                    bool = true;
                }
            }
            if (bool == false) {
                showTipAlert("请先选择一行！");
            } else {
                if (type == "1") {
                    $("#queryProduct").attr("action", "batchShowMobile.htm?CSRFToken=" + $(".token_val").val() + "&sta=1");
                }
                if (type == "0") {
                    $("#queryProduct").attr("action", "batchShowMobile.htm?CSRFToken=" + $(".token_val").val() + "&sta=0");
                }
                $("#queryProduct").submit();
            }
        }

        /**
         * 导出所有
         * */
        function exportLists() {
            $("#searchForm").attr("action", "exportProductList.htm?CSRFToken=" + $(".token_val").val());
            $("#searchForm").submit();
            $("#searchForm").attr("action", "queryAllByGoodsIdNew.htm?CSRFToken=" + $(".token_val").val());
        }

        /**
         * 导出当前页
         * */
        function exportProductPages() {
            $("#searchForm").attr("action", "exportProductPage.htm?CSRFToken=" + $(".token_val").val());
            $("#searchForm").submit();
            $("#searchForm").attr("action", "queryAllByGoodsIdNew.htm?CSRFToken=" + $(".token_val").val());
        }

        /**
         * 导出选中
         * */
        function exportProductByCheckeds(obj) {
            var bool = false;
            var brandIds = document.getElementsByName(obj);
            for (var i = 0; i < brandIds.length; i++) {
                var e = brandIds[i];
                if (e.checked == true) {
                    bool = true;
                }
            }
            if (bool == false) {
                showTipAlert("请先选择一行！");
            } else {
                $("#queryProduct").attr("action", "exportProductByChecked.htm?CSRFToken=" + $(".token_val").val());
                $("#queryProduct").submit();
                $("#queryProduct").attr("action", "");
            }
        }

        /**
         * 调价按钮点击
         * */
        function addProduct() {
            $("#updateProduct")[0].reset();
            $("#productTitle").text("添加货品");
            $("#productTitle2").text("添加货品");
            $("#updateProduct").append("<input id=\"paramLength\" type=\"hidden\" value=\"${fn:length(specs)}\"/>");
            $("#updateProduct").attr("action", "saveProduct.htm?CSRFToken=${token }");
            $('#addSku1').modal('show');
        }

    </script>
</head>
<body>
<input type="hidden" class="checkProdcutExists" value="1" />
<input type="hidden" name="CSRFToken" value="${token}" class="token_val"/>
<jsp:include page="../page/header.jsp"></jsp:include>
<div class="page_body container-fluid">
  <div class="row">
    <jsp:include page="../page/left.jsp"></jsp:include>
    <input type="hidden" value="queryProduct" id="formId"/>
    <input type="hidden" value="queryAllByGoodsId.htm" id="formName"/>
    <div class="col-lg-20 col-md-19 col-sm-18 main">
      <div class="main_cont">
        <jsp:include page="../page/breadcrumbs.jsp"></jsp:include>
        <h2 class="main_title">货品列表 <small>(共${pb.rows}条)</small></h2>

        <div class="common_data p20">
          <form role="form" class="form-inline" action="queryAllByGoodsIdNew.htm" method="post" id="searchForm">
            <div class="filter_area">

              <input type="hidden" class="token_val"  name="CSRFToken" value="${token }" />
              <input type="hidden" name="showFlag" value="1" />
              <input type="hidden" name="goodsId" value="${goodsId}">
              <div class="form-group">
                <div class="input-group">
                  <span class="input-group-addon">货品名称</span>
                  <input type="text" class="form-control" name="goodsName" value="${selectBean.goodsName}"/>
                </div>
              </div>
              <div class="form-group">
                <div class="input-group">
                  <span class="input-group-addon">货品编号</span>
                  <input type="text" class="form-control" name="goodsNo" value="${selectBean.goodsNo}"/>
                </div>
              </div>
              <div class="form-group">
                <button type="submit" class="btn btn-primary">搜索</button>
              </div>

            </div>
            <div class="data_ctrl_area mb20">
              <div class="data_ctrl_search pull-right"></div>
              <div class="data_ctrl_brns pull-left">
                <button type="button" class="btn btn-info" onclick="window.location.href='findAllGoods.htm?CSRFToken=${token }'">
                  返回列表
                </button>
                <button type="button" class="btn btn-info" onclick="addProduct();">
                  添加
                </button>
                <button type="button" class="btn btn-info" onclick="checkDeleteGoodsInfo('queryProduct','productIds','确定要删除所选记录吗？')">
                  批量删除
                </button>
                <button type="button" class="btn btn-info" onclick="batchUpload('productIds')">
                  批量上架
                </button>
                <button type="button" class="btn btn-info" onclick="batchDown('productIds')">
                  批量下架
                </button>
                <button type="button" class="btn btn-info" onclick="batchShow('productIds','1')">
                  列表显示
                </button>
                <button type="button" class="btn btn-info" onclick="batchShow('productIds','0')">
                  列表隐藏
                </button>
                <button type="button" class="btn btn-info" onclick="batchShowMobile('productIds','1')">
                  手机版显示
                </button>
                <button type="button" class="btn btn-info" onclick="batchShowMobile('productIds','0')">
                  手机版隐藏
                </button>
                <div class="btn-group">
                  <button type="button" class="btn btn-info" onclick="exportLists();">导出所有</button>
                  <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                    <span class="caret"></span>
                  </button>
                  <ul class="dropdown-menu" role="menu">
                    <li><a href="javascript:" onclick="exportProductPages();">导出列表</a></li>
                    <li><a href="javascript:" onclick="exportProductByCheckeds('productIds');">导出选中</a></li>
                  </ul>
                </div>
              </div>
              <div class="clr"></div>
            </div>
          </form>
          <form action="batchDelProduct.htm?CSRFToken=${token }" method="post" id="queryProduct">
            <input type="hidden" name="goodsId" value="${goodsId}"/>
            <table class="table table-striped table-hover">
              <thead>
              <tr>
                <th width="50"><input type="checkbox" onclick="allunchecked(this,'productIds');"></th>
                <th width="250">货品信息</th>
                <th width="150">规格值</th>
                <th width="100">货号</th>
                <th>是否上架</th>
                <th>列表显示</th>
                <th>移动版显示</th>
                <th>所属商家</th>
                <th width="150">操作</th>
              </tr>
              </thead>
              <tbody>
              <c:if test="${fn:length(pb.list)==0 }">
                <tr><td colspan="9">
                  <p class="text-center">暂无可用数据！</p>
                </td></tr>
              </c:if>
              <c:forEach items="${pb.list }" var="product" varStatus="sta">
                <tr>
                  <!-- <td class="tc">
				${sta.count }
			</td> -->
                  <td>
                    <input type="checkbox" name="productIds" value="${product.goodsInfoId }"/>
                  </td>
                  <td width="250" style="text-align:left;">
                    <div class="data_item">
                      <img alt="" height="60" src="${product.goodsInfoImgId }"/>
                      <p title="${product.goodsInfoName }">${product.goodsInfoName}</p>
                      <p class="text-muted">${product.goodsInfoPreferPrice}</p>
                    </div>
                  </td>
                  <td>

                    <c:forEach items="${product.specVo }" var="spec" varStatus="sta2">
                     <%--  <c:if test="${sta2.count<3 }"> --%>
                        ${spec.spec.specName }:${spec.specValueRemark }<br/>

                      <input type="hidden" class="sp${spec.spec.specId }" value="${spec.goodsSpecDetail.specDetailId}"/>

                     <%--  </c:if> --%>
                    </c:forEach>
                  </td>
                  <td>
                      ${product.goodsInfoItemNo }
                  </td>
                  <!--<td class="tc">
				 ${product.goodsInfoCostPrice}
			</td>
			<td class="tc">
				 ${product.goodsInfoMarketPrice}
			</td>  -->
                  <td>
                    <c:if test="${product.goodsInfoAdded==1}"><a class="label label-success" onclick="updateGoodsInfoAdded(0,${product.goodsId },${product.goodsInfoId })">是</a></c:if>
                    <c:if test="${product.goodsInfoAdded==0}"><a class="label label-default" onclick="updateGoodsInfoAdded(1,${product.goodsId },${product.goodsInfoId })">否</a></c:if>
                  </td>
                  <td>
                    <c:if test="${product.showList==1}"><a class="label label-success" onclick="updateShowList(0,${product.goodsId },${product.goodsInfoId })">是</a></c:if>
                    <c:if test="${product.showList==0}"><a class="label label-default" onclick="updateShowList(1,${product.goodsId },${product.goodsInfoId })">否</a></c:if>
                  </td>
                  <td>
                    <c:if test="${product.showMobile==1}"><a class="label label-success" onclick="updateShowMobile(0,${product.goodsId },${product.goodsInfoId })">是</a></c:if>
                    <c:if test="${product.showMobile==0}"><a class="label label-default" onclick="updateShowMobile(1,${product.goodsId },${product.goodsInfoId })">否</a></c:if>
                  </td>
                  <td>
                    <c:if test="${product.thirdName != null}">${product.thirdName}</c:if>
                    <c:if test="${product.thirdName == null}">千品猫 商城</c:if>
                  </td>
                  <td>
                    <div class="btn-group">
                      <button type="button" class="btn btn-default" onclick="modifiedImage('${product.goodsInfoId }');">编辑图片</button>
                      <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                        <span class="caret"></span>
                        <span class="sr-only">Toggle Dropdown</span>
                      </button>
                      <ul class="dropdown-menu" role="menu">
                       <%-- <li><a href="javascript:" onclick="modifiedProductAutoStyle('${product.goodsInfoId }');">适配车型</a></li>--%>
                        <li><a href="javascript:" onclick="modifiedProduct('${product.goodsInfoId }');">修改</a></li>
                        <li><a href="javascript:" onclick="delProduct('${product.goodsInfoId }');">删除</a></li>
                      </ul>
                    </div>
                  </td>
                </tr>
              </c:forEach>
              </tbody>
            </table>
          </form>
          <div class="table_foot">
            <c:import url="../page/searchPag.jsp">
              <c:param name="pageBean" value="${pb }"/>
            </c:import>
          </div>

        </div>

      </div>

    </div>
  </div>
</div>

<!-- Modal修改货品开始 -->
<form id="updateProduct" action="updateProduct.htm?CSRFToken=${token }" method="post" enctype="multipart/form-data">
  <input id="update_goodsId" name="goodsId" type="hidden" value="${goodsId}"/>
  <input type="hidden" class="nowProductNo" value=""/>
  <input type="hidden" id="pageNo" name="pageNo" value="${pb.pageNo}"/>
  <input type="hidden" id="pageSize" name="pageSize" value="${pb.pageSize}"/>
  <input id="paramLength" type="hidden" value="${fn:length(specs)}"/>
  <div class="modal fade" id="addSku1"  role="dialog">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
          <h4 class="modal-title" id="productTitle">修改货品</h4>
        </div>
        <div class="modal-body">
          <!--<form role="form">-->
          <div class="form-horizontal">
            <div class="form-group">
              <label class="control-label col-sm-5"><span class="text-danger">*</span>货品名称：</label>
              <div class="col-sm-1"></div>
              <div class="col-sm-16">
                <input id="goodsInfoIds" name="goodsInfoId" type="hidden"  class="j_text ui-widget-content ui-corner-all"/>
                <input type="text" class="form-control required" name="goodsInfoName" id="update_goodsInfoName"  maxlength="120" minlength="3"/>
              </div>
            </div>
            <div class="form-group">
              <label class="control-label col-sm-5">货品副标题：</label>
              <div class="col-sm-1"></div>
              <div class="col-sm-16">
                <input type="text" class="form-control" name="goodsInfoSubtitle" id="update_goodsInfoSubtitle">

              </div>
            </div>
            <div class="form-group">
              <label class="control-label col-sm-5"><span class="text-danger">*</span>货品编号：</label>
              <div class="col-sm-1"></div>
              <div class="col-sm-16">
                <input type="text" class="form-control required maxlength numandletter" onBlur="checkExists('checkProductNo','productNo',this,'update_goodsInfoItemNo_Tips',1,'nowProductNo')" name="goodsInfoItemNo" maxlength="32" id="update_goodsInfoItemNo" minlength="10">

              </div>
            </div>

            <%--<div class="form-group">--%>
              <%--<label class="control-label col-sm-5"><span class="text-danger">*</span>库存：</label>--%>
              <%--<div class="col-sm-1"></div>--%>
              <%--<div class="col-sm-16">--%>
                <%--<input type="text" class="form-control w100 required number" name="goodsInfoStock" id="update_goodsInfoStock">--%>

              <%--</div>--%>
            <%--</div>--%>
            <div class="form-group">
              <label class="control-label col-sm-5"><span class="text-danger">*</span>销售价：</label>
              <div class="col-sm-1"></div>
              <div class="col-sm-16">
                <div class="input-group w200">
                  <input type="hidden" name="oldPrice" id="oldPrice" class="j_text ui-widget-content ui-corner-all">
                  <input type="text" class="form-control required number" name="goodsInfoPreferPrice" id="update_goodsInfoPreferPrice">

                </div>
              </div>
            </div>
              <!-- 2015.10.22 wuyanbo 添加会员价 -->
            <div class="form-group">
              <label class="control-label col-sm-5"><span class="text-danger">*</span>会员价：</label>
              <div class="col-sm-1"></div>
              <div class="col-sm-16">
                  <div class="input-group w200">
                      <input type="hidden" name="oldVipPrice" id="oldVipPrice" class="j_text ui-widget-content ui-corner-all">
                      <input type="text" class="form-control required number" name="goodsInfoVipPrice" id="update_goodsInfoVipPrice">

                  </div>
              </div>
            </div>
            <div class="form-group">
              <label class="control-label col-sm-5"><span class="text-danger">*</span>成本价：</label>
              <div class="col-sm-1"></div>
              <div class="col-sm-16">
                <div class="input-group w200">
                  <input type="text" class="form-control required number" name="goodsInfoCostPrice" id="update_goodsInfoCostPrice">

                </div>
              </div>
            </div>
            <div class="form-group">
              <label class="control-label col-sm-5"><span class="text-danger">*</span>市场价：</label>
              <div class="col-sm-1"></div>
              <div class="col-sm-16">
                <div class="input-group w200">
                  <input type="text" class="form-control required number" name="goodsInfoMarketPrice" id="update_goodsInfoMarketPrice">

                </div>
              </div>
            </div>
            <div class="form-group">
              <label class="control-label col-sm-5"><span class="text-danger">*</span>重量：</label>
              <div class="col-sm-1"></div>
              <div class="col-sm-16">
                <div class="input-group w200">
                  <input type="text" class="form-control required number" id="update_goodsInfoWeight" name="goodsInfoWeight" value="">

                </div>
              </div>
            </div>

            <div class="form-group">
              <label class="control-label col-sm-5"><span class="text-danger">*</span>是否上架：</label>
              <div class="col-sm-1"></div>
              <div class="col-sm-16">
                <label class="radio-inline">
                  <input type="radio" id="update_added" value="1" name="goodsInfoAdded"> 是
                </label>
                <label class="radio-inline">
                  <input type="radio" id="update_unadded" value="0" name="goodsInfoAdded" checked="checked"> 否
                </label>
              </div>
            </div>
           <%-- <div class="form-group">
              <label class="control-label col-sm-5"><span class="text-danger">*</span>参与会员折扣：</label>
              <div class="col-sm-1"></div>
              <div class="col-sm-16">
                <label class="radio-inline">
                  <input type="radio" id="isCustomerAdded" value="1" name="isCustomerDiscount"> 是
                </label>
                <label class="radio-inline">
                  <input type="radio" value="0" id="isCustomer"  name="isCustomerDiscount" checked="checked"> 否
                </label>
              </div>
            </div>--%>

            <div class="form-group">
              <label class="control-label col-sm-5"><span class="text-danger">*</span>列表显示：</label>
              <div class="col-sm-1"></div>
              <div class="col-sm-16">
                <label class="radio-inline">
                  <input type="radio" id="showList1" value="1" name="showList"> 是
                </label>
                <label class="radio-inline">
                  <input type="radio" value="0" id="showList2"  name="showList" checked="checked"> 否
                </label>
              </div>
            </div>

            <div class="form-group">
              <label class="control-label col-sm-5"><span class="text-danger">*</span>移动版显示：</label>
              <div class="col-sm-1"></div>
              <div class="col-sm-16">
                <label class="radio-inline">
                  <input type="radio" id="showMobile1" value="1" name="showMobile"> 是
                </label>
                <label class="radio-inline">
                  <input type="radio" value="0" id="showMobile2"  name="showMobile" checked="checked"> 否
                </label>
              </div>
            </div>

            <div class="form-group">
              <label class="control-label col-sm-5 fw">服务支持：</label>
              <div class="col-sm-1"></div>
              <div class="col-sm-16">
                <c:forEach items="${support}" var="supp">
                  <label class="checkbox-inline">
                    <input type="checkbox" name="productSupp" id="productSupp${supp.id }" value="${supp.id }" />
                      ${supp.serviceName }
                  </label>
                </c:forEach>
              </div>
            </div>
          </div>
          <!--</form>-->
        </div>
        <div class="modal-footer"><!--   onclick="$('#addSku1').modal('hide');$('#addSku2').modal('show');" " -->
          <button id="update_next" type="button" class="btn btn-primary">下一步</button>
          <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        </div>
      </div>
    </div>
  </div>
  <!-- 修改货品结束 -->

  <!-- Modal -->
  <div class="modal fade oldParamDiv" id="addSku2"  role="dialog">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
          <h4 class="modal-title" id="productTitle2">添加货品</h4>
        </div>
        <div class="modal-body" style="height: 300px;">
          <!--<form role="form" class="form-horizontal">-->
          <%--<div class="form-horizontal">
              <div class="form-group">
                  <label class="control-label col-sm-5"><span class="text-danger">*</span>汽车配件类型：</label>
                  <div class="col-sm-1"></div>
                  <div class="col-sm-16">
                      <div class="input-group w200">
                          <select class="inline required" data-live-search="true" name="goodsInfoAutoPartsType" id="goodsInfoAutoPartsType">
                              <option value="1">OEM件</option>
                              <option value="2">常用件</option>
                          </select>

                      </div>
                  </div>
              </div>--%>
             <%-- <div class="form-group">
                  <label class="control-label col-sm-5"><span class="text-danger"></span>OEM编码：</label>
                  <div class="col-sm-1"></div>
                  <div class="col-sm-16">
                      <div class="input-group w200">
                          <input type="text" id="goodsInfoOem" name="goodsInfoOem" class="form-control" maxlength="50" &lt;%&ndash;minlength="3"&ndash;%&gt; value="">

                      </div>
                  </div>
              </div>--%>
            <c:if test="${!empty specs}">
              <c:forEach items="${specs}" var="spec">
                <div class="form-group" >
                  <label class="control-label col-sm-5">选择规格：</label>
                  <div class="col-sm-1"></div>
                  <div class="col-sm-16">
                    <div class="input-group w200">

                      <span class="input-group-addon">${spec.spec.specName }:</span>
                      <input type="hidden" name="specId" value="${spec.spec.specId }"/>
                      <select onChange="selProductParam('update_param_Tips',1,'up_remark_${spec.spec.specId }','sel${spec.spec.specId }');" spec_id="${spec.spec.specId }" id="sel${spec.spec.specId }" class="form-control updateSelParam up_sel_spec" name="specDetailId">
                        <c:forEach items="${spec.specValList }" var="detail">
                          <option value="${detail.specDetail.specDetailId }">${detail.specValueRemark }</option>
                        </c:forEach>
                      </select>
                      <input type="hidden" name="specRemark" class="up_remark_${spec.spec.specId }" value="" />

                    </div>
                  </div>
                </div>
              </c:forEach>
            </c:if>
            <!--<div class="form-group">
              <div class="col-sm-offset-6 col-sm-16">
                <div class="input-group w200">
                  <span class="input-group-addon">尺码</span>
                  <select class="form-control">
                    <option>S</option>
                    <option>M</option>
                    <option>L</option>
                    <option>XL</option>
                  </select>
                  </div>
                </div>
              </div>-->
              <!--2015.10.22 wuyanbo 添加会员价格-->
            <div class="form-group">
              <label class="control-label col-sm-5">库存及价格：</label>
              <div class="col-sm-1"></div>
              <div class="col-sm-16">
                <table class="table table-bordered table-hover">
                  <thead>
                  <tr>
                    <th>仓库</th>
                    <th>库存</th>
                    <th>销售价格</th>
                    <th>会员价格</th>
                  </tr>
                  </thead>
                  <tbody>
                  <c:forEach items="${wareHouse}" var="ware">
                    <tr>
                      <td>${ware.wareName }:</td>
                      <input type="hidden" id="ware${ware.wareId }" name="wareId" value="${ware.wareId }"/>
                      <td><input type="text" class="form-control update_productStock"
                                 <%--onkeyup="this.value=this.value.replace(/[^\d.]/g,'') "--%>
                                 onafterpaste="this.value=this.value.replace(/[^\d]/ "
                                 value="0" id="productStock${ware.wareId}" name="productStocks"/></td>
                      <td><input type="text" class="form-control update_productPrice"
                                 <%--onkeyup="this.value=this.value.replace(/[^\d.]/g,'') "--%>
                                 onafterpaste="this.value=this.value.replace(/[^\d]/ "
                                 value="0.00" id="productPrice${ware.wareId }" name="productPrices"></td>
                      <td><input type="text" class="form-control update_productVipPrice"
                                 <%--onkeyup="this.value=this.value.replace(/[^\d.]/g,'') "--%>
                                 onafterpaste="this.value=this.value.replace(/[^\d]/ "
                                 value="0.00" id="productVipPrice${ware.wareId }" name="productVipPrices"></td>
                    </tr>
                  </c:forEach>
                  </tbody>
                </table>
              </div>
            </div>
          </div>
          <!--</form>-->
        </div>
        <div class="modal-footer"><!-- id="update_pre" -->
          <button type="button" class="btn btn-default" onclick="$('#addSku2').modal('hide');$('#addSku1').modal('show');">上一步</button>
          <button type="button" class="btn btn-primary" id="subUPForm" onclick="updateProduct();">保存</button>
        </div>
      </div>
    </div>
  </div>
</form>

<!-- START 编辑货品图片弹框 -->
<form id="updateProductImage" enctype="multipart/form-data" action="updateProductImage.htm?CSRFToken=${token }" method="post">
  <input id="goodsId" name="goodsId" type="hidden" value="${pb.list[0].goodsId}"/>
  <input type="hidden" name="pageNo" value="${pb.pageNo }"/>
  <input  name="pageSize" type="hidden" value="${pb.pageSize}"/>
  <input id="imageProductId" name="productId" type="hidden"/>
  <input id="defaultImage" name="defaultImage" type="hidden" value=" "/>
  <input id="updateGoodsImgId" name="goodsImgId" type="hidden" value=" "/>
  <div class="modal fade" id="picEdit">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
          <h4 class="modal-title">编辑图片</h4>
          <div id="delImage">
          </div>
        </div>
        <div class="modal-body">
          <div class="mb20">
            <button type="button" class="btn btn-info" onclick="chooseAlert();">添加图片</button>
          </div>
          <div class="container-fluid">
            <div class="row" id="imageList">

            </div>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-primary" onclick="saveImage()">保存</button>
          <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        </div>
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
  </div>
</form>
<!-- END 编辑货品图片弹框 -->

<!-- START 编辑货品适配车型弹框 2015-12-19 wuyanbo add-->
<form id="updateProductAutoStyle" action="updateProductAutoStyle.htm?CSRFToken=${token }" method="post">
    <input id="update_style_goodsId" name="goodsId" type="hidden" value="${goodsId}"/>
    <input id="style_goodsInfoIds" name="goodsInfoIds" type="hidden" value=""/>
    <input type="hidden" class="nowProductNo" value=""/>
    <div class="modal fade" id="productAutoStyle" role="dialog" >
        <div class="modal-dialog" style="width:950px;height:700px;">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="productAutoStyleTitle">修改适配车型</h4>
                </div>
                <div class="ztree" id="tree_penal" style="height:500px;overflow-y:auto;">
                        <%--<ul id="ztree" class="ztree1"></ul>--%>
                    <ul id="treeDemo" class="ztree"></ul>
                </div>
                <div class="modal-footer">
                    <button id="update" type="button" class="btn btn-primary">保存</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </div>
        </div>
    </div>
</form>
<!-- END 编辑货品适配车型弹框 -->

<div class="advanced_search_cont none">
  <div class="advanced_search_form">
    <form class="form-horizontal">
      <div class="form-group">
        <label class="control-label col-sm-7">商品名称：</label>
        <div class="col-sm-15">
          <input type="text" class="form-control">
        </div>
      </div>
      <div class="form-group">
        <label class="control-label col-sm-7">商品编号：</label>
        <div class="col-sm-15">
          <input type="text" class="form-control">
        </div>
      </div>
      <div class="form-group">
        <label class="control-label col-sm-7">商品分类：</label>
        <div class="col-sm-15">
          <select class="cate_selector" data-live-search="true">
            <optgroup label="女装">
              <option>上衣</option>
              <option>裙装</option>
              <option>风衣</option>
              <option>休闲裤</option>
              <option>打底裤</option>
              <option>毛针织衫</option>
            </optgroup>
            <optgroup label="女装">
              <option>上衣</option>
              <option>裙装</option>
              <option>风衣</option>
              <option>休闲裤</option>
              <option>打底裤</option>
              <option>毛针织衫</option>
            </optgroup>
            <optgroup label="女装">
              <option>上衣</option>
              <option>裙装</option>
              <option>风衣</option>
              <option>休闲裤</option>
              <option>打底裤</option>
              <option>毛针织衫</option>
            </optgroup>
            <optgroup label="女装">
              <option>上衣</option>
              <option>裙装</option>
              <option>风衣</option>
              <option>休闲裤</option>
              <option>打底裤</option>
              <option>毛针织衫</option>
            </optgroup>
          </select>
        </div>
      </div>
      <div class="form-group">
        <label class="control-label col-sm-7">商品品牌：</label>
        <div class="col-sm-15">
          <select class="cate_selector" data-live-search="true">
            <option>STEVEMADDEN</option>
            <option>翰墨生香</option>
            <option>玺玥</option>
            <option>娘子写</option>
            <option>E.Beauty/逸·红颜</option>
            <option>逸红颜</option>
          </select>
        </div>
      </div>
      <div class="form-group">
        <label class="control-label col-sm-7">是否上架：</label>
        <div class="col-sm-15">
          <label class="radio-inline">
            <input type="radio" name="inlineRadioOptions" id="inlineRadio1" value="option1"> 是
          </label>
          <label class="radio-inline">
            <input type="radio" name="inlineRadioOptions" id="inlineRadio2" value="option2"> 否
          </label>
        </div>
      </div>
      <div class="form-group">
        <label class="control-label col-sm-7">是否第三方：</label>
        <div class="col-sm-15">
          <label class="radio-inline">
            <input type="radio" name="inlineRadioOptions2" id="inlineRadio3" value="option1"> 全部
          </label>
          <label class="radio-inline">
            <input type="radio" name="inlineRadioOptions2" id="inlineRadio4" value="option2"> 是
          </label>
          <label class="radio-inline">
            <input type="radio" name="inlineRadioOptions2" id="inlineRadio5" value="option3"> 否
          </label>
        </div>
      </div>
      <div class="form-group">
        <label class="control-label col-sm-7">商家名称：</label>
        <div class="col-sm-15">
          <input type="text" class="form-control">
        </div>
      </div>
      <div class="form-group">
        <div class="col-sm-offset-7 col-sm-15">
          <button type="submit" class="btn btn-primary btn-sm">确认搜索</button>
        </div>
      </div>
    </form>
  </div>
</div>

<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=basePath%>js/bootstrap.min.js"></script>
<script src="<%=basePath%>js/summernote.min.js"></script>
<script src="<%=basePath%>js/language/summernote-zh-CN.js"></script>
<script src="<%=basePath %>js/jquery.ztree.core-3.5.min.js"></script>
<script src="<%=basePath %>js/jquery.ztree.excheck-3.5.min.js"></script>
<script src="<%=basePath %>js/jquery.ztree.exhide-3.5.min.js"></script>
<script src="<%=basePath%>js/bootstrap-select.min.js"></script>
<script src="<%=basePath%>js/common.js"></script>
<script src="<%=basePath %>js/common/common_alert.js"></script>
<script src="<%=basePath %>js/common/common_checked.js"></script>
<script src="<%=basePath %>js/goods/goods_product.js"></script>
<script src="<%=basePath %>js/goods_default.js"></script>
<script src="<%=basePath %>js/select2.min.js"></script>
<script>


    $(function () {
        $('select[data-live-search="true"]').select2();
        /* 表单项的值点击后转换为可编辑状态 */
        $('.form_value').click(function () {
            var formItem = $(this);
            if (!$('.form_btns').is(':visible')) {
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
            content: '当商品库存低于该值则进行预警',
            trigger: 'hover'
        });
        /* 高级搜索 */
        $('.advanced_search').popover({
            html: true,
            title: '高级搜索',
            content: $('.advanced_search_cont').html(),
            trigger: 'click',
            placement: 'bottom'
        }).click(function () {
            $('select[data-live-search="true"]').select2();
        });


//        initBrandName();
        $("#update").click(function(){
            var treeObj =$.fn.zTree.getZTreeObj("treeDemo");
            if(treeObj){
                var nodes = treeObj.getCheckedNodes(true);
                if(nodes.length==0){
                    alert('至少选择一个适配的车型！');
                    return false;
                }
                var param={};
                param.goodsInfoId=$("#style_goodsInfoIds").val();
                param.goodsId=$("#update_style_goodsId").val();
                var paramInfo=[];
//                var paramInfo="[";
                for(var i=0;i < nodes.length;i++){
                    //查询该节点下是否有选中的节点
                    var childNodes = treeObj.getNodesByParam("checked", true, nodes[i]);
                    if(childNodes.length==0){
                        var style ={};
                        style.id=nodes[i].id;
                        style.levelNum=nodes[i].levelNum;
                        paramInfo.push(style);
//                        if(paramInfo.length>1){
//                            paramInfo+=",";
//                        }
//                        paramInfo+="{\"id\":\""+nodes[i].id+"\",\"levelNum\":\""+nodes[i].levelNum+"\"}"
                    }
                }
                param.autoStyle=JSON.stringify(paramInfo);
////                paramInfo+="]";
////                param.autoStyle=paramInfo;
//                alert("11123:"+JSON.stringify(paramInfo));
                $.ajax({
                    url:'updateProductAutoStyle.htm?CSRFToken=${token}',
                    dataType:'json',
                    data:param,
//                    data:{"goodsInfoId":$("#goodsInfoIds").val(),"autoStyle":JSON.stringify(paramInfo)},
                    success:function(msg){
                        if(msg && msg.success){
                            alert('修改成功！');
                            $("#productAutoStyle").modal('hide');
                        }

                    }
                });

            }
        });
    });

</script>

</body>
</html>
