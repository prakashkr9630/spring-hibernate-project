<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Gallery</title>
</head>
<body>
<%@ include file="header.jsp"%>
	<c:if test="${!empty list}">
		<table align="left" border="1">
			<c:forEach items="${list}" var="list">				
				<tr>
					<td><img src="${list.name}" alt="Upload_im" height="${list.height}" width="${list.width}"/></td>				
					<td><a href="view.html?id=${list.id}">${list.name}</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

</body>
</html>