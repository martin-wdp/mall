<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
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
  <link href="<%=basePath %>css/bootstrap-datetimepicker.min.css" rel="stylesheet">
  <link href="<%=basePath %>css/select2.min.css" rel="stylesheet">
  <link href="<%=basePath %>css/style.css" rel="stylesheet">

  <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
  <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>
<body>
<input type="hidden" name="CSRFToken" value="${token}" class="token_val"/>
<jsp:include page="../page/header.jsp"></jsp:include>
<div class="page_body container-fluid">
  <div class="row">
    <jsp:include page="../page/left.jsp"></jsp:include>
    <input type="hidden" value="checkGroupList" id="formId"/>
    <input type="hidden" value="checkgrouplist.htm" id="formName"/>
    <div class="col-lg-20 col-md-19 col-sm-18 main">
      <div class="main_cont">
        <jsp:include page="../page/breadcrumbs.jsp"></jsp:include>

        <h2 class="main_title">${pageNameChild} <small>(共${pageBean.rows}条)</small></h2>

        <div class="common_data p20">
          <div class="filter_area">
            <form role="form" class="form-inline" id="search_check_from" method="post">
              <input name="pageNo" value="${pageBean.pageNo }"  type="hidden" id="advancedpageNo"/>
              <input name="pageSize" value="${pageBean.pageSize }"  type="hidden" id="advancedpageSize"/>
              <div class="form-group">
                <div class="input-group">
                  <span class="input-group-addon">小组名称</span>
                  <input type="text" class="form-control search_text" name="groupName" value="${groupname }">
                </div>
              </div>

              <div class="form-group">
                <div class="input-group">
                  <span class="input-group-addon">小组分类</span>
                  <select class="cate_selector w150" name="groupTypeId" data-live-search="true">
                    <option value="" selected="selected">请选择</option>
                    <c:forEach items="${grouptypeList }" var="grouptype">
                      <option value="${grouptype.groupTypeId }">${grouptype.groupTypeName }</option>
                    </c:forEach>
                  </select>
                </div>
              </div>

              <div class="form-group">
                <div class="input-group">
                  <span class="input-group-addon">类型</span>
                  <select class="cate_selector w150" name="groupSecret" id="groupSecret" data-live-search="true">
                    <option value="" selected="selected">全部</option>
                    <option value="0">公开</option>
                    <option value="1">私密</option>
                  </select>
                </div>
              </div>

              <div class="form-group">
                <button type="button" class="btn btn-primary" onclick="searchcheck();">搜索</button>
                <!--<button type="button" class="btn btn-primary advanced_search">高级搜索</button>-->
              </div>
            </form>
          </div>
          <div class="data_ctrl_area mb20">
            <div class="data_ctrl_search pull-right"></div>
            <div class="data_ctrl_brns pull-left">
              <button type="button" class="btn btn-info" id="pass-group">
                <i class="glyphicon glyphicon-ok"></i> 通过
              </button>
              <button type="button" class="btn btn-info" id="refuse-group">
                <i class="glyphicon glyphicon-remove"></i> 拒绝
              </button>
            </div>
            <div class="clr"></div>
          </div>
          <form action="" method="post" id="checkGroupList">
          <table class="table table-striped table-hover">
            <thead>
            <tr>
              <th width="10"><input type="checkbox" onclick="allunchecked(this,'groupIds');"/></th>
              <th>小组名称</th>
              <th>小组分类</th>
              <th>创建时间</th>
              <th>审核状态</th>
              <th>类型</th>
              <th>小组头像</th>
              <th>创建者昵称</th>
              <th width="150">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${pageBean.list}" var="group" varStatus="i">
            <tr>
              <td><input type="checkbox" name=groupIds  value="${group.groupId}"/></td>
              <td>${group.groupName }</td>
              <td>${group.groupTypeName}</td>
              <td>
                <fmt:formatDate value="${group.groupCreateTime }" pattern="yyyy-MM-dd HH:mm:ss" var="createTime" />
                  ${createTime}
              </td>
              <td>
                <a href="javascript:;">
                  <c:if test="${group.groupCheckFlag==1}">
                    <span class="label label-warning">已通过</span>
                  </c:if>
                  <c:if test="${group.groupCheckFlag==0}">
                    <span class="label label-warning">待审核</span>
                  </c:if>
                </a>
              </td>
              <td>
                <c:if test="${group.groupSecret ==0}">
                  公开
                </c:if>
                <c:if test="${group.groupSecret  ==1}">
                  私密
                </c:if>
              </td>
              <td><img alt="" src="${group.groupHead}" height="50"></td>
              <td>${group.groupCreateAuthorName}</td>
              <td>
                <div class="btn-group">
                  <button type="button" class="btn btn-default" onclick="singleOperation('确定要审核通过该小组吗？','passgroup.htm','checkgrouplist.htm','${group.groupId}');">通过</button>
                  <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                    <span class="caret"></span>
                    <span class="sr-only">Toggle Dropdown</span>
                  </button>
                  <ul class="dropdown-menu" role="menu">
                    <li><a href="javascript:;" onclick="modifiedGroup('${group.groupId}');">查看详情</a></li>
                    <!--<li><a href="javascript:;" onclick="">删除</a></li>-->
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

