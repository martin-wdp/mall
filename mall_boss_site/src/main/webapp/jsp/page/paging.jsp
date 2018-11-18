 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
 
<head>
<script type="text/javascript">
	function changePage(obj){
		window.location.href="${pageBean.url}?pageSize="+$(obj).val();
	}  
</script>
</head>	
<div class="table_pagenav pull-right">
            <div class="meneame">
             
             <c:if test="${pageBean.pageNo!=1 }">
               <a href="${pageBean.url}?pageSize=${pageBean.pageSize }&pageNo=${pageBean.pageNo-1 }"> ${basePath}上一页 </a>
             </c:if>
              <c:if test="${pageBean.pageNo==1 }">
              	<span class="disabled"> 上一页 </span>
             </c:if>
    
     	<c:forEach begin="${pageBean.startNo }" end="${pageBean.endNo}" varStatus="sta">
     		<c:choose>
			       <c:when test="${pageBean.pageNo==(pageBean.startNo+sta.count-1)}">
			               <span class="current"> ${pageBean.startNo+sta.count-1}</span>
			       </c:when>
			       <c:otherwise>
			      	 	<a href="${pageBean.url}?pageSize=${pageBean.pageSize }&pageNo=${pageBean.startNo+sta.count-1}">${pageBean.startNo+sta.count-1} </a>
			       </c:otherwise>
			</c:choose>
			     	
       </c:forEach>
       
          <c:if test="${pageBean.pageNo!=pageBean.totalPages}">
               <a href="${pageBean.url}?pageSize=${pageBean.pageSize }&pageNo=${pageBean.pageNo+1 }"> 下一页 </a>
             </c:if>
              <c:if test="${pageBean.pageNo==pageBean.totalPages }">
              	<span class="disabled"> 下一页 </span>
             </c:if>
             
     </div>
   </div>
<div class="table_ctrl pull-left">
    <form role="form" class="form-inline">
        <label class="control-label">每页显示：</label>
            <!--    <input type="text"  class="form-control"> -->
            <select class="form-control" onChange="changePage(this);">
                <option value="10" <c:if test="${pageBean.pageSize==10 }">selected="selected"</c:if>>10</option>
                <option value="15" <c:if test="${pageBean.pageSize==15 }">selected="selected"</c:if>>15</option>
                <option value="20" <c:if test="${pageBean.pageSize==20 }">selected="selected"</c:if>>20</option>
                <option value="30" <c:if test="${pageBean.pageSize==30 }">selected="selected"</c:if>>30</option>
                <option value="50" <c:if test="${pageBean.pageSize==50 }">selected="selected"</c:if>>50</option>
                <option value="100" <c:if test="${pageBean.pageSize==100 }">selected="selected"</c:if>>100</option>
            </select>
    </form>
</div>
   <div class="clr"></div>