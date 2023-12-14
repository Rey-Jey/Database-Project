<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Statistics</title>
</head>
<body>
<a href="rootView.jsp"target ="_self" > Back to Root View</a><br><br> 

<h1 align = "center">View User Statistics</h1>
    <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>Statistics</h2></caption>
            <tr>
                <th>ID</th>
                <th>Total Trees</th>
                <th>Total Amount Due</th>
                <th>Total Amount Paid</th>
                <th>Dates</th>
            </tr>
            <c:forEach var="stats" items="${listStat}">
                <tr style="text-align:center">
                    <td><c:out value="${stats.email}" /></td>
                    <td><c:out value="${stats.totalTrees}" /></td>
                    <td><c:out value="${stats.totalDue}" /></td>
                    <td><c:out value="${stats.totalPaid}" /></td>
                    <td><c:out value="${stats.workDates}" /></td>
                </tr>
            </c:forEach>
        </table>
</body>
</html>