<!doctype html>
<html lang="zh-CN">
<#assign basePath=request.contextPath>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="renderer" content="webkit">
    <title>我的店铺</title>
    <link rel="stylesheet" href="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/css/bootstrap.min.css"/>
    <link href="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/css/bootstrap-datepicker.min.css" rel="stylesheet">
    <link href="${basePath}/css/material.css" rel="stylesheet">
    <link href="${basePath}/css/ripples.css" rel="stylesheet">
    <link rel="stylesheet" href="${basePath}/css/style.css"/>
</head>
<body>

<#--引入头部-->
<#include "../index/indextop.ftl">
<input type="hidden" value="${basePath}" id="basePath"/>

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
                <li class="active" style="color: #07d;">员工管理</li>
            </ol>

            <div class="app-content">
                <div class="cfg-content">
                    <div class="bg-warning tips-word mb20">
                        <strong>当前子账户使用情况</strong>
                        <span class="ml10">已使用<b class="text-danger"><#if (pb.list?size!=0)>${pb.rows}</#if></b></span>
                        <span class="ml10">剩余<b class="text-danger"><#if (pb.list?size!=0)>${50-pb.rows}</#if></b></span>
                    </div>
                    <div class="ops-bar">
                        <button class="btn btn-primary btn-sm" type="button" data-toggle="modal" onclick="shows();"><i class="glyphicon glyphicon-plus"></i>新建员工</button>
                    </div>
                    <table class="table">
                        <thead>
                        <tr>
                            <th>账户名称</th>
                            <th>昵称</th>
                            <th>角色</th>
                            <th>状态</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                    <#if (pb.list?size>0)>
                    <#list pb.list as cust>
                        <#if cust.thirdAuthority??>
                        <tr>
                            <td>${cust.customerUsername!''}</td>
                            <td>${cust.customerNickname!''}</td>
                            <td><#if cust.thirdAuthority??>${cust.thirdAuthority.designation}</#if></td>
                            <td>
                                <#if cust.isFlag??>
                                    <#if cust.isFlag == '0' >
                                        <i class="glyphicon glyphicon-ok"></i>
                                    <#else>
                                        <i class="glyphicon glyphicon-remove"></i>
                                    </#if>
                                </#if>
                            </td>
                            <td>
                                <div class="btn-group">
                                    <button type="button" class="btn btn-primary btn-sm modify-auth-btn" data-toggle="modal" data-key="${cust.customerId}" auth-key="<#if cust.thirdAuthority??>${cust.thirdAuthority.id}</#if>" data-target="#changePower">修改权限</button>
                                    <button type="button" class="btn btn-primary btn-sm dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                        <span class="caret"></span>
                                    </button>
                                    <ul class="dropdown-menu" role="menu">
                                        <#if cust.isFlag??>
                                            <#if cust.isFlag == '0' >
                                                <li><a href="javascript:;" class="disable-btn" data-key="${cust.customerId}">停用</a></li>
                                            <#else>
                                                <li><a href="javascript:;" class="disable-btn disable-btn-s" data-key="${cust.customerId}">启用</a></li>
                                            </#if>
                                        </#if>
                                        <li><a href="javascript:;" class="delete-btn" data-key="${cust.customerId}">删除</a></li>
                                    </ul>
                                </div>
                            </td>
                        </tr>
                        </#if>
                    </#list>
                    </#if>
                        </tbody>
                    </table>
                    <div class="footer-operation">
                    <#-- 分页 -->
		                 <#import "pageBean.ftl" as page>
		                 <@page.pagination pb />
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<#--引入右部与底部-->
<#include "../common/leftfooter.ftl">

