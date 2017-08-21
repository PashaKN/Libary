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
		<p>Old Password
		<p />
		<input type="text" value="" name="oldPassword" /><br>
		<p>New Password
		<p />
		<input type="text" value="" name="newPassword1" /><br>
		<p>Repeat new password please
		<p />
		<input type="text" value="" name="newPassword2" /><br> 
		${Messege}
		<input
			type="hidden" value="ChangePassword" name="Command" /> <input
			type="submit" value="Change" /> <br>
	</form>
</body>
</html>