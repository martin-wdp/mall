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
    <link href="<%=basePath %>css/zTreeStyle/zTreeStyle.css" rel="stylesheet">
    <link href="<%=basePath %>js/artDialog/skins/simple.css" rel="stylesheet">
    <link href="<%=basePath %>css/style.css" rel="stylesheet">
      <link href="<%=basePath%>css/bootstrap-select.min.css" rel="stylesheet">
      <link href="<%=basePath %>css/select2.min.css" rel="stylesheet">

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

            <h2 class="main_title">添加订单</h2>
              <form id="checkform" action="">
                  <input type="hidden" name="customerId" id="customerId"/>
                  <input type="hidden" name="CSRFToken" value="${token}"/>
                  <input type="hidden" name="vipFlag" id="vipFlag" value="0"/>
            <div class="add_order_1 common_form common_form_lg p20">
              <div class="form-horizontal">

                  <div class="form-group">
                  <label class="control-label col-sm-5"><span class="text-danger">*</span>用户名：</label>
                  <div class="col-sm-1"></div>
                  <div class="col-sm-8">
                    <input type="text" placeholder="手机号码" class="form-control required " id="customerName" name="customerName" onblur="checkUsername()">
                  </div>
                  <div class="col-sm-1"></div>
                  <div class="col-sm-3">
                    <a href="javascript:;" class="help_tips">
                      <i class="icon iconfont">&#xe611;</i>
                    </a>
                  </div>
                </div>
                <div class="form-group" id="isvipvaluediv" style="display: none">
                  <label class="control-label col-sm-5"><span class="text-danger">*</span>会员类型：</label>
                    <input type="hidden" id="vip_flag" name="isvip" value="0"/>
                  <div class="col-sm-1"></div>
                  <div class="col-sm-17">
                    <p id="isvipvalue"></p>
                  </div>
                </div>
                <div class="form-group">
                  <label class="control-label col-sm-5"><span class="text-danger">*</span>订单商品：</label>
                  <div class="col-sm-1"></div>
                  <div class="col-sm-17">
                    <a class="btn btn-info btn-sm add_goods_btn">添加订单商品</a>


                  </div>
                </div>
                  <div class="form-group">
                      <label class="control-label col-sm-5">已选择货品：</label>
                      <div class="col-sm-1"></div>
                      <div class="col-sm-17">
                          <div style="max-height:300px;overflow-y:auto;position:relative;">

                          <table id="readproduct" class="table table-striped table-hover table-bordered" style="margin-bottom:0px;" >
                              <thead style="top:0;">
                              <tr>
                                  <th width="100">货品图片</th>
                                  <th width="150">货品编号</th>
                                  <th width="240">货品名称</th>
                                  <th width="100">普通价格</th>
                                  <th width="100">会员价格</th>
                                  <th width="90">数量</th>
                                  <th width="130">操作</th>
                              </tr>
                              </thead>
                              <tbody>

                              </tbody>
                          </table>



                          </div>
                      </div>
                  </div>
                <div class="form-group">
                  <div class="col-sm-offset-6 col-sm-8">
                    <a class="btn btn-primary" onclick="nextStep()">下一步</a>
                  </div>
                </div>

              </div>
            </div>

            <div class="add_order_2 common_form common_form_lg" style="display:none;">
              <p><a class="btn btn-default" onclick="$('.add_order_1').show();$('.add_order_2').hide()">返回修改会员与商品</a> </p>
              <div class="form-horizontal">
                <div class="form-group">
                  <label class="control-label col-sm-5">收货人信息：</label>
                  <div class="col-sm-1"></div>
                  <div class="col-sm-17">
                    <div class="order_address_add" >
                      <div class="form-group">
                        <label class="control-label col-xs-5"><span class="text-danger">*</span>收货人姓名：</label>
                        <div class="col-xs-1"></div>
                        <div class="col-xs-5">
                          <input type="text" class="form-control required" name="addressName">
                        </div>
                      </div>

                        <div class="form-group">
                            <label class="control-label col-sm-5"><span class="text-danger">*</span>收货地区：</label>
                            <div class="col-sm-1"></div>
                            <div class="col-sm-18">
                                <select class="form-control inline" data-live-search="true" id="provinces" name="infoProvince" onchange="queryCityByProvinceId(this)">
                                    <option>选择省份</option>
                                    <option>江苏省</option>
                                </select>
                                <select class="form-control inline" data-live-search="true" name="infoCity" id="cities" onchange="queryDistrictByCityId(this)">
                                    <option>选择城市</option>
                                    <option>南京市</option>
                                </select>
                                <select class="form-control inline" data-live-search="true" id="districts" name="distinctId" >
                                    <option value="0">选择区县</option>
                                    <option>建邺区</option>
                                </select>
                            </div>
                        </div>


                      <div class="form-group">
                        <label class="control-label col-xs-5"><span class="text-danger">*</span>收货地址：</label>
                        <div class="col-xs-1"></div>
                        <div class="col-xs-15">
                          <input type="text" class="form-control required" name="addressDetail">
                        </div>
                      </div>
                      <div class="form-group">
                        <label class="control-label col-xs-5"><span class="text-danger">*</span>手机：</label>
                        <div class="col-xs-1"></div>
                        <div class="col-xs-8">
                          <input type="text" class="form-control mobile required" name="addressMoblie">
                        </div>
                      </div>
                        <div class="form-group">
                        <label class="control-label col-xs-5">固话：</label>
                        <div class="col-xs-1"></div>
                        <div class="col-xs-8">
                          <input type="text" class="form-control isPhone" name="addressPhone">
                        </div>
                      </div>
                      <div class="form-group">
                        <label class="control-label col-xs-5">邮编：</label>
                        <div class="col-xs-1"></div>
                        <div class="col-xs-8">
                          <input type="text" class="form-control digits" name="addressZip">
                        </div>
                      </div>
                    </div>
                  </div>
                </div>

                <div class="form-group">
                  <label class="control-label col-sm-5">快递公司选择：</label>
                  <div class="col-sm-1"></div>
                      <div class="col-xs-5">
                          <select class="form-control" name="companyInfo">

                              <c:forEach items="${companyList }" var="list">
                                  <option value="${list.logComId }_${list.name }">${list.name }</option>
                              </c:forEach>
                          </select>


                      </div>


                </div>
                <div class="form-group">
                  <label class="control-label col-sm-5">支付方式选择：</label>
                  <div class="col-sm-1"></div>
                  <div class="col-sm-16 zhifu">


                  </div>
                </div>
                <div class="form-group">
                  <label class="control-label col-sm-5">发票信息：</label>
                  <div class="col-sm-1"></div>
                  <div class="col-sm-16">
                    <div class="radio">
                      <label>
                        <input type="radio" name="invoiceType" value="0" checked>
                        不需要发票
                      </label>
                    </div>
                    <div class="radio">
                      <label>
                        <input type="radio" name="invoiceType" value="1">
                        普通发票
                      </label>
                    </div>
                    <div class="invoice_info" style="display:none;">
                      <div class="form-group">
                        <label class="control-label col-xs-5">发票抬头：</label>
                        <div class="col-xs-1"></div>
                        <div class="col-xs-15">
                          <input type="text" class="form-control " name="invoiceTitle" id="invoiceTitle">
                        </div>
                      </div>
                      <div class="form-group">
                        <label class="control-label col-xs-5">发票内容：</label>
                        <div class="col-xs-1"></div>
                        <div class="col-xs-5">
                          <select class="form-control" name="invoiceContent">
                            <option>明细</option>
                            <option>办公用品</option>
                            <option>电脑配件</option>
                            <option>耗材</option>
                          </select>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>

                <div class="form-group">
                  <label class="control-label col-sm-5">支付订单确认：</label>
                  <div class="col-sm-1"></div>
                  <div class="col-sm-16">
                    <div class="add_order_info">
                      <div class="form-group">
                        <label class="control-label col-xs-5"><span class="text-danger">*</span>商品总价格：</label>
                        <div class="col-xs-1"></div>
                        <div class="col-xs-5">
                            <input type="text" class="form-control isNoInteger required " maxlength="10" name="goodsAllPrice" id="totalPrice">
                        </div>
                      </div>
                      <div class="form-group">
                        <label class="control-label col-xs-5"><span class="text-danger">*</span>配送费用：</label>
                        <div class="col-xs-1"></div>
                        <div class="col-xs-5">
                            <input type="text" class="form-control isNoInteger required " maxlength="4" name="freightPrice" id="freight">
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                  <div class="form-group">
                      <label class="control-label col-sm-5">订单备注：</label>
                      <div class="col-sm-1"></div>
                      <div class="col-sm-16">
                          <input type="text" class="form-control" name="customerRemark">
                      </div>
                  </div>
                <div class="form-group">
                  <div class="col-sm-offset-6 col-sm-10">
                    <a class="btn btn-primary" onclick="saveOrder()">保存</a>
                    <a class="btn btn-default" onclick="cancleOrder()">取消</a>
                  </div>
                </div>
              </div>
            </div>
              </form>
          </div>
        </div>
      </div>
    </div>

  <!-- 添加订单货品Modal -->
  <div class="modal fade" id="chooseGoods"  role="dialog">
      <div class="modal-dialog modal-lg">
          <div class="modal-content">
              <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                  <h4 class="modal-title">选择货品</h4>
              </div>
              <div class="modal-body">
                  <div class="mb10">
                      <form class="form-inline" id="searchGoodsInfo" action="" method="post" >
                          <input type="hidden" name="pageNo" id="pageNo" value=""/>
                          <input type="hidden" name="pageSize" id="pageSize" value=""/>
                          <div class="form-group" style="margin-bottom:10px;">
                              商品名称：<input type="text" class="form-control" placeholder="商品名称"  name="goodsName">
                          </div>
                          <div class="form-group" style="margin-bottom:10px;">
                              商品编号：<input type="text" class="form-control" placeholder="商品编号"  name="goodsNo">
                          </div>
                          <div class="form-group" style="margin-bottom:10px;">
                              货品编号：<input type="text" class="form-control" placeholder="货品编号" name="goodsInfoItemNo">
                          </div>
                          <div class="form-group" style="margin-bottom:10px;">
                              货品售价：<input type="text" class="form-control isNumber" id="lowgoodsInfoPrice" name="lowGoodsInfoPrice">
                              -<input type="text" class="form-control isNumber"  id="highgoodsInfoPrice" name="highGoodsInfoPrice">
                          </div>
                          <div class="form-group" style="margin-bottom:10px;">
                              <button type="button" class="btn btn-info" onclick="chooseProduct();">搜索</button>
                          </div>
                      </form>
                  </div>
                  <table class="table table-striped table-hover table-bordered" id="productAddList">
                      <thead>
                      <tr>
                          <th width="50"><input type="checkbox" onchange="chooseAllPro(this);" id="chooseAllPro"></th>
                          <th width="100">货品图片</th>
                          <th width="100">货品规格</th>
                          <th width="150">货品编号</th>
                          <th>货品名称</th>
                          <th>普通价格</th>
                          <th>会员价格</th>
                      </tr>
                      </thead>
                      <tbody>

                      </tbody>
                  </table>
                  <div class="table_foot" id="productAddList_table_foot">
                      <div class="table_pagenav pull-right">
                          <div class="meneame">

                          </div>
                      </div>
                      <div class="clr"></div>
                  </div>
              </div>
              <div class="modal-footer">
                  <button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
                  <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
              </div>
          </div>
      </div>
  </div>
  <!-- 添加订单成功Modal -->
  <div class="modal fade" id="dialog-confirm2"  role="dialog" width="">
      <div class="modal-dialog">
          <div class="modal-content">
              <form class="form-horizontal" method="post" id="auditProductAction" action="toaddorder.htm">
                  <input type="hidden" name="CSRFToken" value="${token}">


                  <div class="modal-header">
                      <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                              aria-hidden="true">&times;</span></button>
                      <h4 class="modal-title">操作提示</h4>
                  </div>
                  <div class="modal-body">
                    订单添加成功!
                  </div>
                  <div class="modal-footer">
                      <button type="submit" class="btn btn-primary" >确定</button>
                  </div>
              </form>
          </div>
      </div>
  </div>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<%=basePath %>js/bootstrap.min.js"></script>
    <script src="<%=basePath %>js/jquery.ztree.core-3.5.min.js"></script>
    <script src="<%=basePath %>js/jquery.ztree.excheck-3.5.min.js"></script>
    <script src="<%=basePath %>js/common.js"></script>
  <script src="<%=basePath %>js/select2.min.js"></script>
  <script type="text/javascript" src="<%=basePath %>js/jsOperation.js"></script>
  <script src="<%=basePath%>js/common/common_alert.js"></script>
    <script>



        var payflag='';
        var customerflag=false;
        function saveOrder(){

            var districts=$("#districts").val();
            if(districts==0){
                showTipAlert("请选择收货地区");
                        return;
            }
            if($("#checkform").valid()&&payflag==1){

                $.ajax({
                    type: "post",
                    url: "ajaxsaveOrder.htm",
                    async: false,
                    data: $("#checkform").serialize(),
                    traditional: true,
                    success: function (data) {
                    if(data==1){
                        showTipAlert("选择的货品中存在库存不足,请重新选择!")
                    }
                    if(data==2){
                        showTipAlert("货品的总价格不能为空!")
                    }
                    if(data==3) {
                        showTipAlert("物流公司不能为空,请先去物流公司设置添加!");
                    }
                    if(data==4){
                        showTipAlert("支付方式不能为空,请先添加支付方式或开启支付方式!");
                    }
                    if(data==5){
                   window.location.href="orderlist.htm";
                    }
                    }
                });
            }
        }
        function checkUsername(){
            var customerName=$("#customerName").val();

            if(customerName==''||customerName==null) {
                showTipAlert("用户名不能为空")
            }else{
            //根据会员名称查询会员是否存在
            $.ajax({
                type: "post",
                url: "ajaxGetorderDetail.htm?CSRFToken=${token}",
                async: false,
                data: {"customerName": customerName},
                traditional: true,
                success: function (data) {
                    $("#isvipvaluediv").hide();
                if(data.isPassed==0){
                    showTipAlert("输入的会员用户名不存在!");
                    customerflag=false;
                }else{
                    customerflag=true;
                    $("#customerId").val(data.customerId);
                    if(data.vip!==null){
                        $("#vipFlag").val(data.vip);
                    }
                    $("#isvipvalue").text($("#vipFlag").val()=="1"?"企业会员":"普通会员");
                        $("#vip_flag").val($("#vipFlag").val()=="1"?"1":"0");
                    $("#isvipvaluediv").show();
                    if(data.payList.length>0){
                        var stt="";
                        $.each(data.payList,function(index,pay){
                          if(pay.isOpen==1){
                              payflag=1;

                              var checked="";
                              if(index==0){
                                  checked="checked";
                              }
                              else{
                                  checked="";
                              }
                              stt+='<div class="radio"><label><input type="radio"'+checked+' name="payType" value="'+pay.paymentId+'">'+pay.name;
                              stt+='</label></div>';
                          }
                        })
                        if(payflag!=1){
                            stt+='<label>  <strong style="color: red">请到支付方式设置开启支付功能!</strong></label>';
                        }
                        $(".zhifu").html(stt);

                    }
                }
                }
            });

            }

        }

        var provinceflag='';
        //下一步
        function nextStep(){

            var customerName=$("#customerName").val();
        if(customerflag){
               var str=$('input[name="goodsIdP"]').length;
               if(str==0){
                   showTipAlert("请至少选择一件货品!")
                   return;
               }
            $('.add_order_2').show();
            $('.add_order_1').hide();

               var total=0;
            //计算货品总金额
            $(".goodsPrice").each(function (index, item) {
               var goodsNum= $(this).val();
                var goodsPriceinit=$(this).attr("attr_price");
                var goodsPrice=goodsPriceinit.split("#")[0];
                var goodsVipPrice=goodsPriceinit.split("#")[1];
                var isVip = $("#vipFlag").val();
                //一件货品的价格
                var singlepriceSum = 0;
                if(isVip == "1"){
                     singlepriceSum=accMul(goodsNum,goodsVipPrice);
                }else{
                     singlepriceSum=accMul(goodsNum,goodsPrice);
                }


                //货品总价格
                total=accAdd(total,singlepriceSum);

            });
               $("#totalPrice").val(total);



            var checked="";
            //加载省份
            $.get("queryAllProvince.htm?CSRFToken=${token}",function(data){
                var provinces="<option value=''>选择省份</option>";
                for(var i=0;i<data.length;i++){
                    provinces+="<option ";
                    if(data[i].provinceId==provinceflag){
                        checked=" selected ";
                    }else{
                        checked='';
                    }
                    provinces+=checked+" value='"+data[i].provinceId+"'>"+data[i].provinceName+"</option>";
                }
                $("#provinces").html(provinces);
                $('select[data-live-search="true"]').select2();
            });


        }
        }

        /*根据省份ID查询城市*/
        function queryCityByProvinceId(province){
            provinceflag=$(province).val();
            //设置省份名称
            $.get("queryCityByProvinceId.htm?CSRFToken=${token}&provinceId="+($(province).val()),function(data){
                var provinces="<option value=''>选择城市</option>";
                for(var i=0;i<data.length;i++){
                    provinces+="<option value='"+data[i].cityId+"'>"+data[i].cityName+"</option>";
                }
                $("#cities").html(provinces);
                $('select[data-live-search="true"]').select2();
            });
        }

        /*根据城市ID查询区县*/
        function queryDistrictByCityId(city){
            //设置城市名称
            $("#temp2").val($(city).find("option:selected").text());
            //城市id
            $("#temp5").val($(city).find("option:selected").val());
            $.get("queryDistrictByCityId.htm?CSRFToken=${token}&cityId="+($(city).val()),function(data){
                var provinces="<option value='0'>选择区县</option>";
                for(var i=0;i<data.length;i++){

                    provinces+="<option value='"+data[i].districtId+"'>"+data[i].districtName+"</option>";
                }

                $("#districts").html(provinces);
                $('select[data-live-search="true"]').select2();
            });
        }

        //搜索货品列表
        function chooseProduct(){
            doAjax(1,8);
            $('#chooseGoods').modal('show');
        }
        /*点击添加货品的时候*/
        function doAjax(pageNo, pageSize)
        {
            $("#pageNo").val(pageNo),
                    $("#pageSize").val(pageSize),
                    $("#chooseAllPro").attr("checked",false);
            $.ajax({
                url:"newqueryProductForCoupon.htm",
                data:$("#searchGoodsInfo").serialize(),
                async:true,
                success:function(data){
                    var list = data.list;
                    var productListHtml = "";
                    for (var i = 0; i < list.length; i++)
                    {
                        productListHtml = productListHtml + "<tr>" +"<td class='tc'><input type='checkbox' class='productId' name='productId' onclick='addpro(this);'";

                        var pro =  document.getElementsByName("goodsIdP");
                        for(var j=0;j<pro.length;j++){
                            if(pro[j].value==list[i].goodsInfoId){
                                productListHtml = productListHtml +' checked="checked" ';
                            }
                        }
                        productListHtml = productListHtml+" value='" + list[i].goodsInfoId + "'/></td>";
                        productListHtml+='<td><img src="'+list[i].goodsInfoImgId+'" class="goodsImg"  width="50" height="50"/></td>';
                        productListHtml+= "<td  class='goodsTag' >" ;
                        if (list[i].specVo.length > 0)
                        {
                            for (var k = 0; k < list[i].specVo.length; k++)
                            {
                                productListHtml = productListHtml + list[i].specVo[k].spec.specName;
                                productListHtml = productListHtml + ":" + list[i].specVo[k].goodsSpecDetail.specDetailName + "<br/>";
                            }
                        }
                        productListHtml = productListHtml + "</td>" + "<td class='goodsNo'>" + list[i].goodsInfoItemNo+ "</td>" + "<td  class='goodsName' title='"+list[i].goodsInfoName+"' >" + list[i].goodsInfoName + "</td>"+"<td class='goodsInfoPreferPrice'>"+list[i].goodsInfoPreferPrice  +
                        "</td>" + "<td class='goodsInfoVipPrice'>" + list[i].goodsInfoVipPrice + "</td>" +"</tr>";
                    }
                    $("#productAddList tbody").html(productListHtml);
                    $("input[type=button]").button();
                    /*添加页脚*/
                    $("#productAddList .meneame").html("");
                    var foot = "";
                    var i = 1;
                    for (var l = data.startNo; l <= data.endNo; l++)
                    {
                        if ((i - 1 + data.startNo) == data.pageNo)
                        {
                            foot = foot + "<span class='current'> " + (i - 1 + data.startNo) + "</span>";
                        }
                        else
                        {
                            foot = foot + "<a onclick='doAjax(" + (i - 1 + data.startNo) + "," + (data.pageSize) + ")' href='javascript:void(0)'>" + (i - 1 + data.startNo) + "</a>";
                        }
                        i++;
                    }
                    foot = foot + "";
                    /*添加tfoot分页信息*/
                    $("#productAddList_table_foot .meneame").html(foot);
                }
            });

        }

        //选择的商品
        function addpro(obj){
            var productId=$(obj).val();
            var goodsImg=$(obj).parents("tr").find(".goodsImg").attr("src");
            var goodsNo=$(obj).parents("tr").find(".goodsNo").text();
            var goodsName=$(obj).parents("tr").find(".goodsName").text();
            var goodsPrice=$(obj).parents("tr").find(".goodsInfoPreferPrice").html();
            var goodsVipPrice=$(obj).parents("tr").find(".goodsInfoVipPrice").html();
            if(obj.checked==true){


                var htm = "<tr id='goods_tr_"+productId+"'>";
                htm+='<td width="100"><img src="'+goodsImg+'" width="50" height="50"/><input type="hidden" name="goodsIdP" id="goods_Id_'+productId+'" value="'+productId+'"/></td>';
                htm+='<td width="150">'+goodsNo+'</td>';
                htm+='<td  width="240">'+goodsName+'</td>';
                htm+='<td width="100" >'+goodsPrice+'</td>';
                htm+='<td width="100" >'+goodsVipPrice+'</td>';
                htm+='<td width="90"><input class="w50 text-center required goodsPrice" maxlength="3" onkeyup="this.value=this.value.replace(/\\D/g, \'\')" name="goodsNum" onafterpaste="this.value=this.value.replace(/\\D/g,\'\')" attr_price="'+goodsPrice+"#"+goodsVipPrice
                +'" type="text" value="1"></td>';
                htm+='<td width="130"><button onclick="removeTr(this);">移除</button></td>';
                htm+="</tr>";
                $("#readproduct tbody").append(htm);
            }else{
                $("#goods_tr_"+productId).remove();

            }
        }

        function removeTr(obj){
            $(obj).parents("tr").remove();
        }

        //选择所有当前页的所有货品
        function chooseAllPro(obj){
            if(obj.checked){
                $("input[name='productId']").each(function(){
                    this.checked=true;
                    $("#goods_tr_"+$(this).val()).remove();
                    addpro(this);
                });
            }else{
                $("input[name='productId']").each(function(){
                    this.checked=false;
                    addpro(this);
                });
            }
        }
        /**
         * 全选反选
         * @param obj
         * @param name
         */
        function allunchecked(obj,name){
            if(obj.checked){
                $(obj).parents(".tab-pane").find($("input[name='"+name+"']")).each(function(){
                    this.checked=true;
                });
            }else{
                $(obj).parents(".tab-pane").find($("input[name='"+name+"']")).each(function(){
                    this.checked=false;
                });
            }
        }

        /*添加货品的时候 分页*/
        /*改变每页显示的行数*/
        function changePageShow()
        {
            doAjax( 1, $("#list_size").val());
        }
        /*跳转到某一页*/
        function changeShowPage( pageSize)
        {
            doAjax( $("#list_pageno").val(), pageSize);
        }

      $(function(){

          /* 为选定的select下拉菜单开启搜索提示 */
       //   $('select[data-live-search="true"]').select2();
          /* 下面是表单里面的填写项提示相关的 */
          $('.help_tips').popover({
              content : '会员账号',
              trigger : 'hover'
          });

          //添加订单货品
        $('.add_goods_btn').click(function(){

            doAjax(1,8);
            $('#chooseGoods').modal('show');

        });


        $('input[name="invoiceType"]').change(function(){
          if($(this).val() == '1'){
            $('.invoice_info').show();
              $("#invoiceTitle").addClass("required");

          }
          else{
            $('.invoice_info').hide();
              $("#invoiceTitle").removeClass("required");
          }
        });

      });

    </script>
  </body>
</html>
