<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- <title>Spring3MVC with Hibernate3 CRUD Example using Annotations</title> -->
</head>
<body>

<%@ include file="header.jsp"%>
	<!-- <img src="C:\test111\pp.jpg" alt="Upload_im" /> -->
	<%
		String s = "image/image_88.jpg";
	%>
	<!-- <img src="image/image_88.jpg" alt="image" /> -->

	<%-- <img src="<%=s %>" alt="image" /> --%>

	Total Number of Image is :${count}

	<%-- <c:if test="${!empty list}">		
		<table align="left" border="1">
			<c:forEach items="${list}" var="list">
				<tr>
					<td><c:out value="${list.id}" /></td>
					<td><c:out value="${list.name}" /></td>
					<td><c:out value="${list.height}" /></td>
					<td><c:out value="${list.width}" /></td>
					
					<td align="center"><a href="edit.html?id=${article.id}">Edit</a>
						| <a href="delete.html?id=${article.id}">Delete</a> | <a
						href="view.html?id=${article.id}">View</a></td>
										
				</tr>

			</c:forEach>
		</table>
	</c:if>
 --%>

	<%-- <table align="left" border="1">
	<c:forEach items="${list}" var="item">		
			<tr>
				<td>${item}</td>
				<td>${list[1]}</td>
			</tr>					
	</c:forEach>
	</table> --%>

	<table align="left" border="1" cellspacing="1" cellpadding="10">
		<%-- <c:forEach var="i" begin="0" end="${count}" step="1"> --%>
		<c:forEach var="i" begin="0" end="${count}">
			<tr>
				<td>${i}</td>
				<td><a href="gallery.html?subname=${list[i]}">${list[i]}</a></td>
			</tr>

			<%-- <tr>
				<td><a href="gallery.html?subname=${list[i]}">${list[i]}</a></td>
				<td><a href="gallery.html?subname=${list[i+1]}">${list[i+1]}</a></td>
				<td><a href="gallery.html?subname=${list[i+2]}">${list[i+2]}</a></td>
				<td><a href="gallery.html?subname=${list[i+3]}">${list[i+3]}</a></td>
				<td><a href="gallery.html?subname=${list[i+4]}">${list[i+4]}</a></td>								
			</tr> --%>
		</c:forEach>
	</table>
</body>
</html>