<div class="modal fade" id="addStaff" role="dialog" aria-hidden="true">
    <form method='post' action="addemp.htm" id="empForm" >
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">新建员工</h4>
            </div>
            <div class="modal-body">
                <div class="form-item">
                    <label class="control-label"><b>*</b>用户名：</label>
                    <div class="controls">
                        <input type="text" maxlength="20" name="customerUsername" id="customerUsername" class="form-control"/>
                        <label style="color:red;"></label>
                    </div>
                </div>
                <div class="form-item">
                    <label class="control-label"><b>*</b>密码：</label>
                    <div class="controls">
                        <input type="password" name="customerPassword" id="password" class="form-control"/>
                        <label style="color:red;"></label>
                    </div>
                </div>
                <div class="form-item">
                    <label class="control-label"><b>*</b>确认密码：</label>
                    <div class="controls">
                        <input type="password" name="repassword" id="repassword" class="form-control"/>
                        <label style="color:red;"></label>
                    </div>
                </div>
                <div class="form-item">
                    <label class="control-label"><b>*</b>员工姓名：</label>
                    <div class="controls">
                        <input type="text" name="infoRealname" id="realname" class="form-control"
                        <#-- 禁止录入特殊字符-->
                               onKeypress="if ((event.keyCode > 32 && event.keyCode < 48) || (event.keyCode > 57 && event.keyCode < 65) || (event.keyCode > 90 && event.keyCode < 97)) event.returnValue = false;"
                               maxlength="20"/>
                        <label style="color:red;"></label>
                    </div>
                </div>
                <div class="form-item">
                    <label class="control-label"><b>*</b>角色：</label>
                    <div class="controls checkWp authlist radio radio-primary">
                        <#--<label class="choose-label"><input name="role" type="radio"/>新职位</label>
                        <label class="choose-label"><input name="role" type="radio"/>测试</label>
                        <label class="choose-label"><input name="role" type="radio"/>客服</label>-->
                            </br>
                        <label class="tips error-tip" style="color:red;"></label>
                    </div>
                </div>
                <!--<div class="form-item">
                    <label class="control-label">权限：</label>
                    <div class="controls checkWp">
                        此员工未设置权限
                    </div>
                </div>-->
            </div>
            <div class="modal-footer">
                <button class="btn btn-default" type="button" data-dismiss="modal">关闭</button>
                <button class="btn btn-primary" type="button" id="addEmp">保存</button>
            </div>
        </div>
    </div>
    </form>
</div>
<#--修改角色-->
<div class="modal fade" id="changePower" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">修改角色</h4>
            </div>
            <form id="updatebycustid" method="post" action="updatebycustid.html">
                <input type="hidden" id="updatecustid" name="managerId" />
            <div class="modal-body">
                <div class="form-item">
                    <label class="control-label">角色：</label>
                    <div class="controls">
                        <select class="form-control" name="authorityId">
                        <#list roles as role>
                            <option value="${role.id}">${role.designation}</option>
                        </#list>
                        </select>
                    </div>
                </div>
            </div>
            </form>
            <div class="modal-footer">
                <button class="btn btn-default cancel-modify-auth" type="button" data-dismiss="modal">关闭</button>
                <button class="btn btn-primary" type="button" onclick="javascript:$('#updatebycustid').submit();">确定</button>
            </div>
        </div>
    </div>
</div>

<#--禁用／删除角色-->
<div class="modal fade" id="operate-tip" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">操作提示</h4>
            </div>
            <div class="modal-body">
                <div class="form-item error">
                    <label class="control-label opearte-info-title">请至少选择一行！</label>
                </div>
            </div>
            <form id="operate-form" action="" method="post">
                <input type="hidden" name="custId" id="operateKey"/>
                <input type="hidden" name="flag" id="cusFlag"/>
            </form>
            <div class="modal-footer">
            <button class="btn btn-default" id="cancel-operate" type="button" data-dismiss="modal">取消</button>
                <button class="btn btn-primary" type="button" id="confirm-operate" data-dismiss="modal">确定</button>
            </div>
        </div>
    </div>
</div>

<script src="http://cdn.staticfile.org/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/js/bootstrap.min.js"></script>
<script src="${basePath}/js/ripples.min.js"></script>
<script src="${basePath}/js/material.min.js"></script>
<script src="${basePath}/js/app.js"></script>
<script type="text/javascript" src="${basePath}/js/navmenu/navmenu.js"></script>
<!--模板相关END-->
<#import "../common/selectindex.ftl" as selectindex>
<@selectindex.selectindex "${n!''}" "${l!''}" />
<script type="text/javascript" src="${basePath}/js/sell/sellervalidate.js"></script>
<script src="${basePath}/js/sell/emplist.js"></script>
<script>
    function show(){
        $(".show_text").fadeOut(1000).fadeIn(1000);
    }
    setTimeout("show()",1000);
    /*加载角色*/
    loadauth();
    function shows(){
        $("#customerUsername").val("");
        $("#password").val("");
        $("#repassword").val("");
        $("#realname").val("");
        $("#addStaff").modal("show");
    }
    $(function(){
    	$.material.init();
    });
</script>
</body>
</html>