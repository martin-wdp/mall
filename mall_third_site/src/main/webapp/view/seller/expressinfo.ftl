<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商城第三方后台-物流列表展示</title>
<#assign basePath=request.contextPath>

<link rel="stylesheet" href="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/css/bootstrap-datepicker.min.css">
<link href="${basePath}/css/material.css" rel="stylesheet">
<link href="${basePath}/css/ripples.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${basePath}/css/style.css"/>
<link rel="stylesheet" type="text/css" href="${basePath}/css/third.css"/>

<script type="text/javascript" src="http://cdn.staticfile.org/jquery/1.11.1/jquery.min.js"></script>
<script type="text/javascript" src="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${basePath}/js/third.js"></script>
<script src="${basePath}/js/ripples.min.js"></script>
<script src="${basePath}/js/material.min.js"></script>
<script type="text/javascript" src="${basePath}/js/app.js"></script>
<script type="text/javascript" src="${basePath}/js/sell/express.js"></script>
</head>

<body>
<#-- 引入头部 -->
<#include "../index/indextop.ftl">
<div class="wp">
    <div class="ui-sidebar">
        <div class="sidebar-nav">
            <#import "../index/indexleft.ftl" as leftmenu>
            <@leftmenu.leftmenu '${basePath}' />
        </div>
    </div>

    <div class="app show_text" style="display: none;"">
        <div class="app-container">
            <ol class="breadcrumb">
                <li>您所在的位置</li>
                <li>我的店铺</li>
                <li class="active" style="color: #07d;" >物流管理</li>
            </ol>

            <div class="app-content">
                <div>
                    <ul class="nav nav-tabs">
                        <li role="presentation" class="active">
                            <a href="javascript:;">物流信息管理</a>
                            <span><b><#if (express?size!=0)></#if></b></span>
                            <span><b><#if (express?size!=0)></#if></b></span>
                        </li>
                    </ul>
                    <div class="cfg-content">
                        <div class="ops-bar">
                            <button class="btn btn-primary btn-sm"  type="button" data-toggle="modal" data-target="#addLogistics"><i class="glyphicon glyphicon-plus"></i>新建物流</button>
                        </div>
                        <table class="table">
                            <thead>
                            <tr>
                                <th>物流名称</th>
                                <th>物流编号</th>
                                <th>是否开启</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                                <#if (express?size!=0)>
                                    <#list express as exp>
                                     <tr>
                                        <td>${(exp.expName)!''}</td>
                                        <td>${(exp.expCompany)!''}</td>
                                        <td>
                                            <#if exp.isDefault??>
                                                <#if exp.isDefault == '1' >
                                                    <span class="tb_right"></span>
                                                <#else>
                                                    <span class="tb_error"></span>
                                                </#if>
                                            </#if>
                                        </td>
                                        <td>
                                            <div class="btn-group">
                                                <#if exp.isDefault??>
                                                    <#if exp.isDefault == '0' >
                                                        <button type="button" data-toggle="modal" data-target="#update_flag" onclick="modifyemptodisable('${exp.shoreExpId}','${exp.isDefault}')"  class="btn btn-primary btn-sm">开启</button>
                                                    <#else>
                                                        <button type="button" data-toggle="modal" data-target="#update_flag" onclick="modifyemptodisable('${exp.shoreExpId}','${exp.isDefault}')"  class="btn btn-primary btn-sm">关闭</button>
                                                    </#if>
                                                </#if>

                                                <button type="button" class="btn btn-primary btn-sm dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                                    <span class="caret"></span>
                                                </button>
                                                <ul class="dropdown-menu" role="menu">
                                                    <li><a href="javascript:;" onclick="updateexp(${exp.shoreExpId})"   data-toggle="modal" data-target="#update_Logistics">修改</a></li>
                                                    <li><a href="javascript:;" onclick="delemp(${exp.shoreExpId})"      data-toggle="modal" data-target="#delete_express">删除</a></li>
                                                </ul>
                                            </div>
                                        </td>
                                    </tr>
                                    </#list>
                                <#else>
                                    <tr>
                                        <td colspan="6">暂无物流记录！</td>
                                    </tr>
                                </#if>
                            </tbody>
                        </table>
                        <div class="footer-operation">
                            <div class="ops-right">
                                <nav>
                                    <ul class="pagination">
                                        <li class="disabled">
                                            <a href="javascript:;" aria-label="Previous">
                                                <span aria-hidden="true">&laquo;</span>
                                            </a>
                                        </li>
                                        <li class="active"><a href="javascript:;">1</a></li>
                                        <li>
                                            <a href="javascript:;" aria-label="Next">
                                                <span aria-hidden="true">&raquo;</span>
                                            </a>
                                        </li>
                                    </ul>
                                </nav>
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



<#--更改物流状态弹出框-->
<div class="modal fade" id="update_flag" role="dialog" aria-hidden="true" >
    <input type="hidden" value="" class="exp_id_hide" />
    <input type="hidden" value="" class="flag_id_hide" />
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">操作提示</h4>
            </div>
            <div class="modal-body">
                <div class="form-item">
                    <label class="control-label"></label>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" onclick="dodisable()" type="button">确定</button>
                <button class="btn btn-primary" type="button" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<#--删除弹出框-->
