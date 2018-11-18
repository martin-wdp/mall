<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<#assign basePath=request.contextPath>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="renderer" content="webkit">
    <title>促销管理</title>
    <link rel="stylesheet" href="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/css/bootstrap.min.css"/>
    <link href="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/css/bootstrap-datepicker.min.css" rel="stylesheet">
    <link href="${basePath}/css/material.css" rel="stylesheet">
    <link href="${basePath}/css/ripples.css" rel="stylesheet">
    <link rel="stylesheet" href="http://cdn.bootcss.com/font-awesome/4.3.0/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="${basePath}/css/summernote.css"/>
    <link rel="stylesheet" href="${basePath}/css/style.css"/>
</head>

<style type="text/css">
    .couponScope{display:none;}
    .couponNoList{display:none;}
    .couponUnGot{display:none;}
    .couponGot{display:none;}
    .couponUsed{display:none;}
</style>
<body>

<#--引入头部-->
<#include "../index/indextop.ftl">

<div class="wp">
    <div class="ui-sidebar">
        <div class="sidebar-nav">
            <#import "../index/indexleft.ftl" as leftmenu>
            <@leftmenu.leftmenu '${basePath}' />
        </div>
    </div>

    <div class="app">
        <div class="app-container">
            <ol class="breadcrumb">
                <li>您所在的位置</li>
                <li>促销管理</li>
                <li class="active" style="color: #07d;">优惠劵促销</li>
            </ol>

            <div class="app-content">
                <div>
                    <ul class="nav nav-tabs">
                        <li class="active">
                            <a href="javascript:;">优惠券促销列表</a>
                        </li>
                    </ul>
                    <div class="cfg-content">
                        <div class="ops-bar">
                            <button class="btn btn-primary btn-sm" type="button" onclick="javascript:window.location.href='toaddcouponthird.html'"><i class="glyphicon glyphicon-plus"></i>添加优惠券</button>
                        </div>
                       <form id="delallform" method="post" action="newdelcouponlist.htm" method="post" >
                        <table class="table">
                            <thead>
                            <tr>
                                <th><input type="checkbox" onclick="allunchecked(this,'couponId')"/></th>
                                <th>活动名称</th>
                                <th>面额</th>
                                <th>使用规则</th>
                                <th>每人限领</th>
                                <th style="width:150px;">有效时间</th>
                                <th>活动状态</th>
                                <th>获取链接</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#if (pb.list)?? && (pb.list)?size gt 0>
                              <#list pb.list as coupon>
                               <tr>
                                <td><input type="checkbox" name="couponId" value="${coupon.couponId}" /></td>
                                <td>${(coupon.couponName)!''}</td>
                                <td>${(coupon.reductionPrice)!''}</td>
                                <td>
                                  <#if (coupon.couponRulesType)?? && coupon.couponRulesType=='2'>
                                      满x元使用
                                  <#else >
                                      不受限
                                  </#if>
                                </td>
                                <td>${(coupon.couponGetNo)!''}</td>
                                <td>${coupon.couponStartTime?string("yyyy-MM-dd HH:mm:ss")} 至 ${coupon.couponEndTime?string("yyyy-MM-dd HH:mm:ss") }</td>
                                <td>
                                 <#if (coupon.couponStartTime?string("yyyy-MM-dd HH:mm:ss"))?date("yyyy-MM-dd HH:mm:ss") gt nowdate?date("yyyy-MM-dd HH:mm:ss")>
                                    <span class="label label-default">未开始</span>
                                   <#elseif (coupon.couponEndTime?string("yyyy-MM-dd HH:mm:ss"))?date("yyyy-MM-dd HH:mm:ss") lt nowdate?date("yyyy-MM-dd HH:mm:ss")>
                                    <span class="label label-danger">已结束</span>
                                   <#elseif (coupon.couponStartTime?string("yyyy-MM-dd HH:mm:ss"))?date("yyyy-MM-dd HH:mm:ss") lt nowdate?date("yyyy-MM-dd HH:mm:ss") && (coupon.couponEndTime?string("yyyy-MM-dd HH:mm:ss"))?date("yyyy-MM-dd HH:mm:ss") gt nowdate?date("yyyy-MM-dd HH:mm:ss")>
                                    <span class="label label-primary">正在进行</span>
                                </#if>
                                </td>
                                <td><a href="javascript:void(0);"  class="btn btn-default copy" attr-id="${coupon.couponId}" target="_blank">复制链接</a></td>
                                <td style="width: 17%">
                                    <div class="btn-group">
                                    <button type="button" class="btn btn-primary btn-sm" onclick="getScanCoupons('${coupon.couponId}')">查看详情</button>
                                        <#--<button type="button" class="btn btn-primary btn-sm" onclick="updatecoupon('${coupon.couponId}')">修改</button>-->
                                        <button type="button" class="btn btn-primary btn-sm dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                            <span class="caret"></span>
                                        </button>
                                        <ul class="dropdown-menu" role="menu">
                                            <li><a href="javascript:void(0);" onclick="getScanCouponNums('${coupon.couponId }');">查看券码</a></li>
                                            <li><a href="javascript:void(0);" onclick="delcouponById('${coupon.couponId}')">删除</a></li>
                                        </ul>
                                    </div>
                                </td>
                            </tr>
                              </#list>
                            </#if>
                            </tbody>
                        </table>
                       </form>
                        <div class="footer-operation">
                            <div class="ops-left">
                                <button class="btn btn-primary btn-xs" type="button" onclick="delcouponlist()">批量删除</button>
                            </div>
	                         <#import "../pagination/pageBean.ftl" as page>
			                 <@page.pagination pb />
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


      <div class="modal fade" id="scanCoupons"  role="dialog">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">优惠券详细信息</h4>
                </div>
                <div class="modal-body">
                    <div class="mb20 container-fluid">
                        <div class="row">
                            <div class="col-sm-4">
                                <p>优惠券名称：<span id="couponName"></span></p>
                            </div>
                            <div class="col-sm-4">
                                <p>优惠券描述：<span id="couponRemark"></span></p>
                            </div>
                            <div class="col-sm-4">
                                <p>优惠券金额：<span id="couponPrice"></span></p>
                            </div>

                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <p>使用等级：<span id="couponLelvel"></span> </p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-4">
                                <p>使用规则：<span id="couponType"></span></p>
                            </div>
                            <div class="col-sm-4">
                                <p>开始时间：<span id="couponStartTime"></span></p>
                            </div>
                            <div class="col-sm-4">
                                <p>结束时间：<span id="couponEndTime"></span></p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-4">
                                <p>创建时间：<span id="createTime"></span></p>
                            </div>
                            <div class="col-sm-4">
                                <p>发行总数：<span id="coupouCount"></span></p>
                            </div>
                            <#--<div class="col-sm-4">-->
                                <#--<p>修改时间：<span id="couponGetNo"></span></p>-->
                            <#--</div>-->
                        </div>
                    </div>
                    <h4>优惠券使用范围</h4>
                    <table class="table table-striped table-hover table-bordered">
                        <thead>
                        <tr id="couponTop">
                            <th width="80">图片</th>
                            <th width="150">货品编号</th>
                            <th>货品名称</th>
                        </tr>
                        </thead>
                        <tbody id="couponScope">

                        </tbody>
                    </table>
                    <div class="table_foot">
							<div class="ops-right">
                              <nav>
                               <ul class="pagination" id="couponPage">
                               </ul>
                              </nav>
                            </div>
                        <div class="clr"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>


	<div class="modal fade" id="scanCouponNums"  role="dialog">
	    <div class="modal-dialog modal-lg">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	                <h4 class="modal-title">券码列表</h4>
	            </div>
	            <div class="modal-body">
	                <div class="common_info common_tabs mt20">
	                    <ul class="nav nav-tabs" role="tablist">
	                        <li role="presentation" class="active"><a href="#tab1" aria-controls="tab1" role="tab" data-toggle="tab">所有券码</a></li>
	                        <li role="presentation"><a href="#tab2" aria-controls="tab2" role="tab" data-toggle="tab">未领取券码</a></li>
	                        <li role="presentation"><a href="#tab3" aria-controls="tab3" role="tab" data-toggle="tab">已领取券码</a></li>
	                        <li role="presentation"><a href="#tab4" aria-controls="tab4" role="tab" data-toggle="tab">已使用券码</a></li>
	                    </ul>
	                    <div class="tab-content">
	                        <div role="tabpanel" class="tab-pane active" id="tab1">
	                            <table class="table table-striped table-hover table-bordered">
	                                <thead>
	                                <tr>
	                                    <th>券码序列号</th>
	                                    <th width="100">状态</th>
	                                    <th>领取时间</th>
	                                    <th>领取人</th>
	                                </tr>
	                                </thead>
	                                <tbody id="couponNoList">

	                                </tbody>
	                            </table>
	                            <div class="table_foot">
									<div class="ops-right">
                                       <nav>
	                                       <ul class="pagination" id="couponNoPage">
	                                       </ul>
                                      </nav>
	                                </div>
	                                <div class="clr"></div>
	                            </div>
	                        </div>
	                        <div role="tabpanel" class="tab-pane" id="tab2">
	                            <table class="table table-striped table-hover table-bordered">
	                                <thead>
	                                <tr>
	                                    <th>券码序列号</th>
	                                    <th width="100">状态</th>
	                                    <th>领取时间</th>
	                                    <th>领取人</th>
	                                </tr>
	                                </thead>
	                                <tbody id="couponUnGot">

	                                </tbody>
	                            </table>
	                            <div class="table_foot">
									<div class="ops-right">
                                       <nav>
	                                       <ul class="pagination" id="couponUnGotPage">
	                                       </ul>
                                      </nav>
	                                </div>
	                                <div class="clr"></div>
	                            </div>
	                        </div>
	                        <div role="tabpanel" class="tab-pane" id="tab3">
	                            <table class="table table-striped table-hover table-bordered">
	                                <thead>
	                                <tr>
	                                    <th>券码序列号</th>
	                                    <th width="100">状态</th>
	                                    <th>领取时间</th>
	                                    <th>领取人</th>
	                                </tr>
	                                </thead>
	                                <tbody id="couponGot">

	                                </tbody>
	                            </table>
	                            <div class="table_foot">
									<div class="ops-right">
                                      <nav>
                                       <ul class="pagination" id="couponGotPage">
                                       </ul>
                                      </nav>
                                    </div>
	                                <div class="clr"></div>
	                            </div>
	                        </div>
	                        <div role="tabpanel" class="tab-pane" id="tab4">
	                            <table class="table table-striped table-hover table-bordered">
	                                <thead>
	                                <tr>
	                                    <th>券码序列号</th>
	                                    <th width="100">状态</th>
	                                    <th>领取时间</th>
	                                    <th>领取人</th>
	                                </tr>
	                                </thead>
	                                <tbody id="couponUsed">

	                                </tbody>
	                            </table>
	                            <div class="table_foot">
                                    <div class="ops-right">
                                      <nav>
                                       <ul class="pagination" id="couponUsedPage">
                                       </ul>
                                      </nav>
                                    </div>
	                                <div class="clr"></div>
	                            </div>
	                        </div>
	                    </div>
	                </div>

	            </div>
	        </div>
	    </div>
	</div>


