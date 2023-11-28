<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="admin-insertproduct" method="post" enctype="multipart/form-data">
	<label>Nhập tên product</label>
	<input type="text" name="productName">
	<br>
	<label>Nhập description</label>
	<textarea name="description" rows="" cols="5" style="width:100%"></textarea>
	<br>	
	<label>Nhập price</label>
	<input type="text" name="price">
	<br>
	<label>Nhập link hình</label>
	<input type="file" name="imageLink">
	<br>
	<label for="category">Category: </label>
		<select name="categoryID">
			<c:forEach items="${listcate}" var="item">
				<option value="${item.categoryID}" <%-- ${product.categoryID==item.categoryID?'selected':'' } --%>>${item.categoryName}</option>		
			</c:forEach>
		</select>
	<br>
	<label>Nhập tên stoke</label>
	<input type="text" name="stoke">
	<input type="submit" value="Insert Product">
	</form>
</body>
</html>