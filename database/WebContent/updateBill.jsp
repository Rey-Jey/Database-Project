<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head><title>Create a Bill</title></head>
<body>
	<div align="center">
		 <form action="updateBill" method="post">
		<h1>Update this Bill</h1>
			<table border="1" cellpadding="5">
				<tr>
					<th>Price: </th>
					<td align="left" colspan="3">
						<input type="number" step=0.01 name="price" size="45"  value="00000.00" onfocus="this.value=''">
					</td>
				</tr>
					<th>Message: </th>
					<td align="center" colspan="30">
						<textarea  name="msg" rows = "4" cols = "45" onfocus="this.value=''">Type Your Message Here...</textarea>
					</td>
				</tr>
				<tr>
					<td align="center" colspan="5">
						<input type="submit" value="Update"/>
					</td>
				</tr>
				
			</table>
			<br>
			<br>
			<br>
		</form>
	</div>
</body>