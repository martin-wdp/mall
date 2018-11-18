<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>

<ol class="breadcrumb Noprint">
    <c:if test="${pageNameRoot!=null&&pageNameRoot!=''}"><li>${pageNameRoot }</li></c:if>
  	<c:if test="${pageNameParent!=null&&pageNameParent!=''}"><li>${pageNameParent}</li></c:if>
	<c:if test="${pageNameChild!=null&&pageNameChild!=''}"><li class="active">${pageNameChild}</li></c:if>
</ol>
