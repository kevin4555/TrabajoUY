<%@page import="logica.datatypes.DtOfertaLaboral"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet" />
<link href="../../recourse/css/general.css" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.0/font/bootstrap-icons.css" />
<title>Home</title>

<%
ArrayList<String> listaKeywords = (ArrayList<String>) session.getAttribute("listaKeywords");
%>

<jsp:include page="../include/Head.jsp" />
</head>

<body class="mb-4">
	<jsp:include page="../include/NavBar.jsp" />
	<main class="container pt-5">
		<div class="row">
			<jsp:include page="../include/Menu.jsp" />
			<div class="col-8">
				<section>

					<c:forEach var="oferta" items="${listaOfertasConfirmadas}">




						<div class="card">
							<div class="row g-0">
								<div
									class="col-md-4 justify-content-center align-items-center d-flex">
									<img src="${oferta.imagen}" class="img-fluid rounded-start"
										alt="Imagen Oferta" />
								</div>
								<div class="col-md-8">
									<div class="card-body">
										<h5 class="card-header p-0 border-0 bg-white text-start">
											<c:url var="ofertaUrl" value="/oferta">
												<c:param name="nombreOferta" value="${oferta.nombre}" />
											</c:url>
										</h5>
										<p class="card-text">${oferta.descripcion}</p>
									</div>
									<div class="card-footer border-0 bg-white text-end">
										<a href="${ofertaUrl}" class="btn btn-primary">Mas Info</a>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</section>
				</div>




		
				
			</div>
	</main>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>