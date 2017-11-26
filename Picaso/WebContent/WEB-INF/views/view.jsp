<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <%@ page import="java.util.List" %> --%>
<jsp:useBean id="random" class="java.util.Random" scope="application" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View</title>
</head>
<body>

	<%@ include file="header.jsp"%>
	<table align="left" border="1" cellpadding="5">
		<tr>
			<td><a href="view.html?id=${id-1}">Previous</a> <a
				href="view.html?id=${id+1}">Next</a> <a
				href="view.html?id=${random.nextInt(12000)}">Random</a></td>
			<td><c:forEach items="${tags}" var="item">
									${item}
				</c:forEach></td>
		</tr>
		<tr>
			<td><img src="${image}" alt="Upload_im" height="${height}"
				width="${width}" /></td>
			<%-- <td><%@ include file="addtag.jsp"%></td> --%>
			<td><form method="POST" action="/Picaso/addtag.html">
					<table border="1">
						<tr>
							<td>ID:</td>
							<td><input name="id" value="${id}" readonly="true" /></td>
						</tr>
						<tr>
							<td>Tags</td>
							<td><input name="tag" /></td>
							<td colspan="1">								
								<input type="submit"  value="Add tag" />
							</td>				
						</tr>												
						
					</table>
				</form></td>
		</tr>

		<!-- for next option -->
		<tr>
			<td><a href="view.html?id=${id-1}">Previous</a> <a
				href="view.html?id=${id+1}">Next</a> <a
				href="view.html?id=${random.nextInt(12000)}">Random</a></td>
		</tr>
	</table>
</body>
</html>