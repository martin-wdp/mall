<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<head>
    <script type="text/javascript">
        var formId=$("#formId").val();
        function changePage(obj){
             $("#"+$("#formId").val()).attr("action",$("#formName").val()).append("<input type='hidden' name='pageSize' value='"+$(obj).val()+"''>").submit();
        }

        function changeNextPage(pageSize,pageNo){
            $("#"+$("#formId").val()).attr("action",$("#formName").val())
                    .append("<input type='hidden' name='pageSize' value='"+pageSize+"''>")
                    .append("<input type='hidden' name='pageNo' value='"+pageNo+"''>")
                    .submit();
        }
    </script>
</head>
<div class="table_pagenav pull-right" id="rightPage">
    <div class="meneame">

        <c:if test="${pageBean.pageNo!=1 }">
            <a  href="javascript:void(0);" onclick="changeNextPage(${pageBean.pageSize },${pageBean.pageNo-1 })"> ${basePath}上一页 </a>
        </c:if>
        <c:if test="${pageBean.pageNo==1 }">
            <span class="disabled"> 上一页 </span>
        </c:if>
		<c:if test="${pageBean.startNo>2}">
			<a  href="javascript:void(0);"  onclick="changeNextPage(${pageBean.pageSize },1)" >1 </a>
			  <span class="current"> ...</span>
		</c:if>
        <c:forEach begin="${pageBean.startNo }" end="${pageBean.endNo}" varStatus="sta">
            <c:choose>
                <c:when test="${pageBean.pageNo==(pageBean.startNo+sta.count-1)}">
                    <span class="current"> ${pageBean.startNo+sta.count-1}</span>
                </c:when>
                <c:otherwise>
          			<a  href="javascript:void(0);"  onclick="changeNextPage(${pageBean.pageSize },${pageBean.startNo+sta.count-1})" >${pageBean.startNo+sta.count-1} </a>
                </c:otherwise>
            </c:choose>

        </c:forEach>

	 	<c:if test="${pageBean.endNo<pageBean.totalPages}"> 
		 	<span class="current"> ...</span>
			<a  href="javascript:void(0);"  onclick="changeNextPage(${pageBean.pageSize },${pageBean.totalPages })" >${pageBean.totalPages }</a>
	 	</c:if> 
        <c:if test="${pageBean.pageNo!=pageBean.totalPages}">
				 <a  href="javascript:void(0);"  onclick="changeNextPage(${pageBean.pageSize },${pageBean.pageNo+1 })"> 下一页 </a>
        </c:if>
        <c:if test="${pageBean.pageNo==pageBean.totalPages }">
            <span class="disabled"> 下一页 </span>
        </c:if>

    </div>
</div>
<div class="table_ctrl pull-left" id="leftpage">
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