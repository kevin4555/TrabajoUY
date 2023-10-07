<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="logica.datatypes.Dtusuario"%>
<%@page import="logica.datatypes.DtOfertaLaboral"%>
<%@page import="logica.datatypes.Dtpostulante"%>
<%@page import="logica.datatypes.Dtempresa"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet" />
<link href="<%=request.getContextPath()%>/recourse/css/general.css" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.0/font/bootstrap-icons.css" />

<title>Perfil de Usuario</title>
<jsp:include page="../include/Head.jsp" />
</head>
<body>
	<jsp:include page="../include/NavBar.jsp" />
	<main class="container pt-5">
		<div class="row">
			<jsp:include page="../include/Menu.jsp" />
			<%
			Dtusuario usuario = (Dtusuario) request.getAttribute("usuario");
			String tipoUsuario = (String) request.getAttribute("tipoUsuario");
			%>

			<div class="menuMiPerfil container mt-3 col-8">
				<!-- Tab panes -->
				<div class="tab-content">
					<section>
						<div class="row">
							<div class="col-4">
								<%
								if (usuario.getImagen() != null) {
								%>
								<img src="data:image/png;base64,<%=usuario.getImagenBase64()%>"
									alt="Imagen de Perfil" width="200" height="200">
								<%
								}
								%>
							</div>
							<div class="col-8">
								<h2 class="mb-3"><%=usuario.getNombre()%>
									<%=usuario.getApellido()%></h2>
								<label for="" class="labelNicknameEmail mb-3"><%=usuario.getNickname()%>
									/ <%=usuario.getEmail()%></label>
							</div>
						</div>
						<div class="menuMiPerfil container mt-3">
							<!-- Nav tabs -->

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
											  Dtempresa empresa = (Dtempresa) request.getAttribute("usuario");
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
											  Dtpostulante postulante = (Dtpostulante) request.getAttribute("usuario");
											%>
											<tr>
												<td><strong>Nacionalidad</strong></td>
												<td><%=postulante.getNacionalidad()%></td>
											</tr>
											<tr>
												<td><strong>Fecha de nacimiento</strong></td>
												<td><%=postulante.getFechaNacimiento()%></td>
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
												%>
												<td><a
													href="<%=request.getContextPath()%>/oferta?nombreOferta=<%=oferta.getNombre()%>"><%=oferta.getNombre()%></a></td>
												<td><%=oferta.getHorarioInicio()%> -
													<%=oferta.getHorarioFinal()%></td>
												<td><%=oferta.getRemuneracion()%></td>
												<td><%=oferta.getDescripcion()%></td>
												<td><%=oferta.getDepartamento()%></td>
												<td><%=oferta.getCiudad()%></td>
											</tr>
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
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
