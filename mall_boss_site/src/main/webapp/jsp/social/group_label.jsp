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
    <input type="hidden" value="grouplabelList" id="formId"/>
    <input type="hidden" value="labellist.htm" id="formName"/>
    <div class="col-lg-20 col-md-19 col-sm-18 main">
      <div class="main_cont">
        <jsp:include page="../page/breadcrumbs.jsp"></jsp:include>

        <h2 class="main_title">${pageNameChild}<small>(共${pageBean.rows }条)</small></h2>

        <div class="common_data p20">
          <div class="filter_area">
            <form role="form" class="form-inline" id="search_from" method="post" action="labellist.htm?condition=1">
              <input name="pageNo" value="${pageBean.pageNo }"  type="hidden" id="pageNo"/>
              <input name="pageSize" value="${pageBean.pageSize }"  type="hidden" id="pageSize"/>
              <div class="form-group">
                <div class="input-group">
                  <span class="input-group-addon">标签名称</span>
                  <input type="text" class="form-control search_text" name="searchText" value="${selectBean.searchText }" id="searchText"/>
                </div>
              </div>
              <div class="form-group">
                <button type="submit" class="btn btn-primary" onclick="searchtype();">搜索</button>
              </div>
            </form>
          </div>
          <div class="data_ctrl_area mb20">
            <div class="data_ctrl_search pull-right"></div>
            <div class="data_ctrl_brns pull-left">
              <button type="button" class="btn btn-info" id="create-label">
                <i class="glyphicon glyphicon-plus"></i> 添加
              </button>
              <button type="button" class="btn btn-info" id="unuse-label">
                <i class="glyphicon glyphicon-remove-circle"></i> 停用
              </button>
              <button type="button" class="btn btn-info" id="recovery-label">
                <i class="glyphicon glyphicon-ok-circle"></i> 恢复
              </button>
            </div>
            <div class="clr"></div>
          </div>
          <form action="" method="post" id="grouplabelList">
          <table class="table table-striped table-hover">
            <thead>
            <tr>
              <th width="10"><input type="checkbox" onclick="allunchecked(this,'groupLabelId')"></th>
              <th>标签名称</th>
              <th>标签状态</th>
              <th>排序</th>
              <th>创建时间</th>
              <th>修改时间</th>
              <th width="150">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${pageBean.list}" var="grouplabel" varStatus="i">
            <tr>
              <td><input type="checkbox" name="groupLabelId" value="${grouplabel.groupLabelId}"></td>
              <td>${grouplabel.groupLabelName}</td>
              <td>
                <c:if test="${grouplabel.groupLabelStatus ==0}">
                  <span class="label label-success">正常</span>
                </c:if>
                <c:if test="${grouplabel.groupLabelStatus ==1}">
                  <span class="label label-default">停用</span>
                </c:if>
                 </td>
              <td>${grouplabel.groupLabelSort}</td>
              <td>
                <fmt:formatDate value="${grouplabel.groupLabelCreateTime}" pattern="yyyy-MM-dd HH:mm:ss" var="createTime"/>
                  ${createTime}
              </td>
              <td>
                <fmt:formatDate value="${grouplabel.groupLabelModifyTime}" pattern="yyyy-MM-dd HH:mm:ss" var="modifyTime"/>
                  ${modifyTime}
              </td>
              <td>
                <div class="btn-group">
                  <button type="button" class="btn btn-default" onclick="labelEdit('${grouplabel.groupLabelId}');">编辑</button>
                  <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                    <span class="caret"></span>
                    <span class="sr-only">Toggle Dropdown</span>
                  </button>
                  <ul class="dropdown-menu" role="menu">
                    <li><a href="javascript:;" onclick="stopgroupOne('unuselabel.htm','labellist.htm','${grouplabel.groupLabelId}');">停用</a></li>
                    <li><a href="javascript:;" onclick="recoverygroupOne('recoverylabel.htm','labellist.htm','${grouplabel.groupLabelId}');">恢复</a></li>
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
<div class="modal fade" id="addGroupLabel"  role="dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="updateTitle">添加小组标签</h4>
      </div>
      <div class="modal-body">
        <form class="form-horizontal" id="addForm" method="post">
          <input type="hidden" name="groupLabelId" id="grouplabelId"/>
          <input id="oldgrouplabelName" type="hidden" />
          <div class="form-group">
            <label class="control-label col-sm-5"><span class="text-danger">*</span>标签名称：</label>
            <div class="col-sm-1"></div>
            <div class="col-sm-8">
              <input type="text" class="form-control required" name="groupLabelName" id="groupLabelName" />
            </div>
          </div>
          <div class="form-group">
            <label class="control-label col-sm-5"><span class="text-danger">*</span>排序：</label>
            <div class="col-sm-1"></div>
            <div class="col-sm-4">
              <input type="text" class="form-control required number" name="groupLabelSort" id="groupLabelSort" />
            </div>
            <div class="col-sm-3"></div>
          </div>
          <div class="form-group">
            <label class="control-label col-sm-5">状态：</label>
            <div class="col-sm-1"></div>
            <div class="col-sm-10">
              <label class="radio-inline">
                <input type="radio" id="status1" value="0" checked="checked" name="groupLabelStatus" /> 正常
              </label>
              <label class="radio-inline">
                <input type="radio" id="status2" value="1" name="groupLabelStatus" /> 停用
              </label>
            </div>
            <div class="col-sm-3"></div>
          </div>
          <div class="form-group">
            <label class="control-label col-sm-5">SEO标题：</label>
            <div class="col-sm-1"></div>
            <div class="col-sm-16">
              <input type="text" class="form-control" name="groupLabelSeoTitle" id="seotitle"/>
            </div>
          </div>
          <div class="form-group">
            <label class="control-label col-sm-5">SEO关键字：</label>
            <div class="col-sm-1"></div>
            <div class="col-sm-16">
              <input type="text" class="form-control" name="groupLabelSeoKeyword" id="seokeyword"/>
            </div>
          </div>
          <div class="form-group">
            <label class="control-label col-sm-5">SEO描述：</label>
            <div class="col-sm-1"></div>
            <div class="col-sm-16">
              <textarea class="form-control" rows="5" name="groupLabelSeoRemark" id="seodesc"></textarea>
            </div>
            <div class="col-sm-3"></div>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" onclick="groupLabelSub();">确定</button>
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
                  <img class="good_img" alt="" src="images/avatar.jpg" width="200">
                  <div class="container-fluid">
                    <div class="row">
                      <div class="col-sm-12">
                        <p>小组名称：	54454</p>
                      </div>
                      <div class="col-sm-12">
                        <p>小组上限：	2000</p>
                      </div>
                      <div class="col-sm-12">
                        <p>加入类型：	只有接受组员邀请才能加入小组</p>
                      </div>
                      <div class="col-sm-12">
                        <p>小组分类：	</p>
                      </div>
                      <div class="col-sm-12">
                        <p>类型：	私密</p>
                      </div>
                      <div class="col-sm-12">
                        <p>热门：	一般</p>
                      </div>
                      <div class="col-sm-12">
                        <p>创建时间：	2015-02-07 16:49:53</p>
                      </div>
                      <div class="col-sm-12">
                        <p>小组描述：	1、PAIStore支持PC版与微信版...</p>
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

