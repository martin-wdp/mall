<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath%>css/font-awesome.min.css">
    <link href="<%=basePath%>iconfont/iconfont.css" rel="stylesheet">
    <link href="<%=basePath%>css/summernote.css" rel="stylesheet">
    <link href="<%=basePath%>css/bootstrap-select.min.css" rel="stylesheet">
    <link href="<%=basePath%>css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <link href="<%=basePath%>css/style.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
    <!-- 引用头 -->
	<jsp:include page="../../page/header.jsp"></jsp:include>
    <div class="container-fluid page_body">
      <div class="row">
        <jsp:include page="../../page/left.jsp"></jsp:include>
        <div class="col-lg-20 col-md-19 col-sm-18 main">
          
          <div class="main_cont">
            <jsp:include page="../../page/breadcrumbs.jsp"></jsp:include>

            <h2 class="main_title">${pageNameChild} <small>(共${pb.rows }条)</small></h2>

            <div class="common_data p20">

              <div class="data_ctrl_area mb20">
                <div class="data_ctrl_search pull-right"></div>
                <div class="data_ctrl_brns pull-left">
                  <button type="button" class="btn btn-info" onclick="addmodal()">
                    <i class="glyphicon glyphicon-plus"></i> 添加
                  </button>
                  <button type="button" class="btn btn-info" onclick="delsupportsYN()">
                    <i class="glyphicon glyphicon-trash"></i> 删除
                  </button>
                </div>
                <div class="clr"></div>
              </div>

              <table class="table table-striped table-hover">
                <thead>
                <tr>
                  <th><input type="checkbox" name="id" onclick="allunchecked(this,'id');"></th>
                  <th>图片</th>
                  <th>服务支持名称</th>
                  <th>帮助标题</th>
                  <th class="w100">创建时间</th>
                  <th width="150">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="ser" items="${pb.list }" varStatus="status">
                <tr>
                  <td><input type="checkbox" name="id"  value="${ser.id }"></td>
                  <td><img alt="" src="${ser.serviceImageUrl }" height="30"/></td>
                  <td style="text-align:left;">${ser.serviceName }</td>
                  <td>
                  	<c:forEach items="${helpList }" var="help">
		        		<c:if test="${help.helpId == ser.helpId }">${help.helpTitle }</c:if>
		        	</c:forEach>
                  </td>
                  <td>
                 	<fmt:formatDate value="${ser.createTime }" var="cdate" type="both"/>
					${cdate }
					</td>
                  <td>
                    <div class="btn-group">
                      <button type="button" class="btn btn-default" onclick="upmodal(${ser.id })">编辑</button>
                      <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                        <span class="caret"></span>
                        <span class="sr-only">Toggle Dropdown</span>
                      </button>
                      <ul class="dropdown-menu" role="menu">
                        <li><a href="#" onclick="delsupportYN(${ser.id });">删除</a></li>
                      </ul>
                    </div>
                  </td>
                </tr>
                </c:forEach>
                </tbody>
              </table>

              <c:import url="../../page/searchPag.jsp">
				     <c:param name="pageBean" value="${pb }"/>
				     <c:param name="path" value="../"></c:param>
				</c:import>

            </div>

          </div>
        </div>
      </div>
    </div>

    <!-- Modal -->
    <div class="modal fade" id="addSupport"  role="dialog">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
            <h4 class="modal-title">添加服务支持</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal" id="showserviceSupportForm" action="newServiceSupport.htm?CSRFToken=${token}" method="post" enctype="multipart/form-data">
              <div class="form-group">
                <label class="control-label col-sm-5"><span class="text-danger">*</span>服务支持名称：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-12">
                  <input type="text" class="form-control required" name="serviceName" id="serviceName">
                </div>
                <div class="col-sm-3"></div>
              </div>
              <div class="form-group">
                <label class="control-label col-sm-5">服务支持图标：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-12">
                  <p class="pt5"><input type="file" name="picFile" id="picFile"></p>
                    <input type="hidden" name="serviceImageUrl" id="serviceImageUrl">
                    <iframe id="uploadFrame" name="uploadFrame" style="display:none;"></iframe>
                </div>
                <div class="col-sm-3">
                    <a href="javascript:;" class="serviceimg help_tips" data-original-title="" title="">
                        <i class="icon iconfont"></i>
                    </a>
           		</div>
              </div>
              <div class="form-group">
                <label class="control-label col-sm-5">预览图片：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-12">
                  <img alt="" id="imgView" src="" width="80">
                </div>
              </div>
              <div class="form-group">
                <label class="control-label col-sm-5">帮助：</label>
                <div class="col-sm-1"></div>
                <div class="col-sm-6">
                  <select class="form-control"  name="helpId" id="helpId">
                      <c:forEach items="${helpList}" var="help" varStatus="">
                          <option value="${help.helpId}" >${help.helpTitle }</option>
                      </c:forEach>
                  </select>
                </div>
                <div class="col-sm-3"></div>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-primary" onclick="addsupport()">确定</button>
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
          </div>
        </div>
      </div>
    </div>

    <!-- Modal -->
    <div class="modal fade" id="upSupport"  role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title">修改服务支持</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" id="upshowserviceSupportForm" action="updateServiceSupport.htm?CSRFToken=${token}" method="post" enctype="multipart/form-data">
                        <div class="form-group">
                            <input type="hidden" name="id" id="up_id" value=""/>
                            <label class="control-label col-sm-5"><span class="text-danger">*</span>服务支持名称：</label>
                            <div class="col-sm-1"></div>
                            <div class="col-sm-12">
                                <input type="text" class="form-control required" name="serviceName" id="up_serviceName">
                            </div>
                            <div class="col-sm-3"></div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-5">服务支持图标：</label>
                            <div class="col-sm-1"></div>
                            <div class="col-sm-12">
                                <p class="pt5"><input type="file" name="picFile" id="up_picFile"></p>
                                <input type="hidden" name="serviceImageUrl" id="up_serviceImageUrl">
                            </div>
                            <div class="col-sm-3">
			                    <a href="javascript:;" class="serviceimg help_tips" data-original-title="" title="">
			                        <i class="icon iconfont"></i>
			                    </a>
			           		</div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-5">预览图片：</label>
                            <div class="col-sm-1"></div>
                            <div class="col-sm-12">
                                <img alt="" src=""   width="80" id="img_up">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-5">帮助：</label>
                            <div class="col-sm-1"></div>
                            <div class="col-sm-6">
                                <select class="form-control"  name="helpId" id="up_helpId">
                                    <c:forEach items="${helpList}" var="help" varStatus="">
                                    <option value="${help.helpId}" >${help.helpTitle }</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-sm-3"></div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" onclick="upsupport()">确定</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </div>
        </div>
    </div>

	<!-- Modal -->
    <div class="modal fade" id="delsupport"  role="dialog">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
            <h4 class="modal-title">操作提示</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
              <input type="hidden" id="delserid" />
              <div class="form-group">
                <div class="col-sm-1"></div>
                <div class="col-sm-10">
                  <p>
                		你确定要删除吗？
					</p>
                </div>
              </div>

            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-primary" onclick="delsupport();">确定</button>
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
          </div>
        </div>
      </div>
    </div>

	<!-- Modal -->
    <div class="modal fade" id="delsupports"  role="dialog">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
            <h4 class="modal-title">操作提示</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
              <div class="form-group">
                <div class="col-sm-1"></div>
                <div class="col-sm-10">
                  <p>
                		你确定要删除吗？
					</p>
                </div>
              </div>

            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-primary" onclick="delsupports();">确定</button>
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
          </div>
        </div>
      </div>
    </div>
	
	<input type="hidden" value="supportform" id="formId"/>
    <input type="hidden" value="selectServiceSupportList.htm" id="formName"/>
    <form role="form"  method="post" action="selectServiceSupportList.htm" id="supportform">
         
    </form>
	<input type="hidden" name="CSRFToken" id="CSRFToken" value="${token}">
    <input type="hidden"  id="basePath" value="${basePath}">
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<%=basePath %>js/common/common_alert.js"></script>
    <script src="<%=basePath%>js/bootstrap.min.js"></script>
    <script src="<%=basePath%>js/summernote.min.js"></script>
    <script src="<%=basePath%>js/language/summernote-zh-CN.js"></script>
    <script src="<%=basePath%>js/bootstrap-select.min.js"></script>
    <script src="<%=basePath%>js/bootstrap-datetimepicker.min.js"></script>
    <script src="<%=basePath%>js/language/bootstrap-datetimepicker.zh-CN.js"></script>
    <script src="<%=basePath%>js/system/serviceSupport_List.js"></script>
    <script src="<%=basePath%>js/common.js"></script>
    <script src="<%=basePath %>js/common/preview_image.js"></script>
    <script src="<%=basePath %>js/common/common_checked.js"></script>
  </body>
</html>