<#--<div class="service-wrap">-->
    <#--<span class="service-close">×</span>-->
    <#--<a href="javascript:;" class="service-box">联系客服</a>-->
<#--</div>-->

<div class="back-to-top">
    <a href="javascript:;"><i></i>返回顶部</a>
</div>

<#--<div class="notice-center">-->
    <#--<div class="notice-center-ring"><i></i></div>-->
    <#--<div class="notice-center-wrapper">-->
        <#--<div class="nt-header">-->
            <#--<h3>系统通知（<span>1</span>）</h3>-->
            <#--<a href="javascript:;" class="nt-close">收起》</a>-->
        <#--</div>-->
        <#--<ul class="nt-content">-->
            <#--<li>-->
                <#--<div class="nt-item unread">-->
                    <#--<p>刘仙升于2015-04-08 15:41:23申请提现1.00元，已提现成功，请注意查询您的银行账户。</p>-->
                    <#--<a href="javascript:;">查看提现记录 》</a>-->
                <#--</div>-->
            <#--</li>-->
        <#--</ul>-->
        <#--<div class="nt-footer">-->
            <#--<a href="javascript:;" class="mark-read">全部标记已读</a>-->
            <#--<a href="javascript:;" class="nt-all">查看全部信息</a>-->
        <#--</div>-->
    <#--</div>-->
