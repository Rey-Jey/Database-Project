<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
    
<!DOCTYPE html>
<html>
<head>
<style>
div {
	margin: 1rem;
}
</style>
<meta charset="ISO-8859-1">
<title>Activity page</title>
</head>

<center><h1>Welcome! You have been successfully logged in</h1> </center>

 
	<body>
	 <center>
	 <div>
		 <form action="request" method="post">

			<input type="submit" value="Request a Quote"/>

		</form>
	</div>
	
	<div>
		<form action="viewQuotesUser" method="post">

			<input type="submit" value="View Pending Quotes"/>

		</form>
	</div>
	
	<div>
		<form action="viewQuotesAUser" method="post">

			<input type="submit" value="View Accepted Quotes"/>

		</form>
	</div>
	
	<div>
		<form action="viewQuotesRUser" method="post">

			<input type="submit" value="View Rejected Quotes"/>

		</form>
	</div>
	
	<div>
		<form action="viewOrdersUser" method="post">

			<input type="submit" value="View all orders"/>

		</form>
	</div>
	
		 <a href="login.jsp"target ="_self" > logout</a>
		 <p> You can find quotes you've made here.</p>
		 </center>
	</body>
</html>