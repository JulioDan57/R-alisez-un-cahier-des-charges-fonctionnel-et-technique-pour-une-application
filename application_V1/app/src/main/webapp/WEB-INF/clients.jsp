<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Livrai</title>
	<style><%@include file="/WEB-INF/style.css"%></style>
</head>
<body>
<%@ include file="navbar.jsp" %>
	<h1>Clients</h1>
	<c:choose>
	    <c:when test="${ empty clients }">Aucun client</c:when>
	    <c:otherwise>
			<table>
				<thead>
					<tr>
						<td>Id</td>
						<td>Nom</td>
						<td>Email</td>
					</tr>
				</thead>
				<tbody>
				    <c:forEach items="${ clients }" var="client">
					    <tr>
					    	<td><c:out value="${ client.id }" /></td>
					    	<td><c:out value="${ client.name }" /></td>
					    	<td><c:out value="${ client.email }" /></td>
					    </tr>
					</c:forEach>
				</tbody>
	    </c:otherwise>
	</c:choose>
	</table>
	
	<h2 style="margin-top: 100px;">Ajouter un nouveau client</h2>
	<form method="post" action="clients">
		<label for="email">Email :</label><br>
		<input type="text" id="email" name="email"><br>
		<label for="name">Nom :</label><br>
		<input type="text" id="name" name="name"><br>
		<label for="password">Mot de passe :</label><br>
		<input type="password" id="password" name="password"><br>
		<input class="button" type="submit" value="Submit">
	</form>
</body>
</html>