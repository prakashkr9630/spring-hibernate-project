<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Type of Article</title>
</head>
<body>


	<h4>Add Tag</h4>
	<form method="POST" action="/picaso/addtag.html">
		<table border="1">
			<tr>
				<td>Tag:</td>
				<td><input name="tagname"/></td>
				<td colspan="2"><input type="submit" name="mytag" value="Add Tag" /></td>
			</tr>
		</table>
	</form>
	
	<%-- <form:form method="POST" action="/sdnext/savecat.html">
		<table>
			<tr>
				<td><form:label path="id">Category ID:</form:label></td>
				<td><form:input path="id" value="${category.id}" readonly="true" /></td>
						
			</tr>
			<tr>
				<td><form:label path="name">Category:</form:label></td>
				<td><form:input path="name" value="${category.name}" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Add" /></td>
			</tr>
		</table>
	</form:form> --%>

	<%-- <c:if test="${!empty cats}">
		<h2>List Category</h2>
		<table align="left" border="1">
			<tr>
				<th>Category ID</th>
				<th>Category Name</th>				
			</tr>

			<c:forEach items="${cats}" var="cat">
				<tr>
					<td><c:out value="${cat.id}" /></td>
					<td><c:out value="${cat.name}" /></td>
				</tr>
			</c:forEach>
		</table>
	</c:if> --%>



</body>
</html>