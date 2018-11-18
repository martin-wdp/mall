<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
        .hideElement{
            display: none !important;
        }
    </style>
    
    <div class="page_top container-fluid Noprint">
      <div class="row">
        <div class="col-lg-4 col-md-5 col-sm-6">
          <h1 class="logo"><a href="index.htm"><img alt="" width="75%" src="${indexLogo }" id="logo"></a></h1>
        </div>
        <div class="col-lg-20 col-md-19 col-sm-18">
          <div class="top_banner">
            <div class="ctrl_banner">
              <ul class="ctrl_nav pull-right">
              
              
              <li>
                  <a href="index.htm">
                    <i class="icon icon-white icon-home"></i>&nbsp;桌面
                  </a>
                </li>
                
             
                
                <li>
                  <a href="javascript:">
                    <i class="icon icon-white icon-dashboard"></i>&nbsp;控制台
                  </a>
                  <div class="popover bottom">
                    <div class="arrow"></div>
                    <div class="popover-content">
                      <ul class="web_preview">
                       <c:if test="${managerFlag=='1' }">
                         <li class="control_panel">
                          <a href="<%=basePath %>initSetting.htm">
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
                  <a href="javascript:" class="">
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
                <%--<li>
                  <a href="javascript:">
                    <i class="icon icon-white icon-eye-open"></i>&nbsp;预览站点
                  </a>
                  <div class="popover bottom" style="min-width:183px;">
                    <div class="arrow"></div>
                    <div class="popover-content">
                      <ul class="web_preview">
                        <li class="shop_preview" style="width:43%">
                          <a href="javascript:" target="_blank" id="link">
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
                </li>--%>
                  <%--2016-01-09 wuyanbo 屏蔽Boss后台轮播小广告的显示--%>
                <li class="hideElement">
                  <a href="javascript:void(0);" onclick="$('#kdxd').modal('show')">
                    <i class="icon icon-white icon-question-sign"></i>&nbsp;向导
                  </a>
                </li>
                  <%--2016-01-09 wuyanbo 屏蔽Boss后台进入供货网的显示--%>
                <li class="hideElement">
                   <a href="javascript:" onclick="$('#feedback').modal('show')">
                    <i class="icon icon-white icon-edit"></i>&nbsp;反馈
                  </a>
                </li>
                  <%--2016-01-09 wuyanbo 屏蔽Boss后台进入供货网的显示--%>
                <li class="hideElement">
                  <a href="http://admin.qianmi.com" target="_blank">
                    <i class="icon iconfont">&#xe61d;</i>&nbsp;进入E生活
                  </a>
                </li>
                  <%--2016-01-09 wuyanbo 屏蔽Boss后进入供货网的显示--%>
                <li class="hideElement">
                  <a href="http://www.gonghuo.com" target="_blank">
                    <i class="icon icon-white icon-inbox"></i>&nbsp;进入供货网
                  </a>
                </li>
                 
                <li>
                   <a href="javascript:void(0);" onclick="goOut();">
                    <i class="icon icon-white icon-off"></i>&nbsp;退出
                  </a>
                </li>
              </ul>
                <%--2016-01-09 wuyanbo 屏蔽Boss后台版本号的显示--%>
                <p class="version" style="color: #ffffff;" class="hideElement">标准版（2.3.7-RELEASE） </p>
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
					                   		   <c:set var="tt" value="${navsss.url }?menuId=${navs.id}&menuParentId=${navss.id}&myselfId=${navsss.id}"></c:set>
					                   		   </c:if>
				                   		  </c:forEach>
			                   		  	</c:if> 
		                   			</c:forEach> 
	                   		    
		                   		  <li class="<c:if test="${navs.id==menuId}">active</c:if>">
		                   		  <a  href="${tt }">${navs.designation }</a></li>
		                   		  <c:if test="${navs.menuVos!=null}">
			                   		<div style="display:none;"> 
			                   		<c:forEach  items="${navs.menuVos}" var="navss">
			                   		  <div class="popover-content">
			                   		  	<div class="nav_sub">
			                   		  		<h4>${navss.designation }</h4>
			                   		  			<ul>
			                   		  			 <c:if test="${navss.menuVos!=null}">
			                   		  			 	<c:forEach  items="${navss.menuVos}" var="navsss">
			                   		  				<li><a href="${navsss.url }?menuId=${navs.id}&menuParentId=${navss.id}&myselfId=${navsss.id}">${navsss.designation }</a></li>  
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
		                <input type="submit" class="btn btn-primary"/>
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
                           <input class="form-control required" rows="5" id="mcode"/>
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
	                           <input class="form-control required" rows="5" name="userkey" id="userkey" type="password"/><span id="oldpass"></span>
	                        </div>
                     </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5"><span class="text-danger">*</span>新密码：</label>
                    	 <div class="col-sm-1"></div>
	                        <div class="col-sm-10">
	                           <input class="form-control required" rows="5" name="newuserkey" id="newuserkey" type="password"/>
	                        </div>
                     </div>
                    
                     <div class="form-group">
                        <label class="control-label col-sm-5 required"><span class="text-danger">*</span>重复新密码：</label>
                        <div class="col-sm-1"></div>
                        <div class="col-sm-10">
                           <input class="form-control" rows="5"  equalTo="#newuserkey" name="renewuserkey" id="renewuserkey" type="password"/>
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
	                           <input class="form-control required" rows="5"  id="userkeyold" type="password"/><span id="oldpassuse"></span>
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
	                           <input class="form-control mobile" rows="5"  id="mobileupdate" name="mobile"/>
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
    
    

    <script src="<%=basePath %>js/jquery.min.js"></script>
      <script type="text/javascript" src="<%=basePath%>js/jquery.qrcode.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery.validate.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/artDialog4.1.7/artDialog.source.js?skin=default"></script>
    <script type="text/javascript" src="<%=basePath%>js/artDialog4.1.7/plugins/iframeTools.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery.cookie.js"></script>
    
    
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

					}else if(data == -1){
					    $("#mcode").addClass('error');
						  $("#errorCode").html("<label class='error'>验证码已过期</label>");
						  

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
      
    </script>
    