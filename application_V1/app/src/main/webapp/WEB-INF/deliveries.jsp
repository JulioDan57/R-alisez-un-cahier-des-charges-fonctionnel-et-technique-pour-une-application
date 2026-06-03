<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Livrai</title>
	<style><%@include file="/WEB-INF/style.css"%></style>
</head>
<body>
<%@ include file="navbar.jsp" %>
	<h1>Mes livraisons à venir</h1>
	<c:choose>
	    <c:when test="${ empty upcomingDeliveries }">Aucune livraison à venir</c:when>
	    <c:otherwise>
	    	<table>
				<thead>
					<tr>
						<td>Id</td>
						<c:if test="${ !empty delivery.clientName }"><td>Client</td></c:if>
						<td>Volume</td>
						<td>Poids</td>
						<td>Statut</td>
						<c:if test="${ admin }">
							<td />
						</c:if>
					</tr>
				</thead>
				<tbody>
				    <c:forEach items="${ upcomingDeliveries }" var="delivery">
					    <tr>
					    	<td><c:out value="${ delivery.id }" />
					    	<c:if test="${ !empty delivery.clientName }">
					    		<td><c:out value="${ delivery.clientName }"/>
					    	</c:if>
					    	<td><c:out value="${ delivery.volume }" />
					    	<td><c:out value="${ delivery.weight }" />
					    	<td><c:out value="${ delivery.status }" />
					    	<c:if test="${ admin }">
					    		<td>
						    	<c:if test="${ delivery.status == 'En attente' }">
							    	<form method="post" action="livraison">
							    		<input type="hidden" name="id" value="${ delivery.id }" />
							    		<input type="hidden" name="action" value="ACCEPT" />
							    		<input type="submit" value="Accepter">
							    	</form>
							    	<form method="post" action="livraisons">
							    		<input type="hidden" name="id" value="${ delivery.id }" />
							    		<input type="hidden" name="action" value="REJECT" />
							    		<input type="submit" value="Refuser">
							    	</form>
						    	</c:if>
						    	<c:if test="${ delivery.status == 'Acceptée' }">
							    	<form method="post" action="livraison">
							    		<input type="hidden" name="id" value="${ delivery.id }" />
							    		<input type="hidden" name="action" value="BILL" />
							    		<input type="number" name="price" />
							    		<input type="submit" value="Facturer">
							    	</form>
						    	</c:if>
						    	</td>
					    	</c:if>
					    </tr>
					</c:forEach>
				</tbody>
	    	</table>
	    </c:otherwise>
	</c:choose>
	
	<h1>Mes livraisons passées</h1>
	<c:choose>
	    <c:when test="${ empty pastDeliveries }">Aucune livraison terminée</c:when>
	    <c:otherwise>
	    	<table>
				<thead>
					<tr>
						<td>Id</td>
						<c:if test="${ !empty delivery.clientName }"><td>Client</td></c:if>
						<td>Volume</td>
						<td>Poids</td>
						<td>Prix</td>
						<td>Statut</td>
					</tr>
				</thead>
				<tbody>
				    <c:forEach items="${ pastDeliveries }" var="delivery">
					    <tr>
					    	<td><c:out value="${ delivery.id }" />
					    	<c:if test="${ !empty delivery.clientName }">
					    		<td><c:out value="${ delivery.clientName }"/>
					    	</c:if>
					    	<td><c:out value="${ delivery.volume }" />
					    	<td><c:out value="${ delivery.weight }" />
			    			<c:out value="${ delivery.price }" />
					    	<td><c:out value="${ delivery.status }" />
					    </tr>
					</c:forEach>
				</tbody>
	    	</table>
	    </c:otherwise>
	</c:choose>
</body>
</html>