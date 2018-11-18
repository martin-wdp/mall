<!DOCTYPE html>
<html>
<head>
<#assign basePath=request.contextPath>

    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="renderer" content="webkit">
    <title>创建店铺</title>
    <link rel="stylesheet" href="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/css/bootstrap-datepicker.min.css" >
    <link href="${basePath}/css/material.css" rel="stylesheet">
    <link href="${basePath}/css/ripples.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${basePath}/css/style.css"/>
    <link rel="stylesheet" type="text/css" href="${basePath}/css/third.css" />
    <link rel="stylesheet" type="text/css" href="${basePath}/css/select2.min.css" rel="stylesheet">
    <style>
        .store .create-wrapper .lg-control {
            width: 500px;
            position: relative;
        }
    </style>
    <script type="text/javascript">
        window.history.forward(1);
    </script>
</head>
<body>
<div class="container">
    <div class="store">
        <div class="wrapper-app">
            <div id="header">
            <#include "../index/top.ftl">
            <ul class="process-nav process-03 clearfix">
                <li>1.在线协议</li>
                <li class="current">2.商家信息提交</li>
                <li>3.店铺开通</li>
            </ul>
            </div>

            <div id="content">
                <div class="create-wrapper">
                    <form method="post" id="want_from" action="${basePath}/savewantengage.htm" enctype="multipart/form-data" class="form-horizontal">
                        <div class="form-item">
                            <div class="form-wp">
                                <div class="form-group">
                                    <label class="control-label col-xs-3"><b>*</b>店铺名称：</label>
                                    <div class="controls col-xs-7">
                                        <input type="text" class="form-control" name="storeName" onblur="checqpmallName()" id="storeName" value="${(info.storeName)!''}"/>
                                        <p id=storeNameTip ><i></i><i class=border></i><s></s><span></span></p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <#--<div class="form-item">
                            <div class="form-wp">
                                <div class="form-group">
                                    <label class="control-label">店铺承诺：</label>
                                    <div class="controls">
                                        <label class="promise-check">
                                            <input value="1" name="storePromise" type="checkbox"/>
                                            <img src="${basePath}/images/store_promise1.png"  width="60px;" height="60px;" alt="预约送货上门服务" title="预约送货上门服务"/><br/>
                                            <span>预约送货上门服务</span>
                                        </label>
                                        <label class="promise-check">
                                            <input type="checkbox" value="2" name="storePromise"/>
                                            <img src="${basePath}/images/store_promise2.png"  width="60px;" height="60px;" alt="折扣优惠承诺" title="折扣优惠承诺"/><br/>
                                            <span>折扣优惠承诺</span>
                                        </label>
                                        <label class="promise-check">
                                            <input type="checkbox" value="3" name="storePromise"/>
                                            <img src="${basePath}/images/store_promise3.png"  width="60px;" height="60px;" alt="安装维修承诺" title="安装维修承诺"/><br/>
                                            <span>安装维修承诺</span>
                                        </label>
                                        <label class="promise-check">
                                            <input type="checkbox"  value="4" name="storePromise"/>
                                            <img src="${basePath}/images/store_promise4.png"  width="60px;" height="60px;" alt="维护保养承诺" title="维护保养承诺"/><br/>
                                            <br/><span>维护保养承诺</span>
                                        </label>
                                        <label class="promise-check">
                                            <input type="checkbox" value="5" name="storePromise"/>
                                            <img src="${basePath}/images/store_promise5.png"  width="60px;" height="60px;" alt="电话咨询服务" title="电话咨询服务"/><br/>
                                            <span>电话咨询服务</span>
                                        </label>
                                        <label class="promise-check">
                                            <input type="checkbox" value="6" name="storePromise"/>
                                            <img src="${basePath}/images/store_promise6.png"  width="60px;" height="60px;" alt="翻新服务" title="翻新服务"/><br/>
                                            <span>翻新服务</span>
                                        </label>
                                        <label class="promise-check">
                                            <input type="checkbox" value="7" name="storePromise"/>
                                            <img src="${basePath}/images/store_promise6.png"  width="60px;" height="60px;" alt="翻新服务" title="翻新服务"/><br/>
                                            <span>购物定制服务</span>
                                        </label>
                                        <label class="promise-check">
                                            <input type="checkbox" value="8" name="storePromise"/>
                                            <img src="${basePath}/images/store_promise8.png"  width="60px;" height="60px;" alt="免费打包服务" title="免费打包服务"/><br/>
                                            <span>免费打包服务</span>
                                        </label>
                                    </div>
                                </div>
                            </div>
                        </div>-->
                        <div class="form-item">
                            <div class="form-wp">
                                <div class="form-group">
                                    <label class="control-label col-xs-3"><b>*</b>经营类目：</label>
                                    <div class="controls col-xs-9">
                                        <p class="add-item clearfix" style="line-height:48px;">主营类目：<button class="btn btn-primary btn-sm pull-right btn-sm-man" type="button">添加</button></p>
                                        <p id=cidsTip ><i></i><i class=border></i><s></s><span></span></p>
                                        <table class="table table-bordered">
                                            <thead>
                                            <tr>
                                                <th>序号</th>
                                                <th>一级类目</th>
                                                <th>二级类目</th>
                                                <th>二级类目扣率</th>
                                                <th>操作</th>
                                            </tr>
                                            </thead>
                                            <tbody class="catbo">
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                <input type="hidden" value="" id="pc_id" />
                                <input type="hidden" value="" id="sc_id" />
                                <input type="hidden" value="" name="cids" id="cids" class="hasa" />
                                <input type="hidden" value="" class="b_id" />
                                <input type="hidden" value="" name="bids" id="bids" class="hasb" />

                                <div class="form-group">
                                    <label class="control-label col-xs-3"><b>*</b>主营品牌：</label>
                                    <div class="controls col-xs-9">
                                        <p class="add-item clearfix" style="line-height:48px;">主营品牌在此处选择：<button class="btn btn-primary btn-sm pull-right btn-sms"  type="button">添加</button></p>
                                        <p id=bidsTip ><i></i><i class=border></i><s></s><span></span></p>
                                        <table class="table table-bordered">
                                            <thead>
                                            <tr>
                                                <th>序号</th>
                                                <th>品牌中文名称</th>
                                                <th>品牌类型</th>
                                                <th>操作</th>
                                            </tr>
                                            </thead>
                                            <tbody class="bra_tb">
                                            </tbody>
                                        </table>
                                    </div>
                                </div>

                                <div class="form-group">
                                	<label class="col-xs-3"></label>
                                    <div class="controls col-xs-9">
                                        <p class="add-item clearfix" style="line-height:48px;">若以上品牌中没有您经营的品牌，请自定义品牌：<button class="btn btn-primary pull-right btn-sm" type="button" onclick="viewt(this)"; data-toggle="modal" data-target="#otherBrands">添加</button></p>
                                        <p id=bidsTip ><i></i><i class=border></i><s></s><span></span></p>
                                        <table class="table table-bordered">
                                            <thead>
                                            <tr>
                                                <th>品牌中文名称</th>
                                                <th>图片</th>
                                                <th>操作</th>
                                            </tr>
                                            </thead>
                                            <tbody class="appbrand">
                                            <#if apbrand??&&apbrand?size!=0>
                                                <#list apbrand as brand>
                                                <tr id="applyBrand_${brand.applyBrandId}">
                                                    <td>${brand.applyBrandName}</td>
                                                    <td><img src="<#if brand.applyBrandPic??>${brand.applyBrandPic?default('')}</#if>" width="150" height="50"/> <input type="hidden" value="${brand.applyBrandId}" name="appId"/> </td>
                                                    <td><a class="blu_bt" href="javascript:void(0);" onclick="delApplyBrand(${brand.applyBrandId})">删除</a></td>
                                                </tr>
                                                </#list>
                                            </#if>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-item">
                            <div class="form-wp">
                                <div class="form-group">
                                	<label class="col-xs-3"></label>
                                    <div class="controls col-xs-7">
                                        <button class="btn btn-large btn-primary" type="button" onclick="javascript:window.location.href='filltax.html'">上一步</button>
                                        <button class="btn btn-large btn-primary want_btn"  type="button" >保存并下一步</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="footer">
        <#include "../index/foot.ftl">
        </div>
    </div>
