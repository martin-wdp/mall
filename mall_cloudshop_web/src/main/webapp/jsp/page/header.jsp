    
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String httpPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
    <link rel="stylesheet" href="<%=basePath%>css/font-awesome.min.css">
    <link href="<%=basePath%>iconfont/iconfont.css" rel="stylesheet">
    <style>
    .ctrl_nav li>a>i {
		 margin:0 auto 10px; 
		}
    .modal-dialog{
    	margin:90px auto;
    }
            .modal-article {padding: 0 10px; height: 400px; overflow-y: scroll;}
      .modal-article p {line-height: 180%; font-size: 16px; position: relative; padding-left: 50px;}
      .modal-article p em {font-style: normal; position: absolute; top: 0; left: 0;}
      .modal-article img {display: block; width: 100%; margin: 10px auto 20px;}
      h1 {
  font-size: 18px;
  font-weight: normal;
}
      .shop-guide-wrapper {
  width: 780px;
  margin: 0 auto;
}
.shop-guide-wrapper .mtitle, .shop-guide-wrapper .mbody {
  border: 1px solid #ECECEC;
}
.shop-guide-wrapper .mtitle {
  height: 85px;
  background-color: #F8F8F8;
  text-align: center;
  border-radius: 5px 5px 0 0;
  box-shadow: inset 0 0 10px #ECECEC;
}
.shop-guide-wrapper .mbody {
  height: 332px;
  border-top: 0;
  border-radius: 0 0 5px 5px;
  position: relative;
}

.shop-guide-wrapper .stepInsts {
  width: 770px;
  padding-top: 18px;
  margin: 0 auto;
}
.shop-guide-wrapper .stepInsts .stepShow {
  height: 25px;
  background-image: url(images/guide-step-sprite.png);
  background-repeat: no-repeat;
}
.shop-guide-wrapper .stepInsts ul {
 *zoom: 1;
 margin-left:97px;
}
.shop-guide-wrapper .stepInsts ul:before, .shop-guide-wrapper .stepInsts ul:after {
  display: table;
  content: "";
  line-height: 0;
}
.shop-guide-wrapper .stepInsts ul:after {
  clear: both;
}
.shop-guide-wrapper .stepInsts ul li {
  width: 96px;
  height: 18px;
  padding: 9px 0;
  font-size: 14px;
  color: #68676C;
  line-height: 18px;
  text-align: center;
  float: left;
}
.shop-guide-wrapper .stepInsts ul li.past {
  color: #333;
}
.shop-guide-wrapper .stepInsts ul li.finish {
  color: #C00;
}
.shop-guide-wrapper .step-0 .stepShow {
  background-position: center 0;
}
.shop-guide-wrapper .step-1 .stepShow {
  background-position: center -25px;
}
.shop-guide-wrapper .step-2 .stepShow {
  background-position: center -50px;
}
.shop-guide-wrapper .step-3 .stepShow {
  background-position: center -75px;
}
.shop-guide-wrapper .step-4 .stepShow {
  background-position: center -100px;
}
.shop-guide-wrapper .step-5 .stepShow {
  background-position: center -125px;
}
.shop-guide-wrapper .step-6 .stepShow {
  background-position: center -150px;
}
.shop-guide-wrapper .step-7 .stepShow {
  background-position: center -175px;
}
.shop-guide-wrapper .step-8 .stepShow {
  background-position: center -200px;
}
.shop-guide-wrapper .center-btn-wrapper {
  text-align: center;
  margin-top: 50px;
}
.shop-guide-wrapper .corner-btn-wrapper {
  position: absolute;
  right: 45px;
  bottom: 20px;
}
.shop-guide-wrapper .inner-content {
  padding: 15px 45px 0;
}
.shop-guide-wrapper .inner-content .content {
  padding-left: 70px;
  height: 178px;
  overflow-y: auto;
}
.shop-guide-wrapper .centered-content {
  padding: 60px 0 0;
  text-align: center;
}
.shop-guide-wrapper .mtitle h1 {
  text-align: center;
  margin: 30px 0 0;
}
.shop-guide-wrapper .mbody .stepInsts {
  padding-top: 44px;
}
    
    </style>
    
    <div class="page_top container-fluid Noprint">
      <div class="row">
        <div class="col-lg-4 col-md-5 col-sm-6">
          <h1 class="logo"><a href="<%=httpPath%>boss/index.htm"><img alt="" width="75%" src="${indexLogo }" id="logo"></a></h1>
        </div>
        <div class="col-lg-20 col-md-19 col-sm-18">
          <div class="top_banner">
            <div class="ctrl_banner">
              <ul class="ctrl_nav pull-right">
              
              
              <li>
                  <a href="<%=httpPath%>boss/index.htm">
                    <i class="icon icon-white icon-home"></i>&nbsp;桌面
                  </a>
                </li>
    <c:if test="${managerFlag=='1' }">
             <li>
                    <a href="javascript:void(0);" onclick="appMarket()">
                        <i class="icon icon-white icon-home"></i>&nbsp;应用市场
                    </a>
                </li>
    </c:if>
                <%--<li>
                    <a href="appBoss.htm?CSRFToken=${token}">
                        <i class="icon icon-white icon-home"></i>&nbsp;应用管理
                    </a>
                </li>--%>
                
                <li>
                  <a href="javascript:;">
                    <i class="icon icon-white icon-dashboard"></i>&nbsp;控制台
                  </a>
                  <div class="popover bottom">
                    <div class="arrow"></div>
                    <div class="popover-content">
                      <ul class="web_preview">
                       <c:if test="${managerFlag=='1' }">
                         <li class="control_panel">
                          <a href="<%=httpPath %>boss/initSetting.htm">
                            <i></i>
                            <span>控制面板</span>
                          </a>
                        </li>
                        </c:if>
                        <li class="change_info">
                          <a href="javascript:modifyInfo();">
                            <i></i>
                            <span>修改资料</span>
                          </a>
                        </li>
                        <li class="change_password">
                          <a href="javascript:modifyPwd();">
                            <i></i>
                            <span>密码修改</span>
                          </a>
                        </li>
                      </ul>
                    </div>
                  </div>
                </li>
                <li>
                  <a href="javascript:;" class="">
                    <i class="icon icon-white icon-envelope"></i>&nbsp;站内信
                  </a>
                   <div class="popover bottom">
                    <div class="arrow"></div>
                    <div class="popover-content">
                      <p id="messagecount">没有未读新消息</p>
                      <div class="message_list">
                       
                      </div>
                    </div>
                  </div>
                </li>
                <li>
                  <a href="javascript:;">
                    <i class="icon icon-white icon-eye-open"></i>&nbsp;预览站点
                  </a>
                  <div class="popover bottom" style="min-width:183px;">
                    <div class="arrow"></div>
                    <div class="popover-content">
                      <ul class="web_preview">
                        <li class="shop_preview" style="width:43%">
                          <a href="javascript:;" target="_blank" id="link">
                            <i></i>
                            <span>商城预览</span>
                          </a>
                        </li>    
                        <!--<li class="business_preview" >
                          <a href="javascript:;"  target="_blank" id="thirdlink">
                            <i></i>
                            <span>商家预览</span>
                          </a>
                        </li>-->
                        <li class="weixin_preview" style="width:43%">
                            <i style="margin:0 auto 10px auto"><div id="erweima"></div></i>
                            <span>微商城预览</span>
                        </li>
                      </ul>
                    </div>
                  </div>
                </li>
                <li>
                  <a href="javascript:void(0);" onclick="$('#kdxd').modal('show')">
                    <i class="icon icon-white icon-question-sign"></i>&nbsp;向导
                  </a>
                </li>
                <li>
                   <a href="javascript:;" onclick="$('#feedback').modal('show')">
                    <i class="icon icon-white icon-edit"></i>&nbsp;反馈
                  </a>
                </li>
                <li>
                  <a href="http://admin.qianmi.com" target="_blank">
                    <i class="icon iconfont">&#xe61d;</i>&nbsp;进入E生活
                  </a>
                </li>
                <li>
                  <a href="http://www.gonghuo.com" target="_blank">
                    <i class="icon icon-white icon-inbox"></i>&nbsp;进入供货网
                  </a>
                </li>
                 
                <li>
                   <a href="javascript:void(0);" onclick="goOut();">
                    <i class="icon icon-white icon-off"></i>&nbsp;退出
                  </a>
                </li>
                
              
              
              
              
              
              
              
              
          <%--     
                <li>
                  <a href="javascript:void(0);" style="width:80px;font-size:12px;" onclick="$('#kdxd').modal('show')">
                    <span class="glyphicon" style="font-weight:bold">开店向导</span>
                  </a>
                </li>
                <li>
                  <a href="index.htm">
                    <span class="glyphicon glyphicon-home"></span>
                  </a>
                </li>
                <li>
                  <a href="javascript:;" class="message">
                    <span class="glyphicon glyphicon-envelope"></span> <span class="badge">0</span>
                  </a>
                </li>
                <li>
                  <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown">
                    <span class="glyphicon glyphicon-cog"></span>
                  </a>
                  <div class="dropdown-menu" role="menu">
                    <div class="arrow"></div>
                    <c:if test="${managerFlag=='1' }"><p><a href="<%=basePath %>initSetting.htm"><i class="glyphicon glyphicon-dashboard"></i> 控制面板</a></p></c:if>
                    <p><a href="javascript:modifyInfo();"><i class="glyphicon glyphicon-user"></i> 修改资料</a></p>
                    <p><a href="javascript:modifyPwd();"><i class="glyphicon glyphicon-lock"></i> 密码修改</a></p>
                  </div>
                </li>
               
                <li>
                  <a href="javascript:;" onclick="$('#feedback').modal('show')">
                    <span class="glyphicon glyphicon-edit"></span>
                  </a>
                </li>
                <li>
                  <a href="javascript:void(0);" onclick="goOut();">
                    <span class="glyphicon glyphicon-off"></span>
                  </a>
                </li> --%>
              </ul>
              <div class="pull-left">
                <p class="version"><a href="initversion.htm">标准版（2.0 - Release）</a> </p>
              </div>
            </div>

            <div class="nav_area">
              <div class="user_data pull-right">
                <ul>
                  <li>
                      <a href="javascript:modifyInfo();">
                          <c:if test="${empty photoImg }">
                              <img class="vm mr10" id="m_img" alt="" src="<%=basePath %>images/avatar.jpg" 
                                   height="25" style="margin-top:-3px;"/>
                          </c:if>
                          <c:if test="${not empty photoImg }">
                              <img class="vm mr10" id="m_img" alt="" src="${photoImg }" height="25"
                                   style="margin-top:-3px;"/>
                          </c:if>
                          <font size="4">&nbsp;&nbsp;<small>${name }</small></font>

                      </a>
                  </li>
                </ul>
              </div>
              <nav class="nav pull-left">
                <ul>
                 <c:if test="${menus!=null }">
                 <!-- 获取全部菜单 -->
	                <c:forEach items="${menus}" var="nav">
	                	<!-- 判断菜单不是全部 全部为null -->
	                   <c:if test="${nav.parentId ==null&&nav.menuVos!=null}">
	                   	<c:forEach  items="${nav.menuVos}" var="navs">
	                   		    <c:if test="${navs.parentId==0}"> 
	                   		       <c:set var="tt" value=""></c:set>
	                   		    	  <c:forEach  items="${navs.menuVos}" var="navss" varStatus="i">
		                   		   		<c:if test="${i.index==0 }">
				                   		  <c:forEach  items="${navss.menuVos}" var="navsss" varStatus="j">
					                   		   <c:if test="${j.index==0 }">
					                   		   <c:set var="tt" value="${navsss.bundleName}/${navsss.url }?menuId=${navs.id}&menuParentId=${navss.id}&myselfId=${navsss.id}"></c:set>
					                   		   </c:if>
				                   		  </c:forEach>
			                   		  	</c:if> 
		                   			</c:forEach> 
	                   		    
		                   		  <li class="<c:if test="${navs.id==menuId}">active</c:if>">
		                   		  <a  href="<%=httpPath%>${tt }">${navs.designation }</a></li>
		                   		  <c:if test="${navs.menuVos!=null}">
			                   		<div style="display:none;"> 
			                   		<c:forEach  items="${navs.menuVos}" var="navss">
			                   		  <div class="popover-content">
			                   		  	<div class="nav_sub">
			                   		  		<h4>${navss.designation }</h4>
			                   		  			<ul>
			                   		  			 <c:if test="${navss.menuVos!=null}">
			                   		  			 	<c:forEach  items="${navss.menuVos}" var="navsss">
			                   		  				<li><a href="<%=httpPath%>${navsss.bundleName}/${navsss.url }?menuId=${navs.id}&menuParentId=${navss.id}&myselfId=${navsss.id}">${navsss.designation }</a></li>
			                   		  				</c:forEach>
			                   		  			 </c:if>
			                   		  			</ul>
			                   		  		
				                   		  	</div>
			                   		  </div>
			                   		  </c:forEach>
			                   		   </div>
		                   		  </c:if>
		                   		</c:if>
	                   	</c:forEach>
	                 <%--  <li class="active"><a href="javascript:;">${nav.designation }</a></li> --%>
	                   </c:if>
	             
	                </c:forEach>
                </c:if>
                </ul>
              </nav>
            </div>
          </div>
        </div>
      </div>
    </div>
    
