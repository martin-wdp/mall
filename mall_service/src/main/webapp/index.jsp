<html>
<body>
<h2>Hello World!</h2>
<%
request.getSession().setAttribute("custName","HE HU");
%>

<%=request.getSession().getAttribute("custName") %>
<%=request.getSession().getId() %>
<%=request.getSession() %>
</body>
</html>
