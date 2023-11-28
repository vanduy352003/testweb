<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<c:forEach var="item" items="${listpro}">
			<a href='<c:url value="/detail?id=${item.productID}"/>'>${item.productName}</a> <br>
			<c:if test="${item.imageLink.substring(4)!='http'}">
				<img alt="image1" src='<c:url value="/image?fname=products/${item.imageLink}"/>' width="300px">
			</c:if>
			<br>
			<c:if test="${item.imageLink.substring(4)=='http'}">
				<img alt="image" src="${item.imageLink}" width="300px">
			</c:if>
			<br>
			${item.category.cateName} <br>
			${item.description} <br>
		</c:forEach>
</body>
</html>