<div id="feedback" class="modal fade Noprint" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">用户反馈</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span class="text-danger">*</span>反馈内容：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-15">
                            <textarea class="form-control" rows="5" id="feedbackcontent" ></textarea>
                        </div>
                    </div>
					<div class="form-group">
					   <label class="control-label col-sm-5"></label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                             <p class="text-muted">限定输入250字</p>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="fankui();">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
    
    
       
<div id="goout" class="modal fade Noprint" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">退出系统</h4>
            </div>
               <form class="form-horizontal" method="post" action="login.htm">
		            <div class="modal-body">
		                    <div class="form-group">
		                        <div class="col-sm-2"></div>
		                        <div class="col-sm-7">
		                            <p class="text-muted">您确定要退出系统吗？</p>
		                        </div>
		                    </div>
		
		            </div>
		            <div class="modal-footer">
		                <input type="submit" class="btn btn-primary"></input>
		                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		            </div>
            	</form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->






<div id="dalog-send-sms" class="modal fade Noprint" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">密码验证</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="sendCodeForm" method="post">
                    <div class="form-group">
                        <label class="control-label col-sm-5">手机号码：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-5">
                            <label class="radio-inline" style="padding-left:0px;"> <span id="viewmobile"></span></label>
                        </div>
                         <div class="col-sm-1"></div>
                         <div class="col-sm-4"><input id="sendCodeButton" value="发送验证码" type="button" onclick="sendCode();"/></div>
                         <div class="col-sm-4"><label class="radio-inline"><span id="sendCodeButtonTip"></span></label></div>
                    </div>
                    
                     <div class="form-group">
                        <label class="control-label col-sm-5"><span class="text-danger">*</span>验证码：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-5">
                           <input class="form-control required" rows="5" id="mcode"></input>
                           <span id="errorCode"></span>
                        </div>
                    </div>
                    
                    
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="checkCode();">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
    
    
    
    
    


<div id="dalog-updatepass-sms" class="modal fade Noprint" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">修改密码</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal"  method="post" id="updatePassForm">
               		 <div class="form-group">
                        <label class="control-label col-sm-5"><span class="text-danger">*</span>原始密码：</label>
                    	 <div class="col-sm-1"></div>
	                        <div class="col-sm-10">
	                           <input class="form-control required" rows="5" name="userkey" id="userkey" type="password"></input><span id="oldpass"></span>
	                        </div>
                     </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span class="text-danger">*</span>新密码：</label>
                    	 <div class="col-sm-1"></div>
	                        <div class="col-sm-10">
	                           <input class="form-control required" rows="5" name="newuserkey" id="newuserkey" type="password"></input>
	                        </div>
                     </div>
                    
                     <div class="form-group">
                        <label class="control-label col-sm-5 required"><span class="text-danger">*</span>重复新密码：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                           <input class="form-control" rows="5"  equalTo="#newuserkey" name="renewuserkey" id="renewuserkey" type="password"></input>
                        </div>
                    </div>
                    
                    
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="updatepass();">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->