<!-- Modal -->
<div class="modal fade" id="addMember"  role="dialog">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">添加会员</h4>
      </div>
      <div class="modal-body">
        <form class="form-horizontal">
          <div class="form-group">
            <label class="control-label col-sm-5">用户名：</label>
            <div class="col-sm-1"></div>
            <div class="col-sm-8">
              <input type="text" class="form-control">
            </div>
          </div>
          <div class="form-group">
            <label class="control-label col-sm-5">邮箱：</label>
            <div class="col-sm-1"></div>
            <div class="col-sm-8">
              <input type="text" class="form-control">
            </div>
            <div class="col-sm-3"></div>
          </div>
          <div class="form-group">
            <label class="control-label col-sm-5">密码：</label>
            <div class="col-sm-1"></div>
            <div class="col-sm-8">
              <input type="password" class="form-control">
            </div>
            <div class="col-sm-3"></div>
          </div>
          <div class="form-group">
            <label class="control-label col-sm-5">重复密码：</label>
            <div class="col-sm-1"></div>
            <div class="col-sm-8">
              <input type="password" class="form-control">
            </div>
            <div class="col-sm-3"></div>
          </div>
          <div class="form-group">
            <label class="control-label col-sm-5">会员等级：</label>
            <div class="col-sm-1"></div>
            <div class="col-sm-4">
              <select class="form-control">
                <option>普通会员</option>
                <option>黄金会员</option>
                <option>白金会员</option>
                <option>钻石会员</option>
              </select>
            </div>
            <div class="col-sm-3"></div>
          </div>
          <div class="form-group">
            <label class="control-label col-sm-5">选择图片：</label>
            <div class="col-sm-1"></div>
            <div class="col-sm-8">
              <p class="pt5"><input type="file"></p>
            </div>
          </div>
          <div class="form-group">
            <label class="control-label col-sm-5">预览图片：</label>
            <div class="col-sm-1"></div>
            <div class="col-sm-8">
              <img alt="" src="images/avatar.jpg" height="100">
            </div>
          </div>
          <div class="form-group">
            <label class="control-label col-sm-5">性别：</label>
            <div class="col-sm-1"></div>
            <div class="col-sm-10">
              <label class="radio-inline">
                <input type="radio" name="inlineRadioOptions" id="inlineRadio1" value="option1"> 保密
              </label>
              <label class="radio-inline">
                <input type="radio" name="inlineRadioOptions" id="inlineRadio2" value="option2"> 男
              </label>
              <label class="radio-inline">
                <input type="radio" name="inlineRadioOptions" id="inlineRadio3" value="option2"> 女
              </label>
            </div>
            <div class="col-sm-3"></div>
          </div>
          <div class="form-group">
            <label class="control-label col-sm-5">真实姓名：</label>
            <div class="col-sm-1"></div>
            <div class="col-sm-4">
              <input type="text" class="form-control">
            </div>
          </div>
          <div class="form-group">
            <label class="control-label col-sm-5">手机号码：</label>
            <div class="col-sm-1"></div>
            <div class="col-sm-8">
              <input type="text" class="form-control">
            </div>
          </div>
          <div class="form-group">
            <label class="control-label col-sm-5">身份证号码：</label>
            <div class="col-sm-1"></div>
            <div class="col-sm-12">
              <input type="text" class="form-control">
            </div>
          </div>
          <div class="form-group">
            <label class="control-label col-sm-5">所在地区：</label>
            <div class="col-sm-1"></div>
            <div class="col-sm-12">
              <select class="inline" data-live-search="true">
                <option>选择省份</option>
                <option>江苏省</option>
              </select>
              <select class="inline" data-live-search="true">
                <option>选择城市</option>
                <option>南京市</option>
              </select>
              <select class="inline" data-live-search="true">
                <option>选择区县</option>
                <option>建邺区</option>
              </select>
            </div>
          </div>
          <div class="form-group">
            <label class="control-label col-sm-5">详细地址：</label>
            <div class="col-sm-1"></div>
            <div class="col-sm-12">
              <input type="text" class="form-control">
            </div>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary">确定</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
      </div>
    </div>
  </div>
</div>

