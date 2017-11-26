<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add New</title>
</head>
<body>
	<%@ include file="header.jsp"%>


${newlist}

	<table border="1">
		<tr>
			<td>New Files:</td>
			<td>${count}</td>
		</tr>
		<tr>
			<td>
				<form method="get" action="/Picaso/upload.html">
					<table border="1">
						<tr>
							<c:if test="${count > 0}">
								<td colspan="2"><input type="submit" value="Add All" /></td>
							</c:if>

						</tr>
					</table>
				</form>
			</td>
		</tr>
	</table>







</body>
</html>