<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script type="text/javascript" src="http://fw.qq.com/ipaddress" charset="gb2312"></script>
<script type="text/javascript">
	$(document).ready(function() { 
	    $("#ip").val(IPData[0]); 
	    $("#add").val(IPData[2]); 
	})
</script>


</head>
<body>
  <input style="color: red" type="text" id="ip" value=""/>
</body>
</html>