<%@page import="logica.datatypes.Dtpostulacion"%>
<%@page import="logica.datatypes.DtpaquetePublicacion"%>
<%@page import="logica.datatypes.DtOfertaLaboral"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="logica.datatypes.Dtusuario"%>
<%@page import="model.TipoUsuario"%>
<%@page import="model.EstadoSesion"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet" />
<link href="<%=request.getContextPath()%>/recourse/css/general.css" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.0/font/bootstrap-icons.css" />
<title>Detalles de la Oferta</title>
<jsp:include page="../include/Head.jsp" />
</head>
<body>

	<%
	DtOfertaLaboral oferta = (DtOfertaLaboral) request.getAttribute("oferta");
	HttpSession sesion = request.getSession();
	EstadoSesion estadoSesion = (EstadoSesion) session.getAttribute("estadoSesion");
	Dtusuario usuario = (Dtusuario) session.getAttribute("usuarioLogueado");
	TipoUsuario tipoUsuario = (TipoUsuario) session.getAttribute("tipoUsuario");
	Map<String,String> mapImagenes = (Map<String,String>) request.getAttribute("mapImagenes");
	%>
	<jsp:include page="../include/NavBar.jsp" />
	<main class="container pt-5">
		<div class="row">
			<jsp:include page="../include/Menu.jsp" />
			<div class="col-8">
				<section>
					<div class="row">
						<div class="col">
							<div class="card">
								<div class="row g-0">
									<%
									if (oferta != null && oferta.getImagen() != null) {
									%>
									<div
										class="col-md-4 justify-content-center align-items-center d-flex">
										<img
											src="data:image/png;base64,<%=oferta.getImagenBase64()%>"
											class="img-fluid rounded-start" alt="Imagen Oferta" />
									</div>
									<%
									}
									%>
									<div class="col-md-8">
										<div class="card-body">
											<h5 class="card-header p-0 border-0 bg-white text-start">
												<%=oferta.getNombre()%>
											</h5>
											<p class="card-text"><%=oferta.getDescripcion()%></p>
										</div>
										<div class="card-body">
											<ul class="list-group list-group-flush">
												<li class="list-group-item"><b>Remuneración:</b> <%=oferta.getRemuneracion()%></li>
												<li class="list-group-item"><b>Horario:</b> <%=oferta.getHorarioInicio()%>
													- <%=oferta.getHorarioFinal()%></li>
												<li class="list-group-item"><b>Departamento:</b> <%=oferta.getDepartamento()%></li>
												<li class="list-group-item"><b>Ciudad:</b> <%=oferta.getCiudad()%></li>
												<li class="list-group-item"><b>Fecha de alta:</b> <%=oferta.getFechaAlta()%></li>
												<li class="list-group-item"><b>Keywords:</b> 
												<%if (oferta.getKeywords() != null) {
													   for (String keyword : oferta.getKeywords()) {
													     out.print(keyword + ", ");
													   }
													 }
													 %></li>
												<%
												if (tipoUsuario.equals(TipoUsuario.EMPRESA)) {
												%>
												<li class="list-group-item"><b>Estado de la Oferta:</b>
													<%=oferta.getEstadoOferta()%></li>
												<%
												}
												%>
											</ul>
											<%
											if (tipoUsuario.equals(TipoUsuario.POSTULANTE)) {
											  if ((Boolean) request.getAttribute("estaPostulado")) {
											%>
											<div class="text-center">
												<a href="<%=request.getContextPath()%>/verPostulacion"
													class="btn btn-primary">Ir a Mi Postulacion</a>
											</div>
											<%
											} else {
											%>
											<div class="text-center">
												<a href="<%=request.getContextPath()%>/postulacion?nombreOferta=<%=oferta.getNombre()%>" class="btn btn-primary">Postularse</a>
											</div>

											<%
											}
											%>
											<%
											}
											%>
										</div>
									</div>
								</div>
							</div>

							<%
							if (tipoUsuario.equals(TipoUsuario.EMPRESA)) {
							%>
							<%
							if (oferta.getPaqueteAsociado() != null) {
							  DtpaquetePublicacion paquete = oferta.getPaqueteAsociado();
							%>
							<div>
								<h1>Paquete</h1>
								<div class="card cardOferta mb-3">
									<div class="row g-0">
										<div class="col-md-4">
											<img
												src="data:image/png;base64,<%=paquete.getImagenBase64()%>"
												class="img-fluid rounded-start" alt="..." />
										</div>
										<div class="col-md-8">
											<div class="card-body">
												<%
												String contextPath = request.getContextPath();
												String paqueteUrl = contextPath + "/paquete?nombrePaquete="
												    + java.net.URLEncoder.encode(oferta.getPaqueteAsociado().getNombre(), "UTF-8");
												%>

												<h1 class="card-title"><%=oferta.getPaqueteAsociado().getNombre()%></h1>
												<p class="card-text">
													<a href="<%=paqueteUrl%>" class="btn btn-primary">Ir
														a Paquete</a>
												</p>
											</div>
										</div>
									</div>
								</div>
							</div>
							<%
							}
							%>
							<%
							}
							%>
						</div>
						<%
						if (tipoUsuario.equals(TipoUsuario.EMPRESA)) {
						%>
						<div class="col-md-3">
							
							<%
							if (oferta.getPostulaciones() != null) {
							%>
							<div>
								<h1>Postulaciones</h1>
							</div>
							<%
							for (Dtpostulacion postulacion : oferta.getPostulaciones()) {
							%>
							<div class="card">
							<%if(mapImagenes != null && mapImagenes.getOrDefault(postulacion.getnicknamePostulante(), null) != null)  {%>
							
							<img src="data:image/png;base64,<%=mapImagenes.get(postulacion.getnicknamePostulante())%>" class="card-img-top" />
							<% }else{ %>
							<img src="" class="card-img-top" />
							<% } %>
								
								<div class="card-body">
									<p class="card-text">
										<%
										String contextPath = request.getContextPath();
										String perfilUrl = contextPath + "/perfil?nicknameUsuario="
										    + java.net.URLEncoder.encode(postulacion.getnicknamePostulante(), "UTF-8");
										%>

										<a href="<%=perfilUrl%>"><%=postulacion.getnicknamePostulante()%></a>
									</p>
									<a href="" class="btn btn-primary">Postulacion</a>
								</div>
							</div>
							<%
							}
							%>
							<%
							}
							%>
						</div>
						<%
						}
						%>

					</div>
				</section>
			</div>
		</div>
	</main>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