<div id="dalog-checkPass-sms" class="modal fade Noprint" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">密码验证</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal"  method="post" id="checkPassForm">
               		 <div class="form-group">
                        <label class="control-label col-sm-5"><span class="text-danger">*</span>原始密码：</label>
                    	 <div class="col-sm-1"></div>
	                        <div class="col-sm-10">
	                           <input class="form-control required" rows="5"  id="userkeyold" type="password"></input><span id="oldpassuse"></span>
	                        </div>
                     </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="checkPass();">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->






<div id="dalog-Info-sms" class="modal fade Noprint" role="dialog" style="z-index:1700;" >
    <div class="modal-dialog" style="z-index:1800;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">修改资料</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal"  method="post" id="InfoForm" action="modifymanager.htm?CSRFToken=${token }" enctype="multipart/form-data">
               		 <div class="form-group">
                        <label class="control-label col-sm-5"><span class="text-danger">*</span>用户名：</label>
                    	 <div class="col-sm-1"></div>
	                        <div class="col-sm-10">
	                        	<input id="manaid" name="id" type="hidden" value="${loginUserId }">
	                           <label class="radio-inline"> <span class="usernamen">${name }</span></label>
	                        </div>
                     </div>
                     <div class="form-group">
                        <label class="control-label col-sm-5">头像：</label>
                    	 <div class="col-sm-1"></div>
	                        <div class="col-sm-1">
	                           <input name="photoImg" id="photoImg1" type="hidden" value="${photoImg }">
	                           <img src="${photoImg }" height="30" height="30" id="headImgs"/>
	                        </div>
	                        <div class="col-sm-1"></div>
	                        <div class="col-sm-2"><input id="chooseAdminImg" type="button" value="选择"/></div>
                     </div>
                     <div class="form-group">
                        <label class="control-label col-sm-5">手机号：</label>
                    	 <div class="col-sm-1"></div>
	                        <div class="col-sm-10">
	                           <input class="form-control mobile" rows="5"  id="mobileupdate" name="mobile"></input>
	                        </div>
                     </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="subInfoForm();">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

 <div class="modal fade" id="kdxd"  role="dialog">
      <div class="modal-dialog modal-lg">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">开店向导</h4>
          </div>
          <div class="modal-body">
              <div class="shop-guide-wrapper" id="step-0" style="/* display: none; */">
                <div class="module">
                  <div class="mtitle">
                    <h1>欢迎使用系统开店向导，请按以下步骤操作</h1>
                  </div>
                  <div class="mbody">
                    <div class="stepInsts step-0">
                      <div class="stepShow">&nbsp;</div>
                      <ul>
                        <li>开始准备</li>
                        <li>接口设置</li>
                        <li>添加商品</li>
                        <li>页面设置</li>
                        <li>订单处理</li>
                        <li>完成</li>
                      </ul>
                    </div>
                    <div class="center-btn-wrapper">
                      <a href="javascript:void(0)" onclick="nextStep('1')" class="btn btn-primary">&nbsp;&nbsp;开始向导&nbsp;&nbsp;</a>
                    </div>
                  </div>
                </div>
            </div>

            <div class="shop-guide-wrapper" id="step-1">
              <div class="module">
                <div class="mtitle">
                  <div class="stepInsts step-1">
                    <div class="stepShow">&nbsp;</div>
                    <ul>
                      <li class="past">开始准备</li>
                      <li>接口设置</li>
                      <li>添加商品</li>
                      <li>页面设置</li>
                      <li>订单处理</li>
                      <li>完成</li>
                    </ul>
                  </div>

                </div>
                <div class="mbody">
                  <div class="inner-content">
                    <h1>Step1&nbsp;&nbsp;&nbsp;&nbsp;站点设置</h1>
                    <div class="content">
                      <p>站点设置用来设置店铺的基本信息，在这里配置网站名称、网站地址、网站logo、版权信息、注册时的用户协议等基本信息。</p>
                      <a href="<%=basePath%>basicset.htm?menuId=1&menuParentId=37&myselfId=38">（现在设置>>）</a>
                    </div>
                  </div>

                  <div class="corner-btn-wrapper">
                    <a href="javascript:void(0)" onclick="nextStep('2')" class="btn btn-primary">下一步</a>
                  </div>
                </div>
              </div>
            </div>

            <div class="shop-guide-wrapper" id="step-2">
              <div class="module">
                <div class="mtitle">
                  <div class="stepInsts step-2">
                    <div class="stepShow">&nbsp;</div>
                    <ul>
                      <li class="past">开始准备</li>
                      <li class="past">接口设置</li>
                      <li>添加商品</li>
                      <li>页面设置</li>
                      <li>订单处理</li>
                      <li>完成</li>
                    </ul>
                  </div>
                </div>
                <div class="mbody">
                  <div class="inner-content">
                    <h1>Step2&nbsp;&nbsp;&nbsp;&nbsp;接口设置</h1>
                    <div class="content">
                      <h1>1）邮箱接口设置</h1>
                      <p class="embbled">
                        提交有效的邮件地址（没有的去申请相关的个人邮箱或企业邮箱），你的网店会员账号的注册验证和密码找回等邮件都将使用该邮箱地址发送。<a href="<%=basePath%>emailset.htm?menuId=1&menuParentId=72&myselfId=39">（现在设置>>）</a><br>
                      </p>

                      <h1>2）支付接口设置</h1>
                      <p class="embbled">
                        系统已对接好支付宝支付接口，你需要去申请支付宝企业支付，申请成功后，修改成您的支付宝信息。
                        <br>
                        <a href="<%=basePath%>payset.htm?menuId=1&menuParentId=72&myselfId=78">（现在设置>>）</a>
                      </p>

                    </div>
                  </div>
                  <div class="corner-btn-wrapper">
                    <a href="javascript:void(0)" onclick="nextStep('1')" class="btn btn-default">上一步</a>
                    <a href="javascript:void(0)" onclick="nextStep('3')" class="btn btn-primary">下一步</a>
                  </div>
                </div>
              </div>
            </div>

            <div class="shop-guide-wrapper" id="step-3">
              <div class="module">
                <div class="mtitle">
                  <div class="stepInsts step-3">
                    <div class="stepShow">&nbsp;</div>
                    <ul>
                      <li class="past">开始准备</li>
                      <li class="past">接口设置</li>
                      <li class="past">添加商品</li>
                      <li>页面设置</li>
                      <li>订单处理</li>
                      <li>完成</li>
                    </ul>
                  </div>

                </div>
                <div class="mbody">
                  <div class="inner-content">
                    <h1>Step3&nbsp;&nbsp;&nbsp;&nbsp;添加商品</h1>
                    <div class="content">
                      <h1>1)添加商品品牌</h1>
                      <p>商品品牌是对您商品个性的一种区分，您需要在这里添加所有商品所需要的品牌。<a href="<%=basePath%>findAllBrand.htm?menuId=3&menuParentId=8&myselfId=23">（现在设置>>）</a></p>
                      <h1>2)添加商品分类</h1>
                      <p>商品分类是对您的商品进行分门别类，可以让客户对店铺销售的商品一目了然，快速找到相应商品；系统需添加配置三级分类，通过第三级分类来添加对应商品。<a href="<%=basePath%>findAllCate.htm?menuId=3&menuParentId=8&myselfId=20">（现在设置>>）</a></p>
                      <h1>3)添加商品规格</h1>
                      <p>商品规格是对第三极分类商品的品质的划分，如颜色、尺码等；通过已添加的三级分类，去添加相应的分类商品规格。<a href="<%=basePath%>findAllSpec.htm?menuId=3&menuParentId=8&myselfId=22">（现在设置>>）</a></p>
                      <h1>4)添加商品类型</h1>
                      <p>商品类型是对第三级分类商品的所有属性的划分，通过已添加的三级分类，添加相应的分类类型，添加过程中关联已添加的商品品牌和商品规格。<a href="<%=basePath%>findAllType.htm?menuId=3&menuParentId=8&myselfId=21">（现在设置>>）</a></p>
                      <h1>5)修改商品分类</h1>
                      <p>商修改下已添加好的三级分类，把对应的商品类型关联到该分类当中。<a href="<%=basePath%>findAllCate.htm?menuId=3&menuParentId=8&myselfId=20">（现在设置>>）</a></p>
                      <h1>6)添加商品</h1>
                      <p>前面配置完后，您可以添加商品了，选择相应的三级分类，添加该分类的商品信息。<a href="<%=basePath%>newUploadGoods.htm?menuId=3&menuParentId=7&myselfId=1587">（现在设置>>）</a></p>
                    </div>
                  </div>
                  <div class="corner-btn-wrapper">
                    <a href="javascript:void(0)" onclick="nextStep('2')" class="btn btn-default">上一步</a>
                    <a href="javascript:void(0)" onclick="nextStep('4')" class="btn btn-primary">下一步</a>
                  </div>
                </div>
              </div>
            </div>

            <div class="shop-guide-wrapper" id="step-4">
              <div class="module">
                <div class="mtitle">
                  <div class="stepInsts step-4">
                    <div class="stepShow">&nbsp;</div>
                    <ul>
                      <li class="past">开始准备</li>
                      <li class="past">接口设置</li>
                      <li class="past">添加商品</li>
                      <li class="past">页面设置</li>
                      <li>订单处理</li>
                      <li>完成</li>
                    </ul>
                  </div>
                </div>
                <div class="mbody">
                  <div class="inner-content">
                    <h1>Step4&nbsp;&nbsp;&nbsp;&nbsp;页面设置</h1>
                    <div class="content">
                      <p class="embbled">
                        <h1>1)挑选模板</h1>
                        系统内置了多套主流的模板，你可以挑选一套，进行你的页面设置。<a href="<%=basePath%>queryTempByType.htm?menuId=945&menuParentId=1039&myselfId=1041">（现在设置>>）</a>
                      </p>
                      <p class="embbled">
                        <h1>2)模板配置</h1>
                        选择好模板后，点击模板配置，配置商品分类、轮播广告、页面广告、楼层等信息。
                      </p>

                    </div>
                  </div>
                  <div class="corner-btn-wrapper">
                    <a href="javascript:void(0)" onclick="nextStep('3')" class="btn btn-default">上一步</a>
                    <a href="javascript:void(0)" onclick="nextStep('5')" class="btn btn-primary">下一步</a>
                  </div>
                </div>
              </div>
            </div>

            <div class="shop-guide-wrapper" id="step-5">
              <div class="module">
                <div class="mtitle">
                  <div class="stepInsts step-5">
                    <div class="stepShow">&nbsp;</div>
                    <ul>
                      <li class="past">开始准备</li>
                      <li class="past">接口设置</li>
                      <li class="past">添加商品</li>
                      <li class="past">页面设置</li>
                      <li class="past">订单处理</li>
                      <li>完成</li>
                    </ul>
                  </div>
                </div>
                <div class="mbody">
                  <div class="inner-content">
                    <h1>Step5&nbsp;&nbsp;&nbsp;&nbsp;订单处理</h1>
                    <div class="content">
                      <p>系统通过拣货、装箱、发货三个步骤来进行订单处理。</p>
                      <p class="embbled">
                        <h1>1)订单查看</h1>
                        系统可以查看所有订单信息，包含已付款和未付款的，可以对订单状态和价格进行修改。            
                        <a href="<%=basePath%>orderlist.htm?menuId=89&menuParentId=1165&myselfId=947">（现在查看>>）</a>
                      </p>
                      <p class="embbled">
                        <h1>2)拣货处理</h1>
                        在拣货列表，您可以对已付款的订单进行拣货处理。
                        <a href="<%=basePath%>orderpickinglist.htm?menuId=1565&menuParentId=1567&myselfId=1441">（现在拣货>>）</a>
                      </p>
                      <p class="embbled">
                        <h1>3)装箱处理</h1>
                        在拣货列表，您可以对已拣货的订单进行装箱处理。
                        <a href="<%=basePath%>orderdeliverylist.htm?menuId=1565&menuParentId=1567&myselfId=1467">（现在装箱>>）</a>
                      </p>
                      <p class="embbled">
                        <h1>4)出库处理</h1>
                        在拣货列表，您可以对已装箱的订单进行发货处理。
                        <a href="<%=basePath%>ordersendgoods.htm?menuId=1565&menuParentId=1567&myselfId=1471">（现在发货>>）</a>
                      </p>
                    </div>
                  </div>

                  <div class="corner-btn-wrapper">
                    <a href="javascript:void(0)" onclick="nextStep('4')" class="btn btn-default">上一步</a>
                    <a href="javascript:void(0)" onclick="nextStep('6')" class="btn btn-primary">下一步</a>
                  </div>
                </div>
              </div>
            </div>

            <div class="shop-guide-wrapper" id="step-6">
              <div class="module">
                <div class="mtitle">
                  <div class="stepInsts step-6">
                    <div class="stepShow">&nbsp;</div>
                    <ul>
                      <li class="past">开始准备</li>
                      <li class="past">接口设置</li>
                      <li class="past">添加商品</li>
                      <li class="past">页面设置</li>
                      <li class="past">订单处理</li>
                      <li class="finish">完成</li>
                    </ul>
                  </div>
                </div>
                <div class="mbody">
                  <div class="centered-content">
                    <h1>祝贺您，您已经完成了系统开店向导！</h1>
                    <p>
                      您可以
                      <a href="" id="shouye" target="_blank">您可以登录企业主站测试购物流程</a>
                    </p>
                  </div>
                  <div class="center-btn-wrapper">
                    <a href="javascript:void(0)" onclick="completeStep()" class="btn btn-primary">&nbsp;&nbsp;完&nbsp;&nbsp;成&nbsp;&nbsp;</a>
                  </div>
                </div>
              </div>
            </div>


          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
          </div>
        </div>
      </div>
    </div>

