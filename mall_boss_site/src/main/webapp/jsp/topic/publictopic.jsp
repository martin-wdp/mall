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
    <input type="hidden" value="publictopicList" id="formId"/>
    <input type="hidden" value="publictopic.htm" id="formName"/>
    <div class="col-lg-20 col-md-19 col-sm-18 main">
      <div class="main_cont">
        <jsp:include page="../page/breadcrumbs.jsp"></jsp:include>

        <h2 class="main_title">${pageNameChild}<small>(共${pb.rows }条)</small></h2>

        <div class="common_data p20">
          <div class="filter_area">
            <form role="form" id="advancedForm" class="form-inline" action="publictopic.htm" method="post">
              <input name="pageNo" type="hidden" id="advancedpageNo"/>
              <input name="pageSize" type="hidden" id="advancedpageSize"/>
              <div class="form-group">
                <div class="input-group">
                  <span class="input-group-addon">话题名称</span>
                  <input type="text" class="form-control" name="topicTitle" value="${tp.topicTitle }"/>
                </div>
              </div>
              <div class="form-group">
                <div class="input-group">
                  <span class="input-group-addon">发表人</span>
                  <input type="text" class="form-control" name="customerName" value="${tp.customerName }"/>
                </div>
              </div>
              <div class="form-group">
                <div class="input-group">
                  <span class="input-group-addon">浏览数</span>
                  <input type="text" class="form-control" name="topicHot" value="${tp.topicHot }"/>
                </div>
              </div>
              <div class="form-group">
                <div class="input-group">
                  <span class="input-group-addon">精华</span>
                  <select class="cate_selector w150"  data-live-search="true" name="topicEssence" id="topicEssence">
                    <option value="" selected="selected">全部</option>
                    <option value="0">否</option>
                    <option value="1">是</option>
                  </select>
                </div>
              </div>
              <div class="form-group">
                <div class="input-group">
                  <span class="input-group-addon">热帖</span>
                  <select class="cate_selector w150"  data-live-search="true" name="topicFever" id="topicFever">
                    <option value="" selected="selected">全部</option>
                    <option value="0">否</option>
                    <option value="1">是</option>
                  </select>
                </div>
              </div>
              <div class="form-group">
                <div class="input-group">
                  <span class="input-group-addon">置顶</span>
                  <select class="cate_selector w150"  data-live-search="true" name="topicTopView" id="topicTopView">
                    <option value="" selected="selected">全部</option>
                    <option value="0">否</option>
                    <option value="1">一般置顶</option>
                    <option value="2">特别置顶</option>
                  </select>
                </div>
              </div>

              <div class="form-group">
                <button type="button" class="btn btn-primary" onclick="advancesearchtopic();">搜索</button>
              </div>
            </form>
          </div>
          <div class="data_ctrl_area mb20">
            <div class="data_ctrl_search pull-right"></div>
            <div class="data_ctrl_brns pull-left">
              <button type="button" class="btn btn-info" onclick="showDeleteBatchConfirmAlerts('topicId','确认要删除此话题？')">
                <i class="glyphicon glyphicon-trash"></i> 删除
              </button>
            </div>
            <div class="clr"></div>
          </div>
          <form action="" method="post" id="publictopicList">
          <table class="table table-striped table-hover">
            <thead>
            <tr>
              <th width="10"><input type="checkbox" onclick="allunchecked(this,'topicId')"/></th>
              <th>话题标题</th>
              <th>发表人</th>
              <th>发表组</th>
              <th>浏览数</th>
              <th>精华</th>
              <th>热帖</th>
              <th>置顶</th> 
              <th class="w100">创建时间</th>
              <th width="150">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${pb.list}" var="topic" varStatus="i">
            <tr>
              <td><input type="checkbox" name="topicId" value="${topic.topicId}"/></td>
              <td style="text-align:left;"  title="${topic.topicTitle}">
                <c:if test="${fn:length(topic.topicTitle)>20 }">
                  ${fn:substring(topic.topicTitle,0,19) }...
                </c:if>
                <c:if test="${fn:length(topic.topicTitle)<=20 }">
                  ${topic.topicTitle}
                </c:if>
              </td>
              <td>
                ${topic.customerName}
              </td>
              <td>
                ${topic.groupName}
              </td>
              <td>${topic.topicHot}</td>
              <td>
                <c:if test="${topic.topicEssence==0}">
                  <span class="label label-default">否</span>
                </c:if>
                <c:if test="${topic.topicEssence==1}">
                  <span class="label label-success">是</span>
                </c:if>
              </td>
              <td>
                <c:if test="${topic.topicFever ==0}">
                  <span class="label label-default">否</span>
                </c:if>
                <c:if test="${topic.topicFever ==1}">
                  <span class="label label-success">是</span>
                </c:if>
              </td>
              <td>
              <c:if test="${topic.topicTopView ==0}">
                <span class="label label-default">否</span>
              </c:if>
              <c:if test="${topic.topicTopView  ==1}">
                <span class="label label-default">一般置顶</span>
              </c:if>
              <c:if test="${topic.topicTopView  ==2}">
                <span class="label label-success">特别置顶</span>
              </c:if>
              </td> 
              <td>
                <fmt:formatDate value="${topic.topicCreateTime }" pattern="yyyy-MM-dd HH:mm:ss" var="createTime" />
                ${createTime}
              </td>
              <td>
                <div class="btn-group">
                  <button type="button" class="btn btn-default" onclick="topicEdit('${topic.topicId}')">编辑</button>
                  <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                    <span class="caret"></span>
                    <span class="sr-only">Toggle Dropdown</span>
                  </button>
                  <ul class="dropdown-menu" role="menu">
                    <input type="hidden" id="topic_content" value="${topic.topicContent}"/>
                    <li><a href="javascript:;" onclick="topicDetail('${topic.topicContent}');">查看详情</a></li>
                    <li><a href="javascript:;" onclick="deleteMultiWinOne('deletegrouptopic.htm','publictopic.htm','${topic.topicId}');">删除</a></li>
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
        <h4 class="modal-title">添加小组标签</h4>
      </div>
      <div class="modal-body">
        <form class="form-horizontal">
          <div class="form-group">
            <label class="control-label col-sm-5">标签名称：</label>
            <div class="col-sm-1"></div>
            <div class="col-sm-8">
              <input type="text" class="form-control">
            </div>
          </div>
          <div class="form-group">
            <label class="control-label col-sm-5">排序：</label>
            <div class="col-sm-1"></div>
            <div class="col-sm-4">
              <input type="text" class="form-control">
            </div>
            <div class="col-sm-3"></div>
          </div>
          <div class="form-group">
            <label class="control-label col-sm-5">状态：</label>
            <div class="col-sm-1"></div>
            <div class="col-sm-10">
              <label class="radio-inline">
                <input type="radio" name="radio1"> 正常
              </label>
              <label class="radio-inline">
                <input type="radio" name="radio1"> 停用
              </label>
            </div>
            <div class="col-sm-3"></div>
          </div>
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
            <label class="control-label col-sm-5">SEO描述：</label>
            <div class="col-sm-1"></div>
            <div class="col-sm-16">
              <textarea class="form-control" rows="5"></textarea>
            </div>
            <div class="col-sm-3"></div>
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

