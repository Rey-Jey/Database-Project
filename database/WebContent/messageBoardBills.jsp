<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head><title>Message Board</title></head>
<body>
<form action = "msgGoBackBill" method = "post">
  		<input type="submit" value="Back to Bills "/>
  		</form>
<div align="center">
<h1>Message Board</h1>
		 <form action="responseBill.jsp" method="post">
		 <input type="submit" value="Write a Message"/>
		</form>
		
	<table border="1" cellpadding="6">
	<caption><h3>Selected Bill: ${BillID}</h3></caption>
            <tr>
                <th>Sender</th>
                <th>Message</th>
                <th>Date</th>
            </tr>
            <c:forEach var="convo" items="${listMessage}">
                <tr style="text-align:center">
                    <td><c:out value="${convo.email}" /></td>
                    <td><c:out value="${convo.msg}" /></td>
                    <td><c:out value="${convo.date}" /></td>
                </tr>
            </c:forEach>
        </table>
	</div>
</body>