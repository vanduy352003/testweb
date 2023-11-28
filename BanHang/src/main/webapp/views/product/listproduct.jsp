<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file ="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class='container'>
	<c:if test="${not empty message}}">${message}</c:if>
	<c:if test="${not empty error}}">${error}</c:if>

	<a href='<c:url value="/admin-insertproduct"/>'>Add product</a>
	<table border="1" style="width:100%">
		<thead>
			<tr>
				<th>ProID</th>
				<th>ProName</th>
				<th>Desc</th>
				<th>Price</th>
				<th>ImageLink</th>
				<th>CateID</th>
				<th>SellerID</th>
				<th>Amount</th>
				<th>Stoke</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${listproduct}">
				<tr>
					<td>${item.productID}</td>
					<td>${item.productName}</td>
					<td>${item.description}</td>
					<td>${item.price}</td>
					<td>${item.imageLink}</td>
					<td>${item.categoryID}</td>
					<td>${item.sellerID}</td>	
					<td>${item.amount}</td>
					<td>${item.stoke}</td>				
					<!-- Lay duong dan hien tai cua project de bo vao  -->
					<td><a href='<c:url value="/product/update?categoryID=${item.productID}"/>'>Update</a>
						|| <a href='<c:url value="/product/delete?categoryID=${item.productID}"/>'>Delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>	
	</div>
</body>
</html>