<!-- 编辑话题start -->
<div class="modal fade" id="editTopic"  role="dialog">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">设置话题</h4>
      </div>
      <div class="modal-body">
        <form class="form-horizontal" id="editTopicForm" action="savegrouptopic.htm" method="post">
          <input type="hidden" name="topicId" id="topicId"/>
          <div class="form-group">
            <label class="control-label col-sm-5"><span class="text-danger">*</span>话题标题：</label>
            <div class="col-sm-1"></div>
            <div class="col-sm-12">
              <input type="text" class="form-control required" name="topicTitle" id="topicTitle">
            </div>
          </div>
          <div class="form-group">
            <label class="control-label col-sm-5">发表人：</label>
            <div class="col-sm-1"></div>
            <div class="col-sm-12">
              <label class="control-label" name="customerName" id="customerName"></label>
            </div>
          </div>
          <div class="form-group">
              <label class="control-label col-sm-5">发表小组：</label>
              <div class="col-sm-1"></div>
              <div class="col-sm-12">
              <label class="control-label" name="groupName" id="groupName"></label>
            </div>
          </div>
          <div class="form-group">
            <label class="control-label col-sm-5">话题内容：</label>
            <div class="col-sm-1"></div>
            <div class="col-sm-16">
              <div class="summernote" name="topicContent" id="topicContents"></div>
            </div>
          </div>
          <div class="form-group">
            <label class="control-label col-sm-5">加入精华：</label>
            <div class="col-sm-1"></div>
            <div class="col-sm-16">
              <label class="radio-inline">
                <input type="radio" name="topicEssence" value="0" id="topicEssence1"> 否
              </label>
              <label class="radio-inline">
                <input type="radio" name="topicEssence" value="1" checked="checked" id="topicEssence2"> 是
              </label>
            </div>
          </div>
          <div class="form-group">
            <label class="control-label col-sm-5">加入热帖：</label>
            <div class="col-sm-1"></div>
            <div class="col-sm-16">
              <label class="radio-inline">
                <input type="radio" name="topicFever" id="topicFever1" value="0"> 否
              </label>
              <label class="radio-inline">
                <input type="radio" name="topicFever" id="topicFever2" value="1" checked="checked"> 是
              </label>
            </div>
          </div>
          <div class="form-group">
            <label class="control-label col-sm-5">置顶：</label>
            <div class="col-sm-1"></div>
            <div class="col-sm-16">
              <label class="radio-inline">
                <input type="radio" name="topicTopView" id="topicTopView1" value="0"> 否
              </label>
              <label class="radio-inline">
                <input type="radio" name="topicTopView" id="topicTopView2" value="1"> 一般置顶
              </label>
              <label class="radio-inline">
                <input type="radio" name="topicTopView" id="topicTopView3" value="2" checked="checked"> 特别置顶
              </label>
            </div>
          </div>
          <div class="form-group">
            <label class="control-label col-sm-5">SEO标题：</label>
            <div class="col-sm-1"></div>
            <div class="col-sm-12">
              <input type="text" class="form-control" name="topicSeoTitle" id="topicSeoTitle">
            </div>
          </div>
          <div class="form-group">
            <label class="control-label col-sm-5">SEO关键字：</label>
            <div class="col-sm-1"></div>
            <div class="col-sm-12">
              <input type="text" class="form-control" name="topicSeoKeyword" id="topicSeoKeyword">
            </div>
          </div>
          <div class="form-group">
            <label class="control-label col-sm-5">SEO描述：</label>
            <div class="col-sm-1"></div>
            <div class="col-sm-12">
              <textarea class="form-control" rows="5" name="topicSeoDescription" id="topicSeoDescription"></textarea>
            </div>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary"  onclick="editTopic();">确定</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
      </div>
    </div>
  </div>
