<%@page import="java.time.ZoneId"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.time.LocalDate"%>
<%@page import="main.java.webservices.DtPostulacion"%>
<%@page import="model.TipoUsuario"%>
<%@page import="main.java.webservices.DtUsuario"%>
<%@page import="model.EstadoSesion"%>
<%@page import="main.java.webservices.DtPostulante"%>
<%@page import="main.java.webservices.DtOfertaLaboral"%>
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
								String video = postulacion.getLinkVideo();
								String videoId = "";

								if (video != null && video.contains("v=")) {
									int startIndex = video.indexOf("v=") + 2;
									int endIndex = video.indexOf("&", startIndex);
									if (endIndex == -1) {
										videoId = video.substring(startIndex);
									} else {
										videoId = video.substring(startIndex, endIndex);
									}
								}
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
									<%
										if(video != null && !videoId.equals("")){%>
									<div>
									<iframe class="videoPostulacion"
									
										src="https://www.youtube.com/embed/<%=videoId%>"
										frameborder="0" allowfullscreen></iframe></div>
									
										
										<br><br>
									<%} 
										else if (video != null){%>
										<div>
									<iframe class="videoPostulacion"
									
										src="<%=video%>"
										frameborder="0" allowfullscreen></iframe></div>
									
										
										<br><br>
									<%}%>
									<%if(oferta.getFechaSeleccionString() != null && postulante.getNickname().equals(usuario.getNickname()) ) {
									  
									  String contextPathpdf = request.getContextPath();
									  String pdfUrl = contextPathpdf + "/PdfServlet?nicknamePostulante=" 
									  + java.net.URLEncoder.encode(postulante.getNickname(), "UTF-8") 
									  + "&nombreOferta=" 
									  + java.net.URLEncoder.encode(oferta.getNombre(), "UTF-8");
									  
									%>
									
								<p class="card-text">
									<a href="<%=pdfUrl%>" class="btn btn-primary"> Solicitar PDF</a>
								</p>
								<%} %>
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



