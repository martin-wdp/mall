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
    <input type="hidden" value="groupList" id="formId"/>
    <input type="hidden" value="togroup.htm" id="formName"/>
    <div class="col-lg-20 col-md-19 col-sm-18 main">
      <div class="main_cont">
        <jsp:include page="../page/breadcrumbs.jsp"></jsp:include>

        <h2 class="main_title">${pageNameChild} <small>(共${pageBean.rows}条)</small></h2>
        <div class="common_data p20">
          <div class="filter_area">
            <form role="form" class="form-inline" id="search_from" method="post">
              <input name="pageNo" value="${pageBean.pageNo }"  type="hidden" id="pageNo"/>
              <input name="pageSize" value="${pageBean.pageSize }"  type="hidden" id="pageSize"/>
              <div class="form-group">
                <div class="input-group">
                  <span class="input-group-addon">小组名称</span>
                  <input type="text" class="form-control" name="groupName" value="${groupVo.groupName }">
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

              <input class="search_text" type="hidden"/>
              <div class="form-group">
                <button type="submit" class="btn btn-primary" onclick="searchgroup();">搜索</button>
                <!--<button type="button" class="btn btn-primary advanced_search">高级搜索</button>-->
              </div>
            </form>
          </div>
          <div class="data_ctrl_area mb20">
            <div class="data_ctrl_search pull-right"></div>
            <div class="data_ctrl_brns pull-left">
              <button type="button" class="btn btn-info" id="dissolove-group">
                <i class="glyphicon glyphicon-ok-sign"></i> 解散
              </button>
            </div>
            <div class="clr"></div>
          </div>
          <form action="" method="post" id="groupList">
          <table class="table table-striped table-hover">
            <thead>
            <tr>
              <th width="10"><input type="checkbox" onclick="allunchecked(this,'groupId');"></th>
              <th>小组名称</th>
              <th>小组分类</th>
              <th>审核状态</th>
              <th>使用状态</th>
              <th>是否热门</th>
              <th>类型</th>
              <th>成员数量</th>
              <th>小组创建者</th>
               <th class="w100">创建时间</th>
              <th width="150">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${pageBean.list}" var="group" varStatus="i">
              <tr>
                <td><input type="checkbox" name="groupId" value="${group.groupId}"></td>
                <td>${group.groupName }</td>
                <td>${group.groupTypeName}</td>
               
                <td>
                  <c:if test="${group.groupCheckFlag==1}">
                    <span class="label label-success">已通过</span>
                  </c:if>
                  <c:if test="${group.groupCheckFlag==0}">
                    <span class="label label-default">待审核</span>
                  </c:if>
                  </td>
                <td>
                  <c:if test="${group.groupStatus==0}">
                    <span class="label label-success">正常</span>
                  </c:if>
                  <c:if test="${group.groupStatus==1}">
                    <span class="label label-default">停用</span>
                  </c:if>
                  </td>
                <td>
                  <c:if test="${group.groupIsHot ==0}">
                    <span class="label label-default">否</span>
                  </c:if>
                  <c:if test="${group.groupIsHot ==1}">
                    <span class="label label-success">是</span>
                  </c:if>
                  </td>
                <td>
                  <c:if test="${group.groupSecret ==0}">
                    公开
                  </c:if>
                  <c:if test="${group.groupSecret  ==1}">
                    私密
                  </c:if>
                </td>
                <td>${group.groupmember }</td>
                <td>${group.groupCreateAuthorName}</td>
                 <td><fmt:formatDate value="${group.groupCreateTime }" pattern="yyyy-MM-dd HH:mm:ss" var="createTime" />
                    ${createTime}
                </td>
                <td>
                  <div class="btn-group">
                    <button type="button" class="btn btn-default" onclick="modifiedsGroup('${group.groupId}');">设置</button>
                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                      <span class="caret"></span>
                      <span class="sr-only">Toggle Dropdown</span>
                    </button>
                    <ul class="dropdown-menu" role="menu">
                      <li><a href="javascript:;" onclick="modifiedGroup('${group.groupId}')">查看详情</a></li>
                      <li><a href="javascript:;" onclick="singleOperation('确认要解散该小组吗？','dissolvegroup.htm','togroup.htm','${group.groupId}');">解散</a></li>
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
<div class="modal fade" id="setGroup"  role="dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">设置小组</h4>
      </div>
      <div class="modal-body">
        <form class="form-horizontal" id="updateForm" method="post" enctype="multipart/form-data">
          <input type="hidden" name="groupId" id="groupupdateId">
          <h4>基本设置</h4>
          <div class="form-group">
            <label class="control-label col-sm-6">小组类型：</label>
            <div class="col-sm-1"></div>
            <div class="col-sm-8">
              <label class="radio-inline">
                <input type="radio" name="groupSecret" id="groupSecret1" value="0" checked="checked"/> 公开小组
              </label>
              <label class="radio-inline">
                <input type="radio" id="groupSecret2" value="1" name="groupSecret"/> 私密小组
              </label>
            </div>
          </div>
          <div class="form-group">
            <label class="control-label col-sm-6">小组状态：</label>
            <div class="col-sm-1"></div>
            <div class="col-sm-8">
              <label class="radio-inline">
                <input type="radio" id="groupStatus1" value="0" checked="checked" name="groupStatus"> 正常
              </label>
              <label class="radio-inline">
                <input type="radio" id="groupStatus2" value="1" name="groupStatus"> 停用
              </label>
            </div>
          </div>
          <div class="form-group">
            <label class="control-label col-sm-6">是否热门小组：</label>
            <div class="col-sm-1"></div>
            <div class="col-sm-8">
              <label class="radio-inline">
                <input type="radio" id="groupIsHot2" value="1" name="groupIsHot"> 是
              </label>
              <label class="radio-inline">
                <input type="radio" id="groupIsHot1" value="0" checked="checked"
                       name="groupIsHot"> 否
              </label>
            </div>
          </div>
          <div class="form-group">
            <label class="control-label col-sm-6">小组规模：</label>
            <div class="col-sm-1"></div>
            <div class="col-sm-5">
              <input type="number" class="form-control sort_spinner" value="2000" maxlength="3" name="groupTypeSort" id="groupTypeSort">
            </div>
            <div class="col-sm-3"></div>
          </div>
          <h4>访问权限</h4>
          <div class="form-group">
            <label class="control-label col-sm-6">成员加入方式：</label>
            <div class="col-sm-1"></div>
            <div class="col-sm-12">
              <div class="radio">
                <label>
                  <input type="radio" id="limitAddType1" value="0" checked="checked"
                         name="limitAddType" /> 允许任何人加入小组
                </label>
              </div>
              <div class="radio">
                <label>
                  <input type="radio" id="limitAddType2" value="1" name="limitAddType"/> 需要小组管理员批准后才能加入小组
                </label>
              </div>
              <div class="radio disabled">
                <label>
                  <input type="radio" id="limitAddType3" value="2" name="limitAddType"> 只有接受组员邀请才能加入小组
                </label>
              </div>
            </div>
          </div>
          <div class="form-group">
            <label class="control-label col-sm-6">成员加入条件：</label>
            <div class="col-sm-1"></div>
            <div class="col-sm-12">
              <div class="radio">
                <label>
                  <input type="radio" id="limitCondition1" value="0"
                         checked="checked" name="limitCondition"/> 全部
                </label>
              </div>
              <div class="radio">
                <label>
                  <input type="radio" id="limitCondition2" value="1"
                         name="limitCondition"/> 只限女性用户
                </label>
              </div>
              <div class="radio disabled">
                <label>
                  <input type="radio" id="limitCondition3" value="2"
                         name="limitCondition"/> 只限男性用户
                </label>
              </div>
            </div>
          </div>
          <h4>删除权限</h4>
          <div class="form-group">
            <label class="control-label col-sm-6">删除本人回复：</label>
            <div class="col-sm-1"></div>
            <div class="col-sm-8">
              <label class="radio-inline">
                <input type="radio" id="limitReplyDelType1" value="0"
                       checked="checked" name="limitReplyDelType"> 开启
              </label>
              <label class="radio-inline">
                <input type="radio" id="limitReplyDelType2"
                       value="1" name="limitReplyDelType"> 关闭
              </label>
            </div>
          </div>
          <div class="form-group">
            <label class="control-label col-sm-6">修改本人帖子回复：</label>
            <div class="col-sm-1"></div>
            <div class="col-sm-8">
              <label class="radio-inline">
                <input type="radio" id="limitModifyTopicType1" value="0"
                       checked="checked" name="limitModifyTopicType"/> 开启
              </label>
              <label class="radio-inline">
                <input type="radio" id="limitModifyTopicType2"
                       value="1" name="limitModifyTopicType"/> 关闭
              </label>
            </div>
          </div>
          <div class="form-group">
            <label class="control-label col-sm-6">删除本人小组图片：</label>
            <div class="col-sm-1"></div>
            <div class="col-sm-8">
              <label class="radio-inline">
                <input type="radio" id="limitDelPicType1" value="0" checked="checked"
                       name="limitDelPicType"/> 开启
              </label>
              <label class="radio-inline">
                <input type="radio" id="limitDelPicType2" value="1" name="limitDelPicType"> 关闭
              </label>
            </div>
          </div>
          <div class="form-group">
            <label class="control-label col-sm-6">回复帖子条件：</label>
            <div class="col-sm-1"></div>
            <div class="col-sm-12">
              <div class="radio">
                <label>
                  <input type="radio" id="limitReplyType1" value="0" checked="checked" name="limitReplyType"/> 允许任何人回复
                </label>
              </div>
              <div class="radio">
                <label>
                  <input type="radio" id="limitReplyType2" value="1" name="limitReplyType"/> 仅小组成员可以回复
                </label>
              </div>
              <div class="radio disabled">
                <label>
                  <input type="radio" id="limitReplyType3" value="2" name="limitReplyType"/> 不允许任何人回复
                </label>
              </div>
            </div>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" id="updateFormSub">确定</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
      </div>
    </div>
  </div>
