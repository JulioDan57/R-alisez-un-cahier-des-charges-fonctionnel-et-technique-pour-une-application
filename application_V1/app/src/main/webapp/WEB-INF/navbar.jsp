<nav>
	<c:choose>
	    <c:when test="${ admin }">
			<a class="nav-link" href="/app/clients">Clients</a>
		</c:when>
	    <c:otherwise>
			<a class="nav-link" href="/app/commande">Commande</a>
	    </c:otherwise>
	</c:choose>
	<a class="nav-link" href="/app/livraisons">Livraisons</a>
	<a class="nav-link nav-link-pull-right" href="/app/logout">Se déconnecter</a>
</nav>