<#--</div>-->

<#--<div class="page-help-btn">帮助</div>-->
<div class="page-help-container">
    <div class="page-help-content">
        <p style="color:#f00;">不知道从哪里开始？</p>
        <p>完成掌柜任务，简简单单开店铺！</p>
        <p>点击开始》》<a href="javascript:;">掌柜成长之旅</a></p>
    </div>
    <div class="page-help-operation">
        <a href="javascript:;" class="btn btn-primary btn-sm">进入帮助中心</a>
        <a href="javascript:;" class="btn btn-default btn-sm">建议反馈</a>
    </div>
</div>

<script src="http://cdn.staticfile.org/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/js/bootstrap.min.js"></script>
<script src="${basePath}/js/marketing/modifymarketing.js"></script>
<script src="${basePath}/js/material.min.js"></script>
<script src="${basePath}/js/app.js"></script>
<script src="${basePath}/js/common/common_checked.js"></script>
<script src="${basePath}/js/common/common_alert.js"></script>
<script src="${basePath}/js/coupon/couponlist.js"></script>
<script type="text/javascript" src="${basePath}/js/navmenu/navmenu.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery.zclip.min.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery.zclip.js"></script>
<#import "../common/selectindex.ftl" as selectindex>
<@selectindex.selectindex "${n!''}" "${l!''}" />
</body>
<script type="text/javascript">

    $(function(){
        var flag="";
        $('.copy').zclip({
            path:'../js/ZeroClipboard.swf',
            copy:function(){
                var couponId=$(this).attr("attr-id");
                $.ajax({
                    url:'getCouponNoByCouponId.htm?couponId='+couponId,
                    async : false,
                    success:function(data) {
                        flag=data;

                    }
                });
                return flag;

            },
            afterCopy:function(){
                if(flag=='0'){
                    showTipAlert("优惠券已过期!");
                }else{

                    showTipAlert("复制成功!");
                }
            }
        });
    })
  //删除店铺
       //删除店铺
      function delcouponById(couponId){
    	  showDeleteOneConfirmAlert("newdelcouponthird.htm?couponId="+couponId);
      }

      function delcouponlist(){
          showAjaxDeleteBatchConfirmAlert("delallform","couponId");
      }

      function updatecoupon(couponId){
    	window.location.href="toupdatecouponthird.htm?couponId="+couponId;
      }


</script>
<script>
    $(function(){
    	$.material.init();
    });
</script>
</html>