<div class="advanced_search_cont none">
  <div class="advanced_search_form">
    <form class="form-horizontal">
      <div class="form-group">
        <label class="control-label col-sm-7">小组名称：</label>
        <div class="col-sm-15">
          <input type="text" class="form-control">
        </div>
      </div>
      <div class="form-group">
        <label class="control-label col-sm-7">小组分类：</label>
        <div class="col-sm-15">
          <select class="form-control">
            <option>美食</option>
            <option>旅游</option>
            <option>运动</option>
          </select>
        </div>
      </div>
      <div class="form-group">
        <label class="control-label col-sm-7">起始时间：</label>
        <div class="col-sm-15">
          <input type="text" value="" class="form-control" placeholder="yyyy-mm-dd">
        </div>
      </div>
      <div class="form-group">
        <label class="control-label col-sm-7">结束时间：</label>
        <div class="col-sm-15">
          <input type="text" value="" class="form-control" placeholder="yyyy-mm-dd">
        </div>
      </div>
      <div class="form-group">
        <label class="control-label col-sm-7">类型：</label>
        <div class="col-sm-15">
          <label class="radio-inline">
            <input type="radio" name="yanzheng1" value="option1"> 公开
          </label>
          <label class="radio-inline">
            <input type="radio" name="yanzheng1" value="option2"> 私密
          </label>
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
<script src="<%=basePath %>js/bootstrap.min.js"></script>
<script src="<%=basePath %>js/summernote.min.js"></script>
<script src="<%=basePath %>js/language/summernote-zh-CN.js"></script>
<script src="<%=basePath %>js/bootstrap-select.min.js"></script>
<script src="<%=basePath %>js/bootstrap-datetimepicker.min.js"></script>
<script src="<%=basePath %>js/language/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="<%=basePath %>js/common.js"></script>
<script src="<%=basePath %>js/social/grouplist.js"></script>
<script src="<%=basePath %>js/social/grouplabel.js"></script>
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
