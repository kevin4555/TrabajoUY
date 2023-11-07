<%@page import="jakarta.servlet.http.HttpSession"%>
<%@page import="logica.webservices.DtPostulante"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="logica.webservices.DtPostulacion"%>
<%@page import="logica.webservices.DtPaquetePublicacion"%>
<%@page import="logica.webservices.DtOfertaLaboral"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="logica.webservices.DtUsuario"%>
<%@page import="jakarta.servlet.http.HttpSession"%>
<%@page import="model.TipoUsuario"%>
<%@page import="model.EstadoSesion"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Detalles de la Oferta</title>
<link href="<%= request.getContextPath() %>/recourse/css/general.css" />
<script src="<%= request.getContextPath() %>/resource/javaScript/oferta.js"></script>
<jsp:include page="../include/Head.jsp" />


<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<body>
	<jsp:include page="../include/NavBar.jsp" />
	<main class="container pt-5">
		<div class="row">
			<jsp:include page="../include/Menu.jsp" />
			<%
			DtOfertaLaboral oferta = (DtOfertaLaboral) request.getAttribute("oferta");
			HttpSession sesion = request.getSession();
			EstadoSesion estadoSesion = (EstadoSesion) session.getAttribute("estadoSesion");
			DtUsuario usuario = (DtUsuario) session.getAttribute("usuarioLogueado");
			TipoUsuario tipoUsuario = (TipoUsuario) session.getAttribute("tipoUsuario");
			Map<String, String> mapImagenes = (Map<String, String>) request.getAttribute("mapImagenes");
			int i = 0;
			%>
			<div class="col-8">
				<section>
				<div class="alert alert-danger" role="alert" id="errorAlert" style="display: none;">
				<span id="errorMessage"></span>
				<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
				</div>
					<div class="row">
						<div class="col">
							<div class="card">
								<div class="row g-0">
									<%
									if (oferta != null && oferta.getImagenBase64() != null) {
									%>
									<div
										class="col-md-4 justify-content-center align-items-center d-flex">
										<img src="data:image/png;base64,<%=oferta.getImagenBase64()%>"
											class="img-fluid rounded-start" alt="Imagen Oferta" />
									</div>
									<%
									}
									%>
									<div class="col-md-8">
										<div class="card-body">
										<div class="card-header d-flex justify-content-between align-items-start">
                                	<h5  class=" card-title">
                                            <%= oferta.getNombre() %>
	                                </h5>
	                                <%
				                    TipoUsuario tipoUsuarioSesion = (TipoUsuario) session.getAttribute("tipoUsuario");
				                    if (tipoUsuarioSesion == TipoUsuario.POSTULANTE) { 
				                    	DtPostulante postulante = (DtPostulante) usuario; %>
				                    		<div id="estrella" class="star-icon" onclick="agregarFavorita('<%= postulante.getNickname() %>', '<%= oferta.getNombre() %>')">
			     								<i id="estrellaIcon" class="bi bi-star<%= postulante.getOfertasFavoritas().contains(oferta.getNombre())? "-fill":"" %> fs-5 orange-star" ></i>
			    							</div>	                        
				                    <% } %>	                                
			    				</div>
											
										</div>
										<div class="card-body">
										<p class="card-text"><%=oferta.getDescripcion()%></p>
											<ul class="list-group list-group-flush">
												<li class="list-group-item"><b>Remuneración:</b> <%=oferta.getRemuneracion()%></li>
												<li class="list-group-item"><b>Horario:</b> <%=oferta.getHorarioInicio()%>
													- <%=oferta.getHorarioFinal()%></li>
												<li class="list-group-item"><b>Departamento:</b> <%=oferta.getDepartamento()%></li>
												<li class="list-group-item"><b>Ciudad:</b> <%=oferta.getCiudad()%></li>
												<li class="list-group-item"><b>Fecha de alta:</b> <%=oferta.getFechaAltaString()%></li>
												<li class="list-group-item"><b>Keywords:</b> <%
 if (oferta.getKeywords() != null) {
 	for (String keyword : oferta.getKeywords()) {
 		out.print(keyword + ", ");
 	}
 }
 %></li>
												<%
												if (tipoUsuario.equals(TipoUsuario.EMPRESA) && usuario.getNickname().equals(oferta.getEmpresa())) {
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
												<a
													href="<%=request.getContextPath()%>/verPostulacion?nombreOferta=<%=oferta.getNombre()%>&nicknamePostulante=<%=usuario.getNickname()%>"
													class="btn btn-primary">Ir a Mi Postulacion</a>
											</div>
											<%
											} else {
											%>
											<div class="text-center">

												<a
													href="<%=request.getContextPath()%>/postulacion?nombreOferta=<%=oferta.getNombre()%>"
													class="btn btn-primary">Postularse</a>
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
							if (tipoUsuario.equals(TipoUsuario.EMPRESA) && usuario.getNickname().equals(oferta.getEmpresa())) {
							%>
							<%
							if (oferta.getPaqueteAsociado() != null ) {
								DtPaquetePublicacion paquete = oferta.getPaqueteAsociado();
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
													<a href="<%=paqueteUrl%>" class="btn btn-primary">Ir a
														Paquete</a>
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
						if (tipoUsuario.equals(TipoUsuario.EMPRESA) && usuario.getNickname().equals(oferta.getEmpresa())) {
						%>
						<div class="col-md-3">

							<%
							if (oferta.getPostulaciones() != null) {
							%>
							<div>
								<h1>Postulaciones</h1>
							</div>
							<form
								action="<%=request.getContextPath()%>/seleccionarPostulacion"
								id="frmExample" method="post" enctype="multipart/form-data">
								<ul id="sortable" class="list-group">
									<%
									for (DtPostulacion postulacion : oferta.getPostulaciones()) {
									%>
									<li>
										<div class="card"
											id="postulante<%=i%>">
											<%
											if (mapImagenes != null && mapImagenes.getOrDefault(postulacion.getNicknamePostulante(), null) != null) {
											%>

											<img
												src="data:image/png;base64,<%=mapImagenes.get(postulacion.getNicknamePostulante())%>"
												class="card-img-top" />
											<%
											} else {
											%>
											<img src="" class="card-img-top" />
											<%
											}
											%>

											<div class="card-body">
												<p class="card-text">
													<%
													String contextPath = request.getContextPath();
													String perfilUrl = contextPath + "/perfil?nicknameUsuario="
															+ java.net.URLEncoder.encode(postulacion.getNicknamePostulante(), "UTF-8");
													String postulacionUrl = contextPath + "/verPostulacion?nombreOferta="
															+ java.net.URLEncoder.encode(oferta.getNombre(), "UTF-8") + "&nicknamePostulante="
															+ java.net.URLEncoder.encode(postulacion.getNicknamePostulante(), "UTF-8");
													%>

													<a href="<%=perfilUrl%>"><%=postulacion.getNicknamePostulante()%></a>
												</p>
												<a href="<%=postulacionUrl%>" class="btn btn-primary">Postulacion</a>
											</div>
											<% i = i + 1; %>
										</div> <%
 }
 %>
									</li>
								</ul>

								<%
								String contextPath3 = request.getContextPath();
								%>
								<div class="espacio"></div>
								<a class="btn btn-secondary"
									href="<%=contextPath3%>/seleccionarPostulacion?nombreOferta=<%=oferta.getNombre()%>">Confirmar
									Orden</a>

							</form>
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
		src="<%=request.getContextPath()%>/resource/javaScript/sorteable.js"></script>
</body>
</html>
