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
	<table border="1">
			<tr>
				<td colspan="2"><%@ include file="/common/manager/header.jsp"%></td>
				
			</tr>
			<tr>
				<td><%@ include file="/common/manager/left.jsp"%></td>
				<td><decorator:body></decorator:body></td>
			</tr>
			<tr>
				<td colspan="2"><%@ include file="/common/manager/footer.jsp"%></td>
			</tr>
	</table>
</body>
</html>