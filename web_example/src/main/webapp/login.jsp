<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${language}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title>Demo</title>
</head>
<body>
	<form action="welcome/Tsovak" method="post">
		Name:<input type="text" name="userName" /><br /> 
		<input type="submit" value="post" />
	</form>

	<form action="callMethod" method="get">
		<input type="submit" value="callMethod" />
	</form>
	
	<form action="" method="get">
		<input type="submit" value="delete" />
	</form>
	<script type="text/javascript"src="web/js/loadHomePage.js"></script>
</body>
</html>