</div>
<!-- 编辑话题end -->

<!-- 查看话题详情start -->
<div class="modal fade" id="scanTopic"  role="dialog">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">查看话题详情</h4>
      </div>
      <div class="modal-body">
        <div class="common_info common_tabs mt20">
          <ul class="nav nav-tabs" role="tablist">
            <li role="presentation" class="active"><a href="#atab1" aria-controls="tab1" role="tab" data-toggle="tab">话题原文</a></li>
          </ul>
          <div class="tab-content">
            <div role="tabpanel" class="tab-pane active" id="atab1">
              <p id="topicContent"></p>
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
<!-- 查看话题详情end -->

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
        <label class="control-label col-sm-7">标题：</label>
        <div class="col-sm-15">
          <input type="text" class="form-control">
        </div>
      </div>
      <div class="form-group">
        <label class="control-label col-sm-7">内容：</label>
        <div class="col-sm-15">
          <input type="text" class="form-control">
        </div>
      </div>
      <div class="form-group">
        <label class="control-label col-sm-7">发表人：</label>
        <div class="col-sm-15">
          <input type="text" class="form-control">
        </div>
      </div>
      <div class="form-group">
        <label class="control-label col-sm-7">浏览数：</label>
        <div class="col-sm-15">
          <input type="text" class="form-control">
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
        <label class="control-label col-sm-7">推荐：</label>
        <div class="col-sm-15">
          <select class="form-control">
            <option>全部</option>
            <option>是</option>
            <option>否</option>
          </select>
        </div>
      </div>
      <div class="form-group">
        <label class="control-label col-sm-7">精华：</label>
        <div class="col-sm-15">
          <select class="form-control">
            <option>全部</option>
            <option>是</option>
            <option>否</option>
          </select>
        </div>
      </div>
      <div class="form-group">
        <label class="control-label col-sm-7">热帖：</label>
        <div class="col-sm-15">
          <select class="form-control">
            <option>全部</option>
            <option>是</option>
            <option>否</option>
          </select>
        </div>
      </div>
      <div class="form-group">
        <label class="control-label col-sm-7">置顶：</label>
        <div class="col-sm-15">
          <select class="form-control">
            <option>全部</option>
            <option>是</option>
            <option>否</option>
          </select>
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
<script src="<%=basePath %>js/topic/topiclist.js"></script>
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
