<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Livrai</title>
	<style><%@include file="/WEB-INF/style.css"%></style>
</head>
<body>
	<form method="post" action="login">
	  <label for="email">Email :</label><br>
	  <input type="text" id="email" name="email"><br>
	  <label for="password">Mot de passe :</label><br>
	  <input type="password" id="password" name="password"><br>
	  <input class="button" type="submit" value="Submit">
	</form>
</body>
</html>