<!-- Modal -->
<div class="modal fade" id="setMemberType"  role="dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">设置会员类型</h4>
      </div>
      <div class="modal-body">
        <form class="form-horizontal">
          <div class="form-group">
            <label class="control-label col-sm-5">设置会员类型：</label>
            <div class="col-sm-1"></div>
            <div class="col-sm-10">
              <label class="radio-inline">
                <input type="radio" name="inlineRadioOptions2" id="inlineRadio5" value="option1"> 普通会员
              </label>
              <label class="radio-inline">
                <input type="radio" name="inlineRadioOptions2" id="inlineRadio6" value="option2"> 前台管理员
              </label>
            </div>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary">确定</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
      </div>
    </div>
  </div>
</div>

<!-- Modal -->
<div class="modal fade" id="sendMessages"  role="dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">发送通知</h4>
      </div>
      <div class="modal-body">
        <form class="form-horizontal">
          <div class="form-group">
            <label class="control-label col-sm-5">标题：</label>
            <div class="col-sm-1"></div>
            <div class="col-sm-10">
              <input type="text" class="form-control">
            </div>
          </div>
          <div class="form-group">
            <label class="control-label col-sm-5">内容：</label>
            <div class="col-sm-1"></div>
            <div class="col-sm-10">
              <textarea class="form-control" rows="5"></textarea>
            </div>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary">确定</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
      </div>
    </div>
  </div>
</div>

<!-- Modal -->
<div class="modal fade" id="scanGroup"  role="dialog">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">查看小组详情</h4>
      </div>
      <div class="modal-body">
        <div class="common_info common_tabs mt20">
          <ul class="nav nav-tabs" role="tablist">
            <li role="presentation" class="active"><a href="#tab1" aria-controls="tab1" role="tab" data-toggle="tab">详细信息</a></li>
          </ul>
          <div class="tab-content">
            <div role="tabpanel" class="tab-pane active" id="tab1">
              <div class="rec_example">
                <div class="recommend_edit">
                  <img class="good_img" alt="" src="" id="groupHead" width="200">
                  <div class="container-fluid">
                    <div class="row">
                      <div class="col-sm-12">
                        <p id="groupName"></p>
                      </div>
                      <div class="col-sm-12">
                        <p id="groupLimitMember"></p>
                      </div>
                      <div class="col-sm-12">
                        <p>加入类型：	只有接受组员邀请才能加入小组</p>
                      </div>
                      <div class="col-sm-12">
                        <p id="groupTypeName"></p>
                      </div>
                      <div class="col-sm-12">
                        <p id="groupSecrets"></p>
                      </div>
                      <div class="col-sm-12">
                        <p id="groupIsHot"></p>
                      </div>
                      <div class="col-sm-12">
                        <p id="groupCreateTime"></p>
                      </div>
                      <div class="col-sm-12">
                        <p id="groupRemark"></p>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
      </div>
    </div>
  </div>
</div>

<!-- 高级搜索弹出层start
<div class="advanced_search_cont none">
  <div class="advanced_search_form">
    <form class="form-horizontal" id="advanced_from" method="post">
      <input name="pageNo" value="${pageBean.pageNo }"  type="hidden" id="advancedpageNo1"/>
      <input name="pageSize" value="${pageBean.pageSize }"  type="hidden" id="advancedpageSize1"/>
      <div class="form-group">
        <label class="control-label col-sm-7">小组名称：</label>
        <div class="col-sm-15">
          <input type="text" class="form-control" name="groupName" value="${groupname }">
        </div>
      </div>
      <div class="form-group">
        <label class="control-label col-sm-7">小组分类：</label>
        <div class="col-sm-15">
          <select class="form-control" name="groupTypeId" >
            <option value="" selected="selected">请选择</option>
            <c:forEach items="${grouptypeList }" var="grouptype">
              <option value="${grouptype.groupTypeId }">${grouptype.groupTypeName }</option>
            </c:forEach>
          </select>
        </div>
      </div>
      <div class="form-group">
        <label class="control-label col-sm-7">起始时间：</label>
        <div class="col-sm-15">
          <fmt:formatDate value="${group.groupCreateTime }" pattern="MM/dd/yyyy" var="createTime" />
          <input  readonly="readonly" id="datepicker" value="${createTime}" type="text" name="createtime" class="form-control" placeholder="yyyy-mm-dd"/>
        </div>
      </div>
      <div class="form-group">
        <label class="control-label col-sm-7">结束时间：</label>
        <div class="col-sm-15">
          <fmt:formatDate value="${group.groupCreateTimeTo }" pattern="MM/dd/yyyy" var="createTimeto" />
          <input  readonly="readonly" id="datepickerr" value="${createTimeto}" type="text" name="createtimeto" class="form-control" placeholder="yyyy-mm-dd"/>
        </div>
      </div>
      <div class="form-group">
        <label class="control-label col-sm-7">类型：</label>
        <div class="col-sm-15">
          <select class="form-control" name="groupSecret" id="groupSecret" >
            <option value="" selected="selected">全部</option>
            <option value="0">公开</option>
            <option value="1">私密</option>
          </select>
          <!--<label class="radio-inline">
            <input type="radio" name="yanzheng1" value="option1"> 公开
          </label>
          <label class="radio-inline">
            <input type="radio" name="yanzheng1" value="option2"> 私密
          </label>
        </div>
      </div>
      <div class="form-group">
        <div class="col-sm-offset-7 col-sm-15">
          <button type="button" class="btn btn-primary btn-sm" onclick="searchadvancedcheck()">确认搜索</button>
        </div>
      </div>
    </form>
  </div>
