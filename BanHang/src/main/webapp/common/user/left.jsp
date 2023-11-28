<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
.active {
	background-color: blue;
	color: white;
}
</style>
</head>
<body>
	<c:forEach var="item" items="${listcate}">
		<c:forEach var="item1" items="${countCID}">
				<c:if test="${item1.categoryID == item.categoryID && item1.total != 0}">
					<a href='<c:url value="/listprocate?categoryID=${item.categoryID}"/>'>
						<span class="${setactive==item.categoryID?'active':''}">${item.categoryName}</span> <small> (${item1.total})</small> <br>
					</a>
				</c:if>
		</c:forEach>
	</c:forEach>
</body>
</html>