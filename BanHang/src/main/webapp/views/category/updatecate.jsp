<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="update" method="post">
	<label>mã category</label>
	<input type="number" name="categoryID" value="${cate.categoryID}" readonly="readonly">
	<br>
	<label>Nhập tên category</label>
	<input type="text" name="categoryName" value="${cate.categoryName}">
	<br>
	<label>Nhập icon</label>
	<input type="text" name="icon" value="${cate.icon}">
	<br>
	<input type="submit" value="Update category">
	</form>
</body>
</html>