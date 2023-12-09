<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Your Orders</title>
</head>
<body>
<a href="activitypage.jsp"target ="_self" > Back to Home</a><br><br> 

<h1 align = "center">View Your Orders</h1>
    <div align="center">
    <form action="selectOrder" method="post">
	<label>Select an order to view its trees</label>
    	<select name = "quoteID">
 			<c:forEach items = "${listUserOrder}" var="orders" varStatus="loop">
 				<option value="${orders.orderID}"><c:out value="${orders.orderID}" /></option>
 			</c:forEach>
 		</select>
 		
	<input type = "submit" value = "Select Order"/>
	</form>
        <table border="1" cellpadding="6">
            <caption><h2>List of Orders</h2></caption>
            <tr>
                <th>ID</th>
                <th># of Trees</th>
                <th>Price</th>
                <th>Start Date</th>
                <th>End Date</th>
                <th>Status</th>
                <%--<th>User Email</th> --%>
            </tr>
            <c:forEach var="orders" items="${listUserOrder}">
                <tr style="text-align:center">
                    <td><c:out value="${orders.orderID}" /></td>
                    <td><c:out value="${orders.tree_amt}"/></td>
                    <td>$<c:out value="${orders.price}" /></td>
                    <td><c:out value="${orders.start_time}" /></td>
                    <td><c:out value="${orders.end_time}" /></td>
                    <td><c:out value="${orders.status}" /></td>
                    <%-- <td><c:out value="${orders.email}" /></td> --%>

                </tr>
            </c:forEach>
        </table>
</body>
</html>