<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${language}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title>Demo Ajax</title>
<!--  <script type="text/javascript" src="web/js/jquery-3.3.1.js"></script>-->
</head>
<body>
	<h2 id="demo">Hello Admin</h2>

	<script type="text/javascript">
		document.addEventListener("click", myFunction);
	
		function myFunction() {
			document.getElementById("demo").innerHTML = "Hello Tsovak";
		}
	</script>
	
	<form action="index/show" method="post">
		Name:<input type="text" name="userName" /><br /> 
		<input type="submit" value="login" />
	</form>
</body>
</html>