</div>

<#--首选类目弹出框-->
<div class="modal fade" id="addCategory" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">主营类目</h4>
            </div>
            <div class="modal-body">
                <div class="form-item">
                    <label class="control-label">首选类目：</label>
                    <div class="controls">
                        <select class="form-control" id="cate">
                        </select>
                    </div>
                </div>
                <div class="form-item">
                    <label class="control-label">从属类目：</label>
                    <div class="controls checkWp">
                        <label><input class="vm mr5 sel_all" type="checkbox"/>全选</label>
                        <div class="check-cont">
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-default" onclick="closeDia()" type="button" data-dismiss="modal">取消</button>
                <button class="btn btn-primary" onclick="addCate()" type="button">添加</button>
            </div>
        </div>
    </div>
</div>


<#--添加品牌弹出框-->
<div class="modal fade" id="mainBrands" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">主营品牌</h4>
            </div>
            <div class="modal-body">
                <div class="form-item">
                    <label class="control-label">请选择品牌：</label>
                    <div class="controls">
                        <select class="form-control" data-live-search="true" id="bra" >

                        </select>
                    </div>
                </div>
                <#--<div class="form-item">
                    <label class="control-label">品牌搜索：</label>
                    <div class="controls searchWp">
                        <input type="text" class="form-control" value="" id="chaxunpinpai"/>
                        <button class="btn btn-primary btn-sm" onclick="selectBraByName()" type="button">查询</button>
                    </div>
                    <p style="color: red;" id="pinpaierror"></p>
                </div>-->
            </div>
            <div class="modal-footer">
                <button class="btn btn-default" type="button" data-dismiss="modal" onclick="closeBrDia()">取消</button>
                <button class="btn btn-primary" type="button" onclick="addBra()">添加</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="otherBrands" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">自定义品牌</h4>
            </div>
            <div class="modal-body">
                <div class="form-item">
                    <label class="control-label"><b>*</b>中文名称：</label>
                    <div class="controls">
                        <input type="text" class="form-control" name="applyBrandName" id="applyBrandName" onblur="checkOtherName(this);" />
                        <span id="brandtip" class="tip_Brand"></span>
                    </div>
                </div>
                <!--<div class="form-item">
                    <label class="control-label" >品牌网址：</label>
                    <div class="controls">
                        <input type="text" class="form-control" name="applyUrl" id="applyUrl" />
                        <span id="applyUrltip" class="tip_Brand"></span>
                    </div>
                </div>-->
                <div class="form-item">
                    <label class="control-label"><b>*</b>品牌LOGO：</label>
                    <div class="controls">
                        <input type="file" id="applyPic" name="applyPic"/>
                        <span id="applyPictip" class="tip_Brand"></span>
                    </div>
                </div>
                <div class="form-item">
                    <label class="control-label"><b>*</b>授权证书：</label>
                    <div class="controls">
                        <input type="file" id="certificate" name="certificate"/>
                        <span id="certificatetip" class="tip_Brand"></span>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-default" type="button" data-dismiss="modal" onclick="closeBrDia()">取消</button>
                <button class="btn btn-primary" type="button" onclick="addApply()">添加</button>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="${basePath}/js/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/js/bootstrap.min.js"></script>
