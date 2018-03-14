<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

<!-- 

<script type="text/javascript">
function ajax() {
	return new Promise((resolve, reject) => {
		var url = 'http://localhost:8080/web_example/';
		var request = new XMLHttpRequest();
		request.onload = function() {
			if (this.status == 200) {
				resolve(this.responseText);
			} else {
				reject(new Error(this.status));
			}
		};
		request.onerror = function() {
			reject(new Error('Network error'));
		}
		request.open('DELETE', url, true);
		request.send();
	});
};
ajax();
</script>

-->
	<form action="welcome/Tsovak" method="post">
		Name: <input type="text" name="userName" /><br />
		<input type="submit" value="post welcome" />
	</form>
	
	<form action="" method="get">
		<input type="submit" value="delete" />
	</form>
	
	<form action="" method="post">
		<input type="submit" value="post" />
	</form>
	
	<form action="callMethod" method="post">
	Name: <input type="text" name="userName" /><br />
		<input type="submit" value="post callMethod" />
	</form>

	<form action="callMethod/ts" method="get">
		<input type="submit" value="ts callMethod" />
	</form>
	
	<form action="callMethod/pl" method="get">
		<input type="submit" value="pl callMethod" />
	</form>


</body>
</html>
