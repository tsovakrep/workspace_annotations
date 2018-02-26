<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${language}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title>Demo</title>
<script type="text/javascript" src="web/js/jquery-3.3.1.js"></script>
</head>
<body>
	<form action="go/roor" method="post">
		Name:<input type="text" name="userName" /><br /> 
		<input type="submit" value="login" />
	</form>

	<form action="simple" method="get">
		<input type="submit" value="get" />
	</form>
</body>
</html>
