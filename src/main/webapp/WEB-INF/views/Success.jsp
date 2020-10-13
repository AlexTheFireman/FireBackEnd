<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<jsp:include page="styles.css"/>
<title>Upload Done</title>
</head>
<body>
	<div align="center">
		<h1>Файл успешно загружен</h1>
	</div>
	<div align="center">
		<button class="myButton" onclick="location.href ='/fileList'" >Перейти к списку файлов</button>
	</div>
	<div align="center">
		<button class="myButton" onclick="location.href ='/file'" >Вернуться</button>
	</div>
</body>
</html>