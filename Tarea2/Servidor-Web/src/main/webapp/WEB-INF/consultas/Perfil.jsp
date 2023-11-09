<%@page import="model.EstadoSesion"%>
<%@page import="model.TipoUsuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="main.java.webservices.DtUsuario"%>
<%@page import="main.java.webservices.DtOfertaLaboral"%>
<%@page import="main.java.webservices.DtPostulante"%>
<%@page import="main.java.webservices.DtEmpresa"%>
<%@page import="main.java.webservices.EstadoOferta"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Perfil de Usuario</title>
<jsp:include page="../include/Head.jsp" />
<script src="<%= request.getContextPath() %>/resource/javaScript/addSeguirOrRemove.js"></script>
</head>
<body>
	<jsp:include page="../include/NavBar.jsp" />
	<main class="container pt-5">
		<div class="row">
			<jsp:include page="../include/Menu.jsp" />
			<%
			DtUsuario usuario = (DtUsuario) request.getAttribute("usuario");
			String tipoUsuario = (String) request.getAttribute("tipoUsuario");
			DtUsuario usuarioLogueado = (DtUsuario) session.getAttribute("usuarioLogueado");
			EstadoSesion estadoSesion = (EstadoSesion) session.getAttribute("estadoSesion");
			TipoUsuario tipoUsuarioLoguado = (TipoUsuario) session.getAttribute("tipoUsuario");
			Boolean seguidoOno = (Boolean) request.getAttribute("seguidoOno");
			%>
			
			<div class="menuMiPerfil container mt-3 col-8">
				<!-- Tab panes -->
				<div class="tab-content">
					<section>
					<div class="alert alert-danger" role="alert" id="errorAlert" style="display: none;">
						<span id="errorMessage"></span>
						<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
					</div>
						<div class="row">
							<div class="col-3">
								<%
								if (usuario.getImagenBase64() != null) {
								%>
								<img src="data:image/png;base64,<%=usuario.getImagenBase64()%>"
									alt="Imagen de Perfil" class="img img-fluid">
								<%
								}
								%>
							</div>
							<div class="col-10 col-sm-5 col-md-8  p-0" id="info">
								<h2 class="mb-3"><%=usuario.getNombre()%>
									<%=usuario.getApellido()%></h2>
								<p  class="labelNicknameEmail mb-3"><%=usuario.getNickname()%>
									/ <%=usuario.getEmail()%></p>
							</div>
							<%if (usuarioLogueado != null ) {%>
							<div class="col-1 p-0">
					            <button class="btn btn-primary" onclick="seguirDejarSeguir('<%= usuario.getNickname() %>')">
					            <i id="accionIcon" class="bi bi-person-<%= usuarioLogueado.getSeguidos().contains(usuario.getNickname())? "dash-fill":"plus" %>"></i>
					            </button>
					        </div>	
					        <%} %>							
						</div>
						<div class="menuMiPerfil container mt-3">
							<ul class="nav nav-tabs" role="tablist">
								<li class="nav-item"><a class="nav-link active"
									data-bs-toggle="tab" href="#perfilUsuario">Perfil</a></li>
								<%
								if (tipoUsuario.equals("empresa")) {
								%>
								<li class="nav-item"><a class="nav-link"
									data-bs-toggle="tab" href="#ofertaLaboralesEmpresa">Ofertas
										Laborales</a></li>
								<%
								}
								%>
							</ul>

							<!-- Tab panes -->
							<div class="tab-content">
								<div id="perfilUsuario" class="container tab-pane active">
									<br />
									<section class="">
										<table class="table table-hover table-bordered">
											<tr>
												<td><strong>Nickname</strong></td>
												<td><%=usuario.getNickname()%></td>
											</tr>
											<tr>
												<td><strong>Nombre</strong></td>
												<td><%=usuario.getNombre()%></td>
											</tr>
											<tr>
												<td><strong>Apellido</strong></td>
												<td><%=usuario.getApellido()%></td>
											</tr>
											<tr>
												<td><strong>E-mail</strong></td>
												<td><%=usuario.getEmail()%></td>
											</tr>
											<%
											if (tipoUsuario.equals("empresa")) {
												DtEmpresa empresa = (DtEmpresa) request.getAttribute("usuario");
											%>
											<tr>
												<td><strong>Sitio web</strong></td>
												<td><%=empresa.getSitioWeb()%></td>
											</tr>
											<tr>
												<td><strong>Descripción</strong></td>
												<td><%=empresa.getDescripcion()%></td>
											</tr>
											<%
											}
											%>
											<%
											if (tipoUsuario.equals("postulante")) {
												DtPostulante postulante = (DtPostulante) request.getAttribute("usuario");
											%>
											<tr>
												<td><strong>Nacionalidad</strong></td>
												<td><%=postulante.getNacionalidad()%></td>
											</tr>
											<tr>
												<td><strong>Fecha de nacimiento</strong></td>
												<td><%=postulante.getFechaNacimientoString()%></td>
											</tr>
											<%
											}
											%>
										</table>
									</section>
								</div>
								<%
								if (tipoUsuario.equals("empresa")) {
								%>
								<div id="ofertaLaboralesEmpresa" class="container tab-pane fade">
									<table class="table table-hover table-bordered">
										<thead>
											<tr>
												<th>Nombre</th>
												<th>Horario</th>
												<th>Remuneración</th>
												<th>Descripción</th>
												<th>Departamento</th>
												<th>Ciudad</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<%
												List<DtOfertaLaboral> ofertasColeccion = usuario.getOfertasColeccion();
												for (DtOfertaLaboral oferta : ofertasColeccion) {
													if (oferta.getEstadoOferta().equals(EstadoOferta.CONFIRMADA)) {
												%>
												<td><a
													href="<%=request.getContextPath()%>/oferta?nombreOferta=<%=oferta.getNombre()%>"><%=oferta.getNombre()%></a></td>
												<td><%=oferta.getHorarioInicio()%> - <%=oferta.getHorarioFinal()%></td>
												<td><%=oferta.getRemuneracion()%></td>
												<td><%=oferta.getDescripcion()%></td>
												<td><%=oferta.getDepartamento()%></td>
												<td><%=oferta.getCiudad()%></td>
											</tr>
											<%
											}
											%>
											<%
											}
											%>
										</tbody>
									</table>
								</div>
								<%
								}
								%>

							</div>
						</div>
					</section>
				</div>
			</div>
		</div>
	</main>
</body>
</html>