</div>-->
<!-- 高级搜索弹出层end -->


<!-- 搜索非法弹出层start -->
<div class="modal fade" id="dialog-refuse-tip1"  role="dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">错误提示</h4>
      </div>
      <div class="modal-body">
        <form class="form-horizontal">
          <div class="form-group">
            <span style="padding-left:20px;color:red;font-size:15px;">请输入正确的内容,不能包含特殊字符!</span>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">确定</button>
      </div>
    </div>
  </div>
</div>
<!-- 搜索非法弹出层end -->


<!-- 拒绝回复弹出层start -->
<div class="modal fade" id="dialog-refuse-tip2"  role="dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">拒绝理由</h4>
      </div>
      <div class="modal-body">
        <form class="form-horizontal">
          <div class="form-group">
            <label class="control-label col-sm-5">原因：</label>
            <div class="col-sm-1"></div>
            <div class="col-sm-15">
              <textarea class="form-control" rows="5" name="refuseReason" id="reason"></textarea><br/>
              <span id="treplay" style="color: red;"></span>
            </div>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal" onclick="refuseGroupMultiWin('refusegroup.htm','checkgrouplist.htm')">确定</button>
      </div>
    </div>
  </div>
</div>
<!-- 拒绝回复弹出层end -->


<!-- Include all compiled plugins (below), or include individual files as needed
<script src="<%=basePath %>js/jquery.min.js"></script> -->
<script src="<%=basePath %>js/bootstrap.min.js"></script>
<script src="<%=basePath %>js/summernote.min.js"></script>
<script src="<%=basePath %>js/language/summernote-zh-CN.js"></script>
<script src="<%=basePath %>js/bootstrap-select.min.js"></script>
<script src="<%=basePath %>js/bootstrap-datetimepicker.min.js"></script>
<script src="<%=basePath %>js/language/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="<%=basePath %>js/common.js"></script>
<script src="<%=basePath %>js/social/grouplist.js"></script>
<script src="<%=basePath %>js/social/checkgroup.js"></script>
<script src="<%=basePath %>js/common/common_alert.js"></script>
<script src="<%=basePath %>js/common/common_checked.js"></script>
<script src="<%=basePath %>js/select2.min.js"></script>
<script>
  $(function(){
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

    /* 为选定的select下拉菜单开启搜索提示 */
    $('select[data-live-search="true"]').select2();
    /* 为选定的select下拉菜单开启搜索提示 END */

    /* 富文本编辑框 */
    $('.summernote').summernote({
      height: 300,
      tabsize: 2,
      lang: 'zh-CN'
    });

    /* 选择规格值 */
    $('.spec_set input').change(function(){
      if($(this).is(':checked')){
        $(this).parent().parent().next().slideDown('fast');
      }
      else {
        $(this).parent().parent().next().slideUp('fast');
      }
    });

    /* 下面是表单里面的填写项提示相关的 */
    $('.zhekoulv').popover({
      content : '有效值0~1,如果输入0.85,表示该会员等级以销售价85折购买商品',
      trigger : 'hover'
    });
    $('.morendengji').popover({
      content : '如果选择"是",顾客注册会员时,初始等级为当前等级',
      trigger : 'hover'
    });
    $('.suoxujifen').popover({
      content : '按积分升级时,会员积分达到此标准后会自动升级为当前等级',
      trigger : 'hover'
    });

    /* 高级搜索 */
    $('.advanced_search').popover({
      html : true,
      title : '高级搜索',
      content : $('.advanced_search_cont').html(),
      trigger : 'click',
      placement : 'bottom'
    });

    /* 下面是表单里面的日期时间选择相关的 */
    $('.form_datetime').datetimepicker({
        format: 'yyyy-mm-dd hh:ii:00:00',
      weekStart : 1,
      autoclose : true,
      language : 'zh-CN',
      pickerPosition : 'bottom-left',
      todayBtn : true,
      viewSelect : 'hour'
    });
    $('.form_date').datetimepicker({
      format : 'yyyy-mm-dd',
      weekStart : 1,
      autoclose : true,
      language : 'zh-CN',
      pickerPosition : 'bottom-left',
      minView : 2,
      todayBtn : true
    });
    /* 下面是表单里面的日期时间选择相关的 END */

  });
</script>
</body>
</html>
