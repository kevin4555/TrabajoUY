<%@page import="java.time.ZoneId"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.time.LocalDate"%>
<%@page import="logica.webservices.DtPostulacion"%>
<%@page import="model.TipoUsuario"%>
<%@page import="logica.webservices.DtUsuario"%>
<%@page import="model.EstadoSesion"%>
<%@page import="logica.webservices.DtPostulante"%>
<%@page import="logica.webservices.DtOfertaLaboral"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ver Postulacion</title>
<jsp:include page="../include/Head.jsp" />
</head>
<body class="mb-4">
	<%
	DtPostulante postulante = (DtPostulante) request.getAttribute("postulante");
		DtOfertaLaboral oferta = (DtOfertaLaboral) request.getAttribute("ofertas");
		DtPostulacion postulacion = (DtPostulacion) request.getAttribute("postulacion");
		EstadoSesion estadoSesion = (EstadoSesion) session.getAttribute("estadoSesion");
		DtUsuario usuario = (DtUsuario) session.getAttribute("usuarioLogueado");
		TipoUsuario tipoUsuario = (TipoUsuario) session.getAttribute("tipoUsuario");
		String contextPath = request.getContextPath();
	%>
	<jsp:include page="../include/NavBar.jsp" />
	<main class="container pt-5">
		<div class="row">
			<jsp:include page="../include/Menu.jsp" />
			<div class="col-8">
				<div class="card">
					<div class="row g-0">
						<%
						if (postulante != null && postulante.getImagenBase64() != null)
						{
						%>
						<div class="col-md-4">
							<img
								src="data:image/png;base64,<%=postulante.getImagenBase64()%>"
								class="img-fluid rounded-start" alt="imagen postulante">
						</div>
						<%
						}
						%>
						<div class="col-md-8">
							<div class="card-body">
								<h5 class="card-title">Postulación a oferta laboral</h5>
								<%
								String perfilUrl = contextPath + "/perfil?nicknameUsuario="
										+ java.net.URLEncoder.encode(postulante.getNickname(), "UTF-8");
								String fecha = postulacion.getFechaPostulacionString();
								%>
								<p class="card-text">
									<strong>Postulante: </strong><a href="<%=perfilUrl%>"><%=postulante.getNombre()%>
										<%=postulante.getApellido()%></a> <br> <br> <strong>CV
										reducido: </strong>
									<%=postulacion.getCvReducido()%>
									<br> <br> <strong>Motivación: </strong>
									<%=postulacion.getDescripMotivacion()%>
									<br> <br> <strong>Fecha de postulación: </strong>
									<%=fecha%>
									<br> <br>
								</p>
							</div>
						</div>
					</div>
				</div>
				<div class="col-3">
					<div class="card">
						<img src="data:image/png;base64,<%=oferta.getImagenBase64()%>"
							class="card-img-top" alt="Imagen Oferta">
						<div class="card-body">
							<%
							String ofertaUrl = contextPath + "/oferta?nombreOferta=" + java.net.URLEncoder.encode(oferta.getNombre(), "UTF-8");
							%>
							<h5 class="card-title text-center">
								<a href="<%=ofertaUrl%>" class="card-link"><%=oferta.getNombre()%></a>
							</h5>

						</div>
					</div>
				</div>
			</div>
		</div>
	</main>
</body>
</html>



