<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Livrai</title>
	<style><%@include file="/WEB-INF/style.css"%></style>
</head>
<body>
<%@ include file="navbar.jsp" %>
	<h1>Nouvelle livraison</h1>
	<form method="post" action="commande">
		<label for="volume">Volume :</label><br>
		<input type="number" id="volume" name="volume"><br>
		<label for="weight">Poids :</label><br>
		<input type="number" id="weight" name="weight"><br>
		<input class="button" type="submit" value="Submit">
	</form>
</body>
</html>