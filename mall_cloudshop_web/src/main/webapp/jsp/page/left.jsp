<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<style>
.menu_item .menu_body ul li{
	padding-left:15px;
}
    .sidebar{min-height:427px;}
</style>
        <div class="col-lg-4 col-md-5 col-sm-6 sidebar">
          
          <%--  <div class="user_area">
            <a href="javascript:modifyInfo();">
           		  <c:if test="${empty photoImg }">
						<img class="vm mr10" id="m_img" alt=""  src="<%=basePath %>images/avatar.jpg" />
					</c:if>
					<c:if test="${not empty photoImg }">
						<img class="vm mr10" id="m_img"     alt="" src="${photoImg }" />
					</c:if>
              <p>${name }</p>
              <span>${designation }</span>
            </a>
          </div>
           --%>
          <div class="menu">
          
           <c:forEach items="${menus}" var="nav">
	                	<!-- 判断菜单不是全部 全部为null -->
	        	<c:if test="${nav.parentId ==null&&nav.menuVos!=null}">
	        		 <c:forEach items="${nav.menuVos}" var="navs">
	        		 	<c:if test="${navs.menuVos!=null }">
	        		 	 
	        		 	   
	        		 		<c:if test="${navs.id==menuId}">
	        		 	
		        		 	 	<c:forEach items="${navs.menuVos}" var="navss">
		        		 	 	  <div class="menu_item">
					              <div class="menu_title">
					                <h4><a href="javascript:;" data-pid="${navss.id}"><img src="<c:if test="${navss.id==menuParentId}">${navss.imgUrlSelected }</c:if><c:if test="${navss.id!=menuParentId}">${navss.imgUrl }</c:if>" width="18" height="18" style="margin-top:-3px;"/> ${navss.designation }</a></h4>
					              </div>
					              <div class="menu_body" <c:if test="${navss.id==menuParentId}">style="display:block;"</c:if><c:if test="${navss.id!=menuParentId}">style="display:none;"</c:if>>
					                <ul>
					               <c:if test="${navss.menuVos!=null }">
						                 <c:forEach items="${navss.menuVos}" var="navsss">
							                  <li <c:if test="${navsss.id==myselfId}"> class="active"</c:if>><a href="${navsss.url }?menuId=${navs.id}&menuParentId=${navss.id}&myselfId=${navsss.id}">${navsss.designation }</a></li>
						                  </c:forEach>
					                </c:if>
					                </ul>
					              </div>
					            </div>
		        		 		</c:forEach>
	        		 		</c:if>
	        		 	</c:if>
				            
				       </c:forEach>
            	</c:if>
             </c:forEach>
             
          </div>
       
        </div>