</div>

<!-- 查看小组详情start -->
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
<!-- 查看小组详情end -->

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
<div class="modal fade" id="scanMember"  role="dialog">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">查看会员详情</h4>
      </div>
      <div class="modal-body">
        <div class="common_info common_tabs mt20">
          <ul class="nav nav-tabs" role="tablist">
            <li role="presentation" class="active"><a href="#tab1" aria-controls="tab1" role="tab" data-toggle="tab">基本信息</a></li>
            <li role="presentation"><a href="#tab2" aria-controls="tab2" role="tab" data-toggle="tab">购买信息</a></li>
            <li role="presentation"><a href="#tab3" aria-controls="tab3" role="tab" data-toggle="tab">未使用的优惠券</a></li>
            <li role="presentation"><a href="#tab4" aria-controls="tab4" role="tab" data-toggle="tab">已使用的优惠券</a></li>
            <li role="presentation"><a href="#tab5" aria-controls="tab5" role="tab" data-toggle="tab">商品关注</a></li>
          </ul>
          <div class="tab-content">
            <div role="tabpanel" class="tab-pane active" id="tab1">
              <div class="rec_example">
                <div class="recommend_edit">
                  <img class="good_img" alt="" src="images/avatar.jpg" width="200">
                  <div class="container-fluid">
                    <div class="row">
                      <div class="col-sm-12">
                        <p>用户名：	ningpai</p>
                      </div>
                      <div class="col-sm-12">
                        <p>真实姓名：	宁派科技</p>
                      </div>
                      <div class="col-sm-12">
                        <p>兴趣爱好：	家居/家具/家装/厨具|手机/数码|电...</p>
                      </div>
                      <div class="col-sm-12">
                        <p>昵称：	宁派科技</p>
                      </div>
                      <div class="col-sm-12">
                        <p>身份证：	320381199011043277</p>
                      </div>
                      <div class="col-sm-12">
                        <p>收货地址：	安徽- 安庆- 安庆市- dsdsd</p>
                      </div>
                      <div class="col-sm-12">
                        <p>账户余额：	￥500.00</p>
                      </div>
                      <div class="col-sm-12">
                        <p>性别：	女</p>
                      </div>
                      <div class="col-sm-12">
                        <p>手机：	13917732453</p>
                      </div>
                      <div class="col-sm-12">
                        <p>月收入：	4000-5999元</p>
                      </div>
                      <div class="col-sm-12">
                        <p>邮箱：	694113006@qq.com</p>
                      </div>
                      <div class="col-sm-12">
                        <p>注册时间：	2015-02-27 12:49:25</p>
                      </div>
                      <div class="col-sm-12">
                        <p>登 录 IP：	183.131.78.28</p>
                      </div>
                      <div class="col-sm-12">
                        <p>修改时间：	2015-02-27 12:49:25</p>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div role="tabpanel" class="tab-pane" id="tab2">
              <table class="table table-striped table-hover table-bordered">
                <thead>
                <tr>
                  <th>订单编号</th>
                  <th>商品图片</th>
                  <th width="250">商品名称</th>
                  <th>订单总价</th>
                  <th width="100">购买时间</th>
                  <th width="100">操作</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                  <td>20140702141641</td>
                  <td><img alt="" src="images/good_1_small.jpg" height="50"> </td>
                  <td>花落红*娘子写新款中式真丝香云纱桑蚕丝时尚花色七分袖旗袍上衣</td>
                  <td>8788.42</td>
                  <td>2014-07-02 14:16:41</td>
                  <td>
                    <button type="button" class="btn btn-default">查看订单</button>
                  </td>
                </tr>
                <tr>
                  <td>20140702141641</td>
                  <td><img alt="" src="images/good_1_small.jpg" height="50"> </td>
                  <td>花落红*娘子写新款中式真丝香云纱桑蚕丝时尚花色七分袖旗袍上衣</td>
                  <td>8788.42</td>
                  <td>2014-07-02 14:16:41</td>
                  <td>
                    <button type="button" class="btn btn-default">查看订单</button>
                  </td>
                </tr>
                <tr>
                  <td>20140702141641</td>
                  <td><img alt="" src="images/good_1_small.jpg" height="50"> </td>
                  <td>花落红*娘子写新款中式真丝香云纱桑蚕丝时尚花色七分袖旗袍上衣</td>
                  <td>8788.42</td>
                  <td>2014-07-02 14:16:41</td>
                  <td>
                    <button type="button" class="btn btn-default">查看订单</button>
                  </td>
                </tr>
                </tbody>
              </table>
              <div class="table_foot">
                <div class="table_pagenav pull-right">
                  <div class="meneame">
                    <span class="disabled"> 上一页 </span>
                    <span class="current"> 1 </span>
                    <a href="#?page=2"> 2 </a>
                    <a href="#?page=3"> 3 </a>
                    <a href="#?page=4"> 4 </a>
                    <a href="#?page=5"> 5 </a>
                    ...
                    <a href="#?page=199"> 199 </a>
                    <a href="#?page=200"> 200 </a>
                    <a href="#?page=2"> 下一页 </a>
                  </div>
                </div>
                <div class="clr"></div>
              </div>
            </div>
            <div role="tabpanel" class="tab-pane" id="tab3">
              <table class="table table-striped table-hover table-bordered">
                <thead>
                <tr>
                  <th>优惠券号码</th>
                  <th>类别</th>
                  <th>优惠金额</th>
                  <th width="100">操作</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                  <td>6D899MA5WL7F5LJHW4J5H8LRQ4JS006X</td>
                  <td>直降</td>
                  <td>100</td>
                  <td>
                    <button type="button" class="btn btn-default">查看</button>
                  </td>
                </tr>
                <tr>
                  <td>6D899MA5WL7F5LJHW4J5H8LRQ4JS006X</td>
                  <td>直降</td>
                  <td>100</td>
                  <td>
                    <button type="button" class="btn btn-default">查看</button>
                  </td>
                </tr>
                </tbody>
              </table>
              <div class="table_foot">
                <div class="table_pagenav pull-right">
                  <div class="meneame">
                    <span class="disabled"> 上一页 </span>
                    <span class="current"> 1 </span>
                    <a href="#?page=2"> 2 </a>
                    <a href="#?page=3"> 3 </a>
                    <a href="#?page=4"> 4 </a>
                    <a href="#?page=5"> 5 </a>
                    ...
                    <a href="#?page=199"> 199 </a>
                    <a href="#?page=200"> 200 </a>
                    <a href="#?page=2"> 下一页 </a>
                  </div>
                </div>
                <div class="clr"></div>
              </div>
            </div>
            <div role="tabpanel" class="tab-pane" id="tab4">
              <table class="table table-striped table-hover table-bordered">
                <thead>
                <tr>
                  <th>优惠券号码</th>
                  <th>订单号</th>
                  <th>优惠券类型</th>
                  <th>优惠金额</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                  <td colspan="4"><p class="text-center">暂无可用数据</p></td>
                </tr>
                </tbody>
              </table>
            </div>
            <div role="tabpanel" class="tab-pane" id="tab5">
              <table class="table table-striped table-hover table-bordered">
                <thead>
                <tr>
                  <th>商品编号</th>
                  <th>商品图片</th>
                  <th width="250">商品名称</th>
                  <th>商品价格</th>
                  <th>关注时间</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                  <td>3170</td>
                  <td><img alt="" src="images/good_1_small.jpg" height="50"> </td>
                  <td>花落红*娘子写新款中式真丝香云纱桑蚕丝时尚花色七分袖旗袍上衣</td>
                  <td>99.00</td>
                  <td>2014-07-02 14:16:41</td>
                </tr>
                <tr>
                  <td>3170</td>
                  <td><img alt="" src="images/good_1_small.jpg" height="50"> </td>
                  <td>花落红*娘子写新款中式真丝香云纱桑蚕丝时尚花色七分袖旗袍上衣</td>
                  <td>99.00</td>
                  <td>2014-07-02 14:16:41</td>
                </tr>
                <tr>
                  <td>3170</td>
                  <td><img alt="" src="images/good_1_small.jpg" height="50"> </td>
                  <td>花落红*娘子写新款中式真丝香云纱桑蚕丝时尚花色七分袖旗袍上衣</td>
                  <td>99.00</td>
                  <td>2014-07-02 14:16:41</td>
                </tr>
                </tbody>
              </table>
              <div class="table_foot">
                <div class="table_pagenav pull-right">
                  <div class="meneame">
                    <span class="disabled"> 上一页 </span>
                    <span class="current"> 1 </span>
                    <a href="#?page=2"> 2 </a>
                    <a href="#?page=3"> 3 </a>
                    <a href="#?page=4"> 4 </a>
                    <a href="#?page=5"> 5 </a>
                    ...
                    <a href="#?page=199"> 199 </a>
                    <a href="#?page=200"> 200 </a>
                    <a href="#?page=2"> 下一页 </a>
                  </div>
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
<!-- 高级搜索start -->
<div class="advanced_search_cont none">
  <div class="advanced_search_form">
    <form class="form-horizontal" id="advanced_from" method="post">
      <input name="pageNo" value="${pageBean.pageNo }"  type="hidden" id="advancedpageNo"/>
      <input name="pageSize" value="${pageBean.pageSize }"  type="hidden" id="advancedpageSize"/>
      <div class="form-group">
        <label class="control-label col-sm-7">小组名称：</label>
        <div class="col-sm-15">
          <input type="text" class="form-control" name="groupName" value="${groupVo.groupName}">
        </div>
      </div>
      <div class="form-group">
        <label class="control-label col-sm-7">小组分类：</label>
        <div class="col-sm-15">
          <select class="form-control" name="groupTypeId">
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
          <input type="text" name="createtime" class="form-control" placeholder="yyyy-mm-dd" readonly="readonly" id="datepicker" value="${createTime}">
        </div>
      </div>
      <div class="form-group">
        <label class="control-label col-sm-7">结束时间：</label>
        <div class="col-sm-15">
          <fmt:formatDate value="${group.groupCreateTimeTo }" pattern="MM/dd/yyyy" var="createTimeto" />
          <input type="text" name="createtimeto" class="form-control" placeholder="yyyy-mm-dd" readonly="readonly" id="datepickerr" value="${createTimeto}">
        </div>
      </div>
      <div class="form-group">
        <label class="control-label col-sm-7">类型：</label>
        <div class="col-sm-15">
          <select class="form-control" name="groupSecret">
            <option value="" selected="selected">全部</option>
            <option value="0">公开</option>
            <option value="1">私密</option>
          </select>
        </div>
      </div>
      <div class="form-group">
        <div class="col-sm-offset-7 col-sm-15">
          <button type="submit" class="btn btn-primary btn-sm" onclick="searchadvancedgroup()">确认搜索</button>
        </div>
      </div>
    </form>
  </div>
