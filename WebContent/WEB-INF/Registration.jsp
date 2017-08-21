<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="Controller" method="get">
		<p>Login
		<p />
		<input type="text" value="" name="Login" /><br>
		<p>Password
		<p />
		<input type="text" value="" name="Password1" /><br>
		<p>Repeat password please
		<p />
		<input type="text" value="" name="Password2" /><br> 
		${Messege}
		<input
			type="hidden" value="Registration" name="Command" /> <input
			type="submit" value="Registration" /> <br>
	</form>
</body>
</html>