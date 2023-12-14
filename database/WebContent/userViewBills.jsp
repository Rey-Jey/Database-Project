<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Your Bills</title>
</head>
<body>
<a href="activitypage.jsp"target ="_self" > Back to Home</a><br><br> 

<h1 align = "center">View Your Bills</h1>
    <div align="center">
    <form action="selectBill" method="post">
	<label>Select a bill to view its trees</label>
    	<select name = "billID">
 			<c:forEach items = "${listUserBill}" var="bills" varStatus="loop">
 				<option value="${bills.billID}"><c:out value="${bills.billID}" /></option>
 			</c:forEach>
 		</select>
 		
	<input type = "submit" value = "Select Bill"/>
	</form>
        <table border="1" cellpadding="6">
            <caption><h2>List of Bills</h2></caption>
            <tr>
                <th>ID</th>
                <th># of Trees</th>
                <th>Price</th>
                <th>Start Date</th>
                <th>End Date</th>
                <th>Status</th>
                <th>Date Posted</th>
                <%--<th>User Email</th> --%>
            </tr>
            <c:forEach var="bills" items="${listUserBill}">
                <tr style="text-align:center">
                    <td><c:out value="${bills.billID}" /></td>
                    <td><c:out value="${bills.tree_amt}"/></td>
                    <td>$<c:out value="${bills.price}" /></td>
                    <td><c:out value="${bills.start_time}" /></td>
                    <td><c:out value="${bills.end_time}" /></td>
                    <td><c:out value="${bills.status}" /></td>
                    <td><c:out value="${bills.date}" /></td>
                    <%-- <td><c:out value="${orders.email}" /></td> --%>

                </tr>
            </c:forEach>
        </table>
</body>
</html>