</div>
<!-- 高级搜索end -->
<input type="hidden"  id="attrName" value="${condition }" />
<input type="hidden" id="attrValue" value="${convlaue }" />
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<%=basePath %>js/bootstrap.min.js"></script>
<script src="<%=basePath %>js/summernote.min.js"></script>
<script src="<%=basePath %>js/language/summernote-zh-CN.js"></script>
<script src="<%=basePath %>js/bootstrap-select.min.js"></script>
<script src="<%=basePath %>js/bootstrap-datetimepicker.min.js"></script>
<script src="<%=basePath %>js/language/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="<%=basePath %>js/social/grouplist.js"></script>
<script src="<%=basePath %>js/common.js"></script>
<script src="<%=basePath %>js/common/common_alert.js"></script>
<script src="<%=basePath %>js/common/common_checked.js"></script>
<script src="<%=basePath %>js/select2.min.js"></script>
<script type="text/javascript">
  window.onload=function(){
    if($("#attrName").val()!=""){
      $(".search_sel span").text($("#attrName").val());
      $(".search_text").val($("#attrValue").val());
      if($("#attrName").val()=="小组名称"){
        $(".search_text").attr("name","groupName");
      }else if($("#attrName").val()=="小组创建者"){
        $(".search_text").attr("name","groupCreateAuthorName");
      }
    }
    /* if($("#showFlag").val()=='1'){
     $(".advancedsearch").show();
     } */
  };
</script>

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

