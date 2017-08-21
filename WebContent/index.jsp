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
		<input type="text" value="" name="Login" /><br /> <input
			type="password" value="" name="Password" /><br /> <input
			type="hidden" value="login" name="Command"> <input
			type="submit" value="SigIn" /> <br>
	</form>

	<form action="Controller" method="get">
		<input type="hidden" value="GoToRegistration" name="Command">  <br>
		<input type="submit" value="Registration"> <br>
	</form>

</body>
</html>