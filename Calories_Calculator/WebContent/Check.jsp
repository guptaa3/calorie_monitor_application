<%@ page import="com.dcu.box.Selectedinput"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Nutrition calculator form</title>
<link rel="stylesheet" href="style.css">
<style>
@import url('https://fonts.googleapis.com/css?family=Open+Sans');

body:before {
	content: '';
	position: fixed;
	width: 100vw;
	height: 100vh;
	background-image: url("bgimage.jpg");
	background-position: center center;
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-size: cover;
	-webkit-filter: blur(3px);
	-moz-filter: blur(3px);
	-o-filter: blur(3px);
	-ms-filter: blur(3px);
	filter: blur(3px);
}
</style>
</head>
<body>
	<div class="nutrition-form">

		<h2>Calories Calculator</h2>
		<Form ACTION="productinput" METHOD="POST">
			<p>TYPE THE FOOD ITEM YOU LIKE TO SEARCH</p>

			<input type="text" name="FOODNAME" placeholder="Enter the food name">
			<input type="submit" name="SUBMIT" value="Submit">
		</form>

		<%
		Map<Integer,String> listOfItemsSelected = Selectedinput.listOfItems;
		%>

		<% if(!listOfItemsSelected.isEmpty()) { %>
		<p>You Have already selected below Items:</p>
		<% for (Map.Entry<Integer, String> pair : listOfItemsSelected.entrySet()) {  %>
		<li><p><%= pair.getValue() %></p></li>
		<% }  %>
		
		<form action="graph.jsp">
		<input type="submit" value="Show Analytics">
		</form>
		<% } %>
	</div>
	
</body>
</html>