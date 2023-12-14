<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Highest Trees</title>
</head>
<body>
<a href="rootView.jsp"target ="_self" > Back to Root View</a><br><br> 

<h1 align = "center">View the Highest Trees</h1>
    <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>List of Trees</h2></caption>
            <tr>
                <th>ID</th>
               	<th>Height</th>
            </tr>
            <c:forEach var="trees" items="${listTree}">
                <tr style="text-align:center">
                    <td><c:out value="${trees.treeID}" /></td>
                    <td><c:out value="${trees.height}" /></td>
                   

                    <%-- <td><c:out value="${orders.email}" /></td> --%>

                </tr>
            </c:forEach>
        </table>
</body>
</html>