<script src="${basePath}/js/ripples.min.js"></script>
<script src="${basePath}/js/material.min.js"></script>
<script type="text/javascript" src="${basePath}/js/sell/seller.js"></script>
<script type="text/javascript" src="${basePath}/js/sell/catgra.js"></script>
<script type="text/javascript" src="${basePath}/js/sell/openstore.js"></script>
<script type="text/javascript" src="${basePath}/js/sell/sellervalidate.js"></script>
<script type="text/javascript" src="${basePath}/js/ajaxMutiUploadFile.js"></script>
<script type="text/javascript" src="${basePath}/js/app.js"></script>
<script type="text/javascript" src="${basePath}/js/bootstrap-select.min.js"></script>
<script type="text/javascript" src="${basePath}/js/select2.min.js"></script>
<script type="text/javascript" src="${basePath}/js/common/common_alert.js"></script>
<script>
    var baseUrl = '${baseUrl}';
    $(function(){
		$.material.init();
        $(".addinfo").click(function(){
            $(this).siblings(".add_dialog").show();
        });
        $('#storeNameTip').focus(function(){
            $('#storeNameTip').addClass("alertTip");
        })
    });

    function delApplyBrand(obj){
        $.ajax({
            type: 'post',
            url:'third/deleteapplybrand.htm?applyBrandId='+obj,
            async:false,
            success: function(data) {
                if(data>0){
                    $("#applyBrand_"+obj).remove();
                }

            }
        });
    }

    var f=0;
    var flag=0;
    function addApply(){
        var boo = true;
        var name = $("#applyBrandName").val();
        var applyUrl=$("#applyUrl").val();
        var f = $("#applyPic").val();
        var c = $("#certificate").val();
        var fi ="applyPic,certificate";
        if(name==''){
            $("#brandtip").html('中文名称不能为空');
            boo = false&&boo;
        }else{
            checkbrandName(name);
            if(flag==0){
                $("#brandtip").html('该品牌已存在于主营品牌');
                $("#brandtip").css("color","red");
                boo=false&&boo;
            }


        }

        if(f==''){
            $("#applyPictip").html('未选择文件');
            boo = false&&boo;
        }else{
            $("#applyPictip").html('');
        }

        if(c==''){
            $("#certificatetip").html('未选择文件');
            boo = false&&boo;
        }else{
            $("#certificatetip").html('');
        }

        if(f==0){
            boo=false&&boo;
            return;
        }else{
            boo=true&&boo;
        }
        if(boo){
            var param = 'third/addapplybrand.htm?applyBrandName='+encodeURI(name)+'&applyUrl='+encodeURI(applyUrl);
            //移除事件
            //$(".btn-primary").removeAttr("onclick");
            $.ajaxMutiUpload({
                url:param,
                fileElementId: fi, async: false,
                success: function(data){
                    if(data!=null){
                        var html='<tr id="applyBrand_'+data.applyBrandId+'">';
                        html+='<td>'+data.applyBrandName+'</td>';
                        html+='<td><img src="'+data.applyBrandPic+'" width="150" height="50"/> <input type="hidden" value="'+data.applyBrandId+'" name="appId"/></td>';
                        html+='<td><a class="blu_bt" href="javascript:void(0);" onclick="delApplyBrand('+data.applyBrandId+')">删除</a></td>';
                        html+='</tr>';
                        $(".appbrand").append(html);
                        $(".tip_Brand").html('');
                        clearTip();
                        //恢复删除的事件
                        //$(".btn-primary").attr("onclick","addApply();");
                        $('.modal').modal('hide')
                    }else{
                        clearTip();
                    }
                }
            });
        }
        return;
    }

    /*自定义品牌*/
    function viewt(obj){
        //初始化提示错误信息
        clearTip();
       // $(obj).siblings(".add_brand").show();
    }

    function checqpmallName(){
        var storeName = $("#storeName").val();
        $.ajax({
            url:"checqpmallNameIsUsing.htm?storeName="+storeName,
            type:"post",
            async:false,
            success:function(data){
                if(data > 0){

                    $('#storeNameTip').removeClass("alertTip");
                    $('#storeNameTip').css('color',"red");
                    $('#storeNameTip').find('span').html("店铺名称已存在！请重新填写");
                }else{
                    $('#storeNameTip').css('color',"green");
                  //  $('#storeNameTip').addClass("alertTip")
                    if($("#storeName").val()!=''){
                        $('#storeNameTip').find('span').html("输入正确");
                    }

                }
            }
        });
    }

    function checkbrandName(name){
        $.post("checkThirdBrandName.htm?brandName="+encodeURI(encodeURI(name)),function(data){
            if(data){
                $("#brandtip").html('该品牌可以使用');
                $("#brandtip").css('color','green');
                flag=1;
            }else{
                $("#brandtip").html('该品牌已存在于主营品牌');
                $("#brandtip").css("color","red");
                flag=0;
            }
        });
    }

    /**/
    function checkOtherName(obj){
        if(obj.value!=''){
            checkbrandName(obj.value);
        }else{
            $("#brandtip").html('中文名称不能为空！');
            $("#brandtip").css("color","red");
        }

    }
    function clearTip(){
        $("#applyBrandName").val('');
        $("#applyUrl").val('');
        $("#applyPic").val('');
        $("#brandtip").html('');
        $("#applyUrltip").html('');
        $("#applyPictip").html('');
    }
</script>
</html>
