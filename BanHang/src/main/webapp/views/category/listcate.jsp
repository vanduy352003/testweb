<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class='container'>
	<form action="insert">
		<input type="submit" value="Di qua insert">
	</form>
	<c:if test="${message!=null}">
		<span>${message}</span>
	</c:if>
	<c:if test="${error!=null}">
		<span>${error}</span>
	</c:if>
	<table border="1" style="width:100%">
		<thead>
			<tr>
				<th>CateID</th>
				<th>CateName</th>
				<th>Icon</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${listcate}">
				<tr>
					<td>${item.categoryID}</td>
					<td><a href='<c:url value="/product/findproductbycate?categoryID=${item.categoryID}"/>'>${item.categoryName}</td>
					<td>${item.icon}</td>
					<!-- Lay duong dan hien tai cua project de bo vao  -->
					<td><a href='<c:url value="update?categoryID=${item.categoryID}"/>'>Update</a>
						|| <a href="delete?categoryID=${item.categoryID}">Delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>	
	</div>
</body>
</html>