<div class="modal fade" id="delete_express" role="dialog" aria-hidden="true" >
    <input type="hidden" value="" class="exp_id_hide" />
    <input type="hidden" value="" class="flag_id_hide" />
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">操作提示</h4>
            </div>
            <div class="modal-body">
                <div class="form-item">
                    <label class="control-label">确认删除吗？</label>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" onclick="dodelemp()" type="button">确定</button>
                <button class="btn btn-primary" type="button" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<#--新增物流弹出框-->
<div class="modal fade" id="addLogistics" role="dialog" aria-hidden="true">
    <form id="expForm_add" method='post' action="${basePath}/third/insertexpress.htm" >
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">新建物流</h4>
                </div>
                <div class="modal-body">
                    <input class="shoreExpId " type="hidden" name="shoreExpId" value=""/>
                    <div class="form-item">
                        <label class="control-label"><b>*</b>物流名称：</label>
                        <div class="controls">
                            <input type="text" class="form-control expname_addexpress" value=""  name="expName"/>
                            <label class="si_word"></label>
                        </div>
                    </div>
                    <div class="form-item">
                        <label class="control-label"><b>*</b>物流编号：</label>
                        <div class="controls">
                            <input type="text" class="form-control expcompany_addexpress" value=""
                                   onkeyup="this.value=this.value.replace(/[^\d]/g,'') "
                                   onafterpaste="this.value=this.value.replace(/[^\d]/g,'') "
                                   name="expCompany"/>
                            <label class="si_word"></label>
                            <label class="expcompany_Tip"></label>
                        </div>
                    </div>
                    <div class="form-item">
                        <label class="control-label"><b>*</b>快递100：</label>
                        <div class="controls">
                            <input type="text" class="form-control kuaidi_addexpress" value="" name="kudi100code" id="expkuaidi"/>
                            <label class="expkuaidi"></label>
                            <label><a class="text-primary" href="ap.kuaidi100公司代码.doc">快递100物流公司代码文档</a></label>
                        </div>
                    </div>
                    <div class="form-item">
                        <label class="control-label">是否开启：</label>
                        <div class="controls checkWp radio radio-primary">
                            <label class="choose-label"><input name="isDefault" class="isDefault" value="1" type="radio" checked="checked"/>是</label>
                            <label class="choose-label"><input name="isDefault" class="isDefaults" value="0" type="radio"/>否</label>
                            <label class="si_word" style="display:none;"></label>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-default" type="button" onclick="back()" data-dismiss="modal">关闭</button>
                    <button class="btn btn-primary" id="savebutton"   type="button" onclick="return add_express('addexpress')"'>保存</button>
                </div>
            </div>
         </div>
    </form>
</div>


<#--修改物流弹出框-->
<div class="modal fade" id="update_Logistics" role="dialog" aria-hidden="true">
    <form method='post' action="${basePath}/third/updatedoneexpress.htm" id="expform_updates" >
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">修改物流</h4>
                </div>
                <div class="modal-body">
                    <input class="shoreExpId_update " type="hidden" name="shoreExpId" value=""/>
                    <div class="form-item">
                        <label class="control-label"><b>*</b>物流名称：</label>
                        <div class="controls">
                            <input type="text" class="form-control expname_updatexpress" value=""  name="expName"/>
                            <label class="si_word"></label>
                        </div>
                    </div>
                    <div class="form-item">
                        <label class="control-label"><b>*</b>物流编号：</label>
                        <div class="controls">
                            <input type="text" class="form-control expcompany_updatexpress"
                               onkeyup="this.value=this.value.replace(/[^\d]/g,'') "
                               onafterpaste="this.value=this.value.replace(/[^\d]/g,'') "
                               value="" name="expCompany"/>
                            <label class="si_word"></label>
                            <label class="expcompany_Tip"></label>
                        </div>
                    </div>
                    <div class="form-item">
                        <label class="control-label"><b>*</b>快递100：</label>
                        <div class="controls">
                            <input type="text" class="form-control kuaidi_updatexpress" id="kuaidi_updatexpress" value="" name="kudi100code" id="expkuaidi"/>
                            <label class="expkuaidi"></label>
                            <label><a class="text-primary" href="ap.kuaidi100公司代码.doc">快递100物流公司代码文档</a></label>
                        </div>
                    </div>
                    <div class="form-item">
                        <label class="control-label">是否开启：</label>
                        <div class="controls checkWp">
                            <label class="choose-label"><input name="isDefault" class="isDefault_update" value="1" type="radio" checked="checked"/>是</label>
                            <label class="choose-label"><input name="isDefault" class="isDefaults_update" value="0" type="radio"/>否</label>
                            <label class="si_word"></label></dd>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-default" type="button" onclick="back()" data-dismiss="modal">关闭</button>
                    <button class="btn btn-primary" id="updatebutton" type="button" onclick="return update_express('updatexpress')">修改</button>
                </div>
             </div>
            </div>
        </form>
    </div>


</div>
<script type="text/javascript" src="${basePath}/js/navmenu/navmenu.js"></script>
<#import "../common/selectindex.ftl" as selectindex>
<@selectindex.selectindex "${n!''}" "${l!''}" />
<script>
    $(function(){
    	$.material.init();
    });

    /*用于控制显示div层  先显示头部和左边 稍后在显示里面的内容*/
    function show(){
        $(".show_text").fadeOut(1000).fadeIn(1000);
    }
    setTimeout("show()",1000);
</script>
</body>
</html>
