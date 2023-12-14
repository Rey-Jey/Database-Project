<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Confirm Payment</title>
</head>
<body>
<a href="activitypage.jsp"target ="_self" > Back to Home</a><br><br> 

<h1 align = "center">Confirm Payment</h1>
    <div align="center">
    <form action = "payBillEnd">
        <table border="1" cellpadding="6">
            <caption><h2>Is this your info?</h2></caption>
            <tr>
            	<th>User Email</th>
                <th>Credit Card</th>
                <th>Phone Number</th>
                
            </tr>
            <c:forEach var="users" items="${userInfo}">
                <tr style="text-align:center">
                	<td><c:out value="${users.email}" /></td>
                    <td><c:out value="${users.credit}" /></td>
                    <td><c:out value="${users.phone}"/></td>
                </tr>
            </c:forEach>
        </table>
        <br>
		<input type="submit" value="Confirm"/>
		</form>
        
    </div>
</body>
</html>