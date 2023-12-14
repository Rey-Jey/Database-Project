<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Overdue Bills</title>
</head>
<body>
<a href="rootView.jsp"target ="_self" > Back to Root View</a><br><br> 

<h1 align = "center">List Overdue Bills</h1>
    <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>List of Bills</h2></caption>
            <tr>
                <th>ID</th>
                <th># of Trees</th>
                <th>Price</th>
                <th>Start Date</th>
                <th>End Date</th>
                <th>Status</th>
                <th>User Email</th>
                <th>Date Posted</th>
            </tr>
            <c:forEach var="bills" items="${listBill}">
                <tr style="text-align:center">
                    <td><c:out value="${bills.billID}" /></td>
                    <td><c:out value="${bills.tree_amt}"/></td>
                    <td>$<c:out value="${bills.price}" /></td>
                    <td><c:out value="${bills.start_time}" /></td>
                    <td><c:out value="${bills.end_time}" /></td>
                    <td><c:out value="${bills.status}" /></td>
                    <td><c:out value="${bills.email}" /></td>
                    <td><c:out value="${bills.date}" /></td>

                </tr>
            </c:forEach>
        </table>
</body>
</html>