<div class="modal fade" id="appProtocalModal" role="dialog" style="z-index:1700;" >
    <div class="modal-dialog" style="z-index:1800;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">应用市场协议</h4>
            </div>
            <div class="modal-body">
                <div class="agreement_wp mt15" style="height:300px;overflow-y:scroll;">
                    <p style="font-family:HannotateSC-W5;line-height:16px;white-space:normal;">
                        本协议是您与网站（简称"本站"，网址：www.XXXX.com）所有者（以下简称为""）之间就网站服务等相关事宜所订立的契约，请您仔细阅读本注册协议，您点击"同意并继续"按钮后，本协议即构成对双方有约束力的法律文件。
                    </p>
                    <h5 style="font-family:HannotateSC-W5;white-space:normal;">
                        第1条 本站服务条款的确认和接纳
                    </h5>
                    <p style="font-family:HannotateSC-W5;line-height:16px;white-space:normal;">
                        1.1本站的各项电子服务的所有权和运作权归所有。用户同意所有注册协议条款并完成注册程序，才能成为本站的正式用户。用户确认：本协议条款是处理双方权利义务的契约，始终有效，法律另有强制性规定或双方另有特别约定的，依其规定。
                    </p>
                    <p style="font-family:HannotateSC-W5;line-height:16px;white-space:normal;">
                        1.2用户点击同意本协议的，即视为用户确认自己具有享受本站服务、下单购物等相应的权利能力和行为能力，能够独立承担法律责任。
                    </p>
                    <p style="font-family:HannotateSC-W5;line-height:16px;white-space:normal;">
                        1.3如果您在18周岁以下，您只能在父母或监护人的监护参与下才能使用本站。
                    </p>
                    <p style="font-family:HannotateSC-W5;line-height:16px;white-space:normal;">
                        1.4保留在中华人民共和国大陆地区法施行之法律允许的范围内独自决定拒绝服务、关闭用户账户、清除或编辑内容或取消订单的权利。
                    </p>
                    <h5 style="font-family:HannotateSC-W5;white-space:normal;">
                        第2条 本站服务
                    </h5>
                    <p style="font-family:HannotateSC-W5;line-height:16px;white-space:normal;">
                        2.1通过互联网依法为用户提供互联网信息等服务，用户在完全同意本协议及本站规定的情况下，方有权使用本站的相关服务。
                    </p>
                    <p style="font-family:HannotateSC-W5;line-height:16px;white-space:normal;">
                        2.2用户必须自行准备如下设备和承担如下开支：（1）上网设备，包括并不限于电脑或者其他上网终端、调制解调器及其他必备的上网装置；（2）上网开支，包括并不限于网络接入费、上网设备租用费、手机流量费等。
                    </p>
                    <h5 style="font-family:HannotateSC-W5;white-space:normal;">
                        第3条 用户信息
                    </h5>
                    <p style="font-family:HannotateSC-W5;line-height:16px;white-space:normal;">
                        3.1用户应自行诚信向本站提供注册资料，用户同意其提供的注册资料真实、准确、完整、合法有效，用户注册资料如有变动的，应及时更新其注册资料。如果用户提供的注册资料不合法、不真实、不准确、不详尽的，用户需承担因此引起的相应责任及后果，并且保留终止用户使用各项服务的权利。
                    </p>
                    <p style="font-family:HannotateSC-W5;line-height:16px;white-space:normal;">
                        3.2用户在本站进行浏览、下单购物等活动时，涉及用户真实姓名/名称、通信地址、联系电话、电子邮箱等隐私信息的，本站将予以严格保密，除非得到用户的授权或法律另有规定，本站不会向外界披露用户隐私信息。
                    </p>
                    <p style="font-family:HannotateSC-W5;line-height:16px;white-space:normal;">
                        3.3用户注册成功后，将产生用户名和密码等账户信息，您可以根据本站规定改变您的密码。用户应谨慎合理的保存、使用其用户名和密码。用户若发现任何非法使用用户账号或存在安全漏洞的情况，请立即通知本站并向公安机关报案。
                    </p>
                    <p style="font-family:HannotateSC-W5;line-height:16px;white-space:normal;">
                        3.4用户同意，拥有通过邮件、短信电话等形式，向在本站注册、购物用户、收货人发送订单信息、促销活动等告知信息的权利。
                    </p>
                    <p style="font-family:HannotateSC-W5;line-height:16px;white-space:normal;">
                        3.5用户不得将在本站注册获得的账户借给他人使用，否则用户应承担由此产生的全部责任，并与实际使用人承担连带责任。
                    </p>
                    <p style="font-family:HannotateSC-W5;line-height:16px;white-space:normal;">
                        3.6用户同意，有权使用用户的注册信息、用户名、密码等信息，登录进入用户的注册账户，进行证据保全，包括但不限于公证、见证等。
                    </p>
                    <h5 style="font-family:HannotateSC-W5;white-space:normal;">
                        第4条 用户依法言行义务
                    </h5>
                    <p style="font-family:HannotateSC-W5;line-height:16px;white-space:normal;">
                        本协议依据国家相关法律法规规章制定，用户同意严格遵守以下义务：
                    </p>
                    <p style="font-family:HannotateSC-W5;line-height:16px;white-space:normal;">
                        （1）不得传输或发表：煽动抗拒、破坏宪法和法律、行政法规实施的言论，煽动颠覆国家政权，推翻社会主义制度的言论，煽动分裂国家、破坏国家统一的的言论，煽动民族仇恨、民族歧视、破坏民族团结的言论；
                    </p>
                    <p style="font-family:HannotateSC-W5;line-height:16px;white-space:normal;">
                        （2）从中国大陆向境外传输资料信息时必须符合中国有关法规；
                    </p>
                    <p style="font-family:HannotateSC-W5;line-height:16px;white-space:normal;">
                        （3）不得利用本站从事洗钱、窃取商业秘密、窃取个人信息等违法犯罪活动；
                    </p>
                    <p style="font-family:HannotateSC-W5;line-height:16px;white-space:normal;">
                        （4）不得干扰本站的正常运转，不得侵入本站及国家计算机信息系统；
                    </p>
                    <p style="font-family:HannotateSC-W5;line-height:16px;white-space:normal;">
                        （5）不得传输或发表任何违法犯罪的、骚扰性的、中伤他人的、辱骂性的、恐吓性的、伤害性的、庸俗的，淫秽的、不文明的等信息资料；
                    </p>
                    <p style="font-family:HannotateSC-W5;line-height:16px;white-space:normal;">
                        （6）不得传输或发表损害国家社会公共利益和涉及国家安全的信息资料或言论；
                    </p>
                    <p style="font-family:HannotateSC-W5;line-height:16px;white-space:normal;">
                        （7）不得教唆他人从事本条所禁止的行为；
                    </p>
                    <p style="font-family:HannotateSC-W5;line-height:16px;white-space:normal;">
                        （8）不得利用在本站注册的账户进行牟利性经营活动；
                    </p>
                    <p style="font-family:HannotateSC-W5;line-height:16px;white-space:normal;">
                        （9）不得发布任何侵犯他人著作权、商标权等知识产权或合法权利的内容；
                    </p>
                    <p style="font-family:HannotateSC-W5;line-height:16px;white-space:normal;">
                        用户应不时关注并遵守本站不时公布或修改的各类合法规则规定。
                    </p>
                    <p style="font-family:HannotateSC-W5;line-height:16px;white-space:normal;">
                        本站保有删除站内各类不符合法律政策或不真实的信息内容而无须通知用户的权利。
                    </p>
                    <p style="font-family:HannotateSC-W5;line-height:16px;white-space:normal;">
                        若用户未遵守以上规定的，本站有权作出独立判断并采取暂停或关闭用户帐号等措施。用户须对自己在网上的言论和行为承担法律责任。
                    </p>
                    <h5 style="font-family:HannotateSC-W5;white-space:normal;">
                        第5条 商品信息
                    </h5>
                    <p style="font-family:HannotateSC-W5;line-height:16px;white-space:normal;">
                        本站上的商品价格、数量、是否有货等商品信息随时都有可能发生变动，本站不作特别通知。由于网站上商品信息的数量极其庞大，虽然本站会尽最大努力保证您所浏览商品信息的准确性，但由于众所周知的互联网技术因素等客观原因存在，本站网页显示的信息可能会有一定的滞后性或差错，对此情形您知悉并理解；欢迎纠错，并会视情况给予纠错者一定的奖励。
                    </p>
                    <p style="font-family:HannotateSC-W5;line-height:16px;white-space:normal;">
                        为表述便利，商品和服务简称为"商品"或"货物"。
                    </p>
                    <h5 style="font-family:HannotateSC-W5;white-space:normal;">
                        第6条 订单
                    </h5>
                    <p style="font-family:HannotateSC-W5;line-height:16px;white-space:normal;">
                        6.1在您下订单时，请您仔细确认所购商品的名称、价格、数量、型号、规格、尺寸、联系地址、电话、收货人等信息。收货人与用户本人不一致的，收货人的行为和意思表示视为用户的行为和意思表示，用户应对收货人的行为及意思表示的法律后果承担连带责任。
                    </p>
                    <p style="font-family:HannotateSC-W5;line-height:16px;white-space:normal;">
                        6.2除法律另有强制性规定外，双方约定如下：本站上销售方展示的商品和价格等信息仅仅是要约邀请，您下单时须填写您希望购买的商品数量、价款及支付方式、收货人、联系方式、收货地址（合同履行地点）、合同履行方式等内容；系统生成的订单信息是计算机信息系统根据您填写的内容自动生成的数据，仅是您向销售方发出的合同要约；销售方收到您的订单信息后，只有在销售方将您在订单中订购的商品从仓库实际直接向您发出时（ 以商品出库为标志），方视为您与销售方之间就实际直接向您发出的商品建立了合同关系；如果您在一份订单里订购了多种商品并且销售方只给您发出了部分商品时，您与销售方之间仅就实际直接向您发出的商品建立了合同关系；只有在销售方实际直接向您发出了订单中订购的其他商品时，您和销售方之间就订单中该其他已实际直接向您发出的商品才成立合同关系。您可以随时登录您在本站注册的账户，查询您的订单状态。
                    </p>
                    <p style="font-family:HannotateSC-W5;line-height:16px;white-space:normal;">
                        6.3由于市场变化及各种以合理商业努力难以控制的因素的影响，本站无法保证您提交的订单信息中希望购买的商品都会有货；如您拟购买的商品，发生缺货，您有权取消订单。
                    </p>
                    <h5 style="font-family:HannotateSC-W5;white-space:normal;">
                        第7条 配送
                    </h5>
                    <p style="font-family:HannotateSC-W5;line-height:16px;white-space:normal;">
                        7.1销售方将会把商品（货物）送到您所指定的收货地址，所有在本站上列出的送货时间为参考时间，参考时间的计算是根据库存状况、正常的处理过程和送货时间、送货地点的基础上估计得出的。
                    </p>
                    <p style="font-family:HannotateSC-W5;line-height:16px;white-space:normal;">
                        7.2因如下情况造成订单延迟或无法配送等，销售方不承担延迟配送的责任：
                    </p>
                    <p style="font-family:HannotateSC-W5;line-height:16px;white-space:normal;">
                        （1）用户提供的信息错误、地址不详细等原因导致的；
                    </p>
                    <p style="font-family:HannotateSC-W5;line-height:16px;white-space:normal;">
                        （2）货物送达后无人签收，导致无法配送或延迟配送的；
                    </p>
                    <p style="font-family:HannotateSC-W5;line-height:16px;white-space:normal;">
                        （3）情势变更因素导致的；
                    </p>
                    <p style="font-family:HannotateSC-W5;line-height:16px;white-space:normal;">
                        （4）不可抗力因素导致的，例如：自然灾害、交通戒严、突发战争等。
                    </p>
                    <h5 style="font-family:HannotateSC-W5;white-space:normal;">
                        第8条 所有权及知识产权条款
                    </h5>
                    <p style="font-family:HannotateSC-W5;line-height:16px;white-space:normal;">
                        8.1用户一旦接受本协议，即表明该用户主动将其在任何时间段在本站发表的任何形式的信息内容（包括但不限于客户评价、客户咨询、各类话题文章等信息内容）的财产性权利等任何可转让的权利，如著作权财产权（包括并不限于：复制权、发行权、出租权、展览权、表演权、放映权、广播权、信息网络传播权、摄制权、改编权、翻译权、汇编权以及应当由著作权人享有的其他可转让权利），全部独家且不可撤销地转让给所有，用户同意有权就任何主体侵权而单独提起诉讼。
                    </p>
                    <p style="font-family:HannotateSC-W5;line-height:16px;white-space:normal;">
                        8.2本协议已经构成《中华人民共和国著作权法》第二十五条（条文序号依照2011年版著作权法确定）及相关法律规定的著作财产权等权利转让书面协议，其效力及于用户在网站上发布的任何受著作权法保护的作品内容，无论该等内容形成于本协议订立前还是本协议订立后。
                    </p>
                    <p style="font-family:HannotateSC-W5;line-height:16px;white-space:normal;">
                        8.3用户同意并已充分了解本协议的条款，承诺不将已发表于本站的信息，以任何形式发布或授权其它主体以任何方式使用（包括但限于在各类网站、媒体上使用）。
                    </p>
                    <p style="font-family:HannotateSC-W5;line-height:16px;white-space:normal;">
                        8.4是本站的制作者,拥有此网站内容及资源的著作权等合法权利,受国家法律保护,有权不时地对本协议及本站的内容进行修改，并在本站张贴，无须另行通知用户。在法律允许的最大限度范围内，对本协议及本站内容拥有解释权。
                    </p>
                    <p style="font-family:HannotateSC-W5;line-height:16px;white-space:normal;">
                        8.5除法律另有强制性规定外，未经明确的特别书面许可,任何单位或个人不得以任何方式非法地全部或部分复制、转载、引用、链接、抓取或以其他方式使用本站的信息内容，否则，有权追究其法律责任。
                    </p>
                    <p style="font-family:HannotateSC-W5;line-height:16px;white-space:normal;">
                        8.6本站所刊登的资料信息（诸如文字、图表、标识、按钮图标、图像、声音文件片段、数字下载、数据编辑和软件），均是或其内容提供者的财产，受中国和国际版权法的保护。本站上所有内容的汇编是的排他财产，受中国和国际版权法的保护。本站上所有软件都是或其关联公司或其软件供应商的财产，受中国和国际版权法的保护。
                    </p>
                    <h5 style="font-family:HannotateSC-W5;white-space:normal;">
                        第9条 责任限制及不承诺担保
                    </h5>
                    <p style="font-family:HannotateSC-W5;line-height:16px;white-space:normal;">
                        除非另有明确的书面说明,本站及其所包含的或以其它方式通过本站提供给您的全部信息、内容、材料、产品（包括软件）和服务，均是在"按现状"和"按现有"的基础上提供的。
                    </p>
                    <p style="font-family:HannotateSC-W5;line-height:16px;white-space:normal;">
                        除非另有明确的书面说明,不对本站的运营及其包含在本网站上的信息、内容、材料、产品（包括软件）或服务作任何形式的、明示或默示的声明或担保（根据中华人民共和国法律另有规定的以外）。
                    </p>
                    <p style="font-family:HannotateSC-W5;line-height:16px;white-space:normal;">
                        不担保本站所包含的或以其它方式通过本站提供给您的全部信息、内容、材料、产品（包括软件）和服务、其服务器或从本站发出的电子信件、信息没有病毒或其他有害成分。
                    </p>
                    <p style="font-family:HannotateSC-W5;line-height:16px;white-space:normal;">
                        如因不可抗力或其它本站无法控制的原因使本站销售系统崩溃或无法正常使用导致网上交易无法完成或丢失有关的信息、记录等，会合理地尽力协助处理善后事宜。
                    </p>
                    <h5 style="font-family:HannotateSC-W5;white-space:normal;">
                        第10条 协议更新及用户关注义务
                    </h5>
                    <span style="font-family:HannotateSC-W5;line-height:16px;white-space:normal;">根据国家法律法规变化及网站运营需要，有权对本协议条款不时地进行修改，修改后的协议一旦被张贴在本站上即生效，并代替原来的协议。用户可随时登录查阅最新协议；</span><strong style="font-family:HannotateSC-W5;line-height:16px;white-space:normal;"><em>用户有义务不时关注并阅读最新版的协议及网站公告。如用户不同意更新后的协议，可以且应立即停止接受网站依据本协议提供的服务；如用户继续使用本网站提供的服务的，即视为同意更新后的协议。建议您在使用本站之前阅读本协议及本站的公告。</em></strong><span style="font-family:HannotateSC-W5;line-height:16px;white-space:normal;"> </span><span style="font-family:HannotateSC-W5;line-height:16px;white-space:normal;">如果本协议中任何一条被视为废止、无效或因任何理由不可执行，该条应视为可分的且并不影响任何其余条款的有效性和可执行性。</span><span style="font-family:HannotateSC-W5;line-height:16px;white-space:normal;"> </span>
                    <h5 style="font-family:HannotateSC-W5;white-space:normal;">
                        第11条 法律管辖和适用
                    </h5>
                    <span style="font-family:HannotateSC-W5;line-height:16px;white-space:normal;">本协议的订立、执行和解释及争议的解决均应适用在中华人民共和国大陆地区适用之有效法律（但不包括其冲突法规则）。 如发生本协议与适用之法律相抵触时，则这些条款将完全按法律规定重新解释，而其它有效条款继续有效。 如缔约方就本协议内容或其执行发生任何争议，双方应尽力友好协商解决；协商不成时，任何一方均可向有管辖权的中华人民共和国大陆地区法院提起诉讼。</span><span style="font-family:HannotateSC-W5;line-height:16px;white-space:normal;"> </span>
                    <h5 style="font-family:HannotateSC-W5;white-space:normal;">
                        第12条 其他
                    </h5>
                    <p style="font-family:HannotateSC-W5;line-height:16px;white-space:normal;">
                        12.1网站所有者是指在政府部门依法许可或备案的网站经营主体。
                    </p>
                    <p style="font-family:HannotateSC-W5;line-height:16px;white-space:normal;">
                        12.2尊重用户和消费者的合法权利，本协议及本网站上发布的各类规则、声明等其他内容，均是为了更好的、更加便利的为用户和消费者提供服务。本站欢迎用户和社会各界提出意见和建议，将虚心接受并适时修改本协议及本站上的各类规则。
                    </p>
                    <p style="font-family:HannotateSC-W5;line-height:16px;white-space:normal;">
                        12.3本协议内容中以黑体、加粗、下划线、斜体等方式显著标识的条款，请用户着重阅读。
                    </p>
                    <p style="font-family:HannotateSC-W5;line-height:16px;white-space:normal;">
                        12.4您点击本协议下方的"同意并继续"按钮即视为您完全接受本协议，在点击之前请您再次确认已知悉并完全理解本协议的全部内容。
                    </p>
                    <div>
                        <br>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="goAppMarket();">同意并继续</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
    

    <script src="<%=basePath %>js/jquery.min.js"></script>
      <script type="text/javascript" src="<%=basePath%>js/jquery.qrcode.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery.validate.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/artDialog4.1.7/artDialog.source.js?skin=default"></script>
    <script type="text/javascript" src="<%=basePath%>js/artDialog4.1.7/plugins/iframeTools.js"></script>
    <script type="text/javascript" src="http://pic.ofcard.com/themes/admin/js/jquery.cookie.js"></script>
    
    
    <script type="text/javascript">
    
	$(function(){ 
		
	    $("#InfoForm").validate();
	    $("#sendCodeForm").validate();
	    $("#updatePassForm").validate();  
	    $("#checkPassForm").validate();
	   
		$("#chooseAdminImg").click(function(){
	          i=1;
	          art.dialog.open('queryImageManageByPbAndCidForChoose.htm?location=Head&CSRFToken=${token}&size=10000', {
	              lock: true,
	              opacity:0.3,
	              width: '900px',
	              height: '400px',
	              title: '选择图片'
	          });
	      });
	      
	     var logoflag = "${indexLogo }"; 
	     if(logoflag==null||logoflag==''){
	    	//获取登录logo
	 		$.ajax({
	 			url: "ajaxGetSysBasic.htm",
	 			
	 			success: function(data){  
	 				if(data.indexLogo!=""){
	 					$("#logo").attr("src",data.indexLogo);
	 				}
	 			}});
	     }
		
		
		$.ajax({
			url:"getBasicSetName.htm", 
			success:function(data){ 
				if(data!=""){
					$('#link').attr("href",data.bsetAddress);
					$('#shouye').attr("href",data.bsetAddress);
					$("title").html("${pageName} - "+data.bsetName);
					$('#thirdlink').attr("href",data.bsetThirdAddress);
					jQuery('#erweima').qrcode({
					    render  : "canvas",
					    width   : 50,
					    height  : 50,
					    text    : data.bsetAddress
					});
				}
			}});
		
		
		
		  /* 导航弹出菜单 */
		   
		  	 $('.nav ul li').each(function(){
		 	    $(this).mouseenter(function(e){ 
		 	        var positionX =$(this).width()/2 - 200; 
		 	        $(this).append('<div class="menu_down popover bottom" style="position:absolute;top: 32px; left: ' + positionX + 'px; display: block;"><div class="arrow" style="left: 44%;"></div><div class="popover-content">' + $(this).next().html() + '</div></div>');
		 	        $(this).mouseleave(function(){
		 	            $(this).find(".menu_down").remove();
		 	        });
		 	    });
			     
			 }); 
		  
		  
		  	$.ajax({
				url: "initNotice.htm?"+Math.random(), 
				context: document.body, 
				async:true,
				success: function(data){
					
					 /* 顶部右侧功能辅助导航 */
				    var message_num = data.count,//信息数量
				        $message = '';
				          
				     	if(data.list!=null&&data.list.length!=0){
							 for (var i = 0; i < data.list.length; i++) {
									var d= new Date(data.list[i].payTime);
									 $message+='<p><a href="orderlist.htm?menuId=89&menuParentId=1165&myselfId=947&orderCode='+data.list[i].orderNo+'"';
									 if(i==0){
									     $message+='class="text-danger"';
									 }
									 $message+='>';  
							         $message+='<span>'+(setNumToTen(d.getMonth()+1))+'-'+setNumToTen(d.getDate())+'</span>';
							         $message+='<i class="glyphicon glyphicon-comment"></i>';
							         $message+='订单:'+data.list[i].orderNo+'</a>';
							         $message+='</p>';	
							}
				          $(".message_list").html($message);
				          $("#messagecount").html('您有<span>'+message_num+'</span>条未读消息');
				     	} 
				       
			}});
	    
	    
	});
	
	
	function setNumToTen(data){
		if(data<10){
			return "0"+data;
		}else{
			return data;
		}
		
	}
	
	
	function goOut(){
	    $('#goout').modal('show');
	}
	
	var uurl='getmobile.htm?CSRFToken=${token}';
	
	function fankui(){
	    if($("#feedbackcontent").val()!=''){
	        $("#feedbackcontent").removeClass('error');
	        jQuery.ajax({
	            url : "sendemailusersite.htm?CSRFToken=${token}&feedbackname=${name }&feedbackcontent="+$("#feedbackcontent").val(),
	            success : function(html) {
	                if (html == 1) {
	                    $('#feedback').modal('hide');
	                    $("#feedbackcontent").val(''); 
	                    showNoAlert('反馈成功'); 
	                } else {    
	                    $('#feedback').modal('hide');
	                    showNoAlert('反馈失败');
	                    
	                }
	            }
	        });
	    }else{
	        $("#feedbackcontent").addClass('error');
	    }
	       
	}
	 
	/**
	 * 删除单个记录的确认框
	 * @param deleteUrl 删除链接。
	 */
	function showNoAlert(tips) {
	    $("#ViewmodalDialog").remove();
	    var confirmDialog =
	    '<div class="modal fade" id="ViewmodalDialog"  role="dialog">'+
	    '    <div class="modal-dialog">'+
	    '        <div class="modal-content">'+
	    '            <div class="modal-header">'+
	    '                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>'+
	    '               <h4 class="modal-title">系统提示</h4>'+
	    '           </div>'+
	    '           <div class="modal-body">'+tips+
	    '           </div>'+
	    '           <div class="modal-footer">'+
	    '               <button type="button" class="btn btn-default" data-dismiss="modal" onclick="$(\'#ViewmodalDialog\').modal(\'hide\');">确定</button>'+
	    '           </div>'+
	    '       </div>'+
	    '   </div>'+
	    '</div>';
	    $(document.body).append(confirmDialog);
	    $('#ViewmodalDialog').modal('show');
	}
	
	var updateFlag = '';
	function modifyPwd(){
	    $('#mcode').val('');
	    $('#userkeyold').val('');
	    //修改密码
	    updateFlag = '0';
		$.ajax({
			type : 'post',
			url : uurl,
			success : function(data) {
				if(data.mobile == null || data.mobile.trim().length == 0){
				    $("#dalog-checkPass-sms").modal('show');
				}else{
					$("#viewmobile").text(data.mobile);
					$("#dalog-send-sms").modal("show");
				}
			}
		});
	}
	

	function modifyInfo(){
	    $('#mcode').val('');
	    $('#userkeyold').val('');
	    //修改资料
	    updateFlag = '1';
	    $.ajax({
			type : 'post',
			url : uurl,
			success : function(data) {
				if(data.mobile == null || data.mobile.trim().length == 0){
				    $("#dalog-checkPass-sms").modal("show");
				}else{
				    $("#viewmobile").text(data.mobile);
					$("#dalog-send-sms").modal("show");
				}
			}
		});
	}
	
	function sendCode(){
	   // var url = 'getcodecore.htm?code='+code+"&CSRFToken="+$("#hi_token").val();
	   var url = 'sendcodecore.htm?mobile='+$(".mobile").text()+"&CSRFToken=${token}";
		$.ajax({
			type : 'post',
			url : url,
			async : false,
			success : function(data) {
			    if (data > 0) {
					
			        time($("#sendCodeButton"));
					$("#sendCodeButtonTip").text('发送成功');
				} else if(data == 0) {
				    $("#sendCodeButtonTip").text('发送失败');
				}else if(data == -1){
				    $("#sendCodeButtonTip").text('60秒内只能发送一次!');
				}
			}
		});
	}
	
	var wait = 60;
	function time(o) {
        if (wait == 0) {
            o.attr("disabled", false);   
            o.val('发送验证码');
            wait = 60;
        } else { 
            o.attr("disabled", true);
            o.val("重新发送(" + wait + ")");
            wait--;
            setTimeout(function() {
                time(o);
            },
            1000);
        }
    }
	
	
	function checkCode(){
	     if( $("#sendCodeForm").valid()){
	        var url = 'getcodecore.htm?code='+$("#mcode").val()+"&CSRFToken=${token}";
			$.ajax({
				type : 'post',
				url : url,
				async : false,
				success : function(data) {
					if (data > 0) {
					    
					    if(updateFlag=='0'){
					        $("#dalog-send-sms").modal("hide");
						    $("#dalog-updatepass-sms").modal('show');
		        	     }else{
		        	         getMobileForm();
		        	         $("#dalog-send-sms").modal("hide");
		        	         $("#dalog-Info-sms").modal('show');
		        	     }
					   
					
					} else if(data == 0) {
					  $("#mcode").addClass('error');
					  $("#errorCode").html("<label class='error'>验证码不正确</label>");
						return;
					}else if(data == -1){
					    $("#mcode").addClass('error');
						  $("#errorCode").html("<label class='error'>验证码已过期</label>");
						  
						return;
					}
				}
			});
	    }
	    
	  
	}
	
	function checkPass(){
	    if($("#checkPassForm").valid()){
	        var userkey=$("#userkeyold");
			var url="checkUserKeyMain.htm?userKey="+userkey.val()+"&CSRFToken=${token}";
			$.ajax({
		         type: 'post',
		         url:url,
		         async:false,
		         success: function(data) {
		        	 if (data > 0){
		        	     $("#dalog-checkPass-sms").modal('hide');
		        	     if(updateFlag=='0'){
		        	         $("#dalog-updatepass-sms").modal('show');
		        	     }else{
		        	         getMobileForm();
		        	         $("#dalog-Info-sms").modal('show');
		        	     }
				      } else {
				         $("#userkeyold").addClass('error');
				    	 $("#oldpassuse").html('<label class="error">原始密码不正确</label>');
			          }
		         } 
			 });
	    }
	    
	}
	
	function getMobileForm(){
	    $.ajax({
			type : 'post',
			url : 'getmobile.htm?CSRFToken=${token}',
			success : function(data) {
				if(data.mobile != null && data.mobile.trim().length != 0){
				    $("#mobileupdate").val(data.mobile);
				}
			
			}
		});
	}
	function updatepass(){
	    if($("#updatePassForm").valid()){
	        var bValid = true;
		    var userkey=$("#userkey"),
		    newuserkey=$("#newuserkey");
			var url="checkUserKey.htm?userKey="+userkey.val()+"&CSRFToken=${token}";
			$.ajax({
		         type: 'post',
		         url:url,
		         async:false,
		         success: function(data) {
		        	 if (data > 0){
		        		 bValid=true&&bValid;
				      } else {
				         $("#userkey").addClass('error');
				    	 $("#oldpass").html('<label class="error">原始密码不正确</label>');
				    	  bValid=false&&bValid;
			          }
		         } 
			 });
	    
			if(bValid){
			    var url="modifiedUserKey.htm?userKey="+userkey.val()+"&newuserkey="+newuserkey.val()+"&CSRFToken=${token}";
				$.ajax({
			         type: 'post',
			         url:url,
			         async:false,
			         success: function(data) {
			        	 if (data > 0){
			        		 $("#dalog-updatepass-sms").modal('hide');
			        		  showNoAlert('修改成功'); 
					      } else {
					          $("#dalog-updatepass-sms").modal('hide');
					          $("#mcode").val('');
					          showNoAlert('修改失败'); 
				          }
			         }
				 });
			}
	    
	    
	    }
	   
		
	}
	
	
	  

      //图片回调
      function saveChoooseImageHead(url,id) {
        if(typeof (url) != 'string') {
            url = url[0];
        }
        if(url.indexOf(',')!=-1){
            url=url.substring(0,url.indexOf(','));  
        }
       
        $("#headImgs").attr("src",url);
        $("#photoImg1").val(url);

    }
    
    function subInfoForm(){
        if($("#InfoForm").valid()){
            $("#InfoForm").submit();
        }
    }
    
    
    
    
    var ckey = 'guide_step';
    var path = '/';
    $(document).ready(function() {
      $(".shop-guide-wrapper").hide();
      continueLastStep();
    });
    //根据cookie判断跳转的页面
    function continueLastStep(){
      var step = $.cookie(ckey);
      if(step==null||step=='0'){
        // 记录向导的步骤
        $.cookie(ckey, '0', { expires: 365, path: path });
        $("#step-0").show();
      }else{
        $("#step-"+step).show();
      }
    }
    // 跳转到某个步骤
    function nextStep(step){
      if(step=='1'){  //开始向导
         $.cookie('guideSidebar', null);
         $.cookie('closeAutoGuide', 'true', { expires: 1 });  //关闭自动弹出开店向导
         $("#guideSidebar").removeClass("hide-i");
      }
      // cookie值修改
      $.cookie(ckey, step, { expires: 365, path: path });
      $(".shop-guide-wrapper").hide();
      $("#step-"+step).show();
    }
    //完成
    function completeStep(){
      //隐藏向导侧栏
      $('#kdxd').modal('hide');
      nextStep('1');
    }
    //跳转到相应的页面
    function toSetPage(url){
      closeDialog();
      if(url!=null && url.indexOf("/store")==0){
        unfoldMenu(url, '2');
      }else{
        unfoldMenu(url, '1');
      }
    }

      function toB2c(furl, extended, cssTargetUrl,subUrl){  
          unfoldMenu(furl, extended, cssTargetUrl,subUrl);
          closeDialog();  
      }

    function appMarket() {
        $.ajax({
            url:'<%=httpPath%>boss/queryAppMarketKeyState.htm?CSRFToken=${token}',
            async:false,
            success:function(result) {
                if(result==-1) {
//                    showSimpleConfirmAlert("初次访问应用市场，需要获取秘钥，点击“确定”获取","goAppMarket()");
                    $("#appProtocalModal").modal("show");
                    return;
                }
                if(result==-2) {
                    showSimpleConfirmAlert("您的秘钥已过期，点击“确定”重新获取秘钥","goAppMarket()");
                    return;
                }
                window.location='<%=httpPath%>boss/app.htm?CSRFToken=${token}';
            }
        });

    }

    function goAppMarket() {
        $.ajax({
            url:'<%=httpPath%>boss/newgetLoginPatcha.htm?CSRFToken=${token}',
            success:function(data) {
                var tip = '请在系统→基本信息中填写您网站的';
                var errorName = '';
                if(data.bsetName=='') {
                    errorName += '名称、';
                }
                if(data.bsetAddress=='') {
                    errorName += '地址、';
                }
                if(data.bsetAdmin=='') {
                    errorName += '联系人、';
                }
                if(data.bsetPhone=='') {
                    errorName += '联系电话、';
                }
                if(data.bsetEmail=='') {
                    errorName += '联系邮箱、';
                }
                if(errorName!='') {
                    errorName = errorName.substr(0,errorName.lastIndexOf(''))
                    showSimpleConfirmAlert(errorName,'goBasicset()')
                    return;
                }
                window.location='<%=httpPath%>boss/app.htm?CSRFToken=${token}';
            }
        });
    }

    function goBasicset() {
        window.location = '<%=httpPath%>boss/basicset.htm';
    }

    /**
     * 简单的确认框，返回true或false
     */
    function showSimpleConfirmAlert(tips,functionName) {
        $('#modalDialog').remove();
        var confirmDialog =
                '<div class="modal fade" id="modalDialog" tabindex="-1" role="dialog">'+
                '    <div class="modal-dialog">'+
                '        <div class="modal-content">'+
                '            <div class="modal-header">'+
                '                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>'+
                '               <h4 class="modal-title">确认提示</h4>'+
                '           </div>'+
                '           <div class="modal-body">'
                +tips+
                '           </div>'+
                '           <div class="modal-footer">'+
                '             <button type="button" class="btn btn-primary" onclick="'+functionName+'">确定</button>'+
                '               <button type="button" class="btn btn-default" data-dismiss="modal" onclick="$(\'#modalDialog\').modal(\'hide\');">取消</button>'+
                '           </div>'+
                '       </div>'+
                '   </div>'+
                '</div>';
        $(document.body).append(confirmDialog);
        $('#modalDialog').modal('show');
    }

    </script>
    